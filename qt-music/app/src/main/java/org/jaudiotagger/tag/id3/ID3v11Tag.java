package org.jaudiotagger.tag.id3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagNotFoundException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTALB;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTCON;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTDRC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTIT2;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTPE1;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTRCK;
import org.jaudiotagger.tag.reference.GenreTypes;

/* loaded from: classes3.dex */
public class ID3v11Tag extends ID3v1Tag {
    protected static final int FIELD_COMMENT_LENGTH = 28;
    protected static final int FIELD_COMMENT_POS = 97;
    protected static final int FIELD_TRACK_INDICATOR_LENGTH = 1;
    protected static final int FIELD_TRACK_INDICATOR_POS = 125;
    protected static final int FIELD_TRACK_LENGTH = 1;
    protected static final int FIELD_TRACK_POS = 126;
    private static final byte MAJOR_VERSION = 1;
    private static final byte RELEASE = 1;
    private static final byte REVISION = 0;
    protected static final int TRACK_MAX_VALUE = 255;
    protected static final int TRACK_MIN_VALUE = 1;
    protected static final int TRACK_UNDEFINED = 0;
    protected static final String TYPE_TRACK = "track";
    protected byte track;

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
    public int getFieldCount() {
        return 7;
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getMajorVersion() {
        return (byte) 1;
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getRelease() {
        return (byte) 1;
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getRevision() {
        return (byte) 0;
    }

    public ID3v11Tag() {
        this.track = (byte) 0;
    }

    public ID3v11Tag(ID3v11Tag iD3v11Tag) {
        super((ID3v1Tag) iD3v11Tag);
        this.track = (byte) 0;
        this.track = iD3v11Tag.track;
    }

    public ID3v11Tag(AbstractTag abstractTag) {
        ID3v24Tag iD3v24Tag;
        this.track = (byte) 0;
        if (abstractTag != null) {
            if (abstractTag instanceof ID3v1Tag) {
                if (abstractTag instanceof ID3v11Tag) {
                    throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
                }
                ID3v1Tag iD3v1Tag = (ID3v1Tag) abstractTag;
                this.title = iD3v1Tag.title;
                this.artist = iD3v1Tag.artist;
                this.album = iD3v1Tag.album;
                this.comment = iD3v1Tag.comment;
                this.year = iD3v1Tag.year;
                this.genre = iD3v1Tag.genre;
                return;
            }
            if (!(abstractTag instanceof ID3v24Tag)) {
                iD3v24Tag = new ID3v24Tag(abstractTag);
            } else {
                iD3v24Tag = (ID3v24Tag) abstractTag;
            }
            if (iD3v24Tag.hasFrame("TIT2")) {
                this.title = ID3Tags.truncate(((FrameBodyTIT2) ((ID3v24Frame) iD3v24Tag.getFrame("TIT2").get(0)).getBody()).getText(), 30);
            }
            if (iD3v24Tag.hasFrame("TPE1")) {
                this.artist = ID3Tags.truncate(((FrameBodyTPE1) ((ID3v24Frame) iD3v24Tag.getFrame("TPE1").get(0)).getBody()).getText(), 30);
            }
            if (iD3v24Tag.hasFrame("TALB")) {
                this.album = ID3Tags.truncate(((FrameBodyTALB) ((ID3v24Frame) iD3v24Tag.getFrame("TALB").get(0)).getBody()).getText(), 30);
            }
            if (iD3v24Tag.hasFrame(ID3v24Frames.FRAME_ID_YEAR)) {
                this.year = ID3Tags.truncate(((FrameBodyTDRC) ((ID3v24Frame) iD3v24Tag.getFrame(ID3v24Frames.FRAME_ID_YEAR).get(0)).getBody()).getText(), 4);
            }
            if (iD3v24Tag.hasFrame("COMM")) {
                Iterator<Object> frameOfType = iD3v24Tag.getFrameOfType("COMM");
                String str = "";
                while (frameOfType.hasNext()) {
                    str = str + ((FrameBodyCOMM) ((ID3v24Frame) frameOfType.next()).getBody()).getText() + " ";
                }
                this.comment = ID3Tags.truncate(str, 28);
            }
            if (iD3v24Tag.hasFrame("TCON")) {
                String text = ((FrameBodyTCON) ((ID3v24Frame) iD3v24Tag.getFrame("TCON").get(0)).getBody()).getText();
                try {
                    this.genre = (byte) ID3Tags.findNumber(text);
                } catch (TagException e) {
                    Integer idForValue = GenreTypes.getInstanceOf().getIdForValue(text);
                    if (idForValue != null) {
                        this.genre = idForValue.byteValue();
                    } else {
                        logger.log(Level.WARNING, getLoggingFilename() + ":Unable to convert TCON frame to format suitable for v11 tag", (Throwable) e);
                        this.genre = (byte) -1;
                    }
                }
            }
            if (iD3v24Tag.hasFrame("TRCK")) {
                this.track = (byte) ((FrameBodyTRCK) ((ID3v24Frame) iD3v24Tag.getFrame("TRCK").get(0)).getBody()).getTrackNo().intValue();
            }
        }
    }

    public ID3v11Tag(RandomAccessFile randomAccessFile, String str) throws TagNotFoundException, IOException {
        this.track = (byte) 0;
        setLoggingFilename(str);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
        if (randomAccessFile.length() < 128) {
            throw new IOException("File not large enough to contain a tag");
        }
        FileChannel channel = randomAccessFile.getChannel();
        channel.position(randomAccessFile.length() - 128);
        channel.read(byteBufferAllocate);
        byteBufferAllocate.flip();
        read(byteBufferAllocate);
    }

    public ID3v11Tag(RandomAccessFile randomAccessFile) throws TagNotFoundException, IOException {
        this(randomAccessFile, "");
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag
    public void setComment(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        this.comment = ID3Tags.truncate(str, 28);
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag
    public String getFirstComment() {
        return this.comment;
    }

    public void setTrack(String str) throws NumberFormatException {
        int i;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            i = 0;
        }
        if (i > 255 || i < 1) {
            this.track = (byte) 0;
        } else {
            this.track = (byte) Integer.parseInt(str);
        }
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag
    public String getFirstTrack() {
        return String.valueOf(this.track & 255);
    }

    public void addTrack(String str) throws NumberFormatException {
        setTrack(str);
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag
    public List<TagField> getTrack() {
        if (getFirst(FieldKey.TRACK).length() > 0) {
            return returnFieldToList(new ID3v1TagField(ID3v1FieldKey.TRACK.name(), getFirst(FieldKey.TRACK)));
        }
        return new ArrayList();
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) throws NumberFormatException {
        if (FieldKey.valueOf(tagField.getId()) == FieldKey.TRACK) {
            setTrack(tagField.toString());
        } else {
            super.setField(tagField);
        }
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) {
        if (fieldKey == FieldKey.TRACK) {
            return getTrack();
        }
        return super.getFields(fieldKey);
    }

    /* renamed from: org.jaudiotagger.tag.id3.ID3v11Tag$1, reason: invalid class name */
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
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.TRACK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.COMMENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
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
                return getFirstTrack();
            case 7:
                return getFirstComment();
            default:
                return "";
        }
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
    public TagField getFirstField(String str) {
        if (FieldKey.TRACK.name().equals(str)) {
            List<TagField> track = getTrack();
            if (track == null || track.size() <= 0) {
                return null;
            }
            return track.get(0);
        }
        return super.getFirstField(str);
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        return this.track <= 0 && super.isEmpty();
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) {
        if (fieldKey == FieldKey.TRACK) {
            this.track = (byte) 0;
        } else {
            super.deleteField(fieldKey);
        }
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractTag, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof ID3v11Tag) && this.track == ((ID3v11Tag) obj).track && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractTag
    public boolean seek(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[3];
        byteBuffer.get(bArr, 0, 3);
        if (!Arrays.equals(bArr, TAG_ID)) {
            return false;
        }
        byteBuffer.position(FIELD_TRACK_INDICATOR_POS);
        return byteBuffer.get() == 0 && byteBuffer.get() != 0;
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws TagNotFoundException {
        if (!seek(byteBuffer)) {
            throw new TagNotFoundException("ID3v1 tag not found");
        }
        logger.finer("Reading v1.1 tag");
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
        if (matcher3.find()) {
            this.album = this.album.substring(0, matcher3.start());
        }
        this.year = new String(bArr, 93, 4, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher4 = AbstractID3v1Tag.endofStringPattern.matcher(this.year);
        if (matcher4.find()) {
            this.year = this.year.substring(0, matcher4.start());
        }
        this.comment = new String(bArr, 97, 28, StandardCharsets.ISO_8859_1).trim();
        Matcher matcher5 = AbstractID3v1Tag.endofStringPattern.matcher(this.comment);
        if (matcher5.find()) {
            this.comment = this.comment.substring(0, matcher5.start());
        }
        this.track = bArr[126];
        this.genre = bArr[127];
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag, org.jaudiotagger.tag.id3.AbstractTag
    public void write(RandomAccessFile randomAccessFile) throws IOException {
        logger.config("Saving ID3v11 tag to file");
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
            String strTruncate5 = ID3Tags.truncate(this.comment, 28);
            for (int i5 = 0; i5 < strTruncate5.length(); i5++) {
                bArr[i5 + 97] = (byte) strTruncate5.charAt(i5);
            }
        }
        bArr[126] = this.track;
        if (TagOptionSingleton.getInstance().isId3v1SaveGenre()) {
            bArr[127] = this.genre;
        }
        randomAccessFile.write(bArr);
        logger.config("Saved ID3v11 tag to file");
    }

    @Override // org.jaudiotagger.tag.id3.ID3v1Tag
    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement("tag", getIdentifier());
        MP3File.getStructureFormatter().addElement("title", this.title);
        MP3File.getStructureFormatter().addElement("artist", this.artist);
        MP3File.getStructureFormatter().addElement("album", this.album);
        MP3File.getStructureFormatter().addElement("year", this.year);
        MP3File.getStructureFormatter().addElement("comment", this.comment);
        MP3File.getStructureFormatter().addElement("track", this.track);
        MP3File.getStructureFormatter().addElement("genre", this.genre);
        MP3File.getStructureFormatter().closeHeadingElement("tag");
    }
}
