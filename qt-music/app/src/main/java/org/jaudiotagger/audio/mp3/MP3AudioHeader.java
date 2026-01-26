package org.jaudiotagger.audio.mp3;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.logging.Hex;

/* loaded from: classes3.dex */
public class MP3AudioHeader implements AudioHeader {
    private static final int FILE_BUFFER_SIZE = 5000;
    private static final int MIN_BUFFER_REMAINING_REQUIRED = 196;
    private static final int NO_SECONDS_IN_HOUR = 3600;
    private static final char isVbrIdentifier = '~';
    private Long audioDataEndPosition;
    private Long audioDataStartPosition;
    private long bitrate;
    private String encoder = "";
    private long fileSize;
    protected MPEGFrameHeader mp3FrameHeader;
    protected VbriFrame mp3VbriFrame;
    protected XingFrame mp3XingFrame;
    private long numberOfFrames;
    private long numberOfFramesEstimate;
    private long startByte;
    private double timePerFrame;
    private double trackLength;
    private static final SimpleDateFormat timeInFormat = new SimpleDateFormat("ss", Locale.UK);
    private static final SimpleDateFormat timeOutFormat = new SimpleDateFormat("mm:ss", Locale.UK);
    private static final SimpleDateFormat timeOutOverAnHourFormat = new SimpleDateFormat("kk:mm:ss", Locale.UK);
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.mp3");

    @Override // org.jaudiotagger.audio.AudioHeader
    public int getBitsPerSample() {
        return 16;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Integer getByteRate() {
        return null;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public boolean isLossless() {
        return false;
    }

    public MP3AudioHeader() {
    }

    public MP3AudioHeader(File file) throws InvalidAudioFrameException, IOException {
        if (!seek(file, 0L)) {
            throw new InvalidAudioFrameException("No audio header found within" + file.getName());
        }
    }

    public MP3AudioHeader(File file, long j) throws InvalidAudioFrameException, IOException {
        if (!seek(file, j)) {
            throw new InvalidAudioFrameException(ErrorMessage.NO_AUDIO_HEADER_FOUND.getMsg(file.getName()));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bc A[Catch: all -> 0x00d3, IOException -> 0x00d5, EOFException -> 0x00e0, PHI: r1
  0x00bc: PHI (r1v18 boolean) = (r1v2 boolean), (r1v2 boolean), (r1v9 boolean), (r1v16 boolean) binds: [B:13:0x0046, B:63:0x00bc, B:36:0x00bb, B:34:0x00b8] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #4 {IOException -> 0x00d5, blocks: (B:3:0x001a, B:5:0x0022, B:11:0x0041, B:14:0x0048, B:16:0x0052, B:17:0x006a, B:18:0x0070, B:20:0x0076, B:22:0x0080, B:23:0x0087, B:25:0x008e, B:27:0x0096, B:29:0x00a0, B:30:0x00a7, B:33:0x00b4, B:37:0x00bc), top: B:70:0x001a, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cc A[PHI: r9 r13
  0x00cc: PHI (r9v2 boolean) = (r9v0 boolean), (r9v4 boolean) binds: [B:51:0x00ea, B:41:0x00ca] A[DONT_GENERATE, DONT_INLINE]
  0x00cc: PHI (r13v4 long) = (r13v1 long), (r13v5 long) binds: [B:51:0x00ea, B:41:0x00ca] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00f7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean seek(java.io.File r12, long r13) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 302
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.mp3.MP3AudioHeader.seek(java.io.File, long):boolean");
    }

    private boolean isNextFrameValid(File file, long j, ByteBuffer byteBuffer, FileChannel fileChannel) throws IOException {
        if (logger.isLoggable(Level.FINEST)) {
            logger.finer("Checking next frame" + file.getName() + ":fpc:" + j + "skipping to:" + (this.mp3FrameHeader.getFrameLength() + j));
        }
        int iPosition = byteBuffer.position();
        boolean z = false;
        if (this.mp3FrameHeader.getFrameLength() > 4804) {
            logger.finer("Frame size is too large to be a frame:" + this.mp3FrameHeader.getFrameLength());
            return false;
        }
        if (byteBuffer.remaining() <= this.mp3FrameHeader.getFrameLength() + 196) {
            logger.finer("Buffer too small, need to reload, buffer size:" + byteBuffer.remaining());
            byteBuffer.clear();
            fileChannel.position(j);
            fileChannel.read(byteBuffer, fileChannel.position());
            byteBuffer.flip();
            if (byteBuffer.limit() <= 196) {
                logger.finer("Nearly at end of file, no header found:");
                return false;
            }
            if (byteBuffer.limit() <= this.mp3FrameHeader.getFrameLength() + 196) {
                logger.finer("Nearly at end of file, no room for next frame, no header found:");
                return false;
            }
            iPosition = 0;
        }
        byteBuffer.position(byteBuffer.position() + this.mp3FrameHeader.getFrameLength());
        if (MPEGFrameHeader.isMPEGFrame(byteBuffer)) {
            try {
                MPEGFrameHeader.parseMPEGHeader(byteBuffer);
                logger.finer("Check next frame confirms is an audio header ");
                z = true;
            } catch (InvalidAudioFrameException unused) {
                logger.finer("Check next frame has identified this is not an audio header");
            }
        } else {
            logger.finer("isMPEGFrame has identified this is not an audio header");
        }
        byteBuffer.position(iPosition);
        return z;
    }

    protected void setMp3StartByte(long j) {
        this.startByte = j;
    }

    public long getMp3StartByte() {
        return this.startByte;
    }

    protected void setNumberOfFrames() {
        this.numberOfFramesEstimate = (this.fileSize - this.startByte) / this.mp3FrameHeader.getFrameLength();
        XingFrame xingFrame = this.mp3XingFrame;
        if (xingFrame != null && xingFrame.isFrameCountEnabled()) {
            this.numberOfFrames = this.mp3XingFrame.getFrameCount();
            return;
        }
        if (this.mp3VbriFrame != null) {
            this.numberOfFrames = r0.getFrameCount();
        } else {
            this.numberOfFrames = this.numberOfFramesEstimate;
        }
    }

    public long getNumberOfFrames() {
        return this.numberOfFrames;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Long getNoOfSamples() {
        return Long.valueOf(this.numberOfFrames);
    }

    public long getNumberOfFramesEstimate() {
        return this.numberOfFramesEstimate;
    }

    protected void setTimePerFrame() {
        this.timePerFrame = this.mp3FrameHeader.getNoOfSamples() / this.mp3FrameHeader.getSamplingRate().doubleValue();
        if (this.mp3FrameHeader.getVersion() == 2 || this.mp3FrameHeader.getVersion() == 0) {
            if ((this.mp3FrameHeader.getLayer() == 2 || this.mp3FrameHeader.getLayer() == 1) && this.mp3FrameHeader.getNumberOfChannels() == 1) {
                this.timePerFrame /= 2.0d;
            }
        }
    }

    private double getTimePerFrame() {
        return this.timePerFrame;
    }

    protected void setTrackLength() {
        this.trackLength = this.numberOfFrames * getTimePerFrame();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public double getPreciseTrackLength() {
        return this.trackLength;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public int getTrackLength() {
        return (int) Math.round(getPreciseTrackLength());
    }

    public String getTrackLengthAsString() {
        Date date;
        String str;
        String str2;
        try {
            long trackLength = getTrackLength();
            SimpleDateFormat simpleDateFormat = timeInFormat;
            synchronized (simpleDateFormat) {
                date = simpleDateFormat.parse(String.valueOf(trackLength));
            }
            if (trackLength < 3600) {
                SimpleDateFormat simpleDateFormat2 = timeOutFormat;
                synchronized (simpleDateFormat2) {
                    str2 = simpleDateFormat2.format(date);
                }
                return str2;
            }
            SimpleDateFormat simpleDateFormat3 = timeOutOverAnHourFormat;
            synchronized (simpleDateFormat3) {
                str = simpleDateFormat3.format(date);
            }
            return str;
        } catch (ParseException e) {
            logger.warning("Unable to parse:" + getPreciseTrackLength() + " failed with ParseException:" + e.getMessage());
            return "";
        }
        logger.warning("Unable to parse:" + getPreciseTrackLength() + " failed with ParseException:" + e.getMessage());
        return "";
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getEncodingType() {
        return this.mp3FrameHeader.getVersionAsString() + " " + this.mp3FrameHeader.getLayerAsString();
    }

    protected void setBitRate() {
        XingFrame xingFrame = this.mp3XingFrame;
        if (xingFrame != null && xingFrame.isVbr()) {
            if (this.mp3XingFrame.isAudioSizeEnabled() && this.mp3XingFrame.getAudioSize() > 0) {
                this.bitrate = (long) ((this.mp3XingFrame.getAudioSize() * Utils.BITS_IN_BYTE_MULTIPLIER) / ((this.timePerFrame * getNumberOfFrames()) * Utils.KILOBYTE_MULTIPLIER));
                return;
            } else {
                this.bitrate = (long) (((this.fileSize - this.startByte) * Utils.BITS_IN_BYTE_MULTIPLIER) / ((this.timePerFrame * getNumberOfFrames()) * Utils.KILOBYTE_MULTIPLIER));
                return;
            }
        }
        VbriFrame vbriFrame = this.mp3VbriFrame;
        if (vbriFrame != null) {
            if (vbriFrame.getAudioSize() > 0) {
                this.bitrate = (long) ((this.mp3VbriFrame.getAudioSize() * Utils.BITS_IN_BYTE_MULTIPLIER) / ((this.timePerFrame * getNumberOfFrames()) * Utils.KILOBYTE_MULTIPLIER));
                return;
            } else {
                this.bitrate = (long) (((this.fileSize - this.startByte) * Utils.BITS_IN_BYTE_MULTIPLIER) / ((this.timePerFrame * getNumberOfFrames()) * Utils.KILOBYTE_MULTIPLIER));
                return;
            }
        }
        this.bitrate = this.mp3FrameHeader.getBitRate().intValue();
    }

    protected void setEncoder() {
        XingFrame xingFrame = this.mp3XingFrame;
        if (xingFrame != null) {
            if (xingFrame.getLameFrame() != null) {
                this.encoder = this.mp3XingFrame.getLameFrame().getEncoder();
            }
        } else {
            VbriFrame vbriFrame = this.mp3VbriFrame;
            if (vbriFrame != null) {
                this.encoder = vbriFrame.getEncoder();
            }
        }
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public long getBitRateAsNumber() {
        return this.bitrate;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getBitRate() {
        XingFrame xingFrame = this.mp3XingFrame;
        if (xingFrame != null && xingFrame.isVbr()) {
            return "~" + String.valueOf(this.bitrate);
        }
        if (this.mp3VbriFrame != null) {
            return "~" + String.valueOf(this.bitrate);
        }
        return String.valueOf(this.bitrate);
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public int getSampleRateAsNumber() {
        return this.mp3FrameHeader.getSamplingRate().intValue();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getSampleRate() {
        return String.valueOf(this.mp3FrameHeader.getSamplingRate());
    }

    public String getMpegVersion() {
        return this.mp3FrameHeader.getVersionAsString();
    }

    public String getMpegLayer() {
        return this.mp3FrameHeader.getLayerAsString();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getFormat() {
        return SupportedFileFormat.MP3.getDisplayName();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getChannels() {
        return this.mp3FrameHeader.getChannelModeAsString();
    }

    public String getEmphasis() {
        return this.mp3FrameHeader.getEmphasisAsString();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public boolean isVariableBitRate() {
        XingFrame xingFrame = this.mp3XingFrame;
        if (xingFrame != null) {
            return xingFrame.isVbr();
        }
        VbriFrame vbriFrame = this.mp3VbriFrame;
        if (vbriFrame != null) {
            return vbriFrame.isVbr();
        }
        return this.mp3FrameHeader.isVariableBitRate();
    }

    public boolean isProtected() {
        return this.mp3FrameHeader.isProtected();
    }

    public boolean isPrivate() {
        return this.mp3FrameHeader.isPrivate();
    }

    public boolean isCopyrighted() {
        return this.mp3FrameHeader.isCopyrighted();
    }

    public boolean isOriginal() {
        return this.mp3FrameHeader.isOriginal();
    }

    public boolean isPadding() {
        return this.mp3FrameHeader.isPadding();
    }

    public String getEncoder() {
        return this.encoder;
    }

    protected void setFileSize(long j) {
        this.fileSize = j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Audio Header content:\n");
        sb.append("\tfileSize:" + this.fileSize + "\n\tencoder:" + this.encoder + "\n\tencoderType:" + getEncodingType() + "\n\tformat:" + getFormat() + "\n\tstartByte:" + Hex.asHex(this.startByte) + "\n\tnumberOfFrames:" + this.numberOfFrames + "\n\tnumberOfFramesEst:" + this.numberOfFramesEstimate + "\n\ttimePerFrame:" + this.timePerFrame + "\n\tbitrate:" + this.bitrate + "\n\ttrackLength:" + getTrackLengthAsString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        MPEGFrameHeader mPEGFrameHeader = this.mp3FrameHeader;
        if (mPEGFrameHeader != null) {
            sb.append(mPEGFrameHeader.toString());
        } else {
            sb.append("MPEG Frame Header:false");
        }
        XingFrame xingFrame = this.mp3XingFrame;
        if (xingFrame != null) {
            sb.append(xingFrame.toString());
        } else {
            sb.append("Xing Frame:false");
        }
        VbriFrame vbriFrame = this.mp3VbriFrame;
        if (vbriFrame != null) {
            sb.append(vbriFrame.toString());
        } else {
            sb.append("VBRI Frame:false");
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Long getAudioDataLength() {
        return 0L;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Long getAudioDataStartPosition() {
        return this.audioDataStartPosition;
    }

    public void setAudioDataStartPosition(Long l) {
        this.audioDataStartPosition = l;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Long getAudioDataEndPosition() {
        return this.audioDataEndPosition;
    }

    public void setAudioDataEndPosition(Long l) {
        this.audioDataEndPosition = l;
    }
}
