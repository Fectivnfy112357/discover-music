package com.doublesymmetry.trackplayer.utils;

import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RejectionException.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0004HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0004HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/doublesymmetry/trackplayer/utils/RejectionException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", StackTraceHelper.MESSAGE_KEY, "", "code", "(Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()Ljava/lang/String;", "getMessage", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class RejectionException extends Exception {
    private final String code;
    private final String message;

    public static /* synthetic */ RejectionException copy$default(RejectionException rejectionException, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = rejectionException.message;
        }
        if ((i & 2) != 0) {
            str2 = rejectionException.code;
        }
        return rejectionException.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMessage() {
        return this.message;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCode() {
        return this.code;
    }

    public final RejectionException copy(String message, String code) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        return new RejectionException(message, code);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RejectionException)) {
            return false;
        }
        RejectionException rejectionException = (RejectionException) other;
        return Intrinsics.areEqual(this.message, rejectionException.message) && Intrinsics.areEqual(this.code, rejectionException.code);
    }

    public int hashCode() {
        return (this.message.hashCode() * 31) + this.code.hashCode();
    }

    @Override // java.lang.Throwable
    public String toString() {
        return "RejectionException(message=" + this.message + ", code=" + this.code + ")";
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public final String getCode() {
        return this.code;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RejectionException(String message, String code) {
        super(message);
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(code, "code");
        this.message = message;
        this.code = code;
    }
}
