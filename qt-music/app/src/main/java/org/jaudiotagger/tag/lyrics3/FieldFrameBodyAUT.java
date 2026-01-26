package org.jaudiotagger.tag.lyrics3;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public class FieldFrameBodyAUT extends AbstractLyrics3v2FieldFrameBody {
    public FieldFrameBodyAUT() {
    }

    public FieldFrameBodyAUT(FieldFrameBodyAUT fieldFrameBodyAUT) {
        super(fieldFrameBodyAUT);
    }

    public FieldFrameBodyAUT(String str) {
        setObjectValue("Author", str);
    }

    public FieldFrameBodyAUT(ByteBuffer byteBuffer) throws InvalidTagException {
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
        return Lyrics3v2Fields.FIELD_V2_AUTHOR;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringSizeTerminated("Author", this));
    }
}
