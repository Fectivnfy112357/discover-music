package org.jaudiotagger.audio.mp3;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;

/* loaded from: classes3.dex */
public class XingFrame {
    private static final int BYTE_1 = 0;
    private static final int BYTE_2 = 1;
    private static final int BYTE_3 = 2;
    private static final int BYTE_4 = 3;
    public static final int MAX_BUFFER_SIZE_NEEDED_TO_READ_XING = 192;
    private static final int MPEG_VERSION_1_MODE_MONO_OFFSET = 21;
    private static final int MPEG_VERSION_1_MODE_STEREO_OFFSET = 36;
    private static final int MPEG_VERSION_2_MODE_MONO_OFFSET = 13;
    private static final int MPEG_VERSION_2_MODE_STEREO_OFFSET = 21;
    private static final int XING_AUDIOSIZE_BUFFER_SIZE = 4;
    private static final int XING_FLAG_BUFFER_SIZE = 4;
    private static final int XING_FRAMECOUNT_BUFFER_SIZE = 4;
    private static final int XING_HEADER_BUFFER_SIZE = 120;
    private static final int XING_IDENTIFIER_BUFFER_SIZE = 4;
    private ByteBuffer header;
    private LameFrame lameFrame;
    private static final byte[] XING_VBR_ID = {88, 105, 110, 103};
    private static final byte[] XING_CBR_ID = {73, 110, 102, 111};
    private boolean vbr = false;
    private boolean isFrameCountEnabled = false;
    private int frameCount = -1;
    private boolean isAudioSizeEnabled = false;
    private int audioSize = -1;

    private XingFrame(ByteBuffer byteBuffer) {
        this.header = byteBuffer;
        byteBuffer.rewind();
        setVbr();
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        if ((bArr[3] & 1) != 0) {
            setFrameCount();
        }
        if ((bArr[3] & 2) != 0) {
            setAudioSize();
        }
        if (byteBuffer.limit() >= 156) {
            byteBuffer.position(120);
            this.lameFrame = LameFrame.parseLameFrame(byteBuffer);
        }
    }

    public LameFrame getLameFrame() {
        return this.lameFrame;
    }

    private void setVbr() {
        byte[] bArr = new byte[4];
        this.header.get(bArr);
        if (Arrays.equals(bArr, XING_VBR_ID)) {
            MP3File.logger.finest("Is Vbr");
            this.vbr = true;
        }
    }

    private void setFrameCount() {
        byte[] bArr = new byte[4];
        this.header.get(bArr);
        this.isFrameCountEnabled = true;
        this.frameCount = (bArr[3] & 255) | ((bArr[1] << 16) & 16711680) | ((bArr[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    public final boolean isFrameCountEnabled() {
        return this.isFrameCountEnabled;
    }

    public final int getFrameCount() {
        return this.frameCount;
    }

    private void setAudioSize() {
        byte[] bArr = new byte[4];
        this.header.get(bArr);
        this.isAudioSizeEnabled = true;
        this.audioSize = (bArr[3] & 255) | ((bArr[1] << 16) & 16711680) | ((bArr[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    public final boolean isAudioSizeEnabled() {
        return this.isAudioSizeEnabled;
    }

    public final int getAudioSize() {
        return this.audioSize;
    }

    public static XingFrame parseXingFrame(ByteBuffer byteBuffer) throws InvalidAudioFrameException {
        return new XingFrame(byteBuffer);
    }

    public static ByteBuffer isXingFrame(ByteBuffer byteBuffer, MPEGFrameHeader mPEGFrameHeader) {
        int iPosition = byteBuffer.position();
        if (mPEGFrameHeader.getVersion() == 3) {
            if (mPEGFrameHeader.getChannelMode() == 3) {
                byteBuffer.position(iPosition + 21);
            } else {
                byteBuffer.position(iPosition + 36);
            }
        } else if (mPEGFrameHeader.getChannelMode() == 3) {
            byteBuffer.position(iPosition + 13);
        } else {
            byteBuffer.position(iPosition + 21);
        }
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBuffer.position(iPosition);
        byte[] bArr = new byte[4];
        byteBufferSlice.get(bArr);
        if (!Arrays.equals(bArr, XING_VBR_ID) && !Arrays.equals(bArr, XING_CBR_ID)) {
            return null;
        }
        MP3File.logger.finest("Found Xing Frame");
        return byteBufferSlice;
    }

    public final boolean isVbr() {
        return this.vbr;
    }

    public String toString() {
        return "Xing Header+\n\tvbr:" + this.vbr + "\n\tframeCountEnabled:" + this.isFrameCountEnabled + "\n\tframeCount:" + this.frameCount + "\n\taudioSizeEnabled:" + this.isAudioSizeEnabled + "\n\taudioFileSize:" + this.audioSize + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }
}
