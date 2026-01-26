package com.facebook.react.views.view;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.drawable.CSSBackgroundDrawable;

@UnstableReactNativeAPI
/* loaded from: classes2.dex */
public class ReactViewBackgroundManager {
    private CSSBackgroundDrawable mCSSBackgroundDrawable;
    private int mColor = 0;
    private Overflow mOverflow = Overflow.VISIBLE;
    private View mView;

    private enum Overflow {
        VISIBLE,
        HIDDEN,
        SCROLL
    }

    public ReactViewBackgroundManager(View view) {
        this.mView = view;
    }

    public void cleanup() {
        ViewCompat.setBackground(this.mView, null);
        this.mView = null;
        this.mCSSBackgroundDrawable = null;
    }

    private CSSBackgroundDrawable getOrCreateReactViewBackground() {
        if (this.mCSSBackgroundDrawable == null) {
            this.mCSSBackgroundDrawable = new CSSBackgroundDrawable(this.mView.getContext());
            Drawable background = this.mView.getBackground();
            ViewCompat.setBackground(this.mView, null);
            if (background == null) {
                ViewCompat.setBackground(this.mView, this.mCSSBackgroundDrawable);
            } else {
                ViewCompat.setBackground(this.mView, new LayerDrawable(new Drawable[]{this.mCSSBackgroundDrawable, background}));
            }
        }
        return this.mCSSBackgroundDrawable;
    }

    public void setBackgroundColor(int i) {
        if (i == 0 && this.mCSSBackgroundDrawable == null) {
            return;
        }
        getOrCreateReactViewBackground().setColor(i);
    }

    public int getBackgroundColor() {
        return this.mColor;
    }

    public void setBorderWidth(int i, float f) {
        getOrCreateReactViewBackground().setBorderWidth(i, f);
    }

    public void setBorderColor(int i, Integer num) {
        getOrCreateReactViewBackground().setBorderColor(i, num);
    }

    public int getBorderColor(int i) {
        return getOrCreateReactViewBackground().getBorderColor(i);
    }

    public void setBorderRadius(float f) {
        getOrCreateReactViewBackground().setRadius(f);
    }

    public void setBorderRadius(float f, int i) {
        getOrCreateReactViewBackground().setRadius(f, i);
    }

    public void setBorderStyle(String str) {
        getOrCreateReactViewBackground().setBorderStyle(str);
    }

    public void setOverflow(String str) {
        Overflow overflow = this.mOverflow;
        if (ViewProps.HIDDEN.equals(str)) {
            this.mOverflow = Overflow.HIDDEN;
        } else if (ViewProps.SCROLL.equals(str)) {
            this.mOverflow = Overflow.SCROLL;
        } else {
            this.mOverflow = Overflow.VISIBLE;
        }
        if (overflow != this.mOverflow) {
            this.mView.invalidate();
        }
    }

    public void maybeClipToPaddingBox(Canvas canvas) {
        if (this.mOverflow == Overflow.VISIBLE) {
            return;
        }
        Rect rect = new Rect();
        this.mView.getDrawingRect(rect);
        CSSBackgroundDrawable cSSBackgroundDrawable = this.mCSSBackgroundDrawable;
        if (cSSBackgroundDrawable == null) {
            canvas.clipRect(rect);
            return;
        }
        Path paddingBoxPath = cSSBackgroundDrawable.getPaddingBoxPath();
        if (paddingBoxPath != null) {
            paddingBoxPath.offset(rect.left, rect.top);
            canvas.clipPath(paddingBoxPath);
        } else {
            RectF paddingBoxRect = cSSBackgroundDrawable.getPaddingBoxRect();
            paddingBoxRect.offset(rect.left, rect.top);
            canvas.clipRect(paddingBoxRect);
        }
    }
}
