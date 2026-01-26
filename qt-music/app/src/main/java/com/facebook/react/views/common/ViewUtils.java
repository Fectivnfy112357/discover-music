package com.facebook.react.views.common;

import android.view.View;
import com.facebook.react.R;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: ViewUtils.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/views/common/ViewUtils;", "", "()V", "getTestId", "", "view", "Landroid/view/View;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ViewUtils {
    public static final ViewUtils INSTANCE = new ViewUtils();

    private ViewUtils() {
    }

    @JvmStatic
    public static final String getTestId(View view) {
        Object tag = view != null ? view.getTag(R.id.react_test_id) : null;
        if (tag instanceof String) {
            return (String) tag;
        }
        return null;
    }
}
