package org.jaudiotagger.audio.wav.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;
import org.jaudiotagger.audio.wav.WavSubFormat;
import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class WavFormatChunk extends Chunk {
    private static final int EXTENSIBLE_DATA_SIZE = 22;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav.chunk");
    private int blockAlign;
    private int channelMask;
    private GenericAudioHeader info;
    private boolean isValid;
    private WavSubFormat wsf;

    public WavFormatChunk(ByteBuffer byteBuffer, ChunkHeader chunkHeader, GenericAudioHeader genericAudioHeader) throws IOException {
        super(byteBuffer, chunkHeader);
        this.isValid = false;
        this.info = genericAudioHeader;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        int iU = Utils.u(this.chunkData.getShort());
        this.wsf = WavSubFormat.getByCode(Integer.valueOf(iU));
        this.info.setChannelNumber(Utils.u(this.chunkData.getShort()));
        this.info.setSamplingRate(this.chunkData.getInt());
        this.info.setByteRate(this.chunkData.getInt());
        GenericAudioHeader genericAudioHeader = this.info;
        genericAudioHeader.setBitRate((genericAudioHeader.getByteRate().intValue() * Utils.BITS_IN_BYTE_MULTIPLIER) / Utils.KILOBYTE_MULTIPLIER);
        this.info.setVariableBitRate(false);
        this.blockAlign = Utils.u(this.chunkData.getShort());
        this.info.setBitsPerSample(Utils.u(this.chunkData.getShort()));
        WavSubFormat wavSubFormat = this.wsf;
        if (wavSubFormat != null && wavSubFormat == WavSubFormat.FORMAT_EXTENSIBLE && Utils.u(this.chunkData.getShort()) == 22) {
            this.info.setBitsPerSample(Utils.u(this.chunkData.getShort()));
            this.channelMask = this.chunkData.getInt();
            this.wsf = WavSubFormat.getByCode(Integer.valueOf(Utils.u(this.chunkData.getShort())));
        }
        if (this.wsf != null) {
            if (this.info.getBitsPerSample() > 0) {
                this.info.setEncodingType(this.wsf.getDescription() + " " + this.info.getBitsPerSample() + " bits");
                return true;
            }
            this.info.setEncodingType(this.wsf.getDescription());
            return true;
        }
        this.info.setEncodingType("Unknown Sub Format Code:" + Hex.asHex(iU));
        return true;
    }

    public String toString() {
        return "RIFF-WAVE Header:\nIs valid?: " + this.isValid;
    }
}
