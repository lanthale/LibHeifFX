// Generated by jextract

package org.libheif.linuxosx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$4 {

    static final FunctionDescriptor heif_context_get_primary_image_handle$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle heif_context_get_primary_image_handle$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_get_primary_image_handle",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$4.heif_context_get_primary_image_handle$FUNC, false
    );
    static final FunctionDescriptor heif_context_get_primary_image_handle_alloc$FUNC = FunctionDescriptor.of(C_POINTER,
        C_POINTER
    );
    static final MethodHandle heif_context_get_primary_image_handle_alloc$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_get_primary_image_handle_alloc",
        "(Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemoryAddress;",
        constants$4.heif_context_get_primary_image_handle_alloc$FUNC, false
    );
    static final FunctionDescriptor heif_context_get_image_handle$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_INT,
        C_POINTER
    );
    static final MethodHandle heif_context_get_image_handle$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_get_image_handle",
        "(Ljdk/incubator/foreign/MemoryAddress;ILjdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$4.heif_context_get_image_handle$FUNC, false
    );
    static final FunctionDescriptor heif_context_debug_dump_boxes_to_file$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_context_debug_dump_boxes_to_file$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_debug_dump_boxes_to_file",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$4.heif_context_debug_dump_boxes_to_file$FUNC, false
    );
    static final FunctionDescriptor heif_context_set_maximum_image_size_limit$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_context_set_maximum_image_size_limit$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_set_maximum_image_size_limit",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$4.heif_context_set_maximum_image_size_limit$FUNC, false
    );
    static final FunctionDescriptor heif_context_set_max_decoding_threads$FUNC = FunctionDescriptor.ofVoid(
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_context_set_max_decoding_threads$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_set_max_decoding_threads",
        "(Ljdk/incubator/foreign/MemoryAddress;I)V",
        constants$4.heif_context_set_max_decoding_threads$FUNC, false
    );
}

