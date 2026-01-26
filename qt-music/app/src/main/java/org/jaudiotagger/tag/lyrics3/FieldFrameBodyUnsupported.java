package org.jaudiotagger.tag.lyrics3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.jaudiotagger.tag.InvalidTagException;

/* loaded from: classes3.dex */
public class FieldFrameBodyUnsupported extends AbstractLyrics3v2FieldFrameBody {
    private byte[] value;

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
    }

    public FieldFrameBodyUnsupported() {
        this.value = null;
    }

    public FieldFrameBodyUnsupported(FieldFrameBodyUnsupported fieldFrameBodyUnsupported) {
        super(fieldFrameBodyUnsupported);
        this.value = null;
        this.value = (byte[]) fieldFrameBodyUnsupported.value.clone();
    }

    public FieldFrameBodyUnsupported(byte[] bArr) {
        this.value = bArr;
    }

    public FieldFrameBodyUnsupported(ByteBuffer byteBuffer) throws InvalidTagException {
        this.value = null;
        read(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "ZZZ";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean isSubsetOf(Object obj) {
        if (obj instanceof FieldFrameBodyUnsupported) {
            return new String(((FieldFrameBodyUnsupported) obj).value).contains(new String(this.value)) && super.isSubsetOf(obj);
        }
        return false;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof FieldFrameBodyUnsupported) && Arrays.equals(this.value, ((FieldFrameBodyUnsupported) obj).value) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.lyrics3.AbstractLyrics3v2FieldFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidTagException {
        byte[] bArr = new byte[5];
        byteBuffer.get(bArr, 0, 5);
        byte[] bArr2 = new byte[Integer.parseInt(new String(bArr, 0, 5))];
        this.value = bArr2;
        byteBuffer.get(bArr2);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String toString() {
        return getIdentifier() + " : " + new String(this.value);
    }

    @Override // org.jaudiotagger.tag.lyrics3.AbstractLyrics3v2FieldFrameBody
    public void write(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = new byte[5];
        String string = Integer.toString(this.value.length);
        for (int i = 0; i < 5 - string.length(); i++) {
            bArr[i] = 48;
        }
        int length = 5 - string.length();
        for (int i2 = 0; i2 < string.length(); i2++) {
            bArr[i2 + length] = (byte) string.charAt(i2);
        }
        randomAccessFile.write(bArr);
        randomAccessFile.write(this.value);
    }
}
