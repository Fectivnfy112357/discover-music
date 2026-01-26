package org.jaudiotagger.audio.asf.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class AsfStreamer {
    private void copyChunk(GUID guid, InputStream inputStream, OutputStream outputStream) throws IOException {
        long uint64 = Utils.readUINT64(inputStream);
        outputStream.write(guid.getBytes());
        Utils.writeUINT64(uint64, outputStream);
        Utils.copy(inputStream, outputStream, uint64 - 24);
    }

    public void createModifiedCopy(InputStream inputStream, OutputStream outputStream, List<ChunkModifier> list) throws IOException {
        long j;
        byte[] bArr;
        long j2;
        byte[] bArr2;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
        }
        GUID guid = Utils.readGUID(inputStream);
        if (GUID.GUID_HEADER.equals(guid)) {
            long uint64 = Utils.readUINT64(inputStream);
            long uint32 = Utils.readUINT32(inputStream);
            byte[] bArr3 = {(byte) (inputStream.read() & 255), (byte) (inputStream.read() & 255)};
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long j3 = 0;
            long byteDifference = 0;
            long chunkCountDifference = 0;
            byte[] byteArray = null;
            while (j3 < uint32) {
                GUID guid2 = Utils.readGUID(inputStream);
                if (GUID.GUID_FILE.equals(guid2)) {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    long uint642 = Utils.readUINT64(inputStream);
                    Utils.writeUINT64(uint642, byteArrayOutputStream2);
                    Utils.copy(inputStream, byteArrayOutputStream2, uint642 - 24);
                    byteArray = byteArrayOutputStream2.toByteArray();
                    j = uint32;
                    bArr = bArr3;
                } else {
                    byte[] bArr4 = byteArray;
                    int i = 0;
                    boolean z = false;
                    while (i < arrayList.size() && !z) {
                        if (((ChunkModifier) arrayList.get(i)).isApplicable(guid2)) {
                            bArr2 = bArr3;
                            j2 = uint32;
                            chunkCountDifference += r9.getChunkCountDifference();
                            byteDifference += ((ChunkModifier) arrayList.get(i)).modify(guid2, inputStream, byteArrayOutputStream).getByteDifference();
                            arrayList.remove(i);
                            z = true;
                        } else {
                            j2 = uint32;
                            bArr2 = bArr3;
                        }
                        i++;
                        bArr3 = bArr2;
                        uint32 = j2;
                    }
                    j = uint32;
                    bArr = bArr3;
                    if (!z) {
                        copyChunk(guid2, inputStream, byteArrayOutputStream);
                    }
                    byteArray = bArr4;
                }
                j3++;
                bArr3 = bArr;
                uint32 = j;
            }
            long j4 = uint32;
            byte[] bArr5 = byteArray;
            byte[] bArr6 = bArr3;
            Iterator it = arrayList.iterator();
            long byteDifference2 = byteDifference;
            while (it.hasNext()) {
                chunkCountDifference += r9.getChunkCountDifference();
                byteDifference2 += ((ChunkModifier) it.next()).modify(null, null, byteArrayOutputStream).getByteDifference();
            }
            outputStream.write(guid.getBytes());
            Utils.writeUINT64(uint64 + byteDifference2, outputStream);
            Utils.writeUINT32(j4 + chunkCountDifference, outputStream);
            outputStream.write(bArr6);
            modifyFileHeader(new ByteArrayInputStream(bArr5), outputStream, byteDifference2);
            outputStream.write(byteArrayOutputStream.toByteArray());
            Utils.flush(inputStream, outputStream);
            return;
        }
        throw new IllegalArgumentException("No ASF header object.");
    }

    private void modifyFileHeader(InputStream inputStream, OutputStream outputStream, long j) throws IOException {
        outputStream.write(GUID.GUID_FILE.getBytes());
        long uint64 = Utils.readUINT64(inputStream);
        Utils.writeUINT64(uint64, outputStream);
        outputStream.write(Utils.readGUID(inputStream).getBytes());
        Utils.writeUINT64(Utils.readUINT64(inputStream) + j, outputStream);
        Utils.copy(inputStream, outputStream, uint64 - 48);
    }
}
