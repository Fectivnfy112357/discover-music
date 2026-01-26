package com.facebook.react.views.progressbar;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ProgressBar;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaMeasureFunction;
import com.facebook.yoga.YogaMeasureMode;
import com.facebook.yoga.YogaMeasureOutput;
import com.facebook.yoga.YogaNode;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ProgressBarShadowNode.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J0\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n@GX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/facebook/react/views/progressbar/ProgressBarShadowNode;", "Lcom/facebook/react/uimanager/LayoutShadowNode;", "Lcom/facebook/yoga/YogaMeasureFunction;", "()V", "height", "Landroid/util/SparseIntArray;", "measured", "", "", "value", "", "style", "getStyle", "()Ljava/lang/String;", "setStyle", "(Ljava/lang/String;)V", "width", "measure", "", "node", "Lcom/facebook/yoga/YogaNode;", "", "widthMode", "Lcom/facebook/yoga/YogaMeasureMode;", "heightMode", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ProgressBarShadowNode extends LayoutShadowNode implements YogaMeasureFunction {
    private String style;
    private final SparseIntArray height = new SparseIntArray();
    private final SparseIntArray width = new SparseIntArray();
    private final Set<Integer> measured = new HashSet();

    public ProgressBarShadowNode() {
        setMeasureFunction(this);
        this.style = ReactProgressBarViewManager.DEFAULT_STYLE;
    }

    public final String getStyle() {
        return this.style;
    }

    @ReactProp(name = ReactProgressBarViewManager.PROP_STYLE)
    public final void setStyle(String str) {
        if (str == null) {
            str = ReactProgressBarViewManager.DEFAULT_STYLE;
        }
        this.style = str;
    }

    @Override // com.facebook.yoga.YogaMeasureFunction
    public long measure(YogaNode node, float width, YogaMeasureMode widthMode, float height, YogaMeasureMode heightMode) {
        Intrinsics.checkNotNullParameter(node, "node");
        Intrinsics.checkNotNullParameter(widthMode, "widthMode");
        Intrinsics.checkNotNullParameter(heightMode, "heightMode");
        int styleFromString$ReactAndroid_release = ReactProgressBarViewManager.INSTANCE.getStyleFromString$ReactAndroid_release(this.style);
        if (!this.measured.contains(Integer.valueOf(styleFromString$ReactAndroid_release))) {
            ProgressBar progressBarCreateProgressBar = ReactProgressBarViewManager.INSTANCE.createProgressBar(getThemedContext(), styleFromString$ReactAndroid_release);
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(-2, 0);
            progressBarCreateProgressBar.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            this.height.put(styleFromString$ReactAndroid_release, progressBarCreateProgressBar.getMeasuredHeight());
            this.width.put(styleFromString$ReactAndroid_release, progressBarCreateProgressBar.getMeasuredWidth());
            this.measured.add(Integer.valueOf(styleFromString$ReactAndroid_release));
        }
        return YogaMeasureOutput.make(this.width.get(styleFromString$ReactAndroid_release), this.height.get(styleFromString$ReactAndroid_release));
    }
}
