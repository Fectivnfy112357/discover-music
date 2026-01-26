package coil.intercept;

import coil.EventListener;
import coil.intercept.EngineInterceptor;
import coil.request.ImageRequest;
import coil.request.Options;
import coil.transform.Transformation;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: EngineInterceptor.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Lcoil/intercept/EngineInterceptor$ExecuteResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
@DebugMetadata(c = "coil.intercept.EngineInterceptor$transform$3", f = "EngineInterceptor.kt", i = {0, 0, 0}, l = {242}, m = "invokeSuspend", n = {"$this$withContext", "$this$foldIndices$iv", "i$iv"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
final class EngineInterceptor$transform$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super EngineInterceptor.ExecuteResult>, Object> {
    final /* synthetic */ EventListener $eventListener;
    final /* synthetic */ Options $options;
    final /* synthetic */ ImageRequest $request;
    final /* synthetic */ EngineInterceptor.ExecuteResult $result;
    final /* synthetic */ List<Transformation> $transformations;
    int I$0;
    int I$1;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ EngineInterceptor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    EngineInterceptor$transform$3(EngineInterceptor engineInterceptor, EngineInterceptor.ExecuteResult executeResult, Options options, List<? extends Transformation> list, EventListener eventListener, ImageRequest imageRequest, Continuation<? super EngineInterceptor$transform$3> continuation) {
        super(2, continuation);
        this.this$0 = engineInterceptor;
        this.$result = executeResult;
        this.$options = options;
        this.$transformations = list;
        this.$eventListener = eventListener;
        this.$request = imageRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        EngineInterceptor$transform$3 engineInterceptor$transform$3 = new EngineInterceptor$transform$3(this.this$0, this.$result, this.$options, this.$transformations, this.$eventListener, this.$request, continuation);
        engineInterceptor$transform$3.L$0 = obj;
        return engineInterceptor$transform$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super EngineInterceptor.ExecuteResult> continuation) {
        return ((EngineInterceptor$transform$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x007a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0070 -> B:13:0x0073). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 1
            if (r1 == 0) goto L27
            if (r1 != r2) goto L1f
            int r1 = r10.I$1
            int r3 = r10.I$0
            java.lang.Object r4 = r10.L$2
            coil.request.Options r4 = (coil.request.Options) r4
            java.lang.Object r5 = r10.L$1
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r6 = r10.L$0
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            kotlin.ResultKt.throwOnFailure(r11)
            goto L73
        L1f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L27:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            coil.intercept.EngineInterceptor r1 = r10.this$0
            coil.intercept.EngineInterceptor$ExecuteResult r3 = r10.$result
            android.graphics.drawable.Drawable r3 = r3.getDrawable()
            coil.request.Options r4 = r10.$options
            java.util.List<coil.transform.Transformation> r5 = r10.$transformations
            android.graphics.Bitmap r1 = coil.intercept.EngineInterceptor.access$convertDrawableToBitmap(r1, r3, r4, r5)
            coil.EventListener r3 = r10.$eventListener
            coil.request.ImageRequest r4 = r10.$request
            r3.transformStart(r4, r1)
            java.util.List<coil.transform.Transformation> r3 = r10.$transformations
            coil.request.Options r4 = r10.$options
            int r5 = r3.size()
            r6 = 0
            r9 = r6
            r6 = r11
            r11 = r1
            r1 = r5
            r5 = r3
            r3 = r9
        L54:
            if (r3 >= r1) goto L7a
            java.lang.Object r7 = r5.get(r3)
            coil.transform.Transformation r7 = (coil.transform.Transformation) r7
            coil.size.Size r8 = r4.getSize()
            r10.L$0 = r6
            r10.L$1 = r5
            r10.L$2 = r4
            r10.I$0 = r3
            r10.I$1 = r1
            r10.label = r2
            java.lang.Object r11 = r7.transform(r11, r8, r10)
            if (r11 != r0) goto L73
            return r0
        L73:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            kotlinx.coroutines.CoroutineScopeKt.ensureActive(r6)
            int r3 = r3 + r2
            goto L54
        L7a:
            coil.EventListener r0 = r10.$eventListener
            coil.request.ImageRequest r1 = r10.$request
            r0.transformEnd(r1, r11)
            coil.intercept.EngineInterceptor$ExecuteResult r2 = r10.$result
            coil.request.ImageRequest r0 = r10.$request
            android.content.Context r0 = r0.getContext()
            android.content.res.Resources r0 = r0.getResources()
            android.graphics.drawable.BitmapDrawable r1 = new android.graphics.drawable.BitmapDrawable
            r1.<init>(r0, r11)
            r3 = r1
            android.graphics.drawable.Drawable r3 = (android.graphics.drawable.Drawable) r3
            r7 = 14
            r8 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            coil.intercept.EngineInterceptor$ExecuteResult r11 = coil.intercept.EngineInterceptor.ExecuteResult.copy$default(r2, r3, r4, r5, r6, r7, r8)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.intercept.EngineInterceptor$transform$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
