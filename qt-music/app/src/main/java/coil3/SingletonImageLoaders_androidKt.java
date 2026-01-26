package coil3;

import android.content.Context;
import android.widget.ImageView;
import coil3.request.Disposable;
import coil3.request.ImageRequest;
import coil3.request.ImageRequests_androidKt;
import coil3.request.ImageResult;
import coil3.util.CoilUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: singletonImageLoaders.android.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a<\u0010\u0005\u001a\u00020\u0006*\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0000\u001a\u00020\u00012\u0019\b\u0002\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0086\b\u001a\r\u0010\u000f\u001a\u00020\r*\u00020\u0007H\u0086\b\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u00078Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"imageLoader", "Lcoil3/ImageLoader;", "Landroid/content/Context;", "getImageLoader", "(Landroid/content/Context;)Lcoil3/ImageLoader;", "load", "Lcoil3/request/Disposable;", "Landroid/widget/ImageView;", "data", "", "builder", "Lkotlin/Function1;", "Lcoil3/request/ImageRequest$Builder;", "", "Lkotlin/ExtensionFunctionType;", "dispose", "result", "Lcoil3/request/ImageResult;", "getResult", "(Landroid/widget/ImageView;)Lcoil3/request/ImageResult;", "coil_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class SingletonImageLoaders_androidKt {
    public static final ImageLoader getImageLoader(Context context) {
        return SingletonImageLoader.get(context);
    }

    public static /* synthetic */ Disposable load$default(ImageView imageView, Object obj, ImageLoader imageLoader, Function1 function1, int i, Object obj2) {
        if ((i & 2) != 0) {
            imageLoader = SingletonImageLoader.get(imageView.getContext());
        }
        if ((i & 4) != 0) {
            function1 = new Function1<ImageRequest.Builder, Unit>() { // from class: coil3.SingletonImageLoaders_androidKt.load.1
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ImageRequest.Builder builder) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ImageRequest.Builder builder) {
                    invoke2(builder);
                    return Unit.INSTANCE;
                }
            };
        }
        ImageRequest.Builder builderTarget = ImageRequests_androidKt.target(new ImageRequest.Builder(imageView.getContext()).data(obj), imageView);
        function1.invoke(builderTarget);
        return imageLoader.enqueue(builderTarget.build());
    }

    public static final Disposable load(ImageView imageView, Object obj, ImageLoader imageLoader, Function1<? super ImageRequest.Builder, Unit> function1) {
        ImageRequest.Builder builderTarget = ImageRequests_androidKt.target(new ImageRequest.Builder(imageView.getContext()).data(obj), imageView);
        function1.invoke(builderTarget);
        return imageLoader.enqueue(builderTarget.build());
    }

    public static final void dispose(ImageView imageView) {
        CoilUtils.dispose(imageView);
    }

    public static final ImageResult getResult(ImageView imageView) {
        return CoilUtils.result(imageView);
    }
}
