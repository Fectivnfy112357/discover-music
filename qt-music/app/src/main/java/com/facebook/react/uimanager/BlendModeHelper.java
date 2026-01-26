package com.facebook.react.uimanager;

import android.graphics.BlendMode;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: BlendModeHelper.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/uimanager/BlendModeHelper;", "", "()V", "parseMixBlendMode", "Landroid/graphics/BlendMode;", "mixBlendMode", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class BlendModeHelper {
    public static final BlendModeHelper INSTANCE = new BlendModeHelper();

    private BlendModeHelper() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @JvmStatic
    public static final BlendMode parseMixBlendMode(String mixBlendMode) {
        if (mixBlendMode == null || Build.VERSION.SDK_INT < 29) {
            return null;
        }
        switch (mixBlendMode.hashCode()) {
            case -2120744511:
                if (mixBlendMode.equals("luminosity")) {
                    return BlendMode.LUMINOSITY;
                }
                break;
            case -1427739212:
                if (mixBlendMode.equals("hard-light")) {
                    return BlendMode.HARD_LIGHT;
                }
                break;
            case -1338968417:
                if (mixBlendMode.equals("darken")) {
                    return BlendMode.DARKEN;
                }
                break;
            case -1247677005:
                if (mixBlendMode.equals("soft-light")) {
                    return BlendMode.SOFT_LIGHT;
                }
                break;
            case -1091287984:
                if (mixBlendMode.equals("overlay")) {
                    return BlendMode.OVERLAY;
                }
                break;
            case -1039745817:
                if (mixBlendMode.equals("normal")) {
                    return null;
                }
                break;
            case -907689876:
                if (mixBlendMode.equals("screen")) {
                    return BlendMode.SCREEN;
                }
                break;
            case -230491182:
                if (mixBlendMode.equals("saturation")) {
                    return BlendMode.SATURATION;
                }
                break;
            case -120580883:
                if (mixBlendMode.equals("color-dodge")) {
                    return BlendMode.COLOR_DODGE;
                }
                break;
            case 103672:
                if (mixBlendMode.equals("hue")) {
                    return BlendMode.HUE;
                }
                break;
            case 94842723:
                if (mixBlendMode.equals("color")) {
                    return BlendMode.COLOR;
                }
                break;
            case 170546239:
                if (mixBlendMode.equals("lighten")) {
                    return BlendMode.LIGHTEN;
                }
                break;
            case 653829668:
                if (mixBlendMode.equals("multiply")) {
                    return BlendMode.MULTIPLY;
                }
                break;
            case 1242982905:
                if (mixBlendMode.equals("color-burn")) {
                    return BlendMode.COLOR_BURN;
                }
                break;
            case 1686617550:
                if (mixBlendMode.equals("exclusion")) {
                    return BlendMode.EXCLUSION;
                }
                break;
            case 1728361789:
                if (mixBlendMode.equals("difference")) {
                    return BlendMode.DIFFERENCE;
                }
                break;
        }
        throw new IllegalArgumentException("Invalid mix-blend-mode name: " + mixBlendMode);
    }
}
