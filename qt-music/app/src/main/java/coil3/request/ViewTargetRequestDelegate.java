package coil3.request;

import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import coil3.ImageLoader;
import coil3.target.ViewTarget;
import coil3.util.LifecyclesKt;
import com.facebook.react.uimanager.events.TouchesHelper;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.Job;

/* compiled from: RequestDelegate.android.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0012\u001a\u00020\u0010H\u0016J\u000e\u0010\u0013\u001a\u00020\u0010H\u0096@¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\u0010\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcoil3/request/ViewTargetRequestDelegate;", "Lcoil3/request/RequestDelegate;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "imageLoader", "Lcoil3/ImageLoader;", "initialRequest", "Lcoil3/request/ImageRequest;", TouchesHelper.TARGET_KEY, "Lcoil3/target/ViewTarget;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "job", "Lkotlinx/coroutines/Job;", "<init>", "(Lcoil3/ImageLoader;Lcoil3/request/ImageRequest;Lcoil3/target/ViewTarget;Landroidx/lifecycle/Lifecycle;Lkotlinx/coroutines/Job;)V", "restart", "", "assertActive", "start", "awaitStarted", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dispose", "onDestroy", "owner", "Landroidx/lifecycle/LifecycleOwner;", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ViewTargetRequestDelegate implements RequestDelegate, DefaultLifecycleObserver {
    private final ImageLoader imageLoader;
    private final ImageRequest initialRequest;
    private final Job job;
    private final Lifecycle lifecycle;
    private final ViewTarget<?> target;

    public ViewTargetRequestDelegate(ImageLoader imageLoader, ImageRequest imageRequest, ViewTarget<?> viewTarget, Lifecycle lifecycle, Job job) {
        this.imageLoader = imageLoader;
        this.initialRequest = imageRequest;
        this.target = viewTarget;
        this.lifecycle = lifecycle;
        this.job = job;
    }

    public final void restart() {
        this.imageLoader.enqueue(this.initialRequest);
    }

    @Override // coil3.request.RequestDelegate
    public void assertActive() {
        if (this.target.getView().isAttachedToWindow()) {
            return;
        }
        ViewTargetRequestManagerKt.getRequestManager(this.target.getView()).setRequest(this);
        throw new CancellationException("'ViewTarget.view' must be attached to a window.");
    }

    @Override // coil3.request.RequestDelegate
    public void start() {
        Lifecycle lifecycle;
        Lifecycle lifecycle2 = this.lifecycle;
        if (lifecycle2 != null) {
            lifecycle2.addObserver(this);
        }
        ViewTarget<?> viewTarget = this.target;
        if ((viewTarget instanceof LifecycleObserver) && (lifecycle = this.lifecycle) != null) {
            LifecyclesKt.removeAndAddObserver(lifecycle, (LifecycleObserver) viewTarget);
        }
        ViewTargetRequestManagerKt.getRequestManager(this.target.getView()).setRequest(this);
    }

    @Override // coil3.request.RequestDelegate
    public Object awaitStarted(Continuation<? super Unit> continuation) {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle == null) {
            return Unit.INSTANCE;
        }
        Object objAwaitStarted = LifecyclesKt.awaitStarted(lifecycle, continuation);
        return objAwaitStarted == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitStarted : Unit.INSTANCE;
    }

    @Override // coil3.request.RequestDelegate
    public void dispose() {
        Lifecycle lifecycle;
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
        ViewTarget<?> viewTarget = this.target;
        if ((viewTarget instanceof LifecycleObserver) && (lifecycle = this.lifecycle) != null) {
            lifecycle.removeObserver((LifecycleObserver) viewTarget);
        }
        Lifecycle lifecycle2 = this.lifecycle;
        if (lifecycle2 != null) {
            lifecycle2.removeObserver(this);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onDestroy(LifecycleOwner owner) {
        ViewTargetRequestManagerKt.getRequestManager(this.target.getView()).dispose();
    }
}
