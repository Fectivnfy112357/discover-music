package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PlayerConfig.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000e\"\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;", "", "interceptPlayerActionsTriggeredExternally", "", "handleAudioBecomingNoisy", "handleAudioFocus", "audioContentType", "Lcom/doublesymmetry/kotlinaudio/models/AudioContentType;", "wakeMode", "Lcom/doublesymmetry/kotlinaudio/models/WakeMode;", "(ZZZLcom/doublesymmetry/kotlinaudio/models/AudioContentType;Lcom/doublesymmetry/kotlinaudio/models/WakeMode;)V", "getAudioContentType", "()Lcom/doublesymmetry/kotlinaudio/models/AudioContentType;", "getHandleAudioBecomingNoisy", "()Z", "getHandleAudioFocus", "getInterceptPlayerActionsTriggeredExternally", "setInterceptPlayerActionsTriggeredExternally", "(Z)V", "getWakeMode", "()Lcom/doublesymmetry/kotlinaudio/models/WakeMode;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class PlayerConfig {
    private final AudioContentType audioContentType;
    private final boolean handleAudioBecomingNoisy;
    private final boolean handleAudioFocus;
    private boolean interceptPlayerActionsTriggeredExternally;
    private final WakeMode wakeMode;

    public PlayerConfig() {
        this(false, false, false, null, null, 31, null);
    }

    public static /* synthetic */ PlayerConfig copy$default(PlayerConfig playerConfig, boolean z, boolean z2, boolean z3, AudioContentType audioContentType, WakeMode wakeMode, int i, Object obj) {
        if ((i & 1) != 0) {
            z = playerConfig.interceptPlayerActionsTriggeredExternally;
        }
        if ((i & 2) != 0) {
            z2 = playerConfig.handleAudioBecomingNoisy;
        }
        boolean z4 = z2;
        if ((i & 4) != 0) {
            z3 = playerConfig.handleAudioFocus;
        }
        boolean z5 = z3;
        if ((i & 8) != 0) {
            audioContentType = playerConfig.audioContentType;
        }
        AudioContentType audioContentType2 = audioContentType;
        if ((i & 16) != 0) {
            wakeMode = playerConfig.wakeMode;
        }
        return playerConfig.copy(z, z4, z5, audioContentType2, wakeMode);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getInterceptPlayerActionsTriggeredExternally() {
        return this.interceptPlayerActionsTriggeredExternally;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getHandleAudioBecomingNoisy() {
        return this.handleAudioBecomingNoisy;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getHandleAudioFocus() {
        return this.handleAudioFocus;
    }

    /* renamed from: component4, reason: from getter */
    public final AudioContentType getAudioContentType() {
        return this.audioContentType;
    }

    /* renamed from: component5, reason: from getter */
    public final WakeMode getWakeMode() {
        return this.wakeMode;
    }

    public final PlayerConfig copy(boolean interceptPlayerActionsTriggeredExternally, boolean handleAudioBecomingNoisy, boolean handleAudioFocus, AudioContentType audioContentType, WakeMode wakeMode) {
        Intrinsics.checkNotNullParameter(audioContentType, "audioContentType");
        Intrinsics.checkNotNullParameter(wakeMode, "wakeMode");
        return new PlayerConfig(interceptPlayerActionsTriggeredExternally, handleAudioBecomingNoisy, handleAudioFocus, audioContentType, wakeMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlayerConfig)) {
            return false;
        }
        PlayerConfig playerConfig = (PlayerConfig) other;
        return this.interceptPlayerActionsTriggeredExternally == playerConfig.interceptPlayerActionsTriggeredExternally && this.handleAudioBecomingNoisy == playerConfig.handleAudioBecomingNoisy && this.handleAudioFocus == playerConfig.handleAudioFocus && this.audioContentType == playerConfig.audioContentType && this.wakeMode == playerConfig.wakeMode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r2v0, types: [boolean] */
    public int hashCode() {
        boolean z = this.interceptPlayerActionsTriggeredExternally;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        ?? r2 = this.handleAudioBecomingNoisy;
        int i2 = r2;
        if (r2 != 0) {
            i2 = 1;
        }
        int i3 = (i + i2) * 31;
        boolean z2 = this.handleAudioFocus;
        return ((((i3 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.audioContentType.hashCode()) * 31) + this.wakeMode.hashCode();
    }

    public String toString() {
        return "PlayerConfig(interceptPlayerActionsTriggeredExternally=" + this.interceptPlayerActionsTriggeredExternally + ", handleAudioBecomingNoisy=" + this.handleAudioBecomingNoisy + ", handleAudioFocus=" + this.handleAudioFocus + ", audioContentType=" + this.audioContentType + ", wakeMode=" + this.wakeMode + ")";
    }

    public PlayerConfig(boolean z, boolean z2, boolean z3, AudioContentType audioContentType, WakeMode wakeMode) {
        Intrinsics.checkNotNullParameter(audioContentType, "audioContentType");
        Intrinsics.checkNotNullParameter(wakeMode, "wakeMode");
        this.interceptPlayerActionsTriggeredExternally = z;
        this.handleAudioBecomingNoisy = z2;
        this.handleAudioFocus = z3;
        this.audioContentType = audioContentType;
        this.wakeMode = wakeMode;
    }

    public final boolean getInterceptPlayerActionsTriggeredExternally() {
        return this.interceptPlayerActionsTriggeredExternally;
    }

    public final void setInterceptPlayerActionsTriggeredExternally(boolean z) {
        this.interceptPlayerActionsTriggeredExternally = z;
    }

    public final boolean getHandleAudioBecomingNoisy() {
        return this.handleAudioBecomingNoisy;
    }

    public final boolean getHandleAudioFocus() {
        return this.handleAudioFocus;
    }

    public /* synthetic */ PlayerConfig(boolean z, boolean z2, boolean z3, AudioContentType audioContentType, WakeMode wakeMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? false : z2, (i & 4) == 0 ? z3 : false, (i & 8) != 0 ? AudioContentType.MUSIC : audioContentType, (i & 16) != 0 ? WakeMode.NONE : wakeMode);
    }

    public final AudioContentType getAudioContentType() {
        return this.audioContentType;
    }

    public final WakeMode getWakeMode() {
        return this.wakeMode;
    }
}
