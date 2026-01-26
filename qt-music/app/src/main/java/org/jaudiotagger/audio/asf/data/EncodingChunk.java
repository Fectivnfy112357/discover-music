package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class EncodingChunk extends Chunk {
    private final List<String> strings;

    public EncodingChunk(BigInteger bigInteger) {
        super(GUID.GUID_ENCODING, bigInteger);
        this.strings = new ArrayList();
    }

    public void addString(String str) {
        this.strings.add(str);
    }

    public Collection<String> getStrings() {
        return new ArrayList(this.strings);
    }

    @Override // org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        this.strings.iterator();
        Iterator<String> it = this.strings.iterator();
        while (it.hasNext()) {
            sb.append(str).append("  | : ").append(it.next()).append(Utils.LINE_SEPARATOR);
        }
        return sb.toString();
    }
}
