package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class VideoStreamChunk extends StreamChunk {
    private byte[] codecId;
    private long pictureHeight;
    private long pictureWidth;

    public VideoStreamChunk(BigInteger bigInteger) {
        super(GUID.GUID_VIDEOSTREAM, bigInteger);
        this.codecId = new byte[0];
    }

    public byte[] getCodecId() {
        return (byte[]) this.codecId.clone();
    }

    public String getCodecIdAsString() {
        if (this.codecId == null) {
            return "Unknown";
        }
        return new String(getCodecId());
    }

    public long getPictureHeight() {
        return this.pictureHeight;
    }

    public long getPictureWidth() {
        return this.pictureWidth;
    }

    @Override // org.jaudiotagger.audio.asf.data.StreamChunk, org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        sb.insert(0, Utils.LINE_SEPARATOR + str + "|->VideoStream");
        sb.append(str).append("Video info:").append(Utils.LINE_SEPARATOR);
        sb.append(str).append("      |->Width  : ").append(getPictureWidth()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("      |->Heigth : ").append(getPictureHeight()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("      |->Codec  : ").append(getCodecIdAsString()).append(Utils.LINE_SEPARATOR);
        return sb.toString();
    }

    public void setCodecId(byte[] bArr) {
        this.codecId = (byte[]) bArr.clone();
    }

    public void setPictureHeight(long j) {
        this.pictureHeight = j;
    }

    public void setPictureWidth(long j) {
        this.pictureWidth = j;
    }
}
