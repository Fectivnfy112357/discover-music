package org.jaudiotagger.audio.generic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.logging.Level;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.NoReadPermissionsException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

/* loaded from: classes3.dex */
public abstract class AudioFileReader2 extends AudioFileReader {
    protected abstract GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException;

    protected abstract Tag getTag(Path path) throws CannotReadException, IOException;

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    public AudioFile read(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        Path path = file.toPath();
        if (logger.isLoggable(Level.CONFIG)) {
            logger.config(ErrorMessage.GENERAL_READ.getMsg(path));
        }
        if (Files.isReadable(path)) {
            if (file.length() <= 100) {
                throw new CannotReadException(ErrorMessage.GENERAL_READ_FAILED_FILE_TOO_SMALL.getMsg(path));
            }
            return new AudioFile(file, getEncodingInfo(path), getTag(path));
        }
        if (!Files.exists(path, new LinkOption[0])) {
            throw new FileNotFoundException(ErrorMessage.UNABLE_TO_FIND_FILE.getMsg(path));
        }
        logger.warning(Permissions.displayPermissions(path));
        throw new NoReadPermissionsException(ErrorMessage.GENERAL_READ_FAILED_DO_NOT_HAVE_PERMISSION_TO_READ_FILE.getMsg(path));
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected GenericAudioHeader getEncodingInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        throw new UnsupportedOperationException("Old method not used in version 2");
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected Tag getTag(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        throw new UnsupportedOperationException("Old method not used in version 2");
    }
}
