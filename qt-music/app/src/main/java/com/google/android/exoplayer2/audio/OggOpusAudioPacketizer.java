package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

@Deprecated
/* loaded from: classes2.dex */
public final class OggOpusAudioPacketizer {
    private static final int FIRST_AUDIO_SAMPLE_PAGE_SEQUENCE = 2;
    private ByteBuffer outputBuffer = AudioProcessor.EMPTY_BUFFER;
    private int granulePosition = 0;
    private int pageSequenceNumber = 2;

    public void packetize(DecoderInputBuffer decoderInputBuffer) {
        Assertions.checkNotNull(decoderInputBuffer.data);
        if (decoderInputBuffer.data.limit() - decoderInputBuffer.data.position() == 0) {
            return;
        }
        this.outputBuffer = packetizeInternal(decoderInputBuffer.data);
        decoderInputBuffer.clear();
        decoderInputBuffer.ensureSpaceForWrite(this.outputBuffer.remaining());
        decoderInputBuffer.data.put(this.outputBuffer);
        decoderInputBuffer.flip();
    }

    public void reset() {
        this.outputBuffer = AudioProcessor.EMPTY_BUFFER;
        this.granulePosition = 0;
        this.pageSequenceNumber = 2;
    }

    private ByteBuffer packetizeInternal(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        int i = iLimit - iPosition;
        int i2 = (i + 255) / 255;
        ByteBuffer byteBufferReplaceOutputBuffer = replaceOutputBuffer(i2 + 27 + i);
        byteBufferReplaceOutputBuffer.put((byte) 79);
        byteBufferReplaceOutputBuffer.put((byte) 103);
        byteBufferReplaceOutputBuffer.put((byte) 103);
        byteBufferReplaceOutputBuffer.put((byte) 83);
        byteBufferReplaceOutputBuffer.put((byte) 0);
        byteBufferReplaceOutputBuffer.put((byte) 0);
        int packetAudioSampleCount = this.granulePosition + OpusUtil.parsePacketAudioSampleCount(byteBuffer);
        this.granulePosition = packetAudioSampleCount;
        byteBufferReplaceOutputBuffer.putLong(packetAudioSampleCount);
        byteBufferReplaceOutputBuffer.putInt(0);
        byteBufferReplaceOutputBuffer.putInt(this.pageSequenceNumber);
        this.pageSequenceNumber++;
        byteBufferReplaceOutputBuffer.putInt(0);
        byteBufferReplaceOutputBuffer.put((byte) i2);
        for (int i3 = 0; i3 < i2; i3++) {
            if (i >= 255) {
                byteBufferReplaceOutputBuffer.put((byte) -1);
                i -= 255;
            } else {
                byteBufferReplaceOutputBuffer.put((byte) i);
                i = 0;
            }
        }
        while (iPosition < iLimit) {
            byteBufferReplaceOutputBuffer.put(byteBuffer.get(iPosition));
            iPosition++;
        }
        byteBuffer.position(byteBuffer.limit());
        byteBufferReplaceOutputBuffer.flip();
        byteBufferReplaceOutputBuffer.putInt(22, Util.crc32(byteBufferReplaceOutputBuffer.array(), byteBufferReplaceOutputBuffer.arrayOffset(), byteBufferReplaceOutputBuffer.limit() - byteBufferReplaceOutputBuffer.position(), 0));
        byteBufferReplaceOutputBuffer.position(0);
        return byteBufferReplaceOutputBuffer;
    }

    private ByteBuffer replaceOutputBuffer(int i) {
        if (this.outputBuffer.capacity() < i) {
            this.outputBuffer = ByteBuffer.allocate(i).order(ByteOrder.LITTLE_ENDIAN);
        } else {
            this.outputBuffer.clear();
        }
        return this.outputBuffer;
    }
}
