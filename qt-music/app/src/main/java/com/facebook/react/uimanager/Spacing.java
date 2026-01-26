package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConstants;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Spacing.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u0014\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u000f\b\u0016\u0012\u0006\u0010\u0006\u001a\u00020\u0000¢\u0006\u0002\u0010\u0007B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0011\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000eH\u0086\u0002J\u000e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000eJ\u0016\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eJ\u0006\u0010\u0014\u001a\u00020\u0015J\u0019\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0004H\u0086\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/uimanager/Spacing;", "", "()V", "defaultValue", "", "(F)V", "original", "(Lcom/facebook/react/uimanager/Spacing;)V", "spacing", "", "(F[F)V", "hasAliasesSet", "", "valueFlags", "", "get", "spacingType", "getRaw", "getWithFallback", "fallbackType", "reset", "", "set", "value", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Spacing {
    public static final int ALL = 8;
    public static final int BLOCK = 9;
    public static final int BLOCK_END = 10;
    public static final int BLOCK_START = 11;
    public static final int BOTTOM = 3;
    public static final int END = 5;
    public static final int HORIZONTAL = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 2;
    public static final int START = 4;
    public static final int TOP = 1;
    public static final int VERTICAL = 7;
    private final float defaultValue;
    private boolean hasAliasesSet;
    private final float[] spacing;
    private int valueFlags;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int[] flagsMap = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};

    public Spacing(float f, float[] spacing) {
        Intrinsics.checkNotNullParameter(spacing, "spacing");
        this.defaultValue = f;
        this.spacing = spacing;
    }

    public Spacing() {
        this(0.0f, INSTANCE.newFullSpacingArray());
    }

    public Spacing(float f) {
        this(f, INSTANCE.newFullSpacingArray());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Spacing(Spacing original) {
        Intrinsics.checkNotNullParameter(original, "original");
        float f = original.defaultValue;
        float[] fArr = original.spacing;
        float[] fArrCopyOf = Arrays.copyOf(fArr, fArr.length);
        Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
        this(f, fArrCopyOf);
        this.valueFlags = original.valueFlags;
        this.hasAliasesSet = original.hasAliasesSet;
    }

    public final boolean set(int spacingType, float value) {
        int i;
        if (FloatUtil.floatsEqual(this.spacing[spacingType], value)) {
            return false;
        }
        this.spacing[spacingType] = value;
        if (YogaConstants.isUndefined(value)) {
            i = (~flagsMap[spacingType]) & this.valueFlags;
        } else {
            i = flagsMap[spacingType] | this.valueFlags;
        }
        this.valueFlags = i;
        int[] iArr = flagsMap;
        this.hasAliasesSet = ((iArr[8] & i) == 0 && (iArr[7] & i) == 0 && (iArr[6] & i) == 0 && (i & iArr[9]) == 0) ? false : true;
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final float get(int r5) {
        /*
            r4 = this;
            r0 = 4
            if (r5 == r0) goto Lc
            r0 = 5
            if (r5 == r0) goto Lc
            switch(r5) {
                case 9: goto Lc;
                case 10: goto Lc;
                case 11: goto Lc;
                default: goto L9;
            }
        L9:
            float r0 = r4.defaultValue
            goto Le
        Lc:
            r0 = 2143289344(0x7fc00000, float:NaN)
        Le:
            int r1 = r4.valueFlags
            if (r1 != 0) goto L13
            return r0
        L13:
            int[] r2 = com.facebook.react.uimanager.Spacing.flagsMap
            r3 = r2[r5]
            r3 = r3 & r1
            if (r3 == 0) goto L1f
            float[] r0 = r4.spacing
            r5 = r0[r5]
            return r5
        L1f:
            boolean r3 = r4.hasAliasesSet
            if (r3 == 0) goto L42
            r3 = 1
            if (r5 == r3) goto L2b
            r3 = 3
            if (r5 == r3) goto L2b
            r5 = 6
            goto L2c
        L2b:
            r5 = 7
        L2c:
            r3 = r2[r5]
            r3 = r3 & r1
            if (r3 == 0) goto L36
            float[] r0 = r4.spacing
            r5 = r0[r5]
            return r5
        L36:
            r5 = 8
            r2 = r2[r5]
            r1 = r1 & r2
            if (r1 == 0) goto L42
            float[] r0 = r4.spacing
            r5 = r0[r5]
            return r5
        L42:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.uimanager.Spacing.get(int):float");
    }

    public final float getRaw(int spacingType) {
        return this.spacing[spacingType];
    }

    public final void reset() {
        ArraysKt.fill$default(this.spacing, Float.NaN, 0, 0, 6, (Object) null);
        this.hasAliasesSet = false;
        this.valueFlags = 0;
    }

    public final float getWithFallback(int spacingType, int fallbackType) {
        if ((this.valueFlags & flagsMap[spacingType]) != 0) {
            return this.spacing[spacingType];
        }
        return get(fallbackType);
    }

    /* compiled from: Spacing.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0014\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/facebook/react/uimanager/Spacing$Companion;", "", "()V", "ALL", "", "BLOCK", "BLOCK_END", "BLOCK_START", "BOTTOM", "END", "HORIZONTAL", "LEFT", "RIGHT", "START", "TOP", "VERTICAL", "flagsMap", "", "newFullSpacingArray", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final float[] newFullSpacingArray() {
            return new float[]{Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN, Float.NaN};
        }
    }
}
