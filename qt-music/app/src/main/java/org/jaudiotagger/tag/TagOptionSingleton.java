package org.jaudiotagger.tag;

import android.support.v4.media.session.PlaybackStateCompat;
import com.facebook.hermes.intl.Constants;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import org.jaudiotagger.audio.wav.WavOptions;
import org.jaudiotagger.audio.wav.WavSaveOptions;
import org.jaudiotagger.audio.wav.WavSaveOrder;
import org.jaudiotagger.tag.id3.ID3v22Frames;
import org.jaudiotagger.tag.id3.ID3v23Frames;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTIPL;
import org.jaudiotagger.tag.id3.framebody.ID3v24FrameBody;
import org.jaudiotagger.tag.lyrics3.Lyrics3v2Fields;
import org.jaudiotagger.tag.options.PadNumberOption;
import org.jaudiotagger.tag.reference.GenreTypes;
import org.jaudiotagger.tag.reference.ID3V2Version;
import org.jaudiotagger.tag.reference.Languages;
import org.jaudiotagger.tag.vorbiscomment.VorbisAlbumArtistReadOptions;
import org.jaudiotagger.tag.vorbiscomment.VorbisAlbumArtistSaveOptions;

/* loaded from: classes3.dex */
public class TagOptionSingleton {
    private static HashMap<String, TagOptionSingleton> tagOptionTable = new HashMap<>();
    private static String DEFAULT = Constants.COLLATION_DEFAULT;
    private static String defaultOptions = Constants.COLLATION_DEFAULT;
    private boolean isWriteWavForTwonky = false;
    private WavOptions wavOptions = WavOptions.READ_ID3_ONLY;
    private WavSaveOptions wavSaveOptions = WavSaveOptions.SAVE_BOTH;
    private WavSaveOrder wavSaveOrder = WavSaveOrder.INFO_THEN_ID3;
    private VorbisAlbumArtistSaveOptions vorbisAlbumArtistSaveOptions = VorbisAlbumArtistSaveOptions.WRITE_ALBUMARTIST;
    private VorbisAlbumArtistReadOptions vorbisAlbumArtistReadOptions = VorbisAlbumArtistReadOptions.READ_ALBUMARTIST_THEN_JRIVER;
    private HashMap<Class<? extends ID3v24FrameBody>, LinkedList<String>> keywordMap = new HashMap<>();
    private HashMap<String, Boolean> lyrics3SaveFieldMap = new HashMap<>();
    private HashMap<String, String> parenthesisMap = new HashMap<>();
    private HashMap<String, String> replaceWordMap = new HashMap<>();
    private String language = Languages.DEFAULT_ID;
    private boolean filenameTagSave = false;
    private boolean id3v1Save = true;
    private boolean id3v1SaveAlbum = true;
    private boolean id3v1SaveArtist = true;
    private boolean id3v1SaveComment = true;
    private boolean id3v1SaveGenre = true;
    private boolean id3v1SaveTitle = true;
    private boolean id3v1SaveTrack = true;
    private boolean id3v1SaveYear = true;
    private boolean id3v2PaddingCopyTag = true;
    private boolean id3v2PaddingWillShorten = false;
    private boolean id3v2Save = true;
    private boolean id3v2ITunes12_6WorkGroupingMode = false;
    private boolean lyrics3KeepEmptyFieldIfRead = false;
    private boolean lyrics3Save = true;
    private boolean lyrics3SaveEmptyField = false;
    private boolean originalSavedAfterAdjustingID3v2Padding = true;
    private byte timeStampFormat = 2;
    private int numberMP3SyncFrame = 3;
    private boolean unsyncTags = false;
    private boolean removeTrailingTerminatorOnWrite = true;
    private byte id3v23DefaultTextEncoding = 0;
    private byte id3v24DefaultTextEncoding = 0;
    private byte id3v24UnicodeTextEncoding = 1;
    private boolean resetTextEncodingForExistingFrames = false;
    private boolean truncateTextWithoutErrors = false;
    private boolean padNumbers = false;
    private PadNumberOption padNumberTotalLength = PadNumberOption.PAD_ONE_ZERO;
    private boolean isAPICDescriptionITunesCompatible = false;
    private boolean isEncodeUTF16BomAsLittleEndian = true;
    private int playerCompatability = -1;
    private long writeChunkSize = PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED;
    private boolean isWriteMp4GenresAsText = false;
    private boolean isWriteMp3GenresAsText = false;
    private ID3V2Version id3v2Version = ID3V2Version.ID3_V23;
    private boolean checkIsWritable = false;
    private boolean preserveFileIdentity = true;
    private Charset overrideCharset = null;
    private boolean isOverrideCharsetForInfo = false;
    private boolean isOverrideCharsetForId3 = false;
    private EnumSet<FieldKey> overrideCharsetFields = EnumSet.noneOf(FieldKey.class);

    public void setWavOptions(WavOptions wavOptions) {
        this.wavOptions = wavOptions;
    }

    public WavOptions getWavOptions() {
        return this.wavOptions;
    }

    public void setWavSaveOptions(WavSaveOptions wavSaveOptions) {
        this.wavSaveOptions = wavSaveOptions;
    }

    public WavSaveOptions getWavSaveOptions() {
        return this.wavSaveOptions;
    }

    public void setWavSaveOrder(WavSaveOrder wavSaveOrder) {
        this.wavSaveOrder = wavSaveOrder;
    }

    public WavSaveOrder getWavSaveOrder() {
        return this.wavSaveOrder;
    }

    public void setVorbisAlbumArtistSaveOptions(VorbisAlbumArtistSaveOptions vorbisAlbumArtistSaveOptions) {
        this.vorbisAlbumArtistSaveOptions = vorbisAlbumArtistSaveOptions;
    }

    public VorbisAlbumArtistSaveOptions getVorbisAlbumArtistSaveOptions() {
        return this.vorbisAlbumArtistSaveOptions;
    }

    public void setVorbisAlbumArtistReadOptions(VorbisAlbumArtistReadOptions vorbisAlbumArtistReadOptions) {
        this.vorbisAlbumArtistReadOptions = vorbisAlbumArtistReadOptions;
    }

    public VorbisAlbumArtistReadOptions getVorbisAlbumArtisReadOptions() {
        return this.vorbisAlbumArtistReadOptions;
    }

    private TagOptionSingleton() {
        setToDefault();
    }

    public static TagOptionSingleton getInstance() {
        return getInstance(defaultOptions);
    }

    public static TagOptionSingleton getInstance(String str) {
        TagOptionSingleton tagOptionSingleton = tagOptionTable.get(str);
        if (tagOptionSingleton != null) {
            return tagOptionSingleton;
        }
        TagOptionSingleton tagOptionSingleton2 = new TagOptionSingleton();
        tagOptionTable.put(str, tagOptionSingleton2);
        return tagOptionSingleton2;
    }

    public void setFilenameTagSave(boolean z) {
        this.filenameTagSave = z;
    }

    public boolean isFilenameTagSave() {
        return this.filenameTagSave;
    }

    public void setID3V2Version(ID3V2Version iD3V2Version) {
        this.id3v2Version = iD3V2Version;
    }

    public ID3V2Version getID3V2Version() {
        return this.id3v2Version;
    }

    public void setInstanceKey(String str) {
        defaultOptions = str;
    }

    public static String getInstanceKey() {
        return defaultOptions;
    }

    public void setId3v1Save(boolean z) {
        this.id3v1Save = z;
    }

    public boolean isId3v1Save() {
        return this.id3v1Save;
    }

    public void setId3v1SaveAlbum(boolean z) {
        this.id3v1SaveAlbum = z;
    }

    public boolean isId3v1SaveAlbum() {
        return this.id3v1SaveAlbum;
    }

    public void setId3v1SaveArtist(boolean z) {
        this.id3v1SaveArtist = z;
    }

    public boolean isId3v1SaveArtist() {
        return this.id3v1SaveArtist;
    }

    public void setId3v1SaveComment(boolean z) {
        this.id3v1SaveComment = z;
    }

    public boolean isId3v1SaveComment() {
        return this.id3v1SaveComment;
    }

    public void setId3v1SaveGenre(boolean z) {
        this.id3v1SaveGenre = z;
    }

    public boolean isId3v1SaveGenre() {
        return this.id3v1SaveGenre;
    }

    public void setId3v1SaveTitle(boolean z) {
        this.id3v1SaveTitle = z;
    }

    public boolean isId3v1SaveTitle() {
        return this.id3v1SaveTitle;
    }

    public void setId3v1SaveTrack(boolean z) {
        this.id3v1SaveTrack = z;
    }

    public boolean isId3v1SaveTrack() {
        return this.id3v1SaveTrack;
    }

    public void setId3v1SaveYear(boolean z) {
        this.id3v1SaveYear = z;
    }

    public boolean isId3v1SaveYear() {
        return this.id3v1SaveYear;
    }

    public void setId3v2PaddingCopyTag(boolean z) {
        this.id3v2PaddingCopyTag = z;
    }

    public boolean isId3v2PaddingCopyTag() {
        return this.id3v2PaddingCopyTag;
    }

    public void setId3v2PaddingWillShorten(boolean z) {
        this.id3v2PaddingWillShorten = z;
    }

    public boolean isId3v2PaddingWillShorten() {
        return this.id3v2PaddingWillShorten;
    }

    public void setId3v2Save(boolean z) {
        this.id3v2Save = z;
    }

    public boolean isId3v2Save() {
        return this.id3v2Save;
    }

    public boolean isId3v2ITunes12_6WorkGroupingMode() {
        return this.id3v2ITunes12_6WorkGroupingMode;
    }

    public void setId3v2ITunes12_6WorkGroupingMode(boolean z) {
        if (this.id3v2ITunes12_6WorkGroupingMode != z) {
            ID3v22Frames.getInstanceOf().setITunes12_6WorkGroupingMode(z);
            ID3v23Frames.getInstanceOf().setITunes12_6WorkGroupingMode(z);
            ID3v24Frames.getInstanceOf().setITunes12_6WorkGroupingMode(z);
            this.id3v2ITunes12_6WorkGroupingMode = z;
        }
    }

    public Iterator<Class<? extends ID3v24FrameBody>> getKeywordIterator() {
        return this.keywordMap.keySet().iterator();
    }

    public Iterator<String> getKeywordListIterator(Class<? extends ID3v24FrameBody> cls) {
        return this.keywordMap.get(cls).iterator();
    }

    public void setLanguage(String str) {
        if (Languages.getInstanceOf().getIdToValueMap().containsKey(str)) {
            this.language = str;
        }
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLyrics3KeepEmptyFieldIfRead(boolean z) {
        this.lyrics3KeepEmptyFieldIfRead = z;
    }

    public boolean isLyrics3KeepEmptyFieldIfRead() {
        return this.lyrics3KeepEmptyFieldIfRead;
    }

    public void setLyrics3Save(boolean z) {
        this.lyrics3Save = z;
    }

    public boolean isLyrics3Save() {
        return this.lyrics3Save;
    }

    public void setLyrics3SaveEmptyField(boolean z) {
        this.lyrics3SaveEmptyField = z;
    }

    public boolean isLyrics3SaveEmptyField() {
        return this.lyrics3SaveEmptyField;
    }

    public void setLyrics3SaveField(String str, boolean z) {
        this.lyrics3SaveFieldMap.put(str, Boolean.valueOf(z));
    }

    public boolean getLyrics3SaveField(String str) {
        return this.lyrics3SaveFieldMap.get(str).booleanValue();
    }

    public HashMap<String, Boolean> getLyrics3SaveFieldMap() {
        return this.lyrics3SaveFieldMap;
    }

    public String getNewReplaceWord(String str) {
        return this.replaceWordMap.get(str);
    }

    public void setNumberMP3SyncFrame(int i) {
        this.numberMP3SyncFrame = i;
    }

    public int getNumberMP3SyncFrame() {
        return this.numberMP3SyncFrame;
    }

    public Iterator<String> getOldReplaceWordIterator() {
        return this.replaceWordMap.keySet().iterator();
    }

    public boolean isOpenParenthesis(String str) {
        return this.parenthesisMap.containsKey(str);
    }

    public Iterator<String> getOpenParenthesisIterator() {
        return this.parenthesisMap.keySet().iterator();
    }

    public void setOriginalSavedAfterAdjustingID3v2Padding(boolean z) {
        this.originalSavedAfterAdjustingID3v2Padding = z;
    }

    public boolean isOriginalSavedAfterAdjustingID3v2Padding() {
        return this.originalSavedAfterAdjustingID3v2Padding;
    }

    public void setTimeStampFormat(byte b) {
        if (b == 1 || b == 2) {
            this.timeStampFormat = b;
        }
    }

    public byte getTimeStampFormat() {
        return this.timeStampFormat;
    }

    public void setToDefault() {
        this.isWriteWavForTwonky = false;
        this.wavOptions = WavOptions.READ_ID3_UNLESS_ONLY_INFO;
        this.wavSaveOptions = WavSaveOptions.SAVE_BOTH;
        this.keywordMap = new HashMap<>();
        this.filenameTagSave = false;
        this.id3v1Save = true;
        this.id3v1SaveAlbum = true;
        this.id3v1SaveArtist = true;
        this.id3v1SaveComment = true;
        this.id3v1SaveGenre = true;
        this.id3v1SaveTitle = true;
        this.id3v1SaveTrack = true;
        this.id3v1SaveYear = true;
        this.id3v2PaddingCopyTag = true;
        this.id3v2PaddingWillShorten = false;
        this.id3v2Save = true;
        this.language = Languages.DEFAULT_ID;
        this.lyrics3KeepEmptyFieldIfRead = false;
        this.lyrics3Save = true;
        this.lyrics3SaveEmptyField = false;
        this.lyrics3SaveFieldMap = new HashMap<>();
        this.numberMP3SyncFrame = 3;
        this.parenthesisMap = new HashMap<>();
        this.replaceWordMap = new HashMap<>();
        this.timeStampFormat = (byte) 2;
        this.unsyncTags = false;
        this.removeTrailingTerminatorOnWrite = true;
        this.id3v23DefaultTextEncoding = (byte) 0;
        this.id3v24DefaultTextEncoding = (byte) 0;
        this.id3v24UnicodeTextEncoding = (byte) 1;
        this.resetTextEncodingForExistingFrames = false;
        this.truncateTextWithoutErrors = false;
        this.padNumbers = false;
        this.isAPICDescriptionITunesCompatible = false;
        this.isEncodeUTF16BomAsLittleEndian = true;
        this.writeChunkSize = DashMediaSource.MIN_LIVE_DEFAULT_START_POSITION_US;
        this.isWriteMp4GenresAsText = false;
        this.padNumberTotalLength = PadNumberOption.PAD_ONE_ZERO;
        this.id3v2Version = ID3V2Version.ID3_V23;
        this.checkIsWritable = false;
        this.preserveFileIdentity = true;
        this.overrideCharset = null;
        this.isOverrideCharsetForInfo = false;
        this.isOverrideCharsetForId3 = false;
        this.overrideCharsetFields = EnumSet.noneOf(FieldKey.class);
        Iterator<String> it = Lyrics3v2Fields.getInstanceOf().getIdToValueMap().keySet().iterator();
        while (it.hasNext()) {
            this.lyrics3SaveFieldMap.put(it.next(), true);
        }
        try {
            addKeyword(FrameBodyCOMM.class, "ultimix");
            addKeyword(FrameBodyCOMM.class, "dance");
            addKeyword(FrameBodyCOMM.class, "mix");
            addKeyword(FrameBodyCOMM.class, "remix");
            addKeyword(FrameBodyCOMM.class, "rmx");
            addKeyword(FrameBodyCOMM.class, "live");
            addKeyword(FrameBodyCOMM.class, "cover");
            addKeyword(FrameBodyCOMM.class, "soundtrack");
            addKeyword(FrameBodyCOMM.class, "version");
            addKeyword(FrameBodyCOMM.class, "acoustic");
            addKeyword(FrameBodyCOMM.class, "original");
            addKeyword(FrameBodyCOMM.class, "cd");
            addKeyword(FrameBodyCOMM.class, "extended");
            addKeyword(FrameBodyCOMM.class, "vocal");
            addKeyword(FrameBodyCOMM.class, "unplugged");
            addKeyword(FrameBodyCOMM.class, "acapella");
            addKeyword(FrameBodyCOMM.class, "edit");
            addKeyword(FrameBodyCOMM.class, "radio");
            addKeyword(FrameBodyCOMM.class, "original");
            addKeyword(FrameBodyCOMM.class, "album");
            addKeyword(FrameBodyCOMM.class, "studio");
            addKeyword(FrameBodyCOMM.class, "instrumental");
            addKeyword(FrameBodyCOMM.class, "unedited");
            addKeyword(FrameBodyCOMM.class, "karoke");
            addKeyword(FrameBodyCOMM.class, "quality");
            addKeyword(FrameBodyCOMM.class, "uncensored");
            addKeyword(FrameBodyCOMM.class, "clean");
            addKeyword(FrameBodyCOMM.class, "dirty");
            addKeyword(FrameBodyTIPL.class, "f.");
            addKeyword(FrameBodyTIPL.class, "feat");
            addKeyword(FrameBodyTIPL.class, "feat.");
            addKeyword(FrameBodyTIPL.class, "featuring");
            addKeyword(FrameBodyTIPL.class, "ftng");
            addKeyword(FrameBodyTIPL.class, "ftng.");
            addKeyword(FrameBodyTIPL.class, "ft.");
            addKeyword(FrameBodyTIPL.class, "ft");
            Iterator<String> it2 = GenreTypes.getInstanceOf().getValueToIdMap().keySet().iterator();
            while (it2.hasNext()) {
                addKeyword(FrameBodyCOMM.class, it2.next());
            }
            addReplaceWord("v.", "vs.");
            addReplaceWord("vs.", "vs.");
            addReplaceWord("versus", "vs.");
            addReplaceWord("f.", "feat.");
            addReplaceWord("feat", "feat.");
            addReplaceWord("featuring", "feat.");
            addReplaceWord("ftng.", "feat.");
            addReplaceWord("ftng", "feat.");
            addReplaceWord("ft.", "feat.");
            addReplaceWord("ft", "feat.");
            getKeywordListIterator(FrameBodyTIPL.class);
            addParenthesis("(", ")");
            addParenthesis("[", "]");
            addParenthesis("{", "}");
            addParenthesis("<", ">");
        } catch (TagException e) {
            throw new RuntimeException(e);
        }
    }

    public void addKeyword(Class<? extends ID3v24FrameBody> cls, String str) throws TagException {
        LinkedList<String> linkedList;
        if (!AbstractID3v2FrameBody.class.isAssignableFrom(cls)) {
            throw new TagException("Invalid class type. Must be AbstractId3v2FrameBody " + cls);
        }
        if (str == null || str.length() <= 0) {
            return;
        }
        if (!this.keywordMap.containsKey(cls)) {
            linkedList = new LinkedList<>();
            this.keywordMap.put(cls, linkedList);
        } else {
            linkedList = this.keywordMap.get(cls);
        }
        linkedList.add(str);
    }

    public void addParenthesis(String str, String str2) {
        this.parenthesisMap.put(str, str2);
    }

    public void addReplaceWord(String str, String str2) {
        this.replaceWordMap.put(str, str2);
    }

    public boolean isUnsyncTags() {
        return this.unsyncTags;
    }

    public void setUnsyncTags(boolean z) {
        this.unsyncTags = z;
    }

    public boolean isRemoveTrailingTerminatorOnWrite() {
        return this.removeTrailingTerminatorOnWrite;
    }

    public void setRemoveTrailingTerminatorOnWrite(boolean z) {
        this.removeTrailingTerminatorOnWrite = z;
    }

    public byte getId3v23DefaultTextEncoding() {
        return this.id3v23DefaultTextEncoding;
    }

    public void setId3v23DefaultTextEncoding(byte b) {
        if (b == 0 || b == 1) {
            this.id3v23DefaultTextEncoding = b;
        }
    }

    public byte getId3v24DefaultTextEncoding() {
        return this.id3v24DefaultTextEncoding;
    }

    public void setId3v24DefaultTextEncoding(byte b) {
        if (b == 0 || b == 1 || b == 2 || b == 3) {
            this.id3v24DefaultTextEncoding = b;
        }
    }

    public byte getId3v24UnicodeTextEncoding() {
        return this.id3v24UnicodeTextEncoding;
    }

    public void setId3v24UnicodeTextEncoding(byte b) {
        if (b == 1 || b == 2 || b == 3) {
            this.id3v24UnicodeTextEncoding = b;
        }
    }

    public boolean isResetTextEncodingForExistingFrames() {
        return this.resetTextEncodingForExistingFrames;
    }

    public void setResetTextEncodingForExistingFrames(boolean z) {
        this.resetTextEncodingForExistingFrames = z;
    }

    public boolean isTruncateTextWithoutErrors() {
        return this.truncateTextWithoutErrors;
    }

    public void setTruncateTextWithoutErrors(boolean z) {
        this.truncateTextWithoutErrors = z;
    }

    public boolean isPadNumbers() {
        return this.padNumbers;
    }

    public void setPadNumbers(boolean z) {
        this.padNumbers = z;
    }

    public int getPlayerCompatability() {
        return this.playerCompatability;
    }

    public void setPlayerCompatability(int i) {
        this.playerCompatability = i;
    }

    public boolean isEncodeUTF16BomAsLittleEndian() {
        return this.isEncodeUTF16BomAsLittleEndian;
    }

    public void setEncodeUTF16BomAsLittleEndian(boolean z) {
        this.isEncodeUTF16BomAsLittleEndian = z;
    }

    public long getWriteChunkSize() {
        return this.writeChunkSize;
    }

    public void setWriteChunkSize(long j) {
        this.writeChunkSize = j;
    }

    public boolean isWriteMp4GenresAsText() {
        return this.isWriteMp4GenresAsText;
    }

    public void setWriteMp4GenresAsText(boolean z) {
        this.isWriteMp4GenresAsText = z;
    }

    public boolean isWriteMp3GenresAsText() {
        return this.isWriteMp3GenresAsText;
    }

    public void setWriteMp3GenresAsText(boolean z) {
        this.isWriteMp3GenresAsText = z;
    }

    public PadNumberOption getPadNumberTotalLength() {
        return this.padNumberTotalLength;
    }

    public void setPadNumberTotalLength(PadNumberOption padNumberOption) {
        this.padNumberTotalLength = padNumberOption;
    }

    public boolean isAPICDescriptionITunesCompatible() {
        return this.isAPICDescriptionITunesCompatible;
    }

    public void setAPICDescriptionITunesCompatible(boolean z) {
        this.isAPICDescriptionITunesCompatible = z;
    }

    public boolean isCheckIsWritable() {
        return this.checkIsWritable;
    }

    public void setCheckIsWritable(boolean z) {
        this.checkIsWritable = z;
    }

    public boolean isPreserveFileIdentity() {
        return this.preserveFileIdentity;
    }

    public void setPreserveFileIdentity(boolean z) {
        this.preserveFileIdentity = z;
    }

    public boolean isWriteWavForTwonky() {
        return this.isWriteWavForTwonky;
    }

    public void setWriteWavForTwonky(boolean z) {
        this.isWriteWavForTwonky = z;
    }

    public Charset getOverrideCharset() {
        return this.overrideCharset;
    }

    public void setOverrideCharset(Charset charset) {
        this.overrideCharset = charset;
    }

    public boolean isOverrideCharsetForInfo() {
        return this.isOverrideCharsetForInfo;
    }

    public void setOverrideCharsetForInfo(boolean z) {
        this.isOverrideCharsetForInfo = z;
    }

    public boolean isOverrideCharsetForId3() {
        return this.isOverrideCharsetForId3;
    }

    public void setOverrideCharsetForId3(boolean z) {
        this.isOverrideCharsetForId3 = z;
    }

    public void addOverrideCharsetFields(FieldKey fieldKey) {
        this.overrideCharsetFields.add(fieldKey);
    }

    public EnumSet<FieldKey> getOverrideCharsetFields() {
        return this.overrideCharsetFields;
    }
}
