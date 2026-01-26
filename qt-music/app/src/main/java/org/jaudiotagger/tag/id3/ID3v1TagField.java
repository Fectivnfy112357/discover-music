package org.jaudiotagger.tag.id3;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.io.encoding.Base64;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;

/* loaded from: classes3.dex */
public class ID3v1TagField implements TagTextField {
    private boolean common;
    private String content;
    private String id;

    @Override // org.jaudiotagger.tag.TagField
    public void isBinary(boolean z) {
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return false;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setEncoding(Charset charset) {
    }

    public ID3v1TagField(byte[] bArr) throws UnsupportedEncodingException {
        String str = new String(bArr, StandardCharsets.ISO_8859_1);
        int iIndexOf = str.indexOf(61);
        if (iIndexOf == -1) {
            this.id = "ERRONEOUS";
            this.content = str;
        } else {
            this.id = str.substring(0, iIndexOf).toUpperCase();
            if (str.length() > iIndexOf) {
                this.content = str.substring(iIndexOf + 1);
            } else {
                this.content = "";
            }
        }
        checkCommon();
    }

    public ID3v1TagField(String str, String str2) {
        this.id = str.toUpperCase();
        this.content = str2;
        checkCommon();
    }

    private void checkCommon() {
        this.common = this.id.equals(ID3v1FieldKey.TITLE.name()) || this.id.equals(ID3v1FieldKey.ALBUM.name()) || this.id.equals(ID3v1FieldKey.ARTIST.name()) || this.id.equals(ID3v1FieldKey.GENRE.name()) || this.id.equals(ID3v1FieldKey.YEAR.name()) || this.id.equals(ID3v1FieldKey.COMMENT.name()) || this.id.equals(ID3v1FieldKey.TRACK.name());
    }

    protected void copy(byte[] bArr, byte[] bArr2, int i) {
        System.arraycopy(bArr, 0, bArr2, i, bArr.length);
    }

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
        if (tagField instanceof TagTextField) {
            this.content = ((TagTextField) tagField).getContent();
        }
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public String getContent() {
        return this.content;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public Charset getEncoding() {
        return StandardCharsets.ISO_8859_1;
    }

    @Override // org.jaudiotagger.tag.TagField
    public String getId() {
        return this.id;
    }

    @Override // org.jaudiotagger.tag.TagField
    public byte[] getRawContent() throws UnsupportedEncodingException {
        byte[] bytes = this.id.getBytes(StandardCharsets.ISO_8859_1);
        byte[] bytes2 = this.content.getBytes(StandardCharsets.ISO_8859_1);
        byte[] bArr = new byte[bytes.length + 5 + bytes2.length];
        int length = bytes.length + 1 + bytes2.length;
        copy(new byte[]{(byte) (length & 255), (byte) ((65280 & length) >> 8), (byte) ((16711680 & length) >> 16), (byte) (((-16777216) & length) >> 24)}, bArr, 0);
        copy(bytes, bArr, 4);
        int length2 = bytes.length;
        bArr[4 + length2] = Base64.padSymbol;
        copy(bytes2, bArr, length2 + 5);
        return bArr;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isCommon() {
        return this.common;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return "".equals(this.content);
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setContent(String str) {
        this.content = str;
    }

    @Override // org.jaudiotagger.tag.TagField
    public String toString() {
        return getContent();
    }
}
