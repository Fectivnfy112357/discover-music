package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class Mp4MvhdBox extends AbstractMp4Box {
    public static final int CREATED_DATE_LONG_LENGTH = 8;
    public static final int CREATED_DATE_LONG_POS = 4;
    public static final int CREATED_DATE_SHORT_LENGTH = 4;
    public static final int CREATED_DATE_SHORT_POS = 4;
    public static final int DURATION_LONG_LENGTH = 8;
    public static final int DURATION_LONG_POS = 24;
    public static final int DURATION_SHORT_LENGTH = 4;
    public static final int DURATION_SHORT_POS = 16;
    private static final int LONG_FORMAT = 1;
    public static final int MODIFIED_DATE_LONG_LENGTH = 8;
    public static final int MODIFIED_DATE_LONG_POS = 12;
    public static final int MODIFIED_DATE_SHORT_LENGTH = 4;
    public static final int MODIFIED_DATE_SHORT_POS = 8;
    public static final int OTHER_FLAG_LENGTH = 3;
    public static final int OTHER_FLAG_POS = 1;
    public static final int TIMESCALE_LENGTH = 4;
    public static final int TIMESCALE_LONG_POS = 20;
    public static final int TIMESCALE_SHORT_POS = 12;
    public static final int VERSION_FLAG_LENGTH = 1;
    public static final int VERSION_FLAG_POS = 0;
    private long timeLength;
    private int timeScale;

    public Mp4MvhdBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        byteBuffer.order(ByteOrder.BIG_ENDIAN);
        if (byteBuffer.get(0) == 1) {
            this.timeScale = byteBuffer.getInt(20);
            this.timeLength = byteBuffer.getLong(24);
        } else {
            this.timeScale = byteBuffer.getInt(12);
            this.timeLength = Utils.u(byteBuffer.getInt(16));
        }
    }

    public int getLength() {
        return (int) (this.timeLength / this.timeScale);
    }

    public double getPreciseLength() {
        return this.timeLength / this.timeScale;
    }
}
