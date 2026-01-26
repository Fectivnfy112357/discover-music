package org.jaudiotagger.audio.generic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagOptionSingleton;

/* loaded from: classes3.dex */
public abstract class AudioFileWriter2 extends AudioFileWriter {
    protected abstract void deleteTag(Tag tag, Path path) throws CannotReadException, CannotWriteException;

    protected abstract void writeTag(Tag tag, Path path) throws CannotWriteException;

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    public void delete(AudioFile audioFile) throws CannotReadException, CannotWriteException {
        Path path = audioFile.getFile().toPath();
        if (TagOptionSingleton.getInstance().isCheckIsWritable() && !Files.isWritable(path)) {
            logger.severe(Permissions.displayPermissions(path));
            throw new CannotWriteException(ErrorMessage.GENERAL_DELETE_FAILED.getMsg(path));
        }
        if (audioFile.getFile().length() <= 100) {
            throw new CannotWriteException(ErrorMessage.GENERAL_DELETE_FAILED_BECAUSE_FILE_IS_TOO_SMALL.getMsg(path));
        }
        deleteTag(audioFile.getTag(), path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    public void write(AudioFile audioFile) throws CannotWriteException {
        Path path = audioFile.getFile().toPath();
        if (TagOptionSingleton.getInstance().isCheckIsWritable() && !Files.isWritable(path)) {
            logger.severe(Permissions.displayPermissions(path));
            logger.severe(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(audioFile.getFile().getPath()));
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(path));
        }
        if (audioFile.getFile().length() <= 100) {
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_IS_TOO_SMALL.getMsg(path));
        }
        writeTag(audioFile.getTag(), path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    public void deleteTag(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        throw new UnsupportedOperationException("Old method not used in version 2");
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter
    protected void writeTag(AudioFile audioFile, Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        throw new UnsupportedOperationException("Old method not used in version 2");
    }
}
