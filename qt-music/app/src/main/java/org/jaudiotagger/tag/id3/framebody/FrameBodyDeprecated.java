package org.jaudiotagger.tag.id3.framebody;

/* loaded from: classes3.dex */
public class FrameBodyDeprecated extends AbstractID3v2FrameBody implements ID3v24FrameBody, ID3v23FrameBody {
    private AbstractID3v2FrameBody originalFrameBody;

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    protected void setupObjectList() {
    }

    public FrameBodyDeprecated(AbstractID3v2FrameBody abstractID3v2FrameBody) {
        this.originalFrameBody = abstractID3v2FrameBody;
    }

    public FrameBodyDeprecated(FrameBodyDeprecated frameBodyDeprecated) {
        super(frameBodyDeprecated);
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return this.originalFrameBody.getIdentifier();
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return this.originalFrameBody.getSize();
    }

    @Override // org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody, org.jaudiotagger.tag.id3.AbstractTagFrameBody, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof FrameBodyDeprecated) && getIdentifier().equals(((FrameBodyDeprecated) obj).getIdentifier()) && super.equals(obj);
    }

    public AbstractID3v2FrameBody getOriginalFrameBody() {
        return this.originalFrameBody;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String toString() {
        return getIdentifier();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrameBody
    public String getBriefDescription() {
        AbstractID3v2FrameBody abstractID3v2FrameBody = this.originalFrameBody;
        if (abstractID3v2FrameBody != null) {
            return abstractID3v2FrameBody.getBriefDescription();
        }
        return "";
    }
}
