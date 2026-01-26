package org.jaudiotagger.audio.wav;

import java.io.IOException;
import java.nio.file.Path;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader2;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.wav.WavTag;

/* loaded from: classes3.dex */
public class WavFileReader extends AudioFileReader2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected GenericAudioHeader getEncodingInfo(Path path) throws CannotReadException, IOException {
        return new WavInfoReader(path.toString()).read(path);
    }

    /* renamed from: org.jaudiotagger.audio.wav.WavFileReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$wav$WavOptions;

        static {
            int[] iArr = new int[WavOptions.values().length];
            $SwitchMap$org$jaudiotagger$audio$wav$WavOptions = iArr;
            try {
                iArr[WavOptions.READ_ID3_ONLY_AND_SYNC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_ID3_UNLESS_ONLY_INFO_AND_SYNC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_INFO_ONLY_AND_SYNC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$wav$WavOptions[WavOptions.READ_INFO_UNLESS_ONLY_ID3_AND_SYNC.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader2
    protected Tag getTag(Path path) throws CannotReadException, KeyNotFoundException, IOException {
        WavTag wavTag = new WavTagReader(path.toString()).read(path);
        int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$wav$WavOptions[TagOptionSingleton.getInstance().getWavOptions().ordinal()];
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            wavTag.syncTagsAfterRead();
        }
        return wavTag;
    }
}
