package org.jaudiotagger.audio.mp4;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.audio.mp4.atom.Mp4MetaBox;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.Mp4NonStandardFieldKey;
import org.jaudiotagger.tag.mp4.Mp4Tag;
import org.jaudiotagger.tag.mp4.field.Mp4DiscNoField;
import org.jaudiotagger.tag.mp4.field.Mp4FieldType;
import org.jaudiotagger.tag.mp4.field.Mp4GenreField;
import org.jaudiotagger.tag.mp4.field.Mp4TagBinaryField;
import org.jaudiotagger.tag.mp4.field.Mp4TagByteField;
import org.jaudiotagger.tag.mp4.field.Mp4TagCoverField;
import org.jaudiotagger.tag.mp4.field.Mp4TagRawBinaryField;
import org.jaudiotagger.tag.mp4.field.Mp4TagReverseDnsField;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextField;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextNumberField;
import org.jaudiotagger.tag.mp4.field.Mp4TrackField;

/* loaded from: classes3.dex */
public class Mp4TagReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.mp4");

    public Mp4Tag read(Path path) throws CannotReadException, IOException {
        Mp4BoxHeader mp4BoxHeaderSeekWithinLevel;
        SeekableByteChannel seekableByteChannelNewByteChannel = Files.newByteChannel(path, new OpenOption[0]);
        try {
            Mp4Tag mp4Tag = new Mp4Tag();
            if (Mp4BoxHeader.seekWithinLevel(seekableByteChannelNewByteChannel, Mp4AtomIdentifier.MOOV.getFieldName()) == null) {
                throw new CannotReadException(ErrorMessage.MP4_FILE_NOT_CONTAINER.getMsg());
            }
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(r4.getLength() - 8);
            seekableByteChannelNewByteChannel.read(byteBufferAllocate);
            byteBufferAllocate.rewind();
            if (Mp4BoxHeader.seekWithinLevel(byteBufferAllocate, Mp4AtomIdentifier.UDTA.getFieldName()) != null) {
                Mp4BoxHeader mp4BoxHeaderSeekWithinLevel2 = Mp4BoxHeader.seekWithinLevel(byteBufferAllocate, Mp4AtomIdentifier.META.getFieldName());
                if (mp4BoxHeaderSeekWithinLevel2 == null) {
                    logger.warning(ErrorMessage.MP4_FILE_HAS_NO_METADATA.getMsg());
                    if (seekableByteChannelNewByteChannel != null) {
                        seekableByteChannelNewByteChannel.close();
                    }
                    return mp4Tag;
                }
                new Mp4MetaBox(mp4BoxHeaderSeekWithinLevel2, byteBufferAllocate).processData();
                mp4BoxHeaderSeekWithinLevel = Mp4BoxHeader.seekWithinLevel(byteBufferAllocate, Mp4AtomIdentifier.ILST.getFieldName());
                if (mp4BoxHeaderSeekWithinLevel == null) {
                    logger.warning(ErrorMessage.MP4_FILE_HAS_NO_METADATA.getMsg());
                    if (seekableByteChannelNewByteChannel != null) {
                        seekableByteChannelNewByteChannel.close();
                    }
                    return mp4Tag;
                }
            } else {
                Mp4BoxHeader mp4BoxHeaderSeekWithinLevel3 = Mp4BoxHeader.seekWithinLevel(byteBufferAllocate, Mp4AtomIdentifier.META.getFieldName());
                if (mp4BoxHeaderSeekWithinLevel3 == null) {
                    logger.warning(ErrorMessage.MP4_FILE_HAS_NO_METADATA.getMsg());
                    if (seekableByteChannelNewByteChannel != null) {
                        seekableByteChannelNewByteChannel.close();
                    }
                    return mp4Tag;
                }
                new Mp4MetaBox(mp4BoxHeaderSeekWithinLevel3, byteBufferAllocate).processData();
                mp4BoxHeaderSeekWithinLevel = Mp4BoxHeader.seekWithinLevel(byteBufferAllocate, Mp4AtomIdentifier.ILST.getFieldName());
                if (mp4BoxHeaderSeekWithinLevel == null) {
                    logger.warning(ErrorMessage.MP4_FILE_HAS_NO_METADATA.getMsg());
                    if (seekableByteChannelNewByteChannel != null) {
                        seekableByteChannelNewByteChannel.close();
                    }
                    return mp4Tag;
                }
            }
            int length = mp4BoxHeaderSeekWithinLevel.getLength() - 8;
            ByteBuffer byteBufferSlice = byteBufferAllocate.slice();
            logger.config("headerlengthsays:" + length + "datalength:" + byteBufferSlice.limit());
            logger.config("Started to read metadata fields at position is in metadata buffer:" + byteBufferSlice.position());
            for (int length2 = 0; length2 < length; length2 += mp4BoxHeaderSeekWithinLevel.getLength()) {
                mp4BoxHeaderSeekWithinLevel.update(byteBufferSlice);
                logger.config("Next position is at:" + byteBufferSlice.position());
                createMp4Field(mp4Tag, mp4BoxHeaderSeekWithinLevel, byteBufferSlice.slice());
                byteBufferSlice.position(byteBufferSlice.position() + mp4BoxHeaderSeekWithinLevel.getDataLength());
            }
            if (seekableByteChannelNewByteChannel != null) {
                seekableByteChannelNewByteChannel.close();
            }
            return mp4Tag;
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

    private void createMp4Field(Mp4Tag mp4Tag, Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        if (mp4BoxHeader.getDataLength() == 0) {
            return;
        }
        if (mp4BoxHeader.getId().equals("----")) {
            try {
                mp4Tag.addField(new Mp4TagReverseDnsField(mp4BoxHeader, byteBuffer));
                return;
            } catch (Exception e) {
                logger.warning(ErrorMessage.MP4_UNABLE_READ_REVERSE_DNS_FIELD.getMsg(e.getMessage()));
                mp4Tag.addField(new Mp4TagRawBinaryField(mp4BoxHeader, byteBuffer));
                return;
            }
        }
        int iPosition = byteBuffer.position();
        boolean zEquals = Utils.getString(byteBuffer, 4, 4, StandardCharsets.ISO_8859_1).equals("data");
        byteBuffer.position(iPosition);
        if (zEquals) {
            int intBE = Utils.getIntBE(byteBuffer, 9, 11);
            Mp4FieldType fieldType = Mp4FieldType.getFieldType(intBE);
            logger.config("Box Type id:" + mp4BoxHeader.getId() + ":type:" + fieldType);
            if (mp4BoxHeader.getId().equals(Mp4FieldKey.TRACK.getFieldName())) {
                mp4Tag.addField(new Mp4TrackField(mp4BoxHeader.getId(), byteBuffer));
                return;
            }
            if (mp4BoxHeader.getId().equals(Mp4FieldKey.DISCNUMBER.getFieldName())) {
                mp4Tag.addField(new Mp4DiscNoField(mp4BoxHeader.getId(), byteBuffer));
                return;
            }
            if (mp4BoxHeader.getId().equals(Mp4FieldKey.GENRE.getFieldName())) {
                mp4Tag.addField(new Mp4GenreField(mp4BoxHeader.getId(), byteBuffer));
                return;
            }
            int dataAndHeaderSize = 0;
            if (mp4BoxHeader.getId().equals(Mp4FieldKey.ARTWORK.getFieldName()) || Mp4FieldType.isCoverArtType(fieldType)) {
                int i = 0;
                while (dataAndHeaderSize < mp4BoxHeader.getDataLength()) {
                    if (i > 0) {
                        fieldType = Mp4FieldType.getFieldType(Utils.getIntBE(byteBuffer, dataAndHeaderSize + 9, dataAndHeaderSize + 11));
                    }
                    Mp4TagCoverField mp4TagCoverField = new Mp4TagCoverField(byteBuffer, fieldType);
                    mp4Tag.addField(mp4TagCoverField);
                    dataAndHeaderSize += mp4TagCoverField.getDataAndHeaderSize();
                    i++;
                }
                return;
            }
            if (fieldType == Mp4FieldType.TEXT) {
                mp4Tag.addField(new Mp4TagTextField(mp4BoxHeader.getId(), byteBuffer));
                return;
            }
            if (fieldType == Mp4FieldType.IMPLICIT) {
                mp4Tag.addField(new Mp4TagTextNumberField(mp4BoxHeader.getId(), byteBuffer));
                return;
            }
            if (fieldType == Mp4FieldType.INTEGER) {
                mp4Tag.addField(new Mp4TagByteField(mp4BoxHeader.getId(), byteBuffer));
                return;
            }
            Mp4FieldKey[] mp4FieldKeyArrValues = Mp4FieldKey.values();
            int length = mp4FieldKeyArrValues.length;
            while (dataAndHeaderSize < length) {
                if (mp4FieldKeyArrValues[dataAndHeaderSize].getFieldName().equals(mp4BoxHeader.getId())) {
                    logger.warning("Known Field:" + mp4BoxHeader.getId() + " with invalid field type of:" + intBE + " is ignored");
                    return;
                }
                dataAndHeaderSize++;
            }
            logger.warning("UnKnown Field:" + mp4BoxHeader.getId() + " with invalid field type of:" + intBE + " created as binary");
            mp4Tag.addField(new Mp4TagBinaryField(mp4BoxHeader.getId(), byteBuffer));
            return;
        }
        if (mp4BoxHeader.getId().equals(Mp4NonStandardFieldKey.AAPR.getFieldName())) {
            mp4Tag.addField(new Mp4TagRawBinaryField(mp4BoxHeader, byteBuffer));
        } else {
            mp4Tag.addField(new Mp4TagRawBinaryField(mp4BoxHeader, byteBuffer));
        }
    }
}
