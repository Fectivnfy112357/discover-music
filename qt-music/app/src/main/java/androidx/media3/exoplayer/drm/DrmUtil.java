package androidx.media3.exoplayer.drm;

import android.media.DeniedByServerException;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.exoplayer.drm.DefaultDrmSessionManager;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class DrmUtil {
    public static final int ERROR_SOURCE_EXO_MEDIA_DRM = 1;
    public static final int ERROR_SOURCE_LICENSE_ACQUISITION = 2;
    public static final int ERROR_SOURCE_PROVISIONING = 3;
    private static final int MAX_MANUAL_REDIRECTS = 5;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorSource {
    }

    public static int getErrorCodeForMediaDrmException(Throwable th, int i) {
        if (th instanceof MediaDrm.MediaDrmStateException) {
            return Util.getErrorCodeForMediaDrmErrorCode(Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th).getDiagnosticInfo()));
        }
        if (Util.SDK_INT >= 23 && Api23.isMediaDrmResetException(th)) {
            return 6006;
        }
        if ((th instanceof NotProvisionedException) || isFailureToConstructNotProvisionedException(th)) {
            return 6002;
        }
        if (th instanceof DeniedByServerException) {
            return 6007;
        }
        if (th instanceof UnsupportedDrmException) {
            return 6001;
        }
        if (th instanceof DefaultDrmSessionManager.MissingSchemeDataException) {
            return 6003;
        }
        if (th instanceof KeysExpiredException) {
            return 6008;
        }
        if (i == 1) {
            return 6006;
        }
        if (i == 2) {
            return 6004;
        }
        if (i == 3) {
            return 6002;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isFailureToConstructNotProvisionedException(Throwable th) {
        return Util.SDK_INT == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/NotProvisionedException;.<init>(");
    }

    public static boolean isFailureToConstructResourceBusyException(Throwable th) {
        return Util.SDK_INT == 34 && (th instanceof NoSuchMethodError) && th.getMessage() != null && th.getMessage().contains("Landroid/media/ResourceBusyException;.<init>(");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:9|21|10|(2:12|13)(2:26|15)) */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0036, code lost:
    
        r1 = getRedirectUrl(r11, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x003a, code lost:
    
        if (r1 != null) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x003c, code lost:
    
        r8 = r8 + 1;
        r9 = r9.buildUpon().setUri(r1).build();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x004e, code lost:
    
        throw r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004f, code lost:
    
        androidx.media3.common.util.Util.closeQuietly(r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
    
        throw r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0033, code lost:
    
        r8 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0035, code lost:
    
        r11 = move-exception;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static byte[] executePost(androidx.media3.datasource.DataSource r8, java.lang.String r9, byte[] r10, java.util.Map<java.lang.String, java.lang.String> r11) throws androidx.media3.exoplayer.drm.MediaDrmCallbackException {
        /*
            androidx.media3.datasource.StatsDataSource r0 = new androidx.media3.datasource.StatsDataSource
            r0.<init>(r8)
            androidx.media3.datasource.DataSpec$Builder r8 = new androidx.media3.datasource.DataSpec$Builder
            r8.<init>()
            androidx.media3.datasource.DataSpec$Builder r8 = r8.setUri(r9)
            androidx.media3.datasource.DataSpec$Builder r8 = r8.setHttpRequestHeaders(r11)
            r9 = 2
            androidx.media3.datasource.DataSpec$Builder r8 = r8.setHttpMethod(r9)
            androidx.media3.datasource.DataSpec$Builder r8 = r8.setHttpBody(r10)
            r9 = 1
            androidx.media3.datasource.DataSpec$Builder r8 = r8.setFlags(r9)
            androidx.media3.datasource.DataSpec r2 = r8.build()
            r8 = 0
            r9 = r2
        L26:
            androidx.media3.datasource.DataSourceInputStream r10 = new androidx.media3.datasource.DataSourceInputStream     // Catch: java.lang.Exception -> L53
            r10.<init>(r0, r9)     // Catch: java.lang.Exception -> L53
            byte[] r8 = com.google.common.io.ByteStreams.toByteArray(r10)     // Catch: java.lang.Throwable -> L33 androidx.media3.datasource.HttpDataSource.InvalidResponseCodeException -> L35
            androidx.media3.common.util.Util.closeQuietly(r10)     // Catch: java.lang.Exception -> L53
            return r8
        L33:
            r8 = move-exception
            goto L4f
        L35:
            r11 = move-exception
            java.lang.String r1 = getRedirectUrl(r11, r8)     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L4e
            int r8 = r8 + 1
            androidx.media3.datasource.DataSpec$Builder r9 = r9.buildUpon()     // Catch: java.lang.Throwable -> L33
            androidx.media3.datasource.DataSpec$Builder r9 = r9.setUri(r1)     // Catch: java.lang.Throwable -> L33
            androidx.media3.datasource.DataSpec r9 = r9.build()     // Catch: java.lang.Throwable -> L33
            androidx.media3.common.util.Util.closeQuietly(r10)     // Catch: java.lang.Exception -> L53
            goto L26
        L4e:
            throw r11     // Catch: java.lang.Throwable -> L33
        L4f:
            androidx.media3.common.util.Util.closeQuietly(r10)     // Catch: java.lang.Exception -> L53
            throw r8     // Catch: java.lang.Exception -> L53
        L53:
            r8 = move-exception
            r7 = r8
            androidx.media3.exoplayer.drm.MediaDrmCallbackException r8 = new androidx.media3.exoplayer.drm.MediaDrmCallbackException
            android.net.Uri r9 = r0.getLastOpenedUri()
            java.lang.Object r9 = androidx.media3.common.util.Assertions.checkNotNull(r9)
            r3 = r9
            android.net.Uri r3 = (android.net.Uri) r3
            java.util.Map r4 = r0.getResponseHeaders()
            long r5 = r0.getBytesRead()
            r1 = r8
            r1.<init>(r2, r3, r4, r5, r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.exoplayer.drm.DrmUtil.executePost(androidx.media3.datasource.DataSource, java.lang.String, byte[], java.util.Map):byte[]");
    }

    private static String getRedirectUrl(HttpDataSource.InvalidResponseCodeException invalidResponseCodeException, int i) {
        Map<String, List<String>> map;
        List<String> list;
        if ((invalidResponseCodeException.responseCode != 307 && invalidResponseCodeException.responseCode != 308) || i >= 5 || (map = invalidResponseCodeException.headerFields) == null || (list = map.get("Location")) == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    private static final class Api23 {
        private Api23() {
        }

        public static boolean isMediaDrmResetException(Throwable th) {
            return th instanceof MediaDrmResetException;
        }
    }

    private DrmUtil() {
    }
}
