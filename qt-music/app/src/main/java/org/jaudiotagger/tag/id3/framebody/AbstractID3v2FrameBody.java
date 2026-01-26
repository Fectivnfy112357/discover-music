package org.jaudiotagger.tag.id3.framebody;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.AbstractDataType;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public abstract class AbstractID3v2FrameBody extends AbstractTagFrameBody {
    protected static final String TYPE_BODY = "body";
    private int size;

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public abstract String getIdentifier();

    protected AbstractID3v2FrameBody() {
    }

    protected AbstractID3v2FrameBody(AbstractID3v2FrameBody abstractID3v2FrameBody) {
        super(abstractID3v2FrameBody);
    }

    protected AbstractID3v2FrameBody(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        setSize(i);
        read(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }

    public void setSize() {
        this.size = 0;
        Iterator<AbstractDataType> it = this.objectList.iterator();
        while (it.hasNext()) {
            this.size += it.next().getSize();
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof AbstractID3v2FrameBody) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidTagException {
        int size = getSize();
        logger.config("Reading body for" + getIdentifier() + ":" + size);
        byte[] bArr = new byte[size];
        byteBuffer.get(bArr);
        int size2 = 0;
        for (AbstractDataType abstractDataType : this.objectList) {
            readIntoNextObject(bArr, abstractDataType, size2);
            size2 += abstractDataType.getSize();
        }
    }

    protected void readIntoNextObject(byte[] bArr, AbstractDataType abstractDataType, int i) throws InvalidTagException {
        logger.finest("offset:" + i);
        if (i > this.size) {
            logger.warning("Invalid Size for FrameBody");
            throw new InvalidFrameException("Invalid size for Frame Body");
        }
        try {
            abstractDataType.readByteArray(bArr, i);
        } catch (InvalidDataTypeException e) {
            logger.warning("Problem reading datatype within Frame Body:" + e.getMessage());
            throw e;
        }
    }

    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        logger.config("Writing frame body for" + getIdentifier() + ":Est Size:" + this.size);
        Iterator<AbstractDataType> it = this.objectList.iterator();
        while (it.hasNext()) {
            byte[] bArrWriteByteArray = it.next().writeByteArray();
            if (bArrWriteByteArray != null) {
                try {
                    byteArrayOutputStream.write(bArrWriteByteArray);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        setSize();
        logger.config("Written frame body for" + getIdentifier() + ":Real Size:" + this.size);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement("body", "");
        Iterator<AbstractDataType> it = this.objectList.iterator();
        while (it.hasNext()) {
            it.next().createStructure();
        }
        MP3File.getStructureFormatter().closeHeadingElement("body");
    }
}
