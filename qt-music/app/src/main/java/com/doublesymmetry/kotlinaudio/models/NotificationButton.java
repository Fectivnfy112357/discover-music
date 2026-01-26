package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NotificationConfig.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0007\u0003\u0004\u0005\u0006\u0007\b\tB\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0007\n\u000b\f\r\u000e\u000f\u0010¨\u0006\u0011"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "", "()V", "BACKWARD", "FORWARD", "NEXT", "PLAY_PAUSE", "PREVIOUS", "SEEK_TO", "STOP", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$BACKWARD;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$FORWARD;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$NEXT;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$PLAY_PAUSE;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$PREVIOUS;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$SEEK_TO;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$STOP;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class NotificationButton {
    public /* synthetic */ NotificationButton(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private NotificationButton() {
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0003\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\t\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$PLAY_PAUSE;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "playIcon", "", "pauseIcon", "(Ljava/lang/Integer;Ljava/lang/Integer;)V", "getPauseIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPlayIcon", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class PLAY_PAUSE extends NotificationButton {
        private final Integer pauseIcon;
        private final Integer playIcon;

        public PLAY_PAUSE() {
            this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
        }

        public PLAY_PAUSE(Integer num, Integer num2) {
            super(null);
            this.playIcon = num;
            this.pauseIcon = num2;
        }

        public /* synthetic */ PLAY_PAUSE(Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? null : num2);
        }

        public final Integer getPauseIcon() {
            return this.pauseIcon;
        }

        public final Integer getPlayIcon() {
            return this.playIcon;
        }
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$STOP;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "icon", "", "(Ljava/lang/Integer;)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class STOP extends NotificationButton {
        private final Integer icon;

        public STOP() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public STOP(Integer num) {
            super(null);
            this.icon = num;
        }

        public /* synthetic */ STOP(Integer num, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num);
        }

        public final Integer getIcon() {
            return this.icon;
        }
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$FORWARD;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "icon", "", "isCompact", "", "(Ljava/lang/Integer;Z)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class FORWARD extends NotificationButton {
        private final Integer icon;
        private final boolean isCompact;

        public FORWARD() {
            this(null, false, 3, 0 == true ? 1 : 0);
        }

        public FORWARD(Integer num, boolean z) {
            super(null);
            this.icon = num;
            this.isCompact = z;
        }

        public /* synthetic */ FORWARD(Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? false : z);
        }

        public final Integer getIcon() {
            return this.icon;
        }

        /* renamed from: isCompact, reason: from getter */
        public final boolean getIsCompact() {
            return this.isCompact;
        }
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$BACKWARD;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "icon", "", "isCompact", "", "(Ljava/lang/Integer;Z)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class BACKWARD extends NotificationButton {
        private final Integer icon;
        private final boolean isCompact;

        public BACKWARD() {
            this(null, false, 3, 0 == true ? 1 : 0);
        }

        public BACKWARD(Integer num, boolean z) {
            super(null);
            this.icon = num;
            this.isCompact = z;
        }

        public /* synthetic */ BACKWARD(Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? false : z);
        }

        public final Integer getIcon() {
            return this.icon;
        }

        /* renamed from: isCompact, reason: from getter */
        public final boolean getIsCompact() {
            return this.isCompact;
        }
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$NEXT;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "icon", "", "isCompact", "", "(Ljava/lang/Integer;Z)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class NEXT extends NotificationButton {
        private final Integer icon;
        private final boolean isCompact;

        public NEXT() {
            this(null, false, 3, 0 == true ? 1 : 0);
        }

        public NEXT(Integer num, boolean z) {
            super(null);
            this.icon = num;
            this.isCompact = z;
        }

        public /* synthetic */ NEXT(Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? false : z);
        }

        public final Integer getIcon() {
            return this.icon;
        }

        /* renamed from: isCompact, reason: from getter */
        public final boolean getIsCompact() {
            return this.isCompact;
        }
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0003\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$PREVIOUS;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "icon", "", "isCompact", "", "(Ljava/lang/Integer;Z)V", "getIcon", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Z", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class PREVIOUS extends NotificationButton {
        private final Integer icon;
        private final boolean isCompact;

        public PREVIOUS() {
            this(null, false, 3, 0 == true ? 1 : 0);
        }

        public PREVIOUS(Integer num, boolean z) {
            super(null);
            this.icon = num;
            this.isCompact = z;
        }

        public /* synthetic */ PREVIOUS(Integer num, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? null : num, (i & 2) != 0 ? false : z);
        }

        public final Integer getIcon() {
            return this.icon;
        }

        /* renamed from: isCompact, reason: from getter */
        public final boolean getIsCompact() {
            return this.isCompact;
        }
    }

    /* compiled from: NotificationConfig.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationButton$SEEK_TO;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "()V", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SEEK_TO extends NotificationButton {
        public static final SEEK_TO INSTANCE = new SEEK_TO();

        private SEEK_TO() {
            super(null);
        }
    }
}
