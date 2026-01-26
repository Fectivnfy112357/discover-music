package org.jaudiotagger.audio.asf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.ChunkContainer;
import org.jaudiotagger.audio.asf.data.MetadataContainer;
import org.jaudiotagger.audio.asf.io.AsfExtHeaderModifier;
import org.jaudiotagger.audio.asf.io.AsfHeaderReader;
import org.jaudiotagger.audio.asf.io.AsfStreamer;
import org.jaudiotagger.audio.asf.io.RandomAccessFileInputstream;
import org.jaudiotagger.audio.asf.io.RandomAccessFileOutputStream;
import org.jaudiotagger.audio.asf.io.WriteableChunkModifer;
import org.jaudiotagger.audio.asf.util.TagConverter;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.generic.AudioFileWriter;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.asf.AsfTag;

/* loaded from: classes3.dex */
public class AsfFileWriter extends AudioFileWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void deleteTag(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException, IllegalArgumentException, CannotWriteException {
        writeTag(null, new AsfTag(true), randomAccessFile, randomAccessFile2);
    }

    private boolean[] searchExistence(ChunkContainer chunkContainer, MetadataContainer[] metadataContainerArr) {
        int length = metadataContainerArr.length;
        boolean[] zArr = new boolean[length];
        for (int i = 0; i < length; i++) {
            zArr[i] = chunkContainer.hasChunkByGUID(metadataContainerArr[i].getContainerType().getContainerGUID());
        }
        return zArr;
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void writeTag(AudioFile audioFile, Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException, IllegalArgumentException, CannotWriteException {
        AsfHeader tagHeader = AsfHeaderReader.readTagHeader(randomAccessFile);
        randomAccessFile.seek(0L);
        MetadataContainer[] metadataContainerArrDistributeMetadata = TagConverter.distributeMetadata(new AsfTag(tag, true));
        boolean[] zArrSearchExistence = searchExistence(tagHeader, metadataContainerArrDistributeMetadata);
        boolean[] zArrSearchExistence2 = searchExistence(tagHeader.getExtendedHeader(), metadataContainerArrDistributeMetadata);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < metadataContainerArrDistributeMetadata.length; i++) {
            WriteableChunkModifer writeableChunkModifer = new WriteableChunkModifer(metadataContainerArrDistributeMetadata[i]);
            if (zArrSearchExistence[i]) {
                arrayList.add(writeableChunkModifer);
            } else if (zArrSearchExistence2[i]) {
                arrayList2.add(writeableChunkModifer);
            } else if (i == 0 || i == 2 || i == 1) {
                arrayList.add(writeableChunkModifer);
            } else {
                arrayList2.add(writeableChunkModifer);
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList.add(new AsfExtHeaderModifier(arrayList2));
        }
        new AsfStreamer().createModifiedCopy(new RandomAccessFileInputstream(randomAccessFile), new RandomAccessFileOutputStream(randomAccessFile2), arrayList);
    }
}
