package org.jaudiotagger.tag.images;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;

/* loaded from: classes3.dex */
public class ArtworkFactory {
    public static Artwork getNew() {
        return new AndroidArtwork();
    }

    public static Artwork createArtworkFromMetadataBlockDataPicture(MetadataBlockDataPicture metadataBlockDataPicture) {
        return AndroidArtwork.createArtworkFromMetadataBlockDataPicture(metadataBlockDataPicture);
    }

    public static Artwork createArtworkFromFile(File file) throws IOException {
        return AndroidArtwork.createArtworkFromFile(file);
    }

    public static Artwork createLinkedArtworkFromURL(String str) throws IOException {
        return AndroidArtwork.createLinkedArtworkFromURL(str);
    }
}
