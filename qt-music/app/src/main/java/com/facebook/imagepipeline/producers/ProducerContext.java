package com.facebook.imagepipeline.producers;

import com.facebook.fresco.middleware.HasExtraData;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.core.ImagePipelineConfigInterface;
import com.facebook.imagepipeline.request.ImageRequest;
import kotlin.Metadata;

/* compiled from: ProducerContext.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'H&J\u0012\u0010(\u001a\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u0007H&J\u001c\u0010(\u001a\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u00072\b\u0010*\u001a\u0004\u0018\u00010\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\u0004\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\t¨\u0006+"}, d2 = {"Lcom/facebook/imagepipeline/producers/ProducerContext;", "Lcom/facebook/fresco/middleware/HasExtraData;", "callerContext", "", "getCallerContext", "()Ljava/lang/Object;", "id", "", "getId", "()Ljava/lang/String;", "imagePipelineConfig", "Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "getImagePipelineConfig", "()Lcom/facebook/imagepipeline/core/ImagePipelineConfigInterface;", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getImageRequest", "()Lcom/facebook/imagepipeline/request/ImageRequest;", "isIntermediateResultExpected", "", "()Z", "isPrefetch", "lowestPermittedRequestLevel", "Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;", "getLowestPermittedRequestLevel", "()Lcom/facebook/imagepipeline/request/ImageRequest$RequestLevel;", "priority", "Lcom/facebook/imagepipeline/common/Priority;", "getPriority", "()Lcom/facebook/imagepipeline/common/Priority;", "producerListener", "Lcom/facebook/imagepipeline/producers/ProducerListener2;", "getProducerListener", "()Lcom/facebook/imagepipeline/producers/ProducerListener2;", "uiComponentId", "getUiComponentId", "addCallbacks", "", "callbacks", "Lcom/facebook/imagepipeline/producers/ProducerContextCallbacks;", "putOriginExtra", "origin", "subcategory", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface ProducerContext extends HasExtraData {
    void addCallbacks(ProducerContextCallbacks callbacks);

    Object getCallerContext();

    String getId();

    ImagePipelineConfigInterface getImagePipelineConfig();

    ImageRequest getImageRequest();

    ImageRequest.RequestLevel getLowestPermittedRequestLevel();

    Priority getPriority();

    ProducerListener2 getProducerListener();

    String getUiComponentId();

    boolean isIntermediateResultExpected();

    boolean isPrefetch();

    void putOriginExtra(String origin);

    void putOriginExtra(String origin, String subcategory);
}
