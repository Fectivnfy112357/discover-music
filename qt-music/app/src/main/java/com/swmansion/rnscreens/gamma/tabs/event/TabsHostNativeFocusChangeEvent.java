package com.swmansion.rnscreens.gamma.tabs.event;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.Event;
import com.swmansion.rnscreens.gamma.common.NamingAwareEventType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TabsHostNativeFocusChangeEvent.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0012B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\f\u001a\u00020\rH\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\bH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/event/TabsHostNativeFocusChangeEvent;", "Lcom/facebook/react/uimanager/events/Event;", "Lcom/swmansion/rnscreens/gamma/tabs/event/TabScreenDidAppearEvent;", "Lcom/swmansion/rnscreens/gamma/common/NamingAwareEventType;", "surfaceId", "", "viewId", TabsHostNativeFocusChangeEvent.EVENT_KEY_TAB_KEY, "", "(IILjava/lang/String;)V", "getTabKey", "()Ljava/lang/String;", "getCoalescingKey", "", "getEventData", "Lcom/facebook/react/bridge/WritableMap;", "getEventName", "getEventRegistrationName", "Companion", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TabsHostNativeFocusChangeEvent extends Event<TabScreenDidAppearEvent> implements NamingAwareEventType {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String EVENT_KEY_TAB_KEY = "tabKey";
    public static final String EVENT_NAME = "topNativeFocusChange";
    public static final String EVENT_REGISTRATION_NAME = "onNativeFocusChange";
    private final String tabKey;

    @Override // com.facebook.react.uimanager.events.Event
    public short getCoalescingKey() {
        return (short) 0;
    }

    public final String getTabKey() {
        return this.tabKey;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TabsHostNativeFocusChangeEvent(int i, int i2, String tabKey) {
        super(i, i2);
        Intrinsics.checkNotNullParameter(tabKey, "tabKey");
        this.tabKey = tabKey;
    }

    @Override // com.facebook.react.uimanager.events.Event
    public String getEventName() {
        return EVENT_NAME;
    }

    @Override // com.swmansion.rnscreens.gamma.common.NamingAwareEventType
    public String getEventRegistrationName() {
        return EVENT_REGISTRATION_NAME;
    }

    @Override // com.facebook.react.uimanager.events.Event
    protected WritableMap getEventData() {
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putString(EVENT_KEY_TAB_KEY, this.tabKey);
        return writableMapCreateMap;
    }

    /* compiled from: TabsHostNativeFocusChangeEvent.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/swmansion/rnscreens/gamma/tabs/event/TabsHostNativeFocusChangeEvent$Companion;", "Lcom/swmansion/rnscreens/gamma/common/NamingAwareEventType;", "()V", "EVENT_KEY_TAB_KEY", "", "EVENT_NAME", "EVENT_REGISTRATION_NAME", "getEventName", "getEventRegistrationName", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion implements NamingAwareEventType {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @Override // com.swmansion.rnscreens.gamma.common.NamingAwareEventType
        public String getEventName() {
            return TabsHostNativeFocusChangeEvent.EVENT_NAME;
        }

        @Override // com.swmansion.rnscreens.gamma.common.NamingAwareEventType
        public String getEventRegistrationName() {
            return TabsHostNativeFocusChangeEvent.EVENT_REGISTRATION_NAME;
        }
    }
}
