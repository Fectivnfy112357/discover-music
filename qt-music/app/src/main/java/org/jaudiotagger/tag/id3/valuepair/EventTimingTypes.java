package org.jaudiotagger.tag.id3.valuepair;

import com.facebook.imageutils.JfifUtil;
import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;
import org.mozilla.universalchardet.prober.HebrewProber;

/* loaded from: classes3.dex */
public class EventTimingTypes extends AbstractIntStringValuePair {
    private static EventTimingTypes eventTimingTypes;

    public static EventTimingTypes getInstanceOf() {
        if (eventTimingTypes == null) {
            eventTimingTypes = new EventTimingTypes();
        }
        return eventTimingTypes;
    }

    private EventTimingTypes() {
        this.idToValue.put(0, "Padding (has no meaning)");
        this.idToValue.put(1, "End of initial silence");
        this.idToValue.put(2, "Intro start");
        this.idToValue.put(3, "Main part start");
        this.idToValue.put(4, "Outro start");
        this.idToValue.put(5, "Outro end");
        this.idToValue.put(6, "Verse start");
        this.idToValue.put(7, "Refrain start");
        this.idToValue.put(8, "Interlude start");
        this.idToValue.put(9, "Theme start");
        this.idToValue.put(10, "Variation start");
        this.idToValue.put(11, "Key change");
        this.idToValue.put(12, "Time change");
        this.idToValue.put(13, "Momentary unwanted noise (Snap, Crackle & Pop)");
        this.idToValue.put(14, "Sustained noise");
        this.idToValue.put(15, "Sustained noise end");
        this.idToValue.put(16, "Intro end");
        this.idToValue.put(17, "Main part end");
        this.idToValue.put(18, "Verse end");
        this.idToValue.put(19, "Refrain end");
        this.idToValue.put(20, "Theme end");
        this.idToValue.put(21, "Profanity");
        this.idToValue.put(22, "Profanity end");
        this.idToValue.put(224, "Not predefined synch 0");
        this.idToValue.put(Integer.valueOf(JfifUtil.MARKER_APP1), "Not predefined synch 1");
        this.idToValue.put(226, "Not predefined synch 2");
        this.idToValue.put(227, "Not predefined synch 3");
        this.idToValue.put(228, "Not predefined synch 4");
        this.idToValue.put(229, "Not predefined synch 5");
        this.idToValue.put(230, "Not predefined synch 6");
        this.idToValue.put(231, "Not predefined synch 7");
        this.idToValue.put(232, "Not predefined synch 8");
        this.idToValue.put(233, "Not predefined synch 9");
        this.idToValue.put(Integer.valueOf(HebrewProber.FINAL_KAF), "Not predefined synch A");
        this.idToValue.put(Integer.valueOf(HebrewProber.NORMAL_KAF), "Not predefined synch B");
        this.idToValue.put(236, "Not predefined synch C");
        this.idToValue.put(Integer.valueOf(HebrewProber.FINAL_MEM), "Not predefined synch D");
        this.idToValue.put(Integer.valueOf(HebrewProber.NORMAL_MEM), "Not predefined synch E");
        this.idToValue.put(239, "Not predefined synch F");
        this.idToValue.put(253, "Audio end (start of silence)");
        this.idToValue.put(254, "Audio file ends");
        createMaps();
    }
}
