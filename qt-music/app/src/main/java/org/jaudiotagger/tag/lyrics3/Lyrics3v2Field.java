package org.jaudiotagger.tag.lyrics3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractTagFrame;
import org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyTextInfo;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodySYLT;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUSLT;

/* loaded from: classes3.dex */
public class Lyrics3v2Field extends AbstractTagFrame {
    protected static Set<String> LYRICS_FRAME_IDS;

    public Lyrics3v2Field() {
    }

    public Lyrics3v2Field(Lyrics3v2Field lyrics3v2Field) {
        super(lyrics3v2Field);
    }

    public Lyrics3v2Field(AbstractLyrics3v2FieldFrameBody abstractLyrics3v2FieldFrameBody) {
        this.frameBody = abstractLyrics3v2FieldFrameBody;
    }

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("USLT");
        hashSet.add("SYLT");
        hashSet.add("COMM");
        hashSet.add("TCOM");
        hashSet.add("TALB");
        hashSet.add("TPE1");
        hashSet.add("TIT2");
        LYRICS_FRAME_IDS = Collections.unmodifiableSet(hashSet);
    }

    public static boolean isLyrics3v2Field(AbstractID3v2Frame abstractID3v2Frame) {
        return LYRICS_FRAME_IDS.contains(abstractID3v2Frame.getIdentifier());
    }

    public Lyrics3v2Field(AbstractID3v2Frame abstractID3v2Frame) throws TagException {
        String identifier = abstractID3v2Frame.getIdentifier();
        if (identifier.startsWith("USLT")) {
            this.frameBody = new FieldFrameBodyLYR("");
            ((FieldFrameBodyLYR) this.frameBody).addLyric((FrameBodyUSLT) abstractID3v2Frame.getBody());
            return;
        }
        if (identifier.startsWith("SYLT")) {
            this.frameBody = new FieldFrameBodyLYR("");
            ((FieldFrameBodyLYR) this.frameBody).addLyric((FrameBodySYLT) abstractID3v2Frame.getBody());
            return;
        }
        if (identifier.startsWith("COMM")) {
            this.frameBody = new FieldFrameBodyINF(((FrameBodyCOMM) abstractID3v2Frame.getBody()).getText());
            return;
        }
        if (identifier.equals("TCOM")) {
            AbstractFrameBodyTextInfo abstractFrameBodyTextInfo = (AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody();
            this.frameBody = new FieldFrameBodyAUT("");
            if (abstractFrameBodyTextInfo == null || abstractFrameBodyTextInfo.getText().length() <= 0) {
                return;
            }
            this.frameBody = new FieldFrameBodyAUT(abstractFrameBodyTextInfo.getText());
            return;
        }
        if (identifier.equals("TALB")) {
            AbstractFrameBodyTextInfo abstractFrameBodyTextInfo2 = (AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody();
            if (abstractFrameBodyTextInfo2 == null || abstractFrameBodyTextInfo2.getText().length() <= 0) {
                return;
            }
            this.frameBody = new FieldFrameBodyEAL(abstractFrameBodyTextInfo2.getText());
            return;
        }
        if (identifier.equals("TPE1")) {
            AbstractFrameBodyTextInfo abstractFrameBodyTextInfo3 = (AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody();
            if (abstractFrameBodyTextInfo3 == null || abstractFrameBodyTextInfo3.getText().length() <= 0) {
                return;
            }
            this.frameBody = new FieldFrameBodyEAR(abstractFrameBodyTextInfo3.getText());
            return;
        }
        if (identifier.equals("TIT2")) {
            AbstractFrameBodyTextInfo abstractFrameBodyTextInfo4 = (AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody();
            if (abstractFrameBodyTextInfo4 == null || abstractFrameBodyTextInfo4.getText().length() <= 0) {
                return;
            }
            this.frameBody = new FieldFrameBodyETT(abstractFrameBodyTextInfo4.getText());
            return;
        }
        throw new TagException("Cannot createField Lyrics3v2 field from given ID3v2 frame");
    }

    public Lyrics3v2Field(ByteBuffer byteBuffer) throws InvalidTagException {
        read(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        if (this.frameBody == null) {
            return "";
        }
        return this.frameBody.getIdentifier();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return this.frameBody.getSize() + 5 + getIdentifier().length();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidTagException {
        byte[] bArr = new byte[6];
        while (byteBuffer.get() == 0) {
        }
        byteBuffer.position(byteBuffer.position() - 1);
        byteBuffer.get(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        if (!Lyrics3v2Fields.isLyrics3v2FieldIdentifier(str)) {
            throw new InvalidTagException(str.concat(" is not a valid ID3v2.4 frame"));
        }
        this.frameBody = readBody(str, byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrame
    public String toString() {
        if (this.frameBody == null) {
            return "";
        }
        return this.frameBody.toString();
    }

    public void write(RandomAccessFile randomAccessFile) throws IOException {
        if (this.frameBody.getSize() > 0 || TagOptionSingleton.getInstance().isLyrics3SaveEmptyField()) {
            byte[] bArr = new byte[3];
            String identifier = getIdentifier();
            for (int i = 0; i < identifier.length(); i++) {
                bArr[i] = (byte) identifier.charAt(i);
            }
            randomAccessFile.write(bArr, 0, identifier.length());
        }
    }

    private AbstractLyrics3v2FieldFrameBody readBody(String str, ByteBuffer byteBuffer) throws InvalidTagException {
        if (str.equals(Lyrics3v2Fields.FIELD_V2_AUTHOR)) {
            return new FieldFrameBodyAUT(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_ALBUM)) {
            return new FieldFrameBodyEAL(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_ARTIST)) {
            return new FieldFrameBodyEAR(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_TRACK)) {
            return new FieldFrameBodyETT(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_IMAGE)) {
            return new FieldFrameBodyIMG(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_INDICATIONS)) {
            return new FieldFrameBodyIND(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_ADDITIONAL_MULTI_LINE_TEXT)) {
            return new FieldFrameBodyINF(byteBuffer);
        }
        if (str.equals(Lyrics3v2Fields.FIELD_V2_LYRICS_MULTI_LINE_TEXT)) {
            return new FieldFrameBodyLYR(byteBuffer);
        }
        return new FieldFrameBodyUnsupported(byteBuffer);
    }
}
