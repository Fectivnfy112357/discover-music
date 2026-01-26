package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutUpdateAnimation.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0002J7\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\bH\u0010¢\u0006\u0002\b\fJ\r\u0010\r\u001a\u00020\u000eH\u0010¢\u0006\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lcom/facebook/react/uimanager/layoutanimation/LayoutUpdateAnimation;", "Lcom/facebook/react/uimanager/layoutanimation/AbstractLayoutAnimation;", "()V", "createAnimationImpl", "Landroid/view/animation/Animation;", "view", "Landroid/view/View;", "x", "", "y", "width", "height", "createAnimationImpl$ReactAndroid_release", "isValid", "", "isValid$ReactAndroid_release", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final Companion Companion = new Companion(null);
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    /* renamed from: isValid$ReactAndroid_release, reason: merged with bridge method [inline-methods] */
    public boolean isValid() {
        return this.mDurationMs > 0;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    /* renamed from: createAnimationImpl$ReactAndroid_release, reason: merged with bridge method [inline-methods] */
    public Animation createAnimationImpl(View view, int x, int y, int width, int height) {
        Intrinsics.checkNotNullParameter(view, "view");
        boolean z = (((int) view.getX()) == x && ((int) view.getY()) == y) ? false : true;
        boolean z2 = (view.getWidth() == width && view.getHeight() == height) ? false : true;
        if (z || z2) {
            return new PositionAndSizeAnimation(view, x, y, width, height);
        }
        return null;
    }

    /* compiled from: LayoutUpdateAnimation.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/uimanager/layoutanimation/LayoutUpdateAnimation$Companion;", "", "()V", "USE_TRANSLATE_ANIMATION", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
