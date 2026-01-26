package org.jaudiotagger.utils;

import com.umeng.commonsdk.statistics.UMErrorCode;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class FileTypeUtil {
    private static final int BUFFER_SIZE = 4096;
    private static final int MAX_SIGNATURE_SIZE = 8;
    private static Map<String, String> extensionMap;
    private static final Integer[] mp3v1Sig_1;
    private static final Integer[] mp3v1Sig_2;
    private static final Integer[] mp3v1Sig_3;
    private static final Integer[] mp3v1Sig_4;
    private static final Integer[] mp3v2Sig;
    private static final Integer[] mp4Sig;
    private static Map<String, Integer[]> signatureMap;

    static {
        Integer[] numArr = {73, 68, 51};
        mp3v2Sig = numArr;
        Integer[] numArr2 = {255, 243};
        mp3v1Sig_1 = numArr2;
        Integer[] numArr3 = {255, 250};
        mp3v1Sig_2 = numArr3;
        Integer[] numArr4 = {255, 242};
        mp3v1Sig_3 = numArr4;
        Integer[] numArr5 = {255, 251};
        mp3v1Sig_4 = numArr5;
        Integer[] numArr6 = {0, 0, 0, null, 102, 116, Integer.valueOf(UMErrorCode.E_UM_BE_EMPTY_URL_PATH), Integer.valueOf(UMErrorCode.E_UM_BE_DEFLATE_FAILED)};
        mp4Sig = numArr6;
        HashMap map = new HashMap();
        signatureMap = map;
        map.put("MP3IDv2", numArr);
        signatureMap.put("MP3IDv1_1", numArr2);
        signatureMap.put("MP3IDv1_2", numArr3);
        signatureMap.put("MP3IDv1_3", numArr4);
        signatureMap.put("MP3IDv1_4", numArr5);
        signatureMap.put("MP4", numArr6);
        HashMap map2 = new HashMap();
        extensionMap = map2;
        map2.put("MP3IDv2", "mp3");
        extensionMap.put("MP3IDv1_1", "mp3");
        extensionMap.put("MP3IDv1_2", "mp3");
        extensionMap.put("MP3IDv1_3", "mp3");
        extensionMap.put("MP3IDv1_4", "mp3");
        extensionMap.put("MP4", "m4a");
        extensionMap.put("UNKNOWN", "");
    }

    public static String getMagicFileType(File file) throws IOException {
        byte[] bArr = new byte[4096];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            int i = fileInputStream.read(bArr, 0, 4096);
            int i2 = i;
            while (i < 8 && i2 > 0) {
                i2 = fileInputStream.read(bArr, i, 4096 - i);
                i += i2;
            }
            String str = "UNKNOWN";
            Iterator<String> it = signatureMap.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (matchesSignature(signatureMap.get(next), bArr, i)) {
                    str = next;
                    break;
                }
            }
            return str;
        } finally {
            fileInputStream.close();
        }
    }

    public static String getMagicExt(String str) {
        return extensionMap.get(str);
    }

    private static boolean matchesSignature(Integer[] numArr, byte[] bArr, int i) {
        if (i < numArr.length) {
            return false;
        }
        for (int i2 = 0; i2 < numArr.length; i2++) {
            Integer num = numArr[i2];
            if (num != null && num.intValue() != (bArr[i2] & 255)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] strArr) throws IOException {
        String magicFileType = getMagicFileType(new File("C:/Users/keerthi/Dropbox/Works/Java/github/GaanaExtractor/workspace/jaudiotagger/testm4a"));
        System.out.println("File type: " + magicFileType);
        System.out.println("File Extension: " + getMagicExt(magicFileType));
    }
}
