package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.ContentBranding;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class ContentBrandingReader implements ChunkReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final GUID[] APPLYING = {GUID.GUID_CONTENT_BRANDING};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    protected ContentBrandingReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException, IllegalArgumentException {
        BigInteger big64 = Utils.readBig64(inputStream);
        long uint32 = Utils.readUINT32(inputStream);
        byte[] binary = Utils.readBinary(inputStream, Utils.readUINT32(inputStream));
        String str = new String(Utils.readBinary(inputStream, Utils.readUINT32(inputStream)));
        String str2 = new String(Utils.readBinary(inputStream, Utils.readUINT32(inputStream)));
        ContentBranding contentBranding = new ContentBranding(j, big64);
        contentBranding.setImage(uint32, binary);
        contentBranding.setCopyRightURL(str);
        contentBranding.setBannerImageURL(str2);
        return contentBranding;
    }
}
