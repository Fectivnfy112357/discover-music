package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidChunkException;
import org.jaudiotagger.audio.generic.AudioFileReader2;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class DffFileReader extends AudioFileReader2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected Tag getTag(Path path) throws CannotReadException, IOException {
        return null;
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException {
        PropChunk chunk;
        BaseChunk idChunk;
        long jLongValue;
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            if (Frm8Chunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, 12)) != null) {
                if (DsdChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, 8)) == null) {
                    throw new CannotReadException(path + " Not a valid dff file. Missing 'DSD '  after 'FRM8' ");
                }
                do {
                    chunk = PropChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, 12));
                } while (chunk == null);
                if (chunk == null) {
                    throw new CannotReadException(path + " Not a valid dff file. Content does not have 'PROP'");
                }
                if (SndChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, 4)) == null) {
                    throw new CannotReadException(path + " Not a valid dff file. Missing 'SND '  after 'PROP' ");
                }
                ChnlChunk chnlChunk = null;
                FsChunk fsChunk = null;
                DstChunk dstChunk = null;
                FrteChunk frteChunk = null;
                while (true) {
                    try {
                        idChunk = BaseChunk.readIdChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, 4));
                        if (idChunk instanceof FsChunk) {
                            fsChunk = (FsChunk) idChunk;
                            fsChunk.readDataChunch(fileChannelOpen);
                        } else if (idChunk instanceof ChnlChunk) {
                            chnlChunk = (ChnlChunk) idChunk;
                            chnlChunk.readDataChunch(fileChannelOpen);
                        } else if (idChunk instanceof CmprChunk) {
                            ((CmprChunk) idChunk).readDataChunch(fileChannelOpen);
                        } else if (idChunk instanceof DitiChunk) {
                            ((DitiChunk) idChunk).readDataChunch(fileChannelOpen);
                        } else {
                            if (idChunk instanceof EndChunk) {
                                break;
                            }
                            if (idChunk instanceof DstChunk) {
                                dstChunk = (DstChunk) idChunk;
                                dstChunk.readDataChunch(fileChannelOpen);
                                try {
                                    frteChunk = (FrteChunk) BaseChunk.readIdChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, 4));
                                    if (frteChunk != null) {
                                        frteChunk.readDataChunch(fileChannelOpen);
                                    }
                                } catch (InvalidChunkException unused) {
                                    throw new CannotReadException(path + "Not a valid dft file. Missing 'FRTE' chunk");
                                }
                            } else if (idChunk instanceof Id3Chunk) {
                                ((Id3Chunk) idChunk).readDataChunch(fileChannelOpen);
                            }
                        }
                    } catch (InvalidChunkException unused2) {
                    }
                }
                EndChunk endChunk = (EndChunk) idChunk;
                endChunk.readDataChunch(fileChannelOpen);
                if (chnlChunk == null) {
                    throw new CannotReadException(path + " Not a valid dff file. Missing 'CHNL' chunk");
                }
                if (fsChunk == null) {
                    throw new CannotReadException(path + " Not a valid dff file. Missing 'FS' chunk");
                }
                if (dstChunk != null && frteChunk == null) {
                    throw new CannotReadException(path + " Not a valid dst file. Missing 'FRTE' chunk");
                }
                if (endChunk == null && dstChunk == null) {
                    throw new CannotReadException(path + " Not a valid dff file. Missing 'DSD' end chunk");
                }
                short sShortValue = chnlChunk.getNumChannels().shortValue();
                int sampleRate = fsChunk.getSampleRate();
                if (dstChunk != null) {
                    jLongValue = (frteChunk.getNumFrames() / frteChunk.getRate().shortValue()) * sampleRate;
                } else {
                    jLongValue = (endChunk.getDataEnd().longValue() - endChunk.getDataStart().longValue()) * (8 / sShortValue);
                }
                GenericAudioHeader genericAudioHeaderBuildAudioHeader = buildAudioHeader(sShortValue, sampleRate, jLongValue, 1, dstChunk != null);
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
                return genericAudioHeaderBuildAudioHeader;
            }
            throw new CannotReadException(path + " Not a valid dff file. Content does not start with 'FRM8'");
        } finally {
        }
    }

    private GenericAudioHeader buildAudioHeader(int i, int i2, long j, int i3, boolean z) {
        GenericAudioHeader genericAudioHeader = new GenericAudioHeader();
        genericAudioHeader.setEncodingType("DFF");
        genericAudioHeader.setBitRate(i3 * i2 * i);
        genericAudioHeader.setBitsPerSample(i3);
        genericAudioHeader.setChannelNumber(i);
        genericAudioHeader.setSamplingRate(i2);
        genericAudioHeader.setNoOfSamples(Long.valueOf(j));
        genericAudioHeader.setPreciseLength(j / i2);
        genericAudioHeader.setVariableBitRate(z);
        logger.log(Level.FINE, "Created audio header: " + genericAudioHeader);
        return genericAudioHeader;
    }
}
