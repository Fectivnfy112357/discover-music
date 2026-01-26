package org.jaudiotagger.audio.flac;

import java.io.IOException;
import java.nio.file.Path;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader2;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class FlacFileReader extends AudioFileReader2 {
    private FlacInfoReader ir = new FlacInfoReader();
    private FlacTagReader tr = new FlacTagReader();

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException {
        return this.ir.read(path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected Tag getTag(Path path) throws CannotReadException, IOException {
        return this.tr.read(path);
    }
}
