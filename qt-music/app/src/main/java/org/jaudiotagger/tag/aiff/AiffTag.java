package org.jaudiotagger.tag.aiff;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.iff.ChunkSummary;
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
public class AiffTag implements Tag, Id3SupportingTag {
    private long fileSize;
    private long formSize;
    private AbstractID3v2Tag id3Tag;
    private List<ChunkSummary> chunkSummaryList = new ArrayList();
    private boolean lastChunkSizeExtendsPastFormSize = false;
    private boolean isIncorrectlyAlignedTag = false;
    private boolean isExistingId3Tag = false;

    public void addChunkSummary(ChunkSummary chunkSummary) {
        this.chunkSummaryList.add(chunkSummary);
    }

    public List<ChunkSummary> getChunkSummaryList() {
        return this.chunkSummaryList;
    }

    public boolean isExistingId3Tag() {
        return this.isExistingId3Tag;
    }

    public void setExistingId3Tag(boolean z) {
        this.isExistingId3Tag = z;
    }

    public AiffTag() {
    }

    public AiffTag(AbstractID3v2Tag abstractID3v2Tag) {
        this.id3Tag = abstractID3v2Tag;
    }

    @Override // org.jaudiotagger.tag.id3.Id3SupportingTag
    public AbstractID3v2Tag getID3Tag() {
        return this.id3Tag;
    }

    @Override // org.jaudiotagger.tag.id3.Id3SupportingTag
    public void setID3Tag(AbstractID3v2Tag abstractID3v2Tag) {
        this.id3Tag = abstractID3v2Tag;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) throws FieldDataInvalidException {
        this.id3Tag.addField(tagField);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(String str) {
        return this.id3Tag.getFields(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        return this.id3Tag.getAll(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasCommonFields() {
        return this.id3Tag.hasCommonFields();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        AbstractID3v2Tag abstractID3v2Tag = this.id3Tag;
        return abstractID3v2Tag == null || abstractID3v2Tag.isEmpty();
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
        this.id3Tag.setField(tagField);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        return this.id3Tag.createField(fieldKey, strArr);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(String str) {
        return this.id3Tag.getFirst(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        return this.id3Tag.getValue(fieldKey, i);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(String str) {
        return this.id3Tag.getFirstField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        return this.id3Tag.getFirstField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        this.id3Tag.deleteField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(String str) throws KeyNotFoundException {
        this.id3Tag.deleteField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public Iterator<TagField> getFields() {
        return this.id3Tag.getFields();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCount() {
        return this.id3Tag.getFieldCount();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCountIncludingSubValues() {
        return getFieldCount();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean setEncoding(Charset charset) throws FieldDataInvalidException {
        return this.id3Tag.setEncoding(charset);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        return this.id3Tag.createField(artwork);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws FieldDataInvalidException {
        this.id3Tag.setField(artwork);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        this.id3Tag.addField(artwork);
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        return this.id3Tag.getArtworkList();
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        return this.id3Tag.getFields(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public Artwork getFirstArtwork() {
        return this.id3Tag.getFirstArtwork();
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        this.id3Tag.deleteArtworkField();
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        return this.id3Tag.hasField(fieldKey);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(String str) {
        return this.id3Tag.hasField(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return createField(FieldKey.IS_COMPILATION, String.valueOf(z));
    }

    @Override // org.jaudiotagger.tag.Tag
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FileSize:" + Hex.asDecAndHex(this.fileSize) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        sb.append("FORMSize:" + Hex.asDecAndHex(this.formSize + 8) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        if (this.lastChunkSizeExtendsPastFormSize) {
            sb.append("Last Chunk extends past Form stated size\n");
        } else if (this.fileSize > this.formSize + 8) {
            sb.append("Non Iff Data at End of File:" + (this.fileSize - (this.formSize + 8)) + " bytes\n");
        }
        sb.append("Chunks:\n");
        Iterator<ChunkSummary> it = this.chunkSummaryList.iterator();
        while (it.hasNext()) {
            sb.append("\t" + it.next().toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (this.id3Tag != null) {
            sb.append("Aiff ID3 Tag:\n");
            if (isExistingId3Tag()) {
                if (this.isIncorrectlyAlignedTag) {
                    sb.append("\tincorrectly starts as odd byte\n");
                }
                sb.append("\tstartLocation:" + Hex.asDecAndHex(getStartLocationInFileOfId3Chunk()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                sb.append("\tendLocation:" + Hex.asDecAndHex(getEndLocationInFileOfId3Chunk()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
            sb.append(this.id3Tag.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            return sb.toString();
        }
        return "tag:empty";
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

    public boolean equals(Object obj) {
        return this.id3Tag.equals(obj);
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

    public long getFormSize() {
        return this.formSize;
    }

    public void setFormSize(long j) {
        this.formSize = j;
    }

    public boolean isLastChunkSizeExtendsPastFormSize() {
        return this.lastChunkSizeExtendsPastFormSize;
    }

    public void setLastChunkSizeExtendsPastFormSize(boolean z) {
        this.lastChunkSizeExtendsPastFormSize = z;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(long j) {
        this.fileSize = j;
    }
}
