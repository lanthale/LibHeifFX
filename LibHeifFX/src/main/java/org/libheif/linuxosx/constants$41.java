// Generated by jextract

package org.libheif.linuxosx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import java.lang.foreign.*;
import static java.lang.foreign.ValueLayout.*;
final class constants$41 {

    // Suppresses default constructor, ensuring non-instantiability.
    private constants$41() {}
    static final MethodHandle const$0 = RuntimeHelper.downcallHandle(
        "heif_register_encoder_plugin",
        constants$40.const$4
    );
    static final MethodHandle const$1 = RuntimeHelper.downcallHandle(
        "heif_encoder_descriptor_supportes_lossy_compression",
        constants$2.const$2
    );
    static final MethodHandle const$2 = RuntimeHelper.downcallHandle(
        "heif_encoder_descriptor_supportes_lossless_compression",
        constants$2.const$2
    );
    static final MemorySegment const$3 = MemorySegment.ofAddress(0L);
    static final MemorySegment const$4 = RuntimeHelper.CONSTANT_ALLOCATOR.allocateUtf8String("1.12.0");
}

