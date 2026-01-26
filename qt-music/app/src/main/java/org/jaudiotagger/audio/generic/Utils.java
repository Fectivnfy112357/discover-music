package org.jaudiotagger.audio.generic;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.DataInput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UShort;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.utils.FileTypeUtil;

/* loaded from: classes3.dex */
public class Utils {
    public static int BITS_IN_BYTE_MULTIPLIER = 8;
    public static int KILOBYTE_MULTIPLIER = 1000;
    private static final int MAX_BASE_TEMP_FILENAME_LENGTH = 20;
    private static final Logger logger = Logger.getLogger("org.jaudiotagger.audio.generic.utils");

    public static boolean isOddLength(long j) {
        return (j & 1) != 0;
    }

    public static int u(byte b) {
        return b & 255;
    }

    public static int u(short s) {
        return s & UShort.MAX_VALUE;
    }

    public static long u(int i) {
        return i & 4294967295L;
    }

    public static String getExtension(File file) {
        String lowerCase = file.getName().toLowerCase();
        int iLastIndexOf = lowerCase.lastIndexOf(".");
        if (iLastIndexOf == -1) {
            return "";
        }
        return lowerCase.substring(iLastIndexOf + 1);
    }

    public static String getMagicExtension(File file) throws IOException {
        return FileTypeUtil.getMagicExt(FileTypeUtil.getMagicFileType(file));
    }

    public static long getLongLE(ByteBuffer byteBuffer, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < (i2 - i) + 1; i3++) {
            j += (byteBuffer.get(i + i3) & 255) << (i3 * 8);
        }
        return j;
    }

    public static long getLongBE(ByteBuffer byteBuffer, int i, int i2) {
        long j = 0;
        for (int i3 = 0; i3 < (i2 - i) + 1; i3++) {
            j += (byteBuffer.get(i2 - i3) & 255) << (i3 * 8);
        }
        return j;
    }

    public static int getIntLE(byte[] bArr) {
        return (int) getLongLE(ByteBuffer.wrap(bArr), 0, bArr.length - 1);
    }

    public static int getIntLE(byte[] bArr, int i, int i2) {
        return (int) getLongLE(ByteBuffer.wrap(bArr), i, i2);
    }

    public static int getIntBE(ByteBuffer byteBuffer, int i, int i2) {
        return (int) getLongBE(byteBuffer, i, i2);
    }

    public static short getShortBE(ByteBuffer byteBuffer, int i, int i2) {
        return (short) getIntBE(byteBuffer, i, i2);
    }

    public static byte[] getSizeBEInt32(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] getSizeBEInt16(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    public static byte[] getSizeLEInt32(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >>> 8) & 255), (byte) ((i >>> 16) & 255), (byte) (255 & (i >>> 24))};
    }

    public static String readPascalString(ByteBuffer byteBuffer) throws IOException {
        int iU = u(byteBuffer.get());
        byte[] bArr = new byte[iU];
        byteBuffer.get(bArr);
        return new String(bArr, 0, iU, StandardCharsets.ISO_8859_1);
    }

    public static String getString(ByteBuffer byteBuffer, int i, int i2, Charset charset) {
        byte[] bArr = new byte[i2];
        byteBuffer.position(byteBuffer.position() + i);
        byteBuffer.get(bArr);
        return new String(bArr, 0, i2, charset);
    }

    public static String getString(ByteBuffer byteBuffer, Charset charset) {
        int iRemaining = byteBuffer.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBuffer.get(bArr);
        return new String(bArr, 0, iRemaining, charset);
    }

    public static long readUint32(DataInput dataInput) throws IOException {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        dataInput.readFully(bArr, 4, 4);
        return ByteBuffer.wrap(bArr).getLong();
    }

    public static int readUint16(DataInput dataInput) throws IOException {
        byte[] bArr = {0, 0, 0, 0};
        dataInput.readFully(bArr, 2, 2);
        return ByteBuffer.wrap(bArr).getInt();
    }

    public static String readString(DataInput dataInput, int i) throws IOException {
        byte[] bArr = new byte[i];
        dataInput.readFully(bArr);
        return new String(bArr, StandardCharsets.US_ASCII);
    }

    public static String getBaseFilenameForTempFile(File file) {
        String minBaseFilenameAllowedForTempFile = getMinBaseFilenameAllowedForTempFile(file);
        return minBaseFilenameAllowedForTempFile.length() <= 20 ? minBaseFilenameAllowedForTempFile : minBaseFilenameAllowedForTempFile.substring(0, 20);
    }

    public static String getMinBaseFilenameAllowedForTempFile(File file) {
        String baseFilename = AudioFile.getBaseFilename(file);
        if (baseFilename.length() >= 3) {
            return baseFilename;
        }
        if (baseFilename.length() == 1) {
            return baseFilename + "000";
        }
        if (baseFilename.length() == 1) {
            return baseFilename + "00";
        }
        return baseFilename.length() == 2 ? baseFilename + SessionDescription.SUPPORTED_SDP_VERSION : baseFilename;
    }

    public static boolean rename(File file, File file2) {
        Logger logger2 = logger;
        logger2.log(Level.CONFIG, "Renaming From:" + file.getAbsolutePath() + " to " + file2.getAbsolutePath());
        if (file2.exists()) {
            logger2.log(Level.SEVERE, "Destination File:" + file2 + " already exists");
            return false;
        }
        if (file.renameTo(file2)) {
            return true;
        }
        if (!copy(file, file2)) {
            return false;
        }
        if (file.delete()) {
            return true;
        }
        logger2.log(Level.SEVERE, "Unable to delete File:" + file);
        file2.delete();
        return false;
    }

    public static boolean copy(File file, File file2) {
        try {
            copyThrowsOnException(file, file2);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readFourBytesAsChars(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        return new String(bArr, StandardCharsets.ISO_8859_1);
    }

    public static String readThreeBytesAsChars(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[3];
        byteBuffer.get(bArr);
        return new String(bArr, StandardCharsets.ISO_8859_1);
    }

    public static ByteBuffer readFileDataIntoBufferLE(FileChannel fileChannel, int i) throws IOException {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i);
        fileChannel.read(byteBufferAllocateDirect);
        byteBufferAllocateDirect.position(0);
        byteBufferAllocateDirect.order(ByteOrder.LITTLE_ENDIAN);
        return byteBufferAllocateDirect;
    }

    public static ByteBuffer readFileDataIntoBufferBE(FileChannel fileChannel, int i) throws IOException {
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i);
        fileChannel.read(byteBufferAllocateDirect);
        byteBufferAllocateDirect.position(0);
        byteBufferAllocateDirect.order(ByteOrder.BIG_ENDIAN);
        return byteBufferAllocateDirect;
    }

    public static void copyThrowsOnException(File file, File file2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                FileChannel channel = fileInputStream.getChannel();
                FileChannel channel2 = fileOutputStream.getChannel();
                long size = channel.size();
                for (long jTransferTo = 0; jTransferTo < size; jTransferTo += channel.transferTo(jTransferTo, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, channel2)) {
                }
                fileOutputStream.close();
                fileInputStream.close();
            } finally {
            }
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
