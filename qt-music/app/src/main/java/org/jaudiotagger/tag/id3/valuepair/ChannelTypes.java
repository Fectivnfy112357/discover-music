package org.jaudiotagger.tag.id3.valuepair;

import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class ChannelTypes extends AbstractIntStringValuePair {
    private static ChannelTypes channelTypes;

    public static ChannelTypes getInstanceOf() {
        if (channelTypes == null) {
            channelTypes = new ChannelTypes();
        }
        return channelTypes;
    }

    private ChannelTypes() {
        this.idToValue.put(0, "Other");
        this.idToValue.put(1, "Master volume");
        this.idToValue.put(2, "Front right");
        this.idToValue.put(3, "Front left");
        this.idToValue.put(4, "Back right");
        this.idToValue.put(5, "Back left");
        this.idToValue.put(6, "Front centre");
        this.idToValue.put(7, "Back centre");
        this.idToValue.put(8, "Subwoofer");
        createMaps();
    }
}
