package org.jaudiotagger.audio.generic;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public abstract class AudioFileReader {
    protected static final int MINIMUM_SIZE_FOR_VALID_AUDIO_FILE = 100;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.generic");

    protected abstract GenericAudioHeader getEncodingInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException;

    protected abstract Tag getTag(RandomAccessFile randomAccessFile) throws CannotReadException, IOException;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.jaudiotagger.audio.AudioFile read(java.io.File r8) throws org.jaudiotagger.audio.exceptions.ReadOnlyFileException, org.jaudiotagger.audio.exceptions.CannotReadException, org.jaudiotagger.tag.TagException, org.jaudiotagger.audio.exceptions.InvalidAudioFrameException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 295
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.generic.AudioFileReader.read(java.io.File):org.jaudiotagger.audio.AudioFile");
    }
}
