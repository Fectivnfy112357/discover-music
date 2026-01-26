package org.jaudiotagger.audio.mp4;

import java.nio.file.Path;
import org.jaudiotagger.audio.generic.AudioFileWriter2;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class Mp4FileWriter extends AudioFileWriter2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void writeTag(Tag tag, Path path) throws Throwable {
        new Mp4TagWriter(path.toString()).write(tag, path);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void deleteTag(Tag tag, Path path) throws Throwable {
        new Mp4TagWriter(path.toString()).delete(tag, path);
    }
}
