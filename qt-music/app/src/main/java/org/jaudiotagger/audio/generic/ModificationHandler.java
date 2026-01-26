package org.jaudiotagger.audio.generic;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.exceptions.ModifyVetoException;

/* loaded from: classes3.dex */
public class ModificationHandler implements AudioFileModificationListener {
    private Vector<AudioFileModificationListener> listeners = new Vector<>();

    public void addAudioFileModificationListener(AudioFileModificationListener audioFileModificationListener) {
        if (this.listeners.contains(audioFileModificationListener)) {
            return;
        }
        this.listeners.add(audioFileModificationListener);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void fileModified(AudioFile audioFile, File file) throws ModifyVetoException {
        Iterator<AudioFileModificationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            AudioFileModificationListener next = it.next();
            try {
                next.fileModified(audioFile, file);
            } catch (ModifyVetoException e) {
                vetoThrown(next, audioFile, e);
                throw e;
            }
        }
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void fileOperationFinished(File file) {
        Iterator<AudioFileModificationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().fileOperationFinished(file);
        }
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void fileWillBeModified(AudioFile audioFile, boolean z) throws ModifyVetoException {
        Iterator<AudioFileModificationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            AudioFileModificationListener next = it.next();
            try {
                next.fileWillBeModified(audioFile, z);
            } catch (ModifyVetoException e) {
                vetoThrown(next, audioFile, e);
                throw e;
            }
        }
    }

    public void removeAudioFileModificationListener(AudioFileModificationListener audioFileModificationListener) {
        if (this.listeners.contains(audioFileModificationListener)) {
            this.listeners.remove(audioFileModificationListener);
        }
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileModificationListener
    public void vetoThrown(AudioFileModificationListener audioFileModificationListener, AudioFile audioFile, ModifyVetoException modifyVetoException) {
        Iterator<AudioFileModificationListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().vetoThrown(audioFileModificationListener, audioFile, modifyVetoException);
        }
    }
}
