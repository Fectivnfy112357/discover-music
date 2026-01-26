package org.jaudiotagger.audio.aiff;

import java.io.IOException;
import java.nio.file.Path;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader2;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class AiffFileReader extends AudioFileReader2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException {
        return new AiffInfoReader(path.toString()).read(path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected Tag getTag(Path path) throws CannotReadException, IOException {
        return new AiffTagReader(path.toString()).read(path);
    }
}
