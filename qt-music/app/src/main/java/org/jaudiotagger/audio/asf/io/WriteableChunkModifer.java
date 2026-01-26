package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class WriteableChunkModifer implements ChunkModifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final WriteableChunk writableChunk;

    public WriteableChunkModifer(WriteableChunk writeableChunk) {
        this.writableChunk = writeableChunk;
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkModifier
    public boolean isApplicable(GUID guid) {
        return guid.equals(this.writableChunk.getGuid());
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkModifier
    public ModificationResult modify(GUID guid, InputStream inputStream, OutputStream outputStream) throws IOException {
        int i;
        long jWriteInto;
        long uint64 = 0;
        if (this.writableChunk.isEmpty()) {
            i = 0;
            jWriteInto = 0;
        } else {
            jWriteInto = this.writableChunk.writeInto(outputStream);
            i = guid == null ? 1 : 0;
        }
        if (guid != null) {
            if (this.writableChunk.isEmpty()) {
                i--;
            }
            uint64 = Utils.readUINT64(inputStream);
            inputStream.skip(uint64 - 24);
        }
        return new ModificationResult(i, jWriteInto - uint64, guid);
    }
}
