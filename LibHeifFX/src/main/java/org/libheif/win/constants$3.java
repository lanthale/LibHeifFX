// Generated by jextract

package org.libheif.win;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
class constants$3 {

    static final FunctionDescriptor heif_context_read_from_memory_without_copy$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_POINTER,
        C_LONG_LONG,
        C_POINTER
    );
    static final MethodHandle heif_context_read_from_memory_without_copy$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_read_from_memory_without_copy",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;JLjdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$3.heif_context_read_from_memory_without_copy$FUNC, false
    );
    static final FunctionDescriptor heif_context_read_from_reader$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_POINTER,
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle heif_context_read_from_reader$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_read_from_reader",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$3.heif_context_read_from_reader$FUNC, false
    );
    static final FunctionDescriptor heif_context_get_number_of_top_level_images$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER
    );
    static final MethodHandle heif_context_get_number_of_top_level_images$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_get_number_of_top_level_images",
        "(Ljdk/incubator/foreign/MemoryAddress;)I",
        constants$3.heif_context_get_number_of_top_level_images$FUNC, false
    );
    static final FunctionDescriptor heif_context_is_top_level_image_ID$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_context_is_top_level_image_ID$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_is_top_level_image_ID",
        "(Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$3.heif_context_is_top_level_image_ID$FUNC, false
    );
    static final FunctionDescriptor heif_context_get_list_of_top_level_image_IDs$FUNC = FunctionDescriptor.of(C_INT,
        C_POINTER,
        C_POINTER,
        C_INT
    );
    static final MethodHandle heif_context_get_list_of_top_level_image_IDs$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_get_list_of_top_level_image_IDs",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;I)I",
        constants$3.heif_context_get_list_of_top_level_image_IDs$FUNC, false
    );
    static final FunctionDescriptor heif_context_get_primary_image_ID$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error"),
        C_POINTER,
        C_POINTER
    );
    static final MethodHandle heif_context_get_primary_image_ID$MH = RuntimeHelper.downcallHandle(
        heif_h.LIBRARIES, "heif_context_get_primary_image_ID",
        "(Ljdk/incubator/foreign/MemoryAddress;Ljdk/incubator/foreign/MemoryAddress;)Ljdk/incubator/foreign/MemorySegment;",
        constants$3.heif_context_get_primary_image_ID$FUNC, false
    );
}

