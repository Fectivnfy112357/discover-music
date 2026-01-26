package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class KeyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -4532369719091873024L;

    public KeyNotFoundException() {
    }

    public KeyNotFoundException(Throwable th) {
        super(th);
    }

    public KeyNotFoundException(String str) {
        super(str);
    }

    public KeyNotFoundException(String str, Throwable th) {
        super(str, th);
    }
}
