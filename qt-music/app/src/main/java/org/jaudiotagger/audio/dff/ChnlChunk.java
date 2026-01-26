package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class ChnlChunk extends BaseChunk {
    String[] IDs;
    private short numChannels;

    public ChnlChunk(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jaudiotagger.audio.dff.BaseChunk
    public void readDataChunch(FileChannel fileChannel) throws IOException {
        super.readDataChunch(fileChannel);
        int iReverseBytes = Short.reverseBytes(Utils.readFileDataIntoBufferLE(fileChannel, 2).getShort());
        this.numChannels = iReverseBytes;
        this.IDs = new String[iReverseBytes];
        for (int i = 0; i < this.numChannels; i++) {
            this.IDs[i] = Utils.readFourBytesAsChars(Utils.readFileDataIntoBufferLE(fileChannel, 4));
        }
        skipToChunkEnd(fileChannel);
    }

    public Short getNumChannels() {
        return Short.valueOf(this.numChannels);
    }

    public String toString() {
        return DffChunkType.CHNL.getCode();
    }
}
