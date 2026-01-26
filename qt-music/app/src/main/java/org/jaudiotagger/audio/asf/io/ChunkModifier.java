package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.jaudiotagger.audio.asf.data.GUID;

/* loaded from: classes3.dex */
public interface ChunkModifier {
    boolean isApplicable(GUID guid);

    ModificationResult modify(GUID guid, InputStream inputStream, OutputStream outputStream) throws IOException;
}
