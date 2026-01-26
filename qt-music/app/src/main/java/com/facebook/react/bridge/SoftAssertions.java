package com.facebook.react.bridge;

import androidx.exifinterface.media.ExifInterface;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SoftAssertions.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u001f\u0010\t\u001a\u0004\u0018\u0001H\n\"\u0004\b\u0000\u0010\n2\b\u0010\u000b\u001a\u0004\u0018\u0001H\nH\u0007¢\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u000e"}, d2 = {"Lcom/facebook/react/bridge/SoftAssertions;", "", "()V", "assertCondition", "", "condition", "", StackTraceHelper.MESSAGE_KEY, "", "assertNotNull", ExifInterface.GPS_DIRECTION_TRUE, "instance", "(Ljava/lang/Object;)Ljava/lang/Object;", "assertUnreachable", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SoftAssertions {
    public static final SoftAssertions INSTANCE = new SoftAssertions();

    private SoftAssertions() {
    }

    @JvmStatic
    public static final void assertUnreachable(String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        ReactSoftExceptionLogger.logSoftException("SoftAssertions", new AssertionException(message));
    }

    @JvmStatic
    public static final void assertCondition(boolean condition, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (condition) {
            return;
        }
        ReactSoftExceptionLogger.logSoftException("SoftAssertions", new AssertionException(message));
    }

    @JvmStatic
    public static final <T> T assertNotNull(T instance) {
        if (instance == null) {
            ReactSoftExceptionLogger.logSoftException("SoftAssertions", new AssertionException("Expected object to not be null!"));
        }
        return instance;
    }
}
