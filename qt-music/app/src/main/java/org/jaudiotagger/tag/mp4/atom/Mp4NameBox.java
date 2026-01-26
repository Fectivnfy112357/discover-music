package org.jaudiotagger.tag.mp4.atom;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.AbstractMp4Box;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;

/* loaded from: classes3.dex */
public class Mp4NameBox extends AbstractMp4Box {
    public static final int FLAGS_LENGTH = 3;
    public static final String IDENTIFIER = "name";
    public static final int PRE_DATA_LENGTH = 4;
    public static final int VERSION_LENGTH = 1;
    private String name;

    public Mp4NameBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        if (!mp4BoxHeader.getId().equals("name")) {
            throw new RuntimeException("Unable to process name box because identifier is:" + mp4BoxHeader.getId());
        }
        this.dataBuffer = byteBuffer.slice();
        this.name = Utils.getString(this.dataBuffer, 4, mp4BoxHeader.getDataLength() - 4, mp4BoxHeader.getEncoding());
    }

    public String getName() {
        return this.name;
    }
}
