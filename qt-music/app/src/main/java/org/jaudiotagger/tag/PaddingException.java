package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class PaddingException extends InvalidFrameIdentifierException {
    private static final long serialVersionUID = 1308046847105237663L;

    public PaddingException() {
    }

    public PaddingException(Throwable th) {
        super(th);
    }

    public PaddingException(String str) {
        super(str);
    }

    public PaddingException(String str, Throwable th) {
        super(str, th);
    }
}
