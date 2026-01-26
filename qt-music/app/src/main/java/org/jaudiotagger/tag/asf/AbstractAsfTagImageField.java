package org.jaudiotagger.tag.asf;

import org.jaudiotagger.audio.asf.data.MetadataDescriptor;

/* loaded from: classes3.dex */
abstract class AbstractAsfTagImageField extends AsfTagField {
    public abstract int getImageDataSize();

    public abstract byte[] getRawImageData();

    public AbstractAsfTagImageField(AsfFieldKey asfFieldKey) {
        super(asfFieldKey);
    }

    public AbstractAsfTagImageField(MetadataDescriptor metadataDescriptor) {
        super(metadataDescriptor);
    }

    public AbstractAsfTagImageField(String str) {
        super(str);
    }
}
