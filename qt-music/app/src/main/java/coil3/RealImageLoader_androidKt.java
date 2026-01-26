package coil3;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import coil3.ComponentRegistry;
import coil3.RealImageLoader;
import coil3.decode.BitmapFactoryDecoder;
import coil3.decode.ExifOrientationStrategy;
import coil3.decode.StaticImageDecoder;
import coil3.fetch.AssetUriFetcher;
import coil3.fetch.BitmapFetcher;
import coil3.fetch.ContentUriFetcher;
import coil3.fetch.DrawableFetcher;
import coil3.fetch.ResourceUriFetcher;
import coil3.key.AndroidResourceUriKeyer;
import coil3.map.AndroidUriMapper;
import coil3.map.ResourceIntMapper;
import coil3.request.Disposable;
import coil3.request.ImageRequest;
import coil3.request.ImageRequests_androidKt;
import coil3.request.ImageResult;
import coil3.request.OneShotDisposable;
import coil3.request.ViewTargetRequestManagerKt;
import coil3.size.ViewSizeResolver;
import coil3.target.Target;
import coil3.target.ViewTarget;
import coil3.transition.NoneTransition;
import coil3.transition.Transition;
import coil3.transition.TransitionTarget;
import com.facebook.react.uimanager.events.TouchesHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Semaphore;
import kotlinx.coroutines.sync.SemaphoreKt;

/* compiled from: RealImageLoader.android.kt */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0000\u001a1\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H\u0080\b\u001a\u0014\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0000\u001a\u0010\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0002¨\u0006\u0017"}, d2 = {"needsExecuteOnMainDispatcher", "", "request", "Lcoil3/request/ImageRequest;", "getDisposable", "Lcoil3/request/Disposable;", "job", "Lkotlinx/coroutines/Deferred;", "Lcoil3/request/ImageResult;", "transition", "", "result", TouchesHelper.TARGET_KEY, "Lcoil3/target/Target;", "eventListener", "Lcoil3/EventListener;", "setDrawable", "Lkotlin/Function0;", "addAndroidComponents", "Lcoil3/ComponentRegistry$Builder;", "options", "Lcoil3/RealImageLoader$Options;", "enableStaticImageDecoder", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RealImageLoader_androidKt {
    public static final boolean needsExecuteOnMainDispatcher(ImageRequest imageRequest) {
        return (imageRequest.getTarget() instanceof ViewTarget) || (imageRequest.getSizeResolver() instanceof ViewSizeResolver) || ImageRequests_androidKt.getLifecycle(imageRequest) != null;
    }

    public static final Disposable getDisposable(ImageRequest imageRequest, Deferred<? extends ImageResult> deferred) {
        if (imageRequest.getTarget() instanceof ViewTarget) {
            return ViewTargetRequestManagerKt.getRequestManager(((ViewTarget) imageRequest.getTarget()).getView()).getDisposable(deferred);
        }
        return new OneShotDisposable(deferred);
    }

    public static final void transition(ImageResult imageResult, Target target, EventListener eventListener, Function0<Unit> function0) {
        if (!(target instanceof TransitionTarget)) {
            function0.invoke();
            return;
        }
        Transition transitionCreate = ImageRequests_androidKt.getTransitionFactory(imageResult.getRequest()).create((TransitionTarget) target, imageResult);
        if (transitionCreate instanceof NoneTransition) {
            function0.invoke();
            return;
        }
        eventListener.transitionStart(imageResult.getRequest(), transitionCreate);
        transitionCreate.transition();
        eventListener.transitionEnd(imageResult.getRequest(), transitionCreate);
    }

    public static final ComponentRegistry.Builder addAndroidComponents(ComponentRegistry.Builder builder, RealImageLoader.Options options) {
        builder.add(new AndroidUriMapper(), Reflection.getOrCreateKotlinClass(android.net.Uri.class));
        builder.add(new ResourceIntMapper(), Reflection.getOrCreateKotlinClass(Integer.class));
        builder.add(new AndroidResourceUriKeyer(), Reflection.getOrCreateKotlinClass(Uri.class));
        builder.add(new AssetUriFetcher.Factory(), Reflection.getOrCreateKotlinClass(Uri.class));
        builder.add(new ContentUriFetcher.Factory(), Reflection.getOrCreateKotlinClass(Uri.class));
        builder.add(new ResourceUriFetcher.Factory(), Reflection.getOrCreateKotlinClass(Uri.class));
        builder.add(new DrawableFetcher.Factory(), Reflection.getOrCreateKotlinClass(Drawable.class));
        builder.add(new BitmapFetcher.Factory(), Reflection.getOrCreateKotlinClass(Bitmap.class));
        Semaphore semaphoreSemaphore$default = SemaphoreKt.Semaphore$default(ImageLoaders_androidKt.getBitmapFactoryMaxParallelism(options), 0, 2, null);
        if (enableStaticImageDecoder(options)) {
            builder.add(new StaticImageDecoder.Factory(semaphoreSemaphore$default));
        }
        builder.add(new BitmapFactoryDecoder.Factory(semaphoreSemaphore$default, ImageLoaders_androidKt.getBitmapFactoryExifOrientationStrategy(options)));
        return builder;
    }

    private static final boolean enableStaticImageDecoder(RealImageLoader.Options options) {
        if (Build.VERSION.SDK_INT >= 29) {
            ExifOrientationStrategy bitmapFactoryExifOrientationStrategy = ImageLoaders_androidKt.getBitmapFactoryExifOrientationStrategy(options);
            if (Intrinsics.areEqual(bitmapFactoryExifOrientationStrategy, ExifOrientationStrategy.RESPECT_PERFORMANCE) || Intrinsics.areEqual(bitmapFactoryExifOrientationStrategy, ExifOrientationStrategy.RESPECT_ALL)) {
                return true;
            }
        }
        return false;
    }
}
