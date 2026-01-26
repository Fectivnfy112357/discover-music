package com.facebook.react.uimanager.style;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: BorderRadiusStyle.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/uimanager/style/BorderRadiusProp;", "", "(Ljava/lang/String;I)V", "BORDER_RADIUS", "BORDER_TOP_LEFT_RADIUS", "BORDER_TOP_RIGHT_RADIUS", "BORDER_BOTTOM_RIGHT_RADIUS", "BORDER_BOTTOM_LEFT_RADIUS", "BORDER_TOP_START_RADIUS", "BORDER_TOP_END_RADIUS", "BORDER_BOTTOM_START_RADIUS", "BORDER_BOTTOM_END_RADIUS", "BORDER_END_END_RADIUS", "BORDER_END_START_RADIUS", "BORDER_START_END_RADIUS", "BORDER_START_START_RADIUS", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BorderRadiusProp {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ BorderRadiusProp[] $VALUES;
    public static final BorderRadiusProp BORDER_RADIUS = new BorderRadiusProp("BORDER_RADIUS", 0);
    public static final BorderRadiusProp BORDER_TOP_LEFT_RADIUS = new BorderRadiusProp("BORDER_TOP_LEFT_RADIUS", 1);
    public static final BorderRadiusProp BORDER_TOP_RIGHT_RADIUS = new BorderRadiusProp("BORDER_TOP_RIGHT_RADIUS", 2);
    public static final BorderRadiusProp BORDER_BOTTOM_RIGHT_RADIUS = new BorderRadiusProp("BORDER_BOTTOM_RIGHT_RADIUS", 3);
    public static final BorderRadiusProp BORDER_BOTTOM_LEFT_RADIUS = new BorderRadiusProp("BORDER_BOTTOM_LEFT_RADIUS", 4);
    public static final BorderRadiusProp BORDER_TOP_START_RADIUS = new BorderRadiusProp("BORDER_TOP_START_RADIUS", 5);
    public static final BorderRadiusProp BORDER_TOP_END_RADIUS = new BorderRadiusProp("BORDER_TOP_END_RADIUS", 6);
    public static final BorderRadiusProp BORDER_BOTTOM_START_RADIUS = new BorderRadiusProp("BORDER_BOTTOM_START_RADIUS", 7);
    public static final BorderRadiusProp BORDER_BOTTOM_END_RADIUS = new BorderRadiusProp("BORDER_BOTTOM_END_RADIUS", 8);
    public static final BorderRadiusProp BORDER_END_END_RADIUS = new BorderRadiusProp("BORDER_END_END_RADIUS", 9);
    public static final BorderRadiusProp BORDER_END_START_RADIUS = new BorderRadiusProp("BORDER_END_START_RADIUS", 10);
    public static final BorderRadiusProp BORDER_START_END_RADIUS = new BorderRadiusProp("BORDER_START_END_RADIUS", 11);
    public static final BorderRadiusProp BORDER_START_START_RADIUS = new BorderRadiusProp("BORDER_START_START_RADIUS", 12);

    private static final /* synthetic */ BorderRadiusProp[] $values() {
        return new BorderRadiusProp[]{BORDER_RADIUS, BORDER_TOP_LEFT_RADIUS, BORDER_TOP_RIGHT_RADIUS, BORDER_BOTTOM_RIGHT_RADIUS, BORDER_BOTTOM_LEFT_RADIUS, BORDER_TOP_START_RADIUS, BORDER_TOP_END_RADIUS, BORDER_BOTTOM_START_RADIUS, BORDER_BOTTOM_END_RADIUS, BORDER_END_END_RADIUS, BORDER_END_START_RADIUS, BORDER_START_END_RADIUS, BORDER_START_START_RADIUS};
    }

    public static EnumEntries<BorderRadiusProp> getEntries() {
        return $ENTRIES;
    }

    public static BorderRadiusProp valueOf(String str) {
        return (BorderRadiusProp) Enum.valueOf(BorderRadiusProp.class, str);
    }

    public static BorderRadiusProp[] values() {
        return (BorderRadiusProp[]) $VALUES.clone();
    }

    private BorderRadiusProp(String str, int i) {
    }

    static {
        BorderRadiusProp[] borderRadiusPropArr$values = $values();
        $VALUES = borderRadiusPropArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(borderRadiusPropArr$values);
    }
}
