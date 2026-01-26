package org.jaudiotagger.audio;

/* loaded from: classes3.dex */
public interface AudioHeader {
    Long getAudioDataEndPosition();

    Long getAudioDataLength();

    Long getAudioDataStartPosition();

    String getBitRate();

    long getBitRateAsNumber();

    int getBitsPerSample();

    Integer getByteRate();

    String getChannels();

    String getEncodingType();

    String getFormat();

    Long getNoOfSamples();

    double getPreciseTrackLength();

    String getSampleRate();

    int getSampleRateAsNumber();

    int getTrackLength();

    boolean isLossless();

    boolean isVariableBitRate();
}
