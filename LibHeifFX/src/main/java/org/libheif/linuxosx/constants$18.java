// Generated by jextract

package org.libheif.linuxosx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;
class constants$18 {

    static final FunctionDescriptor heif_encoder_list_parameters$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_encoder_list_parameters$MH = RuntimeHelper.downcallHandle(
        "heif_encoder_list_parameters",
        constants$18.heif_encoder_list_parameters$FUNC, false
    );
    static final FunctionDescriptor heif_encoder_parameter_get_name$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_encoder_parameter_get_name$MH = RuntimeHelper.downcallHandle(
        "heif_encoder_parameter_get_name",
        constants$18.heif_encoder_parameter_get_name$FUNC, false
    );
    static final FunctionDescriptor heif_encoder_parameter_get_type$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_encoder_parameter_get_type$MH = RuntimeHelper.downcallHandle(
        "heif_encoder_parameter_get_type",
        constants$18.heif_encoder_parameter_get_type$FUNC, false
    );
    static final FunctionDescriptor heif_encoder_parameter_get_valid_integer_range$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("code"),
        Constants$root.C_INT$LAYOUT.withName("subcode"),
        Constants$root.C_POINTER$LAYOUT.withName("message")
    ).withName("heif_error"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_encoder_parameter_get_valid_integer_range$MH = RuntimeHelper.downcallHandle(
        "heif_encoder_parameter_get_valid_integer_range",
        constants$18.heif_encoder_parameter_get_valid_integer_range$FUNC, false
    );
    static final FunctionDescriptor heif_encoder_parameter_get_valid_integer_values$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("code"),
        Constants$root.C_INT$LAYOUT.withName("subcode"),
        Constants$root.C_POINTER$LAYOUT.withName("message")
    ).withName("heif_error"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_encoder_parameter_get_valid_integer_values$MH = RuntimeHelper.downcallHandle(
        "heif_encoder_parameter_get_valid_integer_values",
        constants$18.heif_encoder_parameter_get_valid_integer_values$FUNC, false
    );
    static final FunctionDescriptor heif_encoder_parameter_get_valid_string_values$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("code"),
        Constants$root.C_INT$LAYOUT.withName("subcode"),
        Constants$root.C_POINTER$LAYOUT.withName("message")
    ).withName("heif_error"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_encoder_parameter_get_valid_string_values$MH = RuntimeHelper.downcallHandle(
        "heif_encoder_parameter_get_valid_string_values",
        constants$18.heif_encoder_parameter_get_valid_string_values$FUNC, false
    );
}


