// Generated by jextract

package org.libheif.linuxosx;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.ValueLayout.*;
class constants$14 {

    static final FunctionDescriptor heif_image_has_channel$FUNC = FunctionDescriptor.of(Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT
    );
    static final MethodHandle heif_image_has_channel$MH = RuntimeHelper.downcallHandle(
        "heif_image_has_channel",
        constants$14.heif_image_has_channel$FUNC, false
    );
    static final FunctionDescriptor heif_image_get_plane_readonly$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_image_get_plane_readonly$MH = RuntimeHelper.downcallHandle(
        "heif_image_get_plane_readonly",
        constants$14.heif_image_get_plane_readonly$FUNC, false
    );
    static final FunctionDescriptor heif_image_get_plane$FUNC = FunctionDescriptor.of(Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_image_get_plane$MH = RuntimeHelper.downcallHandle(
        "heif_image_get_plane",
        constants$14.heif_image_get_plane$FUNC, false
    );
    static final FunctionDescriptor heif_image_scale_image$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("code"),
        Constants$root.C_INT$LAYOUT.withName("subcode"),
        Constants$root.C_POINTER$LAYOUT.withName("message")
    ).withName("heif_error"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_INT$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_image_scale_image$MH = RuntimeHelper.downcallHandle(
        "heif_image_scale_image",
        constants$14.heif_image_scale_image$FUNC, false
    );
    static final FunctionDescriptor heif_image_set_raw_color_profile$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("code"),
        Constants$root.C_INT$LAYOUT.withName("subcode"),
        Constants$root.C_POINTER$LAYOUT.withName("message")
    ).withName("heif_error"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_LONG_LONG$LAYOUT
    );
    static final MethodHandle heif_image_set_raw_color_profile$MH = RuntimeHelper.downcallHandle(
        "heif_image_set_raw_color_profile",
        constants$14.heif_image_set_raw_color_profile$FUNC, false
    );
    static final FunctionDescriptor heif_image_set_nclx_color_profile$FUNC = FunctionDescriptor.of(MemoryLayout.structLayout(
        Constants$root.C_INT$LAYOUT.withName("code"),
        Constants$root.C_INT$LAYOUT.withName("subcode"),
        Constants$root.C_POINTER$LAYOUT.withName("message")
    ).withName("heif_error"),
        Constants$root.C_POINTER$LAYOUT,
        Constants$root.C_POINTER$LAYOUT
    );
    static final MethodHandle heif_image_set_nclx_color_profile$MH = RuntimeHelper.downcallHandle(
        "heif_image_set_nclx_color_profile",
        constants$14.heif_image_set_nclx_color_profile$FUNC, false
    );
}


