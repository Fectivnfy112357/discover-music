package coil3;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: Access modifiers changed from: private */
/* compiled from: SingletonImageLoader.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public /* synthetic */ class SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private {
    private static final /* synthetic */ AtomicReferenceFieldUpdater reference$volatile$FU = AtomicReferenceFieldUpdater.newUpdater(SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private.class, Object.class, "reference$volatile");
    private volatile /* synthetic */ Object reference$volatile;

    private SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private() {
    }

    public /* synthetic */ SingletonImageLoader$SingletonImageLoader$VolatileWrapper$atomicfu$private(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private final /* synthetic */ Object getReference$volatile() {
        return this.reference$volatile;
    }

    private final /* synthetic */ void setReference$volatile(Object obj) {
        this.reference$volatile = obj;
    }
}
