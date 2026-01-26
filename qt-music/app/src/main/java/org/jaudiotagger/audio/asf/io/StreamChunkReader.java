package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.AudioStreamChunk;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.data.VideoStreamChunk;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class StreamChunkReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_STREAM};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return true;
    }

    protected StreamChunkReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        long j2;
        long j3;
        long j4;
        boolean z;
        VideoStreamChunk videoStreamChunk;
        BigInteger bigInteger;
        long j5;
        int i;
        BigInteger big64 = Utils.readBig64(inputStream);
        GUID guid2 = Utils.readGUID(inputStream);
        if (!GUID.GUID_AUDIOSTREAM.equals(guid2) && !GUID.GUID_VIDEOSTREAM.equals(guid2)) {
            return null;
        }
        GUID guid3 = Utils.readGUID(inputStream);
        long uint64 = Utils.readUINT64(inputStream);
        long uint32 = Utils.readUINT32(inputStream);
        long uint322 = Utils.readUINT32(inputStream);
        int uint16 = Utils.readUINT16(inputStream);
        int i2 = uint16 & 127;
        boolean z2 = (uint16 & 32768) != 0;
        inputStream.skip(4L);
        if (GUID.GUID_AUDIOSTREAM.equals(guid2)) {
            AudioStreamChunk audioStreamChunk = new AudioStreamChunk(big64);
            long uint162 = Utils.readUINT16(inputStream);
            long uint163 = Utils.readUINT16(inputStream);
            j2 = uint64;
            long uint323 = Utils.readUINT32(inputStream);
            j3 = uint32;
            long uint324 = Utils.readUINT32(inputStream);
            z = z2;
            j4 = uint322;
            long uint164 = Utils.readUINT16(inputStream);
            int uint165 = Utils.readUINT16(inputStream);
            int uint166 = Utils.readUINT16(inputStream);
            byte[] bArr = new byte[uint166];
            inputStream.read(bArr);
            audioStreamChunk.setCompressionFormat(uint162);
            audioStreamChunk.setChannelCount(uint163);
            audioStreamChunk.setSamplingRate(uint323);
            audioStreamChunk.setAverageBytesPerSec(uint324);
            audioStreamChunk.setErrorConcealment(guid3);
            audioStreamChunk.setBlockAlignment(uint164);
            audioStreamChunk.setBitsPerSample(uint165);
            audioStreamChunk.setCodecData(bArr);
            j5 = uint166 + 18;
            videoStreamChunk = audioStreamChunk;
            i = i2;
            bigInteger = big64;
        } else {
            j2 = uint64;
            j3 = uint32;
            j4 = uint322;
            z = z2;
            bigInteger = big64;
            videoStreamChunk = new VideoStreamChunk(bigInteger);
            long uint325 = Utils.readUINT32(inputStream);
            long uint326 = Utils.readUINT32(inputStream);
            inputStream.skip(1L);
            inputStream.skip(2L);
            inputStream.skip(16L);
            byte[] bArr2 = new byte[4];
            inputStream.read(bArr2);
            videoStreamChunk.setPictureWidth(uint325);
            videoStreamChunk.setPictureHeight(uint326);
            videoStreamChunk.setCodecId(bArr2);
            j5 = 31;
            i = i2;
        }
        videoStreamChunk.setStreamNumber(i);
        videoStreamChunk.setStreamSpecificDataSize(j4);
        videoStreamChunk.setTypeSpecificDataSize(j3);
        videoStreamChunk.setTimeOffset(j2);
        videoStreamChunk.setContentEncrypted(z);
        videoStreamChunk.setPosition(j);
        inputStream.skip(((bigInteger.longValue() - 24) - j5) - 54);
        return videoStreamChunk;
    }
}
