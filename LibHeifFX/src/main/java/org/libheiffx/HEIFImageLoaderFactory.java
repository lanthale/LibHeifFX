/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.libheiffx;

import org.librawfx.dimension.DimensionProvider;
import org.librawfx.dimension.DefaultDimensionProvider;
import com.sun.javafx.iio.ImageFormatDescription;
import com.sun.javafx.iio.ImageLoader;
import com.sun.javafx.iio.ImageLoaderFactory;
import com.sun.javafx.iio.ImageStorage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to install the TIFF renderer to the javafx image systems
 * @author selfemp
 */
public class HEIFImageLoaderFactory implements ImageLoaderFactory {

    private static final HEIFImageLoaderFactory instance = new HEIFImageLoaderFactory();
    
    private static String tempDir;

    private static DimensionProvider dimensionProvider;

    /**
     * Main method to install the image handler to support any loading of images with the Image class
     * @param tempdir the directory to specify where the native libs are extracted. Import on OSX because there is a bug on Apple side preventing using the temporary directory
     */
    public static final void install(String tempdir) {  
        HEIFImageLoaderFactory.tempDir=tempdir;
        install(new DefaultDimensionProvider());        
    }
    
    /**
     * Main method to install the image handler to support any loading of images with the Image class
     */
    public static final void install() {  
        HEIFImageLoaderFactory.tempDir=null;
        install(new DefaultDimensionProvider());        
    }

    public static final void install(DimensionProvider dimensionProvider) {
        try {
            LibheifImage.loadLibs(tempDir);
        } catch (IOException ex) {
            Logger.getLogger(HEIFImageLoaderFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        HEIFImageLoaderFactory.dimensionProvider = dimensionProvider;

        ImageStorage.addImageLoaderFactory(instance);        
    }

    public static final ImageLoaderFactory getInstance() {
        return instance;
    }

    @Override
    public ImageFormatDescription getFormatDescription() {
        return HEIFDescriptor.getInstance();
    }

    @Override
    public ImageLoader createImageLoader(InputStream input) throws IOException {
        return new HEIFImageLoader(input, dimensionProvider);
    }        

}
