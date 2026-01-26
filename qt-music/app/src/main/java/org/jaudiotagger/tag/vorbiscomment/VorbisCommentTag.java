package org.jaudiotagger.tag.vorbiscomment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;
import org.jaudiotagger.audio.generic.AbstractTag;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.tag.vorbiscomment.util.Base64Coder;

/* loaded from: classes3.dex */
public class VorbisCommentTag extends AbstractTag {
    public static final String DEFAULT_VENDOR = "jaudiotagger";
    private static EnumMap<FieldKey, VorbisCommentFieldKey> tagFieldToOggField;

    static {
        EnumMap<FieldKey, VorbisCommentFieldKey> enumMap = new EnumMap<>(FieldKey.class);
        tagFieldToOggField = enumMap;
        enumMap.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ACOUSTID_FINGERPRINT, (FieldKey) VorbisCommentFieldKey.ACOUSTID_FINGERPRINT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ACOUSTID_ID, (FieldKey) VorbisCommentFieldKey.ACOUSTID_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM, (FieldKey) VorbisCommentFieldKey.ALBUM);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM_ARTIST, (FieldKey) VorbisCommentFieldKey.ALBUMARTIST);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM_YEAR, (FieldKey) VorbisCommentFieldKey.ALBUM_YEAR);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM_ARTISTS, (FieldKey) VorbisCommentFieldKey.ALBUMARTISTS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM_ARTISTS_SORT, (FieldKey) VorbisCommentFieldKey.ALBUMARTISTSSORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM_ARTIST_SORT, (FieldKey) VorbisCommentFieldKey.ALBUMARTISTSORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ALBUM_SORT, (FieldKey) VorbisCommentFieldKey.ALBUMSORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.AMAZON_ID, (FieldKey) VorbisCommentFieldKey.ASIN);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ARRANGER, (FieldKey) VorbisCommentFieldKey.ARRANGER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ARRANGER_SORT, (FieldKey) VorbisCommentFieldKey.ARRANGER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.AUDIO_ENGINEER, (FieldKey) VorbisCommentFieldKey.AUDIO_ENGINEER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.AUDIO_ENGINEER_SORT, (FieldKey) VorbisCommentFieldKey.AUDIO_ENGINEER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ARTIST, (FieldKey) VorbisCommentFieldKey.ARTIST);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ARTISTS, (FieldKey) VorbisCommentFieldKey.ARTISTS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ARTISTS_SORT, (FieldKey) VorbisCommentFieldKey.ARTISTS_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ARTIST_SORT, (FieldKey) VorbisCommentFieldKey.ARTISTSORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.BALANCE_ENGINEER, (FieldKey) VorbisCommentFieldKey.BALANCE_ENGINEER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.BALANCE_ENGINEER_SORT, (FieldKey) VorbisCommentFieldKey.BALANCE_ENGINEER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.BARCODE, (FieldKey) VorbisCommentFieldKey.BARCODE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.BPM, (FieldKey) VorbisCommentFieldKey.BPM);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CATALOG_NO, (FieldKey) VorbisCommentFieldKey.CATALOGNUMBER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CHOIR, (FieldKey) VorbisCommentFieldKey.CHOIR);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CHOIR_SORT, (FieldKey) VorbisCommentFieldKey.CHOIR_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CLASSICAL_CATALOG, (FieldKey) VorbisCommentFieldKey.CLASSICAL_CATALOG);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CLASSICAL_NICKNAME, (FieldKey) VorbisCommentFieldKey.CLASSICAL_NICKNAME);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.COMMENT, (FieldKey) VorbisCommentFieldKey.COMMENT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.COMPOSER, (FieldKey) VorbisCommentFieldKey.COMPOSER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.COMPOSER_SORT, (FieldKey) VorbisCommentFieldKey.COMPOSERSORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.COPYRIGHT, (FieldKey) VorbisCommentFieldKey.COPYRIGHT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CONDUCTOR, (FieldKey) VorbisCommentFieldKey.CONDUCTOR);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CONDUCTOR_SORT, (FieldKey) VorbisCommentFieldKey.CONDUCTOR_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.COUNTRY, (FieldKey) VorbisCommentFieldKey.COUNTRY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.COVER_ART, (FieldKey) VorbisCommentFieldKey.METADATA_BLOCK_PICTURE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CREDITS, (FieldKey) VorbisCommentFieldKey.CREDITS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CUSTOM1, (FieldKey) VorbisCommentFieldKey.CUSTOM1);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CUSTOM2, (FieldKey) VorbisCommentFieldKey.CUSTOM2);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CUSTOM3, (FieldKey) VorbisCommentFieldKey.CUSTOM3);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CUSTOM4, (FieldKey) VorbisCommentFieldKey.CUSTOM4);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.CUSTOM5, (FieldKey) VorbisCommentFieldKey.CUSTOM5);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.DISC_NO, (FieldKey) VorbisCommentFieldKey.DISCNUMBER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.DISC_SUBTITLE, (FieldKey) VorbisCommentFieldKey.DISCSUBTITLE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.DISC_TOTAL, (FieldKey) VorbisCommentFieldKey.DISCTOTAL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.DJMIXER, (FieldKey) VorbisCommentFieldKey.DJMIXER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.DJMIXER_SORT, (FieldKey) VorbisCommentFieldKey.DJMIXER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ENCODER, (FieldKey) VorbisCommentFieldKey.VENDOR);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ENGINEER, (FieldKey) VorbisCommentFieldKey.ENGINEER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ENGINEER_SORT, (FieldKey) VorbisCommentFieldKey.ENGINEER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ENSEMBLE, (FieldKey) VorbisCommentFieldKey.ENSEMBLE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ENSEMBLE_SORT, (FieldKey) VorbisCommentFieldKey.ENSEMBLE_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.FBPM, (FieldKey) VorbisCommentFieldKey.FBPM);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.GENRE, (FieldKey) VorbisCommentFieldKey.GENRE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.GROUP, (FieldKey) VorbisCommentFieldKey.GROUP);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.GROUPING, (FieldKey) VorbisCommentFieldKey.GROUPING);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.INSTRUMENT, (FieldKey) VorbisCommentFieldKey.INSTRUMENT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.INVOLVEDPEOPLE, (FieldKey) VorbisCommentFieldKey.INVOLVEDPEOPLE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IPI, (FieldKey) VorbisCommentFieldKey.IPI);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ISRC, (FieldKey) VorbisCommentFieldKey.ISRC);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ISWC, (FieldKey) VorbisCommentFieldKey.ISWC);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IS_CLASSICAL, (FieldKey) VorbisCommentFieldKey.IS_CLASSICAL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IS_COMPILATION, (FieldKey) VorbisCommentFieldKey.COMPILATION);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IS_GREATEST_HITS, (FieldKey) VorbisCommentFieldKey.IS_GREATEST_HITS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IS_HD, (FieldKey) VorbisCommentFieldKey.IS_HD);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IS_LIVE, (FieldKey) VorbisCommentFieldKey.IS_LIVE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.IS_SOUNDTRACK, (FieldKey) VorbisCommentFieldKey.IS_SOUNDTRACK);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.JAIKOZ_ID, (FieldKey) VorbisCommentFieldKey.JAIKOZ_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.KEY, (FieldKey) VorbisCommentFieldKey.KEY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.LANGUAGE, (FieldKey) VorbisCommentFieldKey.LANGUAGE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.LYRICIST, (FieldKey) VorbisCommentFieldKey.LYRICIST);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.LYRICIST_SORT, (FieldKey) VorbisCommentFieldKey.LYRICIST_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.LYRICS, (FieldKey) VorbisCommentFieldKey.LYRICS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MASTERING, (FieldKey) VorbisCommentFieldKey.MASTERING);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MASTERING_SORT, (FieldKey) VorbisCommentFieldKey.MASTERING_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MEDIA, (FieldKey) VorbisCommentFieldKey.MEDIA);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MIXER, (FieldKey) VorbisCommentFieldKey.MIXER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MIXER_SORT, (FieldKey) VorbisCommentFieldKey.MIXER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD, (FieldKey) VorbisCommentFieldKey.MOOD);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_ACOUSTIC, (FieldKey) VorbisCommentFieldKey.MOOD_ACOUSTIC);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_AGGRESSIVE, (FieldKey) VorbisCommentFieldKey.MOOD_AGGRESSIVE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_AROUSAL, (FieldKey) VorbisCommentFieldKey.MOOD_AROUSAL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_DANCEABILITY, (FieldKey) VorbisCommentFieldKey.MOOD_DANCEABILITY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_ELECTRONIC, (FieldKey) VorbisCommentFieldKey.MOOD_ELECTRONIC);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_HAPPY, (FieldKey) VorbisCommentFieldKey.MOOD_HAPPY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_INSTRUMENTAL, (FieldKey) VorbisCommentFieldKey.MOOD_INSTRUMENTAL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_PARTY, (FieldKey) VorbisCommentFieldKey.MOOD_PARTY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_RELAXED, (FieldKey) VorbisCommentFieldKey.MOOD_RELAXED);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_SAD, (FieldKey) VorbisCommentFieldKey.MOOD_SAD);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOOD_VALENCE, (FieldKey) VorbisCommentFieldKey.MOOD_VALENCE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOVEMENT, (FieldKey) VorbisCommentFieldKey.MOVEMENT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOVEMENT_NO, (FieldKey) VorbisCommentFieldKey.MOVEMENT_NO);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MOVEMENT_TOTAL, (FieldKey) VorbisCommentFieldKey.MOVEMENT_TOTAL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_ARTISTID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_ARTISTID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_DISC_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_DISCID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_ORIGINAL_RELEASE_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_ORIGINAL_ALBUMID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASEARTISTID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_ALBUMARTISTID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASEID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_ALBUMID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_COUNTRY, (FieldKey) VorbisCommentFieldKey.RELEASECOUNTRY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_GROUP_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_RELEASEGROUPID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_STATUS, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_ALBUMSTATUS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_TRACK_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_RELEASETRACKID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_ALBUMTYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_TRACK_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_TRACKID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RECORDING_WORK, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_RECORDING_WORK);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_RECORDING_WORK_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_RECORDING_WORK_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORKID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL1);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL2);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL3);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL4);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL5);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL6);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE, (FieldKey) VorbisCommentFieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.MUSICIP_ID, (FieldKey) VorbisCommentFieldKey.MUSICIP_PUID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.OCCASION, (FieldKey) VorbisCommentFieldKey.OCCASION);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.OPUS, (FieldKey) VorbisCommentFieldKey.OPUS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORCHESTRA, (FieldKey) VorbisCommentFieldKey.ORCHESTRA);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORCHESTRA_SORT, (FieldKey) VorbisCommentFieldKey.ORCHESTRA_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORIGINAL_ALBUM, (FieldKey) VorbisCommentFieldKey.ORIGINAL_ALBUM);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORIGINALRELEASEDATE, (FieldKey) VorbisCommentFieldKey.ORIGINALRELEASEDATE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORIGINAL_ARTIST, (FieldKey) VorbisCommentFieldKey.ORIGINAL_ARTIST);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORIGINAL_LYRICIST, (FieldKey) VorbisCommentFieldKey.ORIGINAL_LYRICIST);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ORIGINAL_YEAR, (FieldKey) VorbisCommentFieldKey.ORIGINAL_YEAR);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.OVERALL_WORK, (FieldKey) VorbisCommentFieldKey.OVERALL_WORK);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PART, (FieldKey) VorbisCommentFieldKey.PART);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PART_NUMBER, (FieldKey) VorbisCommentFieldKey.PART_NUMBER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PART_TYPE, (FieldKey) VorbisCommentFieldKey.PART_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PERFORMER, (FieldKey) VorbisCommentFieldKey.PERFORMER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PERFORMER_NAME, (FieldKey) VorbisCommentFieldKey.PERFORMER_NAME);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PERFORMER_NAME_SORT, (FieldKey) VorbisCommentFieldKey.PERFORMER_NAME_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PERIOD, (FieldKey) VorbisCommentFieldKey.PERIOD);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PRODUCER, (FieldKey) VorbisCommentFieldKey.PRODUCER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.PRODUCER_SORT, (FieldKey) VorbisCommentFieldKey.PRODUCER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.QUALITY, (FieldKey) VorbisCommentFieldKey.QUALITY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RANKING, (FieldKey) VorbisCommentFieldKey.RANKING);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RATING, (FieldKey) VorbisCommentFieldKey.RATING);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORD_LABEL, (FieldKey) VorbisCommentFieldKey.LABEL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORDING_ENGINEER, (FieldKey) VorbisCommentFieldKey.RECORDING_ENGINEER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORDING_ENGINEER_SORT, (FieldKey) VorbisCommentFieldKey.RECORDING_ENGINEER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORDINGLOCATION, (FieldKey) VorbisCommentFieldKey.RECORDINGLOCATION);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORDINGDATE, (FieldKey) VorbisCommentFieldKey.RECORDINGDATE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORDINGSTARTDATE, (FieldKey) VorbisCommentFieldKey.RECORDINGSTARTDATE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.RECORDINGENDDATE, (FieldKey) VorbisCommentFieldKey.RECORDINGENDDATE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.REMIXER, (FieldKey) VorbisCommentFieldKey.REMIXER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ROONALBUMTAG, (FieldKey) VorbisCommentFieldKey.ROONALBUMTAG);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.ROONTRACKTAG, (FieldKey) VorbisCommentFieldKey.ROONTRACKTAG);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SCRIPT, (FieldKey) VorbisCommentFieldKey.SCRIPT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SECTION, (FieldKey) VorbisCommentFieldKey.SECTION);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SINGLE_DISC_TRACK_NO, (FieldKey) VorbisCommentFieldKey.SINGLE_DISC_TRACK_NO);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SONGKONG_ID, (FieldKey) VorbisCommentFieldKey.SONGKONG_ID);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SOUND_ENGINEER, (FieldKey) VorbisCommentFieldKey.SOUND_ENGINEER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SOUND_ENGINEER_SORT, (FieldKey) VorbisCommentFieldKey.SOUND_ENGINEER_SORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.SUBTITLE, (FieldKey) VorbisCommentFieldKey.SUBTITLE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TAGS, (FieldKey) VorbisCommentFieldKey.TAGS);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TEMPO, (FieldKey) VorbisCommentFieldKey.TEMPO);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TIMBRE, (FieldKey) VorbisCommentFieldKey.TIMBRE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TITLE, (FieldKey) VorbisCommentFieldKey.TITLE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TITLE_MOVEMENT, (FieldKey) VorbisCommentFieldKey.TITLE_MOVEMENT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TITLE_SORT, (FieldKey) VorbisCommentFieldKey.TITLESORT);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TONALITY, (FieldKey) VorbisCommentFieldKey.TONALITY);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TRACK, (FieldKey) VorbisCommentFieldKey.TRACKNUMBER);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.TRACK_TOTAL, (FieldKey) VorbisCommentFieldKey.TRACKTOTAL);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_BANDCAMP_ARTIST_SITE, (FieldKey) VorbisCommentFieldKey.URL_BANDCAMP_ARTIST_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_BANDCAMP_RELEASE_SITE, (FieldKey) VorbisCommentFieldKey.URL_BANDCAMP_RELEASE_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_DISCOGS_ARTIST_SITE, (FieldKey) VorbisCommentFieldKey.URL_DISCOGS_ARTIST_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_DISCOGS_RELEASE_SITE, (FieldKey) VorbisCommentFieldKey.URL_DISCOGS_RELEASE_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_LYRICS_SITE, (FieldKey) VorbisCommentFieldKey.URL_LYRICS_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_OFFICIAL_ARTIST_SITE, (FieldKey) VorbisCommentFieldKey.URL_OFFICIAL_ARTIST_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_OFFICIAL_RELEASE_SITE, (FieldKey) VorbisCommentFieldKey.URL_OFFICIAL_RELEASE_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_WIKIPEDIA_ARTIST_SITE, (FieldKey) VorbisCommentFieldKey.URL_WIKIPEDIA_ARTIST_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.URL_WIKIPEDIA_RELEASE_SITE, (FieldKey) VorbisCommentFieldKey.URL_WIKIPEDIA_RELEASE_SITE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.VERSION, (FieldKey) VorbisCommentFieldKey.VERSION);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.WORK, (FieldKey) VorbisCommentFieldKey.WORK);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.WORK_TYPE, (FieldKey) VorbisCommentFieldKey.WORK_TYPE);
        tagFieldToOggField.put((EnumMap<FieldKey, VorbisCommentFieldKey>) FieldKey.YEAR, (FieldKey) VorbisCommentFieldKey.DATE);
    }

    public static VorbisCommentTag createNewTag() {
        VorbisCommentTag vorbisCommentTag = new VorbisCommentTag();
        vorbisCommentTag.setVendor(DEFAULT_VENDOR);
        return vorbisCommentTag;
    }

    public String getVendor() {
        return getFirst(VorbisCommentFieldKey.VENDOR.getFieldName());
    }

    public void setVendor(String str) {
        if (str == null) {
            str = DEFAULT_VENDOR;
        }
        super.setField(new VorbisCommentTagField(VorbisCommentFieldKey.VENDOR.getFieldName(), str));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag
    protected boolean isAllowedEncoding(Charset charset) {
        return charset.equals("UTF-8");
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public String toString() {
        return "OGG " + super.toString();
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        return createField(tagFieldToOggField.get(fieldKey), strArr[0]);
    }

    public TagField createField(VorbisCommentFieldKey vorbisCommentFieldKey, String str) throws KeyNotFoundException, FieldDataInvalidException {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (vorbisCommentFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return new VorbisCommentTagField(vorbisCommentFieldKey.getFieldName(), str);
    }

    public TagField createField(String str, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        return new VorbisCommentTagField(str, str2);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        VorbisCommentFieldKey vorbisCommentFieldKey = tagFieldToOggField.get(fieldKey);
        if (vorbisCommentFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFields(vorbisCommentFieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        VorbisCommentFieldKey vorbisCommentFieldKey = tagFieldToOggField.get(fieldKey);
        if (vorbisCommentFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getAll(vorbisCommentFieldKey.getFieldName());
    }

    public List<TagField> get(VorbisCommentFieldKey vorbisCommentFieldKey) throws KeyNotFoundException {
        if (vorbisCommentFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFields(vorbisCommentFieldKey.getFieldName());
    }

    public String getFirst(VorbisCommentFieldKey vorbisCommentFieldKey) throws KeyNotFoundException {
        if (vorbisCommentFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFirst(vorbisCommentFieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return getFields(tagFieldToOggField.get(fieldKey).getFieldName()).size() != 0;
    }

    public boolean hasField(VorbisCommentFieldKey vorbisCommentFieldKey) {
        return getFields(vorbisCommentFieldKey.getFieldName()).size() != 0;
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (fieldKey == FieldKey.ALBUM_ARTIST) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[TagOptionSingleton.getInstance().getVorbisAlbumArtistSaveOptions().ordinal()];
            if (i == 1 || i == 2) {
                deleteField(tagFieldToOggField.get(fieldKey));
                return;
            }
            if (i == 3 || i == 4) {
                deleteField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER);
                return;
            } else {
                if (i != 5) {
                    return;
                }
                deleteField(tagFieldToOggField.get(fieldKey));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER);
                return;
            }
        }
        deleteField(tagFieldToOggField.get(fieldKey));
    }

    public void deleteField(VorbisCommentFieldKey vorbisCommentFieldKey) throws KeyNotFoundException {
        if (vorbisCommentFieldKey == null) {
            throw new KeyNotFoundException();
        }
        super.deleteField(vorbisCommentFieldKey.getFieldName());
    }

    public byte[] getArtworkBinaryData() {
        return Base64Coder.decode(getFirst(VorbisCommentFieldKey.COVERART).toCharArray());
    }

    public String getArtworkMimeType() {
        return getFirst(VorbisCommentFieldKey.COVERARTMIME);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        return this.fields.size() <= 1;
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) {
        if (tagField.getId().equals(VorbisCommentFieldKey.VENDOR.getFieldName())) {
            super.setField(tagField);
        } else {
            super.addField(tagField);
        }
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        return getFirstField(tagFieldToOggField.get(fieldKey).getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        ArrayList arrayList = new ArrayList(1);
        if ((getArtworkBinaryData().length > 0) & (getArtworkBinaryData() != null)) {
            Artwork artwork = ArtworkFactory.getNew();
            artwork.setMimeType(getArtworkMimeType());
            artwork.setBinaryData(getArtworkBinaryData());
            arrayList.add(artwork);
        }
        Iterator<TagField> it = get(VorbisCommentFieldKey.METADATA_BLOCK_PICTURE).iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(ArtworkFactory.createArtworkFromMetadataBlockDataPicture(new MetadataBlockDataPicture(ByteBuffer.wrap(Base64Coder.decode(((TagTextField) it.next()).getContent())))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFrameException e2) {
                throw new RuntimeException(e2);
            }
        }
        return arrayList;
    }

    private MetadataBlockDataPicture createMetadataBlockDataPicture(Artwork artwork) throws FieldDataInvalidException {
        if (artwork.isLinked()) {
            return new MetadataBlockDataPicture(artwork.getImageUrl().getBytes(StandardCharsets.ISO_8859_1), artwork.getPictureType(), "-->", "", 0, 0, 0, 0);
        }
        if (!artwork.setImageFromData()) {
            throw new FieldDataInvalidException("Unable to create MetadataBlockDataPicture from buffered");
        }
        return new MetadataBlockDataPicture(artwork.getBinaryData(), artwork.getPictureType(), artwork.getMimeType(), artwork.getDescription(), artwork.getWidth(), artwork.getHeight(), 0, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        try {
            return createField(VorbisCommentFieldKey.METADATA_BLOCK_PICTURE, new String(Base64Coder.encode(createMetadataBlockDataPicture(artwork).getRawContent())));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(artwork));
        if (getFirst(VorbisCommentFieldKey.COVERART).length() > 0) {
            deleteField(VorbisCommentFieldKey.COVERART);
            deleteField(VorbisCommentFieldKey.COVERARTMIME);
        }
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        addField(createField(artwork));
    }

    @Deprecated
    public void setArtworkField(byte[] bArr, String str) {
        VorbisCommentTagField vorbisCommentTagField = new VorbisCommentTagField(VorbisCommentFieldKey.COVERART.getFieldName(), new String(Base64Coder.encode(bArr)));
        VorbisCommentTagField vorbisCommentTagField2 = new VorbisCommentTagField(VorbisCommentFieldKey.COVERARTMIME.getFieldName(), str);
        setField(vorbisCommentTagField);
        setField(vorbisCommentTagField2);
    }

    public void setField(String str, String str2) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(str, str2));
    }

    public void addField(String str, String str2) throws KeyNotFoundException, FieldDataInvalidException {
        addField(createField(str, str2));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        deleteField(VorbisCommentFieldKey.METADATA_BLOCK_PICTURE);
        deleteField(VorbisCommentFieldKey.COVERART);
        deleteField(VorbisCommentFieldKey.COVERARTMIME);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return createField(FieldKey.IS_COMPILATION, String.valueOf(z));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        String str;
        if (strArr == null || (str = strArr[0]) == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (fieldKey == FieldKey.ALBUM_ARTIST) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[TagOptionSingleton.getInstance().getVorbisAlbumArtistSaveOptions().ordinal()];
            if (i == 1) {
                setField(createField(fieldKey, str));
                return;
            }
            if (i == 2) {
                setField(createField(fieldKey, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName());
                return;
            }
            if (i == 3) {
                setField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
            if (i == 4) {
                setField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST.getFieldName());
                return;
            } else {
                if (i != 5) {
                    return;
                }
                setField(createField(fieldKey, str));
                setField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
        }
        setField(createField(fieldKey, str));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        String str;
        if (strArr == null || (str = strArr[0]) == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (fieldKey == FieldKey.ALBUM_ARTIST) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[TagOptionSingleton.getInstance().getVorbisAlbumArtistSaveOptions().ordinal()];
            if (i == 1) {
                addField(createField(fieldKey, str));
                return;
            }
            if (i == 2) {
                addField(createField(fieldKey, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName());
                return;
            }
            if (i == 3) {
                addField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
            if (i == 4) {
                addField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                deleteField(VorbisCommentFieldKey.ALBUMARTIST.getFieldName());
                return;
            } else {
                if (i != 5) {
                    return;
                }
                addField(createField(fieldKey, str));
                addField(createField(VorbisCommentFieldKey.ALBUMARTIST_JRIVER, str));
                return;
            }
        }
        addField(createField(fieldKey, str));
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        if (fieldKey == FieldKey.ALBUM_ARTIST) {
            int i2 = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistReadOptions[TagOptionSingleton.getInstance().getVorbisAlbumArtisReadOptions().ordinal()];
            if (i2 == 1) {
                return super.getItem(VorbisCommentFieldKey.ALBUMARTIST.getFieldName(), i);
            }
            if (i2 == 2) {
                return super.getItem(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName(), i);
            }
            if (i2 == 3) {
                String item = super.getItem(VorbisCommentFieldKey.ALBUMARTIST.getFieldName(), i);
                return item.isEmpty() ? super.getItem(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName(), i) : item;
            }
            if (i2 == 4) {
                String item2 = super.getItem(VorbisCommentFieldKey.ALBUMARTIST_JRIVER.getFieldName(), i);
                return item2.isEmpty() ? super.getItem(VorbisCommentFieldKey.ALBUMARTIST.getFieldName(), i) : item2;
            }
            VorbisCommentFieldKey vorbisCommentFieldKey = tagFieldToOggField.get(fieldKey);
            if (vorbisCommentFieldKey == null) {
                throw new KeyNotFoundException();
            }
            return super.getItem(vorbisCommentFieldKey.getFieldName(), i);
        }
        VorbisCommentFieldKey vorbisCommentFieldKey2 = tagFieldToOggField.get(fieldKey);
        if (vorbisCommentFieldKey2 == null) {
            throw new KeyNotFoundException();
        }
        return super.getItem(vorbisCommentFieldKey2.getFieldName(), i);
    }

    /* renamed from: org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistReadOptions;
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions;

        static {
            int[] iArr = new int[VorbisAlbumArtistReadOptions.values().length];
            $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistReadOptions = iArr;
            try {
                iArr[VorbisAlbumArtistReadOptions.READ_ALBUMARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistReadOptions[VorbisAlbumArtistReadOptions.READ_JRIVER_ALBUMARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistReadOptions[VorbisAlbumArtistReadOptions.READ_ALBUMARTIST_THEN_JRIVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistReadOptions[VorbisAlbumArtistReadOptions.READ_JRIVER_THEN_ALBUMARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            int[] iArr2 = new int[VorbisAlbumArtistSaveOptions.values().length];
            $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions = iArr2;
            try {
                iArr2[VorbisAlbumArtistSaveOptions.WRITE_ALBUMARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_ALBUMARTIST_AND_DELETE_JRIVER_ALBUMARTIST.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_JRIVER_ALBUMARTIST.ordinal()] = 3;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_JRIVER_ALBUMARTIST_AND_DELETE_ALBUMARTIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$vorbiscomment$VorbisAlbumArtistSaveOptions[VorbisAlbumArtistSaveOptions.WRITE_BOTH.ordinal()] = 5;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }
}
