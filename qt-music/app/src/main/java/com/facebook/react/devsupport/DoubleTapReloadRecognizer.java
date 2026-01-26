package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DoubleTapReloadRecognizer.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/facebook/react/devsupport/DoubleTapReloadRecognizer;", "", "()V", "doRefresh", "", "didDoubleTapR", "keyCode", "", "view", "Landroid/view/View;", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DoubleTapReloadRecognizer {
    private static final Companion Companion = new Companion(null);
    private static final long DOUBLE_TAP_DELAY = 200;
    private boolean doRefresh;

    public final boolean didDoubleTapR(int keyCode, View view) {
        if (keyCode == 46 && !(view instanceof EditText)) {
            if (this.doRefresh) {
                this.doRefresh = false;
                return true;
            }
            this.doRefresh = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.facebook.react.devsupport.DoubleTapReloadRecognizer$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    DoubleTapReloadRecognizer.didDoubleTapR$lambda$0(this.f$0);
                }
            }, DOUBLE_TAP_DELAY);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void didDoubleTapR$lambda$0(DoubleTapReloadRecognizer this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.doRefresh = false;
    }

    /* compiled from: DoubleTapReloadRecognizer.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/facebook/react/devsupport/DoubleTapReloadRecognizer$Companion;", "", "()V", "DOUBLE_TAP_DELAY", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
