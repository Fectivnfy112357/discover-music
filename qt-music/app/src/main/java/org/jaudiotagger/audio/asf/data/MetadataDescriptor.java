package org.jaudiotagger.audio.asf.data;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.logging.Logger;
import org.jaudiotagger.audio.asf.util.Utils;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.TagOptionSingleton;

/* loaded from: classes3.dex */
public class MetadataDescriptor implements Comparable<MetadataDescriptor>, Cloneable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int MAX_LANG_INDEX = 127;
    public static final int MAX_STREAM_NUMBER = 127;
    public static final int TYPE_BINARY = 1;
    public static final int TYPE_BOOLEAN = 2;
    public static final int TYPE_DWORD = 3;
    public static final int TYPE_GUID = 6;
    public static final int TYPE_QWORD = 4;
    public static final int TYPE_STRING = 0;
    public static final int TYPE_WORD = 5;
    public static final int WORD_MAXVALUE = 65535;
    private final ContainerType containerType;
    private byte[] content;
    private int descriptorType;
    private int languageIndex;
    private final String name;
    private int streamNumber;
    public static final long DWORD_MAXVALUE = new BigInteger("FFFFFFFF", 16).longValue();
    private static final Logger LOGGER = Logger.getLogger("org.jaudiotagger.audio.asf.data");
    public static final BigInteger QWORD_MAXVALUE = new BigInteger("FFFFFFFFFFFFFFFF", 16);

    public MetadataDescriptor(ContainerType containerType, String str, int i) {
        this(containerType, str, i, 0, 0);
    }

    public MetadataDescriptor(ContainerType containerType, String str, int i, int i2, int i3) {
        this.content = new byte[0];
        this.languageIndex = 0;
        this.streamNumber = 0;
        containerType.assertConstraints(str, new byte[0], i, i2, i3);
        this.containerType = containerType;
        this.name = str;
        this.descriptorType = i;
        this.streamNumber = i2;
        this.languageIndex = i3;
    }

    public MetadataDescriptor(String str) {
        this(str, 0);
    }

    public MetadataDescriptor(String str, int i) {
        this(ContainerType.METADATA_LIBRARY_OBJECT, str, i, 0, 0);
    }

    public BigInteger asNumber() {
        BigInteger bigInteger;
        switch (this.descriptorType) {
            case 0:
                bigInteger = new BigInteger(getString(), 10);
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                if (this.content.length > 8) {
                    throw new NumberFormatException("Binary data would exceed QWORD");
                }
                bigInteger = null;
                break;
            case 6:
                throw new NumberFormatException("GUID cannot be converted to a number.");
            default:
                throw new IllegalStateException();
        }
        if (bigInteger != null) {
            return bigInteger;
        }
        int length = this.content.length;
        byte[] bArr = new byte[length];
        int i = 0;
        while (i < length) {
            byte[] bArr2 = this.content;
            int i2 = i + 1;
            bArr[i] = bArr2[bArr2.length - i2];
            i = i2;
        }
        return new BigInteger(1, bArr);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // java.lang.Comparable
    public int compareTo(MetadataDescriptor metadataDescriptor) {
        return getName().compareTo(metadataDescriptor.getName());
    }

    public MetadataDescriptor createCopy() {
        MetadataDescriptor metadataDescriptor = new MetadataDescriptor(this.containerType, this.name, this.descriptorType, this.streamNumber, this.languageIndex);
        metadataDescriptor.content = getRawData();
        return metadataDescriptor;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MetadataDescriptor)) {
            return false;
        }
        if (obj != this) {
            MetadataDescriptor metadataDescriptor = (MetadataDescriptor) obj;
            if (!metadataDescriptor.getName().equals(getName()) || metadataDescriptor.descriptorType != this.descriptorType || metadataDescriptor.languageIndex != this.languageIndex || metadataDescriptor.streamNumber != this.streamNumber || !Arrays.equals(this.content, metadataDescriptor.content)) {
                return false;
            }
        }
        return true;
    }

    public boolean getBoolean() {
        byte[] bArr = this.content;
        return bArr.length > 0 && bArr[0] != 0;
    }

    @Deprecated
    public byte[] getBytes() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            writeInto(byteArrayOutputStream, this.containerType);
        } catch (IOException e) {
            LOGGER.warning(e.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    public ContainerType getContainerType() {
        return this.containerType;
    }

    public int getCurrentAsfSize(ContainerType containerType) {
        int length = (containerType != ContainerType.EXTENDED_CONTENT ? 14 : 8) + (getName().length() * 2);
        if (getType() == 2) {
            return containerType == ContainerType.EXTENDED_CONTENT ? length + 4 : length + 2;
        }
        int length2 = length + this.content.length;
        return getType() == 0 ? length2 + 2 : length2;
    }

    public GUID getGuid() {
        if (getType() == 6 && this.content.length == 16) {
            return new GUID(this.content);
        }
        return null;
    }

    public int getLanguageIndex() {
        return this.languageIndex;
    }

    public String getName() {
        return this.name;
    }

    public long getNumber() {
        int type = getType();
        int i = 2;
        if (type == 2) {
            i = 1;
        } else if (type == 3) {
            i = 4;
        } else if (type == 4) {
            i = 8;
        } else if (type != 5) {
            throw new UnsupportedOperationException("The current type doesn't allow an interpretation as a number. (" + getType() + ")");
        }
        if (i > this.content.length) {
            throw new IllegalStateException("The stored data cannot represent the type of current object.");
        }
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j |= (this.content[i2] & 255) << (i2 * 8);
        }
        return j;
    }

    public byte[] getRawData() {
        byte[] bArr = this.content;
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }

    public int getRawDataSize() {
        return this.content.length;
    }

    public int getStreamNumber() {
        return this.streamNumber;
    }

    public String getString() {
        switch (getType()) {
            case 0:
                try {
                    return new String(this.content, "UTF-16LE");
                } catch (UnsupportedEncodingException e) {
                    LOGGER.warning(e.getMessage());
                    return null;
                }
            case 1:
                return "binary data";
            case 2:
                return String.valueOf(getBoolean());
            case 3:
            case 4:
            case 5:
                return String.valueOf(getNumber());
            case 6:
                return getGuid() == null ? "Invalid GUID" : getGuid().toString();
            default:
                throw new IllegalStateException("Current type is not known.");
        }
    }

    public int getType() {
        return this.descriptorType;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isEmpty() {
        return this.content.length == 0;
    }

    public void setBinaryValue(byte[] bArr) throws IllegalArgumentException {
        this.containerType.assertConstraints(this.name, bArr, this.descriptorType, this.streamNumber, this.languageIndex);
        this.content = (byte[]) bArr.clone();
        this.descriptorType = 1;
    }

    public void setBooleanValue(boolean z) {
        this.content = new byte[]{z ? (byte) 1 : (byte) 0};
        this.descriptorType = 2;
    }

    public void setDWordValue(long j) {
        if (j < 0 || j > DWORD_MAXVALUE) {
            throw new IllegalArgumentException("value out of range (0-" + DWORD_MAXVALUE + ")");
        }
        this.content = Utils.getBytes(j, 4);
        this.descriptorType = 3;
    }

    public void setGUIDValue(GUID guid) {
        this.containerType.assertConstraints(this.name, guid.getBytes(), 6, this.streamNumber, this.languageIndex);
        this.content = guid.getBytes();
        this.descriptorType = 6;
    }

    public void setLanguageIndex(int i) {
        this.containerType.assertConstraints(this.name, this.content, this.descriptorType, this.streamNumber, i);
        this.languageIndex = i;
    }

    public void setQWordValue(BigInteger bigInteger) throws IllegalArgumentException {
        if (bigInteger == null) {
            throw new NumberFormatException("null");
        }
        if (BigInteger.ZERO.compareTo(bigInteger) > 0) {
            throw new IllegalArgumentException("Only unsigned values allowed (no negative)");
        }
        if (QWORD_MAXVALUE.compareTo(bigInteger) < 0) {
            throw new IllegalArgumentException("Value exceeds QWORD (64 bit unsigned)");
        }
        this.content = new byte[8];
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray.length <= 8) {
            for (int length = byteArray.length - 1; length >= 0; length--) {
                this.content[byteArray.length - (length + 1)] = byteArray[length];
            }
        } else {
            Arrays.fill(this.content, (byte) -1);
        }
        this.descriptorType = 4;
    }

    public void setQWordValue(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("value out of range (0-" + QWORD_MAXVALUE.toString() + ")");
        }
        this.content = Utils.getBytes(j, 8);
        this.descriptorType = 4;
    }

    public void setStreamNumber(int i) {
        this.containerType.assertConstraints(this.name, this.content, this.descriptorType, i, this.languageIndex);
        this.streamNumber = i;
    }

    public void setString(String str) throws IllegalArgumentException {
        try {
            switch (getType()) {
                case 0:
                    setStringValue(str);
                    return;
                case 1:
                    throw new IllegalArgumentException("Cannot interpret binary as string.");
                case 2:
                    setBooleanValue(Boolean.parseBoolean(str));
                    return;
                case 3:
                    setDWordValue(Long.parseLong(str));
                    return;
                case 4:
                    setQWordValue(new BigInteger(str, 10));
                    return;
                case 5:
                    setWordValue(Integer.parseInt(str));
                    return;
                case 6:
                    setGUIDValue(GUID.parseGUID(str));
                    return;
                default:
                    throw new IllegalStateException();
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Value cannot be parsed as Number or is out of range (\"" + str + "\")", e);
        }
    }

    public void setStringValue(String str) throws IllegalArgumentException {
        if (str == null) {
            this.content = new byte[0];
        } else {
            byte[] bytes = Utils.getBytes(str, AsfHeader.ASF_CHARSET);
            if (getContainerType().isWithinValueRange(bytes.length)) {
                this.content = bytes;
            } else if (TagOptionSingleton.getInstance().isTruncateTextWithoutErrors()) {
                int iLongValue = (int) getContainerType().getMaximumDataLength().longValue();
                if (iLongValue % 2 != 0) {
                    iLongValue--;
                }
                byte[] bArr = new byte[iLongValue];
                this.content = bArr;
                System.arraycopy(bytes, 0, bArr, 0, bArr.length);
            } else {
                throw new IllegalArgumentException(ErrorMessage.WMA_LENGTH_OF_DATA_IS_TOO_LARGE.getMsg(Integer.valueOf(bytes.length), getContainerType().getMaximumDataLength(), getContainerType().getContainerGUID().getDescription()));
            }
        }
        this.descriptorType = 0;
    }

    public void setWordValue(int i) throws IllegalArgumentException {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException("value out of range (0-65535)");
        }
        this.content = Utils.getBytes(i, 2);
        this.descriptorType = 5;
    }

    public String toString() {
        return getName() + " : " + new String[]{"String: ", "Binary: ", "Boolean: ", "DWORD: ", "QWORD:", "WORD:", "GUID:"}[this.descriptorType] + getString() + " (language: " + this.languageIndex + " / stream: " + this.streamNumber + ")";
    }

    public int writeInto(OutputStream outputStream, ContainerType containerType) throws IOException {
        byte[] bArr;
        int currentAsfSize = getCurrentAsfSize(containerType);
        if (this.descriptorType == 2) {
            bArr = new byte[containerType == ContainerType.EXTENDED_CONTENT ? 4 : 2];
            bArr[0] = getBoolean() ? (byte) 1 : (byte) 0;
        } else {
            bArr = this.content;
        }
        if (containerType != ContainerType.EXTENDED_CONTENT) {
            Utils.writeUINT16(getLanguageIndex(), outputStream);
            Utils.writeUINT16(getStreamNumber(), outputStream);
        }
        Utils.writeUINT16((getName().length() * 2) + 2, outputStream);
        if (containerType == ContainerType.EXTENDED_CONTENT) {
            outputStream.write(Utils.getBytes(getName(), AsfHeader.ASF_CHARSET));
            outputStream.write(AsfHeader.ZERO_TERM);
        }
        int type = getType();
        Utils.writeUINT16(type, outputStream);
        int length = bArr.length;
        if (type == 0) {
            length += 2;
        }
        if (containerType == ContainerType.EXTENDED_CONTENT) {
            Utils.writeUINT16(length, outputStream);
        } else {
            Utils.writeUINT32(length, outputStream);
        }
        if (containerType != ContainerType.EXTENDED_CONTENT) {
            outputStream.write(Utils.getBytes(getName(), AsfHeader.ASF_CHARSET));
            outputStream.write(AsfHeader.ZERO_TERM);
        }
        outputStream.write(bArr);
        if (type == 0) {
            outputStream.write(AsfHeader.ZERO_TERM);
        }
        return currentAsfSize;
    }
}
