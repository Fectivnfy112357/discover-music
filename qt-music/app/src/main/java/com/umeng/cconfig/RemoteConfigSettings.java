package com.umeng.cconfig;

/* loaded from: classes3.dex */
public class RemoteConfigSettings {
    private final boolean autoUpdateConfig;

    public static class Builder {
        private boolean autoUpdateConfig = false;

        public RemoteConfigSettings build() {
            return new RemoteConfigSettings(this);
        }

        public Builder setAutoUpdateModeEnabled(boolean z) {
            this.autoUpdateConfig = z;
            return this;
        }
    }

    private RemoteConfigSettings(Builder builder) {
        this.autoUpdateConfig = builder.autoUpdateConfig;
    }

    public boolean isAutoUpdateModeEnabled() {
        return this.autoUpdateConfig;
    }
}
