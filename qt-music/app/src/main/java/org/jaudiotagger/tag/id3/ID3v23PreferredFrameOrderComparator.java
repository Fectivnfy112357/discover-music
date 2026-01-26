package org.jaudiotagger.tag.id3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes3.dex */
public class ID3v23PreferredFrameOrderComparator implements Comparator<String> {
    private static ID3v23PreferredFrameOrderComparator comparator;
    private static List<String> frameIdsInPreferredOrder;

    static {
        ArrayList arrayList = new ArrayList();
        frameIdsInPreferredOrder = arrayList;
        arrayList.add("UFID");
        frameIdsInPreferredOrder.add("TIT2");
        frameIdsInPreferredOrder.add("TPE1");
        frameIdsInPreferredOrder.add("TALB");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TORY);
        frameIdsInPreferredOrder.add("TCON");
        frameIdsInPreferredOrder.add("TCOM");
        frameIdsInPreferredOrder.add("TPE3");
        frameIdsInPreferredOrder.add("TIT1");
        frameIdsInPreferredOrder.add("TRCK");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TYER);
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TDAT);
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TIME);
        frameIdsInPreferredOrder.add("TBPM");
        frameIdsInPreferredOrder.add("TSRC");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TORY);
        frameIdsInPreferredOrder.add("TPE2");
        frameIdsInPreferredOrder.add("TIT3");
        frameIdsInPreferredOrder.add("USLT");
        frameIdsInPreferredOrder.add("TXXX");
        frameIdsInPreferredOrder.add("WXXX");
        frameIdsInPreferredOrder.add("WOAR");
        frameIdsInPreferredOrder.add("WCOM");
        frameIdsInPreferredOrder.add("WCOP");
        frameIdsInPreferredOrder.add("WOAF");
        frameIdsInPreferredOrder.add("WORS");
        frameIdsInPreferredOrder.add("WPAY");
        frameIdsInPreferredOrder.add("WPUB");
        frameIdsInPreferredOrder.add("WCOM");
        frameIdsInPreferredOrder.add("TEXT");
        frameIdsInPreferredOrder.add("TMED");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_INVOLVED_PEOPLE);
        frameIdsInPreferredOrder.add("TLAN");
        frameIdsInPreferredOrder.add("TSOT");
        frameIdsInPreferredOrder.add("TDLY");
        frameIdsInPreferredOrder.add("PCNT");
        frameIdsInPreferredOrder.add("POPM");
        frameIdsInPreferredOrder.add("TPUB");
        frameIdsInPreferredOrder.add("TSO2");
        frameIdsInPreferredOrder.add("TSOC");
        frameIdsInPreferredOrder.add("TCMP");
        frameIdsInPreferredOrder.add("TSOT");
        frameIdsInPreferredOrder.add("TSOP");
        frameIdsInPreferredOrder.add("TSOA");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TITLE_SORT_ORDER_MUSICBRAINZ);
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_ARTIST_SORT_ORDER_MUSICBRAINZ);
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_ALBUM_SORT_ORDER_MUSICBRAINZ);
        frameIdsInPreferredOrder.add("TSO2");
        frameIdsInPreferredOrder.add("TSOC");
        frameIdsInPreferredOrder.add("COMM");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TRDA);
        frameIdsInPreferredOrder.add("COMR");
        frameIdsInPreferredOrder.add("TCOP");
        frameIdsInPreferredOrder.add("TENC");
        frameIdsInPreferredOrder.add("ENCR");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_EQUALISATION);
        frameIdsInPreferredOrder.add("ETCO");
        frameIdsInPreferredOrder.add("TOWN");
        frameIdsInPreferredOrder.add("TFLT");
        frameIdsInPreferredOrder.add("GRID");
        frameIdsInPreferredOrder.add("TSSE");
        frameIdsInPreferredOrder.add("TKEY");
        frameIdsInPreferredOrder.add("TLEN");
        frameIdsInPreferredOrder.add("LINK");
        frameIdsInPreferredOrder.add(ID3v23Frames.FRAME_ID_V3_TSIZ);
        frameIdsInPreferredOrder.add("MLLT");
        frameIdsInPreferredOrder.add("TOPE");
        frameIdsInPreferredOrder.add("TOFN");
        frameIdsInPreferredOrder.add("TOLY");
        frameIdsInPreferredOrder.add("TOAL");
        frameIdsInPreferredOrder.add("OWNE");
        frameIdsInPreferredOrder.add("POSS");
        frameIdsInPreferredOrder.add("TRSN");
        frameIdsInPreferredOrder.add("TRSO");
        frameIdsInPreferredOrder.add("RBUF");
        frameIdsInPreferredOrder.add("TPE4");
        frameIdsInPreferredOrder.add("RVRB");
        frameIdsInPreferredOrder.add("TPOS");
        frameIdsInPreferredOrder.add("SYLT");
        frameIdsInPreferredOrder.add("SYTC");
        frameIdsInPreferredOrder.add("USER");
        frameIdsInPreferredOrder.add("APIC");
        frameIdsInPreferredOrder.add("PRIV");
        frameIdsInPreferredOrder.add("MCDI");
        frameIdsInPreferredOrder.add("AENC");
        frameIdsInPreferredOrder.add("GEOB");
    }

    private ID3v23PreferredFrameOrderComparator() {
    }

    public static ID3v23PreferredFrameOrderComparator getInstanceof() {
        if (comparator == null) {
            comparator = new ID3v23PreferredFrameOrderComparator();
        }
        return comparator;
    }

    @Override // java.util.Comparator
    public int compare(String str, String str2) {
        int iIndexOf = frameIdsInPreferredOrder.indexOf(str);
        if (iIndexOf == -1) {
            iIndexOf = Integer.MAX_VALUE;
        }
        int iIndexOf2 = frameIdsInPreferredOrder.indexOf(str2);
        int i = iIndexOf2 != -1 ? iIndexOf2 : Integer.MAX_VALUE;
        return iIndexOf == i ? str.compareTo(str2) : iIndexOf - i;
    }

    @Override // java.util.Comparator
    public boolean equals(Object obj) {
        return obj instanceof ID3v23PreferredFrameOrderComparator;
    }
}
