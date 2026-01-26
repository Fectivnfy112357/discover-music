package org.jaudiotagger.tag.id3.valuepair;

import org.jaudiotagger.tag.datatype.AbstractIntStringValuePair;

/* loaded from: classes3.dex */
public class ReceivedAsTypes extends AbstractIntStringValuePair {
    public static final int RECEIVED_AS_FIELD_SIZE = 1;
    private static ReceivedAsTypes receivedAsTypes;

    public static ReceivedAsTypes getInstanceOf() {
        if (receivedAsTypes == null) {
            receivedAsTypes = new ReceivedAsTypes();
        }
        return receivedAsTypes;
    }

    private ReceivedAsTypes() {
        this.idToValue.put(0, "Other");
        this.idToValue.put(1, "Standard CD album with other songs");
        this.idToValue.put(2, "Compressed audio on CD");
        this.idToValue.put(3, "File over the Internet");
        this.idToValue.put(4, "Stream over the Internet");
        this.idToValue.put(5, "As note sheets");
        this.idToValue.put(6, "As note sheets in a book with other sheets");
        this.idToValue.put(7, "Music on other media");
        this.idToValue.put(8, "Non-musical merchandise");
        createMaps();
    }
}
