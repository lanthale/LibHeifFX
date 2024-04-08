/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.libheiffx;

import com.drew.imaging.heif.HeifMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.foreign.*;

/**
 * Loads the native libheif lib and process the image in the native space. This
 * is the entry point of the whole libheif lib to be used
 *
 * @author Clemens Lanthaler
 */
public class LibheifImage {

    private static String arch;

    private final String imageFileURL;
    private int imageWidth;
    private int imageHeight;
    private int imageBits;
    private int imageColors;
    private int stride;
    private static String[] loadLibraryFromJar;
    private static String operatingSystem;
    private HEIFImageLoader loader;
    private String cameraModel;
    private LocalDateTime shootingDateTime;
    private static SymbolLookup loaderLookup;

    public LibheifImage(String imageFile) {
        this.imageFileURL = imageFile;
    }

    LibheifImage(HEIFImageLoader loader) {
        imageFileURL = null;
        this.loader = loader;
    }

    /**
     * Unpacks all native libs and places them in a temp directory and include
     * them in the directory
     *
     * @param tempDir directory where the native libs are placed
     * @throws IOException if it is not possible to load/unpack the native libs
     * into a temp directory
     */
    public static void loadLibs(String tempDir) throws IOException {
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Init native libs...");
        operatingSystem = System.getProperty("os.name").toUpperCase();
        arch = System.getProperty("os.arch").toUpperCase();
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "OS was: " + operatingSystem);
        if (operatingSystem.contains("WIN")) {
            loadLibraryFromJar = NativeUtils.loadLibraryFromJar(tempDir, "/lib/win-x86_64/libx265.dll", "/lib/win-x86_64/libde265.dll", "/lib/win-x86_64/heif.dll");
        } else if (operatingSystem.contains("MAC") && !arch.contains("AARCH64")) {
            loadLibraryFromJar = NativeUtils.loadLibraryFromJar(tempDir, "/lib/osx-x86_64/libc++.1.dylib", "/lib/osx-x86_64/libde265.0.dylib", "/lib/osx-x86_64/libx265.199.dylib", "/lib/osx-x86_64/libSystem.B.dylib", "/lib/osx-x86_64/libiconv.2.dylib", "/lib/osx-x86_64/libresolv.9.dylib", "/lib/osx-x86_64/libheif.1.dylib");
        } else if (operatingSystem.contains("MAC") && arch.contains("AARCH64")) {
            loadLibraryFromJar = NativeUtils.loadLibraryFromJar(tempDir, "/lib/osx-arm64/libde265.0.dylib", "/lib/osx-arm64/libx265.199.dylib", "/lib/osx-arm64/libaom.3.dylib", "/lib/osx-arm64/libjxl.0.8.dylib", "/lib/osx-arm64/libvmaf.1.dylib", "/lib/osx-arm64/librav1e.0.6.dylib", "/lib/osx-arm64/libdav1d.6.dylib", "/lib/osx-arm64/libheif.1.dylib");
        } else if (operatingSystem.contains("NUX")) {
            loadLibraryFromJar = NativeUtils.loadLibraryFromJar(tempDir, "/lib/linux-x86_64/libheif.so");
        }

        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "loadLibraryFromJar: " + Arrays.toString(loadLibraryFromJar) + " , tempdir: " + tempDir);
        for (String part : loadLibraryFromJar) {
            //System.out.println("libsarray "+part);
            new File(part).deleteOnExit();
        }
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Init native libs...finished");
        for (String strTemp : loadLibraryFromJar) {
            System.load(strTemp);
            loaderLookup = SymbolLookup.loaderLookup();
        }
    }

    /**
     * Loading the native image from the given stream and give it as byte array
     * back
     *
     * @param sourceFileAsByteArray the stream as array of bytes (raw bytes)
     * @return an array of rgb bytes of the image which can be feed to the image
     * class
     * @throws IOException if the given byte array cannot be read
     */
    public byte[] readPixelDataFromStream(byte[] sourceFileAsByteArray) throws IOException {
        if (sourceFileAsByteArray == null) {
            Logger.getLogger(LibheifImage.class.getName()).log(Level.SEVERE, null, "sourceFileAsByteArray == null!");
            throw new IllegalArgumentException("sourceFileAsByteArray == null!");
        }
        if (loadLibraryFromJar == null) {
            Logger.getLogger(LibheifImage.class.getName()).log(Level.SEVERE, null, "Please call loadLibs as static method first!");
            throw new IllegalArgumentException("Please call loadLibs as static method first!");
        }
        try (var scope = Arena.ofShared()) {
            MemorySegment heif_context_alloc = org.libheif.nativ.heif_h.heif_context_alloc();

            MemorySegment inputStreamBytes = MemorySegment.ofArray(sourceFileAsByteArray);
            //MemorySegment allocateNative = SegmentAllocator.nativeAllocator(scope.scope()).allocateArray(org.libheif.nativ.heif_h.C_CHAR, sourceFileAsByteArray);
            MemorySegment allocateNative = scope.allocateFrom(ValueLayout.JAVA_BYTE, sourceFileAsByteArray);
            MemorySegment errorOpening = org.libheif.nativ.heif_h.heif_context_read_from_memory_without_copy(scope, heif_context_alloc, allocateNative, inputStreamBytes.byteSize(), MemorySegment.NULL);
            int errorcode = org.libheif.nativ.heif_error.code(errorOpening);
            if (errorcode > 0) {
                throw new IOException("Cannot open image file because reading of file data is not possible  (" + errorcode + ")!");
            }

            int heif_context_get_number_of_top_level_images = org.libheif.nativ.heif_h.heif_context_get_number_of_top_level_images(heif_context_alloc);

            //MemorySegment primary_image_handle_seg = MemorySegment.allocateNative(org.libheif.nativ.heif_h.C_POINTER, scope.scope());
            MemorySegment primary_image_handle_seg = scope.allocate(org.libheif.nativ.heif_h.C_POINTER);
            MemorySegment errorhandle = org.libheif.nativ.heif_h.heif_context_get_primary_image_handle(scope, heif_context_alloc, primary_image_handle_seg);
            errorcode = org.libheif.nativ.heif_error.code(errorhandle);
            if (errorcode > 0) {
                throw new IOException("Cannot open image file because codec is not supported (" + errorcode + ")!");
            }
            MemorySegment primary_image_handle = primary_image_handle_seg.get(ValueLayout.ADDRESS, 0);

            //MemorySegment heif_image_seg = MemorySegment.allocateNative(org.libheif.nativ.heif_h.C_POINTER, scope.scope());
            MemorySegment heif_image_seg = scope.allocate(org.libheif.nativ.heif_h.C_POINTER);
            org.libheif.nativ.heif_h.heif_decode_image(scope, primary_image_handle, heif_image_seg, org.libheif.nativ.heif_h.heif_colorspace_RGB(), org.libheif.nativ.heif_h.heif_chroma_interleaved_RGBA(), MemorySegment.NULL);
            MemorySegment heif_image = heif_image_seg.get(ValueLayout.ADDRESS, 0);

            imageHeight = org.libheif.nativ.heif_h.heif_image_get_height(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved());
            imageWidth = org.libheif.nativ.heif_h.heif_image_get_width(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved());
            imageBits = org.libheif.nativ.heif_h.heif_image_get_bits_per_pixel(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved());
            imageColors = 4;

            //MemorySegment strideSegm = MemorySegment.allocateNative(org.libheif.nativ.heif_h.C_INT, scope.scope());
            MemorySegment strideSegm = scope.allocate(org.libheif.nativ.heif_h.C_INT);
            MemorySegment image_data = org.libheif.nativ.heif_h.heif_image_get_plane_readonly(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved(), strideSegm);
            stride = strideSegm.toArray(ValueLayout.JAVA_INT)[0];
            int pos = 0;
            int[] data = new int[imageHeight * imageWidth];
            for (var i = 0; i < imageHeight; i++) {
                long offset = image_data.address() + stride * i;
                MemorySegment asSegmentRestricted = MemorySegment.ofAddress(offset);
                int[] toIntArray = asSegmentRestricted.reinterpret(stride).toArray(ValueLayout.JAVA_INT);
                for (int j = 0; j < toIntArray.length; j = j + 1) {
                    int rgba = toIntArray[j];
                    int r = (rgba) & 0xFF;
                    int g = (rgba >> 8) & 0xFF;
                    int b = (rgba >> 16) & 0xFF;
                    int a = (rgba >> 24) & 0xFF;
                    int finalValue = (int) (a << 24 | r << 16 | g << 8 | b);
                    data[pos] = rgba;
                    pos = pos + 1;
                }
            }
            byte[] retData = new byte[imageWidth * imageHeight * getNumBands()];
            IntToByte(retData, data, imageWidth * imageHeight);
            org.libheif.nativ.heif_h.heif_context_free(heif_context_alloc);
            data = null;
            heif_context_alloc = null;
            return retData;
        }
    }

    private int IntToByte(byte arrayDst[], int arrayOrg[], int maxOrg) {
        int i;
        int idxDst;
        int maxDst;
        //
        maxDst = maxOrg * getNumBands();
        //
        if (arrayDst == null) {
            return 0;
        }
        if (arrayOrg == null) {
            return 0;
        }
        if (arrayDst.length < maxDst) {
            return 0;
        }
        if (arrayOrg.length < maxOrg) {
            return 0;
        }
        //
        idxDst = 0;
        for (i = 0; i < maxOrg; i++) {
            // Copia o int, byte a byte.
            arrayDst[idxDst] = (byte) (arrayOrg[i]);
            idxDst++;
            arrayDst[idxDst] = (byte) (arrayOrg[i] >> 8);
            idxDst++;
            arrayDst[idxDst] = (byte) (arrayOrg[i] >> 16);
            idxDst++;
            arrayDst[idxDst] = (byte) (arrayOrg[i] >> 24);
            idxDst++;
        }
        //
        return idxDst;
    }

    /**
     * Reads from give URL as string the file. Please use before constructor
     * LibrawImage(String imageFile)
     *
     * @return int array of RGB values which can be further processed
     * @throws IOException if the given file cannot be read
     */
    public synchronized int[] readPixelData() throws IOException {
        if (imageFileURL == null) {
            Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "imageFileURL == null! " + imageFileURL);
            throw new IllegalArgumentException("imageFileURL == null!");
        }
        if (loadLibraryFromJar == null) {
            Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Please call loadLibs as static method first!");
            throw new IllegalArgumentException("Please call loadLibs as static method first!");
        }
        try (var scope = Arena.ofShared()) {
            MemorySegment heif_context_alloc = org.libheif.nativ.heif_h.heif_context_alloc();

            //MemorySegment errorOpening = org.libheif.nativ.heif_h.heif_context_read_from_file(scope, heif_context_alloc, SegmentAllocator.nativeAllocator(scope.scope()).allocateUtf8String(imageFileURL), MemorySegment.NULL);
            MemorySegment errorOpening = org.libheif.nativ.heif_h.heif_context_read_from_file(scope, heif_context_alloc, scope.allocateFrom(imageFileURL), MemorySegment.NULL);
            int errorcode = org.libheif.nativ.heif_error.code(errorOpening);
            if (errorcode > 0) {
                throw new IOException("Cannot open image file because reading of file data is not possible  (" + errorcode + ")!");
            }
            int heif_context_get_number_of_top_level_images = org.libheif.nativ.heif_h.heif_context_get_number_of_top_level_images(heif_context_alloc);

            MemorySegment primary_image_handle_seg = scope.allocate(org.libheif.nativ.heif_h.C_POINTER);
            MemorySegment errorhandle = org.libheif.nativ.heif_h.heif_context_get_primary_image_handle(scope, heif_context_alloc, primary_image_handle_seg);
            errorcode = org.libheif.nativ.heif_error.code(errorhandle);
            if (errorcode > 0) {
                throw new IOException("Cannot open image file because codec is not supported (" + errorcode + ")!");
            }
            MemorySegment primary_image_handle = primary_image_handle_seg.get(ValueLayout.ADDRESS, 0);

            MemorySegment heif_image_seg = scope.allocate(org.libheif.nativ.heif_h.C_POINTER);
            org.libheif.nativ.heif_h.heif_decode_image(scope, primary_image_handle, heif_image_seg, org.libheif.nativ.heif_h.heif_colorspace_RGB(), org.libheif.nativ.heif_h.heif_chroma_interleaved_RGBA(), MemorySegment.NULL);
            MemorySegment heif_image = heif_image_seg.get(ValueLayout.ADDRESS, 0);

            imageHeight = org.libheif.nativ.heif_h.heif_image_get_height(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved());
            imageWidth = org.libheif.nativ.heif_h.heif_image_get_width(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved());
            imageBits = org.libheif.nativ.heif_h.heif_image_get_bits_per_pixel(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved());
            imageColors = 4;

            MemorySegment strideSegm = scope.allocate(org.libheif.nativ.heif_h.C_INT);
            MemorySegment image_data = org.libheif.nativ.heif_h.heif_image_get_plane_readonly(heif_image, org.libheif.nativ.heif_h.heif_channel_interleaved(), strideSegm);
            stride = strideSegm.toArray(ValueLayout.JAVA_INT)[0];
            int pos = 0;
            int[] data = new int[imageHeight * imageWidth];
            for (var i = 0; i < imageHeight; i++) {
                long offset = image_data.address() + stride * i;
                MemorySegment asSegmentRestricted = MemorySegment.ofAddress(offset);
                int[] toIntArray = asSegmentRestricted.reinterpret(stride).toArray(ValueLayout.JAVA_INT);
                for (int j = 0; j < toIntArray.length; j = j + 1) {
                    int rgba = toIntArray[j];
                    int r = (rgba) & 0xFF;
                    int g = (rgba >> 8) & 0xFF;
                    int b = (rgba >> 16) & 0xFF;
                    int a = (rgba >> 24) & 0xFF;
                    data[pos] = (int) (a << 24 | r << 16 | g << 8 | b);
                    pos = pos + 1;
                }
            }
            org.libheif.nativ.heif_h.heif_context_free(heif_context_alloc);
            heif_context_alloc = null;
            return data;
        }
    }

    /**
     * Convert RGB byte array to the corresponding INT values for javafx
     *
     * @param rawBytes byte array of RGB bytes
     * @return an int array of the RGB bytes
     */
    private synchronized int[] convertToINT(byte[] rawBytes) {
        int[] raw = new int[rawBytes.length * 4 / 3];
        for (int j = 0; j < rawBytes.length / 3; j++) {
            raw[j] = 0xFF000000
                    | ((rawBytes[3 * j + 0] & 0xFF) << 16)
                    | ((rawBytes[3 * j + 1] & 0xFF) << 8)
                    | ((rawBytes[3 * j + 2] & 0xFF));
        }
        return raw;
    }

    /**
     * Retrievs for the given file in the constructor the metadata Important:
     * The timestamps are in EpocheInSeconds and not milliseconds
     *
     * @return Hashmap of all meta data
     * @throws IOException
     */
    public HashMap<String, String> getMetaData() throws IOException {
        if (imageFileURL == null) {
            Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "imageFileURL == null! " + imageFileURL);
            throw new IllegalArgumentException("imageFileURL == null!");
        }
        if (loadLibraryFromJar == null) {
            Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Please call loadLibs as static method first!");
            throw new IllegalArgumentException("Please call loadLibs as static method first!");
        }
        for (String strTemp : loadLibraryFromJar) {
            System.load(strTemp);
            SymbolLookup.loaderLookup();
        }
        HashMap<String, String> retMap = new HashMap<>();
        Metadata metadata;
        FileInputStream in = new FileInputStream(new File(imageFileURL));
        metadata = HeifMetadataReader.readMetadata(in);
        String invalidTagNames = "Red TRC;Green TRC;Blue TRC";
        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                if (!invalidTagNames.contains(tag.getTagName())) {
                    retMap.put(tag.getTagName(), tag.getDescription());
                }
            }
        }
        return retMap;
    }

    /**
     * URL of the given file
     *
     * @return the URL to be read by readPixelData
     */
    public String getImageFileURL() {
        return imageFileURL;
    }

    /**
     * Gets the width of the native image
     *
     * @return width as short (int) of the native image
     */
    public int getImageWidth() {
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Native image width " + imageWidth);
        return imageWidth;
    }

    /**
     * Gets the height of the given image
     *
     * @return height as short (int) of the given image
     */
    public int getImageHeight() {
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Native image height " + imageHeight);
        return imageHeight;
    }

    /**
     * Image bit depth (e.g. 16bit, 8 bit, ...)
     *
     * @return the image bits
     */
    public int getImageBits() {
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Native image bits " + imageBits);
        return imageBits;
    }

    /**
     * Image colors
     *
     * @return if RGB or ARGB (3 or 4 color model)
     */
    public int getImageColors() {
        Logger.getLogger(LibheifImage.class.getName()).log(Level.FINEST, null, "Native image colors " + imageColors);
        return imageColors;
    }

    /**
     * One line of the native image
     *
     * @return line of the image
     */
    public int getStride() {
        return stride;
    }

    /**
     * same as getStride but without the including the width of the image
     *
     * @return numBands
     */
    public int getNumBands() {
        return (imageBits / 8);
    }

    public String getCameraModel() {
        return cameraModel;
    }

    public LocalDateTime getShootingDateTime() {
        return shootingDateTime;
    }

    /**
     * String representation of the image
     *
     * @return debug string which values are stored in the class
     */
    @Override
    public String toString() {
        return "LibHeifImage{" + "imageFileURL=" + imageFileURL + ", imageWidth=" + imageWidth + ", imageHeight=" + imageHeight + ", imageBits=" + imageBits + ", imageColors=" + imageColors + ", stride=" + stride + '}';
    }

}
