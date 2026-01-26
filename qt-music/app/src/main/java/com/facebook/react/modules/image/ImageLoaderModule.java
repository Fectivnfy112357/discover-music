package com.facebook.react.modules.image;

import android.net.Uri;
import android.util.SparseArray;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.BaseDataSubscriber;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.views.image.ReactCallerContextFactory;
import com.facebook.react.views.imagehelper.ImageSource;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageLoaderModule.kt */
@ReactModule(name = "ImageLoader")
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 32\u00020\u00012\u00020\u0002:\u00013B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020#H\u0017J$\u0010$\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\"\u001a\u00020#H\u0017J\b\u0010'\u001a\u00020\u001cH\u0016J\b\u0010(\u001a\u00020\u001cH\u0016J\b\u0010)\u001a\u00020\u001cH\u0016J\"\u0010*\u001a\u00020\u001c2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010+\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020#H\u0016J\u0018\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.2\u0006\u0010\"\u001a\u00020#H\u0017J \u0010/\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u0002002\u000e\u00101\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014H\u0002J\u001a\u00102\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0015\u0018\u00010\u00142\u0006\u0010\u001d\u001a\u000200H\u0002R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u00064"}, d2 = {"Lcom/facebook/react/modules/image/ImageLoaderModule;", "Lcom/facebook/fbreact/specs/NativeImageLoaderAndroidSpec;", "Lcom/facebook/react/bridge/LifecycleEventListener;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", "callerContext", "", "(Lcom/facebook/react/bridge/ReactApplicationContext;Ljava/lang/Object;)V", "imagePipeline", "Lcom/facebook/imagepipeline/core/ImagePipeline;", "callerContextFactory", "Lcom/facebook/react/views/image/ReactCallerContextFactory;", "(Lcom/facebook/react/bridge/ReactApplicationContext;Lcom/facebook/imagepipeline/core/ImagePipeline;Lcom/facebook/react/views/image/ReactCallerContextFactory;)V", "_imagePipeline", "getCallerContext", "()Ljava/lang/Object;", "enqueuedRequestMonitor", "enqueuedRequests", "Landroid/util/SparseArray;", "Lcom/facebook/datasource/DataSource;", "Ljava/lang/Void;", "value", "getImagePipeline", "()Lcom/facebook/imagepipeline/core/ImagePipeline;", "setImagePipeline", "(Lcom/facebook/imagepipeline/core/ImagePipeline;)V", "abortRequest", "", "requestId", "", "getSize", "uriString", "", BaseJavaModule.METHOD_TYPE_PROMISE, "Lcom/facebook/react/bridge/Promise;", "getSizeWithHeaders", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "onHostDestroy", "onHostPause", "onHostResume", "prefetchImage", "requestIdAsDouble", "queryCache", "uris", "Lcom/facebook/react/bridge/ReadableArray;", "registerRequest", "", "request", "removeRequest", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ImageLoaderModule extends NativeImageLoaderAndroidSpec implements LifecycleEventListener {
    private static final String ERROR_GET_SIZE_FAILURE = "E_GET_SIZE_FAILURE";
    private static final String ERROR_INVALID_URI = "E_INVALID_URI";
    private static final String ERROR_PREFETCH_FAILURE = "E_PREFETCH_FAILURE";
    public static final String NAME = "ImageLoader";
    private ImagePipeline _imagePipeline;
    private final Object callerContext;
    private ReactCallerContextFactory callerContextFactory;
    private final Object enqueuedRequestMonitor;
    private final SparseArray<DataSource<Void>> enqueuedRequests;

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostPause() {
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostResume() {
    }

    private final Object getCallerContext() {
        Object orCreateCallerContext;
        ReactCallerContextFactory reactCallerContextFactory = this.callerContextFactory;
        return (reactCallerContextFactory == null || (orCreateCallerContext = reactCallerContextFactory.getOrCreateCallerContext("", "")) == null) ? this.callerContext : orCreateCallerContext;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImagePipeline getImagePipeline() {
        ImagePipeline imagePipeline = this._imagePipeline;
        if (imagePipeline != null) {
            return imagePipeline;
        }
        ImagePipeline imagePipeline2 = Fresco.getImagePipeline();
        Intrinsics.checkNotNullExpressionValue(imagePipeline2, "getImagePipeline(...)");
        return imagePipeline2;
    }

    private final void setImagePipeline(ImagePipeline imagePipeline) {
        this._imagePipeline = imagePipeline;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageLoaderModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.enqueuedRequestMonitor = new Object();
        this.enqueuedRequests = new SparseArray<>();
        this.callerContext = this;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageLoaderModule(ReactApplicationContext reactContext, Object obj) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.enqueuedRequestMonitor = new Object();
        this.enqueuedRequests = new SparseArray<>();
        this.callerContext = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageLoaderModule(ReactApplicationContext reactContext, ImagePipeline imagePipeline, ReactCallerContextFactory callerContextFactory) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        Intrinsics.checkNotNullParameter(imagePipeline, "imagePipeline");
        Intrinsics.checkNotNullParameter(callerContextFactory, "callerContextFactory");
        this.enqueuedRequestMonitor = new Object();
        this.enqueuedRequests = new SparseArray<>();
        this.callerContextFactory = callerContextFactory;
        setImagePipeline(imagePipeline);
        this.callerContext = null;
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void getSize(String uriString, final Promise promise) throws NumberFormatException {
        Intrinsics.checkNotNullParameter(promise, "promise");
        String str = uriString;
        if (str == null || str.length() == 0) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ImageRequest imageRequestBuild = ImageRequestBuilder.newBuilderWithSource(new ImageSource(reactApplicationContext, uriString, 0.0d, 0.0d, 12, null).getUri()).build();
        Intrinsics.checkNotNullExpressionValue(imageRequestBuild, "build(...)");
        getImagePipeline().fetchDecodedImage(imageRequestBuild, getCallerContext()).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: com.facebook.react.modules.image.ImageLoaderModule$getSize$dataSubscriber$1
            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                Intrinsics.checkNotNullParameter(dataSource, "dataSource");
                if (dataSource.isFinished()) {
                    CloseableReference<CloseableImage> result = dataSource.getResult();
                    try {
                        if (result == null) {
                            promise.reject("E_GET_SIZE_FAILURE", "Failed to get the size of the image");
                            return;
                        }
                        try {
                            CloseableImage closeableImage = result.get();
                            Intrinsics.checkNotNullExpressionValue(closeableImage, "get(...)");
                            CloseableImage closeableImage2 = closeableImage;
                            WritableMap writableMapCreateMap = Arguments.createMap();
                            Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap(...)");
                            writableMapCreateMap.putInt("width", closeableImage2.getWidth());
                            writableMapCreateMap.putInt("height", closeableImage2.getHeight());
                            promise.resolve(writableMapCreateMap);
                        } catch (Exception e) {
                            promise.reject("E_GET_SIZE_FAILURE", e);
                        }
                    } finally {
                        CloseableReference.closeSafely(result);
                    }
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                Intrinsics.checkNotNullParameter(dataSource, "dataSource");
                promise.reject("E_GET_SIZE_FAILURE", dataSource.getFailureCause());
            }
        }, CallerThreadExecutor.getInstance());
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void getSizeWithHeaders(String uriString, ReadableMap headers, final Promise promise) {
        Intrinsics.checkNotNullParameter(promise, "promise");
        String str = uriString;
        if (str == null || str.length() == 0) {
            promise.reject(ERROR_INVALID_URI, "Cannot get the size of an image for an empty URI");
            return;
        }
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Intrinsics.checkNotNullExpressionValue(reactApplicationContext, "getReactApplicationContext(...)");
        ImageRequestBuilder imageRequestBuilderNewBuilderWithSource = ImageRequestBuilder.newBuilderWithSource(new ImageSource(reactApplicationContext, uriString, 0.0d, 0.0d, 12, null).getUri());
        Intrinsics.checkNotNullExpressionValue(imageRequestBuilderNewBuilderWithSource, "newBuilderWithSource(...)");
        getImagePipeline().fetchDecodedImage(ReactNetworkImageRequest.INSTANCE.fromBuilderWithHeaders(imageRequestBuilderNewBuilderWithSource, headers), getCallerContext()).subscribe(new BaseDataSubscriber<CloseableReference<CloseableImage>>() { // from class: com.facebook.react.modules.image.ImageLoaderModule$getSizeWithHeaders$dataSubscriber$1
            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onNewResultImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                Intrinsics.checkNotNullParameter(dataSource, "dataSource");
                if (dataSource.isFinished()) {
                    CloseableReference<CloseableImage> result = dataSource.getResult();
                    try {
                        if (result == null) {
                            promise.reject("E_GET_SIZE_FAILURE", "Failed to get the size of the image");
                            return;
                        }
                        try {
                            CloseableImage closeableImage = result.get();
                            Intrinsics.checkNotNullExpressionValue(closeableImage, "get(...)");
                            CloseableImage closeableImage2 = closeableImage;
                            WritableMap writableMapCreateMap = Arguments.createMap();
                            Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap(...)");
                            writableMapCreateMap.putInt("width", closeableImage2.getWidth());
                            writableMapCreateMap.putInt("height", closeableImage2.getHeight());
                            promise.resolve(writableMapCreateMap);
                        } catch (Exception e) {
                            promise.reject("E_GET_SIZE_FAILURE", e);
                        }
                    } finally {
                        CloseableReference.closeSafely(result);
                    }
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
                Intrinsics.checkNotNullParameter(dataSource, "dataSource");
                promise.reject("E_GET_SIZE_FAILURE", dataSource.getFailureCause());
            }
        }, CallerThreadExecutor.getInstance());
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    public void prefetchImage(String uriString, double requestIdAsDouble, final Promise promise) throws NumberFormatException {
        Intrinsics.checkNotNullParameter(promise, "promise");
        final int i = (int) requestIdAsDouble;
        String str = uriString;
        if (str == null || str.length() == 0) {
            promise.reject(ERROR_INVALID_URI, "Cannot prefetch an image for an empty URI");
            return;
        }
        ImageRequest imageRequestBuild = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uriString)).build();
        Intrinsics.checkNotNullExpressionValue(imageRequestBuild, "build(...)");
        DataSource<Void> dataSourcePrefetchToDiskCache = getImagePipeline().prefetchToDiskCache(imageRequestBuild, getCallerContext());
        BaseDataSubscriber<Void> baseDataSubscriber = new BaseDataSubscriber<Void>() { // from class: com.facebook.react.modules.image.ImageLoaderModule$prefetchImage$prefetchSubscriber$1
            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onNewResultImpl(DataSource<Void> dataSource) {
                Intrinsics.checkNotNullParameter(dataSource, "dataSource");
                if (dataSource.isFinished()) {
                    try {
                        try {
                            this.this$0.removeRequest(i);
                            promise.resolve(true);
                        } catch (Exception e) {
                            promise.reject("E_PREFETCH_FAILURE", e);
                        }
                    } finally {
                        dataSource.close();
                    }
                }
            }

            @Override // com.facebook.datasource.BaseDataSubscriber
            protected void onFailureImpl(DataSource<Void> dataSource) {
                Intrinsics.checkNotNullParameter(dataSource, "dataSource");
                try {
                    this.this$0.removeRequest(i);
                    promise.reject("E_PREFETCH_FAILURE", dataSource.getFailureCause());
                } finally {
                    dataSource.close();
                }
            }
        };
        registerRequest(i, dataSourcePrefetchToDiskCache);
        dataSourcePrefetchToDiskCache.subscribe(baseDataSubscriber, CallerThreadExecutor.getInstance());
    }

    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    public void abortRequest(double requestId) {
        DataSource<Void> dataSourceRemoveRequest = removeRequest((int) requestId);
        if (dataSourceRemoveRequest != null) {
            dataSourceRemoveRequest.close();
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.facebook.react.modules.image.ImageLoaderModule$queryCache$1] */
    @Override // com.facebook.fbreact.specs.NativeImageLoaderAndroidSpec
    @ReactMethod
    public void queryCache(ReadableArray uris, Promise promise) {
        Intrinsics.checkNotNullParameter(uris, "uris");
        Intrinsics.checkNotNullParameter(promise, "promise");
        new GuardedAsyncTask<Void, Void>(uris, promise, getReactApplicationContext()) { // from class: com.facebook.react.modules.image.ImageLoaderModule.queryCache.1
            final /* synthetic */ Promise $promise;
            final /* synthetic */ ReadableArray $uris;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(reactApplicationContext);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.react.bridge.GuardedAsyncTask
            public void doInBackgroundGuarded(Void... params) {
                Intrinsics.checkNotNullParameter(params, "params");
                WritableMap writableMapCreateMap = Arguments.createMap();
                Intrinsics.checkNotNullExpressionValue(writableMapCreateMap, "createMap(...)");
                ImagePipeline imagePipeline = ImageLoaderModule.this.getImagePipeline();
                int size = this.$uris.size();
                for (int i = 0; i < size; i++) {
                    String string = this.$uris.getString(i);
                    if (string.length() > 0) {
                        Uri uri = Uri.parse(string);
                        if (imagePipeline.isInBitmapMemoryCache(uri)) {
                            writableMapCreateMap.putString(string, "memory");
                        } else if (imagePipeline.isInDiskCacheSync(uri)) {
                            writableMapCreateMap.putString(string, "disk");
                        }
                    }
                }
                this.$promise.resolve(writableMapCreateMap);
            }
        }.executeOnExecutor(GuardedAsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private final void registerRequest(int requestId, DataSource<Void> request) {
        synchronized (this.enqueuedRequestMonitor) {
            this.enqueuedRequests.put(requestId, request);
            Unit unit = Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DataSource<Void> removeRequest(int requestId) {
        DataSource<Void> dataSource;
        synchronized (this.enqueuedRequestMonitor) {
            dataSource = this.enqueuedRequests.get(requestId);
            this.enqueuedRequests.remove(requestId);
        }
        return dataSource;
    }

    @Override // com.facebook.react.bridge.LifecycleEventListener
    public void onHostDestroy() {
        synchronized (this.enqueuedRequestMonitor) {
            int size = this.enqueuedRequests.size();
            for (int i = 0; i < size; i++) {
                DataSource<Void> dataSourceValueAt = this.enqueuedRequests.valueAt(i);
                Intrinsics.checkNotNullExpressionValue(dataSourceValueAt, "valueAt(...)");
                dataSourceValueAt.close();
            }
            this.enqueuedRequests.clear();
            Unit unit = Unit.INSTANCE;
        }
    }
}
