package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class ChunkRemover implements ChunkModifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Set<GUID> toRemove = new HashSet();

    public ChunkRemover(GUID... guidArr) {
        for (GUID guid : guidArr) {
            this.toRemove.add(guid);
        }
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkModifier
    public boolean isApplicable(GUID guid) {
        return this.toRemove.contains(guid);
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkModifier
    public ModificationResult modify(GUID guid, InputStream inputStream, OutputStream outputStream) throws IOException {
        if (guid == null) {
            return new ModificationResult(0, 0L, new GUID[0]);
        }
        long uint64 = Utils.readUINT64(inputStream);
        inputStream.skip(uint64 - 24);
        return new ModificationResult(-1, uint64 * (-1), guid);
    }
}
