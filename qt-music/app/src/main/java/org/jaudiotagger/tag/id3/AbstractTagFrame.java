package org.jaudiotagger.tag.id3;

import org.jaudiotagger.utils.EqualsUtil;

/* loaded from: classes3.dex */
public abstract class AbstractTagFrame extends AbstractTagItem {
    protected AbstractTagFrameBody frameBody;

    public AbstractTagFrame() {
    }

    public AbstractTagFrame(AbstractTagFrame abstractTagFrame) {
        AbstractTagFrameBody abstractTagFrameBody = (AbstractTagFrameBody) ID3Tags.copyObject(abstractTagFrame.frameBody);
        this.frameBody = abstractTagFrameBody;
        abstractTagFrameBody.setHeader(this);
    }

    public void setBody(AbstractTagFrameBody abstractTagFrameBody) {
        this.frameBody = abstractTagFrameBody;
        abstractTagFrameBody.setHeader(this);
    }

    public AbstractTagFrameBody getBody() {
        return this.frameBody;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean isSubsetOf(Object obj) {
        AbstractTagFrameBody abstractTagFrameBody;
        if (!(obj instanceof AbstractTagFrame)) {
            return false;
        }
        AbstractTagFrameBody abstractTagFrameBody2 = this.frameBody;
        if (abstractTagFrameBody2 == null && ((AbstractTagFrame) obj).frameBody == null) {
            return true;
        }
        return abstractTagFrameBody2 != null && (abstractTagFrameBody = ((AbstractTagFrame) obj).frameBody) != null && abstractTagFrameBody2.isSubsetOf(abstractTagFrameBody) && super.isSubsetOf(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractTagFrame)) {
            return false;
        }
        AbstractTagFrame abstractTagFrame = (AbstractTagFrame) obj;
        return EqualsUtil.areEqual(getIdentifier(), abstractTagFrame.getIdentifier()) && EqualsUtil.areEqual(this.frameBody, abstractTagFrame.frameBody) && super.equals(abstractTagFrame);
    }

    public String toString() {
        return getBody().toString();
    }
}
