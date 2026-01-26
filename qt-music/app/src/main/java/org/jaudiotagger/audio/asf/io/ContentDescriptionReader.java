package org.jaudiotagger.audio.asf.io;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import org.jaudiotagger.audio.asf.data.Chunk;
import org.jaudiotagger.audio.asf.data.ContentDescription;
import org.jaudiotagger.audio.asf.data.GUID;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class ContentDescriptionReader implements ChunkReader {
    private static final GUID[] APPLYING = {GUID.GUID_CONTENTDESCRIPTION};

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public boolean canFail() {
        return false;
    }

    protected ContentDescriptionReader() {
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public GUID[] getApplyingIds() {
        return (GUID[]) APPLYING.clone();
    }

    private int[] getStringSizes(InputStream inputStream) throws IOException {
        int[] iArr = new int[5];
        for (int i = 0; i < 5; i++) {
            iArr[i] = Utils.readUINT16(inputStream);
        }
        return iArr;
    }

    @Override // org.jaudiotagger.audio.asf.io.ChunkReader
    public Chunk read(GUID guid, InputStream inputStream, long j) throws IOException, IllegalArgumentException {
        BigInteger big64 = Utils.readBig64(inputStream);
        int[] stringSizes = getStringSizes(inputStream);
        int length = stringSizes.length;
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            int i2 = stringSizes[i];
            if (i2 > 0) {
                strArr[i] = Utils.readFixedSizeUTF16Str(inputStream, i2);
            }
        }
        ContentDescription contentDescription = new ContentDescription(j, big64);
        if (stringSizes[0] > 0) {
            contentDescription.setTitle(strArr[0]);
        }
        if (stringSizes[1] > 0) {
            contentDescription.setAuthor(strArr[1]);
        }
        if (stringSizes[2] > 0) {
            contentDescription.setCopyright(strArr[2]);
        }
        if (stringSizes[3] > 0) {
            contentDescription.setComment(strArr[3]);
        }
        if (stringSizes[4] > 0) {
            contentDescription.setRating(strArr[4]);
        }
        return contentDescription;
    }
}
