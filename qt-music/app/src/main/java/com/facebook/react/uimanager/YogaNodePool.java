package com.facebook.react.uimanager;

import com.facebook.react.common.ClearableSynchronizedPool;
import com.facebook.yoga.YogaNode;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;

/* compiled from: YogaNodePool.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0007R!\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/uimanager/YogaNodePool;", "", "()V", "pool", "Lcom/facebook/react/common/ClearableSynchronizedPool;", "Lcom/facebook/yoga/YogaNode;", "getPool", "()Lcom/facebook/react/common/ClearableSynchronizedPool;", "pool$delegate", "Lkotlin/Lazy;", "get", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class YogaNodePool {
    public static final YogaNodePool INSTANCE = new YogaNodePool();

    /* renamed from: pool$delegate, reason: from kotlin metadata */
    private static final Lazy pool = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ClearableSynchronizedPool<YogaNode>>() { // from class: com.facebook.react.uimanager.YogaNodePool$pool$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ClearableSynchronizedPool<YogaNode> invoke() {
            return new ClearableSynchronizedPool<>(1024);
        }
    });

    private YogaNodePool() {
    }

    private final ClearableSynchronizedPool<YogaNode> getPool() {
        return (ClearableSynchronizedPool) pool.getValue();
    }

    @JvmStatic
    public static final ClearableSynchronizedPool<YogaNode> get() {
        return INSTANCE.getPool();
    }
}
