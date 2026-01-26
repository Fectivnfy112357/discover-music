package org.jaudiotagger.tag.id3.valuepair;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class ImageFormats {
    public static final String MIME_TYPE_BMP = "image/bmp";
    public static final String MIME_TYPE_GIF = "image/gif";
    public static final String MIME_TYPE_JPEG = "image/jpeg";
    public static final String MIME_TYPE_JPG = "image/jpg";
    public static final String MIME_TYPE_PDF = "image/pdf";
    public static final String MIME_TYPE_PICT = "image/x-pict";
    public static final String MIME_TYPE_PNG = "image/png";
    public static final String MIME_TYPE_TIFF = "image/tiff";
    public static final String V22_BMP_FORMAT = "BMP";
    public static final String V22_GIF_FORMAT = "GIF";
    public static final String V22_JPG_FORMAT = "JPG";
    public static final String V22_PDF_FORMAT = "PDF";
    public static final String V22_PIC_FORMAT = "PIC";
    public static final String V22_PNG_FORMAT = "PNG";
    public static final String V22_TIF_FORMAT = "TIF";
    private static Map<String, String> imageFormatsToMimeType = new HashMap();
    private static Map<String, String> imageMimeTypeToFormat = new HashMap();

    static {
        imageFormatsToMimeType.put(V22_JPG_FORMAT, "image/jpeg");
        imageFormatsToMimeType.put(V22_PNG_FORMAT, "image/png");
        imageFormatsToMimeType.put(V22_GIF_FORMAT, MIME_TYPE_GIF);
        imageFormatsToMimeType.put(V22_BMP_FORMAT, "image/bmp");
        imageFormatsToMimeType.put(V22_TIF_FORMAT, MIME_TYPE_TIFF);
        imageFormatsToMimeType.put(V22_PDF_FORMAT, MIME_TYPE_PDF);
        imageFormatsToMimeType.put("PIC", MIME_TYPE_PICT);
        for (String str : imageFormatsToMimeType.keySet()) {
            imageMimeTypeToFormat.put(imageFormatsToMimeType.get(str), str);
        }
        imageMimeTypeToFormat.put("image/jpg", V22_JPG_FORMAT);
    }

    public static String getMimeTypeForFormat(String str) {
        return imageFormatsToMimeType.get(str);
    }

    public static String getFormatForMimeType(String str) {
        return imageMimeTypeToFormat.get(str);
    }

    public static boolean binaryDataIsPngFormat(byte[] bArr) {
        return bArr.length >= 4 && 137 == (bArr[0] & 255) && 80 == (bArr[1] & 255) && 78 == (bArr[2] & 255) && 71 == (bArr[3] & 255);
    }

    public static boolean binaryDataIsJpgFormat(byte[] bArr) {
        return bArr.length >= 4 && 255 == (bArr[0] & 255) && 216 == (bArr[1] & 255) && 255 == (bArr[2] & 255) && 219 <= (bArr[3] & 255);
    }

    public static boolean binaryDataIsGifFormat(byte[] bArr) {
        return bArr.length >= 3 && 71 == (bArr[0] & 255) && 73 == (bArr[1] & 255) && 70 == (bArr[2] & 255);
    }

    public static boolean binaryDataIsBmpFormat(byte[] bArr) {
        return bArr.length >= 2 && 66 == (bArr[0] & 255) && 77 == (bArr[1] & 255);
    }

    public static boolean binaryDataIsPdfFormat(byte[] bArr) {
        return bArr.length >= 4 && 37 == (bArr[0] & 255) && 80 == (bArr[1] & 255) && 68 == (bArr[2] & 255) && 70 == (bArr[3] & 255);
    }

    public static boolean binaryDataIsTiffFormat(byte[] bArr) {
        if (bArr.length < 4) {
            return false;
        }
        byte b = bArr[0];
        return (73 == (b & 255) && 73 == (bArr[1] & 255) && 42 == (bArr[2] & 255) && (bArr[3] & 255) == 0) || (77 == (b & 255) && 77 == (bArr[1] & 255) && (bArr[2] & 255) == 0 && 42 == (bArr[3] & 255));
    }

    public static boolean isPortableFormat(byte[] bArr) {
        return binaryDataIsPngFormat(bArr) || binaryDataIsJpgFormat(bArr) || binaryDataIsGifFormat(bArr);
    }

    public static String getMimeTypeForBinarySignature(byte[] bArr) {
        if (binaryDataIsPngFormat(bArr)) {
            return "image/png";
        }
        if (binaryDataIsJpgFormat(bArr)) {
            return "image/jpeg";
        }
        if (binaryDataIsGifFormat(bArr)) {
            return MIME_TYPE_GIF;
        }
        if (binaryDataIsBmpFormat(bArr)) {
            return "image/bmp";
        }
        if (binaryDataIsPdfFormat(bArr)) {
            return MIME_TYPE_PDF;
        }
        if (binaryDataIsTiffFormat(bArr)) {
            return MIME_TYPE_TIFF;
        }
        return null;
    }
}
