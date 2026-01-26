package org.jaudiotagger.audio.exceptions;

/* loaded from: classes3.dex */
public class NoReadPermissionsException extends CannotReadException {
    private static final long serialVersionUID = 1917828252842714301L;

    public NoReadPermissionsException() {
    }

    public NoReadPermissionsException(Throwable th) {
        super(th);
    }

    public NoReadPermissionsException(String str) {
        super(str);
    }

    public NoReadPermissionsException(String str, Throwable th) {
        super(str, th);
    }
}
