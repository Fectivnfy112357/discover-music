package org.jaudiotagger.tag.id3.valuepair;

import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class InterpolationTypes extends AbstractIntStringValuePair {
    private static InterpolationTypes interpolationTypes;

    public static InterpolationTypes getInstanceOf() {
        if (interpolationTypes == null) {
            interpolationTypes = new InterpolationTypes();
        }
        return interpolationTypes;
    }

    private InterpolationTypes() {
        this.idToValue.put(0, "Band");
        this.idToValue.put(1, "Linear");
        createMaps();
    }
}
