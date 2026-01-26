package org.jaudiotagger.tag;

import java.nio.charset.Charset;

/* loaded from: classes3.dex */
public interface TagTextField extends TagField {
    String getContent();

    Charset getEncoding();

    void setContent(String str);

    void setEncoding(Charset charset);
}
