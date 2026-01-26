package org.jaudiotagger.audio.dsf;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader2;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.IffHeaderChunk;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.ID3v22Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;

/* loaded from: classes3.dex */
public class DsfFileReader extends AudioFileReader2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException {
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            DsdChunk chunk = DsdChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, DsdChunk.DSD_HEADER_LENGTH));
            if (chunk != null) {
                FmtChunk chunkHeader = FmtChunk.readChunkHeader(Utils.readFileDataIntoBufferLE(fileChannelOpen, IffHeaderChunk.SIGNATURE_LENGTH + 8));
                if (chunkHeader != null) {
                    GenericAudioHeader chunkData = chunkHeader.readChunkData(chunk, fileChannelOpen);
                    if (fileChannelOpen != null) {
                        fileChannelOpen.close();
                    }
                    return chunkData;
                }
                throw new CannotReadException(path + " Not a valid dsf file. Content does not include 'fmt ' chunk");
            }
            throw new CannotReadException(path + " Not a valid dsf file. Content does not start with 'DSD '");
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

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected Tag getTag(Path path) throws CannotReadException, IOException {
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            DsdChunk chunk = DsdChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, DsdChunk.DSD_HEADER_LENGTH));
            if (chunk != null) {
                logger.config(path + ":actualFileSize:" + fileChannelOpen.size() + ":" + chunk.toString());
                Tag tag = readTag(fileChannelOpen, chunk, path.toString());
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
                return tag;
            }
            throw new CannotReadException(path + " Not a valid dsf file. Content does not start with 'DSD '.");
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

    private Tag readTag(FileChannel fileChannel, DsdChunk dsdChunk, String str) throws CannotReadException, IOException {
        if (dsdChunk.getMetadataOffset() > 0) {
            fileChannel.position(dsdChunk.getMetadataOffset());
            if (fileChannel.size() - fileChannel.position() >= DsfChunkType.ID3.getCode().length()) {
                ID3Chunk chunk = ID3Chunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannel, (int) (fileChannel.size() - fileChannel.position())));
                if (chunk != null) {
                    byte b = chunk.getDataBuffer().get(3);
                    try {
                        if (b == 2) {
                            return new ID3v22Tag(chunk.getDataBuffer(), str);
                        }
                        if (b == 3) {
                            return new ID3v23Tag(chunk.getDataBuffer(), str);
                        }
                        if (b == 4) {
                            return new ID3v24Tag(chunk.getDataBuffer(), str);
                        }
                        logger.log(Level.WARNING, str + " Unknown ID3v2 version " + ((int) b) + ". Returning an empty ID3v2 Tag.");
                        return null;
                    } catch (TagException unused) {
                        throw new CannotReadException(str + " Could not read ID3v2 tag:corruption");
                    }
                }
                logger.log(Level.WARNING, str + " No existing ID3 tag(1)");
                return null;
            }
            logger.log(Level.WARNING, str + " No existing ID3 tag(2)");
            return null;
        }
        logger.log(Level.WARNING, str + " No existing ID3 tag(3)");
        return null;
    }
}
