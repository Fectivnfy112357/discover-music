package org.jaudiotagger.audio.generic;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import org.jaudiotagger.audio.AudioHeader;

/* loaded from: classes3.dex */
public class GenericAudioHeader implements AudioHeader {
    private Long audioDataEndPosition;
    private Long audioDataLength;
    private Long audioDataStartPosition;
    private Integer bitRate;
    private Integer bitsPerSample;
    private Integer byteRate;
    private String encodingType;
    private String format;
    private Boolean isLossless;
    private Boolean isVbr = Boolean.TRUE;
    private Integer noOfChannels;
    private Long noOfSamples;
    private Integer samplingRate;
    private Double trackLength;

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getBitRate() {
        return String.valueOf(this.bitRate);
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public long getBitRateAsNumber() {
        return this.bitRate.intValue();
    }

    public int getChannelNumber() {
        return this.noOfChannels.intValue();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getChannels() {
        return String.valueOf(getChannelNumber());
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getEncodingType() {
        return this.encodingType;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getFormat() {
        return this.format;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public int getTrackLength() {
        return (int) Math.round(getPreciseTrackLength());
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public double getPreciseTrackLength() {
        return this.trackLength.doubleValue();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public String getSampleRate() {
        return String.valueOf(this.samplingRate);
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public int getSampleRateAsNumber() {
        return this.samplingRate.intValue();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public int getBitsPerSample() {
        Integer num = this.bitsPerSample;
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public boolean isVariableBitRate() {
        Boolean bool = this.isVbr;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public boolean isLossless() {
        Boolean bool = this.isLossless;
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    public void setBitRate(int i) {
        this.bitRate = Integer.valueOf(i);
    }

    public void setChannelNumber(int i) {
        this.noOfChannels = Integer.valueOf(i);
    }

    public void setEncodingType(String str) {
        this.encodingType = str;
    }

    public void setFormat(String str) {
        this.format = str;
    }

    public void setPreciseLength(double d) {
        this.trackLength = Double.valueOf(d);
    }

    public void setSamplingRate(int i) {
        this.samplingRate = Integer.valueOf(i);
    }

    public void setBitsPerSample(int i) {
        this.bitsPerSample = Integer.valueOf(i);
    }

    public void setByteRate(int i) {
        this.byteRate = Integer.valueOf(i);
    }

    public void setVariableBitRate(boolean z) {
        this.isVbr = Boolean.valueOf(z);
    }

    public void setLossless(boolean z) {
        this.isLossless = Boolean.valueOf(z);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Audio Header content:\n");
        if (this.audioDataLength != null) {
            sb.append("\taudioDataLength:" + this.audioDataLength + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.audioDataStartPosition != null) {
            sb.append("\taudioDataStartPosition:" + this.audioDataStartPosition + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.audioDataEndPosition != null) {
            sb.append("\taudioDataEndPosition:" + this.audioDataEndPosition + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.byteRate != null) {
            sb.append("\tbyteRate:" + this.byteRate + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.bitRate != null) {
            sb.append("\tbitRate:" + this.bitRate + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.samplingRate != null) {
            sb.append("\tsamplingRate:" + this.samplingRate + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.bitsPerSample != null) {
            sb.append("\tbitsPerSample:" + this.bitsPerSample + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.noOfSamples != null) {
            sb.append("\ttotalNoSamples:" + this.noOfSamples + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.noOfChannels != null) {
            sb.append("\tnumberOfChannels:" + this.noOfChannels + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.format != null) {
            sb.append("\tformat:" + this.format + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.encodingType != null) {
            sb.append("\tencodingType:" + this.encodingType + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.isVbr != null) {
            sb.append("\tisVbr:" + this.isVbr + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.isLossless != null) {
            sb.append("\tisLossless:" + this.isLossless + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.trackLength != null) {
            sb.append("\ttrackDuration:" + this.trackLength + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Long getAudioDataLength() {
        return this.audioDataLength;
    }

    public void setAudioDataLength(long j) {
        this.audioDataLength = Long.valueOf(j);
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Integer getByteRate() {
        return this.byteRate;
    }

    @Override // org.jaudiotagger.audio.AudioHeader
    public Long getNoOfSamples() {
        return this.noOfSamples;
    }

    public void setNoOfSamples(Long l) {
        this.noOfSamples = l;
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
