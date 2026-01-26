package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class ReadOnlyFileException extends Exception {
    private static final long serialVersionUID = 3390133566776688874L;

    public ReadOnlyFileException() {
    }

    public ReadOnlyFileException(Throwable th) {
        super(th);
    }

    public ReadOnlyFileException(String str) {
        super(str);
    }

    public ReadOnlyFileException(String str, Throwable th) {
        super(str, th);
    }
}
