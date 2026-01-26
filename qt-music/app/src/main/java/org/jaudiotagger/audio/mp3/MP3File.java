package org.jaudiotagger.audio.mp3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.NoWritePermissionsException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.exceptions.UnableToModifyFileException;
import org.jaudiotagger.audio.generic.Permissions;
import org.jaudiotagger.logging.AbstractTagDisplayFormatter;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.logging.PlainTextTagDisplayFormatter;
import org.jaudiotagger.logging.XMLTagDisplayFormatter;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagNotFoundException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.AbstractTag;
import org.jaudiotagger.tag.id3.ID3v11Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v22Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.lyrics3.AbstractLyrics3;
import org.jaudiotagger.tag.reference.ID3V2Version;

/* loaded from: classes3.dex */
public class MP3File extends AudioFile {
    public static final int LOAD_ALL = 14;
    public static final int LOAD_IDV1TAG = 2;
    public static final int LOAD_IDV2TAG = 4;
    public static final int LOAD_LYRICS3 = 8;
    private static final int MINIMUM_FILESIZE = 150;
    protected static AbstractTagDisplayFormatter tagFormatter;
    private ID3v1Tag id3v1tag;
    private ID3v24Tag id3v2Asv24tag;
    private AbstractID3v2Tag id3v2tag;
    private AbstractLyrics3 lyrics3tag;

    public MP3File() {
        this.id3v2tag = null;
        this.id3v2Asv24tag = null;
        this.lyrics3tag = null;
        this.id3v1tag = null;
    }

    public MP3File(String str) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        this(new File(str));
    }

    public MP3File(File file, int i) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        this(file, i, false);
    }

    private void readV1Tag(File file, RandomAccessFile randomAccessFile, int i) throws IOException {
        if ((i & 2) != 0) {
            logger.finer("Attempting to read id3v1tags");
            try {
                this.id3v1tag = new ID3v11Tag(randomAccessFile, file.getName());
            } catch (TagNotFoundException unused) {
                logger.config("No ids3v11 tag found");
            }
            try {
                if (this.id3v1tag == null) {
                    this.id3v1tag = new ID3v1Tag(randomAccessFile, file.getName());
                }
            } catch (TagNotFoundException unused2) {
                logger.config("No id3v1 tag found");
            }
        }
    }

    private void readV2Tag(File file, int i, int i2) throws TagException, IOException {
        if (i2 >= 10) {
            logger.finer("Attempting to read id3v2tags");
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2);
                fileInputStream.getChannel().read(byteBufferAllocateDirect, 0L);
                byteBufferAllocateDirect.rewind();
                if ((i & 4) != 0) {
                    logger.config("Attempting to read id3v2tags");
                    try {
                        setID3v2Tag((AbstractID3v2Tag) new ID3v24Tag(byteBufferAllocateDirect, file.getName()));
                    } catch (TagNotFoundException unused) {
                        logger.config("No id3v24 tag found");
                    }
                    try {
                        if (this.id3v2tag == null) {
                            setID3v2Tag((AbstractID3v2Tag) new ID3v23Tag(byteBufferAllocateDirect, file.getName()));
                        }
                    } catch (TagNotFoundException unused2) {
                        logger.config("No id3v23 tag found");
                    }
                    try {
                        if (this.id3v2tag == null) {
                            setID3v2Tag((AbstractID3v2Tag) new ID3v22Tag(byteBufferAllocateDirect, file.getName()));
                        }
                    } catch (TagNotFoundException unused3) {
                        logger.config("No id3v22 tag found");
                    }
                }
                fileInputStream.close();
                return;
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        logger.config("Not enough room for valid id3v2 tag:" + i2);
    }

    private boolean isFilePortionNull(int i, int i2) throws Throwable {
        FileInputStream fileInputStream;
        logger.config("Checking file portion:" + Hex.asHex(i) + ":" + Hex.asHex(i2));
        FileChannel channel = null;
        try {
            fileInputStream = new FileInputStream(this.file);
            try {
                channel = fileInputStream.getChannel();
                channel.position(i);
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(i2 - i);
                channel.read(byteBufferAllocateDirect);
                while (byteBufferAllocateDirect.hasRemaining()) {
                    if (byteBufferAllocateDirect.get() != 0) {
                        if (channel != null) {
                            channel.close();
                        }
                        fileInputStream.close();
                        return false;
                    }
                }
                if (channel != null) {
                    channel.close();
                }
                fileInputStream.close();
                return true;
            } catch (Throwable th) {
                th = th;
                if (channel != null) {
                    channel.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    private MP3AudioHeader checkAudioStart(long j, MP3AudioHeader mP3AudioHeader) throws InvalidAudioFrameException, IOException {
        logger.warning(ErrorMessage.MP3_ID3TAG_LENGTH_INCORRECT.getMsg(this.file.getPath(), Hex.asHex(j), Hex.asHex(mP3AudioHeader.getMp3StartByte())));
        MP3AudioHeader mP3AudioHeader2 = new MP3AudioHeader(this.file, 0L);
        logger.config("Checking from start:" + mP3AudioHeader2);
        if (mP3AudioHeader.getMp3StartByte() == mP3AudioHeader2.getMp3StartByte()) {
            logger.config(ErrorMessage.MP3_START_OF_AUDIO_CONFIRMED.getMsg(this.file.getPath(), Hex.asHex(mP3AudioHeader2.getMp3StartByte())));
            return mP3AudioHeader;
        }
        logger.config(ErrorMessage.MP3_RECALCULATED_POSSIBLE_START_OF_MP3_AUDIO.getMsg(this.file.getPath(), Hex.asHex(mP3AudioHeader2.getMp3StartByte())));
        if (mP3AudioHeader.getNumberOfFrames() == mP3AudioHeader2.getNumberOfFrames()) {
            logger.warning(ErrorMessage.MP3_RECALCULATED_START_OF_MP3_AUDIO.getMsg(this.file.getPath(), Hex.asHex(mP3AudioHeader2.getMp3StartByte())));
            return mP3AudioHeader2;
        }
        if (isFilePortionNull((int) j, (int) mP3AudioHeader.getMp3StartByte())) {
            return mP3AudioHeader;
        }
        MP3AudioHeader mP3AudioHeader3 = new MP3AudioHeader(this.file, mP3AudioHeader2.getMp3StartByte() + mP3AudioHeader2.mp3FrameHeader.getFrameLength());
        if (mP3AudioHeader3.getMp3StartByte() == mP3AudioHeader.getMp3StartByte()) {
            logger.warning(ErrorMessage.MP3_START_OF_AUDIO_CONFIRMED.getMsg(this.file.getPath(), Hex.asHex(mP3AudioHeader.getMp3StartByte())));
            return mP3AudioHeader;
        }
        if (mP3AudioHeader3.getNumberOfFrames() == mP3AudioHeader2.getNumberOfFrames()) {
            logger.warning(ErrorMessage.MP3_RECALCULATED_START_OF_MP3_AUDIO.getMsg(this.file.getPath(), Hex.asHex(mP3AudioHeader2.getMp3StartByte())));
            return mP3AudioHeader2;
        }
        logger.warning(ErrorMessage.MP3_RECALCULATED_START_OF_MP3_AUDIO.getMsg(this.file.getPath(), Hex.asHex(mP3AudioHeader.getMp3StartByte())));
        return mP3AudioHeader;
    }

    public MP3File(File file, int i, boolean z) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        RandomAccessFile randomAccessFile = null;
        this.id3v2tag = null;
        this.id3v2Asv24tag = null;
        this.lyrics3tag = null;
        this.id3v1tag = null;
        try {
            this.file = file;
            RandomAccessFile randomAccessFileCheckFilePermissions = checkFilePermissions(file, z);
            long v2TagSizeIfExists = AbstractID3v2Tag.getV2TagSizeIfExists(file);
            logger.config("TagHeaderSize:" + Hex.asHex(v2TagSizeIfExists));
            this.audioHeader = new MP3AudioHeader(file, v2TagSizeIfExists);
            if (v2TagSizeIfExists != ((MP3AudioHeader) this.audioHeader).getMp3StartByte()) {
                logger.config("First header found after tag:" + this.audioHeader);
                this.audioHeader = checkAudioStart(v2TagSizeIfExists, (MP3AudioHeader) this.audioHeader);
            }
            readV1Tag(file, randomAccessFileCheckFilePermissions, i);
            readV2Tag(file, i, (int) ((MP3AudioHeader) this.audioHeader).getMp3StartByte());
            if (getID3v2Tag() != null) {
                this.tag = getID3v2Tag();
            } else {
                ID3v1Tag iD3v1Tag = this.id3v1tag;
                if (iD3v1Tag != null) {
                    this.tag = iD3v1Tag;
                }
            }
            if (randomAccessFileCheckFilePermissions != null) {
                randomAccessFileCheckFilePermissions.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public long getMP3StartByte(File file) throws InvalidAudioFrameException, IOException {
        long v2TagSizeIfExists = AbstractID3v2Tag.getV2TagSizeIfExists(file);
        MP3AudioHeader mP3AudioHeader = new MP3AudioHeader(file, v2TagSizeIfExists);
        if (v2TagSizeIfExists != mP3AudioHeader.getMp3StartByte()) {
            logger.config("First header found after tag:" + mP3AudioHeader);
            mP3AudioHeader = checkAudioStart(v2TagSizeIfExists, mP3AudioHeader);
        }
        return mP3AudioHeader.getMp3StartByte();
    }

    public File extractID3v2TagDataIntoFile(File file) throws TagNotFoundException, IOException {
        int mp3StartByte = (int) ((MP3AudioHeader) this.audioHeader).getMp3StartByte();
        if (mp3StartByte >= 0) {
            FileInputStream fileInputStream = new FileInputStream(this.file);
            FileChannel channel = fileInputStream.getChannel();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(mp3StartByte);
            channel.read(byteBufferAllocate);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteBufferAllocate.array());
            fileOutputStream.close();
            channel.close();
            fileInputStream.close();
            return file;
        }
        throw new TagNotFoundException("There is no ID3v2Tag data in this file");
    }

    public MP3AudioHeader getMP3AudioHeader() {
        return (MP3AudioHeader) getAudioHeader();
    }

    public boolean hasID3v1Tag() {
        return this.id3v1tag != null;
    }

    public boolean hasID3v2Tag() {
        return this.id3v2tag != null;
    }

    public MP3File(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        this(file, 14);
    }

    public void setID3v1Tag(ID3v1Tag iD3v1Tag) {
        logger.config("setting tagv1:v1 tag");
        this.id3v1tag = iD3v1Tag;
    }

    public void setID3v1Tag(Tag tag) {
        logger.config("setting tagv1:v1 tag");
        this.id3v1tag = (ID3v1Tag) tag;
    }

    public void setID3v1Tag(AbstractTag abstractTag) {
        logger.config("setting tagv1:abstract");
        this.id3v1tag = new ID3v11Tag(abstractTag);
    }

    public ID3v1Tag getID3v1Tag() {
        return this.id3v1tag;
    }

    public byte[] getHash(String str) throws NoSuchAlgorithmException, InvalidAudioFrameException, IOException {
        return getHash(str, 32768);
    }

    public byte[] getHash(int i) throws NoSuchAlgorithmException, InvalidAudioFrameException, IOException {
        return getHash("MD5", i);
    }

    public byte[] getHash() throws NoSuchAlgorithmException, InvalidAudioFrameException, IOException {
        return getHash("MD5", 32768);
    }

    public byte[] getHash(String str, int i) throws InvalidAudioFrameException, NoSuchAlgorithmException, IOException {
        File file = getFile();
        long mP3StartByte = getMP3StartByte(file);
        int size = hasID3v1Tag() ? getID3v1Tag().getSize() : 0;
        InputStream inputStreamNewInputStream = Files.newInputStream(Paths.get(file.getAbsolutePath(), new String[0]), new OpenOption[0]);
        byte[] bArr = new byte[i];
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        inputStreamNewInputStream.skip(mP3StartByte);
        long length = (file.length() - mP3StartByte) - size;
        int i2 = i;
        while (i2 <= length) {
            messageDigest.update(bArr, 0, inputStreamNewInputStream.read(bArr));
            i2 += i;
        }
        messageDigest.update(bArr, 0, inputStreamNewInputStream.read(bArr, 0, (((int) length) - i2) + i));
        return messageDigest.digest();
    }

    public void setID3v2Tag(AbstractTag abstractTag) {
        this.id3v2tag = new ID3v24Tag(abstractTag);
    }

    public void setID3v2Tag(AbstractID3v2Tag abstractID3v2Tag) {
        this.id3v2tag = abstractID3v2Tag;
        if (abstractID3v2Tag instanceof ID3v24Tag) {
            this.id3v2Asv24tag = (ID3v24Tag) abstractID3v2Tag;
        } else {
            this.id3v2Asv24tag = new ID3v24Tag(abstractID3v2Tag);
        }
    }

    public void setID3v2TagOnly(AbstractID3v2Tag abstractID3v2Tag) {
        this.id3v2tag = abstractID3v2Tag;
        this.id3v2Asv24tag = null;
    }

    public AbstractID3v2Tag getID3v2Tag() {
        return this.id3v2tag;
    }

    public ID3v24Tag getID3v2TagAsv24() {
        return this.id3v2Asv24tag;
    }

    public void delete(AbstractTag abstractTag) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.file, "rw");
        abstractTag.delete(randomAccessFile);
        randomAccessFile.close();
        if (abstractTag instanceof ID3v1Tag) {
            this.id3v1tag = null;
        }
        if (abstractTag instanceof AbstractID3v2Tag) {
            this.id3v2tag = null;
        }
    }

    public void save() throws Throwable {
        save(this.file);
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public void commit() throws Throwable {
        try {
            save();
        } catch (UnableToModifyFileException e) {
            throw new NoWritePermissionsException(e);
        } catch (IOException e2) {
            throw new CannotWriteException(e2);
        } catch (TagException e3) {
            throw new CannotWriteException(e3);
        }
    }

    public void precheck(File file) throws IOException {
        Path path = file.toPath();
        if (!Files.exists(path, new LinkOption[0])) {
            logger.severe(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_NOT_FOUND.getMsg(file.getName()));
            throw new IOException(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_NOT_FOUND.getMsg(file.getName()));
        }
        if (TagOptionSingleton.getInstance().isCheckIsWritable() && !Files.isWritable(path)) {
            logger.severe(Permissions.displayPermissions(path));
            logger.severe(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(file.getName()));
            throw new IOException(ErrorMessage.GENERAL_WRITE_FAILED.getMsg(file.getName()));
        }
        if (file.length() > 150) {
            return;
        }
        logger.severe(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_IS_TOO_SMALL.getMsg(file.getName()));
        throw new IOException(ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_IS_TOO_SMALL.getMsg(file.getName()));
    }

    public void save(File file) throws Throwable {
        AbstractLyrics3 abstractLyrics3;
        File absoluteFile = file.getAbsoluteFile();
        logger.config("Saving  : " + absoluteFile.getPath());
        precheck(absoluteFile);
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                if (TagOptionSingleton.getInstance().isId3v2Save()) {
                    if (this.id3v2tag == null) {
                        RandomAccessFile randomAccessFile2 = new RandomAccessFile(absoluteFile, "rw");
                        try {
                            new ID3v24Tag().delete(randomAccessFile2);
                            new ID3v23Tag().delete(randomAccessFile2);
                            new ID3v22Tag().delete(randomAccessFile2);
                            logger.config("Deleting ID3v2 tag:" + absoluteFile.getName());
                            randomAccessFile2.close();
                        } catch (FileNotFoundException e) {
                            e = e;
                            logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE_FILE_NOT_FOUND.getMsg(absoluteFile.getName()), (Throwable) e);
                            throw e;
                        } catch (IOException e2) {
                            e = e2;
                            logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE.getMsg(absoluteFile.getName(), e.getMessage()), (Throwable) e);
                            throw e;
                        } catch (RuntimeException e3) {
                            e = e3;
                            logger.log(Level.SEVERE, ErrorMessage.GENERAL_WRITE_FAILED_BECAUSE.getMsg(absoluteFile.getName(), e.getMessage()), (Throwable) e);
                            throw e;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = randomAccessFile2;
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            throw th;
                        }
                    } else {
                        logger.config("Writing ID3v2 tag:" + absoluteFile.getName());
                        MP3AudioHeader mP3AudioHeader = (MP3AudioHeader) getAudioHeader();
                        long mp3StartByte = mP3AudioHeader.getMp3StartByte();
                        long jWrite = this.id3v2tag.write(absoluteFile, mp3StartByte);
                        if (mp3StartByte != jWrite) {
                            logger.config("New mp3 start byte: " + jWrite);
                            mP3AudioHeader.setMp3StartByte(jWrite);
                        }
                    }
                }
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(absoluteFile, "rw");
                if (TagOptionSingleton.getInstance().isLyrics3Save() && (abstractLyrics3 = this.lyrics3tag) != null) {
                    abstractLyrics3.write(randomAccessFile3);
                }
                if (TagOptionSingleton.getInstance().isId3v1Save()) {
                    logger.config("Processing ID3v1");
                    if (this.id3v1tag == null) {
                        logger.config("Deleting ID3v1");
                        new ID3v1Tag().delete(randomAccessFile3);
                    } else {
                        logger.config("Saving ID3v1");
                        this.id3v1tag.write(randomAccessFile3);
                    }
                }
                randomAccessFile3.close();
            } catch (FileNotFoundException e4) {
                e = e4;
            } catch (IOException e5) {
                e = e5;
            } catch (RuntimeException e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public String displayStructureAsXML() {
        createXMLStructureFormatter();
        tagFormatter.openHeadingElement("file", getFile().getAbsolutePath());
        if (getID3v1Tag() != null) {
            getID3v1Tag().createStructure();
        }
        if (getID3v2Tag() != null) {
            getID3v2Tag().createStructure();
        }
        tagFormatter.closeHeadingElement("file");
        return tagFormatter.toString();
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public String displayStructureAsPlainText() {
        createPlainTextStructureFormatter();
        tagFormatter.openHeadingElement("file", getFile().getAbsolutePath());
        if (getID3v1Tag() != null) {
            getID3v1Tag().createStructure();
        }
        if (getID3v2Tag() != null) {
            getID3v2Tag().createStructure();
        }
        tagFormatter.closeHeadingElement("file");
        return tagFormatter.toString();
    }

    private static void createXMLStructureFormatter() {
        tagFormatter = new XMLTagDisplayFormatter();
    }

    private static void createPlainTextStructureFormatter() {
        tagFormatter = new PlainTextTagDisplayFormatter();
    }

    public static AbstractTagDisplayFormatter getStructureFormatter() {
        return tagFormatter;
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public void setTag(Tag tag) {
        this.tag = tag;
        if (tag instanceof ID3v1Tag) {
            setID3v1Tag((ID3v1Tag) tag);
        } else {
            setID3v2Tag((AbstractID3v2Tag) tag);
        }
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public Tag createDefaultTag() {
        if (TagOptionSingleton.getInstance().getID3V2Version() == ID3V2Version.ID3_V24) {
            return new ID3v24Tag();
        }
        if (TagOptionSingleton.getInstance().getID3V2Version() == ID3V2Version.ID3_V23) {
            return new ID3v23Tag();
        }
        if (TagOptionSingleton.getInstance().getID3V2Version() == ID3V2Version.ID3_V22) {
            return new ID3v22Tag();
        }
        return new ID3v24Tag();
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public Tag getTagOrCreateDefault() {
        AbstractID3v2Tag iD3v2Tag = getID3v2Tag();
        return iD3v2Tag == null ? createDefaultTag() : iD3v2Tag;
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public Tag getTagAndConvertOrCreateDefault() {
        Tag tagOrCreateDefault = getTagOrCreateDefault();
        AbstractID3v2Tag abstractID3v2TagConvertID3Tag = convertID3Tag((AbstractID3v2Tag) tagOrCreateDefault, TagOptionSingleton.getInstance().getID3V2Version());
        return abstractID3v2TagConvertID3Tag != null ? abstractID3v2TagConvertID3Tag : tagOrCreateDefault;
    }

    @Override // org.jaudiotagger.audio.AudioFile
    public Tag getTagAndConvertOrCreateAndSetDefault() {
        setTag(getTagAndConvertOrCreateDefault());
        return getTag();
    }
}
