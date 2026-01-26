package org.jaudiotagger.audio.asf.util;

import java.util.Iterator;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.ContainerType;
import org.jaudiotagger.audio.asf.data.MetadataContainer;
import org.jaudiotagger.audio.asf.data.MetadataContainerFactory;
import org.jaudiotagger.audio.asf.data.MetadataDescriptor;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.asf.AsfFieldKey;
import org.jaudiotagger.tag.asf.AsfTag;
import org.jaudiotagger.tag.asf.AsfTagBannerField;
import org.jaudiotagger.tag.asf.AsfTagCoverField;
import org.jaudiotagger.tag.asf.AsfTagField;
import org.jaudiotagger.tag.asf.AsfTagTextField;
import org.jaudiotagger.tag.reference.GenreTypes;

/* loaded from: classes3.dex */
public final class TagConverter {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public static void assignCommonTagValues(Tag tag, MetadataContainer metadataContainer) throws IllegalArgumentException {
        if (!Utils.isBlank(tag.getFirst(FieldKey.ALBUM))) {
            MetadataDescriptor metadataDescriptor = new MetadataDescriptor(metadataContainer.getContainerType(), AsfFieldKey.ALBUM.getFieldName(), 0);
            metadataDescriptor.setStringValue(tag.getFirst(FieldKey.ALBUM));
            metadataContainer.removeDescriptorsByName(metadataDescriptor.getName());
            metadataContainer.addDescriptor(metadataDescriptor);
        } else {
            metadataContainer.removeDescriptorsByName(AsfFieldKey.ALBUM.getFieldName());
        }
        if (!Utils.isBlank(tag.getFirst(FieldKey.TRACK))) {
            MetadataDescriptor metadataDescriptor2 = new MetadataDescriptor(metadataContainer.getContainerType(), AsfFieldKey.TRACK.getFieldName(), 0);
            metadataDescriptor2.setStringValue(tag.getFirst(FieldKey.TRACK));
            metadataContainer.removeDescriptorsByName(metadataDescriptor2.getName());
            metadataContainer.addDescriptor(metadataDescriptor2);
        } else {
            metadataContainer.removeDescriptorsByName(AsfFieldKey.TRACK.getFieldName());
        }
        if (!Utils.isBlank(tag.getFirst(FieldKey.YEAR))) {
            MetadataDescriptor metadataDescriptor3 = new MetadataDescriptor(metadataContainer.getContainerType(), AsfFieldKey.YEAR.getFieldName(), 0);
            metadataDescriptor3.setStringValue(tag.getFirst(FieldKey.YEAR));
            metadataContainer.removeDescriptorsByName(metadataDescriptor3.getName());
            metadataContainer.addDescriptor(metadataDescriptor3);
        } else {
            metadataContainer.removeDescriptorsByName(AsfFieldKey.YEAR.getFieldName());
        }
        if (!Utils.isBlank(tag.getFirst(FieldKey.GENRE))) {
            MetadataDescriptor metadataDescriptor4 = new MetadataDescriptor(metadataContainer.getContainerType(), AsfFieldKey.GENRE.getFieldName(), 0);
            metadataDescriptor4.setStringValue(tag.getFirst(FieldKey.GENRE));
            metadataContainer.removeDescriptorsByName(metadataDescriptor4.getName());
            metadataContainer.addDescriptor(metadataDescriptor4);
            Integer idForName = GenreTypes.getInstanceOf().getIdForName(tag.getFirst(FieldKey.GENRE));
            if (idForName != null) {
                MetadataDescriptor metadataDescriptor5 = new MetadataDescriptor(metadataContainer.getContainerType(), AsfFieldKey.GENRE_ID.getFieldName(), 0);
                metadataDescriptor5.setStringValue("(" + idForName + ")");
                metadataContainer.removeDescriptorsByName(metadataDescriptor5.getName());
                metadataContainer.addDescriptor(metadataDescriptor5);
                return;
            }
            metadataContainer.removeDescriptorsByName(AsfFieldKey.GENRE_ID.getFieldName());
            return;
        }
        metadataContainer.removeDescriptorsByName(AsfFieldKey.GENRE.getFieldName());
        metadataContainer.removeDescriptorsByName(AsfFieldKey.GENRE_ID.getFieldName());
    }

    public static AsfTag createTagOf(AsfHeader asfHeader) {
        TagField asfTagTextField;
        AsfTag asfTag = new AsfTag(true);
        for (int i = 0; i < ContainerType.values().length; i++) {
            MetadataContainer metadataContainerFindMetadataContainer = asfHeader.findMetadataContainer(ContainerType.values()[i]);
            if (metadataContainerFindMetadataContainer != null) {
                for (MetadataDescriptor metadataDescriptor : metadataContainerFindMetadataContainer.getDescriptors()) {
                    if (metadataDescriptor.getType() == 1) {
                        if (metadataDescriptor.getName().equals(AsfFieldKey.COVER_ART.getFieldName())) {
                            asfTagTextField = new AsfTagCoverField(metadataDescriptor);
                        } else if (metadataDescriptor.getName().equals(AsfFieldKey.BANNER_IMAGE.getFieldName())) {
                            asfTagTextField = new AsfTagBannerField(metadataDescriptor);
                        } else {
                            asfTagTextField = new AsfTagField(metadataDescriptor);
                        }
                    } else {
                        asfTagTextField = new AsfTagTextField(metadataDescriptor);
                    }
                    asfTag.addField(asfTagTextField);
                }
            }
        }
        return asfTag;
    }

    public static MetadataContainer[] distributeMetadata(AsfTag asfTag) throws IllegalArgumentException {
        Iterator<AsfTagField> asfFields = asfTag.getAsfFields();
        MetadataContainer[] metadataContainerArrCreateContainers = MetadataContainerFactory.getInstance().createContainers(ContainerType.getOrdered());
        while (asfFields.hasNext()) {
            AsfTagField next = asfFields.next();
            boolean z = false;
            for (int i = 0; !z && i < metadataContainerArrCreateContainers.length; i++) {
                if (ContainerType.areInCorrectOrder(metadataContainerArrCreateContainers[i].getContainerType(), AsfFieldKey.getAsfFieldKey(next.getId()).getHighestContainer()) && metadataContainerArrCreateContainers[i].isAddSupported(next.getDescriptor())) {
                    metadataContainerArrCreateContainers[i].addDescriptor(next.getDescriptor());
                    z = true;
                }
            }
        }
        return metadataContainerArrCreateContainers;
    }

    private TagConverter() {
    }
}
