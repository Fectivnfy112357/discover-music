package org.jaudiotagger.tag.id3;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;

/* loaded from: classes3.dex */
public class AggregatedFrame implements TagTextField {
    protected Set<AbstractID3v2Frame> frames = new LinkedHashSet();

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
    }

    @Override // org.jaudiotagger.tag.TagField
    public void isBinary(boolean z) {
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return false;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isCommon() {
        return true;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return false;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setContent(String str) {
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setEncoding(Charset charset) {
    }

    public void addFrame(AbstractID3v2Frame abstractID3v2Frame) {
        this.frames.add(abstractID3v2Frame);
    }

    public Set<AbstractID3v2Frame> getFrames() {
        return this.frames;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        Iterator<AbstractID3v2Frame> it = this.frames.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getContent());
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public Charset getEncoding() {
        return TextEncoding.getInstanceOf().getCharsetForId(this.frames.iterator().next().getBody().getTextEncoding());
    }

    @Override // org.jaudiotagger.tag.TagField
    public String getId() {
        StringBuilder sb = new StringBuilder();
        Iterator<AbstractID3v2Frame> it = this.frames.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getId());
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.tag.TagField
    public byte[] getRawContent() throws UnsupportedEncodingException {
        throw new UnsupportedEncodingException();
    }
}
