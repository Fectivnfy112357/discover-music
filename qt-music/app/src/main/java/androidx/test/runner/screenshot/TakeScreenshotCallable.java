package androidx.test.runner.screenshot;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
final class TakeScreenshotCallable implements Callable<Bitmap> {
    private static final String TAG = "TakeScreenshotCallable";
    private WeakReference<View> viewRef;

    static class Factory {
        Factory() {
        }

        Callable<Bitmap> create(View view) {
            return new TakeScreenshotCallable(view);
        }
    }

    private TakeScreenshotCallable(View view) {
        this.viewRef = new WeakReference<>(view);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public Bitmap call() {
        this.viewRef.get().setDrawingCacheEnabled(true);
        try {
            try {
                return Bitmap.createBitmap(this.viewRef.get().getDrawingCache());
            } catch (OutOfMemoryError e) {
                Log.e(TAG, "Out of memory exception while trying to take a screenshot.", e);
                this.viewRef.get().setDrawingCacheEnabled(false);
                return null;
            }
        } finally {
            this.viewRef.get().setDrawingCacheEnabled(false);
        }
    }
}
