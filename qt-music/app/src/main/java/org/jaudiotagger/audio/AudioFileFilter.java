package org.jaudiotagger.audio;

import java.io.File;
import java.io.FileFilter;
import org.jaudiotagger.audio.generic.Utils;

/* loaded from: classes3.dex */
public class AudioFileFilter implements FileFilter {
    private final boolean allowDirectories;

    public AudioFileFilter(boolean z) {
        this.allowDirectories = z;
    }

    public AudioFileFilter() {
        this(true);
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (!file.isHidden() && file.canRead()) {
            if (file.isDirectory()) {
                return this.allowDirectories;
            }
            try {
                if (SupportedFileFormat.valueOf(Utils.getExtension(file).toUpperCase()) != null) {
                    return true;
                }
            } catch (IllegalArgumentException unused) {
            }
        }
        return false;
    }
}
