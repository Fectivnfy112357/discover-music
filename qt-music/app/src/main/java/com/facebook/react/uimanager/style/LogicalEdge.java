package com.facebook.react.uimanager.style;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: LogicalEdge.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0081\u0002\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/uimanager/style/LogicalEdge;", "", "(Ljava/lang/String;I)V", "toSpacingType", "", "ALL", "LEFT", "RIGHT", "TOP", "BOTTOM", "START", "END", "HORIZONTAL", "VERTICAL", "BLOCK_START", "BLOCK_END", "BLOCK", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class LogicalEdge {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ LogicalEdge[] $VALUES;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final LogicalEdge ALL = new LogicalEdge("ALL", 0) { // from class: com.facebook.react.uimanager.style.LogicalEdge.ALL
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 8;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge LEFT = new LogicalEdge("LEFT", 1) { // from class: com.facebook.react.uimanager.style.LogicalEdge.LEFT
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 0;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge RIGHT = new LogicalEdge("RIGHT", 2) { // from class: com.facebook.react.uimanager.style.LogicalEdge.RIGHT
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 2;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge TOP = new LogicalEdge("TOP", 3) { // from class: com.facebook.react.uimanager.style.LogicalEdge.TOP
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 1;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge BOTTOM = new LogicalEdge("BOTTOM", 4) { // from class: com.facebook.react.uimanager.style.LogicalEdge.BOTTOM
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 3;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge START = new LogicalEdge("START", 5) { // from class: com.facebook.react.uimanager.style.LogicalEdge.START
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 4;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge END = new LogicalEdge("END", 6) { // from class: com.facebook.react.uimanager.style.LogicalEdge.END
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 5;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge HORIZONTAL = new LogicalEdge("HORIZONTAL", 7) { // from class: com.facebook.react.uimanager.style.LogicalEdge.HORIZONTAL
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 6;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge VERTICAL = new LogicalEdge("VERTICAL", 8) { // from class: com.facebook.react.uimanager.style.LogicalEdge.VERTICAL
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 7;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge BLOCK_START = new LogicalEdge("BLOCK_START", 9) { // from class: com.facebook.react.uimanager.style.LogicalEdge.BLOCK_START
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 11;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge BLOCK_END = new LogicalEdge("BLOCK_END", 10) { // from class: com.facebook.react.uimanager.style.LogicalEdge.BLOCK_END
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 10;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };
    public static final LogicalEdge BLOCK = new LogicalEdge("BLOCK", 11) { // from class: com.facebook.react.uimanager.style.LogicalEdge.BLOCK
        @Override // com.facebook.react.uimanager.style.LogicalEdge
        public int toSpacingType() {
            return 9;
        }

        {
            DefaultConstructorMarker defaultConstructorMarker = null;
        }
    };

    private static final /* synthetic */ LogicalEdge[] $values() {
        return new LogicalEdge[]{ALL, LEFT, RIGHT, TOP, BOTTOM, START, END, HORIZONTAL, VERTICAL, BLOCK_START, BLOCK_END, BLOCK};
    }

    public /* synthetic */ LogicalEdge(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i);
    }

    @JvmStatic
    public static final LogicalEdge fromSpacingType(int i) {
        return INSTANCE.fromSpacingType(i);
    }

    public static EnumEntries<LogicalEdge> getEntries() {
        return $ENTRIES;
    }

    public static LogicalEdge valueOf(String str) {
        return (LogicalEdge) Enum.valueOf(LogicalEdge.class, str);
    }

    public static LogicalEdge[] values() {
        return (LogicalEdge[]) $VALUES.clone();
    }

    public abstract int toSpacingType();

    private LogicalEdge(String str, int i) {
    }

    static {
        LogicalEdge[] logicalEdgeArr$values = $values();
        $VALUES = logicalEdgeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(logicalEdgeArr$values);
        INSTANCE = new Companion(null);
    }

    /* compiled from: LogicalEdge.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/uimanager/style/LogicalEdge$Companion;", "", "()V", "fromSpacingType", "Lcom/facebook/react/uimanager/style/LogicalEdge;", "spacingType", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final LogicalEdge fromSpacingType(int spacingType) {
            switch (spacingType) {
                case 0:
                    return LogicalEdge.LEFT;
                case 1:
                    return LogicalEdge.TOP;
                case 2:
                    return LogicalEdge.RIGHT;
                case 3:
                    return LogicalEdge.BOTTOM;
                case 4:
                    return LogicalEdge.START;
                case 5:
                    return LogicalEdge.END;
                case 6:
                    return LogicalEdge.HORIZONTAL;
                case 7:
                    return LogicalEdge.VERTICAL;
                case 8:
                    return LogicalEdge.ALL;
                case 9:
                    return LogicalEdge.BLOCK;
                case 10:
                    return LogicalEdge.BLOCK_END;
                case 11:
                    return LogicalEdge.BLOCK_START;
                default:
                    throw new IllegalArgumentException("Unknown spacing type: " + spacingType);
            }
        }
    }
}
