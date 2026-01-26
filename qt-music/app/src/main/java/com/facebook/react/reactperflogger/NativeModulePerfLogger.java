package com.facebook.react.reactperflogger;

import com.facebook.jni.HybridData;
import kotlin.Metadata;

/* compiled from: NativeModulePerfLogger.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\b&\u0018\u00002\u00020\u0001B\u0007\b\u0004¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0004H$J\b\u0010\u0007\u001a\u00020\bH\u0004J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0011\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0012\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0015\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0017"}, d2 = {"Lcom/facebook/react/reactperflogger/NativeModulePerfLogger;", "", "()V", "mHybridData", "Lcom/facebook/jni/HybridData;", "getMHybridData$annotations", "initHybrid", "maybeLoadOtherSoLibraries", "", "moduleCreateCacheHit", "moduleName", "", "id", "", "moduleCreateConstructEnd", "moduleCreateConstructStart", "moduleCreateEnd", "moduleCreateFail", "moduleCreateSetUpEnd", "moduleCreateSetUpStart", "moduleCreateStart", "moduleDataCreateEnd", "moduleDataCreateStart", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public abstract class NativeModulePerfLogger {
    private final HybridData mHybridData;

    private static /* synthetic */ void getMHybridData$annotations() {
    }

    protected abstract HybridData initHybrid();

    public abstract void moduleCreateCacheHit(String moduleName, int id);

    public abstract void moduleCreateConstructEnd(String moduleName, int id);

    public abstract void moduleCreateConstructStart(String moduleName, int id);

    public abstract void moduleCreateEnd(String moduleName, int id);

    public abstract void moduleCreateFail(String moduleName, int id);

    public abstract void moduleCreateSetUpEnd(String moduleName, int id);

    public abstract void moduleCreateSetUpStart(String moduleName, int id);

    public abstract void moduleCreateStart(String moduleName, int id);

    public abstract void moduleDataCreateEnd(String moduleName, int id);

    public abstract void moduleDataCreateStart(String moduleName, int id);

    protected NativeModulePerfLogger() {
        maybeLoadOtherSoLibraries();
        this.mHybridData = initHybrid();
    }

    protected final synchronized void maybeLoadOtherSoLibraries() {
    }
}
