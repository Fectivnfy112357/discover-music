package org.jaudiotagger.audio.generic;

import java.io.File;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.ModifyVetoException;

/* loaded from: classes3.dex */
public class AudioFileModificationAdapter implements AudioFileModificationListener {
    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void fileModified(AudioFile audioFile, File file) throws ModifyVetoException {
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void fileOperationFinished(File file) {
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void fileWillBeModified(AudioFile audioFile, boolean z) throws ModifyVetoException {
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void vetoThrown(AudioFileModificationListener audioFileModificationListener, AudioFile audioFile, ModifyVetoException modifyVetoException) {
    }
}
