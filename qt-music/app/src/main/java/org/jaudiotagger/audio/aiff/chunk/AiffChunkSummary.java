package org.jaudiotagger.audio.aiff.chunk;

import org.jaudiotagger.audio.iff.ChunkSummary;
import org.jaudiotagger.tag.aiff.AiffTag;

/* loaded from: classes3.dex */
public class AiffChunkSummary {
    public static boolean isOnlyMetadataTagsAfterStartingMetadataTag(AiffTag aiffTag) {
        boolean z = false;
        for (ChunkSummary chunkSummary : aiffTag.getChunkSummaryList()) {
            if (z) {
                if (!chunkSummary.getChunkId().equals(AiffChunkType.TAG.getCode())) {
                    return false;
                }
            } else if (chunkSummary.getFileStartLocation() == aiffTag.getStartLocationInFileOfId3Chunk()) {
                z = true;
            }
        }
        return z;
    }

    public static ChunkSummary getChunkBeforeStartingMetadataTag(AiffTag aiffTag) {
        for (int i = 0; i < aiffTag.getChunkSummaryList().size(); i++) {
            if (aiffTag.getChunkSummaryList().get(i).getFileStartLocation() == aiffTag.getStartLocationInFileOfId3Chunk()) {
                return aiffTag.getChunkSummaryList().get(i - 1);
            }
        }
        return null;
    }
}
