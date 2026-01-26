package coil3.request;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* compiled from: RequestDelegate.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0081@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Lcoil3/request/BaseRequestDelegate;", "Lcoil3/request/RequestDelegate;", "job", "Lkotlinx/coroutines/Job;", "constructor-impl", "(Lkotlinx/coroutines/Job;)Lkotlinx/coroutines/Job;", "dispose", "", "dispose-impl", "(Lkotlinx/coroutines/Job;)V", "equals", "", "other", "", "hashCode", "", "toString", "", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class BaseRequestDelegate implements RequestDelegate {
    private final Job job;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BaseRequestDelegate m252boximpl(Job job) {
        return new BaseRequestDelegate(job);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static Job m253constructorimpl(Job job) {
        return job;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m255equalsimpl(Job job, Object obj) {
        return (obj instanceof BaseRequestDelegate) && Intrinsics.areEqual(job, ((BaseRequestDelegate) obj).getJob());
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m256equalsimpl0(Job job, Job job2) {
        return Intrinsics.areEqual(job, job2);
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m257hashCodeimpl(Job job) {
        return job.hashCode();
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m258toStringimpl(Job job) {
        return "BaseRequestDelegate(job=" + job + ')';
    }

    public boolean equals(Object other) {
        return m255equalsimpl(this.job, other);
    }

    public int hashCode() {
        return m257hashCodeimpl(this.job);
    }

    public String toString() {
        return m258toStringimpl(this.job);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ Job getJob() {
        return this.job;
    }

    private /* synthetic */ BaseRequestDelegate(Job job) {
        this.job = job;
    }

    @Override // coil3.request.RequestDelegate
    public void dispose() {
        m254disposeimpl(this.job);
    }

    /* renamed from: dispose-impl, reason: not valid java name */
    public static void m254disposeimpl(Job job) {
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }
}
