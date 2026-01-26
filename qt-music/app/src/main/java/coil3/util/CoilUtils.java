package coil3.util;

import android.view.View;
import coil3.request.ImageResult;
import coil3.request.ViewTargetRequestManagerKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: CoilUtils.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\n"}, d2 = {"Lcoil3/util/CoilUtils;", "", "<init>", "()V", "dispose", "", "view", "Landroid/view/View;", "result", "Lcoil3/request/ImageResult;", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CoilUtils {
    public static final CoilUtils INSTANCE = new CoilUtils();

    private CoilUtils() {
    }

    @JvmStatic
    public static final void dispose(View view) {
        ViewTargetRequestManagerKt.getRequestManager(view).dispose();
    }

    @JvmStatic
    public static final ImageResult result(View view) {
        return ViewTargetRequestManagerKt.getRequestManager(view).getResult();
    }
}
