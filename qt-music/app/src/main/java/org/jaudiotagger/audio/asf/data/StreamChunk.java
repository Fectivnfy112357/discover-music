package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public abstract class StreamChunk extends Chunk {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private boolean contentEncrypted;
    private int streamNumber;
    private long streamSpecificDataSize;
    private long timeOffset;
    private final GUID type;
    private long typeSpecificDataSize;

    public StreamChunk(GUID guid, BigInteger bigInteger) {
        super(GUID.GUID_STREAM, bigInteger);
        this.type = guid;
    }

    public int getStreamNumber() {
        return this.streamNumber;
    }

    public long getStreamSpecificDataSize() {
        return this.streamSpecificDataSize;
    }

    public GUID getStreamType() {
        return this.type;
    }

    public long getTimeOffset() {
        return this.timeOffset;
    }

    public long getTypeSpecificDataSize() {
        return this.typeSpecificDataSize;
    }

    public boolean isContentEncrypted() {
        return this.contentEncrypted;
    }

    @Override // org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        sb.append(str).append("  |-> Stream number: ").append(getStreamNumber()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |-> Type specific data size  : ").append(getTypeSpecificDataSize()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |-> Stream specific data size: ").append(getStreamSpecificDataSize()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |-> Time Offset              : ").append(getTimeOffset()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |-> Content Encryption       : ").append(isContentEncrypted()).append(Utils.LINE_SEPARATOR);
        return sb.toString();
    }

    public void setContentEncrypted(boolean z) {
        this.contentEncrypted = z;
    }

    public void setStreamNumber(int i) {
        this.streamNumber = i;
    }

    public void setStreamSpecificDataSize(long j) {
        this.streamSpecificDataSize = j;
    }

    public void setTimeOffset(long j) {
        this.timeOffset = j;
    }

    public void setTypeSpecificDataSize(long j) {
        this.typeSpecificDataSize = j;
    }
}
