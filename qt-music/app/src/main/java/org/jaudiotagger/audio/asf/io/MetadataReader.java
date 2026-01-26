package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.ContainerType;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.data.MetadataContainer;
import org.jaudiotagger.audio.asf.data.MetadataDescriptor;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class MetadataReader implements ChunkReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final GUID[] APPLYING = {ContainerType.EXTENDED_CONTENT.getContainerGUID(), ContainerType.METADATA_OBJECT.getContainerGUID(), ContainerType.METADATA_LIBRARY_OBJECT.getContainerGUID()};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException, IllegalArgumentException {
        int uint16;
        int uint162;
        MetadataContainer metadataContainer = new MetadataContainer(guid, j, Utils.readBig64(inputStream));
        boolean z = metadataContainer.getContainerType() == ContainerType.EXTENDED_CONTENT;
        int uint163 = Utils.readUINT16(inputStream);
        int i = 0;
        while (i < uint163) {
            if (z) {
                uint16 = 0;
                uint162 = 0;
            } else {
                uint162 = Utils.readUINT16(inputStream);
                uint16 = Utils.readUINT16(inputStream);
            }
            int uint164 = Utils.readUINT16(inputStream);
            String fixedSizeUTF16Str = z ? Utils.readFixedSizeUTF16Str(inputStream, uint164) : null;
            int uint165 = Utils.readUINT16(inputStream);
            long uint166 = z ? Utils.readUINT16(inputStream) : Utils.readUINT32(inputStream);
            int i2 = i;
            MetadataDescriptor metadataDescriptor = new MetadataDescriptor(metadataContainer.getContainerType(), !z ? Utils.readFixedSizeUTF16Str(inputStream, uint164) : fixedSizeUTF16Str, uint165, uint16, uint162);
            switch (uint165) {
                case 0:
                    metadataDescriptor.setStringValue(Utils.readFixedSizeUTF16Str(inputStream, (int) uint166));
                    continue;
                    metadataContainer.addDescriptor(metadataDescriptor);
                    i = i2 + 1;
                case 1:
                    metadataDescriptor.setBinaryValue(Utils.readBinary(inputStream, uint166));
                    continue;
                    metadataContainer.addDescriptor(metadataDescriptor);
                    i = i2 + 1;
                case 2:
                    metadataDescriptor.setBooleanValue(readBoolean(inputStream, (int) uint166));
                    continue;
                    metadataContainer.addDescriptor(metadataDescriptor);
                    i = i2 + 1;
                case 3:
                    metadataDescriptor.setDWordValue(Utils.readUINT32(inputStream));
                    break;
                case 4:
                    metadataDescriptor.setQWordValue(Utils.readUINT64(inputStream));
                    break;
                case 5:
                    metadataDescriptor.setWordValue(Utils.readUINT16(inputStream));
                    break;
                case 6:
                    metadataDescriptor.setGUIDValue(Utils.readGUID(inputStream));
                    break;
                default:
                    metadataDescriptor.setStringValue("Invalid datatype: ".concat(new String(Utils.readBinary(inputStream, uint166))));
                    continue;
                    metadataContainer.addDescriptor(metadataDescriptor);
                    i = i2 + 1;
            }
            metadataContainer.addDescriptor(metadataDescriptor);
            i = i2 + 1;
        }
        return metadataContainer;
    }

    private boolean readBoolean(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        inputStream.read(bArr);
        return bArr[i - 1] == 1;
    }
}
