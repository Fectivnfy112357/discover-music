package org.jaudiotagger.audio.generic;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public abstract class AbstractTagCreator {
    public abstract ByteBuffer convertMetadata(Tag tag, boolean z) throws UnsupportedEncodingException;

    public ByteBuffer convertMetadata(Tag tag) throws UnsupportedEncodingException {
        return convertMetadata(tag, false);
    }
}
