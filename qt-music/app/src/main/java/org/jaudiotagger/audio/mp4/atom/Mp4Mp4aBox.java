package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.exceptions.CannotReadException;

/* loaded from: classes3.dex */
public class Mp4Mp4aBox extends AbstractMp4Box {
    public static final int AUDIO_COMPRESSION_ID_LENGTH = 2;
    public static final int AUDIO_COMPRESSION_ID_POS = 20;
    public static final int AUDIO_ENCODING_LENGTH = 2;
    public static final int AUDIO_ENCODING_POS = 8;
    public static final int AUDIO_ENCODING_VENDOR_LENGTH = 4;
    public static final int AUDIO_ENCODING_VENDOR_POS = 12;
    public static final int AUDIO_PACKET_SIZE_LENGTH = 2;
    public static final int AUDIO_PACKET_SIZE_POS = 22;
    public static final int AUDIO_REVISION_LENGTH = 2;
    public static final int AUDIO_REVISION_POS = 10;
    public static final int AUDIO_SAMPLE_RATE_LENGTH = 4;
    public static final int AUDIO_SAMPLE_RATE_POS = 24;
    public static final int AUDIO_SAMPLE_SIZE_LENGTH = 2;
    public static final int AUDIO_SAMPLE_SIZE_POS = 18;
    public static final int CHANNELS_LENGTH = 2;
    public static final int CHANNELS_POS = 16;
    public static final int REFERENCE_INDEX_LENGTH = 2;
    public static final int REFERENCE_INDEX_POS = 6;
    public static final int RESERVED_LENGTH = 6;
    public static final int RESERVED_POS = 0;
    public static final int TOTAL_LENGTH = 28;

    public Mp4Mp4aBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer;
    }

    public void processData() throws CannotReadException {
        this.dataBuffer.position(this.dataBuffer.position() + 28);
    }
}
