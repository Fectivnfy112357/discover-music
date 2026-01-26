package org.jaudiotagger.audio.flac.metadatablock;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.reference.PictureTypes;

/* loaded from: classes3.dex */
public class MetadataBlockDataPicture implements MetadataBlockData, TagField {
    public static final String IMAGE_IS_URL = "-->";
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac.MetadataBlockDataPicture");
    private int colourDepth;
    private String description;
    private int descriptionSize;
    private int height;
    private byte[] imageData;
    private int indexedColouredCount;
    private int lengthOfPictureInBytes;
    private String mimeType;
    private int mimeTypeSize;
    private int pictureType;
    private int width;

    @Override // org.jaudiotagger.tag.TagField
    public void isBinary(boolean z) {
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return true;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isCommon() {
        return true;
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return false;
    }

    private void initFromByteBuffer(ByteBuffer byteBuffer) throws InvalidFrameException, IOException {
        int i = byteBuffer.getInt();
        this.pictureType = i;
        if (i >= PictureTypes.getInstanceOf().getSize()) {
            throw new InvalidFrameException("PictureType was:" + this.pictureType + "but the maximum allowed is " + (PictureTypes.getInstanceOf().getSize() - 1));
        }
        int i2 = byteBuffer.getInt();
        this.mimeTypeSize = i2;
        if (i2 < 0) {
            throw new InvalidFrameException("PictureType mimeType size was invalid:" + this.mimeTypeSize);
        }
        this.mimeType = getString(byteBuffer, i2, StandardCharsets.ISO_8859_1.name());
        int i3 = byteBuffer.getInt();
        this.descriptionSize = i3;
        if (i3 < 0) {
            throw new InvalidFrameException("PictureType descriptionSize size was invalid:" + this.mimeTypeSize);
        }
        this.description = getString(byteBuffer, i3, StandardCharsets.UTF_8.name());
        this.width = byteBuffer.getInt();
        this.height = byteBuffer.getInt();
        this.colourDepth = byteBuffer.getInt();
        this.indexedColouredCount = byteBuffer.getInt();
        int i4 = byteBuffer.getInt();
        this.lengthOfPictureInBytes = i4;
        if (i4 > byteBuffer.remaining()) {
            throw new InvalidFrameException("PictureType Size was:" + this.lengthOfPictureInBytes + " but remaining bytes size " + byteBuffer.remaining());
        }
        byte[] bArr = new byte[this.lengthOfPictureInBytes];
        this.imageData = bArr;
        byteBuffer.get(bArr);
        logger.config("Read image:" + toString());
    }

    public MetadataBlockDataPicture(ByteBuffer byteBuffer) throws InvalidFrameException, IOException {
        this.mimeType = "";
        this.description = "";
        initFromByteBuffer(byteBuffer);
    }

    public MetadataBlockDataPicture(MetadataBlockHeader metadataBlockHeader, FileChannel fileChannel) throws InvalidFrameException, IOException {
        this.mimeType = "";
        this.description = "";
        if (metadataBlockHeader.getDataLength() == 0) {
            throw new IOException("MetadataBlockDataPicture HeaderDataSize is zero");
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(metadataBlockHeader.getDataLength());
        int i = fileChannel.read(byteBufferAllocate);
        if (i < metadataBlockHeader.getDataLength()) {
            throw new IOException("Unable to read required number of databytes read:" + i + ":required:" + metadataBlockHeader.getDataLength());
        }
        byteBufferAllocate.rewind();
        initFromByteBuffer(byteBufferAllocate);
    }

    public MetadataBlockDataPicture(byte[] bArr, int i, String str, String str2, int i2, int i3, int i4, int i5) {
        this.mimeType = "";
        this.description = "";
        this.pictureType = i;
        if (str != null) {
            this.mimeType = str;
        }
        this.description = str2;
        this.width = i2;
        this.height = i3;
        this.colourDepth = i4;
        this.indexedColouredCount = i5;
        this.imageData = bArr;
    }

    private String getString(ByteBuffer byteBuffer, int i, String str) throws IOException {
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return new String(bArr, str);
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public ByteBuffer getBytes() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.pictureType));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.mimeType.getBytes(StandardCharsets.ISO_8859_1).length));
            byteArrayOutputStream.write(this.mimeType.getBytes(StandardCharsets.ISO_8859_1));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.description.getBytes(StandardCharsets.UTF_8).length));
            byteArrayOutputStream.write(this.description.getBytes(StandardCharsets.UTF_8));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.width));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.height));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.colourDepth));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.indexedColouredCount));
            byteArrayOutputStream.write(Utils.getSizeBEInt32(this.imageData.length));
            byteArrayOutputStream.write(this.imageData);
            return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override // org.jaudiotagger.audio.flac.metadatablock.MetadataBlockData
    public int getLength() {
        return getBytes().limit();
    }

    public int getPictureType() {
        return this.pictureType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getDescription() {
        return this.description;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getColourDepth() {
        return this.colourDepth;
    }

    public int getIndexedColourCount() {
        return this.indexedColouredCount;
    }

    public byte[] getImageData() {
        return this.imageData;
    }

    public boolean isImageUrl() {
        return getMimeType().equals("-->");
    }

    public String getImageUrl() {
        if (isImageUrl()) {
            return new String(getImageData(), 0, getImageData().length, StandardCharsets.ISO_8859_1);
        }
        return "";
    }

    @Override // org.jaudiotagger.tag.TagField
    public String toString() {
        return "\t\t" + PictureTypes.getInstanceOf().getValueForId(this.pictureType) + "\n\t\tmimeType:size:" + this.mimeTypeSize + ":" + this.mimeType + "\n\t\tdescription:size:" + this.descriptionSize + ":" + this.description + "\n\t\twidth:" + this.width + "\n\t\theight:" + this.height + "\n\t\tcolourdepth:" + this.colourDepth + "\n\t\tindexedColourCount:" + this.indexedColouredCount + "\n\t\timage size in bytes:" + this.lengthOfPictureInBytes + "/" + this.imageData.length + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE;
    }

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
        throw new UnsupportedOperationException();
    }

    @Override // org.jaudiotagger.tag.TagField
    public String getId() {
        return FieldKey.COVER_ART.name();
    }

    @Override // org.jaudiotagger.tag.TagField
    public byte[] getRawContent() throws UnsupportedEncodingException {
        return getBytes().array();
    }
}
