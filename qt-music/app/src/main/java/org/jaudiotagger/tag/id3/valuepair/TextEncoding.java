package org.jaudiotagger.tag.id3.valuepair;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class TextEncoding extends AbstractIntStringValuePair {
    public static final byte ISO_8859_1 = 0;
    public static final int TEXT_ENCODING_FIELD_SIZE = 1;
    public static final byte UTF_16 = 1;
    public static final byte UTF_16BE = 2;
    public static final byte UTF_8 = 3;
    private static TextEncoding textEncodings;
    private final Map<Integer, Charset> idToCharset;

    public static synchronized TextEncoding getInstanceOf() {
        if (textEncodings == null) {
            textEncodings = new TextEncoding();
        }
        return textEncodings;
    }

    private TextEncoding() {
        HashMap map = new HashMap();
        this.idToCharset = map;
        map.put(0, StandardCharsets.ISO_8859_1);
        map.put(1, StandardCharsets.UTF_16);
        map.put(2, StandardCharsets.UTF_16BE);
        map.put(3, StandardCharsets.UTF_8);
        for (Map.Entry entry : map.entrySet()) {
            this.idToValue.put((Integer) entry.getKey(), ((Charset) entry.getValue()).name());
        }
        createMaps();
    }

    public Integer getIdForCharset(Charset charset) {
        return (Integer) this.valueToId.get(charset.name());
    }

    public Charset getCharsetForId(int i) {
        return this.idToCharset.get(Integer.valueOf(i));
    }
}
