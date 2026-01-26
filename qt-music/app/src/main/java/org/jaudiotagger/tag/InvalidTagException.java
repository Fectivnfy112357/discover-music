package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class InvalidTagException extends TagException {
    private static final long serialVersionUID = -8871114835151336156L;

    public InvalidTagException() {
    }

    public InvalidTagException(Throwable th) {
        super(th);
    }

    public InvalidTagException(String str) {
        super(str);
    }

    public InvalidTagException(String str, Throwable th) {
        super(str, th);
    }
}
