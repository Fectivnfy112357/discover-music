package org.jaudiotagger.audio.asf.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.ChunkContainer;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class AsfHeaderReader extends ChunkContainerReader<AsfHeader> {
    private static final GUID[] APPLYING = {GUID.GUID_HEADER};
    private static final AsfHeaderReader FULL_READER;
    private static final AsfHeaderReader INFO_READER;
    private static final AsfHeaderReader TAG_READER;

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkContainerReader, org.jaudiotagger.audio.asf.io.ChunkReader
    public /* bridge */ /* synthetic */ ChunkContainer read(GUID guid, InputStream inputStream, long j) throws IOException, IllegalArgumentException {
        return super.read(guid, inputStream, j);
    }

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(FileHeaderReader.class);
        arrayList.add(StreamChunkReader.class);
        INFO_READER = new AsfHeaderReader(arrayList, true);
        arrayList.clear();
        arrayList.add(ContentDescriptionReader.class);
        arrayList.add(ContentBrandingReader.class);
        arrayList.add(LanguageListReader.class);
        arrayList.add(MetadataReader.class);
        AsfExtHeaderReader asfExtHeaderReader = new AsfExtHeaderReader(arrayList, true);
        AsfExtHeaderReader asfExtHeaderReader2 = new AsfExtHeaderReader(arrayList, true);
        AsfHeaderReader asfHeaderReader = new AsfHeaderReader(arrayList, true);
        TAG_READER = asfHeaderReader;
        asfHeaderReader.setExtendedHeaderReader(asfExtHeaderReader);
        arrayList.add(FileHeaderReader.class);
        arrayList.add(StreamChunkReader.class);
        arrayList.add(EncodingChunkReader.class);
        arrayList.add(EncryptionChunkReader.class);
        arrayList.add(StreamBitratePropertiesReader.class);
        AsfHeaderReader asfHeaderReader2 = new AsfHeaderReader(arrayList, false);
        FULL_READER = asfHeaderReader2;
        asfHeaderReader2.setExtendedHeaderReader(asfExtHeaderReader2);
    }

    private static InputStream createStream(RandomAccessFile randomAccessFile) {
        return new FullRequestInputStream(new BufferedInputStream(new RandomAccessFileInputstream(randomAccessFile)));
    }

    public static AsfHeader readHeader(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        AsfHeader asfHeader = (AsfHeader) FULL_READER.read(Utils.readGUID(fileInputStream), (InputStream) fileInputStream, 0L);
        fileInputStream.close();
        return asfHeader;
    }

    public static AsfHeader readHeader(RandomAccessFile randomAccessFile) throws IOException {
        InputStream inputStreamCreateStream = createStream(randomAccessFile);
        return (AsfHeader) FULL_READER.read(Utils.readGUID(inputStreamCreateStream), inputStreamCreateStream, 0L);
    }

    public static AsfHeader readInfoHeader(RandomAccessFile randomAccessFile) throws IOException {
        InputStream inputStreamCreateStream = createStream(randomAccessFile);
        return (AsfHeader) INFO_READER.read(Utils.readGUID(inputStreamCreateStream), inputStreamCreateStream, 0L);
    }

    public static AsfHeader readTagHeader(RandomAccessFile randomAccessFile) throws IOException {
        InputStream inputStreamCreateStream = createStream(randomAccessFile);
        return (AsfHeader) TAG_READER.read(Utils.readGUID(inputStreamCreateStream), inputStreamCreateStream, 0L);
    }

    public AsfHeaderReader(List<Class<? extends ChunkReader>> list, boolean z) {
        super(list, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jaudiotagger.audio.asf.io.ChunkContainerReader
    public AsfHeader createContainer(long j, BigInteger bigInteger, InputStream inputStream) throws IOException {
        long uint32 = Utils.readUINT32(inputStream);
        if (inputStream.read() != 1) {
            throw new IOException("No ASF");
        }
        if (inputStream.read() != 2) {
            throw new IOException("No ASF");
        }
        return new AsfHeader(j, bigInteger, uint32);
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    public void setExtendedHeaderReader(AsfExtHeaderReader asfExtHeaderReader) {
        for (GUID guid : asfExtHeaderReader.getApplyingIds()) {
            this.readerMap.put(guid, asfExtHeaderReader);
        }
    }
}
