package com.facebook.react.util;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JSStackTrace.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/facebook/react/util/JSStackTrace;", "", "()V", "COLUMN_KEY", "", "FILE_ID_PATTERN", "Ljava/util/regex/Pattern;", "kotlin.jvm.PlatformType", "FILE_KEY", "LINE_NUMBER_KEY", "METHOD_NAME_KEY", "format", StackTraceHelper.MESSAGE_KEY, "stack", "Lcom/facebook/react/bridge/ReadableArray;", "parseFileId", "frame", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class JSStackTrace {
    public static final String COLUMN_KEY = "column";
    public static final String FILE_KEY = "file";
    public static final String LINE_NUMBER_KEY = "lineNumber";
    public static final String METHOD_NAME_KEY = "methodName";
    public static final JSStackTrace INSTANCE = new JSStackTrace();
    private static final Pattern FILE_ID_PATTERN = Pattern.compile("\\b((?:seg-\\d+(?:_\\d+)?|\\d+)\\.js)");

    private JSStackTrace() {
    }

    @JvmStatic
    public static final String format(String message, ReadableArray stack) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(stack, "stack");
        StringBuilder sbAppend = new StringBuilder(message).append(", stack:\n");
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            ReadableMap map = stack.getMap(i);
            sbAppend.append(map.getString("methodName")).append("@").append(INSTANCE.parseFileId(map));
            if (map.hasKey("lineNumber") && !map.isNull("lineNumber") && map.getType("lineNumber") == ReadableType.Number) {
                sbAppend.append(map.getInt("lineNumber"));
            } else {
                sbAppend.append(-1);
            }
            if (map.hasKey("column") && !map.isNull("column") && map.getType("column") == ReadableType.Number) {
                sbAppend.append(":").append(map.getInt("column"));
            }
            sbAppend.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        String string = sbAppend.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        return string;
    }

    private final String parseFileId(ReadableMap frame) {
        String string;
        if (frame.hasKey("file") && !frame.isNull("file") && frame.getType("file") == ReadableType.String && (string = frame.getString("file")) != null) {
            Matcher matcher = FILE_ID_PATTERN.matcher(string);
            return matcher.find() ? matcher.group(1) + ":" : "";
        }
        return "";
    }
}
