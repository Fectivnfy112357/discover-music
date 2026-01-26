package org.jaudiotagger.tag.id3.framebody;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.EventTimingCode;
import org.jaudiotagger.tag.datatype.EventTimingCodeList;
import org.jaudiotagger.tag.datatype.NumberHashMap;
import org.jaudiotagger.tag.id3.valuepair.EventTimingTimestampTypes;

/* loaded from: classes3.dex */
public class FrameBodyETCO extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    public static final int MILLISECONDS = 2;
    public static final int MPEG_FRAMES = 1;

    public FrameBodyETCO() {
        setObjectValue(DataTypes.OBJ_TIME_STAMP_FORMAT, 2);
    }

    public FrameBodyETCO(FrameBodyETCO frameBodyETCO) {
        super(frameBodyETCO);
    }

    public FrameBodyETCO(ByteBuffer byteBuffer, int i) throws InvalidTagException {
        super(byteBuffer, i);
    }

    public int getTimestampFormat() {
        return ((Number) getObjectValue(DataTypes.OBJ_TIME_STAMP_FORMAT)).intValue();
    }

    public void setTimestampFormat(int i) {
        if (EventTimingTimestampTypes.getInstanceOf().getValueForId(i) == null) {
            throw new IllegalArgumentException("Timestamp format must be 1 or 2 (ID3v2.4, 4.5): " + i);
        }
        setObjectValue(DataTypes.OBJ_TIME_STAMP_FORMAT, Integer.valueOf(i));
    }

    public Map<Long, int[]> getTimingCodes() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        long timestamp = 0;
        for (EventTimingCode eventTimingCode : (List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST)) {
            if (eventTimingCode.getTimestamp() != 0) {
                timestamp = eventTimingCode.getTimestamp();
            }
            int[] iArr = (int[]) linkedHashMap.get(Long.valueOf(timestamp));
            if (iArr == null) {
                linkedHashMap.put(Long.valueOf(timestamp), new int[]{eventTimingCode.getType()});
            } else {
                int length = iArr.length;
                int[] iArr2 = new int[length + 1];
                System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
                iArr2[length] = eventTimingCode.getType();
                linkedHashMap.put(Long.valueOf(timestamp), iArr2);
            }
        }
        return Collections.unmodifiableMap(linkedHashMap);
    }

    public List<Long> getTimestamps(int... iArr) {
        Set<Integer> set = toSet(iArr);
        ArrayList arrayList = new ArrayList();
        long timestamp = 0;
        for (EventTimingCode eventTimingCode : (List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST)) {
            if (eventTimingCode.getTimestamp() != 0) {
                timestamp = eventTimingCode.getTimestamp();
            }
            if (set.contains(Integer.valueOf(eventTimingCode.getType()))) {
                arrayList.add(Long.valueOf(timestamp));
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    public void addTimingCode(long j, int... iArr) {
        int i;
        List<EventTimingCode> list = (List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST);
        if (list.isEmpty() || ((EventTimingCode) list.get(0)).getTimestamp() > j) {
            i = 0;
        } else {
            i = 0;
            long timestamp = 0;
            for (EventTimingCode eventTimingCode : list) {
                if (eventTimingCode.getTimestamp() != 0) {
                    timestamp = eventTimingCode.getTimestamp();
                }
                if (j < timestamp) {
                    break;
                } else {
                    i++;
                }
            }
        }
        int i2 = i;
        for (int i3 : iArr) {
            list.add(i2, new EventTimingCode(DataTypes.OBJ_TIMED_EVENT, this, i3, j));
            i2++;
        }
    }

    public boolean removeTimingCode(long j, int... iArr) {
        resolveRelativeTimestamps();
        Set<Integer> set = toSet(iArr);
        ListIterator listIterator = ((List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST)).listIterator();
        boolean z = false;
        while (listIterator.hasNext()) {
            EventTimingCode eventTimingCode = (EventTimingCode) listIterator.next();
            if (j == eventTimingCode.getTimestamp() && set.contains(Integer.valueOf(eventTimingCode.getType()))) {
                listIterator.remove();
                z = true;
            }
            if (j > eventTimingCode.getTimestamp()) {
                break;
            }
        }
        return z;
    }

    public void clearTimingCodes() {
        ((List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST)).clear();
    }

    private void resolveRelativeTimestamps() {
        long timestamp = 0;
        for (EventTimingCode eventTimingCode : (List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST)) {
            if (eventTimingCode.getTimestamp() != 0) {
                timestamp = eventTimingCode.getTimestamp();
            }
            eventTimingCode.setTimestamp(timestamp);
        }
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidTagException {
        super.read(byteBuffer);
        long j = 0;
        for (EventTimingCode eventTimingCode : (List) getObjectValue(DataTypes.OBJ_TIMED_EVENT_LIST)) {
            long timestamp = eventTimingCode.getTimestamp() == 0 ? j : eventTimingCode.getTimestamp();
            if (eventTimingCode.getTimestamp() < j) {
                logger.warning("Event codes are not in chronological order. " + j + " is followed by " + eventTimingCode.getTimestamp() + ".");
            }
            j = timestamp;
        }
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "ETCO";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
        this.objectList.add(new NumberHashMap(DataTypes.OBJ_TIME_STAMP_FORMAT, this, 1));
        this.objectList.add(new EventTimingCodeList(this));
    }

    private static Set<Integer> toSet(int... iArr) {
        HashSet hashSet = new HashSet();
        for (int i : iArr) {
            hashSet.add(Integer.valueOf(i));
        }
        return hashSet;
    }
}
