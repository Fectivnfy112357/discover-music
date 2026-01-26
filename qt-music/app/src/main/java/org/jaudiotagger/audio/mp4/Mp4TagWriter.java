package org.jaudiotagger.audio.mp4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.audio.mp4.atom.Mp4FreeBox;
import org.jaudiotagger.audio.mp4.atom.Mp4HdlrBox;
import org.jaudiotagger.audio.mp4.atom.Mp4MetaBox;
import org.jaudiotagger.audio.mp4.atom.Mp4StcoBox;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.mp4.Mp4Tag;
import org.jaudiotagger.tag.mp4.Mp4TagCreator;
import org.jaudiotagger.utils.ShiftData;
import org.jaudiotagger.utils.tree.DefaultMutableTreeNode;

/* loaded from: classes3.dex */
public class Mp4TagWriter {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.mp4");
    private String loggingName;
    private Mp4TagCreator tc = new Mp4TagCreator();

    public Mp4TagWriter(String str) {
        this.loggingName = str;
    }

    private void writeMetadataSameSize(SeekableByteChannel seekableByteChannel, Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) throws IOException {
        logger.config("Writing:Option 1:Same Size");
        seekableByteChannel.position(mp4BoxHeader.getFilePos());
        seekableByteChannel.write(byteBuffer);
    }

    private void adjustSizeOfMoovHeader(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer, int i, Mp4BoxHeader mp4BoxHeader2, Mp4BoxHeader mp4BoxHeader3) {
        mp4BoxHeader.setLength(mp4BoxHeader.getLength() + i);
        if (mp4BoxHeader2 != null) {
            mp4BoxHeader2.setLength(mp4BoxHeader2.getLength() + i);
            byteBuffer.position((int) ((mp4BoxHeader2.getFilePos() - mp4BoxHeader.getFilePos()) - 8));
            byteBuffer.put(mp4BoxHeader2.getHeaderData());
        }
        if (mp4BoxHeader3 != null) {
            mp4BoxHeader3.setLength(mp4BoxHeader3.getLength() + i);
            byteBuffer.position((int) ((mp4BoxHeader3.getFilePos() - mp4BoxHeader.getFilePos()) - 8));
            byteBuffer.put(mp4BoxHeader3.getHeaderData());
        }
    }

    private void writeOldMetadataLargerThanNewMetadata(SeekableByteChannel seekableByteChannel, Mp4BoxHeader mp4BoxHeader, Mp4BoxHeader mp4BoxHeader2, Mp4BoxHeader mp4BoxHeader3, Mp4BoxHeader mp4BoxHeader4, Mp4BoxHeader mp4BoxHeader5, Mp4BoxHeader mp4BoxHeader6, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, List<Mp4StcoBox> list, int i) throws IOException {
        logger.config("Writing:Option 1:Smaller Size");
        int filePos = (int) (mp4BoxHeader4.getFilePos() - (mp4BoxHeader.getFilePos() + 8));
        int iLimit = byteBuffer2.limit();
        if (i > 0) {
            logger.config("Writing:Option 2:Smaller Size have free atom:" + mp4BoxHeader4.getLength() + ":" + iLimit);
            seekableByteChannel.position(mp4BoxHeader4.getFilePos());
            seekableByteChannel.write(byteBuffer2);
            Mp4FreeBox mp4FreeBox = new Mp4FreeBox((i + (mp4BoxHeader4.getLength() - iLimit)) - 8);
            seekableByteChannel.write(mp4FreeBox.getHeader().getHeaderData());
            seekableByteChannel.write(mp4FreeBox.getData());
        } else {
            int length = (mp4BoxHeader4.getLength() - iLimit) - 8;
            if (length > 0) {
                logger.config("Writing:Option 3:Smaller Size can create free atom");
                seekableByteChannel.position(mp4BoxHeader4.getFilePos());
                seekableByteChannel.write(byteBuffer2);
                Mp4FreeBox mp4FreeBox2 = new Mp4FreeBox(length);
                seekableByteChannel.write(mp4FreeBox2.getHeader().getHeaderData());
                seekableByteChannel.write(mp4FreeBox2.getData());
            } else {
                logger.config("Writing:Option 4:Smaller Size <=8 cannot create free atoms");
                long fileEndPos = mp4BoxHeader.getFileEndPos();
                int length2 = mp4BoxHeader4.getLength() - iLimit;
                if (mp4BoxHeader5.getFilePos() > mp4BoxHeader.getFilePos()) {
                    Iterator<Mp4StcoBox> it = list.iterator();
                    while (it.hasNext()) {
                        it.next().adjustOffsets(-length2);
                    }
                }
                adjustSizeOfMoovHeader(mp4BoxHeader, byteBuffer, -length2, mp4BoxHeader2, mp4BoxHeader3);
                seekableByteChannel.position(mp4BoxHeader.getFilePos());
                seekableByteChannel.write(mp4BoxHeader.getHeaderData());
                byteBuffer.rewind();
                byteBuffer.limit(filePos);
                seekableByteChannel.write(byteBuffer);
                seekableByteChannel.write(byteBuffer2);
                byteBuffer.limit(byteBuffer.capacity());
                byteBuffer.position(filePos + mp4BoxHeader4.getLength());
                seekableByteChannel.write(byteBuffer);
                shiftData(seekableByteChannel, fileEndPos, Math.abs(length2));
            }
        }
    }

    private void shiftData(SeekableByteChannel seekableByteChannel, long j, int i) throws IOException {
        seekableByteChannel.position(j);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) TagOptionSingleton.getInstance().getWriteChunkSize());
        while (true) {
            if (seekableByteChannel.read(byteBufferAllocate) >= 0 || byteBufferAllocate.position() != 0) {
                byteBufferAllocate.flip();
                long jPosition = seekableByteChannel.position();
                seekableByteChannel.position((jPosition - i) - byteBufferAllocate.limit());
                seekableByteChannel.write(byteBufferAllocate);
                seekableByteChannel.position(jPosition);
                byteBufferAllocate.compact();
            } else {
                long size = seekableByteChannel.size() - i;
                logger.config(this.loggingName + "-------------Setting new length to:" + size);
                seekableByteChannel.truncate(size);
                return;
            }
        }
    }

    private void writeNewMetadataLargerButCanUseFreeAtom(SeekableByteChannel seekableByteChannel, Mp4BoxHeader mp4BoxHeader, int i, ByteBuffer byteBuffer, int i2) throws IOException, CannotWriteException {
        int i3 = i - i2;
        logger.config("Writing:Option 5;Larger Size can use meta free atom need extra:" + i3 + "bytes");
        seekableByteChannel.position(mp4BoxHeader.getFilePos());
        seekableByteChannel.write(byteBuffer);
        Mp4FreeBox mp4FreeBox = new Mp4FreeBox(i3 - 8);
        seekableByteChannel.write(mp4FreeBox.getHeader().getHeaderData());
        seekableByteChannel.write(mp4FreeBox.getData());
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x024f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x013f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:115:? A[Catch: IOException -> 0x0259, SYNTHETIC, TRY_LEAVE, TryCatch #0 {IOException -> 0x0259, blocks: (B:71:0x0221, B:92:0x0258, B:91:0x0255, B:87:0x024f), top: B:99:0x000c, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x011b A[Catch: all -> 0x00f4, TRY_ENTER, TryCatch #2 {all -> 0x00f4, blocks: (B:12:0x0092, B:30:0x011b, B:32:0x012d, B:35:0x0144, B:46:0x0169, B:48:0x0174, B:50:0x0191, B:52:0x019a, B:58:0x01c2, B:38:0x0150, B:15:0x00b4, B:16:0x00bf, B:17:0x00cc, B:20:0x00e5), top: B:102:0x008c }] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0144 A[Catch: all -> 0x00f4, TryCatch #2 {all -> 0x00f4, blocks: (B:12:0x0092, B:30:0x011b, B:32:0x012d, B:35:0x0144, B:46:0x0169, B:48:0x0174, B:50:0x0191, B:52:0x019a, B:58:0x01c2, B:38:0x0150, B:15:0x00b4, B:16:0x00bf, B:17:0x00cc, B:20:0x00e5), top: B:102:0x008c }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0159 A[Catch: all -> 0x0239, TRY_ENTER, TryCatch #5 {all -> 0x0239, blocks: (B:4:0x001c, B:5:0x0021, B:7:0x002b, B:27:0x0109, B:28:0x0115, B:44:0x0160, B:55:0x01a9, B:42:0x0159, B:24:0x00fa), top: B:105:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0169 A[Catch: all -> 0x00f4, TRY_ENTER, TryCatch #2 {all -> 0x00f4, blocks: (B:12:0x0092, B:30:0x011b, B:32:0x012d, B:35:0x0144, B:46:0x0169, B:48:0x0174, B:50:0x0191, B:52:0x019a, B:58:0x01c2, B:38:0x0150, B:15:0x00b4, B:16:0x00bf, B:17:0x00cc, B:20:0x00e5), top: B:102:0x008c }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0221 A[Catch: IOException -> 0x0259, TRY_ENTER, TRY_LEAVE, TryCatch #0 {IOException -> 0x0259, blocks: (B:71:0x0221, B:92:0x0258, B:91:0x0255, B:87:0x024f), top: B:99:0x000c, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void write(org.jaudiotagger.tag.Tag r26, java.nio.file.Path r27) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.mp4.Mp4TagWriter.write(org.jaudiotagger.tag.Tag, java.nio.file.Path):void");
    }

    private void convertandWriteTagsAtomToFreeAtom(SeekableByteChannel seekableByteChannel, Mp4BoxHeader mp4BoxHeader) throws IOException {
        Mp4FreeBox mp4FreeBox = new Mp4FreeBox(mp4BoxHeader.getDataLength());
        seekableByteChannel.write(mp4FreeBox.getHeader().getHeaderData());
        seekableByteChannel.write(mp4FreeBox.getData());
    }

    private int getMetaLevelFreeAtomSize(Mp4AtomTree mp4AtomTree) {
        for (DefaultMutableTreeNode defaultMutableTreeNode : mp4AtomTree.getFreeNodes()) {
            DefaultMutableTreeNode defaultMutableTreeNode2 = (DefaultMutableTreeNode) defaultMutableTreeNode.getParent();
            DefaultMutableTreeNode previousSibling = defaultMutableTreeNode.getPreviousSibling();
            if (!defaultMutableTreeNode2.isRoot()) {
                Mp4BoxHeader mp4BoxHeader = (Mp4BoxHeader) defaultMutableTreeNode2.getUserObject();
                Mp4BoxHeader mp4BoxHeader2 = (Mp4BoxHeader) defaultMutableTreeNode.getUserObject();
                if (previousSibling != null) {
                    Mp4BoxHeader mp4BoxHeader3 = (Mp4BoxHeader) previousSibling.getUserObject();
                    if (mp4BoxHeader.getId().equals(Mp4AtomIdentifier.META.getFieldName()) && mp4BoxHeader3.getId().equals(Mp4AtomIdentifier.ILST.getFieldName())) {
                        return mp4BoxHeader2.getLength();
                    }
                } else {
                    continue;
                }
            }
        }
        return 0;
    }

    private void checkFileWrittenCorrectly(Mp4BoxHeader mp4BoxHeader, SeekableByteChannel seekableByteChannel, List<Mp4StcoBox> list) throws IOException, CannotWriteException {
        logger.config("Checking file has been written correctly");
        try {
            try {
                Mp4AtomTree mp4AtomTree = new Mp4AtomTree(seekableByteChannel, false);
                Mp4BoxHeader boxHeader = mp4AtomTree.getBoxHeader(mp4AtomTree.getMdatNode());
                if (boxHeader == null) {
                    throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_NO_DATA.getMsg());
                }
                if (boxHeader.getLength() != mp4BoxHeader.getLength()) {
                    throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_DATA_CORRUPT.getMsg());
                }
                if (mp4AtomTree.getBoxHeader(mp4AtomTree.getUdtaNode()) == null) {
                    throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_NO_TAG_DATA.getMsg());
                }
                if (mp4AtomTree.getBoxHeader(mp4AtomTree.getMetaNode()) == null) {
                    throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_NO_TAG_DATA.getMsg());
                }
                List<Mp4StcoBox> stcos = mp4AtomTree.getStcos();
                if (stcos.size() != list.size()) {
                    throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_INCORRECT_NUMBER_OF_TRACKS.getMsg(Integer.valueOf(list.size()), Integer.valueOf(stcos.size())));
                }
                int firstOffSet = 0;
                for (int i = 0; i < stcos.size(); i++) {
                    Mp4StcoBox mp4StcoBox = stcos.get(i);
                    Mp4StcoBox mp4StcoBox2 = list.get(i);
                    logger.finer("stco:Original First Offset" + mp4StcoBox2.getFirstOffSet());
                    logger.finer("stco:Original Diff" + ((int) (mp4StcoBox2.getFirstOffSet() - mp4BoxHeader.getFilePos())));
                    logger.finer("stco:Original Mdat Pos" + mp4BoxHeader.getFilePos());
                    logger.finer("stco:New First Offset" + mp4StcoBox.getFirstOffSet());
                    logger.finer("stco:New Diff" + ((int) (mp4StcoBox.getFirstOffSet() - boxHeader.getFilePos())));
                    logger.finer("stco:New Mdat Pos" + boxHeader.getFilePos());
                    if (i == 0) {
                        long firstOffSet2 = (int) (mp4StcoBox2.getFirstOffSet() - mp4BoxHeader.getFilePos());
                        if (mp4StcoBox.getFirstOffSet() - boxHeader.getFilePos() != firstOffSet2) {
                            throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_INCORRECT_OFFSETS.getMsg(Integer.valueOf((int) ((mp4StcoBox.getFirstOffSet() - boxHeader.getFilePos()) - firstOffSet2))));
                        }
                        firstOffSet = mp4StcoBox2.getFirstOffSet() - mp4StcoBox.getFirstOffSet();
                    } else if (firstOffSet != mp4StcoBox2.getFirstOffSet() - mp4StcoBox.getFirstOffSet()) {
                        throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED_INCORRECT_OFFSETS.getMsg(Integer.valueOf(firstOffSet)));
                    }
                }
                seekableByteChannel.close();
                logger.config("File has been written correctly");
            } catch (Exception e) {
                if (e instanceof CannotWriteException) {
                    throw ((CannotWriteException) e);
                }
                e.printStackTrace();
                throw new CannotWriteException(ErrorMessage.MP4_CHANGES_TO_FILE_FAILED.getMsg() + ":" + e.getMessage());
            }
        } catch (Throwable th) {
            seekableByteChannel.close();
            throw th;
        }
    }

    public void delete(Tag tag, Path path) throws Throwable {
        write(new Mp4Tag(), path);
    }

    private void writeNoExistingUdtaAtom(SeekableByteChannel seekableByteChannel, ByteBuffer byteBuffer, Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer2, Mp4BoxHeader mp4BoxHeader2, List<Mp4StcoBox> list, int i, boolean z, Mp4BoxHeader mp4BoxHeader3) throws IOException {
        long fileEndPos = mp4BoxHeader.getFileEndPos();
        Mp4HdlrBox mp4HdlrBoxCreateiTunesStyleHdlrBox = Mp4HdlrBox.createiTunesStyleHdlrBox();
        Mp4MetaBox mp4MetaBoxCreateiTunesStyleMetaBox = Mp4MetaBox.createiTunesStyleMetaBox(mp4HdlrBoxCreateiTunesStyleHdlrBox.getHeader().getLength() + byteBuffer.limit());
        Mp4BoxHeader mp4BoxHeader4 = new Mp4BoxHeader(Mp4AtomIdentifier.UDTA.getFieldName());
        mp4BoxHeader4.setLength(mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getLength() + 8);
        boolean zAdjustStcosIfNoSuitableTopLevelAtom = adjustStcosIfNoSuitableTopLevelAtom(i, z, mp4BoxHeader4.getLength(), list, mp4BoxHeader, mp4BoxHeader2);
        mp4BoxHeader.setLength(mp4BoxHeader.getLength() + mp4BoxHeader4.getLength());
        seekableByteChannel.position(mp4BoxHeader.getFilePos());
        seekableByteChannel.write(mp4BoxHeader.getHeaderData());
        byteBuffer2.rewind();
        seekableByteChannel.write(byteBuffer2);
        if (!zAdjustStcosIfNoSuitableTopLevelAtom) {
            logger.severe("Writing:Option 5.1;No udta atom");
            seekableByteChannel.write(mp4BoxHeader4.getHeaderData());
            seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getHeaderData());
            seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getData());
            seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getHeader().getHeaderData());
            seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getData());
            seekableByteChannel.write(byteBuffer);
            adjustTopLevelFreeAtom(seekableByteChannel, i, mp4BoxHeader4.getLength());
            return;
        }
        logger.severe("Writing:Option 5.2;No udta atom, not enough free space");
        seekableByteChannel.position(fileEndPos);
        ShiftData.shiftDataByOffsetToMakeSpace(seekableByteChannel, mp4BoxHeader4.getLength());
        seekableByteChannel.position(fileEndPos);
        seekableByteChannel.write(mp4BoxHeader4.getHeaderData());
        seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getHeaderData());
        seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getData());
        seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getHeader().getHeaderData());
        seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getData());
        seekableByteChannel.write(byteBuffer);
    }

    private void writeNoExistingMetaAtom(Mp4BoxHeader mp4BoxHeader, SeekableByteChannel seekableByteChannel, ByteBuffer byteBuffer, Mp4BoxHeader mp4BoxHeader2, ByteBuffer byteBuffer2, Mp4BoxHeader mp4BoxHeader3, List<Mp4StcoBox> list, int i, boolean z, Mp4BoxHeader mp4BoxHeader4, int i2, int i3, int i4, int i5) throws IOException {
        int iLimit = byteBuffer.limit();
        int dataLength = mp4BoxHeader2.getDataLength();
        long fileEndPos = mp4BoxHeader2.getFileEndPos();
        int length = mp4BoxHeader.getLength();
        int dataLength2 = mp4BoxHeader.getDataLength();
        Mp4HdlrBox mp4HdlrBoxCreateiTunesStyleHdlrBox = Mp4HdlrBox.createiTunesStyleHdlrBox();
        Mp4MetaBox mp4MetaBoxCreateiTunesStyleMetaBox = Mp4MetaBox.createiTunesStyleMetaBox(mp4HdlrBoxCreateiTunesStyleHdlrBox.getHeader().getLength() + iLimit);
        Mp4BoxHeader mp4BoxHeader5 = new Mp4BoxHeader(Mp4AtomIdentifier.UDTA.getFieldName());
        mp4BoxHeader5.setLength(mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getLength() + 8 + dataLength2);
        int dataLength3 = mp4BoxHeader5.getDataLength() - dataLength2;
        boolean zAdjustStcosIfNoSuitableTopLevelAtom = adjustStcosIfNoSuitableTopLevelAtom(i, z, dataLength3, list, mp4BoxHeader2, mp4BoxHeader3);
        mp4BoxHeader2.setLength(mp4BoxHeader2.getLength() + dataLength3);
        seekableByteChannel.position(mp4BoxHeader2.getFilePos());
        seekableByteChannel.write(mp4BoxHeader2.getHeaderData());
        byteBuffer2.rewind();
        byteBuffer2.limit(dataLength - length);
        seekableByteChannel.write(byteBuffer2);
        seekableByteChannel.write(mp4BoxHeader5.getHeaderData());
        if (byteBuffer2.position() + 8 < byteBuffer2.capacity()) {
            byteBuffer2.limit(byteBuffer2.capacity());
            byteBuffer2.position(byteBuffer2.position() + 8);
            seekableByteChannel.write(byteBuffer2);
        }
        if (!zAdjustStcosIfNoSuitableTopLevelAtom) {
            logger.severe("Writing:Option 6.1;No meta atom");
            seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getHeaderData());
            seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getData());
            seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getHeader().getHeaderData());
            seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getData());
            seekableByteChannel.write(byteBuffer);
            writeRestOfMoovHeaderAfterNewIlistAndAmendedTopLevelFreeAtom(seekableByteChannel, i2, mp4BoxHeader2, byteBuffer2, i5, i4, mp4BoxHeader4, i3);
            return;
        }
        logger.severe("Writing:Option 6.2;No meta atom, not enough free space");
        seekableByteChannel.position(fileEndPos);
        ShiftData.shiftDataByOffsetToMakeSpace(seekableByteChannel, mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getLength());
        seekableByteChannel.position(fileEndPos);
        seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getHeader().getHeaderData());
        seekableByteChannel.write(mp4MetaBoxCreateiTunesStyleMetaBox.getData());
        seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getHeader().getHeaderData());
        seekableByteChannel.write(mp4HdlrBoxCreateiTunesStyleHdlrBox.getData());
        seekableByteChannel.write(byteBuffer);
    }

    private void writeHaveExistingMetadata(Mp4BoxHeader mp4BoxHeader, Mp4BoxHeader mp4BoxHeader2, SeekableByteChannel seekableByteChannel, int i, Mp4BoxHeader mp4BoxHeader3, ByteBuffer byteBuffer, Mp4BoxHeader mp4BoxHeader4, List<Mp4StcoBox> list, int i2, boolean z, ByteBuffer byteBuffer2, Mp4BoxHeader mp4BoxHeader5, int i3) throws IOException {
        long fileEndPos = mp4BoxHeader3.getFileEndPos();
        int iLimit = byteBuffer2.limit() - i3;
        boolean zAdjustStcosIfNoSuitableTopLevelAtom = adjustStcosIfNoSuitableTopLevelAtom(i2, z, iLimit, list, mp4BoxHeader3, mp4BoxHeader4);
        adjustSizeOfMoovHeader(mp4BoxHeader3, byteBuffer, iLimit, mp4BoxHeader, mp4BoxHeader2);
        seekableByteChannel.position(mp4BoxHeader3.getFilePos());
        seekableByteChannel.write(mp4BoxHeader3.getHeaderData());
        byteBuffer.rewind();
        byteBuffer.limit(i);
        seekableByteChannel.write(byteBuffer);
        if (!zAdjustStcosIfNoSuitableTopLevelAtom) {
            logger.severe("Writing:Option 7.1, Increased Data");
            seekableByteChannel.write(byteBuffer2);
            writeRestOfMoovHeaderAfterNewIlistAndAmendedTopLevelFreeAtom(seekableByteChannel, i, mp4BoxHeader3, byteBuffer, iLimit, i2, mp4BoxHeader5, i3);
            return;
        }
        logger.severe("Writing:Option 7.2 Increased Data, not enough free space");
        seekableByteChannel.position(fileEndPos);
        ShiftData.shiftDataByOffsetToMakeSpace(seekableByteChannel, iLimit);
        seekableByteChannel.position(mp4BoxHeader3.getFilePos() + 8 + i);
        seekableByteChannel.write(byteBuffer2);
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.position(i + i3);
        if (byteBuffer.position() < byteBuffer.capacity()) {
            seekableByteChannel.write(byteBuffer);
        }
    }

    private void writeRestOfMoovHeaderAfterNewIlistAndAmendedTopLevelFreeAtom(SeekableByteChannel seekableByteChannel, int i, Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer, int i2, int i3, Mp4BoxHeader mp4BoxHeader2, int i4) throws IOException {
        if (mp4BoxHeader2 != null) {
            byteBuffer.limit(byteBuffer.capacity());
            byteBuffer.position(i + i4);
            writeFromEndOfIlstToNeroTagsAndMakeNeroFree(mp4BoxHeader, byteBuffer, seekableByteChannel, mp4BoxHeader2);
            adjustTopLevelFreeAtom(seekableByteChannel, i3, i2);
            return;
        }
        byteBuffer.limit(byteBuffer.capacity());
        byteBuffer.position(i + i4);
        if (byteBuffer.position() < byteBuffer.capacity()) {
            seekableByteChannel.write(byteBuffer);
        }
        adjustTopLevelFreeAtom(seekableByteChannel, i3, i2);
    }

    private void writeFromEndOfIlstToNeroTagsAndMakeNeroFree(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer, SeekableByteChannel seekableByteChannel, Mp4BoxHeader mp4BoxHeader2) throws IOException {
        byteBuffer.limit((int) (mp4BoxHeader2.getFilePos() - (mp4BoxHeader.getFilePos() + 8)));
        seekableByteChannel.write(byteBuffer);
        convertandWriteTagsAtomToFreeAtom(seekableByteChannel, mp4BoxHeader2);
    }

    private void adjustTopLevelFreeAtom(SeekableByteChannel seekableByteChannel, int i, int i2) throws IOException {
        int i3 = i - 8;
        if (i3 < i2) {
            if (i == i2) {
                logger.config("Writing:Option 7;Larger Size uses top free atom including header");
            }
        } else {
            logger.config("Writing:Option 6;Larger Size can use top free atom");
            Mp4FreeBox mp4FreeBox = new Mp4FreeBox(i3 - i2);
            seekableByteChannel.write(mp4FreeBox.getHeader().getHeaderData());
            seekableByteChannel.write(mp4FreeBox.getData());
        }
    }

    private boolean adjustStcosIfNoSuitableTopLevelAtom(int i, boolean z, int i2, List<Mp4StcoBox> list, Mp4BoxHeader mp4BoxHeader, Mp4BoxHeader mp4BoxHeader2) {
        if (mp4BoxHeader2.getFilePos() <= mp4BoxHeader.getFilePos()) {
            return false;
        }
        if (z && (i - 8 >= i2 || i == i2)) {
            return false;
        }
        Iterator<Mp4StcoBox> it = list.iterator();
        while (it.hasNext()) {
            it.next().adjustOffsets(i2);
        }
        return true;
    }
}
