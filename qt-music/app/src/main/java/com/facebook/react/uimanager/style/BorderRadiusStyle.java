package com.facebook.react.uimanager.style;

import android.content.Context;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.ViewProps;
import com.umeng.analytics.pro.f;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BorderRadiusStyle.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b:\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\b\u0016\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0003¢\u0006\u0002\u0010\u0007B¡\u0001\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0015J\u000b\u00102\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00104\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00105\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00106\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003J¥\u0001\u0010?\u001a\u00020\u00002\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010@\u001a\u00020A2\b\u0010B\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u00062\u0006\u0010D\u001a\u00020\u0005J\u0006\u0010E\u001a\u00020AJ\t\u0010F\u001a\u00020GHÖ\u0001J&\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020G2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020NJ\u0018\u0010P\u001a\u00020Q2\u0006\u0010D\u001a\u00020\u00052\b\u0010R\u001a\u0004\u0018\u00010\u0006J\t\u0010S\u001a\u00020THÖ\u0001R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0017\"\u0004\b\u001b\u0010\u0019R\u001c\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u0019R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0017\"\u0004\b!\u0010\u0019R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0017\"\u0004\b'\u0010\u0019R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0017\"\u0004\b)\u0010\u0019R\u001c\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0017\"\u0004\b-\u0010\u0019R\u001c\u0010\r\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0017\"\u0004\b/\u0010\u0019R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0017\"\u0004\b1\u0010\u0019¨\u0006U"}, d2 = {"Lcom/facebook/react/uimanager/style/BorderRadiusStyle;", "", "properties", "", "Lkotlin/Pair;", "Lcom/facebook/react/uimanager/style/BorderRadiusProp;", "Lcom/facebook/react/uimanager/LengthPercentage;", "(Ljava/util/List;)V", "uniform", "topLeft", "topRight", "bottomLeft", "bottomRight", "topStart", "topEnd", "bottomStart", "bottomEnd", "startStart", "startEnd", "endStart", "endEnd", "(Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;Lcom/facebook/react/uimanager/LengthPercentage;)V", "getBottomEnd", "()Lcom/facebook/react/uimanager/LengthPercentage;", "setBottomEnd", "(Lcom/facebook/react/uimanager/LengthPercentage;)V", "getBottomLeft", "setBottomLeft", "getBottomRight", "setBottomRight", "getBottomStart", "setBottomStart", "getEndEnd", "setEndEnd", "getEndStart", "setEndStart", "getStartEnd", "setStartEnd", "getStartStart", "setStartStart", "getTopEnd", "setTopEnd", "getTopLeft", "setTopLeft", "getTopRight", "setTopRight", "getTopStart", "setTopStart", "getUniform", "setUniform", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "get", "property", "hasRoundedBorders", "hashCode", "", "resolve", "Lcom/facebook/react/uimanager/style/ComputedBorderRadius;", ViewProps.LAYOUT_DIRECTION, f.X, "Landroid/content/Context;", "width", "", "height", "set", "", "value", "toString", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class BorderRadiusStyle {
    private LengthPercentage bottomEnd;
    private LengthPercentage bottomLeft;
    private LengthPercentage bottomRight;
    private LengthPercentage bottomStart;
    private LengthPercentage endEnd;
    private LengthPercentage endStart;
    private LengthPercentage startEnd;
    private LengthPercentage startStart;
    private LengthPercentage topEnd;
    private LengthPercentage topLeft;
    private LengthPercentage topRight;
    private LengthPercentage topStart;
    private LengthPercentage uniform;

    /* compiled from: BorderRadiusStyle.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BorderRadiusProp.values().length];
            try {
                iArr[BorderRadiusProp.BORDER_RADIUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_TOP_LEFT_RADIUS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_TOP_RIGHT_RADIUS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_BOTTOM_LEFT_RADIUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_BOTTOM_RIGHT_RADIUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_TOP_START_RADIUS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_TOP_END_RADIUS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_BOTTOM_START_RADIUS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_BOTTOM_END_RADIUS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_START_START_RADIUS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_START_END_RADIUS.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_END_START_RADIUS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[BorderRadiusProp.BORDER_END_END_RADIUS.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public BorderRadiusStyle() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
    }

    /* renamed from: component1, reason: from getter */
    public final LengthPercentage getUniform() {
        return this.uniform;
    }

    /* renamed from: component10, reason: from getter */
    public final LengthPercentage getStartStart() {
        return this.startStart;
    }

    /* renamed from: component11, reason: from getter */
    public final LengthPercentage getStartEnd() {
        return this.startEnd;
    }

    /* renamed from: component12, reason: from getter */
    public final LengthPercentage getEndStart() {
        return this.endStart;
    }

    /* renamed from: component13, reason: from getter */
    public final LengthPercentage getEndEnd() {
        return this.endEnd;
    }

    /* renamed from: component2, reason: from getter */
    public final LengthPercentage getTopLeft() {
        return this.topLeft;
    }

    /* renamed from: component3, reason: from getter */
    public final LengthPercentage getTopRight() {
        return this.topRight;
    }

    /* renamed from: component4, reason: from getter */
    public final LengthPercentage getBottomLeft() {
        return this.bottomLeft;
    }

    /* renamed from: component5, reason: from getter */
    public final LengthPercentage getBottomRight() {
        return this.bottomRight;
    }

    /* renamed from: component6, reason: from getter */
    public final LengthPercentage getTopStart() {
        return this.topStart;
    }

    /* renamed from: component7, reason: from getter */
    public final LengthPercentage getTopEnd() {
        return this.topEnd;
    }

    /* renamed from: component8, reason: from getter */
    public final LengthPercentage getBottomStart() {
        return this.bottomStart;
    }

    /* renamed from: component9, reason: from getter */
    public final LengthPercentage getBottomEnd() {
        return this.bottomEnd;
    }

    public final BorderRadiusStyle copy(LengthPercentage uniform, LengthPercentage topLeft, LengthPercentage topRight, LengthPercentage bottomLeft, LengthPercentage bottomRight, LengthPercentage topStart, LengthPercentage topEnd, LengthPercentage bottomStart, LengthPercentage bottomEnd, LengthPercentage startStart, LengthPercentage startEnd, LengthPercentage endStart, LengthPercentage endEnd) {
        return new BorderRadiusStyle(uniform, topLeft, topRight, bottomLeft, bottomRight, topStart, topEnd, bottomStart, bottomEnd, startStart, startEnd, endStart, endEnd);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BorderRadiusStyle)) {
            return false;
        }
        BorderRadiusStyle borderRadiusStyle = (BorderRadiusStyle) other;
        return Intrinsics.areEqual(this.uniform, borderRadiusStyle.uniform) && Intrinsics.areEqual(this.topLeft, borderRadiusStyle.topLeft) && Intrinsics.areEqual(this.topRight, borderRadiusStyle.topRight) && Intrinsics.areEqual(this.bottomLeft, borderRadiusStyle.bottomLeft) && Intrinsics.areEqual(this.bottomRight, borderRadiusStyle.bottomRight) && Intrinsics.areEqual(this.topStart, borderRadiusStyle.topStart) && Intrinsics.areEqual(this.topEnd, borderRadiusStyle.topEnd) && Intrinsics.areEqual(this.bottomStart, borderRadiusStyle.bottomStart) && Intrinsics.areEqual(this.bottomEnd, borderRadiusStyle.bottomEnd) && Intrinsics.areEqual(this.startStart, borderRadiusStyle.startStart) && Intrinsics.areEqual(this.startEnd, borderRadiusStyle.startEnd) && Intrinsics.areEqual(this.endStart, borderRadiusStyle.endStart) && Intrinsics.areEqual(this.endEnd, borderRadiusStyle.endEnd);
    }

    public int hashCode() {
        LengthPercentage lengthPercentage = this.uniform;
        int iHashCode = (lengthPercentage == null ? 0 : lengthPercentage.hashCode()) * 31;
        LengthPercentage lengthPercentage2 = this.topLeft;
        int iHashCode2 = (iHashCode + (lengthPercentage2 == null ? 0 : lengthPercentage2.hashCode())) * 31;
        LengthPercentage lengthPercentage3 = this.topRight;
        int iHashCode3 = (iHashCode2 + (lengthPercentage3 == null ? 0 : lengthPercentage3.hashCode())) * 31;
        LengthPercentage lengthPercentage4 = this.bottomLeft;
        int iHashCode4 = (iHashCode3 + (lengthPercentage4 == null ? 0 : lengthPercentage4.hashCode())) * 31;
        LengthPercentage lengthPercentage5 = this.bottomRight;
        int iHashCode5 = (iHashCode4 + (lengthPercentage5 == null ? 0 : lengthPercentage5.hashCode())) * 31;
        LengthPercentage lengthPercentage6 = this.topStart;
        int iHashCode6 = (iHashCode5 + (lengthPercentage6 == null ? 0 : lengthPercentage6.hashCode())) * 31;
        LengthPercentage lengthPercentage7 = this.topEnd;
        int iHashCode7 = (iHashCode6 + (lengthPercentage7 == null ? 0 : lengthPercentage7.hashCode())) * 31;
        LengthPercentage lengthPercentage8 = this.bottomStart;
        int iHashCode8 = (iHashCode7 + (lengthPercentage8 == null ? 0 : lengthPercentage8.hashCode())) * 31;
        LengthPercentage lengthPercentage9 = this.bottomEnd;
        int iHashCode9 = (iHashCode8 + (lengthPercentage9 == null ? 0 : lengthPercentage9.hashCode())) * 31;
        LengthPercentage lengthPercentage10 = this.startStart;
        int iHashCode10 = (iHashCode9 + (lengthPercentage10 == null ? 0 : lengthPercentage10.hashCode())) * 31;
        LengthPercentage lengthPercentage11 = this.startEnd;
        int iHashCode11 = (iHashCode10 + (lengthPercentage11 == null ? 0 : lengthPercentage11.hashCode())) * 31;
        LengthPercentage lengthPercentage12 = this.endStart;
        int iHashCode12 = (iHashCode11 + (lengthPercentage12 == null ? 0 : lengthPercentage12.hashCode())) * 31;
        LengthPercentage lengthPercentage13 = this.endEnd;
        return iHashCode12 + (lengthPercentage13 != null ? lengthPercentage13.hashCode() : 0);
    }

    public String toString() {
        return "BorderRadiusStyle(uniform=" + this.uniform + ", topLeft=" + this.topLeft + ", topRight=" + this.topRight + ", bottomLeft=" + this.bottomLeft + ", bottomRight=" + this.bottomRight + ", topStart=" + this.topStart + ", topEnd=" + this.topEnd + ", bottomStart=" + this.bottomStart + ", bottomEnd=" + this.bottomEnd + ", startStart=" + this.startStart + ", startEnd=" + this.startEnd + ", endStart=" + this.endStart + ", endEnd=" + this.endEnd + ")";
    }

    public BorderRadiusStyle(LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2, LengthPercentage lengthPercentage3, LengthPercentage lengthPercentage4, LengthPercentage lengthPercentage5, LengthPercentage lengthPercentage6, LengthPercentage lengthPercentage7, LengthPercentage lengthPercentage8, LengthPercentage lengthPercentage9, LengthPercentage lengthPercentage10, LengthPercentage lengthPercentage11, LengthPercentage lengthPercentage12, LengthPercentage lengthPercentage13) {
        this.uniform = lengthPercentage;
        this.topLeft = lengthPercentage2;
        this.topRight = lengthPercentage3;
        this.bottomLeft = lengthPercentage4;
        this.bottomRight = lengthPercentage5;
        this.topStart = lengthPercentage6;
        this.topEnd = lengthPercentage7;
        this.bottomStart = lengthPercentage8;
        this.bottomEnd = lengthPercentage9;
        this.startStart = lengthPercentage10;
        this.startEnd = lengthPercentage11;
        this.endStart = lengthPercentage12;
        this.endEnd = lengthPercentage13;
    }

    public /* synthetic */ BorderRadiusStyle(LengthPercentage lengthPercentage, LengthPercentage lengthPercentage2, LengthPercentage lengthPercentage3, LengthPercentage lengthPercentage4, LengthPercentage lengthPercentage5, LengthPercentage lengthPercentage6, LengthPercentage lengthPercentage7, LengthPercentage lengthPercentage8, LengthPercentage lengthPercentage9, LengthPercentage lengthPercentage10, LengthPercentage lengthPercentage11, LengthPercentage lengthPercentage12, LengthPercentage lengthPercentage13, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : lengthPercentage, (i & 2) != 0 ? null : lengthPercentage2, (i & 4) != 0 ? null : lengthPercentage3, (i & 8) != 0 ? null : lengthPercentage4, (i & 16) != 0 ? null : lengthPercentage5, (i & 32) != 0 ? null : lengthPercentage6, (i & 64) != 0 ? null : lengthPercentage7, (i & 128) != 0 ? null : lengthPercentage8, (i & 256) != 0 ? null : lengthPercentage9, (i & 512) != 0 ? null : lengthPercentage10, (i & 1024) != 0 ? null : lengthPercentage11, (i & 2048) != 0 ? null : lengthPercentage12, (i & 4096) == 0 ? lengthPercentage13 : null);
    }

    public final LengthPercentage getUniform() {
        return this.uniform;
    }

    public final void setUniform(LengthPercentage lengthPercentage) {
        this.uniform = lengthPercentage;
    }

    public final LengthPercentage getTopLeft() {
        return this.topLeft;
    }

    public final void setTopLeft(LengthPercentage lengthPercentage) {
        this.topLeft = lengthPercentage;
    }

    public final LengthPercentage getTopRight() {
        return this.topRight;
    }

    public final void setTopRight(LengthPercentage lengthPercentage) {
        this.topRight = lengthPercentage;
    }

    public final LengthPercentage getBottomLeft() {
        return this.bottomLeft;
    }

    public final void setBottomLeft(LengthPercentage lengthPercentage) {
        this.bottomLeft = lengthPercentage;
    }

    public final LengthPercentage getBottomRight() {
        return this.bottomRight;
    }

    public final void setBottomRight(LengthPercentage lengthPercentage) {
        this.bottomRight = lengthPercentage;
    }

    public final LengthPercentage getTopStart() {
        return this.topStart;
    }

    public final void setTopStart(LengthPercentage lengthPercentage) {
        this.topStart = lengthPercentage;
    }

    public final LengthPercentage getTopEnd() {
        return this.topEnd;
    }

    public final void setTopEnd(LengthPercentage lengthPercentage) {
        this.topEnd = lengthPercentage;
    }

    public final LengthPercentage getBottomStart() {
        return this.bottomStart;
    }

    public final void setBottomStart(LengthPercentage lengthPercentage) {
        this.bottomStart = lengthPercentage;
    }

    public final LengthPercentage getBottomEnd() {
        return this.bottomEnd;
    }

    public final void setBottomEnd(LengthPercentage lengthPercentage) {
        this.bottomEnd = lengthPercentage;
    }

    public final LengthPercentage getStartStart() {
        return this.startStart;
    }

    public final void setStartStart(LengthPercentage lengthPercentage) {
        this.startStart = lengthPercentage;
    }

    public final LengthPercentage getStartEnd() {
        return this.startEnd;
    }

    public final void setStartEnd(LengthPercentage lengthPercentage) {
        this.startEnd = lengthPercentage;
    }

    public final LengthPercentage getEndStart() {
        return this.endStart;
    }

    public final void setEndStart(LengthPercentage lengthPercentage) {
        this.endStart = lengthPercentage;
    }

    public final LengthPercentage getEndEnd() {
        return this.endEnd;
    }

    public final void setEndEnd(LengthPercentage lengthPercentage) {
        this.endEnd = lengthPercentage;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public BorderRadiusStyle(List<? extends Pair<? extends BorderRadiusProp, LengthPercentage>> properties) {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, 8191, null);
        Intrinsics.checkNotNullParameter(properties, "properties");
        Iterator<T> it = properties.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            set((BorderRadiusProp) pair.component1(), (LengthPercentage) pair.component2());
        }
    }

    public final void set(BorderRadiusProp property, LengthPercentage value) {
        Intrinsics.checkNotNullParameter(property, "property");
        switch (WhenMappings.$EnumSwitchMapping$0[property.ordinal()]) {
            case 1:
                this.uniform = value;
                break;
            case 2:
                this.topLeft = value;
                break;
            case 3:
                this.topRight = value;
                break;
            case 4:
                this.bottomLeft = value;
                break;
            case 5:
                this.bottomRight = value;
                break;
            case 6:
                this.topStart = value;
                break;
            case 7:
                this.topEnd = value;
                break;
            case 8:
                this.bottomStart = value;
                break;
            case 9:
                this.bottomEnd = value;
                break;
            case 10:
                this.startStart = value;
                break;
            case 11:
                this.startEnd = value;
                break;
            case 12:
                this.endStart = value;
                break;
            case 13:
                this.endEnd = value;
                break;
        }
    }

    public final LengthPercentage get(BorderRadiusProp property) {
        Intrinsics.checkNotNullParameter(property, "property");
        switch (WhenMappings.$EnumSwitchMapping$0[property.ordinal()]) {
            case 1:
                return this.uniform;
            case 2:
                return this.topLeft;
            case 3:
                return this.topRight;
            case 4:
                return this.bottomLeft;
            case 5:
                return this.bottomRight;
            case 6:
                return this.topStart;
            case 7:
                return this.topEnd;
            case 8:
                return this.bottomStart;
            case 9:
                return this.bottomEnd;
            case 10:
                return this.startStart;
            case 11:
                return this.startEnd;
            case 12:
                return this.endStart;
            case 13:
                return this.endEnd;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final boolean hasRoundedBorders() {
        return (this.uniform == null && this.topLeft == null && this.topRight == null && this.bottomLeft == null && this.bottomRight == null && this.topStart == null && this.topEnd == null && this.bottomStart == null && this.bottomEnd == null && this.startStart == null && this.startEnd == null && this.endStart == null && this.endEnd == null) ? false : true;
    }

    public final ComputedBorderRadius resolve(int layoutDirection, Context context, float width, float height) {
        ComputedBorderRadius computedBorderRadius;
        CornerRadii cornerRadiiResolve;
        CornerRadii cornerRadiiResolve2;
        CornerRadii cornerRadiiResolve3;
        CornerRadii cornerRadiiResolve4;
        CornerRadii cornerRadiiResolve5;
        CornerRadii cornerRadiiResolve6;
        CornerRadii cornerRadiiResolve7;
        CornerRadii cornerRadiiResolve8;
        CornerRadii cornerRadiiResolve9;
        CornerRadii cornerRadiiResolve10;
        CornerRadii cornerRadiiResolve11;
        CornerRadii cornerRadiiResolve12;
        Intrinsics.checkNotNullParameter(context, "context");
        CornerRadii cornerRadii = new CornerRadii(0.0f, 0.0f);
        if (layoutDirection == 0) {
            LengthPercentage lengthPercentage = this.startStart;
            if (lengthPercentage == null && (lengthPercentage = this.topStart) == null && (lengthPercentage = this.topLeft) == null) {
                lengthPercentage = this.uniform;
            }
            if (lengthPercentage == null || (cornerRadiiResolve = lengthPercentage.resolve(width, height)) == null) {
                cornerRadiiResolve = cornerRadii;
            }
            LengthPercentage lengthPercentage2 = this.endStart;
            if (lengthPercentage2 == null && (lengthPercentage2 = this.topEnd) == null && (lengthPercentage2 = this.topRight) == null) {
                lengthPercentage2 = this.uniform;
            }
            if (lengthPercentage2 == null || (cornerRadiiResolve2 = lengthPercentage2.resolve(width, height)) == null) {
                cornerRadiiResolve2 = cornerRadii;
            }
            LengthPercentage lengthPercentage3 = this.startEnd;
            if (lengthPercentage3 == null && (lengthPercentage3 = this.bottomStart) == null && (lengthPercentage3 = this.bottomLeft) == null) {
                lengthPercentage3 = this.uniform;
            }
            if (lengthPercentage3 == null || (cornerRadiiResolve3 = lengthPercentage3.resolve(width, height)) == null) {
                cornerRadiiResolve3 = cornerRadii;
            }
            LengthPercentage lengthPercentage4 = this.endEnd;
            if (lengthPercentage4 == null && (lengthPercentage4 = this.bottomEnd) == null && (lengthPercentage4 = this.bottomRight) == null) {
                lengthPercentage4 = this.uniform;
            }
            if (lengthPercentage4 != null && (cornerRadiiResolve4 = lengthPercentage4.resolve(width, height)) != null) {
                cornerRadii = cornerRadiiResolve4;
            }
            computedBorderRadius = new ComputedBorderRadius(cornerRadiiResolve, cornerRadiiResolve2, cornerRadiiResolve3, cornerRadii);
        } else if (layoutDirection == 1) {
            if (I18nUtil.INSTANCE.getInstance().doLeftAndRightSwapInRTL(context)) {
                LengthPercentage lengthPercentage5 = this.endStart;
                if (lengthPercentage5 == null && (lengthPercentage5 = this.topEnd) == null && (lengthPercentage5 = this.topRight) == null) {
                    lengthPercentage5 = this.uniform;
                }
                if (lengthPercentage5 == null || (cornerRadiiResolve9 = lengthPercentage5.resolve(width, height)) == null) {
                    cornerRadiiResolve9 = cornerRadii;
                }
                LengthPercentage lengthPercentage6 = this.startStart;
                if (lengthPercentage6 == null && (lengthPercentage6 = this.topStart) == null && (lengthPercentage6 = this.topLeft) == null) {
                    lengthPercentage6 = this.uniform;
                }
                if (lengthPercentage6 == null || (cornerRadiiResolve10 = lengthPercentage6.resolve(width, height)) == null) {
                    cornerRadiiResolve10 = cornerRadii;
                }
                LengthPercentage lengthPercentage7 = this.endEnd;
                if (lengthPercentage7 == null && (lengthPercentage7 = this.bottomStart) == null && (lengthPercentage7 = this.bottomRight) == null) {
                    lengthPercentage7 = this.uniform;
                }
                if (lengthPercentage7 == null || (cornerRadiiResolve11 = lengthPercentage7.resolve(width, height)) == null) {
                    cornerRadiiResolve11 = cornerRadii;
                }
                LengthPercentage lengthPercentage8 = this.startEnd;
                if (lengthPercentage8 == null && (lengthPercentage8 = this.bottomEnd) == null && (lengthPercentage8 = this.bottomLeft) == null) {
                    lengthPercentage8 = this.uniform;
                }
                if (lengthPercentage8 != null && (cornerRadiiResolve12 = lengthPercentage8.resolve(width, height)) != null) {
                    cornerRadii = cornerRadiiResolve12;
                }
                computedBorderRadius = new ComputedBorderRadius(cornerRadiiResolve9, cornerRadiiResolve10, cornerRadiiResolve11, cornerRadii);
            } else {
                LengthPercentage lengthPercentage9 = this.endStart;
                if (lengthPercentage9 == null && (lengthPercentage9 = this.topEnd) == null && (lengthPercentage9 = this.topLeft) == null) {
                    lengthPercentage9 = this.uniform;
                }
                if (lengthPercentage9 == null || (cornerRadiiResolve5 = lengthPercentage9.resolve(width, height)) == null) {
                    cornerRadiiResolve5 = cornerRadii;
                }
                LengthPercentage lengthPercentage10 = this.startStart;
                if (lengthPercentage10 == null && (lengthPercentage10 = this.topStart) == null && (lengthPercentage10 = this.topRight) == null) {
                    lengthPercentage10 = this.uniform;
                }
                if (lengthPercentage10 == null || (cornerRadiiResolve6 = lengthPercentage10.resolve(width, height)) == null) {
                    cornerRadiiResolve6 = cornerRadii;
                }
                LengthPercentage lengthPercentage11 = this.endEnd;
                if (lengthPercentage11 == null && (lengthPercentage11 = this.bottomStart) == null && (lengthPercentage11 = this.bottomLeft) == null) {
                    lengthPercentage11 = this.uniform;
                }
                if (lengthPercentage11 == null || (cornerRadiiResolve7 = lengthPercentage11.resolve(width, height)) == null) {
                    cornerRadiiResolve7 = cornerRadii;
                }
                LengthPercentage lengthPercentage12 = this.startEnd;
                if (lengthPercentage12 == null && (lengthPercentage12 = this.bottomEnd) == null && (lengthPercentage12 = this.bottomRight) == null) {
                    lengthPercentage12 = this.uniform;
                }
                if (lengthPercentage12 != null && (cornerRadiiResolve8 = lengthPercentage12.resolve(width, height)) != null) {
                    cornerRadii = cornerRadiiResolve8;
                }
                computedBorderRadius = new ComputedBorderRadius(cornerRadiiResolve5, cornerRadiiResolve6, cornerRadiiResolve7, cornerRadii);
            }
        } else {
            throw new IllegalArgumentException("Expected?.resolved layout direction");
        }
        return computedBorderRadius;
    }
}
