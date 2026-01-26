package org.jaudiotagger.audio.asf.util;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.GregorianCalendar;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.logging.ErrorMessage;

/* loaded from: classes3.dex */
public class Utils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final long DIFF_BETWEEN_ASF_DATE_AND_JAVA_DATE = 11644470000000L;
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final int MAXIMUM_STRING_LENGTH_ALLOWED = 32766;

    public static void checkStringLengthNullSafe(String str) throws IllegalArgumentException {
        if (str != null && str.length() > MAXIMUM_STRING_LENGTH_ALLOWED) {
            throw new IllegalArgumentException(ErrorMessage.WMA_LENGTH_OF_STRING_IS_TOO_LARGE.getMsg(Integer.valueOf(str.length() * 2)));
        }
    }

    public static boolean isStringLengthValidNullSafe(String str) {
        return str == null || str.length() <= MAXIMUM_STRING_LENGTH_ALLOWED;
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        byte[] bArr = new byte[8192];
        long j2 = 0;
        while (j2 < j) {
            long j3 = j - j2;
            int i = inputStream.read(bArr, 0, j3 < PlaybackStateCompat.ACTION_PLAY_FROM_URI ? (int) j3 : 8192);
            if (i == -1) {
                throw new IOException("Inputstream has to continue for another " + j3 + " bytes.");
            }
            outputStream.write(bArr, 0, i);
            j2 += i;
        }
    }

    public static void flush(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public static byte[] getBytes(long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((j >>> (i2 * 8)) & 255);
        }
        return bArr;
    }

    public static byte[] getBytes(String str, Charset charset) {
        ByteBuffer byteBufferEncode = charset.encode(str);
        byte[] bArr = new byte[byteBufferEncode.limit()];
        byteBufferEncode.rewind();
        byteBufferEncode.get(bArr);
        return bArr;
    }

    public static GregorianCalendar getDateOf(BigInteger bigInteger) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(bigInteger.divide(new BigInteger("10")).longValue() - DIFF_BETWEEN_ASF_DATE_AND_JAVA_DATE));
        return gregorianCalendar;
    }

    public static boolean isBlank(String str) {
        if (str == null) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static BigInteger readBig64(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[8];
        byte[] bArr2 = new byte[8];
        if (inputStream.read(bArr) != 8) {
            throw new EOFException();
        }
        for (int i = 0; i < 8; i++) {
            bArr2[7 - i] = bArr[i];
        }
        return new BigInteger(bArr2);
    }

    public static byte[] readBinary(InputStream inputStream, long j) throws IOException {
        byte[] bArr = new byte[(int) j];
        inputStream.read(bArr);
        return bArr;
    }

    public static String readCharacterSizedString(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int uint16 = readUINT16(inputStream);
        int i = inputStream.read() | (inputStream.read() << 8);
        while (true) {
            if (i != 0) {
                sb.append((char) i);
                i = inputStream.read() | (inputStream.read() << 8);
            }
            if (i == 0 && sb.length() + 1 <= uint16) {
                break;
            }
        }
        if (uint16 != sb.length() + 1) {
            throw new IllegalStateException("Invalid Data for current interpretation");
        }
        return sb.toString();
    }

    public static String readFixedSizeUTF16Str(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        if (inputStream.read(bArr) == i) {
            if (i >= 2 && bArr[i - 1] == 0) {
                int i2 = i - 2;
                if (bArr[i2] == 0) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    bArr = bArr2;
                }
            }
            return new String(bArr, "UTF-16LE");
        }
        throw new IllegalStateException("Couldn't read the necessary amount of bytes.");
    }

    public static GUID readGUID(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Argument must not be null");
        }
        int[] iArr = new int[16];
        for (int i = 0; i < 16; i++) {
            iArr[i] = inputStream.read();
        }
        return new GUID(iArr);
    }

    public static int readUINT16(InputStream inputStream) throws IOException {
        return (inputStream.read() << 8) | inputStream.read();
    }

    public static long readUINT32(InputStream inputStream) throws IOException {
        long j = 0;
        for (int i = 0; i <= 24; i += 8) {
            j |= inputStream.read() << i;
        }
        return j;
    }

    public static long readUINT64(InputStream inputStream) throws IOException {
        long j = 0;
        for (int i = 0; i <= 56; i += 8) {
            j |= inputStream.read() << i;
        }
        return j;
    }

    public static String readUTF16LEStr(InputStream inputStream) throws IOException {
        int uint16 = readUINT16(inputStream);
        byte[] bArr = new byte[uint16];
        int i = inputStream.read(bArr);
        if (i == uint16 || (uint16 == 0 && i == -1)) {
            if (uint16 >= 2 && bArr[uint16 - 1] == 0) {
                int i2 = uint16 - 2;
                if (bArr[i2] == 0) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(bArr, 0, bArr2, 0, i2);
                    bArr = bArr2;
                }
            }
            return new String(bArr, AsfHeader.ASF_CHARSET.name());
        }
        throw new IllegalStateException("Invalid Data for current interpretation");
    }

    public static void writeUINT16(int i, OutputStream outputStream) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("positive value expected.");
        }
        byte[] bArr = new byte[2];
        for (int i2 = 0; i2 <= 8; i2 += 8) {
            bArr[i2 / 8] = (byte) ((i >> i2) & 255);
        }
        outputStream.write(bArr);
    }

    public static void writeUINT32(long j, OutputStream outputStream) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("positive value expected.");
        }
        byte[] bArr = new byte[4];
        for (int i = 0; i <= 24; i += 8) {
            bArr[i / 8] = (byte) ((j >> i) & 255);
        }
        outputStream.write(bArr);
    }

    public static void writeUINT64(long j, OutputStream outputStream) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("positive value expected.");
        }
        byte[] bArr = new byte[8];
        for (int i = 0; i <= 56; i += 8) {
            bArr[i / 8] = (byte) ((j >> i) & 255);
        }
        outputStream.write(bArr);
    }
}
