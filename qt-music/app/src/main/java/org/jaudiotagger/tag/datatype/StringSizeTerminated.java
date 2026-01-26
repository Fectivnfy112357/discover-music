package org.jaudiotagger.tag.datatype;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class StringSizeTerminated extends TextEncodedStringSizeTerminated {
    public StringSizeTerminated(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody);
    }

    public StringSizeTerminated(StringSizeTerminated stringSizeTerminated) {
        super(stringSizeTerminated);
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringSizeTerminated, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof StringSizeTerminated) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.AbstractString
    protected Charset getTextEncodingCharSet() {
        return StandardCharsets.ISO_8859_1;
    }
}
