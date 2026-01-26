package org.jaudiotagger.tag.datatype;

import java.util.Collections;

/* loaded from: classes3.dex */
public class AbstractStringStringValuePair extends AbstractValuePair<String, String> {
    protected String lkey = null;

    public String getIdForValue(String str) {
        return (String) this.valueToId.get(str);
    }

    public String getValueForId(String str) {
        return (String) this.idToValue.get(str);
    }

    protected void createMaps() {
        this.iterator = this.idToValue.keySet().iterator();
        while (this.iterator.hasNext()) {
            this.lkey = (String) this.iterator.next();
            this.value = (String) this.idToValue.get(this.lkey);
            this.valueToId.put(this.value, this.lkey);
        }
        this.iterator = this.idToValue.keySet().iterator();
        while (this.iterator.hasNext()) {
            this.valueList.add((String) this.idToValue.get(this.iterator.next()));
        }
        Collections.sort(this.valueList);
    }
}
