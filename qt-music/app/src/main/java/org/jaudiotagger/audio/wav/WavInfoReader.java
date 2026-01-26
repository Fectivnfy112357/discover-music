package org.jaudiotagger.audio.wav;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.audio.wav.chunk.WavCorruptChunkType;
import org.jaudiotagger.audio.wav.chunk.WavFactChunk;
import org.jaudiotagger.audio.wav.chunk.WavFormatChunk;
import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class WavInfoReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav");
    private boolean isFoundAudio = false;
    private boolean isFoundFormat = false;
    private String loggingName;

    public WavInfoReader(String str) {
        this.loggingName = str;
    }

    public GenericAudioHeader read(Path path) throws CannotReadException, IOException {
        GenericAudioHeader genericAudioHeader = new GenericAudioHeader();
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            if (WavRIFFHeader.isValidHeader(this.loggingName, fileChannelOpen)) {
                while (fileChannelOpen.position() < fileChannelOpen.size() && readChunk(fileChannelOpen, genericAudioHeader)) {
                }
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
                if (this.isFoundFormat && this.isFoundAudio) {
                    genericAudioHeader.setFormat(SupportedFileFormat.WAV.getDisplayName());
                    genericAudioHeader.setLossless(true);
                    calculateTrackLength(genericAudioHeader);
                    return genericAudioHeader;
                }
                throw new CannotReadException(this.loggingName + " Unable to safetly read chunks for this file, appears to be corrupt");
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

    private void calculateTrackLength(GenericAudioHeader genericAudioHeader) throws CannotReadException {
        if (genericAudioHeader.getNoOfSamples() != null) {
            if (genericAudioHeader.getSampleRateAsNumber() > 0) {
                genericAudioHeader.setPreciseLength(genericAudioHeader.getNoOfSamples().longValue() / genericAudioHeader.getSampleRateAsNumber());
            }
        } else {
            if (genericAudioHeader.getAudioDataLength().longValue() > 0) {
                genericAudioHeader.setPreciseLength(genericAudioHeader.getAudioDataLength().longValue() / genericAudioHeader.getByteRate().intValue());
                return;
            }
            throw new CannotReadException(this.loggingName + " Wav Data Header Missing");
        }
    }

    protected boolean readChunk(FileChannel fileChannel, GenericAudioHeader genericAudioHeader) throws CannotReadException, IOException {
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        if (!chunkHeader.readHeader(fileChannel)) {
            return false;
        }
        String id = chunkHeader.getID();
        logger.info(this.loggingName + " Reading Chunk:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
        WavChunkType wavChunkType = WavChunkType.get(id);
        if (wavChunkType != null) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[wavChunkType.ordinal()];
            if (i != 1) {
                if (i == 2) {
                    genericAudioHeader.setAudioDataLength(chunkHeader.getSize());
                    genericAudioHeader.setAudioDataStartPosition(Long.valueOf(fileChannel.position()));
                    genericAudioHeader.setAudioDataEndPosition(Long.valueOf(fileChannel.position() + chunkHeader.getSize()));
                    fileChannel.position(fileChannel.position() + chunkHeader.getSize());
                    this.isFoundAudio = true;
                } else if (i != 3) {
                    if (fileChannel.position() + chunkHeader.getSize() <= fileChannel.size()) {
                        fileChannel.position(fileChannel.position() + chunkHeader.getSize());
                    } else if (this.isFoundAudio && this.isFoundFormat) {
                        logger.severe(this.loggingName + " Size of Chunk Header larger than data, skipping to file end:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                        fileChannel.position(fileChannel.size());
                    } else {
                        logger.severe(this.loggingName + " Size of Chunk Header larger than data, cannot read file");
                        throw new CannotReadException(this.loggingName + " Size of Chunk Header larger than data, cannot read file");
                    }
                } else {
                    if (!new WavFormatChunk(Utils.readFileDataIntoBufferLE(fileChannel, (int) chunkHeader.getSize()), chunkHeader, genericAudioHeader).readChunk()) {
                        return false;
                    }
                    this.isFoundFormat = true;
                }
            } else if (!new WavFactChunk(Utils.readFileDataIntoBufferLE(fileChannel, (int) chunkHeader.getSize()), chunkHeader, genericAudioHeader).readChunk()) {
                return false;
            }
        } else {
            if (id.substring(1, 3).equals(WavCorruptChunkType.CORRUPT_LIST_EARLY.getCode())) {
                logger.severe(this.loggingName + " Found Corrupt LIST Chunk, starting at Odd Location:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                fileChannel.position(fileChannel.position() - 7);
                return true;
            }
            if (id.substring(0, 3).equals(WavCorruptChunkType.CORRUPT_LIST_LATE.getCode())) {
                logger.severe(this.loggingName + " Found Corrupt LIST Chunk (2), starting at Odd Location:" + chunkHeader.getID() + ":" + chunkHeader.getSize());
                fileChannel.position(fileChannel.position() - 9);
                return true;
            }
            if (id.equals("\u0000\u0000\u0000\u0000") && chunkHeader.getSize() == 0) {
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) (fileChannel.size() - fileChannel.position()));
                fileChannel.read(byteBufferAllocate);
                byteBufferAllocate.flip();
                while (byteBufferAllocate.hasRemaining() && byteBufferAllocate.get() == 0) {
                }
                logger.severe(this.loggingName + "Found Null Padding, starting at " + chunkHeader.getStartLocationInFile() + ", size:" + byteBufferAllocate.position() + 8);
                fileChannel.position(chunkHeader.getStartLocationInFile() + byteBufferAllocate.position() + 7);
                return true;
            }
            if (chunkHeader.getSize() < 0) {
                if (this.isFoundAudio && this.isFoundFormat) {
                    logger.severe(this.loggingName + " Size of Chunk Header is negative, skipping to file end:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                    fileChannel.position(fileChannel.size());
                } else {
                    String str = this.loggingName + " Not a valid header, unable to read a sensible size:Header" + chunkHeader.getID() + "Size:" + chunkHeader.getSize();
                    logger.severe(str);
                    throw new CannotReadException(str);
                }
            } else if (fileChannel.position() + chunkHeader.getSize() <= fileChannel.size()) {
                logger.severe(this.loggingName + " Skipping chunk bytes:" + chunkHeader.getSize() + " for " + chunkHeader.getID());
                fileChannel.position(fileChannel.position() + chunkHeader.getSize());
            } else if (this.isFoundAudio && this.isFoundFormat) {
                logger.severe(this.loggingName + " Size of Chunk Header larger than data, skipping to file end:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
                fileChannel.position(fileChannel.size());
            } else {
                logger.severe(this.loggingName + " Size of Chunk Header larger than data, cannot read file");
                throw new CannotReadException(this.loggingName + " Size of Chunk Header larger than data, cannot read file");
            }
        }
        IffHeaderChunk.ensureOnEqualBoundary(fileChannel, chunkHeader);
        return true;
    }

    /* renamed from: org.jaudiotagger.audio.wav.WavInfoReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType;

        static {
            int[] iArr = new int[WavChunkType.values().length];
            $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType = iArr;
            try {
                iArr[WavChunkType.FACT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[WavChunkType.DATA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[WavChunkType.FORMAT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
