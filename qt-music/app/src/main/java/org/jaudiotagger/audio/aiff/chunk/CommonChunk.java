package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.jaudiotagger.audio.aiff.AiffAudioHeader;
import org.jaudiotagger.audio.aiff.AiffType;
import org.jaudiotagger.audio.aiff.AiffUtil;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public class CommonChunk extends Chunk {
    private AiffAudioHeader aiffHeader;

    public CommonChunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer, AiffAudioHeader aiffAudioHeader) {
        super(byteBuffer, chunkHeader);
        this.aiffHeader = aiffAudioHeader;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        int iU = Utils.u(this.chunkData.getShort());
        long j = this.chunkData.getInt();
        int iU2 = Utils.u(this.chunkData.getShort());
        double d = AiffUtil.read80BitDouble(this.chunkData);
        if (this.aiffHeader.getFileType() == AiffType.AIFC) {
            if (this.chunkData.remaining() == 0) {
                return false;
            }
            String fourBytesAsChars = Utils.readFourBytesAsChars(this.chunkData);
            if (fourBytesAsChars.equals(AiffCompressionType.SOWT.getCode())) {
                this.aiffHeader.setEndian(AiffAudioHeader.Endian.LITTLE_ENDIAN);
            }
            String pascalString = Utils.readPascalString(this.chunkData);
            if (fourBytesAsChars != null) {
                AiffCompressionType byCode = AiffCompressionType.getByCode(fourBytesAsChars);
                if (byCode != null) {
                    pascalString = byCode.getCompression();
                    this.aiffHeader.setLossless(byCode.isLossless());
                    if (byCode == AiffCompressionType.NONE) {
                        this.aiffHeader.setVariableBitRate(false);
                    }
                } else {
                    this.aiffHeader.setLossless(false);
                }
                if (pascalString.isEmpty()) {
                    this.aiffHeader.setEncodingType(fourBytesAsChars);
                } else {
                    this.aiffHeader.setEncodingType(pascalString);
                }
            }
        } else {
            this.aiffHeader.setLossless(true);
            this.aiffHeader.setEncodingType(AiffCompressionType.NONE.getCompression());
            this.aiffHeader.setVariableBitRate(false);
        }
        this.aiffHeader.setBitsPerSample(iU2);
        this.aiffHeader.setSamplingRate((int) d);
        this.aiffHeader.setChannelNumber(iU);
        this.aiffHeader.setPreciseLength(j / d);
        this.aiffHeader.setNoOfSamples(Long.valueOf(j));
        return true;
    }
}
