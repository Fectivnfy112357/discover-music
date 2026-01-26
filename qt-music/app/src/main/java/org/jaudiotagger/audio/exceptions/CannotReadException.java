package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class CannotReadException extends Exception {
    private static final long serialVersionUID = 8012136673806032717L;

    public CannotReadException() {
    }

    public CannotReadException(Throwable th) {
        super(th);
    }

    public CannotReadException(String str) {
        super(str);
    }

    public CannotReadException(String str, Throwable th) {
        super(str, th);
    }
}
