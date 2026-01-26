package org.jaudiotagger.tag.datatype;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.reference.Languages;

/* loaded from: classes3.dex */
public class StringHashMap extends StringFixedLength implements HashMapInterface<String, String> {
    boolean hasEmptyValue;
    Map<String, String> keyToValue;
    Map<String, String> valueToKey;

    public StringHashMap(String str, AbstractTagFrameBody abstractTagFrameBody, int i) {
        super(str, abstractTagFrameBody, i);
        this.keyToValue = null;
        this.valueToKey = null;
        this.hasEmptyValue = false;
        if (str.equals(DataTypes.OBJ_LANGUAGE)) {
            this.valueToKey = Languages.getInstanceOf().getValueToIdMap();
            this.keyToValue = Languages.getInstanceOf().getIdToValueMap();
            return;
        }
        throw new IllegalArgumentException("Hashmap identifier not defined in this class: " + str);
    }

    public StringHashMap(StringHashMap stringHashMap) {
        super(stringHashMap);
        this.keyToValue = null;
        this.valueToKey = null;
        this.hasEmptyValue = false;
        this.hasEmptyValue = stringHashMap.hasEmptyValue;
        this.keyToValue = stringHashMap.keyToValue;
        this.valueToKey = stringHashMap.valueToKey;
    }

    @Override // org.jaudiotagger.tag.datatype.HashMapInterface
    public Map<String, String> getKeyToValue() {
        return this.keyToValue;
    }

    @Override // org.jaudiotagger.tag.datatype.HashMapInterface
    public Map<String, String> getValueToKey() {
        return this.valueToKey;
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void setValue(Object obj) {
        if (obj instanceof String) {
            if (obj.equals(Languages.MEDIA_MONKEY_ID)) {
                this.value = obj.toString();
                return;
            } else {
                this.value = ((String) obj).toLowerCase();
                return;
            }
        }
        this.value = obj;
    }

    @Override // org.jaudiotagger.tag.datatype.StringFixedLength, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (!(obj instanceof StringHashMap)) {
            return false;
        }
        StringHashMap stringHashMap = (StringHashMap) obj;
        if (this.hasEmptyValue != stringHashMap.hasEmptyValue) {
            return false;
        }
        Map<String, String> map = this.keyToValue;
        if (map == null) {
            if (stringHashMap.keyToValue != null) {
                return false;
            }
        } else if (!map.equals(stringHashMap.keyToValue)) {
            return false;
        }
        if (this.keyToValue == null) {
            if (stringHashMap.keyToValue != null) {
                return false;
            }
        } else if (!this.valueToKey.equals(stringHashMap.valueToKey)) {
            return false;
        }
        return super.equals(obj);
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

    @Override // org.jaudiotagger.tag.datatype.AbstractString
    public String toString() {
        if (this.value == null || this.keyToValue.get(this.value) == null) {
            return "";
        }
        return this.keyToValue.get(this.value);
    }

    @Override // org.jaudiotagger.tag.datatype.StringFixedLength, org.jaudiotagger.tag.datatype.AbstractString
    protected Charset getTextEncodingCharSet() {
        return StandardCharsets.ISO_8859_1;
    }
}
