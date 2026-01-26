package org.jaudiotagger.tag.reference;

import androidx.exifinterface.media.ExifInterface;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 org.jaudiotagger.tag.reference.MusicalKey, still in use, count: 1, list:
  (r0v0 org.jaudiotagger.tag.reference.MusicalKey) from 0x0091: INVOKE (r0v1 java.util.EnumSet) = 
  (r0v0 org.jaudiotagger.tag.reference.MusicalKey)
  (wrap:org.jaudiotagger.tag.reference.MusicalKey[]:0x008d: FILLED_NEW_ARRAY 
  (r4v0 org.jaudiotagger.tag.reference.MusicalKey)
  (r5v0 org.jaudiotagger.tag.reference.MusicalKey)
  (r6v0 org.jaudiotagger.tag.reference.MusicalKey)
  (r7v0 org.jaudiotagger.tag.reference.MusicalKey)
  (r8v0 org.jaudiotagger.tag.reference.MusicalKey)
  (r9v0 org.jaudiotagger.tag.reference.MusicalKey)
 A[WRAPPED] (LINE:43) elemType: org.jaudiotagger.tag.reference.MusicalKey)
 STATIC call: java.util.EnumSet.of(java.lang.Enum, java.lang.Enum[]):java.util.EnumSet A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>, E extends java.lang.Enum<E>[]):java.util.EnumSet<E extends java.lang.Enum<E>> VARARG (c), VARARG_CALL] (LINE:43)
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:252)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:180)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes3.dex */
public final class MusicalKey {
    NOTE_A(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS),
    NOTE_B("B"),
    NOTE_C("C"),
    NOTE_D("D"),
    NOTE_E(ExifInterface.LONGITUDE_EAST),
    NOTE_F("F"),
    NOTE_G("G"),
    FLAT("b"),
    SHARP("#"),
    MINOR("m"),
    OFF_KEY("o");

    private static final int MAX_KEY_LENGTH = 3;
    private static final HashMap<String, MusicalKey> groundKeyMap;
    private static final HashMap<String, MusicalKey> halfKeyMap;
    private String value;

    public static MusicalKey valueOf(String str) {
        return (MusicalKey) Enum.valueOf(MusicalKey.class, str);
    }

    public static MusicalKey[] values() {
        return (MusicalKey[]) $VALUES.clone();
    }

    static {
        EnumSet enumSetOf = EnumSet.of(new MusicalKey(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS), new MusicalKey("B"), new MusicalKey("C"), new MusicalKey("D"), new MusicalKey(ExifInterface.LONGITUDE_EAST), new MusicalKey("F"), new MusicalKey("G"));
        groundKeyMap = new HashMap<>(values().length);
        Iterator it = enumSetOf.iterator();
        while (it.hasNext()) {
            MusicalKey musicalKey = (MusicalKey) it.next();
            groundKeyMap.put(musicalKey.getValue(), musicalKey);
        }
        EnumSet enumSetOf2 = EnumSet.of(FLAT, SHARP, MINOR);
        halfKeyMap = new HashMap<>(values().length);
        Iterator it2 = enumSetOf2.iterator();
        while (it2.hasNext()) {
            MusicalKey musicalKey2 = (MusicalKey) it2.next();
            halfKeyMap.put(musicalKey2.getValue(), musicalKey2);
        }
    }

    private MusicalKey(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }

    public static boolean isValid(String str) {
        if (str == null || str.length() > 3 || str.length() == 0) {
            return false;
        }
        if (str.length() == 1 && str.equals(OFF_KEY.getValue())) {
            return true;
        }
        if (!groundKeyMap.containsKey(str.substring(0, 1))) {
            return false;
        }
        if ((str.length() == 2 || str.length() == 3) && !halfKeyMap.containsKey(str.substring(1, 2))) {
            return false;
        }
        return str.length() != 3 || str.substring(2, 3).equals(MINOR.getValue());
    }
}
