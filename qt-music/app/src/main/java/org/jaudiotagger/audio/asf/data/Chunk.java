package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class Chunk {
    protected final BigInteger chunkLength;
    protected final GUID guid;
    protected long position;

    public Chunk(GUID guid, BigInteger bigInteger) {
        if (guid == null) {
            throw new IllegalArgumentException("GUID must not be null.");
        }
        if (bigInteger == null || bigInteger.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("chunkLen must not be null nor negative.");
        }
        this.guid = guid;
        this.chunkLength = bigInteger;
    }

    public Chunk(GUID guid, long j, BigInteger bigInteger) {
        if (guid == null) {
            throw new IllegalArgumentException("GUID must not be null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("Position of header can't be negative.");
        }
        if (bigInteger == null || bigInteger.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("chunkLen must not be null nor negative.");
        }
        this.guid = guid;
        this.position = j;
        this.chunkLength = bigInteger;
    }

    @Deprecated
    public long getChunckEnd() {
        return this.position + this.chunkLength.longValue();
    }

    public long getChunkEnd() {
        return this.position + this.chunkLength.longValue();
    }

    public BigInteger getChunkLength() {
        return this.chunkLength;
    }

    public GUID getGuid() {
        return this.guid;
    }

    public long getPosition() {
        return this.position;
    }

    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str).append("-> GUID: ").append(GUID.getGuidDescription(this.guid)).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  | : Starts at position: ").append(getPosition()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  | : Last byte at: ").append(getChunkEnd() - 1).append(Utils.LINE_SEPARATOR);
        return sb.toString();
    }

    public void setPosition(long j) {
        this.position = j;
    }

    public String toString() {
        return prettyPrint("");
    }
}
