package com.facebook.react.devsupport.interfaces;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: ErrorType.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/facebook/react/devsupport/interfaces/ErrorType;", "", "displayName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "toString", "JS", "NATIVE", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ErrorType {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ ErrorType[] $VALUES;
    public static final ErrorType JS = new ErrorType("JS", 0, "JS");
    public static final ErrorType NATIVE = new ErrorType("NATIVE", 1, "Native");
    private final String displayName;

    private static final /* synthetic */ ErrorType[] $values() {
        return new ErrorType[]{JS, NATIVE};
    }

    public static EnumEntries<ErrorType> getEntries() {
        return $ENTRIES;
    }

    public static ErrorType valueOf(String str) {
        return (ErrorType) Enum.valueOf(ErrorType.class, str);
    }

    public static ErrorType[] values() {
        return (ErrorType[]) $VALUES.clone();
    }

    private ErrorType(String str, int i, String str2) {
        this.displayName = str2;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    static {
        ErrorType[] errorTypeArr$values = $values();
        $VALUES = errorTypeArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(errorTypeArr$values);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.displayName;
    }
}
