package org.jaudiotagger.logging;

/* loaded from: classes3.dex */
public class Hex {
    public static String asHex(long j) {
        String hexString = Long.toHexString(j);
        if (hexString.length() == 1) {
            return "0x0" + hexString;
        }
        return "0x" + hexString;
    }

    public static String asHex(int i) {
        return "0x" + Integer.toHexString(i);
    }

    public static String asHex(byte b) {
        return "0x" + Integer.toHexString(b);
    }

    public static String asDecAndHex(long j) {
        return j + " (" + asHex(j) + ")";
    }
}
