package org.jaudiotagger.tag.id3.valuepair;

import com.umeng.analytics.pro.f;
import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class SynchronisedLyricsContentType extends AbstractIntStringValuePair {
    public static final int CONTENT_KEY_FIELD_SIZE = 1;
    private static SynchronisedLyricsContentType eventTimingTypes;

    public static SynchronisedLyricsContentType getInstanceOf() {
        if (eventTimingTypes == null) {
            eventTimingTypes = new SynchronisedLyricsContentType();
        }
        return eventTimingTypes;
    }

    private SynchronisedLyricsContentType() {
        this.idToValue.put(0, "other");
        this.idToValue.put(1, "lyrics");
        this.idToValue.put(2, "text transcription");
        this.idToValue.put(3, "movement/part name");
        this.idToValue.put(4, f.ax);
        this.idToValue.put(5, "chord");
        this.idToValue.put(6, "trivia");
        this.idToValue.put(7, "URLs to webpages");
        this.idToValue.put(8, "URLs to images");
        createMaps();
    }
}
