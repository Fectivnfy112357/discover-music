package com.facebook.imagepipeline.producers;

import android.os.Looper;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.imagepipeline.instrumentation.FrescoInstrumenter;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ThreadHandoffProducer.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u0011*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u0011B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThreadHandoffProducer;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/facebook/imagepipeline/producers/Producer;", "inputProducer", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "(Lcom/facebook/imagepipeline/producers/Producer;Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;)V", "getInputProducer", "()Lcom/facebook/imagepipeline/producers/Producer;", "getThreadHandoffProducerQueue", "()Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "produceResults", "", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", f.X, "Lcom/facebook/imagepipeline/producers/ProducerContext;", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ThreadHandoffProducer<T> implements Producer<T> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String PRODUCER_NAME = "BackgroundThreadHandoffProducer";
    private final Producer<T> inputProducer;
    private final ThreadHandoffProducerQueue threadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer<T> inputProducer, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        Intrinsics.checkNotNullParameter(inputProducer, "inputProducer");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue, "threadHandoffProducerQueue");
        this.inputProducer = inputProducer;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue;
    }

    public final Producer<T> getInputProducer() {
        return this.inputProducer;
    }

    public final ThreadHandoffProducerQueue getThreadHandoffProducerQueue() {
        return this.threadHandoffProducerQueue;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<T> consumer, final ProducerContext context) {
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(context, "context");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            final ProducerListener2 producerListener = context.getProducerListener();
            Companion companion = INSTANCE;
            if (companion.shouldRunImmediately(context)) {
                producerListener.onProducerStart(context, PRODUCER_NAME);
                producerListener.onProducerFinishWithSuccess(context, PRODUCER_NAME, null);
                this.inputProducer.produceResults(consumer, context);
                return;
            } else {
                final StatefulProducerRunnable<T> statefulProducerRunnable = new StatefulProducerRunnable<T>(consumer, producerListener, context, this) { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducer$produceResults$1$statefulRunnable$1
                    final /* synthetic */ Consumer<T> $consumer;
                    final /* synthetic */ ProducerContext $context;
                    final /* synthetic */ ProducerListener2 $producerListener;
                    final /* synthetic */ ThreadHandoffProducer<T> this$0;

                    @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
                    protected void disposeResult(T ignored) {
                    }

                    @Override // com.facebook.common.executors.StatefulRunnable
                    protected T getResult() throws Exception {
                        return null;
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(consumer, producerListener, context, ThreadHandoffProducer.PRODUCER_NAME);
                        this.$consumer = consumer;
                        this.$producerListener = producerListener;
                        this.$context = context;
                        this.this$0 = this;
                    }

                    @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
                    protected void onSuccess(T ignored) {
                        this.$producerListener.onProducerFinishWithSuccess(this.$context, ThreadHandoffProducer.PRODUCER_NAME, null);
                        this.this$0.getInputProducer().produceResults(this.$consumer, this.$context);
                    }
                };
                context.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducer$produceResults$1$1
                    @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                    public void onCancellationRequested() {
                        statefulProducerRunnable.cancel();
                        this.getThreadHandoffProducerQueue().remove(statefulProducerRunnable);
                    }
                });
                this.threadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(statefulProducerRunnable, companion.getInstrumentationTag(context)));
                return;
            }
        }
        FrescoSystrace.beginSection("ThreadHandoffProducer#produceResults");
        try {
            final ProducerListener2 producerListener2 = context.getProducerListener();
            Companion companion2 = INSTANCE;
            if (companion2.shouldRunImmediately(context)) {
                producerListener2.onProducerStart(context, PRODUCER_NAME);
                producerListener2.onProducerFinishWithSuccess(context, PRODUCER_NAME, null);
                this.inputProducer.produceResults(consumer, context);
            } else {
                final StatefulProducerRunnable<T> statefulProducerRunnable2 = new StatefulProducerRunnable<T>(consumer, producerListener2, context, this) { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducer$produceResults$1$statefulRunnable$1
                    final /* synthetic */ Consumer<T> $consumer;
                    final /* synthetic */ ProducerContext $context;
                    final /* synthetic */ ProducerListener2 $producerListener;
                    final /* synthetic */ ThreadHandoffProducer<T> this$0;

                    @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
                    protected void disposeResult(T ignored) {
                    }

                    @Override // com.facebook.common.executors.StatefulRunnable
                    protected T getResult() throws Exception {
                        return null;
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(consumer, producerListener2, context, ThreadHandoffProducer.PRODUCER_NAME);
                        this.$consumer = consumer;
                        this.$producerListener = producerListener2;
                        this.$context = context;
                        this.this$0 = this;
                    }

                    @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
                    protected void onSuccess(T ignored) {
                        this.$producerListener.onProducerFinishWithSuccess(this.$context, ThreadHandoffProducer.PRODUCER_NAME, null);
                        this.this$0.getInputProducer().produceResults(this.$consumer, this.$context);
                    }
                };
                context.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.ThreadHandoffProducer$produceResults$1$1
                    @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                    public void onCancellationRequested() {
                        statefulProducerRunnable2.cancel();
                        this.getThreadHandoffProducerQueue().remove(statefulProducerRunnable2);
                    }
                });
                this.threadHandoffProducerQueue.addToQueueOrExecute(FrescoInstrumenter.decorateRunnable(statefulProducerRunnable2, companion2.getInstrumentationTag(context)));
                Unit unit = Unit.INSTANCE;
            }
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* compiled from: ThreadHandoffProducer.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/facebook/imagepipeline/producers/ThreadHandoffProducer$Companion;", "", "()V", "PRODUCER_NAME", "", "getInstrumentationTag", f.X, "Lcom/facebook/imagepipeline/producers/ProducerContext;", "shouldRunImmediately", "", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String getInstrumentationTag(ProducerContext context) {
            if (!FrescoInstrumenter.isTracing()) {
                return null;
            }
            return "ThreadHandoffProducer_produceResults_" + context.getId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean shouldRunImmediately(ProducerContext context) {
            return context.getImagePipelineConfig().getExperiments().getHandOffOnUiThreadOnly() && Looper.getMainLooper().getThread() != Thread.currentThread();
        }
    }
}
