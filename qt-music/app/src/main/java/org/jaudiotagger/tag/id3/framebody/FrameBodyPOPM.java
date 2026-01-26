package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.NumberFixedLength;
import org.jaudiotagger.tag.datatype.NumberVariableLength;
import org.jaudiotagger.tag.datatype.StringNullTerminated;

/* loaded from: classes3.dex */
public class FrameBodyPOPM extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    private static final int COUNTER_MINIMUM_FIELD_SIZE = 0;
    public static final String MEDIA_MONKEY_NO_EMAIL = "no@email";
    private static final int RATING_FIELD_SIZE = 1;

    public FrameBodyPOPM() {
        setObjectValue(DataTypes.OBJ_EMAIL, "");
        setObjectValue(DataTypes.OBJ_RATING, 0L);
        setObjectValue(DataTypes.OBJ_COUNTER, 0L);
    }

    public FrameBodyPOPM(FrameBodyPOPM frameBodyPOPM) {
        super(frameBodyPOPM);
    }

    public FrameBodyPOPM(String str, long j, long j2) {
        setObjectValue(DataTypes.OBJ_EMAIL, str);
        setObjectValue(DataTypes.OBJ_RATING, Long.valueOf(j));
        setObjectValue(DataTypes.OBJ_COUNTER, Long.valueOf(j2));
    }

    public FrameBodyPOPM(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public void setEmailToUser(String str) {
        setObjectValue(DataTypes.OBJ_EMAIL, str);
    }

    public String getEmailToUser() {
        return (String) getObjectValue(DataTypes.OBJ_EMAIL);
    }

    public long getRating() {
        return ((Number) getObjectValue(DataTypes.OBJ_RATING)).longValue();
    }

    public void setRating(long j) {
        setObjectValue(DataTypes.OBJ_RATING, Long.valueOf(j));
    }

    public long getCounter() {
        return ((Number) getObjectValue(DataTypes.OBJ_COUNTER)).longValue();
    }

    public void setCounter(long j) {
        setObjectValue(DataTypes.OBJ_COUNTER, Long.valueOf(j));
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "POPM";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String getUserFriendlyValue() {
        return getEmailToUser() + ":" + getRating() + ":" + getCounter();
    }

    public void parseString(String str) {
        try {
            setRating(Integer.parseInt(str));
            setEmailToUser(MEDIA_MONKEY_NO_EMAIL);
        } catch (NumberFormatException unused) {
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new StringNullTerminated(DataTypes.OBJ_EMAIL, this));
        this.objectList.add(new NumberFixedLength(DataTypes.OBJ_RATING, this, 1));
        this.objectList.add(new NumberVariableLength(DataTypes.OBJ_COUNTER, this, 0));
    }
}
