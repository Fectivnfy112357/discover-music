package org.jaudiotagger.tag.id3;

import java.util.logging.Logger;

/* loaded from: classes3.dex */
public abstract class AbstractID3Tag extends AbstractTag {
    protected static final String TAG_RELEASE = "ID3v";
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");
    private String loggingFilename;

    public abstract byte getMajorVersion();

    public abstract byte getRelease();

    public abstract byte getRevision();

    public AbstractID3Tag() {
        this.loggingFilename = "";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return TAG_RELEASE + ((int) getRelease()) + "." + ((int) getMajorVersion()) + "." + ((int) getRevision());
    }

    public AbstractID3Tag(AbstractID3Tag abstractID3Tag) {
        super(abstractID3Tag);
        this.loggingFilename = "";
    }

    public String getLoggingFilename() {
        return this.loggingFilename;
    }

    public void setLoggingFilename(String str) {
        this.loggingFilename = str;
    }
}
