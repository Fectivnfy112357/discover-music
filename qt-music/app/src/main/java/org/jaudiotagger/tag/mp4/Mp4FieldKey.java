package org.jaudiotagger.tag.mp4;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;
import org.jaudiotagger.tag.mp4.field.Mp4FieldType;
import org.jaudiotagger.tag.reference.Tagger;

/* loaded from: classes3.dex */
public enum Mp4FieldKey {
    ACOUSTID_FINGERPRINT("com.apple.iTunes", FrameBodyTXXX.ACOUSTID_FINGERPRINT, Mp4FieldType.TEXT, Tagger.PICARD),
    ACOUSTID_FINGERPRINT_OLD("com.apple.iTunes", "AcoustId Fingerprint", Mp4FieldType.TEXT, Tagger.PICARD),
    ACOUSTID_ID("com.apple.iTunes", FrameBodyTXXX.ACOUSTID_ID, Mp4FieldType.TEXT, Tagger.PICARD),
    AK_ID("akID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.INTEGER, 1),
    ALBUM("©alb", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ALBUM_ARTIST("aART", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ALBUM_ARTIST_SORT("soaa", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ALBUM_SORT("soal", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ALBUM_YEAR("com.apple.iTunes", FrameBodyTXXX.ALBUM_YEAR, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    AP_ID("apID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.TEXT),
    ARRANGER("com.apple.iTunes", FrameBodyTXXX.ARRANGER, Mp4FieldType.TEXT, Tagger.PICARD),
    ARRANGER_SORT("com.apple.iTunes", FrameBodyTXXX.ARRANGER_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ARTIST("©ART", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ARTISTS("com.apple.iTunes", FrameBodyTXXX.ARTISTS, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ARTISTS_SORT("com.apple.iTunes", FrameBodyTXXX.ARTISTS_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ALBUM_ARTISTS("com.apple.iTunes", FrameBodyTXXX.ALBUM_ARTISTS, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ALBUM_ARTISTS_SORT("com.apple.iTunes", FrameBodyTXXX.ALBUM_ARTISTS_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ARTIST_SORT("soar", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ARTWORK("covr", Mp4TagFieldSubType.ARTWORK, Mp4FieldType.COVERART_JPEG),
    ASIN("com.apple.iTunes", FrameBodyTXXX.AMAZON_ASIN, Mp4FieldType.TEXT, Tagger.PICARD),
    AT_ID("atID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.INTEGER, 4),
    AUDIO_ENGINEER("com.apple.iTunes", FrameBodyTXXX.AUDIO_ENGINEER, Mp4FieldType.TEXT, Tagger.SONGKONG),
    AUDIO_ENGINEER_SORT("com.apple.iTunes", FrameBodyTXXX.AUDIO_ENGINEER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    BALANCE_ENGINEER("com.apple.iTunes", FrameBodyTXXX.BALANCE_ENGINEER, Mp4FieldType.TEXT, Tagger.SONGKONG),
    BALANCE_ENGINEER_SORT("com.apple.iTunes", FrameBodyTXXX.BALANCE_ENGINEER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    BARCODE("com.apple.iTunes", FrameBodyTXXX.BARCODE, Mp4FieldType.TEXT, Tagger.PICARD),
    BPM("tmpo", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 2),
    CATALOGNO("com.apple.iTunes", FrameBodyTXXX.CATALOG_NO, Mp4FieldType.TEXT, Tagger.PICARD),
    CATEGORY("catg", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    CDDB_1("com.apple.iTunes", "iTunes_CDDB_1", Mp4FieldType.TEXT),
    CDDB_IDS("com.apple.iTunes", "iTunes_CDDB_IDs", Mp4FieldType.TEXT),
    CDDB_TRACKNUMBER("com.apple.iTunes", "iTunes_CDDB_TrackNumber", Mp4FieldType.TEXT),
    CN_ID("cnID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.INTEGER, 4),
    CHOIR("com.apple.iTunes", "CHOR", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    CHOIR_SORT("com.apple.iTunes", FrameBodyTXXX.CHOIR_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    CLASSICAL_CATALOG("com.apple.iTunes", FrameBodyTXXX.CLASSICAL_CATALOG, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    CLASSICAL_NICKNAME("com.apple.iTunes", FrameBodyTXXX.CLASSICAL_NICKNAME, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    COMMENT("©cmt", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    COMPILATION("cpil", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    COMPOSER("©wrt", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    COMPOSER_SORT("soco", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    CONDUCTOR("com.apple.iTunes", "CONDUCTOR", Mp4FieldType.TEXT, Tagger.PICARD),
    CONDUCTOR_MM3BETA("cond", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    CONDUCTOR_SORT("com.apple.iTunes", FrameBodyTXXX.CONDUCTOR_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    CONTENT_TYPE("stik", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    COPYRIGHT("cprt", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    COUNTRY("com.apple.iTunes", FrameBodyTXXX.COUNTRY, Mp4FieldType.TEXT, Tagger.PICARD),
    CREDITS("com.apple.iTunes", FrameBodyTXXX.CREDITS, Mp4FieldType.TEXT, Tagger.SONGKONG),
    CUSTOM_1("cus1", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    CUSTOM_2("cus2", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    CUSTOM_3("cus3", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    CUSTOM_4("cus4", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    CUSTOM_5("cus5", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    DAY("©day", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    DESCRIPTION("desc", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    DISCNUMBER("disk", Mp4TagFieldSubType.DISC_NO, Mp4FieldType.IMPLICIT),
    DISC_SUBTITLE("com.apple.iTunes", "DISCSUBTITLE", Mp4FieldType.TEXT, Tagger.PICARD),
    DJMIXER("com.apple.iTunes", FrameBodyTXXX.DJMIXER, Mp4FieldType.TEXT, Tagger.PICARD),
    DJMIXER_SORT("com.apple.iTunes", FrameBodyTXXX.DJMIXER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    ENCODER("©too", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    ENGINEER("com.apple.iTunes", FrameBodyTXXX.ENGINEER, Mp4FieldType.TEXT, Tagger.PICARD),
    ENGINEER_SORT("com.apple.iTunes", FrameBodyTXXX.ENGINEER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    ENSEMBLE("com.apple.iTunes", "Ensemble", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ENSEMBLE_SORT("com.apple.iTunes", "Ensemble Sort", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    EPISODE_GLOBAL_ID("egid", Mp4TagFieldSubType.NUMBER, Mp4FieldType.IMPLICIT),
    FBPM("com.apple.iTunes", "fBPM", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    GENRE("gnre", Mp4TagFieldSubType.GENRE, Mp4FieldType.IMPLICIT),
    GENRE_CUSTOM("©gen", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    GE_ID("geID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.INTEGER, 4),
    GROUP("com.apple.iTunes", FrameBodyTXXX.GROUP, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    GROUPING("©grp", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    INSTRUMENT("com.apple.iTunes", FrameBodyTXXX.INSTRUMENT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    INVOLVED_PEOPLE("peop", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    INVOLVEDPEOPLE("com.apple.iTunes", "involvedpeople", Mp4FieldType.TEXT, Tagger.ROON),
    IPI("com.apple.iTunes", FrameBodyTXXX.IPI, Mp4FieldType.TEXT, Tagger.PICARD),
    ISRC("com.apple.iTunes", "ISRC", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ISWC("com.apple.iTunes", FrameBodyTXXX.ISWC, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ISRC_MMBETA("isrc", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    IS_CLASSICAL("com.apple.iTunes", FrameBodyTXXX.IS_CLASSICAL, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    IS_GREATEST_HITS("com.apple.iTunes", FrameBodyTXXX.IS_GREATEST_HITS, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    IS_HD("com.apple.iTunes", FrameBodyTXXX.IS_HD, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    IS_LIVE("com.apple.iTunes", FrameBodyTXXX.LIVE, Mp4FieldType.TEXT, Tagger.ROON),
    IS_SOUNDTRACK("com.apple.iTunes", FrameBodyTXXX.IS_SOUNDTRACK, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ITUNES_NORM("com.apple.iTunes", FrameBodyCOMM.ITUNES_NORMALIZATION, Mp4FieldType.TEXT),
    ITUNES_SMPB("com.apple.iTunes", "iTunSMPB", Mp4FieldType.TEXT),
    JAIKOZ_ID("com.apple.iTunes", FrameBodyTXXX.JAIKOZ_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    KEY("com.apple.iTunes", "initialkey", Mp4FieldType.TEXT),
    KEYS("keys", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    KEYWORD("keyw", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    KEY_OLD("com.apple.iTunes", "KEY", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    LABEL("com.apple.iTunes", "LABEL", Mp4FieldType.TEXT, Tagger.PICARD),
    LANGUAGE("com.apple.iTunes", "LANGUAGE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    LYRICIST("com.apple.iTunes", "LYRICIST", Mp4FieldType.TEXT, Tagger.PICARD),
    LYRICIST_SORT("com.apple.iTunes", FrameBodyTXXX.LYRICIST_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    LYRICIST_MM3BETA("lyrc", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    LYRICS("©lyr", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    MASTERING("com.apple.iTunes", FrameBodyTXXX.MASTERING, Mp4FieldType.TEXT, Tagger.SONGKONG),
    MASTERING_SORT("com.apple.iTunes", FrameBodyTXXX.MASTERING_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    MEDIA("com.apple.iTunes", "MEDIA", Mp4FieldType.TEXT, Tagger.PICARD),
    MIXER("com.apple.iTunes", FrameBodyTXXX.MIXER, Mp4FieldType.TEXT, Tagger.PICARD),
    MIXER_SORT("com.apple.iTunes", FrameBodyTXXX.MIXER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    MM_CUSTOM_1("com.apple.iTunes", "CUSTOM1", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_CUSTOM_2("com.apple.iTunes", "CUSTOM2", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_CUSTOM_3("com.apple.iTunes", "CUSTOM3", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_CUSTOM_4("com.apple.iTunes", "CUSTOM4", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_CUSTOM_5("com.apple.iTunes", "CUSTOM5", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_INVOLVED_PEOPLE("com.apple.iTunes", "INVOLVED PEOPLE", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_OCCASION("com.apple.iTunes", "OCCASION", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_ORIGINAL_ALBUM_TITLE("com.apple.iTunes", "ORIGINAL ALBUM", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_ORIGINAL_ARTIST("com.apple.iTunes", "ORIGINAL ARTIST", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_ORIGINAL_LYRICIST("com.apple.iTunes", "ORIGINAL LYRICIST", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_ORIGINAL_YEAR("com.apple.iTunes", "ORIGINAL YEAR", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_PUBLISHER("com.apple.iTunes", "ORGANIZATION", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_QUALITY("com.apple.iTunes", "QUALITY", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MM_TEMPO("com.apple.iTunes", "TEMPO", Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MOOD("com.apple.iTunes", FrameBodyTXXX.MOOD, Mp4FieldType.TEXT, Tagger.PICARD),
    MOOD_ACOUSTIC("com.apple.iTunes", FrameBodyTXXX.MOOD_ACOUSTIC, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_AGGRESSIVE("com.apple.iTunes", FrameBodyTXXX.MOOD_AGGRESSIVE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_AROUSAL("com.apple.iTunes", FrameBodyTXXX.MOOD_AROUSAL, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_DANCEABILITY("com.apple.iTunes", FrameBodyTXXX.MOOD_DANCEABILITY, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_ELECTRONIC("com.apple.iTunes", FrameBodyTXXX.MOOD_ELECTRONIC, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_HAPPY("com.apple.iTunes", FrameBodyTXXX.MOOD_HAPPY, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_INSTRUMENTAL("com.apple.iTunes", FrameBodyTXXX.MOOD_INSTRUMENTAL, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_MM3BETA("mood", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    MOOD_PARTY("com.apple.iTunes", FrameBodyTXXX.MOOD_PARTY, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_RELAXED("com.apple.iTunes", FrameBodyTXXX.MOOD_RELAXED, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_SAD("com.apple.iTunes", FrameBodyTXXX.MOOD_SAD, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOOD_VALENCE("com.apple.iTunes", FrameBodyTXXX.MOOD_VALENCE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MOVEMENT("©mvn", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    MOVEMENT_NO("©mvi", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    MOVEMENT_TOTAL("©mvc", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    MUSICBRAINZ_ALBUMARTISTID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ALBUM_ARTISTID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_ALBUMID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ALBUMID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_ALBUM_STATUS("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ALBUM_STATUS, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_ALBUM_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ALBUM_TYPE, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_ARTISTID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ARTISTID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_DISCID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_DISCID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_ORIGINALALBUMID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ORIGINAL_ALBUMID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_RELEASE_GROUPID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_RELEASE_GROUPID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_RELEASE_TRACKID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_RELEASE_TRACKID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_TRACKID("com.apple.iTunes", "MusicBrainz Track Id", Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_WORK("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORKID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORKID, Mp4FieldType.TEXT, Tagger.PICARD),
    MUSICBRAINZ_RECORDING_WORK("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_RECORDING_WORK, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_RECORDING_WORK_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_RECORDING_WORK_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL1("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL1_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL1_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL2("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL2_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL2_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL3("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL3_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL3_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL4("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL4_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL4_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL5("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL5_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL5_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL6("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL6_ID("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6_ID, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICBRAINZ_WORK_PART_LEVEL6_TYPE("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    MUSICIP_PUID("com.apple.iTunes", FrameBodyTXXX.MUSICIP_ID, Mp4FieldType.TEXT, Tagger.PICARD),
    OCCASION("occa", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    OPUS("com.apple.iTunes", "OPUS", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ORCHESTRA("com.apple.iTunes", FrameBodyTXXX.ORCHESTRA, Mp4FieldType.TEXT, Tagger.PICARD),
    ORCHESTRA_SORT("com.apple.iTunes", FrameBodyTXXX.ORCHESTRA_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    ORIGINAL_ALBUM_TITLE("otit", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    ORIGINALRELEASEDATE("com.apple.iTunes", FrameBodyTXXX.ORIGINALRELEASEDATE, Mp4FieldType.TEXT, Tagger.ROON),
    ORIGINAL_ARTIST("oart", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    ORIGINAL_LYRICIST("olyr", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    OVERALL_WORK("com.apple.iTunes", FrameBodyTXXX.OVERALL_WORK, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    PART("com.apple.iTunes", FrameBodyTXXX.PART, Mp4FieldType.TEXT, Tagger.PICARD),
    PART_NUMBER("com.apple.iTunes", FrameBodyTXXX.PART_NUMBER, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    PART_OF_GAPLESS_ALBUM("pgap", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER),
    PART_TYPE("com.apple.iTunes", FrameBodyTXXX.PART_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    PERFORMER("com.apple.iTunes", "Performer", Mp4FieldType.TEXT, Tagger.PICARD),
    PERFORMER_NAME("com.apple.iTunes", FrameBodyTXXX.PERFORMER_NAME, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    PERFORMER_NAME_SORT("com.apple.iTunes", FrameBodyTXXX.PERFORMER_NAME_SORT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    PERIOD("com.apple.iTunes", FrameBodyTXXX.PERIOD, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    PL_ID("plID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.INTEGER, 8),
    PODCAST_KEYWORD("keyw", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    PODCAST_URL("purl", Mp4TagFieldSubType.NUMBER, Mp4FieldType.IMPLICIT),
    PRODUCER("com.apple.iTunes", FrameBodyTXXX.PRODUCER, Mp4FieldType.TEXT, Tagger.PICARD),
    PRODUCER_SORT("com.apple.iTunes", FrameBodyTXXX.PRODUCER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    PURCHASE_DATE("purd", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    QUALITY("qual", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    RANKING("com.apple.iTunes", FrameBodyTXXX.RANKING, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    RATING("rtng", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    RECORDING_ENGINEER("com.apple.iTunes", FrameBodyTXXX.RECORDING_ENGINEER, Mp4FieldType.TEXT, Tagger.SONGKONG),
    RECORDING_ENGINEER_SORT("com.apple.iTunes", FrameBodyTXXX.RECORDING_ENGINEER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    RECORDINGDATE("com.apple.iTunes", FrameBodyTXXX.RECORDINGDATE, Mp4FieldType.TEXT, Tagger.ROON),
    RECORDINGSTARTDATE("com.apple.iTunes", FrameBodyTXXX.RECORDINGSTARTDATE, Mp4FieldType.TEXT, Tagger.ROON),
    RECORDINGENDDATE("com.apple.iTunes", FrameBodyTXXX.RECORDINGENDDATE, Mp4FieldType.TEXT, Tagger.ROON),
    RECORDINGLOCATION("com.apple.iTunes", FrameBodyTXXX.RECORDINGLOCATION, Mp4FieldType.TEXT, Tagger.ROON),
    RELEASECOUNTRY("com.apple.iTunes", FrameBodyTXXX.MUSICBRAINZ_ALBUM_COUNTRY, Mp4FieldType.TEXT, Tagger.PICARD),
    REMIXER("com.apple.iTunes", FrameBodyTXXX.REMIXER, Mp4FieldType.TEXT, Tagger.PICARD),
    ROONALBUMTAG("com.apple.iTunes", FrameBodyTXXX.ROONALBUMTAG, Mp4FieldType.TEXT, Tagger.ROON),
    ROONTRACKTAG("com.apple.iTunes", FrameBodyTXXX.ROONTRACKTAG, Mp4FieldType.TEXT, Tagger.ROON),
    SCORE("rate", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    SCRIPT("com.apple.iTunes", FrameBodyTXXX.SCRIPT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    SF_ID("sfID", Mp4TagFieldSubType.UNKNOWN, Mp4FieldType.INTEGER, 4),
    SECTION("com.apple.iTunes", FrameBodyTXXX.SECTION, Mp4FieldType.TEXT, Tagger.ROON),
    SHOW("tvsh", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    SHOW_SORT("sosn", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    SINGLE_DISC_TRACK_NO("com.apple.iTunes", FrameBodyTXXX.SINGLE_DISC_TRACK_NO, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    SONGKONG_ID("com.apple.iTunes", FrameBodyTXXX.SONGKONG_ID, Mp4FieldType.TEXT, Tagger.SONGKONG),
    SOUND_ENGINEER("com.apple.iTunes", FrameBodyTXXX.SOUND_ENGINEER, Mp4FieldType.TEXT, Tagger.SONGKONG),
    SOUND_ENGINEER_SORT("com.apple.iTunes", FrameBodyTXXX.SOUND_ENGINEER_SORT, Mp4FieldType.TEXT, Tagger.SONGKONG),
    SUBTITLE("com.apple.iTunes", "SUBTITLE", Mp4FieldType.TEXT, Tagger.PICARD),
    TAGS("com.apple.iTunes", FrameBodyTXXX.TAGS, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    TEMPO("empo", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT, Tagger.MEDIA_MONKEY),
    TIMBRE("com.apple.iTunes", FrameBodyTXXX.TIMBRE, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    TITLE("©nam", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    TITLE_MOVEMENT("com.apple.iTunes", FrameBodyTXXX.TITLE_MOVEMENT, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    TITLE_SORT("sonm", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    TONALITY("com.apple.iTunes", FrameBodyTXXX.TONALITY, Mp4FieldType.TEXT, Tagger.JAIKOZ),
    TOOL(SessionDescription.ATTR_TOOL, Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 4),
    TRACK("trkn", Mp4TagFieldSubType.TRACK_NO, Mp4FieldType.IMPLICIT),
    TV_EPISODE("tves", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    TV_EPISODE_NUMBER("tven", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    TV_NETWORK("tvnn", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    TV_SEASON("tvsn", Mp4TagFieldSubType.BYTE, Mp4FieldType.INTEGER, 1),
    URL_BANDCAMP_ARTIST_SITE("com.apple.iTunes", "URL_BANDCAMP_ARTIST_SITE", Mp4FieldType.TEXT, Tagger.SONGKONG),
    URL_BANDCAMP_RELEASE_SITE("com.apple.iTunes", "URL_BANDCAMP_RELEASE_SITE", Mp4FieldType.TEXT, Tagger.SONGKONG),
    URL_DISCOGS_ARTIST_SITE("com.apple.iTunes", "URL_DISCOGS_ARTIST_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    URL_DISCOGS_RELEASE_SITE("com.apple.iTunes", "URL_DISCOGS_RELEASE_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    URL_LYRICS_SITE("com.apple.iTunes", "URL_LYRICS_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    URL_OFFICIAL_ARTIST_SITE("com.apple.iTunes", "URL_OFFICIAL_ARTIST_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    URL_OFFICIAL_RELEASE_SITE("com.apple.iTunes", "URL_OFFICIAL_RELEASE_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    URL_WIKIPEDIA_ARTIST_SITE("com.apple.iTunes", "URL_WIKIPEDIA_ARTIST_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    URL_WIKIPEDIA_RELEASE_SITE("com.apple.iTunes", "URL_WIKIPEDIA_RELEASE_SITE", Mp4FieldType.TEXT, Tagger.JAIKOZ),
    VERSION("com.apple.iTunes", FrameBodyTXXX.VERSION, Mp4FieldType.TEXT, Tagger.ROON),
    WINAMP_PUBLISHER("com.nullsoft.winamp", "publisher", Mp4FieldType.TEXT, Tagger.WINAMP),
    WORK("©wrk", Mp4TagFieldSubType.TEXT, Mp4FieldType.TEXT),
    WORK_TYPE("com.apple.iTunes", FrameBodyTXXX.WORK_TYPE, Mp4FieldType.TEXT, Tagger.JAIKOZ);

    private int fieldLength;
    private String fieldName;
    private Mp4FieldType fieldType;
    private String identifier;
    private String issuer;
    private Mp4TagFieldSubType subclassType;
    private Tagger tagger;

    Mp4FieldKey(String str, Mp4TagFieldSubType mp4TagFieldSubType, Mp4FieldType mp4FieldType) {
        this.fieldName = str;
        this.subclassType = mp4TagFieldSubType;
        this.fieldType = mp4FieldType;
    }

    Mp4FieldKey(String str, Mp4TagFieldSubType mp4TagFieldSubType, Mp4FieldType mp4FieldType, Tagger tagger) {
        this.fieldName = str;
        this.subclassType = mp4TagFieldSubType;
        this.fieldType = mp4FieldType;
        this.tagger = tagger;
    }

    Mp4FieldKey(String str, Mp4TagFieldSubType mp4TagFieldSubType, Mp4FieldType mp4FieldType, int i) {
        this.fieldName = str;
        this.subclassType = mp4TagFieldSubType;
        this.fieldType = mp4FieldType;
        this.fieldLength = i;
    }

    Mp4FieldKey(String str, String str2, Mp4FieldType mp4FieldType) {
        this.issuer = str;
        this.identifier = str2;
        this.fieldName = "----:" + str + ":" + str2;
        this.subclassType = Mp4TagFieldSubType.REVERSE_DNS;
        this.fieldType = mp4FieldType;
    }

    Mp4FieldKey(String str, String str2, Mp4FieldType mp4FieldType, Tagger tagger) {
        this.issuer = str;
        this.identifier = str2;
        this.fieldName = "----:" + str + ":" + str2;
        this.subclassType = Mp4TagFieldSubType.REVERSE_DNS;
        this.fieldType = mp4FieldType;
        this.tagger = tagger;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public Mp4FieldType getFieldType() {
        return this.fieldType;
    }

    public Mp4TagFieldSubType getSubClassFieldType() {
        return this.subclassType;
    }

    public boolean isReverseDnsType() {
        return this.identifier.startsWith("----");
    }

    public String getIssuer() {
        return this.issuer;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public int getFieldLength() {
        return this.fieldLength;
    }

    public Tagger getTagger() {
        Tagger tagger = this.tagger;
        return tagger != null ? tagger : Tagger.ITUNES;
    }
}
