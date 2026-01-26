package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class InvalidChunkException extends Exception {
    private static final long serialVersionUID = -3020712878276167444L;

    public InvalidChunkException() {
    }

    public InvalidChunkException(Throwable th) {
        super(th);
    }

    public InvalidChunkException(String str) {
        super(str);
    }

    public InvalidChunkException(String str, Throwable th) {
        super(str, th);
    }
}
