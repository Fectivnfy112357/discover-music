package org.jaudiotagger.audio.asf.util;

import java.io.Serializable;
import java.util.Comparator;
import org.jaudiotagger.audio.asf.data.Chunk;

/* loaded from: classes3.dex */
public final class ChunkPositionComparator implements Comparator<Chunk>, Serializable {
    private static final long serialVersionUID = -6337108235272376289L;

    @Override // java.util.Comparator
    public int compare(Chunk chunk, Chunk chunk2) {
        return Long.valueOf(chunk.getPosition()).compareTo(Long.valueOf(chunk2.getPosition()));
    }
}
