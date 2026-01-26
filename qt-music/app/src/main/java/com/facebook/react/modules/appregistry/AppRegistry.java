package com.facebook.react.modules.appregistry;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.WritableMap;
import kotlin.Metadata;

/* compiled from: AppRegistry.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\nH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/modules/appregistry/AppRegistry;", "Lcom/facebook/react/bridge/JavaScriptModule;", "runApplication", "", "appKey", "", "appParameters", "Lcom/facebook/react/bridge/WritableMap;", "startHeadlessTask", "taskId", "", "taskKey", "data", "unmountApplicationComponentAtRootTag", "rootNodeTag", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public interface AppRegistry extends JavaScriptModule {
    void runApplication(String appKey, WritableMap appParameters);

    void startHeadlessTask(int taskId, String taskKey, WritableMap data);

    void unmountApplicationComponentAtRootTag(int rootNodeTag);
}
