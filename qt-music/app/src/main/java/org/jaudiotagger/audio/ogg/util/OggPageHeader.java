package org.jaudiotagger.audio.ogg.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

/* loaded from: classes3.dex */
public class OggPageHeader {
    public static final int FIELD_ABSOLUTE_GRANULE_LENGTH = 8;
    public static final int FIELD_ABSOLUTE_GRANULE_POS = 6;
    public static final int FIELD_CAPTURE_PATTERN_LENGTH = 4;
    public static final int FIELD_CAPTURE_PATTERN_POS = 0;
    public static final int FIELD_HEADER_TYPE_FLAG_LENGTH = 1;
    public static final int FIELD_HEADER_TYPE_FLAG_POS = 5;
    public static final int FIELD_PAGE_CHECKSUM_LENGTH = 4;
    public static final int FIELD_PAGE_CHECKSUM_POS = 22;
    public static final int FIELD_PAGE_SEGMENTS_LENGTH = 1;
    public static final int FIELD_PAGE_SEGMENTS_POS = 26;
    public static final int FIELD_PAGE_SEQUENCE_NO_LENGTH = 4;
    public static final int FIELD_PAGE_SEQUENCE_NO_POS = 18;
    public static final int FIELD_SEGMENT_TABLE_POS = 27;
    public static final int FIELD_STREAM_SERIAL_NO_LENGTH = 4;
    public static final int FIELD_STREAM_SERIAL_NO_POS = 14;
    public static final int FIELD_STREAM_STRUCTURE_VERSION_LENGTH = 1;
    public static final int FIELD_STREAM_STRUCTURE_VERSION_POS = 4;
    public static final int MAXIMUM_NO_OF_SEGMENT_SIZE = 255;
    public static final int MAXIMUM_PAGE_DATA_SIZE = 65025;
    public static final int MAXIMUM_PAGE_HEADER_SIZE = 282;
    public static final int MAXIMUM_PAGE_SIZE = 65307;
    public static final int MAXIMUM_SEGMENT_SIZE = 255;
    public static final int OGG_PAGE_HEADER_FIXED_LENGTH = 27;
    private double absoluteGranulePosition;
    private int checksum;
    private byte headerTypeFlag;
    private boolean isValid;
    private boolean lastPacketIncomplete;
    private int pageSequenceNumber;
    private byte[] rawHeaderData;
    private byte[] segmentTable;
    private int streamSerialNumber;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg.atom");
    public static final byte[] CAPTURE_PATTERN = {79, 103, 103, 83};
    private int pageLength = 0;
    private List<PacketStartAndLength> packetList = new ArrayList();
    private long startByte = 0;

    private int u(int i) {
        return i & 255;
    }

    public static OggPageHeader read(ByteBuffer byteBuffer) throws CannotReadException, IOException {
        int iPosition = byteBuffer.position();
        logger.fine("Trying to read OggPage at:" + iPosition);
        byte[] bArr = CAPTURE_PATTERN;
        byte[] bArr2 = new byte[bArr.length];
        byteBuffer.get(bArr2);
        if (!Arrays.equals(bArr2, bArr)) {
            throw new CannotReadException(ErrorMessage.OGG_HEADER_CANNOT_BE_FOUND.getMsg(new String(bArr2)));
        }
        byteBuffer.position(iPosition + 26);
        int i = byteBuffer.get() & 255;
        byteBuffer.position(iPosition);
        byte[] bArr3 = new byte[i + 27];
        byteBuffer.get(bArr3);
        return new OggPageHeader(bArr3);
    }

    public static OggPageHeader read(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        long filePointer = randomAccessFile.getFilePointer();
        logger.fine("Trying to read OggPage at:" + filePointer);
        byte[] bArr = CAPTURE_PATTERN;
        byte[] bArr2 = new byte[bArr.length];
        randomAccessFile.read(bArr2);
        if (!Arrays.equals(bArr2, bArr)) {
            randomAccessFile.seek(filePointer);
            if (AbstractID3v2Tag.isId3Tag(randomAccessFile)) {
                logger.warning(ErrorMessage.OGG_CONTAINS_ID3TAG.getMsg(Long.valueOf(randomAccessFile.getFilePointer() - filePointer)));
                randomAccessFile.read(bArr2);
                if (Arrays.equals(bArr2, bArr)) {
                    filePointer = randomAccessFile.getFilePointer() - bArr.length;
                }
            } else {
                throw new CannotReadException(ErrorMessage.OGG_HEADER_CANNOT_BE_FOUND.getMsg(new String(bArr2)));
            }
        }
        randomAccessFile.seek(26 + filePointer);
        int i = randomAccessFile.readByte() & 255;
        randomAccessFile.seek(filePointer);
        byte[] bArr3 = new byte[i + 27];
        randomAccessFile.read(bArr3);
        OggPageHeader oggPageHeader = new OggPageHeader(bArr3);
        oggPageHeader.setStartByte(filePointer);
        return oggPageHeader;
    }

    public OggPageHeader(byte[] bArr) {
        this.isValid = false;
        this.lastPacketIncomplete = false;
        this.rawHeaderData = bArr;
        byte b = bArr[4];
        this.headerTypeFlag = bArr[5];
        if (b == 0) {
            this.absoluteGranulePosition = 0.0d;
            for (int i = 0; i < 8; i++) {
                this.absoluteGranulePosition += u(bArr[i + 6]) * Math.pow(2.0d, i * 8);
            }
            this.streamSerialNumber = Utils.getIntLE(bArr, 14, 17);
            this.pageSequenceNumber = Utils.getIntLE(bArr, 18, 21);
            this.checksum = Utils.getIntLE(bArr, 22, 25);
            u(bArr[26]);
            this.segmentTable = new byte[bArr.length - 27];
            Integer numValueOf = null;
            int i2 = 0;
            int iIntValue = 0;
            while (true) {
                byte[] bArr2 = this.segmentTable;
                if (i2 >= bArr2.length) {
                    break;
                }
                byte b2 = bArr[i2 + 27];
                bArr2[i2] = b2;
                numValueOf = Integer.valueOf(u(b2));
                this.pageLength += numValueOf.intValue();
                iIntValue += numValueOf.intValue();
                if (numValueOf.intValue() < 255) {
                    this.packetList.add(new PacketStartAndLength(this.pageLength - iIntValue, iIntValue));
                    iIntValue = 0;
                }
                i2++;
            }
            if (numValueOf != null && numValueOf.intValue() == 255) {
                this.packetList.add(new PacketStartAndLength(this.pageLength - iIntValue, iIntValue));
                this.lastPacketIncomplete = true;
            }
            this.isValid = true;
        }
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config("Constructed OggPage:" + toString());
        }
    }

    public boolean isLastPacketIncomplete() {
        return this.lastPacketIncomplete;
    }

    public double getAbsoluteGranulePosition() {
        logger.fine("Number Of Samples: " + this.absoluteGranulePosition);
        return this.absoluteGranulePosition;
    }

    public int getCheckSum() {
        return this.checksum;
    }

    public byte getHeaderType() {
        return this.headerTypeFlag;
    }

    public int getPageLength() {
        logger.finer("This page length: " + this.pageLength);
        return this.pageLength;
    }

    public int getPageSequence() {
        return this.pageSequenceNumber;
    }

    public int getSerialNumber() {
        return this.streamSerialNumber;
    }

    public byte[] getSegmentTable() {
        return this.segmentTable;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public List<PacketStartAndLength> getPacketList() {
        return this.packetList;
    }

    public byte[] getRawHeaderData() {
        return this.rawHeaderData;
    }

    public String toString() {
        String str = "Ogg Page Header:isValid:" + this.isValid + ":type:" + ((int) this.headerTypeFlag) + ":oggPageHeaderLength:" + this.rawHeaderData.length + ":length:" + this.pageLength + ":seqNo:" + getPageSequence() + ":packetIncomplete:" + isLastPacketIncomplete() + ":serNum:" + getSerialNumber();
        Iterator<PacketStartAndLength> it = getPacketList().iterator();
        while (it.hasNext()) {
            str = str + it.next().toString();
        }
        return str;
    }

    public long getStartByte() {
        return this.startByte;
    }

    public void setStartByte(long j) {
        this.startByte = j;
    }

    public static class PacketStartAndLength {
        private Integer length;
        private Integer startPosition;

        public PacketStartAndLength(int i, int i2) {
            this.startPosition = 0;
            this.length = 0;
            this.startPosition = Integer.valueOf(i);
            this.length = Integer.valueOf(i2);
        }

        public int getStartPosition() {
            return this.startPosition.intValue();
        }

        public void setStartPosition(int i) {
            this.startPosition = Integer.valueOf(i);
        }

        public int getLength() {
            return this.length.intValue();
        }

        public void setLength(int i) {
            this.length = Integer.valueOf(i);
        }

        public String toString() {
            return "NextPkt(start:" + this.startPosition + ":length:" + this.length + "),";
        }
    }

    public enum HeaderTypeFlag {
        FRESH_PACKET((byte) 0),
        CONTINUED_PACKET((byte) 1),
        START_OF_BITSTREAM((byte) 2),
        END_OF_BITSTREAM((byte) 4);

        byte fileValue;

        HeaderTypeFlag(byte b) {
            this.fileValue = b;
        }

        public byte getFileValue() {
            return this.fileValue;
        }
    }
}
