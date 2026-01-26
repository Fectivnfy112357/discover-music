package com.facebook.react.modules.fresco;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.bridge.ReadableMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactNetworkImageRequest.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lcom/facebook/react/modules/fresco/ReactNetworkImageRequest;", "Lcom/facebook/imagepipeline/request/ImageRequest;", "builder", "Lcom/facebook/imagepipeline/request/ImageRequestBuilder;", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "(Lcom/facebook/imagepipeline/request/ImageRequestBuilder;Lcom/facebook/react/bridge/ReadableMap;)V", "getHeaders$ReactAndroid_release", "()Lcom/facebook/react/bridge/ReadableMap;", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactNetworkImageRequest extends ImageRequest {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ReadableMap headers;

    public /* synthetic */ ReactNetworkImageRequest(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap, DefaultConstructorMarker defaultConstructorMarker) {
        this(imageRequestBuilder, readableMap);
    }

    @JvmStatic
    public static final ReactNetworkImageRequest fromBuilderWithHeaders(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap) {
        return INSTANCE.fromBuilderWithHeaders(imageRequestBuilder, readableMap);
    }

    /* renamed from: getHeaders$ReactAndroid_release, reason: from getter */
    public final ReadableMap getHeaders() {
        return this.headers;
    }

    private ReactNetworkImageRequest(ImageRequestBuilder imageRequestBuilder, ReadableMap readableMap) {
        super(imageRequestBuilder);
        this.headers = readableMap;
    }

    /* compiled from: ReactNetworkImageRequest.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0007¨\u0006\t"}, d2 = {"Lcom/facebook/react/modules/fresco/ReactNetworkImageRequest$Companion;", "", "()V", "fromBuilderWithHeaders", "Lcom/facebook/react/modules/fresco/ReactNetworkImageRequest;", "builder", "Lcom/facebook/imagepipeline/request/ImageRequestBuilder;", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ReactNetworkImageRequest fromBuilderWithHeaders(ImageRequestBuilder builder, ReadableMap headers) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            return new ReactNetworkImageRequest(builder, headers, null);
        }
    }
}
