package com.facebook.drawee.backends.pipeline.info.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.drawable.VisibilityCallback;
import com.facebook.fresco.ui.common.BaseControllerListener2;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.DimensionsInfo;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfNotifierHolder;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.OnDrawControllerListener;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.image.ImageInfo;
import java.io.Closeable;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfControllerListener2 extends BaseControllerListener2<ImageInfo> implements ImagePerfNotifierHolder, OnDrawControllerListener<ImageInfo>, Closeable, VisibilityCallback {
    private static final int WHAT_STATUS = 1;
    private static final int WHAT_VISIBILITY = 2;

    @Nullable
    private static LogHandler sHandler;
    private final Supplier<Boolean> mAsyncLogging;
    private final MonotonicClock mClock;
    private final ImagePerfNotifier mImagePerfNotifier;
    private final ImagePerfState mImagePerfState;

    @Nullable
    private ImagePerfNotifier mLocalImagePerfNotifier;
    private final boolean mReportVisibleOnSubmitAndRelease;

    @Override // com.facebook.drawee.drawable.VisibilityCallback
    public void onDraw() {
    }

    static class LogHandler extends Handler implements ImagePerfNotifierHolder {

        @Nullable
        private ImagePerfNotifier mLocalNotifier;
        private final ImagePerfNotifier mNotifier;

        public LogHandler(Looper looper, ImagePerfNotifier imagePerfNotifier, @Nullable ImagePerfNotifier imagePerfNotifier2) {
            super(looper);
            this.mNotifier = imagePerfNotifier;
            this.mLocalNotifier = imagePerfNotifier2;
        }

        @Override // com.facebook.fresco.ui.common.ImagePerfNotifierHolder
        public void setImagePerfNotifier(@Nullable ImagePerfNotifier imagePerfNotifier) {
            this.mLocalNotifier = imagePerfNotifier;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ImagePerfState imagePerfState = (ImagePerfState) Preconditions.checkNotNull(message.obj);
            ImagePerfNotifier imagePerfNotifier = this.mLocalNotifier;
            int i = message.what;
            if (i == 1) {
                ImageLoadStatus imageLoadStatusFromInt = ImageLoadStatus.INSTANCE.fromInt(message.arg1);
                if (imageLoadStatusFromInt == null) {
                    throw new IllegalArgumentException("Invalid ImageLoadStatus value: " + message.arg1);
                }
                this.mNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatusFromInt);
                if (imagePerfNotifier != null) {
                    imagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatusFromInt);
                    return;
                }
                return;
            }
            if (i != 2) {
                return;
            }
            VisibilityState visibilityStateFromInt = VisibilityState.INSTANCE.fromInt(message.arg1);
            if (visibilityStateFromInt == null) {
                throw new IllegalArgumentException("Invalid VisibilityState value: " + message.arg1);
            }
            this.mNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, visibilityStateFromInt);
            if (imagePerfNotifier != null) {
                imagePerfNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, visibilityStateFromInt);
            }
        }
    }

    public ImagePerfControllerListener2(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfNotifier imagePerfNotifier, Supplier<Boolean> supplier) {
        this(monotonicClock, imagePerfState, imagePerfNotifier, supplier, true);
    }

    public ImagePerfControllerListener2(MonotonicClock monotonicClock, ImagePerfState imagePerfState, ImagePerfNotifier imagePerfNotifier, Supplier<Boolean> supplier, boolean z) {
        this.mLocalImagePerfNotifier = null;
        this.mClock = monotonicClock;
        this.mImagePerfState = imagePerfState;
        this.mImagePerfNotifier = imagePerfNotifier;
        this.mAsyncLogging = supplier;
        this.mReportVisibleOnSubmitAndRelease = z;
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifierHolder
    public void setImagePerfNotifier(@Nullable ImagePerfNotifier imagePerfNotifier) {
        this.mLocalImagePerfNotifier = imagePerfNotifier;
        LogHandler logHandler = sHandler;
        if (logHandler != null) {
            logHandler.setImagePerfNotifier(imagePerfNotifier);
        }
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onSubmit(String str, @Nullable Object obj, @Nullable ControllerListener2.Extras extras) {
        long jNow = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.resetPointsTimestamps();
        imagePerfState.setControllerSubmitTimeMs(jNow);
        imagePerfState.setControllerId(str);
        imagePerfState.setCallerContext(obj);
        imagePerfState.setExtraData(extras);
        updateStatus(imagePerfState, ImageLoadStatus.REQUESTED);
        if (this.mReportVisibleOnSubmitAndRelease) {
            reportViewVisible(imagePerfState, jNow);
        }
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onIntermediateImageSet(String str, @Nullable ImageInfo imageInfo) {
        long jNow = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setControllerIntermediateImageSetTimeMs(jNow);
        imagePerfState.setControllerId(str);
        imagePerfState.setImageInfo(imageInfo);
        updateStatus(imagePerfState, ImageLoadStatus.INTERMEDIATE_AVAILABLE);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onFinalImageSet(String str, @Nullable ImageInfo imageInfo, @Nullable ControllerListener2.Extras extras) {
        long jNow = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerFinalImageSetTimeMs(jNow);
        imagePerfState.setImageRequestEndTimeMs(jNow);
        imagePerfState.setControllerId(str);
        imagePerfState.setImageInfo(imageInfo);
        updateStatus(imagePerfState, ImageLoadStatus.SUCCESS);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onFailure(String str, @Nullable Throwable th, @Nullable ControllerListener2.Extras extras) {
        long jNow = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerFailureTimeMs(jNow);
        imagePerfState.setControllerId(str);
        imagePerfState.setErrorThrowable(th);
        updateStatus(imagePerfState, ImageLoadStatus.ERROR);
        reportViewInvisible(imagePerfState, jNow);
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onRelease(String str, @Nullable ControllerListener2.Extras extras) {
        long jNow = this.mClock.now();
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setExtraData(extras);
        imagePerfState.setControllerId(str);
        ImageLoadStatus imageLoadStatus = imagePerfState.getImageLoadStatus();
        if (imageLoadStatus != ImageLoadStatus.SUCCESS && imageLoadStatus != ImageLoadStatus.ERROR && imageLoadStatus != ImageLoadStatus.DRAW) {
            imagePerfState.setControllerCancelTimeMs(jNow);
            updateStatus(imagePerfState, ImageLoadStatus.CANCELED);
        }
        updateStatus(imagePerfState, ImageLoadStatus.RELEASED);
        if (this.mReportVisibleOnSubmitAndRelease) {
            reportViewInvisible(imagePerfState, jNow);
        }
    }

    @Override // com.facebook.fresco.ui.common.OnDrawControllerListener
    public void onImageDrawn(String str, ImageInfo imageInfo, DimensionsInfo dimensionsInfo) {
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setControllerId(str);
        imagePerfState.setImageDrawTimeMs(this.mClock.now());
        imagePerfState.setDimensionsInfo(dimensionsInfo);
        updateStatus(imagePerfState, ImageLoadStatus.DRAW);
    }

    public void reportViewVisible(ImagePerfState imagePerfState, long j) {
        imagePerfState.setVisible(true);
        imagePerfState.setVisibilityEventTimeMs(j);
        updateVisibility(imagePerfState, VisibilityState.VISIBLE);
    }

    public void resetState() {
        this.mImagePerfState.reset();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        resetState();
    }

    private void reportViewInvisible(ImagePerfState imagePerfState, long j) {
        imagePerfState.setVisible(false);
        imagePerfState.setInvisibilityEventTimeMs(j);
        updateVisibility(imagePerfState, VisibilityState.INVISIBLE);
    }

    private void updateStatus(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        imagePerfState.setImageLoadStatus(imageLoadStatus);
        if (shouldDispatchAsync()) {
            Message messageObtainMessage = ((LogHandler) Preconditions.checkNotNull(sHandler)).obtainMessage();
            messageObtainMessage.what = 1;
            messageObtainMessage.arg1 = imageLoadStatus.getValue();
            messageObtainMessage.obj = imagePerfState;
            sHandler.sendMessage(messageObtainMessage);
            return;
        }
        this.mImagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatus);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyStatusUpdated(imagePerfState, imageLoadStatus);
        }
    }

    private void updateVisibility(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        if (shouldDispatchAsync()) {
            Message messageObtainMessage = ((LogHandler) Preconditions.checkNotNull(sHandler)).obtainMessage();
            messageObtainMessage.what = 2;
            messageObtainMessage.arg1 = visibilityState.getValue();
            messageObtainMessage.obj = imagePerfState;
            sHandler.sendMessage(messageObtainMessage);
            return;
        }
        this.mImagePerfNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, visibilityState);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyListenersOfVisibilityStateUpdate(imagePerfState, visibilityState);
        }
    }

    private synchronized void initHandler() {
        if (sHandler != null) {
            return;
        }
        HandlerThread handlerThread = new HandlerThread("ImagePerfControllerListener2Thread");
        handlerThread.start();
        sHandler = new LogHandler((Looper) Preconditions.checkNotNull(handlerThread.getLooper()), this.mImagePerfNotifier, this.mLocalImagePerfNotifier);
    }

    private boolean shouldDispatchAsync() {
        boolean zBooleanValue = this.mAsyncLogging.get().booleanValue();
        if (zBooleanValue && sHandler == null) {
            initHandler();
        }
        return zBooleanValue;
    }

    @Override // com.facebook.fresco.ui.common.BaseControllerListener2, com.facebook.fresco.ui.common.ControllerListener2
    public void onEmptyEvent(Object obj) {
        ImagePerfState imagePerfState = this.mImagePerfState;
        imagePerfState.setImageLoadStatus(ImageLoadStatus.EMPTY_EVENT);
        this.mImagePerfNotifier.notifyStatusUpdated(imagePerfState, ImageLoadStatus.EMPTY_EVENT);
        ImagePerfNotifier imagePerfNotifier = this.mLocalImagePerfNotifier;
        if (imagePerfNotifier != null) {
            imagePerfNotifier.notifyStatusUpdated(imagePerfState, ImageLoadStatus.EMPTY_EVENT);
        }
    }

    @Override // com.facebook.drawee.drawable.VisibilityCallback
    public void onVisibilityChange(boolean z) {
        if (z) {
            reportViewVisible(this.mImagePerfState, this.mClock.now());
        } else {
            reportViewInvisible(this.mImagePerfState, this.mClock.now());
        }
    }
}
