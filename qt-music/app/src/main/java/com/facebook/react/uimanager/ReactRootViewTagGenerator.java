package com.facebook.react.uimanager;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: ReactRootViewTagGenerator.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/uimanager/ReactRootViewTagGenerator;", "", "()V", "ROOT_VIEW_TAG_INCREMENT", "", "nextRootViewTag", "getNextRootViewTag", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactRootViewTagGenerator {
    private static final int ROOT_VIEW_TAG_INCREMENT = 10;
    public static final ReactRootViewTagGenerator INSTANCE = new ReactRootViewTagGenerator();
    private static int nextRootViewTag = 1;

    private ReactRootViewTagGenerator() {
    }

    @JvmStatic
    public static final synchronized int getNextRootViewTag() {
        int i;
        i = nextRootViewTag;
        nextRootViewTag = i + 10;
        return i;
    }
}
