// Generated by jextract

package org.libheif.win;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;
import jdk.incubator.foreign.*;
import static jdk.incubator.foreign.CLinker.*;
public class heif_error {

    static final MemoryLayout $struct$LAYOUT = MemoryLayout.structLayout(
        C_INT.withName("code"),
        C_INT.withName("subcode"),
        C_POINTER.withName("message")
    ).withName("heif_error");
    public static MemoryLayout $LAYOUT() {
        return heif_error.$struct$LAYOUT;
    }
    static final VarHandle code$VH = $struct$LAYOUT.varHandle(int.class, MemoryLayout.PathElement.groupElement("code"));
    public static VarHandle code$VH() {
        return heif_error.code$VH;
    }
    public static int code$get(MemorySegment seg) {
        return (int)heif_error.code$VH.get(seg);
    }
    public static void code$set( MemorySegment seg, int x) {
        heif_error.code$VH.set(seg, x);
    }
    public static int code$get(MemorySegment seg, long index) {
        return (int)heif_error.code$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void code$set(MemorySegment seg, long index, int x) {
        heif_error.code$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle subcode$VH = $struct$LAYOUT.varHandle(int.class, MemoryLayout.PathElement.groupElement("subcode"));
    public static VarHandle subcode$VH() {
        return heif_error.subcode$VH;
    }
    public static int subcode$get(MemorySegment seg) {
        return (int)heif_error.subcode$VH.get(seg);
    }
    public static void subcode$set( MemorySegment seg, int x) {
        heif_error.subcode$VH.set(seg, x);
    }
    public static int subcode$get(MemorySegment seg, long index) {
        return (int)heif_error.subcode$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void subcode$set(MemorySegment seg, long index, int x) {
        heif_error.subcode$VH.set(seg.asSlice(index*sizeof()), x);
    }
    static final VarHandle message$VH = MemoryHandles.asAddressVarHandle($struct$LAYOUT.varHandle(long.class, MemoryLayout.PathElement.groupElement("message")));
    public static VarHandle message$VH() {
        return heif_error.message$VH;
    }
    public static MemoryAddress message$get(MemorySegment seg) {
        return (jdk.incubator.foreign.MemoryAddress)heif_error.message$VH.get(seg);
    }
    public static void message$set( MemorySegment seg, MemoryAddress x) {
        heif_error.message$VH.set(seg, x);
    }
    public static MemoryAddress message$get(MemorySegment seg, long index) {
        return (jdk.incubator.foreign.MemoryAddress)heif_error.message$VH.get(seg.asSlice(index*sizeof()));
    }
    public static void message$set(MemorySegment seg, long index, MemoryAddress x) {
        heif_error.message$VH.set(seg.asSlice(index*sizeof()), x);
    }
    public static long sizeof() { return $LAYOUT().byteSize(); }
    public static MemorySegment allocate(SegmentAllocator allocator) { return allocator.allocate($LAYOUT()); }
    public static MemorySegment allocate(ResourceScope scope) { return allocate(SegmentAllocator.ofScope(scope)); }
    public static MemorySegment allocateArray(int len, SegmentAllocator allocator) {
        return allocator.allocate(MemoryLayout.sequenceLayout(len, $LAYOUT()));
    }
    public static MemorySegment allocateArray(int len, ResourceScope scope) {
        return allocateArray(len, SegmentAllocator.ofScope(scope));
    }
    public static MemorySegment ofAddress(MemoryAddress addr, ResourceScope scope) { return RuntimeHelper.asArray(addr, $LAYOUT(), 1, scope); }
}

