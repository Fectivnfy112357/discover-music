package org.jaudiotagger.audio.mp4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotReadVideoException;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.Mp4AlacBox;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.audio.mp4.atom.Mp4DrmsBox;
import org.jaudiotagger.audio.mp4.atom.Mp4EsdsBox;
import org.jaudiotagger.audio.mp4.atom.Mp4FtypBox;
import org.jaudiotagger.audio.mp4.atom.Mp4MdhdBox;
import org.jaudiotagger.audio.mp4.atom.Mp4Mp4aBox;
import org.jaudiotagger.audio.mp4.atom.Mp4MvhdBox;
import org.jaudiotagger.audio.mp4.atom.Mp4StcoBox;
import org.jaudiotagger.audio.mp4.atom.Mp4StsdBox;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class Mp4InfoReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.mp4.atom");

    private boolean isTrackAtomVideo(Mp4FtypBox mp4FtypBox, Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) throws IOException {
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel;
        if (Mp4BoxHeader.seekWithinLevel(byteBuffer, Mp4AtomIdentifier.MDIA.getFieldName()) == null || (mp4BoxHeaderSeekWithinLevel = Mp4BoxHeader.seekWithinLevel(byteBuffer, Mp4AtomIdentifier.MDHD.getFieldName())) == null) {
            return false;
        }
        byteBuffer.position(byteBuffer.position() + mp4BoxHeaderSeekWithinLevel.getDataLength());
        return (Mp4BoxHeader.seekWithinLevel(byteBuffer, Mp4AtomIdentifier.MINF.getFieldName()) == null || Mp4BoxHeader.seekWithinLevel(byteBuffer, Mp4AtomIdentifier.VMHD.getFieldName()) == null) ? false : true;
    }

    public GenericAudioHeader read(Path path) throws CannotReadException, IOException {
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel;
        SeekableByteChannel seekableByteChannelNewByteChannel = Files.newByteChannel(path, new OpenOption[0]);
        try {
            Mp4AudioHeader mp4AudioHeader = new Mp4AudioHeader();
            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel2 = Mp4BoxHeader.seekWithinLevel(seekableByteChannelNewByteChannel, Mp4AtomIdentifier.FTYP.getFieldName());
            if (mp4BoxHeaderSeekWithinLevel2 == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_CONTAINER.getMsg());
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(mp4BoxHeaderSeekWithinLevel2.getLength() - 8);
            seekableByteChannelNewByteChannel.read(byteBufferAllocate);
            byteBufferAllocate.rewind();
            Mp4FtypBox mp4FtypBox = new Mp4FtypBox(mp4BoxHeaderSeekWithinLevel2, byteBufferAllocate);
            mp4FtypBox.processData();
            mp4AudioHeader.setBrand(mp4FtypBox.getMajorBrand());
            if (Mp4BoxHeader.seekWithinLevel(seekableByteChannelNewByteChannel, Mp4AtomIdentifier.MOOV.getFieldName()) == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(r2.getLength() - 8);
            byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
            seekableByteChannelNewByteChannel.read(byteBufferAllocate2);
            byteBufferAllocate2.rewind();
            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel3 = Mp4BoxHeader.seekWithinLevel(byteBufferAllocate2, Mp4AtomIdentifier.MVHD.getFieldName());
            if (mp4BoxHeaderSeekWithinLevel3 == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            ByteBuffer byteBufferSlice = byteBufferAllocate2.slice();
            mp4AudioHeader.setPreciseLength(new Mp4MvhdBox(mp4BoxHeaderSeekWithinLevel3, byteBufferSlice).getPreciseLength());
            byteBufferSlice.position(byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel3.getDataLength());
            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel4 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.TRAK.getFieldName());
            if (mp4BoxHeaderSeekWithinLevel4 == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            int iPosition = byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel4.getDataLength();
            if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MDIA.getFieldName()) == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel5 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MDHD.getFieldName());
            if (mp4BoxHeaderSeekWithinLevel5 == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            mp4AudioHeader.setSamplingRate(new Mp4MdhdBox(mp4BoxHeaderSeekWithinLevel5, byteBufferSlice.slice()).getSampleRate());
            byteBufferSlice.position(byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel5.getDataLength());
            if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MINF.getFieldName()) == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            int iPosition2 = byteBufferSlice.position();
            if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.SMHD.getFieldName()) == null) {
                byteBufferSlice.position(iPosition2);
                if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.VMHD.getFieldName()) != null) {
                    throw new CannotReadVideoException(ErrorMessage.MP4_FILE_IS_VIDEO.getMsg());
                }
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            byteBufferSlice.position(iPosition2);
            if (Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.STBL.getFieldName()) == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_AUDIO.getMsg());
            }
            int iPosition3 = byteBufferSlice.position();
            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel6 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.STSD.getFieldName());
            if (mp4BoxHeaderSeekWithinLevel6 != null) {
                new Mp4StsdBox(mp4BoxHeaderSeekWithinLevel6, byteBufferSlice).processData();
                int iPosition4 = byteBufferSlice.position();
                Mp4BoxHeader mp4BoxHeaderSeekWithinLevel7 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.MP4A.getFieldName());
                if (mp4BoxHeaderSeekWithinLevel7 != null) {
                    ByteBuffer byteBufferSlice2 = byteBufferSlice.slice();
                    new Mp4Mp4aBox(mp4BoxHeaderSeekWithinLevel7, byteBufferSlice2).processData();
                    Mp4BoxHeader mp4BoxHeaderSeekWithinLevel8 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice2, Mp4AtomIdentifier.ESDS.getFieldName());
                    if (mp4BoxHeaderSeekWithinLevel8 != null) {
                        Mp4EsdsBox mp4EsdsBox = new Mp4EsdsBox(mp4BoxHeaderSeekWithinLevel8, byteBufferSlice2.slice());
                        mp4AudioHeader.setBitRate(mp4EsdsBox.getAvgBitrate() / Utils.KILOBYTE_MULTIPLIER);
                        mp4AudioHeader.setChannelNumber(mp4EsdsBox.getNumberOfChannels());
                        mp4AudioHeader.setKind(mp4EsdsBox.getKind());
                        mp4AudioHeader.setProfile(mp4EsdsBox.getAudioProfile());
                        mp4AudioHeader.setEncodingType(EncoderType.AAC.getDescription());
                    }
                } else {
                    byteBufferSlice.position(iPosition4);
                    Mp4BoxHeader mp4BoxHeaderSeekWithinLevel9 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.DRMS.getFieldName());
                    if (mp4BoxHeaderSeekWithinLevel9 != null) {
                        new Mp4DrmsBox(mp4BoxHeaderSeekWithinLevel9, byteBufferSlice).processData();
                        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel10 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.ESDS.getFieldName());
                        if (mp4BoxHeaderSeekWithinLevel10 != null) {
                            Mp4EsdsBox mp4EsdsBox2 = new Mp4EsdsBox(mp4BoxHeaderSeekWithinLevel10, byteBufferSlice.slice());
                            mp4AudioHeader.setBitRate(mp4EsdsBox2.getAvgBitrate() / Utils.KILOBYTE_MULTIPLIER);
                            mp4AudioHeader.setChannelNumber(mp4EsdsBox2.getNumberOfChannels());
                            mp4AudioHeader.setKind(mp4EsdsBox2.getKind());
                            mp4AudioHeader.setProfile(mp4EsdsBox2.getAudioProfile());
                            mp4AudioHeader.setEncodingType(EncoderType.DRM_AAC.getDescription());
                        }
                    } else {
                        byteBufferSlice.position(iPosition4);
                        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel11 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.ALAC.getFieldName());
                        if (mp4BoxHeaderSeekWithinLevel11 != null) {
                            new Mp4AlacBox(mp4BoxHeaderSeekWithinLevel11, byteBufferSlice).processData();
                            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel12 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.ALAC.getFieldName());
                            if (mp4BoxHeaderSeekWithinLevel12 != null) {
                                Mp4AlacBox mp4AlacBox = new Mp4AlacBox(mp4BoxHeaderSeekWithinLevel12, byteBufferSlice);
                                mp4AlacBox.processData();
                                mp4AudioHeader.setEncodingType(EncoderType.APPLE_LOSSLESS.getDescription());
                                mp4AudioHeader.setChannelNumber(mp4AlacBox.getChannels());
                                mp4AudioHeader.setBitRate(mp4AlacBox.getBitRate() / Utils.KILOBYTE_MULTIPLIER);
                                mp4AudioHeader.setBitsPerSample(mp4AlacBox.getSampleSize());
                            }
                        }
                    }
                }
            }
            byteBufferSlice.position(iPosition3);
            Mp4BoxHeader mp4BoxHeaderSeekWithinLevel13 = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.STCO.getFieldName());
            if (mp4BoxHeaderSeekWithinLevel13 != null) {
                Mp4StcoBox mp4StcoBox = new Mp4StcoBox(mp4BoxHeaderSeekWithinLevel13, byteBufferSlice);
                mp4AudioHeader.setAudioDataStartPosition(Long.valueOf(mp4StcoBox.getFirstOffSet()));
                mp4AudioHeader.setAudioDataEndPosition(Long.valueOf(seekableByteChannelNewByteChannel.size()));
                mp4AudioHeader.setAudioDataLength(seekableByteChannelNewByteChannel.size() - mp4StcoBox.getFirstOffSet());
            }
            if (mp4AudioHeader.getChannelNumber() == -1) {
                mp4AudioHeader.setChannelNumber(2);
            }
            if (mp4AudioHeader.getBitRateAsNumber() == -1) {
                mp4AudioHeader.setBitRate(128);
            }
            if (mp4AudioHeader.getBitsPerSample() == -1) {
                mp4AudioHeader.setBitsPerSample(16);
            }
            if (mp4AudioHeader.getEncodingType().equals("")) {
                mp4AudioHeader.setEncodingType(EncoderType.AAC.getDescription());
            }
            logger.config(mp4AudioHeader.toString());
            byteBufferSlice.position(iPosition);
            while (byteBufferSlice.hasRemaining() && (mp4BoxHeaderSeekWithinLevel = Mp4BoxHeader.seekWithinLevel(byteBufferSlice, Mp4AtomIdentifier.TRAK.getFieldName())) != null) {
                if (isTrackAtomVideo(mp4FtypBox, mp4BoxHeaderSeekWithinLevel, byteBufferSlice)) {
                    throw new CannotReadVideoException(ErrorMessage.MP4_FILE_IS_VIDEO.getMsg());
                }
            }
            mp4AudioHeader.setFormat(mp4AudioHeader.getEncodingType());
            new Mp4AtomTree(seekableByteChannelNewByteChannel, false);
            if (seekableByteChannelNewByteChannel != null) {
                seekableByteChannelNewByteChannel.close();
            }
            return mp4AudioHeader;
        } catch (Throwable th) {
            if (seekableByteChannelNewByteChannel != null) {
                try {
                    seekableByteChannelNewByteChannel.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
