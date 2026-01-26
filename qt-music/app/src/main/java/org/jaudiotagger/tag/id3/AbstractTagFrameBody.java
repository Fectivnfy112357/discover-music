package org.jaudiotagger.tag.id3;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.tag.datatype.AbstractDataType;
import org.jaudiotagger.tag.datatype.DataTypes;

/* loaded from: classes3.dex */
public abstract class AbstractTagFrameBody extends AbstractTagItem {
    private AbstractTagFrame header;
    protected List<AbstractDataType> objectList = new ArrayList();

    public void createStructure() {
    }

    protected abstract void setupObjectList();

    public final byte getTextEncoding() {
        AbstractDataType object = getObject(DataTypes.OBJ_TEXT_ENCODING);
        if (object != null) {
            return ((Long) object.getValue()).byteValue();
        }
        return (byte) 0;
    }

    public final void setTextEncoding(byte b) {
        setObjectValue(DataTypes.OBJ_TEXT_ENCODING, Byte.valueOf(b));
    }

    protected AbstractTagFrameBody() {
        setupObjectList();
    }

    protected AbstractTagFrameBody(AbstractTagFrameBody abstractTagFrameBody) {
        for (int i = 0; i < abstractTagFrameBody.objectList.size(); i++) {
            AbstractDataType abstractDataType = (AbstractDataType) ID3Tags.copyObject(abstractTagFrameBody.objectList.get(i));
            abstractDataType.setBody(this);
            this.objectList.add(abstractDataType);
        }
    }

    public String getUserFriendlyValue() {
        return toString();
    }

    public String getBriefDescription() {
        String str = "";
        for (AbstractDataType abstractDataType : this.objectList) {
            if (abstractDataType.toString() != null && abstractDataType.toString().length() > 0) {
                str = str + abstractDataType.getIdentifier() + "=\"" + abstractDataType.toString() + "\"; ";
            }
        }
        return str;
    }

    public final String getLongDescription() {
        String str = "";
        for (AbstractDataType abstractDataType : this.objectList) {
            if (abstractDataType.toString() != null && abstractDataType.toString().length() > 0) {
                str = str + abstractDataType.getIdentifier() + " = " + abstractDataType.toString() + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
            }
        }
        return str;
    }

    public final void setObjectValue(String str, Object obj) {
        for (AbstractDataType abstractDataType : this.objectList) {
            if (abstractDataType.getIdentifier().equals(str)) {
                abstractDataType.setValue(obj);
            }
        }
    }

    public final Object getObjectValue(String str) {
        return getObject(str).getValue();
    }

    public final AbstractDataType getObject(String str) {
        for (AbstractDataType abstractDataType : this.objectList) {
            if (abstractDataType.getIdentifier().equals(str)) {
                return abstractDataType;
            }
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        Iterator<AbstractDataType> it = this.objectList.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += it.next().getSize();
        }
        return size;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean isSubsetOf(Object obj) {
        if (!(obj instanceof AbstractTagFrameBody)) {
            return false;
        }
        List<AbstractDataType> list = ((AbstractTagFrameBody) obj).objectList;
        for (AbstractDataType abstractDataType : this.objectList) {
            if (abstractDataType.getValue() != null && !list.contains(abstractDataType)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof AbstractTagFrameBody) && this.objectList.equals(((AbstractTagFrameBody) obj).objectList) && super.equals(obj);
    }

    public Iterator<? extends AbstractDataType> iterator() {
        return this.objectList.iterator();
    }

    public String toString() {
        return getBriefDescription();
    }

    public AbstractTagFrame getHeader() {
        return this.header;
    }

    public void setHeader(AbstractTagFrame abstractTagFrame) {
        this.header = abstractTagFrame;
    }
}
