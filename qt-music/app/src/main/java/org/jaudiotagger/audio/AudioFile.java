package org.jaudiotagger.audio;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.jaudiotagger.audio.dsf.Dsf;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.NoReadPermissionsException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.generic.Permissions;
import org.jaudiotagger.audio.real.RealTag;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.aiff.AiffTag;
import org.jaudiotagger.tag.asf.AsfTag;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v22Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.mp4.Mp4Tag;
import org.jaudiotagger.tag.reference.ID3V2Version;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class AudioFile {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio");
    protected AudioHeader audioHeader;
    protected String extension;
    protected File file;
    protected Tag tag;

    public AudioFile() {
    }

    public AudioFile(File file, AudioHeader audioHeader, Tag tag) {
        this.file = file;
        this.audioHeader = audioHeader;
        this.tag = tag;
    }

    public AudioFile(String str, AudioHeader audioHeader, Tag tag) {
        this.file = new File(str);
        this.audioHeader = audioHeader;
        this.tag = tag;
    }

    public void commit() throws CannotWriteException {
        AudioFileIO.write(this);
    }

    public void delete() throws CannotReadException, CannotWriteException {
        AudioFileIO.delete(this);
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    public void setExt(String str) {
        this.extension = str;
    }

    public String getExt() {
        return this.extension;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public AudioHeader getAudioHeader() {
        return this.audioHeader;
    }

    public Tag getTag() {
        return this.tag;
    }

    public String toString() {
        StringBuilder sbAppend = new StringBuilder("AudioFile ").append(getFile().getAbsolutePath()).append("  --------\n").append(this.audioHeader.toString()).append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        Tag tag = this.tag;
        return sbAppend.append(tag == null ? "" : tag.toString()).append("\n-------------------").toString();
    }

    public void checkFileExists(File file) throws FileNotFoundException {
        logger.config("Reading file:path" + file.getPath() + ":abs:" + file.getAbsolutePath());
        if (file.exists()) {
            return;
        }
        logger.severe("Unable to find:" + file.getPath());
        throw new FileNotFoundException(ErrorMessage.UNABLE_TO_FIND_FILE.getMsg(file.getPath()));
    }

    protected RandomAccessFile checkFilePermissions(File file, boolean z) throws ReadOnlyFileException, CannotReadException, FileNotFoundException {
        Path path = file.toPath();
        checkFileExists(file);
        if (z) {
            if (!Files.isReadable(path)) {
                logger.severe("Unable to read file:" + path);
                logger.severe(Permissions.displayPermissions(path));
                throw new NoReadPermissionsException(ErrorMessage.GENERAL_READ_FAILED_DO_NOT_HAVE_PERMISSION_TO_READ_FILE.getMsg(path));
            }
            return new RandomAccessFile(file, "r");
        }
        if (TagOptionSingleton.getInstance().isCheckIsWritable() && !Files.isWritable(path)) {
            logger.severe(Permissions.displayPermissions(file.toPath()));
            logger.severe(Permissions.displayPermissions(path));
            throw new ReadOnlyFileException(ErrorMessage.NO_PERMISSIONS_TO_WRITE_TO_FILE.getMsg(path));
        }
        return new RandomAccessFile(file, "rw");
    }

    public String displayStructureAsXML() {
        return "";
    }

    public String displayStructureAsPlainText() {
        return "";
    }

    public Tag createDefaultTag() {
        String ext = getExt();
        if (ext == null) {
            String name = this.file.getName();
            ext = name.substring(name.lastIndexOf(46) + 1);
            setExt(ext);
        }
        if (SupportedFileFormat.FLAC.getFilesuffix().equals(ext)) {
            return new FlacTag(VorbisCommentTag.createNewTag(), new ArrayList());
        }
        if (SupportedFileFormat.OGG.getFilesuffix().equals(ext)) {
            return VorbisCommentTag.createNewTag();
        }
        if (SupportedFileFormat.OGA.getFilesuffix().equals(ext)) {
            return VorbisCommentTag.createNewTag();
        }
        if (SupportedFileFormat.MP4.getFilesuffix().equals(ext)) {
            return new Mp4Tag();
        }
        if (SupportedFileFormat.M4A.getFilesuffix().equals(ext)) {
            return new Mp4Tag();
        }
        if (SupportedFileFormat.M4P.getFilesuffix().equals(ext)) {
            return new Mp4Tag();
        }
        if (SupportedFileFormat.WMA.getFilesuffix().equals(ext)) {
            return new AsfTag();
        }
        if (SupportedFileFormat.WAV.getFilesuffix().equals(ext)) {
            return new WavTag(TagOptionSingleton.getInstance().getWavOptions());
        }
        if (SupportedFileFormat.RA.getFilesuffix().equals(ext)) {
            return new RealTag();
        }
        if (SupportedFileFormat.RM.getFilesuffix().equals(ext)) {
            return new RealTag();
        }
        if (SupportedFileFormat.AIF.getFilesuffix().equals(ext)) {
            return new AiffTag();
        }
        if (SupportedFileFormat.AIFC.getFilesuffix().equals(ext)) {
            return new AiffTag();
        }
        if (SupportedFileFormat.AIFF.getFilesuffix().equals(ext)) {
            return new AiffTag();
        }
        if (SupportedFileFormat.DSF.getFilesuffix().equals(ext)) {
            return Dsf.createDefaultTag();
        }
        throw new RuntimeException("Unable to create default tag for this file format");
    }

    public Tag getTagOrCreateDefault() {
        Tag tag = getTag();
        return tag == null ? createDefaultTag() : tag;
    }

    public Tag getTagOrCreateAndSetDefault() {
        Tag tagOrCreateDefault = getTagOrCreateDefault();
        setTag(tagOrCreateDefault);
        return tagOrCreateDefault;
    }

    public Tag getTagAndConvertOrCreateDefault() {
        AbstractID3v2Tag abstractID3v2TagConvertID3Tag;
        Tag tagOrCreateDefault = getTagOrCreateDefault();
        return (!(tagOrCreateDefault instanceof AbstractID3v2Tag) || (abstractID3v2TagConvertID3Tag = convertID3Tag((AbstractID3v2Tag) tagOrCreateDefault, TagOptionSingleton.getInstance().getID3V2Version())) == null) ? tagOrCreateDefault : abstractID3v2TagConvertID3Tag;
    }

    public Tag getTagAndConvertOrCreateAndSetDefault() {
        setTag(getTagAndConvertOrCreateDefault());
        return getTag();
    }

    public static String getBaseFilename(File file) {
        int iLastIndexOf = file.getName().toLowerCase().lastIndexOf(".");
        if (iLastIndexOf > 0) {
            return file.getName().substring(0, iLastIndexOf);
        }
        return file.getName();
    }

    public AbstractID3v2Tag convertID3Tag(AbstractID3v2Tag abstractID3v2Tag, ID3V2Version iD3V2Version) {
        if (abstractID3v2Tag instanceof ID3v24Tag) {
            int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version[iD3V2Version.ordinal()];
            if (i == 1) {
                return new ID3v22Tag((ID3v24Tag) abstractID3v2Tag);
            }
            if (i != 2) {
                return null;
            }
            return new ID3v23Tag((ID3v24Tag) abstractID3v2Tag);
        }
        if (abstractID3v2Tag instanceof ID3v23Tag) {
            int i2 = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version[iD3V2Version.ordinal()];
            if (i2 == 1) {
                return new ID3v22Tag((ID3v23Tag) abstractID3v2Tag);
            }
            if (i2 != 3) {
                return null;
            }
            return new ID3v24Tag((ID3v23Tag) abstractID3v2Tag);
        }
        if (!(abstractID3v2Tag instanceof ID3v22Tag)) {
            return null;
        }
        int i3 = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version[iD3V2Version.ordinal()];
        if (i3 == 2) {
            return new ID3v23Tag((ID3v22Tag) abstractID3v2Tag);
        }
        if (i3 != 3) {
            return null;
        }
        return new ID3v24Tag((ID3v22Tag) abstractID3v2Tag);
    }

    /* renamed from: org.jaudiotagger.audio.AudioFile$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version;

        static {
            int[] iArr = new int[ID3V2Version.values().length];
            $SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version = iArr;
            try {
                iArr[ID3V2Version.ID3_V22.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version[ID3V2Version.ID3_V23.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$reference$ID3V2Version[ID3V2Version.ID3_V24.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
