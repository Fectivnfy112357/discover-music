package org.jaudiotagger.tag.id3.valuepair;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public enum StandardIPLSKey {
    ENGINEER("engineer"),
    MIXER("mix"),
    DJMIXER("DJ-mix"),
    PRODUCER("producer"),
    ARRANGER("arranger");

    private static final Map<String, StandardIPLSKey> lookup = new HashMap();
    private String key;

    static {
        Iterator it = EnumSet.allOf(StandardIPLSKey.class).iterator();
        while (it.hasNext()) {
            StandardIPLSKey standardIPLSKey = (StandardIPLSKey) it.next();
            lookup.put(standardIPLSKey.getKey(), standardIPLSKey);
        }
    }

    StandardIPLSKey(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    public static StandardIPLSKey get(String str) {
        return lookup.get(str);
    }

    public static boolean isKey(String str) {
        return get(str) != null;
    }
}
