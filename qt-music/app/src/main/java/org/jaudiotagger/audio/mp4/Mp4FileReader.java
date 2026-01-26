package org.jaudiotagger.audio.mp4;

import java.io.IOException;
import java.nio.file.Path;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader2;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class Mp4FileReader extends AudioFileReader2 {
    private Mp4InfoReader ir = new Mp4InfoReader();
    private Mp4TagReader tr = new Mp4TagReader();

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException {
        return this.ir.read(path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected Tag getTag(Path path) throws CannotReadException, IOException {
        return this.tr.read(path);
    }
}
