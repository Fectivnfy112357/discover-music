package com.facebook.react.fabric;

import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.safeareaview.ReactSafeAreaViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jaudiotagger.tag.datatype.DataTypes;

/* compiled from: FabricComponents.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0007R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/fabric/FabricComponents;", "", "()V", "componentNames", "", "", "getFabricComponentName", "componentName", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class FabricComponents {
    public static final FabricComponents INSTANCE = new FabricComponents();
    private static final Map<String, String> componentNames = MapsKt.mapOf(TuplesKt.to("View", "RCTView"), TuplesKt.to("Image", ReactImageManager.REACT_CLASS), TuplesKt.to("ScrollView", ReactScrollViewManager.REACT_CLASS), TuplesKt.to("Slider", "RCTSlider"), TuplesKt.to("ModalHostView", ReactModalHostManager.REACT_CLASS), TuplesKt.to("Paragraph", ReactTextViewManager.REACT_CLASS), TuplesKt.to(DataTypes.OBJ_TEXT, "RCText"), TuplesKt.to("RawText", ReactRawTextManager.REACT_CLASS), TuplesKt.to("ActivityIndicatorView", ReactProgressBarViewManager.REACT_CLASS), TuplesKt.to("ShimmeringView", "RKShimmeringView"), TuplesKt.to("TemplateView", "RCTTemplateView"), TuplesKt.to("AxialGradientView", "RCTAxialGradientView"), TuplesKt.to("Video", "RCTVideo"), TuplesKt.to("Map", "RCTMap"), TuplesKt.to("WebView", "RCTWebView"), TuplesKt.to("Keyframes", "RCTKeyframes"), TuplesKt.to("ImpressionTrackingView", "RCTImpressionTrackingView"), TuplesKt.to("SafeAreaView", ReactSafeAreaViewManager.REACT_CLASS));

    private FabricComponents() {
    }

    @JvmStatic
    public static final String getFabricComponentName(String componentName) {
        Intrinsics.checkNotNullParameter(componentName, "componentName");
        String str = componentNames.get(componentName);
        return str == null ? componentName : str;
    }
}
