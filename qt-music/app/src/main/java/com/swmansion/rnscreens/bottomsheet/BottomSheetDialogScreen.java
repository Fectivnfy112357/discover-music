package com.swmansion.rnscreens.bottomsheet;

import android.content.Context;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.swmansion.rnscreens.ScreenModalFragment;
import com.umeng.analytics.pro.f;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: BottomSheetDialogScreen.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/BottomSheetDialogScreen;", "Lcom/google/android/material/bottomsheet/BottomSheetDialog;", f.X, "Landroid/content/Context;", "fragment", "Lcom/swmansion/rnscreens/ScreenModalFragment;", "(Landroid/content/Context;Lcom/swmansion/rnscreens/ScreenModalFragment;)V", "fragmentRef", "Ljava/lang/ref/WeakReference;", "cancel", "", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class BottomSheetDialogScreen extends BottomSheetDialog {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = Reflection.getOrCreateKotlinClass(BottomSheetDialogScreen.class).getSimpleName();
    private final WeakReference<ScreenModalFragment> fragmentRef;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BottomSheetDialogScreen(Context context, ScreenModalFragment fragment) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragmentRef = new WeakReference<>(fragment);
    }

    @Override // com.google.android.material.bottomsheet.BottomSheetDialog, android.app.Dialog, android.content.DialogInterface
    public void cancel() {
        ScreenModalFragment screenModalFragment = this.fragmentRef.get();
        Intrinsics.checkNotNull(screenModalFragment);
        screenModalFragment.dismissFromContainer();
        show();
    }

    /* compiled from: BottomSheetDialogScreen.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/BottomSheetDialogScreen$Companion;", "", "()V", AbstractID3v1Tag.TAG, "", "getTAG", "()Ljava/lang/String;", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return BottomSheetDialogScreen.TAG;
        }
    }
}
