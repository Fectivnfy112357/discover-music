package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Build;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapPrepareProducer;
import com.facebook.imagepipeline.producers.BitmapProbeProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.CustomProducerSequenceFactory;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DelayProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.DiskCacheWriteProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedProbeProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalThumbnailBitmapSdk29Producer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.QualifiedResourceFetchProducer;
import com.facebook.imagepipeline.producers.RemoveImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProducerSequenceFactory.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u0083\u00012\u00020\u0001:\u0002\u0083\u0001B\u0089\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\t\u0012\u0006\u0010\u0015\u001a\u00020\t\u0012\u0006\u0010\u0016\u001a\u00020\t\u0012\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018¢\u0006\u0002\u0010\u001aJ\u001c\u0010j\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\u0006\u0010k\u001a\u00020lH\u0002J(\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\u0012\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001cH\u0002J\u0016\u0010o\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030\u001c2\u0006\u0010k\u001a\u00020lJ$\u0010p\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030\u001c2\u0012\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001cH\u0002J\u001a\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\u0006\u0010k\u001a\u00020lJ(\u0010r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\u0012\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001cH\u0002J\u0016\u0010s\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030\u001c2\u0006\u0010k\u001a\u00020lJ\u001a\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0*0\u001c2\u0006\u0010k\u001a\u00020lJ(\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\u0012\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001cH\u0002J(\u0010v\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\u0012\u0010n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001cH\u0002J \u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cJ\"\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J;\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0012\u0010y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0{0zH\u0002¢\u0006\u0002\u0010|J\u0018\u0010}\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007J\u001c\u0010~\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u001c\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J)\u0010\u0080\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0012\u0010y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0{0zH\u0002¢\u0006\u0003\u0010\u0081\u0001J6\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0012\u0010y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0{0zH\u0002¢\u0006\u0002\u0010|R\u000e\u0010\u0016\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001fR#\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b$\u0010!\u001a\u0004\b#\u0010\u001fR#\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b'\u0010!\u001a\u0004\b&\u0010\u001fRH\u0010(\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c0)8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101RD\u00102\u001a\"\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001030\u001c0)8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b4\u0010-\u001a\u0004\b5\u0010/\"\u0004\b6\u00101R!\u00107\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b9\u0010!\u001a\u0004\b8\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b<\u0010!\u001a\u0004\b;\u0010\u001fR\u000e\u0010\u0011\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b?\u0010!\u001a\u0004\b>\u0010\u001fR'\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bC\u0010!\u001a\u0004\bB\u0010\u001fR'\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bF\u0010!\u001a\u0004\bE\u0010\u001fR-\u0010G\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0*0\u001c8FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\bJ\u0010!\u0012\u0004\bH\u0010-\u001a\u0004\bI\u0010\u001fR#\u0010K\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bM\u0010!\u001a\u0004\bL\u0010\u001fR'\u0010N\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bP\u0010!\u001a\u0004\bO\u0010\u001fR'\u0010Q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bS\u0010!\u001a\u0004\bR\u0010\u001fR'\u0010T\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8GX\u0086\u0084\u0002¢\u0006\f\n\u0004\bV\u0010!\u001a\u0004\bU\u0010\u001fR'\u0010W\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bY\u0010!\u001a\u0004\bX\u0010\u001fR'\u0010Z\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\\\u0010!\u001a\u0004\b[\u0010\u001fR'\u0010]\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b_\u0010!\u001a\u0004\b^\u0010\u001fR#\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001030\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bb\u0010!\u001a\u0004\ba\u0010\u001fR\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000RH\u0010c\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c0)8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bd\u0010-\u001a\u0004\be\u0010/\"\u0004\bf\u00101R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*0\u001c8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bi\u0010!\u001a\u0004\bh\u0010\u001fR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0084\u0001"}, d2 = {"Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "", "contentResolver", "Landroid/content/ContentResolver;", "producerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", "networkFetcher", "Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "resizeAndRotateEnabledForNetwork", "", "webpSupportEnabled", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "downsampleMode", "Lcom/facebook/imagepipeline/core/DownsampleMode;", "useBitmapPrepareToDraw", "partialImageCachingEnabled", "diskCacheEnabled", "imageTranscoderFactory", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "isEncodedMemoryCacheProbingEnabled", "isDiskCacheProbingEnabled", "allowDelay", "customProducerSequenceFactories", "", "Lcom/facebook/imagepipeline/producers/CustomProducerSequenceFactory;", "(Landroid/content/ContentResolver;Lcom/facebook/imagepipeline/core/ProducerFactory;Lcom/facebook/imagepipeline/producers/NetworkFetcher;ZZLcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;Lcom/facebook/imagepipeline/core/DownsampleMode;ZZZLcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;ZZZLjava/util/Set;)V", "backgroundLocalContentUriFetchToEncodeMemorySequence", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "getBackgroundLocalContentUriFetchToEncodeMemorySequence", "()Lcom/facebook/imagepipeline/producers/Producer;", "backgroundLocalContentUriFetchToEncodeMemorySequence$delegate", "Lkotlin/Lazy;", "backgroundLocalFileFetchToEncodeMemorySequence", "getBackgroundLocalFileFetchToEncodeMemorySequence", "backgroundLocalFileFetchToEncodeMemorySequence$delegate", "backgroundNetworkFetchToEncodedMemorySequence", "getBackgroundNetworkFetchToEncodedMemorySequence", "backgroundNetworkFetchToEncodedMemorySequence$delegate", "bitmapPrepareSequences", "", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "getBitmapPrepareSequences$annotations", "()V", "getBitmapPrepareSequences", "()Ljava/util/Map;", "setBitmapPrepareSequences", "(Ljava/util/Map;)V", "closeableImagePrefetchSequences", "Ljava/lang/Void;", "getCloseableImagePrefetchSequences$annotations", "getCloseableImagePrefetchSequences", "setCloseableImagePrefetchSequences", "commonNetworkFetchToEncodedMemorySequence", "getCommonNetworkFetchToEncodedMemorySequence", "commonNetworkFetchToEncodedMemorySequence$delegate", "dataFetchSequence", "getDataFetchSequence", "dataFetchSequence$delegate", "localAssetFetchSequence", "getLocalAssetFetchSequence", "localAssetFetchSequence$delegate", "localContentUriFetchEncodedImageProducerSequence", "Lcom/facebook/common/memory/PooledByteBuffer;", "getLocalContentUriFetchEncodedImageProducerSequence", "localContentUriFetchEncodedImageProducerSequence$delegate", "localContentUriFetchSequence", "getLocalContentUriFetchSequence", "localContentUriFetchSequence$delegate", "localFileFetchEncodedImageProducerSequence", "getLocalFileFetchEncodedImageProducerSequence$annotations", "getLocalFileFetchEncodedImageProducerSequence", "localFileFetchEncodedImageProducerSequence$delegate", "localFileFetchToEncodedMemoryPrefetchSequence", "getLocalFileFetchToEncodedMemoryPrefetchSequence", "localFileFetchToEncodedMemoryPrefetchSequence$delegate", "localImageFileFetchSequence", "getLocalImageFileFetchSequence", "localImageFileFetchSequence$delegate", "localResourceFetchSequence", "getLocalResourceFetchSequence", "localResourceFetchSequence$delegate", "localThumbnailBitmapSdk29FetchSequence", "getLocalThumbnailBitmapSdk29FetchSequence", "localThumbnailBitmapSdk29FetchSequence$delegate", "localVideoFileFetchSequence", "getLocalVideoFileFetchSequence", "localVideoFileFetchSequence$delegate", "networkFetchEncodedImageProducerSequence", "getNetworkFetchEncodedImageProducerSequence", "networkFetchEncodedImageProducerSequence$delegate", "networkFetchSequence", "getNetworkFetchSequence", "networkFetchSequence$delegate", "networkFetchToEncodedMemoryPrefetchSequence", "getNetworkFetchToEncodedMemoryPrefetchSequence", "networkFetchToEncodedMemoryPrefetchSequence$delegate", "postprocessorSequences", "getPostprocessorSequences$annotations", "getPostprocessorSequences", "setPostprocessorSequences", "qualifiedResourceFetchSequence", "getQualifiedResourceFetchSequence", "qualifiedResourceFetchSequence$delegate", "getBasicDecodedImageSequence", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getBitmapPrepareSequence", "inputProducer", "getDecodedImagePrefetchProducerSequence", "getDecodedImagePrefetchSequence", "getDecodedImageProducerSequence", "getDelaySequence", "getEncodedImagePrefetchProducerSequence", "getEncodedImageProducerSequence", "getPostprocessorSequence", "newBitmapCacheGetToBitmapCacheSequence", "newBitmapCacheGetToDecodeSequence", "newBitmapCacheGetToLocalTransformSequence", "thumbnailProducers", "", "Lcom/facebook/imagepipeline/producers/ThumbnailProducer;", "(Lcom/facebook/imagepipeline/producers/Producer;[Lcom/facebook/imagepipeline/producers/ThumbnailProducer;)Lcom/facebook/imagepipeline/producers/Producer;", "newCommonNetworkFetchToEncodedMemorySequence", "newDiskCacheSequence", "newEncodedCacheMultiplexToTranscodeSequence", "newLocalThumbnailProducer", "([Lcom/facebook/imagepipeline/producers/ThumbnailProducer;)Lcom/facebook/imagepipeline/producers/Producer;", "newLocalTransformationsSequence", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ProducerSequenceFactory {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean allowDelay;

    /* renamed from: backgroundLocalContentUriFetchToEncodeMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy backgroundLocalContentUriFetchToEncodeMemorySequence;

    /* renamed from: backgroundLocalFileFetchToEncodeMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy backgroundLocalFileFetchToEncodeMemorySequence;

    /* renamed from: backgroundNetworkFetchToEncodedMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy backgroundNetworkFetchToEncodedMemorySequence;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> bitmapPrepareSequences;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> closeableImagePrefetchSequences;

    /* renamed from: commonNetworkFetchToEncodedMemorySequence$delegate, reason: from kotlin metadata */
    private final Lazy commonNetworkFetchToEncodedMemorySequence;
    private final ContentResolver contentResolver;
    private final Set<CustomProducerSequenceFactory> customProducerSequenceFactories;

    /* renamed from: dataFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy dataFetchSequence;
    private final boolean diskCacheEnabled;
    private final DownsampleMode downsampleMode;
    private final ImageTranscoderFactory imageTranscoderFactory;
    private final boolean isDiskCacheProbingEnabled;
    private final boolean isEncodedMemoryCacheProbingEnabled;

    /* renamed from: localAssetFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localAssetFetchSequence;

    /* renamed from: localContentUriFetchEncodedImageProducerSequence$delegate, reason: from kotlin metadata */
    private final Lazy localContentUriFetchEncodedImageProducerSequence;

    /* renamed from: localContentUriFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localContentUriFetchSequence;

    /* renamed from: localFileFetchEncodedImageProducerSequence$delegate, reason: from kotlin metadata */
    private final Lazy localFileFetchEncodedImageProducerSequence;

    /* renamed from: localFileFetchToEncodedMemoryPrefetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localFileFetchToEncodedMemoryPrefetchSequence;

    /* renamed from: localImageFileFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localImageFileFetchSequence;

    /* renamed from: localResourceFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localResourceFetchSequence;

    /* renamed from: localThumbnailBitmapSdk29FetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localThumbnailBitmapSdk29FetchSequence;

    /* renamed from: localVideoFileFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy localVideoFileFetchSequence;

    /* renamed from: networkFetchEncodedImageProducerSequence$delegate, reason: from kotlin metadata */
    private final Lazy networkFetchEncodedImageProducerSequence;

    /* renamed from: networkFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy networkFetchSequence;

    /* renamed from: networkFetchToEncodedMemoryPrefetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy networkFetchToEncodedMemoryPrefetchSequence;
    private final NetworkFetcher<?> networkFetcher;
    private final boolean partialImageCachingEnabled;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> postprocessorSequences;
    private final ProducerFactory producerFactory;

    /* renamed from: qualifiedResourceFetchSequence$delegate, reason: from kotlin metadata */
    private final Lazy qualifiedResourceFetchSequence;
    private final boolean resizeAndRotateEnabledForNetwork;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;
    private final boolean useBitmapPrepareToDraw;
    private final boolean webpSupportEnabled;

    public static /* synthetic */ void getBitmapPrepareSequences$annotations() {
    }

    public static /* synthetic */ void getCloseableImagePrefetchSequences$annotations() {
    }

    public static /* synthetic */ void getLocalFileFetchEncodedImageProducerSequence$annotations() {
    }

    public static /* synthetic */ void getPostprocessorSequences$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ProducerSequenceFactory(ContentResolver contentResolver, ProducerFactory producerFactory, NetworkFetcher<?> networkFetcher, boolean z, boolean z2, ThreadHandoffProducerQueue threadHandoffProducerQueue, DownsampleMode downsampleMode, boolean z3, boolean z4, boolean z5, ImageTranscoderFactory imageTranscoderFactory, boolean z6, boolean z7, boolean z8, Set<? extends CustomProducerSequenceFactory> set) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(producerFactory, "producerFactory");
        Intrinsics.checkNotNullParameter(networkFetcher, "networkFetcher");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(downsampleMode, "downsampleMode");
        Intrinsics.checkNotNullParameter(imageTranscoderFactory, "imageTranscoderFactory");
        this.contentResolver = contentResolver;
        this.producerFactory = producerFactory;
        this.networkFetcher = networkFetcher;
        this.resizeAndRotateEnabledForNetwork = z;
        this.webpSupportEnabled = z2;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue;
        this.downsampleMode = downsampleMode;
        this.useBitmapPrepareToDraw = z3;
        this.partialImageCachingEnabled = z4;
        this.diskCacheEnabled = z5;
        this.imageTranscoderFactory = imageTranscoderFactory;
        this.isEncodedMemoryCacheProbingEnabled = z6;
        this.isDiskCacheProbingEnabled = z7;
        this.allowDelay = z8;
        this.customProducerSequenceFactories = set;
        this.postprocessorSequences = new LinkedHashMap();
        this.closeableImagePrefetchSequences = new LinkedHashMap();
        this.bitmapPrepareSequences = new LinkedHashMap();
        this.networkFetchEncodedImageProducerSequence = LazyKt.lazy(new Function0<RemoveImageTransformMetaDataProducer>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$networkFetchEncodedImageProducerSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RemoveImageTransformMetaDataProducer invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchEncodedImageProducerSequence:init");
                try {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localFileFetchEncodedImageProducerSequence = LazyKt.lazy(new Function0<RemoveImageTransformMetaDataProducer>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localFileFetchEncodedImageProducerSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RemoveImageTransformMetaDataProducer invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchEncodedImageProducerSequence:init");
                try {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localContentUriFetchEncodedImageProducerSequence = LazyKt.lazy(new Function0<RemoveImageTransformMetaDataProducer>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localContentUriFetchEncodedImageProducerSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RemoveImageTransformMetaDataProducer invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalContentUriFetchEncodedImageProducerSequence:init");
                try {
                    return new RemoveImageTransformMetaDataProducer(producerSequenceFactory.getBackgroundLocalContentUriFetchToEncodeMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.networkFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$networkFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchSequence:init");
                try {
                    return producerSequenceFactory.newBitmapCacheGetToDecodeSequence(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.backgroundNetworkFetchToEncodedMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$backgroundNetworkFetchToEncodedMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence(), producerSequenceFactory.threadHandoffProducerQueue);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundNetworkFetchToEncodedMemorySequence:init");
                try {
                    return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.getCommonNetworkFetchToEncodedMemorySequence(), producerSequenceFactory.threadHandoffProducerQueue);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.networkFetchToEncodedMemoryPrefetchSequence = LazyKt.lazy(new Function0<SwallowResultProducer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$networkFetchToEncodedMemoryPrefetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SwallowResultProducer<EncodedImage> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getNetworkFetchToEncodedMemoryPrefetchSequence");
                try {
                    return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundNetworkFetchToEncodedMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.commonNetworkFetchToEncodedMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$commonNetworkFetchToEncodedMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(producerSequenceFactory.networkFetcher);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getCommonNetworkFetchToEncodedMemorySequence");
                try {
                    return producerSequenceFactory.newCommonNetworkFetchToEncodedMemorySequence(producerSequenceFactory.networkFetcher);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localFileFetchToEncodedMemoryPrefetchSequence = LazyKt.lazy(new Function0<SwallowResultProducer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localFileFetchToEncodedMemoryPrefetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SwallowResultProducer<EncodedImage> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getLocalFileFetchToEncodedMemoryPrefetchSequence:init");
                try {
                    return producerSequenceFactory.producerFactory.newSwallowResultProducer(producerSequenceFactory.getBackgroundLocalFileFetchToEncodeMemorySequence());
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.backgroundLocalFileFetchToEncodeMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$backgroundLocalFileFetchToEncodeMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    LocalFileFetchProducer localFileFetchProducerNewLocalFileFetchProducer = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(localFileFetchProducerNewLocalFileFetchProducer, "producerFactory.newLocalFileFetchProducer()");
                    return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(localFileFetchProducerNewLocalFileFetchProducer), producerSequenceFactory.threadHandoffProducerQueue);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalFileFetchToEncodeMemorySequence");
                try {
                    LocalFileFetchProducer localFileFetchProducerNewLocalFileFetchProducer2 = producerSequenceFactory.producerFactory.newLocalFileFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(localFileFetchProducerNewLocalFileFetchProducer2, "producerFactory.newLocalFileFetchProducer()");
                    return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(localFileFetchProducerNewLocalFileFetchProducer2), producerSequenceFactory.threadHandoffProducerQueue);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.backgroundLocalContentUriFetchToEncodeMemorySequence = LazyKt.lazy(new Function0<Producer<EncodedImage>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$backgroundLocalContentUriFetchToEncodeMemorySequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<EncodedImage> invoke() {
                FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
                ProducerSequenceFactory producerSequenceFactory = this.this$0;
                if (!FrescoSystrace.isTracing()) {
                    LocalContentUriFetchProducer localContentUriFetchProducerNewLocalContentUriFetchProducer = producerSequenceFactory.producerFactory.newLocalContentUriFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(localContentUriFetchProducerNewLocalContentUriFetchProducer, "producerFactory.newLocalContentUriFetchProducer()");
                    return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(localContentUriFetchProducerNewLocalContentUriFetchProducer), producerSequenceFactory.threadHandoffProducerQueue);
                }
                FrescoSystrace.beginSection("ProducerSequenceFactory#getBackgroundLocalContentUriFetchToEncodeMemorySequence:init");
                try {
                    LocalContentUriFetchProducer localContentUriFetchProducerNewLocalContentUriFetchProducer2 = producerSequenceFactory.producerFactory.newLocalContentUriFetchProducer();
                    Intrinsics.checkNotNullExpressionValue(localContentUriFetchProducerNewLocalContentUriFetchProducer2, "producerFactory.newLocalContentUriFetchProducer()");
                    return producerSequenceFactory.producerFactory.newBackgroundThreadHandoffProducer(producerSequenceFactory.newEncodedCacheMultiplexToTranscodeSequence(localContentUriFetchProducerNewLocalContentUriFetchProducer2), producerSequenceFactory.threadHandoffProducerQueue);
                } finally {
                    FrescoSystrace.endSection();
                }
            }
        });
        this.localImageFileFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localImageFileFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                LocalFileFetchProducer localFileFetchProducerNewLocalFileFetchProducer = this.this$0.producerFactory.newLocalFileFetchProducer();
                Intrinsics.checkNotNullExpressionValue(localFileFetchProducerNewLocalFileFetchProducer, "producerFactory.newLocalFileFetchProducer()");
                return this.this$0.newBitmapCacheGetToLocalTransformSequence(localFileFetchProducerNewLocalFileFetchProducer);
            }
        });
        this.localVideoFileFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localVideoFileFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                LocalVideoThumbnailProducer localVideoThumbnailProducerNewLocalVideoThumbnailProducer = this.this$0.producerFactory.newLocalVideoThumbnailProducer();
                Intrinsics.checkNotNullExpressionValue(localVideoThumbnailProducerNewLocalVideoThumbnailProducer, "producerFactory.newLocalVideoThumbnailProducer()");
                return this.this$0.newBitmapCacheGetToBitmapCacheSequence(localVideoThumbnailProducerNewLocalVideoThumbnailProducer);
            }
        });
        this.localContentUriFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localContentUriFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                LocalContentUriFetchProducer localContentUriFetchProducerNewLocalContentUriFetchProducer = this.this$0.producerFactory.newLocalContentUriFetchProducer();
                Intrinsics.checkNotNullExpressionValue(localContentUriFetchProducerNewLocalContentUriFetchProducer, "producerFactory.newLocalContentUriFetchProducer()");
                LocalContentUriThumbnailFetchProducer localContentUriThumbnailFetchProducerNewLocalContentUriThumbnailFetchProducer = this.this$0.producerFactory.newLocalContentUriThumbnailFetchProducer();
                Intrinsics.checkNotNullExpressionValue(localContentUriThumbnailFetchProducerNewLocalContentUriThumbnailFetchProducer, "producerFactory.newLocal…iThumbnailFetchProducer()");
                LocalExifThumbnailProducer localExifThumbnailProducerNewLocalExifThumbnailProducer = this.this$0.producerFactory.newLocalExifThumbnailProducer();
                Intrinsics.checkNotNullExpressionValue(localExifThumbnailProducerNewLocalExifThumbnailProducer, "producerFactory.newLocalExifThumbnailProducer()");
                return this.this$0.newBitmapCacheGetToLocalTransformSequence(localContentUriFetchProducerNewLocalContentUriFetchProducer, new ThumbnailProducer[]{localContentUriThumbnailFetchProducerNewLocalContentUriThumbnailFetchProducer, localExifThumbnailProducerNewLocalExifThumbnailProducer});
            }
        });
        this.localThumbnailBitmapSdk29FetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localThumbnailBitmapSdk29FetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() throws Throwable {
                if (Build.VERSION.SDK_INT >= 29) {
                    ProducerSequenceFactory producerSequenceFactory = this.this$0;
                    LocalThumbnailBitmapSdk29Producer localThumbnailBitmapSdk29ProducerNewLocalThumbnailBitmapSdk29Producer = producerSequenceFactory.producerFactory.newLocalThumbnailBitmapSdk29Producer();
                    Intrinsics.checkNotNullExpressionValue(localThumbnailBitmapSdk29ProducerNewLocalThumbnailBitmapSdk29Producer, "producerFactory.newLocal…nailBitmapSdk29Producer()");
                    return producerSequenceFactory.newBitmapCacheGetToBitmapCacheSequence(localThumbnailBitmapSdk29ProducerNewLocalThumbnailBitmapSdk29Producer);
                }
                throw new Throwable("Unreachable exception. Just to make linter happy for the lazy block.");
            }
        });
        this.qualifiedResourceFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$qualifiedResourceFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                QualifiedResourceFetchProducer qualifiedResourceFetchProducerNewQualifiedResourceFetchProducer = this.this$0.producerFactory.newQualifiedResourceFetchProducer();
                Intrinsics.checkNotNullExpressionValue(qualifiedResourceFetchProducerNewQualifiedResourceFetchProducer, "producerFactory.newQuali…edResourceFetchProducer()");
                return this.this$0.newBitmapCacheGetToLocalTransformSequence(qualifiedResourceFetchProducerNewQualifiedResourceFetchProducer);
            }
        });
        this.localResourceFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localResourceFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                LocalResourceFetchProducer localResourceFetchProducerNewLocalResourceFetchProducer = this.this$0.producerFactory.newLocalResourceFetchProducer();
                Intrinsics.checkNotNullExpressionValue(localResourceFetchProducerNewLocalResourceFetchProducer, "producerFactory.newLocalResourceFetchProducer()");
                return this.this$0.newBitmapCacheGetToLocalTransformSequence(localResourceFetchProducerNewLocalResourceFetchProducer);
            }
        });
        this.localAssetFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$localAssetFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                LocalAssetFetchProducer localAssetFetchProducerNewLocalAssetFetchProducer = this.this$0.producerFactory.newLocalAssetFetchProducer();
                Intrinsics.checkNotNullExpressionValue(localAssetFetchProducerNewLocalAssetFetchProducer, "producerFactory.newLocalAssetFetchProducer()");
                return this.this$0.newBitmapCacheGetToLocalTransformSequence(localAssetFetchProducerNewLocalAssetFetchProducer);
            }
        });
        this.dataFetchSequence = LazyKt.lazy(new Function0<Producer<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ProducerSequenceFactory$dataFetchSequence$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Producer<CloseableReference<CloseableImage>> invoke() {
                DataFetchProducer dataFetchProducerNewDataFetchProducer = this.this$0.producerFactory.newDataFetchProducer();
                Intrinsics.checkNotNullExpressionValue(dataFetchProducerNewDataFetchProducer, "producerFactory.newDataFetchProducer()");
                AddImageTransformMetaDataProducer addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(dataFetchProducerNewDataFetchProducer);
                Intrinsics.checkNotNullExpressionValue(addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
                ResizeAndRotateProducer resizeAndRotateProducerNewResizeAndRotateProducer = this.this$0.producerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer, true, this.this$0.imageTranscoderFactory);
                Intrinsics.checkNotNullExpressionValue(resizeAndRotateProducerNewResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
                return this.this$0.newBitmapCacheGetToDecodeSequence(resizeAndRotateProducerNewResizeAndRotateProducer);
            }
        });
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> getPostprocessorSequences() {
        return this.postprocessorSequences;
    }

    public final void setPostprocessorSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.postprocessorSequences = map;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> getCloseableImagePrefetchSequences() {
        return this.closeableImagePrefetchSequences;
    }

    public final void setCloseableImagePrefetchSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.closeableImagePrefetchSequences = map;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> getBitmapPrepareSequences() {
        return this.bitmapPrepareSequences;
    }

    public final void setBitmapPrepareSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.bitmapPrepareSequences = map;
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getEncodedImageProducerSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<PooledByteBuffer>> networkFetchEncodedImageProducerSequence;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            INSTANCE.validateEncodedImageRequest(imageRequest);
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
            int sourceUriType = imageRequest.getSourceUriType();
            if (sourceUriType == 0) {
                return getNetworkFetchEncodedImageProducerSequence();
            }
            if (sourceUriType == 2 || sourceUriType == 3) {
                return getLocalFileFetchEncodedImageProducerSequence();
            }
            if (sourceUriType == 4) {
                return getLocalContentUriFetchEncodedImageProducerSequence();
            }
            Set<CustomProducerSequenceFactory> set = this.customProducerSequenceFactories;
            if (set != null) {
                Iterator<CustomProducerSequenceFactory> it = set.iterator();
                while (it.hasNext()) {
                    Producer<CloseableReference<PooledByteBuffer>> customEncodedImageSequence = it.next().getCustomEncodedImageSequence(imageRequest, this, this.producerFactory, this.threadHandoffProducerQueue);
                    if (customEncodedImageSequence != null) {
                        return customEncodedImageSequence;
                    }
                }
            }
            throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + INSTANCE.getShortenedUriString(sourceUri));
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getEncodedImageProducerSequence");
        try {
            INSTANCE.validateEncodedImageRequest(imageRequest);
            Uri sourceUri2 = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri2, "imageRequest.sourceUri");
            int sourceUriType2 = imageRequest.getSourceUriType();
            if (sourceUriType2 == 0) {
                networkFetchEncodedImageProducerSequence = getNetworkFetchEncodedImageProducerSequence();
            } else if (sourceUriType2 == 2 || sourceUriType2 == 3) {
                networkFetchEncodedImageProducerSequence = getLocalFileFetchEncodedImageProducerSequence();
            } else {
                if (sourceUriType2 != 4) {
                    Set<CustomProducerSequenceFactory> set2 = this.customProducerSequenceFactories;
                    if (set2 != null) {
                        Iterator<CustomProducerSequenceFactory> it2 = set2.iterator();
                        while (it2.hasNext()) {
                            Producer<CloseableReference<PooledByteBuffer>> customEncodedImageSequence2 = it2.next().getCustomEncodedImageSequence(imageRequest, this, this.producerFactory, this.threadHandoffProducerQueue);
                            if (customEncodedImageSequence2 != null) {
                                return customEncodedImageSequence2;
                            }
                        }
                    }
                    throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + INSTANCE.getShortenedUriString(sourceUri2));
                }
                networkFetchEncodedImageProducerSequence = getLocalContentUriFetchEncodedImageProducerSequence();
            }
            return networkFetchEncodedImageProducerSequence;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getNetworkFetchEncodedImageProducerSequence() {
        return (Producer) this.networkFetchEncodedImageProducerSequence.getValue();
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getLocalFileFetchEncodedImageProducerSequence() {
        return (Producer) this.localFileFetchEncodedImageProducerSequence.getValue();
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getLocalContentUriFetchEncodedImageProducerSequence() {
        return (Producer) this.localContentUriFetchEncodedImageProducerSequence.getValue();
    }

    public final Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Companion companion = INSTANCE;
        companion.validateEncodedImageRequest(imageRequest);
        int sourceUriType = imageRequest.getSourceUriType();
        if (sourceUriType == 0) {
            return getNetworkFetchToEncodedMemoryPrefetchSequence();
        }
        if (sourceUriType == 2 || sourceUriType == 3) {
            return getLocalFileFetchToEncodedMemoryPrefetchSequence();
        }
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
        throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion.getShortenedUriString(sourceUri));
    }

    public final Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence = getPostprocessorSequence(basicDecodedImageSequence);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
            }
            return (!this.allowDelay || imageRequest.getDelayMs() <= 0) ? basicDecodedImageSequence : getDelaySequence(basicDecodedImageSequence);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getDecodedImageProducerSequence");
        try {
            Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence2 = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence2 = getPostprocessorSequence(basicDecodedImageSequence2);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence2 = getBitmapPrepareSequence(basicDecodedImageSequence2);
            }
            if (this.allowDelay && imageRequest.getDelayMs() > 0) {
                basicDecodedImageSequence2 = getDelaySequence(basicDecodedImageSequence2);
            }
            return basicDecodedImageSequence2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<Void> getDecodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
        if (this.useBitmapPrepareToDraw) {
            basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
        }
        return getDecodedImagePrefetchSequence(basicDecodedImageSequence);
    }

    private final Producer<CloseableReference<CloseableImage>> getBasicDecodedImageSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> networkFetchSequence;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
            if (sourceUri == null) {
                throw new IllegalStateException("Uri is null.".toString());
            }
            int sourceUriType = imageRequest.getSourceUriType();
            if (sourceUriType == 0) {
                return getNetworkFetchSequence();
            }
            switch (sourceUriType) {
                case 2:
                    return imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ() ? getLocalThumbnailBitmapSdk29FetchSequence() : getLocalVideoFileFetchSequence();
                case 3:
                    return imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ() ? getLocalThumbnailBitmapSdk29FetchSequence() : getLocalImageFileFetchSequence();
                case 4:
                    return imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ() ? getLocalThumbnailBitmapSdk29FetchSequence() : MediaUtils.isVideo(this.contentResolver.getType(sourceUri)) ? getLocalVideoFileFetchSequence() : getLocalContentUriFetchSequence();
                case 5:
                    return getLocalAssetFetchSequence();
                case 6:
                    return getLocalResourceFetchSequence();
                case 7:
                    return getDataFetchSequence();
                case 8:
                    return getQualifiedResourceFetchSequence();
                default:
                    Set<CustomProducerSequenceFactory> set = this.customProducerSequenceFactories;
                    if (set != null) {
                        Iterator<CustomProducerSequenceFactory> it = set.iterator();
                        while (it.hasNext()) {
                            Producer<CloseableReference<CloseableImage>> customDecodedImageSequence = it.next().getCustomDecodedImageSequence(imageRequest, this, this.producerFactory, this.threadHandoffProducerQueue, this.isEncodedMemoryCacheProbingEnabled, this.isDiskCacheProbingEnabled);
                            if (customDecodedImageSequence != null) {
                                return customDecodedImageSequence;
                            }
                        }
                    }
                    throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + INSTANCE.getShortenedUriString(sourceUri));
            }
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getBasicDecodedImageSequence");
        try {
            Uri sourceUri2 = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri2, "imageRequest.sourceUri");
            if (sourceUri2 == null) {
                throw new IllegalStateException("Uri is null.".toString());
            }
            int sourceUriType2 = imageRequest.getSourceUriType();
            if (sourceUriType2 != 0) {
                switch (sourceUriType2) {
                    case 2:
                        if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            networkFetchSequence = getLocalVideoFileFetchSequence();
                            break;
                        } else {
                            return getLocalThumbnailBitmapSdk29FetchSequence();
                        }
                    case 3:
                        if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            networkFetchSequence = getLocalImageFileFetchSequence();
                            break;
                        } else {
                            return getLocalThumbnailBitmapSdk29FetchSequence();
                        }
                    case 4:
                        if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            if (!MediaUtils.isVideo(this.contentResolver.getType(sourceUri2))) {
                                networkFetchSequence = getLocalContentUriFetchSequence();
                                break;
                            } else {
                                return getLocalVideoFileFetchSequence();
                            }
                        } else {
                            return getLocalThumbnailBitmapSdk29FetchSequence();
                        }
                    case 5:
                        networkFetchSequence = getLocalAssetFetchSequence();
                        break;
                    case 6:
                        networkFetchSequence = getLocalResourceFetchSequence();
                        break;
                    case 7:
                        networkFetchSequence = getDataFetchSequence();
                        break;
                    case 8:
                        networkFetchSequence = getQualifiedResourceFetchSequence();
                        break;
                    default:
                        Set<CustomProducerSequenceFactory> set2 = this.customProducerSequenceFactories;
                        if (set2 != null) {
                            Iterator<CustomProducerSequenceFactory> it2 = set2.iterator();
                            while (it2.hasNext()) {
                                Producer<CloseableReference<CloseableImage>> customDecodedImageSequence2 = it2.next().getCustomDecodedImageSequence(imageRequest, this, this.producerFactory, this.threadHandoffProducerQueue, this.isEncodedMemoryCacheProbingEnabled, this.isDiskCacheProbingEnabled);
                                if (customDecodedImageSequence2 != null) {
                                    return customDecodedImageSequence2;
                                }
                            }
                        }
                        throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + INSTANCE.getShortenedUriString(sourceUri2));
                }
            } else {
                networkFetchSequence = getNetworkFetchSequence();
            }
            return networkFetchSequence;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<CloseableReference<CloseableImage>> getNetworkFetchSequence() {
        return (Producer) this.networkFetchSequence.getValue();
    }

    public final Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        Object value = this.backgroundNetworkFetchToEncodedMemorySequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundNetworkFe…codedMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.networkFetchToEncodedMemoryPrefetchSequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-networkFetchToEncod…oryPrefetchSequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        return (Producer) this.commonNetworkFetchToEncodedMemorySequence.getValue();
    }

    public final synchronized Producer<EncodedImage> newCommonNetworkFetchToEncodedMemorySequence(NetworkFetcher<?> networkFetcher) {
        Intrinsics.checkNotNullParameter(networkFetcher, "networkFetcher");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        boolean z = true;
        if (!FrescoSystrace.isTracing()) {
            Producer<EncodedImage> producerNewNetworkFetchProducer = this.producerFactory.newNetworkFetchProducer(networkFetcher);
            Intrinsics.checkNotNullExpressionValue(producerNewNetworkFetchProducer, "producerFactory.newNetwo…hProducer(networkFetcher)");
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(producerNewNetworkFetchProducer));
            Intrinsics.checkNotNullExpressionValue(addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducer = addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer;
            ProducerFactory producerFactory = this.producerFactory;
            if (!this.resizeAndRotateEnabledForNetwork || this.downsampleMode == DownsampleMode.NEVER) {
                z = false;
            }
            ResizeAndRotateProducer resizeAndRotateProducerNewResizeAndRotateProducer = producerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducer, z, this.imageTranscoderFactory);
            Intrinsics.checkNotNullExpressionValue(resizeAndRotateProducerNewResizeAndRotateProducer, "producerFactory.newResiz…  imageTranscoderFactory)");
            ResizeAndRotateProducer networkFetchToEncodedMemorySequence = resizeAndRotateProducerNewResizeAndRotateProducer;
            Intrinsics.checkNotNullExpressionValue(networkFetchToEncodedMemorySequence, "networkFetchToEncodedMemorySequence");
            return networkFetchToEncodedMemorySequence;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#createCommonNetworkFetchToEncodedMemorySequence");
        try {
            Producer<EncodedImage> producerNewNetworkFetchProducer2 = this.producerFactory.newNetworkFetchProducer(networkFetcher);
            Intrinsics.checkNotNullExpressionValue(producerNewNetworkFetchProducer2, "producerFactory.newNetwo…hProducer(networkFetcher)");
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer2 = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(producerNewNetworkFetchProducer2));
            Intrinsics.checkNotNullExpressionValue(addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer2, "newAddImageTransformMeta…taProducer(inputProducer)");
            AddImageTransformMetaDataProducer addImageTransformMetaDataProducer2 = addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer2;
            ProducerFactory producerFactory2 = this.producerFactory;
            if (!this.resizeAndRotateEnabledForNetwork || this.downsampleMode == DownsampleMode.NEVER) {
                z = false;
            }
            ResizeAndRotateProducer resizeAndRotateProducerNewResizeAndRotateProducer2 = producerFactory2.newResizeAndRotateProducer(addImageTransformMetaDataProducer2, z, this.imageTranscoderFactory);
            Intrinsics.checkNotNullExpressionValue(resizeAndRotateProducerNewResizeAndRotateProducer2, "producerFactory.newResiz…  imageTranscoderFactory)");
            ResizeAndRotateProducer networkFetchToEncodedMemorySequence2 = resizeAndRotateProducerNewResizeAndRotateProducer2;
            Intrinsics.checkNotNullExpressionValue(networkFetchToEncodedMemorySequence2, "networkFetchToEncodedMemorySequence");
            return networkFetchToEncodedMemorySequence2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<Void> getLocalFileFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.localFileFetchToEncodedMemoryPrefetchSequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-localFileFetchToEnc…oryPrefetchSequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getBackgroundLocalFileFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalFileFetchToEncodeMemorySequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundLocalFile…ncodeMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getBackgroundLocalContentUriFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalContentUriFetchToEncodeMemorySequence.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundLocalCont…ncodeMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence() {
        return (Producer) this.localImageFileFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        return (Producer) this.localVideoFileFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        return (Producer) this.localContentUriFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalThumbnailBitmapSdk29FetchSequence() {
        return (Producer) this.localThumbnailBitmapSdk29FetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getQualifiedResourceFetchSequence() {
        return (Producer) this.qualifiedResourceFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        return (Producer) this.localResourceFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        return (Producer) this.localAssetFetchSequence.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        return (Producer) this.dataFetchSequence.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer) {
        LocalExifThumbnailProducer localExifThumbnailProducerNewLocalExifThumbnailProducer = this.producerFactory.newLocalExifThumbnailProducer();
        Intrinsics.checkNotNullExpressionValue(localExifThumbnailProducerNewLocalExifThumbnailProducer, "producerFactory.newLocalExifThumbnailProducer()");
        return newBitmapCacheGetToLocalTransformSequence(inputProducer, new ThumbnailProducer[]{localExifThumbnailProducerNewLocalExifThumbnailProducer});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> inputProducer, ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        return newBitmapCacheGetToDecodeSequence(newLocalTransformationsSequence(newEncodedCacheMultiplexToTranscodeSequence(inputProducer), thumbnailProducers));
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> inputProducer) {
        Intrinsics.checkNotNullParameter(inputProducer, "inputProducer");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            DecodeProducer decodeProducerNewDecodeProducer = this.producerFactory.newDecodeProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(decodeProducerNewDecodeProducer, "producerFactory.newDecodeProducer(inputProducer)");
            return newBitmapCacheGetToBitmapCacheSequence(decodeProducerNewDecodeProducer);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newBitmapCacheGetToDecodeSequence");
        try {
            DecodeProducer decodeProducerNewDecodeProducer2 = this.producerFactory.newDecodeProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(decodeProducerNewDecodeProducer2, "producerFactory.newDecodeProducer(inputProducer)");
            return newBitmapCacheGetToBitmapCacheSequence(decodeProducerNewDecodeProducer2);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(Producer<EncodedImage> inputProducer) {
        if (this.diskCacheEnabled) {
            inputProducer = newDiskCacheSequence(inputProducer);
        }
        Producer<EncodedImage> producerNewEncodedMemoryCacheProducer = this.producerFactory.newEncodedMemoryCacheProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(producerNewEncodedMemoryCacheProducer, "producerFactory.newEncodedMemoryCacheProducer(ip)");
        if (this.isDiskCacheProbingEnabled) {
            EncodedProbeProducer encodedProbeProducerNewEncodedProbeProducer = this.producerFactory.newEncodedProbeProducer(producerNewEncodedMemoryCacheProducer);
            Intrinsics.checkNotNullExpressionValue(encodedProbeProducerNewEncodedProbeProducer, "producerFactory.newEncod…codedMemoryCacheProducer)");
            EncodedCacheKeyMultiplexProducer encodedCacheKeyMultiplexProducerNewEncodedCacheKeyMultiplexProducer = this.producerFactory.newEncodedCacheKeyMultiplexProducer(encodedProbeProducerNewEncodedProbeProducer);
            Intrinsics.checkNotNullExpressionValue(encodedCacheKeyMultiplexProducerNewEncodedCacheKeyMultiplexProducer, "producerFactory.newEncod…exProducer(probeProducer)");
            return encodedCacheKeyMultiplexProducerNewEncodedCacheKeyMultiplexProducer;
        }
        EncodedCacheKeyMultiplexProducer encodedCacheKeyMultiplexProducerNewEncodedCacheKeyMultiplexProducer2 = this.producerFactory.newEncodedCacheKeyMultiplexProducer(producerNewEncodedMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(encodedCacheKeyMultiplexProducerNewEncodedCacheKeyMultiplexProducer2, "producerFactory.newEncod…codedMemoryCacheProducer)");
        return encodedCacheKeyMultiplexProducerNewEncodedCacheKeyMultiplexProducer2;
    }

    private final Producer<EncodedImage> newDiskCacheSequence(Producer<EncodedImage> inputProducer) {
        DiskCacheWriteProducer diskCacheWriteProducerNewDiskCacheWriteProducer;
        DiskCacheWriteProducer diskCacheWriteProducerNewDiskCacheWriteProducer2;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer partialDiskCacheProducerNewPartialDiskCacheProducer = this.producerFactory.newPartialDiskCacheProducer(inputProducer);
                Intrinsics.checkNotNullExpressionValue(partialDiskCacheProducerNewPartialDiskCacheProducer, "producerFactory.newParti…heProducer(inputProducer)");
                diskCacheWriteProducerNewDiskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(partialDiskCacheProducerNewPartialDiskCacheProducer);
            } else {
                diskCacheWriteProducerNewDiskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(inputProducer);
            }
            Intrinsics.checkNotNullExpressionValue(diskCacheWriteProducerNewDiskCacheWriteProducer2, "if (partialImageCachingE…utProducer)\n            }");
            DiskCacheReadProducer diskCacheReadProducerNewDiskCacheReadProducer = this.producerFactory.newDiskCacheReadProducer(diskCacheWriteProducerNewDiskCacheWriteProducer2);
            Intrinsics.checkNotNullExpressionValue(diskCacheReadProducerNewDiskCacheReadProducer, "producerFactory.newDiskC…ducer(cacheWriteProducer)");
            return diskCacheReadProducerNewDiskCacheReadProducer;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newDiskCacheSequence");
        try {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer partialDiskCacheProducerNewPartialDiskCacheProducer2 = this.producerFactory.newPartialDiskCacheProducer(inputProducer);
                Intrinsics.checkNotNullExpressionValue(partialDiskCacheProducerNewPartialDiskCacheProducer2, "producerFactory.newParti…heProducer(inputProducer)");
                diskCacheWriteProducerNewDiskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(partialDiskCacheProducerNewPartialDiskCacheProducer2);
            } else {
                diskCacheWriteProducerNewDiskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(inputProducer);
            }
            Intrinsics.checkNotNullExpressionValue(diskCacheWriteProducerNewDiskCacheWriteProducer, "if (partialImageCachingE…utProducer)\n            }");
            DiskCacheReadProducer diskCacheReadProducerNewDiskCacheReadProducer2 = this.producerFactory.newDiskCacheReadProducer(diskCacheWriteProducerNewDiskCacheWriteProducer);
            Intrinsics.checkNotNullExpressionValue(diskCacheReadProducerNewDiskCacheReadProducer2, "producerFactory.newDiskC…ducer(cacheWriteProducer)");
            return diskCacheReadProducerNewDiskCacheReadProducer2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        BitmapMemoryCacheProducer bitmapMemoryCacheProducerNewBitmapMemoryCacheProducer = this.producerFactory.newBitmapMemoryCacheProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(bitmapMemoryCacheProducerNewBitmapMemoryCacheProducer, "producerFactory.newBitma…heProducer(inputProducer)");
        BitmapMemoryCacheKeyMultiplexProducer bitmapMemoryCacheKeyMultiplexProducerNewBitmapMemoryCacheKeyMultiplexProducer = this.producerFactory.newBitmapMemoryCacheKeyMultiplexProducer(bitmapMemoryCacheProducerNewBitmapMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(bitmapMemoryCacheKeyMultiplexProducerNewBitmapMemoryCacheKeyMultiplexProducer, "producerFactory.newBitma…itmapMemoryCacheProducer)");
        Producer<CloseableReference<CloseableImage>> producerNewBackgroundThreadHandoffProducer = this.producerFactory.newBackgroundThreadHandoffProducer(bitmapMemoryCacheKeyMultiplexProducerNewBitmapMemoryCacheKeyMultiplexProducer, this.threadHandoffProducerQueue);
        Intrinsics.checkNotNullExpressionValue(producerNewBackgroundThreadHandoffProducer, "producerFactory.newBackg…readHandoffProducerQueue)");
        if (this.isEncodedMemoryCacheProbingEnabled || this.isDiskCacheProbingEnabled) {
            BitmapMemoryCacheGetProducer bitmapMemoryCacheGetProducerNewBitmapMemoryCacheGetProducer = this.producerFactory.newBitmapMemoryCacheGetProducer(producerNewBackgroundThreadHandoffProducer);
            Intrinsics.checkNotNullExpressionValue(bitmapMemoryCacheGetProducerNewBitmapMemoryCacheGetProducer, "producerFactory.newBitma…er(threadHandoffProducer)");
            BitmapProbeProducer bitmapProbeProducerNewBitmapProbeProducer = this.producerFactory.newBitmapProbeProducer(bitmapMemoryCacheGetProducerNewBitmapMemoryCacheGetProducer);
            Intrinsics.checkNotNullExpressionValue(bitmapProbeProducerNewBitmapProbeProducer, "producerFactory.newBitma…apMemoryCacheGetProducer)");
            return bitmapProbeProducerNewBitmapProbeProducer;
        }
        BitmapMemoryCacheGetProducer bitmapMemoryCacheGetProducerNewBitmapMemoryCacheGetProducer2 = this.producerFactory.newBitmapMemoryCacheGetProducer(producerNewBackgroundThreadHandoffProducer);
        Intrinsics.checkNotNullExpressionValue(bitmapMemoryCacheGetProducerNewBitmapMemoryCacheGetProducer2, "producerFactory.newBitma…er(threadHandoffProducer)");
        return bitmapMemoryCacheGetProducerNewBitmapMemoryCacheGetProducer2;
    }

    private final Producer<EncodedImage> newLocalTransformationsSequence(Producer<EncodedImage> inputProducer, ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        AddImageTransformMetaDataProducer addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
        ResizeAndRotateProducer resizeAndRotateProducerNewResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(addImageTransformMetaDataProducerNewAddImageTransformMetaDataProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(resizeAndRotateProducerNewResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
        ThrottlingProducer throttlingProducerNewThrottlingProducer = this.producerFactory.newThrottlingProducer(resizeAndRotateProducerNewResizeAndRotateProducer);
        Intrinsics.checkNotNullExpressionValue(throttlingProducerNewThrottlingProducer, "producerFactory.newThrot…ducer(localImageProducer)");
        BranchOnSeparateImagesProducer branchOnSeparateImagesProducerNewBranchOnSeparateImagesProducer = ProducerFactory.newBranchOnSeparateImagesProducer(newLocalThumbnailProducer(thumbnailProducers), throttlingProducerNewThrottlingProducer);
        Intrinsics.checkNotNullExpressionValue(branchOnSeparateImagesProducerNewBranchOnSeparateImagesProducer, "newBranchOnSeparateImage…lImageThrottlingProducer)");
        return branchOnSeparateImagesProducerNewBranchOnSeparateImagesProducer;
    }

    private final Producer<EncodedImage> newLocalThumbnailProducer(ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        ThumbnailBranchProducer thumbnailBranchProducerNewThumbnailBranchProducer = this.producerFactory.newThumbnailBranchProducer(thumbnailProducers);
        Intrinsics.checkNotNullExpressionValue(thumbnailBranchProducerNewThumbnailBranchProducer, "producerFactory.newThumb…ducer(thumbnailProducers)");
        ResizeAndRotateProducer resizeAndRotateProducerNewResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(thumbnailBranchProducerNewThumbnailBranchProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(resizeAndRotateProducerNewResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
        return resizeAndRotateProducerNewResizeAndRotateProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        PostprocessedBitmapMemoryCacheProducer postprocessedBitmapMemoryCacheProducerNewPostprocessorBitmapMemoryCacheProducer;
        postprocessedBitmapMemoryCacheProducerNewPostprocessorBitmapMemoryCacheProducer = this.postprocessorSequences.get(inputProducer);
        if (postprocessedBitmapMemoryCacheProducerNewPostprocessorBitmapMemoryCacheProducer == null) {
            PostprocessorProducer postprocessorProducerNewPostprocessorProducer = this.producerFactory.newPostprocessorProducer(inputProducer);
            Intrinsics.checkNotNullExpressionValue(postprocessorProducerNewPostprocessorProducer, "producerFactory.newPostp…orProducer(inputProducer)");
            postprocessedBitmapMemoryCacheProducerNewPostprocessorBitmapMemoryCacheProducer = this.producerFactory.newPostprocessorBitmapMemoryCacheProducer(postprocessorProducerNewPostprocessorProducer);
            this.postprocessorSequences.put(inputProducer, postprocessedBitmapMemoryCacheProducerNewPostprocessorBitmapMemoryCacheProducer);
        }
        return postprocessedBitmapMemoryCacheProducerNewPostprocessorBitmapMemoryCacheProducer;
    }

    private final synchronized Producer<Void> getDecodedImagePrefetchSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        SwallowResultProducer swallowResultProducerNewSwallowResultProducer;
        swallowResultProducerNewSwallowResultProducer = this.closeableImagePrefetchSequences.get(inputProducer);
        if (swallowResultProducerNewSwallowResultProducer == null) {
            swallowResultProducerNewSwallowResultProducer = this.producerFactory.newSwallowResultProducer(inputProducer);
            this.closeableImagePrefetchSequences.put(inputProducer, swallowResultProducerNewSwallowResultProducer);
        }
        return swallowResultProducerNewSwallowResultProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getBitmapPrepareSequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        BitmapPrepareProducer bitmapPrepareProducerNewBitmapPrepareProducer;
        bitmapPrepareProducerNewBitmapPrepareProducer = this.bitmapPrepareSequences.get(inputProducer);
        if (bitmapPrepareProducerNewBitmapPrepareProducer == null) {
            bitmapPrepareProducerNewBitmapPrepareProducer = this.producerFactory.newBitmapPrepareProducer(inputProducer);
            this.bitmapPrepareSequences.put(inputProducer, bitmapPrepareProducerNewBitmapPrepareProducer);
        }
        return bitmapPrepareProducerNewBitmapPrepareProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getDelaySequence(Producer<CloseableReference<CloseableImage>> inputProducer) {
        DelayProducer delayProducerNewDelayProducer;
        delayProducerNewDelayProducer = this.producerFactory.newDelayProducer(inputProducer);
        Intrinsics.checkNotNullExpressionValue(delayProducerNewDelayProducer, "producerFactory.newDelayProducer(inputProducer)");
        return delayProducerNewDelayProducer;
    }

    /* compiled from: ProducerSequenceFactory.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"Lcom/facebook/imagepipeline/core/ProducerSequenceFactory$Companion;", "", "()V", "getShortenedUriString", "", "uri", "Landroid/net/Uri;", "validateEncodedImageRequest", "", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void validateEncodedImageRequest(ImageRequest imageRequest) {
            Preconditions.checkArgument(Boolean.valueOf(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getShortenedUriString(Uri uri) {
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "uri.toString()");
            if (string.length() <= 30) {
                return string;
            }
            String strSubstring = string.substring(0, 30);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            return strSubstring + "...";
        }
    }
}
