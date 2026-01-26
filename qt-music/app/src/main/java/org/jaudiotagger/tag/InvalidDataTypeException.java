package org.jaudiotagger.tag;

/* loaded from: classes3.dex */
public class InvalidDataTypeException extends InvalidTagException {
    private static final long serialVersionUID = -57193274023749388L;

    public InvalidDataTypeException() {
    }

    public InvalidDataTypeException(Throwable th) {
        super(th);
    }

    public InvalidDataTypeException(String str) {
        super(str);
    }

    public InvalidDataTypeException(String str, Throwable th) {
        super(str, th);
    }
}
