package org.jaudiotagger.tag.datatype;

import java.nio.charset.StandardCharsets;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class ID3v2LyricLine extends AbstractDataType {
    String text;
    long timeStamp;

    public ID3v2LyricLine(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.text = "";
        this.timeStamp = 0L;
    }

    public ID3v2LyricLine(ID3v2LyricLine iD3v2LyricLine) {
        super(iD3v2LyricLine);
        this.text = "";
        this.timeStamp = 0L;
        this.text = iD3v2LyricLine.text;
        this.timeStamp = iD3v2LyricLine.timeStamp;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        return this.text.length() + 5;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getText() {
        return this.text;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (!(obj instanceof ID3v2LyricLine)) {
            return false;
        }
        ID3v2LyricLine iD3v2LyricLine = (ID3v2LyricLine) obj;
        return this.text.equals(iD3v2LyricLine.text) && this.timeStamp == iD3v2LyricLine.timeStamp && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        if (bArr == null) {
            throw new NullPointerException("Byte array is null");
        }
        if (i < 0 || i >= bArr.length) {
            throw new IndexOutOfBoundsException("Offset to byte array is out of bounds: offset = " + i + ", array.length = " + bArr.length);
        }
        this.text = new String(bArr, i, (bArr.length - i) - 4, StandardCharsets.ISO_8859_1);
        this.timeStamp = 0L;
        for (int length = bArr.length - 4; length < bArr.length; length++) {
            long j = this.timeStamp << 8;
            this.timeStamp = j;
            this.timeStamp = j + bArr[length];
        }
    }

    public String toString() {
        return this.timeStamp + " " + this.text;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        byte[] bArr = new byte[getSize()];
        int i = 0;
        while (i < this.text.length()) {
            bArr[i] = (byte) this.text.charAt(i);
            i++;
        }
        bArr[i] = 0;
        long j = this.timeStamp;
        bArr[i + 1] = (byte) (((-16777216) & j) >> 24);
        bArr[i + 2] = (byte) ((16711680 & j) >> 16);
        bArr[i + 3] = (byte) ((65280 & j) >> 8);
        bArr[i + 4] = (byte) (j & 255);
        return bArr;
    }
}
