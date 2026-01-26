package org.jaudiotagger.logging;

/* loaded from: classes3.dex */
public class PlainTextTagDisplayFormatter extends AbstractTagDisplayFormatter {
    private static PlainTextTagDisplayFormatter formatter;
    StringBuffer sb = new StringBuffer();
    StringBuffer indent = new StringBuffer();

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void openHeadingElement(String str, String str2) {
        addElement(str, str2);
        increaseLevel();
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void openHeadingElement(String str, boolean z) {
        openHeadingElement(str, String.valueOf(z));
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void openHeadingElement(String str, int i) {
        openHeadingElement(str, String.valueOf(i));
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void closeHeadingElement(String str) {
        decreaseLevel();
    }

    public void increaseLevel() {
        this.level++;
        this.indent.append("  ");
    }

    public void decreaseLevel() {
        this.level--;
        this.indent = new StringBuffer(this.indent.substring(0, r1.length() - 2));
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void addElement(String str, String str2) {
        this.sb.append(this.indent).append(str).append(":").append(str2).append('\n');
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void addElement(String str, int i) {
        addElement(str, String.valueOf(i));
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void addElement(String str, boolean z) {
        addElement(str, String.valueOf(z));
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public String toString() {
        return this.sb.toString();
    }

    public static AbstractTagDisplayFormatter getInstanceOf() {
        if (formatter == null) {
            formatter = new PlainTextTagDisplayFormatter();
        }
        return formatter;
    }
}
