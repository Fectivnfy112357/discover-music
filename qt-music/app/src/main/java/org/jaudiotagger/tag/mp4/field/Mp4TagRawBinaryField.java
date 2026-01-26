package org.jaudiotagger.tag.mp4.field;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.Mp4TagField;

/* loaded from: classes3.dex */
public class Mp4TagRawBinaryField extends Mp4TagField {
    protected byte[] dataBytes;
    protected int dataSize;

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return true;
    }

    public Mp4TagRawBinaryField(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(mp4BoxHeader.getId());
        this.dataSize = mp4BoxHeader.getDataLength();
        build(byteBuffer);
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
        this.dataBytes = new byte[this.dataSize];
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
        throw new UnsupportedOperationException("not done");
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField, org.jaudiotagger.tag.TagField
    public byte[] getRawContent() throws UnsupportedEncodingException {
        logger.fine("Getting Raw data for:" + getId());
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.dataSize + 8));
            byteArrayOutputStream.write(getId().getBytes(StandardCharsets.ISO_8859_1));
            byteArrayOutputStream.write(this.dataBytes);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
