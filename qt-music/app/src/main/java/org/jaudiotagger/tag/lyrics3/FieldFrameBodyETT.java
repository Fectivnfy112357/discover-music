package org.jaudiotagger.tag.lyrics3;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public class FieldFrameBodyETT extends AbstractLyrics3v2FieldFrameBody {
    public FieldFrameBodyETT() {
    }

    public FieldFrameBodyETT(FieldFrameBodyETT fieldFrameBodyETT) {
        super(fieldFrameBodyETT);
    }

    public FieldFrameBodyETT(String str) {
        setObjectValue("Title", str);
    }

    public FieldFrameBodyETT(ByteBuffer byteBuffer) throws InvalidTagException {
        read(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_TRACK;
    }

    public void setTitle(String str) {
        setObjectValue("Title", str);
    }

    public String getTitle() {
        return (String) getObjectValue("Title");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringSizeTerminated("Title", this));
    }
}
