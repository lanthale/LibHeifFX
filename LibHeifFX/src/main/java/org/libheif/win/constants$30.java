// Generated by jextract

package org.libheif.win;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$30 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$30() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "heif_context_write",
        constants$7.const$2
    );
    static final FunctionDescriptor const$1 = FunctionDescriptor.of(JAVA_INT,
        RuntimeHelper.POINTER,
        JAVA_INT,
        RuntimeHelper.POINTER,
        RuntimeHelper.POINTER,
        JAVA_INT
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "heif_context_get_encoder_descriptors",
        constants$30.const$1
    );
    static final MethodHandle const$3 = RuntimeHelper.downcallHandle(
        "heif_encoder_descriptor_get_name",
        constants$9.const$3
    );
    static final MethodHandle const$4 = RuntimeHelper.downcallHandle(
        "heif_encoder_descriptor_get_id_name",
        constants$9.const$3
    );
    static final MethodHandle const$5 = RuntimeHelper.downcallHandle(
        "heif_encoder_descriptor_get_compression_format",
        constants$2.const$2
    );
}

