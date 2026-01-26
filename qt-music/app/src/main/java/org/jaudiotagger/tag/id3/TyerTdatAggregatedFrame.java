package org.jaudiotagger.tag.id3;

import java.util.Iterator;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTDAT;

/* loaded from: classes3.dex */
public class TyerTdatAggregatedFrame extends AggregatedFrame {
    public static final String ID_TYER_TDAT = "TYERTDAT";

    @Override // org.jaudiotagger.tag.id3.AggregatedFrame, org.jaudiotagger.tag.TagTextField
    public String getContent() {
        StringBuilder sb = new StringBuilder();
        Iterator<AbstractID3v2Frame> it = this.frames.iterator();
        sb.append(it.next().getContent());
        AbstractID3v2Frame next = it.next();
        if (next.getContent().length() == 4) {
            sb.append("-");
            sb.append(next.getContent().substring(2, 4));
            if (!((FrameBodyTDAT) next.getBody()).isMonthOnly()) {
                sb.append("-");
                sb.append(next.getContent().substring(0, 2));
            }
        }
        return sb.toString();
    }
}
