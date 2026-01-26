package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.mp4.Mp4AtomIdentifier;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class Mp4MetaBox extends AbstractMp4Box {
    public static final int FLAGS_LENGTH = 4;

    public Mp4MetaBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer;
    }

    public void processData() throws CannotReadException {
        byte[] bArr = new byte[4];
        this.dataBuffer.get(bArr);
        if (bArr[0] != 0) {
            throw new CannotReadException(ErrorMessage.MP4_FILE_META_ATOM_CHILD_DATA_NOT_NULL.getMsg());
        }
    }

    public static Mp4MetaBox createiTunesStyleMetaBox(int i) {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(Mp4AtomIdentifier.META.getFieldName());
        mp4BoxHeader.setLength(i + 12);
        return new Mp4MetaBox(mp4BoxHeader, ByteBuffer.allocate(4));
    }
}
