package com.facebook.react.util;

import android.util.JsonWriter;
import com.facebook.react.bridge.JsonWriterHelper;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.io.IOException;
import java.io.StringWriter;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* compiled from: ExceptionDataHelper.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/facebook/react/util/ExceptionDataHelper;", "", "()V", "EXTRA_DATA_FIELD", "", "getExtraDataAsJson", "metadata", "Lcom/facebook/react/bridge/ReadableMap;", "ReactAndroid_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ExceptionDataHelper {
    public static final String EXTRA_DATA_FIELD = "extraData";
    public static final ExceptionDataHelper INSTANCE = new ExceptionDataHelper();

    private ExceptionDataHelper() {
    }

    @JvmStatic
    public static final String getExtraDataAsJson(ReadableMap metadata) throws IOException {
        if (metadata != null && metadata.getType(EXTRA_DATA_FIELD) != ReadableType.Null) {
            try {
                StringWriter stringWriter = new StringWriter();
                JsonWriter jsonWriter = new JsonWriter(stringWriter);
                JsonWriterHelper.value(jsonWriter, metadata.getDynamic(EXTRA_DATA_FIELD));
                jsonWriter.close();
                stringWriter.close();
                return stringWriter.toString();
            } catch (IOException unused) {
            }
        }
        return null;
    }
}
