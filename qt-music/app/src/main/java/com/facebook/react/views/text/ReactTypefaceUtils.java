package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.assets.ReactFontManager;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ReactTypefaceUtils {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int parseFontWeight(java.lang.String r2) {
        /*
            r0 = -1
            if (r2 == 0) goto Lad
            r2.hashCode()
            int r1 = r2.hashCode()
            switch(r1) {
                case -1039745817: goto L84;
                case 48625: goto L79;
                case 49586: goto L6e;
                case 50547: goto L63;
                case 51508: goto L58;
                case 52469: goto L4d;
                case 53430: goto L42;
                case 54391: goto L37;
                case 55352: goto L2a;
                case 56313: goto L1d;
                case 3029637: goto L10;
                default: goto Ld;
            }
        Ld:
            r2 = r0
            goto L8e
        L10:
            java.lang.String r1 = "bold"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L19
            goto Ld
        L19:
            r2 = 10
            goto L8e
        L1d:
            java.lang.String r1 = "900"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L26
            goto Ld
        L26:
            r2 = 9
            goto L8e
        L2a:
            java.lang.String r1 = "800"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L33
            goto Ld
        L33:
            r2 = 8
            goto L8e
        L37:
            java.lang.String r1 = "700"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L40
            goto Ld
        L40:
            r2 = 7
            goto L8e
        L42:
            java.lang.String r1 = "600"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L4b
            goto Ld
        L4b:
            r2 = 6
            goto L8e
        L4d:
            java.lang.String r1 = "500"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L56
            goto Ld
        L56:
            r2 = 5
            goto L8e
        L58:
            java.lang.String r1 = "400"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L61
            goto Ld
        L61:
            r2 = 4
            goto L8e
        L63:
            java.lang.String r1 = "300"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L6c
            goto Ld
        L6c:
            r2 = 3
            goto L8e
        L6e:
            java.lang.String r1 = "200"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L77
            goto Ld
        L77:
            r2 = 2
            goto L8e
        L79:
            java.lang.String r1 = "100"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L82
            goto Ld
        L82:
            r2 = 1
            goto L8e
        L84:
            java.lang.String r1 = "normal"
            boolean r2 = r2.equals(r1)
            if (r2 != 0) goto L8d
            goto Ld
        L8d:
            r2 = 0
        L8e:
            switch(r2) {
                case 0: goto Laa;
                case 1: goto La7;
                case 2: goto La4;
                case 3: goto La1;
                case 4: goto Laa;
                case 5: goto L9e;
                case 6: goto L9b;
                case 7: goto L98;
                case 8: goto L95;
                case 9: goto L92;
                case 10: goto L98;
                default: goto L91;
            }
        L91:
            goto Lad
        L92:
            r2 = 900(0x384, float:1.261E-42)
            return r2
        L95:
            r2 = 800(0x320, float:1.121E-42)
            return r2
        L98:
            r2 = 700(0x2bc, float:9.81E-43)
            return r2
        L9b:
            r2 = 600(0x258, float:8.41E-43)
            return r2
        L9e:
            r2 = 500(0x1f4, float:7.0E-43)
            return r2
        La1:
            r2 = 300(0x12c, float:4.2E-43)
            return r2
        La4:
            r2 = 200(0xc8, float:2.8E-43)
            return r2
        La7:
            r2 = 100
            return r2
        Laa:
            r2 = 400(0x190, float:5.6E-43)
            return r2
        Lad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.ReactTypefaceUtils.parseFontWeight(java.lang.String):int");
    }

    public static int parseFontStyle(String str) {
        if (str == null) {
            return -1;
        }
        if ("italic".equals(str)) {
            return 2;
        }
        return "normal".equals(str) ? 0 : -1;
    }

    public static String parseFontVariant(ReadableArray readableArray) {
        if (readableArray == null || readableArray.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < readableArray.size(); i++) {
            String string = readableArray.getString(i);
            if (string != null) {
                string.hashCode();
                switch (string) {
                    case "stylistic-thirteen":
                        arrayList.add("'ss13'");
                        break;
                    case "stylistic-fifteen":
                        arrayList.add("'ss15'");
                        break;
                    case "stylistic-eighteen":
                        arrayList.add("'ss18'");
                        break;
                    case "proportional-nums":
                        arrayList.add("'pnum'");
                        break;
                    case "lining-nums":
                        arrayList.add("'lnum'");
                        break;
                    case "historical-ligatures":
                        arrayList.add("'hlig'");
                        break;
                    case "tabular-nums":
                        arrayList.add("'tnum'");
                        break;
                    case "discretionary-ligatures":
                        arrayList.add("'dlig'");
                        break;
                    case "oldstyle-nums":
                        arrayList.add("'onum'");
                        break;
                    case "no-contextual":
                        arrayList.add("'calt' off");
                        break;
                    case "contextual":
                        arrayList.add("'calt'");
                        break;
                    case "no-common-ligatures":
                        arrayList.add("'liga' off");
                        arrayList.add("'clig' off");
                        break;
                    case "stylistic-eight":
                        arrayList.add("'ss08'");
                        break;
                    case "stylistic-seven":
                        arrayList.add("'ss07'");
                        break;
                    case "stylistic-three":
                        arrayList.add("'ss03'");
                        break;
                    case "stylistic-eleven":
                        arrayList.add("'ss11'");
                        break;
                    case "no-historical-ligatures":
                        arrayList.add("'hlig' off");
                        break;
                    case "stylistic-five":
                        arrayList.add("'ss05'");
                        break;
                    case "stylistic-four":
                        arrayList.add("'ss04'");
                        break;
                    case "stylistic-nine":
                        arrayList.add("'ss09'");
                        break;
                    case "stylistic-one":
                        arrayList.add("'ss01'");
                        break;
                    case "stylistic-six":
                        arrayList.add("'ss06'");
                        break;
                    case "stylistic-ten":
                        arrayList.add("'ss10'");
                        break;
                    case "stylistic-two":
                        arrayList.add("'ss02'");
                        break;
                    case "stylistic-sixteen":
                        arrayList.add("'ss16'");
                        break;
                    case "stylistic-twelve":
                        arrayList.add("'ss12'");
                        break;
                    case "stylistic-twenty":
                        arrayList.add("'ss20'");
                        break;
                    case "no-discretionary-ligatures":
                        arrayList.add("'dlig' off");
                        break;
                    case "small-caps":
                        arrayList.add("'smcp'");
                        break;
                    case "common-ligatures":
                        arrayList.add("'liga'");
                        arrayList.add("'clig'");
                        break;
                    case "stylistic-nineteen":
                        arrayList.add("'ss19'");
                        break;
                    case "stylistic-fourteen":
                        arrayList.add("'ss14'");
                        break;
                    case "stylistic-seventeen":
                        arrayList.add("'ss17'");
                        break;
                }
            }
        }
        return TextUtils.join(", ", arrayList);
    }

    public static Typeface applyStyles(Typeface typeface, int i, int i2, String str, AssetManager assetManager) {
        ReactFontManager.TypefaceStyle typefaceStyle = new ReactFontManager.TypefaceStyle(i, i2);
        if (str != null) {
            return com.facebook.react.common.assets.ReactFontManager.getInstance().getTypeface(str, typefaceStyle, assetManager);
        }
        if (typeface == null) {
            typeface = Typeface.DEFAULT;
        }
        return typefaceStyle.apply(typeface);
    }
}
