package com.facebook.react.modules.systeminfo;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.doublesymmetry.trackplayer.service.MusicService;
import com.facebook.react.R;
import com.facebook.react.common.MapBuilder;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes2.dex */
public class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2";
    public static final String METRO_HOST_PROP_NAME = "metro.host";
    private static final String TAG = "AndroidInfoHelpers";
    private static String metroHostPropValue;

    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }

    private static boolean isRunningOnStockEmulator() {
        return Build.FINGERPRINT.contains("generic") || Build.FINGERPRINT.startsWith("google/sdk_gphone");
    }

    public static String getServerHost(Integer num) {
        return getServerIpAddress(num.intValue());
    }

    public static String getServerHost(Context context) {
        return getServerIpAddress(getDevServerPort(context).intValue());
    }

    public static String getAdbReverseTcpCommand(Integer num) {
        return "adb reverse tcp:" + num + " tcp:" + num;
    }

    public static String getAdbReverseTcpCommand(Context context) {
        return getAdbReverseTcpCommand(getDevServerPort(context));
    }

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + Build.VERSION.RELEASE + " - API " + Build.VERSION.SDK_INT;
    }

    public static Map<String, String> getInspectorHostMetadata(@Nullable Context context) {
        String str;
        String str2;
        String string;
        if (context != null) {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            String packageName = context.getPackageName();
            if (i == 0) {
                string = applicationInfo.nonLocalizedLabel.toString();
            } else {
                string = context.getString(i);
            }
            str = string;
            str2 = packageName;
        } else {
            str = null;
            str2 = null;
        }
        return MapBuilder.of("appDisplayName", str, "appIdentifier", str2, "platform", MusicService.ANDROID_OPTIONS_KEY, "deviceName", Build.MODEL, "reactNativeVersion", getReactNativeVersionString());
    }

    private static String getReactNativeVersionString() {
        Map<String, Object> map = ReactNativeVersion.VERSION;
        return map.get("major") + "." + map.get("minor") + "." + map.get("patch") + (map.get("prerelease") != null ? "-" + map.get("prerelease") : "");
    }

    private static Integer getDevServerPort(Context context) {
        return Integer.valueOf(context.getResources().getInteger(R.integer.react_native_dev_server_port));
    }

    private static String getServerIpAddress(int i) {
        String metroHostPropValue2 = getMetroHostPropValue();
        if (metroHostPropValue2.equals("")) {
            if (isRunningOnGenymotion()) {
                metroHostPropValue2 = GENYMOTION_LOCALHOST;
            } else if (isRunningOnStockEmulator()) {
                metroHostPropValue2 = EMULATOR_LOCALHOST;
            } else {
                metroHostPropValue2 = DEVICE_LOCALHOST;
            }
        }
        return String.format(Locale.US, "%s:%d", metroHostPropValue2, Integer.valueOf(i));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0044 A[Catch: all -> 0x0080, PHI: r2 r3
  0x0044: PHI (r2v8 java.lang.Process) = (r2v6 java.lang.Process), (r2v10 java.lang.Process) binds: [B:33:0x006d, B:17:0x0042] A[DONT_GENERATE, DONT_INLINE]
  0x0044: PHI (r3v6 java.io.BufferedReader) = (r3v4 java.io.BufferedReader), (r3v13 java.io.BufferedReader) binds: [B:33:0x006d, B:17:0x0042] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:16:0x003f, B:18:0x0044, B:35:0x0070, B:32:0x006a, B:40:0x0077, B:42:0x007c, B:43:0x007f), top: B:50:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized java.lang.String getMetroHostPropValue() {
        /*
            java.lang.Class<com.facebook.react.modules.systeminfo.AndroidInfoHelpers> r0 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.class
            monitor-enter(r0)
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L80
            if (r1 == 0) goto L9
            monitor-exit(r0)
            return r1
        L9:
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L59
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L59
            java.lang.String r4 = "/system/bin/getprop"
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L59
            java.lang.String r4 = "metro.host"
            r5 = 1
            r3[r5] = r4     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L59
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L54 java.lang.Exception -> L59
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4f
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4f
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4f
            java.lang.String r6 = "UTF-8"
            java.nio.charset.Charset r6 = java.nio.charset.Charset.forName(r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4f
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4a java.lang.Exception -> L4f
            java.lang.String r1 = ""
        L35:
            java.lang.String r4 = r3.readLine()     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L74
            if (r4 == 0) goto L3d
            r1 = r4
            goto L35
        L3d:
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Exception -> L48 java.lang.Throwable -> L74
            r3.close()     // Catch: java.lang.Exception -> L42 java.lang.Throwable -> L80
        L42:
            if (r2 == 0) goto L70
        L44:
            r2.destroy()     // Catch: java.lang.Throwable -> L80
            goto L70
        L48:
            r1 = move-exception
            goto L5d
        L4a:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L75
        L4f:
            r3 = move-exception
            r7 = r3
            r3 = r1
            r1 = r7
            goto L5d
        L54:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
            goto L75
        L59:
            r2 = move-exception
            r3 = r1
            r1 = r2
            r2 = r3
        L5d:
            java.lang.String r4 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.TAG     // Catch: java.lang.Throwable -> L74
            java.lang.String r5 = "Failed to query for metro.host prop:"
            com.facebook.common.logging.FLog.w(r4, r5, r1)     // Catch: java.lang.Throwable -> L74
            java.lang.String r1 = ""
            com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue = r1     // Catch: java.lang.Throwable -> L74
            if (r3 == 0) goto L6d
            r3.close()     // Catch: java.lang.Exception -> L6d java.lang.Throwable -> L80
        L6d:
            if (r2 == 0) goto L70
            goto L44
        L70:
            java.lang.String r1 = com.facebook.react.modules.systeminfo.AndroidInfoHelpers.metroHostPropValue     // Catch: java.lang.Throwable -> L80
            monitor-exit(r0)
            return r1
        L74:
            r1 = move-exception
        L75:
            if (r3 == 0) goto L7a
            r3.close()     // Catch: java.lang.Exception -> L7a java.lang.Throwable -> L80
        L7a:
            if (r2 == 0) goto L7f
            r2.destroy()     // Catch: java.lang.Throwable -> L80
        L7f:
            throw r1     // Catch: java.lang.Throwable -> L80
        L80:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L80
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.modules.systeminfo.AndroidInfoHelpers.getMetroHostPropValue():java.lang.String");
    }
}
