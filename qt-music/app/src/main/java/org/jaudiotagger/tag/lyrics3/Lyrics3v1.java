package org.jaudiotagger.tag.lyrics3;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagNotFoundException;
import org.jaudiotagger.tag.id3.AbstractTag;
import org.jaudiotagger.tag.id3.ID3Tags;

/* loaded from: classes3.dex */
public class Lyrics3v1 extends AbstractLyrics3 {
    private String lyric;

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public boolean seek(ByteBuffer byteBuffer) {
        return false;
    }

    public Lyrics3v1() {
        this.lyric = "";
    }

    public Lyrics3v1(Lyrics3v1 lyrics3v1) {
        super(lyrics3v1);
        this.lyric = "";
        this.lyric = lyrics3v1.lyric;
    }

    public Lyrics3v1(AbstractTag abstractTag) {
        Lyrics3v2 lyrics3v2;
        this.lyric = "";
        if (abstractTag != null) {
            if (abstractTag instanceof Lyrics3v1) {
                throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
            }
            if (abstractTag instanceof Lyrics3v2) {
                lyrics3v2 = (Lyrics3v2) abstractTag;
            } else {
                lyrics3v2 = new Lyrics3v2(abstractTag);
            }
            this.lyric = ((FieldFrameBodyLYR) lyrics3v2.getField(Lyrics3v2Fields.FIELD_V2_LYRICS_MULTI_LINE_TEXT).getBody()).getLyric();
        }
    }

    public Lyrics3v1(ByteBuffer byteBuffer) throws TagNotFoundException, IOException {
        this.lyric = "";
        try {
            read(byteBuffer);
        } catch (TagException e) {
            e.printStackTrace();
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "Lyrics3v1.00";
    }

    public void setLyric(String str) {
        this.lyric = ID3Tags.truncate(str, 5100);
    }

    public String getLyric() {
        return this.lyric;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return "LYRICSBEGIN".length() + this.lyric.length() + "LYRICSEND".length();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean isSubsetOf(Object obj) {
        return (obj instanceof Lyrics3v1) && ((Lyrics3v1) obj).lyric.contains(this.lyric);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof Lyrics3v1) && this.lyric.equals(((Lyrics3v1) obj).lyric) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public Iterator<Object> iterator() {
        throw new UnsupportedOperationException("Method iterator() not yet implemented.");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws TagException {
        byte[] bArr = new byte[5120];
        if (!seek(byteBuffer)) {
            throw new TagNotFoundException("ID3v1 tag not found");
        }
        byteBuffer.get(bArr);
        String str = new String(bArr);
        this.lyric = str.substring(0, str.indexOf("LYRICSEND"));
    }

    public boolean seek(RandomAccessFile randomAccessFile) throws IOException {
        long filePointer;
        byte[] bArr = new byte[5120];
        randomAccessFile.seek(randomAccessFile.length() - 137);
        randomAccessFile.read(bArr, 0, 9);
        if (new String(bArr, 0, 9).equals("LYRICSEND")) {
            filePointer = randomAccessFile.getFilePointer();
        } else {
            randomAccessFile.seek(randomAccessFile.length() - 9);
            randomAccessFile.read(bArr, 0, 9);
            if (!new String(bArr, 0, 9).equals("LYRICSEND")) {
                return false;
            }
            filePointer = randomAccessFile.getFilePointer();
        }
        long j = filePointer - 5120;
        randomAccessFile.seek(j);
        randomAccessFile.read(bArr);
        int iIndexOf = new String(bArr).indexOf("LYRICSBEGIN");
        if (iIndexOf == -1) {
            return false;
        }
        randomAccessFile.seek(j + iIndexOf + 11);
        return true;
    }

    public String toString() {
        return (getIdentifier() + " " + getSize() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE) + this.lyric;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void write(RandomAccessFile randomAccessFile) throws IOException {
        delete(randomAccessFile);
        randomAccessFile.seek(randomAccessFile.length());
        byte[] bArr = new byte[this.lyric.length() + 20];
        for (int i = 0; i < "LYRICSBEGIN".length(); i++) {
            bArr[i] = (byte) "LYRICSBEGIN".charAt(i);
        }
        int length = "LYRICSBEGIN".length();
        String strTruncate = ID3Tags.truncate(this.lyric, 5100);
        for (int i2 = 0; i2 < strTruncate.length(); i2++) {
            bArr[i2 + length] = (byte) strTruncate.charAt(i2);
        }
        int length2 = length + strTruncate.length();
        for (int i3 = 0; i3 < "LYRICSEND".length(); i3++) {
            bArr[i3 + length2] = (byte) "LYRICSEND".charAt(i3);
        }
        randomAccessFile.write(bArr, 0, length2 + "LYRICSEND".length());
    }
}
