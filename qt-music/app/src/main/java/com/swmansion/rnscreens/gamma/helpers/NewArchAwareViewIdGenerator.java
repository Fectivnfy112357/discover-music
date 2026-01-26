package com.swmansion.rnscreens.gamma.helpers;

import kotlin.Metadata;

/* compiled from: ViewIdHelpers.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/swmansion/rnscreens/gamma/helpers/NewArchAwareViewIdGenerator;", "Lcom/swmansion/rnscreens/gamma/helpers/ViewIdProviding;", "()V", "nextId", "", "generateViewId", "isValidReactRootTag", "", "tag", "progressViewId", "", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
final class NewArchAwareViewIdGenerator implements ViewIdProviding {
    private int nextId = 3;

    @Override // com.swmansion.rnscreens.gamma.helpers.ViewIdProviding
    public int generateViewId() {
        int i = this.nextId;
        progressViewId();
        return i;
    }

    private final void progressViewId() {
        int i = this.nextId + 2;
        this.nextId = i;
        if (isValidReactRootTag(i)) {
            this.nextId += 2;
        }
    }

    private final boolean isValidReactRootTag(int tag) {
        return tag % 10 == 1;
    }
}
