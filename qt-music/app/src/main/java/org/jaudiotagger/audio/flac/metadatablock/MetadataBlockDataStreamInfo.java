package org.jaudiotagger.audio.flac.metadatablock;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class MetadataBlockDataStreamInfo implements MetadataBlockData {
    public static final int STREAM_INFO_DATA_LENGTH = 34;
    private int bitsPerSample;
    private boolean isValid;
    private int maxBlockSize;
    private int maxFrameSize;
    private String md5;
    private int minBlockSize;
    private int minFrameSize;
    private int noOfChannels;
    private int noOfSamples;
    private ByteBuffer rawdata;
    private int samplingRate;
    private int samplingRatePerChannel;
    private float trackLength;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac.MetadataBlockDataStreamInfo");
    private static final char[] hexArray = "0123456789abcdef".toCharArray();

    public MetadataBlockDataStreamInfo(MetadataBlockHeader metadataBlockHeader, FileChannel fileChannel) throws IOException {
        this.isValid = true;
        if (metadataBlockHeader.getDataLength() < 34) {
            this.isValid = false;
            throw new IOException("MetadataBlockDataStreamInfo HeaderDataSize is invalid:" + metadataBlockHeader.getDataLength());
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(metadataBlockHeader.getDataLength());
        this.rawdata = byteBufferAllocate;
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        int i = fileChannel.read(this.rawdata);
        if (i < metadataBlockHeader.getDataLength()) {
            this.isValid = false;
            throw new IOException("Unable to read required number of bytes, read:" + i + ":required:" + metadataBlockHeader.getDataLength());
        }
        this.rawdata.flip();
        this.minBlockSize = Utils.u(this.rawdata.getShort());
        this.maxBlockSize = Utils.u(this.rawdata.getShort());
        this.minFrameSize = readThreeByteInteger(this.rawdata.get(), this.rawdata.get(), this.rawdata.get());
        this.maxFrameSize = readThreeByteInteger(this.rawdata.get(), this.rawdata.get(), this.rawdata.get());
        this.samplingRate = readSamplingRate();
        this.noOfChannels = readNoOfChannels();
        this.bitsPerSample = readBitsPerSample();
        this.noOfSamples = readTotalNumberOfSamples();
        this.md5 = readMd5();
        double d = this.noOfSamples;
        int i2 = this.samplingRate;
        this.trackLength = (float) (d / i2);
        this.samplingRatePerChannel = i2 / this.noOfChannels;
        this.rawdata.rewind();
    }

    private String readMd5() {
        char[] cArr = new char[32];
        if (this.rawdata.limit() >= 34) {
            for (int i = 0; i < 16; i++) {
                byte b = this.rawdata.get(i + 18);
                int i2 = i * 2;
                char[] cArr2 = hexArray;
                cArr[i2] = cArr2[(b & 255) >>> 4];
                cArr[i2 + 1] = cArr2[b & 15];
            }
        }
        return new String(cArr);
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public ByteBuffer getBytes() {
        return this.rawdata;
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public int getLength() {
        return this.rawdata.limit();
    }

    public String toString() {
        return "MinBlockSize:" + this.minBlockSize + "MaxBlockSize:" + this.maxBlockSize + "MinFrameSize:" + this.minFrameSize + "MaxFrameSize:" + this.maxFrameSize + "SampleRateTotal:" + this.samplingRate + "SampleRatePerChannel:" + this.samplingRatePerChannel + ":Channel number:" + this.noOfChannels + ":Bits per sample: " + this.bitsPerSample + ":TotalNumberOfSamples: " + this.noOfSamples + ":Length: " + this.trackLength;
    }

    public float getPreciseLength() {
        return this.trackLength;
    }

    public int getNoOfChannels() {
        return this.noOfChannels;
    }

    public int getSamplingRate() {
        return this.samplingRate;
    }

    public int getSamplingRatePerChannel() {
        return this.samplingRatePerChannel;
    }

    public String getEncodingType() {
        return "FLAC " + this.bitsPerSample + " bits";
    }

    public int getBitsPerSample() {
        return this.bitsPerSample;
    }

    public long getNoOfSamples() {
        return this.noOfSamples;
    }

    public String getMD5Signature() {
        return this.md5;
    }

    public boolean isValid() {
        return this.isValid;
    }

    private int readThreeByteInteger(byte b, byte b2, byte b3) {
        return (Utils.u(b) << 16) + (Utils.u(b2) << 8) + Utils.u(b3);
    }

    private int readSamplingRate() {
        return (Utils.u(this.rawdata.get(10)) << 12) + (Utils.u(this.rawdata.get(11)) << 4) + ((Utils.u(this.rawdata.get(12)) & 240) >>> 4);
    }

    private int readNoOfChannels() {
        return ((Utils.u(this.rawdata.get(12)) & 14) >>> 1) + 1;
    }

    private int readBitsPerSample() {
        return ((Utils.u(this.rawdata.get(12)) & 1) << 4) + ((Utils.u(this.rawdata.get(13)) & 240) >>> 4) + 1;
    }

    private int readTotalNumberOfSamples() {
        return Utils.u(this.rawdata.get(17)) + (Utils.u(this.rawdata.get(16)) << 8) + (Utils.u(this.rawdata.get(15)) << 16) + (Utils.u(this.rawdata.get(14)) << 24) + ((Utils.u(this.rawdata.get(13)) & 15) << 32);
    }
}
