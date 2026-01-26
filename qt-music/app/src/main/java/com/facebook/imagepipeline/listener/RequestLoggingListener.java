package com.facebook.imagepipeline.listener;

import android.os.SystemClock;
import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.request.ImageRequest;
import com.umeng.analytics.AnalyticsConfig;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: RequestLoggingListener.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J.\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010H\u0016J6\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010H\u0016J.\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J(\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J(\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010\u001f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010 \u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u001bH\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u0006H\u0016R(\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0083\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0083\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/facebook/imagepipeline/listener/RequestLoggingListener;", "Lcom/facebook/imagepipeline/listener/RequestListener;", "()V", "producerStartTimeMap", "", "Landroid/util/Pair;", "", "", "requestStartTimeMap", "onProducerEvent", "", "requestId", "producerName", "producerEventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", "throwable", "", "onProducerFinishWithSuccess", "onProducerStart", "onRequestCancellation", "onRequestFailure", "request", "Lcom/facebook/imagepipeline/request/ImageRequest;", "isPrefetch", "", "onRequestStart", "callerContextObject", "", "onRequestSuccess", "onUltimateProducerReached", "successful", "requiresExtraMap", "id", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class RequestLoggingListener implements RequestListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "RequestLoggingListener";
    private final Map<Pair<String, String>, Long> producerStartTimeMap = new HashMap();
    private final Map<String, Long> requestStartTimeMap = new HashMap();

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public synchronized void onRequestStart(ImageRequest request, Object callerContextObject, String requestId, boolean isPrefetch) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(callerContextObject, "callerContextObject");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        if (FLog.isLoggable(2)) {
            Companion companion = INSTANCE;
            FLog.v(TAG, "time %d: onRequestSubmit: {requestId: %s, callerContext: %s, isPrefetch: %b}", Long.valueOf(companion.getTime()), requestId, callerContextObject, Boolean.valueOf(isPrefetch));
            this.requestStartTimeMap.put(requestId, Long.valueOf(companion.getTime()));
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public synchronized void onProducerStart(String requestId, String producerName) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (FLog.isLoggable(2)) {
            Pair<String, String> mapKey = Pair.create(requestId, producerName);
            long time = INSTANCE.getTime();
            Long lValueOf = Long.valueOf(time);
            Map<Pair<String, String>, Long> map = this.producerStartTimeMap;
            Intrinsics.checkNotNullExpressionValue(mapKey, "mapKey");
            map.put(mapKey, lValueOf);
            FLog.v(TAG, "time %d: onProducerStart: {requestId: %s, producer: %s}", Long.valueOf(time), requestId, producerName);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public synchronized void onProducerFinishWithSuccess(String requestId, String producerName, Map<String, String> extraMap) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (FLog.isLoggable(2)) {
            Long lRemove = this.producerStartTimeMap.remove(Pair.create(requestId, producerName));
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.v(TAG, "time %d: onProducerFinishWithSuccess: {requestId: %s, producer: %s, elapsedTime: %d ms, extraMap: %s}", Long.valueOf(time), requestId, producerName, Long.valueOf(companion.getElapsedTime(lRemove, time)), extraMap);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public synchronized void onProducerFinishWithFailure(String requestId, String producerName, Throwable throwable, Map<String, String> extraMap) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        if (FLog.isLoggable(5)) {
            Long lRemove = this.producerStartTimeMap.remove(Pair.create(requestId, producerName));
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.w(TAG, throwable, "time %d: onProducerFinishWithFailure: {requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s, throwable: %s}", Long.valueOf(time), requestId, producerName, Long.valueOf(companion.getElapsedTime(lRemove, time)), extraMap, throwable.toString());
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public synchronized void onProducerFinishWithCancellation(String requestId, String producerName, Map<String, String> extraMap) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (FLog.isLoggable(2)) {
            Long lRemove = this.producerStartTimeMap.remove(Pair.create(requestId, producerName));
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.v(TAG, "time %d: onProducerFinishWithCancellation: {requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s}", Long.valueOf(time), requestId, producerName, Long.valueOf(companion.getElapsedTime(lRemove, time)), extraMap);
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public synchronized void onProducerEvent(String requestId, String producerName, String producerEventName) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        Intrinsics.checkNotNullParameter(producerEventName, "producerEventName");
        if (FLog.isLoggable(2)) {
            Long l = this.producerStartTimeMap.get(Pair.create(requestId, producerName));
            Companion companion = INSTANCE;
            FLog.v(TAG, "time %d: onProducerEvent: {requestId: %s, stage: %s, eventName: %s; elapsedTime: %d ms}", Long.valueOf(companion.getTime()), requestId, producerName, producerEventName, Long.valueOf(companion.getElapsedTime(l, companion.getTime())));
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public synchronized void onUltimateProducerReached(String requestId, String producerName, boolean successful) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(producerName, "producerName");
        if (FLog.isLoggable(2)) {
            Long lRemove = this.producerStartTimeMap.remove(Pair.create(requestId, producerName));
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.v(TAG, "time %d: onUltimateProducerReached: {requestId: %s, producer: %s, elapsedTime: %d ms, success: %b}", Long.valueOf(time), requestId, producerName, Long.valueOf(companion.getElapsedTime(lRemove, time)), Boolean.valueOf(successful));
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public synchronized void onRequestSuccess(ImageRequest request, String requestId, boolean isPrefetch) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        if (FLog.isLoggable(2)) {
            Long lRemove = this.requestStartTimeMap.remove(requestId);
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.v(TAG, "time %d: onRequestSuccess: {requestId: %s, elapsedTime: %d ms}", Long.valueOf(time), requestId, Long.valueOf(companion.getElapsedTime(lRemove, time)));
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public synchronized void onRequestFailure(ImageRequest request, String requestId, Throwable throwable, boolean isPrefetch) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        if (FLog.isLoggable(5)) {
            Long lRemove = this.requestStartTimeMap.remove(requestId);
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.w(TAG, "time %d: onRequestFailure: {requestId: %s, elapsedTime: %d ms, throwable: %s}", Long.valueOf(time), requestId, Long.valueOf(companion.getElapsedTime(lRemove, time)), throwable.toString());
        }
    }

    @Override // com.facebook.imagepipeline.listener.RequestListener
    public synchronized void onRequestCancellation(String requestId) {
        Intrinsics.checkNotNullParameter(requestId, "requestId");
        if (FLog.isLoggable(2)) {
            Long lRemove = this.requestStartTimeMap.remove(requestId);
            Companion companion = INSTANCE;
            long time = companion.getTime();
            FLog.v(TAG, "time %d: onRequestCancellation: {requestId: %s, elapsedTime: %d ms}", Long.valueOf(time), requestId, Long.valueOf(companion.getElapsedTime(lRemove, time)));
        }
    }

    @Override // com.facebook.imagepipeline.producers.ProducerListener
    public boolean requiresExtraMap(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        return FLog.isLoggable(2);
    }

    /* compiled from: RequestLoggingListener.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/listener/RequestLoggingListener$Companion;", "", "()V", AbstractID3v1Tag.TAG, "", "time", "", "getTime", "()J", "getElapsedTime", AnalyticsConfig.RTD_START_TIME, "endTime", "(Ljava/lang/Long;J)J", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long getElapsedTime(Long startTime, long endTime) {
            if (startTime != null) {
                return endTime - startTime.longValue();
            }
            return -1L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final long getTime() {
            return SystemClock.uptimeMillis();
        }
    }
}
