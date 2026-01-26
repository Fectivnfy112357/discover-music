package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.exceptions.CannotReadException;

/* loaded from: classes3.dex */
public class Mp4StsdBox extends AbstractMp4Box {
    public static final int NO_OF_DESCRIPTIONS_POS = 4;
    public static final int NO_OF_DESCRIPTIONS_POS_LENGTH = 4;
    public static final int OTHER_FLAG_LENGTH = 3;
    public static final int OTHER_FLAG_POS = 1;
    public static final int VERSION_FLAG_LENGTH = 1;
    public static final int VERSION_FLAG_POS = 0;

    public Mp4StsdBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer;
    }

    public void processData() throws CannotReadException {
        this.dataBuffer.position(this.dataBuffer.position() + 8);
    }
}
