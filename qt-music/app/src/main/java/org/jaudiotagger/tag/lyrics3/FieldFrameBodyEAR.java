package org.jaudiotagger.tag.lyrics3;

import androidx.exifinterface.media.ExifInterface;
import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public class FieldFrameBodyEAR extends AbstractLyrics3v2FieldFrameBody {
    public FieldFrameBodyEAR() {
    }

    public FieldFrameBodyEAR(FieldFrameBodyEAR fieldFrameBodyEAR) {
        super(fieldFrameBodyEAR);
    }

    public FieldFrameBodyEAR(String str) {
        setObjectValue(ExifInterface.TAG_ARTIST, str);
    }

    public FieldFrameBodyEAR(ByteBuffer byteBuffer) throws InvalidTagException {
        read(byteBuffer);
    }

    public void setArtist(String str) {
        setObjectValue(ExifInterface.TAG_ARTIST, str);
    }

    public String getArtist() {
        return (String) getObjectValue(ExifInterface.TAG_ARTIST);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_ARTIST;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringSizeTerminated(ExifInterface.TAG_ARTIST, this));
    }
}
