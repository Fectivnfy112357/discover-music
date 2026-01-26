package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.id3.framebody.FrameBodyETCO;

/* loaded from: classes3.dex */
public class EventTimingCodeList extends AbstractDataTypeList<EventTimingCode> {
    public EventTimingCodeList(EventTimingCodeList eventTimingCodeList) {
        super(eventTimingCodeList);
    }

    public EventTimingCodeList(FrameBodyETCO frameBodyETCO) {
        super(DataTypes.OBJ_TIMED_EVENT_LIST, frameBodyETCO);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jaudiotagger.tag.datatype.AbstractDataTypeList
    public EventTimingCode createListElement() {
        return new EventTimingCode(DataTypes.OBJ_TIMED_EVENT, this.frameBody);
    }
}
