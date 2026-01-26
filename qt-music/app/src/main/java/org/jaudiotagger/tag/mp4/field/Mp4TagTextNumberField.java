package org.jaudiotagger.tag.mp4.field;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.atom.Mp4DataBox;

/* loaded from: classes3.dex */
public class Mp4TagTextNumberField extends Mp4TagTextField {
    public static final int NUMBER_LENGTH = 2;
    protected List<Short> numbers;

    public Mp4TagTextNumberField(String str, String str2) {
        super(str, str2);
    }

    public Mp4TagTextNumberField(String str, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(str, byteBuffer);
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    protected byte[] getDataBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Iterator<Short> it = this.numbers.iterator();
        while (it.hasNext()) {
            try {
                byteArrayOutputStream.write(Utils.getSizeBEInt16(it.next().shortValue()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
        if (tagField instanceof Mp4TagTextNumberField) {
            Mp4TagTextNumberField mp4TagTextNumberField = (Mp4TagTextNumberField) tagField;
            this.content = mp4TagTextNumberField.getContent();
            this.numbers = mp4TagTextNumberField.getNumbers();
        }
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    public Mp4FieldType getFieldType() {
        return Mp4FieldType.IMPLICIT;
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    protected void build(ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(byteBuffer);
        Mp4DataBox mp4DataBox = new Mp4DataBox(mp4BoxHeader, byteBuffer);
        this.dataSize = mp4BoxHeader.getDataLength();
        this.content = mp4DataBox.getContent();
        this.numbers = mp4DataBox.getNumbers();
    }

    public List<Short> getNumbers() {
        return this.numbers;
    }
}
