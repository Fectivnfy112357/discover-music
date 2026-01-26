package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class FrteChunk extends BaseChunk {
    private int numFrames;
    private Short rate;

    public FrteChunk(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // org.jaudiotagger.audio.dff.BaseChunk
    public void readDataChunch(FileChannel fileChannel) throws IOException {
        super.readDataChunch(fileChannel);
        this.numFrames = Integer.reverseBytes(Utils.readFileDataIntoBufferLE(fileChannel, 4).getInt());
        this.rate = Short.valueOf(Short.reverseBytes(Utils.readFileDataIntoBufferLE(fileChannel, 2).getShort()));
        skipToChunkEnd(fileChannel);
    }

    public int getNumFrames() {
        return this.numFrames;
    }

    public Short getRate() {
        return this.rate;
    }

    public String toString() {
        return DffChunkType.FRTE.getCode();
    }
}
