package org.jaudiotagger.tag.flac;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.tag.reference.PictureTypes;
import org.jaudiotagger.tag.vorbiscomment.VorbisAlbumArtistSaveOptions;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentFieldKey;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;

/* loaded from: classes3.dex */
public class FlacTag implements Tag {
    private List<MetadataBlockDataPicture> images;
    private VorbisCommentTag tag;

    public FlacTag() {
        this(VorbisCommentTag.createNewTag(), new ArrayList());
    }

    public FlacTag(VorbisCommentTag vorbisCommentTag, List<MetadataBlockDataPicture> list) {
        this.tag = null;
        new ArrayList();
        this.tag = vorbisCommentTag;
        this.images = list;
    }

    public List<MetadataBlockDataPicture> getImages() {
        return this.images;
    }

    public VorbisCommentTag getVorbisCommentTag() {
        return this.tag;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) throws FieldDataInvalidException {
        if (tagField instanceof MetadataBlockDataPicture) {
            this.images.add((MetadataBlockDataPicture) tagField);
        } else {
            this.tag.addField(tagField);
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(String str) {
        if (str.equals(FieldKey.COVER_ART.name())) {
            ArrayList arrayList = new ArrayList();
            Iterator<MetadataBlockDataPicture> it = this.images.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList;
        }
        return this.tag.getFields(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == FieldKey.COVER_ART) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
        }
        return this.tag.getAll(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasCommonFields() {
        return this.tag.hasCommonFields();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        VorbisCommentTag vorbisCommentTag = this.tag;
        return (vorbisCommentTag == null || vorbisCommentTag.isEmpty()) && this.images.size() == 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        String str;
        if (strArr == null || (str = strArr[0]) == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (fieldKey == FieldKey.ALBUM_ARTIST) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[TagOptionSingleton.getInstance().getVorbisAlbumArtistSaveOptions().ordinal()];
            if (i == 1) {
                setField(createField(fieldKey, str));
                return;
            }
            if (i == 2) {
                setField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
            if (i == 3) {
                setField(createField(fieldKey, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName());
                return;
            } else if (i == 4) {
                setField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST.getFieldName());
                return;
            } else {
                if (i != 5) {
                    return;
                }
                setField(createField(fieldKey, str));
                setField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
        }
        setField(createField(fieldKey, str));
    }

    /* renamed from: org.jaudiotagger.tag.flac.FlacTag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions;

        static {
            int[] iArr = new int[VorbisAlbumArtistSaveOptions.values().length];
            $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions = iArr;
            try {
                iArr[VorbisAlbumArtistSaveOptions.WRITE_ALBUMARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_JRIVER_ALBUMARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_ALBUMARTIST_AND_DELETE_JRIVER_ALBUMARTIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_JRIVER_ALBUMARTIST_AND_DELETE_ALBUMARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_BOTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        String str;
        if (strArr == null || (str = strArr[0]) == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (fieldKey == FieldKey.ALBUM_ARTIST) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[TagOptionSingleton.getInstance().getVorbisAlbumArtistSaveOptions().ordinal()];
            if (i == 1) {
                addField(createField(fieldKey, str));
                return;
            }
            if (i == 2) {
                addField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
            if (i == 3) {
                addField(createField(fieldKey, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName());
                return;
            } else if (i == 4) {
                addField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST.getFieldName());
                return;
            } else {
                if (i != 5) {
                    return;
                }
                addField(createField(fieldKey, str));
                addField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
        }
        addField(createField(fieldKey, str));
    }

    public void setField(String str, String str2) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(str, str2));
    }

    public void addField(String str, String str2) throws KeyNotFoundException, FieldDataInvalidException {
        addField(createField(str, str2));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) throws FieldDataInvalidException {
        if (tagField instanceof MetadataBlockDataPicture) {
            if (this.images.size() == 0) {
                this.images.add(0, (MetadataBlockDataPicture) tagField);
                return;
            } else {
                this.images.set(0, (MetadataBlockDataPicture) tagField);
                return;
            }
        }
        this.tag.setField(tagField);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        if (fieldKey.equals(FieldKey.COVER_ART)) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
        }
        return this.tag.createField(fieldKey, strArr);
    }

    public TagField createField(VorbisCommentFieldKey vorbisCommentFieldKey, String str) throws KeyNotFoundException, FieldDataInvalidException {
        if (vorbisCommentFieldKey.equals(VorbisCommentFieldKey.COVERART)) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
        }
        return this.tag.createField(vorbisCommentFieldKey, str);
    }

    public TagField createField(String str, String str2) {
        if (str.equals(VorbisCommentFieldKey.COVERART.getFieldName())) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
        }
        return this.tag.createField(str, str2);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(String str) {
        if (str.equals(FieldKey.COVER_ART.name())) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
        }
        return this.tag.getFirst(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        if (fieldKey.equals(FieldKey.COVER_ART)) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_RETRIEVED_WITH_THIS_METHOD.getMsg());
        }
        return this.tag.getValue(fieldKey, i);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(String str) {
        if (str.equals(FieldKey.COVER_ART.name())) {
            if (this.images.size() > 0) {
                return this.images.get(0);
            }
            return null;
        }
        return this.tag.getFirstField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (fieldKey == FieldKey.COVER_ART) {
            return getFirstField(FieldKey.COVER_ART.name());
        }
        return this.tag.getFirstField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey.equals(FieldKey.COVER_ART)) {
            this.images.clear();
        } else {
            this.tag.deleteField(fieldKey);
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(String str) throws KeyNotFoundException {
        if (str.equals(FieldKey.COVER_ART.name())) {
            this.images.clear();
        } else {
            this.tag.deleteField(str);
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public Iterator<TagField> getFields() {
        return this.tag.getFields();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCount() {
        return this.tag.getFieldCount() + this.images.size();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCountIncludingSubValues() {
        return getFieldCount();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean setEncoding(Charset charset) throws FieldDataInvalidException {
        return this.tag.setEncoding(charset);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey.equals(FieldKey.COVER_ART)) {
            ArrayList arrayList = new ArrayList();
            Iterator<MetadataBlockDataPicture> it = this.images.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            return arrayList;
        }
        return this.tag.getFields(fieldKey);
    }

    public TagField createArtworkField(byte[] bArr, int i, String str, String str2, int i2, int i3, int i4, int i5) throws FieldDataInvalidException {
        if (bArr == null) {
            throw new FieldDataInvalidException("ImageData cannot be null");
        }
        return new MetadataBlockDataPicture(bArr, i, str, str2, i2, i3, i4, i5);
    }

    public TagField createLinkedArtworkField(String str) {
        return new MetadataBlockDataPicture(str.getBytes(StandardCharsets.ISO_8859_1), PictureTypes.DEFAULT_ID.intValue(), "-->", "", 0, 0, 0, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        if (artwork.isLinked()) {
            return new MetadataBlockDataPicture(artwork.getImageUrl().getBytes(StandardCharsets.ISO_8859_1), artwork.getPictureType(), "-->", "", 0, 0, 0, 0);
        }
        if (!artwork.setImageFromData()) {
            throw new FieldDataInvalidException("Unable to createField buffered image from the image");
        }
        return new MetadataBlockDataPicture(artwork.getBinaryData(), artwork.getPictureType(), artwork.getMimeType(), artwork.getDescription(), artwork.getWidth(), artwork.getHeight(), 0, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws FieldDataInvalidException {
        setField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        addField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        ArrayList arrayList = new ArrayList(this.images.size());
        Iterator<MetadataBlockDataPicture> it = this.images.iterator();
        while (it.hasNext()) {
            arrayList.add(ArtworkFactory.createArtworkFromMetadataBlockDataPicture(it.next()));
        }
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.Tag
    public Artwork getFirstArtwork() {
        List<Artwork> artworkList = getArtworkList();
        if (artworkList.size() > 0) {
            return artworkList.get(0);
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        deleteField(FieldKey.COVER_ART);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        if (fieldKey == FieldKey.COVER_ART) {
            return this.images.size() > 0;
        }
        return this.tag.hasField(fieldKey);
    }

    public boolean hasField(VorbisCommentFieldKey vorbisCommentFieldKey) {
        return this.tag.hasField(vorbisCommentFieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(String str) {
        if (str.equals(FieldKey.COVER_ART.name())) {
            return this.images.size() > 0;
        }
        return this.tag.hasField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return this.tag.createCompilationField(z);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String toString() {
        StringBuilder sb = new StringBuilder("FLAC " + getVorbisCommentTag());
        if (this.images.size() > 0) {
            sb.append("\n\tImages\n");
            Iterator<MetadataBlockDataPicture> it = this.images.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
            }
        }
        return sb.toString();
    }
}
