package org.jaudiotagger.audio.flac.metadatablock;

/* loaded from: classes3.dex */
public class MetadataBlock {
    private MetadataBlockData mbd;
    private MetadataBlockHeader mbh;

    public MetadataBlock(MetadataBlockHeader metadataBlockHeader, MetadataBlockData metadataBlockData) {
        this.mbh = metadataBlockHeader;
        this.mbd = metadataBlockData;
    }

    public MetadataBlockHeader getHeader() {
        return this.mbh;
    }

    public MetadataBlockData getData() {
        return this.mbd;
    }

    public int getLength() {
        return this.mbh.getDataLength() + 4;
    }
}
