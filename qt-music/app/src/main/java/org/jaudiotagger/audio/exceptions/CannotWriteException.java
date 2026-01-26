package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class CannotWriteException extends Exception {
    private static final long serialVersionUID = -4477951875399481164L;

    public CannotWriteException() {
    }

    public CannotWriteException(String str) {
        super(str);
    }

    public CannotWriteException(String str, Throwable th) {
        super(str, th);
    }

    public CannotWriteException(Throwable th) {
        super(th);
    }
}
