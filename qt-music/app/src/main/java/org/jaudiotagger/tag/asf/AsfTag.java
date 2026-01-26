package org.jaudiotagger.tag.asf;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.generic.AbstractTag;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.tag.reference.PictureTypes;

/* loaded from: classes3.dex */
public final class AsfTag extends AbstractTag {
    public static final Set<AsfFieldKey> COMMON_FIELDS;
    private static final EnumMap<FieldKey, AsfFieldKey> tagFieldToAsfField;
    private final boolean copyFields;

    private static class AsfFieldIterator implements Iterator<AsfTagField> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final Iterator<TagField> fieldIterator;

        public AsfFieldIterator(Iterator<TagField> it) {
            this.fieldIterator = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.fieldIterator.hasNext();
        }

        @Override // java.util.Iterator
        public AsfTagField next() {
            return (AsfTagField) this.fieldIterator.next();
        }

        @Override // java.util.Iterator
        public void remove() {
            this.fieldIterator.remove();
        }
    }

    static {
        EnumMap<FieldKey, AsfFieldKey> enumMap = new EnumMap<>(FieldKey.class);
        tagFieldToAsfField = enumMap;
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ACOUSTID_FINGERPRINT, (FieldKey) AsfFieldKey.ACOUSTID_FINGERPRINT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ACOUSTID_ID, (FieldKey) AsfFieldKey.ACOUSTID_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM, (FieldKey) AsfFieldKey.ALBUM);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM_ARTIST, (FieldKey) AsfFieldKey.ALBUM_ARTIST);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM_ARTIST_SORT, (FieldKey) AsfFieldKey.ALBUM_ARTIST_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM_ARTISTS, (FieldKey) AsfFieldKey.ALBUM_ARTISTS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM_ARTISTS_SORT, (FieldKey) AsfFieldKey.ALBUM_ARTISTS_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM_SORT, (FieldKey) AsfFieldKey.ALBUM_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ALBUM_YEAR, (FieldKey) AsfFieldKey.ALBUM_YEAR);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.AMAZON_ID, (FieldKey) AsfFieldKey.AMAZON_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ARRANGER, (FieldKey) AsfFieldKey.ARRANGER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ARRANGER_SORT, (FieldKey) AsfFieldKey.ARRANGER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ARTIST, (FieldKey) AsfFieldKey.AUTHOR);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ARTISTS, (FieldKey) AsfFieldKey.ARTISTS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ARTISTS_SORT, (FieldKey) AsfFieldKey.ARTISTS_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ARTIST_SORT, (FieldKey) AsfFieldKey.ARTIST_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.AUDIO_ENGINEER, (FieldKey) AsfFieldKey.AUDIO_ENGINEER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.AUDIO_ENGINEER_SORT, (FieldKey) AsfFieldKey.AUDIO_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.BALANCE_ENGINEER, (FieldKey) AsfFieldKey.BALANCE_ENGINEER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.BALANCE_ENGINEER_SORT, (FieldKey) AsfFieldKey.BALANCE_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.BARCODE, (FieldKey) AsfFieldKey.BARCODE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.BPM, (FieldKey) AsfFieldKey.BPM);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CATALOG_NO, (FieldKey) AsfFieldKey.CATALOG_NO);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CHOIR, (FieldKey) AsfFieldKey.CHOIR);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CHOIR_SORT, (FieldKey) AsfFieldKey.CHOIR_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CLASSICAL_CATALOG, (FieldKey) AsfFieldKey.CLASSICAL_CATALOG);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CLASSICAL_NICKNAME, (FieldKey) AsfFieldKey.CLASSICAL_NICKNAME);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.COMMENT, (FieldKey) AsfFieldKey.DESCRIPTION);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.COMPOSER, (FieldKey) AsfFieldKey.COMPOSER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.COMPOSER_SORT, (FieldKey) AsfFieldKey.COMPOSER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CONDUCTOR, (FieldKey) AsfFieldKey.CONDUCTOR);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CONDUCTOR_SORT, (FieldKey) AsfFieldKey.CONDUCTOR_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.COPYRIGHT, (FieldKey) AsfFieldKey.COPYRIGHT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.COUNTRY, (FieldKey) AsfFieldKey.COUNTRY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.COVER_ART, (FieldKey) AsfFieldKey.COVER_ART);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CREDITS, (FieldKey) AsfFieldKey.CREDITS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CUSTOM1, (FieldKey) AsfFieldKey.CUSTOM1);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CUSTOM2, (FieldKey) AsfFieldKey.CUSTOM2);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CUSTOM3, (FieldKey) AsfFieldKey.CUSTOM3);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CUSTOM4, (FieldKey) AsfFieldKey.CUSTOM4);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.CUSTOM5, (FieldKey) AsfFieldKey.CUSTOM5);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.DISC_NO, (FieldKey) AsfFieldKey.DISC_NO);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.DISC_SUBTITLE, (FieldKey) AsfFieldKey.DISC_SUBTITLE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.DISC_TOTAL, (FieldKey) AsfFieldKey.DISC_TOTAL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.DJMIXER, (FieldKey) AsfFieldKey.DJMIXER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.DJMIXER_SORT, (FieldKey) AsfFieldKey.DJMIXER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_ELECTRONIC, (FieldKey) AsfFieldKey.MOOD_ELECTRONIC);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ENCODER, (FieldKey) AsfFieldKey.ENCODER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ENGINEER, (FieldKey) AsfFieldKey.ENGINEER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ENGINEER_SORT, (FieldKey) AsfFieldKey.ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ENSEMBLE, (FieldKey) AsfFieldKey.ENSEMBLE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ENSEMBLE_SORT, (FieldKey) AsfFieldKey.ENSEMBLE_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.FBPM, (FieldKey) AsfFieldKey.FBPM);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.GENRE, (FieldKey) AsfFieldKey.GENRE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.GROUP, (FieldKey) AsfFieldKey.GROUP);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.GROUPING, (FieldKey) AsfFieldKey.GROUPING);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.INSTRUMENT, (FieldKey) AsfFieldKey.INSTRUMENT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.INVOLVEDPEOPLE, (FieldKey) AsfFieldKey.INVOLVED_PERSON);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IPI, (FieldKey) AsfFieldKey.IPI);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ISRC, (FieldKey) AsfFieldKey.ISRC);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ISWC, (FieldKey) AsfFieldKey.ISWC);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IS_CLASSICAL, (FieldKey) AsfFieldKey.IS_CLASSICAL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IS_COMPILATION, (FieldKey) AsfFieldKey.IS_COMPILATION);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IS_GREATEST_HITS, (FieldKey) AsfFieldKey.IS_GREATEST_HITS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IS_HD, (FieldKey) AsfFieldKey.IS_HD);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IS_LIVE, (FieldKey) AsfFieldKey.IS_LIVE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.IS_SOUNDTRACK, (FieldKey) AsfFieldKey.IS_SOUNDTRACK);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.JAIKOZ_ID, (FieldKey) AsfFieldKey.JAIKOZ_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.KEY, (FieldKey) AsfFieldKey.INITIAL_KEY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.LANGUAGE, (FieldKey) AsfFieldKey.LANGUAGE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.LYRICIST, (FieldKey) AsfFieldKey.LYRICIST);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.LYRICIST_SORT, (FieldKey) AsfFieldKey.LYRICIST_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.LYRICS, (FieldKey) AsfFieldKey.LYRICS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MASTERING, (FieldKey) AsfFieldKey.MASTERING);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MASTERING_SORT, (FieldKey) AsfFieldKey.MASTERING_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MEDIA, (FieldKey) AsfFieldKey.MEDIA);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MIXER, (FieldKey) AsfFieldKey.MIXER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MIXER_SORT, (FieldKey) AsfFieldKey.MIXER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD, (FieldKey) AsfFieldKey.MOOD);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_ACOUSTIC, (FieldKey) AsfFieldKey.MOOD_ACOUSTIC);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_AGGRESSIVE, (FieldKey) AsfFieldKey.MOOD_AGGRESSIVE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_AROUSAL, (FieldKey) AsfFieldKey.MOOD_AROUSAL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_DANCEABILITY, (FieldKey) AsfFieldKey.MOOD_DANCEABILITY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_HAPPY, (FieldKey) AsfFieldKey.MOOD_HAPPY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_INSTRUMENTAL, (FieldKey) AsfFieldKey.MOOD_INSTRUMENTAL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_PARTY, (FieldKey) AsfFieldKey.MOOD_PARTY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_RELAXED, (FieldKey) AsfFieldKey.MOOD_RELAXED);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_SAD, (FieldKey) AsfFieldKey.MOOD_SAD);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOOD_VALENCE, (FieldKey) AsfFieldKey.MOOD_VALENCE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOVEMENT, (FieldKey) AsfFieldKey.MOVEMENT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOVEMENT_NO, (FieldKey) AsfFieldKey.MOVEMENT_NO);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MOVEMENT_TOTAL, (FieldKey) AsfFieldKey.MOVEMENT_TOTAL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_ARTISTID, (FieldKey) AsfFieldKey.MUSICBRAINZ_ARTISTID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_DISC_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_DISC_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_ORIGINAL_RELEASE_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_ORIGINAL_RELEASEID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASEARTISTID, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASEARTISTID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASEID, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASEID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_COUNTRY, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASE_COUNTRY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_GROUP_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASEGROUPID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_STATUS, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASE_STATUS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_TRACK_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASETRACKID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RELEASE_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_RELEASE_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_TRACK_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_TRACK_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORKID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RECORDING_WORK, (FieldKey) AsfFieldKey.MUSICBRAINZ_RECORDING_WORK);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_RECORDING_WORK_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_RECORDING_WORK_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL1);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL1_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL2);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL2_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL3);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL3_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL4);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL4_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL5);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL5_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL6);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE, (FieldKey) AsfFieldKey.MUSICBRAINZ_WORK_PART_LEVEL6_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.MUSICIP_ID, (FieldKey) AsfFieldKey.MUSICIP_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.OCCASION, (FieldKey) AsfFieldKey.OCCASION);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.OPUS, (FieldKey) AsfFieldKey.OPUS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORCHESTRA, (FieldKey) AsfFieldKey.ORCHESTRA);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORCHESTRA_SORT, (FieldKey) AsfFieldKey.ORCHESTRA_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORIGINAL_ALBUM, (FieldKey) AsfFieldKey.ORIGINAL_ALBUM);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORIGINAL_ARTIST, (FieldKey) AsfFieldKey.ORIGINAL_ARTIST);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORIGINAL_LYRICIST, (FieldKey) AsfFieldKey.ORIGINAL_LYRICIST);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORIGINAL_YEAR, (FieldKey) AsfFieldKey.ORIGINAL_YEAR);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ORIGINALRELEASEDATE, (FieldKey) AsfFieldKey.ORIGINALRELEASEDATE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.OVERALL_WORK, (FieldKey) AsfFieldKey.OVERALL_WORK);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PART, (FieldKey) AsfFieldKey.PART);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PART_NUMBER, (FieldKey) AsfFieldKey.PART_NUMBER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PART_TYPE, (FieldKey) AsfFieldKey.PART_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PERFORMER, (FieldKey) AsfFieldKey.PERFORMER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PERFORMER_NAME, (FieldKey) AsfFieldKey.PERFORMER_NAME);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PERFORMER_NAME_SORT, (FieldKey) AsfFieldKey.PERFORMER_NAME_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PERIOD, (FieldKey) AsfFieldKey.PERIOD);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PRODUCER, (FieldKey) AsfFieldKey.PRODUCER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.PRODUCER_SORT, (FieldKey) AsfFieldKey.PRODUCER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.QUALITY, (FieldKey) AsfFieldKey.QUALITY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RANKING, (FieldKey) AsfFieldKey.RANKING);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RATING, (FieldKey) AsfFieldKey.USER_RATING);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORD_LABEL, (FieldKey) AsfFieldKey.RECORD_LABEL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORDING_ENGINEER, (FieldKey) AsfFieldKey.RECORDING_ENGINEER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORDING_ENGINEER_SORT, (FieldKey) AsfFieldKey.RECORDING_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORDINGDATE, (FieldKey) AsfFieldKey.RECORDINGDATE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORDINGSTARTDATE, (FieldKey) AsfFieldKey.RECORDINGSTARTDATE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORDINGENDDATE, (FieldKey) AsfFieldKey.RECORDINGENDDATE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.RECORDINGLOCATION, (FieldKey) AsfFieldKey.RECORDINGLOCATION);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.REMIXER, (FieldKey) AsfFieldKey.REMIXER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ROONALBUMTAG, (FieldKey) AsfFieldKey.ROONALBUMTAG);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.ROONTRACKTAG, (FieldKey) AsfFieldKey.ROONTRACKTAG);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SCRIPT, (FieldKey) AsfFieldKey.SCRIPT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SECTION, (FieldKey) AsfFieldKey.SECTION);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SINGLE_DISC_TRACK_NO, (FieldKey) AsfFieldKey.SINGLE_DISC_TRACK_NO);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SONGKONG_ID, (FieldKey) AsfFieldKey.SONGKONG_ID);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SOUND_ENGINEER, (FieldKey) AsfFieldKey.SOUND_ENGINEER);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SOUND_ENGINEER_SORT, (FieldKey) AsfFieldKey.SOUND_ENGINEER_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.SUBTITLE, (FieldKey) AsfFieldKey.SUBTITLE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TAGS, (FieldKey) AsfFieldKey.TAGS);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TEMPO, (FieldKey) AsfFieldKey.TEMPO);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TIMBRE, (FieldKey) AsfFieldKey.TIMBRE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TITLE, (FieldKey) AsfFieldKey.TITLE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TITLE_MOVEMENT, (FieldKey) AsfFieldKey.TITLE_MOVEMENT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TITLE_SORT, (FieldKey) AsfFieldKey.TITLE_SORT);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TONALITY, (FieldKey) AsfFieldKey.TONALITY);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TRACK, (FieldKey) AsfFieldKey.TRACK);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.TRACK_TOTAL, (FieldKey) AsfFieldKey.TRACK_TOTAL);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_BANDCAMP_ARTIST_SITE, (FieldKey) AsfFieldKey.URL_BANDCAMP_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_BANDCAMP_RELEASE_SITE, (FieldKey) AsfFieldKey.URL_BANDCAMP_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_DISCOGS_ARTIST_SITE, (FieldKey) AsfFieldKey.URL_DISCOGS_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_DISCOGS_RELEASE_SITE, (FieldKey) AsfFieldKey.URL_DISCOGS_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_LYRICS_SITE, (FieldKey) AsfFieldKey.URL_LYRICS_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_OFFICIAL_ARTIST_SITE, (FieldKey) AsfFieldKey.URL_OFFICIAL_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_OFFICIAL_RELEASE_SITE, (FieldKey) AsfFieldKey.URL_OFFICIAL_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_WIKIPEDIA_ARTIST_SITE, (FieldKey) AsfFieldKey.URL_WIKIPEDIA_ARTIST_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.URL_WIKIPEDIA_RELEASE_SITE, (FieldKey) AsfFieldKey.URL_WIKIPEDIA_RELEASE_SITE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.VERSION, (FieldKey) AsfFieldKey.VERSION);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.WORK, (FieldKey) AsfFieldKey.WORK);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.WORK_TYPE, (FieldKey) AsfFieldKey.WORK_TYPE);
        enumMap.put((EnumMap<FieldKey, AsfFieldKey>) FieldKey.YEAR, (FieldKey) AsfFieldKey.YEAR);
        HashSet hashSet = new HashSet();
        COMMON_FIELDS = hashSet;
        hashSet.add(AsfFieldKey.ALBUM);
        hashSet.add(AsfFieldKey.AUTHOR);
        hashSet.add(AsfFieldKey.DESCRIPTION);
        hashSet.add(AsfFieldKey.GENRE);
        hashSet.add(AsfFieldKey.TITLE);
        hashSet.add(AsfFieldKey.TRACK);
        hashSet.add(AsfFieldKey.YEAR);
    }

    public AsfTag() {
        this(false);
    }

    public AsfTag(boolean z) {
        this.copyFields = z;
    }

    public AsfTag(Tag tag, boolean z) throws UnsupportedEncodingException {
        this(z);
        copyFrom(tag);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) {
        if (isValidField(tagField)) {
            if (AsfFieldKey.isMultiValued(tagField.getId())) {
                super.addField(copyFrom(tagField));
            } else {
                super.setField(copyFrom(tagField));
            }
        }
    }

    public void addCopyright(String str) {
        addField(createCopyrightField(str));
    }

    public void addRating(String str) {
        addField(createRatingField(str));
    }

    private void copyFrom(Tag tag) {
        Iterator<TagField> fields = tag.getFields();
        while (fields.hasNext()) {
            TagField tagFieldCopyFrom = copyFrom(fields.next());
            if (tagFieldCopyFrom != null) {
                super.addField(tagFieldCopyFrom);
            }
        }
    }

    private TagField copyFrom(TagField tagField) {
        TagField asfTagField;
        if (!isCopyingFields()) {
            return tagField;
        }
        if (tagField instanceof AsfTagField) {
            try {
                asfTagField = (TagField) ((AsfTagField) tagField).clone();
            } catch (CloneNotSupportedException unused) {
                asfTagField = new AsfTagField(((AsfTagField) tagField).getDescriptor());
            }
            return asfTagField;
        }
        if (tagField instanceof TagTextField) {
            return new AsfTagTextField(tagField.getId(), ((TagTextField) tagField).getContent());
        }
        throw new RuntimeException("Unknown Asf Tag Field class:" + tagField.getClass());
    }

    @Override // org.jaudiotagger.tag.Tag
    public AsfTagCoverField createField(Artwork artwork) {
        return new AsfTagCoverField(artwork.getBinaryData(), artwork.getPictureType(), artwork.getDescription(), artwork.getMimeType());
    }

    public AsfTagCoverField createArtworkField(byte[] bArr) {
        return new AsfTagCoverField(bArr, PictureTypes.DEFAULT_ID.intValue(), null, null);
    }

    public AsfTagTextField createCopyrightField(String str) {
        return new AsfTagTextField(AsfFieldKey.COPYRIGHT, str);
    }

    public AsfTagTextField createRatingField(String str) {
        return new AsfTagTextField(AsfFieldKey.RATING, str);
    }

    /* renamed from: org.jaudiotagger.tag.asf.AsfTag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$asf$AsfFieldKey;

        static {
            int[] iArr = new int[AsfFieldKey.values().length];
            $SwitchMap$org$jaudiotagger$tag$asf$AsfFieldKey = iArr;
            try {
                iArr[AsfFieldKey.COVER_ART.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$asf$AsfFieldKey[AsfFieldKey.BANNER_IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public AsfTagTextField createField(AsfFieldKey asfFieldKey, String str) {
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (asfFieldKey == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$asf$AsfFieldKey[asfFieldKey.ordinal()];
        if (i == 1) {
            throw new UnsupportedOperationException("Cover Art cannot be created using this method");
        }
        if (i == 2) {
            throw new UnsupportedOperationException("Banner Image cannot be created using this method");
        }
        return new AsfTagTextField(asfFieldKey.getFieldName(), str);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public AsfTagTextField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        if (strArr == null || strArr[0] == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        if (fieldKey == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        AsfFieldKey asfFieldKey = tagFieldToAsfField.get(fieldKey);
        if (asfFieldKey == null) {
            throw new KeyNotFoundException(fieldKey.toString());
        }
        return createField(asfFieldKey, strArr[0]);
    }

    public void deleteField(AsfFieldKey asfFieldKey) {
        super.deleteField(asfFieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        super.deleteField(tagFieldToAsfField.get(fieldKey).getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        AsfFieldKey asfFieldKey = tagFieldToAsfField.get(fieldKey);
        if (asfFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFields(asfFieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        AsfFieldKey asfFieldKey = tagFieldToAsfField.get(fieldKey);
        if (asfFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getAll(asfFieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() throws KeyNotFoundException {
        List<TagField> fields = getFields(FieldKey.COVER_ART);
        ArrayList arrayList = new ArrayList(fields.size());
        Iterator<TagField> it = fields.iterator();
        while (it.hasNext()) {
            AsfTagCoverField asfTagCoverField = (AsfTagCoverField) it.next();
            Artwork artwork = ArtworkFactory.getNew();
            artwork.setBinaryData(asfTagCoverField.getRawImageData());
            artwork.setMimeType(asfTagCoverField.getMimeType());
            artwork.setDescription(asfTagCoverField.getDescription());
            artwork.setPictureType(asfTagCoverField.getPictureType());
            arrayList.add(artwork);
        }
        return arrayList;
    }

    public Iterator<AsfTagField> getAsfFields() {
        if (!isCopyingFields()) {
            throw new IllegalStateException("Since the field conversion is not enabled, this method cannot be executed");
        }
        return new AsfFieldIterator(getFields());
    }

    public List<TagField> getCopyright() {
        return getFields(AsfFieldKey.COPYRIGHT.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    public String getFirst(AsfFieldKey asfFieldKey) throws KeyNotFoundException {
        if (asfFieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getFirst(asfFieldKey.getFieldName());
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        return super.getItem(tagFieldToAsfField.get(fieldKey).getFieldName(), i);
    }

    public String getFirstCopyright() {
        return getFirst(AsfFieldKey.COPYRIGHT.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public AsfTagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        return (AsfTagField) super.getFirstField(tagFieldToAsfField.get(fieldKey).getFieldName());
    }

    public String getFirstRating() {
        return getFirst(AsfFieldKey.RATING.getFieldName());
    }

    public List<TagField> getRating() {
        return getFields(AsfFieldKey.RATING.getFieldName());
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag
    protected boolean isAllowedEncoding(Charset charset) {
        return AsfHeader.ASF_CHARSET.name().equals(charset);
    }

    public boolean isCopyingFields() {
        return this.copyFields;
    }

    private boolean isValidField(TagField tagField) {
        if (tagField != null && (tagField instanceof AsfTagField)) {
            return !tagField.isEmpty();
        }
        return false;
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) {
        if (isValidField(tagField)) {
            super.setField(copyFrom(tagField));
        }
    }

    public void setCopyright(String str) {
        setField(createCopyrightField(str));
    }

    public void setRating(String str) {
        setField(createRatingField(str));
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return getFields(tagFieldToAsfField.get(fieldKey).getFieldName()).size() != 0;
    }

    public boolean hasField(AsfFieldKey asfFieldKey) {
        return getFields(asfFieldKey.getFieldName()).size() != 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return createField(FieldKey.IS_COMPILATION, String.valueOf(z));
    }
}
