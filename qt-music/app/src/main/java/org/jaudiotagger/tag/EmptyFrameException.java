package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class EmptyFrameException extends InvalidFrameException {
    private static final long serialVersionUID = 5761623018903709948L;

    public EmptyFrameException() {
    }

    public EmptyFrameException(Throwable th) {
        super(th);
    }

    public EmptyFrameException(String str) {
        super(str);
    }

    public EmptyFrameException(String str, Throwable th) {
        super(str, th);
    }
}
