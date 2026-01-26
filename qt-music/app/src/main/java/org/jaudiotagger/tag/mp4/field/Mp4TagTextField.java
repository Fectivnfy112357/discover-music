package org.jaudiotagger.tag.mp4.field;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.mp4.Mp4TagField;
import org.jaudiotagger.tag.mp4.atom.Mp4DataBox;

/* loaded from: classes3.dex */
public class Mp4TagTextField extends Mp4TagField implements TagTextField {
    protected String content;
    protected int dataSize;

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return false;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setEncoding(Charset charset) {
    }

    public Mp4TagTextField(String str, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(str, byteBuffer);
    }

    public Mp4TagTextField(String str, String str2) {
        super(str);
        this.content = str2;
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField
    protected void build(ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(byteBuffer);
        Mp4DataBox mp4DataBox = new Mp4DataBox(mp4BoxHeader, byteBuffer);
        this.dataSize = mp4BoxHeader.getDataLength();
        this.content = mp4DataBox.getContent();
    }

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
        if (tagField instanceof Mp4TagTextField) {
            this.content = ((Mp4TagTextField) tagField).getContent();
        }
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public String getContent() {
        return this.content;
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField
    protected byte[] getDataBytes() throws UnsupportedEncodingException {
        return this.content.getBytes(getEncoding());
    }

    @Override // org.jaudiotagger.tag.mp4.Mp4TagField
    public Mp4FieldType getFieldType() {
        return Mp4FieldType.TEXT;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public Charset getEncoding() {
        return StandardCharsets.UTF_8;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return this.content.trim().equals("");
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setContent(String str) {
        this.content = str;
    }

    @Override // org.jaudiotagger.tag.TagField
    public String toString() {
        return this.content;
    }
}
