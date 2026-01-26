package org.jaudiotagger.tag.datatype;

import org.jaudiotagger.tag.id3.framebody.FrameBodySYTC;

/* loaded from: classes3.dex */
public class SynchronisedTempoCodeList extends AbstractDataTypeList<SynchronisedTempoCode> {
    public SynchronisedTempoCodeList(SynchronisedTempoCodeList synchronisedTempoCodeList) {
        super(synchronisedTempoCodeList);
    }

    public SynchronisedTempoCodeList(FrameBodySYTC frameBodySYTC) {
        super(DataTypes.OBJ_SYNCHRONISED_TEMPO_LIST, frameBodySYTC);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jaudiotagger.tag.datatype.AbstractDataTypeList
    public SynchronisedTempoCode createListElement() {
        return new SynchronisedTempoCode(DataTypes.OBJ_SYNCHRONISED_TEMPO, this.frameBody);
    }
}
