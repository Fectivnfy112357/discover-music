package org.jaudiotagger.audio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import org.jaudiotagger.audio.aiff.AiffFileReader;
import org.jaudiotagger.audio.aiff.AiffFileWriter;
import org.jaudiotagger.audio.asf.AsfFileReader;
import org.jaudiotagger.audio.asf.AsfFileWriter;
import org.jaudiotagger.audio.dff.DffFileReader;
import org.jaudiotagger.audio.dsf.DsfFileReader;
import org.jaudiotagger.audio.dsf.DsfFileWriter;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.flac.FlacFileWriter;
import org.jaudiotagger.audio.generic.AudioFileModificationListener;
import org.jaudiotagger.audio.generic.AudioFileReader;
import org.jaudiotagger.audio.generic.AudioFileWriter;
import org.jaudiotagger.audio.generic.ModificationHandler;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp3.MP3FileReader;
import org.jaudiotagger.audio.mp3.MP3FileWriter;
import org.jaudiotagger.audio.mp4.Mp4FileReader;
import org.jaudiotagger.audio.mp4.Mp4FileWriter;
import org.jaudiotagger.audio.ogg.OggFileReader;
import org.jaudiotagger.audio.ogg.OggFileWriter;
import org.jaudiotagger.audio.real.RealFileReader;
import org.jaudiotagger.audio.wav.WavFileReader;
import org.jaudiotagger.audio.wav.WavFileWriter;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.TagException;

/* loaded from: classes3.dex */
public class AudioFileIO {
    private static AudioFileIO defaultInstance;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio");
    public Map<String, AudioFileReader> readers = new HashMap();
    public Map<String, AudioFileWriter> writers = new HashMap();
    private final ModificationHandler modificationHandler = new ModificationHandler();

    public static void delete(AudioFile audioFile) throws CannotReadException, CannotWriteException {
        getDefaultAudioFileIO().deleteTag(audioFile);
    }

    public static AudioFileIO getDefaultAudioFileIO() {
        if (defaultInstance == null) {
            defaultInstance = new AudioFileIO();
        }
        return defaultInstance;
    }

    public static AudioFile readAs(File file, String str) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        return getDefaultAudioFileIO().readFileAs(file, str);
    }

    public static AudioFile readMagic(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        return getDefaultAudioFileIO().readFileMagic(file);
    }

    public static AudioFile read(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        return getDefaultAudioFileIO().readFile(file);
    }

    public static void write(AudioFile audioFile) throws CannotWriteException {
        getDefaultAudioFileIO().writeFile(audioFile, null);
    }

    public static void writeAs(AudioFile audioFile, String str) throws CannotWriteException {
        if (str == null || str.isEmpty()) {
            throw new CannotWriteException("Not a valid target path: " + str);
        }
        getDefaultAudioFileIO().writeFile(audioFile, str);
    }

    public AudioFileIO() {
        prepareReadersAndWriters();
    }

    public void addAudioFileModificationListener(AudioFileModificationListener audioFileModificationListener) {
        this.modificationHandler.addAudioFileModificationListener(audioFileModificationListener);
    }

    public void deleteTag(AudioFile audioFile) throws CannotReadException, CannotWriteException {
        String extension = Utils.getExtension(audioFile.getFile());
        AudioFileWriter audioFileWriter = this.writers.get(extension);
        if (audioFileWriter == null) {
            throw new CannotWriteException(ErrorMessage.NO_DELETER_FOR_THIS_FORMAT.getMsg(extension));
        }
        audioFileWriter.delete(audioFile);
    }

    private void prepareReadersAndWriters() {
        this.readers.put(SupportedFileFormat.OGG.getFilesuffix(), new OggFileReader());
        this.readers.put(SupportedFileFormat.OGA.getFilesuffix(), new OggFileReader());
        this.readers.put(SupportedFileFormat.FLAC.getFilesuffix(), new FlacFileReader());
        this.readers.put(SupportedFileFormat.MP3.getFilesuffix(), new MP3FileReader());
        this.readers.put(SupportedFileFormat.MP4.getFilesuffix(), new Mp4FileReader());
        this.readers.put(SupportedFileFormat.M4A.getFilesuffix(), new Mp4FileReader());
        this.readers.put(SupportedFileFormat.M4P.getFilesuffix(), new Mp4FileReader());
        this.readers.put(SupportedFileFormat.M4B.getFilesuffix(), new Mp4FileReader());
        this.readers.put(SupportedFileFormat.WAV.getFilesuffix(), new WavFileReader());
        this.readers.put(SupportedFileFormat.WMA.getFilesuffix(), new AsfFileReader());
        this.readers.put(SupportedFileFormat.AIF.getFilesuffix(), new AiffFileReader());
        this.readers.put(SupportedFileFormat.AIFC.getFilesuffix(), new AiffFileReader());
        this.readers.put(SupportedFileFormat.AIFF.getFilesuffix(), new AiffFileReader());
        this.readers.put(SupportedFileFormat.DSF.getFilesuffix(), new DsfFileReader());
        this.readers.put(SupportedFileFormat.DFF.getFilesuffix(), new DffFileReader());
        RealFileReader realFileReader = new RealFileReader();
        this.readers.put(SupportedFileFormat.RA.getFilesuffix(), realFileReader);
        this.readers.put(SupportedFileFormat.RM.getFilesuffix(), realFileReader);
        this.writers.put(SupportedFileFormat.OGG.getFilesuffix(), new OggFileWriter());
        this.writers.put(SupportedFileFormat.OGA.getFilesuffix(), new OggFileWriter());
        this.writers.put(SupportedFileFormat.FLAC.getFilesuffix(), new FlacFileWriter());
        this.writers.put(SupportedFileFormat.MP3.getFilesuffix(), new MP3FileWriter());
        this.writers.put(SupportedFileFormat.MP4.getFilesuffix(), new Mp4FileWriter());
        this.writers.put(SupportedFileFormat.M4A.getFilesuffix(), new Mp4FileWriter());
        this.writers.put(SupportedFileFormat.M4P.getFilesuffix(), new Mp4FileWriter());
        this.writers.put(SupportedFileFormat.M4B.getFilesuffix(), new Mp4FileWriter());
        this.writers.put(SupportedFileFormat.WAV.getFilesuffix(), new WavFileWriter());
        this.writers.put(SupportedFileFormat.WMA.getFilesuffix(), new AsfFileWriter());
        this.writers.put(SupportedFileFormat.AIF.getFilesuffix(), new AiffFileWriter());
        this.writers.put(SupportedFileFormat.AIFC.getFilesuffix(), new AiffFileWriter());
        this.writers.put(SupportedFileFormat.AIFF.getFilesuffix(), new AiffFileWriter());
        this.writers.put(SupportedFileFormat.DSF.getFilesuffix(), new DsfFileWriter());
        Iterator<AudioFileWriter> it = this.writers.values().iterator();
        while (it.hasNext()) {
            it.next().setAudioFileModificationListener(this.modificationHandler);
        }
    }

    public AudioFile readFile(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        String extension = Utils.getExtension(file);
        AudioFileReader audioFileReader = this.readers.get(extension);
        if (audioFileReader == null) {
            throw new CannotReadException(ErrorMessage.NO_READER_FOR_THIS_FORMAT.getMsg(extension));
        }
        AudioFile audioFile = audioFileReader.read(file);
        audioFile.setExt(extension);
        return audioFile;
    }

    public AudioFile readFileMagic(File file) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        String magicExtension = Utils.getMagicExtension(file);
        AudioFileReader audioFileReader = this.readers.get(magicExtension);
        if (audioFileReader == null) {
            throw new CannotReadException(ErrorMessage.NO_READER_FOR_THIS_FORMAT.getMsg(magicExtension));
        }
        AudioFile audioFile = audioFileReader.read(file);
        audioFile.setExt(magicExtension);
        return audioFile;
    }

    public AudioFile readFileAs(File file, String str) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
        AudioFileReader audioFileReader = this.readers.get(str);
        if (audioFileReader == null) {
            throw new CannotReadException(ErrorMessage.NO_READER_FOR_THIS_FORMAT.getMsg(str));
        }
        AudioFile audioFile = audioFileReader.read(file);
        audioFile.setExt(str);
        return audioFile;
    }

    public void checkFileExists(File file) throws FileNotFoundException {
        logger.config("Reading file:path" + file.getPath() + ":abs:" + file.getAbsolutePath());
        if (file.exists()) {
            return;
        }
        logger.severe("Unable to find:" + file.getPath());
        throw new FileNotFoundException(ErrorMessage.UNABLE_TO_FIND_FILE.getMsg(file.getPath()));
    }

    public void removeAudioFileModificationListener(AudioFileModificationListener audioFileModificationListener) {
        this.modificationHandler.removeAudioFileModificationListener(audioFileModificationListener);
    }

    public void writeFile(AudioFile audioFile, String str) throws CannotWriteException {
        String ext = audioFile.getExt();
        if (str != null && !str.isEmpty()) {
            File file = new File(str + "." + ext);
            try {
                Utils.copyThrowsOnException(audioFile.getFile(), file);
                audioFile.setFile(file);
            } catch (IOException e) {
                throw new CannotWriteException("Error While Copying" + e.getMessage());
            }
        }
        AudioFileWriter audioFileWriter = this.writers.get(ext);
        if (audioFileWriter == null) {
            throw new CannotWriteException(ErrorMessage.NO_WRITER_FOR_THIS_FORMAT.getMsg(ext));
        }
        audioFileWriter.write(audioFile);
    }
}
