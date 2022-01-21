/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.libheiffx;

import static jdk.incubator.foreign.CLinker.C_CHAR;
import static jdk.incubator.foreign.CLinker.C_FLOAT;
import static jdk.incubator.foreign.CLinker.C_INT;
import static jdk.incubator.foreign.CLinker.C_LONG;
import static jdk.incubator.foreign.CLinker.C_POINTER;
import jdk.incubator.foreign.MemoryLayout;

/**
 *
 * @author selfemp
 */
public class heif_image_handle {
    
    static final MemoryLayout $struct$LAYOUT = MemoryLayout.structLayout(
        C_POINTER.withName("image"),
        C_POINTER.withName("context")    
    );
    public static MemoryLayout $LAYOUT() {
        return heif_image_handle.$struct$LAYOUT;
    }
}
