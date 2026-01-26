package org.jaudiotagger.tag.mp4.field;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.atom.Mp4DataBox;

/* loaded from: classes3.dex */
public class Mp4TagByteField extends Mp4TagTextField {
    public static String FALSE_VALUE = "0";
    public static String TRUE_VALUE = "1";
    private byte[] bytedata;
    private int realDataLength;

    public Mp4TagByteField(Mp4FieldKey mp4FieldKey, String str) throws FieldDataInvalidException {
        this(mp4FieldKey, str, 1);
    }

    public Mp4TagByteField(Mp4FieldKey mp4FieldKey, String str, int i) throws FieldDataInvalidException, NumberFormatException {
        super(mp4FieldKey.getFieldName(), str);
        this.realDataLength = i;
        try {
            Long.parseLong(str);
        } catch (NumberFormatException unused) {
            throw new FieldDataInvalidException("Value of:" + str + " is invalid for field:" + mp4FieldKey);
        }
    }

    public Mp4TagByteField(String str, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(str, byteBuffer);
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    public Mp4FieldType getFieldType() {
        return Mp4FieldType.INTEGER;
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    protected byte[] getDataBytes() throws UnsupportedEncodingException {
        byte[] bArr = this.bytedata;
        if (bArr != null) {
            return bArr;
        }
        int i = this.realDataLength;
        if (i == 1) {
            return new byte[]{new Short(this.content).byteValue()};
        }
        if (i == 2) {
            return Utils.getSizeBEInt16(new Short(this.content).shortValue());
        }
        if (i == 4) {
            return Utils.getSizeBEInt32(new Integer(this.content).intValue());
        }
        throw new RuntimeException(this.id + ":" + this.realDataLength + ":Dont know how to write byte fields of this length");
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    protected void build(ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(byteBuffer);
        Mp4DataBox mp4DataBox = new Mp4DataBox(mp4BoxHeader, byteBuffer);
        this.dataSize = mp4BoxHeader.getDataLength();
        this.realDataLength = this.dataSize - 8;
        this.bytedata = mp4DataBox.getByteData();
        this.content = mp4DataBox.getContent();
    }
}
