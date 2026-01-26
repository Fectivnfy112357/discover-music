package org.jaudiotagger.audio.ogg;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.generic.AudioFileWriter;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class OggFileWriter extends AudioFileWriter {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg");
    private OggVorbisTagWriter vtw = new OggVorbisTagWriter();

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void writeTag(AudioFile audioFile, Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        this.vtw.write(tag, randomAccessFile, randomAccessFile2);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void deleteTag(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        this.vtw.delete(randomAccessFile, randomAccessFile2);
    }
}
