package com.facebook.fresco.ui.common;

import android.net.Uri;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public interface ControllerListener2<INFO> {
    void onEmptyEvent(@Nullable Object obj);

    void onFailure(String str, @Nullable Throwable th, @Nullable Extras extras);

    void onFinalImageSet(String str, @Nullable INFO info, @Nullable Extras extras);

    void onIntermediateImageFailed(String str);

    void onIntermediateImageSet(String str, @Nullable INFO info);

    void onRelease(String str, @Nullable Extras extras);

    void onSubmit(String str, @Nullable Object obj, @Nullable Extras extras);

    public static class Extras {

        @Nullable
        public Object callerContext;

        @Nullable
        public Map<String, Object> componentExtras;

        @Nullable
        public Map<String, Object> datasourceExtras;

        @Nullable
        public Float focusX;

        @Nullable
        public Float focusY;

        @Nullable
        public Map<String, Object> imageExtras;

        @Nullable
        public Map<String, Object> imageSourceExtras;

        @Nullable
        public Uri mainUri;

        @Nullable
        public Object scaleType;

        @Nullable
        public Map<String, Object> shortcutExtras;
        public int viewportWidth = -1;
        public int viewportHeight = -1;
        public boolean logWithHighSamplingRate = false;

        public static Extras of(@Nullable Map<String, Object> map) {
            Extras extras = new Extras();
            extras.componentExtras = map;
            return extras;
        }

        public Extras makeExtrasCopy() {
            Extras extras = new Extras();
            extras.componentExtras = copyMap(this.componentExtras);
            extras.shortcutExtras = copyMap(this.shortcutExtras);
            extras.datasourceExtras = copyMap(this.datasourceExtras);
            extras.imageExtras = copyMap(this.imageExtras);
            extras.callerContext = this.callerContext;
            extras.mainUri = this.mainUri;
            extras.viewportWidth = this.viewportWidth;
            extras.viewportHeight = this.viewportHeight;
            extras.scaleType = this.scaleType;
            extras.focusX = this.focusX;
            extras.focusY = this.focusY;
            return extras;
        }

        private static Map<String, Object> copyMap(Map<String, Object> map) {
            if (map == null) {
                return null;
            }
            return new ConcurrentHashMap(map);
        }
    }
}
