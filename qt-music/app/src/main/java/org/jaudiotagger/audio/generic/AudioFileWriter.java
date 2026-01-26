package org.jaudiotagger.audio.generic;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.ModifyVetoException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagOptionSingleton;

/* loaded from: classes3.dex */
public abstract class AudioFileWriter {
    private static final String FILE_NAME_TOO_LONG = "File name too long";
    private static final int FILE_NAME_TOO_LONG_SAFE_LIMIT = 50;
    protected static final int MINIMUM_FILESIZE = 100;
    private static final String TEMP_FILENAME_SUFFIX = ".tmp";
    private static final String WRITE_MODE = "rw";
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.generic");
    private AudioFileModificationListener modificationListener = null;

    protected abstract void deleteTag(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException;

    protected abstract void writeTag(AudioFile audioFile, Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException;

    /* JADX WARN: Removed duplicated region for block: B:104:0x02b9 A[Catch: Exception -> 0x01fa, TRY_LEAVE, TryCatch #8 {Exception -> 0x01fa, blocks: (B:78:0x01f6, B:82:0x01ff, B:83:0x0202, B:86:0x020c, B:88:0x0216, B:98:0x0245, B:99:0x027b, B:100:0x027c, B:101:0x02b2, B:102:0x02b3, B:104:0x02b9), top: B:117:0x01f6 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01f6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ff A[Catch: Exception -> 0x01fa, TryCatch #8 {Exception -> 0x01fa, blocks: (B:78:0x01f6, B:82:0x01ff, B:83:0x0202, B:86:0x020c, B:88:0x0216, B:98:0x0245, B:99:0x027b, B:100:0x027c, B:101:0x02b2, B:102:0x02b3, B:104:0x02b9), top: B:117:0x01f6 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void delete(org.jaudiotagger.audio.AudioFile r17) throws org.jaudiotagger.audio.exceptions.CannotReadException, org.jaudiotagger.audio.exceptions.CannotWriteException {
        /*
            Method dump skipped, instructions count: 788
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.generic.AudioFileWriter.delete(org.jaudiotagger.audio.AudioFile):void");
    }

    public void delete(Tag tag, RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2) throws CannotReadException, IOException, CannotWriteException {
        randomAccessFile.seek(0L);
        randomAccessFile2.seek(0L);
        deleteTag(tag, randomAccessFile, randomAccessFile2);
    }

    public void setAudioFileModificationListener(AudioFileModificationListener audioFileModificationListener) {
        this.modificationListener = audioFileModificationListener;
    }

    private void precheckWrite(AudioFile audioFile) throws CannotWriteException {
        try {
            if (audioFile.getTag().isEmpty()) {
                delete(audioFile);
                return;
            }
            Path path = audioFile.getFile().toPath();
            if (TagOptionSingleton.getInstance().isCheckIsWritable() && !Files.isWritable(path)) {
                logger.severe(Permissions.displayPermissions(path));
                logger.severe(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(audioFile.getFile().getPath()));
                throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(path));
            }
            if (audioFile.getFile().length() > 100) {
                return;
            }
            logger.severe(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_IS_TOO_SMALL.getMsg(path));
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_IS_TOO_SMALL.getMsg(path));
        } catch (CannotReadException unused) {
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(audioFile.getFile().getPath()));
        }
    }

    public void write(AudioFile audioFile) throws CannotWriteException {
        File fileCreateTempFile;
        logger.config("Started writing tag data for file:" + audioFile.getFile().getName());
        precheckWrite(audioFile);
        if (audioFile instanceof MP3File) {
            audioFile.commit();
            return;
        }
        try {
            fileCreateTempFile = File.createTempFile(audioFile.getFile().getName().replace('.', '_'), ".tmp", audioFile.getFile().getParentFile());
        } catch (IOException e) {
            if (e.getMessage().equals(FILE_NAME_TOO_LONG) && audioFile.getFile().getName().length() > 50) {
                try {
                    fileCreateTempFile = File.createTempFile(audioFile.getFile().getName().substring(0, 50).replace('.', '_'), ".tmp", audioFile.getFile().getParentFile());
                } catch (IOException e2) {
                    logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_TO_CREATE_TEMPORARY_FILE_IN_FOLDER.getMsg(audioFile.getFile().getName(), audioFile.getFile().getParentFile().getAbsolutePath()), (Throwable) e2);
                    throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_CREATE_TEMPORARY_FILE_IN_FOLDER.getMsg(audioFile.getFile().getName(), audioFile.getFile().getParentFile().getAbsolutePath()));
                }
            } else {
                logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_TO_CREATE_TEMPORARY_FILE_IN_FOLDER.getMsg(audioFile.getFile().getName(), audioFile.getFile().getParentFile().getAbsolutePath()), (Throwable) e);
                throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_CREATE_TEMPORARY_FILE_IN_FOLDER.getMsg(audioFile.getFile().getName(), audioFile.getFile().getParentFile().getAbsolutePath()));
            }
        }
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(fileCreateTempFile, WRITE_MODE);
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(audioFile.getFile(), WRITE_MODE);
                try {
                    try {
                        randomAccessFile3.seek(0L);
                        randomAccessFile2.seek(0L);
                        try {
                            AudioFileModificationListener audioFileModificationListener = this.modificationListener;
                            if (audioFileModificationListener != null) {
                                audioFileModificationListener.fileWillBeModified(audioFile, false);
                            }
                            writeTag(audioFile, audioFile.getTag(), randomAccessFile3, randomAccessFile2);
                            AudioFileModificationListener audioFileModificationListener2 = this.modificationListener;
                            if (audioFileModificationListener2 != null) {
                                audioFileModificationListener2.fileModified(audioFile, fileCreateTempFile);
                            }
                            File file = audioFile.getFile();
                            if (fileCreateTempFile.length() > 0) {
                                transferNewFileToOriginalFile(fileCreateTempFile, audioFile.getFile(), TagOptionSingleton.getInstance().isPreserveFileIdentity());
                            } else if (!fileCreateTempFile.delete()) {
                                logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_DELETE_TEMPORARY_FILE.getMsg(fileCreateTempFile.getPath()));
                            }
                            AudioFileModificationListener audioFileModificationListener3 = this.modificationListener;
                            if (audioFileModificationListener3 != null) {
                                audioFileModificationListener3.fileOperationFinished(file);
                            }
                        } catch (ModifyVetoException e3) {
                            throw new CannotWriteException(e3);
                        }
                    } catch (Exception e4) {
                        logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE.getMsg(audioFile.getFile(), e4.getMessage()), (Throwable) e4);
                        try {
                            randomAccessFile3.close();
                            randomAccessFile2.close();
                        } catch (IOException e5) {
                            logger.log(Level.WARNING, ErrorMessage.GENERAL_WRITE_PROBLEM_CLOSING_FILE_HANDLE.getMsg(audioFile.getFile().getAbsolutePath(), e5.getMessage()), (Throwable) e5);
                        }
                        if (!fileCreateTempFile.delete()) {
                            logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_DELETE_TEMPORARY_FILE.getMsg(fileCreateTempFile.getAbsolutePath()));
                        }
                        throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE.getMsg(audioFile.getFile(), e4.getMessage()));
                    }
                } finally {
                    try {
                        randomAccessFile3.close();
                        randomAccessFile2.close();
                    } catch (IOException e6) {
                        logger.log(Level.WARNING, ErrorMessage.GENERAL_WRITE_PROBLEM_CLOSING_FILE_HANDLE.getMsg(audioFile.getFile().getAbsolutePath(), e6.getMessage()), (Throwable) e6);
                    }
                }
            } catch (IOException e7) {
                e = e7;
                randomAccessFile = randomAccessFile2;
                logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(audioFile.getFile().getAbsolutePath()), (Throwable) e);
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e8) {
                        logger.log(Level.WARNING, ErrorMessage.GENERAL_WRITE_PROBLEM_CLOSING_FILE_HANDLE.getMsg(audioFile.getFile(), e.getMessage()), (Throwable) e8);
                    }
                }
                if (!fileCreateTempFile.delete()) {
                    logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_DELETE_TEMPORARY_FILE.getMsg(fileCreateTempFile.getAbsolutePath()));
                }
                throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(audioFile.getFile().getAbsolutePath()));
            }
        } catch (IOException e9) {
            e = e9;
        }
    }

    private void transferNewFileToOriginalFile(File file, File file2, boolean z) throws IOException, CannotWriteException {
        if (z) {
            transferNewFileContentToOriginalFile(file, file2);
        } else {
            transferNewFileToNewOriginalFile(file, file2);
        }
    }

    private void transferNewFileContentToOriginalFile(File file, File file2) throws IOException, CannotWriteException {
        FileLock fileLockTryLock;
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file2, WRITE_MODE);
            try {
                FileChannel channel = randomAccessFile.getChannel();
                try {
                    try {
                        fileLockTryLock = channel.tryLock();
                    } catch (IOException e) {
                        logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(file2.getPath()));
                        if ("Operation not supported".equals(e.getMessage())) {
                            transferNewFileContentToOriginalFile(file, file2, randomAccessFile, channel);
                        } else {
                            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(file2.getPath()), e);
                        }
                    }
                    try {
                        if (fileLockTryLock != null) {
                            transferNewFileContentToOriginalFile(file, file2, randomAccessFile, channel);
                            if (fileLockTryLock != null) {
                                fileLockTryLock.close();
                            }
                            randomAccessFile.close();
                            return;
                        }
                        logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(file2.getPath()));
                        throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(file2.getPath()));
                    } catch (Throwable th) {
                        if (fileLockTryLock != null) {
                            try {
                                fileLockTryLock.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(file2.getPath()));
                    throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(file2.getPath()), e2);
                }
            } finally {
            }
        } catch (FileNotFoundException e3) {
            logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_NOT_FOUND.getMsg(file2.getAbsolutePath()));
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_NOT_FOUND.getMsg(file2.getPath()), e3);
        } catch (Exception e4) {
            logger.warning(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(file2.getAbsolutePath()));
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(file2.getPath()), e4);
        }
    }

    private void transferNewFileContentToOriginalFile(File file, File file2, RandomAccessFile randomAccessFile, FileChannel fileChannel) throws IOException, CannotWriteException {
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    long size = channel.size();
                    long jTransferTo = 0;
                    while (jTransferTo < size) {
                        jTransferTo += channel.transferTo(jTransferTo, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, fileChannel);
                    }
                    randomAccessFile.setLength(size);
                    fileInputStream.close();
                    if (!file.exists() || file.delete()) {
                        return;
                    }
                    logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_DELETE_TEMPORARY_FILE.getMsg(file.getPath()));
                } finally {
                }
            } catch (FileNotFoundException e) {
                e = e;
                logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_NEW_FILE_DOESNT_EXIST.getMsg(file.getAbsolutePath()));
                throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_NEW_FILE_DOESNT_EXIST.getMsg(file.getName()), e);
            } catch (IOException e2) {
                e = e2;
                logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_TO_ORIGINAL_FILE.getMsg(file2.getAbsolutePath(), file.getName()));
                throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_TO_ORIGINAL_FILE.getMsg(file2.getAbsolutePath(), file.getName()), e);
            }
        } catch (FileNotFoundException e3) {
            e = e3;
        } catch (IOException e4) {
            e = e4;
        }
    }

    private void transferNewFileToNewOriginalFile(File file, File file2) throws IOException, CannotWriteException {
        FileTime creationTime = getCreationTime(file2);
        File file3 = new File(file2.getAbsoluteFile().getParentFile().getPath(), AudioFile.getBaseFilename(file2) + ".old");
        int i = 1;
        while (file3.exists()) {
            file3 = new File(file2.getAbsoluteFile().getParentFile().getPath(), AudioFile.getBaseFilename(file2) + ".old" + i);
            i++;
        }
        if (!Utils.rename(file2, file3)) {
            logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_ORIGINAL_FILE_TO_BACKUP.getMsg(file2.getAbsolutePath(), file3.getName()));
            if (file != null) {
                file.delete();
            }
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_ORIGINAL_FILE_TO_BACKUP.getMsg(file2.getPath(), file3.getName()));
        }
        if (!Utils.rename(file, file2)) {
            if (!file.exists()) {
                logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_NEW_FILE_DOESNT_EXIST.getMsg(file.getAbsolutePath()));
            }
            if (!file3.renameTo(file2)) {
                logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_ORIGINAL_BACKUP_TO_ORIGINAL.getMsg(file3.getAbsolutePath(), file2.getName()));
            }
            logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_TO_ORIGINAL_FILE.getMsg(file2.getAbsolutePath(), file.getName()));
            throw new CannotWriteException(ErrorMessage.GENERAL_WRITE_FAILED_TO_RENAME_TO_ORIGINAL_FILE.getMsg(file2.getAbsolutePath(), file.getName()));
        }
        if (!file3.delete()) {
            logger.warning(ErrorMessage.GENERAL_WRITE_WARNING_UNABLE_TO_DELETE_BACKUP_FILE.getMsg(file3.getAbsolutePath()));
        }
        if (creationTime != null) {
            setCreationTime(file2, creationTime);
        }
        if (!file.exists() || file.delete()) {
            return;
        }
        logger.warning(ErrorMessage.GENERAL_WRITE_FAILED_TO_DELETE_TEMPORARY_FILE.getMsg(file.getPath()));
    }

    private void setCreationTime(File file, FileTime fileTime) throws IOException {
        try {
            Files.setAttribute(file.toPath(), "creationTime", fileTime, new LinkOption[0]);
        } catch (Exception e) {
            logger.log(Level.WARNING, ErrorMessage.GENERAL_SET_CREATION_TIME_FAILED.getMsg(file.getAbsolutePath(), e.getMessage()), (Throwable) e);
        }
    }

    private FileTime getCreationTime(File file) {
        try {
            return Files.readAttributes(file.toPath(), BasicFileAttributes.class, new LinkOption[0]).creationTime();
        } catch (Exception e) {
            logger.log(Level.WARNING, ErrorMessage.GENERAL_GET_CREATION_TIME_FAILED.getMsg(file.getAbsolutePath(), e.getMessage()), (Throwable) e);
            return null;
        }
    }
}
