package org.jaudiotagger.tag.mp4.atom;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.atom.AbstractMp4Box;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.mp4.field.Mp4FieldType;

/* loaded from: classes3.dex */
public class Mp4DataBox extends AbstractMp4Box {
    public static final int DATA_HEADER_LENGTH = 16;
    public static final String IDENTIFIER = "data";
    public static final int NULL_LENGTH = 4;
    public static final int NUMBER_LENGTH = 2;
    public static final int PRE_DATA_LENGTH = 8;
    public static final int TYPE_LENGTH = 3;
    public static final int TYPE_POS = 1;
    public static final int TYPE_POS_INCLUDING_HEADER = 9;
    public static final int VERSION_LENGTH = 1;
    private byte[] bytedata;
    private String content;
    private List<Short> numbers;
    private int type;

    public Mp4DataBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        if (!mp4BoxHeader.getId().equals("data")) {
            throw new RuntimeException("Unable to process data box because identifier is:" + mp4BoxHeader.getId());
        }
        this.dataBuffer = byteBuffer.slice();
        int intBE = Utils.getIntBE(this.dataBuffer, 1, 3);
        this.type = intBE;
        if (intBE == Mp4FieldType.TEXT.getFileClassId()) {
            this.content = Utils.getString(this.dataBuffer, 8, mp4BoxHeader.getDataLength() - 8, mp4BoxHeader.getEncoding());
            return;
        }
        int i = 0;
        if (this.type == Mp4FieldType.IMPLICIT.getFileClassId() || this.type == Mp4FieldType.GENRES.getFileClassId()) {
            this.numbers = new ArrayList();
            while (i < (mp4BoxHeader.getDataLength() - 8) / 2) {
                int i2 = i * 2;
                this.numbers.add(Short.valueOf(Utils.getShortBE(this.dataBuffer, i2 + 8, i2 + 9)));
                i++;
            }
            StringBuffer stringBuffer = new StringBuffer();
            ListIterator<Short> listIterator = this.numbers.listIterator();
            while (listIterator.hasNext()) {
                stringBuffer.append(listIterator.next());
                if (listIterator.hasNext()) {
                    stringBuffer.append("/");
                }
            }
            this.content = stringBuffer.toString();
            return;
        }
        if (this.type == Mp4FieldType.INTEGER.getFileClassId()) {
            this.content = Utils.getIntBE(this.dataBuffer, 8, mp4BoxHeader.getDataLength() - 1) + "";
            this.bytedata = new byte[mp4BoxHeader.getDataLength() - 8];
            int iPosition = byteBuffer.position();
            byteBuffer.position(iPosition + 8);
            byteBuffer.get(this.bytedata);
            byteBuffer.position(iPosition);
            this.numbers = new ArrayList();
            while (i < (mp4BoxHeader.getDataLength() - 8) / 2) {
                int i3 = i * 2;
                this.numbers.add(Short.valueOf(Utils.getShortBE(this.dataBuffer, i3 + 8, i3 + 9)));
                i++;
            }
            return;
        }
        if (this.type == Mp4FieldType.COVERART_JPEG.getFileClassId()) {
            this.content = Utils.getString(this.dataBuffer, 8, mp4BoxHeader.getDataLength() - 8, mp4BoxHeader.getEncoding());
        }
    }

    public String getContent() {
        return this.content;
    }

    public int getType() {
        return this.type;
    }

    public List<Short> getNumbers() {
        return this.numbers;
    }

    public byte[] getByteData() {
        return this.bytedata;
    }
}
