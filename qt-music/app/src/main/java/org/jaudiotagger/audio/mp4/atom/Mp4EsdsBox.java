package org.jaudiotagger.audio.mp4.atom;

import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.imageutils.JfifUtil;
import com.umeng.ccg.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import org.jaudiotagger.audio.generic.Utils;
import org.mozilla.universalchardet.prober.contextanalysis.SJISContextAnalysis;

/* loaded from: classes3.dex */
public class Mp4EsdsBox extends AbstractMp4Box {
    public static final int AVERAGE_BITRATE_LENGTH = 4;
    public static final int BUFFER_SIZE_LENGTH = 3;
    public static final int CHANNEL_FLAGS_LENGTH = 1;
    public static final int CONFIG_TYPE_LENGTH = 1;
    public static final int DESCRIPTOR_OBJECT_TYPE_LENGTH = 1;
    public static final int DESCRIPTOR_TYPE_LENGTH = 1;
    public static final int ES_ID_LENGTH = 2;
    private static final int FILLER_END = 254;
    private static final int FILLER_OTHER = 129;
    private static final int FILLER_START = 128;
    public static final int MAX_BITRATE_LENGTH = 4;
    public static final int OBJECT_TYPE_LENGTH = 1;
    public static final int OTHER_FLAG_LENGTH = 3;
    private static final int SECTION_FIVE = 5;
    private static final int SECTION_FOUR = 4;
    private static final int SECTION_THREE = 3;
    public static final int STREAM_PRIORITY_LENGTH = 1;
    public static final int STREAM_TYPE_LENGTH = 1;
    public static final int VERSION_FLAG_LENGTH = 1;
    private static Map<Integer, AudioProfile> audioProfileMap;
    private static Map<Integer, Kind> kindMap = new HashMap();
    private AudioProfile audioProfile;
    private int avgBitrate;
    private Kind kind;
    private int maxBitrate;
    private int numberOfChannels;

    static {
        for (Kind kind : Kind.values()) {
            kindMap.put(Integer.valueOf(kind.getId()), kind);
        }
        audioProfileMap = new HashMap();
        for (AudioProfile audioProfile : AudioProfile.values()) {
            audioProfileMap.put(Integer.valueOf(audioProfile.getId()), audioProfile);
        }
    }

    public Mp4EsdsBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        byteBuffer.position(byteBuffer.position() + 4);
        if (byteBuffer.get() == 3) {
            processSectionHeader(byteBuffer);
            byteBuffer.position(byteBuffer.position() + 3);
        }
        if (byteBuffer.get() == 4) {
            processSectionHeader(byteBuffer);
            this.kind = kindMap.get(Integer.valueOf(byteBuffer.get()));
            byteBuffer.position(byteBuffer.position() + 4);
            this.maxBitrate = byteBuffer.getInt();
            this.avgBitrate = byteBuffer.getInt();
        }
        if (byteBuffer.get() == 5) {
            processSectionHeader(byteBuffer);
            this.audioProfile = audioProfileMap.get(Integer.valueOf(byteBuffer.get() >> 3));
            this.numberOfChannels = (byteBuffer.get() << 1) >> 4;
        }
    }

    public int getNumberOfChannels() {
        return this.numberOfChannels;
    }

    public int getMaxBitrate() {
        return this.maxBitrate;
    }

    public int getAvgBitrate() {
        return this.avgBitrate;
    }

    public int processSectionHeader(ByteBuffer byteBuffer) {
        byte b = byteBuffer.get();
        int i = b & 255;
        if (i == 128 || i == 129 || i == 254) {
            byteBuffer.get();
            byteBuffer.get();
            return Utils.u(byteBuffer.get());
        }
        return Utils.u(b);
    }

    public Kind getKind() {
        return this.kind;
    }

    public AudioProfile getAudioProfile() {
        return this.audioProfile;
    }

    public enum Kind {
        V1(1),
        V2(2),
        MPEG4_VIDEO(32),
        MPEG4_AVC_SPS(33),
        MPEG4_AVC_PPS(34),
        MPEG4_AUDIO(64),
        MPEG2_SIMPLE_VIDEO(96),
        MPEG2_MAIN_VIDEO(97),
        MPEG2_SNR_VIDEO(98),
        MPEG2_SPATIAL_VIDEO(99),
        MPEG2_HIGH_VIDEO(100),
        MPEG2_422_VIDEO(101),
        MPEG4_ADTS_MAIN(102),
        MPEG4_ADTS_LOW_COMPLEXITY(c.c),
        MPEG4_ADTS_SCALEABLE_SAMPLING(104),
        MPEG2_ADTS_MAIN(c.e),
        MPEG1_VIDEO(c.f),
        MPEG1_ADTS(c.g),
        JPEG_VIDEO(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR),
        PRIVATE_AUDIO(192),
        PRIVATE_VIDEO(JfifUtil.MARKER_RST0),
        PCM_LITTLE_ENDIAN_AUDIO(224),
        VORBIS_AUDIO(JfifUtil.MARKER_APP1),
        DOLBY_V3_AUDIO(226),
        ALAW_AUDIO(227),
        MULAW_AUDIO(228),
        ADPCM_AUDIO(229),
        PCM_BIG_ENDIAN_AUDIO(230),
        YV12_VIDEO(240),
        H264_VIDEO(SJISContextAnalysis.HIRAGANA_LOWBYTE_END),
        H263_VIDEO(242),
        H261_VIDEO(243);

        private int id;

        Kind(int i) {
            this.id = i;
        }

        public int getId() {
            return this.id;
        }
    }

    public enum AudioProfile {
        MAIN(1, "Main"),
        LOW_COMPLEXITY(2, "Low Complexity"),
        SCALEABLE(3, "Scaleable Sample rate"),
        T_F(4, "T/F"),
        T_F_MAIN(5, "T/F Main"),
        T_F_LC(6, "T/F LC"),
        TWIN_VQ(7, "TWIN"),
        CELP(8, "CELP"),
        HVXC(9, "HVXC"),
        HILN(10, "HILN"),
        TTSI(11, "TTSI"),
        MAIN_SYNTHESIS(12, "MAIN_SYNTHESIS"),
        WAVETABLE(13, "WAVETABLE");

        private String description;
        private int id;

        AudioProfile(int i, String str) {
            this.id = i;
            this.description = str;
        }

        public int getId() {
            return this.id;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
