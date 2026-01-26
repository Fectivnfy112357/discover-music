package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class Mp4AlacBox extends AbstractMp4Box {
    public static final int OTHER_FLAG_LENGTH = 4;
    private int bitRate;
    private int channels;
    private int historyMult;
    private int initialHistory;
    private int kModifier;
    private int maxCodedFrameSize;
    private int maxSamplePerFrame;
    private int sampleRate;
    private int sampleSize;
    private int unknown1;
    private int unknown2;

    public Mp4AlacBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer;
    }

    public void processData() throws CannotReadException {
        this.dataBuffer.position(this.dataBuffer.position() + 4);
        this.dataBuffer.order(ByteOrder.BIG_ENDIAN);
        this.maxSamplePerFrame = this.dataBuffer.getInt();
        this.unknown1 = Utils.u(this.dataBuffer.get());
        this.sampleSize = Utils.u(this.dataBuffer.get());
        this.historyMult = Utils.u(this.dataBuffer.get());
        this.initialHistory = Utils.u(this.dataBuffer.get());
        this.kModifier = Utils.u(this.dataBuffer.get());
        this.channels = Utils.u(this.dataBuffer.get());
        this.unknown2 = this.dataBuffer.getShort();
        this.maxCodedFrameSize = this.dataBuffer.getInt();
        this.bitRate = this.dataBuffer.getInt();
        this.sampleRate = this.dataBuffer.getInt();
    }

    public int getMaxSamplePerFrame() {
        return this.maxSamplePerFrame;
    }

    public int getUnknown1() {
        return this.unknown1;
    }

    public int getSampleSize() {
        return this.sampleSize;
    }

    public int getHistoryMult() {
        return this.historyMult;
    }

    public int getInitialHistory() {
        return this.initialHistory;
    }

    public int getKModifier() {
        return this.kModifier;
    }

    public int getChannels() {
        return this.channels;
    }

    public int getUnknown2() {
        return this.unknown2;
    }

    public int getMaxCodedFrameSize() {
        return this.maxCodedFrameSize;
    }

    public int getBitRate() {
        return this.bitRate;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public String toString() {
        return "maxSamplePerFrame:" + this.maxSamplePerFrame + "unknown1:" + this.unknown1 + "sampleSize:" + this.sampleSize + "historyMult:" + this.historyMult + "initialHistory:" + this.initialHistory + "kModifier:" + this.kModifier + "channels:" + this.channels + "unknown2 :" + this.unknown2 + "maxCodedFrameSize:" + this.maxCodedFrameSize + "bitRate:" + this.bitRate + "sampleRate:" + this.sampleRate;
    }
}
