package com.facebook.react.views.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.drawee.drawable.RoundedColorDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.GenericDraweeView;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.postprocessors.IterativeBoxBlurPostProcessor;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.modules.fresco.ReactNetworkImageRequest;
import com.facebook.react.uimanager.BackgroundStyleApplicator;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.react.uimanager.LengthPercentage;
import com.facebook.react.uimanager.LengthPercentageType;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.style.BorderRadiusProp;
import com.facebook.react.uimanager.style.LogicalEdge;
import com.facebook.react.util.RNLog;
import com.facebook.react.views.image.ImageLoadEvent;
import com.facebook.react.views.imagehelper.ImageSource;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.views.view.ReactViewBackgroundManager;
import com.facebook.yoga.YogaConstants;
import com.umeng.analytics.pro.f;
import com.umeng.commonsdk.framework.UMModuleRegister;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactImageView.kt */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 t2\u00020\u0001:\u0002tuB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\u0004\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0011H\u0002J\u0012\u0010C\u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\u0016H\u0002J\b\u0010D\u001a\u00020'H\u0002J\b\u0010E\u001a\u00020'H\u0016J\u0006\u0010F\u001a\u00020AJ\u0010\u0010G\u001a\u00020A2\u0006\u0010H\u001a\u00020\u001bH\u0002J\u0010\u0010I\u001a\u00020A2\u0006\u0010J\u001a\u00020'H\u0002J\u0010\u0010K\u001a\u00020A2\u0006\u0010L\u001a\u00020MH\u0016J(\u0010N\u001a\u00020A2\u0006\u0010O\u001a\u00020\f2\u0006\u0010P\u001a\u00020\f2\u0006\u0010Q\u001a\u00020\f2\u0006\u0010R\u001a\u00020\fH\u0014J\u0010\u0010S\u001a\u00020A2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010T\u001a\u00020A2\u0006\u0010U\u001a\u00020\u0013J\u000e\u0010V\u001a\u00020A2\u0006\u0010\u000f\u001a\u00020\fJ\u000e\u0010W\u001a\u00020A2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010W\u001a\u00020A2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010X\u001a\u00020\fJ\u000e\u0010Y\u001a\u00020A2\u0006\u0010\u0014\u001a\u00020\u0013J\u0018\u0010Z\u001a\u00020A2\u000e\u0010[\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018H\u0007J\u0010\u0010\\\u001a\u00020A2\b\u0010]\u001a\u0004\u0018\u00010^J\u000e\u0010_\u001a\u00020A2\u0006\u0010`\u001a\u00020\fJ\u0010\u0010a\u001a\u00020A2\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u0010\u0010b\u001a\u00020A2\b\u0010]\u001a\u0004\u0018\u00010^J\u000e\u0010c\u001a\u00020A2\u0006\u0010-\u001a\u00020\fJ\u000e\u0010d\u001a\u00020A2\u0006\u0010e\u001a\u00020'J\u000e\u0010f\u001a\u00020A2\u0006\u00101\u001a\u000202J\u000e\u0010g\u001a\u00020A2\u0006\u0010h\u001a\u00020\u0013J\u000e\u0010i\u001a\u00020A2\u0006\u00108\u001a\u000209J\u000e\u0010j\u001a\u00020A2\u0006\u0010k\u001a\u00020'J\u0010\u0010l\u001a\u00020A2\b\u0010:\u001a\u0004\u0018\u00010mJ\b\u0010n\u001a\u00020AH\u0002J\u000e\u0010o\u001a\u00020A2\u0006\u0010<\u001a\u00020=J\u0010\u0010p\u001a\u00020'2\u0006\u0010!\u001a\u00020\u0016H\u0002J\u0010\u0010q\u001a\u00020A2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0012\u0010r\u001a\u00020A2\b\u0010s\u001a\u0004\u0018\u00010^H\u0002R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0004\u001a\u0012\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0004\u0018\u00010\u0016X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\u0004\u0018\u0001058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u000e\u00108\u001a\u000209X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00160;X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\b\u0018\u00010?R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006v"}, d2 = {"Lcom/facebook/react/views/image/ReactImageView;", "Lcom/facebook/drawee/view/GenericDraweeView;", f.X, "Landroid/content/Context;", "draweeControllerBuilder", "Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;", "globalImageLoadListener", "Lcom/facebook/react/views/image/GlobalImageLoadListener;", "callerContext", "", "(Landroid/content/Context;Lcom/facebook/drawee/controller/AbstractDraweeControllerBuilder;Lcom/facebook/react/views/image/GlobalImageLoadListener;Ljava/lang/Object;)V", "backgroundColor", "", "backgroundImageDrawable", "Lcom/facebook/drawee/drawable/RoundedColorDrawable;", ViewProps.BORDER_COLOR, "borderCornerRadii", "", ViewProps.BORDER_RADIUS, "", ViewProps.BORDER_WIDTH, "cachedImageSource", "Lcom/facebook/react/views/imagehelper/ImageSource;", "controllerForTesting", "Lcom/facebook/drawee/controller/ControllerListener;", "Lcom/facebook/imagepipeline/image/ImageInfo;", "defaultImageDrawable", "Landroid/graphics/drawable/Drawable;", "downloadListener", "Lcom/facebook/react/views/image/ReactImageDownloadListener;", "fadeDurationMs", "headers", "Lcom/facebook/react/bridge/ReadableMap;", "imageSource", "getImageSource$ReactAndroid_release", "()Lcom/facebook/react/views/imagehelper/ImageSource;", "setImageSource$ReactAndroid_release", "(Lcom/facebook/react/views/imagehelper/ImageSource;)V", "isDirty", "", "isTiled", "()Z", "iterativeBoxBlurPostProcessor", "Lcom/facebook/imagepipeline/postprocessors/IterativeBoxBlurPostProcessor;", "loadingImageDrawable", "overlayColor", "progressiveRenderingEnabled", "reactBackgroundManager", "Lcom/facebook/react/views/view/ReactViewBackgroundManager;", ViewProps.RESIZE_METHOD, "Lcom/facebook/react/views/image/ImageResizeMethod;", "resizeMultiplier", "resizeOptions", "Lcom/facebook/imagepipeline/common/ResizeOptions;", "getResizeOptions", "()Lcom/facebook/imagepipeline/common/ResizeOptions;", "scaleType", "Lcom/facebook/drawee/drawable/ScalingUtils$ScaleType;", "sources", "", "tileMode", "Landroid/graphics/Shader$TileMode;", "tilePostprocessor", "Lcom/facebook/react/views/image/ReactImageView$TilePostprocessor;", "getCornerRadii", "", "computedCorners", "getDrawableIfUnsupported", "hasMultipleSources", "hasOverlappingRendering", "maybeUpdateView", "maybeUpdateViewFromDrawable", "drawable", "maybeUpdateViewFromRequest", "doResize", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onSizeChanged", "w", "h", "oldw", "oldh", "setBackgroundColor", "setBlurRadius", "blurRadius", "setBorderColor", "setBorderRadius", "position", "setBorderWidth", "setControllerListener", "controllerListener", "setDefaultSource", "name", "", "setFadeDuration", "durationMs", "setHeaders", "setLoadingIndicatorSource", "setOverlayColor", "setProgressiveRenderingEnabled", ViewProps.ENABLED, "setResizeMethod", "setResizeMultiplier", "multiplier", "setScaleType", "setShouldNotifyLoadEvents", "shouldNotify", "setSource", "Lcom/facebook/react/bridge/ReadableArray;", "setSourceImage", "setTileMode", "shouldResize", "updateCallerContext", "warnImageSource", "uri", "Companion", "TilePostprocessor", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactImageView extends GenericDraweeView {
    public static final int REMOTE_IMAGE_FADE_DURATION_MS = 300;
    private int backgroundColor;
    private RoundedColorDrawable backgroundImageDrawable;
    private int borderColor;
    private float[] borderCornerRadii;
    private float borderRadius;
    private float borderWidth;
    private ImageSource cachedImageSource;
    private Object callerContext;
    private ControllerListener<ImageInfo> controllerForTesting;
    private Drawable defaultImageDrawable;
    private ReactImageDownloadListener<ImageInfo> downloadListener;
    private final AbstractDraweeControllerBuilder<?, ?, ?, ?> draweeControllerBuilder;
    private int fadeDurationMs;
    private final GlobalImageLoadListener globalImageLoadListener;
    private ReadableMap headers;
    private ImageSource imageSource;
    private boolean isDirty;
    private IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor;
    private Drawable loadingImageDrawable;
    private int overlayColor;
    private boolean progressiveRenderingEnabled;
    private final ReactViewBackgroundManager reactBackgroundManager;
    private ImageResizeMethod resizeMethod;
    private float resizeMultiplier;
    private ScalingUtils.ScaleType scaleType;
    private final List<ImageSource> sources;
    private Shader.TileMode tileMode;
    private TilePostprocessor tilePostprocessor;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final float[] computedCornerRadii = new float[4];
    private static final Matrix tileMatrix = new Matrix();

    /* compiled from: ReactImageView.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ImageResizeMethod.values().length];
            try {
                iArr[ImageResizeMethod.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ImageResizeMethod.RESIZE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactImageView(Context context, AbstractDraweeControllerBuilder<?, ?, ?, ?> draweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener, Object obj) {
        super(context, INSTANCE.buildHierarchy(context));
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(draweeControllerBuilder, "draweeControllerBuilder");
        this.draweeControllerBuilder = draweeControllerBuilder;
        this.globalImageLoadListener = globalImageLoadListener;
        this.callerContext = obj;
        this.sources = new ArrayList();
        this.borderRadius = Float.NaN;
        this.scaleType = ImageResizeMode.defaultValue();
        this.tileMode = ImageResizeMode.defaultTileMode();
        this.fadeDurationMs = -1;
        this.resizeMultiplier = 1.0f;
        ReactViewBackgroundManager reactViewBackgroundManager = new ReactViewBackgroundManager(this);
        this.reactBackgroundManager = reactViewBackgroundManager;
        this.resizeMethod = ImageResizeMethod.AUTO;
        reactViewBackgroundManager.setOverflow(ViewProps.HIDDEN);
        setLegacyVisibilityHandlingEnabled(true);
    }

    /* renamed from: getImageSource$ReactAndroid_release, reason: from getter */
    public final ImageSource getImageSource() {
        return this.imageSource;
    }

    public final void setImageSource$ReactAndroid_release(ImageSource imageSource) {
        this.imageSource = imageSource;
    }

    public final void updateCallerContext(Object callerContext) {
        if (Intrinsics.areEqual(this.callerContext, callerContext)) {
            return;
        }
        this.callerContext = callerContext;
        this.isDirty = true;
    }

    public final void setShouldNotifyLoadEvents(boolean shouldNotify) {
        if (shouldNotify == (this.downloadListener != null)) {
            return;
        }
        if (!shouldNotify) {
            this.downloadListener = null;
        } else {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            final EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
            this.downloadListener = new ReactImageDownloadListener<ImageInfo>() { // from class: com.facebook.react.views.image.ReactImageView.setShouldNotifyLoadEvents.1
                @Override // com.facebook.react.views.image.ReactImageDownloadListener
                public void onProgressChange(int loaded, int total) {
                    if (eventDispatcherForReactTag == null || this.getImageSource() == null) {
                        return;
                    }
                    EventDispatcher eventDispatcher = eventDispatcherForReactTag;
                    ImageLoadEvent.Companion companion = ImageLoadEvent.INSTANCE;
                    int surfaceId = UIManagerHelper.getSurfaceId(this);
                    int id = this.getId();
                    ImageSource imageSource = this.getImageSource();
                    eventDispatcher.dispatchEvent(companion.createProgressEvent(surfaceId, id, imageSource != null ? imageSource.getSource() : null, loaded, total));
                }

                @Override // com.facebook.react.views.image.ReactImageDownloadListener, com.facebook.drawee.controller.ControllerListener
                public void onSubmit(String id, Object callerContext) {
                    Intrinsics.checkNotNullParameter(id, "id");
                    EventDispatcher eventDispatcher = eventDispatcherForReactTag;
                    if (eventDispatcher == null) {
                        return;
                    }
                    eventDispatcher.dispatchEvent(ImageLoadEvent.INSTANCE.createLoadStartEvent(UIManagerHelper.getSurfaceId(this), this.getId()));
                }

                @Override // com.facebook.react.views.image.ReactImageDownloadListener, com.facebook.drawee.controller.ControllerListener
                public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                    EventDispatcher eventDispatcher;
                    Intrinsics.checkNotNullParameter(id, "id");
                    if (imageInfo == null || this.getImageSource() == null || (eventDispatcher = eventDispatcherForReactTag) == null) {
                        return;
                    }
                    ImageLoadEvent.Companion companion = ImageLoadEvent.INSTANCE;
                    int surfaceId = UIManagerHelper.getSurfaceId(this);
                    int id2 = this.getId();
                    ImageSource imageSource = this.getImageSource();
                    eventDispatcher.dispatchEvent(companion.createLoadEvent(surfaceId, id2, imageSource != null ? imageSource.getSource() : null, imageInfo.getWidth(), imageInfo.getHeight()));
                    eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.INSTANCE.createLoadEndEvent(UIManagerHelper.getSurfaceId(this), this.getId()));
                }

                @Override // com.facebook.react.views.image.ReactImageDownloadListener, com.facebook.drawee.controller.ControllerListener
                public void onFailure(String id, Throwable throwable) {
                    Intrinsics.checkNotNullParameter(id, "id");
                    Intrinsics.checkNotNullParameter(throwable, "throwable");
                    EventDispatcher eventDispatcher = eventDispatcherForReactTag;
                    if (eventDispatcher == null) {
                        return;
                    }
                    eventDispatcher.dispatchEvent(ImageLoadEvent.INSTANCE.createErrorEvent(UIManagerHelper.getSurfaceId(this), this.getId(), throwable));
                }
            };
        }
        this.isDirty = true;
    }

    public final void setBlurRadius(float blurRadius) {
        int iDpToPx = ((int) PixelUtil.INSTANCE.dpToPx(blurRadius)) / 2;
        this.iterativeBoxBlurPostProcessor = iDpToPx == 0 ? null : new IterativeBoxBlurPostProcessor(2, iDpToPx);
        this.isDirty = true;
    }

    @Override // android.view.View
    public void setBackgroundColor(int backgroundColor) {
        if (ReactNativeFeatureFlags.enableBackgroundStyleApplicator()) {
            BackgroundStyleApplicator.setBackgroundColor(this, Integer.valueOf(backgroundColor));
            return;
        }
        if (ReactNativeFeatureFlags.useNewReactImageViewBackgroundDrawing()) {
            this.reactBackgroundManager.setBackgroundColor(backgroundColor);
        } else if (this.backgroundColor != backgroundColor) {
            this.backgroundColor = backgroundColor;
            this.backgroundImageDrawable = new RoundedColorDrawable(backgroundColor);
            this.isDirty = true;
        }
    }

    public final void setBorderColor(int borderColor) {
        if (ReactNativeFeatureFlags.enableBackgroundStyleApplicator()) {
            BackgroundStyleApplicator.setBorderColor(this, LogicalEdge.ALL, Integer.valueOf(borderColor));
            return;
        }
        if (ReactNativeFeatureFlags.useNewReactImageViewBackgroundDrawing()) {
            this.reactBackgroundManager.setBorderColor(8, Integer.valueOf(borderColor));
        } else if (this.borderColor != borderColor) {
            this.borderColor = borderColor;
            this.isDirty = true;
        }
    }

    public final void setOverlayColor(int overlayColor) {
        if (this.overlayColor != overlayColor) {
            this.overlayColor = overlayColor;
            this.isDirty = true;
        }
    }

    public final void setBorderWidth(float borderWidth) {
        float fDpToPx = PixelUtil.INSTANCE.dpToPx(borderWidth);
        if (ReactNativeFeatureFlags.enableBackgroundStyleApplicator()) {
            BackgroundStyleApplicator.setBorderWidth(this, LogicalEdge.ALL, Float.valueOf(borderWidth));
            return;
        }
        if (ReactNativeFeatureFlags.useNewReactImageViewBackgroundDrawing()) {
            this.reactBackgroundManager.setBorderWidth(8, fDpToPx);
        } else {
            if (FloatUtil.floatsEqual(this.borderWidth, fDpToPx)) {
                return;
            }
            this.borderWidth = fDpToPx;
            this.isDirty = true;
        }
    }

    public final void setBorderRadius(float borderRadius) {
        if (ReactNativeFeatureFlags.enableBackgroundStyleApplicator()) {
            BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.BORDER_RADIUS, Float.isNaN(borderRadius) ? null : new LengthPercentage(PixelUtil.INSTANCE.pxToDp(borderRadius), LengthPercentageType.POINT));
            return;
        }
        if (ReactNativeFeatureFlags.useNewReactImageViewBackgroundDrawing()) {
            this.reactBackgroundManager.setBorderRadius(borderRadius);
        } else {
            if (FloatUtil.floatsEqual(this.borderRadius, borderRadius)) {
                return;
            }
            this.borderRadius = borderRadius;
            this.isDirty = true;
        }
    }

    public final void setBorderRadius(float borderRadius, int position) {
        if (ReactNativeFeatureFlags.enableBackgroundStyleApplicator()) {
            BackgroundStyleApplicator.setBorderRadius(this, BorderRadiusProp.values()[position], Float.isNaN(borderRadius) ? null : new LengthPercentage(PixelUtil.INSTANCE.pxToDp(borderRadius), LengthPercentageType.POINT));
            return;
        }
        if (ReactNativeFeatureFlags.useNewReactImageViewBackgroundDrawing()) {
            this.reactBackgroundManager.setBorderRadius(borderRadius, position + 1);
            return;
        }
        if (this.borderCornerRadii == null) {
            float[] fArr = new float[4];
            for (int i = 0; i < 4; i++) {
                fArr[i] = Float.NaN;
            }
            this.borderCornerRadii = fArr;
        }
        float[] fArr2 = this.borderCornerRadii;
        if (FloatUtil.floatsEqual(fArr2 != null ? Float.valueOf(fArr2[position]) : null, Float.valueOf(borderRadius))) {
            return;
        }
        float[] fArr3 = this.borderCornerRadii;
        if (fArr3 != null) {
            fArr3[position] = borderRadius;
        }
        this.isDirty = true;
    }

    public final void setScaleType(ScalingUtils.ScaleType scaleType) {
        Intrinsics.checkNotNullParameter(scaleType, "scaleType");
        if (this.scaleType != scaleType) {
            this.scaleType = scaleType;
            this.isDirty = true;
        }
    }

    public final void setTileMode(Shader.TileMode tileMode) {
        Intrinsics.checkNotNullParameter(tileMode, "tileMode");
        if (this.tileMode != tileMode) {
            this.tileMode = tileMode;
            this.tilePostprocessor = isTiled() ? new TilePostprocessor() : null;
            this.isDirty = true;
        }
    }

    public final void setResizeMethod(ImageResizeMethod resizeMethod) {
        Intrinsics.checkNotNullParameter(resizeMethod, "resizeMethod");
        if (this.resizeMethod != resizeMethod) {
            this.resizeMethod = resizeMethod;
            this.isDirty = true;
        }
    }

    public final void setResizeMultiplier(float multiplier) {
        if (Math.abs(this.resizeMultiplier - multiplier) > 9.999999747378752E-5d) {
            this.resizeMultiplier = multiplier;
            this.isDirty = true;
        }
    }

    public final void setSource(ReadableArray sources) {
        ArrayList arrayList = new ArrayList();
        if (sources == null || sources.size() == 0) {
            ImageSource.Companion companion = ImageSource.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            arrayList.add(companion.getTransparentBitmapImageSource(context));
        } else {
            if (sources.size() == 1) {
                ReadableMap map = sources.getMap(0);
                Context context2 = getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                ImageSource imageSource = new ImageSource(context2, map.getString("uri"), 0.0d, 0.0d, 12, null);
                if (Intrinsics.areEqual(Uri.EMPTY, imageSource.getUri())) {
                    warnImageSource(map.getString("uri"));
                    ImageSource.Companion companion2 = ImageSource.INSTANCE;
                    Context context3 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
                    imageSource = companion2.getTransparentBitmapImageSource(context3);
                }
                arrayList.add(imageSource);
            } else {
                int size = sources.size();
                for (int i = 0; i < size; i++) {
                    ReadableMap map2 = sources.getMap(i);
                    Context context4 = getContext();
                    Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
                    ImageSource imageSource2 = new ImageSource(context4, map2.getString("uri"), map2.getDouble("width"), map2.getDouble("height"));
                    if (Intrinsics.areEqual(Uri.EMPTY, imageSource2.getUri())) {
                        warnImageSource(map2.getString("uri"));
                        ImageSource.Companion companion3 = ImageSource.INSTANCE;
                        Context context5 = getContext();
                        Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
                        imageSource2 = companion3.getTransparentBitmapImageSource(context5);
                    }
                    arrayList.add(imageSource2);
                }
            }
        }
        if (Intrinsics.areEqual(this.sources, arrayList)) {
            return;
        }
        this.sources.clear();
        this.sources.addAll(arrayList);
        this.isDirty = true;
    }

    public final void setDefaultSource(String name) {
        ResourceDrawableIdHelper companion = ResourceDrawableIdHelper.INSTANCE.getInstance();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Drawable resourceDrawable = companion.getResourceDrawable(context, name);
        if (Intrinsics.areEqual(this.defaultImageDrawable, resourceDrawable)) {
            return;
        }
        this.defaultImageDrawable = resourceDrawable;
        this.isDirty = true;
    }

    public final void setLoadingIndicatorSource(String name) {
        ResourceDrawableIdHelper companion = ResourceDrawableIdHelper.INSTANCE.getInstance();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Drawable resourceDrawable = companion.getResourceDrawable(context, name);
        AutoRotateDrawable autoRotateDrawable = resourceDrawable != null ? new AutoRotateDrawable(resourceDrawable, 1000) : null;
        if (Intrinsics.areEqual(this.loadingImageDrawable, autoRotateDrawable)) {
            return;
        }
        this.loadingImageDrawable = autoRotateDrawable;
        this.isDirty = true;
    }

    public final void setProgressiveRenderingEnabled(boolean enabled) {
        this.progressiveRenderingEnabled = enabled;
    }

    public final void setFadeDuration(int durationMs) {
        this.fadeDurationMs = durationMs;
    }

    public final void setHeaders(ReadableMap headers) {
        this.headers = headers;
    }

    @Override // android.widget.ImageView, android.view.View
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (ReactNativeFeatureFlags.enableBackgroundStyleApplicator()) {
            BackgroundStyleApplicator.clipToPaddingBox(this, canvas);
        } else if (ReactNativeFeatureFlags.useNewReactImageViewBackgroundDrawing()) {
            this.reactBackgroundManager.maybeClipToPaddingBox(canvas);
        }
        super.onDraw(canvas);
    }

    public final void maybeUpdateView() {
        if (this.isDirty) {
            if (!hasMultipleSources() || (getWidth() > 0 && getHeight() > 0)) {
                setSourceImage();
                ImageSource imageSource = this.imageSource;
                if (imageSource == null) {
                    return;
                }
                boolean zShouldResize = shouldResize(imageSource);
                if (!zShouldResize || (getWidth() > 0 && getHeight() > 0)) {
                    if (!isTiled() || (getWidth() > 0 && getHeight() > 0)) {
                        GenericDraweeHierarchy hierarchy = getHierarchy();
                        hierarchy.setActualImageScaleType(this.scaleType);
                        Drawable drawable = this.defaultImageDrawable;
                        if (drawable != null) {
                            hierarchy.setPlaceholderImage(drawable, this.scaleType);
                        }
                        Drawable drawable2 = this.loadingImageDrawable;
                        if (drawable2 != null) {
                            hierarchy.setPlaceholderImage(drawable2, ScalingUtils.ScaleType.CENTER);
                        }
                        float[] fArr = computedCornerRadii;
                        getCornerRadii(fArr);
                        RoundingParams roundingParams = hierarchy.getRoundingParams();
                        if (roundingParams != null) {
                            roundingParams.setCornersRadii(fArr[0], fArr[1], fArr[2], fArr[3]);
                            RoundedColorDrawable roundedColorDrawable = this.backgroundImageDrawable;
                            if (roundedColorDrawable != null) {
                                roundedColorDrawable.setBorder(this.borderColor, this.borderWidth);
                                float[] cornersRadii = roundingParams.getCornersRadii();
                                if (cornersRadii != null) {
                                    roundedColorDrawable.setRadii(cornersRadii);
                                }
                                hierarchy.setBackgroundImage(roundedColorDrawable);
                            }
                            roundingParams.setBorder(this.borderColor, this.borderWidth);
                            int i = this.overlayColor;
                            if (i != 0) {
                                roundingParams.setOverlayColor(i);
                            } else {
                                roundingParams.setRoundingMethod(RoundingParams.RoundingMethod.BITMAP_ONLY);
                            }
                            hierarchy.setRoundingParams(roundingParams);
                        }
                        int i2 = this.fadeDurationMs;
                        if (i2 < 0) {
                            i2 = imageSource.get_isResource() ? 0 : 300;
                        }
                        hierarchy.setFadeDuration(i2);
                        Drawable drawableIfUnsupported = getDrawableIfUnsupported(imageSource);
                        if (drawableIfUnsupported != null) {
                            maybeUpdateViewFromDrawable(drawableIfUnsupported);
                        } else {
                            maybeUpdateViewFromRequest(zShouldResize);
                        }
                        this.isDirty = false;
                    }
                }
            }
        }
    }

    private final void maybeUpdateViewFromRequest(boolean doResize) {
        Uri uri;
        ImageSource imageSource = this.imageSource;
        if (imageSource == null || (uri = imageSource.getUri()) == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        IterativeBoxBlurPostProcessor iterativeBoxBlurPostProcessor = this.iterativeBoxBlurPostProcessor;
        if (iterativeBoxBlurPostProcessor != null) {
            arrayList.add(iterativeBoxBlurPostProcessor);
        }
        TilePostprocessor tilePostprocessor = this.tilePostprocessor;
        if (tilePostprocessor != null) {
            arrayList.add(tilePostprocessor);
        }
        Postprocessor postprocessorFrom = MultiPostprocessor.INSTANCE.from(arrayList);
        ResizeOptions resizeOptions = doResize ? getResizeOptions() : null;
        ImageRequestBuilder progressiveRenderingEnabled = ImageRequestBuilder.newBuilderWithSource(uri).setPostprocessor(postprocessorFrom).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.progressiveRenderingEnabled);
        ReactNetworkImageRequest.Companion companion = ReactNetworkImageRequest.INSTANCE;
        Intrinsics.checkNotNull(progressiveRenderingEnabled);
        ReactNetworkImageRequest reactNetworkImageRequestFromBuilderWithHeaders = companion.fromBuilderWithHeaders(progressiveRenderingEnabled, this.headers);
        GlobalImageLoadListener globalImageLoadListener = this.globalImageLoadListener;
        if (globalImageLoadListener != null) {
            globalImageLoadListener.onLoadAttempt(uri);
        }
        AbstractDraweeControllerBuilder<?, ?, ?, ?> abstractDraweeControllerBuilder = this.draweeControllerBuilder;
        Intrinsics.checkNotNull(abstractDraweeControllerBuilder, "null cannot be cast to non-null type com.facebook.drawee.controller.AbstractDraweeControllerBuilder<*, com.facebook.imagepipeline.request.ImageRequest, com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>, com.facebook.imagepipeline.image.ImageInfo>");
        abstractDraweeControllerBuilder.reset();
        abstractDraweeControllerBuilder.setImageRequest(reactNetworkImageRequestFromBuilderWithHeaders).setAutoPlayAnimations(true).setOldController(getController());
        Object obj = this.callerContext;
        if (obj != null) {
            abstractDraweeControllerBuilder.setCallerContext(obj);
        }
        ImageSource imageSource2 = this.cachedImageSource;
        if (imageSource2 != null) {
            abstractDraweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(imageSource2.getUri()).setPostprocessor(postprocessorFrom).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.progressiveRenderingEnabled).build());
        }
        ReactImageDownloadListener<ImageInfo> reactImageDownloadListener = this.downloadListener;
        if (reactImageDownloadListener != null && this.controllerForTesting != null) {
            ForwardingControllerListener forwardingControllerListener = new ForwardingControllerListener();
            forwardingControllerListener.addListener(this.downloadListener);
            forwardingControllerListener.addListener(this.controllerForTesting);
            abstractDraweeControllerBuilder.setControllerListener(forwardingControllerListener);
        } else {
            ControllerListener<ImageInfo> controllerListener = this.controllerForTesting;
            if (controllerListener != null) {
                abstractDraweeControllerBuilder.setControllerListener(controllerListener);
            } else if (reactImageDownloadListener != null) {
                abstractDraweeControllerBuilder.setControllerListener(reactImageDownloadListener);
            }
        }
        if (this.downloadListener != null) {
            getHierarchy().setProgressBarImage(this.downloadListener);
        }
        setController(abstractDraweeControllerBuilder.build());
        abstractDraweeControllerBuilder.reset();
    }

    private final void maybeUpdateViewFromDrawable(Drawable drawable) {
        EventDispatcher eventDispatcherForReactTag;
        if (this.downloadListener != null) {
            Context context = getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
            eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag((ReactContext) context, getId());
        } else {
            eventDispatcherForReactTag = null;
        }
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.INSTANCE.createLoadStartEvent(UIManagerHelper.getSurfaceId(this), getId()));
        }
        getHierarchy().setImage(drawable, 1.0f, false);
        if (eventDispatcherForReactTag == null || this.imageSource == null) {
            return;
        }
        ImageLoadEvent.Companion companion = ImageLoadEvent.INSTANCE;
        ReactImageView reactImageView = this;
        int surfaceId = UIManagerHelper.getSurfaceId(reactImageView);
        int id = getId();
        ImageSource imageSource = this.imageSource;
        eventDispatcherForReactTag.dispatchEvent(companion.createLoadEvent(surfaceId, id, imageSource != null ? imageSource.getSource() : null, getWidth(), getHeight()));
        eventDispatcherForReactTag.dispatchEvent(ImageLoadEvent.INSTANCE.createLoadEndEvent(UIManagerHelper.getSurfaceId(reactImageView), getId()));
    }

    private final void getCornerRadii(float[] computedCorners) {
        float f = !YogaConstants.isUndefined(this.borderRadius) ? this.borderRadius : 0.0f;
        float[] fArr = this.borderCornerRadii;
        if (fArr == null) {
            float[] fArr2 = new float[4];
            for (int i = 0; i < 4; i++) {
                fArr2[i] = Float.NaN;
            }
            fArr = fArr2;
        }
        computedCorners[0] = !YogaConstants.isUndefined(fArr[0]) ? fArr[0] : f;
        computedCorners[1] = !YogaConstants.isUndefined(fArr[1]) ? fArr[1] : f;
        computedCorners[2] = !YogaConstants.isUndefined(fArr[2]) ? fArr[2] : f;
        if (!YogaConstants.isUndefined(fArr[3])) {
            f = fArr[3];
        }
        computedCorners[3] = f;
    }

    @VisibleForTesting
    public final void setControllerListener(ControllerListener<ImageInfo> controllerListener) {
        this.controllerForTesting = controllerListener;
        this.isDirty = true;
        maybeUpdateView();
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w <= 0 || h <= 0) {
            return;
        }
        this.isDirty = this.isDirty || hasMultipleSources() || isTiled();
        maybeUpdateView();
    }

    private final boolean hasMultipleSources() {
        return this.sources.size() > 1;
    }

    private final boolean isTiled() {
        return this.tileMode != Shader.TileMode.CLAMP;
    }

    private final void setSourceImage() {
        this.imageSource = null;
        if (this.sources.isEmpty()) {
            List<ImageSource> list = this.sources;
            ImageSource.Companion companion = ImageSource.INSTANCE;
            Context context = getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            list.add(companion.getTransparentBitmapImageSource(context));
        } else if (hasMultipleSources()) {
            MultiSourceHelper.MultiSourceResult bestSourceForSize = MultiSourceHelper.getBestSourceForSize(getWidth(), getHeight(), this.sources);
            this.imageSource = bestSourceForSize.bestResult;
            this.cachedImageSource = bestSourceForSize.bestResultInCache;
            return;
        }
        this.imageSource = this.sources.get(0);
    }

    private final boolean shouldResize(ImageSource imageSource) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.resizeMethod.ordinal()];
        if (i != 1) {
            if (i != 2) {
                return false;
            }
        } else if (!UriUtil.isLocalContentUri(imageSource.getUri()) && !UriUtil.isLocalFileUri(imageSource.getUri())) {
            return false;
        }
        return true;
    }

    private final Drawable getDrawableIfUnsupported(ImageSource imageSource) {
        if (!ReactNativeFeatureFlags.loadVectorDrawablesOnImages()) {
            return null;
        }
        String source = imageSource.getSource();
        if (!imageSource.get_isResource() || source == null) {
            return null;
        }
        ResourceDrawableIdHelper companion = ResourceDrawableIdHelper.INSTANCE.getInstance();
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        if (!companion.isVectorDrawable(context, source)) {
            return null;
        }
        Context context2 = getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        return companion.getResourceDrawable(context2, source);
    }

    private final ResizeOptions getResizeOptions() {
        int iRound = Math.round(getWidth() * this.resizeMultiplier);
        int iRound2 = Math.round(getHeight() * this.resizeMultiplier);
        if (iRound <= 0 || iRound2 <= 0) {
            return null;
        }
        return new ResizeOptions(iRound, iRound2, 0.0f, 0.0f, 12, null);
    }

    private final void warnImageSource(String uri) {
        if (!ReactBuildConfig.DEBUG || ReactFeatureFlags.enableBridgelessArchitecture) {
            return;
        }
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        RNLog.w((ReactContext) context, "ReactImageView: Image source \"" + uri + "\" doesn't exist");
    }

    /* compiled from: ReactImageView.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/facebook/react/views/image/ReactImageView$TilePostprocessor;", "Lcom/facebook/imagepipeline/request/BasePostprocessor;", "(Lcom/facebook/react/views/image/ReactImageView;)V", UMModuleRegister.PROCESS, "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "source", "bitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private final class TilePostprocessor extends BasePostprocessor {
        public TilePostprocessor() {
        }

        @Override // com.facebook.imagepipeline.request.BasePostprocessor, com.facebook.imagepipeline.request.Postprocessor
        public CloseableReference<Bitmap> process(Bitmap source, PlatformBitmapFactory bitmapFactory) {
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(bitmapFactory, "bitmapFactory");
            Rect rect = new Rect(0, 0, ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            ReactImageView.this.scaleType.getTransform(ReactImageView.tileMatrix, rect, source.getWidth(), source.getHeight(), 0.0f, 0.0f);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            BitmapShader bitmapShader = new BitmapShader(source, ReactImageView.this.tileMode, ReactImageView.this.tileMode);
            bitmapShader.setLocalMatrix(ReactImageView.tileMatrix);
            paint.setShader(bitmapShader);
            CloseableReference<Bitmap> closeableReferenceCreateBitmap = bitmapFactory.createBitmap(ReactImageView.this.getWidth(), ReactImageView.this.getHeight());
            Intrinsics.checkNotNullExpressionValue(closeableReferenceCreateBitmap, "createBitmap(...)");
            try {
                new Canvas(closeableReferenceCreateBitmap.get()).drawRect(rect, paint);
                CloseableReference<Bitmap> closeableReferenceMo319clone = closeableReferenceCreateBitmap.mo319clone();
                Intrinsics.checkNotNullExpressionValue(closeableReferenceMo319clone, "clone(...)");
                return closeableReferenceMo319clone;
            } finally {
                CloseableReference.closeSafely(closeableReferenceCreateBitmap);
            }
        }
    }

    /* compiled from: ReactImageView.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/facebook/react/views/image/ReactImageView$Companion;", "", "()V", "REMOTE_IMAGE_FADE_DURATION_MS", "", "computedCornerRadii", "", "tileMatrix", "Landroid/graphics/Matrix;", "buildHierarchy", "Lcom/facebook/drawee/generic/GenericDraweeHierarchy;", f.X, "Landroid/content/Context;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final GenericDraweeHierarchy buildHierarchy(Context context) {
            GenericDraweeHierarchyBuilder genericDraweeHierarchyBuilder = new GenericDraweeHierarchyBuilder(context.getResources());
            RoundingParams roundingParamsFromCornersRadius = RoundingParams.fromCornersRadius(0.0f);
            roundingParamsFromCornersRadius.setPaintFilterBitmap(true);
            GenericDraweeHierarchy genericDraweeHierarchyBuild = genericDraweeHierarchyBuilder.setRoundingParams(roundingParamsFromCornersRadius).build();
            Intrinsics.checkNotNullExpressionValue(genericDraweeHierarchyBuild, "build(...)");
            return genericDraweeHierarchyBuild;
        }
    }
}
