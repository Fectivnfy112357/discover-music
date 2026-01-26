package org.jaudiotagger.tag.mp4.field;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.jaudiotagger.audio.mp4.atom.Mp4BoxHeader;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.atom.Mp4DataBox;
import org.jaudiotagger.tag.reference.GenreTypes;

/* loaded from: classes3.dex */
public class Mp4GenreField extends Mp4TagTextNumberField {
    public Mp4GenreField(String str, ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        super(str, byteBuffer);
    }

    public static boolean isValidGenre(String str) {
        try {
            if (Short.parseShort(str) - 1 <= GenreTypes.getMaxStandardGenreId()) {
                return true;
            }
        } catch (NumberFormatException unused) {
        }
        Integer idForValue = GenreTypes.getInstanceOf().getIdForValue(str);
        return idForValue != null && idForValue.intValue() <= GenreTypes.getMaxStandardGenreId();
    }

    public Mp4GenreField(String str) throws NumberFormatException {
        super(Mp4FieldKey.GENRE.getFieldName(), str);
        try {
            short s = Short.parseShort(str);
            if (s <= GenreTypes.getMaxStandardGenreId()) {
                this.numbers = new ArrayList();
                this.numbers.add(Short.valueOf((short) (s + 1)));
            } else {
                this.numbers = new ArrayList();
                this.numbers.add((short) 1);
            }
        } catch (NumberFormatException unused) {
            Integer idForValue = GenreTypes.getInstanceOf().getIdForValue(str);
            if (idForValue != null && idForValue.intValue() <= GenreTypes.getMaxStandardGenreId()) {
                this.numbers = new ArrayList();
                this.numbers.add(Short.valueOf((short) (idForValue.intValue() + 1)));
            } else {
                this.numbers = new ArrayList();
                this.numbers.add((short) 1);
            }
        }
    }

    @Override // org.jaudiotagger.tag.mp4.field.Mp4TagTextNumberField, org.jaudiotagger.tag.mp4.field.Mp4TagTextField, org.jaudiotagger.tag.mp4.Mp4TagField
    protected void build(ByteBuffer byteBuffer) throws UnsupportedEncodingException {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(byteBuffer);
        Mp4DataBox mp4DataBox = new Mp4DataBox(mp4BoxHeader, byteBuffer);
        this.dataSize = mp4BoxHeader.getDataLength();
        this.numbers = mp4DataBox.getNumbers();
        if (this.numbers != null && this.numbers.size() > 0) {
            short sShortValue = this.numbers.get(0).shortValue();
            this.content = GenreTypes.getInstanceOf().getValueForId(sShortValue - 1);
            if (this.content == null) {
                logger.warning(ErrorMessage.MP4_GENRE_OUT_OF_RANGE.getMsg(Integer.valueOf(sShortValue)));
                return;
            }
            return;
        }
        logger.warning(ErrorMessage.MP4_NO_GENREID_FOR_GENRE.getMsg(Integer.valueOf(mp4BoxHeader.getDataLength())));
    }
}
