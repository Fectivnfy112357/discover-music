package com.facebook.drawee.backends.pipeline.info;

import android.graphics.Rect;
import com.facebook.common.internal.Supplier;
import com.facebook.common.time.MonotonicClock;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfControllerListener2;
import com.facebook.drawee.backends.pipeline.info.internal.ImagePerfRequestListener;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.fresco.ui.common.ImageLoadStatus;
import com.facebook.fresco.ui.common.ImagePerfData;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.fresco.ui.common.ImagePerfNotifier;
import com.facebook.fresco.ui.common.ImagePerfState;
import com.facebook.fresco.ui.common.VisibilityState;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfMonitor implements ImagePerfNotifier {
    private final Supplier<Boolean> mAsyncLogging;
    private boolean mEnabled;

    @Nullable
    private ForwardingRequestListener mForwardingRequestListener;

    @Nullable
    private ImagePerfControllerListener2 mImagePerfControllerListener2;

    @Nullable
    private List<ImagePerfDataListener> mImagePerfDataListeners;

    @Nullable
    private ImagePerfRequestListener mImagePerfRequestListener;
    private final ImagePerfState mImagePerfState = new ImagePerfState();
    private final MonotonicClock mMonotonicClock;
    private final PipelineDraweeController mPipelineDraweeController;

    public ImagePerfMonitor(MonotonicClock monotonicClock, PipelineDraweeController pipelineDraweeController, Supplier<Boolean> supplier) {
        this.mMonotonicClock = monotonicClock;
        this.mPipelineDraweeController = pipelineDraweeController;
        this.mAsyncLogging = supplier;
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        if (z) {
            setupListeners();
            ImagePerfControllerListener2 imagePerfControllerListener2 = this.mImagePerfControllerListener2;
            if (imagePerfControllerListener2 != null) {
                this.mPipelineDraweeController.addControllerListener2(imagePerfControllerListener2);
            }
            ForwardingRequestListener forwardingRequestListener = this.mForwardingRequestListener;
            if (forwardingRequestListener != null) {
                this.mPipelineDraweeController.addRequestListener(forwardingRequestListener);
                return;
            }
            return;
        }
        ImagePerfControllerListener2 imagePerfControllerListener22 = this.mImagePerfControllerListener2;
        if (imagePerfControllerListener22 != null) {
            this.mPipelineDraweeController.removeControllerListener2(imagePerfControllerListener22);
        }
        ForwardingRequestListener forwardingRequestListener2 = this.mForwardingRequestListener;
        if (forwardingRequestListener2 != null) {
            this.mPipelineDraweeController.removeRequestListener(forwardingRequestListener2);
        }
    }

    public void addImagePerfDataListener(@Nullable ImagePerfDataListener imagePerfDataListener) {
        if (imagePerfDataListener == null) {
            return;
        }
        if (this.mImagePerfDataListeners == null) {
            this.mImagePerfDataListeners = new CopyOnWriteArrayList();
        }
        this.mImagePerfDataListeners.add(imagePerfDataListener);
    }

    public void removeImagePerfDataListener(ImagePerfDataListener imagePerfDataListener) {
        List<ImagePerfDataListener> list = this.mImagePerfDataListeners;
        if (list == null) {
            return;
        }
        list.remove(imagePerfDataListener);
    }

    public void clearImagePerfDataListeners() {
        List<ImagePerfDataListener> list = this.mImagePerfDataListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifier
    public void notifyStatusUpdated(ImagePerfState imagePerfState, ImageLoadStatus imageLoadStatus) {
        List<ImagePerfDataListener> list;
        imagePerfState.setImageLoadStatus(imageLoadStatus);
        if (!this.mEnabled || (list = this.mImagePerfDataListeners) == null || list.isEmpty()) {
            return;
        }
        if (imageLoadStatus == ImageLoadStatus.SUCCESS) {
            addViewportData();
        }
        ImagePerfData imagePerfDataSnapshot = imagePerfState.snapshot();
        Iterator<ImagePerfDataListener> it = this.mImagePerfDataListeners.iterator();
        while (it.hasNext()) {
            it.next().onImageLoadStatusUpdated(imagePerfDataSnapshot, imageLoadStatus);
        }
    }

    @Override // com.facebook.fresco.ui.common.ImagePerfNotifier
    public void notifyListenersOfVisibilityStateUpdate(ImagePerfState imagePerfState, VisibilityState visibilityState) {
        List<ImagePerfDataListener> list;
        if (!this.mEnabled || (list = this.mImagePerfDataListeners) == null || list.isEmpty()) {
            return;
        }
        ImagePerfData imagePerfDataSnapshot = imagePerfState.snapshot();
        Iterator<ImagePerfDataListener> it = this.mImagePerfDataListeners.iterator();
        while (it.hasNext()) {
            it.next().onImageVisibilityUpdated(imagePerfDataSnapshot, visibilityState);
        }
    }

    public void addViewportData() {
        DraweeHierarchy hierarchy = this.mPipelineDraweeController.getHierarchy();
        if (hierarchy == null || hierarchy.getTopLevelDrawable() == null) {
            return;
        }
        Rect bounds = hierarchy.getTopLevelDrawable().getBounds();
        this.mImagePerfState.setOnScreenWidth(bounds.width());
        this.mImagePerfState.setOnScreenHeight(bounds.height());
    }

    private void setupListeners() {
        if (this.mImagePerfControllerListener2 == null) {
            this.mImagePerfControllerListener2 = new ImagePerfControllerListener2(this.mMonotonicClock, this.mImagePerfState, this, this.mAsyncLogging);
        }
        if (this.mImagePerfRequestListener == null) {
            this.mImagePerfRequestListener = new ImagePerfRequestListener(this.mMonotonicClock, this.mImagePerfState);
        }
        if (this.mForwardingRequestListener == null) {
            this.mForwardingRequestListener = new ForwardingRequestListener(this.mImagePerfRequestListener);
        }
    }

    public void reset() {
        clearImagePerfDataListeners();
        setEnabled(false);
        this.mImagePerfState.reset();
    }
}
