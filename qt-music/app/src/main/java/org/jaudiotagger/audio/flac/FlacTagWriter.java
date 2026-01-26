package org.jaudiotagger.audio.flac;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.NoWritePermissionsException;
import org.jaudiotagger.audio.flac.metadatablock.BlockType;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlock;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataApplication;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataCueSheet;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPadding;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataSeekTable;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataStreamInfo;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.utils.ShiftData;

/* loaded from: classes3.dex */
public class FlacTagWriter {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac");
    private FlacTagCreator tc = new FlacTagCreator();

    public void delete(Tag tag, Path path) throws IOException, CannotWriteException {
        write(new FlacTag(null, new ArrayList()), path);
    }

    private static class MetadataBlockInfo {
        private List<MetadataBlock> blocks;
        private List<MetadataBlock> metadataBlockApplication;
        private List<MetadataBlock> metadataBlockCueSheet;
        private List<MetadataBlock> metadataBlockPadding;
        private List<MetadataBlock> metadataBlockSeekTable;
        private MetadataBlock streamInfoBlock;

        private MetadataBlockInfo() {
            this.blocks = new ArrayList();
            this.metadataBlockPadding = new ArrayList(1);
            this.metadataBlockApplication = new ArrayList(1);
            this.metadataBlockSeekTable = new ArrayList(1);
            this.metadataBlockCueSheet = new ArrayList(1);
        }

        public List<MetadataBlock> getListOfNonMetadataBlocks() {
            Iterator<MetadataBlock> it = this.metadataBlockSeekTable.iterator();
            while (it.hasNext()) {
                this.blocks.add(it.next());
            }
            Iterator<MetadataBlock> it2 = this.metadataBlockCueSheet.iterator();
            while (it2.hasNext()) {
                this.blocks.add(it2.next());
            }
            Iterator<MetadataBlock> it3 = this.metadataBlockApplication.iterator();
            while (it3.hasNext()) {
                this.blocks.add(it3.next());
            }
            return this.blocks;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getOtherBlockCount(MetadataBlockInfo metadataBlockInfo) {
            return metadataBlockInfo.metadataBlockApplication.size() + metadataBlockInfo.metadataBlockSeekTable.size() + metadataBlockInfo.metadataBlockCueSheet.size();
        }

        public int computeAvailableRoom() {
            Iterator<MetadataBlock> it = this.metadataBlockApplication.iterator();
            int length = 0;
            while (it.hasNext()) {
                length += it.next().getLength();
            }
            Iterator<MetadataBlock> it2 = this.metadataBlockSeekTable.iterator();
            while (it2.hasNext()) {
                length += it2.next().getLength();
            }
            Iterator<MetadataBlock> it3 = this.metadataBlockCueSheet.iterator();
            while (it3.hasNext()) {
                length += it3.next().getLength();
            }
            Iterator<MetadataBlock> it4 = this.metadataBlockPadding.iterator();
            while (it4.hasNext()) {
                length += it4.next().getLength();
            }
            return length;
        }

        public int computeNeededRoom() {
            Iterator<MetadataBlock> it = this.metadataBlockApplication.iterator();
            int length = 0;
            while (it.hasNext()) {
                length += it.next().getLength();
            }
            Iterator<MetadataBlock> it2 = this.metadataBlockSeekTable.iterator();
            while (it2.hasNext()) {
                length += it2.next().getLength();
            }
            Iterator<MetadataBlock> it3 = this.metadataBlockCueSheet.iterator();
            while (it3.hasNext()) {
                length += it3.next().getLength();
            }
            return length;
        }
    }

    public void write(Tag tag, Path path) throws IOException, CannotWriteException {
        logger.config(path + " Writing tag");
        try {
            boolean zIsLastBlock = false;
            FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
            try {
                MetadataBlockInfo metadataBlockInfo = new MetadataBlockInfo();
                FlacStreamReader flacStreamReader = new FlacStreamReader(fileChannelOpen, path.toString() + " ");
                try {
                    flacStreamReader.findStream();
                    while (!zIsLastBlock) {
                        try {
                            MetadataBlockHeader header = MetadataBlockHeader.readHeader(fileChannelOpen);
                            if (header.getBlockType() != null) {
                                switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[header.getBlockType().ordinal()]) {
                                    case 1:
                                        metadataBlockInfo.streamInfoBlock = new MetadataBlock(header, new MetadataBlockDataStreamInfo(header, fileChannelOpen));
                                        break;
                                    case 2:
                                    case 3:
                                    case 4:
                                        fileChannelOpen.position(fileChannelOpen.position() + header.getDataLength());
                                        metadataBlockInfo.metadataBlockPadding.add(new MetadataBlock(header, new MetadataBlockDataPadding(header.getDataLength())));
                                        break;
                                    case 5:
                                        metadataBlockInfo.metadataBlockApplication.add(new MetadataBlock(header, new MetadataBlockDataApplication(header, fileChannelOpen)));
                                        break;
                                    case 6:
                                        metadataBlockInfo.metadataBlockSeekTable.add(new MetadataBlock(header, new MetadataBlockDataSeekTable(header, fileChannelOpen)));
                                        break;
                                    case 7:
                                        metadataBlockInfo.metadataBlockCueSheet.add(new MetadataBlock(header, new MetadataBlockDataCueSheet(header, fileChannelOpen)));
                                        break;
                                    default:
                                        fileChannelOpen.position(fileChannelOpen.position() + header.getDataLength());
                                        break;
                                }
                            }
                            zIsLastBlock = header.isLastBlock();
                        } catch (CannotReadException e) {
                            throw new CannotWriteException(e.getMessage());
                        }
                    }
                    int iComputeAvailableRoom = metadataBlockInfo.computeAvailableRoom();
                    int iLimit = this.tc.convertMetadata(tag).limit();
                    int iComputeNeededRoom = metadataBlockInfo.computeNeededRoom() + iLimit;
                    fileChannelOpen.position(flacStreamReader.getStartOfFlacInFile());
                    logger.config(path + ":Writing tag available bytes:" + iComputeAvailableRoom + ":needed bytes:" + iComputeNeededRoom);
                    if (iComputeAvailableRoom == iComputeNeededRoom || iComputeAvailableRoom > iComputeNeededRoom + 4) {
                        logger.config(path + ":Room to Rewrite");
                        writeAllNonAudioData(tag, fileChannelOpen, metadataBlockInfo, flacStreamReader, iComputeAvailableRoom - iComputeNeededRoom);
                    } else {
                        logger.config(path + ":Audio must be shifted NewTagSize:" + iLimit + ":AvailableRoom:" + iComputeAvailableRoom + ":MinimumAdditionalRoomRequired:" + (iComputeNeededRoom - iComputeAvailableRoom));
                        insertUsingChunks(path, tag, fileChannelOpen, metadataBlockInfo, flacStreamReader, iComputeNeededRoom + FlacTagCreator.DEFAULT_PADDING, iComputeAvailableRoom);
                    }
                    if (fileChannelOpen != null) {
                        fileChannelOpen.close();
                    }
                } catch (CannotReadException e2) {
                    throw new CannotWriteException(e2.getMessage());
                }
            } finally {
            }
        } catch (AccessDeniedException e3) {
            logger.log(Level.SEVERE, e3.getMessage(), (Throwable) e3);
            throw new NoWritePermissionsException(path + ":" + e3.getMessage());
        } catch (IOException e4) {
            logger.log(Level.SEVERE, e4.getMessage(), (Throwable) e4);
            throw new CannotWriteException(path + ":" + e4.getMessage());
        }
    }

    /* renamed from: org.jaudiotagger.audio.flac.FlacTagWriter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType;

        static {
            int[] iArr = new int[BlockType.values().length];
            $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType = iArr;
            try {
                iArr[BlockType.STREAMINFO.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.VORBIS_COMMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.PADDING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.PICTURE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.APPLICATION.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.SEEKTABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.CUESHEET.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public ByteBuffer addPaddingBlock(int i) throws UnsupportedEncodingException {
        logger.config("padding:" + i);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        if (i > 0) {
            int i2 = i - 4;
            MetadataBlockHeader metadataBlockHeader = new MetadataBlockHeader(true, BlockType.PADDING, i2);
            MetadataBlockDataPadding metadataBlockDataPadding = new MetadataBlockDataPadding(i2);
            byteBufferAllocate.put(metadataBlockHeader.getBytes());
            byteBufferAllocate.put(metadataBlockDataPadding.getBytes());
            byteBufferAllocate.rewind();
        }
        return byteBufferAllocate;
    }

    private void writeAllNonAudioData(Tag tag, FileChannel fileChannel, MetadataBlockInfo metadataBlockInfo, FlacStreamReader flacStreamReader, int i) throws IOException {
        fileChannel.position(flacStreamReader.getStartOfFlacInFile() + 4);
        writeStreamBlock(fileChannel, metadataBlockInfo);
        fileChannel.write(this.tc.convertMetadata(tag, i > 0 || metadataBlockInfo.getOtherBlockCount(metadataBlockInfo) > 0));
        List<MetadataBlock> listOfNonMetadataBlocks = metadataBlockInfo.getListOfNonMetadataBlocks();
        if (listOfNonMetadataBlocks.size() > 1) {
            for (int i2 = 0; i2 < listOfNonMetadataBlocks.size() - 1; i2++) {
                fileChannel.write(ByteBuffer.wrap(listOfNonMetadataBlocks.get(i2).getHeader().getBytesWithoutIsLastBlockFlag()));
                fileChannel.write(listOfNonMetadataBlocks.get(i2).getData().getBytes());
            }
        }
        if (listOfNonMetadataBlocks.size() > 0) {
            if (i > 0) {
                fileChannel.write(ByteBuffer.wrap(listOfNonMetadataBlocks.get(listOfNonMetadataBlocks.size() - 1).getHeader().getBytesWithoutIsLastBlockFlag()));
            } else {
                fileChannel.write(ByteBuffer.wrap(listOfNonMetadataBlocks.get(listOfNonMetadataBlocks.size() - 1).getHeader().getBytesWithLastBlockFlag()));
            }
            fileChannel.write(listOfNonMetadataBlocks.get(listOfNonMetadataBlocks.size() - 1).getData().getBytes());
        }
        if (i > 0) {
            fileChannel.write(addPaddingBlock(i));
        }
    }

    private void insertUsingChunks(Path path, Tag tag, FileChannel fileChannel, MetadataBlockInfo metadataBlockInfo, FlacStreamReader flacStreamReader, int i, int i2) throws IOException {
        long startOfFlacInFile = flacStreamReader.getStartOfFlacInFile() + 42 + i2;
        int i3 = i - i2;
        logger.config(path + " Audio needs shifting:" + i3);
        fileChannel.position(startOfFlacInFile);
        ShiftData.shiftDataByOffsetToMakeSpace(fileChannel, i3);
        fileChannel.position(flacStreamReader.getStartOfFlacInFile() + 4);
        writeAllNonAudioData(tag, fileChannel, metadataBlockInfo, flacStreamReader, FlacTagCreator.DEFAULT_PADDING);
    }

    private void writeStreamBlock(FileChannel fileChannel, MetadataBlockInfo metadataBlockInfo) throws IOException {
        fileChannel.write(ByteBuffer.wrap(metadataBlockInfo.streamInfoBlock.getHeader().getBytesWithoutIsLastBlockFlag()));
        fileChannel.write(metadataBlockInfo.streamInfoBlock.getData().getBytes());
    }
}
