package org.jaudiotagger.tag.asf;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.MetadataDescriptor;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;

/* loaded from: classes3.dex */
public class AsfTagCoverField extends AbstractAsfTagImageField {
    public static final Logger LOGGER = Logger.getLogger("org.jaudiotagger.audio.asf.tag");
    private String description;
    private int endOfName;
    private int imageDataSize;
    private String mimeType;
    private int pictureType;

    public AsfTagCoverField(byte[] bArr, int i, String str, String str2) throws IllegalArgumentException {
        super(new MetadataDescriptor(AsfFieldKey.COVER_ART.getFieldName(), 1));
        this.endOfName = 0;
        getDescriptor().setBinaryValue(createRawContent(bArr, i, str, str2));
    }

    public AsfTagCoverField(MetadataDescriptor metadataDescriptor) {
        super(metadataDescriptor);
        this.endOfName = 0;
        if (!metadataDescriptor.getName().equals(AsfFieldKey.COVER_ART.getFieldName())) {
            throw new IllegalArgumentException("Descriptor description must be WM/Picture");
        }
        if (metadataDescriptor.getType() != 1) {
            throw new IllegalArgumentException("Descriptor type must be binary");
        }
        try {
            processRawContent();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] createRawContent(byte[] bArr, int i, String str, String str2) throws UnsupportedEncodingException {
        this.description = str;
        this.imageDataSize = bArr.length;
        this.pictureType = i;
        this.mimeType = str2;
        if (str2 == null && (str2 = ImageFormats.getMimeTypeForBinarySignature(bArr)) == null) {
            LOGGER.warning(ErrorMessage.GENERAL_UNIDENITIFED_IMAGE_FORMAT.getMsg());
            str2 = "image/png";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(i);
        byteArrayOutputStream.write(Utils.getSizeLEInt32(bArr.length), 0, 4);
        try {
            byte[] bytes = str2.getBytes(AsfHeader.ASF_CHARSET.name());
            byteArrayOutputStream.write(bytes, 0, bytes.length);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            if (str != null && str.length() > 0) {
                try {
                    byte[] bytes2 = str.getBytes(AsfHeader.ASF_CHARSET.name());
                    byteArrayOutputStream.write(bytes2, 0, bytes2.length);
                } catch (UnsupportedEncodingException unused) {
                    throw new RuntimeException("Unable to find encoding:" + AsfHeader.ASF_CHARSET.name());
                }
            }
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(0);
            byteArrayOutputStream.write(bArr, 0, bArr.length);
            return byteArrayOutputStream.toByteArray();
        } catch (UnsupportedEncodingException unused2) {
            throw new RuntimeException("Unable to find encoding:" + AsfHeader.ASF_CHARSET.name());
        }
    }

    public String getDescription() {
        return this.description;
    }

    @Override // org.jaudiotagger.tag.asf.AbstractAsfTagImageField
    public int getImageDataSize() {
        return this.imageDataSize;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public int getPictureType() {
        return this.pictureType;
    }

    @Override // org.jaudiotagger.tag.asf.AbstractAsfTagImageField
    public byte[] getRawImageData() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(getRawContent(), this.endOfName, this.toWrap.getRawDataSize() - this.endOfName);
        return byteArrayOutputStream.toByteArray();
    }

    private void processRawContent() throws UnsupportedEncodingException {
        int i = 0;
        this.pictureType = getRawContent()[0];
        this.imageDataSize = Utils.getIntLE(getRawContent(), 1, 2);
        this.mimeType = null;
        this.description = null;
        for (int i2 = 5; i2 < getRawContent().length - 1; i2 += 2) {
            if (getRawContent()[i2] == 0 && getRawContent()[i2 + 1] == 0) {
                if (this.mimeType == null) {
                    this.mimeType = new String(getRawContent(), 5, i2 - 5, "UTF-16LE");
                    i = i2 + 2;
                } else if (this.description == null) {
                    this.description = new String(getRawContent(), i, i2 - i, "UTF-16LE");
                    this.endOfName = i2 + 2;
                    return;
                }
            }
        }
    }
}
