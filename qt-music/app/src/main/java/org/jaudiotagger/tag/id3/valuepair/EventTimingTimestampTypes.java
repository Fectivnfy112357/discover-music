package org.jaudiotagger.tag.id3.valuepair;

import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class EventTimingTimestampTypes extends AbstractIntStringValuePair {
    public static final int TIMESTAMP_KEY_FIELD_SIZE = 1;
    private static EventTimingTimestampTypes eventTimingTimestampTypes;

    public static EventTimingTimestampTypes getInstanceOf() {
        if (eventTimingTimestampTypes == null) {
            eventTimingTimestampTypes = new EventTimingTimestampTypes();
        }
        return eventTimingTimestampTypes;
    }

    private EventTimingTimestampTypes() {
        this.idToValue.put(1, "Absolute time using MPEG [MPEG] frames as unit");
        this.idToValue.put(2, "Absolute time using milliseconds as unit");
        createMaps();
    }
}
