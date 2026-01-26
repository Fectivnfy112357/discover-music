package org.jaudiotagger.audio.wav;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.NoWritePermissionsException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.ChunkSummary;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.audio.iff.PaddingChunkSummary;
import org.jaudiotagger.audio.wav.chunk.WavChunkSummary;
import org.jaudiotagger.audio.wav.chunk.WavInfoIdentifier;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.wav.WavInfoTag;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavTagWriter {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav");
    private String loggingName;

    public WavTagWriter(String str) {
        this.loggingName = str;
    }

    public WavTag getExistingMetadata(Path path) throws IOException, CannotWriteException {
        try {
            return new WavTagReader(this.loggingName).read(path);
        } catch (CannotReadException unused) {
            throw new CannotWriteException("Failed to read file " + path);
        }
    }

    public ChunkHeader seekToStartOfListInfoMetadata(FileChannel fileChannel, WavTag wavTag) throws IOException, CannotWriteException {
        fileChannel.position(wavTag.getInfoTag().getStartLocationInFile().longValue());
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        chunkHeader.readHeader(fileChannel);
        fileChannel.position(fileChannel.position() - 8);
        if (WavChunkType.LIST.getCode().equals(chunkHeader.getID())) {
            return chunkHeader;
        }
        throw new CannotWriteException(this.loggingName + " Unable to find List chunk at original location has file been modified externally");
    }

    public ChunkHeader seekToStartOfListInfoMetadataForChunkSummaryHeader(FileChannel fileChannel, ChunkSummary chunkSummary) throws IOException, CannotWriteException {
        fileChannel.position(chunkSummary.getFileStartLocation());
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        chunkHeader.readHeader(fileChannel);
        fileChannel.position(fileChannel.position() - 8);
        if (WavChunkType.LIST.getCode().equals(chunkHeader.getID())) {
            return chunkHeader;
        }
        throw new CannotWriteException(this.loggingName + " Unable to find List chunk at original location has file been modified externally");
    }

    public ChunkHeader seekToStartOfId3MetadataForChunkSummaryHeader(FileChannel fileChannel, WavTag wavTag) throws IOException, CannotWriteException {
        logger.info(this.loggingName + ":seekToStartOfIdMetadata:" + wavTag.getStartLocationInFileOfId3Chunk());
        fileChannel.position(wavTag.getStartLocationInFileOfId3Chunk());
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        chunkHeader.readHeader(fileChannel);
        fileChannel.position(fileChannel.position() - 8);
        if (!WavChunkType.ID3.getCode().equals(chunkHeader.getID()) && !WavChunkType.ID3_UPPERCASE.getCode().equals(chunkHeader.getID())) {
            throw new CannotWriteException(this.loggingName + " Unable to find ID3 chunk at original location has file been modified externally:" + chunkHeader.getID());
        }
        if (WavChunkType.ID3_UPPERCASE.getCode().equals(chunkHeader.getID())) {
            logger.severe(this.loggingName + ":on save ID3 chunk will be correctly set with id3 id");
        }
        return chunkHeader;
    }

    public ChunkHeader seekToStartOfId3MetadataForChunkSummaryHeader(FileChannel fileChannel, ChunkSummary chunkSummary) throws IOException, CannotWriteException {
        logger.severe(this.loggingName + ":seekToStartOfIdMetadata:" + chunkSummary.getFileStartLocation());
        fileChannel.position(chunkSummary.getFileStartLocation());
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        chunkHeader.readHeader(fileChannel);
        fileChannel.position(fileChannel.position() - 8);
        if (!WavChunkType.ID3.getCode().equals(chunkHeader.getID()) && !WavChunkType.ID3_UPPERCASE.getCode().equals(chunkHeader.getID())) {
            throw new CannotWriteException(this.loggingName + " Unable to find ID3 chunk at original location has file been modified externally:" + chunkHeader.getID());
        }
        if (WavChunkType.ID3_UPPERCASE.getCode().equals(chunkHeader.getID())) {
            logger.severe(this.loggingName + ":on save ID3 chunk will be correctly set with id3 id");
        }
        return chunkHeader;
    }

    public void delete(Tag tag, Path path) throws IOException, CannotWriteException {
        logger.info(this.loggingName + ":Deleting metadata from file");
        try {
            FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
            try {
                WavTag existingMetadata = getExistingMetadata(path);
                if (existingMetadata.isExistingId3Tag() && existingMetadata.isExistingInfoTag()) {
                    BothTagsFileStructure bothTagsFileStructureCheckExistingLocations = checkExistingLocations(existingMetadata, fileChannelOpen);
                    if (bothTagsFileStructureCheckExistingLocations.isContiguous) {
                        if (bothTagsFileStructureCheckExistingLocations.isAtEnd) {
                            if (bothTagsFileStructureCheckExistingLocations.isInfoTagFirst) {
                                fileChannelOpen.truncate(existingMetadata.getInfoTag().getStartLocationInFile().longValue());
                            } else {
                                fileChannelOpen.truncate(existingMetadata.getStartLocationInFileOfId3Chunk());
                            }
                        } else if (bothTagsFileStructureCheckExistingLocations.isInfoTagFirst) {
                            deleteTagChunk(fileChannelOpen, (int) existingMetadata.getEndLocationInFileOfId3Chunk(), (int) (existingMetadata.getEndLocationInFileOfId3Chunk() - existingMetadata.getInfoTag().getStartLocationInFile().longValue()));
                        } else {
                            deleteTagChunk(fileChannelOpen, existingMetadata.getInfoTag().getEndLocationInFile().intValue(), (int) (existingMetadata.getInfoTag().getEndLocationInFile().intValue() - existingMetadata.getStartLocationInFileOfId3Chunk()));
                        }
                    } else {
                        WavInfoTag infoTag = existingMetadata.getInfoTag();
                        ChunkHeader chunkHeaderSeekToStartOfListInfoMetadata = seekToStartOfListInfoMetadata(fileChannelOpen, existingMetadata);
                        ChunkHeader chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader = seekToStartOfId3MetadataForChunkSummaryHeader(fileChannelOpen, existingMetadata);
                        if (isInfoTagAtEndOfFileAllowingForPaddingByte(existingMetadata, fileChannelOpen)) {
                            fileChannelOpen.truncate(infoTag.getStartLocationInFile().longValue());
                            deleteId3TagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader);
                        } else if (isID3TagAtEndOfFileAllowingForPaddingByte(existingMetadata, fileChannelOpen)) {
                            fileChannelOpen.truncate(existingMetadata.getStartLocationInFileOfId3Chunk());
                            deleteInfoTagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfListInfoMetadata);
                        } else if (existingMetadata.getInfoTag().getStartLocationInFile().longValue() > existingMetadata.getStartLocationInFileOfId3Chunk()) {
                            deleteInfoTagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfListInfoMetadata);
                            deleteId3TagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader);
                        } else {
                            deleteId3TagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader);
                            deleteInfoTagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfListInfoMetadata);
                        }
                    }
                } else if (existingMetadata.isExistingInfoTag()) {
                    WavInfoTag infoTag2 = existingMetadata.getInfoTag();
                    ChunkHeader chunkHeaderSeekToStartOfListInfoMetadata2 = seekToStartOfListInfoMetadata(fileChannelOpen, existingMetadata);
                    if (infoTag2.getEndLocationInFile().longValue() == fileChannelOpen.size()) {
                        fileChannelOpen.truncate(infoTag2.getStartLocationInFile().longValue());
                    } else {
                        deleteInfoTagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfListInfoMetadata2);
                    }
                } else if (existingMetadata.isExistingId3Tag()) {
                    ChunkHeader chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader2 = seekToStartOfId3MetadataForChunkSummaryHeader(fileChannelOpen, existingMetadata);
                    if (isID3TagAtEndOfFileAllowingForPaddingByte(existingMetadata, fileChannelOpen)) {
                        fileChannelOpen.truncate(existingMetadata.getStartLocationInFileOfId3Chunk());
                    } else {
                        deleteId3TagChunk(fileChannelOpen, existingMetadata, chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader2);
                    }
                }
                rewriteRiffHeaderSize(fileChannelOpen);
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
            } finally {
            }
        } catch (IOException e) {
            throw new CannotWriteException(path + ":" + e.getMessage());
        }
    }

    private void deleteInfoTagChunk(FileChannel fileChannel, WavTag wavTag, ChunkHeader chunkHeader) throws IOException {
        WavInfoTag infoTag = wavTag.getInfoTag();
        deleteTagChunk(fileChannel, infoTag.getEndLocationInFile().intValue(), ((int) chunkHeader.getSize()) + 8);
    }

    private void deleteId3TagChunk(FileChannel fileChannel, WavTag wavTag, ChunkHeader chunkHeader) throws IOException {
        int size = (int) chunkHeader.getSize();
        int i = size + 8;
        if (Utils.isOddLength(wavTag.getEndLocationInFileOfId3Chunk())) {
            deleteTagChunk(fileChannel, ((int) wavTag.getEndLocationInFileOfId3Chunk()) + 1, size + 9);
        } else {
            deleteTagChunk(fileChannel, (int) wavTag.getEndLocationInFileOfId3Chunk(), i);
        }
    }

    private void deleteTagChunk(FileChannel fileChannel, int i, int i2) throws IOException {
        fileChannel.position(i);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) TagOptionSingleton.getInstance().getWriteChunkSize());
        while (true) {
            if (fileChannel.read(byteBufferAllocate) >= 0 || byteBufferAllocate.position() != 0) {
                byteBufferAllocate.flip();
                long jPosition = fileChannel.position();
                fileChannel.position((jPosition - i2) - byteBufferAllocate.limit());
                fileChannel.write(byteBufferAllocate);
                fileChannel.position(jPosition);
                byteBufferAllocate.compact();
            } else {
                long size = fileChannel.size() - i2;
                logger.severe(this.loggingName + "Shortening by:" + i2 + " Setting new length to:" + size);
                fileChannel.truncate(size);
                return;
            }
        }
    }

    public void write(Tag tag, Path path) throws IOException, CannotWriteException {
        logger.config(this.loggingName + " Writing tag to file:start");
        WavSaveOptions wavSaveOptions = TagOptionSingleton.getInstance().getWavSaveOptions();
        try {
            WavTag existingMetadata = getExistingMetadata(path);
            if (existingMetadata.isBadChunkData()) {
                throw new CannotWriteException("Unable to make changes to this file because contains bad chunk data");
            }
            try {
                boolean z = true;
                FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
                try {
                    WavTag wavTag = (WavTag) tag;
                    if (wavSaveOptions == WavSaveOptions.SAVE_BOTH) {
                        saveBoth(wavTag, fileChannelOpen, existingMetadata);
                    } else if (wavSaveOptions == WavSaveOptions.SAVE_ACTIVE) {
                        saveActive(wavTag, fileChannelOpen, existingMetadata);
                    } else if (wavSaveOptions == WavSaveOptions.SAVE_EXISTING_AND_ACTIVE) {
                        saveActiveExisting(wavTag, fileChannelOpen, existingMetadata);
                    } else if (wavSaveOptions == WavSaveOptions.SAVE_BOTH_AND_SYNC) {
                        wavTag.syncTagBeforeWrite();
                        saveBoth(wavTag, fileChannelOpen, existingMetadata);
                    } else if (wavSaveOptions == WavSaveOptions.SAVE_EXISTING_AND_ACTIVE_AND_SYNC) {
                        wavTag.syncTagBeforeWrite();
                        saveActiveExisting(wavTag, fileChannelOpen, existingMetadata);
                    } else {
                        throw new RuntimeException(this.loggingName + " No setting for:WavSaveOptions");
                    }
                    if (existingMetadata.isNonStandardPadding()) {
                        Iterator<ChunkSummary> it = existingMetadata.getChunkSummaryList().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ChunkSummary next = it.next();
                            if (next instanceof PaddingChunkSummary) {
                                fileChannelOpen.position(next.getFileStartLocation());
                                ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) next.getChunkSize());
                                fileChannelOpen.read(byteBufferAllocate);
                                byteBufferAllocate.flip();
                                while (byteBufferAllocate.position() < byteBufferAllocate.limit()) {
                                    if (byteBufferAllocate.get() != 0) {
                                        z = false;
                                    }
                                }
                                if (z) {
                                    fileChannelOpen.position(next.getFileStartLocation());
                                    deletePaddingChunk(fileChannelOpen, (int) next.getEndLocation(), ((int) next.getChunkSize()) + 8);
                                }
                            }
                        }
                    }
                    rewriteRiffHeaderSize(fileChannelOpen);
                    if (fileChannelOpen != null) {
                        fileChannelOpen.close();
                    }
                    logger.severe(this.loggingName + " Writing tag to file:Done");
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

    private void deletePaddingChunk(FileChannel fileChannel, int i, int i2) throws IOException {
        fileChannel.position(i);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) TagOptionSingleton.getInstance().getWriteChunkSize());
        while (true) {
            if (fileChannel.read(byteBufferAllocate) >= 0 || byteBufferAllocate.position() != 0) {
                byteBufferAllocate.flip();
                long jPosition = fileChannel.position();
                fileChannel.position((jPosition - i2) - byteBufferAllocate.limit());
                fileChannel.write(byteBufferAllocate);
                fileChannel.position(jPosition);
                byteBufferAllocate.compact();
            } else {
                long size = fileChannel.size() - i2;
                logger.config(this.loggingName + "-------------Setting new length to:" + size);
                fileChannel.truncate(size);
                return;
            }
        }
    }

    private void rewriteRiffHeaderSize(FileChannel fileChannel) throws IOException {
        fileChannel.position(IffHeaderChunk.SIGNATURE_LENGTH);
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(IffHeaderChunk.SIZE_LENGTH);
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocateDirect.putInt((((int) fileChannel.size()) - IffHeaderChunk.SIGNATURE_LENGTH) - IffHeaderChunk.SIZE_LENGTH);
        byteBufferAllocateDirect.flip();
        fileChannel.write(byteBufferAllocateDirect);
    }

    private void writeInfoDataToFile(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        if (Utils.isOddLength(fileChannel.position())) {
            writePaddingToFile(fileChannel, 1);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(WavChunkType.LIST.getCode().getBytes(StandardCharsets.US_ASCII));
        byteBufferAllocate.putInt((int) j);
        byteBufferAllocate.flip();
        fileChannel.write(byteBufferAllocate);
        fileChannel.write(byteBuffer);
        writeExtraByteIfChunkOddSize(fileChannel, j);
    }

    private void writeInfoDataToFile(FileChannel fileChannel, ByteBuffer byteBuffer) throws IOException {
        writeInfoDataToFile(fileChannel, byteBuffer, byteBuffer.limit());
    }

    private void writeId3DataToFile(FileChannel fileChannel, ByteBuffer byteBuffer) throws IOException {
        if (Utils.isOddLength(fileChannel.position())) {
            writePaddingToFile(fileChannel, 1);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        byteBufferAllocate.put(WavChunkType.ID3.getCode().getBytes(StandardCharsets.US_ASCII));
        byteBufferAllocate.putInt(byteBuffer.limit());
        byteBufferAllocate.flip();
        fileChannel.write(byteBufferAllocate);
        fileChannel.write(byteBuffer);
    }

    private void writePaddingToFile(FileChannel fileChannel, int i) throws IOException {
        fileChannel.write(ByteBuffer.allocateDirect(i));
    }

    class InfoFieldWriterOrderComparator implements Comparator<TagField> {
        InfoFieldWriterOrderComparator() {
        }

        @Override // java.util.Comparator
        public int compare(TagField tagField, TagField tagField2) {
            WavInfoIdentifier byFieldKey = WavInfoIdentifier.getByFieldKey(FieldKey.valueOf(tagField.getId()));
            WavInfoIdentifier byFieldKey2 = WavInfoIdentifier.getByFieldKey(FieldKey.valueOf(tagField2.getId()));
            return (byFieldKey != null ? byFieldKey.getPreferredWriteOrder() : Integer.MAX_VALUE) - (byFieldKey2 != null ? byFieldKey2.getPreferredWriteOrder() : Integer.MAX_VALUE);
        }
    }

    private void writeField(TagTextField tagTextField, String str, ByteArrayOutputStream byteArrayOutputStream) {
        try {
            byteArrayOutputStream.write(str.getBytes(StandardCharsets.US_ASCII));
            logger.config(this.loggingName + " Writing:" + str + ":" + tagTextField.getContent());
            byte[] bytes = tagTextField.getContent().getBytes(StandardCharsets.ISO_8859_1);
            byteArrayOutputStream.write(Utils.getSizeLEInt32(bytes.length));
            byteArrayOutputStream.write(bytes);
            if (Utils.isOddLength(bytes.length)) {
                byteArrayOutputStream.write(0);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ByteBuffer convertInfoChunk(WavTag wavTag) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        WavInfoTag infoTag = wavTag.getInfoTag();
        List<TagField> all = infoTag.getAll();
        Collections.sort(all, new InfoFieldWriterOrderComparator());
        Iterator<TagField> it = all.iterator();
        boolean z = false;
        while (it.hasNext()) {
            TagTextField tagTextField = (TagTextField) it.next();
            WavInfoIdentifier byFieldKey = WavInfoIdentifier.getByFieldKey(FieldKey.valueOf(tagTextField.getId()));
            writeField(tagTextField, byFieldKey.getCode(), byteArrayOutputStream);
            if (byFieldKey == WavInfoIdentifier.TRACKNO && TagOptionSingleton.getInstance().isWriteWavForTwonky()) {
                writeField(tagTextField, WavInfoIdentifier.TWONKY_TRACKNO.getCode(), byteArrayOutputStream);
                z = true;
            }
        }
        for (TagTextField tagTextField2 : infoTag.getUnrecognisedFields()) {
            if (tagTextField2.getId().equals(WavInfoIdentifier.TWONKY_TRACKNO.getCode())) {
                if (!z && TagOptionSingleton.getInstance().isWriteWavForTwonky()) {
                    writeField(tagTextField2, WavInfoIdentifier.TWONKY_TRACKNO.getCode(), byteArrayOutputStream);
                    z = true;
                }
            } else {
                writeField(tagTextField2, tagTextField2.getId(), byteArrayOutputStream);
            }
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        byteBufferWrap.rewind();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(IffHeaderChunk.SIGNATURE_LENGTH);
        byteBufferAllocate.put(WavChunkType.INFO.getCode().getBytes(StandardCharsets.US_ASCII));
        byteBufferAllocate.flip();
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(byteBufferAllocate.limit() + byteBufferWrap.limit());
        byteBufferAllocateDirect.put(byteBufferAllocate);
        byteBufferAllocateDirect.put(byteBufferWrap);
        byteBufferAllocateDirect.flip();
        return byteBufferAllocateDirect;
    }

    public ByteBuffer convertID3Chunk(WavTag wavTag, WavTag wavTag2) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long sizeOfID3TagOnly = wavTag2.getSizeOfID3TagOnly();
            if (sizeOfID3TagOnly > 0 && (sizeOfID3TagOnly & 1) != 0) {
                sizeOfID3TagOnly++;
            }
            if (wavTag.getID3Tag() == null) {
                wavTag.setID3Tag(WavTag.createDefaultID3Tag());
            }
            wavTag.getID3Tag().write(byteArrayOutputStream, (int) sizeOfID3TagOnly);
            if ((byteArrayOutputStream.toByteArray().length & 1) != 0) {
                int length = byteArrayOutputStream.toByteArray().length + 1;
                byteArrayOutputStream = new ByteArrayOutputStream();
                wavTag.getID3Tag().write(byteArrayOutputStream, length);
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            byteBufferWrap.rewind();
            return byteBufferWrap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class BothTagsFileStructure {
        boolean isInfoTagFirst = false;
        boolean isContiguous = false;
        boolean isAtEnd = false;

        BothTagsFileStructure() {
        }

        public String toString() {
            return "IsInfoTagFirst:" + this.isInfoTagFirst + ":isContiguous:" + this.isContiguous + ":isAtEnd:" + this.isAtEnd;
        }
    }

    private BothTagsFileStructure checkExistingLocations(WavTag wavTag, FileChannel fileChannel) throws IOException {
        BothTagsFileStructure bothTagsFileStructure = new BothTagsFileStructure();
        if (wavTag.getInfoTag().getStartLocationInFile().longValue() < wavTag.getID3Tag().getStartLocationInFile().longValue()) {
            bothTagsFileStructure.isInfoTagFirst = true;
            if (Math.abs(wavTag.getInfoTag().getEndLocationInFile().longValue() - wavTag.getStartLocationInFileOfId3Chunk()) <= 1) {
                bothTagsFileStructure.isContiguous = true;
                if (isID3TagAtEndOfFileAllowingForPaddingByte(wavTag, fileChannel)) {
                    bothTagsFileStructure.isAtEnd = true;
                }
            }
        } else if (Math.abs(wavTag.getID3Tag().getEndLocationInFile().longValue() - wavTag.getInfoTag().getStartLocationInFile().longValue()) <= 1) {
            bothTagsFileStructure.isContiguous = true;
            if (isInfoTagAtEndOfFileAllowingForPaddingByte(wavTag, fileChannel)) {
                bothTagsFileStructure.isAtEnd = true;
            }
        }
        return bothTagsFileStructure;
    }

    private void writeInfoChunk(FileChannel fileChannel, WavInfoTag wavInfoTag, ByteBuffer byteBuffer) throws IOException, CannotWriteException {
        long jLimit = byteBuffer.limit();
        if (wavInfoTag.getSizeOfTag() >= jLimit) {
            writeInfoDataToFile(fileChannel, byteBuffer, wavInfoTag.getSizeOfTag());
            if (wavInfoTag.getSizeOfTag() > jLimit) {
                writePaddingToFile(fileChannel, (int) (wavInfoTag.getSizeOfTag() - jLimit));
                return;
            }
            return;
        }
        writeInfoDataToFile(fileChannel, byteBuffer, jLimit);
    }

    private void writeExtraByteIfChunkOddSize(FileChannel fileChannel, long j) throws IOException {
        if (Utils.isOddLength(j)) {
            writePaddingToFile(fileChannel, 1);
        }
    }

    private boolean isID3TagAtEndOfFileAllowingForPaddingByte(WavTag wavTag, FileChannel fileChannel) throws IOException {
        return wavTag.getID3Tag().getEndLocationInFile().longValue() == fileChannel.size() || ((wavTag.getID3Tag().getEndLocationInFile().longValue() & 1) != 0 && wavTag.getID3Tag().getEndLocationInFile().longValue() + 1 == fileChannel.size());
    }

    private boolean isInfoTagAtEndOfFileAllowingForPaddingByte(WavTag wavTag, FileChannel fileChannel) throws IOException {
        return wavTag.getInfoTag().getEndLocationInFile().longValue() == fileChannel.size() || ((wavTag.getInfoTag().getEndLocationInFile().longValue() & 1) != 0 && wavTag.getInfoTag().getEndLocationInFile().longValue() + 1 == fileChannel.size());
    }

    private void saveBoth(WavTag wavTag, FileChannel fileChannel, WavTag wavTag2) throws IOException, CannotWriteException {
        ByteBuffer byteBufferConvertInfoChunk = convertInfoChunk(wavTag);
        long jLimit = byteBufferConvertInfoChunk.limit();
        ByteBuffer byteBufferConvertID3Chunk = convertID3Chunk(wavTag, wavTag2);
        if (WavChunkSummary.isOnlyMetadataTagsAfterStartingMetadataTag(wavTag2)) {
            deleteExistingMetadataTagsToEndOfFile(fileChannel, wavTag2);
            if (TagOptionSingleton.getInstance().getWavSaveOrder() == WavSaveOrder.INFO_THEN_ID3) {
                writeInfoChunkAtFileEnd(fileChannel, byteBufferConvertInfoChunk, jLimit);
                writeId3ChunkAtFileEnd(fileChannel, byteBufferConvertID3Chunk);
                return;
            } else {
                writeId3ChunkAtFileEnd(fileChannel, byteBufferConvertID3Chunk);
                writeInfoChunkAtFileEnd(fileChannel, byteBufferConvertInfoChunk, jLimit);
                return;
            }
        }
        if (!wavTag2.isIncorrectlyAlignedTag()) {
            if (wavTag2.getMetadataChunkSummaryList().size() > 0) {
                ListIterator<ChunkSummary> listIterator = wavTag2.getMetadataChunkSummaryList().listIterator(wavTag2.getMetadataChunkSummaryList().size());
                while (listIterator.hasPrevious()) {
                    ChunkSummary chunkSummaryPrevious = listIterator.previous();
                    logger.config(">>>>Deleting--" + chunkSummaryPrevious.getChunkId() + "---" + chunkSummaryPrevious.getFileStartLocation() + "--" + chunkSummaryPrevious.getEndLocation());
                    if (Utils.isOddLength(chunkSummaryPrevious.getEndLocation())) {
                        deleteTagChunk(fileChannel, (int) chunkSummaryPrevious.getEndLocation(), (int) ((chunkSummaryPrevious.getEndLocation() + 1) - chunkSummaryPrevious.getFileStartLocation()));
                    } else {
                        deleteTagChunk(fileChannel, (int) chunkSummaryPrevious.getEndLocation(), (int) (chunkSummaryPrevious.getEndLocation() - chunkSummaryPrevious.getFileStartLocation()));
                    }
                }
            }
            if (TagOptionSingleton.getInstance().getWavSaveOrder() == WavSaveOrder.INFO_THEN_ID3) {
                writeInfoChunkAtFileEnd(fileChannel, byteBufferConvertInfoChunk, jLimit);
                writeId3ChunkAtFileEnd(fileChannel, byteBufferConvertID3Chunk);
                return;
            } else {
                writeId3ChunkAtFileEnd(fileChannel, byteBufferConvertID3Chunk);
                writeInfoChunkAtFileEnd(fileChannel, byteBufferConvertInfoChunk, jLimit);
                return;
            }
        }
        throw new CannotWriteException(this.loggingName + " Metadata tags are corrupted and not at end of file so cannot be fixed");
    }

    public void removeAllMetadata(FileChannel fileChannel, WavTag wavTag) throws IOException, CannotWriteException {
        if (wavTag.getStartLocationInFileOfId3Chunk() > wavTag.getInfoTag().getStartLocationInFile().longValue()) {
            deleteId3TagChunk(fileChannel, wavTag, seekToStartOfId3MetadataForChunkSummaryHeader(fileChannel, wavTag));
            deleteInfoTagChunk(fileChannel, wavTag, seekToStartOfListInfoMetadata(fileChannel, wavTag));
        } else if (wavTag.getInfoTag().getStartLocationInFile().longValue() > wavTag.getStartLocationInFileOfId3Chunk()) {
            deleteInfoTagChunk(fileChannel, wavTag, seekToStartOfListInfoMetadata(fileChannel, wavTag));
            deleteId3TagChunk(fileChannel, wavTag, seekToStartOfId3MetadataForChunkSummaryHeader(fileChannel, wavTag));
        }
    }

    public void writeBothTags(FileChannel fileChannel, ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws IOException {
        if (TagOptionSingleton.getInstance().getWavSaveOrder() == WavSaveOrder.INFO_THEN_ID3) {
            writeInfoDataToFile(fileChannel, byteBuffer);
            writeId3DataToFile(fileChannel, byteBuffer2);
        } else {
            writeId3DataToFile(fileChannel, byteBuffer2);
            writeInfoDataToFile(fileChannel, byteBuffer);
        }
    }

    public void replaceInfoChunkAtFileEnd(FileChannel fileChannel, WavTag wavTag, ByteBuffer byteBuffer) throws IOException, CannotWriteException {
        ChunkHeader chunkHeaderSeekToStartOfListInfoMetadata = seekToStartOfListInfoMetadata(fileChannel, wavTag);
        if (isInfoTagAtEndOfFileAllowingForPaddingByte(wavTag, fileChannel)) {
            logger.severe("writinginfo");
            writeInfoChunk(fileChannel, wavTag.getInfoTag(), byteBuffer);
        } else {
            deleteInfoChunkAndCreateNewOneAtFileEnd(fileChannel, wavTag, chunkHeaderSeekToStartOfListInfoMetadata, byteBuffer);
        }
    }

    public void deleteOrTruncateId3Tag(FileChannel fileChannel, WavTag wavTag) throws IOException, CannotWriteException {
        if (isID3TagAtEndOfFileAllowingForPaddingByte(wavTag, fileChannel)) {
            fileChannel.truncate(wavTag.getStartLocationInFileOfId3Chunk());
        } else {
            deleteId3TagChunk(fileChannel, wavTag, seekToStartOfId3MetadataForChunkSummaryHeader(fileChannel, wavTag));
        }
    }

    public void deleteInfoChunkAndCreateNewOneAtFileEnd(FileChannel fileChannel, WavTag wavTag, ChunkHeader chunkHeader, ByteBuffer byteBuffer) throws IOException {
        deleteInfoTagChunk(fileChannel, wavTag, chunkHeader);
        fileChannel.position(fileChannel.size());
        writeInfoDataToFile(fileChannel, byteBuffer);
    }

    public void saveInfo(WavTag wavTag, FileChannel fileChannel, WavTag wavTag2) throws IOException, CannotWriteException {
        ByteBuffer byteBufferConvertInfoChunk = convertInfoChunk(wavTag);
        long jLimit = byteBufferConvertInfoChunk.limit();
        if (WavChunkSummary.isOnlyMetadataTagsAfterStartingMetadataTag(wavTag2)) {
            deleteExistingMetadataTagsToEndOfFile(fileChannel, wavTag2);
            writeInfoChunkAtFileEnd(fileChannel, byteBufferConvertInfoChunk, jLimit);
            return;
        }
        if (!wavTag2.isIncorrectlyAlignedTag()) {
            if (wavTag2.getMetadataChunkSummaryList().size() > 0) {
                ListIterator<ChunkSummary> listIterator = wavTag2.getMetadataChunkSummaryList().listIterator(wavTag2.getMetadataChunkSummaryList().size());
                while (listIterator.hasPrevious()) {
                    ChunkSummary chunkSummaryPrevious = listIterator.previous();
                    logger.config(">>>>Deleting--" + chunkSummaryPrevious.getChunkId() + "---" + chunkSummaryPrevious.getFileStartLocation() + "--" + chunkSummaryPrevious.getEndLocation());
                    if (Utils.isOddLength(chunkSummaryPrevious.getEndLocation())) {
                        deleteTagChunk(fileChannel, (int) chunkSummaryPrevious.getEndLocation(), (int) ((chunkSummaryPrevious.getEndLocation() + 1) - chunkSummaryPrevious.getFileStartLocation()));
                    } else {
                        deleteTagChunk(fileChannel, (int) chunkSummaryPrevious.getEndLocation(), (int) (chunkSummaryPrevious.getEndLocation() - chunkSummaryPrevious.getFileStartLocation()));
                    }
                }
            }
            writeInfoChunkAtFileEnd(fileChannel, byteBufferConvertInfoChunk, jLimit);
            return;
        }
        throw new CannotWriteException(this.loggingName + " Metadata tags are corrupted and not at end of file so cannot be fixed");
    }

    private void writeInfoChunkAtFileEnd(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        fileChannel.position(fileChannel.size());
        writeInfoDataToFile(fileChannel, byteBuffer, j);
    }

    private void saveId3(WavTag wavTag, FileChannel fileChannel, WavTag wavTag2) throws IOException, CannotWriteException {
        ByteBuffer byteBufferConvertID3Chunk = convertID3Chunk(wavTag, wavTag2);
        if (WavChunkSummary.isOnlyMetadataTagsAfterStartingMetadataTag(wavTag2)) {
            deleteExistingMetadataTagsToEndOfFile(fileChannel, wavTag2);
            writeId3ChunkAtFileEnd(fileChannel, byteBufferConvertID3Chunk);
            return;
        }
        if (!wavTag2.isIncorrectlyAlignedTag()) {
            if (wavTag2.getMetadataChunkSummaryList().size() > 0) {
                ListIterator<ChunkSummary> listIterator = wavTag2.getMetadataChunkSummaryList().listIterator(wavTag2.getMetadataChunkSummaryList().size());
                while (listIterator.hasPrevious()) {
                    ChunkSummary chunkSummaryPrevious = listIterator.previous();
                    logger.config(">>>>Deleting--" + chunkSummaryPrevious.getChunkId() + "---" + chunkSummaryPrevious.getFileStartLocation() + "--" + chunkSummaryPrevious.getEndLocation());
                    if (Utils.isOddLength(chunkSummaryPrevious.getEndLocation())) {
                        deleteTagChunk(fileChannel, (int) chunkSummaryPrevious.getEndLocation(), (int) ((chunkSummaryPrevious.getEndLocation() + 1) - chunkSummaryPrevious.getFileStartLocation()));
                    } else {
                        deleteTagChunk(fileChannel, (int) chunkSummaryPrevious.getEndLocation(), (int) (chunkSummaryPrevious.getEndLocation() - chunkSummaryPrevious.getFileStartLocation()));
                    }
                }
            }
            writeId3ChunkAtFileEnd(fileChannel, byteBufferConvertID3Chunk);
            return;
        }
        throw new CannotWriteException(this.loggingName + " Metadata tags are corrupted and not at end of file so cannot be fixed");
    }

    public void replaceId3ChunkAtFileEnd(FileChannel fileChannel, WavTag wavTag, ByteBuffer byteBuffer) throws IOException, CannotWriteException {
        ChunkHeader chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader = seekToStartOfId3MetadataForChunkSummaryHeader(fileChannel, wavTag);
        if (isID3TagAtEndOfFileAllowingForPaddingByte(wavTag, fileChannel)) {
            writeId3DataToFile(fileChannel, byteBuffer);
        } else {
            deleteId3ChunkAndCreateNewOneAtFileEnd(fileChannel, wavTag, chunkHeaderSeekToStartOfId3MetadataForChunkSummaryHeader, byteBuffer);
        }
    }

    public void deleteOrTruncateInfoTag(FileChannel fileChannel, WavTag wavTag) throws IOException, CannotWriteException {
        ChunkHeader chunkHeaderSeekToStartOfListInfoMetadata = seekToStartOfListInfoMetadata(fileChannel, wavTag);
        if (isInfoTagAtEndOfFileAllowingForPaddingByte(wavTag, fileChannel)) {
            fileChannel.truncate(wavTag.getInfoTag().getStartLocationInFile().longValue());
        } else {
            deleteInfoTagChunk(fileChannel, wavTag, chunkHeaderSeekToStartOfListInfoMetadata);
        }
    }

    private void writeId3ChunkAtFileEnd(FileChannel fileChannel, ByteBuffer byteBuffer) throws IOException {
        fileChannel.position(fileChannel.size());
        writeId3DataToFile(fileChannel, byteBuffer);
    }

    private void deleteId3ChunkAndCreateNewOneAtFileEnd(FileChannel fileChannel, WavTag wavTag, ChunkHeader chunkHeader, ByteBuffer byteBuffer) throws IOException {
        deleteId3TagChunk(fileChannel, wavTag, chunkHeader);
        fileChannel.position(fileChannel.size());
        writeId3DataToFile(fileChannel, byteBuffer);
    }

    private void saveActive(WavTag wavTag, FileChannel fileChannel, WavTag wavTag2) throws IOException, CannotWriteException {
        if (wavTag.getActiveTag() instanceof WavInfoTag) {
            saveInfo(wavTag, fileChannel, wavTag2);
        } else {
            saveId3(wavTag, fileChannel, wavTag2);
        }
    }

    private void saveActiveExisting(WavTag wavTag, FileChannel fileChannel, WavTag wavTag2) throws IOException, CannotWriteException {
        if (wavTag.getActiveTag() instanceof WavInfoTag) {
            if (wavTag2.isExistingId3Tag()) {
                saveBoth(wavTag, fileChannel, wavTag2);
                return;
            } else {
                saveActive(wavTag, fileChannel, wavTag2);
                return;
            }
        }
        if (wavTag2.isExistingInfoTag()) {
            saveBoth(wavTag, fileChannel, wavTag2);
        } else {
            saveActive(wavTag, fileChannel, wavTag2);
        }
    }

    private void deleteExistingMetadataTagsToEndOfFile(FileChannel fileChannel, WavTag wavTag) throws IOException {
        ChunkSummary chunkBeforeFirstMetadataTag = WavChunkSummary.getChunkBeforeFirstMetadataTag(wavTag);
        if (!Utils.isOddLength(chunkBeforeFirstMetadataTag.getEndLocation())) {
            fileChannel.truncate(chunkBeforeFirstMetadataTag.getEndLocation());
        } else {
            fileChannel.truncate(chunkBeforeFirstMetadataTag.getEndLocation() + 1);
        }
    }
}
