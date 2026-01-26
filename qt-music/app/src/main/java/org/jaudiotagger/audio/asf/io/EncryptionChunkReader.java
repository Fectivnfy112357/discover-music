package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.EncryptionChunk;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
class EncryptionChunkReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_CONTENT_ENCRYPTION};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    protected EncryptionChunkReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException {
        EncryptionChunk encryptionChunk = new EncryptionChunk(Utils.readBig64(inputStream));
        int uint32 = (int) Utils.readUINT32(inputStream);
        byte[] bArr = new byte[uint32 + 1];
        inputStream.read(bArr, 0, uint32);
        bArr[uint32] = 0;
        int uint322 = (int) Utils.readUINT32(inputStream);
        byte[] bArr2 = new byte[uint322 + 1];
        inputStream.read(bArr2, 0, uint322);
        bArr2[uint322] = 0;
        int uint323 = (int) Utils.readUINT32(inputStream);
        byte[] bArr3 = new byte[uint323 + 1];
        inputStream.read(bArr3, 0, uint323);
        bArr3[uint323] = 0;
        int uint324 = (int) Utils.readUINT32(inputStream);
        byte[] bArr4 = new byte[uint324 + 1];
        inputStream.read(bArr4, 0, uint324);
        bArr4[uint324] = 0;
        encryptionChunk.setSecretData(new String(bArr));
        encryptionChunk.setProtectionType(new String(bArr2));
        encryptionChunk.setKeyID(new String(bArr3));
        encryptionChunk.setLicenseURL(new String(bArr4));
        encryptionChunk.setPosition(j);
        return encryptionChunk;
    }
}
