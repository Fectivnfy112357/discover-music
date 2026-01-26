package org.jaudiotagger.audio.ogg.util;

import com.google.common.base.Ascii;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.jaudiotagger.audio.ogg.VorbisVersion;

/* loaded from: classes3.dex */
public class VorbisIdentificationHeader implements VorbisHeader {
    public static final int FIELD_AUDIO_CHANNELS_LENGTH = 1;
    public static final int FIELD_AUDIO_CHANNELS_POS = 11;
    public static final int FIELD_AUDIO_SAMPLE_RATE_LENGTH = 4;
    public static final int FIELD_AUDIO_SAMPLE_RATE_POS = 12;
    public static final int FIELD_BITRATE_MAX_LENGTH = 4;
    public static final int FIELD_BITRATE_MAX_POS = 16;
    public static final int FIELD_BITRATE_MIN_LENGTH = 4;
    public static final int FIELD_BITRATE_MIN_POS = 24;
    public static final int FIELD_BITRATE_NOMAIML_LENGTH = 4;
    public static final int FIELD_BITRATE_NOMAIML_POS = 20;
    public static final int FIELD_BLOCKSIZE_LENGTH = 1;
    public static final int FIELD_BLOCKSIZE_POS = 28;
    public static final int FIELD_FRAMING_FLAG_LENGTH = 1;
    public static final int FIELD_FRAMING_FLAG_POS = 29;
    public static final int FIELD_VORBIS_VERSION_LENGTH = 4;
    public static final int FIELD_VORBIS_VERSION_POS = 7;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg.atom");
    private int audioChannels;
    private int audioSampleRate;
    private int bitrateMaximal;
    private int bitrateMinimal;
    private int bitrateNominal;
    private boolean isValid = false;
    private int vorbisVersion;

    private int u(int i) {
        return i & 255;
    }

    public VorbisIdentificationHeader(byte[] bArr) {
        decodeHeader(bArr);
    }

    public int getChannelNumber() {
        return this.audioChannels;
    }

    public String getEncodingType() {
        return VorbisVersion.values()[this.vorbisVersion].toString();
    }

    public int getSamplingRate() {
        return this.audioSampleRate;
    }

    public int getNominalBitrate() {
        return this.bitrateNominal;
    }

    public int getMaxBitrate() {
        return this.bitrateMaximal;
    }

    public int getMinBitrate() {
        return this.bitrateMinimal;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void decodeHeader(byte[] bArr) {
        byte b = bArr[0];
        logger.fine("packetType" + ((int) b));
        String str = new String(bArr, 1, 6, StandardCharsets.ISO_8859_1);
        if (b == VorbisPacketType.IDENTIFICATION_HEADER.getType() && str.equals(VorbisHeader.CAPTURE_PATTERN)) {
            this.vorbisVersion = bArr[7] + (bArr[8] << 8) + (bArr[9] << 16) + (bArr[10] << Ascii.CAN);
            logger.fine("vorbisVersion" + this.vorbisVersion);
            this.audioChannels = u(bArr[11]);
            logger.fine("audioChannels" + this.audioChannels);
            this.audioSampleRate = u(bArr[12]) + (u(bArr[13]) << 8) + (u(bArr[14]) << 16) + (u(bArr[15]) << 24);
            logger.fine("audioSampleRate" + this.audioSampleRate);
            logger.fine("audioSampleRate" + ((int) bArr[12]) + " " + ((int) bArr[13]) + " " + ((int) bArr[14]));
            this.bitrateMinimal = u(bArr[16]) + (u(bArr[17]) << 8) + (u(bArr[18]) << 16) + (u(bArr[19]) << 24);
            this.bitrateNominal = u(bArr[20]) + (u(bArr[21]) << 8) + (u(bArr[22]) << 16) + (u(bArr[23]) << 24);
            this.bitrateMaximal = u(bArr[24]) + (u(bArr[25]) << 8) + (u(bArr[26]) << 16) + (u(bArr[27]) << 24);
            byte b2 = bArr[29];
            logger.fine("framingFlag" + ((int) b2));
            if (b2 != 0) {
                this.isValid = true;
            }
        }
    }
}
