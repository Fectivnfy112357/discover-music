package org.jaudiotagger.tag.asf;

import org.jaudiotagger.audio.asf.data.ContainerType;
import org.jaudiotagger.audio.asf.data.MetadataDescriptor;

/* loaded from: classes3.dex */
public class AsfTagBannerField extends AbstractAsfTagImageField {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public AsfTagBannerField() {
        super(AsfFieldKey.BANNER_IMAGE);
    }

    public AsfTagBannerField(MetadataDescriptor metadataDescriptor) {
        super(metadataDescriptor);
    }

    public AsfTagBannerField(byte[] bArr) throws IllegalArgumentException {
        super(new MetadataDescriptor(ContainerType.CONTENT_BRANDING, AsfFieldKey.BANNER_IMAGE.getFieldName(), 1));
        this.toWrap.setBinaryValue(bArr);
    }

    @Override // org.jaudiotagger.tag.asf.AbstractAsfTagImageField
    public int getImageDataSize() {
        return this.toWrap.getRawDataSize();
    }

    @Override // org.jaudiotagger.tag.asf.AbstractAsfTagImageField
    public byte[] getRawImageData() {
        return getRawContent();
    }
}
