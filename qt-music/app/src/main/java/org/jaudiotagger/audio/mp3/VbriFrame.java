package org.jaudiotagger.audio.mp3;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;

/* loaded from: classes3.dex */
public class VbriFrame {
    private static final int BYTE_1 = 0;
    private static final int BYTE_2 = 1;
    private static final int BYTE_3 = 2;
    private static final int BYTE_4 = 3;
    public static final int MAX_BUFFER_SIZE_NEEDED_TO_READ_VBRI = 156;
    private static final int VBRI_AUDIOSIZE_BUFFER_SIZE = 4;
    private static final int VBRI_FRAMECOUNT_BUFFER_SIZE = 4;
    private static final int VBRI_HEADER_BUFFER_SIZE = 120;
    private static final int VBRI_IDENTIFIER_BUFFER_SIZE = 4;
    private static final int VBRI_OFFSET = 36;
    private static final byte[] VBRI_VBR_ID = {86, 66, 82, 73};
    private ByteBuffer header;
    private boolean vbr = false;
    private int frameCount = -1;
    private int audioSize = -1;

    public final boolean isVbr() {
        return true;
    }

    private VbriFrame(ByteBuffer byteBuffer) {
        this.header = byteBuffer;
        byteBuffer.rewind();
        byteBuffer.position(10);
        setAudioSize();
        setFrameCount();
    }

    private void setAudioSize() {
        byte[] bArr = new byte[4];
        this.header.get(bArr);
        this.audioSize = (bArr[3] & 255) | ((bArr[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    private void setFrameCount() {
        byte[] bArr = new byte[4];
        this.header.get(bArr);
        this.frameCount = (bArr[3] & 255) | ((bArr[0] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[1] << 16) & 16711680) | ((bArr[2] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
    }

    public final int getFrameCount() {
        return this.frameCount;
    }

    public final int getAudioSize() {
        return this.audioSize;
    }

    public static VbriFrame parseVBRIFrame(ByteBuffer byteBuffer) throws InvalidAudioFrameException {
        return new VbriFrame(byteBuffer);
    }

    public static ByteBuffer isVbriFrame(ByteBuffer byteBuffer, MPEGFrameHeader mPEGFrameHeader) {
        int iPosition = byteBuffer.position();
        MP3File.logger.finest("Checking VBRI Frame at" + iPosition);
        byteBuffer.position(iPosition + 36);
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBuffer.position(iPosition);
        byte[] bArr = new byte[4];
        byteBufferSlice.get(bArr);
        if (!Arrays.equals(bArr, VBRI_VBR_ID)) {
            return null;
        }
        MP3File.logger.finest("Found VBRI Frame");
        return byteBufferSlice;
    }

    public String getEncoder() {
        return "Fraunhofer";
    }

    public String toString() {
        return "VBRIheader\n\tvbr:" + this.vbr + "\n\tframeCount:" + this.frameCount + "\n\taudioFileSize:" + this.audioSize + "\n\tencoder:" + getEncoder() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }
}
