package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class TagNotFoundException extends TagException {
    private static final long serialVersionUID = -7952067424639848036L;

    public TagNotFoundException() {
    }

    public TagNotFoundException(Throwable th) {
        super(th);
    }

    public TagNotFoundException(String str) {
        super(str);
    }

    public TagNotFoundException(String str, Throwable th) {
        super(str, th);
    }
}
