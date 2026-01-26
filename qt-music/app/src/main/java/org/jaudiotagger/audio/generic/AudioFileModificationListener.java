package org.jaudiotagger.audio.generic;

import java.io.File;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.ModifyVetoException;

/* loaded from: classes3.dex */
public interface AudioFileModificationListener {
    void fileModified(AudioFile audioFile, File file) throws ModifyVetoException;

    void fileOperationFinished(File file);

    void fileWillBeModified(AudioFile audioFile, boolean z) throws ModifyVetoException;

    void vetoThrown(AudioFileModificationListener audioFileModificationListener, AudioFile audioFile, ModifyVetoException modifyVetoException);
}
