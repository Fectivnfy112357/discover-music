package org.jaudiotagger.audio.aiff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.aiff.chunk.AiffChunkReader;
import org.jaudiotagger.audio.aiff.chunk.AiffChunkType;
import org.jaudiotagger.audio.aiff.chunk.ID3Chunk;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.ChunkSummary;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.aiff.AiffTag;

/* loaded from: classes3.dex */
public class AiffTagReader extends AiffChunkReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.aiff");
    private String loggingName;

    public AiffTagReader(String str) {
        this.loggingName = str;
    }

    public AiffTag read(Path path) throws CannotReadException, IOException {
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            AiffAudioHeader aiffAudioHeader = new AiffAudioHeader();
            AiffTag aiffTag = new AiffTag();
            long header = new AiffFileHeader(path.toString()).readHeader(fileChannelOpen, aiffAudioHeader);
            aiffTag.setFormSize(header);
            aiffTag.setFileSize(fileChannelOpen.size());
            long j = header + 8;
            while (true) {
                if (fileChannelOpen.position() >= j || fileChannelOpen.position() >= fileChannelOpen.size()) {
                    break;
                }
                if (!readChunk(fileChannelOpen, aiffTag)) {
                    logger.severe(path + ":UnableToReadProcessChunk");
                    break;
                }
            }
            if (aiffTag.getID3Tag() == null) {
                aiffTag.setID3Tag(AiffTag.createDefaultID3Tag());
            }
            logger.config("LastChunkPos:" + Hex.asDecAndHex(fileChannelOpen.position()) + ":OfficialEndLocation:" + Hex.asDecAndHex(j));
            if (fileChannelOpen.position() > j) {
                aiffTag.setLastChunkSizeExtendsPastFormSize(true);
            }
            if (fileChannelOpen != null) {
                fileChannelOpen.close();
            }
            return aiffTag;
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

    private boolean readChunk(FileChannel fileChannel, AiffTag aiffTag) throws IOException {
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.BIG_ENDIAN);
        if (!chunkHeader.readHeader(fileChannel)) {
            return false;
        }
        logger.config(this.loggingName + ":Reading Chunk:" + chunkHeader.getID() + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
        long jPosition = fileChannel.position();
        AiffChunkType aiffChunkType = AiffChunkType.get(chunkHeader.getID());
        if (aiffChunkType != null && aiffChunkType == AiffChunkType.TAG && chunkHeader.getSize() > 0) {
            ByteBuffer chunkDataIntoBuffer = readChunkDataIntoBuffer(fileChannel, chunkHeader);
            aiffTag.addChunkSummary(new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize()));
            if (aiffTag.getID3Tag() == null) {
                new ID3Chunk(chunkHeader, chunkDataIntoBuffer, aiffTag, this.loggingName).readChunk();
                aiffTag.setExistingId3Tag(true);
                aiffTag.getID3Tag().setStartLocationInFile(jPosition);
                aiffTag.getID3Tag().setEndLocationInFile(fileChannel.position());
            } else {
                logger.warning(this.loggingName + ":Ignoring ID3Tag because already have one:" + chunkHeader.getID() + ":" + chunkHeader.getStartLocationInFile() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile() - 1) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
            }
        } else {
            if (aiffChunkType != null && aiffChunkType == AiffChunkType.CORRUPT_TAG_LATE) {
                logger.warning(this.loggingName + ":Found Corrupt ID3 Chunk, starting at Odd Location:" + chunkHeader.getID() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile() - 1) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                if (aiffTag.getID3Tag() == null) {
                    aiffTag.setIncorrectlyAlignedTag(true);
                }
                fileChannel.position(fileChannel.position() - 9);
                return true;
            }
            if (aiffChunkType == null || aiffChunkType != AiffChunkType.CORRUPT_TAG_EARLY) {
                logger.config(this.loggingName + ":Skipping Chunk:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                aiffTag.addChunkSummary(new ChunkSummary(chunkHeader.getID(), chunkHeader.getStartLocationInFile(), chunkHeader.getSize()));
                fileChannel.position(fileChannel.position() + chunkHeader.getSize());
            } else {
                logger.warning(this.loggingName + ":Found Corrupt ID3 Chunk, starting at Odd Location:" + chunkHeader.getID() + ":" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                if (aiffTag.getID3Tag() == null) {
                    aiffTag.setIncorrectlyAlignedTag(true);
                }
                fileChannel.position(fileChannel.position() - 7);
                return true;
            }
        }
        IffHeaderChunk.ensureOnEqualBoundary(fileChannel, chunkHeader);
        return true;
    }
}
