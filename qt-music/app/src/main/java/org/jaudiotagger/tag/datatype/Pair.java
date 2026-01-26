package org.jaudiotagger.tag.datatype;

/* loaded from: classes3.dex */
public class Pair {
    private String key;
    private String value;

    public Pair(String str, String str2) {
        setKey(str);
        setValue(str2);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public String getPairValue() {
        return getKey() + (char) 0 + getValue();
    }
}
