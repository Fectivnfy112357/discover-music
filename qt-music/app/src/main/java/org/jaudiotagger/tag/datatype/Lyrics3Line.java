package org.jaudiotagger.tag.datatype;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class Lyrics3Line extends AbstractDataType {
    private String lyric;
    private List<Lyrics3TimeStamp> timeStamp;

    public Lyrics3Line(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.timeStamp = new LinkedList();
        this.lyric = "";
    }

    public Lyrics3Line(Lyrics3Line lyrics3Line) {
        super(lyrics3Line);
        this.timeStamp = new LinkedList();
        this.lyric = "";
        this.lyric = lyrics3Line.lyric;
        for (int i = 0; i < lyrics3Line.timeStamp.size(); i++) {
            this.timeStamp.add(new Lyrics3TimeStamp(lyrics3Line.timeStamp.get(i)));
        }
    }

    public void setLyric(String str) {
        this.lyric = str;
    }

    public void setLyric(ID3v2LyricLine iD3v2LyricLine) {
        this.lyric = iD3v2LyricLine.getText();
    }

    public String getLyric() {
        return this.lyric;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public int getSize() {
        Iterator<Lyrics3TimeStamp> it = this.timeStamp.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size + this.lyric.length();
    }

    public void setTimeStamp(Lyrics3TimeStamp lyrics3TimeStamp) {
        this.timeStamp.clear();
        this.timeStamp.add(lyrics3TimeStamp);
    }

    public Iterator<Lyrics3TimeStamp> getTimeStamp() {
        return this.timeStamp.iterator();
    }

    public void addLyric(String str) {
        this.lyric += str;
    }

    public void addLyric(ID3v2LyricLine iD3v2LyricLine) {
        this.lyric += iD3v2LyricLine.getText();
    }

    public void addTimeStamp(Lyrics3TimeStamp lyrics3TimeStamp) {
        this.timeStamp.add(lyrics3TimeStamp);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (!(obj instanceof Lyrics3Line)) {
            return false;
        }
        Lyrics3Line lyrics3Line = (Lyrics3Line) obj;
        return this.lyric.equals(lyrics3Line.lyric) && this.timeStamp.equals(lyrics3Line.timeStamp) && super.equals(obj);
    }

    public boolean hasTimeStamp() {
        return !this.timeStamp.isEmpty();
    }

    public void readString(String str, int i) {
        if (str == null) {
            throw new NullPointerException("Image is null");
        }
        if (i < 0 || i >= str.length()) {
            throw new IndexOutOfBoundsException("Offset to line is out of bounds: offset = " + i + ", line.length()" + str.length());
        }
        this.timeStamp = new LinkedList();
        int iIndexOf = str.indexOf("[", i);
        while (iIndexOf >= 0) {
            i = str.indexOf("]", iIndexOf) + 1;
            Lyrics3TimeStamp lyrics3TimeStamp = new Lyrics3TimeStamp("Time Stamp");
            lyrics3TimeStamp.readString(str.substring(iIndexOf, i));
            this.timeStamp.add(lyrics3TimeStamp);
            iIndexOf = str.indexOf("[", i);
        }
        this.lyric = str.substring(i);
    }

    public String toString() {
        Iterator<Lyrics3TimeStamp> it = this.timeStamp.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next().toString();
        }
        return "timeStamp = " + str + ", lyric = " + this.lyric + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }

    public String writeString() {
        Iterator<Lyrics3TimeStamp> it = this.timeStamp.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next().writeString();
        }
        return str + this.lyric;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        readString(bArr.toString(), i);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public byte[] writeByteArray() {
        return writeString().getBytes(StandardCharsets.ISO_8859_1);
    }
}
