package org.jaudiotagger.audio.aiff;

import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.aiff.chunk.AiffChunkReader;
import org.jaudiotagger.audio.aiff.chunk.AiffChunkType;
import org.jaudiotagger.audio.aiff.chunk.AnnotationChunk;
import org.jaudiotagger.audio.aiff.chunk.ApplicationChunk;
import org.jaudiotagger.audio.aiff.chunk.AuthorChunk;
import org.jaudiotagger.audio.aiff.chunk.CommentsChunk;
import org.jaudiotagger.audio.aiff.chunk.CommonChunk;
import org.jaudiotagger.audio.aiff.chunk.CopyrightChunk;
import org.jaudiotagger.audio.aiff.chunk.FormatVersionChunk;
import org.jaudiotagger.audio.aiff.chunk.NameChunk;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class AiffInfoReader extends AiffChunkReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.aiff");
    private String loggingName;

    public AiffInfoReader(String str) {
        this.loggingName = str;
    }

    protected GenericAudioHeader read(Path path) throws CannotReadException, IOException {
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            logger.config(this.loggingName + ":Reading AIFF file size:" + Hex.asDecAndHex(fileChannelOpen.size()));
            AiffAudioHeader aiffAudioHeader = new AiffAudioHeader();
            long header = new AiffFileHeader(this.loggingName).readHeader(fileChannelOpen, aiffAudioHeader);
            while (true) {
                if (fileChannelOpen.position() >= 8 + header || fileChannelOpen.position() >= fileChannelOpen.size()) {
                    break;
                }
                if (!readChunk(fileChannelOpen, aiffAudioHeader)) {
                    logger.severe(path + ":UnableToReadProcessChunk");
                    break;
                }
            }
            if (aiffAudioHeader.getFileType() == AiffType.AIFC) {
                aiffAudioHeader.setFormat(SupportedFileFormat.AIF.getDisplayName());
            } else {
                aiffAudioHeader.setFormat(SupportedFileFormat.AIF.getDisplayName());
            }
            calculateBitRate(aiffAudioHeader);
            if (fileChannelOpen != null) {
                fileChannelOpen.close();
            }
            return aiffAudioHeader;
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

    private void calculateBitRate(GenericAudioHeader genericAudioHeader) throws CannotReadException {
        if (genericAudioHeader.getAudioDataLength() != null) {
            genericAudioHeader.setBitRate((int) Math.round((genericAudioHeader.getAudioDataLength().longValue() * Utils.BITS_IN_BYTE_MULTIPLIER) / (genericAudioHeader.getPreciseTrackLength() * Utils.KILOBYTE_MULTIPLIER)));
        }
    }

    private boolean readChunk(FileChannel fileChannel, AiffAudioHeader aiffAudioHeader) throws CannotReadException, IOException {
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.BIG_ENDIAN);
        if (!chunkHeader.readHeader(fileChannel)) {
            return false;
        }
        logger.config(this.loggingName + ":Reading Next Chunk:" + chunkHeader.getID() + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + Hex.asDecAndHex(chunkHeader.getSize() + 8) + ":ending at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile() + chunkHeader.getSize() + 8));
        Chunk chunkCreateChunk = createChunk(fileChannel, chunkHeader, aiffAudioHeader);
        if (chunkCreateChunk != null) {
            if (!chunkCreateChunk.readChunk()) {
                logger.severe(this.loggingName + ":ChunkReadFail:" + chunkHeader.getID());
                return false;
            }
        } else {
            if (chunkHeader.getSize() <= 0) {
                String str = this.loggingName + ":Not a valid header, unable to read a sensible size:Header" + chunkHeader.getID() + "Size:" + chunkHeader.getSize();
                logger.severe(str);
                throw new CannotReadException(str);
            }
            fileChannel.position(fileChannel.position() + chunkHeader.getSize());
        }
        IffHeaderChunk.ensureOnEqualBoundary(fileChannel, chunkHeader);
        return true;
    }

    private Chunk createChunk(FileChannel fileChannel, ChunkHeader chunkHeader, AiffAudioHeader aiffAudioHeader) throws IOException {
        AiffChunkType aiffChunkType = AiffChunkType.get(chunkHeader.getID());
        if (aiffChunkType == null) {
            return null;
        }
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[aiffChunkType.ordinal()]) {
            case 1:
                return new FormatVersionChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 2:
                return new ApplicationChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 3:
                return new CommonChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 4:
                return new CommentsChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 5:
                return new NameChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 6:
                return new AuthorChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 7:
                return new CopyrightChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 8:
                return new AnnotationChunk(chunkHeader, readChunkDataIntoBuffer(fileChannel, chunkHeader), aiffAudioHeader);
            case 9:
                aiffAudioHeader.setAudioDataLength(chunkHeader.getSize());
                aiffAudioHeader.setAudioDataStartPosition(Long.valueOf(fileChannel.position()));
                aiffAudioHeader.setAudioDataEndPosition(Long.valueOf(fileChannel.position() + chunkHeader.getSize()));
                return null;
            default:
                return null;
        }
    }

    /* renamed from: org.jaudiotagger.audio.aiff.AiffInfoReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType;

        static {
            int[] iArr = new int[AiffChunkType.values().length];
            $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType = iArr;
            try {
                iArr[AiffChunkType.FORMAT_VERSION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.APPLICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.COMMON.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.COMMENTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.AUTHOR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.COPYRIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.ANNOTATION.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$aiff$chunk$AiffChunkType[AiffChunkType.SOUND.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }
}
