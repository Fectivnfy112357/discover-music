package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* loaded from: classes3.dex */
public class EndChunk extends BaseChunk {
    private Long dataEnd;

    public EndChunk(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // org.jaudiotagger.audio.dff.BaseChunk
    public void readDataChunch(FileChannel fileChannel) throws IOException {
        super.readDataChunch(fileChannel);
        this.dataEnd = getChunkEnd();
    }

    public Long getDataStart() {
        return getChunkStart();
    }

    public Long getDataEnd() {
        return this.dataEnd;
    }

    public String toString() {
        return DffChunkType.END.getCode() + " (END)";
    }
}
