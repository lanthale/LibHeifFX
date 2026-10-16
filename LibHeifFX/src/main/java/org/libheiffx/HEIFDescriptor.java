/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.libheiffx;

import com.sun.javafx.iio.ImageFormatDescription;
import com.sun.javafx.iio.common.ImageDescriptor;

public class HEIFDescriptor extends ImageDescriptor {

    private static final String formatName = "HEIC/AVIF";

    private static final String[] extensions = {"heic", "heif", "heix", "hif", "avif"};

    // "ftyp" as hex, located at bytes 4-7 of every ISO-BMFF file
    private static final String FTYP_HEX = "66747970";

    // All known HEIF/AVIF major brands, hex encoded (bytes 8-11)
    private static final String[] BRANDS_HEX = {
        "68656963", // heic (Apple HEIF)
        "68656978", // heix
        "68656966", // heif
        "6865696D", // heim
        "68656973", // heis
        "68656976", // hevm
        "68656973", // hevs
        "6D696631", // mif1 (generic HEIF, Canon/Nikon/Sony .hif)
        "6D736631", // msf1
        "6D696362", // micb
        "6D696166", // miaf
        "61766966", // avif
        "61766973" // avis
    };

    // Common ftyp box sizes in bytes as 8-digit hex (bytes 0-3):
    // 0x14 (20), 0x18 (24), 0x1C (28), 0x20 (32), 0x24 (36)
    private static final String[] SIZES_HEX = {
        "00000014", "00000018", "0000001C", "00000020", "00000024"
    };

    // ftyp size = 8 + 4*n (major + minor + n compatible brands)
    // => alle Vielfachen von 4 von 0x0C bis 0x40 abdecken
    private static final int FTYP_MIN_SIZE = 0x0C;
    private static final int FTYP_MAX_SIZE = 0x40;

    private static final Signature[] signatures = buildSignatures();

    private static final String[] mimeSubtypes = {"heic", "heif", "avif"};

    private static ImageDescriptor theInstance = null;

    private HEIFDescriptor() {
        super(formatName, extensions, signatures, mimeSubtypes);
    }

    private static Signature[] buildSignatures() {
        java.util.List<Signature> sigs = new java.util.ArrayList<>();
        for (int size = FTYP_MIN_SIZE; size <= FTYP_MAX_SIZE; size += 4) {
            String sizeHex = String.format("%08X", size);
            for (String brand : BRANDS_HEX) {
                sigs.add(new ImageFormatDescription.Signature(
                        hexStringToByteArray(sizeHex + FTYP_HEX + brand)));
            }
        }
        return sigs.toArray(new Signature[0]);
    }

    public static synchronized ImageDescriptor getInstance() {
        if (theInstance == null) {
            theInstance = new HEIFDescriptor();
        }
        return theInstance;
    }

    public static byte[] hexStringToByteArray(String input) {
        int len = input.length();
        if (len == 0) {
            return new byte[]{};
        }
        byte[] data;
        int startIdx;
        if (len % 2 != 0) {
            data = new byte[(len / 2) + 1];
            data[0] = (byte) Character.digit(input.charAt(0), 16);
            startIdx = 1;
        } else {
            data = new byte[len / 2];
            startIdx = 0;
        }
        for (int i = startIdx; i < len; i += 2) {
            data[(i + 1) / 2] = (byte) ((Character.digit(input.charAt(i), 16) << 4)
                    + Character.digit(input.charAt(i + 1), 16));
        }
        return data;
    }
}
