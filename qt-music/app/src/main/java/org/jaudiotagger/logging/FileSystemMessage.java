package org.jaudiotagger.logging;

/* loaded from: classes3.dex */
public enum FileSystemMessage {
    ACCESS_IS_DENIED("Access is denied"),
    PERMISSION_DENIED("Permission denied");

    String msg;

    FileSystemMessage(String str) {
        this.msg = str;
    }

    public String getMsg() {
        return this.msg;
    }
}
