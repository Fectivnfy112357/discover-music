package com.facebook.fresco.ui.common;

import com.facebook.fresco.ui.common.ControllerListener2;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class ImagePerfState {

    @Nullable
    private Object mCallerContext;

    @Nullable
    private String mControllerId;

    @Nullable
    private DimensionsInfo mDimensionsInfo;

    @Nullable
    private Throwable mErrorThrowable;

    @Nullable
    private ControllerListener2.Extras mExtraData;

    @Nullable
    private Object mImageInfo;

    @Nullable
    private Object mImageRequest;
    private boolean mIsPrefetch;

    @Nullable
    private String mRequestId;
    private long mControllerSubmitTimeMs = -1;
    private long mControllerIntermediateImageSetTimeMs = -1;
    private long mControllerFinalImageSetTimeMs = -1;
    private long mControllerFailureTimeMs = -1;
    private long mControllerCancelTimeMs = -1;
    private long mImageRequestStartTimeMs = -1;
    private long mImageRequestEndTimeMs = -1;
    private int mOnScreenWidthPx = -1;
    private int mOnScreenHeightPx = -1;
    private ImageLoadStatus mImageLoadStatus = ImageLoadStatus.UNKNOWN;
    private VisibilityState mVisibilityState = VisibilityState.UNKNOWN;
    private long mVisibilityEventTimeMs = -1;
    private long mInvisibilityEventTimeMs = -1;
    private long mImageDrawTimeMs = -1;

    public void reset() {
        this.mRequestId = null;
        this.mImageRequest = null;
        this.mCallerContext = null;
        this.mImageInfo = null;
        this.mIsPrefetch = false;
        this.mOnScreenWidthPx = -1;
        this.mOnScreenHeightPx = -1;
        this.mErrorThrowable = null;
        this.mImageLoadStatus = ImageLoadStatus.UNKNOWN;
        this.mVisibilityState = VisibilityState.UNKNOWN;
        this.mDimensionsInfo = null;
        this.mExtraData = null;
        resetPointsTimestamps();
    }

    public void resetPointsTimestamps() {
        this.mImageRequestStartTimeMs = -1L;
        this.mImageRequestEndTimeMs = -1L;
        this.mControllerSubmitTimeMs = -1L;
        this.mControllerFinalImageSetTimeMs = -1L;
        this.mControllerFailureTimeMs = -1L;
        this.mControllerCancelTimeMs = -1L;
        this.mVisibilityEventTimeMs = -1L;
        this.mInvisibilityEventTimeMs = -1L;
        this.mImageDrawTimeMs = -1L;
    }

    public void setImageLoadStatus(ImageLoadStatus imageLoadStatus) {
        this.mImageLoadStatus = imageLoadStatus;
    }

    public ImageLoadStatus getImageLoadStatus() {
        return this.mImageLoadStatus;
    }

    public void setControllerId(@Nullable String str) {
        this.mControllerId = str;
    }

    public void setRequestId(@Nullable String str) {
        this.mRequestId = str;
    }

    public void setImageRequest(@Nullable Object obj) {
        this.mImageRequest = obj;
    }

    public void setCallerContext(@Nullable Object obj) {
        this.mCallerContext = obj;
    }

    public void setControllerSubmitTimeMs(long j) {
        this.mControllerSubmitTimeMs = j;
    }

    public void setControllerIntermediateImageSetTimeMs(long j) {
        this.mControllerIntermediateImageSetTimeMs = j;
    }

    public void setControllerFinalImageSetTimeMs(long j) {
        this.mControllerFinalImageSetTimeMs = j;
    }

    public void setControllerFailureTimeMs(long j) {
        this.mControllerFailureTimeMs = j;
    }

    public void setControllerCancelTimeMs(long j) {
        this.mControllerCancelTimeMs = j;
    }

    public void setImageRequestStartTimeMs(long j) {
        this.mImageRequestStartTimeMs = j;
    }

    public void setImageRequestEndTimeMs(long j) {
        this.mImageRequestEndTimeMs = j;
    }

    public void setVisibilityEventTimeMs(long j) {
        this.mVisibilityEventTimeMs = j;
    }

    public void setInvisibilityEventTimeMs(long j) {
        this.mInvisibilityEventTimeMs = j;
    }

    public void setPrefetch(boolean z) {
        this.mIsPrefetch = z;
    }

    public void setImageInfo(@Nullable Object obj) {
        this.mImageInfo = obj;
    }

    public void setOnScreenWidth(int i) {
        this.mOnScreenWidthPx = i;
    }

    public void setOnScreenHeight(int i) {
        this.mOnScreenHeightPx = i;
    }

    public void setErrorThrowable(@Nullable Throwable th) {
        this.mErrorThrowable = th;
    }

    public void setVisible(boolean z) {
        this.mVisibilityState = z ? VisibilityState.VISIBLE : VisibilityState.INVISIBLE;
    }

    public void setImageDrawTimeMs(long j) {
        this.mImageDrawTimeMs = j;
    }

    public ImagePerfData snapshot() {
        return new ImagePerfData(this.mControllerId, this.mRequestId, this.mImageRequest, this.mCallerContext, this.mImageInfo, this.mControllerSubmitTimeMs, this.mControllerIntermediateImageSetTimeMs, this.mControllerFinalImageSetTimeMs, this.mControllerFailureTimeMs, this.mControllerCancelTimeMs, this.mImageRequestStartTimeMs, this.mImageRequestEndTimeMs, this.mIsPrefetch, this.mOnScreenWidthPx, this.mOnScreenHeightPx, this.mErrorThrowable, this.mVisibilityState, this.mVisibilityEventTimeMs, this.mInvisibilityEventTimeMs, this.mImageDrawTimeMs, this.mDimensionsInfo, this.mExtraData);
    }

    public long getImageDrawTimeMs() {
        return this.mImageDrawTimeMs;
    }

    public void setDimensionsInfo(DimensionsInfo dimensionsInfo) {
        this.mDimensionsInfo = dimensionsInfo;
    }

    @Nullable
    public DimensionsInfo getDimensionsInfo() {
        return this.mDimensionsInfo;
    }

    public void setExtraData(@Nullable ControllerListener2.Extras extras) {
        this.mExtraData = extras;
    }

    @Nullable
    public Object getExtraData() {
        return this.mExtraData;
    }
}
