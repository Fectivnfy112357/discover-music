package com.facebook.imagepipeline.bitmaps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.BitmapCreator;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import com.facebook.imagepipeline.memory.PoolFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HoneycombBitmapCreator.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator;", "Lcom/facebook/common/webp/BitmapCreator;", "poolFactory", "Lcom/facebook/imagepipeline/memory/PoolFactory;", "(Lcom/facebook/imagepipeline/memory/PoolFactory;)V", "flexByteArrayPool", "Lcom/facebook/imagepipeline/memory/FlexByteArrayPool;", "jpegGenerator", "Lcom/facebook/imagepipeline/bitmaps/EmptyJpegGenerator;", "createNakedBitmap", "Landroid/graphics/Bitmap;", "width", "", "height", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class HoneycombBitmapCreator implements BitmapCreator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FlexByteArrayPool flexByteArrayPool;
    private final EmptyJpegGenerator jpegGenerator;

    public HoneycombBitmapCreator(PoolFactory poolFactory) {
        Intrinsics.checkNotNullParameter(poolFactory, "poolFactory");
        this.jpegGenerator = new EmptyJpegGenerator(poolFactory.getPooledByteBufferFactory());
        FlexByteArrayPool flexByteArrayPool = poolFactory.getFlexByteArrayPool();
        Intrinsics.checkNotNullExpressionValue(flexByteArrayPool, "poolFactory.flexByteArrayPool");
        this.flexByteArrayPool = flexByteArrayPool;
    }

    @Override // com.facebook.common.webp.BitmapCreator
    public Bitmap createNakedBitmap(int width, int height, Bitmap.Config bitmapConfig) throws Throwable {
        EncodedImage encodedImage;
        Intrinsics.checkNotNullParameter(bitmapConfig, "bitmapConfig");
        CloseableReference<PooledByteBuffer> closeableReferenceGenerate = this.jpegGenerator.generate((short) width, (short) height);
        Intrinsics.checkNotNullExpressionValue(closeableReferenceGenerate, "jpegGenerator.generate(w…hort(), height.toShort())");
        try {
            encodedImage = new EncodedImage(closeableReferenceGenerate);
        } catch (Throwable th) {
            th = th;
            encodedImage = null;
        }
        try {
            encodedImage.setImageFormat(DefaultImageFormats.JPEG);
            BitmapFactory.Options bitmapFactoryOptions = INSTANCE.getBitmapFactoryOptions(encodedImage.getSampleSize(), bitmapConfig);
            int size = closeableReferenceGenerate.get().size();
            PooledByteBuffer pooledByteBuffer = closeableReferenceGenerate.get();
            Intrinsics.checkNotNullExpressionValue(pooledByteBuffer, "jpgRef.get()");
            CloseableReference<byte[]> closeableReference = this.flexByteArrayPool.get(size + 2);
            byte[] bArr = closeableReference.get();
            Intrinsics.checkNotNullExpressionValue(bArr, "encodedBytesArrayRef.get()");
            byte[] bArr2 = bArr;
            pooledByteBuffer.read(0, bArr2, 0, size);
            Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr2, 0, size, bitmapFactoryOptions);
            if (bitmapDecodeByteArray == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            bitmapDecodeByteArray.setHasAlpha(true);
            bitmapDecodeByteArray.eraseColor(0);
            CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(closeableReferenceGenerate);
            return bitmapDecodeByteArray;
        } catch (Throwable th2) {
            th = th2;
            CloseableReference.closeSafely((CloseableReference<?>) null);
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(closeableReferenceGenerate);
            throw th;
        }
    }

    /* compiled from: HoneycombBitmapCreator.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/bitmaps/HoneycombBitmapCreator$Companion;", "", "()V", "getBitmapFactoryOptions", "Landroid/graphics/BitmapFactory$Options;", "sampleSize", "", "bitmapConfig", "Landroid/graphics/Bitmap$Config;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final BitmapFactory.Options getBitmapFactoryOptions(int sampleSize, Bitmap.Config bitmapConfig) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inDither = true;
            options.inPreferredConfig = bitmapConfig;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inSampleSize = sampleSize;
            options.inMutable = true;
            return options;
        }
    }
}
