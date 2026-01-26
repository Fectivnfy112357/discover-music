package org.jaudiotagger.audio.flac;

import java.io.IOException;
import java.nio.file.Path;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.generic.AudioFileWriter2;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class FlacFileWriter extends AudioFileWriter2 {
    private FlacTagWriter tw = new FlacTagWriter();

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void writeTag(Tag tag, Path path) throws IOException, CannotWriteException {
        this.tw.write(tag, path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void deleteTag(Tag tag, Path path) throws IOException, CannotWriteException {
        this.tw.delete(tag, path);
    }
}
