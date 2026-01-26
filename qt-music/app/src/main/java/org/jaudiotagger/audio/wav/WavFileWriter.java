package org.jaudiotagger.audio.wav;

import java.io.IOException;
import java.nio.file.Path;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.generic.AudioFileWriter2;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class WavFileWriter extends AudioFileWriter2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void writeTag(Tag tag, Path path) throws IOException, CannotWriteException {
        new WavTagWriter(path.toString()).write(tag, path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void deleteTag(Tag tag, Path path) throws IOException, CannotWriteException {
        new WavTagWriter(path.toString()).delete(tag, path);
    }
}
