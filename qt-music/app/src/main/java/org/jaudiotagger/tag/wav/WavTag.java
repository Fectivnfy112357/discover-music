package org.jaudiotagger.tag.wav;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.iff.ChunkSummary;
import org.jaudiotagger.audio.wav.WavOptions;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v22Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.id3.Id3SupportingTag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.reference.ID3V2Version;

/* loaded from: classes3.dex */
public class WavTag implements Tag, Id3SupportingTag {
    private static final String NULL = "\u0000";
    private static final Logger logger = Logger.getLogger(WavTag.class.getPackage().getName());
    private AbstractID3v2Tag id3Tag;
    private WavInfoTag infoTag;
    private WavOptions wavOptions;
    private List<ChunkSummary> chunkSummaryList = new ArrayList();
    private List<ChunkSummary> metadataChunkList = new ArrayList();
    private boolean isBadChunkData = false;
    private boolean isNonStandardPadding = false;
    private boolean isIncorrectlyAlignedTag = false;
    private boolean isExistingId3Tag = false;
    private boolean isExistingInfoTag = false;

    public void addChunkSummary(ChunkSummary chunkSummary) {
        this.chunkSummaryList.add(chunkSummary);
    }

    public List<ChunkSummary> getChunkSummaryList() {
        return this.chunkSummaryList;
    }

    public void addMetadataChunkSummary(ChunkSummary chunkSummary) {
        this.metadataChunkList.add(chunkSummary);
    }

    public List<ChunkSummary> getMetadataChunkSummaryList() {
        return this.metadataChunkList;
    }

    public WavTag(WavOptions wavOptions) {
        this.wavOptions = wavOptions;
    }

    public boolean isExistingId3Tag() {
        return this.isExistingId3Tag;
    }

    public boolean isExistingInfoTag() {
        return this.isExistingInfoTag;
    }

    public WavInfoTag getInfoTag() {
        return this.infoTag;
    }

    public void setInfoTag(WavInfoTag wavInfoTag) {
        this.infoTag = wavInfoTag;
    }

    public boolean isInfoTag() {
        return this.infoTag != null;
    }

    @Override // org.jaudiotagger.tag.id3.Id3SupportingTag
    public AbstractID3v2Tag getID3Tag() {
        return this.id3Tag;
    }

    @Override // org.jaudiotagger.tag.id3.Id3SupportingTag
    public void setID3Tag(AbstractID3v2Tag abstractID3v2Tag) {
        this.id3Tag = abstractID3v2Tag;
    }

    public boolean isID3Tag() {
        return this.id3Tag != null;
    }

    @Override // org.jaudiotagger.tag.Tag
    public String toString() {
        StringBuilder sb = new StringBuilder("Chunk Summary:\n");
        Iterator<ChunkSummary> it = this.chunkSummaryList.iterator();
        while (it.hasNext()) {
            sb.append("\t" + it.next().toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (this.id3Tag != null) {
            sb.append("Wav ID3 Tag:\n");
            if (isExistingId3Tag()) {
                sb.append("\tstartLocation:" + Hex.asDecAndHex(getStartLocationInFileOfId3Chunk()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                sb.append("\tendLocation:" + Hex.asDecAndHex(getEndLocationInFileOfId3Chunk()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(this.id3Tag.toString().replace(NULL, "") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.infoTag != null) {
            sb.append(this.infoTag.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    /* renamed from: org.jaudiotagger.tag.wav.WavTag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$wav$WavOptions;

        static {
            int[] iArr = new int[WavOptions.values().length];
            $SwitchMap$org$jaudiotagger$audio$wav$WavOptions = iArr;
            try {
                iArr[WavOptions.READ_ID3_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_ID3_ONLY_AND_SYNC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_INFO_ONLY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_INFO_ONLY_AND_SYNC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_ID3_UNLESS_ONLY_INFO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_ID3_UNLESS_ONLY_INFO_AND_SYNC.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_INFO_UNLESS_ONLY_ID3.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_INFO_UNLESS_ONLY_ID3_AND_SYNC.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public Tag getActiveTag() {
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$wav$WavOptions[this.wavOptions.ordinal()]) {
            case 5:
            case 6:
                if (!isExistingId3Tag() && isExistingInfoTag()) {
                    break;
                } else {
                    break;
                }
                break;
            case 7:
            case 8:
                if (!isExistingInfoTag() && isExistingId3Tag()) {
                    break;
                } else {
                    break;
                }
                break;
        }
        return this.id3Tag;
    }

    public boolean equals(Object obj) {
        return getActiveTag().equals(obj);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) throws FieldDataInvalidException {
        getActiveTag().addField(tagField);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(String str) {
        return getActiveTag().getFields(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        return getActiveTag().getAll(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasCommonFields() {
        return getActiveTag().hasCommonFields();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        return getActiveTag() == null || getActiveTag().isEmpty();
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(fieldKey, strArr));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        addField(createField(fieldKey, strArr));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) throws FieldDataInvalidException {
        getActiveTag().setField(tagField);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        return getActiveTag().createField(fieldKey, strArr);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(String str) {
        return getActiveTag().getFirst(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        return getActiveTag().getValue(fieldKey, i);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(String str) {
        return getActiveTag().getFirstField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        return getActiveTag().getFirstField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        getActiveTag().deleteField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(String str) throws KeyNotFoundException {
        getActiveTag().deleteField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public Iterator<TagField> getFields() {
        return getActiveTag().getFields();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCount() {
        return getActiveTag().getFieldCount();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCountIncludingSubValues() {
        return getFieldCount();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean setEncoding(Charset charset) throws FieldDataInvalidException {
        return getActiveTag().setEncoding(charset);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        return getActiveTag().createField(artwork);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        return getActiveTag().getFields(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public Artwork getFirstArtwork() {
        return getActiveTag().getFirstArtwork();
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        getActiveTag().deleteArtworkField();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return getActiveTag().hasField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(String str) {
        return getActiveTag().hasField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return createField(FieldKey.IS_COMPILATION, String.valueOf(z));
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        return getActiveTag().getArtworkList();
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws FieldDataInvalidException {
        setField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        addField(createField(artwork));
    }

    public void setExistingId3Tag(boolean z) {
        this.isExistingId3Tag = z;
    }

    public void setExistingInfoTag(boolean z) {
        this.isExistingInfoTag = z;
    }

    public long getSizeOfID3TagOnly() {
        if (isExistingId3Tag()) {
            return this.id3Tag.getEndLocationInFile().longValue() - this.id3Tag.getStartLocationInFile().longValue();
        }
        return 0L;
    }

    public long getSizeOfID3TagIncludingChunkHeader() {
        if (isExistingId3Tag()) {
            return getSizeOfID3TagOnly() + 8;
        }
        return 0L;
    }

    public long getStartLocationInFileOfId3Chunk() {
        if (isExistingId3Tag()) {
            return this.id3Tag.getStartLocationInFile().longValue() - 8;
        }
        return 0L;
    }

    public long getEndLocationInFileOfId3Chunk() {
        if (isExistingId3Tag()) {
            return this.id3Tag.getEndLocationInFile().longValue();
        }
        return 0L;
    }

    public void syncToId3FromInfoIfEmpty() throws KeyNotFoundException {
        try {
            Iterator it = WavInfoTag.getSupportedKeys().iterator();
            while (it.hasNext()) {
                FieldKey fieldKey = (FieldKey) it.next();
                if (this.id3Tag.getFirst(fieldKey).isEmpty()) {
                    String first = this.infoTag.getFirst(fieldKey);
                    if (!first.isEmpty()) {
                        this.id3Tag.setField(fieldKey, stripNullTerminator(first));
                    }
                }
            }
        } catch (FieldDataInvalidException e) {
            logger.log(Level.INFO, "Couldn't sync to ID3 because the data to sync was invalid", (Throwable) e);
        }
    }

    public void syncToInfoFromId3IfEmpty() {
        try {
            Iterator it = WavInfoTag.getSupportedKeys().iterator();
            while (it.hasNext()) {
                FieldKey fieldKey = (FieldKey) it.next();
                if (this.infoTag.getFirst(fieldKey).isEmpty() && !this.id3Tag.getFirst(fieldKey).isEmpty()) {
                    this.infoTag.setField(fieldKey, addNullTerminatorIfNone(this.id3Tag.getFirst(fieldKey)));
                }
            }
        } catch (FieldDataInvalidException e) {
            logger.log(Level.INFO, "Couldn't sync to INFO because the data to sync was invalid", (Throwable) e);
        }
    }

    public void syncToId3FromInfoOverwrite() throws KeyNotFoundException {
        try {
            Iterator it = WavInfoTag.getSupportedKeys().iterator();
            while (it.hasNext()) {
                FieldKey fieldKey = (FieldKey) it.next();
                if (!this.infoTag.getFirst(fieldKey).isEmpty()) {
                    this.id3Tag.setField(fieldKey, stripNullTerminator(this.infoTag.getFirst(fieldKey)));
                } else {
                    this.id3Tag.deleteField(fieldKey);
                }
            }
        } catch (FieldDataInvalidException e) {
            logger.log(Level.INFO, "Couldn't sync to ID3 because the data to sync was invalid", (Throwable) e);
        }
    }

    public void syncToInfoFromId3Overwrite() {
        try {
            Iterator it = WavInfoTag.getSupportedKeys().iterator();
            while (it.hasNext()) {
                FieldKey fieldKey = (FieldKey) it.next();
                if (!this.id3Tag.getFirst(fieldKey).isEmpty()) {
                    this.infoTag.setField(fieldKey, addNullTerminatorIfNone(this.id3Tag.getFirst(fieldKey)));
                } else {
                    this.infoTag.deleteField(fieldKey);
                }
            }
        } catch (FieldDataInvalidException e) {
            logger.log(Level.INFO, "Couldn't sync to INFO because the data to sync was invalid", (Throwable) e);
        }
    }

    private String stripNullTerminator(String str) {
        return str.endsWith(NULL) ? str.substring(0, str.length() - 1) : str;
    }

    private String addNullTerminatorIfNone(String str) {
        return str.endsWith(NULL) ? str : str + NULL;
    }

    public void syncTagsAfterRead() throws KeyNotFoundException {
        if (getActiveTag() instanceof WavInfoTag) {
            syncToInfoFromId3IfEmpty();
        } else {
            syncToId3FromInfoIfEmpty();
        }
    }

    public void syncTagBeforeWrite() throws KeyNotFoundException {
        if (getActiveTag() instanceof WavInfoTag) {
            syncToId3FromInfoOverwrite();
        } else {
            syncToInfoFromId3Overwrite();
        }
    }

    public boolean isIncorrectlyAlignedTag() {
        return this.isIncorrectlyAlignedTag;
    }

    public void setIncorrectlyAlignedTag(boolean z) {
        this.isIncorrectlyAlignedTag = z;
    }

    public static AbstractID3v2Tag createDefaultID3Tag() {
        if (TagOptionSingleton.getInstance().getID3V2Version() == ID3V2Version.ID3_V24) {
            return new ID3v24Tag();
        }
        if (TagOptionSingleton.getInstance().getID3V2Version() == ID3V2Version.ID3_V23) {
            return new ID3v23Tag();
        }
        if (TagOptionSingleton.getInstance().getID3V2Version() == ID3V2Version.ID3_V22) {
            return new ID3v22Tag();
        }
        return new ID3v23Tag();
    }

    public boolean isBadChunkData() {
        return this.isBadChunkData;
    }

    public void setBadChunkData(boolean z) {
        this.isBadChunkData = z;
    }

    public boolean isNonStandardPadding() {
        return this.isNonStandardPadding;
    }

    public void setNonStandardPadding(boolean z) {
        this.isNonStandardPadding = z;
    }
}
