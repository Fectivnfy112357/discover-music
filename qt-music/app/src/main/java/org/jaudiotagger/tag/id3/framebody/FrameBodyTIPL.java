package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.Pair;
import org.jaudiotagger.tag.datatype.PairedTextEncodedStringNullTerminated;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jaudiotagger.tag.id3.valuepair.StandardIPLSKey;

/* loaded from: classes3.dex */
public class FrameBodyTIPL extends AbstractFrameBodyPairs implements ID3v24FrameBody {
    public static final String ENGINEER = StandardIPLSKey.ENGINEER.getKey();
    public static final String MIXER = StandardIPLSKey.MIXER.getKey();
    public static final String DJMIXER = StandardIPLSKey.DJMIXER.getKey();
    public static final String PRODUCER = StandardIPLSKey.PRODUCER.getKey();
    public static final String ARRANGER = StandardIPLSKey.ARRANGER.getKey();

    public FrameBodyTIPL() {
    }

    public FrameBodyTIPL(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTIPL(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public FrameBodyTIPL(FrameBodyIPLS frameBodyIPLS) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(frameBodyIPLS.getTextEncoding()));
        setObjectValue(DataTypes.OBJ_TEXT, frameBodyIPLS.getPairing());
    }

    public FrameBodyTIPL(byte b, List<Pair> list) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        PairedTextEncodedStringNullTerminated.ValuePairs valuePairs = new PairedTextEncodedStringNullTerminated.ValuePairs();
        Iterator<Pair> it = list.iterator();
        while (it.hasNext()) {
            valuePairs.add(it.next());
        }
        setObjectValue(DataTypes.OBJ_TEXT, valuePairs);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyPairs, org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return ID3v24Frames.FRAME_ID_INVOLVED_PEOPLE;
    }
}
