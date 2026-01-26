package com.facebook.imageformat;

import com.facebook.common.internal.ByteStreams;
import com.facebook.common.internal.Closeables;
import com.facebook.common.internal.Throwables;
import com.facebook.imageformat.ImageFormat;
import com.umeng.analytics.pro.bm;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageFormatChecker.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0016\u0010\u000e\u001a\u00020\u000f2\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J\b\u0010\u0010\u001a\u00020\u000fH\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/imageformat/ImageFormatChecker;", "", "()V", "customImageFormatCheckers", "", "Lcom/facebook/imageformat/ImageFormat$FormatChecker;", "defaultFormatChecker", "Lcom/facebook/imageformat/DefaultImageFormatChecker;", "maxHeaderLength", "", "determineImageFormat", "Lcom/facebook/imageformat/ImageFormat;", bm.ae, "Ljava/io/InputStream;", "setCustomImageFormatCheckers", "", "updateMaxHeaderLength", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ImageFormatChecker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<ImageFormatChecker> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ImageFormatChecker>() { // from class: com.facebook.imageformat.ImageFormatChecker$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageFormatChecker invoke() {
            return new ImageFormatChecker(null);
        }
    });
    private List<? extends ImageFormat.FormatChecker> customImageFormatCheckers;
    private final DefaultImageFormatChecker defaultFormatChecker;
    private int maxHeaderLength;

    public /* synthetic */ ImageFormatChecker(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final ImageFormat getImageFormat(InputStream inputStream) throws IOException {
        return INSTANCE.getImageFormat(inputStream);
    }

    @JvmStatic
    public static final ImageFormat getImageFormat(String str) {
        return INSTANCE.getImageFormat(str);
    }

    @JvmStatic
    public static final ImageFormat getImageFormat_WrapIOException(InputStream inputStream) {
        return INSTANCE.getImageFormat_WrapIOException(inputStream);
    }

    @JvmStatic
    public static final ImageFormatChecker getInstance() {
        return INSTANCE.getInstance();
    }

    private ImageFormatChecker() {
        this.defaultFormatChecker = new DefaultImageFormatChecker();
        updateMaxHeaderLength();
    }

    public final void setCustomImageFormatCheckers(List<? extends ImageFormat.FormatChecker> customImageFormatCheckers) {
        this.customImageFormatCheckers = customImageFormatCheckers;
        updateMaxHeaderLength();
    }

    public final ImageFormat determineImageFormat(InputStream is) throws IOException {
        Intrinsics.checkNotNullParameter(is, "is");
        int i = this.maxHeaderLength;
        byte[] bArr = new byte[i];
        int headerFromStream = INSTANCE.readHeaderFromStream(i, is, bArr);
        ImageFormat imageFormatDetermineFormat = this.defaultFormatChecker.determineFormat(bArr, headerFromStream);
        if (imageFormatDetermineFormat != ImageFormat.UNKNOWN) {
            return imageFormatDetermineFormat;
        }
        List<? extends ImageFormat.FormatChecker> list = this.customImageFormatCheckers;
        if (list != null) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                ImageFormat imageFormatDetermineFormat2 = ((ImageFormat.FormatChecker) it.next()).determineFormat(bArr, headerFromStream);
                if (imageFormatDetermineFormat2 != ImageFormat.UNKNOWN) {
                    return imageFormatDetermineFormat2;
                }
            }
        }
        return ImageFormat.UNKNOWN;
    }

    private final void updateMaxHeaderLength() {
        this.maxHeaderLength = this.defaultFormatChecker.getHeaderSize();
        List<? extends ImageFormat.FormatChecker> list = this.customImageFormatCheckers;
        if (list != null) {
            Intrinsics.checkNotNull(list);
            Iterator<? extends ImageFormat.FormatChecker> it = list.iterator();
            while (it.hasNext()) {
                this.maxHeaderLength = Math.max(this.maxHeaderLength, it.next().getHeaderSize());
            }
        }
    }

    /* compiled from: ImageFormatChecker.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J\u0012\u0010\t\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007J \u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048GX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/facebook/imageformat/ImageFormatChecker$Companion;", "", "()V", "instance", "Lcom/facebook/imageformat/ImageFormatChecker;", "getInstance", "()Lcom/facebook/imageformat/ImageFormatChecker;", "instance$delegate", "Lkotlin/Lazy;", "getImageFormat", "Lcom/facebook/imageformat/ImageFormat;", bm.ae, "Ljava/io/InputStream;", "filename", "", "getImageFormat_WrapIOException", "readHeaderFromStream", "", "maxHeaderLength", "imageHeaderBytes", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int readHeaderFromStream(int maxHeaderLength, InputStream is, byte[] imageHeaderBytes) throws IOException {
            if (imageHeaderBytes.length < maxHeaderLength) {
                throw new IllegalStateException("Check failed.".toString());
            }
            if (is.markSupported()) {
                try {
                    is.mark(maxHeaderLength);
                    return ByteStreams.read(is, imageHeaderBytes, 0, maxHeaderLength);
                } finally {
                    is.reset();
                }
            }
            return ByteStreams.read(is, imageHeaderBytes, 0, maxHeaderLength);
        }

        @JvmStatic
        public final ImageFormatChecker getInstance() {
            return (ImageFormatChecker) ImageFormatChecker.instance$delegate.getValue();
        }

        @JvmStatic
        public final ImageFormat getImageFormat(InputStream is) throws IOException {
            Intrinsics.checkNotNullParameter(is, "is");
            return getInstance().determineImageFormat(is);
        }

        @JvmStatic
        public final ImageFormat getImageFormat_WrapIOException(InputStream is) throws Throwable {
            Intrinsics.checkNotNullParameter(is, "is");
            try {
                return getImageFormat(is);
            } catch (IOException e) {
                RuntimeException runtimeExceptionPropagate = Throwables.propagate(e);
                Intrinsics.checkNotNullExpressionValue(runtimeExceptionPropagate, "propagate(ioe)");
                throw runtimeExceptionPropagate;
            }
        }

        @JvmStatic
        public final ImageFormat getImageFormat(String filename) throws Throwable {
            ImageFormat imageFormat;
            FileInputStream fileInputStream = null;
            try {
                try {
                    FileInputStream fileInputStream2 = new FileInputStream(filename);
                    try {
                        imageFormat = getImageFormat(fileInputStream2);
                        Closeables.closeQuietly(fileInputStream2);
                    } catch (IOException unused) {
                        fileInputStream = fileInputStream2;
                        imageFormat = ImageFormat.UNKNOWN;
                        Closeables.closeQuietly(fileInputStream);
                        return imageFormat;
                    } catch (Throwable th) {
                        th = th;
                        fileInputStream = fileInputStream2;
                        Closeables.closeQuietly(fileInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (IOException unused2) {
            }
            return imageFormat;
        }
    }
}
