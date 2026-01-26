package org.jaudiotagger.tag.mp4;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.generic.AbstractTag;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.tag.mp4.field.Mp4DiscNoField;
import org.jaudiotagger.tag.mp4.field.Mp4GenreField;
import org.jaudiotagger.tag.mp4.field.Mp4TagByteField;
import org.jaudiotagger.tag.mp4.field.Mp4TagCoverField;
import org.jaudiotagger.tag.mp4.field.Mp4TagReverseDnsField;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextField;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextNumberField;
import org.jaudiotagger.tag.mp4.field.Mp4TrackField;

/* loaded from: classes3.dex */
public class Mp4Tag extends AbstractTag {
    private static final EnumMap<FieldKey, Mp4FieldKey> tagFieldToMp4Field;

    static {
        EnumMap<FieldKey, Mp4FieldKey> enumMap = new EnumMap<>(FieldKey.class);
        tagFieldToMp4Field = enumMap;
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ACOUSTID_FINGERPRINT, (FieldKey) Mp4FieldKey.ACOUSTID_FINGERPRINT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ACOUSTID_ID, (FieldKey) Mp4FieldKey.ACOUSTID_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM, (FieldKey) Mp4FieldKey.ALBUM);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM_ARTIST, (FieldKey) Mp4FieldKey.ALBUM_ARTIST);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM_ARTIST_SORT, (FieldKey) Mp4FieldKey.ALBUM_ARTIST_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM_ARTISTS, (FieldKey) Mp4FieldKey.ALBUM_ARTISTS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM_ARTISTS_SORT, (FieldKey) Mp4FieldKey.ALBUM_ARTISTS_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM_SORT, (FieldKey) Mp4FieldKey.ALBUM_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ALBUM_YEAR, (FieldKey) Mp4FieldKey.ALBUM_YEAR);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.AMAZON_ID, (FieldKey) Mp4FieldKey.ASIN);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ARRANGER, (FieldKey) Mp4FieldKey.ARRANGER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ARRANGER_SORT, (FieldKey) Mp4FieldKey.ARRANGER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ARTIST, (FieldKey) Mp4FieldKey.ARTIST);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ARTISTS, (FieldKey) Mp4FieldKey.ARTISTS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ARTIST_SORT, (FieldKey) Mp4FieldKey.ARTIST_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ARTISTS_SORT, (FieldKey) Mp4FieldKey.ARTISTS_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.AUDIO_ENGINEER, (FieldKey) Mp4FieldKey.AUDIO_ENGINEER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.AUDIO_ENGINEER_SORT, (FieldKey) Mp4FieldKey.AUDIO_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.BALANCE_ENGINEER, (FieldKey) Mp4FieldKey.BALANCE_ENGINEER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.BALANCE_ENGINEER_SORT, (FieldKey) Mp4FieldKey.BALANCE_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.BARCODE, (FieldKey) Mp4FieldKey.BARCODE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.BPM, (FieldKey) Mp4FieldKey.BPM);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CATALOG_NO, (FieldKey) Mp4FieldKey.CATALOGNO);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CHOIR, (FieldKey) Mp4FieldKey.CHOIR);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CHOIR_SORT, (FieldKey) Mp4FieldKey.CHOIR_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CLASSICAL_CATALOG, (FieldKey) Mp4FieldKey.CLASSICAL_CATALOG);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CLASSICAL_NICKNAME, (FieldKey) Mp4FieldKey.CLASSICAL_NICKNAME);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.COMMENT, (FieldKey) Mp4FieldKey.COMMENT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.COMPOSER, (FieldKey) Mp4FieldKey.COMPOSER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.COMPOSER_SORT, (FieldKey) Mp4FieldKey.COMPOSER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CONDUCTOR, (FieldKey) Mp4FieldKey.CONDUCTOR);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.COUNTRY, (FieldKey) Mp4FieldKey.COUNTRY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CONDUCTOR_SORT, (FieldKey) Mp4FieldKey.CONDUCTOR_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.COPYRIGHT, (FieldKey) Mp4FieldKey.COPYRIGHT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.COVER_ART, (FieldKey) Mp4FieldKey.ARTWORK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CREDITS, (FieldKey) Mp4FieldKey.CREDITS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CUSTOM1, (FieldKey) Mp4FieldKey.MM_CUSTOM_1);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CUSTOM2, (FieldKey) Mp4FieldKey.MM_CUSTOM_2);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CUSTOM3, (FieldKey) Mp4FieldKey.MM_CUSTOM_3);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CUSTOM4, (FieldKey) Mp4FieldKey.MM_CUSTOM_4);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.CUSTOM5, (FieldKey) Mp4FieldKey.MM_CUSTOM_5);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.DISC_NO, (FieldKey) Mp4FieldKey.DISCNUMBER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.DISC_SUBTITLE, (FieldKey) Mp4FieldKey.DISC_SUBTITLE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.DISC_TOTAL, (FieldKey) Mp4FieldKey.DISCNUMBER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.DJMIXER, (FieldKey) Mp4FieldKey.DJMIXER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.DJMIXER_SORT, (FieldKey) Mp4FieldKey.DJMIXER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_ELECTRONIC, (FieldKey) Mp4FieldKey.MOOD_ELECTRONIC);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ENCODER, (FieldKey) Mp4FieldKey.ENCODER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ENGINEER, (FieldKey) Mp4FieldKey.ENGINEER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ENGINEER_SORT, (FieldKey) Mp4FieldKey.ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ENSEMBLE, (FieldKey) Mp4FieldKey.ENSEMBLE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ENSEMBLE_SORT, (FieldKey) Mp4FieldKey.ENSEMBLE_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.FBPM, (FieldKey) Mp4FieldKey.FBPM);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.GENRE, (FieldKey) Mp4FieldKey.GENRE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.GROUP, (FieldKey) Mp4FieldKey.GROUP);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.GROUPING, (FieldKey) Mp4FieldKey.GROUPING);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.INSTRUMENT, (FieldKey) Mp4FieldKey.INSTRUMENT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.INVOLVEDPEOPLE, (FieldKey) Mp4FieldKey.INVOLVEDPEOPLE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IPI, (FieldKey) Mp4FieldKey.IPI);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ISRC, (FieldKey) Mp4FieldKey.ISRC);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ISWC, (FieldKey) Mp4FieldKey.ISWC);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IS_COMPILATION, (FieldKey) Mp4FieldKey.COMPILATION);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IS_CLASSICAL, (FieldKey) Mp4FieldKey.IS_CLASSICAL);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IS_GREATEST_HITS, (FieldKey) Mp4FieldKey.IS_GREATEST_HITS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IS_HD, (FieldKey) Mp4FieldKey.IS_HD);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IS_LIVE, (FieldKey) Mp4FieldKey.IS_LIVE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.IS_SOUNDTRACK, (FieldKey) Mp4FieldKey.IS_SOUNDTRACK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.JAIKOZ_ID, (FieldKey) Mp4FieldKey.JAIKOZ_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.KEY, (FieldKey) Mp4FieldKey.KEY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.LANGUAGE, (FieldKey) Mp4FieldKey.LANGUAGE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.LYRICIST, (FieldKey) Mp4FieldKey.LYRICIST);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.LYRICIST_SORT, (FieldKey) Mp4FieldKey.LYRICIST_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.LYRICS, (FieldKey) Mp4FieldKey.LYRICS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MASTERING, (FieldKey) Mp4FieldKey.MASTERING);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MASTERING_SORT, (FieldKey) Mp4FieldKey.MASTERING_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MEDIA, (FieldKey) Mp4FieldKey.MEDIA);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MIXER, (FieldKey) Mp4FieldKey.MIXER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MIXER_SORT, (FieldKey) Mp4FieldKey.MIXER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD, (FieldKey) Mp4FieldKey.MOOD);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_ACOUSTIC, (FieldKey) Mp4FieldKey.MOOD_ACOUSTIC);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_AGGRESSIVE, (FieldKey) Mp4FieldKey.MOOD_AGGRESSIVE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_AROUSAL, (FieldKey) Mp4FieldKey.MOOD_AROUSAL);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_DANCEABILITY, (FieldKey) Mp4FieldKey.MOOD_DANCEABILITY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_HAPPY, (FieldKey) Mp4FieldKey.MOOD_HAPPY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_INSTRUMENTAL, (FieldKey) Mp4FieldKey.MOOD_INSTRUMENTAL);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_PARTY, (FieldKey) Mp4FieldKey.MOOD_PARTY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_RELAXED, (FieldKey) Mp4FieldKey.MOOD_RELAXED);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_SAD, (FieldKey) Mp4FieldKey.MOOD_SAD);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOOD_VALENCE, (FieldKey) Mp4FieldKey.MOOD_VALENCE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOVEMENT, (FieldKey) Mp4FieldKey.MOVEMENT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOVEMENT_NO, (FieldKey) Mp4FieldKey.MOVEMENT_NO);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MOVEMENT_TOTAL, (FieldKey) Mp4FieldKey.MOVEMENT_TOTAL);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_ARTISTID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_ARTISTID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_DISC_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_DISCID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_ORIGINAL_RELEASE_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_ORIGINALALBUMID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASEARTISTID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_ALBUMARTISTID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASEID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_ALBUMID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASE_COUNTRY, (FieldKey) Mp4FieldKey.RELEASECOUNTRY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASE_GROUP_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_RELEASE_GROUPID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASE_STATUS, (FieldKey) Mp4FieldKey.MUSICBRAINZ_ALBUM_STATUS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASE_TRACK_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_RELEASE_TRACKID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RELEASE_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_ALBUM_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_TRACK_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_TRACKID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORKID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RECORDING_WORK_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_RECORDING_WORK_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_RECORDING_WORK, (FieldKey) Mp4FieldKey.MUSICBRAINZ_RECORDING_WORK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE, (FieldKey) Mp4FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.MUSICIP_ID, (FieldKey) Mp4FieldKey.MUSICIP_PUID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.OCCASION, (FieldKey) Mp4FieldKey.MM_OCCASION);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.OPUS, (FieldKey) Mp4FieldKey.OPUS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORCHESTRA, (FieldKey) Mp4FieldKey.ORCHESTRA);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORCHESTRA_SORT, (FieldKey) Mp4FieldKey.ORCHESTRA_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORIGINAL_ALBUM, (FieldKey) Mp4FieldKey.MM_ORIGINAL_ALBUM_TITLE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORIGINALRELEASEDATE, (FieldKey) Mp4FieldKey.ORIGINALRELEASEDATE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORIGINAL_ARTIST, (FieldKey) Mp4FieldKey.MM_ORIGINAL_ARTIST);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORIGINAL_LYRICIST, (FieldKey) Mp4FieldKey.MM_ORIGINAL_LYRICIST);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ORIGINAL_YEAR, (FieldKey) Mp4FieldKey.MM_ORIGINAL_YEAR);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.OVERALL_WORK, (FieldKey) Mp4FieldKey.OVERALL_WORK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PART, (FieldKey) Mp4FieldKey.PART);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PART_NUMBER, (FieldKey) Mp4FieldKey.PART_NUMBER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PART_TYPE, (FieldKey) Mp4FieldKey.PART_TYPE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PERFORMER, (FieldKey) Mp4FieldKey.PERFORMER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PERFORMER_NAME, (FieldKey) Mp4FieldKey.PERFORMER_NAME);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PERFORMER_NAME_SORT, (FieldKey) Mp4FieldKey.PERFORMER_NAME_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PERIOD, (FieldKey) Mp4FieldKey.PERIOD);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PRODUCER, (FieldKey) Mp4FieldKey.PRODUCER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.PRODUCER_SORT, (FieldKey) Mp4FieldKey.PRODUCER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.QUALITY, (FieldKey) Mp4FieldKey.MM_QUALITY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RANKING, (FieldKey) Mp4FieldKey.RANKING);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RATING, (FieldKey) Mp4FieldKey.SCORE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORD_LABEL, (FieldKey) Mp4FieldKey.LABEL);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORDING_ENGINEER, (FieldKey) Mp4FieldKey.RECORDING_ENGINEER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORDING_ENGINEER_SORT, (FieldKey) Mp4FieldKey.RECORDING_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORDINGDATE, (FieldKey) Mp4FieldKey.RECORDINGDATE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORDINGSTARTDATE, (FieldKey) Mp4FieldKey.RECORDINGSTARTDATE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORDINGENDDATE, (FieldKey) Mp4FieldKey.RECORDINGENDDATE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.RECORDINGLOCATION, (FieldKey) Mp4FieldKey.RECORDINGLOCATION);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.REMIXER, (FieldKey) Mp4FieldKey.REMIXER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ROONALBUMTAG, (FieldKey) Mp4FieldKey.ROONALBUMTAG);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.ROONTRACKTAG, (FieldKey) Mp4FieldKey.ROONTRACKTAG);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SCRIPT, (FieldKey) Mp4FieldKey.SCRIPT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SECTION, (FieldKey) Mp4FieldKey.SECTION);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SINGLE_DISC_TRACK_NO, (FieldKey) Mp4FieldKey.SINGLE_DISC_TRACK_NO);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SOUND_ENGINEER, (FieldKey) Mp4FieldKey.SOUND_ENGINEER);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SOUND_ENGINEER_SORT, (FieldKey) Mp4FieldKey.SOUND_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SONGKONG_ID, (FieldKey) Mp4FieldKey.SONGKONG_ID);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.SUBTITLE, (FieldKey) Mp4FieldKey.SUBTITLE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TAGS, (FieldKey) Mp4FieldKey.TAGS);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TEMPO, (FieldKey) Mp4FieldKey.TEMPO);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TIMBRE, (FieldKey) Mp4FieldKey.TIMBRE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TITLE, (FieldKey) Mp4FieldKey.TITLE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TITLE_MOVEMENT, (FieldKey) Mp4FieldKey.TITLE_MOVEMENT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TITLE_SORT, (FieldKey) Mp4FieldKey.TITLE_SORT);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TONALITY, (FieldKey) Mp4FieldKey.TONALITY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TRACK, (FieldKey) Mp4FieldKey.TRACK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.TRACK_TOTAL, (FieldKey) Mp4FieldKey.TRACK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_BANDCAMP_ARTIST_SITE, (FieldKey) Mp4FieldKey.URL_BANDCAMP_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_BANDCAMP_RELEASE_SITE, (FieldKey) Mp4FieldKey.URL_BANDCAMP_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_DISCOGS_ARTIST_SITE, (FieldKey) Mp4FieldKey.URL_DISCOGS_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_DISCOGS_RELEASE_SITE, (FieldKey) Mp4FieldKey.URL_DISCOGS_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_LYRICS_SITE, (FieldKey) Mp4FieldKey.URL_LYRICS_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_OFFICIAL_ARTIST_SITE, (FieldKey) Mp4FieldKey.URL_OFFICIAL_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_OFFICIAL_RELEASE_SITE, (FieldKey) Mp4FieldKey.URL_OFFICIAL_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_WIKIPEDIA_ARTIST_SITE, (FieldKey) Mp4FieldKey.URL_WIKIPEDIA_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.URL_WIKIPEDIA_RELEASE_SITE, (FieldKey) Mp4FieldKey.URL_WIKIPEDIA_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.VERSION, (FieldKey) Mp4FieldKey.VERSION);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.WORK, (FieldKey) Mp4FieldKey.WORK);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.YEAR, (FieldKey) Mp4FieldKey.DAY);
        enumMap.put((EnumMap<FieldKey, Mp4FieldKey>) FieldKey.WORK_TYPE, (FieldKey) Mp4FieldKey.WORK_TYPE);
    }

    public static EnumMap<FieldKey, Mp4FieldKey> getMapping() {
        return tagFieldToMp4Field;
    }

    public TagField createGenreField(String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (TagOptionSingleton.getInstance().isWriteMp4GenresAsText()) {
            return new Mp4TagTextField(Mp4FieldKey.GENRE_CUSTOM.getFieldName(), str);
        }
        if (Mp4GenreField.isValidGenre(str)) {
            return new Mp4GenreField(str);
        }
        return new Mp4TagTextField(Mp4FieldKey.GENRE_CUSTOM.getFieldName(), str);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag
    protected boolean isAllowedEncoding(Charset charset) {
        return StandardCharsets.UTF_8.equals(charset);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public String toString() {
        return "Mpeg4 " + super.toString();
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return getFields(fieldKey).size() != 0;
    }

    public boolean hasField(Mp4FieldKey mp4FieldKey) {
        return getFields(mp4FieldKey.getFieldName()).size() != 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        Mp4FieldKey mp4FieldKey = tagFieldToMp4Field.get(fieldKey);
        if (mp4FieldKey == null) {
            throw new KeyNotFoundException();
        }
        List<TagField> fields = getFields(mp4FieldKey.getFieldName());
        ArrayList arrayList = new ArrayList();
        if (fieldKey == FieldKey.KEY) {
            return fields.size() == 0 ? getFields(Mp4FieldKey.KEY_OLD.getFieldName()) : fields;
        }
        if (fieldKey == FieldKey.GENRE) {
            return fields.size() == 0 ? getFields(Mp4FieldKey.GENRE_CUSTOM.getFieldName()) : fields;
        }
        if (fieldKey == FieldKey.TRACK) {
            for (TagField tagField : fields) {
                if (((Mp4TrackField) tagField).getTrackNo().shortValue() > 0) {
                    arrayList.add(tagField);
                }
            }
            return arrayList;
        }
        if (fieldKey == FieldKey.TRACK_TOTAL) {
            for (TagField tagField2 : fields) {
                if (((Mp4TrackField) tagField2).getTrackTotal().shortValue() > 0) {
                    arrayList.add(tagField2);
                }
            }
            return arrayList;
        }
        if (fieldKey == FieldKey.DISC_NO) {
            for (TagField tagField3 : fields) {
                if (((Mp4DiscNoField) tagField3).getDiscNo().shortValue() > 0) {
                    arrayList.add(tagField3);
                }
            }
            return arrayList;
        }
        if (fieldKey != FieldKey.DISC_TOTAL) {
            return fields;
        }
        for (TagField tagField4 : fields) {
            if (((Mp4DiscNoField) tagField4).getDiscTotal().shortValue() > 0) {
                arrayList.add(tagField4);
            }
        }
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        ArrayList arrayList = new ArrayList();
        for (TagField tagField : getFields(fieldKey)) {
            if (fieldKey == FieldKey.TRACK) {
                arrayList.add(((Mp4TrackField) tagField).getTrackNo().toString());
            } else if (fieldKey == FieldKey.TRACK_TOTAL) {
                arrayList.add(((Mp4TrackField) tagField).getTrackTotal().toString());
            } else if (fieldKey == FieldKey.DISC_NO) {
                arrayList.add(((Mp4DiscNoField) tagField).getDiscNo().toString());
            } else if (fieldKey == FieldKey.DISC_TOTAL) {
                arrayList.add(((Mp4DiscNoField) tagField).getDiscTotal().toString());
            } else {
                arrayList.add(tagField.toString());
            }
        }
        return arrayList;
    }

    public List<TagField> get(Mp4FieldKey mp4FieldKey) throws KeyNotFoundException {
        if (mp4FieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFields(mp4FieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        List<TagField> fields = getFields(fieldKey);
        if (fields.size() > i) {
            TagField tagField = fields.get(i);
            if (fieldKey == FieldKey.TRACK) {
                return ((Mp4TrackField) tagField).getTrackNo().toString();
            }
            if (fieldKey == FieldKey.DISC_NO) {
                return ((Mp4DiscNoField) tagField).getDiscNo().toString();
            }
            if (fieldKey == FieldKey.TRACK_TOTAL) {
                return ((Mp4TrackField) tagField).getTrackTotal().toString();
            }
            if (fieldKey == FieldKey.DISC_TOTAL) {
                return ((Mp4DiscNoField) tagField).getDiscTotal().toString();
            }
            return tagField.toString();
        }
        return "";
    }

    public String getFirst(Mp4FieldKey mp4FieldKey) throws KeyNotFoundException {
        if (mp4FieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFirst(mp4FieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public Mp4TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        List<TagField> fields = getFields(fieldKey);
        if (fields.size() == 0) {
            return null;
        }
        return (Mp4TagField) fields.get(0);
    }

    public Mp4TagField getFirstField(Mp4FieldKey mp4FieldKey) throws KeyNotFoundException {
        if (mp4FieldKey == null) {
            throw new KeyNotFoundException();
        }
        return (Mp4TagField) super.getFirstField(mp4FieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        String fieldName = tagFieldToMp4Field.get(fieldKey).getFieldName();
        if (fieldKey == FieldKey.KEY) {
            deleteField(Mp4FieldKey.KEY_OLD);
            deleteField(fieldName);
            return;
        }
        if (fieldKey == FieldKey.TRACK) {
            if (getFirst(FieldKey.TRACK_TOTAL).length() == 0) {
                super.deleteField(fieldName);
                return;
            } else {
                ((Mp4TrackField) getFirstField(FieldKey.TRACK_TOTAL)).setTrackNo(0);
                return;
            }
        }
        if (fieldKey == FieldKey.TRACK_TOTAL) {
            if (getFirst(FieldKey.TRACK).length() == 0) {
                super.deleteField(fieldName);
                return;
            } else {
                ((Mp4TrackField) getFirstField(FieldKey.TRACK)).setTrackTotal(0);
                return;
            }
        }
        if (fieldKey == FieldKey.DISC_NO) {
            if (getFirst(FieldKey.DISC_TOTAL).length() == 0) {
                super.deleteField(fieldName);
                return;
            } else {
                ((Mp4DiscNoField) getFirstField(FieldKey.DISC_TOTAL)).setDiscNo(0);
                return;
            }
        }
        if (fieldKey == FieldKey.DISC_TOTAL) {
            if (getFirst(FieldKey.DISC_NO).length() == 0) {
                super.deleteField(fieldName);
                return;
            } else {
                ((Mp4DiscNoField) getFirstField(FieldKey.DISC_NO)).setDiscTotal(0);
                return;
            }
        }
        if (fieldKey == FieldKey.GENRE) {
            super.deleteField(Mp4FieldKey.GENRE.getFieldName());
            super.deleteField(Mp4FieldKey.GENRE_CUSTOM.getFieldName());
        } else {
            super.deleteField(fieldName);
        }
    }

    public void deleteField(Mp4FieldKey mp4FieldKey) throws KeyNotFoundException {
        if (mp4FieldKey == null) {
            throw new KeyNotFoundException();
        }
        super.deleteField(mp4FieldKey.getFieldName());
    }

    public TagField createArtworkField(byte[] bArr) {
        return new Mp4TagCoverField(bArr);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        return new Mp4TagCoverField(artwork.getBinaryData());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException, NumberFormatException {
        if (fieldKey == FieldKey.TRACK || fieldKey == FieldKey.TRACK_TOTAL || fieldKey == FieldKey.DISC_NO || fieldKey == FieldKey.DISC_TOTAL) {
            setField(fieldKey, strArr);
        } else {
            addField(createField(fieldKey, strArr));
        }
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException, NumberFormatException {
        if (strArr == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        String str = strArr[0];
        if (fieldKey == FieldKey.TRACK || fieldKey == FieldKey.TRACK_TOTAL || fieldKey == FieldKey.DISC_NO || fieldKey == FieldKey.DISC_TOTAL) {
            try {
                int i = Integer.parseInt(str);
                if (fieldKey == FieldKey.TRACK) {
                    return new Mp4TrackField(i);
                }
                if (fieldKey == FieldKey.TRACK_TOTAL) {
                    return new Mp4TrackField(0, i);
                }
                if (fieldKey == FieldKey.DISC_NO) {
                    return new Mp4DiscNoField(i);
                }
                if (fieldKey == FieldKey.DISC_TOTAL) {
                    return new Mp4DiscNoField(0, i);
                }
            } catch (NumberFormatException e) {
                throw new FieldDataInvalidException("Value " + str + " is not a number as required", e);
            }
        } else if (fieldKey == FieldKey.GENRE) {
            if (TagOptionSingleton.getInstance().isWriteMp4GenresAsText()) {
                return new Mp4TagTextField(Mp4FieldKey.GENRE_CUSTOM.getFieldName(), str);
            }
            if (Mp4GenreField.isValidGenre(str)) {
                return new Mp4GenreField(str);
            }
            return new Mp4TagTextField(Mp4FieldKey.GENRE_CUSTOM.getFieldName(), str);
        }
        return createField(tagFieldToMp4Field.get(fieldKey), str);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException, NumberFormatException {
        TagField tagFieldCreateField = createField(fieldKey, strArr);
        if (fieldKey == FieldKey.GENRE) {
            if (tagFieldCreateField.getId().equals(Mp4FieldKey.GENRE.getFieldName())) {
                deleteField(Mp4FieldKey.GENRE_CUSTOM);
            } else if (tagFieldCreateField.getId().equals(Mp4FieldKey.GENRE_CUSTOM.getFieldName())) {
                deleteField(Mp4FieldKey.GENRE);
            }
        }
        setField(tagFieldCreateField);
    }

    public void setField(Mp4FieldKey mp4FieldKey, String str) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(mp4FieldKey, str));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) {
        if (tagField == null) {
            return;
        }
        if (tagField.getId().equals(Mp4FieldKey.TRACK.getFieldName())) {
            List<TagField> list = this.fields.get(tagField.getId());
            if (list == null || list.size() == 0) {
                super.setField(tagField);
                return;
            }
            Mp4TrackField mp4TrackField = (Mp4TrackField) list.get(0);
            Mp4TrackField mp4TrackField2 = (Mp4TrackField) tagField;
            Short trackNo = mp4TrackField.getTrackNo();
            Short trackTotal = mp4TrackField.getTrackTotal();
            if (mp4TrackField2.getTrackNo().shortValue() > 0) {
                trackNo = mp4TrackField2.getTrackNo();
            }
            if (mp4TrackField2.getTrackTotal().shortValue() > 0) {
                trackTotal = mp4TrackField2.getTrackTotal();
            }
            super.setField(new Mp4TrackField(trackNo.shortValue(), trackTotal.shortValue()));
            return;
        }
        if (tagField.getId().equals(Mp4FieldKey.DISCNUMBER.getFieldName())) {
            List<TagField> list2 = this.fields.get(tagField.getId());
            if (list2 == null || list2.size() == 0) {
                super.setField(tagField);
                return;
            }
            Mp4DiscNoField mp4DiscNoField = (Mp4DiscNoField) list2.get(0);
            Mp4DiscNoField mp4DiscNoField2 = (Mp4DiscNoField) tagField;
            Short discNo = mp4DiscNoField.getDiscNo();
            Short discTotal = mp4DiscNoField.getDiscTotal();
            if (mp4DiscNoField2.getDiscNo().shortValue() > 0) {
                discNo = mp4DiscNoField2.getDiscNo();
            }
            if (mp4DiscNoField2.getDiscTotal().shortValue() > 0) {
                discTotal = mp4DiscNoField2.getDiscTotal();
            }
            super.setField(new Mp4DiscNoField(discNo.shortValue(), discTotal.shortValue()));
            return;
        }
        super.setField(tagField);
    }

    public TagField createField(Mp4FieldKey mp4FieldKey, String str) throws KeyNotFoundException, FieldDataInvalidException {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (mp4FieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (mp4FieldKey == Mp4FieldKey.COMPILATION) {
            if (str.equalsIgnoreCase("true") || str.equals("1")) {
                return createCompilationField(true);
            }
            return createCompilationField(false);
        }
        if (mp4FieldKey == Mp4FieldKey.GENRE) {
            if (Mp4GenreField.isValidGenre(str)) {
                return new Mp4GenreField(str);
            }
            throw new IllegalArgumentException(ErrorMessage.NOT_STANDARD_MP$_GENRE.getMsg());
        }
        if (mp4FieldKey == Mp4FieldKey.GENRE_CUSTOM) {
            return new Mp4TagTextField(Mp4FieldKey.GENRE_CUSTOM.getFieldName(), str);
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.DISC_NO) {
            return new Mp4DiscNoField(str);
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.TRACK_NO) {
            return new Mp4TrackField(str);
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.BYTE) {
            return new Mp4TagByteField(mp4FieldKey, str, mp4FieldKey.getFieldLength());
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.NUMBER) {
            return new Mp4TagTextNumberField(mp4FieldKey.getFieldName(), str);
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.REVERSE_DNS) {
            return new Mp4TagReverseDnsField(mp4FieldKey, str);
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.ARTWORK) {
            throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.TEXT) {
            return new Mp4TagTextField(mp4FieldKey.getFieldName(), str);
        }
        if (mp4FieldKey.getSubClassFieldType() == Mp4TagFieldSubType.UNKNOWN) {
            throw new UnsupportedOperationException(ErrorMessage.DO_NOT_KNOW_HOW_TO_CREATE_THIS_ATOM_TYPE.getMsg(mp4FieldKey.getFieldName()));
        }
        throw new UnsupportedOperationException(ErrorMessage.DO_NOT_KNOW_HOW_TO_CREATE_THIS_ATOM_TYPE.getMsg(mp4FieldKey.getFieldName()));
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() throws KeyNotFoundException {
        List<TagField> list = get(Mp4FieldKey.ARTWORK);
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<TagField> it = list.iterator();
        while (it.hasNext()) {
            Mp4TagCoverField mp4TagCoverField = (Mp4TagCoverField) it.next();
            Artwork artwork = ArtworkFactory.getNew();
            artwork.setBinaryData(mp4TagCoverField.getData());
            artwork.setMimeType(Mp4TagCoverField.getMimeTypeForImageType(mp4TagCoverField.getFieldType()));
            arrayList.add(artwork);
        }
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        if (z) {
            return new Mp4TagByteField(Mp4FieldKey.COMPILATION, Mp4TagByteField.TRUE_VALUE, Mp4FieldKey.COMPILATION.getFieldLength());
        }
        return new Mp4TagByteField(Mp4FieldKey.COMPILATION, Mp4TagByteField.FALSE_VALUE, Mp4FieldKey.COMPILATION.getFieldLength());
    }
}
