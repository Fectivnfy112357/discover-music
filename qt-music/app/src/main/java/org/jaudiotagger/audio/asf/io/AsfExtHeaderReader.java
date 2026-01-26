package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.List;
import org.jaudiotagger.audio.asf.data.AsfExtendedHeader;
import org.jaudiotagger.audio.asf.data.ChunkContainer;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class AsfExtHeaderReader extends ChunkContainerReader<AsfExtendedHeader> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final GUID[] APPLYING = {GUID.GUID_HEADER_EXTENSION};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkContainerReader, org.jaudiotagger.audio.asf.io.ChunkReader
    public /* bridge */ /* synthetic */ ChunkContainer read(GUID guid, InputStream inputStream, long j) throws IOException, IllegalArgumentException {
        return super.read(guid, inputStream, j);
    }

    public AsfExtHeaderReader(List<Class<? extends ChunkReader>> list, boolean z) {
        super(list, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jaudiotagger.audio.asf.io.ChunkContainerReader
    public AsfExtendedHeader createContainer(long j, BigInteger bigInteger, InputStream inputStream) throws IOException {
        Utils.readGUID(inputStream);
        Utils.readUINT16(inputStream);
        Utils.readUINT32(inputStream);
        return new AsfExtendedHeader(j, bigInteger);
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }
}
