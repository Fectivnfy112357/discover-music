package org.jaudiotagger.audio.aiff.chunk;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.jaudiotagger.audio.aiff.AiffAudioHeader;
import org.jaudiotagger.audio.aiff.AiffUtil;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.iff.Chunk;
import org.jaudiotagger.audio.iff.ChunkHeader;

/* loaded from: classes3.dex */
public class CommentsChunk extends Chunk {
    private AiffAudioHeader aiffHeader;

    public CommentsChunk(ChunkHeader chunkHeader, ByteBuffer byteBuffer, AiffAudioHeader aiffAudioHeader) {
        super(byteBuffer, chunkHeader);
        this.aiffHeader = aiffAudioHeader;
    }

    @Override // org.jaudiotagger.audio.iff.Chunk
    public boolean readChunk() throws IOException {
        int iU = Utils.u(this.chunkData.getShort());
        for (int i = 0; i < iU; i++) {
            Date dateTimestampToDate = AiffUtil.timestampToDate(Utils.u(this.chunkData.getInt()));
            Utils.u(this.chunkData.getShort());
            int iU2 = Utils.u(this.chunkData.getShort());
            String str = Utils.getString(this.chunkData, 0, iU2, StandardCharsets.ISO_8859_1) + " " + AiffUtil.formatDate(dateTimestampToDate);
            if (iU2 % 2 != 0 && this.chunkData.position() < this.chunkData.limit()) {
                this.chunkData.get();
            }
            this.aiffHeader.addComment(str);
        }
        return true;
    }
}
