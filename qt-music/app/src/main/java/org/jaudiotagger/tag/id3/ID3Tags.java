package org.jaudiotagger.tag.id3;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Logger;
import org.jaudiotagger.tag.TagException;

/* loaded from: classes3.dex */
public class ID3Tags {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");

    private ID3Tags() {
    }

    public static boolean isID3v22FrameIdentifier(String str) {
        return str.length() >= 3 && str.length() == 3 && ID3v22Frames.getInstanceOf().getIdToValueMap().containsKey(str);
    }

    public static boolean isID3v23FrameIdentifier(String str) {
        return str.length() >= 4 && ID3v23Frames.getInstanceOf().getIdToValueMap().containsKey(str.substring(0, 4));
    }

    public static boolean isID3v24FrameIdentifier(String str) {
        return str.length() >= 4 && ID3v24Frames.getInstanceOf().getIdToValueMap().containsKey(str.substring(0, 4));
    }

    public static long getWholeNumber(Object obj) {
        int iIntValue;
        if (obj instanceof String) {
            return Long.parseLong((String) obj);
        }
        if (obj instanceof Byte) {
            iIntValue = ((Byte) obj).byteValue();
        } else if (obj instanceof Short) {
            iIntValue = ((Short) obj).shortValue();
        } else if (obj instanceof Integer) {
            iIntValue = ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return ((Long) obj).longValue();
            }
            throw new IllegalArgumentException("Unsupported value class: " + obj.getClass().getName());
        }
        return iIntValue;
    }

    public static String convertFrameID22To23(String str) {
        if (str.length() < 3) {
            return null;
        }
        return ID3Frames.convertv22Tov23.get((String) str.subSequence(0, 3));
    }

    public static String convertFrameID22To24(String str) {
        String str2;
        if (str.length() < 3 || (str2 = ID3Frames.convertv22Tov23.get(str.substring(0, 3))) == null) {
            return null;
        }
        String str3 = ID3Frames.convertv23Tov24.get(str2);
        if (str3 != null) {
            return str3;
        }
        if (ID3v24Frames.getInstanceOf().getIdToValueMap().get(str2) != null) {
            return str2;
        }
        return null;
    }

    public static String convertFrameID23To22(String str) {
        if (str.length() >= 4 && ID3v23Frames.getInstanceOf().getIdToValueMap().containsKey(str)) {
            return ID3Frames.convertv23Tov22.get(str.substring(0, 4));
        }
        return null;
    }

    public static String convertFrameID23To24(String str) {
        if (str.length() >= 4 && ID3v23Frames.getInstanceOf().getIdToValueMap().containsKey(str)) {
            return ID3v24Frames.getInstanceOf().getIdToValueMap().containsKey(str) ? str : ID3Frames.convertv23Tov24.get(str.substring(0, 4));
        }
        return null;
    }

    public static String forceFrameID22To23(String str) {
        return ID3Frames.forcev22Tov23.get(str);
    }

    public static String forceFrameID23To22(String str) {
        return ID3Frames.forcev23Tov22.get(str);
    }

    public static String forceFrameID23To24(String str) {
        return ID3Frames.forcev23Tov24.get(str);
    }

    public static String forceFrameID24To23(String str) {
        return ID3Frames.forcev24Tov23.get(str);
    }

    public static String convertFrameID24To23(String str) {
        if (str.length() < 4) {
            return null;
        }
        String str2 = ID3Frames.convertv24Tov23.get(str);
        return (str2 == null && ID3v23Frames.getInstanceOf().getIdToValueMap().containsKey(str)) ? str : str2;
    }

    public static Object copyObject(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj.getClass().getConstructor(obj.getClass()).newInstance(obj);
        } catch (IllegalAccessException unused) {
            throw new IllegalArgumentException("IllegalAccessException: No access to run constructor to create copy" + obj.getClass().getName());
        } catch (InstantiationException unused2) {
            throw new IllegalArgumentException("InstantiationException: Unable to instantiate constructor to copy" + obj.getClass().getName());
        } catch (NoSuchMethodException unused3) {
            throw new IllegalArgumentException("NoSuchMethodException: Error finding constructor to create copy:" + obj.getClass().getName());
        } catch (InvocationTargetException e) {
            if (e.getCause() instanceof Error) {
                throw ((Error) e.getCause());
            }
            if (e.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e.getCause());
            }
            throw new IllegalArgumentException("InvocationTargetException: Unable to invoke constructor to create copy");
        }
    }

    public static long findNumber(String str) throws TagException {
        return findNumber(str, 0);
    }

    public static long findNumber(String str, int i) throws TagException {
        if (str == null) {
            throw new NullPointerException("String is null");
        }
        if (i < 0 || i >= str.length()) {
            throw new IndexOutOfBoundsException("Offset to image string is out of bounds: offset = " + i + ", string.length()" + str.length());
        }
        while (i < str.length() && ((str.charAt(i) < '0' || str.charAt(i) > '9') && str.charAt(i) != '-')) {
            i++;
        }
        int i2 = i + 1;
        while (i2 < str.length() && str.charAt(i2) >= '0' && str.charAt(i2) <= '9') {
            i2++;
        }
        if (i2 <= str.length() && i2 > i) {
            String strSubstring = str.substring(i, i2);
            if (strSubstring.equals("-")) {
                throw new TagException("Unable to find integer in string: " + str);
            }
            return Long.parseLong(strSubstring);
        }
        throw new TagException("Unable to find integer in string: " + str);
    }

    public static String stripChar(String str, char c) {
        if (str == null) {
            return null;
        }
        char[] cArr = new char[str.length()];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if (str.charAt(i2) != c) {
                cArr[i] = str.charAt(i2);
                i++;
            }
        }
        return new String(cArr, 0, i);
    }

    public static String truncate(String str, int i) {
        if (str != null && i >= 0) {
            return str.length() > i ? str.substring(0, i) : str;
        }
        return null;
    }
}
