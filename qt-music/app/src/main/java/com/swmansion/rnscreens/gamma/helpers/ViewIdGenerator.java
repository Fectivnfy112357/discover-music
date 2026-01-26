package com.swmansion.rnscreens.gamma.helpers;

import kotlin.Metadata;

/* compiled from: ViewIdHelpers.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/gamma/helpers/ViewIdGenerator;", "Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;", "()V", "defaultGenerator", "externalGenerator", "getExternalGenerator", "()Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;", "setExternalGenerator", "(Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;)V", "generateViewId", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ViewIdGenerator implements ViewIdProviding {
    public static final ViewIdGenerator INSTANCE = new ViewIdGenerator();
    private static final ViewIdProviding defaultGenerator = new NewArchAwareViewIdGenerator();
    private static ViewIdProviding externalGenerator;

    private ViewIdGenerator() {
    }

    public final ViewIdProviding getExternalGenerator() {
        return externalGenerator;
    }

    public final void setExternalGenerator(ViewIdProviding viewIdProviding) {
        externalGenerator = viewIdProviding;
    }

    @Override // com.swmansion.rnscreens.gamma.helpers.ViewIdProviding
    public int generateViewId() {
        ViewIdProviding viewIdProviding = externalGenerator;
        return viewIdProviding != null ? viewIdProviding.generateViewId() : defaultGenerator.generateViewId();
    }
}
