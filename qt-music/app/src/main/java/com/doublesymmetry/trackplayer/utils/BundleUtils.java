package com.doublesymmetry.trackplayer.utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import com.facebook.common.util.UriUtil;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.umeng.analytics.pro.f;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BundleUtils.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ&\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u000bJ%\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\b¢\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\u000bJ!\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\u000bJ \u0010\u001a\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ$\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001f\u001a\u00020\u0018¨\u0006 "}, d2 = {"Lcom/doublesymmetry/trackplayer/utils/BundleUtils;", "", "()V", "getDoubleOrNull", "", "data", "Landroid/os/Bundle;", "key", "", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Double;", "getIcon", "", f.X, "Landroid/content/Context;", "options", "propertyName", "defaultIcon", "getIconOrNull", "(Landroid/content/Context;Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Integer;", "getInt", "defaultValue", "getIntOrNull", "(Landroid/os/Bundle;Ljava/lang/String;)Ljava/lang/Integer;", "getRating", "Landroid/support/v4/media/RatingCompat;", "ratingType", "getRawResourceId", "getUri", "Landroid/net/Uri;", "setRating", "", "rating", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BundleUtils {
    public static final BundleUtils INSTANCE = new BundleUtils();

    private BundleUtils() {
    }

    public final Uri getUri(Context context, Bundle data, String key) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNull(data);
        if (!data.containsKey(key)) {
            return null;
        }
        Object obj = data.get(key);
        if (!(obj instanceof String)) {
            if (!(obj instanceof Bundle)) {
                return null;
            }
            String string = ((Bundle) obj).getString("uri");
            int resourceDrawableId = ResourceDrawableIdHelper.INSTANCE.DEPRECATED$getInstance().getResourceDrawableId(context, string);
            if (resourceDrawableId <= 0) {
                return Uri.parse(string);
            }
            Resources resources = context.getResources();
            return new Uri.Builder().scheme(UriUtil.QUALIFIED_RESOURCE_SCHEME).authority(resources.getResourcePackageName(resourceDrawableId)).appendPath(resources.getResourceTypeName(resourceDrawableId)).appendPath(resources.getResourceEntryName(resourceDrawableId)).build();
        }
        String str = (String) obj;
        String str2 = str;
        int length = str2.length() - 1;
        int i = 0;
        boolean z = false;
        while (i <= length) {
            boolean z2 = Intrinsics.compare((int) str2.charAt(!z ? i : length), 32) <= 0;
            if (z) {
                if (!z2) {
                    break;
                }
                length--;
            } else if (z2) {
                i++;
            } else {
                z = true;
            }
        }
        if (str2.subSequence(i, length + 1).toString().length() != 0) {
            return Uri.parse(str);
        }
        throw new RuntimeException(key + ": The URL cannot be empty");
    }

    public final int getRawResourceId(Context context, Bundle data, String key) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        if (!data.containsKey(key)) {
            return 0;
        }
        Object obj = data.get(key);
        Bundle bundle = obj instanceof Bundle ? (Bundle) obj : null;
        if (bundle == null || (string = bundle.getString("uri")) == null || string.length() == 0) {
            return 0;
        }
        String lowerCase = string.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String strReplace$default = StringsKt.replace$default(lowerCase, "-", "_", false, 4, (Object) null);
        try {
            return Integer.parseInt(strReplace$default);
        } catch (NumberFormatException unused) {
            return context.getResources().getIdentifier(strReplace$default, "raw", context.getPackageName());
        }
    }

    public final int getIcon(Context context, Bundle options, String propertyName, int defaultIcon) {
        Bundle bundle;
        int resourceDrawableId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        return (!options.containsKey(propertyName) || (bundle = options.getBundle(propertyName)) == null || (resourceDrawableId = ResourceDrawableIdHelper.INSTANCE.DEPRECATED$getInstance().getResourceDrawableId(context, bundle.getString("uri"))) == 0) ? defaultIcon : resourceDrawableId;
    }

    public final Integer getIconOrNull(Context context, Bundle options, String propertyName) {
        Bundle bundle;
        int resourceDrawableId;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(propertyName, "propertyName");
        if (!options.containsKey(propertyName) || (bundle = options.getBundle(propertyName)) == null || (resourceDrawableId = ResourceDrawableIdHelper.INSTANCE.DEPRECATED$getInstance().getResourceDrawableId(context, bundle.getString("uri"))) == 0) {
            return null;
        }
        return Integer.valueOf(resourceDrawableId);
    }

    public final RatingCompat getRating(Bundle data, String key, int ratingType) {
        Intrinsics.checkNotNull(data);
        if (!data.containsKey(key) || ratingType == 0) {
            return RatingCompat.newUnratedRating(ratingType);
        }
        if (ratingType == 1) {
            return RatingCompat.newHeartRating(data.getBoolean(key, true));
        }
        if (ratingType == 2) {
            return RatingCompat.newThumbRating(data.getBoolean(key, true));
        }
        if (ratingType == 6) {
            return RatingCompat.newPercentageRating(data.getFloat(key, 0.0f));
        }
        return RatingCompat.newStarRating(ratingType, data.getFloat(key, 0.0f));
    }

    public final void setRating(Bundle data, String key, RatingCompat rating) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(rating, "rating");
        if (rating.isRated()) {
            int ratingStyle = rating.getRatingStyle();
            if (ratingStyle == 1) {
                data.putBoolean(key, rating.hasHeart());
                return;
            }
            if (ratingStyle == 2) {
                data.putBoolean(key, rating.isThumbUp());
            } else if (ratingStyle == 6) {
                data.putDouble(key, rating.getPercentRating());
            } else {
                data.putDouble(key, rating.getStarRating());
            }
        }
    }

    public final int getInt(Bundle data, String key, int defaultValue) {
        Intrinsics.checkNotNull(data);
        Object obj = data.get(key);
        return obj instanceof Number ? ((Number) obj).intValue() : defaultValue;
    }

    public final Integer getIntOrNull(Bundle data, String key) {
        Intrinsics.checkNotNull(data);
        Object obj = data.get(key);
        if (obj instanceof Number) {
            return Integer.valueOf(((Number) obj).intValue());
        }
        return null;
    }

    public final Double getDoubleOrNull(Bundle data, String key) {
        Intrinsics.checkNotNull(data);
        Object obj = data.get(key);
        if (obj instanceof Number) {
            return Double.valueOf(((Number) obj).doubleValue());
        }
        return null;
    }
}
