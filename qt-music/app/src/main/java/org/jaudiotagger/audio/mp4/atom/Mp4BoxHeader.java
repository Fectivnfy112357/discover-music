package org.jaudiotagger.audio.mp4.atom;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.InvalidBoxHeaderException;
import org.jaudiotagger.audio.exceptions.NullBoxIdException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class Mp4BoxHeader {
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final int DATA_64BITLENGTH = 8;
    public static final int HEADER_LENGTH = 8;
    public static final int IDENTIFIER_LENGTH = 4;
    public static final int IDENTIFIER_POS = 4;
    public static final int OFFSET_LENGTH = 4;
    public static final int OFFSET_POS = 0;
    public static final int REALDATA_64BITLENGTH = 16;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.mp4.atom");
    protected ByteBuffer dataBuffer;
    private long filePos;
    private String id;
    protected int length;

    public Mp4BoxHeader() {
    }

    public Mp4BoxHeader(String str) {
        if (str.length() != 4) {
            throw new RuntimeException("Invalid length:atom idenifier should always be 4 characters long");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        this.dataBuffer = byteBufferAllocate;
        try {
            this.id = str;
            byteBufferAllocate.put(4, str.getBytes("ISO-8859-1")[0]);
            this.dataBuffer.put(5, str.getBytes("ISO-8859-1")[1]);
            this.dataBuffer.put(6, str.getBytes("ISO-8859-1")[2]);
            this.dataBuffer.put(7, str.getBytes("ISO-8859-1")[3]);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Mp4BoxHeader(ByteBuffer byteBuffer) {
        update(byteBuffer);
    }

    public void update(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[8];
        byteBuffer.get(bArr);
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.dataBuffer = byteBufferWrap;
        byteBufferWrap.order(ByteOrder.BIG_ENDIAN);
        this.length = this.dataBuffer.getInt();
        this.id = Utils.readFourBytesAsChars(this.dataBuffer);
        logger.finest("Mp4BoxHeader id:" + this.id + ":length:" + this.length);
        if (this.id.equals("\u0000\u0000\u0000\u0000")) {
            throw new NullBoxIdException(ErrorMessage.MP4_UNABLE_TO_FIND_NEXT_ATOM_BECAUSE_IDENTIFIER_IS_INVALID.getMsg(this.id));
        }
        int i = this.length;
        if (i < 8 && i != 1) {
            throw new InvalidBoxHeaderException(ErrorMessage.MP4_UNABLE_TO_FIND_NEXT_ATOM_BECAUSE_IDENTIFIER_IS_INVALID.getMsg(this.id, Integer.valueOf(this.length)));
        }
    }

    public String getId() {
        return this.id;
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int i) {
        byte[] sizeBEInt32 = Utils.getSizeBEInt32(i);
        this.dataBuffer.put(0, sizeBEInt32[0]);
        this.dataBuffer.put(1, sizeBEInt32[1]);
        this.dataBuffer.put(2, sizeBEInt32[2]);
        this.dataBuffer.put(3, sizeBEInt32[3]);
        this.length = i;
    }

    public void setId(int i) {
        byte[] sizeBEInt32 = Utils.getSizeBEInt32(i);
        this.dataBuffer.put(5, sizeBEInt32[0]);
        this.dataBuffer.put(6, sizeBEInt32[1]);
        this.dataBuffer.put(7, sizeBEInt32[2]);
        this.dataBuffer.put(8, sizeBEInt32[3]);
        this.length = i;
    }

    public ByteBuffer getHeaderData() {
        this.dataBuffer.rewind();
        return this.dataBuffer;
    }

    public int getDataLength() {
        return this.length - 8;
    }

    public String toString() {
        return "Box " + this.id + ":length" + this.length + ":filepos:" + this.filePos;
    }

    public Charset getEncoding() {
        return StandardCharsets.UTF_8;
    }

    public static Mp4BoxHeader seekWithinLevel(SeekableByteChannel seekableByteChannel, String str) throws IOException {
        logger.finer("Started searching for:" + str + " in file at:" + seekableByteChannel.position());
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        if (seekableByteChannel.read(byteBufferAllocate) != 8) {
            return null;
        }
        byteBufferAllocate.rewind();
        mp4BoxHeader.update(byteBufferAllocate);
        while (!mp4BoxHeader.getId().equals(str)) {
            logger.finer("Found:" + mp4BoxHeader.getId() + " Still searching for:" + str + " in file at:" + seekableByteChannel.position());
            if (mp4BoxHeader.getLength() == 1) {
                ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(8);
                byteBufferAllocate2.order(ByteOrder.BIG_ENDIAN);
                if (seekableByteChannel.read(byteBufferAllocate2) != 8) {
                    return null;
                }
                byteBufferAllocate2.rewind();
                long j = byteBufferAllocate2.getLong();
                if (j < 8) {
                    return null;
                }
                seekableByteChannel.position((seekableByteChannel.position() + j) - 16);
                logger.severe("Skipped 64bit data length, now at:" + seekableByteChannel.position());
            } else {
                if (mp4BoxHeader.getLength() < 8) {
                    return null;
                }
                seekableByteChannel.position(seekableByteChannel.position() + mp4BoxHeader.getDataLength());
            }
            if (seekableByteChannel.position() > seekableByteChannel.size()) {
                return null;
            }
            byteBufferAllocate.rewind();
            int i = seekableByteChannel.read(byteBufferAllocate);
            logger.finer("Header Bytes Read:" + i);
            byteBufferAllocate.rewind();
            if (i != 8) {
                return null;
            }
            mp4BoxHeader.update(byteBufferAllocate);
        }
        return mp4BoxHeader;
    }

    public static Mp4BoxHeader seekWithinLevel(ByteBuffer byteBuffer, String str) throws IOException {
        logger.finer("Started searching for:" + str + " in bytebuffer at" + byteBuffer.position());
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader();
        if (byteBuffer.remaining() < 8) {
            return null;
        }
        mp4BoxHeader.update(byteBuffer);
        while (!mp4BoxHeader.getId().equals(str)) {
            logger.finer("Found:" + mp4BoxHeader.getId() + " Still searching for:" + str + " in bytebuffer at" + byteBuffer.position());
            if (mp4BoxHeader.getLength() < 8 || byteBuffer.remaining() < mp4BoxHeader.getLength() - 8) {
                return null;
            }
            byteBuffer.position(byteBuffer.position() + (mp4BoxHeader.getLength() - 8));
            if (byteBuffer.remaining() < 8) {
                return null;
            }
            mp4BoxHeader.update(byteBuffer);
        }
        logger.finer("Found:" + str + " in bytebuffer at" + byteBuffer.position());
        return mp4BoxHeader;
    }

    public long getFilePos() {
        return this.filePos;
    }

    public long getFileEndPos() {
        return this.filePos + this.length;
    }

    public void setFilePos(long j) {
        this.filePos = j;
    }
}
