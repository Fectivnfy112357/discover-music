package com.facebook.imagepipeline.backends.okhttp3;

import android.net.Uri;
import android.os.Looper;
import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import com.facebook.imagepipeline.common.BytesRange;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.BaseNetworkFetcher;
import com.facebook.imagepipeline.producers.BaseProducerContextCallbacks;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.google.common.net.HttpHeaders;
import com.umeng.analytics.pro.f;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: OkHttpNetworkFetcher.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 (2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002()B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B!\b\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001e\u0010\u000f\u001a\u00020\u00022\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J \u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J&\u0010\u001d\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001e2\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016J \u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0018\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00022\u0006\u0010 \u001a\u00020!H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher;", "Lcom/facebook/imagepipeline/producers/BaseNetworkFetcher;", "Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$OkHttpNetworkFetchState;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "callFactory", "Lokhttp3/Call$Factory;", "cancellationExecutor", "Ljava/util/concurrent/Executor;", "disableOkHttpCache", "", "(Lokhttp3/Call$Factory;Ljava/util/concurrent/Executor;Z)V", "cacheControl", "Lokhttp3/CacheControl;", "createFetchState", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", f.X, "Lcom/facebook/imagepipeline/producers/ProducerContext;", "fetch", "", "fetchState", "callback", "Lcom/facebook/imagepipeline/producers/NetworkFetcher$Callback;", "fetchWithRequest", "request", "Lokhttp3/Request;", "getExtraMap", "", "", "byteSize", "", "handleException", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/lang/Exception;", "onFetchCompletion", "Companion", "OkHttpNetworkFetchState", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public class OkHttpNetworkFetcher extends BaseNetworkFetcher<OkHttpNetworkFetchState> {
    private static final Companion Companion = new Companion(null);
    private static final String FETCH_TIME = "fetch_time";
    private static final String IMAGE_SIZE = "image_size";
    private static final String QUEUE_TIME = "queue_time";
    private static final String TOTAL_TIME = "total_time";
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    private final Executor cancellationExecutor;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OkHttpNetworkFetcher(Call.Factory callFactory, Executor cancellationExecutor) {
        this(callFactory, cancellationExecutor, false, 4, null);
        Intrinsics.checkNotNullParameter(callFactory, "callFactory");
        Intrinsics.checkNotNullParameter(cancellationExecutor, "cancellationExecutor");
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public /* bridge */ /* synthetic */ FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
        return createFetchState((Consumer<EncodedImage>) consumer, producerContext);
    }

    public /* synthetic */ OkHttpNetworkFetcher(Call.Factory factory, Executor executor, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(factory, executor, (i & 4) != 0 ? true : z);
    }

    public OkHttpNetworkFetcher(Call.Factory callFactory, Executor cancellationExecutor, boolean z) {
        Intrinsics.checkNotNullParameter(callFactory, "callFactory");
        Intrinsics.checkNotNullParameter(cancellationExecutor, "cancellationExecutor");
        this.callFactory = callFactory;
        this.cancellationExecutor = cancellationExecutor;
        this.cacheControl = z ? new CacheControl.Builder().noStore().build() : null;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public OkHttpNetworkFetcher(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "okHttpClient");
        OkHttpClient okHttpClient2 = okHttpClient;
        ExecutorService executorService = okHttpClient.dispatcher().executorService();
        Intrinsics.checkNotNullExpressionValue(executorService, "okHttpClient.dispatcher().executorService()");
        this(okHttpClient2, executorService, false, 4, null);
    }

    /* compiled from: OkHttpNetworkFetcher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0012\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\t8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$OkHttpNetworkFetchState;", "Lcom/facebook/imagepipeline/producers/FetchState;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "(Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;)V", "fetchCompleteTime", "", "responseTime", "submitTime", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class OkHttpNetworkFetchState extends FetchState {
        public long fetchCompleteTime;
        public long responseTime;
        public long submitTime;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OkHttpNetworkFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
            super(consumer, producerContext);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
        }
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public OkHttpNetworkFetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext context) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(context, "context");
        return new OkHttpNetworkFetchState(consumer, context);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(OkHttpNetworkFetchState fetchState, NetworkFetcher.Callback callback) {
        Intrinsics.checkNotNullParameter(fetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        fetchState.submitTime = SystemClock.elapsedRealtime();
        Uri uri = fetchState.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "fetchState.uri");
        try {
            Request.Builder requestBuilder = new Request.Builder().url(uri.toString()).get();
            CacheControl cacheControl = this.cacheControl;
            if (cacheControl != null) {
                Intrinsics.checkNotNullExpressionValue(requestBuilder, "requestBuilder");
                requestBuilder.cacheControl(cacheControl);
            }
            BytesRange bytesRange = fetchState.getContext().getImageRequest().getBytesRange();
            if (bytesRange != null) {
                requestBuilder.addHeader("Range", bytesRange.toHttpRangeHeaderValue());
            }
            Request requestBuild = requestBuilder.build();
            Intrinsics.checkNotNullExpressionValue(requestBuild, "requestBuilder.build()");
            fetchWithRequest(fetchState, callback, requestBuild);
        } catch (Exception e) {
            callback.onFailure(e);
        }
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public void onFetchCompletion(OkHttpNetworkFetchState fetchState, int byteSize) {
        Intrinsics.checkNotNullParameter(fetchState, "fetchState");
        fetchState.fetchCompleteTime = SystemClock.elapsedRealtime();
    }

    @Override // com.facebook.imagepipeline.producers.BaseNetworkFetcher, com.facebook.imagepipeline.producers.NetworkFetcher
    public Map<String, String> getExtraMap(OkHttpNetworkFetchState fetchState, int byteSize) {
        Intrinsics.checkNotNullParameter(fetchState, "fetchState");
        return MapsKt.mapOf(TuplesKt.to(QUEUE_TIME, String.valueOf(fetchState.responseTime - fetchState.submitTime)), TuplesKt.to(FETCH_TIME, String.valueOf(fetchState.fetchCompleteTime - fetchState.responseTime)), TuplesKt.to(TOTAL_TIME, String.valueOf(fetchState.fetchCompleteTime - fetchState.submitTime)), TuplesKt.to(IMAGE_SIZE, String.valueOf(byteSize)));
    }

    protected void fetchWithRequest(final OkHttpNetworkFetchState fetchState, final NetworkFetcher.Callback callback, Request request) {
        Intrinsics.checkNotNullParameter(fetchState, "fetchState");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(request, "request");
        Call callNewCall = this.callFactory.newCall(request);
        fetchState.getContext().addCallbacks(new AnonymousClass1(callNewCall, this));
        callNewCall.enqueue(new Callback() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher.fetchWithRequest.2
            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                fetchState.responseTime = SystemClock.elapsedRealtime();
                ResponseBody responseBodyBody = response.body();
                Unit unit = null;
                if (responseBodyBody != null) {
                    ResponseBody responseBody = responseBodyBody;
                    OkHttpNetworkFetcher okHttpNetworkFetcher = this;
                    NetworkFetcher.Callback callback2 = callback;
                    OkHttpNetworkFetchState okHttpNetworkFetchState = fetchState;
                    try {
                        ResponseBody responseBody2 = responseBody;
                        try {
                            if (!response.isSuccessful()) {
                                okHttpNetworkFetcher.handleException(call, new IOException("Unexpected HTTP code " + response), callback2);
                            } else {
                                BytesRange bytesRangeFromContentRangeHeader = BytesRange.INSTANCE.fromContentRangeHeader(response.header(HttpHeaders.CONTENT_RANGE));
                                if (bytesRangeFromContentRangeHeader != null && (bytesRangeFromContentRangeHeader.from != 0 || bytesRangeFromContentRangeHeader.to != Integer.MAX_VALUE)) {
                                    okHttpNetworkFetchState.setResponseBytesRange(bytesRangeFromContentRangeHeader);
                                    okHttpNetworkFetchState.setOnNewResultStatusFlags(8);
                                }
                                callback2.onResponse(responseBody2.byteStream(), responseBody2.getContentLength() < 0 ? 0 : (int) responseBody2.getContentLength());
                            }
                        } catch (Exception e) {
                            okHttpNetworkFetcher.handleException(call, e, callback2);
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(responseBody, null);
                        unit = Unit.INSTANCE;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(responseBody, th);
                            throw th2;
                        }
                    }
                }
                if (unit == null) {
                    this.handleException(call, new IOException("Response body null: " + response), callback);
                }
            }

            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                this.handleException(call, e, callback);
            }
        });
    }

    /* compiled from: OkHttpNetworkFetcher.kt */
    @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$fetchWithRequest$1", "Lcom/facebook/imagepipeline/producers/BaseProducerContextCallbacks;", "onCancellationRequested", "", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* renamed from: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher$fetchWithRequest$1, reason: invalid class name */
    public static final class AnonymousClass1 extends BaseProducerContextCallbacks {
        final /* synthetic */ Call $call;
        final /* synthetic */ OkHttpNetworkFetcher this$0;

        AnonymousClass1(Call call, OkHttpNetworkFetcher okHttpNetworkFetcher) {
            this.$call = call;
            this.this$0 = okHttpNetworkFetcher;
        }

        @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
        public void onCancellationRequested() {
            if (Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                Executor executor = this.this$0.cancellationExecutor;
                final Call call = this.$call;
                executor.execute(new Runnable() { // from class: com.facebook.imagepipeline.backends.okhttp3.OkHttpNetworkFetcher$fetchWithRequest$1$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        call.cancel();
                    }
                });
                return;
            }
            this.$call.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleException(Call call, Exception e, NetworkFetcher.Callback callback) {
        if (call.getCanceled()) {
            callback.onCancellation();
        } else {
            callback.onFailure(e);
        }
    }

    /* compiled from: OkHttpNetworkFetcher.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/imagepipeline/backends/okhttp3/OkHttpNetworkFetcher$Companion;", "", "()V", "FETCH_TIME", "", "IMAGE_SIZE", "QUEUE_TIME", "TOTAL_TIME", "imagepipeline-okhttp3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
