package org.jaudiotagger.audio.aiff;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;
import org.jaudiotagger.audio.aiff.chunk.AiffChunkSummary;
import org.jaudiotagger.audio.aiff.chunk.AiffChunkType;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.NoWritePermissionsException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.aiff.AiffTag;

/* loaded from: classes3.dex */
public class AiffTagWriter {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.aiff");

    private AiffTag getExistingMetadata(Path path) throws IOException, CannotWriteException {
        try {
            return new AiffTagReader(path.toString()).read(path);
        } catch (CannotReadException unused) {
            throw new CannotWriteException(path + " Failed to read file");
        }
    }

    private ChunkHeader seekToStartOfMetadata(FileChannel fileChannel, AiffTag aiffTag, String str) throws IOException, CannotWriteException {
        fileChannel.position(aiffTag.getStartLocationInFileOfId3Chunk());
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.BIG_ENDIAN);
        chunkHeader.readHeader(fileChannel);
        fileChannel.position(fileChannel.position() - 8);
        if (AiffChunkType.TAG.getCode().equals(chunkHeader.getID())) {
            return chunkHeader;
        }
        throw new CannotWriteException(str + ":Unable to find ID3 chunk at expected location:" + aiffTag.getStartLocationInFileOfId3Chunk());
    }

    private boolean isAtEndOfFileAllowingForPaddingByte(AiffTag aiffTag, FileChannel fileChannel) throws IOException {
        return aiffTag.getID3Tag().getEndLocationInFile().longValue() >= fileChannel.size() || (Utils.isOddLength(aiffTag.getID3Tag().getEndLocationInFile().longValue()) && aiffTag.getID3Tag().getEndLocationInFile().longValue() + 1 == fileChannel.size());
    }

    public void delete(Tag tag, Path path) throws IOException, CannotWriteException {
        try {
            FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
            try {
                logger.severe(path + ":Deleting tag from file");
                AiffTag existingMetadata = getExistingMetadata(path);
                if (existingMetadata.isExistingId3Tag() && existingMetadata.getID3Tag().getStartLocationInFile() != null) {
                    ChunkHeader chunkHeaderSeekToStartOfMetadata = seekToStartOfMetadata(fileChannelOpen, existingMetadata, path.toString());
                    if (isAtEndOfFileAllowingForPaddingByte(existingMetadata, fileChannelOpen)) {
                        logger.config(path + ":Setting new length to:" + existingMetadata.getStartLocationInFileOfId3Chunk());
                        fileChannelOpen.truncate(existingMetadata.getStartLocationInFileOfId3Chunk());
                    } else {
                        logger.config(path + ":Deleting tag chunk");
                        deleteTagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfMetadata, path.toString());
                    }
                    rewriteRiffHeaderSize(fileChannelOpen);
                }
                logger.config(path + ":Deleted tag from file");
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
            } finally {
            }
        } catch (IOException e) {
            throw new CannotWriteException(path + ":" + e.getMessage());
        }
    }

    private void deleteTagChunk(FileChannel fileChannel, AiffTag aiffTag, ChunkHeader chunkHeader, String str) throws IOException {
        int size = (int) chunkHeader.getSize();
        int i = size + 8;
        long j = i;
        if (Utils.isOddLength(j) && aiffTag.getStartLocationInFileOfId3Chunk() + j < fileChannel.size()) {
            i = size + 9;
        }
        long j2 = i;
        long size2 = fileChannel.size() - j2;
        logger.config(str + ":Size of id3 chunk to delete is:" + Hex.asDecAndHex(j2) + ":Location:" + Hex.asDecAndHex(aiffTag.getStartLocationInFileOfId3Chunk()));
        fileChannel.position(aiffTag.getStartLocationInFileOfId3Chunk() + j2);
        logger.severe(str + ":Moved location to:" + Hex.asDecAndHex(size2));
        deleteTagChunkUsingSmallByteBufferSegments(aiffTag, fileChannel, size2, j2);
        logger.config(str + ":Setting new length to:" + Hex.asDecAndHex(size2));
        fileChannel.truncate(size2);
    }

    private void deleteRemainderOfFile(FileChannel fileChannel, AiffTag aiffTag, String str) throws IOException {
        if (!Utils.isOddLength(AiffChunkSummary.getChunkBeforeStartingMetadataTag(aiffTag).getEndLocation())) {
            logger.config(str + ":Truncating corrupted ID3 tags from:" + (aiffTag.getStartLocationInFileOfId3Chunk() - 1));
            fileChannel.truncate(aiffTag.getStartLocationInFileOfId3Chunk() - 1);
        } else {
            logger.config(str + ":Truncating corrupted ID3 tags from:" + aiffTag.getStartLocationInFileOfId3Chunk());
            fileChannel.truncate(aiffTag.getStartLocationInFileOfId3Chunk());
        }
    }

    private void deleteTagChunkUsingSmallByteBufferSegments(AiffTag aiffTag, FileChannel fileChannel, long j, long j2) throws IOException {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect((int) TagOptionSingleton.getInstance().getWriteChunkSize());
        while (true) {
            if (fileChannel.read(byteBufferAllocateDirect) < 0 && byteBufferAllocateDirect.position() == 0) {
                return;
            }
            byteBufferAllocateDirect.flip();
            long jPosition = fileChannel.position();
            fileChannel.position((jPosition - j2) - byteBufferAllocateDirect.limit());
            fileChannel.write(byteBufferAllocateDirect);
            fileChannel.position(jPosition);
            byteBufferAllocateDirect.compact();
        }
    }

    public void write(Tag tag, Path path) throws IOException, CannotWriteException {
        logger.severe(path + ":Writing Aiff tag to file");
        try {
            AiffTag existingMetadata = getExistingMetadata(path);
            try {
                FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
                try {
                    long formSize = existingMetadata.getFormSize() + 8;
                    long jPosition = fileChannelOpen.position();
                    if (formSize < fileChannelOpen.size() && !existingMetadata.isLastChunkSizeExtendsPastFormSize()) {
                        logger.warning(path + ":Extra Non Chunk Data after end of FORM data length:" + (fileChannelOpen.size() - formSize));
                        fileChannelOpen.position(formSize);
                        fileChannelOpen.truncate(formSize);
                        fileChannelOpen.position(jPosition);
                    }
                    ByteBuffer byteBufferConvert = convert((AiffTag) tag, existingMetadata);
                    if (existingMetadata.isExistingId3Tag() && existingMetadata.getID3Tag().getStartLocationInFile() != null) {
                        if (!existingMetadata.isIncorrectlyAlignedTag()) {
                            ChunkHeader chunkHeaderSeekToStartOfMetadata = seekToStartOfMetadata(fileChannelOpen, existingMetadata, path.toString());
                            logger.config(path + ":Current Space allocated:" + existingMetadata.getSizeOfID3TagOnly() + ":NewTagRequires:" + byteBufferConvert.limit());
                            if (isAtEndOfFileAllowingForPaddingByte(existingMetadata, fileChannelOpen)) {
                                writeDataToFile(fileChannelOpen, byteBufferConvert);
                            } else {
                                deleteTagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfMetadata, path.toString());
                                fileChannelOpen.position(fileChannelOpen.size());
                                writeExtraByteIfChunkOddSize(fileChannelOpen, fileChannelOpen.size());
                                writeDataToFile(fileChannelOpen, byteBufferConvert);
                            }
                        } else if (AiffChunkSummary.isOnlyMetadataTagsAfterStartingMetadataTag(existingMetadata)) {
                            deleteRemainderOfFile(fileChannelOpen, existingMetadata, path.toString());
                            fileChannelOpen.position(fileChannelOpen.size());
                            writeExtraByteIfChunkOddSize(fileChannelOpen, fileChannelOpen.size());
                            writeDataToFile(fileChannelOpen, byteBufferConvert);
                        } else {
                            throw new CannotWriteException(path + ":Metadata tags are corrupted and not at end of file so cannot be fixed");
                        }
                    } else {
                        fileChannelOpen.position(fileChannelOpen.size());
                        if (Utils.isOddLength(fileChannelOpen.size())) {
                            fileChannelOpen.write(ByteBuffer.allocateDirect(1));
                        }
                        writeDataToFile(fileChannelOpen, byteBufferConvert);
                    }
                    rewriteRiffHeaderSize(fileChannelOpen);
                    if (fileChannelOpen != null) {
                        fileChannelOpen.close();
                    }
                } finally {
                }
            } catch (AccessDeniedException e) {
                throw new NoWritePermissionsException(path + ":" + e.getMessage());
            } catch (IOException e2) {
                throw new CannotWriteException(path + ":" + e2.getMessage());
            }
        } catch (IOException e3) {
            throw new CannotWriteException(path + ":" + e3.getMessage());
        }
    }

    private void rewriteRiffHeaderSize(FileChannel fileChannel) throws IOException {
        fileChannel.position(IffHeaderChunk.SIGNATURE_LENGTH);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(IffHeaderChunk.SIZE_LENGTH);
        byteBufferAllocateDirect.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocateDirect.putInt(((int) fileChannel.size()) - 8);
        byteBufferAllocateDirect.flip();
        fileChannel.write(byteBufferAllocateDirect);
    }

    private void writeDataToFile(FileChannel fileChannel, ByteBuffer byteBuffer) throws IOException {
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.BIG_ENDIAN);
        chunkHeader.setID(AiffChunkType.TAG.getCode());
        chunkHeader.setSize(byteBuffer.limit());
        fileChannel.write(chunkHeader.writeHeader());
        fileChannel.write(byteBuffer);
        writeExtraByteIfChunkOddSize(fileChannel, byteBuffer.limit());
    }

    private void writeExtraByteIfChunkOddSize(FileChannel fileChannel, long j) throws IOException {
        if (Utils.isOddLength(j)) {
            fileChannel.write(ByteBuffer.allocateDirect(1));
        }
    }

    public ByteBuffer convert(AiffTag aiffTag, AiffTag aiffTag2) throws UnsupportedEncodingException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long sizeOfID3TagOnly = aiffTag2.getSizeOfID3TagOnly();
            if (sizeOfID3TagOnly > 0 && Utils.isOddLength(sizeOfID3TagOnly)) {
                sizeOfID3TagOnly++;
            }
            aiffTag.getID3Tag().write(byteArrayOutputStream, (int) sizeOfID3TagOnly);
            if (Utils.isOddLength(byteArrayOutputStream.toByteArray().length)) {
                int length = byteArrayOutputStream.toByteArray().length + 1;
                byteArrayOutputStream = new ByteArrayOutputStream();
                aiffTag.getID3Tag().write(byteArrayOutputStream, length);
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            byteBufferWrap.rewind();
            return byteBufferWrap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
