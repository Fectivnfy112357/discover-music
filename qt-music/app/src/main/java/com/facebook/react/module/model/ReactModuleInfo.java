package com.facebook.react.module.model;

import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactModuleInfo.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B?\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u000bB5\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0006\u0010\n\u001a\u00020\u0006¢\u0006\u0002\u0010\u0010J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0004\u001a\u00020\u0003J\b\u0010\b\u001a\u00020\u0006H\u0007J\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010\u0007\u001a\u00020\u0006R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/module/model/ReactModuleInfo;", "", "name", "", "className", "canOverrideExistingModule", "", "needsEagerInit", "hasConstants", "isCxxModule", "isTurboModule", "(Ljava/lang/String;Ljava/lang/String;ZZZZZ)V", "_name", "_className", "_canOverrideExistingModule", "_needsEagerInit", "(Ljava/lang/String;Ljava/lang/String;ZZZZ)V", "()Z", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ReactModuleInfo {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean _canOverrideExistingModule;
    private final String _className;
    private final String _name;
    private final boolean _needsEagerInit;
    private final boolean isCxxModule;
    private final boolean isTurboModule;

    @JvmStatic
    public static final boolean classIsTurboModule(Class<?> cls) {
        return INSTANCE.classIsTurboModule(cls);
    }

    @Deprecated(message = "this is hardcoded to return true, regardless if the module has constants or not")
    public final boolean hasConstants() {
        return true;
    }

    public ReactModuleInfo(String _name, String _className, boolean z, boolean z2, boolean z3, boolean z4) {
        Intrinsics.checkNotNullParameter(_name, "_name");
        Intrinsics.checkNotNullParameter(_className, "_className");
        this._name = _name;
        this._className = _className;
        this._canOverrideExistingModule = z;
        this._needsEagerInit = z2;
        this.isCxxModule = z3;
        this.isTurboModule = z4;
    }

    /* renamed from: isCxxModule, reason: from getter */
    public final boolean getIsCxxModule() {
        return this.isCxxModule;
    }

    /* renamed from: isTurboModule, reason: from getter */
    public final boolean getIsTurboModule() {
        return this.isTurboModule;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "use ReactModuleInfo(String, String, boolean, boolean, boolean, boolean)]")
    public ReactModuleInfo(String name, String className, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this(name, className, z, z2, z4, z5);
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(className, "className");
    }

    /* renamed from: name, reason: from getter */
    public final String get_name() {
        return this._name;
    }

    /* renamed from: className, reason: from getter */
    public final String get_className() {
        return this._className;
    }

    /* renamed from: canOverrideExistingModule, reason: from getter */
    public final boolean get_canOverrideExistingModule() {
        return this._canOverrideExistingModule;
    }

    /* renamed from: needsEagerInit, reason: from getter */
    public final boolean get_needsEagerInit() {
        return this._needsEagerInit;
    }

    /* compiled from: ReactModuleInfo.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0007¨\u0006\u0007"}, d2 = {"Lcom/facebook/react/module/model/ReactModuleInfo$Companion;", "", "()V", "classIsTurboModule", "", "clazz", "Ljava/lang/Class;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final boolean classIsTurboModule(Class<?> clazz) {
            Intrinsics.checkNotNullParameter(clazz, "clazz");
            return TurboModule.class.isAssignableFrom(clazz);
        }
    }
}
