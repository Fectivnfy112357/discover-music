package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public final class AudioStreamChunk extends StreamChunk {
    public static final String[][] CODEC_DESCRIPTIONS = {new String[]{"161", " (Windows Media Audio (ver 7,8,9))"}, new String[]{"162", " (Windows Media Audio 9 series (Professional))"}, new String[]{"163", "(Windows Media Audio 9 series (Lossless))"}, new String[]{"7A21", " (GSM-AMR (CBR))"}, new String[]{"7A22", " (GSM-AMR (VBR))"}};
    public static final long WMA = 353;
    public static final long WMA_CBR = 31265;
    public static final long WMA_LOSSLESS = 355;
    public static final long WMA_PRO = 354;
    public static final long WMA_VBR = 31266;
    private long averageBytesPerSec;
    private int bitsPerSample;
    private long blockAlignment;
    private long channelCount;
    private byte[] codecData;
    private long compressionFormat;
    private GUID errorConcealment;
    private long samplingRate;

    public AudioStreamChunk(BigInteger bigInteger) {
        super(GUID.GUID_AUDIOSTREAM, bigInteger);
        this.codecData = new byte[0];
    }

    public long getAverageBytesPerSec() {
        return this.averageBytesPerSec;
    }

    public int getBitsPerSample() {
        return this.bitsPerSample;
    }

    public long getBlockAlignment() {
        return this.blockAlignment;
    }

    public long getChannelCount() {
        return this.channelCount;
    }

    public byte[] getCodecData() {
        return (byte[]) this.codecData.clone();
    }

    public String getCodecDescription() {
        String str;
        StringBuilder sb = new StringBuilder(Long.toHexString(getCompressionFormat()));
        String[][] strArr = CODEC_DESCRIPTIONS;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = " (Unknown)";
                break;
            }
            String[] strArr2 = strArr[i];
            if (strArr2[0].equalsIgnoreCase(sb.toString())) {
                str = strArr2[1];
                break;
            }
            i++;
        }
        if (sb.length() % 2 == 0) {
            sb.insert(0, "0x");
        } else {
            sb.insert(0, "0x0");
        }
        sb.append(str);
        return sb.toString();
    }

    public long getCompressionFormat() {
        return this.compressionFormat;
    }

    public GUID getErrorConcealment() {
        return this.errorConcealment;
    }

    public int getKbps() {
        return (((int) getAverageBytesPerSec()) * 8) / 1000;
    }

    public long getSamplingRate() {
        return this.samplingRate;
    }

    public boolean isErrorConcealed() {
        return getErrorConcealment().equals(GUID.GUID_AUDIO_ERROR_CONCEALEMENT_INTERLEAVED);
    }

    @Override // org.jaudiotagger.audio.asf.data.StreamChunk, org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        sb.append(str).append("  |-> Audio info:").append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |  : Bitrate : ").append(getKbps()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |  : Channels : ").append(getChannelCount()).append(" at ").append(getSamplingRate()).append(" Hz").append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |  : Bits per Sample: ").append(getBitsPerSample()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |  : Formatcode: ").append(getCodecDescription()).append(Utils.LINE_SEPARATOR);
        return sb.toString();
    }

    public void setAverageBytesPerSec(long j) {
        this.averageBytesPerSec = j;
    }

    public void setBitsPerSample(int i) {
        this.bitsPerSample = i;
    }

    public void setBlockAlignment(long j) {
        this.blockAlignment = j;
    }

    public void setChannelCount(long j) {
        this.channelCount = j;
    }

    public void setCodecData(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        this.codecData = (byte[]) bArr.clone();
    }

    public void setCompressionFormat(long j) {
        this.compressionFormat = j;
    }

    public void setErrorConcealment(GUID guid) {
        this.errorConcealment = guid;
    }

    public void setSamplingRate(long j) {
        this.samplingRate = j;
    }
}
