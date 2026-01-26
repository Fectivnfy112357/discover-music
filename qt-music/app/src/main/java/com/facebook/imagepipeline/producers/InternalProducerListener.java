package com.facebook.imagepipeline.producers;

import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: InternalProducerListener.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005J$\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0016J4\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0018\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u0013H\u0016J>\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0018\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u0013H\u0016J4\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0018\u0010\u0012\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0018\u00010\u0013H\u0016J\u001a\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/facebook/imagepipeline/producers/InternalProducerListener;", "Lcom/facebook/imagepipeline/producers/ProducerListener2;", "producerListener", "Lcom/facebook/imagepipeline/producers/ProducerListener;", "producerListener2", "(Lcom/facebook/imagepipeline/producers/ProducerListener;Lcom/facebook/imagepipeline/producers/ProducerListener2;)V", "getProducerListener", "()Lcom/facebook/imagepipeline/producers/ProducerListener;", "getProducerListener2", "()Lcom/facebook/imagepipeline/producers/ProducerListener2;", "onProducerEvent", "", f.X, "Lcom/facebook/imagepipeline/producers/ProducerContext;", "producerName", "", "eventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", bm.aM, "", "onProducerFinishWithSuccess", "onProducerStart", "onUltimateProducerReached", "successful", "", "requiresExtraMap", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public class InternalProducerListener implements ProducerListener2 {
    private final ProducerListener producerListener;
    private final ProducerListener2 producerListener2;

    public InternalProducerListener(ProducerListener producerListener, ProducerListener2 producerListener2) {
        this.producerListener = producerListener;
        this.producerListener2 = producerListener2;
    }

    public final ProducerListener getProducerListener() {
        return this.producerListener;
    }

    public final ProducerListener2 getProducerListener2() {
        return this.producerListener2;
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerStart(ProducerContext context, String producerName) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        if (producerListener != null) {
            producerListener.onProducerStart(context.getId(), producerName);
        }
        ProducerListener2 producerListener2 = this.producerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerStart(context, producerName);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerEvent(ProducerContext context, String producerName, String eventName) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        if (producerListener != null) {
            producerListener.onProducerEvent(context.getId(), producerName, eventName);
        }
        ProducerListener2 producerListener2 = this.producerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerEvent(context, producerName, eventName);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithSuccess(ProducerContext context, String producerName, Map<String, String> extraMap) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        if (producerListener != null) {
            producerListener.onProducerFinishWithSuccess(context.getId(), producerName, extraMap);
        }
        ProducerListener2 producerListener2 = this.producerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerFinishWithSuccess(context, producerName, extraMap);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithFailure(ProducerContext context, String producerName, Throwable t, Map<String, String> extraMap) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        if (producerListener != null) {
            producerListener.onProducerFinishWithFailure(context.getId(), producerName, t, extraMap);
        }
        ProducerListener2 producerListener2 = this.producerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerFinishWithFailure(context, producerName, t, extraMap);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onProducerFinishWithCancellation(ProducerContext context, String producerName, Map<String, String> extraMap) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        if (producerListener != null) {
            producerListener.onProducerFinishWithCancellation(context.getId(), producerName, extraMap);
        }
        ProducerListener2 producerListener2 = this.producerListener2;
        if (producerListener2 != null) {
            producerListener2.onProducerFinishWithCancellation(context, producerName, extraMap);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public void onUltimateProducerReached(ProducerContext context, String producerName, boolean successful) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        if (producerListener != null) {
            producerListener.onUltimateProducerReached(context.getId(), producerName, successful);
        }
        ProducerListener2 producerListener2 = this.producerListener2;
        if (producerListener2 != null) {
            producerListener2.onUltimateProducerReached(context, producerName, successful);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener2
    public boolean requiresExtraMap(ProducerContext context, String producerName) {
        Intrinsics.checkNotNullParameter(context, "context");
        ProducerListener producerListener = this.producerListener;
        Boolean boolValueOf = producerListener != null ? Boolean.valueOf(producerListener.requiresExtraMap(context.getId())) : null;
        if (!Intrinsics.areEqual((Object) boolValueOf, (Object) true)) {
            ProducerListener2 producerListener2 = this.producerListener2;
            boolValueOf = producerListener2 != null ? Boolean.valueOf(producerListener2.requiresExtraMap(context, producerName)) : null;
        }
        if (boolValueOf != null) {
            return boolValueOf.booleanValue();
        }
        return false;
    }
}
