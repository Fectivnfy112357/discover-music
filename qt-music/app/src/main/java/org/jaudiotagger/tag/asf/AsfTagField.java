package org.jaudiotagger.tag.asf;

import org.jaudiotagger.audio.asf.data.MetadataDescriptor;
import org.jaudiotagger.tag.TagField;

/* loaded from: classes3.dex */
public class AsfTagField implements TagField, Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected MetadataDescriptor toWrap;

    public AsfTagField(AsfFieldKey asfFieldKey) {
        this.toWrap = new MetadataDescriptor(asfFieldKey.getHighestContainer(), asfFieldKey.getFieldName(), 0);
    }

    public AsfTagField(MetadataDescriptor metadataDescriptor) {
        this.toWrap = metadataDescriptor.createCopy();
    }

    public AsfTagField(String str) {
        this.toWrap = new MetadataDescriptor(AsfFieldKey.getAsfFieldKey(str).getHighestContainer(), str, 0);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public MetadataDescriptor getDescriptor() {
        return this.toWrap;
    }

    @Override // org.jaudiotagger.tag.TagField
    public String getId() {
        return this.toWrap.getName();
    }

    @Override // org.jaudiotagger.tag.TagField
    public byte[] getRawContent() {
        return this.toWrap.getRawData();
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return this.toWrap.getType() == 1;
    }

    @Override // org.jaudiotagger.tag.TagField
    public void isBinary(boolean z) throws IllegalArgumentException {
        if (!z && isBinary()) {
            throw new UnsupportedOperationException("No conversion supported.");
        }
        MetadataDescriptor metadataDescriptor = this.toWrap;
        metadataDescriptor.setBinaryValue(metadataDescriptor.getRawData());
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isCommon() {
        return AsfTag.COMMON_FIELDS.contains(AsfFieldKey.getAsfFieldKey(getId()));
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return this.toWrap.isEmpty();
    }

    @Override // org.jaudiotagger.tag.TagField
    public String toString() {
        return this.toWrap.getString();
    }
}
