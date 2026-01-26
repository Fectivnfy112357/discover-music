package androidx.test.internal.runner.tracker;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import androidx.test.internal.util.Checks;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.facebook.common.util.UriUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* loaded from: classes.dex */
public final class AnalyticsBasedUsageTracker implements UsageTracker {
    private static final String API_LEVEL_PARAM = "&cd2=";
    private static final String APP_NAME_PARAM = "an=";
    private static final String APP_VERSION_PARAM = "&av=";
    private static final String CLIENT_ID_PARAM = "&cid=";
    private static final String MODEL_NAME_PARAM = "&cd3=";
    private static final String SCREEN_NAME_PARAM = "&cd=";
    private static final String SCREEN_RESOLUTION_PARAM = "&sr=";
    private static final String TAG = "InfraTrack";
    private static final String TRACKER_ID_PARAM = "&tid=";
    private static final String UTF_8 = "UTF-8";
    private final URL analyticsURI;
    private final String apiLevel;
    private final String model;
    private final String screenResolution;
    private final String targetPackage;
    private final String trackingId;
    private final Map<String, String> usageTypeToVersion;
    private final String userId;

    private AnalyticsBasedUsageTracker(Builder builder) {
        this.usageTypeToVersion = new HashMap();
        this.trackingId = (String) Checks.checkNotNull(builder.trackingId);
        this.targetPackage = (String) Checks.checkNotNull(builder.targetPackage);
        this.analyticsURI = (URL) Checks.checkNotNull(builder.analyticsURI);
        this.apiLevel = (String) Checks.checkNotNull(builder.apiLevel);
        this.model = (String) Checks.checkNotNull(builder.model);
        this.screenResolution = (String) Checks.checkNotNull(builder.screenResolution);
        this.userId = (String) Checks.checkNotNull(builder.userId);
    }

    public static class Builder {
        private URL analyticsURI;
        private boolean hashed;
        private String screenResolution;
        private final Context targetContext;
        private String targetPackage;
        private String userId;
        private Uri analyticsUri = new Uri.Builder().scheme(UriUtil.HTTPS_SCHEME).authority("www.google-analytics.com").path("collect").build();
        private String trackingId = "UA-36650409-3";
        private String apiLevel = String.valueOf(Build.VERSION.SDK_INT);
        private String model = Build.MODEL;

        public Builder(Context targetContext) {
            if (targetContext == null) {
                throw new NullPointerException("Context null!?");
            }
            this.targetContext = targetContext;
        }

        public Builder withTrackingId(String trackingId) {
            this.trackingId = trackingId;
            return this;
        }

        public Builder withAnalyticsUri(Uri analyticsUri) {
            Checks.checkNotNull(analyticsUri);
            this.analyticsUri = analyticsUri;
            return this;
        }

        public Builder withApiLevel(String apiLevel) {
            this.apiLevel = apiLevel;
            return this;
        }

        public Builder withScreenResolution(String resolutionVal) {
            this.screenResolution = resolutionVal;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withModel(String model) {
            this.model = model;
            return this;
        }

        public Builder withTargetPackage(String targetPackage) {
            this.hashed = false;
            this.targetPackage = targetPackage;
            return this;
        }

        public UsageTracker buildIfPossible() throws NoSuchAlgorithmException {
            if (!hasInternetPermission()) {
                Log.d(AnalyticsBasedUsageTracker.TAG, "Tracking disabled due to lack of internet permissions");
                return null;
            }
            if (this.targetPackage == null) {
                withTargetPackage(this.targetContext.getPackageName());
            }
            if (this.targetPackage.contains("com.google.analytics")) {
                Log.d(AnalyticsBasedUsageTracker.TAG, "Refusing to use analytics while testing analytics.");
                return null;
            }
            try {
                if (!this.targetPackage.startsWith("com.google.") && !this.targetPackage.startsWith("com.android.") && !this.targetPackage.startsWith("android.support.")) {
                    if (!this.hashed) {
                        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                        messageDigest.reset();
                        messageDigest.update(this.targetPackage.getBytes("UTF-8"));
                        String strValueOf = String.valueOf(new BigInteger(messageDigest.digest()).toString(16));
                        this.targetPackage = strValueOf.length() != 0 ? "sha256-".concat(strValueOf) : new String("sha256-");
                    }
                    this.hashed = true;
                }
                try {
                    this.analyticsURI = new URL(this.analyticsUri.toString());
                    if (this.screenResolution == null) {
                        Display defaultDisplay = ((WindowManager) this.targetContext.getSystemService("window")).getDefaultDisplay();
                        if (defaultDisplay == null) {
                            this.screenResolution = "0x0";
                        } else {
                            this.screenResolution = defaultDisplay.getWidth() + "x" + defaultDisplay.getHeight();
                        }
                    }
                    if (this.userId == null) {
                        this.userId = UUID.randomUUID().toString();
                    }
                    return new AnalyticsBasedUsageTracker(this);
                } catch (MalformedURLException e) {
                    String strValueOf2 = String.valueOf(this.analyticsUri.toString());
                    Log.w(AnalyticsBasedUsageTracker.TAG, strValueOf2.length() != 0 ? "Tracking disabled bad url: ".concat(strValueOf2) : new String("Tracking disabled bad url: "), e);
                    return null;
                }
            } catch (UnsupportedEncodingException e2) {
                Log.d(AnalyticsBasedUsageTracker.TAG, "Impossible - no utf-8 encoding?", e2);
                return null;
            } catch (NoSuchAlgorithmException e3) {
                Log.d(AnalyticsBasedUsageTracker.TAG, "Cannot hash package name.", e3);
                return null;
            }
        }

        private boolean hasInternetPermission() {
            return this.targetContext.checkCallingOrSelfPermission("android.permission.INTERNET") == 0;
        }
    }

    @Override // androidx.test.internal.runner.tracker.UsageTracker
    public void trackUsage(String usageType, String version) {
        synchronized (this.usageTypeToVersion) {
            this.usageTypeToVersion.put(usageType, version);
        }
    }

    @Override // androidx.test.internal.runner.tracker.UsageTracker
    public void sendUsages() throws Throwable {
        String str;
        HttpURLConnection httpURLConnection;
        synchronized (this.usageTypeToVersion) {
            if (this.usageTypeToVersion.isEmpty()) {
                return;
            }
            HashMap map = new HashMap(this.usageTypeToVersion);
            this.usageTypeToVersion.clear();
            HttpURLConnection httpURLConnection2 = null;
            try {
                str = APP_NAME_PARAM + URLEncoder.encode(this.targetPackage, "UTF-8") + TRACKER_ID_PARAM + URLEncoder.encode(this.trackingId, "UTF-8") + "&v=1&z=" + SystemClock.uptimeMillis() + CLIENT_ID_PARAM + URLEncoder.encode(this.userId, "UTF-8") + SCREEN_RESOLUTION_PARAM + URLEncoder.encode(this.screenResolution, "UTF-8") + API_LEVEL_PARAM + URLEncoder.encode(this.apiLevel, "UTF-8") + MODEL_NAME_PARAM + URLEncoder.encode(this.model, "UTF-8") + "&t=appview&sc=start";
            } catch (IOException e) {
                Log.w(TAG, "Impossible error happened. analytics disabled.", e);
                str = null;
            }
            for (Map.Entry entry : map.entrySet()) {
                try {
                    httpURLConnection = (HttpURLConnection) this.analyticsURI.openConnection();
                } catch (IOException e2) {
                    e = e2;
                    httpURLConnection = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    try {
                        byte[] bytes = (str + SCREEN_NAME_PARAM + URLEncoder.encode((String) entry.getKey(), "UTF-8") + APP_VERSION_PARAM + URLEncoder.encode((String) entry.getValue(), "UTF-8")).getBytes();
                        httpURLConnection.setConnectTimeout(PathInterpolatorCompat.MAX_NUM_POINTS);
                        httpURLConnection.setReadTimeout(5000);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        httpURLConnection.getOutputStream().write(bytes);
                        if (httpURLConnection.getResponseCode() / 100 != 2) {
                            String strValueOf = String.valueOf(entry);
                            int responseCode = httpURLConnection.getResponseCode();
                            String responseMessage = httpURLConnection.getResponseMessage();
                            Log.w(TAG, new StringBuilder(String.valueOf(strValueOf).length() + 45 + String.valueOf(responseMessage).length()).append("Analytics post: ").append(strValueOf).append(" failed. code: ").append(responseCode).append(" - ").append(responseMessage).toString());
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    e = e3;
                    String strValueOf2 = String.valueOf(entry);
                    Log.w(TAG, new StringBuilder(String.valueOf(strValueOf2).length() + 25).append("Analytics post: ").append(strValueOf2).append(" failed. ").toString(), e);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }
        }
    }
}
