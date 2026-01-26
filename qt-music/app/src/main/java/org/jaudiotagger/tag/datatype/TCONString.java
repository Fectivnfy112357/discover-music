package org.jaudiotagger.tag.datatype;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class TCONString extends TextEncodedStringSizeTerminated {
    private boolean isNullSeperateMultipleValues;

    public TCONString(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
        this.isNullSeperateMultipleValues = true;
    }

    public TCONString(TCONString tCONString) {
        super(tCONString);
        this.isNullSeperateMultipleValues = true;
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TCONString) && super.equals(obj);
    }

    public boolean isNullSeperateMultipleValues() {
        return this.isNullSeperateMultipleValues;
    }

    public void setNullSeperateMultipleValues(boolean z) {
        this.isNullSeperateMultipleValues = z;
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated
    public void addValue(String str) {
        if (isNullSeperateMultipleValues()) {
            setValue(this.value + "\u0000" + str);
        } else if (str.startsWith("(")) {
            setValue(this.value + str);
        } else {
            setValue(this.value + "\u0000" + str);
        }
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated
    public int getNumberOfValues() {
        return getValues().size();
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated
    public String getValueAtIndex(int i) {
        return getValues().get(i);
    }

    public static List<String> splitV23(String str) {
        List<String> listAsList = Arrays.asList(str.replaceAll("(\\(\\d+\\)|\\(RX\\)|\\(CR\\)\\w*)", "$1\u0000").split("\u0000"));
        if (listAsList.size() != 0) {
            return listAsList;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add("");
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated
    public List<String> getValues() {
        if (isNullSeperateMultipleValues()) {
            return splitByNullSeperator((String) this.value);
        }
        return splitV23((String) this.value);
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated
    public String getValueWithoutTrailingNull() {
        List<String> values = getValues();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < values.size(); i++) {
            if (i != 0) {
                stringBuffer.append("\u0000");
            }
            stringBuffer.append(values.get(i));
        }
        return stringBuffer.toString();
    }
}
