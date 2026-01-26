package org.jaudiotagger.tag.datatype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.datatype.MultipleTextEncodedStringNullTerminated;
import org.jaudiotagger.tag.datatype.PairedTextEncodedStringNullTerminated;
import org.jaudiotagger.tag.datatype.PartOfSet;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public abstract class AbstractDataType {
    protected static final String TYPE_ELEMENT = "element";
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.datatype");
    protected AbstractTagFrameBody frameBody;
    protected String identifier;
    protected int size;
    protected Object value;

    public abstract int getSize();

    public abstract void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException;

    public abstract byte[] writeByteArray();

    protected AbstractDataType(String str, AbstractTagFrameBody abstractTagFrameBody) {
        this.value = null;
        this.identifier = str;
        this.frameBody = abstractTagFrameBody;
    }

    protected AbstractDataType(String str, AbstractTagFrameBody abstractTagFrameBody, Object obj) {
        this.value = null;
        this.identifier = str;
        this.frameBody = abstractTagFrameBody;
        setValue(obj);
    }

    public AbstractDataType(AbstractDataType abstractDataType) {
        this.value = null;
        this.identifier = "";
        this.frameBody = null;
        this.identifier = abstractDataType.identifier;
        Object obj = abstractDataType.value;
        if (obj == null) {
            this.value = null;
            return;
        }
        if (obj instanceof String) {
            this.value = obj;
            return;
        }
        if (obj instanceof Boolean) {
            this.value = obj;
            return;
        }
        if (obj instanceof Byte) {
            this.value = obj;
            return;
        }
        if (obj instanceof Character) {
            this.value = obj;
            return;
        }
        if (obj instanceof Double) {
            this.value = obj;
            return;
        }
        if (obj instanceof Float) {
            this.value = obj;
            return;
        }
        if (obj instanceof Integer) {
            this.value = obj;
            return;
        }
        if (obj instanceof Long) {
            this.value = obj;
            return;
        }
        if (obj instanceof Short) {
            this.value = obj;
            return;
        }
        if (obj instanceof MultipleTextEncodedStringNullTerminated.Values) {
            this.value = obj;
            return;
        }
        if (obj instanceof PairedTextEncodedStringNullTerminated.ValuePairs) {
            this.value = obj;
            return;
        }
        if (obj instanceof PartOfSet.PartOfSetValue) {
            this.value = obj;
            return;
        }
        if (obj instanceof boolean[]) {
            this.value = ((boolean[]) obj).clone();
            return;
        }
        if (obj instanceof byte[]) {
            this.value = ((byte[]) obj).clone();
            return;
        }
        if (obj instanceof char[]) {
            this.value = ((char[]) obj).clone();
            return;
        }
        if (obj instanceof double[]) {
            this.value = ((double[]) obj).clone();
            return;
        }
        if (obj instanceof float[]) {
            this.value = ((float[]) obj).clone();
            return;
        }
        if (obj instanceof int[]) {
            this.value = ((int[]) obj).clone();
            return;
        }
        if (obj instanceof long[]) {
            this.value = ((long[]) obj).clone();
            return;
        }
        if (obj instanceof short[]) {
            this.value = ((short[]) obj).clone();
            return;
        }
        if (obj instanceof Object[]) {
            this.value = ((Object[]) obj).clone();
        } else if (obj instanceof ArrayList) {
            this.value = ((ArrayList) obj).clone();
        } else {
            if (obj instanceof LinkedList) {
                this.value = ((LinkedList) obj).clone();
                return;
            }
            throw new UnsupportedOperationException("Unable to create copy of class " + abstractDataType.getClass());
        }
    }

    public void setBody(AbstractTagFrameBody abstractTagFrameBody) {
        this.frameBody = abstractTagFrameBody;
    }

    public AbstractTagFrameBody getBody() {
        return this.frameBody;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    public Object getValue() {
        return this.value;
    }

    public final void readByteArray(byte[] bArr) throws InvalidDataTypeException {
        readByteArray(bArr, 0);
    }

    public boolean equals(Object obj) {
        Object obj2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractDataType)) {
            return false;
        }
        AbstractDataType abstractDataType = (AbstractDataType) obj;
        if (!this.identifier.equals(abstractDataType.identifier)) {
            return false;
        }
        Object obj3 = this.value;
        if (obj3 == null && abstractDataType.value == null) {
            return true;
        }
        if (obj3 == null || (obj2 = abstractDataType.value) == null) {
            return false;
        }
        if ((obj3 instanceof boolean[]) && (obj2 instanceof boolean[])) {
            if (!Arrays.equals((boolean[]) obj3, (boolean[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof byte[]) && (obj2 instanceof byte[])) {
            if (!Arrays.equals((byte[]) obj3, (byte[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof char[]) && (obj2 instanceof char[])) {
            if (!Arrays.equals((char[]) obj3, (char[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof double[]) && (obj2 instanceof double[])) {
            if (!Arrays.equals((double[]) obj3, (double[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof float[]) && (obj2 instanceof float[])) {
            if (!Arrays.equals((float[]) obj3, (float[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof int[]) && (obj2 instanceof int[])) {
            if (!Arrays.equals((int[]) obj3, (int[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof long[]) && (obj2 instanceof long[])) {
            if (!Arrays.equals((long[]) obj3, (long[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof Object[]) && (obj2 instanceof Object[])) {
            if (!Arrays.equals((Object[]) obj3, (Object[]) obj2)) {
                return false;
            }
        } else if ((obj3 instanceof short[]) && (obj2 instanceof short[])) {
            if (!Arrays.equals((short[]) obj3, (short[]) obj2)) {
                return false;
            }
        } else if (!obj3.equals(obj2)) {
            return false;
        }
        return true;
    }

    public void createStructure() {
        MP3File.getStructureFormatter().addElement(this.identifier, getValue().toString());
    }
}
