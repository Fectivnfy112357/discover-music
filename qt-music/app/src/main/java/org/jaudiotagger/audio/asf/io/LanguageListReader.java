package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.data.LanguageList;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class LanguageListReader implements ChunkReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final GUID[] APPLYING = {GUID.GUID_LANGUAGE_LIST};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        BigInteger big64 = Utils.readBig64(inputStream);
        int uint16 = Utils.readUINT16(inputStream);
        LanguageList languageList = new LanguageList(j, big64);
        for (int i = 0; i < uint16; i++) {
            languageList.addLanguage(Utils.readFixedSizeUTF16Str(inputStream, inputStream.read() & 255));
        }
        return languageList;
    }
}
