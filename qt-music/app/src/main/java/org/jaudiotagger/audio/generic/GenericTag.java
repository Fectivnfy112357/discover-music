package org.jaudiotagger.audio.generic;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.images.Artwork;

/* loaded from: classes3.dex */
public abstract class GenericTag extends AbstractTag {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    protected static EnumSet<FieldKey> supportedKeys = EnumSet.of(FieldKey.ALBUM, FieldKey.ARTIST, FieldKey.TITLE, FieldKey.TRACK, FieldKey.GENRE, FieldKey.COMMENT, FieldKey.YEAR);

    @Override // org.jaudiotagger.audio.generic.AbstractTag
    protected boolean isAllowedEncoding(Charset charset) {
        return true;
    }

    public static EnumSet<FieldKey> getSupportedKeys() {
        return supportedKeys;
    }

    protected class GenericTagTextField implements TagTextField {
        private String content;
        private final String id;

        @Override // org.jaudiotagger.tag.TagField
        public void isBinary(boolean z) {
        }

        @Override // org.jaudiotagger.tag.TagField
        public boolean isBinary() {
            return false;
        }

        @Override // org.jaudiotagger.tag.TagField
        public boolean isCommon() {
            return true;
        }

        @Override // org.jaudiotagger.tag.TagTextField
        public void setEncoding(Charset charset) {
        }

        public GenericTagTextField(String str, String str2) {
            this.id = str;
            this.content = str2;
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
        public byte[] getRawContent() {
            String str = this.content;
            return str == null ? GenericTag.EMPTY_BYTE_ARRAY : str.getBytes(getEncoding());
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

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        if (supportedKeys.contains(fieldKey)) {
            if (strArr == null || strArr[0] == null) {
                throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
            }
            return new GenericTagTextField(fieldKey.name(), strArr[0]);
        }
        throw new UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(fieldKey));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        if (supportedKeys.contains(fieldKey)) {
            return getItem(fieldKey.name(), i);
        }
        throw new UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(fieldKey));
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        List<TagField> list = this.fields.get(fieldKey.name());
        return list == null ? new ArrayList() : list;
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        return super.getAll(fieldKey.name());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        if (supportedKeys.contains(fieldKey)) {
            deleteField(fieldKey.name());
            return;
        }
        throw new UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(fieldKey));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        if (supportedKeys.contains(fieldKey)) {
            return getFirstField(fieldKey.name());
        }
        throw new UnsupportedOperationException(ErrorMessage.OPERATION_NOT_SUPPORTED_FOR_FIELD.getMsg(fieldKey));
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        return Collections.emptyList();
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        throw new UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg());
    }
}
