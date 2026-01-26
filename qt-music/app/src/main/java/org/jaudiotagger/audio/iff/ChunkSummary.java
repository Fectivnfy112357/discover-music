package org.jaudiotagger.audio.iff;

import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class ChunkSummary {
    private String chunkId;
    private long chunkSize;
    private long fileStartLocation;

    public ChunkSummary(String str, long j, long j2) {
        this.chunkId = str;
        this.fileStartLocation = j;
        this.chunkSize = j2;
    }

    public String toString() {
        return this.chunkId + ":StartLocation:" + Hex.asDecAndHex(this.fileStartLocation) + ":SizeIncHeader:" + (this.chunkSize + 8) + ":EndLocation:" + Hex.asDecAndHex(this.fileStartLocation + this.chunkSize + 8);
    }

    public long getEndLocation() {
        return this.fileStartLocation + this.chunkSize + 8;
    }

    public String getChunkId() {
        return this.chunkId;
    }

    public void setChunkId(String str) {
        this.chunkId = str;
    }

    public long getFileStartLocation() {
        return this.fileStartLocation;
    }

    public void setFileStartLocation(long j) {
        this.fileStartLocation = j;
    }

    public long getChunkSize() {
        return this.chunkSize;
    }

    public void setChunkSize(long j) {
        this.chunkSize = j;
    }
}
