package org.jaudiotagger.audio.wav;

import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class WavCleaner {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav");
    private String loggingName;
    private Path path;

    public WavCleaner(Path path) {
        this.path = path;
        this.loggingName = path.getFileName().toString();
    }

    public void clean() throws Exception {
        System.out.println("EndOfDataChunk:" + Hex.asHex(findEndOfDataChunk()));
    }

    private int findEndOfDataChunk() throws Exception {
        FileChannel fileChannelOpen = FileChannel.open(this.path, StandardOpenOption.WRITE, StandardOpenOption.READ);
        try {
            if (WavRIFFHeader.isValidHeader(this.loggingName, fileChannelOpen)) {
                while (fileChannelOpen.position() < fileChannelOpen.size()) {
                    int chunk = readChunk(fileChannelOpen);
                    if (chunk > 0) {
                        fileChannelOpen.truncate(fileChannelOpen.position());
                        if (fileChannelOpen != null) {
                            fileChannelOpen.close();
                        }
                        return chunk;
                    }
                }
            }
            if (fileChannelOpen != null) {
                fileChannelOpen.close();
            }
            return 0;
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

    private int readChunk(FileChannel fileChannel) throws CannotReadException, IOException {
        ChunkHeader chunkHeader = new ChunkHeader(ByteOrder.LITTLE_ENDIAN);
        if (!chunkHeader.readHeader(fileChannel)) {
            return 0;
        }
        String id = chunkHeader.getID();
        logger.config(this.loggingName + " Reading Chunk:" + id + ":starting at:" + Hex.asDecAndHex(chunkHeader.getStartLocationInFile()) + ":sizeIncHeader:" + (chunkHeader.getSize() + 8));
        WavChunkType wavChunkType = WavChunkType.get(id);
        if (wavChunkType != null) {
            if (AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$wav$WavChunkType[wavChunkType.ordinal()] != 1) {
                logger.config(this.loggingName + " Skipping chunk bytes:" + chunkHeader.getSize());
                fileChannel.position(fileChannel.position() + chunkHeader.getSize());
            } else {
                fileChannel.position(fileChannel.position() + chunkHeader.getSize());
                return (int) fileChannel.position();
            }
        } else if (chunkHeader.getSize() >= 0) {
            logger.severe(this.loggingName + " Skipping chunk bytes:" + chunkHeader.getSize() + " for" + chunkHeader.getID());
            fileChannel.position(fileChannel.position() + chunkHeader.getSize());
        } else {
            String str = this.loggingName + " Not a valid header, unable to read a sensible size:Header" + chunkHeader.getID() + "Size:" + chunkHeader.getSize();
            logger.severe(str);
            throw new CannotReadException(str);
        }
        IffHeaderChunk.ensureOnEqualBoundary(fileChannel, chunkHeader);
        return 0;
    }

    /* renamed from: org.jaudiotagger.audio.wav.WavCleaner$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType;

        static {
            int[] iArr = new int[WavChunkType.values().length];
            $SwitchMap$org$jaudiotagger$audio$wav$WavChunkType = iArr;
            try {
                iArr[WavChunkType.DATA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static void main(String[] strArr) throws Exception {
        recursiveDelete(Paths.get("E:\\MQ\\Schubert, F\\The Last Six Years, vol 4-Imogen Cooper", new String[0]));
    }

    private static void recursiveDelete(Path path) throws Exception {
        for (File file : path.toFile().listFiles()) {
            if (file.isFile() && (file.getName().endsWith(".WAV") || file.getName().endsWith(".wav"))) {
                new WavCleaner(file.toPath()).clean();
            } else if (file.isDirectory()) {
                recursiveDelete(file.toPath());
            }
        }
    }
}
