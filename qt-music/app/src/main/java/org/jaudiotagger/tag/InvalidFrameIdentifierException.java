package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class InvalidFrameIdentifierException extends InvalidFrameException {
    private static final long serialVersionUID = 6459527941265009134L;

    public InvalidFrameIdentifierException() {
    }

    public InvalidFrameIdentifierException(Throwable th) {
        super(th);
    }

    public InvalidFrameIdentifierException(String str) {
        super(str);
    }

    public InvalidFrameIdentifierException(String str, Throwable th) {
        super(str, th);
    }
}
