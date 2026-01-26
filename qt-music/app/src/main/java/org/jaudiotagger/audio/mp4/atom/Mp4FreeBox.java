package org.jaudiotagger.audio.mp4.atom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.Mp4AtomIdentifier;

/* loaded from: classes3.dex */
public class Mp4FreeBox extends AbstractMp4Box {
    public Mp4FreeBox(int i) {
        try {
            this.header = new Mp4BoxHeader();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(Utils.getSizeBEInt32(i + 8));
            byteArrayOutputStream.write(Mp4AtomIdentifier.FREE.getFieldName().getBytes(StandardCharsets.ISO_8859_1));
            this.header.update(ByteBuffer.wrap(byteArrayOutputStream.toByteArray()));
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            for (int i2 = 0; i2 < i; i2++) {
                byteArrayOutputStream2.write(0);
            }
            this.dataBuffer = ByteBuffer.wrap(byteArrayOutputStream2.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
