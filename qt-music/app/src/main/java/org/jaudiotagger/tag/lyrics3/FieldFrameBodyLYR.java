package org.jaudiotagger.tag.lyrics3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.AbstractDataType;
import org.jaudiotagger.tag.datatype.ID3v2LyricLine;
import org.jaudiotagger.tag.datatype.Lyrics3Line;
import org.jaudiotagger.tag.datatype.Lyrics3TimeStamp;
import org.jaudiotagger.tag.id3.framebody.FrameBodySYLT;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUSLT;

/* loaded from: classes3.dex */
public class FieldFrameBodyLYR extends AbstractLyrics3v2FieldFrameBody {
    private ArrayList<Lyrics3Line> lines;

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
    }

    public FieldFrameBodyLYR() {
        this.lines = new ArrayList<>();
    }

    public FieldFrameBodyLYR(FieldFrameBodyLYR fieldFrameBodyLYR) {
        super(fieldFrameBodyLYR);
        this.lines = new ArrayList<>();
        for (int i = 0; i < fieldFrameBodyLYR.lines.size(); i++) {
            this.lines.add(new Lyrics3Line(fieldFrameBodyLYR.lines.get(i)));
        }
    }

    public FieldFrameBodyLYR(String str) {
        this.lines = new ArrayList<>();
        readString(str);
    }

    public FieldFrameBodyLYR(FrameBodySYLT frameBodySYLT) {
        this.lines = new ArrayList<>();
        addLyric(frameBodySYLT);
    }

    public FieldFrameBodyLYR(FrameBodyUSLT frameBodyUSLT) {
        this.lines = new ArrayList<>();
        addLyric(frameBodyUSLT);
    }

    public FieldFrameBodyLYR(ByteBuffer byteBuffer) throws NumberFormatException, InvalidTagException {
        this.lines = new ArrayList<>();
        read(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_LYRICS_MULTI_LINE_TEXT;
    }

    public void setLyric(String str) {
        readString(str);
    }

    public String getLyric() {
        return writeString();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        Iterator<Lyrics3Line> it = this.lines.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getSize() + 2;
        }
        return size;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean isSubsetOf(Object obj) {
        if (!(obj instanceof FieldFrameBodyLYR)) {
            return false;
        }
        ArrayList<Lyrics3Line> arrayList = ((FieldFrameBodyLYR) obj).lines;
        Iterator<Lyrics3Line> it = this.lines.iterator();
        while (it.hasNext()) {
            if (!arrayList.contains(it.next())) {
                return false;
            }
        }
        return super.isSubsetOf(obj);
    }

    public void addLyric(FrameBodySYLT frameBodySYLT) {
        Iterator<? extends AbstractDataType> it = frameBodySYLT.iterator();
        HashMap map = new HashMap();
        while (it.hasNext()) {
            AbstractDataType next = it.next();
            if (next instanceof ID3v2LyricLine) {
                ID3v2LyricLine iD3v2LyricLine = new ID3v2LyricLine((ID3v2LyricLine) next);
                Lyrics3TimeStamp lyrics3TimeStamp = new Lyrics3TimeStamp("Time Stamp", this);
                lyrics3TimeStamp.setTimeStamp(iD3v2LyricLine.getTimeStamp(), (byte) frameBodySYLT.getTimeStampFormat());
                if (map.containsKey(iD3v2LyricLine.getText())) {
                    ((Lyrics3Line) map.get(iD3v2LyricLine.getText())).addTimeStamp(lyrics3TimeStamp);
                } else {
                    Lyrics3Line lyrics3Line = new Lyrics3Line("Lyric Line", this);
                    lyrics3Line.setLyric(iD3v2LyricLine);
                    lyrics3Line.setTimeStamp(lyrics3TimeStamp);
                    map.put(iD3v2LyricLine.getText(), lyrics3Line);
                    this.lines.add(lyrics3Line);
                }
            }
        }
    }

    public void addLyric(FrameBodyUSLT frameBodyUSLT) {
        Lyrics3Line lyrics3Line = new Lyrics3Line("Lyric Line", this);
        lyrics3Line.setLyric(frameBodyUSLT.getLyric());
        this.lines.add(lyrics3Line);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof FieldFrameBodyLYR) && this.lines.equals(((FieldFrameBodyLYR) obj).lines) && super.equals(obj);
    }

    public boolean hasTimeStamp() {
        Iterator<Lyrics3Line> it = this.lines.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (it.next().hasTimeStamp()) {
                z = true;
            }
        }
        return z;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public Iterator<Lyrics3Line> iterator() {
        return this.lines.iterator();
    }

    @Override // org.jaudiotagger.tag.lyrics3.AbstractLyrics3v2FieldFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws NumberFormatException, InvalidTagException {
        byte[] bArr = new byte[5];
        byteBuffer.get(bArr, 0, 5);
        int i = Integer.parseInt(new String(bArr, 0, 5));
        if (i == 0 && !TagOptionSingleton.getInstance().isLyrics3KeepEmptyFieldIfRead()) {
            throw new InvalidTagException("Lyircs3v2 Field has size of zero.");
        }
        byte[] bArr2 = new byte[i];
        byteBuffer.get(bArr2);
        readString(new String(bArr2));
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String toString() {
        String str = getIdentifier() + " : ";
        Iterator<Lyrics3Line> it = this.lines.iterator();
        while (it.hasNext()) {
            str = str + it.next().toString();
        }
        return str;
    }

    @Override // org.jaudiotagger.tag.lyrics3.AbstractLyrics3v2FieldFrameBody
    public void write(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = new byte[5];
        int size = getSize();
        String string = Integer.toString(size);
        for (int i = 0; i < 5 - string.length(); i++) {
            bArr[i] = 48;
        }
        int length = 5 - string.length();
        for (int i2 = 0; i2 < string.length(); i2++) {
            bArr[i2 + length] = (byte) string.charAt(i2);
        }
        string.length();
        randomAccessFile.write(bArr, 0, 5);
        if (size > 0) {
            String strWriteString = writeString();
            byte[] bArr2 = new byte[strWriteString.length()];
            for (int i3 = 0; i3 < strWriteString.length(); i3++) {
                bArr2[i3] = (byte) strWriteString.charAt(i3);
            }
            randomAccessFile.write(bArr2);
        }
    }

    private void readString(String str) {
        int iIndexOf = str.indexOf(Lyrics3v2Fields.CRLF);
        this.lines = new ArrayList<>();
        int length = 0;
        while (iIndexOf >= 0) {
            String strSubstring = str.substring(length, iIndexOf);
            Lyrics3Line lyrics3Line = new Lyrics3Line("Lyric Line", this);
            lyrics3Line.setLyric(strSubstring);
            this.lines.add(lyrics3Line);
            length = Lyrics3v2Fields.CRLF.length() + iIndexOf;
            iIndexOf = str.indexOf(Lyrics3v2Fields.CRLF, length);
        }
        if (length < str.length()) {
            String strSubstring2 = str.substring(length);
            Lyrics3Line lyrics3Line2 = new Lyrics3Line("Lyric Line", this);
            lyrics3Line2.setLyric(strSubstring2);
            this.lines.add(lyrics3Line2);
        }
    }

    private String writeString() {
        Iterator<Lyrics3Line> it = this.lines.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next().writeString() + Lyrics3v2Fields.CRLF;
        }
        return str;
    }
}
