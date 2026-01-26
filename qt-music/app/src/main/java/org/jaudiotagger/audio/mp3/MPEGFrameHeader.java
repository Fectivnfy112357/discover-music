package org.jaudiotagger.audio.mp3;

import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.JfifUtil;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.common.base.Ascii;
import com.umeng.ccg.c;
import com.umeng.commonsdk.statistics.UMErrorCode;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.logging.AbstractTagDisplayFormatter;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.mozilla.universalchardet.prober.contextanalysis.EUCJPContextAnalysis;
import org.mozilla.universalchardet.prober.distributionanalysis.EUCTWDistributionAnalysis;

/* loaded from: classes3.dex */
public class MPEGFrameHeader {
    private static final int BYTE_1 = 0;
    private static final int BYTE_2 = 1;
    private static final int BYTE_3 = 2;
    private static final int BYTE_4 = 3;
    public static final int EMPHASIS_5015MS = 1;
    public static final int EMPHASIS_CCITT = 3;
    public static final int EMPHASIS_NONE = 0;
    public static final int EMPHASIS_RESERVED = 2;
    public static final int HEADER_SIZE = 4;
    public static final int LAYER_I = 3;
    public static final int LAYER_II = 2;
    public static final int LAYER_III = 1;
    private static final int LAYER_III_FRAME_SIZE_COEFFICIENT = 144;
    public static final int LAYER_III_SLOT_SIZE = 1;
    private static final int LAYER_II_FRAME_SIZE_COEFFICIENT = 144;
    public static final int LAYER_II_SLOT_SIZE = 1;
    private static final int LAYER_I_FRAME_SIZE_COEFFICIENT = 12;
    public static final int LAYER_I_SLOT_SIZE = 4;
    private static final int MASK_MP3_BITRATE = 240;
    private static final int MASK_MP3_COPY = 8;
    private static final int MASK_MP3_EMPHASIS = 3;
    private static final int MASK_MP3_FREQUENCY = 12;
    private static final int MASK_MP3_HOME = 4;
    private static final int MASK_MP3_ID = 8;
    private static final int MASK_MP3_LAYER = 6;
    private static final int MASK_MP3_MODE = 192;
    private static final int MASK_MP3_MODE_EXTENSION = 48;
    private static final int MASK_MP3_PADDING = 2;
    private static final int MASK_MP3_PRIVACY = 1;
    private static final int MASK_MP3_PROTECTION = 1;
    private static final int MASK_MP3_VERSION = 24;
    public static final int MODE_DUAL_CHANNEL = 2;
    private static final int MODE_EXTENSION_NONE = 0;
    private static final int MODE_EXTENSION_OFF_OFF = 0;
    private static final int MODE_EXTENSION_OFF_ON = 2;
    private static final int MODE_EXTENSION_ONE = 1;
    private static final int MODE_EXTENSION_ON_OFF = 1;
    private static final int MODE_EXTENSION_ON_ON = 3;
    private static final int MODE_EXTENSION_THREE = 3;
    private static final int MODE_EXTENSION_TWO = 2;
    public static final int MODE_JOINT_STEREO = 1;
    public static final int MODE_MONO = 3;
    public static final int MODE_STEREO = 0;
    private static final int SCALE_BY_THOUSAND = 1000;
    public static final int SYNC_BIT_ANDSAMPING_BYTE3 = 252;
    public static final int SYNC_BYTE1 = 255;
    public static final int SYNC_BYTE2 = 224;
    public static final int SYNC_SIZE = 2;
    public static final int VERSION_1 = 3;
    public static final int VERSION_2 = 2;
    public static final int VERSION_2_5 = 0;
    private static final Map<Integer, Integer> bitrateMap;
    private static final Map<Integer, String> emphasisMap;
    private static final byte[] header = new byte[4];
    private static final Map<Integer, String> modeExtensionLayerIIIMap;
    private static final Map<Integer, String> modeExtensionMap;
    public static final Map<Integer, String> modeMap;
    public static final Map<Integer, String> mpegLayerMap;
    public static final Map<Integer, String> mpegVersionMap;
    private static final Map<Integer, Map<Integer, Integer>> samplesPerFrameMap;
    private static final Map<Integer, Integer> samplesPerFrameV1Map;
    private static final Map<Integer, Integer> samplesPerFrameV25Map;
    private static final Map<Integer, Integer> samplesPerFrameV2Map;
    private static final Map<Integer, Map<Integer, Integer>> samplingRateMap;
    private static final Map<Integer, Integer> samplingV1Map;
    private static final Map<Integer, Integer> samplingV25Map;
    private static final Map<Integer, Integer> samplingV2Map;
    private Integer bitRate;
    private int channelMode;
    private String channelModeAsString;
    private int emphasis;
    private String emphasisAsString;
    private boolean isCopyrighted;
    private boolean isOriginal;
    private boolean isPadding;
    private boolean isPrivate;
    private boolean isProtected;
    private int layer;
    private String layerAsString;
    private String modeExtension;
    private byte[] mpegBytes;
    private Integer samplingRate;
    private int version;
    private String versionAsString;

    public boolean isVariableBitRate() {
        return false;
    }

    static {
        HashMap map = new HashMap();
        mpegVersionMap = map;
        map.put(0, "MPEG-2.5");
        map.put(2, "MPEG-2");
        map.put(3, "MPEG-1");
        HashMap map2 = new HashMap();
        mpegLayerMap = map2;
        map2.put(3, "Layer 1");
        map2.put(2, "Layer 2");
        map2.put(1, "Layer 3");
        HashMap map3 = new HashMap();
        bitrateMap = map3;
        map3.put(30, 32);
        map3.put(46, 64);
        map3.put(62, 96);
        map3.put(78, 128);
        map3.put(94, 160);
        map3.put(Integer.valueOf(UMErrorCode.E_UM_BE_JSON_FAILED), 192);
        map3.put(126, 224);
        map3.put(Integer.valueOf(EUCJPContextAnalysis.SINGLE_SHIFT_2), 256);
        map3.put(158, 288);
        map3.put(174, 320);
        map3.put(190, 352);
        map3.put(206, 384);
        map3.put(222, 416);
        map3.put(Integer.valueOf(HebrewProber.NORMAL_MEM), 448);
        map3.put(28, 32);
        map3.put(44, 48);
        map3.put(60, 56);
        map3.put(76, 64);
        map3.put(92, 80);
        map3.put(Integer.valueOf(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR), 96);
        Integer numValueOf = Integer.valueOf(UMErrorCode.E_UM_BE_DEFLATE_FAILED);
        map3.put(124, numValueOf);
        map3.put(140, 128);
        map3.put(Integer.valueOf(VbriFrame.MAX_BUFFER_SIZE_NEEDED_TO_READ_VBRI), 160);
        map3.put(172, 192);
        map3.put(188, 224);
        map3.put(204, 256);
        map3.put(220, 320);
        map3.put(236, 384);
        map3.put(26, 32);
        map3.put(42, 40);
        map3.put(58, 48);
        map3.put(74, 56);
        map3.put(90, 64);
        map3.put(Integer.valueOf(c.f), 80);
        map3.put(122, 96);
        map3.put(138, numValueOf);
        map3.put(154, 128);
        map3.put(170, 160);
        map3.put(186, 192);
        map3.put(Integer.valueOf(c.l), 224);
        map3.put(Integer.valueOf(JfifUtil.MARKER_SOS), 256);
        map3.put(Integer.valueOf(HebrewProber.FINAL_KAF), 320);
        map3.put(22, 32);
        map3.put(38, 48);
        map3.put(54, 56);
        map3.put(70, 64);
        map3.put(86, 80);
        map3.put(102, 96);
        map3.put(118, numValueOf);
        map3.put(134, 128);
        map3.put(150, 144);
        map3.put(166, 160);
        map3.put(182, 176);
        map3.put(198, 192);
        map3.put(214, 224);
        map3.put(230, 256);
        map3.put(20, 8);
        map3.put(36, 16);
        map3.put(52, 24);
        map3.put(68, 32);
        map3.put(84, 40);
        map3.put(100, 48);
        map3.put(116, 56);
        map3.put(132, 64);
        map3.put(148, 80);
        map3.put(164, 96);
        map3.put(Integer.valueOf(RotationOptions.ROTATE_180), numValueOf);
        map3.put(Integer.valueOf(EUCTWDistributionAnalysis.HIGHBYTE_BEGIN), 128);
        map3.put(212, 144);
        map3.put(228, 160);
        map3.put(18, 8);
        map3.put(34, 16);
        map3.put(50, 24);
        map3.put(66, 32);
        map3.put(82, 40);
        map3.put(98, 48);
        map3.put(Integer.valueOf(UMErrorCode.E_UM_BE_FILE_OVERSIZE), 56);
        map3.put(130, 64);
        map3.put(146, 80);
        map3.put(162, 96);
        map3.put(178, numValueOf);
        map3.put(194, 128);
        map3.put(210, 144);
        map3.put(226, 160);
        HashMap map4 = new HashMap();
        modeMap = map4;
        map4.put(0, "Stereo");
        map4.put(1, "Joint Stereo");
        map4.put(2, "Dual");
        map4.put(3, "Mono");
        HashMap map5 = new HashMap();
        emphasisMap = map5;
        map5.put(0, "None");
        map5.put(1, "5015MS");
        map5.put(2, "Reserved");
        map5.put(3, "CCITT");
        HashMap map6 = new HashMap();
        modeExtensionMap = map6;
        HashMap map7 = new HashMap();
        modeExtensionLayerIIIMap = map7;
        map6.put(0, "4-31");
        map6.put(1, "8-31");
        map6.put(2, "12-31");
        map6.put(3, "16-31");
        map7.put(0, "off-off");
        map7.put(1, "on-off");
        map7.put(2, "off-on");
        map7.put(3, "on-on");
        HashMap map8 = new HashMap();
        samplingRateMap = map8;
        HashMap map9 = new HashMap();
        samplingV1Map = map9;
        HashMap map10 = new HashMap();
        samplingV2Map = map10;
        HashMap map11 = new HashMap();
        samplingV25Map = map11;
        map9.put(0, 44100);
        map9.put(1, 48000);
        map9.put(2, 32000);
        map10.put(0, 22050);
        map10.put(1, 24000);
        map10.put(2, 16000);
        map11.put(0, 11025);
        map11.put(1, 12000);
        map11.put(2, 8000);
        map8.put(3, map9);
        map8.put(2, map10);
        map8.put(0, map11);
        HashMap map12 = new HashMap();
        samplesPerFrameMap = map12;
        HashMap map13 = new HashMap();
        samplesPerFrameV1Map = map13;
        HashMap map14 = new HashMap();
        samplesPerFrameV2Map = map14;
        HashMap map15 = new HashMap();
        samplesPerFrameV25Map = map15;
        map13.put(3, 384);
        map13.put(2, 1152);
        map13.put(1, 1152);
        map14.put(3, 384);
        map14.put(2, 1152);
        map14.put(1, 1152);
        map15.put(3, 384);
        map15.put(2, 1152);
        map15.put(1, 1152);
        map12.put(3, map13);
        map12.put(2, map14);
        map12.put(0, map15);
    }

    public int getLayer() {
        return this.layer;
    }

    public String getLayerAsString() {
        return this.layerAsString;
    }

    private void setCopyrighted() {
        this.isCopyrighted = (this.mpegBytes[3] & 8) != 0;
    }

    private void setVersion() throws InvalidAudioFrameException {
        byte b = (byte) ((this.mpegBytes[1] & Ascii.CAN) >> 3);
        this.version = b;
        String str = mpegVersionMap.get(Integer.valueOf(b));
        this.versionAsString = str;
        if (str == null) {
            throw new InvalidAudioFrameException("Invalid mpeg version");
        }
    }

    private void setOriginal() {
        this.isOriginal = (this.mpegBytes[3] & 4) != 0;
    }

    private void setProtected() {
        this.isProtected = (this.mpegBytes[1] & 1) == 0;
    }

    private void setPrivate() {
        this.isPrivate = (this.mpegBytes[2] & 1) != 0;
    }

    private void setBitrate() throws InvalidAudioFrameException {
        byte[] bArr = this.mpegBytes;
        int i = bArr[2] & 240;
        byte b = bArr[1];
        Integer num = bitrateMap.get(Integer.valueOf((b & 6) | i | (b & 8)));
        this.bitRate = num;
        if (num == null) {
            throw new InvalidAudioFrameException("Invalid bitrate");
        }
    }

    private void setChannelMode() throws InvalidAudioFrameException {
        int i = (this.mpegBytes[3] & 192) >>> 6;
        this.channelMode = i;
        String str = modeMap.get(Integer.valueOf(i));
        this.channelModeAsString = str;
        if (str == null) {
            throw new InvalidAudioFrameException("Invalid channel mode");
        }
    }

    private void setEmphasis() throws InvalidAudioFrameException {
        int i = this.mpegBytes[3] & 3;
        this.emphasis = i;
        this.emphasisAsString = emphasisMap.get(Integer.valueOf(i));
        if (getEmphasisAsString() == null) {
            throw new InvalidAudioFrameException("Invalid emphasis");
        }
    }

    private void setPadding() {
        this.isPadding = (this.mpegBytes[2] & 2) != 0;
    }

    private void setLayer() throws InvalidAudioFrameException {
        int i = (this.mpegBytes[1] & 6) >>> 1;
        this.layer = i;
        String str = mpegLayerMap.get(Integer.valueOf(i));
        this.layerAsString = str;
        if (str == null) {
            throw new InvalidAudioFrameException("Invalid Layer");
        }
    }

    private void setModeExtension() throws InvalidAudioFrameException {
        int i = (this.mpegBytes[3] & 48) >> 4;
        if (this.layer == 1) {
            this.modeExtension = modeExtensionLayerIIIMap.get(Integer.valueOf(i));
            if (getModeExtension() == null) {
                throw new InvalidAudioFrameException("Invalid Mode Extension");
            }
        } else {
            this.modeExtension = modeExtensionMap.get(Integer.valueOf(i));
            if (getModeExtension() == null) {
                throw new InvalidAudioFrameException("Invalid Mode Extension");
            }
        }
    }

    private void setSamplingRate() throws InvalidAudioFrameException {
        int i = (this.mpegBytes[2] & 12) >>> 2;
        Map<Integer, Integer> map = samplingRateMap.get(Integer.valueOf(this.version));
        if (map == null) {
            throw new InvalidAudioFrameException("Invalid version");
        }
        Integer num = map.get(Integer.valueOf(i));
        this.samplingRate = num;
        if (num == null) {
            throw new InvalidAudioFrameException("Invalid sampling rate");
        }
    }

    public int getNumberOfChannels() {
        int i = this.channelMode;
        if (i == 0 || i == 1 || i == 2) {
            return 2;
        }
        return i != 3 ? 0 : 1;
    }

    public int getChannelMode() {
        return this.channelMode;
    }

    public String getChannelModeAsString() {
        return this.channelModeAsString;
    }

    public int getVersion() {
        return this.version;
    }

    public String getVersionAsString() {
        return this.versionAsString;
    }

    public int getPaddingLength() {
        return isPadding() ? 1 : 0;
    }

    public Integer getBitRate() {
        return this.bitRate;
    }

    public Integer getSamplingRate() {
        return this.samplingRate;
    }

    public int getFrameLength() {
        int i = this.version;
        if (i == 0 || i == 2) {
            int i2 = this.layer;
            if (i2 == 1) {
                if (getChannelMode() == 3) {
                    return ((getBitRate().intValue() * 72000) / getSamplingRate().intValue()) + getPaddingLength();
                }
                return ((getBitRate().intValue() * 144000) / getSamplingRate().intValue()) + getPaddingLength();
            }
            if (i2 == 2) {
                return ((getBitRate().intValue() * 144000) / getSamplingRate().intValue()) + getPaddingLength();
            }
            if (i2 == 3) {
                return (((getBitRate().intValue() * 12000) / getSamplingRate().intValue()) + getPaddingLength()) * 4;
            }
            throw new RuntimeException("Mp3 Unknown Layer:" + this.layer);
        }
        if (i == 3) {
            int i3 = this.layer;
            if (i3 == 1) {
                return ((getBitRate().intValue() * 144000) / getSamplingRate().intValue()) + getPaddingLength();
            }
            if (i3 == 2) {
                return ((getBitRate().intValue() * 144000) / getSamplingRate().intValue()) + getPaddingLength();
            }
            if (i3 == 3) {
                return (((getBitRate().intValue() * 12000) / getSamplingRate().intValue()) + getPaddingLength()) * 4;
            }
            throw new RuntimeException("Mp3 Unknown Layer:" + this.layer);
        }
        throw new RuntimeException("Mp3 Unknown Version:" + this.version);
    }

    public int getNoOfSamples() {
        return samplesPerFrameMap.get(Integer.valueOf(this.version)).get(Integer.valueOf(this.layer)).intValue();
    }

    public boolean isPadding() {
        return this.isPadding;
    }

    public boolean isCopyrighted() {
        return this.isCopyrighted;
    }

    public boolean isOriginal() {
        return this.isOriginal;
    }

    public boolean isProtected() {
        return this.isProtected;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public int getEmphasis() {
        return this.emphasis;
    }

    public String getEmphasisAsString() {
        return this.emphasisAsString;
    }

    public String getModeExtension() {
        return this.modeExtension;
    }

    private MPEGFrameHeader() throws InvalidAudioFrameException {
    }

    private MPEGFrameHeader(byte[] bArr) throws InvalidAudioFrameException {
        this.mpegBytes = bArr;
        setBitrate();
        setVersion();
        setLayer();
        setProtected();
        setSamplingRate();
        setPadding();
        setPrivate();
        setChannelMode();
        setModeExtension();
        setCopyrighted();
        setOriginal();
        setEmphasis();
    }

    public static MPEGFrameHeader parseMPEGHeader(ByteBuffer byteBuffer) throws InvalidAudioFrameException {
        int iPosition = byteBuffer.position();
        byte[] bArr = header;
        byteBuffer.get(bArr, 0, 4);
        byteBuffer.position(iPosition);
        return new MPEGFrameHeader(bArr);
    }

    public static boolean isMPEGFrame(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        return (byteBuffer.get(iPosition) & 255) == 255 && (byteBuffer.get(iPosition + 1) & 224) == 224 && (byteBuffer.get(iPosition + 2) & 252) != 252;
    }

    public String toString() {
        return "MPEG Frame Header:\n\tframe length:" + getFrameLength() + "\n\tversion:" + this.versionAsString + "\n\tlayer:" + this.layerAsString + "\n\tchannelMode:" + this.channelModeAsString + "\n\tnoOfSamples:" + getNoOfSamples() + "\n\tsamplingRate:" + this.samplingRate + "\n\tisPadding:" + this.isPadding + "\n\tisProtected:" + this.isProtected + "\n\tisPrivate:" + this.isPrivate + "\n\tisCopyrighted:" + this.isCopyrighted + "\n\tisOriginal:" + this.isCopyrighted + "\n\tisVariableBitRate" + isVariableBitRate() + "\n\theader as binary:\n\t" + AbstractTagDisplayFormatter.displayAsBinary(this.mpegBytes[0]) + " \t" + AbstractTagDisplayFormatter.displayAsBinary(this.mpegBytes[1]) + " \t" + AbstractTagDisplayFormatter.displayAsBinary(this.mpegBytes[2]) + " \t" + AbstractTagDisplayFormatter.displayAsBinary(this.mpegBytes[3]) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }
}
