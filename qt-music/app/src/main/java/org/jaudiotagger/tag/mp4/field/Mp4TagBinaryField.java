package org.jaudiotagger.tag.mp4.field;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.Mp4TagField;

/* loaded from: classes3.dex */
public class Mp4TagBinaryField extends Mp4TagField {
    protected byte[] dataBytes;
    protected int dataSize;
    protected boolean isBinary;

    public Mp4TagBinaryField(String str) {
        super(str);
        this.isBinary = false;
    }

    public Mp4TagBinaryField(String str, byte[] bArr) {
        super(str);
        this.isBinary = false;
        this.dataBytes = bArr;
    }

    public Mp4TagBinaryField(String str, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(str, byteBuffer);
        this.isBinary = false;
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField
    public Mp4FieldType getFieldType() {
        return Mp4FieldType.IMPLICIT;
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField
    protected byte[] getDataBytes() throws UnsupportedEncodingException {
        return this.dataBytes;
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField
    protected void build(ByteBuffer byteBuffer) {
        this.dataSize = new Mp4BoxHeader(byteBuffer).getDataLength();
        byteBuffer.position(byteBuffer.position() + 8);
        this.dataBytes = new byte[this.dataSize - 8];
        int i = 0;
        while (true) {
            byte[] bArr = this.dataBytes;
            if (i >= bArr.length) {
                return;
            }
            bArr[i] = byteBuffer.get();
            i++;
        }
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return this.isBinary;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return this.dataBytes.length == 0;
    }

    public int getDataSize() {
        return this.dataSize;
    }

    public byte[] getData() {
        return this.dataBytes;
    }

    public void setData(byte[] bArr) {
        this.dataBytes = bArr;
    }

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
        if (tagField instanceof Mp4TagBinaryField) {
            this.dataBytes = ((Mp4TagBinaryField) tagField).getData();
            this.isBinary = tagField.isBinary();
        }
    }
}
