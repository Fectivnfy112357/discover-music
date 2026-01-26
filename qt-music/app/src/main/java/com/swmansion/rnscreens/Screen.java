package com.swmansion.rnscreens;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.facebook.react.bridge.GuardedRunnable;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerHelper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.swmansion.rnscreens.ScreenContentWrapper;
import com.swmansion.rnscreens.bottomsheet.BottomSheetBehaviorExtKt;
import com.swmansion.rnscreens.bottomsheet.SheetDelegate;
import com.swmansion.rnscreens.bottomsheet.SheetUtilsKt;
import com.swmansion.rnscreens.events.HeaderHeightChangeEvent;
import com.swmansion.rnscreens.events.SheetDetentChangedEvent;
import com.swmansion.rnscreens.ext.FragmentExtKt;
import com.swmansion.rnscreens.ext.ViewExtKt;
import com.swmansion.rnscreens.gamma.common.FragmentProviding;
import com.umeng.analytics.pro.bm;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.id3.ID3v22Frames;

/* compiled from: Screen.kt */
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\u0010\u0006\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0007\u0018\u0000 á\u00012\u00020\u00012\u00020\u00022\u00020\u0003:\fà\u0001á\u0001â\u0001ã\u0001ä\u0001å\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010¢\u0001\u001a\u00030£\u00012\u0007\u0010¤\u0001\u001a\u00020NJ\u001a\u0010¥\u0001\u001a\u00030£\u00012\u000e\u0010\f\u001a\n\u0012\u0005\u0012\u00030§\u00010¦\u0001H\u0014J\u001a\u0010¨\u0001\u001a\u00030£\u00012\u000e\u0010\f\u001a\n\u0012\u0005\u0012\u00030§\u00010¦\u0001H\u0014J%\u0010©\u0001\u001a\u00030£\u00012\u0007\u0010ª\u0001\u001a\u00020N2\u0007\u0010«\u0001\u001a\u00020N2\u0007\u0010¬\u0001\u001a\u00020NH\u0002J\u001c\u0010\u00ad\u0001\u001a\u00030£\u00012\u0007\u0010®\u0001\u001a\u00020N2\u0007\u0010¯\u0001\u001a\u00020,H\u0002J\b\u0010°\u0001\u001a\u00030£\u0001J\u0014\u0010±\u0001\u001a\u00030£\u00012\b\u0010²\u0001\u001a\u00030³\u0001H\u0002J\u000b\u0010´\u0001\u001a\u0004\u0018\u00010\u001eH\u0016J\u0013\u0010µ\u0001\u001a\u00020,2\b\u0010¶\u0001\u001a\u00030³\u0001H\u0002J\u0007\u0010·\u0001\u001a\u00020,J\u0013\u0010¸\u0001\u001a\u00030£\u00012\u0007\u0010¹\u0001\u001a\u00020NH\u0002J\n\u0010º\u0001\u001a\u00030£\u0001H\u0014J\u0019\u0010»\u0001\u001a\u00030£\u00012\u0007\u0010¼\u0001\u001a\u00020,H\u0000¢\u0006\u0003\b½\u0001J7\u0010¾\u0001\u001a\u00030£\u00012\u0007\u0010¿\u0001\u001a\u00020,2\u0007\u0010À\u0001\u001a\u00020N2\u0007\u0010Á\u0001\u001a\u00020N2\u0007\u0010Â\u0001\u001a\u00020N2\u0007\u0010Ã\u0001\u001a\u00020NH\u0016J\u0010\u0010Ä\u0001\u001a\u00030£\u0001H\u0000¢\u0006\u0003\bÅ\u0001J7\u0010Æ\u0001\u001a\u00030£\u00012\u0007\u0010¿\u0001\u001a\u00020,2\u0007\u0010Ç\u0001\u001a\u00020N2\u0007\u0010È\u0001\u001a\u00020N2\u0007\u0010É\u0001\u001a\u00020N2\u0007\u0010Ê\u0001\u001a\u00020NH\u0014J\u0010\u0010Ë\u0001\u001a\u00030£\u0001H\u0000¢\u0006\u0003\bÌ\u0001J\"\u0010Í\u0001\u001a\u00030£\u00012\u0007\u0010®\u0001\u001a\u00020N2\u0007\u0010¯\u0001\u001a\u00020,H\u0000¢\u0006\u0003\bÎ\u0001J\u0015\u0010Ï\u0001\u001a\u00020,2\n\u0010Ð\u0001\u001a\u0005\u0018\u00010Ñ\u0001H\u0017J\u0011\u0010Ò\u0001\u001a\u00030£\u00012\u0007\u0010Ó\u0001\u001a\u00020\u0013J\u0010\u0010Ô\u0001\u001a\u00030£\u00012\u0006\u0010\t\u001a\u00020\bJ\u001f\u0010Õ\u0001\u001a\u00030£\u00012\u0007\u0010Ö\u0001\u001a\u00020N2\n\u0010×\u0001\u001a\u0005\u0018\u00010Ø\u0001H\u0016J\u0012\u0010Ù\u0001\u001a\u00030£\u00012\b\u0010g\u001a\u0004\u0018\u00010bJ\u0011\u0010Ú\u0001\u001a\u00030£\u00012\u0007\u0010Û\u0001\u001a\u00020,J\b\u0010Ü\u0001\u001a\u00030£\u0001J\u0016\u0010Ý\u0001\u001a\u00030£\u00012\n\u0010²\u0001\u001a\u0005\u0018\u00010³\u0001H\u0002J\n\u0010Þ\u0001\u001a\u00030£\u0001H\u0002J\u001c\u0010ß\u0001\u001a\u00030£\u00012\u0007\u0010ª\u0001\u001a\u00020N2\u0007\u0010«\u0001\u001a\u00020NH\u0002R\"\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R(\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0013\u0010'\u001a\u0004\u0018\u00010(8F¢\u0006\u0006\u001a\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010-\"\u0004\b1\u0010/R\u0014\u00102\u001a\u00020,8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u0010-R*\u00104\u001a\u0004\u0018\u00010,2\b\u00103\u001a\u0004\u0018\u00010,@FX\u0086\u000e¢\u0006\u0010\n\u0002\u00108\u001a\u0004\b4\u00105\"\u0004\b6\u00107R2\u0010:\u001a\u0004\u0018\u00010,2\b\u00109\u001a\u0004\u0018\u00010,8\u0006@FX\u0087\u000e¢\u0006\u0016\n\u0002\u00108\u0012\u0004\b;\u0010<\u001a\u0004\b:\u00105\"\u0004\b=\u00107R\u001a\u0010>\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010-\"\u0004\b?\u0010/R\u001e\u0010@\u001a\u0004\u0018\u00010,X\u0086\u000e¢\u0006\u0010\n\u0002\u00108\u001a\u0004\b@\u00105\"\u0004\bA\u00107R*\u0010C\u001a\u0004\u0018\u00010,2\b\u0010B\u001a\u0004\u0018\u00010,@FX\u0086\u000e¢\u0006\u0010\n\u0002\u00108\u001a\u0004\bC\u00105\"\u0004\bD\u00107R2\u0010F\u001a\u0004\u0018\u00010,2\b\u0010E\u001a\u0004\u0018\u00010,8\u0006@FX\u0087\u000e¢\u0006\u0016\n\u0002\u00108\u0012\u0004\bG\u0010<\u001a\u0004\bF\u00105\"\u0004\bH\u00107R\u000e\u0010I\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010J\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010-\"\u0004\bL\u0010/R2\u0010M\u001a\u0004\u0018\u00010N2\b\u0010M\u001a\u0004\u0018\u00010N8\u0006@FX\u0087\u000e¢\u0006\u0016\n\u0002\u0010T\u0012\u0004\bO\u0010<\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u0013\u0010W\u001a\u0004\u0018\u00010X8F¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u001a\u0010[\u001a\u00020\\X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u001c\u0010a\u001a\u0004\u0018\u00010bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bc\u0010d\"\u0004\be\u0010fR$\u0010g\u001a\u0004\u0018\u00010N2\b\u0010\u0007\u001a\u0004\u0018\u00010N@BX\u0086\u000e¢\u0006\n\n\u0002\u0010T\u001a\u0004\bh\u0010QR\u0019\u0010i\u001a\n\u0012\u0004\u0012\u00020\u0000\u0018\u00010j8F¢\u0006\u0006\u001a\u0004\bk\u0010lR\u001a\u0010m\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010-\"\u0004\bo\u0010/R$\u0010q\u001a\u00020p2\u0006\u0010\u0016\u001a\u00020p@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR \u0010v\u001a\b\u0012\u0004\u0012\u00020x0wX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R\u001a\u0010}\u001a\u00020pX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b~\u0010s\"\u0004\b\u007f\u0010uR\u001d\u0010\u0080\u0001\u001a\u00020,X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0081\u0001\u0010-\"\u0005\b\u0082\u0001\u0010/R\u001f\u0010\u0083\u0001\u001a\u00020NX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001\"\u0006\b\u0086\u0001\u0010\u0087\u0001R\u001f\u0010\u0088\u0001\u001a\u00020NX\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0089\u0001\u0010\u0085\u0001\"\u0006\b\u008a\u0001\u0010\u0087\u0001R\u001d\u0010\u008b\u0001\u001a\u00020,X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u008c\u0001\u0010-\"\u0005\b\u008d\u0001\u0010/R\u000f\u0010\u008e\u0001\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001\"\u0006\b\u0093\u0001\u0010\u0094\u0001R \u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u0006\b\u0099\u0001\u0010\u009a\u0001R7\u0010\u009b\u0001\u001a\u0004\u0018\u00010N2\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010N8\u0006@FX\u0087\u000e¢\u0006\u0019\n\u0002\u0010T\u0012\u0005\b\u009c\u0001\u0010<\u001a\u0005\b\u009d\u0001\u0010Q\"\u0005\b\u009e\u0001\u0010SR,\u0010\u009f\u0001\u001a\u0004\u0018\u00010b2\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010b@FX\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b \u0001\u0010d\"\u0005\b¡\u0001\u0010f¨\u0006æ\u0001"}, d2 = {"Lcom/swmansion/rnscreens/Screen;", "Lcom/swmansion/rnscreens/FabricEnabledViewGroup;", "Lcom/swmansion/rnscreens/ScreenContentWrapper$OnLayoutCallback;", "Lcom/swmansion/rnscreens/gamma/common/FragmentProviding;", "reactContext", "Lcom/facebook/react/uimanager/ThemedReactContext;", "(Lcom/facebook/react/uimanager/ThemedReactContext;)V", "<set-?>", "Lcom/swmansion/rnscreens/Screen$ActivityState;", "activityState", "getActivityState", "()Lcom/swmansion/rnscreens/Screen$ActivityState;", "container", "Lcom/swmansion/rnscreens/ScreenContainer;", "getContainer", "()Lcom/swmansion/rnscreens/ScreenContainer;", "setContainer", "(Lcom/swmansion/rnscreens/ScreenContainer;)V", "contentWrapper", "Lcom/swmansion/rnscreens/ScreenContentWrapper;", "getContentWrapper", "()Lcom/swmansion/rnscreens/ScreenContentWrapper;", "value", "Lcom/swmansion/rnscreens/ScreenFooter;", "footer", "getFooter", "()Lcom/swmansion/rnscreens/ScreenFooter;", "setFooter", "(Lcom/swmansion/rnscreens/ScreenFooter;)V", "fragment", "Landroidx/fragment/app/Fragment;", "getFragment", "()Landroidx/fragment/app/Fragment;", "fragmentWrapper", "Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "getFragmentWrapper", "()Lcom/swmansion/rnscreens/ScreenFragmentWrapper;", "setFragmentWrapper", "(Lcom/swmansion/rnscreens/ScreenFragmentWrapper;)V", "headerConfig", "Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "getHeaderConfig", "()Lcom/swmansion/rnscreens/ScreenStackHeaderConfig;", "isBeingRemoved", "", "()Z", "setBeingRemoved", "(Z)V", "isGestureEnabled", "setGestureEnabled", "isNativeStackScreen", "navigationBarHidden", "isNavigationBarHidden", "()Ljava/lang/Boolean;", "setNavigationBarHidden", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "navigationBarTranslucent", "isNavigationBarTranslucent", "isNavigationBarTranslucent$annotations", "()V", "setNavigationBarTranslucent", "isSheetGrabberVisible", "setSheetGrabberVisible", "isStatusBarAnimated", "setStatusBarAnimated", "statusBarHidden", "isStatusBarHidden", "setStatusBarHidden", "statusBarTranslucent", "isStatusBarTranslucent", "isStatusBarTranslucent$annotations", "setStatusBarTranslucent", "isTransitioning", "nativeBackButtonDismissalEnabled", "getNativeBackButtonDismissalEnabled", "setNativeBackButtonDismissalEnabled", "navigationBarColor", "", "getNavigationBarColor$annotations", "getNavigationBarColor", "()Ljava/lang/Integer;", "setNavigationBarColor", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getReactContext", "()Lcom/facebook/react/uimanager/ThemedReactContext;", "reactEventDispatcher", "Lcom/facebook/react/uimanager/events/EventDispatcher;", "getReactEventDispatcher", "()Lcom/facebook/react/uimanager/events/EventDispatcher;", "replaceAnimation", "Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "getReplaceAnimation", "()Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "setReplaceAnimation", "(Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;)V", "screenId", "", "getScreenId", "()Ljava/lang/String;", "setScreenId", "(Ljava/lang/String;)V", "screenOrientation", "getScreenOrientation", "sheetBehavior", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "getSheetBehavior", "()Lcom/google/android/material/bottomsheet/BottomSheetBehavior;", "sheetClosesOnTouchOutside", "getSheetClosesOnTouchOutside", "setSheetClosesOnTouchOutside", "", "sheetCornerRadius", "getSheetCornerRadius", "()F", "setSheetCornerRadius", "(F)V", "sheetDetents", "", "", "getSheetDetents", "()Ljava/util/List;", "setSheetDetents", "(Ljava/util/List;)V", "sheetElevation", "getSheetElevation", "setSheetElevation", "sheetExpandsWhenScrolledToEdge", "getSheetExpandsWhenScrolledToEdge", "setSheetExpandsWhenScrolledToEdge", "sheetInitialDetentIndex", "getSheetInitialDetentIndex", "()I", "setSheetInitialDetentIndex", "(I)V", "sheetLargestUndimmedDetentIndex", "getSheetLargestUndimmedDetentIndex", "setSheetLargestUndimmedDetentIndex", "shouldTriggerPostponedTransitionAfterLayout", "getShouldTriggerPostponedTransitionAfterLayout", "setShouldTriggerPostponedTransitionAfterLayout", "shouldUpdateSheetCornerRadius", "stackAnimation", "Lcom/swmansion/rnscreens/Screen$StackAnimation;", "getStackAnimation", "()Lcom/swmansion/rnscreens/Screen$StackAnimation;", "setStackAnimation", "(Lcom/swmansion/rnscreens/Screen$StackAnimation;)V", "stackPresentation", "Lcom/swmansion/rnscreens/Screen$StackPresentation;", "getStackPresentation", "()Lcom/swmansion/rnscreens/Screen$StackPresentation;", "setStackPresentation", "(Lcom/swmansion/rnscreens/Screen$StackPresentation;)V", "statusBarColor", "getStatusBarColor$annotations", "getStatusBarColor", "setStatusBarColor", "statusBarStyle", "getStatusBarStyle", "setStatusBarStyle", "changeAccessibilityMode", "", "mode", "dispatchRestoreInstanceState", "Landroid/util/SparseArray;", "Landroid/os/Parcelable;", "dispatchSaveInstanceState", "dispatchShadowStateUpdate", "width", "height", "offsetY", "dispatchSheetDetentChanged", "detentIndex", "isStable", "endRemovalTransition", "endTransitionRecursive", "parent", "Landroid/view/ViewGroup;", "getAssociatedFragment", "hasWebView", "viewGroup", "isTranslucent", "notifyHeaderHeightChange", "headerHeight", "onAttachedToWindow", "onBottomSheetBehaviorDidLayout", "coordinatorLayoutDidChange", "onBottomSheetBehaviorDidLayout$react_native_screens_release", "onContentWrapperLayout", "changed", "left", ViewProps.TOP, "right", ViewProps.BOTTOM, "onFinalizePropsUpdate", "onFinalizePropsUpdate$react_native_screens_release", ViewProps.ON_LAYOUT, CmcdData.Factory.STREAM_TYPE_LIVE, bm.aM, "r", "b", "onSheetCornerRadiusChange", "onSheetCornerRadiusChange$react_native_screens_release", "onSheetDetentChanged", "onSheetDetentChanged$react_native_screens_release", "onTouchEvent", "event", "Landroid/view/MotionEvent;", "registerLayoutCallbackForWrapper", "wrapper", "setActivityState", "setLayerType", "layerType", "paint", "Landroid/graphics/Paint;", "setScreenOrientation", "setTransitioning", "transitioning", "startRemovalTransition", "startTransitionRecursive", "triggerPostponedEnterTransitionIfNeeded", "updateScreenSizePaper", "ActivityState", "Companion", "ReplaceAnimation", "StackAnimation", "StackPresentation", "WindowTraits", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class Screen extends FabricEnabledViewGroup implements ScreenContentWrapper.OnLayoutCallback, FragmentProviding {
    public static final double SHEET_FIT_TO_CONTENTS = -1.0d;
    public static final String TAG = "Screen";
    private ActivityState activityState;
    private ScreenContainer container;
    private ScreenFooter footer;
    private ScreenFragmentWrapper fragmentWrapper;
    private boolean isBeingRemoved;
    private boolean isGestureEnabled;
    private Boolean isNavigationBarHidden;
    private Boolean isNavigationBarTranslucent;
    private boolean isSheetGrabberVisible;
    private Boolean isStatusBarAnimated;
    private Boolean isStatusBarHidden;
    private Boolean isStatusBarTranslucent;
    private boolean isTransitioning;
    private boolean nativeBackButtonDismissalEnabled;
    private Integer navigationBarColor;
    private final ThemedReactContext reactContext;
    private ReplaceAnimation replaceAnimation;
    private String screenId;
    private Integer screenOrientation;
    private boolean sheetClosesOnTouchOutside;
    private float sheetCornerRadius;
    private List<Double> sheetDetents;
    private float sheetElevation;
    private boolean sheetExpandsWhenScrolledToEdge;
    private int sheetInitialDetentIndex;
    private int sheetLargestUndimmedDetentIndex;
    private boolean shouldTriggerPostponedTransitionAfterLayout;
    private boolean shouldUpdateSheetCornerRadius;
    private StackAnimation stackAnimation;
    private StackPresentation stackPresentation;
    private Integer statusBarColor;
    private String statusBarStyle;

    /* compiled from: Screen.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StackPresentation.values().length];
            try {
                iArr[StackPresentation.TRANSPARENT_MODAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[StackPresentation.FORM_SHEET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Deprecated(message = "For all apps targeting Android SDK 35 or above edge-to-edge is enabled by default. ")
    public static /* synthetic */ void getNavigationBarColor$annotations() {
    }

    @Deprecated(message = "For apps targeting SDK 35 or above this prop has no effect because edge-to-edge is enabled by default and the status bar is always translucent.")
    public static /* synthetic */ void getStatusBarColor$annotations() {
    }

    @Deprecated(message = "For all apps targeting Android SDK 35 or above edge-to-edge is enabled by default. ")
    public static /* synthetic */ void isNavigationBarTranslucent$annotations() {
    }

    @Deprecated(message = "For apps targeting SDK 35 or above this prop has no effect because edge-to-edge is enabled by default and the status bar is always translucent.")
    public static /* synthetic */ void isStatusBarTranslucent$annotations() {
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        Intrinsics.checkNotNullParameter(container, "container");
    }

    @Override // android.view.View
    public void setLayerType(int layerType, Paint paint) {
    }

    public final ThemedReactContext getReactContext() {
        return this.reactContext;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Screen(ThemedReactContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
        this.stackPresentation = StackPresentation.PUSH;
        this.replaceAnimation = ReplaceAnimation.POP;
        this.stackAnimation = StackAnimation.DEFAULT;
        this.isGestureEnabled = true;
        this.sheetExpandsWhenScrolledToEdge = true;
        this.sheetDetents = CollectionsKt.mutableListOf(Double.valueOf(1.0d));
        this.sheetLargestUndimmedDetentIndex = -1;
        this.sheetClosesOnTouchOutside = true;
        this.sheetElevation = 24.0f;
        setLayoutParams(new WindowManager.LayoutParams(2));
        this.nativeBackButtonDismissalEnabled = true;
    }

    public final Fragment getFragment() {
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            return screenFragmentWrapper.getFragment();
        }
        return null;
    }

    public final BottomSheetBehavior<Screen> getSheetBehavior() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        CoordinatorLayout.LayoutParams layoutParams2 = layoutParams instanceof CoordinatorLayout.LayoutParams ? (CoordinatorLayout.LayoutParams) layoutParams : null;
        CoordinatorLayout.Behavior behavior = layoutParams2 != null ? layoutParams2.getBehavior() : null;
        if (behavior instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) behavior;
        }
        return null;
    }

    public final EventDispatcher getReactEventDispatcher() {
        return UIManagerHelper.getEventDispatcherForReactTag(this.reactContext, getId());
    }

    public final ScreenFragmentWrapper getFragmentWrapper() {
        return this.fragmentWrapper;
    }

    public final void setFragmentWrapper(ScreenFragmentWrapper screenFragmentWrapper) {
        this.fragmentWrapper = screenFragmentWrapper;
    }

    public final ScreenContainer getContainer() {
        return this.container;
    }

    public final void setContainer(ScreenContainer screenContainer) {
        this.container = screenContainer;
    }

    public final ActivityState getActivityState() {
        return this.activityState;
    }

    public final StackPresentation getStackPresentation() {
        return this.stackPresentation;
    }

    public final void setStackPresentation(StackPresentation stackPresentation) {
        Intrinsics.checkNotNullParameter(stackPresentation, "<set-?>");
        this.stackPresentation = stackPresentation;
    }

    public final ReplaceAnimation getReplaceAnimation() {
        return this.replaceAnimation;
    }

    public final void setReplaceAnimation(ReplaceAnimation replaceAnimation) {
        Intrinsics.checkNotNullParameter(replaceAnimation, "<set-?>");
        this.replaceAnimation = replaceAnimation;
    }

    public final StackAnimation getStackAnimation() {
        return this.stackAnimation;
    }

    public final void setStackAnimation(StackAnimation stackAnimation) {
        Intrinsics.checkNotNullParameter(stackAnimation, "<set-?>");
        this.stackAnimation = stackAnimation;
    }

    /* renamed from: isGestureEnabled, reason: from getter */
    public final boolean getIsGestureEnabled() {
        return this.isGestureEnabled;
    }

    public final void setGestureEnabled(boolean z) {
        this.isGestureEnabled = z;
    }

    public final Integer getScreenOrientation() {
        return this.screenOrientation;
    }

    public final String getScreenId() {
        return this.screenId;
    }

    public final void setScreenId(String str) {
        this.screenId = str;
    }

    /* renamed from: isStatusBarAnimated, reason: from getter */
    public final Boolean getIsStatusBarAnimated() {
        return this.isStatusBarAnimated;
    }

    public final void setStatusBarAnimated(Boolean bool) {
        this.isStatusBarAnimated = bool;
    }

    /* renamed from: isBeingRemoved, reason: from getter */
    public final boolean getIsBeingRemoved() {
        return this.isBeingRemoved;
    }

    public final void setBeingRemoved(boolean z) {
        this.isBeingRemoved = z;
    }

    /* renamed from: isSheetGrabberVisible, reason: from getter */
    public final boolean getIsSheetGrabberVisible() {
        return this.isSheetGrabberVisible;
    }

    public final void setSheetGrabberVisible(boolean z) {
        this.isSheetGrabberVisible = z;
    }

    public final float getSheetCornerRadius() {
        return this.sheetCornerRadius;
    }

    public final void setSheetCornerRadius(float f) {
        if (this.sheetCornerRadius == f) {
            return;
        }
        this.sheetCornerRadius = f;
        this.shouldUpdateSheetCornerRadius = true;
    }

    public final boolean getSheetExpandsWhenScrolledToEdge() {
        return this.sheetExpandsWhenScrolledToEdge;
    }

    public final void setSheetExpandsWhenScrolledToEdge(boolean z) {
        this.sheetExpandsWhenScrolledToEdge = z;
    }

    public final List<Double> getSheetDetents() {
        return this.sheetDetents;
    }

    public final void setSheetDetents(List<Double> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.sheetDetents = list;
    }

    public final int getSheetLargestUndimmedDetentIndex() {
        return this.sheetLargestUndimmedDetentIndex;
    }

    public final void setSheetLargestUndimmedDetentIndex(int i) {
        this.sheetLargestUndimmedDetentIndex = i;
    }

    public final int getSheetInitialDetentIndex() {
        return this.sheetInitialDetentIndex;
    }

    public final void setSheetInitialDetentIndex(int i) {
        this.sheetInitialDetentIndex = i;
    }

    public final boolean getSheetClosesOnTouchOutside() {
        return this.sheetClosesOnTouchOutside;
    }

    public final void setSheetClosesOnTouchOutside(boolean z) {
        this.sheetClosesOnTouchOutside = z;
    }

    public final float getSheetElevation() {
        return this.sheetElevation;
    }

    public final void setSheetElevation(float f) {
        this.sheetElevation = f;
    }

    public final boolean getShouldTriggerPostponedTransitionAfterLayout() {
        return this.shouldTriggerPostponedTransitionAfterLayout;
    }

    public final void setShouldTriggerPostponedTransitionAfterLayout(boolean z) {
        this.shouldTriggerPostponedTransitionAfterLayout = z;
    }

    public final ScreenFooter getFooter() {
        return this.footer;
    }

    public final void setFooter(ScreenFooter screenFooter) {
        BottomSheetBehavior<Screen> sheetBehavior;
        if (screenFooter == null && this.footer != null) {
            BottomSheetBehavior<Screen> sheetBehavior2 = getSheetBehavior();
            if (sheetBehavior2 != null) {
                ScreenFooter screenFooter2 = this.footer;
                Intrinsics.checkNotNull(screenFooter2);
                screenFooter2.unregisterWithSheetBehavior(sheetBehavior2);
            }
        } else if (screenFooter != null && (sheetBehavior = getSheetBehavior()) != null) {
            screenFooter.registerWithSheetBehavior(sheetBehavior);
        }
        this.footer = screenFooter;
    }

    private final boolean isNativeStackScreen() {
        return this.container instanceof ScreenStack;
    }

    @Override // com.swmansion.rnscreens.gamma.common.FragmentProviding
    public Fragment getAssociatedFragment() {
        return getFragment();
    }

    @Override // com.swmansion.rnscreens.ScreenContentWrapper.OnLayoutCallback
    public void onContentWrapperLayout(boolean changed, int left, int top, int right, int bottom) {
        BottomSheetBehavior<Screen> sheetBehavior;
        int i = bottom - top;
        if (SheetUtilsKt.usesFormSheetPresentation(this)) {
            if (SheetUtilsKt.isSheetFitToContents(this) && (sheetBehavior = getSheetBehavior()) != null) {
                BottomSheetBehaviorExtKt.useSingleDetent$default(sheetBehavior, Integer.valueOf(i), false, 2, null);
            }
            this.shouldTriggerPostponedTransitionAfterLayout = true;
            ViewGroup viewGroupParentAsViewGroup = ViewExtKt.parentAsViewGroup(this);
            if (viewGroupParentAsViewGroup == null || viewGroupParentAsViewGroup.isInLayout()) {
                return;
            }
            viewGroupParentAsViewGroup.requestLayout();
        }
    }

    public final void registerLayoutCallbackForWrapper(ScreenContentWrapper wrapper) {
        Intrinsics.checkNotNullParameter(wrapper, "wrapper");
        wrapper.setDelegate$react_native_screens_release(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed && isNativeStackScreen() && !SheetUtilsKt.usesFormSheetPresentation(this)) {
            dispatchShadowStateUpdate(r - l, b - t, t);
            notifyHeaderHeightChange(t);
        }
    }

    public final void onBottomSheetBehaviorDidLayout$react_native_screens_release(boolean coordinatorLayoutDidChange) {
        if (SheetUtilsKt.usesFormSheetPresentation(this) && isNativeStackScreen()) {
            if (coordinatorLayoutDidChange) {
                dispatchShadowStateUpdate(getWidth(), getHeight(), getTop());
            }
            ScreenFooter screenFooter = this.footer;
            if (screenFooter != null) {
                int left = getLeft();
                int top = getTop();
                int right = getRight();
                int bottom = getBottom();
                ScreenContainer screenContainer = this.container;
                Intrinsics.checkNotNull(screenContainer);
                screenFooter.onParentLayout(coordinatorLayoutDidChange, left, top, right, bottom, screenContainer.getHeight());
            }
            triggerPostponedEnterTransitionIfNeeded();
        }
    }

    private final void triggerPostponedEnterTransitionIfNeeded() {
        if (this.shouldTriggerPostponedTransitionAfterLayout) {
            this.shouldTriggerPostponedTransitionAfterLayout = false;
            Fragment fragment = getFragment();
            if (fragment != null) {
                fragment.startPostponedEnterTransition();
            }
        }
    }

    private final void updateScreenSizePaper(final int width, final int height) {
        ThemedReactContext themedReactContext = this.reactContext;
        themedReactContext.runOnNativeModulesQueueThread(new GuardedRunnable(themedReactContext.getExceptionHandler()) { // from class: com.swmansion.rnscreens.Screen.updateScreenSizePaper.1
            @Override // com.facebook.react.bridge.GuardedRunnable
            public void runGuarded() {
                UIManagerModule uIManagerModule = (UIManagerModule) Screen.this.getReactContext().getNativeModule(UIManagerModule.class);
                if (uIManagerModule != null) {
                    uIManagerModule.updateNodeSize(Screen.this.getId(), width, height);
                }
            }
        });
    }

    private final void dispatchShadowStateUpdate(int width, int height, int offsetY) {
        updateScreenSizePaper(width, height);
    }

    public final ScreenStackHeaderConfig getHeaderConfig() {
        View next;
        Iterator<View> it = ViewGroupKt.getChildren(this).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next instanceof ScreenStackHeaderConfig) {
                break;
            }
        }
        if (next instanceof ScreenStackHeaderConfig) {
            return (ScreenStackHeaderConfig) next;
        }
        return null;
    }

    public final ScreenContentWrapper getContentWrapper() {
        View next;
        Iterator<View> it = ViewGroupKt.getChildren(this).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next instanceof ScreenContentWrapper) {
                break;
            }
        }
        if (next instanceof ScreenContentWrapper) {
            return (ScreenContentWrapper) next;
        }
        return null;
    }

    public final void setTransitioning(boolean transitioning) {
        if (this.isTransitioning == transitioning) {
            return;
        }
        this.isTransitioning = transitioning;
        boolean zHasWebView = hasWebView(this);
        if (!zHasWebView || getLayerType() == 2) {
            super.setLayerType((!transitioning || zHasWebView) ? 0 : 2, null);
        }
    }

    public final boolean isTranslucent() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.stackPresentation.ordinal()];
        return i == 1 || i == 2;
    }

    private final boolean hasWebView(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof WebView) {
                return true;
            }
            if ((childAt instanceof ViewGroup) && hasWebView((ViewGroup) childAt)) {
                return true;
            }
        }
        return false;
    }

    public final void setActivityState(ActivityState activityState) {
        Intrinsics.checkNotNullParameter(activityState, "activityState");
        ActivityState activityState2 = this.activityState;
        if (activityState == activityState2) {
            return;
        }
        if ((this.container instanceof ScreenStack) && activityState2 != null) {
            Intrinsics.checkNotNull(activityState2);
            if (activityState.compareTo(activityState2) < 0) {
                throw new IllegalStateException("[RNScreens] activityState can only progress in NativeStack");
            }
        }
        this.activityState = activityState;
        ScreenContainer screenContainer = this.container;
        if (screenContainer != null) {
            screenContainer.onChildUpdate();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0082  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setScreenOrientation(java.lang.String r2) {
        /*
            r1 = this;
            if (r2 != 0) goto L6
            r2 = 0
            r1.screenOrientation = r2
            return
        L6:
            com.swmansion.rnscreens.ScreenWindowTraits r0 = com.swmansion.rnscreens.ScreenWindowTraits.INSTANCE
            r0.applyDidSetOrientation$react_native_screens_release()
            int r0 = r2.hashCode()
            switch(r0) {
                case -1894896954: goto L72;
                case 96673: goto L62;
                case 729267099: goto L53;
                case 1430647483: goto L44;
                case 1651658175: goto L35;
                case 1730732811: goto L25;
                case 2118770584: goto L14;
                default: goto L12;
            }
        L12:
            goto L82
        L14:
            java.lang.String r0 = "landscape_right"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L1e
            goto L82
        L1e:
            r2 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L25:
            java.lang.String r0 = "landscape_left"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L2e
            goto L82
        L2e:
            r2 = 8
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L35:
            java.lang.String r0 = "portrait_up"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L3e
            goto L82
        L3e:
            r2 = 1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L44:
            java.lang.String r0 = "landscape"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L4d
            goto L82
        L4d:
            r2 = 6
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L53:
            java.lang.String r0 = "portrait"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L5c
            goto L82
        L5c:
            r2 = 7
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L62:
            java.lang.String r0 = "all"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L6b
            goto L82
        L6b:
            r2 = 10
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L72:
            java.lang.String r0 = "portrait_down"
            boolean r2 = r2.equals(r0)
            if (r2 != 0) goto L7b
            goto L82
        L7b:
            r2 = 9
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L87
        L82:
            r2 = -1
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L87:
            r1.screenOrientation = r2
            com.swmansion.rnscreens.ScreenFragmentWrapper r2 = r1.fragmentWrapper
            if (r2 == 0) goto L96
            com.swmansion.rnscreens.ScreenWindowTraits r0 = com.swmansion.rnscreens.ScreenWindowTraits.INSTANCE
            android.app.Activity r2 = r2.tryGetActivity()
            r0.setOrientation$react_native_screens_release(r1, r2)
        L96:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swmansion.rnscreens.Screen.setScreenOrientation(java.lang.String):void");
    }

    public final void changeAccessibilityMode(int mode) {
        setImportantForAccessibility(mode);
        ScreenStackHeaderConfig headerConfig = getHeaderConfig();
        CustomToolbar toolbar = headerConfig != null ? headerConfig.getToolbar() : null;
        if (toolbar == null) {
            return;
        }
        toolbar.setImportantForAccessibility(mode);
    }

    public final String getStatusBarStyle() {
        return this.statusBarStyle;
    }

    public final void setStatusBarStyle(String str) {
        if (str != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.statusBarStyle = str;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setStyle$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    /* renamed from: isStatusBarHidden, reason: from getter */
    public final Boolean getIsStatusBarHidden() {
        return this.isStatusBarHidden;
    }

    public final void setStatusBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.isStatusBarHidden = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setHidden$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    /* renamed from: isStatusBarTranslucent, reason: from getter */
    public final Boolean getIsStatusBarTranslucent() {
        return this.isStatusBarTranslucent;
    }

    public final void setStatusBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.isStatusBarTranslucent = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setTranslucent$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final Integer getStatusBarColor() {
        return this.statusBarColor;
    }

    public final void setStatusBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetStatusBarAppearance$react_native_screens_release();
        }
        this.statusBarColor = num;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setColor$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity(), screenFragmentWrapper.tryGetContext());
        }
    }

    public final Integer getNavigationBarColor() {
        return this.navigationBarColor;
    }

    public final void setNavigationBarColor(Integer num) {
        if (num != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.navigationBarColor = num;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarColor$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    /* renamed from: isNavigationBarTranslucent, reason: from getter */
    public final Boolean getIsNavigationBarTranslucent() {
        return this.isNavigationBarTranslucent;
    }

    public final void setNavigationBarTranslucent(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.isNavigationBarTranslucent = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarTranslucent$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    /* renamed from: isNavigationBarHidden, reason: from getter */
    public final Boolean getIsNavigationBarHidden() {
        return this.isNavigationBarHidden;
    }

    public final void setNavigationBarHidden(Boolean bool) {
        if (bool != null) {
            ScreenWindowTraits.INSTANCE.applyDidSetNavigationBarAppearance$react_native_screens_release();
        }
        this.isNavigationBarHidden = bool;
        ScreenFragmentWrapper screenFragmentWrapper = this.fragmentWrapper;
        if (screenFragmentWrapper != null) {
            ScreenWindowTraits.INSTANCE.setNavigationBarHidden$react_native_screens_release(this, screenFragmentWrapper.tryGetActivity());
        }
    }

    public final boolean getNativeBackButtonDismissalEnabled() {
        return this.nativeBackButtonDismissalEnabled;
    }

    public final void setNativeBackButtonDismissalEnabled(boolean z) {
        this.nativeBackButtonDismissalEnabled = z;
    }

    public final void startRemovalTransition() {
        if (this.isBeingRemoved) {
            return;
        }
        this.isBeingRemoved = true;
        startTransitionRecursive(this);
    }

    public final void endRemovalTransition() {
        if (this.isBeingRemoved) {
            this.isBeingRemoved = false;
            endTransitionRecursive(this);
        }
    }

    private final void endTransitionRecursive(ViewGroup parent) {
        for (View view : ViewGroupKt.getChildren(parent)) {
            parent.endViewTransition(view);
            if (view instanceof ScreenStackHeaderConfig) {
                endTransitionRecursive(((ScreenStackHeaderConfig) view).getToolbar());
            }
            if (view instanceof ViewGroup) {
                endTransitionRecursive((ViewGroup) view);
            }
        }
    }

    private final void startTransitionRecursive(ViewGroup parent) {
        if (parent != null) {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = parent.getChildAt(i);
                if ((parent instanceof SwipeRefreshLayout) && (childAt instanceof ImageView)) {
                    parent.addView(new View(getContext()), i);
                } else if (childAt != null) {
                    Intrinsics.checkNotNull(childAt);
                    parent.startViewTransition(childAt);
                }
                if (childAt instanceof ScreenStackHeaderConfig) {
                    startTransitionRecursive(((ScreenStackHeaderConfig) childAt).getToolbar());
                }
                if (childAt instanceof ViewGroup) {
                    startTransitionRecursive((ViewGroup) childAt);
                }
            }
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (SheetUtilsKt.usesFormSheetPresentation(this)) {
            return true;
        }
        return super.onTouchEvent(event);
    }

    private final void notifyHeaderHeightChange(int headerHeight) {
        Context context = getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type com.facebook.react.bridge.ReactContext");
        ReactContext reactContext = (ReactContext) context;
        int surfaceId = UIManagerHelper.getSurfaceId(reactContext);
        EventDispatcher eventDispatcherForReactTag = UIManagerHelper.getEventDispatcherForReactTag(reactContext, getId());
        if (eventDispatcherForReactTag != null) {
            eventDispatcherForReactTag.dispatchEvent(new HeaderHeightChangeEvent(surfaceId, getId(), headerHeight));
        }
    }

    public final void onSheetDetentChanged$react_native_screens_release(int detentIndex, boolean isStable) {
        dispatchSheetDetentChanged(detentIndex, isStable);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        Fragment fragment;
        ScreenStackFragment screenStackFragmentAsScreenStackFragment;
        SheetDelegate sheetDelegate$react_native_screens_release;
        super.onAttachedToWindow();
        if (!SheetUtilsKt.usesFormSheetPresentation(this) || (fragment = getFragment()) == null || (screenStackFragmentAsScreenStackFragment = FragmentExtKt.asScreenStackFragment(fragment)) == null || (sheetDelegate$react_native_screens_release = screenStackFragmentAsScreenStackFragment.getSheetDelegate()) == null) {
            return;
        }
        InsetsObserverProxy.INSTANCE.addOnApplyWindowInsetsListener(sheetDelegate$react_native_screens_release);
    }

    private final void dispatchSheetDetentChanged(int detentIndex, boolean isStable) {
        int surfaceId = UIManagerHelper.getSurfaceId(this.reactContext);
        EventDispatcher reactEventDispatcher = getReactEventDispatcher();
        if (reactEventDispatcher != null) {
            reactEventDispatcher.dispatchEvent(new SheetDetentChangedEvent(surfaceId, getId(), detentIndex, isStable));
        }
    }

    public final void onFinalizePropsUpdate$react_native_screens_release() {
        if (this.shouldUpdateSheetCornerRadius) {
            this.shouldUpdateSheetCornerRadius = false;
            onSheetCornerRadiusChange$react_native_screens_release();
        }
    }

    public final void onSheetCornerRadiusChange$react_native_screens_release() {
        if (this.stackPresentation != StackPresentation.FORM_SHEET || getBackground() == null) {
            return;
        }
        Drawable background = getBackground();
        MaterialShapeDrawable materialShapeDrawable = background instanceof MaterialShapeDrawable ? (MaterialShapeDrawable) background : null;
        if (materialShapeDrawable != null) {
            float dIPFromPixel = PixelUtil.toDIPFromPixel(this.sheetCornerRadius);
            ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
            builder.setTopLeftCorner(0, dIPFromPixel);
            builder.setTopRightCorner(0, dIPFromPixel);
            materialShapeDrawable.setShapeAppearanceModel(builder.build());
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/swmansion/rnscreens/Screen$StackPresentation;", "", "(Ljava/lang/String;I)V", "PUSH", "MODAL", "TRANSPARENT_MODAL", "FORM_SHEET", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class StackPresentation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ StackPresentation[] $VALUES;
        public static final StackPresentation PUSH = new StackPresentation("PUSH", 0);
        public static final StackPresentation MODAL = new StackPresentation("MODAL", 1);
        public static final StackPresentation TRANSPARENT_MODAL = new StackPresentation("TRANSPARENT_MODAL", 2);
        public static final StackPresentation FORM_SHEET = new StackPresentation("FORM_SHEET", 3);

        private static final /* synthetic */ StackPresentation[] $values() {
            return new StackPresentation[]{PUSH, MODAL, TRANSPARENT_MODAL, FORM_SHEET};
        }

        public static EnumEntries<StackPresentation> getEntries() {
            return $ENTRIES;
        }

        public static StackPresentation valueOf(String str) {
            return (StackPresentation) Enum.valueOf(StackPresentation.class, str);
        }

        public static StackPresentation[] values() {
            return (StackPresentation[]) $VALUES.clone();
        }

        private StackPresentation(String str, int i) {
        }

        static {
            StackPresentation[] stackPresentationArr$values = $values();
            $VALUES = stackPresentationArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(stackPresentationArr$values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/swmansion/rnscreens/Screen$StackAnimation;", "", "(Ljava/lang/String;I)V", "DEFAULT", "NONE", "FADE", "SLIDE_FROM_BOTTOM", "SLIDE_FROM_RIGHT", "SLIDE_FROM_LEFT", "FADE_FROM_BOTTOM", "IOS_FROM_RIGHT", "IOS_FROM_LEFT", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class StackAnimation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ StackAnimation[] $VALUES;
        public static final StackAnimation DEFAULT = new StackAnimation("DEFAULT", 0);
        public static final StackAnimation NONE = new StackAnimation("NONE", 1);
        public static final StackAnimation FADE = new StackAnimation("FADE", 2);
        public static final StackAnimation SLIDE_FROM_BOTTOM = new StackAnimation("SLIDE_FROM_BOTTOM", 3);
        public static final StackAnimation SLIDE_FROM_RIGHT = new StackAnimation("SLIDE_FROM_RIGHT", 4);
        public static final StackAnimation SLIDE_FROM_LEFT = new StackAnimation("SLIDE_FROM_LEFT", 5);
        public static final StackAnimation FADE_FROM_BOTTOM = new StackAnimation("FADE_FROM_BOTTOM", 6);
        public static final StackAnimation IOS_FROM_RIGHT = new StackAnimation("IOS_FROM_RIGHT", 7);
        public static final StackAnimation IOS_FROM_LEFT = new StackAnimation("IOS_FROM_LEFT", 8);

        private static final /* synthetic */ StackAnimation[] $values() {
            return new StackAnimation[]{DEFAULT, NONE, FADE, SLIDE_FROM_BOTTOM, SLIDE_FROM_RIGHT, SLIDE_FROM_LEFT, FADE_FROM_BOTTOM, IOS_FROM_RIGHT, IOS_FROM_LEFT};
        }

        public static EnumEntries<StackAnimation> getEntries() {
            return $ENTRIES;
        }

        public static StackAnimation valueOf(String str) {
            return (StackAnimation) Enum.valueOf(StackAnimation.class, str);
        }

        public static StackAnimation[] values() {
            return (StackAnimation[]) $VALUES.clone();
        }

        private StackAnimation(String str, int i) {
        }

        static {
            StackAnimation[] stackAnimationArr$values = $values();
            $VALUES = stackAnimationArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(stackAnimationArr$values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Lcom/swmansion/rnscreens/Screen$ReplaceAnimation;", "", "(Ljava/lang/String;I)V", "PUSH", ID3v22Frames.FRAME_ID_V2_POPULARIMETER, "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ReplaceAnimation {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ReplaceAnimation[] $VALUES;
        public static final ReplaceAnimation PUSH = new ReplaceAnimation("PUSH", 0);
        public static final ReplaceAnimation POP = new ReplaceAnimation(ID3v22Frames.FRAME_ID_V2_POPULARIMETER, 1);

        private static final /* synthetic */ ReplaceAnimation[] $values() {
            return new ReplaceAnimation[]{PUSH, POP};
        }

        public static EnumEntries<ReplaceAnimation> getEntries() {
            return $ENTRIES;
        }

        public static ReplaceAnimation valueOf(String str) {
            return (ReplaceAnimation) Enum.valueOf(ReplaceAnimation.class, str);
        }

        public static ReplaceAnimation[] values() {
            return (ReplaceAnimation[]) $VALUES.clone();
        }

        private ReplaceAnimation(String str, int i) {
        }

        static {
            ReplaceAnimation[] replaceAnimationArr$values = $values();
            $VALUES = replaceAnimationArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(replaceAnimationArr$values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/swmansion/rnscreens/Screen$ActivityState;", "", "(Ljava/lang/String;I)V", "INACTIVE", "TRANSITIONING_OR_BELOW_TOP", "ON_TOP", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class ActivityState {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ ActivityState[] $VALUES;
        public static final ActivityState INACTIVE = new ActivityState("INACTIVE", 0);
        public static final ActivityState TRANSITIONING_OR_BELOW_TOP = new ActivityState("TRANSITIONING_OR_BELOW_TOP", 1);
        public static final ActivityState ON_TOP = new ActivityState("ON_TOP", 2);

        private static final /* synthetic */ ActivityState[] $values() {
            return new ActivityState[]{INACTIVE, TRANSITIONING_OR_BELOW_TOP, ON_TOP};
        }

        public static EnumEntries<ActivityState> getEntries() {
            return $ENTRIES;
        }

        public static ActivityState valueOf(String str) {
            return (ActivityState) Enum.valueOf(ActivityState.class, str);
        }

        public static ActivityState[] values() {
            return (ActivityState[]) $VALUES.clone();
        }

        private ActivityState(String str, int i) {
        }

        static {
            ActivityState[] activityStateArr$values = $values();
            $VALUES = activityStateArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(activityStateArr$values);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Screen.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/swmansion/rnscreens/Screen$WindowTraits;", "", "(Ljava/lang/String;I)V", "ORIENTATION", "COLOR", "STYLE", "TRANSLUCENT", "HIDDEN", "ANIMATED", "NAVIGATION_BAR_COLOR", "NAVIGATION_BAR_TRANSLUCENT", "NAVIGATION_BAR_HIDDEN", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class WindowTraits {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ WindowTraits[] $VALUES;
        public static final WindowTraits ORIENTATION = new WindowTraits("ORIENTATION", 0);
        public static final WindowTraits COLOR = new WindowTraits("COLOR", 1);
        public static final WindowTraits STYLE = new WindowTraits("STYLE", 2);
        public static final WindowTraits TRANSLUCENT = new WindowTraits("TRANSLUCENT", 3);
        public static final WindowTraits HIDDEN = new WindowTraits("HIDDEN", 4);
        public static final WindowTraits ANIMATED = new WindowTraits("ANIMATED", 5);
        public static final WindowTraits NAVIGATION_BAR_COLOR = new WindowTraits("NAVIGATION_BAR_COLOR", 6);
        public static final WindowTraits NAVIGATION_BAR_TRANSLUCENT = new WindowTraits("NAVIGATION_BAR_TRANSLUCENT", 7);
        public static final WindowTraits NAVIGATION_BAR_HIDDEN = new WindowTraits("NAVIGATION_BAR_HIDDEN", 8);

        private static final /* synthetic */ WindowTraits[] $values() {
            return new WindowTraits[]{ORIENTATION, COLOR, STYLE, TRANSLUCENT, HIDDEN, ANIMATED, NAVIGATION_BAR_COLOR, NAVIGATION_BAR_TRANSLUCENT, NAVIGATION_BAR_HIDDEN};
        }

        public static EnumEntries<WindowTraits> getEntries() {
            return $ENTRIES;
        }

        public static WindowTraits valueOf(String str) {
            return (WindowTraits) Enum.valueOf(WindowTraits.class, str);
        }

        public static WindowTraits[] values() {
            return (WindowTraits[]) $VALUES.clone();
        }

        private WindowTraits(String str, int i) {
        }

        static {
            WindowTraits[] windowTraitsArr$values = $values();
            $VALUES = windowTraitsArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(windowTraitsArr$values);
        }
    }
}
