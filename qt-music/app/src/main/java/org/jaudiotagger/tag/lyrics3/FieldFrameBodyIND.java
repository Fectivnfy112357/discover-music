package org.jaudiotagger.tag.lyrics3;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.BooleanString;

/* loaded from: classes3.dex */
public class FieldFrameBodyIND extends AbstractLyrics3v2FieldFrameBody {
    public FieldFrameBodyIND() {
    }

    public FieldFrameBodyIND(FieldFrameBodyIND fieldFrameBodyIND) {
        super(fieldFrameBodyIND);
    }

    public FieldFrameBodyIND(boolean z, boolean z2) {
        setObjectValue("Lyrics Present", Boolean.valueOf(z));
        setObjectValue("Timestamp Present", Boolean.valueOf(z2));
    }

    public FieldFrameBodyIND(ByteBuffer byteBuffer) throws InvalidTagException {
        read(byteBuffer);
    }

    public void setAuthor(String str) {
        setObjectValue("Author", str);
    }

    public String getAuthor() {
        return (String) getObjectValue("Author");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_INDICATIONS;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new BooleanString("Lyrics Present", this));
        this.objectList.add(new BooleanString("Timestamp Present", this));
    }
}
