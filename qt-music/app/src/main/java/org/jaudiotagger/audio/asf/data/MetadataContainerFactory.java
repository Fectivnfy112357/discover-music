package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;

/* loaded from: classes3.dex */
public final class MetadataContainerFactory {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final MetadataContainerFactory INSTANCE = new MetadataContainerFactory();

    public static MetadataContainerFactory getInstance() {
        return INSTANCE;
    }

    private MetadataContainerFactory() {
    }

    public MetadataContainer createContainer(ContainerType containerType) {
        return createContainer(containerType, 0L, BigInteger.ZERO);
    }

    public MetadataContainer createContainer(ContainerType containerType, long j, BigInteger bigInteger) {
        if (containerType == ContainerType.CONTENT_DESCRIPTION) {
            return new ContentDescription(j, bigInteger);
        }
        if (containerType == ContainerType.CONTENT_BRANDING) {
            return new ContentBranding(j, bigInteger);
        }
        return new MetadataContainer(containerType, j, bigInteger);
    }

    public MetadataContainer[] createContainers(ContainerType[] containerTypeArr) {
        int length = containerTypeArr.length;
        MetadataContainer[] metadataContainerArr = new MetadataContainer[length];
        for (int i = 0; i < length; i++) {
            metadataContainerArr[i] = createContainer(containerTypeArr[i]);
        }
        return metadataContainerArr;
    }
}
