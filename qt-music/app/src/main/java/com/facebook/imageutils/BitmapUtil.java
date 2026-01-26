package com.facebook.imageutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Pair;
import androidx.core.util.Pools;
import com.facebook.common.memory.DecodeBufferHelper;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BitmapUtil.kt */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001a\u0004\u0018\u00010\tH\u0002J\u001e\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J \u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J \u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007J\u0012\u0010\u001f\u001a\u00020 2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0007J\u0012\u0010!\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0007J\"\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\b\u0010\"\u001a\u0004\u0018\u00010#H\u0007J\u0012\u0010'\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010)H\u0007J\b\u0010*\u001a\u00020\tH\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\tH\u0002J\u0010\u0010.\u001a\u00020,2\u0006\u0010\u0014\u001a\u00020\u0015H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R#\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/facebook/imageutils/BitmapUtil;", "", "()V", "ALPHA_8_BYTES_PER_PIXEL", "", "ARGB_4444_BYTES_PER_PIXEL", "ARGB_8888_BYTES_PER_PIXEL", "DECODE_BUFFERS", "Landroidx/core/util/Pools$SynchronizedPool;", "Ljava/nio/ByteBuffer;", "getDECODE_BUFFERS", "()Landroidx/core/util/Pools$SynchronizedPool;", "DECODE_BUFFERS$delegate", "Lkotlin/Lazy;", "MAX_BITMAP_SIZE", "", "POOL_SIZE", "RGBA_1010102_BYTES_PER_PIXEL", "RGBA_F16_BYTES_PER_PIXEL", "RGB_565_BYTES_PER_PIXEL", "useDecodeBufferHelper", "", "acquireByteBuffer", "decodeDimensions", "Landroid/util/Pair;", "uri", "Landroid/net/Uri;", "inputStream", "Ljava/io/InputStream;", "bytes", "", "decodeDimensionsAndColorSpace", "Lcom/facebook/imageutils/ImageMetaData;", "getPixelSizeForBitmapConfig", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "getSizeInByteForBitmap", "width", "height", "getSizeInBytes", "bitmap", "Landroid/graphics/Bitmap;", "obtainByteBuffer", "releaseByteBuffer", "", "byteBuffer", "setUseDecodeBufferHelper", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BitmapUtil {
    public static final int ALPHA_8_BYTES_PER_PIXEL = 1;
    public static final int ARGB_4444_BYTES_PER_PIXEL = 2;
    public static final int ARGB_8888_BYTES_PER_PIXEL = 4;
    public static final float MAX_BITMAP_SIZE = 2048.0f;
    private static final int POOL_SIZE = 12;
    public static final int RGBA_1010102_BYTES_PER_PIXEL = 4;
    public static final int RGBA_F16_BYTES_PER_PIXEL = 8;
    public static final int RGB_565_BYTES_PER_PIXEL = 2;
    private static boolean useDecodeBufferHelper;
    public static final BitmapUtil INSTANCE = new BitmapUtil();

    /* renamed from: DECODE_BUFFERS$delegate, reason: from kotlin metadata */
    private static final Lazy DECODE_BUFFERS = LazyKt.lazy(new Function0<Pools.SynchronizedPool<ByteBuffer>>() { // from class: com.facebook.imageutils.BitmapUtil$DECODE_BUFFERS$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Pools.SynchronizedPool<ByteBuffer> invoke() {
            return new Pools.SynchronizedPool<>(12);
        }
    });

    /* compiled from: BitmapUtil.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Bitmap.Config.values().length];
            try {
                iArr[Bitmap.Config.ARGB_8888.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Bitmap.Config.ALPHA_8.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Bitmap.Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Bitmap.Config.RGB_565.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Bitmap.Config.RGBA_F16.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Bitmap.Config.RGBA_1010102.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Bitmap.Config.HARDWARE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private BitmapUtil() {
    }

    private final Pools.SynchronizedPool<ByteBuffer> getDECODE_BUFFERS() {
        return (Pools.SynchronizedPool) DECODE_BUFFERS.getValue();
    }

    @JvmStatic
    public static final int getSizeInBytes(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        try {
            return bitmap.getAllocationByteCount();
        } catch (NullPointerException unused) {
            return bitmap.getByteCount();
        }
    }

    @JvmStatic
    public static final Pair<Integer, Integer> decodeDimensions(byte[] bytes) {
        return decodeDimensions(new ByteArrayInputStream(bytes));
    }

    @JvmStatic
    public static final Pair<Integer, Integer> decodeDimensions(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(uri.getPath(), options);
        if (options.outWidth == -1 || options.outHeight == -1) {
            return null;
        }
        return new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
    }

    @JvmStatic
    public static final Pair<Integer, Integer> decodeDimensions(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        BitmapUtil bitmapUtil = INSTANCE;
        ByteBuffer byteBufferObtainByteBuffer = bitmapUtil.obtainByteBuffer();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = byteBufferObtainByteBuffer.array();
            Pair<Integer, Integer> pair = null;
            BitmapFactory.decodeStream(inputStream, null, options);
            if (options.outWidth != -1 && options.outHeight != -1) {
                pair = new Pair<>(Integer.valueOf(options.outWidth), Integer.valueOf(options.outHeight));
            }
            bitmapUtil.releaseByteBuffer(byteBufferObtainByteBuffer);
            return pair;
        } catch (Throwable th) {
            INSTANCE.releaseByteBuffer(byteBufferObtainByteBuffer);
            throw th;
        }
    }

    @JvmStatic
    public static final ImageMetaData decodeDimensionsAndColorSpace(InputStream inputStream) {
        if (inputStream == null) {
            throw new IllegalStateException("Required value was null.".toString());
        }
        BitmapUtil bitmapUtil = INSTANCE;
        ByteBuffer byteBufferObtainByteBuffer = bitmapUtil.obtainByteBuffer();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        try {
            options.inTempStorage = byteBufferObtainByteBuffer.array();
            BitmapFactory.decodeStream(inputStream, null, options);
            ImageMetaData imageMetaData = new ImageMetaData(options.outWidth, options.outHeight, Build.VERSION.SDK_INT >= 26 ? options.outColorSpace : null);
            bitmapUtil.releaseByteBuffer(byteBufferObtainByteBuffer);
            return imageMetaData;
        } catch (Throwable th) {
            INSTANCE.releaseByteBuffer(byteBufferObtainByteBuffer);
            throw th;
        }
    }

    @JvmStatic
    public static final int getPixelSizeForBitmapConfig(Bitmap.Config bitmapConfig) {
        switch (bitmapConfig == null ? -1 : WhenMappings.$EnumSwitchMapping$0[bitmapConfig.ordinal()]) {
            case 1:
            case 6:
            case 7:
                return 4;
            case 2:
                return 1;
            case 3:
            case 4:
                return 2;
            case 5:
                return 8;
            default:
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
        }
    }

    @JvmStatic
    public static final int getSizeInByteForBitmap(int width, int height, Bitmap.Config bitmapConfig) {
        if (width <= 0) {
            throw new IllegalArgumentException(("width must be > 0, width is: " + width).toString());
        }
        if (height <= 0) {
            throw new IllegalArgumentException(("height must be > 0, height is: " + height).toString());
        }
        int pixelSizeForBitmapConfig = getPixelSizeForBitmapConfig(bitmapConfig);
        int i = width * height * pixelSizeForBitmapConfig;
        if (i > 0) {
            return i;
        }
        throw new IllegalStateException(("size must be > 0: size: " + i + ", width: " + width + ", height: " + height + ", pixelSize: " + pixelSizeForBitmapConfig).toString());
    }

    private final ByteBuffer acquireByteBuffer() {
        if (useDecodeBufferHelper) {
            return DecodeBufferHelper.INSTANCE.acquire();
        }
        return getDECODE_BUFFERS().acquire();
    }

    private final void releaseByteBuffer(ByteBuffer byteBuffer) {
        if (useDecodeBufferHelper) {
            return;
        }
        getDECODE_BUFFERS().release(byteBuffer);
    }

    @JvmStatic
    public static final void setUseDecodeBufferHelper(boolean useDecodeBufferHelper2) {
        useDecodeBufferHelper = useDecodeBufferHelper2;
    }

    private final ByteBuffer obtainByteBuffer() {
        ByteBuffer byteBufferAcquireByteBuffer = acquireByteBuffer();
        if (byteBufferAcquireByteBuffer != null) {
            return byteBufferAcquireByteBuffer;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(DecodeBufferHelper.getRecommendedDecodeBufferSize());
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(DecodeBufferHel…mendedDecodeBufferSize())");
        return byteBufferAllocate;
    }
}
