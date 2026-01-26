package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class InvalidFrameException extends InvalidTagException {
    private static final long serialVersionUID = -757377045904536356L;

    public InvalidFrameException() {
    }

    public InvalidFrameException(Throwable th) {
        super(th);
    }

    public InvalidFrameException(String str) {
        super(str);
    }

    public InvalidFrameException(String str, Throwable th) {
        super(str, th);
    }
}
