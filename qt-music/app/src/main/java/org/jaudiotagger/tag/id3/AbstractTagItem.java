package org.jaudiotagger.tag.id3;

import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.jaudiotagger.tag.TagException;

/* loaded from: classes3.dex */
public abstract class AbstractTagItem {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");

    public abstract String getIdentifier();

    public abstract int getSize();

    public abstract void read(ByteBuffer byteBuffer) throws TagException;

    public AbstractTagItem() {
    }

    public AbstractTagItem(AbstractTagItem abstractTagItem) {
    }

    public boolean isSubsetOf(Object obj) {
        return obj instanceof AbstractTagItem;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof AbstractTagItem;
    }
}
