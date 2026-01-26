package com.google.android.exoplayer2.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableMap;

@Deprecated
/* loaded from: classes2.dex */
public final class CmcdLog {
    private final CmcdObject cmcdObject;
    private final CmcdRequest cmcdRequest;
    private final CmcdSession cmcdSession;
    private final CmcdStatus cmcdStatus;

    public static CmcdLog createInstance(CmcdConfiguration cmcdConfiguration, ExoTrackSelection exoTrackSelection, long j, long j2) {
        ImmutableMap<String, String> customData = cmcdConfiguration.requestConfig.getCustomData();
        int i = exoTrackSelection.getSelectedFormat().bitrate / 1000;
        CmcdObject.Builder customData2 = new CmcdObject.Builder().setCustomData(customData.get("CMCD-Object"));
        if (cmcdConfiguration.isBitrateLoggingAllowed()) {
            customData2.setBitrateKbps(i);
        }
        CmcdRequest.Builder customData3 = new CmcdRequest.Builder().setCustomData(customData.get("CMCD-Request"));
        if (cmcdConfiguration.isBufferLengthLoggingAllowed()) {
            customData3.setBufferLengthMs(j2 == -9223372036854775807L ? 0L : (j2 - j) / 1000);
        }
        CmcdSession.Builder customData4 = new CmcdSession.Builder().setCustomData(customData.get("CMCD-Session"));
        if (cmcdConfiguration.isContentIdLoggingAllowed()) {
            customData4.setContentId(cmcdConfiguration.contentId);
        }
        if (cmcdConfiguration.isSessionIdLoggingAllowed()) {
            customData4.setSessionId(cmcdConfiguration.sessionId);
        }
        CmcdStatus.Builder customData5 = new CmcdStatus.Builder().setCustomData(customData.get("CMCD-Status"));
        if (cmcdConfiguration.isMaximumRequestThroughputLoggingAllowed()) {
            customData5.setMaximumRequestedThroughputKbps(cmcdConfiguration.requestConfig.getRequestedMaximumThroughputKbps(i));
        }
        return new CmcdLog(customData2.build(), customData3.build(), customData4.build(), customData5.build());
    }

    private CmcdLog(CmcdObject cmcdObject, CmcdRequest cmcdRequest, CmcdSession cmcdSession, CmcdStatus cmcdStatus) {
        this.cmcdObject = cmcdObject;
        this.cmcdRequest = cmcdRequest;
        this.cmcdSession = cmcdSession;
        this.cmcdStatus = cmcdStatus;
    }

    public ImmutableMap<String, String> getHttpRequestHeaders() {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        this.cmcdObject.populateHttpRequestHeaders(builder);
        this.cmcdRequest.populateHttpRequestHeaders(builder);
        this.cmcdSession.populateHttpRequestHeaders(builder);
        this.cmcdStatus.populateHttpRequestHeaders(builder);
        return builder.buildOrThrow();
    }

    private static final class CmcdObject {
        public final int bitrateKbps;
        public final String customData;

        public static final class Builder {
            private int bitrateKbps = -2147483647;
            private String customData;

            public Builder setBitrateKbps(int i) {
                this.bitrateKbps = i;
                return this;
            }

            public Builder setCustomData(String str) {
                this.customData = str;
                return this;
            }

            public CmcdObject build() {
                return new CmcdObject(this);
            }
        }

        private CmcdObject(Builder builder) {
            this.bitrateKbps = builder.bitrateKbps;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> builder) {
            StringBuilder sb = new StringBuilder();
            int i = this.bitrateKbps;
            if (i != -2147483647) {
                sb.append(Util.formatInvariant("%s=%d,", "br", Integer.valueOf(i)));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                sb.append(Util.formatInvariant("%s,", this.customData));
            }
            if (sb.length() == 0) {
                return;
            }
            sb.setLength(sb.length() - 1);
            builder.put("CMCD-Object", sb.toString());
        }
    }

    private static final class CmcdRequest {
        public final long bufferLengthMs;
        public final String customData;

        public static final class Builder {
            private long bufferLengthMs = -9223372036854775807L;
            private String customData;

            public Builder setBufferLengthMs(long j) {
                Assertions.checkArgument(j == -9223372036854775807L || j >= 0);
                if (j != -9223372036854775807L) {
                    j = ((j + 50) / 100) * 100;
                }
                this.bufferLengthMs = j;
                return this;
            }

            public Builder setCustomData(String str) {
                this.customData = str;
                return this;
            }

            public CmcdRequest build() {
                return new CmcdRequest(this);
            }
        }

        private CmcdRequest(Builder builder) {
            this.bufferLengthMs = builder.bufferLengthMs;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> builder) {
            StringBuilder sb = new StringBuilder();
            long j = this.bufferLengthMs;
            if (j != -9223372036854775807L) {
                sb.append(Util.formatInvariant("%s=%d,", "bl", Long.valueOf(j)));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                sb.append(Util.formatInvariant("%s,", this.customData));
            }
            if (sb.length() == 0) {
                return;
            }
            sb.setLength(sb.length() - 1);
            builder.put("CMCD-Request", sb.toString());
        }
    }

    private static final class CmcdSession {
        public final String contentId;
        public final String customData;
        public final String sessionId;

        public static final class Builder {
            private String contentId;
            private String customData;
            private String sessionId;

            public Builder setContentId(String str) {
                Assertions.checkArgument(str == null || str.length() <= 64);
                this.contentId = str;
                return this;
            }

            public Builder setSessionId(String str) {
                Assertions.checkArgument(str == null || str.length() <= 64);
                this.sessionId = str;
                return this;
            }

            public Builder setCustomData(String str) {
                this.customData = str;
                return this;
            }

            public CmcdSession build() {
                return new CmcdSession(this);
            }
        }

        private CmcdSession(Builder builder) {
            this.contentId = builder.contentId;
            this.sessionId = builder.sessionId;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> builder) {
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.contentId)) {
                sb.append(Util.formatInvariant("%s=\"%s\",", "cid", this.contentId));
            }
            if (!TextUtils.isEmpty(this.sessionId)) {
                sb.append(Util.formatInvariant("%s=\"%s\",", "sid", this.sessionId));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                sb.append(Util.formatInvariant("%s,", this.customData));
            }
            if (sb.length() == 0) {
                return;
            }
            sb.setLength(sb.length() - 1);
            builder.put("CMCD-Session", sb.toString());
        }
    }

    private static final class CmcdStatus {
        public final String customData;
        public final int maximumRequestedThroughputKbps;

        public static final class Builder {
            private String customData;
            private int maximumRequestedThroughputKbps = -2147483647;

            public Builder setMaximumRequestedThroughputKbps(int i) {
                Assertions.checkArgument(i == -2147483647 || i >= 0);
                if (i != -2147483647) {
                    i = ((i + 50) / 100) * 100;
                }
                this.maximumRequestedThroughputKbps = i;
                return this;
            }

            public Builder setCustomData(String str) {
                this.customData = str;
                return this;
            }

            public CmcdStatus build() {
                return new CmcdStatus(this);
            }
        }

        private CmcdStatus(Builder builder) {
            this.maximumRequestedThroughputKbps = builder.maximumRequestedThroughputKbps;
            this.customData = builder.customData;
        }

        public void populateHttpRequestHeaders(ImmutableMap.Builder<String, String> builder) {
            StringBuilder sb = new StringBuilder();
            int i = this.maximumRequestedThroughputKbps;
            if (i != -2147483647) {
                sb.append(Util.formatInvariant("%s=%d,", "rtp", Integer.valueOf(i)));
            }
            if (!TextUtils.isEmpty(this.customData)) {
                sb.append(Util.formatInvariant("%s,", this.customData));
            }
            if (sb.length() == 0) {
                return;
            }
            sb.setLength(sb.length() - 1);
            builder.put("CMCD-Status", sb.toString());
        }
    }
}
