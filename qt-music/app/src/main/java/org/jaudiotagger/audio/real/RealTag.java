package org.jaudiotagger.audio.real;

import org.jaudiotagger.audio.generic.GenericTag;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.TagField;

/* loaded from: classes3.dex */
public class RealTag extends GenericTag {
    @Override // org.jaudiotagger.audio.generic.AbstractTag, org.jaudiotagger.tag.Tag
    public String toString() {
        return "REAL " + super.toString();
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return createField(FieldKey.IS_COMPILATION, String.valueOf(z));
    }
}
