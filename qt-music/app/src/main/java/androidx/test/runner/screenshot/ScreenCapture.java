package androidx.test.runner.screenshot;

import android.graphics.Bitmap;
import androidx.test.internal.util.Checks;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class ScreenCapture {
    private static final Bitmap.CompressFormat DEFAULT_FORMAT = Bitmap.CompressFormat.PNG;
    private final Bitmap bitmap;
    private ScreenCaptureProcessor defaultProcessor;
    private String filename;
    private Bitmap.CompressFormat format;
    private Set<ScreenCaptureProcessor> processorSet;

    ScreenCapture(Bitmap bitmap) {
        this.defaultProcessor = new BasicScreenCaptureProcessor();
        this.processorSet = new HashSet();
        this.bitmap = bitmap;
        this.format = DEFAULT_FORMAT;
    }

    ScreenCapture(Bitmap bitmap, ScreenCaptureProcessor defaultProcessor) {
        this.defaultProcessor = new BasicScreenCaptureProcessor();
        this.processorSet = new HashSet();
        this.bitmap = bitmap;
        this.format = DEFAULT_FORMAT;
        this.defaultProcessor = defaultProcessor;
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public String getName() {
        return this.filename;
    }

    public Bitmap.CompressFormat getFormat() {
        return this.format;
    }

    public ScreenCapture setName(String filename) {
        this.filename = filename;
        return this;
    }

    public ScreenCapture setFormat(Bitmap.CompressFormat format) {
        this.format = format;
        return this;
    }

    ScreenCapture setProcessors(Set<ScreenCaptureProcessor> processorSet) {
        this.processorSet = (Set) Checks.checkNotNull(processorSet);
        return this;
    }

    Set<ScreenCaptureProcessor> getProcessors() {
        return this.processorSet;
    }

    public void process() throws IOException {
        process(this.processorSet);
    }

    public void process(Set<ScreenCaptureProcessor> processorSet) throws IOException {
        Checks.checkNotNull(processorSet);
        if (processorSet.isEmpty()) {
            this.defaultProcessor.process(this);
            return;
        }
        Iterator<ScreenCaptureProcessor> it = processorSet.iterator();
        while (it.hasNext()) {
            it.next().process(this);
        }
    }

    public int hashCode() {
        Bitmap bitmap = this.bitmap;
        int iHashCode = bitmap != null ? 37 + bitmap.hashCode() : 1;
        Bitmap.CompressFormat compressFormat = this.format;
        if (compressFormat != null) {
            iHashCode = (iHashCode * 37) + compressFormat.hashCode();
        }
        String str = this.filename;
        if (str != null) {
            iHashCode = (iHashCode * 37) + str.hashCode();
        }
        return !this.processorSet.isEmpty() ? (iHashCode * 37) + this.processorSet.hashCode() : iHashCode;
    }

    public boolean equals(Object obj) {
        boolean zSameAs;
        boolean zEquals;
        boolean zEquals2;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof ScreenCapture)) {
            return false;
        }
        ScreenCapture screenCapture = (ScreenCapture) obj;
        if (this.bitmap == null) {
            zSameAs = screenCapture.getBitmap() == null;
        } else {
            zSameAs = getBitmap().sameAs(screenCapture.getBitmap());
        }
        String str = this.filename;
        if (str == null) {
            zEquals = screenCapture.getName() == null;
        } else {
            zEquals = str.equals(screenCapture.getName());
        }
        Bitmap.CompressFormat compressFormat = this.format;
        if (compressFormat == null) {
            zEquals2 = screenCapture.getFormat() == null;
        } else {
            zEquals2 = compressFormat.equals(screenCapture.getFormat());
        }
        return zSameAs && zEquals && zEquals2 && this.processorSet.containsAll(screenCapture.getProcessors()) && screenCapture.getProcessors().containsAll(this.processorSet);
    }
}
