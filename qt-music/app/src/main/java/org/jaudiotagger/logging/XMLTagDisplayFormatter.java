package org.jaudiotagger.logging;

import java.text.StringCharacterIterator;

/* loaded from: classes3.dex */
public class XMLTagDisplayFormatter extends AbstractTagDisplayFormatter {
    protected static final String xmlCDataTagClose = "]]>";
    protected static final String xmlCDataTagOpen = "<![CDATA[";
    protected static final String xmlCloseEnd = ">";
    protected static final String xmlCloseStart = "</";
    protected static final String xmlOpenEnd = ">";
    protected static final String xmlOpenStart = "<";
    protected static final String xmlSingleTagClose = " />";
    StringBuffer sb = new StringBuffer();

    public static String xmlOpen(String str) {
        return xmlOpenStart + str + ">";
    }

    public static String xmlOpenHeading(String str, String str2) {
        return xmlOpen(str + " id=\"" + str2 + "\"");
    }

    public static String xmlCData(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char cCharAt = str.charAt(i);
            if (Character.isLetterOrDigit(cCharAt) || Character.isSpaceChar(cCharAt)) {
                stringBuffer.append(cCharAt);
            } else {
                stringBuffer.append("&#x").append(Integer.toString(Character.codePointAt(str, i), 16));
            }
        }
        return xmlCDataTagOpen + ((Object) stringBuffer) + xmlCDataTagClose;
    }

    public static String xmlClose(String str) {
        return xmlCloseStart + str + ">";
    }

    public static String xmlSingleTag(String str) {
        return xmlOpenStart + str + xmlSingleTagClose;
    }

    public static String xmlFullTag(String str, String str2) {
        return xmlOpen(str) + xmlCData(str2) + xmlClose(str);
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void openHeadingElement(String str, String str2) {
        if (str2.length() == 0) {
            this.sb.append(xmlOpen(str));
        } else {
            this.sb.append(xmlOpenHeading(str, replaceXMLCharacters(str2)));
        }
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
        this.sb.append(xmlClose(str));
    }

    @Override // org.jaudiotagger.logging.AbstractTagDisplayFormatter
    public void addElement(String str, String str2) {
        this.sb.append(xmlFullTag(str, replaceXMLCharacters(str2)));
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

    public static String replaceXMLCharacters(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        StringCharacterIterator stringCharacterIterator = new StringCharacterIterator(str);
        for (char cFirst = stringCharacterIterator.first(); cFirst != 65535; cFirst = stringCharacterIterator.next()) {
            if (cFirst == '\"') {
                stringBuffer.append("&quot;");
            } else if (cFirst == '<') {
                stringBuffer.append("&lt;");
            } else if (cFirst == '>') {
                stringBuffer.append("&gt;");
            } else if (cFirst == '&') {
                stringBuffer.append("&amp;");
            } else if (cFirst == '\'') {
                stringBuffer.append("&apos;");
            } else {
                stringBuffer.append(cFirst);
            }
        }
        return stringBuffer.toString();
    }
}
