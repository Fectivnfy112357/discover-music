package com.swmansion.rnscreens.bottomsheet;

import android.view.View;
import com.swmansion.rnscreens.Screen;
import com.swmansion.rnscreens.ScreenContentWrapper;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SheetUtils.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0004\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0004¨\u0006\u0007"}, d2 = {"isLaidOutOrHasCachedLayout", "", "Landroid/view/View;", "isSheetFitToContents", "Lcom/swmansion/rnscreens/Screen;", "requiresEnterTransitionPostponing", "usesFormSheetPresentation", "react-native-screens_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SheetUtilsKt {
    public static final boolean isSheetFitToContents(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        return screen.getStackPresentation() == Screen.StackPresentation.FORM_SHEET && screen.getSheetDetents().size() == 1 && ((Number) CollectionsKt.first((List) screen.getSheetDetents())).doubleValue() == -1.0d;
    }

    public static final boolean usesFormSheetPresentation(Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "<this>");
        return screen.getStackPresentation() == Screen.StackPresentation.FORM_SHEET;
    }

    public static final boolean requiresEnterTransitionPostponing(Screen screen) {
        ScreenContentWrapper contentWrapper;
        Intrinsics.checkNotNullParameter(screen, "<this>");
        if (usesFormSheetPresentation(screen)) {
            return (isLaidOutOrHasCachedLayout(screen) && (contentWrapper = screen.getContentWrapper()) != null && isLaidOutOrHasCachedLayout(contentWrapper)) ? false : true;
        }
        return false;
    }

    public static final boolean isLaidOutOrHasCachedLayout(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        return view.isLaidOut() || view.getHeight() > 0 || view.getWidth() > 0;
    }
}
