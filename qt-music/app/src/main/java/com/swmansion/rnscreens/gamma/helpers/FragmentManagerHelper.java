package com.swmansion.rnscreens.gamma.helpers;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.facebook.react.ReactRootView;
import com.swmansion.rnscreens.gamma.common.FragmentProviding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: FragmentManagerHelper.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\n"}, d2 = {"Lcom/swmansion/rnscreens/gamma/helpers/FragmentManagerHelper;", "", "()V", "findFragmentManagerForView", "Landroidx/fragment/app/FragmentManager;", "view", "Landroid/view/ViewGroup;", "resolveFragmentManagerForReactRootView", "rootView", "Lcom/facebook/react/ReactRootView;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FragmentManagerHelper {
    public static final FragmentManagerHelper INSTANCE = new FragmentManagerHelper();

    private FragmentManagerHelper() {
    }

    public final FragmentManager findFragmentManagerForView(ViewGroup view) {
        boolean z;
        Intrinsics.checkNotNullParameter(view, "view");
        Object obj = view;
        while (true) {
            z = obj instanceof ReactRootView;
            if (!z && !(obj instanceof FragmentProviding)) {
                ViewGroup viewGroup = (ViewParent) obj;
                if (viewGroup.getParent() == null) {
                    break;
                }
                Object parent = viewGroup.getParent();
                Intrinsics.checkNotNullExpressionValue(parent, "getParent(...)");
                obj = parent;
            } else {
                break;
            }
        }
        if (obj instanceof FragmentProviding) {
            Fragment associatedFragment = ((FragmentProviding) obj).getAssociatedFragment();
            if (associatedFragment == null) {
                throw new IllegalStateException(("[RNScreens] Parent fragment providing view " + obj + " returned nullish fragment").toString());
            }
            return associatedFragment.getChildFragmentManager();
        }
        if (!z) {
            throw new IllegalStateException(("[RNScreens] Expected parent to be a ReactRootView, instead found: " + obj.getClass().getName()).toString());
        }
        return resolveFragmentManagerForReactRootView((ReactRootView) obj);
    }

    private final FragmentManager resolveFragmentManagerForReactRootView(ReactRootView rootView) {
        boolean z;
        Context context = rootView.getContext();
        while (true) {
            z = context instanceof FragmentActivity;
            if (z || !(context instanceof ContextWrapper)) {
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (!z) {
            throw new IllegalStateException("[RNScreens] In order to use react-native-screens components your app's activity need to extend ReactActivity".toString());
        }
        FragmentActivity fragmentActivity = (FragmentActivity) context;
        if (fragmentActivity.getSupportFragmentManager().getFragments().isEmpty()) {
            return fragmentActivity.getSupportFragmentManager();
        }
        try {
            return FragmentManager.findFragment(rootView).getChildFragmentManager();
        } catch (IllegalStateException unused) {
            return fragmentActivity.getSupportFragmentManager();
        }
    }
}
