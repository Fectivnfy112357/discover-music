package org.jaudiotagger.tag.datatype;

import java.util.Collections;
import java.util.Map;

/* loaded from: classes3.dex */
public class AbstractIntStringValuePair extends AbstractValuePair<Integer, String> {
    protected Integer key = null;

    public Integer getIdForValue(String str) {
        return (Integer) this.valueToId.get(str);
    }

    public String getValueForId(int i) {
        return (String) this.idToValue.get(Integer.valueOf(i));
    }

    protected void createMaps() {
        for (Map.Entry entry : this.idToValue.entrySet()) {
            this.valueToId.put((String) entry.getValue(), (Integer) entry.getKey());
        }
        this.valueList.addAll(this.idToValue.values());
        Collections.sort(this.valueList);
    }
}
