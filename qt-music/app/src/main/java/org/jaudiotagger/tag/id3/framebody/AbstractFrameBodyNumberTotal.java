package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.PartOfSet;

/* loaded from: classes3.dex */
public abstract class AbstractFrameBodyNumberTotal extends AbstractID3v2FrameBody {
    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public abstract String getIdentifier();

    public AbstractFrameBodyNumberTotal() {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, (byte) 0);
        setObjectValue(DataTypes.OBJ_TEXT, new PartOfSet.PartOfSetValue());
    }

    public AbstractFrameBodyNumberTotal(AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal) {
        super(abstractFrameBodyNumberTotal);
    }

    public AbstractFrameBodyNumberTotal(byte b, String str) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_TEXT, new PartOfSet.PartOfSetValue(str));
    }

    public AbstractFrameBodyNumberTotal(byte b, Integer num, Integer num2) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
        setObjectValue(DataTypes.OBJ_TEXT, new PartOfSet.PartOfSetValue(num, num2));
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String getUserFriendlyValue() {
        return String.valueOf(((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).getCount());
    }

    public AbstractFrameBodyNumberTotal(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public String getText() {
        return getObjectValue(DataTypes.OBJ_TEXT).toString();
    }

    public void setText(String str) {
        setObjectValue(DataTypes.OBJ_TEXT, new PartOfSet.PartOfSetValue(str));
    }

    public Integer getNumber() {
        return ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).getCount();
    }

    public String getNumberAsText() {
        return ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).getCountAsText();
    }

    public void setNumber(Integer num) {
        ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).setCount(num);
    }

    public void setNumber(String str) {
        ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).setCount(str);
    }

    public Integer getTotal() {
        return ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).getTotal();
    }

    public String getTotalAsText() {
        return ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).getTotalAsText();
    }

    public void setTotal(Integer num) {
        ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).setTotal(num);
    }

    public void setTotal(String str) {
        ((PartOfSet.PartOfSetValue) getObjectValue(DataTypes.OBJ_TEXT)).setTotal(str);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TEXT_ENCODING, this, 1));
        this.objectList.add(new PartOfSet(DataTypes.OBJ_TEXT, this));
    }
}
