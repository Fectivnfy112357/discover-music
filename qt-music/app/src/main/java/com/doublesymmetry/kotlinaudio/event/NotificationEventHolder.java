package com.doublesymmetry.kotlinaudio.event;

import com.doublesymmetry.kotlinaudio.models.NotificationState;
import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

/* compiled from: NotificationEventHolder.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0011R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;", "", "()V", "_notificationStateChange", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/doublesymmetry/kotlinaudio/models/NotificationState;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "notificationStateChange", "Lkotlinx/coroutines/flow/SharedFlow;", "getNotificationStateChange", "()Lkotlinx/coroutines/flow/SharedFlow;", "setNotificationStateChange", "(Lkotlinx/coroutines/flow/SharedFlow;)V", "updateNotificationState", "", MusicService.STATE_KEY, "updateNotificationState$kotlin_audio_release", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class NotificationEventHolder {
    private MutableSharedFlow<NotificationState> _notificationStateChange;
    private final CoroutineScope coroutineScope = CoroutineScopeKt.MainScope();
    private SharedFlow<? extends NotificationState> notificationStateChange;

    public NotificationEventHolder() {
        MutableSharedFlow<NotificationState> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(1, 0, null, 6, null);
        this._notificationStateChange = mutableSharedFlowMutableSharedFlow$default;
        this.notificationStateChange = FlowKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
    }

    public final SharedFlow<NotificationState> getNotificationStateChange() {
        return this.notificationStateChange;
    }

    public final void setNotificationStateChange(SharedFlow<? extends NotificationState> sharedFlow) {
        Intrinsics.checkNotNullParameter(sharedFlow, "<set-?>");
        this.notificationStateChange = sharedFlow;
    }

    public final void updateNotificationState$kotlin_audio_release(NotificationState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new NotificationEventHolder$updateNotificationState$1(this, state, null), 3, null);
    }
}
