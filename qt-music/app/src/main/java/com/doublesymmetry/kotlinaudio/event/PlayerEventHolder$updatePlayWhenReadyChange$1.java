package com.doublesymmetry.kotlinaudio.event;

import androidx.media3.container.MdtaMetadataEntry;
import com.doublesymmetry.kotlinaudio.models.PlayWhenReadyChangeData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PlayerEventHolder.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "com.doublesymmetry.kotlinaudio.event.PlayerEventHolder$updatePlayWhenReadyChange$1", f = "PlayerEventHolder.kt", i = {}, l = {MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes.dex */
final class PlayerEventHolder$updatePlayWhenReadyChange$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlayWhenReadyChangeData $playWhenReadyChange;
    int label;
    final /* synthetic */ PlayerEventHolder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlayerEventHolder$updatePlayWhenReadyChange$1(PlayerEventHolder playerEventHolder, PlayWhenReadyChangeData playWhenReadyChangeData, Continuation<? super PlayerEventHolder$updatePlayWhenReadyChange$1> continuation) {
        super(2, continuation);
        this.this$0 = playerEventHolder;
        this.$playWhenReadyChange = playWhenReadyChangeData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayerEventHolder$updatePlayWhenReadyChange$1(this.this$0, this.$playWhenReadyChange, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlayerEventHolder$updatePlayWhenReadyChange$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.this$0._playWhenReadyChange.emit(this.$playWhenReadyChange, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
