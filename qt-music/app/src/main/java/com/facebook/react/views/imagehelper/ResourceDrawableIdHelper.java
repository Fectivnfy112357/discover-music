package com.facebook.react.views.imagehelper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import androidx.core.content.res.ResourcesCompat;
import com.umeng.analytics.pro.f;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ResourceDrawableIdHelper.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u0002J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/views/imagehelper/ResourceDrawableIdHelper;", "", "()V", "resourceDrawableIdMap", "", "", "", "addDrawableId", f.X, "Landroid/content/Context;", "normalizedName", "clear", "", "getOpeningXmlTag", "name", "getResourceDrawable", "Landroid/graphics/drawable/Drawable;", "getResourceDrawableId", "getResourceDrawableUri", "Landroid/net/Uri;", "isVectorDrawable", "", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ResourceDrawableIdHelper {
    private static final String LOCAL_RESOURCE_SCHEME = "res";
    private final Map<String, Integer> resourceDrawableIdMap = new HashMap();

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ResourceDrawableIdHelper resourceDrawableIdHelper = new ResourceDrawableIdHelper();

    public static final ResourceDrawableIdHelper getInstance() {
        return INSTANCE.getInstance();
    }

    private ResourceDrawableIdHelper() {
    }

    public final synchronized void clear() {
        this.resourceDrawableIdMap.clear();
    }

    public final int getResourceDrawableId(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = name;
        if (str == null || str.length() == 0) {
            return 0;
        }
        String lowerCase = name.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String strReplace$default = StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null);
        try {
            return Integer.parseInt(strReplace$default);
        } catch (NumberFormatException unused) {
            synchronized (this) {
                Integer num = this.resourceDrawableIdMap.get(strReplace$default);
                return num != null ? num.intValue() : addDrawableId(context, strReplace$default);
            }
        }
    }

    private final int addDrawableId(Context context, String normalizedName) {
        int identifier = context.getResources().getIdentifier(normalizedName, "drawable", context.getPackageName());
        this.resourceDrawableIdMap.put(normalizedName, Integer.valueOf(identifier));
        return identifier;
    }

    public final Drawable getResourceDrawable(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        int resourceDrawableId = getResourceDrawableId(context, name);
        if (resourceDrawableId > 0) {
            return ResourcesCompat.getDrawable(context.getResources(), resourceDrawableId, null);
        }
        return null;
    }

    public final Uri getResourceDrawableUri(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        int resourceDrawableId = getResourceDrawableId(context, name);
        if (resourceDrawableId > 0) {
            Uri uriBuild = new Uri.Builder().scheme("res").path(String.valueOf(resourceDrawableId)).build();
            Intrinsics.checkNotNull(uriBuild);
            return uriBuild;
        }
        Uri uri = Uri.EMPTY;
        Intrinsics.checkNotNull(uri);
        return uri;
    }

    public final boolean isVectorDrawable(Context context, String name) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(name, "name");
        return Intrinsics.areEqual(getOpeningXmlTag(context, name), "vector");
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0037, code lost:
    
        r4 = r4.getName();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final java.lang.String getOpeningXmlTag(android.content.Context r4, java.lang.String r5) throws java.lang.Exception {
        /*
            r3 = this;
            int r5 = r3.getResourceDrawableId(r4, r5)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r0 = r5
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            r1 = 0
            if (r0 <= 0) goto L13
            goto L14
        L13:
            r5 = r1
        L14:
            if (r5 == 0) goto L4e
            int r5 = r5.intValue()
            android.content.res.Resources r4 = r4.getResources()     // Catch: android.content.res.Resources.NotFoundException -> L4e
            android.content.res.XmlResourceParser r4 = r4.getXml(r5)     // Catch: android.content.res.Resources.NotFoundException -> L4e
            java.lang.String r5 = "getXml(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch: android.content.res.Resources.NotFoundException -> L4e
            r5 = r4
            java.lang.AutoCloseable r5 = (java.lang.AutoCloseable) r5     // Catch: android.content.res.Resources.NotFoundException -> L4e
            r0 = r5
            android.content.res.XmlResourceParser r0 = (android.content.res.XmlResourceParser) r0     // Catch: java.lang.Throwable -> L47
            int r0 = r4.getEventType()     // Catch: java.lang.Throwable -> L47
        L31:
            r2 = 1
            if (r0 == r2) goto L41
            r2 = 2
            if (r0 != r2) goto L3c
            java.lang.String r4 = r4.getName()     // Catch: java.lang.Throwable -> L47
            goto L42
        L3c:
            int r0 = r4.next()     // Catch: java.lang.Throwable -> L47
            goto L31
        L41:
            r4 = r1
        L42:
            kotlin.jdk7.AutoCloseableKt.closeFinally(r5, r1)     // Catch: android.content.res.Resources.NotFoundException -> L4e
            r1 = r4
            goto L4e
        L47:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L49
        L49:
            r0 = move-exception
            kotlin.jdk7.AutoCloseableKt.closeFinally(r5, r4)     // Catch: android.content.res.Resources.NotFoundException -> L4e
            throw r0     // Catch: android.content.res.Resources.NotFoundException -> L4e
        L4e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.views.imagehelper.ResourceDrawableIdHelper.getOpeningXmlTag(android.content.Context, java.lang.String):java.lang.String");
    }

    /* compiled from: ResourceDrawableIdHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\r\u0010\b\u001a\u00020\u0006H\u0007¢\u0006\u0002\b\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0007\u0010\u0002\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/facebook/react/views/imagehelper/ResourceDrawableIdHelper$Companion;", "", "()V", "LOCAL_RESOURCE_SCHEME", "", "instance", "Lcom/facebook/react/views/imagehelper/ResourceDrawableIdHelper;", "getInstance$annotations", "getInstance", "()Lcom/facebook/react/views/imagehelper/ResourceDrawableIdHelper;", "resourceDrawableIdHelper", "DEPRECATED$getInstance", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final ResourceDrawableIdHelper getInstance() {
            return ResourceDrawableIdHelper.resourceDrawableIdHelper;
        }

        @Deprecated(message = "Use .instance instead, this API is for backward compat", replaceWith = @ReplaceWith(expression = "instance", imports = {}))
        public final ResourceDrawableIdHelper DEPRECATED$getInstance() {
            return getInstance();
        }
    }
}
