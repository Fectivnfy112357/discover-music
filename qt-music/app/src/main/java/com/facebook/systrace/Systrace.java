package com.facebook.systrace;

import androidx.tracing.Trace;
import java.lang.reflect.InvocationTargetException;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Systrace.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001+B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004H\u0007J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J3\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0015J#\u0010\u0016\u001a\u00020\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\u00132\u0006\u0010\u0014\u001a\u00020\u000fH\u0002¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J \u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J(\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0004H\u0007J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\u0004H\u0007J\u0012\u0010\u001e\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007J \u0010!\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J \u0010\"\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007J \u0010#\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\u000fH\u0007J$\u0010&\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010'\u001a\u0004\u0018\u00010\r2\b\u0010(\u001a\u0004\u0018\u00010)H\u0007J\u0012\u0010*\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/facebook/systrace/Systrace;", "", "()V", "TRACE_TAG_REACT_APPS", "", "TRACE_TAG_REACT_FRESCO", "TRACE_TAG_REACT_JAVA_BRIDGE", "TRACE_TAG_REACT_JS_VM_CALLS", "TRACE_TAG_REACT_VIEW", "beginAsyncSection", "", "tag", "sectionName", "", "cookie", "", "startNanos", "beginSection", "args", "", "argsLength", "(JLjava/lang/String;[Ljava/lang/String;I)V", "convertArgsToText", "([Ljava/lang/String;I)Ljava/lang/String;", "endAsyncFlow", "endAsyncSection", "endNanos", "endSection", "isTracing", "", "registerListener", "listener", "Lcom/facebook/systrace/TraceListener;", "startAsyncFlow", "stepAsyncFlow", "traceCounter", "counterName", "counterValue", "traceInstant", "title", "scope", "Lcom/facebook/systrace/Systrace$EventScope;", "unregisterListener", "EventScope", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class Systrace {
    public static final Systrace INSTANCE = new Systrace();
    public static final long TRACE_TAG_REACT_APPS = 0;
    public static final long TRACE_TAG_REACT_FRESCO = 0;
    public static final long TRACE_TAG_REACT_JAVA_BRIDGE = 0;
    public static final long TRACE_TAG_REACT_JS_VM_CALLS = 0;
    public static final long TRACE_TAG_REACT_VIEW = 0;

    @JvmStatic
    public static final boolean isTracing(long tag) {
        return false;
    }

    @JvmStatic
    public static final void registerListener(TraceListener listener) {
    }

    @JvmStatic
    public static final void stepAsyncFlow(long tag, String sectionName, int cookie) {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
    }

    @JvmStatic
    public static final void traceInstant(long tag, String title, EventScope scope) {
    }

    @JvmStatic
    public static final void unregisterListener(TraceListener listener) {
    }

    private Systrace() {
    }

    @JvmStatic
    public static final void beginSection(long tag, String sectionName) {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        Trace.beginSection(sectionName);
    }

    @JvmStatic
    public static final void beginSection(long tag, String sectionName, String[] args, int argsLength) {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        Intrinsics.checkNotNullParameter(args, "args");
        Trace.beginSection(sectionName + "|" + INSTANCE.convertArgsToText(args, argsLength));
    }

    private final String convertArgsToText(String[] args, int argsLength) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < argsLength; i += 2) {
            String str = args[i - 1];
            String str2 = args[i];
            sb.append(str);
            sb.append('=');
            sb.append(str2);
            if (i < argsLength - 1) {
                sb.append(';');
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    @JvmStatic
    public static final void endSection(long tag) {
        Trace.endSection();
    }

    @JvmStatic
    public static final void beginAsyncSection(long tag, String sectionName, int cookie) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        Trace.beginAsyncSection(sectionName, cookie);
    }

    @JvmStatic
    public static final void beginAsyncSection(long tag, String sectionName, int cookie, long startNanos) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        beginAsyncSection(tag, sectionName, cookie);
    }

    @JvmStatic
    public static final void endAsyncSection(long tag, String sectionName, int cookie) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        Trace.endAsyncSection(sectionName, cookie);
    }

    @JvmStatic
    public static final void endAsyncSection(long tag, String sectionName, int cookie, long endNanos) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        endAsyncSection(tag, sectionName, cookie);
    }

    @JvmStatic
    public static final void traceCounter(long tag, String counterName, int counterValue) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(counterName, "counterName");
        Trace.setCounter(counterName, counterValue);
    }

    @JvmStatic
    public static final void startAsyncFlow(long tag, String sectionName, int cookie) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        beginAsyncSection(tag, sectionName, cookie);
    }

    @JvmStatic
    public static final void endAsyncFlow(long tag, String sectionName, int cookie) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Intrinsics.checkNotNullParameter(sectionName, "sectionName");
        endAsyncSection(tag, sectionName, cookie);
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: Systrace.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\f\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/facebook/systrace/Systrace$EventScope;", "", "code", "", "(Ljava/lang/String;IC)V", "getCode", "()C", "THREAD", "PROCESS", "GLOBAL", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class EventScope {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ EventScope[] $VALUES;
        private final char code;
        public static final EventScope THREAD = new EventScope("THREAD", 0, 't');
        public static final EventScope PROCESS = new EventScope("PROCESS", 1, 'p');
        public static final EventScope GLOBAL = new EventScope("GLOBAL", 2, 'g');

        private static final /* synthetic */ EventScope[] $values() {
            return new EventScope[]{THREAD, PROCESS, GLOBAL};
        }

        public static EnumEntries<EventScope> getEntries() {
            return $ENTRIES;
        }

        public static EventScope valueOf(String str) {
            return (EventScope) Enum.valueOf(EventScope.class, str);
        }

        public static EventScope[] values() {
            return (EventScope[]) $VALUES.clone();
        }

        private EventScope(String str, int i, char c) {
            this.code = c;
        }

        public final char getCode() {
            return this.code;
        }

        static {
            EventScope[] eventScopeArr$values = $values();
            $VALUES = eventScopeArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(eventScopeArr$values);
        }
    }
}
