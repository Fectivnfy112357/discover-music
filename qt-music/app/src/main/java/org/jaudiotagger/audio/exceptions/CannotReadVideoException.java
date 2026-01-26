package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class CannotReadVideoException extends CannotReadException {
    private static final long serialVersionUID = -7185020848474992115L;

    public CannotReadVideoException() {
    }

    public CannotReadVideoException(Throwable th) {
        super(th);
    }

    public CannotReadVideoException(String str) {
        super(str);
    }

    public CannotReadVideoException(String str, Throwable th) {
        super(str, th);
    }
}
