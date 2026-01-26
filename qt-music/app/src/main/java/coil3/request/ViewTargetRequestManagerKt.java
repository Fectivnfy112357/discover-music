package coil3.request;

import android.view.View;
import coil3.core.R;
import kotlin.Metadata;

/* compiled from: ViewTargetRequestManager.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"requestManager", "Lcoil3/request/ViewTargetRequestManager;", "Landroid/view/View;", "getRequestManager", "(Landroid/view/View;)Lcoil3/request/ViewTargetRequestManager;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ViewTargetRequestManagerKt {
    public static final ViewTargetRequestManager getRequestManager(View view) {
        Object tag = view.getTag(R.id.coil3_request_manager);
        ViewTargetRequestManager viewTargetRequestManager = tag instanceof ViewTargetRequestManager ? (ViewTargetRequestManager) tag : null;
        if (viewTargetRequestManager == null) {
            synchronized (view) {
                Object tag2 = view.getTag(R.id.coil3_request_manager);
                ViewTargetRequestManager viewTargetRequestManager2 = tag2 instanceof ViewTargetRequestManager ? (ViewTargetRequestManager) tag2 : null;
                if (viewTargetRequestManager2 != null) {
                    viewTargetRequestManager = viewTargetRequestManager2;
                } else {
                    viewTargetRequestManager = new ViewTargetRequestManager(view);
                    view.addOnAttachStateChangeListener(viewTargetRequestManager);
                    view.setTag(R.id.coil3_request_manager, viewTargetRequestManager);
                }
            }
        }
        return viewTargetRequestManager;
    }
}
