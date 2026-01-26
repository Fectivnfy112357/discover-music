package com.facebook.react.internal;

import com.facebook.systrace.Systrace;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SystraceSection.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/internal/SystraceSection;", "Ljava/lang/AutoCloseable;", "sectionName", "", "(Ljava/lang/String;)V", "close", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SystraceSection implements AutoCloseable {
    public SystraceSection(String sectionName) {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        Systrace.beginSection(0L, sectionName);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Systrace.endSection(0L);
    }
}
