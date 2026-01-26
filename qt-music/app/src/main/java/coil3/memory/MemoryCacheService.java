package coil3.memory;

import coil3.BitmapImage;
import coil3.EventListener;
import coil3.ImageLoader;
import coil3.decode.DataSource;
import coil3.intercept.EngineInterceptor;
import coil3.intercept.Interceptor;
import coil3.memory.MemoryCache;
import coil3.request.ImageRequest;
import coil3.request.ImageRequestsKt;
import coil3.request.Options;
import coil3.request.RequestService;
import coil3.request.SuccessResult;
import coil3.size.Dimension;
import coil3.size.Precision;
import coil3.size.Scale;
import coil3.size.Size;
import coil3.size.SizeKt;
import coil3.util.Logger;
import coil3.util.UtilsKt;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MemoryCacheService.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ(\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012J(\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J5\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\u001dJ0\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J \u0010\u001f\u001a\u00020\u001b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010 \u001a\u00020!J&\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010&\u001a\u00020\u001b*\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u001a\u0010(\u001a\u0004\u0018\u00010)*\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lcoil3/memory/MemoryCacheService;", "", "imageLoader", "Lcoil3/ImageLoader;", "requestService", "Lcoil3/request/RequestService;", "logger", "Lcoil3/util/Logger;", "<init>", "(Lcoil3/ImageLoader;Lcoil3/request/RequestService;Lcoil3/util/Logger;)V", "newCacheKey", "Lcoil3/memory/MemoryCache$Key;", "request", "Lcoil3/request/ImageRequest;", "mappedData", "options", "Lcoil3/request/Options;", "eventListener", "Lcoil3/EventListener;", "getCacheValue", "Lcoil3/memory/MemoryCache$Value;", "cacheKey", "size", "Lcoil3/size/Size;", "scale", "Lcoil3/size/Scale;", "isCacheValueValid", "", "cacheValue", "isCacheValueValid$coil_core_release", "isCacheValueValidForSize", "setCacheValue", "result", "Lcoil3/intercept/EngineInterceptor$ExecuteResult;", "newResult", "Lcoil3/request/SuccessResult;", "chain", "Lcoil3/intercept/Interceptor$Chain;", "isSampled", "(Lcoil3/memory/MemoryCache$Value;)Z", "diskCacheKey", "", "getDiskCacheKey", "(Lcoil3/memory/MemoryCache$Value;)Ljava/lang/String;", "Companion", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MemoryCacheService {
    public static final String EXTRA_DISK_CACHE_KEY = "coil#disk_cache_key";
    public static final String EXTRA_IS_SAMPLED = "coil#is_sampled";
    public static final String EXTRA_SIZE = "coil#size";
    private static final String TAG = "MemoryCacheService";
    private final ImageLoader imageLoader;
    private final Logger logger;
    private final RequestService requestService;

    /* compiled from: MemoryCacheService.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Scale.values().length];
            try {
                iArr[Scale.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Scale.FIT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Precision.values().length];
            try {
                iArr2[Precision.EXACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[Precision.INEXACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public MemoryCacheService(ImageLoader imageLoader, RequestService requestService, Logger logger) {
        this.imageLoader = imageLoader;
        this.requestService = requestService;
        this.logger = logger;
    }

    public final MemoryCache.Key newCacheKey(ImageRequest request, Object mappedData, Options options, EventListener eventListener) {
        if (request.getMemoryCacheKey() != null) {
            return new MemoryCache.Key(request.getMemoryCacheKey(), request.getMemoryCacheKeyExtras());
        }
        eventListener.keyStart(request, mappedData);
        String strKey = this.imageLoader.getComponents().key(mappedData, options);
        eventListener.keyEnd(request, strKey);
        if (strKey == null) {
            return null;
        }
        Map mutableMap = MapsKt.toMutableMap(request.getMemoryCacheKeyExtras());
        if (MemoryCacheServiceKt.needsSizeInCacheKey(request)) {
            mutableMap.put(EXTRA_SIZE, options.getSize().toString());
        }
        return new MemoryCache.Key(strKey, mutableMap);
    }

    public final MemoryCache.Value getCacheValue(ImageRequest request, MemoryCache.Key cacheKey, Size size, Scale scale) {
        if (!request.getMemoryCachePolicy().getReadEnabled()) {
            return null;
        }
        MemoryCache memoryCache = this.imageLoader.getMemoryCache();
        MemoryCache.Value value = memoryCache != null ? memoryCache.get(cacheKey) : null;
        if (value == null || !isCacheValueValid$coil_core_release(request, cacheKey, value, size, scale)) {
            return null;
        }
        return value;
    }

    public final boolean isCacheValueValid$coil_core_release(ImageRequest request, MemoryCache.Key cacheKey, MemoryCache.Value cacheValue, Size size, Scale scale) {
        if (!this.requestService.isCacheValueValidForHardware(request, cacheValue)) {
            Logger logger = this.logger;
            if (logger == null) {
                return false;
            }
            Logger.Level level = Logger.Level.Debug;
            if (logger.getMinLevel().compareTo(level) > 0) {
                return false;
            }
            logger.log(TAG, level, request.getData() + ": Cached bitmap is hardware-backed, which is incompatible with the request.", null);
            return false;
        }
        return isCacheValueValidForSize(request, cacheKey, cacheValue, size, scale);
    }

    private final boolean isCacheValueValidForSize(ImageRequest request, MemoryCache.Key cacheKey, MemoryCache.Value cacheValue, Size size, Scale scale) {
        Size maxBitmapSize;
        int iAbs;
        int iAbs2;
        String str = cacheKey.getExtras().get(EXTRA_SIZE);
        if (str != null) {
            if (Intrinsics.areEqual(str, size.toString())) {
                return true;
            }
            Logger logger = this.logger;
            if (logger != null) {
                Logger.Level level = Logger.Level.Debug;
                if (logger.getMinLevel().compareTo(level) <= 0) {
                    logger.log(TAG, level, request.getData() + ": Memory cached image's size (" + str + ") does not exactly match the target size (" + size + ").", null);
                }
            }
            return false;
        }
        if (!isSampled(cacheValue) && (SizeKt.isOriginal(size) || request.getPrecision() == Precision.INEXACT)) {
            return true;
        }
        int width = cacheValue.getImage().getWidth();
        int height = cacheValue.getImage().getHeight();
        if (cacheValue.getImage() instanceof BitmapImage) {
            maxBitmapSize = ImageRequestsKt.getMaxBitmapSize(request);
        } else {
            maxBitmapSize = Size.ORIGINAL;
        }
        Dimension width2 = size.getWidth();
        int iM268unboximpl = width2 instanceof Dimension.Pixels ? ((Dimension.Pixels) width2).m268unboximpl() : Integer.MAX_VALUE;
        Dimension width3 = maxBitmapSize.getWidth();
        int iMin = Math.min(iM268unboximpl, width3 instanceof Dimension.Pixels ? ((Dimension.Pixels) width3).m268unboximpl() : Integer.MAX_VALUE);
        Dimension height2 = size.getHeight();
        int iM268unboximpl2 = height2 instanceof Dimension.Pixels ? ((Dimension.Pixels) height2).m268unboximpl() : Integer.MAX_VALUE;
        Dimension height3 = maxBitmapSize.getHeight();
        int iMin2 = Math.min(iM268unboximpl2, height3 instanceof Dimension.Pixels ? ((Dimension.Pixels) height3).m268unboximpl() : Integer.MAX_VALUE);
        double d = iMin / width;
        double d2 = iMin2 / height;
        int i = WhenMappings.$EnumSwitchMapping$0[((iMin == Integer.MAX_VALUE || iMin2 == Integer.MAX_VALUE) ? Scale.FIT : scale).ordinal()];
        if (i != 1) {
            if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            if (d < d2) {
                iAbs2 = Math.abs(iMin - width);
            } else {
                iAbs = Math.abs(iMin2 - height);
                iAbs2 = iAbs;
                d = d2;
            }
        } else if (d > d2) {
            iAbs2 = Math.abs(iMin - width);
        } else {
            iAbs = Math.abs(iMin2 - height);
            iAbs2 = iAbs;
            d = d2;
        }
        if (iAbs2 <= 1) {
            return true;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[request.getPrecision().ordinal()];
        if (i2 == 1) {
            if (d == 1.0d) {
                return true;
            }
            Logger logger2 = this.logger;
            if (logger2 == null) {
                return false;
            }
            Logger.Level level2 = Logger.Level.Debug;
            if (logger2.getMinLevel().compareTo(level2) > 0) {
                return false;
            }
            logger2.log(TAG, level2, request.getData() + ": Memory cached image's size (" + width + ", " + height + ") does not exactly match the target size (" + iMin + ", " + iMin2 + ").", null);
            return false;
        }
        if (i2 != 2) {
            throw new NoWhenBranchMatchedException();
        }
        if (d <= 1.0d) {
            return true;
        }
        Logger logger3 = this.logger;
        if (logger3 == null) {
            return false;
        }
        Logger.Level level3 = Logger.Level.Debug;
        if (logger3.getMinLevel().compareTo(level3) > 0) {
            return false;
        }
        logger3.log(TAG, level3, request.getData() + ": Memory cached image's size (" + width + ", " + height + ") is smaller than the target size (" + iMin + ", " + iMin2 + ").", null);
        return false;
    }

    public final boolean setCacheValue(MemoryCache.Key cacheKey, ImageRequest request, EngineInterceptor.ExecuteResult result) {
        MemoryCache memoryCache;
        if (cacheKey == null || !request.getMemoryCachePolicy().getWriteEnabled() || !result.getImage().getShareable() || (memoryCache = this.imageLoader.getMemoryCache()) == null) {
            return false;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("coil#is_sampled", Boolean.valueOf(result.isSampled()));
        String diskCacheKey = result.getDiskCacheKey();
        if (diskCacheKey != null) {
            linkedHashMap.put("coil#disk_cache_key", diskCacheKey);
        }
        memoryCache.set(cacheKey, new MemoryCache.Value(result.getImage(), linkedHashMap));
        return true;
    }

    public final SuccessResult newResult(Interceptor.Chain chain, ImageRequest request, MemoryCache.Key cacheKey, MemoryCache.Value cacheValue) {
        return new SuccessResult(cacheValue.getImage(), request, DataSource.MEMORY_CACHE, cacheKey, getDiskCacheKey(cacheValue), isSampled(cacheValue), UtilsKt.isPlaceholderCached(chain));
    }

    private final boolean isSampled(MemoryCache.Value value) {
        Object obj = value.getExtras().get("coil#is_sampled");
        Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    private final String getDiskCacheKey(MemoryCache.Value value) {
        Object obj = value.getExtras().get("coil#disk_cache_key");
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }
}
