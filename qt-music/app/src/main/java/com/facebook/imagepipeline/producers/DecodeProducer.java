package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.core.app.NotificationCompat;
import androidx.core.os.EnvironmentCompat;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.core.DownsampleMode;
import com.facebook.imagepipeline.decoder.DecodeException;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.DownsampleUtil;
import com.facebook.imageutils.BitmapUtil;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.umeng.analytics.pro.bm;
import com.umeng.analytics.pro.f;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: DecodeProducer.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 92\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u00049:;<Bu\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u000e\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0001\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a¢\u0006\u0002\u0010\u001bJ$\u00103\u001a\u0002042\u0012\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002062\u0006\u00107\u001a\u000208H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010!R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0001¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001a¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006="}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer;", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "byteArrayPool", "Lcom/facebook/common/memory/ByteArrayPool;", "executor", "Ljava/util/concurrent/Executor;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "downsampleMode", "Lcom/facebook/imagepipeline/core/DownsampleMode;", "downsampleEnabledForNetwork", "", "decodeCancellationEnabled", "inputProducer", "Lcom/facebook/imagepipeline/image/EncodedImage;", "maxBitmapSize", "", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "reclaimMemoryRunnable", "Ljava/lang/Runnable;", "recoverFromDecoderOOM", "Lcom/facebook/common/internal/Supplier;", "(Lcom/facebook/common/memory/ByteArrayPool;Ljava/util/concurrent/Executor;Lcom/facebook/imagepipeline/decoder/ImageDecoder;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;Lcom/facebook/imagepipeline/core/DownsampleMode;ZZLcom/facebook/imagepipeline/producers/Producer;ILcom/facebook/imagepipeline/core/CloseableReferenceFactory;Ljava/lang/Runnable;Lcom/facebook/common/internal/Supplier;)V", "getByteArrayPool", "()Lcom/facebook/common/memory/ByteArrayPool;", "getCloseableReferenceFactory", "()Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "getDecodeCancellationEnabled", "()Z", "getDownsampleEnabledForNetwork", "getDownsampleMode", "()Lcom/facebook/imagepipeline/core/DownsampleMode;", "getExecutor", "()Ljava/util/concurrent/Executor;", "getImageDecoder", "()Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getInputProducer", "()Lcom/facebook/imagepipeline/producers/Producer;", "getMaxBitmapSize", "()I", "getProgressiveJpegConfig", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "getReclaimMemoryRunnable", "()Ljava/lang/Runnable;", "getRecoverFromDecoderOOM", "()Lcom/facebook/common/internal/Supplier;", "produceResults", "", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", f.X, "Lcom/facebook/imagepipeline/producers/ProducerContext;", "Companion", "LocalImagesProgressiveDecoder", "NetworkImagesProgressiveDecoder", "ProgressiveDecoder", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DecodeProducer implements Producer<CloseableReference<CloseableImage>> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES = 10;
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_BITMAP_BYTES = "byteCount";
    public static final String EXTRA_BITMAP_SIZE = "bitmapSize";
    public static final String EXTRA_HAS_GOOD_QUALITY = "hasGoodQuality";
    public static final String EXTRA_IMAGE_FORMAT_NAME = "imageFormat";
    public static final String EXTRA_IS_FINAL = "isFinal";
    private static final int MAX_BITMAP_SIZE = 104857600;
    public static final String NON_FATAL_DECODE_ERROR = "non_fatal_decode_error";
    public static final String PRODUCER_NAME = "DecodeProducer";
    public static final String REQUESTED_IMAGE_SIZE = "requestedImageSize";
    public static final String SAMPLE_SIZE = "sampleSize";
    private final ByteArrayPool byteArrayPool;
    private final CloseableReferenceFactory closeableReferenceFactory;
    private final boolean decodeCancellationEnabled;
    private final boolean downsampleEnabledForNetwork;
    private final DownsampleMode downsampleMode;
    private final Executor executor;
    private final ImageDecoder imageDecoder;
    private final Producer<EncodedImage> inputProducer;
    private final int maxBitmapSize;
    private final ProgressiveJpegConfig progressiveJpegConfig;
    private final Runnable reclaimMemoryRunnable;
    private final Supplier<Boolean> recoverFromDecoderOOM;

    public DecodeProducer(ByteArrayPool byteArrayPool, Executor executor, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, DownsampleMode downsampleMode, boolean z, boolean z2, Producer<EncodedImage> inputProducer, int i, CloseableReferenceFactory closeableReferenceFactory, Runnable runnable, Supplier<Boolean> recoverFromDecoderOOM) {
        Intrinsics.checkNotNullParameter(byteArrayPool, "byteArrayPool");
        Intrinsics.checkNotNullParameter(executor, "executor");
        Intrinsics.checkNotNullParameter(imageDecoder, "imageDecoder");
        Intrinsics.checkNotNullParameter(progressiveJpegConfig, "progressiveJpegConfig");
        Intrinsics.checkNotNullParameter(downsampleMode, "downsampleMode");
        Intrinsics.checkNotNullParameter(inputProducer, "inputProducer");
        Intrinsics.checkNotNullParameter(closeableReferenceFactory, "closeableReferenceFactory");
        Intrinsics.checkNotNullParameter(recoverFromDecoderOOM, "recoverFromDecoderOOM");
        this.byteArrayPool = byteArrayPool;
        this.executor = executor;
        this.imageDecoder = imageDecoder;
        this.progressiveJpegConfig = progressiveJpegConfig;
        this.downsampleMode = downsampleMode;
        this.downsampleEnabledForNetwork = z;
        this.decodeCancellationEnabled = z2;
        this.inputProducer = inputProducer;
        this.maxBitmapSize = i;
        this.closeableReferenceFactory = closeableReferenceFactory;
        this.reclaimMemoryRunnable = runnable;
        this.recoverFromDecoderOOM = recoverFromDecoderOOM;
    }

    public final ByteArrayPool getByteArrayPool() {
        return this.byteArrayPool;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final ImageDecoder getImageDecoder() {
        return this.imageDecoder;
    }

    public final ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.progressiveJpegConfig;
    }

    public final DownsampleMode getDownsampleMode() {
        return this.downsampleMode;
    }

    public final boolean getDownsampleEnabledForNetwork() {
        return this.downsampleEnabledForNetwork;
    }

    public final boolean getDecodeCancellationEnabled() {
        return this.decodeCancellationEnabled;
    }

    public final Producer<EncodedImage> getInputProducer() {
        return this.inputProducer;
    }

    public final int getMaxBitmapSize() {
        return this.maxBitmapSize;
    }

    public final CloseableReferenceFactory getCloseableReferenceFactory() {
        return this.closeableReferenceFactory;
    }

    public final Runnable getReclaimMemoryRunnable() {
        return this.reclaimMemoryRunnable;
    }

    public final Supplier<Boolean> getRecoverFromDecoderOOM() {
        return this.recoverFromDecoderOOM;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext context) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(context, "context");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            ImageRequest imageRequest = context.getImageRequest();
            this.inputProducer.produceResults((UriUtil.isNetworkUri(imageRequest.getSourceUri()) || ImageRequestBuilder.isCustomNetworkUri(imageRequest.getSourceUri())) ? new NetworkImagesProgressiveDecoder(this, consumer, context, new ProgressiveJpegParser(this.byteArrayPool), this.progressiveJpegConfig, this.decodeCancellationEnabled, this.maxBitmapSize) : new LocalImagesProgressiveDecoder(this, consumer, context, this.decodeCancellationEnabled, this.maxBitmapSize), context);
            return;
        }
        FrescoSystrace.beginSection("DecodeProducer#produceResults");
        try {
            ImageRequest imageRequest2 = context.getImageRequest();
            this.inputProducer.produceResults((UriUtil.isNetworkUri(imageRequest2.getSourceUri()) || ImageRequestBuilder.isCustomNetworkUri(imageRequest2.getSourceUri())) ? new NetworkImagesProgressiveDecoder(this, consumer, context, new ProgressiveJpegParser(this.byteArrayPool), this.progressiveJpegConfig, this.decodeCancellationEnabled, this.maxBitmapSize) : new LocalImagesProgressiveDecoder(this, consumer, context, this.decodeCancellationEnabled, this.maxBitmapSize), context);
            Unit unit = Unit.INSTANCE;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DecodeProducer.kt */
    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B1\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ \u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002JX\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000fH\u0002J\u0010\u0010/\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0002H$J\b\u00100\u001a\u00020!H\u0002J\u0010\u00101\u001a\u00020!2\u0006\u00102\u001a\u000203H\u0002J\u001a\u00104\u001a\u00020!2\b\u00105\u001a\u0004\u0018\u00010\u00042\u0006\u0010#\u001a\u00020\fH\u0002J\"\u00106\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020\u00022\u0006\u00107\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u001dH\u0002J\u0010\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\nH\u0002J\u0010\u0010:\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0002H\u0002J\b\u0010;\u001a\u00020!H\u0016J\u0010\u0010<\u001a\u00020!2\u0006\u00102\u001a\u000203H\u0016J\u001a\u0010=\u001a\u00020!2\b\u0010>\u001a\u0004\u0018\u00010\u00022\u0006\u0010#\u001a\u00020\fH\u0016J\u0010\u0010?\u001a\u00020!2\u0006\u0010@\u001a\u00020AH\u0014J\"\u0010B\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u001a\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u00010\u00022\u0006\u0010#\u001a\u00020\fH\u0014R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\n8B@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\fX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00020\u001dX¤\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006E"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$ProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DelegatingConsumer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "decodeCancellationEnabled", "", "maxBitmapSize", "", "(Lcom/facebook/imagepipeline/producers/DecodeProducer;Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V", AbstractID3v1Tag.TAG, "", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "isFinished", "jobScheduler", "Lcom/facebook/imagepipeline/producers/JobScheduler;", "lastScheduledScanNumber", "getLastScheduledScanNumber", "()I", "setLastScheduledScanNumber", "(I)V", "producerListener", "Lcom/facebook/imagepipeline/producers/ProducerListener2;", "qualityInfo", "Lcom/facebook/imagepipeline/image/QualityInfo;", "getQualityInfo", "()Lcom/facebook/imagepipeline/image/QualityInfo;", "doDecode", "", "encodedImage", NotificationCompat.CATEGORY_STATUS, "getExtraMap", "", "image", "queueTime", "", "quality", "isFinal", "imageFormatName", "encodedImageSize", "requestImageSize", "sampleSize", "getIntermediateImageEndOffset", "handleCancellation", "handleError", bm.aM, "", "handleResult", "decodedImage", "internalDecode", SessionDescription.ATTR_LENGTH, "maybeFinish", "shouldFinish", "maybeIncreaseSampleSize", "onCancellationImpl", "onFailureImpl", "onNewResultImpl", "newResult", "onProgressUpdateImpl", "progress", "", "setImageExtras", "updateDecodeJob", "ref", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>> {
        private final String TAG;
        private final ImageDecodeOptions imageDecodeOptions;
        private boolean isFinished;
        private final JobScheduler jobScheduler;
        private int lastScheduledScanNumber;
        private final ProducerContext producerContext;
        private final ProducerListener2 producerListener;
        final /* synthetic */ DecodeProducer this$0;

        protected abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        protected abstract QualityInfo getQualityInfo();

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ProgressiveDecoder(final DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, final boolean z, final int i) {
            super(consumer);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            this.this$0 = decodeProducer;
            this.producerContext = producerContext;
            this.TAG = "ProgressiveDecoder";
            this.producerListener = producerContext.getProducerListener();
            ImageDecodeOptions imageDecodeOptions = producerContext.getImageRequest().getImageDecodeOptions();
            Intrinsics.checkNotNullExpressionValue(imageDecodeOptions, "producerContext.imageRequest.imageDecodeOptions");
            this.imageDecodeOptions = imageDecodeOptions;
            this.jobScheduler = new JobScheduler(decodeProducer.getExecutor(), new JobScheduler.JobRunnable() { // from class: com.facebook.imagepipeline.producers.DecodeProducer$ProgressiveDecoder$$ExternalSyntheticLambda0
                @Override // com.facebook.imagepipeline.producers.JobScheduler.JobRunnable
                public final void run(EncodedImage encodedImage, int i2) throws Throwable {
                    DecodeProducer.ProgressiveDecoder._init_$lambda$2(this.f$0, decodeProducer, i, encodedImage, i2);
                }
            }, imageDecodeOptions.minDecodeIntervalMs);
            producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.1
                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onIsIntermediateResultExpectedChanged() {
                    if (ProgressiveDecoder.this.producerContext.isIntermediateResultExpected()) {
                        ProgressiveDecoder.this.jobScheduler.scheduleJob();
                    }
                }

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    if (z) {
                        ProgressiveDecoder.this.handleCancellation();
                    }
                }
            });
        }

        protected final int getLastScheduledScanNumber() {
            return this.lastScheduledScanNumber;
        }

        protected final void setLastScheduledScanNumber(int i) {
            this.lastScheduledScanNumber = i;
        }

        private final void maybeIncreaseSampleSize(EncodedImage encodedImage) {
            if (encodedImage.getImageFormat() != DefaultImageFormats.JPEG) {
                return;
            }
            encodedImage.setSampleSize(DownsampleUtil.determineSampleSizeJPEG(encodedImage, BitmapUtil.getPixelSizeForBitmapConfig(this.imageDecodeOptions.bitmapConfig), DecodeProducer.MAX_BITMAP_SIZE));
        }

        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(EncodedImage newResult, int status) {
            FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
            if (!FrescoSystrace.isTracing()) {
                boolean zIsLast = BaseConsumer.isLast(status);
                if (zIsLast) {
                    if (newResult == null) {
                        boolean zAreEqual = Intrinsics.areEqual(this.producerContext.getExtra("cached_value_found"), (Object) true);
                        if (!this.producerContext.getImagePipelineConfig().getExperiments().getCancelDecodeOnCacheMiss() || this.producerContext.getLowestPermittedRequestLevel() == ImageRequest.RequestLevel.FULL_FETCH || zAreEqual) {
                            handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                            return;
                        }
                    } else if (!newResult.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        return;
                    }
                }
                if (updateDecodeJob(newResult, status)) {
                    boolean zStatusHasFlag = BaseConsumer.statusHasFlag(status, 4);
                    if (zIsLast || zStatusHasFlag || this.producerContext.isIntermediateResultExpected()) {
                        this.jobScheduler.scheduleJob();
                        return;
                    }
                    return;
                }
                return;
            }
            FrescoSystrace.beginSection("DecodeProducer#onNewResultImpl");
            try {
                boolean zIsLast2 = BaseConsumer.isLast(status);
                if (zIsLast2) {
                    if (newResult == null) {
                        boolean zAreEqual2 = Intrinsics.areEqual(this.producerContext.getExtra("cached_value_found"), (Object) true);
                        if (!this.producerContext.getImagePipelineConfig().getExperiments().getCancelDecodeOnCacheMiss() || this.producerContext.getLowestPermittedRequestLevel() == ImageRequest.RequestLevel.FULL_FETCH || zAreEqual2) {
                            handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                            return;
                        }
                    } else if (!newResult.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        return;
                    }
                }
                if (updateDecodeJob(newResult, status)) {
                    boolean zStatusHasFlag2 = BaseConsumer.statusHasFlag(status, 4);
                    if (zIsLast2 || zStatusHasFlag2 || this.producerContext.isIntermediateResultExpected()) {
                        this.jobScheduler.scheduleJob();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            } finally {
                FrescoSystrace.endSection();
            }
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        protected void onProgressUpdateImpl(float progress) {
            super.onProgressUpdateImpl(progress * 0.99f);
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onFailureImpl(Throwable t) {
            Intrinsics.checkNotNullParameter(t, "t");
            handleError(t);
        }

        @Override // com.facebook.imagepipeline.producers.DelegatingConsumer, com.facebook.imagepipeline.producers.BaseConsumer
        public void onCancellationImpl() {
            handleCancellation();
        }

        protected boolean updateDecodeJob(EncodedImage ref, int status) {
            return this.jobScheduler.updateJob(ref, status);
        }

        private final void doDecode(EncodedImage encodedImage, int status, int lastScheduledScanNumber) throws Throwable {
            int size;
            QualityInfo quality;
            String str;
            CloseableImage closeableImage;
            String name;
            int i = status;
            if (encodedImage.getImageFormat() != DefaultImageFormats.JPEG && BaseConsumer.isNotLast(status)) {
                return;
            }
            if (this.isFinished || !EncodedImage.isValid(encodedImage)) {
                return;
            }
            if (Intrinsics.areEqual(encodedImage.getImageFormat(), DefaultImageFormats.GIF) && DecodeProducer.INSTANCE.isTooBig(encodedImage, this.imageDecodeOptions)) {
                IllegalStateException illegalStateException = new IllegalStateException("Image is too big to attempt decoding: w = " + encodedImage.getWidth() + ", h = " + encodedImage.getHeight() + ", pixel config = " + this.imageDecodeOptions.bitmapConfig + ", max bitmap size = 104857600");
                this.producerListener.onProducerFinishWithFailure(this.producerContext, DecodeProducer.PRODUCER_NAME, illegalStateException, null);
                handleError(illegalStateException);
                return;
            }
            ImageFormat imageFormat = encodedImage.getImageFormat();
            String str2 = (imageFormat == null || (name = imageFormat.getName()) == null) ? EnvironmentCompat.MEDIA_UNKNOWN : name;
            String str3 = encodedImage.getWidth() + "x" + encodedImage.getHeight();
            String strValueOf = String.valueOf(encodedImage.getSampleSize());
            boolean zIsLast = BaseConsumer.isLast(status);
            boolean z = zIsLast && !BaseConsumer.statusHasFlag(i, 8);
            boolean zStatusHasFlag = BaseConsumer.statusHasFlag(i, 4);
            ResizeOptions resizeOptions = this.producerContext.getImageRequest().getResizeOptions();
            String str4 = resizeOptions != null ? resizeOptions.width + "x" + resizeOptions.height : EnvironmentCompat.MEDIA_UNKNOWN;
            try {
                long queuedTime = this.jobScheduler.getQueuedTime();
                String string = this.producerContext.getImageRequest().getSourceUri().toString();
                Intrinsics.checkNotNullExpressionValue(string, "producerContext.imageRequest.sourceUri.toString()");
                if (z || zStatusHasFlag) {
                    size = encodedImage.getSize();
                } else {
                    size = getIntermediateImageEndOffset(encodedImage);
                }
                if (z || zStatusHasFlag) {
                    quality = ImmutableQualityInfo.FULL_QUALITY;
                } else {
                    quality = getQualityInfo();
                }
                try {
                    this.producerListener.onProducerStart(this.producerContext, DecodeProducer.PRODUCER_NAME);
                    try {
                        try {
                            Intrinsics.checkNotNullExpressionValue(quality, "quality");
                            CloseableImage closeableImageInternalDecode = internalDecode(encodedImage, size, quality);
                            try {
                                if (encodedImage.getSampleSize() != 1) {
                                    i |= 16;
                                }
                                this.producerListener.onProducerFinishWithSuccess(this.producerContext, DecodeProducer.PRODUCER_NAME, getExtraMap(closeableImageInternalDecode, queuedTime, quality, zIsLast, str2, str3, str4, strValueOf));
                                setImageExtras(encodedImage, closeableImageInternalDecode, lastScheduledScanNumber);
                                handleResult(closeableImageInternalDecode, i);
                                EncodedImage.closeSafely(encodedImage);
                            } catch (Exception e) {
                                e = e;
                                str = DecodeProducer.PRODUCER_NAME;
                                closeableImage = closeableImageInternalDecode;
                                Intrinsics.checkNotNullExpressionValue(quality, "quality");
                                try {
                                    this.producerListener.onProducerFinishWithFailure(this.producerContext, str, e, getExtraMap(closeableImage, queuedTime, quality, zIsLast, str2, str3, str4, strValueOf));
                                    handleError(e);
                                    EncodedImage.closeSafely(encodedImage);
                                } catch (Throwable th) {
                                    th = th;
                                    EncodedImage.closeSafely(encodedImage);
                                    throw th;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            str = DecodeProducer.PRODUCER_NAME;
                            closeableImage = null;
                            Intrinsics.checkNotNullExpressionValue(quality, "quality");
                            this.producerListener.onProducerFinishWithFailure(this.producerContext, str, e, getExtraMap(closeableImage, queuedTime, quality, zIsLast, str2, str3, str4, strValueOf));
                            handleError(e);
                            EncodedImage.closeSafely(encodedImage);
                        }
                    } catch (DecodeException e3) {
                        EncodedImage encodedImage2 = e3.getEncodedImage();
                        String str5 = this.TAG;
                        String message = e3.getMessage();
                        str = DecodeProducer.PRODUCER_NAME;
                        try {
                            FLog.w(str5, "%s, {uri: %s, firstEncodedBytes: %s, length: %d}", message, string, encodedImage2.getFirstBytesAsHexString(10), Integer.valueOf(encodedImage2.getSize()));
                            throw e3;
                        } catch (Exception e4) {
                            e = e4;
                            closeableImage = null;
                            Intrinsics.checkNotNullExpressionValue(quality, "quality");
                            this.producerListener.onProducerFinishWithFailure(this.producerContext, str, e, getExtraMap(closeableImage, queuedTime, quality, zIsLast, str2, str3, str4, strValueOf));
                            handleError(e);
                            EncodedImage.closeSafely(encodedImage);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:7:0x0021  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private final com.facebook.imagepipeline.image.CloseableImage internalDecode(com.facebook.imagepipeline.image.EncodedImage r4, int r5, com.facebook.imagepipeline.image.QualityInfo r6) {
            /*
                r3 = this;
                com.facebook.imagepipeline.producers.DecodeProducer r0 = r3.this$0
                java.lang.Runnable r0 = r0.getReclaimMemoryRunnable()
                if (r0 == 0) goto L21
                com.facebook.imagepipeline.producers.DecodeProducer r0 = r3.this$0
                com.facebook.common.internal.Supplier r0 = r0.getRecoverFromDecoderOOM()
                java.lang.Object r0 = r0.get()
                java.lang.String r1 = "recoverFromDecoderOOM.get()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r0 = r0.booleanValue()
                if (r0 == 0) goto L21
                r0 = 1
                goto L22
            L21:
                r0 = 0
            L22:
                com.facebook.imagepipeline.producers.DecodeProducer r1 = r3.this$0     // Catch: java.lang.OutOfMemoryError -> L2f
                com.facebook.imagepipeline.decoder.ImageDecoder r1 = r1.getImageDecoder()     // Catch: java.lang.OutOfMemoryError -> L2f
                com.facebook.imagepipeline.common.ImageDecodeOptions r2 = r3.imageDecodeOptions     // Catch: java.lang.OutOfMemoryError -> L2f
                com.facebook.imagepipeline.image.CloseableImage r4 = r1.decode(r4, r5, r6, r2)     // Catch: java.lang.OutOfMemoryError -> L2f
                goto L4c
            L2f:
                r1 = move-exception
                if (r0 == 0) goto L4d
                com.facebook.imagepipeline.producers.DecodeProducer r0 = r3.this$0
                java.lang.Runnable r0 = r0.getReclaimMemoryRunnable()
                if (r0 == 0) goto L3d
                r0.run()
            L3d:
                java.lang.System.gc()
                com.facebook.imagepipeline.producers.DecodeProducer r0 = r3.this$0
                com.facebook.imagepipeline.decoder.ImageDecoder r0 = r0.getImageDecoder()
                com.facebook.imagepipeline.common.ImageDecodeOptions r1 = r3.imageDecodeOptions
                com.facebook.imagepipeline.image.CloseableImage r4 = r0.decode(r4, r5, r6, r1)
            L4c:
                return r4
            L4d:
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.internalDecode(com.facebook.imagepipeline.image.EncodedImage, int, com.facebook.imagepipeline.image.QualityInfo):com.facebook.imagepipeline.image.CloseableImage");
        }

        private final void setImageExtras(EncodedImage encodedImage, CloseableImage image, int lastScheduledScanNumber) {
            this.producerContext.putExtra("encoded_width", Integer.valueOf(encodedImage.getWidth()));
            this.producerContext.putExtra("encoded_height", Integer.valueOf(encodedImage.getHeight()));
            this.producerContext.putExtra("encoded_size", Integer.valueOf(encodedImage.getSize()));
            this.producerContext.putExtra("image_color_space", encodedImage.getColorSpace());
            if (image instanceof CloseableBitmap) {
                this.producerContext.putExtra("bitmap_config", String.valueOf(((CloseableBitmap) image).getUnderlyingBitmap().getConfig()));
            }
            if (image != null) {
                image.putExtras(this.producerContext.getExtras());
            }
            this.producerContext.putExtra("last_scan_num", Integer.valueOf(lastScheduledScanNumber));
        }

        private final Map<String, String> getExtraMap(CloseableImage image, long queueTime, QualityInfo quality, boolean isFinal, String imageFormatName, String encodedImageSize, String requestImageSize, String sampleSize) {
            Map<String, Object> extras;
            Object obj;
            String string = null;
            if (!this.producerListener.requiresExtraMap(this.producerContext, DecodeProducer.PRODUCER_NAME)) {
                return null;
            }
            String strValueOf = String.valueOf(queueTime);
            String strValueOf2 = String.valueOf(quality.isOfGoodEnoughQuality());
            String strValueOf3 = String.valueOf(isFinal);
            if (image != null && (extras = image.getExtras()) != null && (obj = extras.get("non_fatal_decode_error")) != null) {
                string = obj.toString();
            }
            String str = string;
            if (image instanceof CloseableStaticBitmap) {
                Bitmap underlyingBitmap = ((CloseableStaticBitmap) image).getUnderlyingBitmap();
                Intrinsics.checkNotNullExpressionValue(underlyingBitmap, "image.underlyingBitmap");
                String str2 = underlyingBitmap.getWidth() + "x" + underlyingBitmap.getHeight();
                HashMap map = new HashMap(8);
                map.put("bitmapSize", str2);
                map.put("queueTime", strValueOf);
                map.put("hasGoodQuality", strValueOf2);
                map.put("isFinal", strValueOf3);
                map.put("encodedImageSize", encodedImageSize);
                map.put("imageFormat", imageFormatName);
                map.put("requestedImageSize", requestImageSize);
                map.put("sampleSize", sampleSize);
                map.put("byteCount", new StringBuilder().append(underlyingBitmap.getByteCount()).toString());
                if (str != null) {
                    map.put("non_fatal_decode_error", str);
                }
                return ImmutableMap.copyOf((Map) map);
            }
            HashMap map2 = new HashMap(7);
            map2.put("queueTime", strValueOf);
            map2.put("hasGoodQuality", strValueOf2);
            map2.put("isFinal", strValueOf3);
            map2.put("encodedImageSize", encodedImageSize);
            map2.put("imageFormat", imageFormatName);
            map2.put("requestedImageSize", requestImageSize);
            map2.put("sampleSize", sampleSize);
            if (str != null) {
                map2.put("non_fatal_decode_error", str);
            }
            return ImmutableMap.copyOf((Map) map2);
        }

        private final void maybeFinish(boolean shouldFinish) {
            synchronized (this) {
                if (shouldFinish) {
                    if (!this.isFinished) {
                        getConsumer().onProgressUpdate(1.0f);
                        this.isFinished = true;
                        Unit unit = Unit.INSTANCE;
                        this.jobScheduler.clearJob();
                    }
                }
            }
        }

        private final void handleResult(CloseableImage decodedImage, int status) {
            CloseableReference<CloseableImage> closeableReferenceCreate = this.this$0.getCloseableReferenceFactory().create(decodedImage);
            try {
                maybeFinish(BaseConsumer.isLast(status));
                getConsumer().onNewResult(closeableReferenceCreate, status);
            } finally {
                CloseableReference.closeSafely(closeableReferenceCreate);
            }
        }

        private final void handleError(Throwable t) {
            maybeFinish(true);
            getConsumer().onFailure(t);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void handleCancellation() {
            maybeFinish(true);
            getConsumer().onCancellation();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void _init_$lambda$2(ProgressiveDecoder this$0, DecodeProducer this$1, int i, EncodedImage encodedImage, int i2) throws Throwable {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            if (encodedImage != null) {
                ImageRequest imageRequest = this$0.producerContext.getImageRequest();
                this$0.producerContext.putExtra("image_format", encodedImage.getImageFormat().getName());
                Uri sourceUri = imageRequest.getSourceUri();
                encodedImage.setSource(sourceUri != null ? sourceUri.toString() : null);
                boolean zStatusHasFlag = BaseConsumer.statusHasFlag(i2, 16);
                if ((this$1.getDownsampleMode() == DownsampleMode.ALWAYS || (this$1.getDownsampleMode() == DownsampleMode.AUTO && !zStatusHasFlag)) && (this$1.getDownsampleEnabledForNetwork() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()))) {
                    RotationOptions rotationOptions = imageRequest.getRotationOptions();
                    Intrinsics.checkNotNullExpressionValue(rotationOptions, "request.rotationOptions");
                    encodedImage.setSampleSize(DownsampleUtil.determineSampleSize(rotationOptions, imageRequest.getResizeOptions(), encodedImage, i));
                }
                if (this$0.producerContext.getImagePipelineConfig().getExperiments().getDownsampleIfLargeBitmap()) {
                    this$0.maybeIncreaseSampleSize(encodedImage);
                }
                this$0.doDecode(encodedImage, i2, this$0.lastScheduledScanNumber);
            }
        }
    }

    /* compiled from: DecodeProducer.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B1\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u001a\u0010\u0015\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\fH\u0014R\u0014\u0010\u000e\u001a\u00020\u000f8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$LocalImagesProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer$ProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "decodeCancellationEnabled", "", "maxBitmapSize", "", "(Lcom/facebook/imagepipeline/producers/DecodeProducer;Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V", "qualityInfo", "Lcom/facebook/imagepipeline/image/QualityInfo;", "getQualityInfo", "()Lcom/facebook/imagepipeline/image/QualityInfo;", "getIntermediateImageEndOffset", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "updateDecodeJob", NotificationCompat.CATEGORY_STATUS, "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        final /* synthetic */ DecodeProducer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LocalImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, boolean z, int i) {
            super(decodeProducer, consumer, producerContext, z, i);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            this.this$0 = decodeProducer;
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected synchronized boolean updateDecodeJob(EncodedImage encodedImage, int status) {
            return BaseConsumer.isNotLast(status) ? false : super.updateDecodeJob(encodedImage, status);
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
            return encodedImage.getSize();
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected QualityInfo getQualityInfo() {
            QualityInfo qualityInfoOf = ImmutableQualityInfo.of(0, false, false);
            Intrinsics.checkNotNullExpressionValue(qualityInfoOf, "of(0, false, false)");
            return qualityInfoOf;
        }
    }

    /* compiled from: DecodeProducer.kt */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00060\u0001R\u00020\u0002BA\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u001a\u0010\u001d\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u0010H\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$NetworkImagesProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer$ProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "progressiveJpegParser", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegParser;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "decodeCancellationEnabled", "", "maxBitmapSize", "", "(Lcom/facebook/imagepipeline/producers/DecodeProducer;Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegParser;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZI)V", "getProgressiveJpegConfig", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "getProgressiveJpegParser", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegParser;", "qualityInfo", "Lcom/facebook/imagepipeline/image/QualityInfo;", "getQualityInfo", "()Lcom/facebook/imagepipeline/image/QualityInfo;", "getIntermediateImageEndOffset", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "updateDecodeJob", NotificationCompat.CATEGORY_STATUS, "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        private final ProgressiveJpegConfig progressiveJpegConfig;
        private final ProgressiveJpegParser progressiveJpegParser;
        final /* synthetic */ DecodeProducer this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NetworkImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser, ProgressiveJpegConfig progressiveJpegConfig, boolean z, int i) {
            super(decodeProducer, consumer, producerContext, z, i);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            Intrinsics.checkNotNullParameter(progressiveJpegParser, "progressiveJpegParser");
            Intrinsics.checkNotNullParameter(progressiveJpegConfig, "progressiveJpegConfig");
            this.this$0 = decodeProducer;
            this.progressiveJpegParser = progressiveJpegParser;
            this.progressiveJpegConfig = progressiveJpegConfig;
            setLastScheduledScanNumber(0);
        }

        public final ProgressiveJpegParser getProgressiveJpegParser() {
            return this.progressiveJpegParser;
        }

        public final ProgressiveJpegConfig getProgressiveJpegConfig() {
            return this.progressiveJpegConfig;
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected synchronized boolean updateDecodeJob(EncodedImage encodedImage, int status) {
            if (encodedImage == null) {
                return false;
            }
            boolean zUpdateDecodeJob = super.updateDecodeJob(encodedImage, status);
            if ((BaseConsumer.isNotLast(status) || BaseConsumer.statusHasFlag(status, 8)) && !BaseConsumer.statusHasFlag(status, 4) && EncodedImage.isValid(encodedImage) && encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                if (!this.progressiveJpegParser.parseMoreData(encodedImage)) {
                    return false;
                }
                int bestScanNumber = this.progressiveJpegParser.getBestScanNumber();
                if (bestScanNumber <= getLastScheduledScanNumber()) {
                    return false;
                }
                if (bestScanNumber < this.progressiveJpegConfig.getNextScanNumberToDecode(getLastScheduledScanNumber()) && !this.progressiveJpegParser.isEndMarkerRead()) {
                    return false;
                }
                setLastScheduledScanNumber(bestScanNumber);
            }
            return zUpdateDecodeJob;
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
            return this.progressiveJpegParser.getBestScanEndOffset();
        }

        @Override // com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder
        protected QualityInfo getQualityInfo() {
            QualityInfo qualityInfo = this.progressiveJpegConfig.getQualityInfo(this.progressiveJpegParser.getBestScanNumber());
            Intrinsics.checkNotNullExpressionValue(qualityInfo, "progressiveJpegConfig.ge…pegParser.bestScanNumber)");
            return qualityInfo;
        }
    }

    /* compiled from: DecodeProducer.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$Companion;", "", "()V", "DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES", "", "ENCODED_IMAGE_SIZE", "", "EXTRA_BITMAP_BYTES", "EXTRA_BITMAP_SIZE", "EXTRA_HAS_GOOD_QUALITY", "EXTRA_IMAGE_FORMAT_NAME", "EXTRA_IS_FINAL", "MAX_BITMAP_SIZE", "NON_FATAL_DECODE_ERROR", "PRODUCER_NAME", "REQUESTED_IMAGE_SIZE", "SAMPLE_SIZE", "isTooBig", "", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isTooBig(EncodedImage encodedImage, ImageDecodeOptions imageDecodeOptions) {
            return (((long) encodedImage.getWidth()) * ((long) encodedImage.getHeight())) * ((long) BitmapUtil.getPixelSizeForBitmapConfig(imageDecodeOptions.bitmapConfig)) > StatFsHelper.DEFAULT_DISK_RED_LEVEL_IN_BYTES;
        }
    }
}
