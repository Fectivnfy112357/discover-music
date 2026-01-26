package com.doublesymmetry.kotlinaudio.models;

import android.app.PendingIntent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NotificationConfig.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\fJ\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003JB\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\tHÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/NotificationConfig;", "", "buttons", "", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "accentColor", "", "smallIcon", "pendingIntent", "Landroid/app/PendingIntent;", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/app/PendingIntent;)V", "getAccentColor", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getButtons", "()Ljava/util/List;", "getPendingIntent", "()Landroid/app/PendingIntent;", "getSmallIcon", "component1", "component2", "component3", "component4", "copy", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Landroid/app/PendingIntent;)Lcom/doublesymmetry/kotlinaudio/models/NotificationConfig;", "equals", "", "other", "hashCode", "toString", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class NotificationConfig {
    private final Integer accentColor;
    private final List<NotificationButton> buttons;
    private final PendingIntent pendingIntent;
    private final Integer smallIcon;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NotificationConfig copy$default(NotificationConfig notificationConfig, List list, Integer num, Integer num2, PendingIntent pendingIntent, int i, Object obj) {
        if ((i & 1) != 0) {
            list = notificationConfig.buttons;
        }
        if ((i & 2) != 0) {
            num = notificationConfig.accentColor;
        }
        if ((i & 4) != 0) {
            num2 = notificationConfig.smallIcon;
        }
        if ((i & 8) != 0) {
            pendingIntent = notificationConfig.pendingIntent;
        }
        return notificationConfig.copy(list, num, num2, pendingIntent);
    }

    public final List<NotificationButton> component1() {
        return this.buttons;
    }

    /* renamed from: component2, reason: from getter */
    public final Integer getAccentColor() {
        return this.accentColor;
    }

    /* renamed from: component3, reason: from getter */
    public final Integer getSmallIcon() {
        return this.smallIcon;
    }

    /* renamed from: component4, reason: from getter */
    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final NotificationConfig copy(List<? extends NotificationButton> buttons, Integer accentColor, Integer smallIcon, PendingIntent pendingIntent) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        return new NotificationConfig(buttons, accentColor, smallIcon, pendingIntent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NotificationConfig)) {
            return false;
        }
        NotificationConfig notificationConfig = (NotificationConfig) other;
        return Intrinsics.areEqual(this.buttons, notificationConfig.buttons) && Intrinsics.areEqual(this.accentColor, notificationConfig.accentColor) && Intrinsics.areEqual(this.smallIcon, notificationConfig.smallIcon) && Intrinsics.areEqual(this.pendingIntent, notificationConfig.pendingIntent);
    }

    public int hashCode() {
        int iHashCode = this.buttons.hashCode() * 31;
        Integer num = this.accentColor;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.smallIcon;
        int iHashCode3 = (iHashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        PendingIntent pendingIntent = this.pendingIntent;
        return iHashCode3 + (pendingIntent != null ? pendingIntent.hashCode() : 0);
    }

    public String toString() {
        return "NotificationConfig(buttons=" + this.buttons + ", accentColor=" + this.accentColor + ", smallIcon=" + this.smallIcon + ", pendingIntent=" + this.pendingIntent + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public NotificationConfig(List<? extends NotificationButton> buttons, Integer num, Integer num2, PendingIntent pendingIntent) {
        Intrinsics.checkNotNullParameter(buttons, "buttons");
        this.buttons = buttons;
        this.accentColor = num;
        this.smallIcon = num2;
        this.pendingIntent = pendingIntent;
    }

    public /* synthetic */ NotificationConfig(List list, Integer num, Integer num2, PendingIntent pendingIntent, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : pendingIntent);
    }

    public final List<NotificationButton> getButtons() {
        return this.buttons;
    }

    public final Integer getAccentColor() {
        return this.accentColor;
    }

    public final Integer getSmallIcon() {
        return this.smallIcon;
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }
}
