package org.jaudiotagger.audio.ogg;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.ogg.OggVorbisTagReader;
import org.jaudiotagger.audio.ogg.util.OggCRCFactory;
import org.jaudiotagger.audio.ogg.util.OggPageHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;

/* loaded from: classes3.dex */
public class OggVorbisTagWriter {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg");
    private OggVorbisCommentTagCreator tc = new OggVorbisCommentTagCreator();
    private OggVorbisTagReader reader = new OggVorbisTagReader();

    public void delete(RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        try {
            this.reader.read(randomAccessFile);
            VorbisCommentTag vorbisCommentTagCreateNewTag = VorbisCommentTag.createNewTag();
            randomAccessFile.seek(0L);
            write(vorbisCommentTagCreateNewTag, randomAccessFile, randomAccessFile2);
        } catch (CannotReadException unused) {
            write(VorbisCommentTag.createNewTag(), randomAccessFile, randomAccessFile2);
        }
    }

    public void write(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        logger.config("Starting to write file:");
        logger.fine("Read 1st Page:identificationHeader:");
        OggPageHeader oggPageHeader = OggPageHeader.read(randomAccessFile);
        randomAccessFile.seek(oggPageHeader.getStartByte());
        randomAccessFile2.getChannel().transferFrom(randomAccessFile.getChannel(), 0L, oggPageHeader.getPageLength() + 27 + oggPageHeader.getSegmentTable().length);
        randomAccessFile2.skipBytes(oggPageHeader.getPageLength() + 27 + oggPageHeader.getSegmentTable().length);
        logger.fine("Written identificationHeader:");
        OggPageHeader oggPageHeader2 = OggPageHeader.read(randomAccessFile);
        long filePointer = randomAccessFile.getFilePointer();
        logger.fine("Read 2nd Page:comment and setup and possibly audio:Header finishes at file position:" + filePointer);
        randomAccessFile.seek(0L);
        OggVorbisTagReader.OggVorbisHeaderSizes oggVorbisHeaderSizes = this.reader.readOggVorbisHeaderSizes(randomAccessFile);
        ByteBuffer byteBufferConvert = this.tc.convert(tag);
        int iCapacity = byteBufferConvert.capacity();
        int setupHeaderSize = oggVorbisHeaderSizes.getSetupHeaderSize() + iCapacity + oggVorbisHeaderSizes.getExtraPacketDataSize();
        logger.fine("Old 2nd Page no of packets: " + oggPageHeader2.getPacketList().size());
        logger.fine("Old 2nd Page size: " + oggPageHeader2.getPageLength());
        logger.fine("Old last packet incomplete: " + oggPageHeader2.isLastPacketIncomplete());
        logger.fine("Setup Header Size: " + oggVorbisHeaderSizes.getSetupHeaderSize());
        logger.fine("Extra Packets: " + oggVorbisHeaderSizes.getExtraPacketList().size());
        logger.fine("Extra Packet Data Size: " + oggVorbisHeaderSizes.getExtraPacketDataSize());
        logger.fine("Old comment: " + oggVorbisHeaderSizes.getCommentHeaderSize());
        logger.fine("New comment: " + iCapacity);
        logger.fine("New Page Data Size: " + setupHeaderSize);
        if (isCommentAndSetupHeaderFitsOnASinglePage(iCapacity, oggVorbisHeaderSizes.getSetupHeaderSize(), oggVorbisHeaderSizes.getExtraPacketList())) {
            if (oggPageHeader2.getPageLength() < 65025 && ((oggPageHeader2.getPacketList().size() == 2 && !oggPageHeader2.isLastPacketIncomplete()) || oggPageHeader2.getPacketList().size() > 2)) {
                logger.fine("Header and Setup remain on single page:");
                replaceSecondPageOnly(oggVorbisHeaderSizes, iCapacity, setupHeaderSize, oggPageHeader2, byteBufferConvert, filePointer, randomAccessFile, randomAccessFile2);
                return;
            } else {
                logger.fine("Header and Setup now on single page:");
                replaceSecondPageAndRenumberPageSeqs(oggVorbisHeaderSizes, iCapacity, setupHeaderSize, oggPageHeader2, byteBufferConvert, randomAccessFile, randomAccessFile2);
                return;
            }
        }
        logger.fine("Header and Setup with shift audio:");
        replacePagesAndRenumberPageSeqs(oggVorbisHeaderSizes, iCapacity, oggPageHeader2, byteBufferConvert, randomAccessFile, randomAccessFile2);
    }

    private void calculateChecksumOverPage(ByteBuffer byteBuffer) {
        byteBuffer.putInt(22, 0);
        byte[] bArrComputeCRC = OggCRCFactory.computeCRC(byteBuffer.array());
        for (int i = 0; i < bArrComputeCRC.length; i++) {
            byteBuffer.put(i + 22, bArrComputeCRC[i]);
        }
        byteBuffer.rewind();
    }

    private ByteBuffer startCreateBasicSecondPage(OggVorbisTagReader.OggVorbisHeaderSizes oggVorbisHeaderSizes, int i, int i2, OggPageHeader oggPageHeader, ByteBuffer byteBuffer) throws IOException {
        logger.fine("WriteOgg Type 1");
        byte[] bArrCreateSegmentTable = createSegmentTable(i, oggVorbisHeaderSizes.getSetupHeaderSize(), oggVorbisHeaderSizes.getExtraPacketList());
        int length = bArrCreateSegmentTable.length + 27;
        logger.fine("New second page header length:" + length);
        logger.fine("No of segments:" + bArrCreateSegmentTable.length);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i2 + length);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(oggPageHeader.getRawHeaderData(), 0, 26);
        byteBufferAllocate.put((byte) bArrCreateSegmentTable.length);
        for (byte b : bArrCreateSegmentTable) {
            byteBufferAllocate.put(b);
        }
        byteBufferAllocate.put(byteBuffer);
        return byteBufferAllocate;
    }

    private void replaceSecondPageOnly(OggVorbisTagReader.OggVorbisHeaderSizes oggVorbisHeaderSizes, int i, int i2, OggPageHeader oggPageHeader, ByteBuffer byteBuffer, long j, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException {
        logger.fine("WriteOgg Type 1");
        ByteBuffer byteBufferStartCreateBasicSecondPage = startCreateBasicSecondPage(oggVorbisHeaderSizes, i, i2, oggPageHeader, byteBuffer);
        randomAccessFile.seek(j);
        randomAccessFile.skipBytes(oggVorbisHeaderSizes.getCommentHeaderSize());
        randomAccessFile.getChannel().read(byteBufferStartCreateBasicSecondPage);
        calculateChecksumOverPage(byteBufferStartCreateBasicSecondPage);
        randomAccessFile2.getChannel().write(byteBufferStartCreateBasicSecondPage);
        randomAccessFile2.getChannel().transferFrom(randomAccessFile.getChannel(), randomAccessFile2.getFilePointer(), randomAccessFile.length() - randomAccessFile.getFilePointer());
    }

    private void replaceSecondPageAndRenumberPageSeqs(OggVorbisTagReader.OggVorbisHeaderSizes oggVorbisHeaderSizes, int i, int i2, OggPageHeader oggPageHeader, ByteBuffer byteBuffer, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        logger.fine("WriteOgg Type 2");
        ByteBuffer byteBufferStartCreateBasicSecondPage = startCreateBasicSecondPage(oggVorbisHeaderSizes, i, i2, oggPageHeader, byteBuffer);
        int pageSequence = oggPageHeader.getPageSequence();
        byte[] bArrConvertToVorbisSetupHeaderPacketAndAdditionalPackets = this.reader.convertToVorbisSetupHeaderPacketAndAdditionalPackets(oggVorbisHeaderSizes.getSetupHeaderStartPosition(), randomAccessFile);
        logger.finest(bArrConvertToVorbisSetupHeaderPacketAndAdditionalPackets.length + ":" + byteBufferStartCreateBasicSecondPage.position() + ":" + byteBufferStartCreateBasicSecondPage.capacity());
        byteBufferStartCreateBasicSecondPage.put(bArrConvertToVorbisSetupHeaderPacketAndAdditionalPackets);
        calculateChecksumOverPage(byteBufferStartCreateBasicSecondPage);
        randomAccessFile2.getChannel().write(byteBufferStartCreateBasicSecondPage);
        writeRemainingPages(pageSequence, randomAccessFile, randomAccessFile2);
    }

    private void replacePagesAndRenumberPageSeqs(OggVorbisTagReader.OggVorbisHeaderSizes oggVorbisHeaderSizes, int i, OggPageHeader oggPageHeader, ByteBuffer byteBuffer, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        int i2;
        int i3;
        int pageSequence = oggPageHeader.getPageSequence();
        int i4 = i / 65025;
        logger.config("Comment requires:" + i4 + " complete pages");
        int i5 = 26;
        if (i4 > 0) {
            int i6 = 0;
            i3 = 0;
            while (i6 < i4) {
                byte[] bArrCreateSegments = createSegments(65025, false);
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArrCreateSegments.length + 65052);
                byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
                byteBufferAllocate.put(oggPageHeader.getRawHeaderData(), 0, i5);
                byteBufferAllocate.put((byte) bArrCreateSegments.length);
                for (byte b : bArrCreateSegments) {
                    byteBufferAllocate.put(b);
                }
                ByteBuffer byteBufferSlice = byteBuffer.slice();
                byteBufferSlice.limit(65025);
                byteBufferAllocate.put(byteBufferSlice);
                byteBufferAllocate.putInt(18, pageSequence);
                pageSequence++;
                if (i6 != 0) {
                    byteBufferAllocate.put(5, OggPageHeader.HeaderTypeFlag.CONTINUED_PACKET.getFileValue());
                }
                calculateChecksumOverPage(byteBufferAllocate);
                randomAccessFile2.getChannel().write(byteBufferAllocate);
                i3 += 65025;
                byteBuffer.position(i3);
                i6++;
                i5 = 26;
            }
            i2 = pageSequence;
        } else {
            i2 = pageSequence;
            i3 = 0;
        }
        int i7 = i % 65025;
        logger.fine("Last comment packet size:" + i7);
        if (!isCommentAndSetupHeaderFitsOnASinglePage(i7, oggVorbisHeaderSizes.getSetupHeaderSize(), oggVorbisHeaderSizes.getExtraPacketList())) {
            logger.fine("WriteOgg Type 3");
            byte[] bArrCreateSegments2 = createSegments(i7, true);
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i7 + bArrCreateSegments2.length + 27);
            byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate2.put(oggPageHeader.getRawHeaderData(), 0, 26);
            byteBufferAllocate2.put((byte) bArrCreateSegments2.length);
            for (byte b2 : bArrCreateSegments2) {
                byteBufferAllocate2.put(b2);
            }
            byteBuffer.position(i3);
            byteBufferAllocate2.put(byteBuffer.slice());
            byteBufferAllocate2.putInt(18, i2);
            if (i4 > 0) {
                byteBufferAllocate2.put(5, OggPageHeader.HeaderTypeFlag.CONTINUED_PACKET.getFileValue());
            }
            logger.fine("Writing Last Comment Page " + i2 + " to file");
            i2++;
            calculateChecksumOverPage(byteBufferAllocate2);
            randomAccessFile2.getChannel().write(byteBufferAllocate2);
            byte[] bArrCreateSegmentTable = createSegmentTable(oggVorbisHeaderSizes.getSetupHeaderSize(), oggVorbisHeaderSizes.getExtraPacketList());
            int length = bArrCreateSegmentTable.length + 27;
            byte[] bArrConvertToVorbisSetupHeaderPacketAndAdditionalPackets = this.reader.convertToVorbisSetupHeaderPacketAndAdditionalPackets(oggVorbisHeaderSizes.getSetupHeaderStartPosition(), randomAccessFile);
            ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(bArrConvertToVorbisSetupHeaderPacketAndAdditionalPackets.length + length);
            byteBufferAllocate3.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate3.put(oggPageHeader.getRawHeaderData(), 0, 26);
            byteBufferAllocate3.put((byte) bArrCreateSegmentTable.length);
            for (byte b3 : bArrCreateSegmentTable) {
                byteBufferAllocate3.put(b3);
            }
            byteBufferAllocate3.put(bArrConvertToVorbisSetupHeaderPacketAndAdditionalPackets);
            byteBufferAllocate3.putInt(18, i2);
            logger.fine("Writing Setup Header and packets Page " + i2 + " to file");
            calculateChecksumOverPage(byteBufferAllocate3);
            randomAccessFile2.getChannel().write(byteBufferAllocate3);
        } else {
            logger.fine("WriteOgg Type 4");
            int extraPacketDataSize = oggVorbisHeaderSizes.getExtraPacketDataSize() + oggVorbisHeaderSizes.getSetupHeaderSize() + i7;
            byteBuffer.position(i3);
            ByteBuffer byteBufferStartCreateBasicSecondPage = startCreateBasicSecondPage(oggVorbisHeaderSizes, i7, extraPacketDataSize, oggPageHeader, byteBuffer.slice());
            randomAccessFile.seek(oggVorbisHeaderSizes.getSetupHeaderStartPosition());
            byteBufferStartCreateBasicSecondPage.put(this.reader.convertToVorbisSetupHeaderPacketAndAdditionalPackets(oggVorbisHeaderSizes.getSetupHeaderStartPosition(), randomAccessFile));
            byteBufferStartCreateBasicSecondPage.putInt(18, i2);
            byteBufferStartCreateBasicSecondPage.put(5, OggPageHeader.HeaderTypeFlag.CONTINUED_PACKET.getFileValue());
            calculateChecksumOverPage(byteBufferStartCreateBasicSecondPage);
            randomAccessFile2.getChannel().write(byteBufferStartCreateBasicSecondPage);
        }
        writeRemainingPages(i2, randomAccessFile, randomAccessFile2);
    }

    public void writeRemainingPages(int i, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        long jRemaining;
        long filePointer = randomAccessFile.getFilePointer();
        long filePointer2 = randomAccessFile2.getFilePointer();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) (randomAccessFile.length() - randomAccessFile.getFilePointer()));
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate((int) (randomAccessFile.length() - randomAccessFile.getFilePointer()));
        randomAccessFile.getChannel().read(byteBufferAllocate);
        byteBufferAllocate.rewind();
        while (byteBufferAllocate.hasRemaining()) {
            try {
                OggPageHeader oggPageHeader = OggPageHeader.read(byteBufferAllocate);
                ByteBuffer byteBufferAllocate3 = ByteBuffer.allocate(oggPageHeader.getRawHeaderData().length + oggPageHeader.getPageLength());
                byteBufferAllocate3.order(ByteOrder.LITTLE_ENDIAN);
                byteBufferAllocate3.put(oggPageHeader.getRawHeaderData());
                ByteBuffer byteBufferSlice = byteBufferAllocate.slice();
                byteBufferSlice.limit(oggPageHeader.getPageLength());
                byteBufferAllocate3.put(byteBufferSlice);
                i++;
                byteBufferAllocate3.putInt(18, i);
                calculateChecksumOverPage(byteBufferAllocate3);
                byteBufferAllocate.position(byteBufferAllocate.position() + oggPageHeader.getPageLength());
                byteBufferAllocate3.rewind();
                byteBufferAllocate2.put(byteBufferAllocate3);
            } catch (CannotReadException e) {
                byteBufferAllocate.position(byteBufferAllocate.position() - OggPageHeader.CAPTURE_PATTERN.length);
                if (Utils.readThreeBytesAsChars(byteBufferAllocate).equals(AbstractID3v1Tag.TAG)) {
                    jRemaining = byteBufferAllocate.remaining() + AbstractID3v1Tag.TAG.length();
                } else {
                    throw e;
                }
            }
        }
        jRemaining = 0;
        byteBufferAllocate2.flip();
        randomAccessFile2.getChannel().write(byteBufferAllocate2);
        if (randomAccessFile.length() - filePointer != (randomAccessFile2.length() + jRemaining) - filePointer2) {
            throw new CannotWriteException("File written counts don't match, file not written:origAudioLength:" + (randomAccessFile.length() - filePointer) + ":newAudioLength:" + ((randomAccessFile2.length() + jRemaining) - filePointer2) + ":bytesDiscarded:" + jRemaining);
        }
    }

    private byte[] createSegmentTable(int i, int i2, List<OggPageHeader.PacketStartAndLength> list) {
        byte[] bArrCreateSegments;
        logger.finest("Create SegmentTable CommentLength:" + i + ":SetupHeaderLength:" + i2);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i2 == 0) {
            return createSegments(i, false);
        }
        byte[] bArrCreateSegments2 = createSegments(i, true);
        if (list.size() > 0) {
            bArrCreateSegments = createSegments(i2, true);
        } else {
            bArrCreateSegments = createSegments(i2, false);
        }
        logger.finest("Created " + bArrCreateSegments2.length + " segments for header");
        logger.finest("Created " + bArrCreateSegments.length + " segments for setup");
        try {
            byteArrayOutputStream.write(bArrCreateSegments2);
            byteArrayOutputStream.write(bArrCreateSegments);
            if (list.size() > 0) {
                logger.finer("Creating segments for " + list.size() + " packets");
                Iterator<OggPageHeader.PacketStartAndLength> it = list.iterator();
                while (it.hasNext()) {
                    byteArrayOutputStream.write(createSegments(it.next().getLength(), false));
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create segment table:" + e.getMessage());
        }
    }

    private byte[] createSegmentTable(int i, List<OggPageHeader.PacketStartAndLength> list) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(createSegments(i, true));
            if (list.size() > 0) {
                Iterator<OggPageHeader.PacketStartAndLength> it = list.iterator();
                while (it.hasNext()) {
                    byteArrayOutputStream.write(createSegments(it.next().getLength(), false));
                }
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Unable to create segment table:" + e.getMessage());
        }
    }

    private byte[] createSegments(int i, boolean z) {
        logger.finest("Create Segments for length:" + i + ":QuitStream:" + z);
        int i2 = 0;
        if (i == 0) {
            return new byte[]{0};
        }
        int i3 = (i / 255) + ((i % 255 != 0 || z) ? 1 : 0);
        byte[] bArr = new byte[i3];
        while (true) {
            int i4 = i3 - 1;
            if (i2 < i4) {
                bArr[i2] = -1;
                i2++;
            } else {
                bArr[i4] = (byte) (i - (i2 * 255));
                return bArr;
            }
        }
    }

    private boolean isCommentAndSetupHeaderFitsOnASinglePage(int i, int i2, List<OggPageHeader.PacketStartAndLength> list) {
        int i3;
        int length;
        if (i == 0) {
            i3 = 1;
        } else {
            int i4 = i / 255;
            i3 = i4 + 1;
            if (i % 255 == 0) {
                i3 = i4 + 2;
            }
        }
        logger.finest("Require:" + i3 + " segments for comment");
        if (i2 == 0) {
            length = i3 + 1;
        } else {
            length = i3 + (i2 / 255) + 1;
            if (i2 % 255 == 0) {
                length++;
            }
        }
        logger.finest("Require:" + length + " segments for comment plus setup");
        for (OggPageHeader.PacketStartAndLength packetStartAndLength : list) {
            if (packetStartAndLength.getLength() != 0) {
                length += (packetStartAndLength.getLength() / 255) + 1;
                if (packetStartAndLength.getLength() % 255 == 0) {
                }
            }
            length++;
        }
        logger.finest("Total No Of Segment If New Comment And Header Put On One Page:" + length);
        return length <= 255;
    }
}
