// Generated by jextract

package org.libheif.linuxosx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;
class constants$0 {

    static final FunctionDescriptor heif_get_version$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT);
    static final MethodHandle heif_get_version$MH = RuntimeHelper.downcallHandle(
        "heif_get_version",
        constants$0.heif_get_version$FUNC, false
    );
    static final FunctionDescriptor heif_get_version_number$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle heif_get_version_number$MH = RuntimeHelper.downcallHandle(
        "heif_get_version_number",
        constants$0.heif_get_version_number$FUNC, false
    );
    static final FunctionDescriptor heif_get_version_number_major$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle heif_get_version_number_major$MH = RuntimeHelper.downcallHandle(
        "heif_get_version_number_major",
        constants$0.heif_get_version_number_major$FUNC, false
    );
    static final FunctionDescriptor heif_get_version_number_minor$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle heif_get_version_number_minor$MH = RuntimeHelper.downcallHandle(
        "heif_get_version_number_minor",
        constants$0.heif_get_version_number_minor$FUNC, false
    );
    static final FunctionDescriptor heif_get_version_number_maintenance$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT);
    static final MethodHandle heif_get_version_number_maintenance$MH = RuntimeHelper.downcallHandle(
        "heif_get_version_number_maintenance",
        constants$0.heif_get_version_number_maintenance$FUNC, false
    );
    static final FunctionDescriptor heif_check_filetype$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle heif_check_filetype$MH = RuntimeHelper.downcallHandle(
        "heif_check_filetype",
        constants$0.heif_check_filetype$FUNC, false
    );
}


