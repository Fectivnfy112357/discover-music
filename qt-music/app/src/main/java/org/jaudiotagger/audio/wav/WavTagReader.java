package org.jaudiotagger.audio.wav;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.BadChunkSummary;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.ChunkSummary;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.audio.iff.PaddingChunkSummary;
import org.jaudiotagger.audio.wav.chunk.WavCorruptChunkType;
import org.jaudiotagger.audio.wav.chunk.WavId3Chunk;
import org.jaudiotagger.audio.wav.chunk.WavListChunk;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.wav.WavInfoTag;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavTagReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav");
    private String loggingName;

    public WavTagReader(String str) {
        this.loggingName = str;
    }

    public WavTag read(Path path) throws CannotReadException, IOException {
        logger.config(this.loggingName + " Read Tag:start");
        WavTag wavTag = new WavTag(TagOptionSingleton.getInstance().getWavOptions());
        boolean z = false;
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            if (WavRIFFHeader.isValidHeader(this.loggingName, fileChannelOpen)) {
                while (fileChannelOpen.position() < fileChannelOpen.size() && readChunk(fileChannelOpen, wavTag)) {
                }
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
                Iterator<ChunkSummary> it = wavTag.getChunkSummaryList().iterator();
                while (it.hasNext()) {
                    if (it.next().getChunkId().equals(WavChunkType.DATA.getCode())) {
                        z = true;
                    }
                }
                if (!z) {
                    throw new CannotReadException(this.loggingName + " Unable to determine audio data");
                }
                createDefaultMetadataTagsIfMissing(wavTag);
                logger.config(this.loggingName + " Read Tag:end");
                return wavTag;
            }
            throw new CannotReadException(this.loggingName + " Wav RIFF Header not valid");
        } catch (Throwable th) {
            if (fileChannelOpen != null) {
                try {
                    fileChannelOpen.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void createDefaultMetadataTagsIfMissing(WavTag wavTag) {
        if (!wavTag.isExistingId3Tag()) {
            wavTag.setID3Tag(WavTag.createDefaultID3Tag());
        }
        if (wavTag.isExistingInfoTag()) {
            return;
        }
        wavTag.setInfoTag(new WavInfoTag());
    }

    protected boolean readChunk(FileChannel fileChannel, WavTag wavTag) throws CannotReadException, IOException {
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        if (!chunkHeader.readHeader(fileChannel)) {
            return false;
        }
        String id = chunkHeader.getID();
        logger.info(this.loggingName + " Reading Chunk:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
        WavChunkType wavChunkType = WavChunkType.get(id);
        if (wavChunkType != null) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[wavChunkType.ordinal()];
            if (i == 1) {
                ChunkSummary chunkSummary = new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize());
                wavTag.addChunkSummary(chunkSummary);
                wavTag.addMetadataChunkSummary(chunkSummary);
                if (wavTag.getInfoTag() == null) {
                    if (!new WavListChunk(this.loggingName, Utils.readFileDataIntoBufferLE(fileChannel, (int) chunkHeader.getSize()), chunkHeader, wavTag).readChunk()) {
                        logger.severe(this.loggingName + " LIST readChunkFailed");
                        return false;
                    }
                } else {
                    fileChannel.position(fileChannel.position() + chunkHeader.getSize());
                    logger.warning(this.loggingName + " Ignoring LIST chunk because already have one:" + chunkHeader.getID() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile() - 1) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                }
            } else if (i == 2) {
                ChunkSummary chunkSummary2 = new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize());
                wavTag.addChunkSummary(chunkSummary2);
                wavTag.addMetadataChunkSummary(chunkSummary2);
                if (wavTag.getID3Tag() == null) {
                    if (!new WavId3Chunk(Utils.readFileDataIntoBufferLE(fileChannel, (int) chunkHeader.getSize()), chunkHeader, wavTag, this.loggingName).readChunk()) {
                        logger.severe(this.loggingName + " ID3 readChunkFailed");
                        return false;
                    }
                    logger.severe(this.loggingName + " ID3 chunk should be id3:" + chunkHeader.getID() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                } else {
                    fileChannel.position(fileChannel.position() + chunkHeader.getSize());
                    logger.warning(this.loggingName + " Ignoring id3 chunk because already have one:" + chunkHeader.getID() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                }
            } else if (i == 3) {
                ChunkSummary chunkSummary3 = new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize());
                wavTag.addChunkSummary(chunkSummary3);
                wavTag.addMetadataChunkSummary(chunkSummary3);
                if (wavTag.getID3Tag() == null) {
                    if (!new WavId3Chunk(Utils.readFileDataIntoBufferLE(fileChannel, (int) chunkHeader.getSize()), chunkHeader, wavTag, this.loggingName).readChunk()) {
                        logger.severe(this.loggingName + " id3 readChunkFailed");
                        return false;
                    }
                } else {
                    fileChannel.position(fileChannel.position() + chunkHeader.getSize());
                    logger.warning(this.loggingName + " Ignoring id3 chunk because already have one:" + chunkHeader.getID() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                }
            } else {
                wavTag.addChunkSummary(new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize()));
                fileChannel.position(fileChannel.position() + chunkHeader.getSize());
            }
        } else {
            if (id.substring(1, 3).equals(WavCorruptChunkType.CORRUPT_ID3_EARLY.getCode())) {
                logger.severe(this.loggingName + " Found Corrupt id3 chunk, starting at Odd Location:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                if (wavTag.getInfoTag() == null && wavTag.getID3Tag() == null) {
                    wavTag.setIncorrectlyAlignedTag(true);
                }
                fileChannel.position(fileChannel.position() - 7);
                return true;
            }
            if (id.substring(0, 3).equals(WavCorruptChunkType.CORRUPT_ID3_LATE.getCode())) {
                logger.severe(this.loggingName + " Found Corrupt id3 chunk, starting at Odd Location:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                if (wavTag.getInfoTag() == null && wavTag.getID3Tag() == null) {
                    wavTag.setIncorrectlyAlignedTag(true);
                }
                fileChannel.position(fileChannel.position() - 9);
                return true;
            }
            if (id.substring(1, 3).equals(WavCorruptChunkType.CORRUPT_LIST_EARLY.getCode())) {
                logger.severe(this.loggingName + " Found Corrupt LIST Chunk, starting at Odd Location:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                if (wavTag.getInfoTag() == null && wavTag.getID3Tag() == null) {
                    wavTag.setIncorrectlyAlignedTag(true);
                }
                fileChannel.position(fileChannel.position() - 7);
                return true;
            }
            if (id.substring(0, 3).equals(WavCorruptChunkType.CORRUPT_LIST_LATE.getCode())) {
                logger.severe(this.loggingName + " Found Corrupt LIST Chunk (2), starting at Odd Location:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                if (wavTag.getInfoTag() == null && wavTag.getID3Tag() == null) {
                    wavTag.setIncorrectlyAlignedTag(true);
                }
                fileChannel.position(fileChannel.position() - 9);
                return true;
            }
            if (id.equals("\u0000\u0000\u0000\u0000") && chunkHeader.getSize() == 0) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) (fileChannel.size() - fileChannel.position()));
                fileChannel.read(byteBufferAllocate);
                byteBufferAllocate.flip();
                while (byteBufferAllocate.get() == 0) {
                }
                logger.severe(this.loggingName + "Found Null Padding, starting at " + chunkHeader.getStartLocationInFile() + ", size:" + byteBufferAllocate.position() + 8);
                fileChannel.position(chunkHeader.getStartLocationInFile() + byteBufferAllocate.position() + 7);
                wavTag.addChunkSummary(new PaddingChunkSummary(chunkHeader.getStartLocationInFile(), byteBufferAllocate.position() - 1));
                wavTag.setNonStandardPadding(true);
                return true;
            }
            if (chunkHeader.getSize() < 0) {
                logger.severe(this.loggingName + " Size of Chunk Header is negative, skipping to file end:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                wavTag.addChunkSummary(new BadChunkSummary(chunkHeader.getStartLocationInFile(), fileChannel.size() - fileChannel.position()));
                wavTag.setBadChunkData(true);
                fileChannel.position(fileChannel.size());
            } else if (fileChannel.position() + chunkHeader.getSize() <= fileChannel.size()) {
                logger.severe(this.loggingName + " Skipping chunk bytes:" + chunkHeader.getSize() + " for " + chunkHeader.getID());
                wavTag.addChunkSummary(new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize()));
                fileChannel.position(fileChannel.position() + chunkHeader.getSize());
            } else {
                logger.severe(this.loggingName + " Size of Chunk Header larger than data, skipping to file end:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                wavTag.addChunkSummary(new BadChunkSummary(chunkHeader.getStartLocationInFile(), fileChannel.size() - fileChannel.position()));
                wavTag.setBadChunkData(true);
                fileChannel.position(fileChannel.size());
            }
        }
        IffHeaderChunk.ensureOnEqualBoundary(fileChannel, chunkHeader);
        return true;
    }

    /* renamed from: org.jaudiotagger.audio.wav.WavTagReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType;

        static {
            int[] iArr = new int[WavChunkType.values().length];
            $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType = iArr;
            try {
                iArr[WavChunkType.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[WavChunkType.ID3_UPPERCASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[WavChunkType.ID3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
