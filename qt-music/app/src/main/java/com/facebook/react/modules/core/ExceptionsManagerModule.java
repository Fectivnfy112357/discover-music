package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.fbreact.specs.NativeExceptionsManagerSpec;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.JavascriptException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.util.ExceptionDataHelper;
import com.facebook.react.util.JSStackTrace;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ExceptionsManagerModule.kt */
@ReactModule(name = NativeExceptionsManagerSpec.NAME)
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\u0011\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J$\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0015\u001a\u00020\u0010H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/facebook/react/modules/core/ExceptionsManagerModule;", "Lcom/facebook/fbreact/specs/NativeExceptionsManagerSpec;", "devSupportManager", "Lcom/facebook/react/devsupport/interfaces/DevSupportManager;", "(Lcom/facebook/react/devsupport/interfaces/DevSupportManager;)V", "dismissRedbox", "", "reportException", "data", "Lcom/facebook/react/bridge/ReadableMap;", "reportFatalException", StackTraceHelper.MESSAGE_KEY, "", "stack", "Lcom/facebook/react/bridge/ReadableArray;", "idDouble", "", "reportSoftException", "updateExceptionMessage", "title", "details", "exceptionIdDouble", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class ExceptionsManagerModule extends NativeExceptionsManagerSpec {
    private final DevSupportManager devSupportManager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExceptionsManagerModule(DevSupportManager devSupportManager) {
        super(null);
        Intrinsics.checkNotNullParameter(devSupportManager, "devSupportManager");
        this.devSupportManager = devSupportManager;
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void reportFatalException(String message, ReadableArray stack, double idDouble) throws IOException {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString(StackTraceHelper.MESSAGE_KEY, message);
        javaOnlyMap.putArray("stack", stack);
        javaOnlyMap.putInt("id", (int) idDouble);
        javaOnlyMap.putBoolean(StackTraceHelper.IS_FATAL_KEY, true);
        reportException(javaOnlyMap);
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void reportSoftException(String message, ReadableArray stack, double idDouble) throws IOException {
        JavaOnlyMap javaOnlyMap = new JavaOnlyMap();
        javaOnlyMap.putString(StackTraceHelper.MESSAGE_KEY, message);
        javaOnlyMap.putArray("stack", stack);
        javaOnlyMap.putInt("id", (int) idDouble);
        javaOnlyMap.putBoolean(StackTraceHelper.IS_FATAL_KEY, false);
        reportException(javaOnlyMap);
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void reportException(ReadableMap data) throws IOException {
        Intrinsics.checkNotNullParameter(data, "data");
        String string = data.getString(StackTraceHelper.MESSAGE_KEY);
        if (string == null) {
            string = "";
        }
        WritableArray array = data.getArray("stack");
        if (array == null) {
            array = Arguments.createArray();
        }
        boolean z = data.hasKey(StackTraceHelper.IS_FATAL_KEY) ? data.getBoolean(StackTraceHelper.IS_FATAL_KEY) : false;
        String extraDataAsJson = ExceptionDataHelper.getExtraDataAsJson(data);
        if (z) {
            Intrinsics.checkNotNull(array);
            JavascriptException javascriptException = new JavascriptException(JSStackTrace.format(string, array));
            javascriptException.setExtraDataAsJson(extraDataAsJson);
            throw javascriptException;
        }
        Intrinsics.checkNotNull(array);
        FLog.e(ReactConstants.TAG, JSStackTrace.format(string, array));
        if (extraDataAsJson != null) {
            FLog.d(ReactConstants.TAG, "extraData: %s", extraDataAsJson);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void updateExceptionMessage(String title, ReadableArray details, double exceptionIdDouble) {
        int i = (int) exceptionIdDouble;
        if (this.devSupportManager.getDevSupportEnabled()) {
            this.devSupportManager.updateJSError(title, details, i);
        }
    }

    @Override // com.facebook.fbreact.specs.NativeExceptionsManagerSpec
    public void dismissRedbox() {
        if (this.devSupportManager.getDevSupportEnabled()) {
            this.devSupportManager.hideRedboxDialog();
        }
    }
}
