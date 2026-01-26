package com.facebook.react.common;

import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.devsupport.StackTraceHelper;
import com.umeng.analytics.pro.bm;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DebugServerException.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0006\u0018\u0000 \u00122\u00060\u0001j\u0002`\u0002:\u0001\u0012B'\b\u0012\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\nB\u0019\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u0004\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/facebook/react/common/DebugServerException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "description", "", "fileName", "lineNumber", "", "column", "(Ljava/lang/String;Ljava/lang/String;II)V", "(Ljava/lang/String;)V", "detailMessage", "throwable", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "originalMessage", "getOriginalMessage", "()Ljava/lang/String;", "Companion", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DebugServerException extends RuntimeException {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String GENERIC_ERROR_MESSAGE = "\n\nTry the following to fix the issue:\n\\u2022 Ensure that Metro is running\n\\u2022 Ensure that your device/emulator is connected to your machine and has USB debugging enabled - run 'adb devices' to see a list of connected devices\n\\u2022 Ensure Airplane Mode is disabled\n\\u2022 If you're on a physical device connected to the same machine, run 'adb reverse tcp:<PORT> tcp:<PORT> to forward requests from your device\n\\u2022 If your device is on the same Wi-Fi network, set 'Debug server host & port for device' in 'Dev settings' to your machine's IP address and the port of the local dev server - e.g. 10.0.1.1:<PORT>\n\n";
    private final String originalMessage;

    public /* synthetic */ DebugServerException(String str, String str2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, i, i2);
    }

    @JvmStatic
    public static final DebugServerException makeGeneric(String str, String str2, String str3, Throwable th) {
        return INSTANCE.makeGeneric(str, str2, str3, th);
    }

    @JvmStatic
    public static final DebugServerException makeGeneric(String str, String str2, Throwable th) {
        return INSTANCE.makeGeneric(str, str2, th);
    }

    @JvmStatic
    public static final DebugServerException parse(String str, String str2) {
        return INSTANCE.parse(str, str2);
    }

    public final String getOriginalMessage() {
        return this.originalMessage;
    }

    private DebugServerException(String str, String str2, int i, int i2) {
        super(str + "\n  at " + str2 + ":" + i + ":" + i2);
        this.originalMessage = str;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugServerException(String description) {
        super(description);
        Intrinsics.checkNotNullParameter(description, "description");
        this.originalMessage = description;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DebugServerException(String detailMessage, Throwable th) {
        super(detailMessage, th);
        Intrinsics.checkNotNullParameter(detailMessage, "detailMessage");
        this.originalMessage = detailMessage;
    }

    /* compiled from: DebugServerException.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\"\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007J\u001e\u0010\f\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0007J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/facebook/react/common/DebugServerException$Companion;", "", "()V", "GENERIC_ERROR_MESSAGE", "", "makeGeneric", "Lcom/facebook/react/common/DebugServerException;", "url", "reason", "extra", bm.aM, "", "parse", "str", "shortenFileName", "fullFileName", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DebugServerException makeGeneric(String url, String reason, Throwable t) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(reason, "reason");
            return makeGeneric(url, reason, "", t);
        }

        @JvmStatic
        public final DebugServerException makeGeneric(String url, String reason, String extra, Throwable t) {
            Intrinsics.checkNotNullParameter(url, "url");
            Intrinsics.checkNotNullParameter(reason, "reason");
            Intrinsics.checkNotNullParameter(extra, "extra");
            return new DebugServerException(reason + StringsKt.replace$default(DebugServerException.GENERIC_ERROR_MESSAGE, "<PORT>", String.valueOf(Uri.parse(url).getPort()), false, 4, (Object) null) + extra, t);
        }

        @JvmStatic
        public final DebugServerException parse(String url, String str) throws JSONException {
            String str2 = str;
            if (str2 != null && str2.length() != 0) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    String string = jSONObject.getString("filename");
                    String string2 = jSONObject.getString(StackTraceHelper.MESSAGE_KEY);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    Intrinsics.checkNotNull(string);
                    return new DebugServerException(string2, shortenFileName(string), jSONObject.getInt("lineNumber"), jSONObject.getInt("column"), null);
                } catch (JSONException e) {
                    FLog.w(ReactConstants.TAG, "Could not parse DebugServerException from: " + str, e);
                }
            }
            return null;
        }

        private final String shortenFileName(String fullFileName) {
            List listEmptyList;
            List<String> listSplit = new Regex("/").split(fullFileName, 0);
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (listIterator.hasPrevious()) {
                    if (listIterator.previous().length() != 0) {
                        listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                        break;
                    }
                }
                listEmptyList = CollectionsKt.emptyList();
            } else {
                listEmptyList = CollectionsKt.emptyList();
            }
            return (String) ArraysKt.last((String[]) listEmptyList.toArray(new String[0]));
        }
    }
}
