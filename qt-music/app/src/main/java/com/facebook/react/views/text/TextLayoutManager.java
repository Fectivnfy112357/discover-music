package com.facebook.react.views.text;

import android.content.Context;
import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftExceptionLogger;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.build.ReactBuildConfig;
import com.facebook.react.common.mapbuffer.MapBuffer;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.views.text.internal.span.ReactAbsoluteSizeSpan;
import com.facebook.react.views.text.internal.span.SetSpanOperation;
import com.facebook.yoga.YogaConstants;
import com.facebook.yoga.YogaMeasureMode;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class TextLayoutManager {
    public static final short AS_KEY_CACHE_ID = 3;
    public static final short AS_KEY_FRAGMENTS = 2;
    public static final short AS_KEY_HASH = 0;
    public static final short AS_KEY_STRING = 1;
    private static final boolean DEFAULT_ADJUST_FONT_SIZE_TO_FIT = false;
    private static final boolean DEFAULT_INCLUDE_FONT_PADDING = true;
    private static final boolean ENABLE_MEASURE_LOGGING;
    public static final short FR_KEY_HEIGHT = 4;
    public static final short FR_KEY_IS_ATTACHMENT = 2;
    public static final short FR_KEY_REACT_TAG = 1;
    public static final short FR_KEY_STRING = 0;
    public static final short FR_KEY_TEXT_ATTRIBUTES = 5;
    public static final short FR_KEY_WIDTH = 3;
    private static final String INLINE_VIEW_PLACEHOLDER = "0";
    public static final short PA_KEY_ADJUST_FONT_SIZE_TO_FIT = 3;
    public static final short PA_KEY_ELLIPSIZE_MODE = 1;
    public static final short PA_KEY_HYPHENATION_FREQUENCY = 5;
    public static final short PA_KEY_INCLUDE_FONT_PADDING = 4;
    public static final short PA_KEY_MAXIMUM_FONT_SIZE = 7;
    public static final short PA_KEY_MAX_NUMBER_OF_LINES = 0;
    public static final short PA_KEY_MINIMUM_FONT_SIZE = 6;
    public static final short PA_KEY_TEXT_BREAK_STRATEGY = 2;
    private static final String TAG;
    private static final ConcurrentHashMap<Integer, Spannable> sTagToSpannableCache;
    private static final TextPaint sTextPaintInstance;

    static {
        boolean z = ReactBuildConfig.DEBUG;
        ENABLE_MEASURE_LOGGING = false;
        TAG = "TextLayoutManager";
        sTextPaintInstance = new TextPaint(1);
        sTagToSpannableCache = new ConcurrentHashMap<>();
    }

    public static void setCachedSpannableForTag(int i, Spannable spannable) {
        if (ENABLE_MEASURE_LOGGING) {
            FLog.e(TAG, "Set cached spannable for tag[" + i + "]: " + spannable.toString());
        }
        sTagToSpannableCache.put(Integer.valueOf(i), spannable);
    }

    public static void deleteCachedSpannableForTag(int i) {
        if (ENABLE_MEASURE_LOGGING) {
            FLog.e(TAG, "Delete cached spannable for tag[" + i + "]");
        }
        sTagToSpannableCache.remove(Integer.valueOf(i));
    }

    public static boolean isRTL(MapBuffer mapBuffer) {
        if (!mapBuffer.contains(2)) {
            return false;
        }
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(2);
        if (mapBuffer2.getCount() == 0) {
            return false;
        }
        MapBuffer mapBuffer3 = mapBuffer2.getMapBuffer(0).getMapBuffer(5);
        return mapBuffer3.contains(23) && TextAttributeProps.getLayoutDirection(mapBuffer3.getString(23)) == 1;
    }

    public static Layout.Alignment getTextAlignment(MapBuffer mapBuffer, Spannable spannable) {
        Layout.Alignment alignment;
        if (!mapBuffer.contains(2)) {
            return Layout.Alignment.ALIGN_NORMAL;
        }
        boolean z = isRTL(mapBuffer) != TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spannable, 0, spannable.length());
        Layout.Alignment alignment2 = z ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL;
        MapBuffer mapBuffer2 = mapBuffer.getMapBuffer(2);
        if (mapBuffer2.getCount() == 0) {
            return alignment2;
        }
        MapBuffer mapBuffer3 = mapBuffer2.getMapBuffer(0).getMapBuffer(5);
        if (!mapBuffer3.contains(12)) {
            return alignment2;
        }
        String string = mapBuffer3.getString(12);
        if (string.equals("center")) {
            return Layout.Alignment.ALIGN_CENTER;
        }
        if (!string.equals("right")) {
            return alignment2;
        }
        if (z) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        return alignment;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
    
        return 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:?, code lost:
    
        return 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x0015, code lost:
    
        if (r4 != false) goto L5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
    
        if (r4 != false) goto L6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int getTextGravity(com.facebook.react.common.mapbuffer.MapBuffer r3, android.text.Spannable r4, int r5) {
        /*
            android.text.Layout$Alignment r3 = getTextAlignment(r3, r4)
            android.text.TextDirectionHeuristic r0 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            r1 = 0
            int r2 = r4.length()
            boolean r4 = r0.isRtl(r4, r1, r2)
            android.text.Layout$Alignment r0 = android.text.Layout.Alignment.ALIGN_NORMAL
            r1 = 5
            r2 = 3
            if (r3 != r0) goto L1b
            if (r4 == 0) goto L19
        L17:
            r5 = r1
            goto L27
        L19:
            r5 = r2
            goto L27
        L1b:
            android.text.Layout$Alignment r0 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            if (r3 != r0) goto L22
            if (r4 == 0) goto L17
            goto L19
        L22:
            android.text.Layout$Alignment r4 = android.text.Layout.Alignment.ALIGN_CENTER
            if (r3 != r4) goto L27
            r5 = 1
        L27:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.getTextGravity(com.facebook.react.common.mapbuffer.MapBuffer, android.text.Spannable, int):int");
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0090, code lost:
    
        r21.add(new com.facebook.react.views.text.internal.span.SetSpanOperation(r6, r8, new com.facebook.react.views.text.internal.span.ReactClickableSpan(r10)));
     */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x018f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void buildSpannableFromFragments(android.content.Context r18, com.facebook.react.common.mapbuffer.MapBuffer r19, android.text.SpannableStringBuilder r20, java.util.List<com.facebook.react.views.text.internal.span.SetSpanOperation> r21) {
        /*
            Method dump skipped, instructions count: 435
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.buildSpannableFromFragments(android.content.Context, com.facebook.react.common.mapbuffer.MapBuffer, android.text.SpannableStringBuilder, java.util.List):void");
    }

    public static Spannable getOrCreateSpannableForText(Context context, MapBuffer mapBuffer, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        if (mapBuffer.contains(3)) {
            return sTagToSpannableCache.get(Integer.valueOf(mapBuffer.getInt(3)));
        }
        return createSpannableFromAttributedString(context, mapBuffer, reactTextViewManagerCallback);
    }

    private static Spannable createSpannableFromAttributedString(Context context, MapBuffer mapBuffer, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList arrayList = new ArrayList();
        buildSpannableFromFragments(context, mapBuffer.getMapBuffer(2), spannableStringBuilder, arrayList);
        for (int i = 0; i < arrayList.size(); i++) {
            ((SetSpanOperation) arrayList.get((arrayList.size() - i) - 1)).execute(spannableStringBuilder, i);
        }
        if (reactTextViewManagerCallback != null) {
            reactTextViewManagerCallback.onPostProcessSpannable(spannableStringBuilder);
        }
        return spannableStringBuilder;
    }

    private static Layout createLayout(Spannable spannable, BoringLayout.Metrics metrics, float f, YogaMeasureMode yogaMeasureMode, boolean z, int i, int i2, Layout.Alignment alignment) {
        int i3;
        int length = spannable.length();
        boolean z2 = yogaMeasureMode == YogaMeasureMode.UNDEFINED || f < 0.0f;
        float desiredWidth = metrics == null ? Layout.getDesiredWidth(spannable, sTextPaintInstance) : Float.NaN;
        boolean zIsRtl = TextDirectionHeuristics.FIRSTSTRONG_LTR.isRtl(spannable, 0, length);
        if (metrics == null && (z2 || (!YogaConstants.isUndefined(desiredWidth) && desiredWidth <= f))) {
            if (yogaMeasureMode == YogaMeasureMode.EXACTLY) {
                desiredWidth = f;
            }
            return StaticLayout.Builder.obtain(spannable, 0, length, sTextPaintInstance, (int) Math.ceil(desiredWidth)).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(zIsRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR).build();
        }
        if (metrics != null && (z2 || metrics.width <= f)) {
            int iCeil = metrics.width;
            if (yogaMeasureMode == YogaMeasureMode.EXACTLY) {
                iCeil = (int) Math.ceil(f);
            }
            if (metrics.width < 0) {
                ReactSoftExceptionLogger.logSoftException(TAG, new ReactNoCrashSoftException("Text width is invalid: " + metrics.width));
                i3 = 0;
            } else {
                i3 = iCeil;
            }
            return BoringLayout.make(spannable, sTextPaintInstance, i3, alignment, 1.0f, 0.0f, metrics, z);
        }
        StaticLayout.Builder textDirection = StaticLayout.Builder.obtain(spannable, 0, length, sTextPaintInstance, (int) Math.ceil(f)).setAlignment(alignment).setLineSpacing(0.0f, 1.0f).setIncludePad(z).setBreakStrategy(i).setHyphenationFrequency(i2).setTextDirection(zIsRtl ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
        if (Build.VERSION.SDK_INT >= 28) {
            textDirection.setUseLineSpacingFromFallbacks(true);
        }
        return textDirection.build();
    }

    public static Layout createLayout(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, float f, float f2, ReactTextViewManagerCallback reactTextViewManagerCallback) {
        Spannable orCreateSpannableForText = getOrCreateSpannableForText(context, mapBuffer, reactTextViewManagerCallback);
        BoringLayout.Metrics metricsIsBoring = BoringLayout.isBoring(orCreateSpannableForText, sTextPaintInstance);
        int textBreakStrategy = TextAttributeProps.getTextBreakStrategy(mapBuffer2.getString(2));
        boolean z = mapBuffer2.contains(4) ? mapBuffer2.getBoolean(4) : true;
        int hyphenationFrequency = TextAttributeProps.getHyphenationFrequency(mapBuffer2.getString(5));
        boolean z2 = mapBuffer2.contains(3) ? mapBuffer2.getBoolean(3) : false;
        int i = mapBuffer2.contains(0) ? mapBuffer2.getInt(0) : -1;
        Layout.Alignment textAlignment = getTextAlignment(mapBuffer, orCreateSpannableForText);
        if (z2) {
            adjustSpannableFontToFit(orCreateSpannableForText, f, YogaMeasureMode.EXACTLY, f2, YogaMeasureMode.UNDEFINED, mapBuffer2.contains(6) ? mapBuffer2.getDouble(6) : Double.NaN, i, z, textBreakStrategy, hyphenationFrequency, textAlignment);
        }
        return createLayout(orCreateSpannableForText, metricsIsBoring, f, YogaMeasureMode.EXACTLY, z, textBreakStrategy, hyphenationFrequency, textAlignment);
    }

    public static void adjustSpannableFontToFit(Spannable spannable, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, double d, int i, boolean z, int i2, int i3, Layout.Alignment alignment) {
        BoringLayout.Metrics metricsIsBoring = BoringLayout.isBoring(spannable, sTextPaintInstance);
        Layout layoutCreateLayout = createLayout(spannable, metricsIsBoring, f, yogaMeasureMode, z, i2, i3, alignment);
        int pixelFromDIP = (int) (Double.isNaN(d) ? PixelUtil.toPixelFromDIP(4.0f) : d);
        int i4 = 0;
        int iMax = pixelFromDIP;
        for (ReactAbsoluteSizeSpan reactAbsoluteSizeSpan : (ReactAbsoluteSizeSpan[]) spannable.getSpans(0, spannable.length(), ReactAbsoluteSizeSpan.class)) {
            iMax = Math.max(iMax, reactAbsoluteSizeSpan.getSize());
        }
        int i5 = iMax;
        while (i5 > pixelFromDIP) {
            if ((i == -1 || i == 0 || layoutCreateLayout.getLineCount() <= i) && (yogaMeasureMode2 == YogaMeasureMode.UNDEFINED || layoutCreateLayout.getHeight() <= f2)) {
                return;
            }
            int iMax2 = i5 - Math.max(1, (int) PixelUtil.toPixelFromDIP(1.0f));
            float f3 = iMax2 / iMax;
            ReactAbsoluteSizeSpan[] reactAbsoluteSizeSpanArr = (ReactAbsoluteSizeSpan[]) spannable.getSpans(i4, spannable.length(), ReactAbsoluteSizeSpan.class);
            int length = reactAbsoluteSizeSpanArr.length;
            for (int i6 = i4; i6 < length; i6++) {
                ReactAbsoluteSizeSpan reactAbsoluteSizeSpan2 = reactAbsoluteSizeSpanArr[i6];
                spannable.setSpan(new ReactAbsoluteSizeSpan((int) Math.max(reactAbsoluteSizeSpan2.getSize() * f3, pixelFromDIP)), spannable.getSpanStart(reactAbsoluteSizeSpan2), spannable.getSpanEnd(reactAbsoluteSizeSpan2), spannable.getSpanFlags(reactAbsoluteSizeSpan2));
                spannable.removeSpan(reactAbsoluteSizeSpan2);
            }
            layoutCreateLayout = createLayout(spannable, metricsIsBoring, f, yogaMeasureMode, z, i2, i3, alignment);
            i5 = iMax2;
            i4 = 0;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long measureText(android.content.Context r18, com.facebook.react.common.mapbuffer.MapBuffer r19, com.facebook.react.common.mapbuffer.MapBuffer r20, float r21, com.facebook.yoga.YogaMeasureMode r22, float r23, com.facebook.yoga.YogaMeasureMode r24, com.facebook.react.views.text.ReactTextViewManagerCallback r25, float[] r26) {
        /*
            Method dump skipped, instructions count: 449
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.text.TextLayoutManager.measureText(android.content.Context, com.facebook.react.common.mapbuffer.MapBuffer, com.facebook.react.common.mapbuffer.MapBuffer, float, com.facebook.yoga.YogaMeasureMode, float, com.facebook.yoga.YogaMeasureMode, com.facebook.react.views.text.ReactTextViewManagerCallback, float[]):long");
    }

    public static WritableArray measureLines(Context context, MapBuffer mapBuffer, MapBuffer mapBuffer2, float f, float f2) {
        Layout layoutCreateLayout = createLayout(context, mapBuffer, mapBuffer2, f, f2, null);
        return FontMetricsUtil.getFontMetrics(layoutCreateLayout.getText(), layoutCreateLayout, sTextPaintInstance, context);
    }
}
