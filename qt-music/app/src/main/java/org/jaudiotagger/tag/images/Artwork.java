package org.jaudiotagger.tag.images;

import java.io.File;
import java.io.IOException;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;

/* loaded from: classes3.dex */
public interface Artwork {
    byte[] getBinaryData();

    String getDescription();

    int getHeight();

    Object getImage() throws IOException;

    String getImageUrl();

    String getMimeType();

    int getPictureType();

    int getWidth();

    boolean isLinked();

    void setBinaryData(byte[] bArr);

    void setDescription(String str);

    void setFromFile(File file) throws IOException;

    void setFromMetadataBlockDataPicture(MetadataBlockDataPicture metadataBlockDataPicture);

    void setHeight(int i);

    boolean setImageFromData();

    void setImageUrl(String str);

    void setLinked(boolean z);

    void setMimeType(String str);

    void setPictureType(int i);

    void setWidth(int i);
}
