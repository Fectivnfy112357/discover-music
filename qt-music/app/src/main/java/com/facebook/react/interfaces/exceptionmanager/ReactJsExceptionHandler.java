package com.facebook.react.interfaces.exceptionmanager;

import com.facebook.react.common.annotations.UnstableReactNativeAPI;
import com.facebook.react.devsupport.StackTraceHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ReactJsExceptionHandler.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bç\u0080\u0001\u0018\u00002\u00020\u0001:\u0003\u0006\u0007\bJ\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler;", "", "reportJsException", "", "errorMap", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedError;", "ParsedError", "ParsedErrorImpl", "ParsedStackFrameImpl", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@UnstableReactNativeAPI
/* loaded from: classes2.dex */
public interface ReactJsExceptionHandler {

    /* compiled from: ReactJsExceptionHandler.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001:\u0001\u0012R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedError;", "", "exceptionId", "", "getExceptionId", "()I", "frames", "", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedError$StackFrame;", "getFrames", "()Ljava/util/List;", StackTraceHelper.IS_FATAL_KEY, "", "()Z", StackTraceHelper.MESSAGE_KEY, "", "getMessage", "()Ljava/lang/String;", "StackFrame", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public interface ParsedError {

        /* compiled from: ReactJsExceptionHandler.kt */
        @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedError$StackFrame;", "", "columnNumber", "", "getColumnNumber", "()I", "fileName", "", "getFileName", "()Ljava/lang/String;", "lineNumber", "getLineNumber", "methodName", "getMethodName", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
        public interface StackFrame {
            int getColumnNumber();

            String getFileName();

            int getLineNumber();

            String getMethodName();
        }

        int getExceptionId();

        List<StackFrame> getFrames();

        String getMessage();

        boolean isFatal();
    }

    void reportJsException(ParsedError errorMap);

    /* compiled from: ReactJsExceptionHandler.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0083\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedStackFrameImpl;", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedError$StackFrame;", "fileName", "", "methodName", "lineNumber", "", "columnNumber", "(Ljava/lang/String;Ljava/lang/String;II)V", "getColumnNumber", "()I", "getFileName", "()Ljava/lang/String;", "getLineNumber", "getMethodName", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class ParsedStackFrameImpl implements ParsedError.StackFrame {
        private final int columnNumber;
        private final String fileName;
        private final int lineNumber;
        private final String methodName;

        public static /* synthetic */ ParsedStackFrameImpl copy$default(ParsedStackFrameImpl parsedStackFrameImpl, String str, String str2, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                str = parsedStackFrameImpl.fileName;
            }
            if ((i3 & 2) != 0) {
                str2 = parsedStackFrameImpl.methodName;
            }
            if ((i3 & 4) != 0) {
                i = parsedStackFrameImpl.lineNumber;
            }
            if ((i3 & 8) != 0) {
                i2 = parsedStackFrameImpl.columnNumber;
            }
            return parsedStackFrameImpl.copy(str, str2, i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFileName() {
            return this.fileName;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMethodName() {
            return this.methodName;
        }

        /* renamed from: component3, reason: from getter */
        public final int getLineNumber() {
            return this.lineNumber;
        }

        /* renamed from: component4, reason: from getter */
        public final int getColumnNumber() {
            return this.columnNumber;
        }

        public final ParsedStackFrameImpl copy(String fileName, String methodName, int lineNumber, int columnNumber) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(methodName, "methodName");
            return new ParsedStackFrameImpl(fileName, methodName, lineNumber, columnNumber);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedStackFrameImpl)) {
                return false;
            }
            ParsedStackFrameImpl parsedStackFrameImpl = (ParsedStackFrameImpl) other;
            return Intrinsics.areEqual(this.fileName, parsedStackFrameImpl.fileName) && Intrinsics.areEqual(this.methodName, parsedStackFrameImpl.methodName) && this.lineNumber == parsedStackFrameImpl.lineNumber && this.columnNumber == parsedStackFrameImpl.columnNumber;
        }

        public int hashCode() {
            return (((((this.fileName.hashCode() * 31) + this.methodName.hashCode()) * 31) + Integer.hashCode(this.lineNumber)) * 31) + Integer.hashCode(this.columnNumber);
        }

        public String toString() {
            return "ParsedStackFrameImpl(fileName=" + this.fileName + ", methodName=" + this.methodName + ", lineNumber=" + this.lineNumber + ", columnNumber=" + this.columnNumber + ")";
        }

        public ParsedStackFrameImpl(String fileName, String methodName, int i, int i2) {
            Intrinsics.checkNotNullParameter(fileName, "fileName");
            Intrinsics.checkNotNullParameter(methodName, "methodName");
            this.fileName = fileName;
            this.methodName = methodName;
            this.lineNumber = i;
            this.columnNumber = i2;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError.StackFrame
        public String getFileName() {
            return this.fileName;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError.StackFrame
        public String getMethodName() {
            return this.methodName;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError.StackFrame
        public int getLineNumber() {
            return this.lineNumber;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError.StackFrame
        public int getColumnNumber() {
            return this.columnNumber;
        }
    }

    /* compiled from: ReactJsExceptionHandler.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0083\b\u0018\u00002\u00020\u0001B+\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J\t\u0010\u0016\u001a\u00020\nHÆ\u0003J7\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0018\u001a\u00020\n2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001d"}, d2 = {"Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedErrorImpl;", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedError;", "frames", "Ljava/util/ArrayList;", "Lcom/facebook/react/interfaces/exceptionmanager/ReactJsExceptionHandler$ParsedStackFrameImpl;", StackTraceHelper.MESSAGE_KEY, "", "exceptionId", "", StackTraceHelper.IS_FATAL_KEY, "", "(Ljava/util/ArrayList;Ljava/lang/String;IZ)V", "getExceptionId", "()I", "getFrames", "()Ljava/util/ArrayList;", "()Z", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "other", "", "hashCode", "toString", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    private static final /* data */ class ParsedErrorImpl implements ParsedError {
        private final int exceptionId;
        private final ArrayList<ParsedStackFrameImpl> frames;
        private final boolean isFatal;
        private final String message;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ParsedErrorImpl copy$default(ParsedErrorImpl parsedErrorImpl, ArrayList arrayList, String str, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                arrayList = parsedErrorImpl.frames;
            }
            if ((i2 & 2) != 0) {
                str = parsedErrorImpl.message;
            }
            if ((i2 & 4) != 0) {
                i = parsedErrorImpl.exceptionId;
            }
            if ((i2 & 8) != 0) {
                z = parsedErrorImpl.isFatal;
            }
            return parsedErrorImpl.copy(arrayList, str, i, z);
        }

        public final ArrayList<ParsedStackFrameImpl> component1() {
            return this.frames;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* renamed from: component3, reason: from getter */
        public final int getExceptionId() {
            return this.exceptionId;
        }

        /* renamed from: component4, reason: from getter */
        public final boolean getIsFatal() {
            return this.isFatal;
        }

        public final ParsedErrorImpl copy(ArrayList<ParsedStackFrameImpl> frames, String message, int exceptionId, boolean isFatal) {
            Intrinsics.checkNotNullParameter(frames, "frames");
            Intrinsics.checkNotNullParameter(message, "message");
            return new ParsedErrorImpl(frames, message, exceptionId, isFatal);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ParsedErrorImpl)) {
                return false;
            }
            ParsedErrorImpl parsedErrorImpl = (ParsedErrorImpl) other;
            return Intrinsics.areEqual(this.frames, parsedErrorImpl.frames) && Intrinsics.areEqual(this.message, parsedErrorImpl.message) && this.exceptionId == parsedErrorImpl.exceptionId && this.isFatal == parsedErrorImpl.isFatal;
        }

        public int hashCode() {
            return (((((this.frames.hashCode() * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.exceptionId)) * 31) + Boolean.hashCode(this.isFatal);
        }

        public String toString() {
            return "ParsedErrorImpl(frames=" + this.frames + ", message=" + this.message + ", exceptionId=" + this.exceptionId + ", isFatal=" + this.isFatal + ")";
        }

        public ParsedErrorImpl(ArrayList<ParsedStackFrameImpl> frames, String message, int i, boolean z) {
            Intrinsics.checkNotNullParameter(frames, "frames");
            Intrinsics.checkNotNullParameter(message, "message");
            this.frames = frames;
            this.message = message;
            this.exceptionId = i;
            this.isFatal = z;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError
        public ArrayList<ParsedStackFrameImpl> getFrames() {
            return this.frames;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError
        public String getMessage() {
            return this.message;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError
        public int getExceptionId() {
            return this.exceptionId;
        }

        @Override // com.facebook.react.interfaces.exceptionmanager.ReactJsExceptionHandler.ParsedError
        public boolean isFatal() {
            return this.isFatal;
        }
    }
}
