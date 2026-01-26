package org.jaudiotagger.tag.lyrics3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ListIterator;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.AbstractDataType;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public abstract class AbstractLyrics3v2FieldFrameBody extends AbstractTagFrameBody {
    public AbstractLyrics3v2FieldFrameBody() {
    }

    public AbstractLyrics3v2FieldFrameBody(AbstractLyrics3v2FieldFrameBody abstractLyrics3v2FieldFrameBody) {
        super(abstractLyrics3v2FieldFrameBody);
    }

    protected int readHeader(RandomAccessFile randomAccessFile) throws IOException, NumberFormatException, InvalidTagException {
        byte[] bArr = new byte[5];
        randomAccessFile.read(bArr, 0, 5);
        int i = Integer.parseInt(new String(bArr, 0, 5));
        if (i != 0 || TagOptionSingleton.getInstance().isLyrics3KeepEmptyFieldIfRead()) {
            return i;
        }
        throw new InvalidTagException("Lyircs3v2 Field has size of zero.");
    }

    protected void writeHeader(RandomAccessFile randomAccessFile, int i) throws IOException {
        byte[] bArr = new byte[5];
        String string = Integer.toString(getSize());
        for (int i2 = 0; i2 < 5 - string.length(); i2++) {
            bArr[i2] = 48;
        }
        int length = 5 - string.length();
        for (int i3 = 0; i3 < string.length(); i3++) {
            bArr[i3 + length] = (byte) string.charAt(i3);
        }
        randomAccessFile.write(bArr);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidTagException {
        int size = getSize();
        byte[] bArr = new byte[size];
        byteBuffer.get(bArr);
        ListIterator<AbstractDataType> listIterator = this.objectList.listIterator();
        int size2 = 0;
        while (listIterator.hasNext()) {
            if (size2 > size - 1) {
                throw new InvalidTagException("Invalid size for Frame Body");
            }
            AbstractDataType next = listIterator.next();
            next.readByteArray(bArr, size2);
            size2 += next.getSize();
        }
    }

    public void write(RandomAccessFile randomAccessFile) throws IOException {
        ListIterator<AbstractDataType> listIterator = this.objectList.listIterator();
        while (listIterator.hasNext()) {
            randomAccessFile.write(listIterator.next().writeByteArray());
        }
    }
}
