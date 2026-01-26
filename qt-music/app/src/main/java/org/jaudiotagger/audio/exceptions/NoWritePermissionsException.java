package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class NoWritePermissionsException extends CannotWriteException {
    private static final long serialVersionUID = -156467854598317547L;

    public NoWritePermissionsException() {
    }

    public NoWritePermissionsException(Throwable th) {
        super(th);
    }

    public NoWritePermissionsException(String str) {
        super(str);
    }

    public NoWritePermissionsException(String str, Throwable th) {
        super(str, th);
    }
}
