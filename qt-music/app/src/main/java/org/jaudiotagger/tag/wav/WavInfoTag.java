package org.jaudiotagger.tag.wav;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import org.jaudiotagger.audio.generic.GenericTag;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;

/* loaded from: classes3.dex */
public class WavInfoTag extends GenericTag {
    private List<TagTextField> unrecognisedFields = new ArrayList();
    private Long startLocationInFile = null;
    private Long endLocationInFile = null;

    static {
        supportedKeys = EnumSet.of(FieldKey.ALBUM, FieldKey.ARTIST, FieldKey.ALBUM_ARTIST, FieldKey.TITLE, FieldKey.TRACK, FieldKey.GENRE, FieldKey.COMMENT, FieldKey.YEAR, FieldKey.RECORD_LABEL, FieldKey.ISRC, FieldKey.COMPOSER, FieldKey.LYRICIST, FieldKey.ENCODER, FieldKey.CONDUCTOR, FieldKey.RATING, FieldKey.COPYRIGHT);
    }

    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public String toString() {
        StringBuilder sb = new StringBuilder("Wav Info Tag:\n");
        if (getStartLocationInFile() != null) {
            sb.append("\tstartLocation:" + Hex.asDecAndHex(getStartLocationInFile().longValue()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        if (getEndLocationInFile() != null) {
            sb.append("\tendLocation:" + Hex.asDecAndHex(getEndLocationInFile().longValue()) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        sb.append(super.toString().replace("\u0000", ""));
        if (this.unrecognisedFields.size() > 0) {
            sb.append("\nUnrecognized Tags:\n");
            for (TagTextField tagTextField : this.unrecognisedFields) {
                sb.append("\t" + tagTextField.getId() + ":" + tagTextField.getContent().replace("\u0000", "") + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            }
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return createField(FieldKey.IS_COMPILATION, String.valueOf(z));
    }

    public Long getStartLocationInFile() {
        return this.startLocationInFile;
    }

    public void setStartLocationInFile(long j) {
        this.startLocationInFile = Long.valueOf(j);
    }

    public Long getEndLocationInFile() {
        return this.endLocationInFile;
    }

    public void setEndLocationInFile(long j) {
        this.endLocationInFile = Long.valueOf(j);
    }

    public long getSizeOfTag() {
        Long l = this.endLocationInFile;
        if (l == null || this.startLocationInFile == null) {
            return 0L;
        }
        return (l.longValue() - this.startLocationInFile.longValue()) - 8;
    }

    public void addUnRecognizedField(String str, String str2) {
        this.unrecognisedFields.add(new GenericTag.GenericTagTextField(str, str2));
    }

    public List<TagTextField> getUnrecognisedFields() {
        return this.unrecognisedFields;
    }
}
