package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.ByteArraySizeTerminated;
import org.jaudiotagger.tag.datatype.DataTypes;

/* loaded from: classes3.dex */
public class FrameBodyEncrypted extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    private String identifier;

    public FrameBodyEncrypted(String str) {
        this.identifier = str;
    }

    public FrameBodyEncrypted(FrameBodyEncrypted frameBodyEncrypted) {
        super(frameBodyEncrypted);
        this.identifier = null;
    }

    public FrameBodyEncrypted(String str, ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
        this.identifier = str;
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new ByteArraySizeTerminated(DataTypes.OBJ_DATA, this));
    }
}
