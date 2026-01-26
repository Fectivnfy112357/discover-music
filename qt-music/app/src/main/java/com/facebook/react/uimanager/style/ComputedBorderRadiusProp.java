package com.facebook.react.uimanager.style;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ComputedBorderRadius.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/uimanager/style/ComputedBorderRadiusProp;", "", "(Ljava/lang/String;I)V", "COMPUTED_BORDER_TOP_LEFT_RADIUS", "COMPUTED_BORDER_TOP_RIGHT_RADIUS", "COMPUTED_BORDER_BOTTOM_RIGHT_RADIUS", "COMPUTED_BORDER_BOTTOM_LEFT_RADIUS", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ComputedBorderRadiusProp {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ComputedBorderRadiusProp[] $VALUES;
    public static final ComputedBorderRadiusProp COMPUTED_BORDER_TOP_LEFT_RADIUS = new ComputedBorderRadiusProp("COMPUTED_BORDER_TOP_LEFT_RADIUS", 0);
    public static final ComputedBorderRadiusProp COMPUTED_BORDER_TOP_RIGHT_RADIUS = new ComputedBorderRadiusProp("COMPUTED_BORDER_TOP_RIGHT_RADIUS", 1);
    public static final ComputedBorderRadiusProp COMPUTED_BORDER_BOTTOM_RIGHT_RADIUS = new ComputedBorderRadiusProp("COMPUTED_BORDER_BOTTOM_RIGHT_RADIUS", 2);
    public static final ComputedBorderRadiusProp COMPUTED_BORDER_BOTTOM_LEFT_RADIUS = new ComputedBorderRadiusProp("COMPUTED_BORDER_BOTTOM_LEFT_RADIUS", 3);

    private static final /* synthetic */ ComputedBorderRadiusProp[] $values() {
        return new ComputedBorderRadiusProp[]{COMPUTED_BORDER_TOP_LEFT_RADIUS, COMPUTED_BORDER_TOP_RIGHT_RADIUS, COMPUTED_BORDER_BOTTOM_RIGHT_RADIUS, COMPUTED_BORDER_BOTTOM_LEFT_RADIUS};
    }

    public static EnumEntries<ComputedBorderRadiusProp> getEntries() {
        return $ENTRIES;
    }

    public static ComputedBorderRadiusProp valueOf(String str) {
        return (ComputedBorderRadiusProp) Enum.valueOf(ComputedBorderRadiusProp.class, str);
    }

    public static ComputedBorderRadiusProp[] values() {
        return (ComputedBorderRadiusProp[]) $VALUES.clone();
    }

    private ComputedBorderRadiusProp(String str, int i) {
    }

    static {
        ComputedBorderRadiusProp[] computedBorderRadiusPropArr$values = $values();
        $VALUES = computedBorderRadiusPropArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(computedBorderRadiusPropArr$values);
    }
}
