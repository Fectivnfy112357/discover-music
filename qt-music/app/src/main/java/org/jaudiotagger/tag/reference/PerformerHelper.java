package org.jaudiotagger.tag.reference;

/* loaded from: classes3.dex */
public class PerformerHelper {
    public static String formatForId3(String str, String str2) {
        return str2.toLowerCase() + (char) 0 + str;
    }

    public static String formatForNonId3(String str, String str2) {
        return str + " (" + str2.toLowerCase() + ")";
    }
}
