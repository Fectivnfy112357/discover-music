package org.jaudiotagger.audio.dff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class CmprChunk extends BaseChunk {
    private String compression;
    private String description;

    public CmprChunk(ByteBuffer byteBuffer) {
        super(byteBuffer);
    }

    @Override // org.jaudiotagger.audio.dff.BaseChunk
    public void readDataChunch(FileChannel fileChannel) throws IOException {
        super.readDataChunch(fileChannel);
        this.compression = Utils.readFourBytesAsChars(Utils.readFileDataIntoBufferLE(fileChannel, 4));
        int i = Utils.readFileDataIntoBufferLE(fileChannel, 1).get() & 255;
        ByteBuffer fileDataIntoBufferLE = Utils.readFileDataIntoBufferLE(fileChannel, i);
        byte[] bArr = new byte[i];
        fileDataIntoBufferLE.get(bArr);
        this.description = new String(bArr, StandardCharsets.ISO_8859_1);
        skipToChunkEnd(fileChannel);
    }

    public String getCompression() {
        return this.compression;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return DffChunkType.CMPR.getCode();
    }
}
