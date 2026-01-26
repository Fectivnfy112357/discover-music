package org.jaudiotagger.audio.ogg.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Logger;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

/* loaded from: classes3.dex */
public class OggInfoReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg.atom");

    public GenericAudioHeader read(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        double absoluteGranulePosition;
        long filePointer = randomAccessFile.getFilePointer();
        GenericAudioHeader genericAudioHeader = new GenericAudioHeader();
        logger.fine("Started");
        byte[] bArr = new byte[OggPageHeader.CAPTURE_PATTERN.length];
        randomAccessFile.read(bArr);
        if (!Arrays.equals(bArr, OggPageHeader.CAPTURE_PATTERN)) {
            randomAccessFile.seek(0L);
            if (AbstractID3v2Tag.isId3Tag(randomAccessFile)) {
                randomAccessFile.read(bArr);
                if (Arrays.equals(bArr, OggPageHeader.CAPTURE_PATTERN)) {
                    filePointer = randomAccessFile.getFilePointer();
                }
            } else {
                throw new CannotReadException(ErrorMessage.OGG_HEADER_CANNOT_BE_FOUND.getMsg(new String(bArr)));
            }
        }
        randomAccessFile.seek(filePointer);
        randomAccessFile.seek(randomAccessFile.length() - 2);
        while (true) {
            if (randomAccessFile.getFilePointer() < 4) {
                absoluteGranulePosition = -1.0d;
                break;
            }
            if (randomAccessFile.read() == OggPageHeader.CAPTURE_PATTERN[3]) {
                randomAccessFile.seek(randomAccessFile.getFilePointer() - 4);
                byte[] bArr2 = new byte[3];
                randomAccessFile.readFully(bArr2);
                if (bArr2[0] == OggPageHeader.CAPTURE_PATTERN[0] && bArr2[1] == OggPageHeader.CAPTURE_PATTERN[1] && bArr2[2] == OggPageHeader.CAPTURE_PATTERN[2]) {
                    randomAccessFile.seek(randomAccessFile.getFilePointer() - 3);
                    long filePointer2 = randomAccessFile.getFilePointer();
                    randomAccessFile.seek(randomAccessFile.getFilePointer() + 26);
                    int i = randomAccessFile.readByte() & 255;
                    randomAccessFile.seek(filePointer2);
                    byte[] bArr3 = new byte[i + 27];
                    randomAccessFile.readFully(bArr3);
                    OggPageHeader oggPageHeader = new OggPageHeader(bArr3);
                    randomAccessFile.seek(0L);
                    absoluteGranulePosition = oggPageHeader.getAbsoluteGranulePosition();
                    break;
                }
            }
            randomAccessFile.seek(randomAccessFile.getFilePointer() - 2);
        }
        if (absoluteGranulePosition == -1.0d) {
            throw new CannotReadException(ErrorMessage.OGG_VORBIS_NO_SETUP_BLOCK.getMsg());
        }
        int pageLength = OggPageHeader.read(randomAccessFile).getPageLength();
        byte[] bArr4 = new byte[pageLength];
        if (pageLength < 27) {
            throw new CannotReadException("Invalid Identification header for this Ogg File");
        }
        randomAccessFile.read(bArr4);
        VorbisIdentificationHeader vorbisIdentificationHeader = new VorbisIdentificationHeader(bArr4);
        genericAudioHeader.setPreciseLength((float) (absoluteGranulePosition / vorbisIdentificationHeader.getSamplingRate()));
        genericAudioHeader.setChannelNumber(vorbisIdentificationHeader.getChannelNumber());
        genericAudioHeader.setSamplingRate(vorbisIdentificationHeader.getSamplingRate());
        genericAudioHeader.setEncodingType(vorbisIdentificationHeader.getEncodingType());
        genericAudioHeader.setFormat(SupportedFileFormat.OGG.getDisplayName());
        genericAudioHeader.setBitsPerSample(16);
        if (vorbisIdentificationHeader.getNominalBitrate() != 0 && vorbisIdentificationHeader.getMaxBitrate() == vorbisIdentificationHeader.getNominalBitrate() && vorbisIdentificationHeader.getMinBitrate() == vorbisIdentificationHeader.getNominalBitrate()) {
            genericAudioHeader.setBitRate(vorbisIdentificationHeader.getNominalBitrate() / 1000);
            genericAudioHeader.setVariableBitRate(false);
        } else if (vorbisIdentificationHeader.getNominalBitrate() != 0 && vorbisIdentificationHeader.getMaxBitrate() == 0 && vorbisIdentificationHeader.getMinBitrate() == 0) {
            genericAudioHeader.setBitRate(vorbisIdentificationHeader.getNominalBitrate() / 1000);
            genericAudioHeader.setVariableBitRate(true);
        } else {
            genericAudioHeader.setBitRate(computeBitrate(genericAudioHeader.getTrackLength(), randomAccessFile.length()));
            genericAudioHeader.setVariableBitRate(true);
            return genericAudioHeader;
        }
        return genericAudioHeader;
    }

    private int computeBitrate(int i, long j) {
        if (i == 0) {
            i = 1;
        }
        return (int) (((j / Utils.KILOBYTE_MULTIPLIER) * Utils.BITS_IN_BYTE_MULTIPLIER) / i);
    }
}
