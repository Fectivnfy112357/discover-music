package org.jaudiotagger.audio.aiff;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: classes3.dex */
public class AiffUtil {
    private static final SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public static double read80BitDouble(ByteBuffer byteBuffer) throws IOException {
        byte[] bArr = new byte[10];
        byteBuffer.get(bArr);
        return new ExtDouble(bArr).toDouble();
    }

    public static Date timestampToDate(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1904, 0, 1, 0, 0, 0);
        int i = (int) (j / 3600);
        calendar.add(11, i);
        calendar.add(13, (int) (j - (i * 3600)));
        return calendar.getTime();
    }

    public static String formatDate(Date date) {
        return dateFmt.format(date);
    }
}
