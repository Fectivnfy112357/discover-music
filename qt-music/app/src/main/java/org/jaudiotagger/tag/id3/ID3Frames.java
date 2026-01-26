package org.jaudiotagger.tag.id3;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import org.jaudiotagger.tag.datatype.AbstractStringStringValuePair;

/* loaded from: classes3.dex */
public abstract class ID3Frames extends AbstractStringStringValuePair {
    public static final Map<String, String> convertv22Tov23 = new LinkedHashMap();
    public static final Map<String, String> convertv23Tov22 = new LinkedHashMap();
    public static final Map<String, String> forcev22Tov23 = new LinkedHashMap();
    public static final Map<String, String> forcev23Tov22 = new LinkedHashMap();
    public static final Map<String, String> convertv23Tov24 = new LinkedHashMap();
    public static final Map<String, String> convertv24Tov23 = new LinkedHashMap();
    public static final Map<String, String> forcev23Tov24 = new LinkedHashMap();
    public static final Map<String, String> forcev24Tov23 = new LinkedHashMap();
    protected TreeSet<String> multipleFrames = new TreeSet<>();
    protected TreeSet<String> discardIfFileAlteredFrames = new TreeSet<>();
    protected TreeSet<String> supportedFrames = new TreeSet<>();
    protected TreeSet<String> extensionFrames = new TreeSet<>();
    protected TreeSet<String> commonFrames = new TreeSet<>();
    protected TreeSet<String> binaryFrames = new TreeSet<>();

    public abstract void setITunes12_6WorkGroupingMode(boolean z);

    public boolean isDiscardIfFileAltered(String str) {
        return this.discardIfFileAlteredFrames.contains(str);
    }

    public boolean isMultipleAllowed(String str) {
        return this.multipleFrames.contains(str);
    }

    public boolean isSupportedFrames(String str) {
        return this.supportedFrames.contains(str);
    }

    public TreeSet<String> getSupportedFrames() {
        return this.supportedFrames;
    }

    public boolean isCommon(String str) {
        return this.commonFrames.contains(str);
    }

    public boolean isBinary(String str) {
        return this.binaryFrames.contains(str);
    }

    public boolean isExtensionFrames(String str) {
        return this.extensionFrames.contains(str);
    }

    static {
        loadID3v22ID3v23Mapping();
        loadID3v23ID3v24Mapping();
    }

    private static void loadID3v23ID3v24Mapping() {
        Map<String, String> map = convertv23Tov24;
        map.put(ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_MUSICBRAINZ, "TSOT");
        map.put(ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_MUSICBRAINZ, "TSOP");
        map.put(ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_MUSICBRAINZ, "TSOA");
        Map<String, String> map2 = forcev23Tov24;
        map2.put(ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT, ID3v24Frames.FRAME_ID_RELATIVE_VOLUME_ADJUSTMENT2);
        map2.put(ID3v23Frames.FRAME_ID_V3_EQUALISATION, ID3v24Frames.FRAME_ID_EQUALISATION2);
        map2.put(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE, ID3v24Frames.FRAME_ID_INVOLVED_PEOPLE);
        map2.put(ID3v23Frames.FRAME_ID_V3_TDAT, ID3v24Frames.FRAME_ID_YEAR);
        map2.put(ID3v23Frames.FRAME_ID_V3_TIME, ID3v24Frames.FRAME_ID_YEAR);
        map2.put(ID3v23Frames.FRAME_ID_V3_TORY, ID3v24Frames.FRAME_ID_ORIGINAL_RELEASE_TIME);
        map2.put(ID3v23Frames.FRAME_ID_V3_TRDA, ID3v24Frames.FRAME_ID_YEAR);
        map2.put(ID3v23Frames.FRAME_ID_V3_TYER, ID3v24Frames.FRAME_ID_YEAR);
        Map<String, String> map3 = forcev24Tov23;
        map3.put(ID3v24Frames.FRAME_ID_RELATIVE_VOLUME_ADJUSTMENT2, ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT);
        map3.put(ID3v24Frames.FRAME_ID_INVOLVED_PEOPLE, ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE);
        map3.put(ID3v24Frames.FRAME_ID_MOOD, "TXXX");
        map3.put(ID3v24Frames.FRAME_ID_ORIGINAL_RELEASE_TIME, ID3v23Frames.FRAME_ID_V3_TORY);
    }

    private static void loadID3v22ID3v23Mapping() {
        Map<String, String> map = convertv22Tov23;
        map.put(ID3v22Frames.FRAME_ID_V2_ACCOMPANIMENT, "TPE2");
        map.put(ID3v22Frames.FRAME_ID_V2_ALBUM, "TALB");
        map.put(ID3v22Frames.FRAME_ID_V2_ARTIST, "TPE1");
        map.put(ID3v22Frames.FRAME_ID_V2_AUDIO_ENCRYPTION, "AENC");
        map.put(ID3v22Frames.FRAME_ID_V2_BPM, "TBPM");
        map.put(ID3v22Frames.FRAME_ID_V2_COMMENT, "COMM");
        map.put(ID3v22Frames.FRAME_ID_V2_COMMENT, "COMM");
        map.put(ID3v22Frames.FRAME_ID_V2_COMPOSER, "TCOM");
        map.put(ID3v22Frames.FRAME_ID_V2_CONDUCTOR, "TPE3");
        map.put(ID3v22Frames.FRAME_ID_V2_CONTENT_GROUP_DESC, "TIT1");
        map.put(ID3v22Frames.FRAME_ID_V2_COPYRIGHTINFO, "TCOP");
        map.put(ID3v22Frames.FRAME_ID_V2_ENCODEDBY, "TENC");
        map.put(ID3v22Frames.FRAME_ID_V2_EQUALISATION, ID3v23Frames.FRAME_ID_V3_EQUALISATION);
        map.put(ID3v22Frames.FRAME_ID_V2_EVENT_TIMING_CODES, "ETCO");
        map.put(ID3v22Frames.FRAME_ID_V2_FILE_TYPE, "TFLT");
        map.put(ID3v22Frames.FRAME_ID_V2_GENERAL_ENCAPS_OBJECT, "GEOB");
        map.put(ID3v22Frames.FRAME_ID_V2_GENRE, "TCON");
        map.put(ID3v22Frames.FRAME_ID_V2_HW_SW_SETTINGS, "TSSE");
        map.put(ID3v22Frames.FRAME_ID_V2_INITIAL_KEY, "TKEY");
        map.put(ID3v22Frames.FRAME_ID_V2_IPLS, ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE);
        map.put(ID3v22Frames.FRAME_ID_V2_ISRC, "TSRC");
        map.put(ID3v22Frames.FRAME_ID_V2_ITUNES_GROUPING, "GRP1");
        map.put(ID3v22Frames.FRAME_ID_V2_LANGUAGE, "TLAN");
        map.put(ID3v22Frames.FRAME_ID_V2_LENGTH, "TLEN");
        map.put(ID3v22Frames.FRAME_ID_V2_LINKED_INFO, "LINK");
        map.put(ID3v22Frames.FRAME_ID_V2_LYRICIST, "TEXT");
        map.put(ID3v22Frames.FRAME_ID_V2_MEDIA_TYPE, "TMED");
        map.put(ID3v22Frames.FRAME_ID_V2_MOVEMENT, "MVNM");
        map.put(ID3v22Frames.FRAME_ID_V2_MOVEMENT_NO, "MVIN");
        map.put(ID3v22Frames.FRAME_ID_V2_MPEG_LOCATION_LOOKUP_TABLE, "MLLT");
        map.put(ID3v22Frames.FRAME_ID_V2_MUSIC_CD_ID, "MCDI");
        map.put(ID3v22Frames.FRAME_ID_V2_ORIGARTIST, "TOPE");
        map.put(ID3v22Frames.FRAME_ID_V2_ORIG_FILENAME, "TOFN");
        map.put(ID3v22Frames.FRAME_ID_V2_ORIG_LYRICIST, "TOLY");
        map.put(ID3v22Frames.FRAME_ID_V2_ORIG_TITLE, "TOAL");
        map.put(ID3v22Frames.FRAME_ID_V2_PLAYLIST_DELAY, "TDLY");
        map.put(ID3v22Frames.FRAME_ID_V2_PLAY_COUNTER, "PCNT");
        map.put(ID3v22Frames.FRAME_ID_V2_PLAY_COUNTER, "PCNT");
        map.put(ID3v22Frames.FRAME_ID_V2_POPULARIMETER, "POPM");
        map.put(ID3v22Frames.FRAME_ID_V2_PUBLISHER, "TPUB");
        map.put(ID3v22Frames.FRAME_ID_V2_RECOMMENDED_BUFFER_SIZE, "RBUF");
        map.put(ID3v22Frames.FRAME_ID_V2_RECOMMENDED_BUFFER_SIZE, "RBUF");
        map.put(ID3v22Frames.FRAME_ID_V2_RELATIVE_VOLUME_ADJUSTMENT, ID3v23Frames.FRAME_ID_V3_RELATIVE_VOLUME_ADJUSTMENT);
        map.put(ID3v22Frames.FRAME_ID_V2_REMIXED, "TPE4");
        map.put(ID3v22Frames.FRAME_ID_V2_REVERB, "RVRB");
        map.put(ID3v22Frames.FRAME_ID_V2_SET, "TPOS");
        map.put(ID3v22Frames.FRAME_ID_V2_SET_SUBTITLE, "TSST");
        map.put(ID3v22Frames.FRAME_ID_V2_SYNC_LYRIC, "SYLT");
        map.put(ID3v22Frames.FRAME_ID_V2_SYNC_TEMPO, "SYTC");
        map.put(ID3v22Frames.FRAME_ID_V2_TDAT, ID3v23Frames.FRAME_ID_V3_TDAT);
        map.put(ID3v22Frames.FRAME_ID_V2_TIME, ID3v23Frames.FRAME_ID_V3_TIME);
        map.put(ID3v22Frames.FRAME_ID_V2_TITLE_REFINEMENT, "TIT3");
        map.put(ID3v22Frames.FRAME_ID_V2_TORY, ID3v23Frames.FRAME_ID_V3_TORY);
        map.put(ID3v22Frames.FRAME_ID_V2_TRACK, "TRCK");
        map.put(ID3v22Frames.FRAME_ID_V2_TRDA, ID3v23Frames.FRAME_ID_V3_TRDA);
        map.put(ID3v22Frames.FRAME_ID_V2_TSIZ, ID3v23Frames.FRAME_ID_V3_TSIZ);
        map.put(ID3v22Frames.FRAME_ID_V2_TYER, ID3v23Frames.FRAME_ID_V3_TYER);
        map.put(ID3v22Frames.FRAME_ID_V2_UNIQUE_FILE_ID, "UFID");
        map.put(ID3v22Frames.FRAME_ID_V2_UNIQUE_FILE_ID, "UFID");
        map.put(ID3v22Frames.FRAME_ID_V2_UNSYNC_LYRICS, "USLT");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_ARTIST_WEB, "WOAR");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_COMMERCIAL, "WCOM");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_COPYRIGHT, "WCOP");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_FILE_WEB, "WOAF");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_OFFICIAL_RADIO, "WORS");
        map.put("WPAY", "WPAY");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_PUBLISHERS, "WPUB");
        map.put(ID3v22Frames.FRAME_ID_V2_URL_SOURCE_WEB, "WOAS");
        map.put(ID3v22Frames.FRAME_ID_V2_USER_DEFINED_INFO, "TXXX");
        map.put(ID3v22Frames.FRAME_ID_V2_USER_DEFINED_URL, "WXXX");
        map.put(ID3v22Frames.FRAME_ID_V2_TITLE, "TIT2");
        map.put(ID3v22Frames.FRAME_ID_V2_IS_COMPILATION, "TCMP");
        map.put(ID3v22Frames.FRAME_ID_V2_TITLE_SORT_ORDER_ITUNES, "TSOT");
        map.put(ID3v22Frames.FRAME_ID_V2_ARTIST_SORT_ORDER_ITUNES, "TSOP");
        map.put(ID3v22Frames.FRAME_ID_V2_ALBUM_SORT_ORDER_ITUNES, "TSOA");
        map.put(ID3v22Frames.FRAME_ID_V2_ALBUM_ARTIST_SORT_ORDER_ITUNES, "TSO2");
        map.put(ID3v22Frames.FRAME_ID_V2_COMPOSER_SORT_ORDER_ITUNES, "TSOC");
        for (String str : map.keySet()) {
            convertv23Tov22.put(convertv22Tov23.get(str), str);
        }
        Map<String, String> map2 = convertv23Tov22;
        map2.put(ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_MUSICBRAINZ, ID3v22Frames.FRAME_ID_V2_TITLE_SORT_ORDER_ITUNES);
        map2.put(ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_MUSICBRAINZ, ID3v22Frames.FRAME_ID_V2_ARTIST_SORT_ORDER_ITUNES);
        map2.put(ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_MUSICBRAINZ, ID3v22Frames.FRAME_ID_V2_ALBUM_SORT_ORDER_ITUNES);
        forcev22Tov23.put("PIC", "APIC");
        forcev23Tov22.put("APIC", "PIC");
    }
}
