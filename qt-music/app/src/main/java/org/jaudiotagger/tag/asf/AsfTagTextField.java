package org.jaudiotagger.tag.asf;

import java.nio.charset.Charset;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.MetadataDescriptor;
import org.jaudiotagger.audio.asf.util.Utils;
import org.jaudiotagger.tag.TagTextField;

/* loaded from: classes3.dex */
public class AsfTagTextField extends AsfTagField implements TagTextField {
    public AsfTagTextField(AsfFieldKey asfFieldKey, String str) throws IllegalArgumentException {
        super(asfFieldKey);
        this.toWrap.setString(str);
    }

    public AsfTagTextField(MetadataDescriptor metadataDescriptor) {
        super(metadataDescriptor);
        if (metadataDescriptor.getType() == 1) {
            throw new IllegalArgumentException("Cannot interpret binary as string.");
        }
    }

    public AsfTagTextField(String str, String str2) throws IllegalArgumentException {
        super(str);
        this.toWrap.setString(str2);
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public String getContent() {
        return getDescriptor().getString();
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public Charset getEncoding() {
        return AsfHeader.ASF_CHARSET;
    }

    @Override // org.jaudiotagger.tag.asf.AsfTagField, org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return Utils.isBlank(getContent());
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setContent(String str) throws IllegalArgumentException {
        getDescriptor().setString(str);
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setEncoding(Charset charset) {
        if (!AsfHeader.ASF_CHARSET.equals(charset)) {
            throw new IllegalArgumentException("Only UTF-16LE is possible with ASF.");
        }
    }
}
