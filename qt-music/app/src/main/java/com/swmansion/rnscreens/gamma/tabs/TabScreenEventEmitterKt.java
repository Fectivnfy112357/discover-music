package com.swmansion.rnscreens.gamma.tabs;

import com.swmansion.rnscreens.utils.RNSLog;
import kotlin.Metadata;

/* compiled from: TabScreenEventEmitter.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006\u0006"}, d2 = {"logEventDispatch", "", "viewTag", "", "eventName", "", "react-native-screens_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabScreenEventEmitterKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void logEventDispatch(int i, String str) {
        RNSLog.INSTANCE.d(TabScreenEventEmitter.TAG, "TabScreen [" + i + "] emits event: " + str);
    }
}
