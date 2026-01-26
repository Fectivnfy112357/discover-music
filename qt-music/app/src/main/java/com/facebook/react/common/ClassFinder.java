package com.facebook.react.common;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ClassFinder.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007J\u0016\u0010\u0005\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/react/common/ClassFinder;", "", "()V", "canLoadClassesFromAnnotationProcessors", "", "findClass", "Ljava/lang/Class;", "className", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ClassFinder {
    public static final ClassFinder INSTANCE = new ClassFinder();

    @JvmStatic
    public static final boolean canLoadClassesFromAnnotationProcessors() {
        return false;
    }

    private ClassFinder() {
    }

    @JvmStatic
    public static final Class<?> findClass(String className) throws ClassNotFoundException {
        Intrinsics.checkNotNullParameter(className, "className");
        if (canLoadClassesFromAnnotationProcessors()) {
            return Class.forName(className);
        }
        return null;
    }
}
