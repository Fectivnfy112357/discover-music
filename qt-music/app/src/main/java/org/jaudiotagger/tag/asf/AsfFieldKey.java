package org.jaudiotagger.tag.asf;

import java.util.HashMap;
import java.util.Map;
import org.jaudiotagger.audio.asf.data.ContainerType;
import org.jaudiotagger.audio.asf.data.ContentBranding;
import org.jaudiotagger.audio.asf.data.ContentDescription;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;

/* loaded from: classes3.dex */
public enum AsfFieldKey {
    AUTHOR(ContentDescription.KEY_AUTHOR, false, ContainerType.CONTENT_DESCRIPTION),
    TITLE(ContentDescription.KEY_TITLE, false, ContainerType.CONTENT_DESCRIPTION),
    RATING(ContentDescription.KEY_RATING, false, ContainerType.CONTENT_DESCRIPTION),
    COPYRIGHT(ContentDescription.KEY_COPYRIGHT, false, ContainerType.CONTENT_DESCRIPTION),
    DESCRIPTION(ContentDescription.KEY_DESCRIPTION, false, ContainerType.CONTENT_DESCRIPTION),
    BANNER_IMAGE(ContentBranding.KEY_BANNER_IMAGE, false, ContainerType.CONTENT_BRANDING),
    BANNER_IMAGE_TYPE(ContentBranding.KEY_BANNER_TYPE, false, ContainerType.CONTENT_BRANDING),
    BANNER_IMAGE_URL(ContentBranding.KEY_BANNER_URL, false, ContainerType.CONTENT_BRANDING),
    COPYRIGHT_URL(ContentBranding.KEY_COPYRIGHT_URL, false, ContainerType.CONTENT_BRANDING),
    ACOUSTID_FINGERPRINT("Acoustid/Fingerprint", false),
    ACOUSTID_FINGERPRINT_OLD("AcoustId/Fingerprint", false),
    ACOUSTID_ID("Acoustid/Id", false),
    ALBUM("WM/AlbumTitle", false),
    ALBUM_ARTIST("WM/AlbumArtist", true),
    ALBUM_ARTIST_SORT("WM/AlbumArtistSortOrder", false),
    ALBUM_ARTISTS(FrameBodyTXXX.ALBUM_ARTISTS, true),
    ALBUM_ARTISTS_SORT(FrameBodyTXXX.ALBUM_ARTISTS_SORT, true),
    ALBUM_SORT("WM/AlbumSortOrder", false),
    ALBUM_YEAR(FrameBodyTXXX.ALBUM_YEAR, false),
    AMAZON_ID(FrameBodyTXXX.AMAZON_ASIN, false),
    ARRANGER("WM/Arranger", false),
    ARRANGER_SORT(FrameBodyTXXX.ARRANGER_SORT, true),
    ARTISTS("WM/ARTISTS", true),
    ARTISTS_SORT("WM/ARTISTS_SORT", true),
    ARTIST_SORT("WM/ArtistSortOrder", false),
    AUDIO_ENGINEER(FrameBodyTXXX.AUDIO_ENGINEER, true),
    AUDIO_ENGINEER_SORT(FrameBodyTXXX.AUDIO_ENGINEER_SORT, true),
    BALANCE_ENGINEER(FrameBodyTXXX.BALANCE_ENGINEER, true),
    BALANCE_ENGINEER_SORT(FrameBodyTXXX.BALANCE_ENGINEER_SORT, true),
    BARCODE("WM/Barcode", false),
    BPM("WM/BeatsPerMinute", false),
    CATALOG_NO("WM/CatalogNo", false),
    CATEGORY("WM/Category", true),
    CHOIR(FrameBodyTXXX.CHOIR, true),
    CHOIR_SORT(FrameBodyTXXX.CHOIR_SORT, true),
    CLASSICAL_CATALOG(FrameBodyTXXX.CLASSICAL_CATALOG, true),
    CLASSICAL_NICKNAME(FrameBodyTXXX.CLASSICAL_NICKNAME, true),
    COMPOSER("WM/Composer", true),
    COMPOSER_SORT("WM/ComposerSortOrder", false),
    CONDUCTOR("WM/Conductor", true),
    CONDUCTOR_SORT(FrameBodyTXXX.CONDUCTOR_SORT, true),
    COUNTRY("WM/Country", false),
    COVER_ART("WM/Picture", true),
    COVER_ART_URL("WM/AlbumCoverURL", true),
    CREDITS(FrameBodyTXXX.CREDITS, true),
    CUSTOM1("CUSTOM1", true),
    CUSTOM2("CUSTOM2", true),
    CUSTOM3("CUSTOM3", true),
    CUSTOM4("CUSTOM4", true),
    CUSTOM5("CUSTOM5", true),
    DIRECTOR("WM/Director", true),
    DISC_NO("WM/PartOfSet", false),
    DISC_SUBTITLE("WM/SetSubTitle", false),
    DISC_TOTAL("WM/DiscTotal", false),
    DJMIXER("WM/DJMixer", false),
    DJMIXER_SORT(FrameBodyTXXX.DJMIXER_SORT, false),
    ENCODED_BY("WM/EncodedBy", false),
    ENCODER("WM/ToolName", false),
    ENGINEER("WM/Engineer", false),
    ENGINEER_SORT(FrameBodyTXXX.ENGINEER_SORT, false),
    ENSEMBLE(FrameBodyTXXX.ENSEMBLE, true),
    ENSEMBLE_SORT(FrameBodyTXXX.ENSEMBLE_SORT, true),
    FBPM(FrameBodyTXXX.FBPM, true),
    GENRE("WM/Genre", true),
    GENRE_ID("WM/GenreID", true),
    GROUP(FrameBodyTXXX.GROUP, false),
    GROUPING("WM/ContentGroupDescription", false),
    INITIAL_KEY("WM/InitialKey", false),
    INSTRUMENT(FrameBodyTXXX.INSTRUMENT, true),
    INVOLVED_PERSON("WM/InvolvedPerson", true),
    IPI(FrameBodyTXXX.IPI, false),
    ISRC("WM/ISRC", false),
    ISWC(FrameBodyTXXX.ISWC, false),
    ISVBR("IsVBR", true),
    IS_CLASSICAL(FrameBodyTXXX.IS_CLASSICAL, false),
    IS_COMPILATION("WM/IsCompilation", false),
    IS_GREATEST_HITS(FrameBodyTXXX.IS_GREATEST_HITS, false),
    IS_HD(FrameBodyTXXX.IS_HD, false),
    IS_LIVE(FrameBodyTXXX.LIVE, false),
    IS_SOUNDTRACK(FrameBodyTXXX.IS_SOUNDTRACK, false),
    JAIKOZ_ID(FrameBodyTXXX.JAIKOZ_ID, false),
    LANGUAGE("WM/Language", true),
    LYRICIST("WM/Writer", true),
    LYRICIST_SORT(FrameBodyTXXX.LYRICIST_SORT, true),
    LYRICS("WM/Lyrics", false),
    LYRICS_SYNCHRONISED("WM/Lyrics_Synchronised", true),
    MASTERING(FrameBodyTXXX.MASTERING, true),
    MASTERING_SORT(FrameBodyTXXX.MASTERING_SORT, true),
    MEDIA("WM/Media", false),
    MIXER("WM/Mixer", false),
    MIXER_SORT(FrameBodyTXXX.MIXER_SORT, false),
    MM_RATING("SDB/Rating", true),
    MOOD("WM/Mood", true),
    MOOD_ACOUSTIC(FrameBodyTXXX.MOOD_ACOUSTIC, false),
    MOOD_AGGRESSIVE(FrameBodyTXXX.MOOD_AGGRESSIVE, false),
    MOOD_AROUSAL(FrameBodyTXXX.MOOD_AROUSAL, false),
    MOOD_DANCEABILITY(FrameBodyTXXX.MOOD_DANCEABILITY, false),
    MOOD_ELECTRONIC(FrameBodyTXXX.MOOD_ELECTRONIC, false),
    MOOD_HAPPY(FrameBodyTXXX.MOOD_HAPPY, false),
    MOOD_INSTRUMENTAL(FrameBodyTXXX.MOOD_INSTRUMENTAL, false),
    MOOD_PARTY(FrameBodyTXXX.MOOD_PARTY, false),
    MOOD_RELAXED(FrameBodyTXXX.MOOD_RELAXED, false),
    MOOD_SAD(FrameBodyTXXX.MOOD_SAD, false),
    MOOD_VALENCE(FrameBodyTXXX.MOOD_VALENCE, false),
    MOVEMENT("MOVEMENT", false),
    MOVEMENT_NO("MOVEMENT_NO", false),
    MOVEMENT_TOTAL("MOVEMENT_TOTAL", false),
    MUSICBRAINZ_ARTISTID("MusicBrainz/Artist Id", false),
    MUSICBRAINZ_DISC_ID("MusicBrainz/Disc Id", false),
    MUSICBRAINZ_ORIGINAL_RELEASEID("MusicBrainz/Original Album Id", false),
    MUSICBRAINZ_RELEASEARTISTID("MusicBrainz/Album Artist Id", false),
    MUSICBRAINZ_RELEASEGROUPID("MusicBrainz/Release Group Id", false),
    MUSICBRAINZ_RELEASEID("MusicBrainz/Album Id", false),
    MUSICBRAINZ_RELEASETRACKID("MusicBrainz/Release Track Id", false),
    MUSICBRAINZ_RELEASE_COUNTRY("MusicBrainz/Album Release Country", false),
    MUSICBRAINZ_RELEASE_STATUS("MusicBrainz/Album Status", false),
    MUSICBRAINZ_RELEASE_TYPE("MusicBrainz/Album Type", false),
    MUSICBRAINZ_TRACK_ID("MusicBrainz/Track Id", false),
    MUSICBRAINZ_WORKID("MusicBrainz/Work Id", false),
    MUSICBRAINZ_RECORDING_WORK(FrameBodyTXXX.MUSICBRAINZ_RECORDING_WORK, true),
    MUSICBRAINZ_RECORDING_WORK_ID(FrameBodyTXXX.MUSICBRAINZ_RECORDING_WORK_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL1(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1, true),
    MUSICBRAINZ_WORK_PART_LEVEL1_ID(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL1_TYPE(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE, true),
    MUSICBRAINZ_WORK_PART_LEVEL2(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2, true),
    MUSICBRAINZ_WORK_PART_LEVEL2_ID(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL2_TYPE(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE, true),
    MUSICBRAINZ_WORK_PART_LEVEL3(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3, true),
    MUSICBRAINZ_WORK_PART_LEVEL3_ID(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL3_TYPE(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE, true),
    MUSICBRAINZ_WORK_PART_LEVEL4(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4, true),
    MUSICBRAINZ_WORK_PART_LEVEL4_ID(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL4_TYPE(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE, true),
    MUSICBRAINZ_WORK_PART_LEVEL5(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5, true),
    MUSICBRAINZ_WORK_PART_LEVEL5_ID(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL5_TYPE(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE, true),
    MUSICBRAINZ_WORK_PART_LEVEL6_ID(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6_ID, true),
    MUSICBRAINZ_WORK_PART_LEVEL6(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6, true),
    MUSICBRAINZ_WORK_PART_LEVEL6_TYPE(FrameBodyTXXX.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE, true),
    MUSICIP_ID("MusicIP/PUID", false),
    OCCASION("Occasion", true),
    OPUS("OPUS", true),
    ORCHESTRA(FrameBodyTXXX.ORCHESTRA, true),
    ORCHESTRA_SORT(FrameBodyTXXX.ORCHESTRA_SORT, true),
    ORIGINAL_ALBUM("WM/OriginalAlbumTitle", true),
    ORIGINALRELEASEDATE(FrameBodyTXXX.ORIGINALRELEASEDATE, false),
    ORIGINAL_ARTIST("WM/OriginalArtist", true),
    ORIGINAL_LYRICIST("WM/OriginalLyricist", true),
    ORIGINAL_YEAR("WM/OriginalReleaseYear", true),
    OVERALL_WORK(FrameBodyTXXX.OVERALL_WORK, false),
    PART(FrameBodyTXXX.PART, true),
    PART_NUMBER(FrameBodyTXXX.PART_NUMBER, true),
    PART_TYPE(FrameBodyTXXX.PART_TYPE, true),
    PERFORMER(FrameBodyTXXX.PERFORMER, true),
    PERFORMER_NAME(FrameBodyTXXX.PERFORMER_NAME, true),
    PERFORMER_NAME_SORT(FrameBodyTXXX.PERFORMER_NAME_SORT, true),
    PERIOD(FrameBodyTXXX.PERIOD, true),
    PRODUCER("WM/Producer", false),
    PRODUCER_SORT(FrameBodyTXXX.PRODUCER_SORT, false),
    QUALITY("Quality", true),
    RANKING(FrameBodyTXXX.RANKING, true),
    RECORD_LABEL("WM/Publisher", false),
    RECORDING_ENGINEER(FrameBodyTXXX.RECORDING_ENGINEER, true),
    RECORDING_ENGINEER_SORT(FrameBodyTXXX.RECORDING_ENGINEER_SORT, true),
    RECORDINGDATE(FrameBodyTXXX.RECORDINGDATE, false),
    RECORDINGSTARTDATE(FrameBodyTXXX.RECORDINGSTARTDATE, false),
    RECORDINGENDDATE(FrameBodyTXXX.RECORDINGENDDATE, false),
    RECORDINGLOCATION(FrameBodyTXXX.RECORDINGLOCATION, false),
    REMIXER("WM/ModifiedBy", false),
    ROONALBUMTAG(FrameBodyTXXX.ROONALBUMTAG, false),
    ROONTRACKTAG(FrameBodyTXXX.ROONTRACKTAG, false),
    SCRIPT("WM/Script", false),
    SECTION(FrameBodyTXXX.SECTION, false),
    SINGLE_DISC_TRACK_NO(FrameBodyTXXX.SINGLE_DISC_TRACK_NO, true),
    SONGKONG_ID(FrameBodyTXXX.SONGKONG_ID, false),
    SOUND_ENGINEER(FrameBodyTXXX.SOUND_ENGINEER, true),
    SOUND_ENGINEER_SORT(FrameBodyTXXX.SOUND_ENGINEER_SORT, true),
    SUBTITLE("WM/SubTitle", false),
    TAGS("WM/Tags", false),
    TEMPO("Tempo", true),
    TIMBRE(FrameBodyTXXX.TIMBRE, false),
    TITLE_MOVEMENT(FrameBodyTXXX.TITLE_MOVEMENT, false),
    MUSICBRAINZ_WORK(FrameBodyTXXX.MUSICBRAINZ_WORK, false),
    TITLE_SORT("WM/TitleSortOrder", false),
    TONALITY(FrameBodyTXXX.TONALITY, false),
    TRACK("WM/TrackNumber", false),
    TRACK_TOTAL("WM/TrackTotal", false),
    URL_BANDCAMP_ARTIST_SITE("WM/BandcampArtistUrl", false),
    URL_BANDCAMP_RELEASE_SITE("WM/BandcampReleaseUrl", false),
    URL_DISCOGS_ARTIST_SITE("WM/DiscogsArtistUrl", false),
    URL_DISCOGS_RELEASE_SITE("WM/DiscogsReleaseUrl", false),
    URL_LYRICS_SITE("WM/LyricsUrl", false),
    URL_OFFICIAL_ARTIST_SITE("WM/AuthorURL", false),
    URL_OFFICIAL_RELEASE_SITE("WM/OfficialReleaseUrl", false),
    URL_PROMOTIONAL_SITE("WM/PromotionURL", true),
    URL_WIKIPEDIA_ARTIST_SITE("WM/WikipediaArtistUrl", false),
    URL_WIKIPEDIA_RELEASE_SITE("WM/WikipediaReleaseUrl", false),
    USER_RATING("WM/SharedUserRating", true),
    VERSION(FrameBodyTXXX.VERSION, false),
    WORK(FrameBodyTXXX.WORK, true),
    WORK_TYPE(FrameBodyTXXX.WORK_TYPE, true),
    YEAR("WM/Year", false),
    CUSTOM("___CUSTOM___", true);

    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Map<String, AsfFieldKey> FIELD_ID_MAP = new HashMap(values().length);
    private final String fieldName;
    private final ContainerType highestContainer;
    private final ContainerType lowestContainer;
    private final boolean multiValued;

    static {
        for (AsfFieldKey asfFieldKey : values()) {
            if (asfFieldKey != CUSTOM) {
                FIELD_ID_MAP.put(asfFieldKey.getFieldName(), asfFieldKey);
            }
        }
    }

    public static AsfFieldKey getAsfFieldKey(String str) {
        AsfFieldKey asfFieldKey = FIELD_ID_MAP.get(str);
        return asfFieldKey == null ? CUSTOM : asfFieldKey;
    }

    public static boolean isMultiValued(String str) {
        AsfFieldKey asfFieldKey = getAsfFieldKey(str);
        return asfFieldKey != null && asfFieldKey.isMultiValued();
    }

    AsfFieldKey(String str, boolean z) {
        this(str, z, ContainerType.EXTENDED_CONTENT, ContainerType.METADATA_LIBRARY_OBJECT);
    }

    AsfFieldKey(String str, boolean z, ContainerType containerType) {
        this(str, z, containerType, containerType);
    }

    AsfFieldKey(String str, boolean z, ContainerType containerType, ContainerType containerType2) {
        this.fieldName = str;
        this.multiValued = z && containerType2.isMultiValued();
        this.lowestContainer = containerType;
        this.highestContainer = containerType2;
    }

    public String getFieldName() {
        return this.fieldName;
    }

    public ContainerType getHighestContainer() {
        return this.highestContainer;
    }

    public ContainerType getLowestContainer() {
        return this.lowestContainer;
    }

    public boolean isMultiValued() {
        return this.multiValued;
    }

    @Override // java.lang.Enum
    public String toString() {
        return getFieldName();
    }
}
