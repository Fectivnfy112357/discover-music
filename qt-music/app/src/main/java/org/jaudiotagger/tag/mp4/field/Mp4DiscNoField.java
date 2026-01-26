package org.jaudiotagger.tag.mp4.field;

import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.atom.Mp4DataBox;

/* loaded from: classes3.dex */
public class Mp4DiscNoField extends Mp4TagTextNumberField {
    private static final int DISC_NO_INDEX = 1;
    private static final int DISC_TOTAL_INDEX = 2;

    public Mp4DiscNoField(String str) throws FieldDataInvalidException {
        super(Mp4FieldKey.DISCNUMBER.getFieldName(), str);
        this.numbers = new ArrayList();
        this.numbers.add(new Short(SessionDescription.SUPPORTED_SDP_VERSION));
        String[] strArrSplit = str.split("/");
        int length = strArrSplit.length;
        if (length == 1) {
            try {
                this.numbers.add(Short.valueOf(Short.parseShort(strArrSplit[0])));
                this.numbers.add(new Short(SessionDescription.SUPPORTED_SDP_VERSION));
            } catch (NumberFormatException unused) {
                throw new FieldDataInvalidException("Value of:" + strArrSplit[0] + " is invalid for field:" + this.id);
            }
        } else {
            if (length == 2) {
                try {
                    this.numbers.add(Short.valueOf(Short.parseShort(strArrSplit[0])));
                    try {
                        this.numbers.add(Short.valueOf(Short.parseShort(strArrSplit[1])));
                        return;
                    } catch (NumberFormatException unused2) {
                        throw new FieldDataInvalidException("Value of:" + strArrSplit[1] + " is invalid for field:" + this.id);
                    }
                } catch (NumberFormatException unused3) {
                    throw new FieldDataInvalidException("Value of:" + strArrSplit[0] + " is invalid for field:" + this.id);
                }
            }
            throw new FieldDataInvalidException("Value is invalid for field:" + this.id);
        }
    }

    public Mp4DiscNoField(int i) {
        super(Mp4FieldKey.DISCNUMBER.getFieldName(), String.valueOf(i));
        this.numbers = new ArrayList();
        this.numbers.add(new Short(SessionDescription.SUPPORTED_SDP_VERSION));
        this.numbers.add(Short.valueOf((short) i));
        this.numbers.add(new Short(SessionDescription.SUPPORTED_SDP_VERSION));
    }

    public Mp4DiscNoField(int i, int i2) {
        super(Mp4FieldKey.DISCNUMBER.getFieldName(), String.valueOf(i));
        this.numbers = new ArrayList();
        this.numbers.add(new Short(SessionDescription.SUPPORTED_SDP_VERSION));
        this.numbers.add(Short.valueOf((short) i));
        this.numbers.add(Short.valueOf((short) i2));
    }

    public Mp4DiscNoField(String str, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(str, byteBuffer);
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextNumberField, org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    protected void build(ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(byteBuffer);
        Mp4DataBox mp4DataBox = new Mp4DataBox(mp4BoxHeader, byteBuffer);
        this.dataSize = mp4BoxHeader.getDataLength();
        this.numbers = mp4DataBox.getNumbers();
        StringBuffer stringBuffer = new StringBuffer();
        if (this.numbers.size() > 1 && this.numbers.get(1).shortValue() > 0) {
            stringBuffer.append(this.numbers.get(1));
        }
        if (this.numbers.size() > 2 && this.numbers.get(2).shortValue() > 0) {
            stringBuffer.append("/").append(this.numbers.get(2));
        }
        this.content = stringBuffer.toString();
    }

    public Short getDiscNo() {
        return this.numbers.get(1);
    }

    public void setDiscNo(int i) {
        this.numbers.set(1, Short.valueOf((short) i));
    }

    public Short getDiscTotal() {
        if (this.numbers.size() <= 2) {
            return (short) 0;
        }
        return this.numbers.get(2);
    }

    public void setDiscTotal(int i) {
        this.numbers.set(2, Short.valueOf((short) i));
    }
}
