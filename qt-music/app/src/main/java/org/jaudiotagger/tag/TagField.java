package org.jaudiotagger.tag;

import java.io.UnsupportedEncodingException;

/* loaded from: classes3.dex */
public interface TagField {
    void copyContent(TagField tagField);

    String getId();

    byte[] getRawContent() throws UnsupportedEncodingException;

    void isBinary(boolean z);

    boolean isBinary();

    boolean isCommon();

    boolean isEmpty();

    String toString();
}
