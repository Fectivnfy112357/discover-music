package org.jaudiotagger.tag.id3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagNotFoundException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.reference.GenreTypes;

/* loaded from: classes3.dex */
public class ID3v1Tag extends AbstractID3v1Tag implements Tag {
    protected static final int BYTE_TO_UNSIGNED = 255;
    protected static final int FIELD_COMMENT_LENGTH = 30;
    protected static final int FIELD_COMMENT_POS = 97;
    protected static final int GENRE_UNDEFINED = 255;
    private static final byte MAJOR_VERSION = 0;
    private static final byte RELEASE = 1;
    private static final byte REVISION = 0;
    protected static final String TYPE_COMMENT = "comment";
    static EnumMap<FieldKey, ID3v1FieldKey> tagFieldToID3v1Field;
    protected String album;
    protected String artist;
    protected String comment;
    protected byte genre;
    protected String title;
    protected String year;

    @Override // org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) {
    }

    public int getFieldCount() {
        return 6;
    }

    @Override // org.jaudiotagger.tag.Tag
    public Artwork getFirstArtwork() {
        return null;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getMajorVersion() {
        return (byte) 0;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getRelease() {
        return (byte) 1;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getRevision() {
        return (byte) 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasCommonFields() {
        return true;
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean setEncoding(Charset charset) {
        return true;
    }

    static {
        EnumMap<FieldKey, ID3v1FieldKey> enumMap = new EnumMap<>(FieldKey.class);
        tagFieldToID3v1Field = enumMap;
        enumMap.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.ARTIST, (FieldKey) ID3v1FieldKey.ARTIST);
        tagFieldToID3v1Field.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.ALBUM, (FieldKey) ID3v1FieldKey.ALBUM);
        tagFieldToID3v1Field.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.TITLE, (FieldKey) ID3v1FieldKey.TITLE);
        tagFieldToID3v1Field.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.TRACK, (FieldKey) ID3v1FieldKey.TRACK);
        tagFieldToID3v1Field.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.YEAR, (FieldKey) ID3v1FieldKey.YEAR);
        tagFieldToID3v1Field.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.GENRE, (FieldKey) ID3v1FieldKey.GENRE);
        tagFieldToID3v1Field.put((EnumMap<FieldKey, ID3v1FieldKey>) FieldKey.COMMENT, (FieldKey) ID3v1FieldKey.COMMENT);
    }

    public ID3v1Tag() {
        this.album = "";
        this.artist = "";
        this.comment = "";
        this.title = "";
        this.year = "";
        this.genre = (byte) -1;
    }

    public ID3v1Tag(ID3v1Tag iD3v1Tag) {
        super(iD3v1Tag);
        this.album = "";
        this.artist = "";
        this.comment = "";
        this.title = "";
        this.year = "";
        this.genre = (byte) -1;
        this.album = iD3v1Tag.album;
        this.artist = iD3v1Tag.artist;
        this.comment = iD3v1Tag.comment;
        this.title = iD3v1Tag.title;
        this.year = iD3v1Tag.year;
        this.genre = iD3v1Tag.genre;
    }

    public ID3v1Tag(AbstractTag abstractTag) {
        ID3v11Tag iD3v11Tag;
        this.album = "";
        this.artist = "";
        this.comment = "";
        this.title = "";
        this.year = "";
        this.genre = (byte) -1;
        if (abstractTag != null) {
            if (abstractTag instanceof ID3v1Tag) {
                throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
            }
            if (abstractTag instanceof ID3v11Tag) {
                iD3v11Tag = (ID3v11Tag) abstractTag;
            } else {
                iD3v11Tag = new ID3v11Tag(abstractTag);
            }
            this.album = iD3v11Tag.album;
            this.artist = iD3v11Tag.artist;
            this.comment = iD3v11Tag.comment;
            this.title = iD3v11Tag.title;
            this.year = iD3v11Tag.year;
            this.genre = iD3v11Tag.genre;
        }
    }

    public ID3v1Tag(RandomAccessFile randomAccessFile, String str) throws TagNotFoundException, IOException {
        this.album = "";
        this.artist = "";
        this.comment = "";
        this.title = "";
        this.year = "";
        this.genre = (byte) -1;
        setLoggingFilename(str);
        FileChannel channel = randomAccessFile.getChannel();
        if (randomAccessFile.length() < 128) {
            throw new IOException("File not large enough to contain a tag");
        }
        channel.position(randomAccessFile.length() - 128);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
        channel.read(byteBufferAllocate);
        byteBufferAllocate.flip();
        read(byteBufferAllocate);
    }

    public ID3v1Tag(RandomAccessFile randomAccessFile) throws TagNotFoundException, IOException {
        this(randomAccessFile, "");
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getFirst(fieldKey.name()));
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(String str) {
        if (FieldKey.ARTIST.name().equals(str)) {
            return getArtist();
        }
        if (FieldKey.ALBUM.name().equals(str)) {
            return getAlbum();
        }
        if (FieldKey.TITLE.name().equals(str)) {
            return getTitle();
        }
        if (FieldKey.GENRE.name().equals(str)) {
            return getGenre();
        }
        if (FieldKey.YEAR.name().equals(str)) {
            return getYear();
        }
        if (FieldKey.COMMENT.name().equals(str)) {
            return getComment();
        }
        return new ArrayList();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCountIncludingSubValues() {
        return getFieldCount();
    }

    protected List<TagField> returnFieldToList(ID3v1TagField iD3v1TagField) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(iD3v1TagField);
        return arrayList;
    }

    public void setAlbum(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        this.album = ID3Tags.truncate(str, 30);
    }

    public String getFirstAlbum() {
        return this.album;
    }

    public List<TagField> getAlbum() {
        if (getFirstAlbum().length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.ALBUM.name(), getFirstAlbum()));
        }
        return new ArrayList();
    }

    public void setArtist(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        this.artist = ID3Tags.truncate(str, 30);
    }

    public String getFirstArtist() {
        return this.artist;
    }

    public List<TagField> getArtist() {
        if (getFirstArtist().length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.ARTIST.name(), getFirstArtist()));
        }
        return new ArrayList();
    }

    public void setComment(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        this.comment = ID3Tags.truncate(str, 30);
    }

    public List<TagField> getComment() {
        if (getFirstComment().length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.COMMENT.name(), getFirstComment()));
        }
        return new ArrayList();
    }

    public String getFirstComment() {
        return this.comment;
    }

    public void setGenre(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        Integer idForValue = GenreTypes.getInstanceOf().getIdForValue(str);
        if (idForValue != null) {
            this.genre = idForValue.byteValue();
        } else {
            this.genre = (byte) -1;
        }
    }

    public String getFirstGenre() {
        String valueForId = GenreTypes.getInstanceOf().getValueForId(Integer.valueOf(this.genre & 255).intValue());
        return valueForId == null ? "" : valueForId;
    }

    public List<TagField> getGenre() {
        if (getFirst(FieldKey.GENRE).length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.GENRE.name(), getFirst(FieldKey.GENRE)));
        }
        return new ArrayList();
    }

    public void setTitle(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        this.title = ID3Tags.truncate(str, 30);
    }

    public String getFirstTitle() {
        return this.title;
    }

    public List<TagField> getTitle() {
        if (getFirst(FieldKey.TITLE).length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.TITLE.name(), getFirst(FieldKey.TITLE)));
        }
        return new ArrayList();
    }

    public void setYear(String str) {
        this.year = ID3Tags.truncate(str, 4);
    }

    public String getFirstYear() {
        return this.year;
    }

    public List<TagField> getYear() {
        if (getFirst(FieldKey.YEAR).length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.YEAR.name(), getFirst(FieldKey.YEAR)));
        }
        return new ArrayList();
    }

    public String getFirstTrack() {
        throw new UnsupportedOperationException("ID3v10 cannot store track numbers");
    }

    public List<TagField> getTrack() {
        throw new UnsupportedOperationException("ID3v10 cannot store track numbers");
    }

    public TagField getFirstField(String str) {
        List<TagField> comment;
        if (FieldKey.ARTIST.name().equals(str)) {
            comment = getArtist();
        } else if (FieldKey.ALBUM.name().equals(str)) {
            comment = getAlbum();
        } else if (FieldKey.TITLE.name().equals(str)) {
            comment = getTitle();
        } else if (FieldKey.GENRE.name().equals(str)) {
            comment = getGenre();
        } else if (FieldKey.YEAR.name().equals(str)) {
            comment = getYear();
        } else {
            comment = FieldKey.COMMENT.name().equals(str) ? getComment() : null;
        }
        if (comment == null || comment.size() <= 0) {
            return null;
        }
        return comment.get(0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public Iterator<TagField> getFields() {
        throw new UnsupportedOperationException("TODO:Not done yet");
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return getFirst(fieldKey).length() > 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(String str) {
        try {
            return hasField(FieldKey.valueOf(str.toUpperCase()));
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    public boolean isEmpty() {
        return getFirst(FieldKey.TITLE).length() <= 0 && getFirstArtist().length() <= 0 && getFirstAlbum().length() <= 0 && getFirst(FieldKey.GENRE).length() <= 0 && getFirst(FieldKey.YEAR).length() <= 0 && getFirstComment().length() <= 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(fieldKey, strArr));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        setField(fieldKey, strArr);
    }

    /* renamed from: org.jaudiotagger.tag.id3.ID3v1Tag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$FieldKey;

        static {
            int[] iArr = new int[FieldKey.values().length];
            $SwitchMap$org$jaudiotagger$tag$FieldKey = iArr;
            try {
                iArr[FieldKey.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.TITLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.GENRE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.YEAR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.COMMENT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    public void setField(TagField tagField) {
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.valueOf(tagField.getId()).ordinal()]) {
            case 1:
                setArtist(tagField.toString());
                break;
            case 2:
                setAlbum(tagField.toString());
                break;
            case 3:
                setTitle(tagField.toString());
                break;
            case 4:
                setGenre(tagField.toString());
                break;
            case 5:
                setYear(tagField.toString());
                break;
            case 6:
                setComment(tagField.toString());
                break;
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) {
        String str = strArr[0];
        if (fieldKey == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        ID3v1FieldKey iD3v1FieldKey = tagFieldToID3v1Field.get(fieldKey);
        if (iD3v1FieldKey == null) {
            throw new KeyNotFoundException(ErrorMessage.INVALID_FIELD_FOR_ID3V1TAG.getMsg(fieldKey.name()));
        }
        return new ID3v1TagField(iD3v1FieldKey.name(), str);
    }

    public Charset getEncoding() {
        return StandardCharsets.ISO_8859_1;
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) {
        List<TagField> fields = getFields(fieldKey);
        if (fields.size() != 0) {
            return fields.get(0);
        }
        return null;
    }

    public List<TagField> getFields(FieldKey fieldKey) {
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$FieldKey[fieldKey.ordinal()]) {
            case 1:
                return getArtist();
            case 2:
                return getAlbum();
            case 3:
                return getTitle();
            case 4:
                return getGenre();
            case 5:
                return getYear();
            case 6:
                return getComment();
            default:
                return new ArrayList();
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(String str) {
        FieldKey fieldKeyValueOf = FieldKey.valueOf(str);
        if (fieldKeyValueOf != null) {
            return getFirst(fieldKeyValueOf);
        }
        return "";
    }

    public String getFirst(FieldKey fieldKey) {
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$FieldKey[fieldKey.ordinal()]) {
            case 1:
                return getFirstArtist();
            case 2:
                return getFirstAlbum();
            case 3:
                return getFirstTitle();
            case 4:
                return getFirstGenre();
            case 5:
                return getFirstYear();
            case 6:
                return getFirstComment();
            default:
                return "";
        }
    }

    public String getSubValue(FieldKey fieldKey, int i, int i2) {
        return getValue(fieldKey, i);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) {
        return getFirst(fieldKey);
    }

    public void deleteField(FieldKey fieldKey) {
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$FieldKey[fieldKey.ordinal()]) {
            case 1:
                setArtist("");
                break;
            case 2:
                setAlbum("");
                break;
            case 3:
                setTitle("");
                break;
            case 4:
                setGenre("");
                break;
            case 5:
                setYear("");
                break;
            case 6:
                setComment("");
                break;
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(String str) {
        FieldKey fieldKeyValueOf = FieldKey.valueOf(str);
        if (fieldKeyValueOf != null) {
            deleteField(fieldKeyValueOf);
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (!(obj instanceof ID3v1Tag)) {
            return false;
        }
        ID3v1Tag iD3v1Tag = (ID3v1Tag) obj;
        return this.album.equals(iD3v1Tag.album) && this.artist.equals(iD3v1Tag.artist) && this.comment.equals(iD3v1Tag.comment) && this.genre == iD3v1Tag.genre && this.title.equals(iD3v1Tag.title) && this.year.equals(iD3v1Tag.year) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public Iterator<Object> iterator() {
        return new ID3v1Iterator(this);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws TagNotFoundException {
        if (!seek(byteBuffer)) {
            throw new TagNotFoundException(getLoggingFilename() + ":ID3v1 tag not found");
        }
        logger.finer(getLoggingFilename() + ":Reading v1 tag");
        byte[] bArr = new byte[128];
        byteBuffer.position(0);
        byteBuffer.get(bArr, 0, 128);
        this.title = new String(bArr, 3, 30, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher = AbstractID3v1Tag.endofStringPattern.matcher(this.title);
        if (matcher.find()) {
            this.title = this.title.substring(0, matcher.start());
        }
        this.artist = new String(bArr, 33, 30, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher2 = AbstractID3v1Tag.endofStringPattern.matcher(this.artist);
        if (matcher2.find()) {
            this.artist = this.artist.substring(0, matcher2.start());
        }
        this.album = new String(bArr, 63, 30, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher3 = AbstractID3v1Tag.endofStringPattern.matcher(this.album);
        logger.finest(getLoggingFilename() + ":Orig Album is:" + this.comment + ":");
        if (matcher3.find()) {
            this.album = this.album.substring(0, matcher3.start());
            logger.finest(getLoggingFilename() + ":Album is:" + this.album + ":");
        }
        this.year = new String(bArr, 93, 4, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher4 = AbstractID3v1Tag.endofStringPattern.matcher(this.year);
        if (matcher4.find()) {
            this.year = this.year.substring(0, matcher4.start());
        }
        this.comment = new String(bArr, 97, 30, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher5 = AbstractID3v1Tag.endofStringPattern.matcher(this.comment);
        logger.finest(getLoggingFilename() + ":Orig Comment is:" + this.comment + ":");
        if (matcher5.find()) {
            this.comment = this.comment.substring(0, matcher5.start());
            logger.finest(getLoggingFilename() + ":Comment is:" + this.comment + ":");
        }
        this.genre = bArr[127];
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public boolean seek(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[3];
        byteBuffer.get(bArr, 0, 3);
        return Arrays.equals(bArr, TAG_ID);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void write(RandomAccessFile randomAccessFile) throws IOException {
        logger.config("Saving ID3v1 tag to file");
        byte[] bArr = new byte[128];
        delete(randomAccessFile);
        randomAccessFile.seek(randomAccessFile.length());
        System.arraycopy(TAG_ID, 0, bArr, 0, TAG_ID.length);
        if (TagOptionSingleton.getInstance().isId3v1SaveTitle()) {
            String strTruncate = ID3Tags.truncate(this.title, 30);
            for (int i = 0; i < strTruncate.length(); i++) {
                bArr[i + 3] = (byte) strTruncate.charAt(i);
            }
        }
        if (TagOptionSingleton.getInstance().isId3v1SaveArtist()) {
            String strTruncate2 = ID3Tags.truncate(this.artist, 30);
            for (int i2 = 0; i2 < strTruncate2.length(); i2++) {
                bArr[i2 + 33] = (byte) strTruncate2.charAt(i2);
            }
        }
        if (TagOptionSingleton.getInstance().isId3v1SaveAlbum()) {
            String strTruncate3 = ID3Tags.truncate(this.album, 30);
            for (int i3 = 0; i3 < strTruncate3.length(); i3++) {
                bArr[i3 + 63] = (byte) strTruncate3.charAt(i3);
            }
        }
        if (TagOptionSingleton.getInstance().isId3v1SaveYear()) {
            String strTruncate4 = ID3Tags.truncate(this.year, 4);
            for (int i4 = 0; i4 < strTruncate4.length(); i4++) {
                bArr[i4 + 93] = (byte) strTruncate4.charAt(i4);
            }
        }
        if (TagOptionSingleton.getInstance().isId3v1SaveComment()) {
            String strTruncate5 = ID3Tags.truncate(this.comment, 30);
            for (int i5 = 0; i5 < strTruncate5.length(); i5++) {
                bArr[i5 + 97] = (byte) strTruncate5.charAt(i5);
            }
        }
        if (TagOptionSingleton.getInstance().isId3v1SaveGenre()) {
            bArr[127] = this.genre;
        }
        randomAccessFile.write(bArr);
        logger.config("Saved ID3v1 tag to file");
    }

    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement("tag", getIdentifier());
        MP3File.getStructureFormatter().addElement("title", this.title);
        MP3File.getStructureFormatter().addElement("artist", this.artist);
        MP3File.getStructureFormatter().addElement("album", this.album);
        MP3File.getStructureFormatter().addElement("year", this.year);
        MP3File.getStructureFormatter().addElement(TYPE_COMMENT, this.comment);
        MP3File.getStructureFormatter().addElement("genre", this.genre);
        MP3File.getStructureFormatter().closeHeadingElement("tag");
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        return Collections.emptyList();
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        throw new UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg());
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws FieldDataInvalidException {
        throw new UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg());
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        throw new UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg());
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        throw new UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg());
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        throw new UnsupportedOperationException(ErrorMessage.GENERIC_NOT_SUPPORTED.getMsg());
    }
}
