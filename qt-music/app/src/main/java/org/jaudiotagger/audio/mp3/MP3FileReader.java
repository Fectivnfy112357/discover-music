package org.jaudiotagger.audio.mp3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.generic.AudioFileReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/* loaded from: classes3.dex */
public class MP3FileReader extends AudioFileReader {
    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected GenericAudioHeader getEncodingInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        throw new RuntimeException("MP3FileReader.getEncodingInfo should be called");
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected Tag getTag(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        throw new RuntimeException("MP3FileReader.getEncodingInfo should be called");
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    public AudioFile read(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        return new MP3File(file, 6, true);
    }

    public AudioFile readMustBeWritable(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        return new MP3File(file, 6, false);
    }
}
