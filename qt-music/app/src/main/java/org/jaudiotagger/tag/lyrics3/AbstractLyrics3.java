package org.jaudiotagger.tag.lyrics3;

import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.tag.id3.AbstractTag;
import org.jaudiotagger.tag.id3.ID3v1Tag;

/* loaded from: classes3.dex */
public abstract class AbstractLyrics3 extends AbstractTag {
    public AbstractLyrics3() {
    }

    public AbstractLyrics3(AbstractLyrics3 abstractLyrics3) {
        super(abstractLyrics3);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void delete(RandomAccessFile randomAccessFile) throws IOException {
        new ID3v1Tag();
    }
}
