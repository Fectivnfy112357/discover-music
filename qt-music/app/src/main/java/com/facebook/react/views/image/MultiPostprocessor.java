package com.facebook.react.views.image;

import android.graphics.Bitmap;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.request.Postprocessor;
import com.umeng.commonsdk.framework.UMModuleRegister;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MultiPostprocessor.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/views/image/MultiPostprocessor;", "Lcom/facebook/imagepipeline/request/Postprocessor;", "postprocessors", "", "(Ljava/util/List;)V", "getName", "", "getPostprocessorCacheKey", "Lcom/facebook/cache/common/CacheKey;", UMModuleRegister.PROCESS, "Lcom/facebook/common/references/CloseableReference;", "Landroid/graphics/Bitmap;", "sourceBitmap", "bitmapFactory", "Lcom/facebook/imagepipeline/bitmaps/PlatformBitmapFactory;", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class MultiPostprocessor implements Postprocessor {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<Postprocessor> postprocessors;

    public /* synthetic */ MultiPostprocessor(List list, DefaultConstructorMarker defaultConstructorMarker) {
        this(list);
    }

    @JvmStatic
    public static final Postprocessor from(List<? extends Postprocessor> list) {
        return INSTANCE.from(list);
    }

    private MultiPostprocessor(List<? extends Postprocessor> list) {
        this.postprocessors = new LinkedList(list);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public String getName() {
        return "MultiPostProcessor (" + CollectionsKt.joinToString$default(this.postprocessors, ",", null, null, 0, null, null, 62, null) + ")";
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    /* renamed from: getPostprocessorCacheKey */
    public CacheKey getCacheKey() {
        List<Postprocessor> list = this.postprocessors;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Postprocessor) it.next()).getCacheKey());
        }
        return new MultiCacheKey(arrayList);
    }

    @Override // com.facebook.imagepipeline.request.Postprocessor
    public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(sourceBitmap, "sourceBitmap");
        Intrinsics.checkNotNullParameter(bitmapFactory, "bitmapFactory");
        CloseableReference<Bitmap> closeableReferenceProcess = null;
        try {
            CloseableReference<Bitmap> closeableReferenceMo319clone = null;
            for (Postprocessor postprocessor : this.postprocessors) {
                if (closeableReferenceMo319clone == null || (bitmap = closeableReferenceMo319clone.get()) == null) {
                    bitmap = sourceBitmap;
                }
                closeableReferenceProcess = postprocessor.process(bitmap, bitmapFactory);
                CloseableReference.closeSafely(closeableReferenceMo319clone);
                closeableReferenceMo319clone = closeableReferenceProcess.mo319clone();
            }
            if (closeableReferenceProcess == null) {
                throw new IllegalStateException(("MultiPostprocessor returned null bitmap - Number of Postprocessors: " + this.postprocessors.size()).toString());
            }
            CloseableReference<Bitmap> closeableReferenceMo319clone2 = closeableReferenceProcess.mo319clone();
            Intrinsics.checkNotNullExpressionValue(closeableReferenceMo319clone2, "clone(...)");
            return closeableReferenceMo319clone2;
        } finally {
            CloseableReference.closeSafely((CloseableReference<?>) null);
        }
    }

    /* compiled from: MultiPostprocessor.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/views/image/MultiPostprocessor$Companion;", "", "()V", "from", "Lcom/facebook/imagepipeline/request/Postprocessor;", "postprocessors", "", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Postprocessor from(List<? extends Postprocessor> postprocessors) {
            Intrinsics.checkNotNullParameter(postprocessors, "postprocessors");
            int size = postprocessors.size();
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (size == 0) {
                return null;
            }
            if (size == 1) {
                return postprocessors.get(0);
            }
            return new MultiPostprocessor(postprocessors, defaultConstructorMarker);
        }
    }
}
