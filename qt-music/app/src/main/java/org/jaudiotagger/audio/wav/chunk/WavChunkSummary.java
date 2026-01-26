package org.jaudiotagger.audio.wav.chunk;

import java.util.logging.Logger;
import org.jaudiotagger.audio.iff.ChunkSummary;
import org.jaudiotagger.audio.wav.WavChunkType;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavChunkSummary {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.wav.chunk");

    public static long getStartLocationOfFirstMetadataChunk(WavTag wavTag) {
        if (wavTag.getMetadataChunkSummaryList().size() > 0) {
            return wavTag.getMetadataChunkSummaryList().get(0).getFileStartLocation();
        }
        return -1L;
    }

    public static boolean isOnlyMetadataTagsAfterStartingMetadataTag(WavTag wavTag) {
        long startLocationOfFirstMetadataChunk = getStartLocationOfFirstMetadataChunk(wavTag);
        if (startLocationOfFirstMetadataChunk == -1) {
            logger.severe("Unable to find any metadata tags !");
            return false;
        }
        boolean z = false;
        for (ChunkSummary chunkSummary : wavTag.getChunkSummaryList()) {
            if (z) {
                if (!chunkSummary.getChunkId().equals(WavChunkType.ID3.getCode()) && !chunkSummary.getChunkId().equals(WavChunkType.ID3_UPPERCASE.getCode()) && !chunkSummary.getChunkId().equals(WavChunkType.LIST.getCode()) && !chunkSummary.getChunkId().equals(WavChunkType.INFO.getCode())) {
                    return false;
                }
            } else if (chunkSummary.getFileStartLocation() == startLocationOfFirstMetadataChunk) {
                z = true;
            }
        }
        return z;
    }

    public static ChunkSummary getChunkBeforeFirstMetadataTag(WavTag wavTag) {
        long startLocationOfFirstMetadataChunk = getStartLocationOfFirstMetadataChunk(wavTag);
        for (int i = 0; i < wavTag.getChunkSummaryList().size(); i++) {
            if (wavTag.getChunkSummaryList().get(i).getFileStartLocation() == startLocationOfFirstMetadataChunk) {
                return wavTag.getChunkSummaryList().get(i - 1);
            }
        }
        return null;
    }
}
