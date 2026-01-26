package org.jaudiotagger.tag.reference;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.media3.extractor.ts.TsExtractor;
import com.facebook.imagepipeline.common.RotationOptions;
import com.google.common.net.HttpHeaders;
import com.umeng.ccg.c;
import com.umeng.commonsdk.statistics.UMErrorCode;
import java.util.LinkedHashMap;
import java.util.Map;
import org.jaudiotagger.audio.mp3.VbriFrame;
import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;
import org.mozilla.universalchardet.prober.contextanalysis.EUCJPContextAnalysis;

/* loaded from: classes3.dex */
public class GenreTypes extends AbstractIntStringValuePair {
    private static final int MAX_GENRE_ID = 191;
    private static final int MAX_STANDARD_GENRE_ID = 125;
    private static GenreTypes genreTypes;
    private Map<String, Integer> nameToIdMap;

    public static int getMaxGenreId() {
        return MAX_GENRE_ID;
    }

    public static int getMaxStandardGenreId() {
        return MAX_STANDARD_GENRE_ID;
    }

    public static GenreTypes getInstanceOf() {
        if (genreTypes == null) {
            genreTypes = new GenreTypes();
        }
        return genreTypes;
    }

    private GenreTypes() {
        this.idToValue.put(0, "Blues");
        this.idToValue.put(1, "Classic Rock");
        this.idToValue.put(2, FrameBodyTXXX.COUNTRY);
        this.idToValue.put(3, "Dance");
        this.idToValue.put(4, "Disco");
        this.idToValue.put(5, "Funk");
        this.idToValue.put(6, "Grunge");
        this.idToValue.put(7, "Hip-Hop");
        this.idToValue.put(8, "Jazz");
        this.idToValue.put(9, "Metal");
        this.idToValue.put(10, "New Age");
        this.idToValue.put(11, "Oldies");
        this.idToValue.put(12, "Other");
        this.idToValue.put(13, "Pop");
        this.idToValue.put(14, "R&B");
        this.idToValue.put(15, "Rap");
        this.idToValue.put(16, "Reggae");
        this.idToValue.put(17, "Rock");
        this.idToValue.put(18, "Techno");
        this.idToValue.put(19, "Industrial");
        this.idToValue.put(20, "Alternative");
        this.idToValue.put(21, "Ska");
        this.idToValue.put(22, "Death Metal");
        this.idToValue.put(23, "Pranks");
        this.idToValue.put(24, "Soundtrack");
        this.idToValue.put(25, "Euro-Techno");
        this.idToValue.put(26, "Ambient");
        this.idToValue.put(27, "Trip-Hop");
        this.idToValue.put(28, "Vocal");
        this.idToValue.put(29, "Jazz+Funk");
        this.idToValue.put(30, "Fusion");
        this.idToValue.put(31, "Trance");
        this.idToValue.put(32, "Classical");
        this.idToValue.put(33, "Instrumental");
        this.idToValue.put(34, "Acid");
        this.idToValue.put(35, "House");
        this.idToValue.put(36, "Game");
        this.idToValue.put(37, "Sound Clip");
        this.idToValue.put(38, "Gospel");
        this.idToValue.put(39, "Noise");
        this.idToValue.put(40, "AlternRock");
        this.idToValue.put(41, "Bass");
        this.idToValue.put(42, "Soul");
        this.idToValue.put(43, "Punk");
        this.idToValue.put(44, "Space");
        this.idToValue.put(45, "Meditative");
        this.idToValue.put(46, "Instrumental Pop");
        this.idToValue.put(47, "Instrumental Rock");
        this.idToValue.put(48, "Ethnic");
        this.idToValue.put(49, "Gothic");
        this.idToValue.put(50, "Darkwave");
        this.idToValue.put(51, "Techno-Industrial");
        this.idToValue.put(52, "Electronic");
        this.idToValue.put(53, "Pop-Folk");
        this.idToValue.put(54, "Eurodance");
        this.idToValue.put(55, "Dream");
        this.idToValue.put(56, "Southern Rock");
        this.idToValue.put(57, "Comedy");
        this.idToValue.put(58, "Cult");
        this.idToValue.put(59, "Gangsta");
        this.idToValue.put(60, "Top 40");
        this.idToValue.put(61, "Christian Rap");
        this.idToValue.put(62, "Pop/Funk");
        this.idToValue.put(63, "Jungle");
        this.idToValue.put(64, "Native American");
        this.idToValue.put(65, "Cabaret");
        this.idToValue.put(66, "New Wave");
        this.idToValue.put(67, "Psychadelic");
        this.idToValue.put(68, "Rave");
        this.idToValue.put(69, "Showtunes");
        this.idToValue.put(70, HttpHeaders.TRAILER);
        this.idToValue.put(71, "Lo-Fi");
        this.idToValue.put(72, "Tribal");
        this.idToValue.put(73, "Acid Punk");
        this.idToValue.put(74, "Acid Jazz");
        this.idToValue.put(75, "Polka");
        this.idToValue.put(76, "Retro");
        this.idToValue.put(77, "Musical");
        this.idToValue.put(78, "Rock & Roll");
        this.idToValue.put(79, "Hard Rock");
        this.idToValue.put(80, "Folk");
        this.idToValue.put(81, "Folk-Rock");
        this.idToValue.put(82, "National Folk");
        this.idToValue.put(83, "Swing");
        this.idToValue.put(84, "Fast Fusion");
        this.idToValue.put(85, "Bebob");
        this.idToValue.put(86, "Latin");
        this.idToValue.put(87, "Revival");
        this.idToValue.put(88, "Celtic");
        this.idToValue.put(89, "Bluegrass");
        this.idToValue.put(90, "Avantgarde");
        this.idToValue.put(91, "Gothic Rock");
        this.idToValue.put(92, "Progressive Rock");
        this.idToValue.put(93, "Psychedelic Rock");
        this.idToValue.put(94, "Symphonic Rock");
        this.idToValue.put(95, "Slow Rock");
        this.idToValue.put(96, "Big Band");
        this.idToValue.put(97, "Chorus");
        this.idToValue.put(98, "Easy Listening");
        this.idToValue.put(99, "Acoustic");
        this.idToValue.put(100, "Humour");
        this.idToValue.put(101, "Speech");
        this.idToValue.put(102, "Chanson");
        this.idToValue.put(Integer.valueOf(c.c), "Opera");
        this.idToValue.put(104, "Chamber Music");
        this.idToValue.put(Integer.valueOf(c.e), "Sonata");
        this.idToValue.put(Integer.valueOf(c.f), "Symphony");
        this.idToValue.put(Integer.valueOf(c.g), "Booty Bass");
        this.idToValue.put(Integer.valueOf(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR), "Primus");
        this.idToValue.put(Integer.valueOf(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY), "Porn Groove");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_JSON_FAILED), "Satire");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_CREATE_FAILED), "Slow Jam");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_DEFLATE_FAILED), "Club");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_RAW_OVERSIZE), "Tango");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_FILE_OVERSIZE), "Samba");
        this.idToValue.put(115, "Folklore");
        this.idToValue.put(116, "Ballad");
        this.idToValue.put(117, "Power Ballad");
        this.idToValue.put(118, "Rhythmic Soul");
        this.idToValue.put(119, "Freestyle");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_NOT_MAINPROCESS), "Duet");
        this.idToValue.put(Integer.valueOf(UMErrorCode.E_UM_BE_EMPTY_URL_PATH), "Punk Rock");
        this.idToValue.put(122, "Drum Solo");
        this.idToValue.put(123, "Acapella");
        this.idToValue.put(124, "Euro-House");
        this.idToValue.put(Integer.valueOf(MAX_STANDARD_GENRE_ID), "Dance Hall");
        this.idToValue.put(126, "Goa");
        this.idToValue.put(127, "Drum & Bass");
        this.idToValue.put(128, "Club-House");
        this.idToValue.put(129, "Hardcore");
        this.idToValue.put(130, "Terror");
        this.idToValue.put(131, "Indie");
        this.idToValue.put(132, "BritPop");
        this.idToValue.put(133, "Negerpunk");
        this.idToValue.put(134, "Polsk Punk");
        this.idToValue.put(135, "Beat");
        this.idToValue.put(Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS_HD), "Christian Gangsta Rap");
        this.idToValue.put(137, "Heavy Metal");
        this.idToValue.put(138, "Black Metal");
        this.idToValue.put(Integer.valueOf(TsExtractor.TS_STREAM_TYPE_DTS_UHD), "Crossover");
        this.idToValue.put(140, "Contemporary Christian");
        this.idToValue.put(141, "Christian Rock");
        this.idToValue.put(Integer.valueOf(EUCJPContextAnalysis.SINGLE_SHIFT_2), "Merengue");
        this.idToValue.put(Integer.valueOf(EUCJPContextAnalysis.SINGLE_SHIFT_3), "Salsa");
        this.idToValue.put(144, "Thrash Metal");
        this.idToValue.put(145, "Anime");
        this.idToValue.put(146, "JPop");
        this.idToValue.put(147, "SynthPop");
        this.idToValue.put(148, "Abstract");
        this.idToValue.put(149, "Art Rock");
        this.idToValue.put(150, "Baroque");
        this.idToValue.put(151, "Bhangra");
        this.idToValue.put(152, "Big Beat");
        this.idToValue.put(153, "Breakbeat");
        this.idToValue.put(154, "Chillout");
        this.idToValue.put(155, "Downtempo");
        this.idToValue.put(Integer.valueOf(VbriFrame.MAX_BUFFER_SIZE_NEEDED_TO_READ_VBRI), "Dub");
        this.idToValue.put(157, "EBM");
        this.idToValue.put(158, "Eclectic");
        this.idToValue.put(159, "Electro");
        this.idToValue.put(160, "Electroclash");
        this.idToValue.put(161, "Emo");
        this.idToValue.put(162, "Experimental");
        this.idToValue.put(163, "Garage");
        this.idToValue.put(164, "Global");
        this.idToValue.put(165, "IDM");
        this.idToValue.put(166, "Illbient");
        this.idToValue.put(167, "Industro-Goth");
        this.idToValue.put(168, "Jam Band");
        this.idToValue.put(169, "Krautrock");
        this.idToValue.put(170, "Leftfield");
        this.idToValue.put(171, "Lounge");
        this.idToValue.put(172, "Math Rock");
        this.idToValue.put(173, "New Romantic");
        this.idToValue.put(174, "Nu-Breakz");
        this.idToValue.put(175, "Post-Punk");
        this.idToValue.put(176, "Post-Rock");
        this.idToValue.put(177, "Psytrance");
        this.idToValue.put(178, "Shoegaze");
        this.idToValue.put(179, "Space Rock");
        this.idToValue.put(Integer.valueOf(RotationOptions.ROTATE_180), "Trop Rock");
        this.idToValue.put(181, "World Music");
        this.idToValue.put(182, "Neoclassical");
        this.idToValue.put(183, "Audiobook");
        this.idToValue.put(184, "Audio Theatre");
        this.idToValue.put(185, "Neue Deutsche Welle");
        this.idToValue.put(186, "Podcast");
        this.idToValue.put(187, "Indie Rock");
        this.idToValue.put(188, "G-Funk");
        this.idToValue.put(189, "Dubstep");
        this.idToValue.put(190, "Garage Rock");
        this.idToValue.put(Integer.valueOf(MAX_GENRE_ID), "Psybient");
        createMaps();
        this.nameToIdMap = new LinkedHashMap(this.idToValue.size());
        for (Map.Entry entry : this.idToValue.entrySet()) {
            this.nameToIdMap.put(((String) entry.getValue()).toLowerCase(), (Integer) entry.getKey());
        }
    }

    public Integer getIdForName(String str) {
        return this.nameToIdMap.get(str.toLowerCase());
    }
}
