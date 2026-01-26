package org.jaudiotagger.tag.images;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;
import org.jaudiotagger.tag.reference.PictureTypes;

/* loaded from: classes3.dex */
public class AndroidArtwork implements Artwork {
    private byte[] binaryData;
    private int height;
    private int width;
    private String mimeType = "";
    private String description = "";
    private boolean isLinked = false;
    private String imageUrl = "";
    private int pictureType = -1;

    @Override // org.jaudiotagger.tag.images.Artwork
    public byte[] getBinaryData() {
        return this.binaryData;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setBinaryData(byte[] bArr) {
        this.binaryData = bArr;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public String getMimeType() {
        return this.mimeType;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setMimeType(String str) {
        this.mimeType = str;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public String getDescription() {
        return this.description;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public int getHeight() {
        return this.height;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public int getWidth() {
        return this.width;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setDescription(String str) {
        this.description = str;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public boolean setImageFromData() {
        throw new UnsupportedOperationException();
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public Object getImage() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public boolean isLinked() {
        return this.isLinked;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setLinked(boolean z) {
        this.isLinked = z;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public String getImageUrl() {
        return this.imageUrl;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public int getPictureType() {
        return this.pictureType;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setPictureType(int i) {
        this.pictureType = i;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setFromFile(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        byte[] bArr = new byte[(int) randomAccessFile.length()];
        randomAccessFile.read(bArr);
        randomAccessFile.close();
        setBinaryData(bArr);
        setMimeType(ImageFormats.getMimeTypeForBinarySignature(bArr));
        setDescription("");
        setPictureType(PictureTypes.DEFAULT_ID.intValue());
    }

    public static AndroidArtwork createArtworkFromFile(File file) throws IOException {
        AndroidArtwork androidArtwork = new AndroidArtwork();
        androidArtwork.setFromFile(file);
        return androidArtwork;
    }

    public static AndroidArtwork createLinkedArtworkFromURL(String str) throws IOException {
        AndroidArtwork androidArtwork = new AndroidArtwork();
        androidArtwork.setLinkedFromURL(str);
        return androidArtwork;
    }

    public void setLinkedFromURL(String str) throws IOException {
        setLinked(true);
        setImageUrl(str);
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setFromMetadataBlockDataPicture(MetadataBlockDataPicture metadataBlockDataPicture) {
        setMimeType(metadataBlockDataPicture.getMimeType());
        setDescription(metadataBlockDataPicture.getDescription());
        setPictureType(metadataBlockDataPicture.getPictureType());
        if (metadataBlockDataPicture.isImageUrl()) {
            setLinked(metadataBlockDataPicture.isImageUrl());
            setImageUrl(metadataBlockDataPicture.getImageUrl());
        } else {
            setBinaryData(metadataBlockDataPicture.getImageData());
        }
        setWidth(metadataBlockDataPicture.getWidth());
        setHeight(metadataBlockDataPicture.getHeight());
    }

    public static AndroidArtwork createArtworkFromMetadataBlockDataPicture(MetadataBlockDataPicture metadataBlockDataPicture) {
        AndroidArtwork androidArtwork = new AndroidArtwork();
        androidArtwork.setFromMetadataBlockDataPicture(metadataBlockDataPicture);
        return androidArtwork;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setWidth(int i) {
        this.width = i;
    }

    @Override // org.jaudiotagger.tag.images.Artwork
    public void setHeight(int i) {
        this.height = i;
    }
}
