// Generated by jextract

package org.libheif.linuxosx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$22 {

    static final FunctionDescriptor heif_context_add_XMP_metadata$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_POINTER,
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_context_add_XMP_metadata$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_add_XMP_metadata",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;I)Ljdk/incubator/foreign/MemorySegment;",
        constants$22.heif_context_add_XMP_metadata$FUNC, false
    );
    static final FunctionDescriptor heif_context_add_generic_metadata$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_POINTER,
        C_POINTER,
        C_INT,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle heif_context_add_generic_metadata$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_add_generic_metadata",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;ILjdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$22.heif_context_add_generic_metadata$FUNC, false
    );
    static final FunctionDescriptor heif_image_create$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_INT,
        C_INT,
        C_INT,
        C_INT,
        C_POINTER
    );
    static final MethodHandle heif_image_create$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_image_create",
        "(IIIILjdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$22.heif_image_create$FUNC, false
    );
    static final FunctionDescriptor heif_image_add_plane$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_INT,
        C_INT,
        C_INT,
        C_INT
    );
    static final MethodHandle heif_image_add_plane$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_image_add_plane",
        "(Ljdk/incubator/foreign/MemoryAddress;IIII)Ljdk/incubator/foreign/MemorySegment;",
        constants$22.heif_image_add_plane$FUNC, false
    );
    static final FunctionDescriptor heif_image_set_premultiplied_alpha$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_image_set_premultiplied_alpha$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_image_set_premultiplied_alpha",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$22.heif_image_set_premultiplied_alpha$FUNC, false
    );
    static final FunctionDescriptor heif_image_is_premultiplied_alpha$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle heif_image_is_premultiplied_alpha$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_image_is_premultiplied_alpha",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$22.heif_image_is_premultiplied_alpha$FUNC, false
    );
}

