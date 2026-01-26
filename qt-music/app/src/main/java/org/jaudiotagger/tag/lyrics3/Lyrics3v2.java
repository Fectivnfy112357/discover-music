package org.jaudiotagger.tag.lyrics3;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagNotFoundException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractTag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;

/* loaded from: classes3.dex */
public class Lyrics3v2 extends AbstractLyrics3 {
    private HashMap<String, Lyrics3v2Field> fieldMap;

    private int seekSize(ByteBuffer byteBuffer) {
        return -1;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public boolean seek(ByteBuffer byteBuffer) {
        return false;
    }

    public Lyrics3v2() {
        this.fieldMap = new HashMap<>();
    }

    public Lyrics3v2(Lyrics3v2 lyrics3v2) {
        super(lyrics3v2);
        this.fieldMap = new HashMap<>();
        for (String str : lyrics3v2.fieldMap.keySet()) {
            this.fieldMap.put(str, new Lyrics3v2Field(lyrics3v2.fieldMap.get(str)));
        }
    }

    public Lyrics3v2(AbstractTag abstractTag) {
        this.fieldMap = new HashMap<>();
        if (abstractTag != null) {
            if (abstractTag instanceof Lyrics3v2) {
                throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
            }
            if (abstractTag instanceof Lyrics3v1) {
                Lyrics3v2Field lyrics3v2Field = new Lyrics3v2Field(new FieldFrameBodyLYR(((Lyrics3v1) abstractTag).getLyric()));
                this.fieldMap.put(lyrics3v2Field.getIdentifier(), lyrics3v2Field);
                return;
            }
            Iterator<List<TagField>> it = new ID3v24Tag(abstractTag).iterator();
            while (it.hasNext()) {
                try {
                    for (TagField tagField : it.next()) {
                        if (tagField instanceof AbstractID3v2Frame) {
                            AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) tagField;
                            if (Lyrics3v2Field.isLyrics3v2Field(abstractID3v2Frame)) {
                                Lyrics3v2Field lyrics3v2Field2 = new Lyrics3v2Field(abstractID3v2Frame);
                                this.fieldMap.put(lyrics3v2Field2.getIdentifier(), lyrics3v2Field2);
                            }
                        }
                    }
                } catch (TagException unused) {
                }
            }
        }
    }

    public Lyrics3v2(ByteBuffer byteBuffer) throws TagNotFoundException, IOException {
        this.fieldMap = new HashMap<>();
        try {
            read(byteBuffer);
        } catch (TagException e) {
            e.printStackTrace();
        }
    }

    public void setField(Lyrics3v2Field lyrics3v2Field) {
        this.fieldMap.put(lyrics3v2Field.getIdentifier(), lyrics3v2Field);
    }

    public Lyrics3v2Field getField(String str) {
        return this.fieldMap.get(str);
    }

    public int getFieldCount() {
        return this.fieldMap.size();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "Lyrics3v2.00";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        Iterator<Lyrics3v2Field> it = this.fieldMap.values().iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size + 11;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof Lyrics3v2) && this.fieldMap.equals(((Lyrics3v2) obj).fieldMap) && super.equals(obj);
    }

    public boolean hasField(String str) {
        return this.fieldMap.containsKey(str);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public Iterator<Lyrics3v2Field> iterator() {
        return this.fieldMap.values().iterator();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws TagException {
        if (seek(byteBuffer)) {
            int iSeekSize = seekSize(byteBuffer);
            seek(byteBuffer);
            byteBuffer.position();
            this.fieldMap = new HashMap<>();
            while (byteBuffer.position() < iSeekSize - 11) {
                try {
                    setField(new Lyrics3v2Field(byteBuffer));
                } catch (InvalidTagException unused) {
                }
            }
            return;
        }
        throw new TagNotFoundException("Lyrics3v2.00 Tag Not Found");
    }

    public void removeField(String str) {
        this.fieldMap.remove(str);
    }

    public boolean seek(RandomAccessFile randomAccessFile) throws IOException {
        long filePointer;
        byte[] bArr = new byte[11];
        randomAccessFile.seek(randomAccessFile.length() - 137);
        randomAccessFile.read(bArr, 0, 9);
        if (new String(bArr, 0, 9).equals("LYRICS200")) {
            filePointer = randomAccessFile.getFilePointer();
        } else {
            randomAccessFile.seek(randomAccessFile.length() - 9);
            randomAccessFile.read(bArr, 0, 9);
            if (!new String(bArr, 0, 9).equals("LYRICS200")) {
                return false;
            }
            filePointer = randomAccessFile.getFilePointer();
        }
        long j = filePointer - 15;
        randomAccessFile.seek(j);
        randomAccessFile.read(bArr, 0, 6);
        randomAccessFile.seek(j - Integer.parseInt(new String(bArr, 0, 6)));
        randomAccessFile.read(bArr, 0, 11);
        return new String(bArr, 0, 11).equals("LYRICSBEGIN");
    }

    public String toString() {
        Iterator<Lyrics3v2Field> it = this.fieldMap.values().iterator();
        String str = getIdentifier() + " " + getSize() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        while (it.hasNext()) {
            str = str + it.next().toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
        }
        return str;
    }

    public void updateField(String str) {
        if (str.equals(Lyrics3v2Fields.FIELD_V2_INDICATIONS)) {
            boolean zContainsKey = this.fieldMap.containsKey(Lyrics3v2Fields.FIELD_V2_LYRICS_MULTI_LINE_TEXT);
            setField(new Lyrics3v2Field(new FieldFrameBodyIND(zContainsKey, zContainsKey ? ((FieldFrameBodyLYR) this.fieldMap.get(Lyrics3v2Fields.FIELD_V2_LYRICS_MULTI_LINE_TEXT).getBody()).hasTimeStamp() : false)));
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void write(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = new byte[15];
        new ID3v1Tag();
        delete(randomAccessFile);
        randomAccessFile.seek(randomAccessFile.length());
        long filePointer = randomAccessFile.getFilePointer();
        for (int i = 0; i < "LYRICSBEGIN".length(); i++) {
            bArr[i] = (byte) "LYRICSBEGIN".charAt(i);
        }
        randomAccessFile.write(bArr, 0, "LYRICSBEGIN".length());
        updateField(Lyrics3v2Fields.FIELD_V2_INDICATIONS);
        this.fieldMap.get(Lyrics3v2Fields.FIELD_V2_INDICATIONS).write(randomAccessFile);
        for (Lyrics3v2Field lyrics3v2Field : this.fieldMap.values()) {
            String identifier = lyrics3v2Field.getIdentifier();
            boolean lyrics3SaveField = TagOptionSingleton.getInstance().getLyrics3SaveField(identifier);
            if (!identifier.equals(Lyrics3v2Fields.FIELD_V2_INDICATIONS) && lyrics3SaveField) {
                lyrics3v2Field.write(randomAccessFile);
            }
        }
        long filePointer2 = randomAccessFile.getFilePointer() - filePointer;
        getSize();
        String string = Long.toString(filePointer2);
        for (int i2 = 0; i2 < 6 - string.length(); i2++) {
            bArr[i2] = 48;
        }
        int length = 6 - string.length();
        for (int i3 = 0; i3 < string.length(); i3++) {
            bArr[i3 + length] = (byte) string.charAt(i3);
        }
        int length2 = length + string.length();
        for (int i4 = 0; i4 < "LYRICS200".length(); i4++) {
            bArr[i4 + length2] = (byte) "LYRICS200".charAt(i4);
        }
        randomAccessFile.write(bArr, 0, length2 + "LYRICS200".length());
    }
}
