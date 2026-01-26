package org.jaudiotagger.tag.lyrics3;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.StringSizeTerminated;

/* loaded from: classes3.dex */
public class FieldFrameBodyINF extends AbstractLyrics3v2FieldFrameBody {
    public FieldFrameBodyINF() {
    }

    public FieldFrameBodyINF(FieldFrameBodyINF fieldFrameBodyINF) {
        super(fieldFrameBodyINF);
    }

    public FieldFrameBodyINF(String str) {
        setObjectValue("Additional Information", str);
    }

    public FieldFrameBodyINF(ByteBuffer byteBuffer) throws InvalidTagException {
        read(byteBuffer);
    }

    public void setAdditionalInformation(String str) {
        setObjectValue("Additional Information", str);
    }

    public String getAdditionalInformation() {
        return (String) getObjectValue("Additional Information");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return Lyrics3v2Fields.FIELD_V2_ADDITIONAL_MULTI_LINE_TEXT;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringSizeTerminated("Additional Information", this));
    }
}
