package org.jaudiotagger.audio.mp3;

import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.generic.AudioFileWriter;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;

/* loaded from: classes3.dex */
public class MP3FileWriter extends AudioFileWriter {
    public void deleteTag(AudioFile audioFile) throws CannotWriteException {
        audioFile.commit();
    }

    public void writeFile(AudioFile audioFile) throws CannotWriteException {
        audioFile.commit();
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    public synchronized void delete(AudioFile audioFile) throws CannotReadException, CannotWriteException {
        ((MP3File) audioFile).setID3v1Tag((ID3v1Tag) null);
        ((MP3File) audioFile).setID3v2Tag((AbstractID3v2Tag) null);
        audioFile.commit();
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void writeTag(AudioFile audioFile, Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException, CannotWriteException {
        throw new RuntimeException("MP3FileReaderwriteTag should not be called");
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void deleteTag(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException, CannotWriteException {
        throw new RuntimeException("MP3FileReader.getEncodingInfo should be called");
    }
}
