package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class StringDateTime extends StringSizeTerminated {
    public StringDateTime(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
    }

    public StringDateTime(StringDateTime stringDateTime) {
        super(stringDateTime);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void setValue(Object obj) {
        if (obj != null) {
            this.value = obj.toString().replace(' ', 'T');
        }
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public Object getValue() {
        if (this.value != null) {
            return this.value.toString().replace(' ', 'T');
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.datatype.StringSizeTerminated, org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof StringDateTime) && super.equals(obj);
    }
}
