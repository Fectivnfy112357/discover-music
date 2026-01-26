package org.jaudiotagger.tag.datatype;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.tag.id3.AbstractTagFrameBody;

/* loaded from: classes3.dex */
public class StringNullTerminated extends TextEncodedStringNullTerminated {
    public StringNullTerminated(String str, AbstractTagFrameBody abstractTagFrameBody) {
        super(str, abstractTagFrameBody, false);
    }

    public StringNullTerminated(StringNullTerminated stringNullTerminated) {
        super(stringNullTerminated);
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringNullTerminated, org.jaudiotagger.tag.datatype.AbstractDataType
    public boolean equals(Object obj) {
        return (obj instanceof StringNullTerminated) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.datatype.TextEncodedStringNullTerminated, org.jaudiotagger.tag.datatype.AbstractString
    protected Charset getTextEncodingCharSet() {
        return StandardCharsets.ISO_8859_1;
    }
}
