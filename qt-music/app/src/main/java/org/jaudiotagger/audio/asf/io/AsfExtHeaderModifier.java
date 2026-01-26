package org.jaudiotagger.audio.asf.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class AsfExtHeaderModifier implements ChunkModifier {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final List<ChunkModifier> modifierList;

    public AsfExtHeaderModifier(List<ChunkModifier> list) {
        this.modifierList = new ArrayList(list);
    }

    private void copyChunk(GUID guid, InputStream inputStream, OutputStream outputStream) throws IOException {
        long uint64 = Utils.readUINT64(inputStream);
        outputStream.write(guid.getBytes());
        Utils.writeUINT64(uint64, outputStream);
        Utils.copy(inputStream, outputStream, uint64 - 24);
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkModifier
    public boolean isApplicable(GUID guid) {
        return GUID.GUID_HEADER_EXTENSION.equals(guid);
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkModifier
    public ModificationResult modify(GUID guid, InputStream inputStream, OutputStream outputStream) throws IOException {
        ArrayList arrayList = new ArrayList(this.modifierList);
        HashSet hashSet = new HashSet();
        hashSet.add(guid);
        BigInteger big64 = Utils.readBig64(inputStream);
        GUID guid2 = Utils.readGUID(inputStream);
        int uint16 = Utils.readUINT16(inputStream);
        long uint32 = Utils.readUINT32(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CountingInputStream countingInputStream = new CountingInputStream(inputStream);
        long byteDifference = 0;
        while (true) {
            if (countingInputStream.getReadCount() >= uint32) {
                break;
            }
            GUID guid3 = Utils.readGUID(countingInputStream);
            long j = uint32;
            boolean z = false;
            for (int i = 0; i < arrayList.size() && !z; i++) {
                if (((ChunkModifier) arrayList.get(i)).isApplicable(guid3)) {
                    ModificationResult modificationResultModify = ((ChunkModifier) arrayList.get(i)).modify(guid3, countingInputStream, byteArrayOutputStream);
                    byteDifference += modificationResultModify.getByteDifference();
                    hashSet.addAll(modificationResultModify.getOccuredGUIDs());
                    arrayList.remove(i);
                    z = true;
                }
            }
            if (!z) {
                hashSet.add(guid3);
                copyChunk(guid3, countingInputStream, byteArrayOutputStream);
            }
            uint32 = j;
        }
        long j2 = uint32;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ModificationResult modificationResultModify2 = ((ChunkModifier) it.next()).modify(null, null, byteArrayOutputStream);
            byteDifference += modificationResultModify2.getByteDifference();
            hashSet.addAll(modificationResultModify2.getOccuredGUIDs());
        }
        outputStream.write(GUID.GUID_HEADER_EXTENSION.getBytes());
        Utils.writeUINT64(big64.add(BigInteger.valueOf(byteDifference)).longValue(), outputStream);
        outputStream.write(guid2.getBytes());
        Utils.writeUINT16(uint16, outputStream);
        Utils.writeUINT32(j2 + byteDifference, outputStream);
        outputStream.write(byteArrayOutputStream.toByteArray());
        return new ModificationResult(0, byteDifference, hashSet);
    }
}
