package com.doublesymmetry.kotlinaudio.models;

import android.app.Notification;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationState.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationState;", "", "()V", "CANCELLED", "POSTED", "Lcom/doublesymmetry/kotlinaudio/models/NotificationState$CANCELLED;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationState$POSTED;", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class NotificationState {
    public /* synthetic */ NotificationState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: NotificationState.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationState$POSTED;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationState;", "notificationId", "", "notification", "Landroid/app/Notification;", "ongoing", "", "(ILandroid/app/Notification;Z)V", "getNotification", "()Landroid/app/Notification;", "getNotificationId", "()I", "getOngoing", "()Z", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class POSTED extends NotificationState {
        private final Notification notification;
        private final int notificationId;
        private final boolean ongoing;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public POSTED(int i, Notification notification, boolean z) {
            super(null);
            Intrinsics.checkNotNullParameter(notification, "notification");
            this.notificationId = i;
            this.notification = notification;
            this.ongoing = z;
        }

        public final Notification getNotification() {
            return this.notification;
        }

        public final int getNotificationId() {
            return this.notificationId;
        }

        public final boolean getOngoing() {
            return this.ongoing;
        }
    }

    private NotificationState() {
    }

    /* compiled from: NotificationState.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationState$CANCELLED;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationState;", "notificationId", "", "(I)V", "getNotificationId", "()I", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class CANCELLED extends NotificationState {
        private final int notificationId;

        public CANCELLED(int i) {
            super(null);
            this.notificationId = i;
        }

        public final int getNotificationId() {
            return this.notificationId;
        }
    }
}
