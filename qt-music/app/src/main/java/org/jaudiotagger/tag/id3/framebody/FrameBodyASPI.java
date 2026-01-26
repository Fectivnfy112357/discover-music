package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.NumberFixedLength;
import org.jaudiotagger.tag.datatype.NumberVariableLength;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* loaded from: classes3.dex */
public class FrameBodyASPI extends AbstractID3v2FrameBody implements ID3v24FrameBody {
    private static final String BITS_PER_INDEX_POINT = "BitsPerIndexPoint";
    private static final int BITS_PER_INDEX_POINTS_FIELD_SIZE = 1;
    private static final int DATA_LENGTH_FIELD_SIZE = 4;
    private static final int DATA_START_FIELD_SIZE = 4;
    private static final String FRACTION_AT_INDEX = "FractionAtIndex";
    private static final int FRACTION_AT_INDEX_MINIMUM_FIELD_SIZE = 1;
    private static final String INDEXED_DATA_LENGTH = "IndexedDataLength";
    private static final String INDEXED_DATA_START = "IndexedDataStart";
    private static final int NO_OF_INDEX_POINTS_FIELD_SIZE = 2;
    private static final String NUMBER_OF_INDEX_POINTS = "NumberOfIndexPoints";

    public FrameBodyASPI() {
    }

    public FrameBodyASPI(FrameBodyASPI frameBodyASPI) {
        super(frameBodyASPI);
    }

    public FrameBodyASPI(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_AUDIO_SEEK_POINT_INDEX;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberFixedLength(INDEXED_DATA_START, this, 4));
        this.objectList.add(new NumberFixedLength(INDEXED_DATA_LENGTH, this, 4));
        this.objectList.add(new NumberFixedLength(NUMBER_OF_INDEX_POINTS, this, 2));
        this.objectList.add(new NumberFixedLength(BITS_PER_INDEX_POINT, this, 1));
        this.objectList.add(new NumberVariableLength(FRACTION_AT_INDEX, this, 1));
    }
}
