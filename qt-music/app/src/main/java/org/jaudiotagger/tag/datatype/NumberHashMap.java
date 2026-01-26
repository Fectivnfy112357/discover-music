package org.jaudiotagger.tag.datatype;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.valuepair.ChannelTypes;
import org.jaudiotagger.tag.id3.valuepair.EventTimingTimestampTypes;
import org.jaudiotagger.tag.id3.valuepair.EventTimingTypes;
import org.jaudiotagger.tag.id3.valuepair.InterpolationTypes;
import org.jaudiotagger.tag.id3.valuepair.ReceivedAsTypes;
import org.jaudiotagger.tag.id3.valuepair.SynchronisedLyricsContentType;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.tag.reference.GenreTypes;
import org.jaudiotagger.tag.reference.PictureTypes;
import org.jaudiotagger.utils.EqualsUtil;

/* loaded from: classes3.dex */
public class NumberHashMap extends NumberFixedLength implements HashMapInterface<Integer, String> {
    private boolean hasEmptyValue;
    private Map<Integer, String> keyToValue;
    private Map<String, Integer> valueToKey;

    public NumberHashMap(String str, AbstractTagFrameBody abstractTagFrameBody, int i) {
        super(str, abstractTagFrameBody, i);
        this.keyToValue = null;
        this.valueToKey = null;
        this.hasEmptyValue = false;
        if (str.equals(DataTypes.OBJ_GENRE)) {
            this.valueToKey = GenreTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = GenreTypes.getInstanceOf().getIdToValueMap();
            this.hasEmptyValue = true;
            return;
        }
        if (str.equals(DataTypes.OBJ_TEXT_ENCODING)) {
            this.valueToKey = TextEncoding.getInstanceOf().getValueToIdMap();
            this.keyToValue = TextEncoding.getInstanceOf().getIdToValueMap();
            return;
        }
        if (str.equals(DataTypes.OBJ_INTERPOLATION_METHOD)) {
            this.valueToKey = InterpolationTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = InterpolationTypes.getInstanceOf().getIdToValueMap();
            return;
        }
        if (str.equals(DataTypes.OBJ_PICTURE_TYPE)) {
            this.valueToKey = PictureTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = PictureTypes.getInstanceOf().getIdToValueMap();
            this.hasEmptyValue = true;
            return;
        }
        if (str.equals(DataTypes.OBJ_TYPE_OF_EVENT)) {
            this.valueToKey = EventTimingTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = EventTimingTypes.getInstanceOf().getIdToValueMap();
            return;
        }
        if (str.equals(DataTypes.OBJ_TIME_STAMP_FORMAT)) {
            this.valueToKey = EventTimingTimestampTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = EventTimingTimestampTypes.getInstanceOf().getIdToValueMap();
            return;
        }
        if (str.equals(DataTypes.OBJ_TYPE_OF_CHANNEL)) {
            this.valueToKey = ChannelTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = ChannelTypes.getInstanceOf().getIdToValueMap();
        } else if (str.equals(DataTypes.OBJ_RECIEVED_AS)) {
            this.valueToKey = ReceivedAsTypes.getInstanceOf().getValueToIdMap();
            this.keyToValue = ReceivedAsTypes.getInstanceOf().getIdToValueMap();
        } else {
            if (str.equals(DataTypes.OBJ_CONTENT_TYPE)) {
                this.valueToKey = SynchronisedLyricsContentType.getInstanceOf().getValueToIdMap();
                this.keyToValue = SynchronisedLyricsContentType.getInstanceOf().getIdToValueMap();
                return;
            }
            throw new IllegalArgumentException("Hashmap identifier not defined in this class: " + str);
        }
    }

    public NumberHashMap(NumberHashMap numberHashMap) {
        super(numberHashMap);
        this.keyToValue = null;
        this.valueToKey = null;
        this.hasEmptyValue = false;
        this.hasEmptyValue = numberHashMap.hasEmptyValue;
        this.keyToValue = numberHashMap.keyToValue;
        this.valueToKey = numberHashMap.valueToKey;
    }

    @Override // org.jaudiotagger.tag.datatype.HashMapInterface
    public Map<Integer, String> getKeyToValue() {
        return this.keyToValue;
    }

    @Override // org.jaudiotagger.tag.datatype.HashMapInterface
    public Map<String, Integer> getValueToKey() {
        return this.valueToKey;
    }

    @Override // org.jaudiotagger.tag.datatype.NumberFixedLength, org.jaudiotagger.tag.datatype.AbstractDataType
    public void setValue(Object obj) {
        if (obj instanceof Byte) {
            this.value = Long.valueOf(((Byte) obj).byteValue());
            return;
        }
        if (obj instanceof Short) {
            this.value = Long.valueOf(((Short) obj).shortValue());
        } else if (obj instanceof Integer) {
            this.value = Long.valueOf(((Integer) obj).intValue());
        } else {
            this.value = obj;
        }
    }

    @Override // org.jaudiotagger.tag.datatype.NumberFixedLength, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberHashMap)) {
            return false;
        }
        NumberHashMap numberHashMap = (NumberHashMap) obj;
        return EqualsUtil.areEqual(this.hasEmptyValue, numberHashMap.hasEmptyValue) && EqualsUtil.areEqual(this.keyToValue, numberHashMap.keyToValue) && EqualsUtil.areEqual(this.valueToKey, numberHashMap.valueToKey) && super.equals(numberHashMap);
    }

    @Override // org.jaudiotagger.tag.datatype.HashMapInterface
    public Iterator<String> iterator() {
        if (this.keyToValue == null) {
            return null;
        }
        TreeSet treeSet = new TreeSet(this.keyToValue.values());
        if (this.hasEmptyValue) {
            treeSet.add("");
        }
        return treeSet.iterator();
    }

    @Override // org.jaudiotagger.tag.datatype.NumberFixedLength, org.jaudiotagger.tag.datatype.AbstractDataType
    public void readByteArray(byte[] bArr, int i) throws InvalidDataTypeException {
        super.readByteArray(bArr, i);
        Integer numValueOf = Integer.valueOf(((Long) this.value).intValue());
        if (this.keyToValue.containsKey(numValueOf)) {
            return;
        }
        if (!this.hasEmptyValue) {
            throw new InvalidDataTypeException(ErrorMessage.MP3_REFERENCE_KEY_INVALID.getMsg(this.identifier, numValueOf));
        }
        if (this.identifier.equals(DataTypes.OBJ_PICTURE_TYPE)) {
            logger.warning(ErrorMessage.MP3_PICTURE_TYPE_INVALID.getMsg(this.value));
        }
    }

    @Override // org.jaudiotagger.tag.datatype.NumberFixedLength
    public String toString() {
        return (this.value == null || this.keyToValue.get(this.value) == null) ? "" : this.keyToValue.get(this.value);
    }
}
