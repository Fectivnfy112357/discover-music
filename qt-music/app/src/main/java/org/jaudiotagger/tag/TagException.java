package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class TagException extends Exception {
    private static final long serialVersionUID = -5226319371974235699L;

    public TagException() {
    }

    public TagException(Throwable th) {
        super(th);
    }

    public TagException(String str) {
        super(str);
    }

    public TagException(String str, Throwable th) {
        super(str, th);
    }
}
