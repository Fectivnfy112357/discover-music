package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.id3.AbstractTagFrameBody;
import org.jaudiotagger.tag.id3.ID3Tags;

/* loaded from: classes3.dex */
public class StringDate extends StringFixedLength {
    public StringDate(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody, 8);
    }

    public StringDate(StringDate stringDate) {
        super(stringDate);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public void setValue(Object obj) {
        if (obj != null) {
            this.value = ID3Tags.stripChar(obj.toString(), '-');
        }
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractDataType
    public Object getValue() {
        if (this.value != null) {
            return ID3Tags.stripChar(this.value.toString(), '-');
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.datatype.StringFixedLength, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof StringDate) && super.equals(obj);
    }
}
