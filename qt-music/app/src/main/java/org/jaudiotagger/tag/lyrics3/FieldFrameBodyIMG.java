package org.jaudiotagger.tag.lyrics3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.Lyrics3Image;

/* loaded from: classes3.dex */
public class FieldFrameBodyIMG extends AbstractLyrics3v2FieldFrameBody {
    private ArrayList<Lyrics3Image> images;

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
    }

    public FieldFrameBodyIMG() {
        this.images = new ArrayList<>();
    }

    public FieldFrameBodyIMG(FieldFrameBodyIMG fieldFrameBodyIMG) {
        super(fieldFrameBodyIMG);
        this.images = new ArrayList<>();
        for (int i = 0; i < fieldFrameBodyIMG.images.size(); i++) {
            this.images.add(new Lyrics3Image(fieldFrameBodyIMG.images.get(i)));
        }
    }

    public FieldFrameBodyIMG(String str) {
        this.images = new ArrayList<>();
        readString(str);
    }

    public FieldFrameBodyIMG(Lyrics3Image lyrics3Image) {
        ArrayList<Lyrics3Image> arrayList = new ArrayList<>();
        this.images = arrayList;
        arrayList.add(lyrics3Image);
    }

    public FieldFrameBodyIMG(ByteBuffer byteBuffer) throws NumberFormatException, InvalidTagException {
        this.images = new ArrayList<>();
        read(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_IMAGE;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        Iterator<Lyrics3Image> it = this.images.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getSize() + 2;
        }
        return size - 2;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean isSubsetOf(Object obj) {
        if (!(obj instanceof FieldFrameBodyIMG)) {
            return false;
        }
        ArrayList<Lyrics3Image> arrayList = ((FieldFrameBodyIMG) obj).images;
        Iterator<Lyrics3Image> it = this.images.iterator();
        while (it.hasNext()) {
            if (!arrayList.contains(it.next())) {
                return false;
            }
        }
        return super.isSubsetOf(obj);
    }

    public void setValue(String str) {
        readString(str);
    }

    public String getValue() {
        return writeString();
    }

    public void addImage(Lyrics3Image lyrics3Image) {
        this.images.add(lyrics3Image);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof FieldFrameBodyIMG) && this.images.equals(((FieldFrameBodyIMG) obj).images) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public Iterator<Lyrics3Image> iterator() {
        return this.images.iterator();
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
        Iterator<Lyrics3Image> it = this.images.iterator();
        while (it.hasNext()) {
            str = str + it.next().toString() + " ; ";
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
        this.images = new ArrayList<>();
        int length = 0;
        while (iIndexOf >= 0) {
            String strSubstring = str.substring(length, iIndexOf);
            Lyrics3Image lyrics3Image = new Lyrics3Image("Image", this);
            lyrics3Image.setFilename(strSubstring);
            this.images.add(lyrics3Image);
            length = Lyrics3v2Fields.CRLF.length() + iIndexOf;
            iIndexOf = str.indexOf(Lyrics3v2Fields.CRLF, length);
        }
        if (length < str.length()) {
            String strSubstring2 = str.substring(length);
            Lyrics3Image lyrics3Image2 = new Lyrics3Image("Image", this);
            lyrics3Image2.setFilename(strSubstring2);
            this.images.add(lyrics3Image2);
        }
    }

    private String writeString() {
        Iterator<Lyrics3Image> it = this.images.iterator();
        String str = "";
        while (it.hasNext()) {
            str = str + it.next().writeString() + Lyrics3v2Fields.CRLF;
        }
        return str.length() > 2 ? str.substring(0, str.length() - 2) : str;
    }
}
