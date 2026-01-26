package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class FsChunk extends BaseChunk {
    private int sampleRate;

    public FsChunk(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // org.jaudiotagger.audio.dff.BaseChunk
    public void readDataChunch(FileChannel fileChannel) throws IOException {
        super.readDataChunch(fileChannel);
        this.sampleRate = Integer.reverseBytes(Utils.readFileDataIntoBufferLE(fileChannel, 4).getInt());
        skipToChunkEnd(fileChannel);
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public String toString() {
        return DffChunkType.FS.getCode();
    }
}
