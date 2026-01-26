package org.jaudiotagger.tag.datatype;

import java.nio.charset.StandardCharsets;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class Lyrics3TimeStamp extends AbstractDataType {
    private long minute;
    private long second;

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return 7;
    }

    public void readString(String str) {
    }

    public Lyrics3TimeStamp(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.minute = 0L;
        this.second = 0L;
    }

    public Lyrics3TimeStamp(String str) {
        super(str, null);
        this.minute = 0L;
        this.second = 0L;
    }

    public Lyrics3TimeStamp(Lyrics3TimeStamp lyrics3TimeStamp) {
        super(lyrics3TimeStamp);
        this.minute = 0L;
        this.second = 0L;
        this.minute = lyrics3TimeStamp.minute;
        this.second = lyrics3TimeStamp.second;
    }

    public void setMinute(long j) {
        this.minute = j;
    }

    public long getMinute() {
        return this.minute;
    }

    public void setSecond(long j) {
        this.second = j;
    }

    public long getSecond() {
        return this.second;
    }

    public void setTimeStamp(long j, byte b) {
        long j2 = j / 1000;
        this.minute = j2 / 60;
        this.second = j2 % 60;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (!(obj instanceof Lyrics3TimeStamp)) {
            return false;
        }
        Lyrics3TimeStamp lyrics3TimeStamp = (Lyrics3TimeStamp) obj;
        return this.minute == lyrics3TimeStamp.minute && this.second == lyrics3TimeStamp.second && super.equals(obj);
    }

    public void readString(String str, int i) {
        if (str == null) {
            throw new NullPointerException("Image is null");
        }
        if (i < 0 || i >= str.length()) {
            throw new IndexOutOfBoundsException("Offset to timeStamp is out of bounds: offset = " + i + ", timeStamp.length()" + str.length());
        }
        if (str.substring(i).length() == 7) {
            this.minute = Integer.parseInt(r4.substring(1, 3));
            this.second = Integer.parseInt(r4.substring(4, 6));
        } else {
            this.minute = 0L;
            this.second = 0L;
        }
    }

    public String toString() {
        return writeString();
    }

    public String writeString() {
        String str;
        String str2;
        String str3;
        long j = this.minute;
        if (j < 0) {
            str2 = "[00";
        } else {
            if (j >= 10) {
                str = "[";
            } else {
                str = "[0";
            }
            str2 = str + Long.toString(this.minute);
        }
        String str4 = str2 + ':';
        long j2 = this.second;
        if (j2 < 0) {
            str3 = str4 + "00";
        } else {
            if (j2 < 10) {
                str4 = str4 + '0';
            }
            str3 = str4 + Long.toString(this.second);
        }
        return str3 + ']';
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        readString(bArr.toString(), i);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        return writeString().getBytes(StandardCharsets.ISO_8859_1);
    }
}
