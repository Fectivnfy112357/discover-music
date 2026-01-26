package com.google.android.exoplayer2.upstream;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.upstream.CmcdConfiguration;
import com.google.android.exoplayer2.util.Assertions;
import com.google.common.collect.ImmutableMap;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

@Deprecated
/* loaded from: classes2.dex */
public final class CmcdConfiguration {
    public static final String KEY_BITRATE = "br";
    public static final String KEY_BUFFER_LENGTH = "bl";
    public static final String KEY_CMCD_OBJECT = "CMCD-Object";
    public static final String KEY_CMCD_REQUEST = "CMCD-Request";
    public static final String KEY_CMCD_SESSION = "CMCD-Session";
    public static final String KEY_CMCD_STATUS = "CMCD-Status";
    public static final String KEY_CONTENT_ID = "cid";
    public static final String KEY_MAXIMUM_REQUESTED_BITRATE = "rtp";
    public static final String KEY_SESSION_ID = "sid";
    public static final int MAX_ID_LENGTH = 64;
    public final String contentId;
    public final RequestConfig requestConfig;
    public final String sessionId;

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CmcdKey {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface HeaderKey {
    }

    public interface Factory {
        public static final Factory DEFAULT = new Factory() { // from class: com.google.android.exoplayer2.upstream.CmcdConfiguration$Factory$$ExternalSyntheticLambda0
            @Override // com.google.android.exoplayer2.upstream.CmcdConfiguration.Factory
            public final CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem) {
                return CmcdConfiguration.Factory.lambda$static$0(mediaItem);
            }
        };

        CmcdConfiguration createCmcdConfiguration(MediaItem mediaItem);

        static /* synthetic */ CmcdConfiguration lambda$static$0(MediaItem mediaItem) {
            String str;
            String string = UUID.randomUUID().toString();
            if (mediaItem.mediaId != null) {
                str = mediaItem.mediaId;
            } else {
                str = "";
            }
            return new CmcdConfiguration(string, str, new RequestConfig() { // from class: com.google.android.exoplayer2.upstream.CmcdConfiguration.Factory.1
            });
        }
    }

    public interface RequestConfig {
        default int getRequestedMaximumThroughputKbps(int i) {
            return -2147483647;
        }

        default boolean isKeyAllowed(String str) {
            return true;
        }

        default ImmutableMap<String, String> getCustomData() {
            return ImmutableMap.of();
        }
    }

    public CmcdConfiguration(String str, String str2, RequestConfig requestConfig) {
        Assertions.checkArgument(str == null || str.length() <= 64);
        Assertions.checkArgument(str2 == null || str2.length() <= 64);
        Assertions.checkNotNull(requestConfig);
        this.sessionId = str;
        this.contentId = str2;
        this.requestConfig = requestConfig;
    }

    public boolean isBitrateLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("br");
    }

    public boolean isBufferLengthLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("bl");
    }

    public boolean isContentIdLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("cid");
    }

    public boolean isSessionIdLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("sid");
    }

    public boolean isMaximumRequestThroughputLoggingAllowed() {
        return this.requestConfig.isKeyAllowed("rtp");
    }
}
