package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

@Deprecated
/* loaded from: classes2.dex */
public class ReactFontManager {
    private static ReactFontManager sReactFontManagerInstance;
    private final com.facebook.react.common.assets.ReactFontManager mDelegate;

    private ReactFontManager(com.facebook.react.common.assets.ReactFontManager reactFontManager) {
        this.mDelegate = reactFontManager;
    }

    public static ReactFontManager getInstance() {
        if (sReactFontManagerInstance == null) {
            sReactFontManagerInstance = new ReactFontManager(com.facebook.react.common.assets.ReactFontManager.getInstance());
        }
        return sReactFontManagerInstance;
    }

    public Typeface getTypeface(String str, int i, AssetManager assetManager) {
        return this.mDelegate.getTypeface(str, i, assetManager);
    }

    public Typeface getTypeface(String str, int i, boolean z, AssetManager assetManager) {
        return this.mDelegate.getTypeface(str, i, z, assetManager);
    }

    public Typeface getTypeface(String str, int i, int i2, AssetManager assetManager) {
        return this.mDelegate.getTypeface(str, i, i2, assetManager);
    }

    public void addCustomFont(Context context, String str, int i) {
        this.mDelegate.addCustomFont(context, str, i);
    }

    public void addCustomFont(String str, Typeface typeface) {
        this.mDelegate.addCustomFont(str, typeface);
    }

    public void setTypeface(String str, int i, Typeface typeface) {
        this.mDelegate.setTypeface(str, i, typeface);
    }
}
