package com.doublesymmetry.kotlinaudio.notification;

import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: NotificationManager.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.doublesymmetry.kotlinaudio.notification.NotificationManager$showPreviousButtonCompact$1", f = "NotificationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class NotificationManager$showPreviousButtonCompact$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $value;
    int label;
    final /* synthetic */ NotificationManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    NotificationManager$showPreviousButtonCompact$1(NotificationManager notificationManager, boolean z, Continuation<? super NotificationManager$showPreviousButtonCompact$1> continuation) {
        super(2, continuation);
        this.this$0 = notificationManager;
        this.$value = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NotificationManager$showPreviousButtonCompact$1(this.this$0, this.$value, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NotificationManager$showPreviousButtonCompact$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.showPreviousButtonCompact = this.$value;
        PlayerNotificationManager playerNotificationManager = this.this$0.internalNotificationManager;
        if (playerNotificationManager != null) {
            playerNotificationManager.setUsePreviousActionInCompactView(this.$value);
        }
        return Unit.INSTANCE;
    }
}
