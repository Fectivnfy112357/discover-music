package org.jaudiotagger.tag.id3.framebody;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.StringTokenizer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.Pair;
import org.jaudiotagger.tag.datatype.PairedTextEncodedStringNullTerminated;

/* loaded from: classes3.dex */
public abstract class AbstractFrameBodyPairs extends AbstractID3v2FrameBody implements ID3v24FrameBody {
    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public abstract String getIdentifier();

    public AbstractFrameBodyPairs() {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
    }

    public AbstractFrameBodyPairs(byte b, String str) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        setText(str);
    }

    public AbstractFrameBodyPairs(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public void setText(String str) {
        PairedTextEncodedStringNullTerminated.ValuePairs valuePairs = new PairedTextEncodedStringNullTerminated.ValuePairs();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\u0000");
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            if (stringTokenizer.hasMoreTokens()) {
                valuePairs.add(strNextToken, stringTokenizer.nextToken());
            }
        }
        setObjectValue(DataTypes.OBJ_TEXT, valuePairs);
    }

    public void addPair(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\u0000");
        if (stringTokenizer.countTokens() == 2) {
            addPair(stringTokenizer.nextToken(), stringTokenizer.nextToken());
        } else {
            addPair("", str);
        }
    }

    public void addPair(String str, String str2) {
        ((PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT)).getValue().add(str, str2);
    }

    public void resetPairs() {
        ((PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT)).getValue().getMapping().clear();
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        if (!((PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT)).canBeEncoded()) {
            setTextEncoding((byte) 1);
        }
        super.write(byteArrayOutputStream);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TEXT_ENCODING, this, 1));
        this.objectList.add(new PairedTextEncodedStringNullTerminated(DataTypes.OBJ_TEXT, this));
    }

    public PairedTextEncodedStringNullTerminated.ValuePairs getPairing() {
        return (PairedTextEncodedStringNullTerminated.ValuePairs) getObject(DataTypes.OBJ_TEXT).getValue();
    }

    public String getKeyAtIndex(int i) {
        return ((PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT)).getValue().getMapping().get(i).getKey();
    }

    public String getValueAtIndex(int i) {
        return ((PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT)).getValue().getMapping().get(i).getValue();
    }

    public int getNumberOfPairs() {
        return ((PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT)).getValue().getNumberOfPairs();
    }

    public String getText() {
        PairedTextEncodedStringNullTerminated pairedTextEncodedStringNullTerminated = (PairedTextEncodedStringNullTerminated) getObject(DataTypes.OBJ_TEXT);
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Pair pair : pairedTextEncodedStringNullTerminated.getValue().getMapping()) {
            sb.append(pair.getKey() + (char) 0 + pair.getValue());
            if (i != getNumberOfPairs()) {
                sb.append((char) 0);
            }
            i++;
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String getUserFriendlyValue() {
        return getText();
    }
}
