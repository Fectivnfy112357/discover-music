package org.jaudiotagger.tag.id3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* loaded from: classes3.dex */
public abstract class AbstractTag extends AbstractTagItem {
    protected static final String TYPE_TAG = "tag";

    public abstract void delete(RandomAccessFile randomAccessFile) throws IOException;

    public abstract Iterator<? extends Object> iterator();

    public abstract boolean seek(ByteBuffer byteBuffer);

    public abstract void write(RandomAccessFile randomAccessFile) throws IOException;

    public AbstractTag() {
    }

    public AbstractTag(AbstractTag abstractTag) {
        super(abstractTag);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof AbstractTag) && super.equals(obj);
    }
}
