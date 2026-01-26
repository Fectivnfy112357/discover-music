package org.jaudiotagger.audio.real;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class RealFileReader extends AudioFileReader {
    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected GenericAudioHeader getEncodingInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        GenericAudioHeader genericAudioHeader = new GenericAudioHeader();
        DataInputStream dataInputStream = findPropChunk(randomAccessFile).getDataInputStream();
        if (Utils.readUint16(dataInputStream) == 0) {
            long uint32 = Utils.readUint32(dataInputStream) / 1000;
            long uint322 = Utils.readUint32(dataInputStream) / 1000;
            Utils.readUint32(dataInputStream);
            Utils.readUint32(dataInputStream);
            Utils.readUint32(dataInputStream);
            int uint323 = ((int) Utils.readUint32(dataInputStream)) / 1000;
            Utils.readUint32(dataInputStream);
            Utils.readUint32(dataInputStream);
            Utils.readUint32(dataInputStream);
            Utils.readUint16(dataInputStream);
            Utils.readUint16(dataInputStream);
            genericAudioHeader.setBitRate((int) uint322);
            genericAudioHeader.setPreciseLength(uint323);
            genericAudioHeader.setVariableBitRate(uint32 != uint322);
            genericAudioHeader.setFormat(SupportedFileFormat.RA.getDisplayName());
        }
        return genericAudioHeader;
    }

    private RealChunk findPropChunk(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        RealChunk.readChunk(randomAccessFile);
        return RealChunk.readChunk(randomAccessFile);
    }

    private RealChunk findContChunk(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        RealChunk.readChunk(randomAccessFile);
        RealChunk.readChunk(randomAccessFile);
        RealChunk chunk = RealChunk.readChunk(randomAccessFile);
        while (!chunk.isCONT()) {
            chunk = RealChunk.readChunk(randomAccessFile);
        }
        return chunk;
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected Tag getTag(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        DataInputStream dataInputStream = findContChunk(randomAccessFile).getDataInputStream();
        String string = Utils.readString(dataInputStream, Utils.readUint16(dataInputStream));
        String string2 = Utils.readString(dataInputStream, Utils.readUint16(dataInputStream));
        String string3 = Utils.readString(dataInputStream, Utils.readUint16(dataInputStream));
        String string4 = Utils.readString(dataInputStream, Utils.readUint16(dataInputStream));
        RealTag realTag = new RealTag();
        try {
            FieldKey fieldKey = FieldKey.TITLE;
            String[] strArr = new String[1];
            strArr[0] = string.length() == 0 ? string2 : string;
            realTag.addField(fieldKey, strArr);
            FieldKey fieldKey2 = FieldKey.ARTIST;
            String[] strArr2 = new String[1];
            if (string.length() == 0) {
                string2 = string3;
            }
            strArr2[0] = string2;
            realTag.addField(fieldKey2, strArr2);
            realTag.addField(FieldKey.COMMENT, string4);
            return realTag;
        } catch (FieldDataInvalidException e) {
            throw new RuntimeException(e);
        }
    }
}
