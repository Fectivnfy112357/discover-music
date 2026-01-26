package com.facebook.imagepipeline.transcoder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.os.Build;
import com.facebook.common.logging.FLog;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: SimpleImageTranscoder.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J$\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\"\u0010\u0015\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002JO\u0010\u0016\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u001a\u001a\u0004\u0018\u00010\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016¢\u0006\u0002\u0010\u001eR\u0014\u0010\u0007\u001a\u00020\bX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/facebook/imagepipeline/transcoder/SimpleImageTranscoder;", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoder;", "resizingEnabled", "", "maxBitmapSize", "", "(ZI)V", "identifier", "", "getIdentifier", "()Ljava/lang/String;", "canResize", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "rotationOptions", "Lcom/facebook/imagepipeline/common/RotationOptions;", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "canTranscode", "imageFormat", "Lcom/facebook/imageformat/ImageFormat;", "getSampleSize", "transcode", "Lcom/facebook/imagepipeline/transcoder/ImageTranscodeResult;", "outputStream", "Ljava/io/OutputStream;", "outputFormat", "quality", "colorSpace", "Landroid/graphics/ColorSpace;", "(Lcom/facebook/imagepipeline/image/EncodedImage;Ljava/io/OutputStream;Lcom/facebook/imagepipeline/common/RotationOptions;Lcom/facebook/imagepipeline/common/ResizeOptions;Lcom/facebook/imageformat/ImageFormat;Ljava/lang/Integer;Landroid/graphics/ColorSpace;)Lcom/facebook/imagepipeline/transcoder/ImageTranscodeResult;", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SimpleImageTranscoder implements ImageTranscoder {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "SimpleImageTranscoder";
    private final String identifier = TAG;
    private final int maxBitmapSize;
    private final boolean resizingEnabled;

    public SimpleImageTranscoder(boolean z, int i) {
        this.resizingEnabled = z;
        this.maxBitmapSize = i;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public ImageTranscodeResult transcode(EncodedImage encodedImage, OutputStream outputStream, RotationOptions rotationOptions, ResizeOptions resizeOptions, ImageFormat outputFormat, Integer quality, ColorSpace colorSpace) throws Throwable {
        SimpleImageTranscoder simpleImageTranscoder;
        RotationOptions rotationOptionsAutoRotate;
        Bitmap bitmap;
        ImageTranscodeResult imageTranscodeResult;
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        Intrinsics.checkNotNullParameter(outputStream, "outputStream");
        Integer num = quality == null ? 85 : quality;
        if (rotationOptions == null) {
            rotationOptionsAutoRotate = RotationOptions.INSTANCE.autoRotate();
            simpleImageTranscoder = this;
        } else {
            simpleImageTranscoder = this;
            rotationOptionsAutoRotate = rotationOptions;
        }
        int sampleSize = simpleImageTranscoder.getSampleSize(encodedImage, rotationOptionsAutoRotate, resizeOptions);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize;
        if (colorSpace != null && Build.VERSION.SDK_INT >= 26) {
            options.inPreferredColorSpace = colorSpace;
        }
        try {
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(encodedImage.getInputStream(), null, options);
            if (bitmapDecodeStream == null) {
                FLog.e(TAG, "Couldn't decode the EncodedImage InputStream ! ");
                return new ImageTranscodeResult(2);
            }
            Matrix transformationMatrix = JpegTranscoderUtils.getTransformationMatrix(encodedImage, rotationOptionsAutoRotate);
            if (transformationMatrix != null) {
                try {
                    Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapDecodeStream, 0, 0, bitmapDecodeStream.getWidth(), bitmapDecodeStream.getHeight(), transformationMatrix, false);
                    Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …x,\n                false)");
                    bitmap = bitmapCreateBitmap;
                } catch (OutOfMemoryError e) {
                    e = e;
                    bitmap = bitmapDecodeStream;
                    FLog.e(TAG, "Out-Of-Memory during transcode", e);
                    imageTranscodeResult = new ImageTranscodeResult(2);
                    bitmap.recycle();
                    bitmapDecodeStream.recycle();
                    return imageTranscodeResult;
                } catch (Throwable th) {
                    th = th;
                    bitmap = bitmapDecodeStream;
                    bitmap.recycle();
                    bitmapDecodeStream.recycle();
                    throw th;
                }
            } else {
                bitmap = bitmapDecodeStream;
            }
            try {
                try {
                    bitmap.compress(INSTANCE.getOutputFormat(outputFormat), num.intValue(), outputStream);
                    imageTranscodeResult = new ImageTranscodeResult(sampleSize > 1 ? 0 : 1);
                } catch (Throwable th2) {
                    th = th2;
                    bitmap.recycle();
                    bitmapDecodeStream.recycle();
                    throw th;
                }
            } catch (OutOfMemoryError e2) {
                e = e2;
                FLog.e(TAG, "Out-Of-Memory during transcode", e);
                imageTranscodeResult = new ImageTranscodeResult(2);
                bitmap.recycle();
                bitmapDecodeStream.recycle();
                return imageTranscodeResult;
            }
            bitmap.recycle();
            bitmapDecodeStream.recycle();
            return imageTranscodeResult;
        } catch (OutOfMemoryError e3) {
            FLog.e(TAG, "Out-Of-Memory during transcode", e3);
            return new ImageTranscodeResult(2);
        }
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canResize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
        if (rotationOptions == null) {
            rotationOptions = RotationOptions.INSTANCE.autoRotate();
        }
        return this.resizingEnabled && DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.maxBitmapSize) > 1;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public boolean canTranscode(ImageFormat imageFormat) {
        Intrinsics.checkNotNullParameter(imageFormat, "imageFormat");
        return imageFormat == DefaultImageFormats.HEIF || imageFormat == DefaultImageFormats.JPEG;
    }

    @Override // com.facebook.imagepipeline.transcoder.ImageTranscoder
    public String getIdentifier() {
        return this.identifier;
    }

    private final int getSampleSize(EncodedImage encodedImage, RotationOptions rotationOptions, ResizeOptions resizeOptions) {
        if (this.resizingEnabled) {
            return DownsampleUtil.determineSampleSize(rotationOptions, resizeOptions, encodedImage, this.maxBitmapSize);
        }
        return 1;
    }

    /* compiled from: SimpleImageTranscoder.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/facebook/imagepipeline/transcoder/SimpleImageTranscoder$Companion;", "", "()V", AbstractID3v1Tag.TAG, "", "getOutputFormat", "Landroid/graphics/Bitmap$CompressFormat;", "format", "Lcom/facebook/imageformat/ImageFormat;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Bitmap.CompressFormat getOutputFormat(ImageFormat format) {
            if (format == null) {
                return Bitmap.CompressFormat.JPEG;
            }
            if (format == DefaultImageFormats.JPEG) {
                return Bitmap.CompressFormat.JPEG;
            }
            if (format == DefaultImageFormats.PNG) {
                return Bitmap.CompressFormat.PNG;
            }
            if (DefaultImageFormats.isStaticWebpFormat(format)) {
                return Bitmap.CompressFormat.WEBP;
            }
            return Bitmap.CompressFormat.JPEG;
        }
    }
}
