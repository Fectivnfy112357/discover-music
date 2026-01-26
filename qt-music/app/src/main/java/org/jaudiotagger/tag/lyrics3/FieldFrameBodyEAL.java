package org.jaudiotagger.tag.lyrics3;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public class FieldFrameBodyEAL extends AbstractLyrics3v2FieldFrameBody {
    public FieldFrameBodyEAL() {
    }

    public FieldFrameBodyEAL(FieldFrameBodyEAL fieldFrameBodyEAL) {
        super(fieldFrameBodyEAL);
    }

    public FieldFrameBodyEAL(String str) {
        setObjectValue("Album", str);
    }

    public FieldFrameBodyEAL(ByteBuffer byteBuffer) throws InvalidTagException {
        read(byteBuffer);
    }

    public void setAlbum(String str) {
        setObjectValue("Album", str);
    }

    public String getAlbum() {
        return (String) getObjectValue("Album");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_ALBUM;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringSizeTerminated("Album", this));
    }
}
