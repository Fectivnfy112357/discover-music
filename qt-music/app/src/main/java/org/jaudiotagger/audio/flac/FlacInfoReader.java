package org.jaudiotagger.audio.flac;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.flac.metadatablock.BlockType;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataStreamInfo;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockHeader;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class FlacInfoReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac");

    public FlacAudioHeader read(Path path) throws CannotReadException, IOException {
        logger.config(path + ":start");
        boolean zIsLastBlock = false;
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            new FlacStreamReader(fileChannelOpen, path.toString() + " ").findStream();
            MetadataBlockDataStreamInfo metadataBlockDataStreamInfo = null;
            while (!zIsLastBlock) {
                MetadataBlockHeader header = MetadataBlockHeader.readHeader(fileChannelOpen);
                logger.info(path.toString() + " " + header.toString());
                if (header.getBlockType() == BlockType.STREAMINFO) {
                    if (header.getDataLength() == 0) {
                        throw new CannotReadException(path + ":FLAC StreamInfo has zeo data length");
                    }
                    metadataBlockDataStreamInfo = new MetadataBlockDataStreamInfo(header, fileChannelOpen);
                    if (!metadataBlockDataStreamInfo.isValid()) {
                        throw new CannotReadException(path + ":FLAC StreamInfo not valid");
                    }
                } else {
                    fileChannelOpen.position(fileChannelOpen.position() + header.getDataLength());
                }
                zIsLastBlock = header.isLastBlock();
            }
            long jPosition = fileChannelOpen.position();
            if (metadataBlockDataStreamInfo == null) {
                throw new CannotReadException(path + ":Unable to find Flac StreamInfo");
            }
            FlacAudioHeader flacAudioHeader = new FlacAudioHeader();
            flacAudioHeader.setNoOfSamples(Long.valueOf(metadataBlockDataStreamInfo.getNoOfSamples()));
            flacAudioHeader.setPreciseLength(metadataBlockDataStreamInfo.getPreciseLength());
            flacAudioHeader.setChannelNumber(metadataBlockDataStreamInfo.getNoOfChannels());
            flacAudioHeader.setSamplingRate(metadataBlockDataStreamInfo.getSamplingRate());
            flacAudioHeader.setBitsPerSample(metadataBlockDataStreamInfo.getBitsPerSample());
            flacAudioHeader.setEncodingType(metadataBlockDataStreamInfo.getEncodingType());
            flacAudioHeader.setFormat(SupportedFileFormat.FLAC.getDisplayName());
            flacAudioHeader.setLossless(true);
            flacAudioHeader.setMd5(metadataBlockDataStreamInfo.getMD5Signature());
            flacAudioHeader.setAudioDataLength(fileChannelOpen.size() - jPosition);
            flacAudioHeader.setAudioDataStartPosition(Long.valueOf(jPosition));
            flacAudioHeader.setAudioDataEndPosition(Long.valueOf(fileChannelOpen.size()));
            flacAudioHeader.setBitRate(computeBitrate(flacAudioHeader.getAudioDataLength().longValue(), metadataBlockDataStreamInfo.getPreciseLength()));
            if (fileChannelOpen != null) {
                fileChannelOpen.close();
            }
            return flacAudioHeader;
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

    private int computeBitrate(long j, float f) {
        return (int) (((j / Utils.KILOBYTE_MULTIPLIER) * Utils.BITS_IN_BYTE_MULTIPLIER) / f);
    }

    public int countMetaBlocks(File file) throws CannotReadException, IOException {
        boolean zIsLastBlock = false;
        FileChannel fileChannelOpen = FileChannel.open(file.toPath(), new OpenOption[0]);
        try {
            new FlacStreamReader(fileChannelOpen, file.toPath().toString() + " ").findStream();
            int i = 0;
            while (!zIsLastBlock) {
                MetadataBlockHeader header = MetadataBlockHeader.readHeader(fileChannelOpen);
                logger.config(file + ":Found block:" + header.getBlockType());
                fileChannelOpen.position(fileChannelOpen.position() + header.getDataLength());
                zIsLastBlock = header.isLastBlock();
                i++;
            }
            if (fileChannelOpen != null) {
                fileChannelOpen.close();
            }
            return i;
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
}
