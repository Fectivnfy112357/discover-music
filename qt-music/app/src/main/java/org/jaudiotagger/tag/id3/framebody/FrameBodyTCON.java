package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.datatype.TCONString;
import org.jaudiotagger.tag.id3.valuepair.ID3V2ExtendedGenreTypes;
import org.jaudiotagger.tag.reference.GenreTypes;

/* loaded from: classes3.dex */
public class FrameBodyTCON extends AbstractFrameBodyTextInfo implements ID3v24FrameBody, ID3v23FrameBody {
    public FrameBodyTCON() {
    }

    public FrameBodyTCON(FrameBodyTCON frameBodyTCON) {
        super(frameBodyTCON);
    }

    public FrameBodyTCON(byte b, String str) {
        super(b, str);
    }

    public FrameBodyTCON(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "TCON";
    }

    public static String convertGenericToID3v24Genre(String str) throws NumberFormatException {
        try {
            int i = Integer.parseInt(str);
            return i <= GenreTypes.getMaxGenreId() ? String.valueOf(i) : str;
        } catch (NumberFormatException unused) {
            Integer idForName = GenreTypes.getInstanceOf().getIdForName(str);
            if (idForName != null && idForName.intValue() <= GenreTypes.getMaxStandardGenreId()) {
                return String.valueOf(idForName);
            }
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.RX.getDescription())) {
                return ID3V2ExtendedGenreTypes.RX.name();
            }
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.CR.getDescription())) {
                return ID3V2ExtendedGenreTypes.CR.name();
            }
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.RX.name())) {
                return ID3V2ExtendedGenreTypes.RX.name();
            }
            return str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.CR.name()) ? ID3V2ExtendedGenreTypes.CR.name() : str;
        }
    }

    public static String convertGenericToID3v23Genre(String str) throws NumberFormatException {
        try {
            int i = Integer.parseInt(str);
            return i <= GenreTypes.getMaxGenreId() ? bracketWrap(String.valueOf(i)) : str;
        } catch (NumberFormatException unused) {
            Integer idForName = GenreTypes.getInstanceOf().getIdForName(str);
            if (idForName != null && idForName.intValue() <= GenreTypes.getMaxStandardGenreId()) {
                return bracketWrap(String.valueOf(idForName));
            }
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.RX.getDescription())) {
                return bracketWrap(ID3V2ExtendedGenreTypes.RX.name());
            }
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.CR.getDescription())) {
                return bracketWrap(ID3V2ExtendedGenreTypes.CR.name());
            }
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.RX.name())) {
                return bracketWrap(ID3V2ExtendedGenreTypes.RX.name());
            }
            return str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.CR.name()) ? bracketWrap(ID3V2ExtendedGenreTypes.CR.name()) : str;
        }
    }

    public static String convertGenericToID3v22Genre(String str) {
        return convertGenericToID3v23Genre(str);
    }

    private static String bracketWrap(Object obj) {
        return "(" + obj + ')';
    }

    public static String convertID3v24GenreToGeneric(String str) throws NumberFormatException {
        try {
            int i = Integer.parseInt(str);
            return i <= GenreTypes.getMaxGenreId() ? GenreTypes.getInstanceOf().getValueForId(i) : str;
        } catch (NumberFormatException unused) {
            if (str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.RX.name())) {
                return ID3V2ExtendedGenreTypes.RX.getDescription();
            }
            return str.equalsIgnoreCase(ID3V2ExtendedGenreTypes.CR.name()) ? ID3V2ExtendedGenreTypes.CR.getDescription() : str;
        }
    }

    private static String checkBracketed(String str) throws NumberFormatException {
        String strReplace = str.replace("(", "").replace(")", "");
        try {
            int i = Integer.parseInt(strReplace);
            return i <= GenreTypes.getMaxGenreId() ? GenreTypes.getInstanceOf().getValueForId(i) : strReplace;
        } catch (NumberFormatException unused) {
            if (strReplace.equalsIgnoreCase(ID3V2ExtendedGenreTypes.RX.name())) {
                return ID3V2ExtendedGenreTypes.RX.getDescription();
            }
            return strReplace.equalsIgnoreCase(ID3V2ExtendedGenreTypes.CR.name()) ? ID3V2ExtendedGenreTypes.CR.getDescription() : strReplace;
        }
    }

    public static String convertID3v23GenreToGeneric(String str) {
        if (str.contains(")") && str.lastIndexOf(41) < str.length() - 1) {
            return checkBracketed(str.substring(0, str.lastIndexOf(41))) + ' ' + str.substring(str.lastIndexOf(41) + 1);
        }
        return checkBracketed(str);
    }

    public static String convertID3v22GenreToGeneric(String str) {
        return convertID3v23GenreToGeneric(str);
    }

    public void setV23Format() {
        ((TCONString) getObject(DataTypes.OBJ_TEXT)).setNullSeperateMultipleValues(false);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyTextInfo, org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TEXT_ENCODING, this, 1));
        this.objectList.add(new TCONString(DataTypes.OBJ_TEXT, this));
    }
}
