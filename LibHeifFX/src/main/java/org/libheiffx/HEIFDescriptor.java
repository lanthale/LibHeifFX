/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.libheiffx;

import com.sun.javafx.iio.common.ImageDescriptor;

/**
 *
 * @author selfemp
 */
public class HEIFDescriptor extends ImageDescriptor {

    private static final String formatName = "HEIC/AVIF";

    private static final String[] extensions = {"heic","avif"};

    private static final Signature[] signatures = {
        new Signature(hexStringToByteArray("000000186674797068656963")),
        new Signature(hexStringToByteArray("0000001C6674797061760000"))
    };
    
    private static final String[] mimeSubtypes = { "heic","avif" };

    private static ImageDescriptor theInstance = null;

    private HEIFDescriptor() {
        super(formatName, extensions, signatures, mimeSubtypes);
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
