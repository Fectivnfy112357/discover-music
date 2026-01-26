package com.facebook.react.views.switchview;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import androidx.appcompat.widget.SwitchCompat;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactSwitch.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J\u001d\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0006J\u0015\u0010\u0018\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0015\u0010\u001a\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0019J\u0015\u0010\u001b\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0019J\u0015\u0010\u001c\u001a\u00020\u000f2\b\u0010\r\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0012\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/views/switchview/ReactSwitch;", "Landroidx/appcompat/widget/SwitchCompat;", f.X, "Landroid/content/Context;", "(Landroid/content/Context;)V", "allowChange", "", "trackColorForFalse", "", "Ljava/lang/Integer;", "trackColorForTrue", "createRippleDrawableColorStateList", "Landroid/content/res/ColorStateList;", "color", "setBackgroundColor", "", "setChecked", "checked", "setColor", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;Ljava/lang/Integer;)V", "setOn", "on", "setThumbColor", "(Ljava/lang/Integer;)V", "setTrackColor", "setTrackColorForFalse", "setTrackColorForTrue", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactSwitch extends SwitchCompat {
    private boolean allowChange;
    private Integer trackColorForFalse;
    private Integer trackColorForTrue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReactSwitch(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.allowChange = true;
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean checked) throws Resources.NotFoundException {
        if (this.allowChange && isChecked() != checked) {
            this.allowChange = false;
            super.setChecked(checked);
            setTrackColor(checked);
            return;
        }
        super.setChecked(isChecked());
    }

    @Override // android.view.View
    public void setBackgroundColor(int color) {
        setBackground(new RippleDrawable(createRippleDrawableColorStateList(color), new ColorDrawable(color), null));
    }

    public final void setColor(Drawable drawable, Integer color) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        if (color == null) {
            drawable.clearColorFilter();
        } else {
            drawable.setColorFilter(new PorterDuffColorFilter(color.intValue(), PorterDuff.Mode.MULTIPLY));
        }
    }

    public final void setTrackColor(Integer color) {
        Drawable trackDrawable = super.getTrackDrawable();
        Intrinsics.checkNotNullExpressionValue(trackDrawable, "getTrackDrawable(...)");
        setColor(trackDrawable, color);
    }

    public final void setThumbColor(Integer color) {
        Drawable thumbDrawable = super.getThumbDrawable();
        Intrinsics.checkNotNullExpressionValue(thumbDrawable, "getThumbDrawable(...)");
        setColor(thumbDrawable, color);
        if (color == null || !(super.getBackground() instanceof RippleDrawable)) {
            return;
        }
        ColorStateList colorStateListCreateRippleDrawableColorStateList = createRippleDrawableColorStateList(color.intValue());
        Drawable background = super.getBackground();
        Intrinsics.checkNotNull(background, "null cannot be cast to non-null type android.graphics.drawable.RippleDrawable");
        ((RippleDrawable) background).setColor(colorStateListCreateRippleDrawableColorStateList);
    }

    public final void setOn(boolean on) throws Resources.NotFoundException {
        if (isChecked() != on) {
            super.setChecked(on);
            setTrackColor(on);
        }
        this.allowChange = true;
    }

    public final void setTrackColorForTrue(Integer color) {
        if (Intrinsics.areEqual(color, this.trackColorForTrue)) {
            return;
        }
        this.trackColorForTrue = color;
        if (isChecked()) {
            setTrackColor(this.trackColorForTrue);
        }
    }

    public final void setTrackColorForFalse(Integer color) {
        if (Intrinsics.areEqual(color, this.trackColorForFalse)) {
            return;
        }
        this.trackColorForFalse = color;
        if (isChecked()) {
            return;
        }
        setTrackColor(this.trackColorForFalse);
    }

    private final void setTrackColor(boolean checked) {
        Integer num = this.trackColorForTrue;
        if (num == null && this.trackColorForFalse == null) {
            return;
        }
        if (!checked) {
            num = this.trackColorForFalse;
        }
        setTrackColor(num);
    }

    private final ColorStateList createRippleDrawableColorStateList(int color) {
        return new ColorStateList(new int[][]{new int[]{R.attr.state_pressed}}, new int[]{color});
    }
}
