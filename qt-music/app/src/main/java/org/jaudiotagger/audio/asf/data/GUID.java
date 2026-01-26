package org.jaudiotagger.audio.asf.data;

import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.localmediametadata.Utils;
import com.umeng.ccg.c;
import com.umeng.commonsdk.statistics.UMErrorCode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.jaudiotagger.audio.mp3.MPEGFrameHeader;
import org.mozilla.universalchardet.prober.HebrewProber;
import org.mozilla.universalchardet.prober.contextanalysis.EUCJPContextAnalysis;
import org.mozilla.universalchardet.prober.contextanalysis.SJISContextAnalysis;

/* loaded from: classes3.dex */
public final class GUID {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final GUID GUID_AUDIOSTREAM;
    public static final GUID GUID_AUDIO_ERROR_CONCEALEMENT_ABSENT;
    public static final GUID GUID_AUDIO_ERROR_CONCEALEMENT_INTERLEAVED;
    public static final GUID GUID_CONTENTDESCRIPTION;
    public static final GUID GUID_CONTENT_BRANDING;
    public static final GUID GUID_CONTENT_ENCRYPTION;
    public static final GUID GUID_ENCODING;
    public static final GUID GUID_EXTENDED_CONTENT_DESCRIPTION;
    public static final GUID GUID_FILE;
    public static final GUID GUID_HEADER;
    public static final GUID GUID_HEADER_EXTENSION;
    public static final GUID GUID_LANGUAGE_LIST;
    public static final int GUID_LENGTH = 16;
    public static final GUID GUID_METADATA;
    public static final GUID GUID_METADATA_LIBRARY;
    private static final Pattern GUID_PATTERN;
    public static final GUID GUID_STREAM;
    public static final GUID GUID_STREAM_BITRATE_PROPERTIES;
    private static final Map<GUID, GUID> GUID_TO_CONFIGURED;
    public static final GUID GUID_UNSPECIFIED;
    public static final GUID GUID_VIDEOSTREAM;
    public static final GUID[] KNOWN_GUIDS;
    public static final GUID SCRIPT_COMMAND_OBJECT;
    private String description;
    private int[] guidData;
    private int hash;

    static {
        GUID guid = new GUID(new int[]{64, 164, SJISContextAnalysis.HIRAGANA_LOWBYTE_END, 73, 206, 78, JfifUtil.MARKER_RST0, 17, 163, 172, 0, 160, c.k, 3, 72, HebrewProber.NORMAL_TSADI}, "Audio error concealment absent.");
        GUID_AUDIO_ERROR_CONCEALEMENT_ABSENT = guid;
        GUID_AUDIO_ERROR_CONCEALEMENT_INTERLEAVED = new GUID(new int[]{64, 164, SJISContextAnalysis.HIRAGANA_LOWBYTE_END, 73, 206, 78, JfifUtil.MARKER_RST0, 17, 163, 172, 0, 160, c.k, 3, 72, HebrewProber.NORMAL_TSADI}, "Interleaved audio error concealment.");
        GUID guid2 = new GUID(new int[]{64, 158, c.e, 248, 77, 91, 207, 17, 168, 253, 0, 128, 95, 92, 68, 43}, " Audio stream");
        GUID_AUDIOSTREAM = guid2;
        GUID guid3 = new GUID(new int[]{250, 179, 17, 34, 35, 189, 210, 17, RotationOptions.ROTATE_180, 183, 0, 160, c.k, 85, MPEGFrameHeader.SYNC_BIT_ANDSAMPING_BYTE3, UMErrorCode.E_UM_BE_JSON_FAILED}, "Content Branding");
        GUID_CONTENT_BRANDING = guid3;
        GUID guid4 = new GUID(new int[]{251, 179, 17, 34, 35, 189, 210, 17, RotationOptions.ROTATE_180, 183, 0, 160, c.k, 85, MPEGFrameHeader.SYNC_BIT_ANDSAMPING_BYTE3, UMErrorCode.E_UM_BE_JSON_FAILED}, "Content Encryption Object");
        GUID_CONTENT_ENCRYPTION = guid4;
        GUID guid5 = new GUID(new int[]{51, 38, 178, 117, EUCJPContextAnalysis.SINGLE_SHIFT_2, 102, 207, 17, 166, JfifUtil.MARKER_EOI, 0, 170, 0, 98, 206, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR}, "Content Description");
        GUID_CONTENTDESCRIPTION = guid5;
        GUID guid6 = new GUID(new int[]{64, 82, 209, 134, 29, 49, JfifUtil.MARKER_RST0, 17, 163, 164, 0, 160, c.k, 3, 72, HebrewProber.NORMAL_TSADI}, "Encoding description");
        GUID_ENCODING = guid6;
        GUID guid7 = new GUID(new int[]{64, 164, JfifUtil.MARKER_RST0, 210, 7, 227, 210, 17, 151, 240, 0, 160, c.k, 94, 168, 80}, "Extended Content Description");
        GUID_EXTENDED_CONTENT_DESCRIPTION = guid7;
        GUID guid8 = new GUID(new int[]{161, 220, 171, 140, 71, 169, 207, 17, EUCJPContextAnalysis.SINGLE_SHIFT_2, 228, 0, 192, 12, 32, 83, 101}, "File header");
        GUID_FILE = guid8;
        GUID guid9 = new GUID(new int[]{48, 38, 178, 117, EUCJPContextAnalysis.SINGLE_SHIFT_2, 102, 207, 17, 166, JfifUtil.MARKER_EOI, 0, 170, 0, 98, 206, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR}, "Asf header");
        GUID_HEADER = guid9;
        GUID guid10 = new GUID(new int[]{181, 3, 191, 95, 46, 169, 207, 17, EUCJPContextAnalysis.SINGLE_SHIFT_2, 227, 0, 192, 12, 32, 83, 101}, "Header Extension");
        GUID_HEADER_EXTENSION = guid10;
        GUID guid11 = new GUID(new int[]{169, 70, 67, 124, 224, 239, MPEGFrameHeader.SYNC_BIT_ANDSAMPING_BYTE3, 75, 178, 41, 57, 62, 222, 65, 92, 133}, "Language List");
        GUID_LANGUAGE_LIST = guid11;
        GUID guid12 = new GUID(new int[]{HebrewProber.FINAL_KAF, c.m, 248, 197, 175, 91, 119, 72, 132, c.c, 170, 140, 68, 250, 76, c.l}, Utils.LOG);
        GUID_METADATA = guid12;
        GUID guid13 = new GUID(new int[]{148, 28, 35, 68, 152, 148, 209, 73, 161, 65, 29, 19, 78, 69, UMErrorCode.E_UM_BE_DEFLATE_FAILED, 84}, "Metadata Library");
        GUID_METADATA_LIBRARY = guid13;
        GUID_PATTERN = Pattern.compile("[a-f0-9]{8}\\-[a-f0-9]{4}\\-[a-f0-9]{4}\\-[a-f0-9]{4}\\-[a-f0-9]{12}", 2);
        GUID guid14 = new GUID(new int[]{145, 7, 220, 183, 183, 169, 207, 17, EUCJPContextAnalysis.SINGLE_SHIFT_2, 230, 0, 192, 12, 32, 83, 101}, "Stream");
        GUID_STREAM = guid14;
        GUID guid15 = new GUID(new int[]{206, 117, 248, 123, 141, 70, 209, 17, 141, 130, 0, 96, 151, c.k, 162, 178}, "Stream bitrate properties");
        GUID_STREAM_BITRATE_PROPERTIES = guid15;
        GUID guid16 = new GUID(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, "Unspecified");
        GUID_UNSPECIFIED = guid16;
        GUID guid17 = new GUID(new int[]{192, 239, 25, 188, 77, 91, 207, 17, 168, 253, 0, 128, 95, 92, 68, 43}, "Video stream");
        GUID_VIDEOSTREAM = guid17;
        GUID guid18 = new GUID(new int[]{48, 26, 251, 30, 98, 11, JfifUtil.MARKER_RST0, 17, 163, 155, 0, 160, c.k, 3, 72, HebrewProber.NORMAL_TSADI}, "Script Command Object");
        SCRIPT_COMMAND_OBJECT = guid18;
        GUID[] guidArr = {guid, guid5, guid2, guid6, guid8, guid9, guid14, guid7, guid17, guid10, guid15, guid18, guid4, guid3, guid16, guid13, guid12, guid11};
        KNOWN_GUIDS = guidArr;
        GUID_TO_CONFIGURED = new HashMap(guidArr.length);
        for (GUID guid19 : guidArr) {
            GUID_TO_CONFIGURED.put(guid19, guid19);
        }
    }

    public static boolean assertGUID(int[] iArr) {
        return iArr != null && iArr.length == 16;
    }

    public static GUID getConfigured(GUID guid) {
        return GUID_TO_CONFIGURED.get(guid);
    }

    public static String getGuidDescription(GUID guid) {
        if (guid == null) {
            throw new IllegalArgumentException("Argument must not be null.");
        }
        if (getConfigured(guid) != null) {
            return getConfigured(guid).getDescription();
        }
        return null;
    }

    public static GUID parseGUID(String str) throws GUIDFormatException {
        if (str == null) {
            throw new GUIDFormatException("null");
        }
        if (!GUID_PATTERN.matcher(str).matches()) {
            throw new GUIDFormatException("Invalid guidData format.");
        }
        int[] iArr = new int[16];
        int[] iArr2 = {3, 2, 1, 0, 5, 4, 7, 6, 8, 9, 10, 11, 12, 13, 14, 15};
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            if (str.charAt(i) != '-') {
                iArr[iArr2[i2]] = Integer.parseInt(str.substring(i, i + 2), 16);
                i++;
                i2++;
            }
            i++;
        }
        return new GUID(iArr);
    }

    public GUID(byte[] bArr) {
        this.description = "";
        this.guidData = null;
        int[] iArr = new int[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            iArr[i] = bArr[i] & 255;
        }
        setGUID(iArr);
    }

    public GUID(int[] iArr) {
        this.description = "";
        this.guidData = null;
        setGUID(iArr);
    }

    public GUID(int[] iArr, String str) {
        this(iArr);
        if (str == null) {
            throw new IllegalArgumentException("Argument must not be null.");
        }
        this.description = str;
    }

    public GUID(String str, String str2) {
        this(parseGUID(str).getGUID());
        if (str2 == null) {
            throw new IllegalArgumentException("Argument must not be null.");
        }
        this.description = str2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GUID) {
            return Arrays.equals(getGUID(), ((GUID) obj).getGUID());
        }
        return false;
    }

    public byte[] getBytes() {
        int length = this.guidData.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (this.guidData[i] & 255);
        }
        return bArr;
    }

    public String getDescription() {
        return this.description;
    }

    public int[] getGUID() {
        int[] iArr = this.guidData;
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        return iArr2;
    }

    private String[] getHex(byte[] bArr) {
        String[] strArr = new String[bArr.length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            sb.delete(0, sb.length());
            sb.append(Integer.toHexString(bArr[i] & 255));
            if (sb.length() == 1) {
                sb.insert(0, SessionDescription.SUPPORTED_SDP_VERSION);
            }
            strArr[i] = sb.toString();
        }
        return strArr;
    }

    public int hashCode() {
        if (this.hash == -1) {
            int i = 0;
            for (int i2 : getGUID()) {
                i = (i * 31) + i2;
            }
            this.hash = i;
        }
        return this.hash;
    }

    public boolean isValid() {
        return assertGUID(getGUID());
    }

    public String prettyPrint() {
        StringBuilder sb = new StringBuilder();
        String description = getDescription();
        if (org.jaudiotagger.audio.asf.util.Utils.isBlank(description)) {
            description = getGuidDescription(this);
        }
        if (!org.jaudiotagger.audio.asf.util.Utils.isBlank(description)) {
            sb.append("Description: ").append(description).append(org.jaudiotagger.audio.asf.util.Utils.LINE_SEPARATOR).append("   ");
        }
        sb.append(toString());
        return sb.toString();
    }

    private void setGUID(int[] iArr) {
        if (assertGUID(iArr)) {
            int[] iArr2 = new int[16];
            this.guidData = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, 16);
            return;
        }
        throw new IllegalArgumentException("The given guidData doesn't match the GUID specification.");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String[] hex = getHex(getBytes());
        sb.append(hex[3]);
        sb.append(hex[2]);
        sb.append(hex[1]);
        sb.append(hex[0]);
        sb.append('-');
        sb.append(hex[5]);
        sb.append(hex[4]);
        sb.append('-');
        sb.append(hex[7]);
        sb.append(hex[6]);
        sb.append('-');
        sb.append(hex[8]);
        sb.append(hex[9]);
        sb.append('-');
        sb.append(hex[10]);
        sb.append(hex[11]);
        sb.append(hex[12]);
        sb.append(hex[13]);
        sb.append(hex[14]);
        sb.append(hex[15]);
        return sb.toString();
    }
}
