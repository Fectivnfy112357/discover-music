package com.facebook.react.uimanager;

import com.facebook.yoga.YogaConfig;
import com.facebook.yoga.YogaConfigFactory;
import com.facebook.yoga.YogaErrata;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactYogaConfigProvider.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\u0004H\u0007R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/facebook/react/uimanager/ReactYogaConfigProvider;", "", "()V", "yogaConfig", "Lcom/facebook/yoga/YogaConfig;", "getYogaConfig", "()Lcom/facebook/yoga/YogaConfig;", "yogaConfig$delegate", "Lkotlin/Lazy;", "get", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactYogaConfigProvider {
    public static final ReactYogaConfigProvider INSTANCE = new ReactYogaConfigProvider();

    /* renamed from: yogaConfig$delegate, reason: from kotlin metadata */
    private static final Lazy yogaConfig = LazyKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<YogaConfig>() { // from class: com.facebook.react.uimanager.ReactYogaConfigProvider$yogaConfig$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final YogaConfig invoke() {
            YogaConfig yogaConfigCreate = YogaConfigFactory.create();
            yogaConfigCreate.setPointScaleFactor(0.0f);
            yogaConfigCreate.setErrata(YogaErrata.ALL);
            return yogaConfigCreate;
        }
    });

    private ReactYogaConfigProvider() {
    }

    private final YogaConfig getYogaConfig() {
        Object value = yogaConfig.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (YogaConfig) value;
    }

    @JvmStatic
    public static final YogaConfig get() {
        return INSTANCE.getYogaConfig();
    }
}
