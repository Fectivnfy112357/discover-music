package com.facebook.react.modules.fresco;

import android.util.Pair;
import com.facebook.imagepipeline.listener.BaseRequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.systrace.Systrace;
import com.umeng.analytics.pro.bm;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: SystraceRequestListener.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J.\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011H\u0016J6\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011H\u0016J.\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0011H\u0016J\u0018\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0016J\u0010\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0016J(\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J(\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010!\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010\f\u001a\u00020\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\b0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010\t\u001a\u001a\u0012\u0004\u0012\u00020\u0007\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\b0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/facebook/react/modules/fresco/SystraceRequestListener;", "Lcom/facebook/imagepipeline/listener/BaseRequestListener;", "()V", "currentId", "", "producerId", "", "", "Landroid/util/Pair;", "requestsId", "onProducerEvent", "", "requestId", "producerName", "eventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", bm.aM, "", "onProducerFinishWithSuccess", "onProducerStart", "onRequestCancellation", "onRequestFailure", "request", "Lcom/facebook/imagepipeline/request/ImageRequest;", "throwable", "isPrefetch", "", "onRequestStart", "callerContext", "", "onRequestSuccess", "requiresExtraMap", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SystraceRequestListener extends BaseRequestListener {
    private int currentId;
    private Map<String, Pair<Integer, String>> producerId = new LinkedHashMap();
    private Map<String, Pair<Integer, String>> requestsId = new LinkedHashMap();

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public boolean requiresExtraMap(String requestId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        return false;
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerStart(String requestId, String producerName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (Systrace.isTracing(0L)) {
            Pair<Integer, String> pairCreate = Pair.create(Integer.valueOf(this.currentId), "FRESCO_PRODUCER_" + StringsKt.replace$default(producerName, ':', '_', false, 4, (Object) null));
            Object second = pairCreate.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Systrace.beginAsyncSection(0L, (String) second, this.currentId);
            Map<String, Pair<Integer, String>> map = this.producerId;
            Intrinsics.checkNotNull(pairCreate);
            map.put(requestId, pairCreate);
            this.currentId++;
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithSuccess(String requestId, String producerName, Map<String, String> extraMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (Systrace.isTracing(0L) && this.producerId.containsKey(requestId)) {
            Pair<Integer, String> pair = this.producerId.get(requestId);
            Intrinsics.checkNotNull(pair);
            Pair<Integer, String> pair2 = pair;
            Object second = pair2.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Object first = pair2.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Systrace.endAsyncSection(0L, (String) second, ((Number) first).intValue());
            this.producerId.remove(requestId);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithFailure(String requestId, String producerName, Throwable t, Map<String, String> extraMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        Intrinsics.checkNotNullParameter(t, "t");
        if (Systrace.isTracing(0L) && this.producerId.containsKey(requestId)) {
            Pair<Integer, String> pair = this.producerId.get(requestId);
            Intrinsics.checkNotNull(pair);
            Pair<Integer, String> pair2 = pair;
            Object second = pair2.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Object first = pair2.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Systrace.endAsyncSection(0L, (String) second, ((Number) first).intValue());
            this.producerId.remove(requestId);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerFinishWithCancellation(String requestId, String producerName, Map<String, String> extraMap) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (Systrace.isTracing(0L) && this.producerId.containsKey(requestId)) {
            Pair<Integer, String> pair = this.producerId.get(requestId);
            Intrinsics.checkNotNull(pair);
            Pair<Integer, String> pair2 = pair;
            Object second = pair2.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Object first = pair2.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Systrace.endAsyncSection(0L, (String) second, ((Number) first).intValue());
            this.producerId.remove(requestId);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.producers.ProducerListener
    public void onProducerEvent(String requestId, String producerName, String eventName) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        Intrinsics.checkNotNullParameter(eventName, "eventName");
        if (Systrace.isTracing(0L)) {
            Systrace.traceInstant(0L, "FRESCO_PRODUCER_EVENT_" + StringsKt.replace$default(requestId, ':', '_', false, 4, (Object) null) + "_" + StringsKt.replace$default(producerName, ':', '_', false, 4, (Object) null) + "_" + StringsKt.replace$default(eventName, ':', '_', false, 4, (Object) null), Systrace.EventScope.THREAD);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestStart(ImageRequest request, Object callerContext, String requestId, boolean isPrefetch) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callerContext, "callerContext");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        if (Systrace.isTracing(0L)) {
            StringBuilder sb = new StringBuilder("FRESCO_REQUEST_");
            String string = request.getSourceUri().toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            sb.append(StringsKt.replace$default(string, ':', '_', false, 4, (Object) null));
            Pair<Integer, String> pairCreate = Pair.create(Integer.valueOf(this.currentId), sb.toString());
            Object second = pairCreate.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Systrace.beginAsyncSection(0L, (String) second, this.currentId);
            Map<String, Pair<Integer, String>> map = this.requestsId;
            Intrinsics.checkNotNull(pairCreate);
            map.put(requestId, pairCreate);
            this.currentId++;
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        if (Systrace.isTracing(0L) && this.requestsId.containsKey(requestId)) {
            Pair<Integer, String> pair = this.requestsId.get(requestId);
            Intrinsics.checkNotNull(pair);
            Pair<Integer, String> pair2 = pair;
            Object second = pair2.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Object first = pair2.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Systrace.endAsyncSection(0L, (String) second, ((Number) first).intValue());
            this.requestsId.remove(requestId);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        if (Systrace.isTracing(0L) && this.requestsId.containsKey(requestId)) {
            Pair<Integer, String> pair = this.requestsId.get(requestId);
            Intrinsics.checkNotNull(pair);
            Pair<Integer, String> pair2 = pair;
            Object second = pair2.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Object first = pair2.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Systrace.endAsyncSection(0L, (String) second, ((Number) first).intValue());
            this.requestsId.remove(requestId);
        }
    }

    @Override // com.facebook.imagepipeline.listener.BaseRequestListener, com.facebook.imagepipeline.listener.RequestListener
    public void onRequestCancellation(String requestId) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        if (Systrace.isTracing(0L) && this.requestsId.containsKey(requestId)) {
            Pair<Integer, String> pair = this.requestsId.get(requestId);
            Intrinsics.checkNotNull(pair);
            Pair<Integer, String> pair2 = pair;
            Object second = pair2.second;
            Intrinsics.checkNotNullExpressionValue(second, "second");
            Object first = pair2.first;
            Intrinsics.checkNotNullExpressionValue(first, "first");
            Systrace.endAsyncSection(0L, (String) second, ((Number) first).intValue());
            this.requestsId.remove(requestId);
        }
    }
}
