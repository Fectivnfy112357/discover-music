package org.jaudiotagger.audio.generic;

import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public interface TagWriter {
    void delete(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException, CannotWriteException;

    void write(AudioFile audioFile, Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws IOException, CannotWriteException;
}
