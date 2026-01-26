package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;

/* loaded from: classes3.dex */
public class FrameBodyUnsupported extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody, ID3v22FrameBody {
    private String identifier;

    public FrameBodyUnsupported() {
        this.identifier = "";
    }

    public FrameBodyUnsupported(String str) {
        this.identifier = str;
    }

    public FrameBodyUnsupported(String str, byte[] bArr) {
        this.identifier = str;
        setObjectValue(DataTypes.OBJ_DATA, bArr);
    }

    public FrameBodyUnsupported(byte[] bArr) {
        this.identifier = "";
        setObjectValue(DataTypes.OBJ_DATA, bArr);
    }

    public FrameBodyUnsupported(FrameBodyUnsupported frameBodyUnsupported) {
        super(frameBodyUnsupported);
        this.identifier = "";
        this.identifier = frameBodyUnsupported.identifier;
    }

    public FrameBodyUnsupported(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
        this.identifier = "";
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof FrameBodyUnsupported) && this.identifier.equals(((FrameBodyUnsupported) obj).identifier) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String toString() {
        return getIdentifier();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_DATA, this));
    }
}
