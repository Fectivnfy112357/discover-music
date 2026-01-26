package com.facebook.imagepipeline.core;

import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.callercontext.CallerContextVerifier;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Predicate;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.ForwardingRequestListener2;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.listener.RequestListener2;
import com.facebook.imagepipeline.producers.InternalRequestListener;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImagePipeline.kt */
@Metadata(d1 = {"\u0000Ö\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 n2\u00020\u0001:\u0001nB±\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005\u0012\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r\u0012\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\r\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0002\u0010\u001fJ\u0006\u00104\u001a\u000205J\u0006\u00106\u001a\u000205J\u0006\u00107\u001a\u000205J\u000e\u00108\u001a\u0002052\u0006\u00109\u001a\u00020:J\u0010\u0010;\u001a\u0002052\b\u00109\u001a\u0004\u0018\u00010:J\u0010\u0010;\u001a\u0002052\b\u0010<\u001a\u0004\u0018\u00010=J\u000e\u0010>\u001a\u0002052\u0006\u00109\u001a\u00020:J&\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J.\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010.\u001a\u00020\u0006J.\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010C\u001a\u00020DJV\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010C\u001a\u00020D2\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010F2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0002\b\u0003\u0018\u00010HJJ\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010C\u001a\u0004\u0018\u00010D2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010E\u001a\u0004\u0018\u00010FJ$\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J.\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006J$\u0010J\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J\u0006\u0010K\u001a\u00020FJ\u001c\u0010L\u001a\u0004\u0018\u00010\u000e2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J\u0018\u0010M\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010A2\b\u0010N\u001a\u0004\u0018\u00010\u000eJ\u0010\u0010O\u001a\u00020\u00062\b\u0010P\u001a\u0004\u0018\u00010\u0006J4\u0010Q\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010R\u001a\u0004\u0018\u00010DJ>\u0010Q\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010R\u001a\u0004\u0018\u00010D2\b\u0010.\u001a\u0004\u0018\u00010\u0006JH\u0010Q\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010R\u001a\u0004\u0018\u00010D2\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010FJ*\u0010S\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110A0@0\n2\u0006\u0010<\u001a\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J\u001a\u0010T\u001a\u00020\u00062\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010.\u001a\u0004\u0018\u00010\u0006J\u0010\u0010U\u001a\u00020\u000b2\b\u0010N\u001a\u0004\u0018\u00010\u000eJ\u0006\u0010V\u001a\u000205J\u0010\u0010W\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:J\u0010\u0010W\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010=J\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u000b0@2\b\u00109\u001a\u0004\u0018\u00010:J\u0016\u0010X\u001a\b\u0012\u0004\u0012\u00020\u000b0@2\b\u0010<\u001a\u0004\u0018\u00010=J\u0010\u0010Y\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:J\u001a\u0010Y\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010Z\u001a\u0004\u0018\u00010[J\u000e\u0010Y\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020=J\u0010\u0010\\\u001a\u00020\u000b2\b\u00109\u001a\u0004\u0018\u00010:J\u0010\u0010\\\u001a\u00020\u000b2\b\u0010<\u001a\u0004\u0018\u00010=J\u0006\u0010]\u001a\u000205J\u0016\u0010^\u001a\b\u0012\u0004\u0012\u00020\u000e0_2\u0006\u00109\u001a\u00020:H\u0002J$\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001H\u0007J.\u0010`\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0007J\"\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u0001J*\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010c\u001a\u00020dJ6\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010c\u001a\u00020d2\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0007J,\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006J:\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010c\u001a\u00020d2\n\b\u0002\u0010.\u001a\u0004\u0018\u00010\u0006H\u0007J,\u0010e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u0006J\u0006\u0010f\u001a\u000205J\\\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010FH\u0002Jp\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u00062\b\u0010E\u001a\u0004\u0018\u00010F2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0002\b\u0003\u0018\u00010HH\u0002Jf\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0012\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\b\u0010.\u001a\u0004\u0018\u00010\u00062\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u00020F\u0012\u0002\b\u0003\u0018\u00010HH\u0002J@\u0010g\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002Hh0A0@\"\u0004\b\u0000\u0010h2\u0014\u0010i\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u0002Hh\u0018\u00010A0j2\u0006\u0010k\u001a\u00020l2\b\u0010.\u001a\u0004\u0018\u00010\u0006JL\u0010m\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0@2\u000e\u0010i\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010a0j2\u0006\u0010<\u001a\u00020=2\u0006\u0010C\u001a\u00020D2\b\u0010B\u001a\u0004\u0018\u00010\u00012\u0006\u0010c\u001a\u00020d2\b\u0010.\u001a\u0004\u0018\u00010\u0006H\u0002R\u001d\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u000e\u0010.\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u00100\u001a\u0002018F¢\u0006\u0006\u001a\u0004\b2\u00103¨\u0006o"}, d2 = {"Lcom/facebook/imagepipeline/core/ImagePipeline;", "", "producerSequenceFactory", "Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "requestListeners", "", "Lcom/facebook/imagepipeline/listener/RequestListener;", "requestListener2s", "Lcom/facebook/imagepipeline/listener/RequestListener2;", "isPrefetchEnabledSupplier", "Lcom/facebook/common/internal/Supplier;", "", "bitmapMemoryCache", "Lcom/facebook/imagepipeline/cache/MemoryCache;", "Lcom/facebook/cache/common/CacheKey;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "encodedMemoryCache", "Lcom/facebook/common/memory/PooledByteBuffer;", "mainBufferedDiskCache", "Lcom/facebook/imagepipeline/cache/BufferedDiskCache;", "smallImageBufferedDiskCache", "cacheKeyFactory", "Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "suppressBitmapPrefetchingSupplier", "lazyDataSource", "callerContextVerifier", "Lcom/facebook/callercontext/CallerContextVerifier;", "config", "Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "(Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;Ljava/util/Set;Ljava/util/Set;Lcom/facebook/common/internal/Supplier;Lcom/facebook/imagepipeline/cache/MemoryCache;Lcom/facebook/imagepipeline/cache/MemoryCache;Lcom/facebook/imagepipeline/cache/BufferedDiskCache;Lcom/facebook/imagepipeline/cache/BufferedDiskCache;Lcom/facebook/imagepipeline/cache/CacheKeyFactory;Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;Lcom/facebook/common/internal/Supplier;Lcom/facebook/common/internal/Supplier;Lcom/facebook/callercontext/CallerContextVerifier;Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;)V", "getBitmapMemoryCache", "()Lcom/facebook/imagepipeline/cache/MemoryCache;", "getCacheKeyFactory", "()Lcom/facebook/imagepipeline/cache/CacheKeyFactory;", "getConfig", "()Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "idCounter", "Ljava/util/concurrent/atomic/AtomicLong;", "isLazyDataSource", "()Lcom/facebook/common/internal/Supplier;", "isPaused", "()Z", "getProducerSequenceFactory", "()Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "requestListener", "requestListener2", "usedDiskCacheSize", "", "getUsedDiskCacheSize", "()J", "clearCaches", "", "clearDiskCaches", "clearMemoryCaches", "evictFromCache", "uri", "Landroid/net/Uri;", "evictFromDiskCache", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "evictFromMemoryCache", "fetchDecodedImage", "Lcom/facebook/datasource/DataSource;", "Lcom/facebook/common/references/CloseableReference;", "callerContext", "lowestPermittedRequestLevelOnSubmit", "Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;", "uiComponentId", "", "extras", "", "fetchEncodedImage", "fetchImageFromBitmapCache", "generateUniqueFutureId", "getCacheKey", "getCachedImage", "cacheKey", "getCombinedRequestListener", "listener", "getDataSourceSupplier", "requestLevel", "getEncodedImageDataSourceSupplier", "getRequestListenerForRequest", "hasCachedImage", "init", "isInBitmapMemoryCache", "isInDiskCache", "isInDiskCacheSync", "cacheChoice", "Lcom/facebook/imagepipeline/request/ImageRequest$CacheChoice;", "isInEncodedMemoryCache", "pause", "predicateForUri", "Lcom/facebook/common/internal/Predicate;", "prefetchToBitmapCache", "Ljava/lang/Void;", "prefetchToDiskCache", "priority", "Lcom/facebook/imagepipeline/common/Priority;", "prefetchToEncodedCache", "resume", "submitFetchRequest", ExifInterface.GPS_DIRECTION_TRUE, "producerSequence", "Lcom/facebook/imagepipeline/producers/Producer;", "settableProducerContext", "Lcom/facebook/imagepipeline/producers/SettableProducerContext;", "submitPrefetchRequest", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImagePipeline {
    private final MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache;
    private final CacheKeyFactory cacheKeyFactory;
    private final CallerContextVerifier callerContextVerifier;
    private final ImagePipelineConfigInterface config;
    private final MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache;
    private final AtomicLong idCounter;
    private final Supplier<Boolean> isLazyDataSource;
    private final Supplier<Boolean> isPrefetchEnabledSupplier;
    private final BufferedDiskCache mainBufferedDiskCache;
    private final ProducerSequenceFactory producerSequenceFactory;
    private final RequestListener requestListener;
    private final RequestListener2 requestListener2;
    private final BufferedDiskCache smallImageBufferedDiskCache;
    private final Supplier<Boolean> suppressBitmapPrefetchingSupplier;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private static final CancellationException NULL_IMAGEREQUEST_EXCEPTION = new CancellationException("ImageRequest is null");

    /* compiled from: ImagePipeline.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImageRequest.CacheChoice.values().length];
            try {
                iArr[ImageRequest.CacheChoice.DEFAULT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ImageRequest.CacheChoice.SMALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean clearMemoryCaches$lambda$2(CacheKey it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return true;
    }

    public final void init() {
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object obj) {
        return prefetchToEncodedCache$default(this, imageRequest, obj, null, null, 12, null);
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object obj, Priority priority) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        return prefetchToEncodedCache$default(this, imageRequest, obj, priority, null, 8, null);
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<? extends RequestListener> requestListeners, Set<? extends RequestListener2> requestListener2s, Supplier<Boolean> isPrefetchEnabledSupplier, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache mainBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue, Supplier<Boolean> suppressBitmapPrefetchingSupplier, Supplier<Boolean> lazyDataSource, CallerContextVerifier callerContextVerifier, ImagePipelineConfigInterface config) {
        Intrinsics.checkNotNullParameter(producerSequenceFactory, "producerSequenceFactory");
        Intrinsics.checkNotNullParameter(requestListeners, "requestListeners");
        Intrinsics.checkNotNullParameter(requestListener2s, "requestListener2s");
        Intrinsics.checkNotNullParameter(isPrefetchEnabledSupplier, "isPrefetchEnabledSupplier");
        Intrinsics.checkNotNullParameter(bitmapMemoryCache, "bitmapMemoryCache");
        Intrinsics.checkNotNullParameter(encodedMemoryCache, "encodedMemoryCache");
        Intrinsics.checkNotNullParameter(mainBufferedDiskCache, "mainBufferedDiskCache");
        Intrinsics.checkNotNullParameter(smallImageBufferedDiskCache, "smallImageBufferedDiskCache");
        Intrinsics.checkNotNullParameter(cacheKeyFactory, "cacheKeyFactory");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(suppressBitmapPrefetchingSupplier, "suppressBitmapPrefetchingSupplier");
        Intrinsics.checkNotNullParameter(lazyDataSource, "lazyDataSource");
        Intrinsics.checkNotNullParameter(config, "config");
        this.producerSequenceFactory = producerSequenceFactory;
        this.isPrefetchEnabledSupplier = isPrefetchEnabledSupplier;
        this.requestListener = new ForwardingRequestListener((Set<RequestListener>) requestListeners);
        this.requestListener2 = new ForwardingRequestListener2(requestListener2s);
        this.idCounter = new AtomicLong();
        this.bitmapMemoryCache = bitmapMemoryCache;
        this.encodedMemoryCache = encodedMemoryCache;
        this.mainBufferedDiskCache = mainBufferedDiskCache;
        this.smallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.cacheKeyFactory = cacheKeyFactory;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue;
        this.suppressBitmapPrefetchingSupplier = suppressBitmapPrefetchingSupplier;
        this.isLazyDataSource = lazyDataSource;
        this.callerContextVerifier = callerContextVerifier;
        this.config = config;
    }

    public final ProducerSequenceFactory getProducerSequenceFactory() {
        return this.producerSequenceFactory;
    }

    public final MemoryCache<CacheKey, CloseableImage> getBitmapMemoryCache() {
        return this.bitmapMemoryCache;
    }

    public final CacheKeyFactory getCacheKeyFactory() {
        return this.cacheKeyFactory;
    }

    public final Supplier<Boolean> isLazyDataSource() {
        return this.isLazyDataSource;
    }

    public final ImagePipelineConfigInterface getConfig() {
        return this.config;
    }

    public final String generateUniqueFutureId() {
        return String.valueOf(this.idCounter.getAndIncrement());
    }

    public final Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final ImageRequest.RequestLevel requestLevel) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getDataSourceSupplier.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.fetchDecodedImage$default(ImagePipeline.this, imageRequest, callerContext, requestLevel, null, null, 24, null);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final ImageRequest.RequestLevel requestLevel, final RequestListener requestListener) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getDataSourceSupplier.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.fetchDecodedImage$default(ImagePipeline.this, imageRequest, callerContext, requestLevel, requestListener, null, 16, null);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final ImageRequest.RequestLevel requestLevel, final RequestListener requestListener, final String uiComponentId) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getDataSourceSupplier.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<CloseableImage>> get() {
                return ImagePipeline.this.fetchDecodedImage(imageRequest, callerContext, requestLevel, requestListener, uiComponentId);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() { // from class: com.facebook.imagepipeline.core.ImagePipeline.getEncodedImageDataSourceSupplier.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.facebook.common.internal.Supplier
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, callerContext);
            }

            public String toString() {
                String string = Objects.toStringHelper(this).add("uri", imageRequest.getSourceUri()).toString();
                Intrinsics.checkNotNullExpressionValue(string, "toStringHelper(this).add…est.sourceUri).toString()");
                return string;
            }
        };
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, Object callerContext) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return fetchDecodedImage(imageRequest, callerContext, ImageRequest.RequestLevel.BITMAP_MEMORY_CACHE);
    }

    public static /* synthetic */ DataSource fetchDecodedImage$default(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, ImageRequest.RequestLevel requestLevel, RequestListener requestListener, String str, int i, Object obj2) {
        return imagePipeline.fetchDecodedImage(imageRequest, obj, (i & 4) != 0 ? null : requestLevel, (i & 8) != 0 ? null : requestListener, (i & 16) != 0 ? null : str);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, RequestListener requestListener, String uiComponentId) {
        if (imageRequest == null) {
            DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(new NullPointerException());
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(NullPointerException())");
            return dataSourceImmediateFailedDataSource;
        }
        try {
            Producer<CloseableReference<CloseableImage>> decodedImageProducerSequence = this.producerSequenceFactory.getDecodedImageProducerSequence(imageRequest);
            if (lowestPermittedRequestLevelOnSubmit == null) {
                lowestPermittedRequestLevelOnSubmit = ImageRequest.RequestLevel.FULL_FETCH;
            }
            return submitFetchRequest(decodedImageProducerSequence, imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener, uiComponentId);
        } catch (Exception e) {
            DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…taSource(exception)\n    }");
            return dataSourceImmediateFailedDataSource2;
        }
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext) {
        return fetchDecodedImage$default(this, imageRequest, callerContext, null, null, null, 24, null);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        Intrinsics.checkNotNullParameter(requestListener, "requestListener");
        return fetchDecodedImage$default(this, imageRequest, callerContext, null, requestListener, null, 16, null);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit) {
        Intrinsics.checkNotNullParameter(lowestPermittedRequestLevelOnSubmit, "lowestPermittedRequestLevelOnSubmit");
        return fetchDecodedImage$default(this, imageRequest, callerContext, lowestPermittedRequestLevelOnSubmit, null, null, 16, null);
    }

    public final DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, RequestListener requestListener, String uiComponentId, Map<String, ?> extras) {
        Intrinsics.checkNotNullParameter(lowestPermittedRequestLevelOnSubmit, "lowestPermittedRequestLevelOnSubmit");
        if (imageRequest == null) {
            DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(new NullPointerException());
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(NullPointerException())");
            return dataSourceImmediateFailedDataSource;
        }
        try {
            return submitFetchRequest(this.producerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener, uiComponentId, extras);
        } catch (Exception e) {
            DataSource<CloseableReference<CloseableImage>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…taSource(exception)\n    }");
            return dataSourceImmediateFailedDataSource2;
        }
    }

    public final DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object callerContext) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        return fetchEncodedImage(imageRequest, callerContext, null);
    }

    public final DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        if (imageRequest.getSourceUri() == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        try {
            Producer<CloseableReference<PooledByteBuffer>> encodedImageProducerSequence = this.producerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(null).build();
                Intrinsics.checkNotNullExpressionValue(imageRequest, "fromRequest(imageRequest…sizeOptions(null).build()");
            }
            return submitFetchRequest(encodedImageProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, requestListener, null, null);
        } catch (Exception e) {
            DataSource<CloseableReference<PooledByteBuffer>> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n      DataSources.imme…taSource(exception)\n    }");
            return dataSourceImmediateFailedDataSource;
        }
    }

    public final DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object callerContext) {
        return prefetchToBitmapCache(imageRequest, callerContext, null);
    }

    public final DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        DataSource<Void> dataSourceImmediateFailedDataSource;
        Producer<Void> decodedImagePrefetchProducerSequence;
        Producer<Void> decodedImagePrefetchProducerSequence2;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        boolean zBooleanValue = true;
        if (!FrescoSystrace.isTracing()) {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
                return dataSourceImmediateFailedDataSource2;
            }
            try {
                if (this.config.getExperiments().getPrefetchShortcutEnabled() && isInBitmapMemoryCache(imageRequest)) {
                    DataSource<Void> dataSourceImmediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                    Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateSuccessfulDataSource()");
                    return dataSourceImmediateSuccessfulDataSource;
                }
                if (imageRequest == null) {
                    throw new IllegalStateException("Required value was null.".toString());
                }
                Boolean boolShouldDecodePrefetches = imageRequest.shouldDecodePrefetches();
                if (boolShouldDecodePrefetches != null) {
                    if (boolShouldDecodePrefetches.booleanValue()) {
                        zBooleanValue = false;
                    }
                } else {
                    Boolean bool = this.suppressBitmapPrefetchingSupplier.get();
                    Intrinsics.checkNotNullExpressionValue(bool, "suppressBitmapPrefetchin…                   .get()");
                    zBooleanValue = bool.booleanValue();
                }
                if (zBooleanValue) {
                    decodedImagePrefetchProducerSequence2 = this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest);
                } else {
                    decodedImagePrefetchProducerSequence2 = this.producerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest);
                }
                return submitPrefetchRequest(decodedImagePrefetchProducerSequence2, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, Priority.MEDIUM, requestListener);
            } catch (Exception e) {
                DataSource<Void> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource(e);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource3;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#prefetchToBitmapCache");
        try {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource4 = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource4, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
                return dataSourceImmediateFailedDataSource4;
            }
            try {
            } catch (Exception e2) {
                dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
            }
            if (this.config.getExperiments().getPrefetchShortcutEnabled() && isInBitmapMemoryCache(imageRequest)) {
                DataSource<Void> dataSourceImmediateSuccessfulDataSource2 = DataSources.immediateSuccessfulDataSource();
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource2, "immediateSuccessfulDataSource()");
                return dataSourceImmediateSuccessfulDataSource2;
            }
            if (imageRequest == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            Boolean boolShouldDecodePrefetches2 = imageRequest.shouldDecodePrefetches();
            if (boolShouldDecodePrefetches2 != null) {
                if (boolShouldDecodePrefetches2.booleanValue()) {
                    zBooleanValue = false;
                }
            } else {
                Boolean bool2 = this.suppressBitmapPrefetchingSupplier.get();
                Intrinsics.checkNotNullExpressionValue(bool2, "suppressBitmapPrefetchin…                   .get()");
                zBooleanValue = bool2.booleanValue();
            }
            if (zBooleanValue) {
                decodedImagePrefetchProducerSequence = this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest);
            } else {
                decodedImagePrefetchProducerSequence = this.producerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest);
            }
            dataSourceImmediateFailedDataSource = submitPrefetchRequest(decodedImagePrefetchProducerSequence, imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, Priority.MEDIUM, requestListener);
            return dataSourceImmediateFailedDataSource;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        return prefetchToDiskCache(imageRequest, callerContext, Priority.MEDIUM, requestListener);
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext) {
        return prefetchToDiskCache(imageRequest, callerContext, Priority.MEDIUM, null);
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        return prefetchToDiskCache(imageRequest, callerContext, priority, null);
    }

    public final DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority, RequestListener requestListener) {
        Intrinsics.checkNotNullParameter(priority, "priority");
        if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
            DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
            return dataSourceImmediateFailedDataSource;
        }
        if (imageRequest == null) {
            DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(new NullPointerException("imageRequest is null"));
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n      DataSources.imme…eRequest is null\"))\n    }");
            return dataSourceImmediateFailedDataSource2;
        }
        try {
            return submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
        } catch (Exception e) {
            DataSource<Void> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource(e);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "{\n        DataSources.im…Source(exception)\n      }");
            return dataSourceImmediateFailedDataSource3;
        }
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object callerContext, RequestListener requestListener) {
        return prefetchToEncodedCache(imageRequest, callerContext, Priority.MEDIUM, requestListener);
    }

    public static /* synthetic */ DataSource prefetchToEncodedCache$default(ImagePipeline imagePipeline, ImageRequest imageRequest, Object obj, Priority priority, RequestListener requestListener, int i, Object obj2) {
        if ((i & 4) != 0) {
            priority = Priority.MEDIUM;
        }
        if ((i & 8) != 0) {
            requestListener = null;
        }
        return imagePipeline.prefetchToEncodedCache(imageRequest, obj, priority, requestListener);
    }

    public final DataSource<Void> prefetchToEncodedCache(ImageRequest imageRequest, Object callerContext, Priority priority, RequestListener requestListener) {
        DataSource<Void> dataSourceImmediateFailedDataSource;
        Intrinsics.checkNotNullParameter(priority, "priority");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
                return dataSourceImmediateFailedDataSource2;
            }
            if (imageRequest == null) {
                DataSource<Void> dataSourceImmediateFailedDataSource3 = DataSources.immediateFailedDataSource(NULL_IMAGEREQUEST_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource3, "immediateFailedDataSourc…L_IMAGEREQUEST_EXCEPTION)");
                return dataSourceImmediateFailedDataSource3;
            }
            try {
                if (!this.config.getExperiments().getPrefetchShortcutEnabled() || !isInEncodedMemoryCache(imageRequest)) {
                    return submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
                }
                DataSource<Void> dataSourceImmediateSuccessfulDataSource = DataSources.immediateSuccessfulDataSource();
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource, "immediateSuccessfulDataSource()");
                return dataSourceImmediateSuccessfulDataSource;
            } catch (Exception e) {
                DataSource<Void> dataSourceImmediateFailedDataSource4 = DataSources.immediateFailedDataSource(e);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource4, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource4;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#prefetchToEncodedCache");
        try {
            if (!this.isPrefetchEnabledSupplier.get().booleanValue()) {
                DataSource<Void> dataSourceImmediateFailedDataSource5 = DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource5, "immediateFailedDataSource(PREFETCH_EXCEPTION)");
                return dataSourceImmediateFailedDataSource5;
            }
            if (imageRequest == null) {
                DataSource<Void> dataSourceImmediateFailedDataSource6 = DataSources.immediateFailedDataSource(NULL_IMAGEREQUEST_EXCEPTION);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource6, "immediateFailedDataSourc…L_IMAGEREQUEST_EXCEPTION)");
                return dataSourceImmediateFailedDataSource6;
            }
            try {
            } catch (Exception e2) {
                dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
            }
            if (!this.config.getExperiments().getPrefetchShortcutEnabled() || !isInEncodedMemoryCache(imageRequest)) {
                dataSourceImmediateFailedDataSource = submitPrefetchRequest(this.producerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest.RequestLevel.FULL_FETCH, callerContext, priority, requestListener);
                return dataSourceImmediateFailedDataSource;
            }
            DataSource<Void> dataSourceImmediateSuccessfulDataSource2 = DataSources.immediateSuccessfulDataSource();
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateSuccessfulDataSource2, "immediateSuccessfulDataSource()");
            return dataSourceImmediateSuccessfulDataSource2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final void evictFromMemoryCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        Predicate<CacheKey> predicatePredicateForUri = predicateForUri(uri);
        this.bitmapMemoryCache.removeAll(predicatePredicateForUri);
        this.encodedMemoryCache.removeAll(predicatePredicateForUri);
    }

    public final void evictFromDiskCache(Uri uri) {
        ImageRequest imageRequestFromUri = ImageRequest.fromUri(uri);
        if (imageRequestFromUri == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        evictFromDiskCache(imageRequestFromUri);
    }

    public final void evictFromDiskCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return;
        }
        CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        BufferedDiskCache bufferedDiskCache = this.mainBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        bufferedDiskCache.remove(cacheKey);
        this.smallImageBufferedDiskCache.remove(cacheKey);
    }

    public final void evictFromCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public final void clearMemoryCaches() {
        Predicate<CacheKey> predicate = new Predicate() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda3
            @Override // com.facebook.common.internal.Predicate
            public final boolean apply(Object obj) {
                return ImagePipeline.clearMemoryCaches$lambda$2((CacheKey) obj);
            }
        };
        this.bitmapMemoryCache.removeAll(predicate);
        this.encodedMemoryCache.removeAll(predicate);
    }

    public final void clearDiskCaches() {
        this.mainBufferedDiskCache.clearAll();
        this.smallImageBufferedDiskCache.clearAll();
    }

    public final long getUsedDiskCacheSize() {
        return this.mainBufferedDiskCache.getSize() + this.smallImageBufferedDiskCache.getSize();
    }

    public final void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public final boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.bitmapMemoryCache.contains(predicateForUri(uri));
    }

    public final boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CacheKey cacheKey = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, null);
        MemoryCache<CacheKey, CloseableImage> memoryCache = this.bitmapMemoryCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public final boolean isInEncodedMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.encodedMemoryCache.contains(predicateForUri(uri));
    }

    public final boolean isInEncodedMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        MemoryCache<CacheKey, PooledByteBuffer> memoryCache = this.encodedMemoryCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        CloseableReference<PooledByteBuffer> closeableReference = memoryCache.get(cacheKey);
        try {
            return CloseableReference.isValid(closeableReference);
        } finally {
            CloseableReference.closeSafely(closeableReference);
        }
    }

    public final boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(uri, ImageRequest.CacheChoice.SMALL) || isInDiskCacheSync(uri, ImageRequest.CacheChoice.DEFAULT);
    }

    public final boolean isInDiskCacheSync(Uri uri, ImageRequest.CacheChoice cacheChoice) throws NumberFormatException {
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).setCacheChoice(cacheChoice).build();
        Intrinsics.checkNotNullExpressionValue(imageRequest, "imageRequest");
        return isInDiskCacheSync(imageRequest);
    }

    public final boolean isInDiskCacheSync(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        ImageRequest.CacheChoice cacheChoice = imageRequest.getCacheChoice();
        Intrinsics.checkNotNullExpressionValue(cacheChoice, "imageRequest.cacheChoice");
        int i = WhenMappings.$EnumSwitchMapping$0[cacheChoice.ordinal()];
        if (i == 1) {
            BufferedDiskCache bufferedDiskCache = this.mainBufferedDiskCache;
            Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
            return bufferedDiskCache.diskCheckSync(cacheKey);
        }
        if (i != 2) {
            return false;
        }
        BufferedDiskCache bufferedDiskCache2 = this.smallImageBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        return bufferedDiskCache2.diskCheckSync(cacheKey);
    }

    public final DataSource<Boolean> isInDiskCache(Uri uri) {
        ImageRequest imageRequestFromUri = ImageRequest.fromUri(uri);
        if (imageRequestFromUri != null) {
            return isInDiskCache(imageRequestFromUri);
        }
        throw new IllegalStateException("Required value was null.".toString());
    }

    public final DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey cacheKey = this.cacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        final SimpleDataSource dataSource = SimpleDataSource.create();
        BufferedDiskCache bufferedDiskCache = this.mainBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        bufferedDiskCache.contains(cacheKey).continueWithTask(new Continuation() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda0
            @Override // bolts.Continuation
            public final Object then(Task task) {
                return ImagePipeline.isInDiskCache$lambda$3(this.f$0, cacheKey, task);
            }
        }).continueWith(new Continuation() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda1
            @Override // bolts.Continuation
            public final Object then(Task task) {
                return ImagePipeline.isInDiskCache$lambda$4(dataSource, task);
            }
        });
        Intrinsics.checkNotNullExpressionValue(dataSource, "dataSource");
        return dataSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Task isInDiskCache$lambda$3(ImagePipeline this$0, CacheKey cacheKey, Task task) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!task.isCancelled() && !task.isFaulted()) {
            Object result = task.getResult();
            Intrinsics.checkNotNullExpressionValue(result, "task.result");
            if (((Boolean) result).booleanValue()) {
                return Task.forResult(true);
            }
        }
        BufferedDiskCache bufferedDiskCache = this$0.smallImageBufferedDiskCache;
        Intrinsics.checkNotNullExpressionValue(cacheKey, "cacheKey");
        return bufferedDiskCache.contains(cacheKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Void isInDiskCache$lambda$4(com.facebook.datasource.SimpleDataSource r1, bolts.Task r2) {
        /*
            boolean r0 = r2.isCancelled()
            if (r0 != 0) goto L20
            boolean r0 = r2.isFaulted()
            if (r0 != 0) goto L20
            java.lang.Object r2 = r2.getResult()
            java.lang.String r0 = "task.result"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L20
            r2 = 1
            goto L21
        L20:
            r2 = 0
        L21:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r1.setResult(r2)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.core.ImagePipeline.isInDiskCache$lambda$4(com.facebook.datasource.SimpleDataSource, bolts.Task):java.lang.Void");
    }

    public final CacheKey getCacheKey(ImageRequest imageRequest, Object callerContext) {
        CacheKey bitmapCacheKey;
        CacheKey bitmapCacheKey2;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        CacheKey cacheKey = null;
        if (!FrescoSystrace.isTracing()) {
            if (imageRequest == null) {
                return null;
            }
            if (imageRequest.getPostprocessor() != null) {
                bitmapCacheKey2 = this.cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
            } else {
                bitmapCacheKey2 = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, callerContext);
            }
            return bitmapCacheKey2;
        }
        FrescoSystrace.beginSection("ImagePipeline#getCacheKey");
        if (imageRequest != null) {
            try {
                if (imageRequest.getPostprocessor() != null) {
                    bitmapCacheKey = this.cacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
                } else {
                    bitmapCacheKey = this.cacheKeyFactory.getBitmapCacheKey(imageRequest, callerContext);
                }
                cacheKey = bitmapCacheKey;
            } finally {
                FrescoSystrace.endSection();
            }
        }
        return cacheKey;
    }

    public final CloseableReference<CloseableImage> getCachedImage(CacheKey cacheKey) {
        if (cacheKey == null) {
            return null;
        }
        CloseableReference<CloseableImage> closeableReference = this.bitmapMemoryCache.get(cacheKey);
        if (closeableReference == null || closeableReference.get().getQualityInfo().isOfFullQuality()) {
            return closeableReference;
        }
        closeableReference.close();
        return null;
    }

    public final boolean hasCachedImage(CacheKey cacheKey) {
        if (cacheKey == null) {
            return false;
        }
        return this.bitmapMemoryCache.contains((MemoryCache<CacheKey, CloseableImage>) cacheKey);
    }

    private final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, RequestListener requestListener, String uiComponentId) {
        return submitFetchRequest(producerSequence, imageRequest, lowestPermittedRequestLevelOnSubmit, callerContext, requestListener, uiComponentId, null);
    }

    private final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, RequestListener requestListener, String uiComponentId, Map<String, ?> extras) {
        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
            CallerContextVerifier callerContextVerifier = this.callerContextVerifier;
            if (callerContextVerifier != null) {
                callerContextVerifier.verifyCallerContext(callerContext, false);
            }
            try {
                ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                Intrinsics.checkNotNullExpressionValue(max, "getMax(\n                …ttedRequestLevelOnSubmit)");
                SettableProducerContext settableProducerContext = new SettableProducerContext(imageRequest, generateUniqueFutureId(), uiComponentId, internalRequestListener, callerContext, max, false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.config);
                settableProducerContext.putExtras(extras);
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, internalRequestListener);
                Intrinsics.checkNotNullExpressionValue(dataSourceCreate, "{\n          val lowestPe…questListener2)\n        }");
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource2;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        try {
            InternalRequestListener internalRequestListener2 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
            CallerContextVerifier callerContextVerifier2 = this.callerContextVerifier;
            if (callerContextVerifier2 != null) {
                callerContextVerifier2.verifyCallerContext(callerContext, false);
            }
            try {
                ImageRequest.RequestLevel max2 = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                Intrinsics.checkNotNullExpressionValue(max2, "getMax(\n                …ttedRequestLevelOnSubmit)");
                SettableProducerContext settableProducerContext2 = new SettableProducerContext(imageRequest, generateUniqueFutureId(), uiComponentId, internalRequestListener2, callerContext, max2, false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.config);
                settableProducerContext2.putExtras(extras);
                dataSourceImmediateFailedDataSource = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext2, internalRequestListener2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          val lowestPe…questListener2)\n        }");
            } catch (Exception e2) {
                dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
            }
            return dataSourceImmediateFailedDataSource;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    private final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, RequestListener requestListener, Map<String, ?> extras) {
        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
            CallerContextVerifier callerContextVerifier = this.callerContextVerifier;
            if (callerContextVerifier != null) {
                callerContextVerifier.verifyCallerContext(callerContext, false);
            }
            try {
                ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                Intrinsics.checkNotNullExpressionValue(max, "getMax(\n                …ttedRequestLevelOnSubmit)");
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, new SettableProducerContext(imageRequest, generateUniqueFutureId(), null, internalRequestListener, callerContext, max, false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.config), internalRequestListener);
                Intrinsics.checkNotNullExpressionValue(dataSourceCreate, "{\n          val lowestPe…questListener2)\n        }");
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource2;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        try {
            InternalRequestListener internalRequestListener2 = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
            CallerContextVerifier callerContextVerifier2 = this.callerContextVerifier;
            if (callerContextVerifier2 != null) {
                callerContextVerifier2.verifyCallerContext(callerContext, false);
            }
            try {
                ImageRequest.RequestLevel max2 = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
                Intrinsics.checkNotNullExpressionValue(max2, "getMax(\n                …ttedRequestLevelOnSubmit)");
                dataSourceImmediateFailedDataSource = CloseableProducerToDataSourceAdapter.create(producerSequence, new SettableProducerContext(imageRequest, generateUniqueFutureId(), null, internalRequestListener2, callerContext, max2, false, imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()), imageRequest.getPriority(), this.config), internalRequestListener2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          val lowestPe…questListener2)\n        }");
            } catch (Exception e2) {
                dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
            }
            return dataSourceImmediateFailedDataSource;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, SettableProducerContext settableProducerContext, RequestListener requestListener) {
        DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource;
        Intrinsics.checkNotNullParameter(producerSequence, "producerSequence");
        Intrinsics.checkNotNullParameter(settableProducerContext, "settableProducerContext");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            try {
                DataSource<CloseableReference<T>> dataSourceCreate = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, new InternalRequestListener(requestListener, this.requestListener2));
                Intrinsics.checkNotNullExpressionValue(dataSourceCreate, "{\n          val requestL…questListener2)\n        }");
                return dataSourceCreate;
            } catch (Exception e) {
                DataSource<CloseableReference<T>> dataSourceImmediateFailedDataSource2 = DataSources.immediateFailedDataSource(e);
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource2, "{\n          DataSources.…urce(exception)\n        }");
                return dataSourceImmediateFailedDataSource2;
            }
        }
        FrescoSystrace.beginSection("ImagePipeline#submitFetchRequest");
        try {
            try {
                dataSourceImmediateFailedDataSource = CloseableProducerToDataSourceAdapter.create(producerSequence, settableProducerContext, new InternalRequestListener(requestListener, this.requestListener2));
                Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          val requestL…questListener2)\n        }");
            } finally {
                FrescoSystrace.endSection();
            }
        } catch (Exception e2) {
            dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e2);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n          DataSources.…urce(exception)\n        }");
        }
        return dataSourceImmediateFailedDataSource;
    }

    private final DataSource<Void> submitPrefetchRequest(Producer<Void> producerSequence, ImageRequest imageRequest, ImageRequest.RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, Priority priority, RequestListener requestListener) {
        InternalRequestListener internalRequestListener = new InternalRequestListener(getRequestListenerForRequest(imageRequest, requestListener), this.requestListener2);
        CallerContextVerifier callerContextVerifier = this.callerContextVerifier;
        if (callerContextVerifier != null) {
            callerContextVerifier.verifyCallerContext(callerContext, true);
        }
        try {
            ImageRequest.RequestLevel max = ImageRequest.RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
            Intrinsics.checkNotNullExpressionValue(max, "getMax(\n              im…ttedRequestLevelOnSubmit)");
            String strGenerateUniqueFutureId = generateUniqueFutureId();
            InternalRequestListener internalRequestListener2 = internalRequestListener;
            ImagePipelineExperiments experiments = this.config.getExperiments();
            return ProducerToDataSourceAdapter.INSTANCE.create(producerSequence, new SettableProducerContext(imageRequest, strGenerateUniqueFutureId, internalRequestListener2, callerContext, max, true, experiments != null && experiments.getAllowProgressiveOnPrefetch() && imageRequest.getProgressiveRenderingEnabled(), priority, this.config), internalRequestListener);
        } catch (Exception e) {
            DataSource<Void> dataSourceImmediateFailedDataSource = DataSources.immediateFailedDataSource(e);
            Intrinsics.checkNotNullExpressionValue(dataSourceImmediateFailedDataSource, "{\n      DataSources.imme…taSource(exception)\n    }");
            return dataSourceImmediateFailedDataSource;
        }
    }

    public final RequestListener getRequestListenerForRequest(ImageRequest imageRequest, RequestListener requestListener) {
        ForwardingRequestListener forwardingRequestListener;
        if (imageRequest == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        if (requestListener == null) {
            if (imageRequest.getRequestListener() == null) {
                return this.requestListener;
            }
            return new ForwardingRequestListener(this.requestListener, imageRequest.getRequestListener());
        }
        if (imageRequest.getRequestListener() == null) {
            forwardingRequestListener = new ForwardingRequestListener(this.requestListener, requestListener);
        } else {
            forwardingRequestListener = new ForwardingRequestListener(this.requestListener, requestListener, imageRequest.getRequestListener());
        }
        return forwardingRequestListener;
    }

    public final RequestListener getCombinedRequestListener(RequestListener listener) {
        return listener != null ? new ForwardingRequestListener(this.requestListener, listener) : this.requestListener;
    }

    private final Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate() { // from class: com.facebook.imagepipeline.core.ImagePipeline$$ExternalSyntheticLambda2
            @Override // com.facebook.common.internal.Predicate
            public final boolean apply(Object obj) {
                return ImagePipeline.predicateForUri$lambda$10(uri, (CacheKey) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean predicateForUri$lambda$10(Uri uri, CacheKey key) {
        Intrinsics.checkNotNullParameter(uri, "$uri");
        Intrinsics.checkNotNullParameter(key, "key");
        return key.containsUri(uri);
    }

    public final void pause() {
        this.threadHandoffProducerQueue.startQueueing();
    }

    public final void resume() {
        this.threadHandoffProducerQueue.stopQueuing();
    }

    public final boolean isPaused() {
        return this.threadHandoffProducerQueue.isQueueing();
    }
}
