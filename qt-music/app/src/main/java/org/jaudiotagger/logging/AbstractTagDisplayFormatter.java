package org.jaudiotagger.logging;

import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import com.umeng.analytics.pro.bm;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class AbstractTagDisplayFormatter {
    private static HashMap<String, String> hexBinaryMap;
    protected int level;

    public abstract void addElement(String str, int i);

    public abstract void addElement(String str, String str2);

    public abstract void addElement(String str, boolean z);

    public abstract void closeHeadingElement(String str);

    public abstract void openHeadingElement(String str, int i);

    public abstract void openHeadingElement(String str, String str2);

    public abstract void openHeadingElement(String str, boolean z);

    public abstract String toString();

    static {
        HashMap<String, String> map = new HashMap<>();
        hexBinaryMap = map;
        map.put(SessionDescription.SUPPORTED_SDP_VERSION, "0000");
        hexBinaryMap.put("1", "0001");
        hexBinaryMap.put(ExifInterface.GPS_MEASUREMENT_2D, "0010");
        hexBinaryMap.put(ExifInterface.GPS_MEASUREMENT_3D, "0011");
        hexBinaryMap.put("4", "0100");
        hexBinaryMap.put("5", "0101");
        hexBinaryMap.put("6", "0110");
        hexBinaryMap.put("7", "0111");
        hexBinaryMap.put("8", "1000");
        hexBinaryMap.put("9", "1001");
        hexBinaryMap.put("a", "1010");
        hexBinaryMap.put("b", "1011");
        hexBinaryMap.put(bm.aJ, "1100");
        hexBinaryMap.put("d", "1101");
        hexBinaryMap.put("e", "1110");
        hexBinaryMap.put("f", "1111");
    }

    public static String displayAsBinary(byte b) {
        String str;
        String strSubstring;
        String strSubstring2;
        String hexString = Integer.toHexString(b);
        String str2 = "";
        try {
            if (hexString.length() == 8) {
                strSubstring = hexString.substring(6, 7);
                strSubstring2 = hexString.substring(7, 8);
            } else if (hexString.length() == 2) {
                strSubstring = hexString.substring(0, 1);
                strSubstring2 = hexString.substring(1, 2);
            } else {
                if (hexString.length() != 1) {
                    str = "";
                    return hexBinaryMap.get(str2) + hexBinaryMap.get(str);
                }
                strSubstring = SessionDescription.SUPPORTED_SDP_VERSION;
                strSubstring2 = hexString.substring(0, 1);
            }
            str = strSubstring2;
            str2 = strSubstring;
            return hexBinaryMap.get(str2) + hexBinaryMap.get(str);
        } catch (StringIndexOutOfBoundsException unused) {
            return "";
        }
    }
}
