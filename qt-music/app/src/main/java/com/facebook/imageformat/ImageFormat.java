package com.facebook.imageformat;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageFormat.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00122\u00020\u0001:\u0002\u0012\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\b\u0010\u0011\u001a\u00020\u0003H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/facebook/imageformat/ImageFormat;", "", "name", "", "fileExtension", "(Ljava/lang/String;Ljava/lang/String;)V", "getFileExtension", "()Ljava/lang/String;", "getName", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "FormatChecker", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class ImageFormat {
    public static final ImageFormat UNKNOWN = new ImageFormat("UNKNOWN", null);
    private final String fileExtension;
    private final String name;

    /* compiled from: ImageFormat.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0002\u001a\u00020\u0003H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lcom/facebook/imageformat/ImageFormat$FormatChecker;", "", "headerSize", "", "getHeaderSize", "()I", "determineFormat", "Lcom/facebook/imageformat/ImageFormat;", "headerBytes", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface FormatChecker {
        ImageFormat determineFormat(byte[] headerBytes, int headerSize);

        int getHeaderSize();
    }

    public static /* synthetic */ ImageFormat copy$default(ImageFormat imageFormat, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = imageFormat.name;
        }
        if ((i & 2) != 0) {
            str2 = imageFormat.fileExtension;
        }
        return imageFormat.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFileExtension() {
        return this.fileExtension;
    }

    public final ImageFormat copy(String name, String fileExtension) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new ImageFormat(name, fileExtension);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ImageFormat)) {
            return false;
        }
        ImageFormat imageFormat = (ImageFormat) other;
        return Intrinsics.areEqual(this.name, imageFormat.name) && Intrinsics.areEqual(this.fileExtension, imageFormat.fileExtension);
    }

    public int hashCode() {
        int iHashCode = this.name.hashCode() * 31;
        String str = this.fileExtension;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public ImageFormat(String name, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.fileExtension = str;
    }

    public final String getName() {
        return this.name;
    }

    public final String getFileExtension() {
        return this.fileExtension;
    }

    public String toString() {
        return this.name;
    }
}
