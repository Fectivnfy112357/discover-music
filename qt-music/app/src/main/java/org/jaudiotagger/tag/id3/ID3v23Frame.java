package org.jaudiotagger.tag.id3;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.EmptyFrameException;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.InvalidFrameIdentifierException;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.ID3v24Frame;
import org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody;
import org.jaudiotagger.tag.id3.framebody.FrameBodyDeprecated;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported;
import org.jaudiotagger.tag.id3.framebody.ID3v23FrameBody;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.utils.EqualsUtil;

/* loaded from: classes3.dex */
public class ID3v23Frame extends AbstractID3v2Frame {
    protected static final int FRAME_COMPRESSION_UNCOMPRESSED_SIZE = 4;
    protected static final int FRAME_ENCRYPTION_INDICATOR_SIZE = 1;
    protected static final int FRAME_FLAGS_SIZE = 2;
    protected static final int FRAME_GROUPING_INDICATOR_SIZE = 1;
    protected static final int FRAME_HEADER_SIZE = 10;
    protected static final int FRAME_ID_SIZE = 4;
    protected static final int FRAME_SIZE_SIZE = 4;
    private static Pattern validFrameIdentifier = Pattern.compile("[A-Z][0-9A-Z]{3}");
    private int encryptionMethod;
    private int groupIdentifier;

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected int getFrameHeaderSize() {
        return 10;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected int getFrameIdSize() {
        return 4;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected int getFrameSizeSize() {
        return 4;
    }

    public ID3v23Frame() {
    }

    public ID3v23Frame(String str) {
        super(str);
        this.statusFlags = new StatusFlags();
        this.encodingFlags = new EncodingFlags();
    }

    public ID3v23Frame(ID3v23Frame iD3v23Frame) {
        super(iD3v23Frame);
        this.statusFlags = new StatusFlags(iD3v23Frame.getStatusFlags().getOriginalFlags());
        this.encodingFlags = new EncodingFlags(iD3v23Frame.getEncodingFlags().getFlags());
    }

    protected ID3v23Frame(ID3v24Frame iD3v24Frame, String str) throws InvalidFrameException {
        this.identifier = str;
        this.statusFlags = new StatusFlags((ID3v24Frame.StatusFlags) iD3v24Frame.getStatusFlags());
        this.encodingFlags = new EncodingFlags(iD3v24Frame.getEncodingFlags().getFlags());
    }

    public ID3v23Frame(AbstractID3v2Frame abstractID3v2Frame) throws InvalidFrameException {
        logger.finer("Creating frame from a frame of a different version");
        if (abstractID3v2Frame instanceof ID3v23Frame) {
            throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
        }
        boolean z = abstractID3v2Frame instanceof ID3v22Frame;
        if (z) {
            this.statusFlags = new StatusFlags();
            this.encodingFlags = new EncodingFlags();
        } else if (abstractID3v2Frame instanceof ID3v24Frame) {
            this.statusFlags = new StatusFlags((ID3v24Frame.StatusFlags) abstractID3v2Frame.getStatusFlags());
            this.encodingFlags = new EncodingFlags(abstractID3v2Frame.getEncodingFlags().getFlags());
        }
        if (abstractID3v2Frame instanceof ID3v24Frame) {
            if (abstractID3v2Frame.getBody() instanceof FrameBodyUnsupported) {
                this.frameBody = new FrameBodyUnsupported((FrameBodyUnsupported) abstractID3v2Frame.getBody());
                this.frameBody.setHeader(this);
                this.identifier = abstractID3v2Frame.getIdentifier();
                logger.config("UNKNOWN:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                return;
            }
            if (abstractID3v2Frame.getBody() instanceof FrameBodyDeprecated) {
                if (ID3Tags.isID3v23FrameIdentifier(abstractID3v2Frame.getIdentifier())) {
                    this.frameBody = ((FrameBodyDeprecated) abstractID3v2Frame.getBody()).getOriginalFrameBody();
                    this.frameBody.setHeader(this);
                    this.frameBody.setTextEncoding(ID3TextEncodingConversion.getTextEncoding(this, this.frameBody.getTextEncoding()));
                    this.identifier = abstractID3v2Frame.getIdentifier();
                    logger.config("DEPRECATED:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                } else {
                    this.frameBody = new FrameBodyDeprecated((FrameBodyDeprecated) abstractID3v2Frame.getBody());
                    this.frameBody.setHeader(this);
                    this.frameBody.setTextEncoding(ID3TextEncodingConversion.getTextEncoding(this, this.frameBody.getTextEncoding()));
                    this.identifier = abstractID3v2Frame.getIdentifier();
                    logger.config("DEPRECATED:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                    return;
                }
            } else {
                if (ID3Tags.isID3v24FrameIdentifier(abstractID3v2Frame.getIdentifier())) {
                    logger.finer("isID3v24FrameIdentifier");
                    this.identifier = ID3Tags.convertFrameID24To23(abstractID3v2Frame.getIdentifier());
                    if (this.identifier != null) {
                        logger.finer("V4:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                        this.frameBody = (AbstractTagFrameBody) ID3Tags.copyObject(abstractID3v2Frame.getBody());
                        this.frameBody.setHeader(this);
                        this.frameBody.setTextEncoding(ID3TextEncodingConversion.getTextEncoding(this, this.frameBody.getTextEncoding()));
                        return;
                    }
                    this.identifier = ID3Tags.forceFrameID24To23(abstractID3v2Frame.getIdentifier());
                    if (this.identifier != null) {
                        logger.finer("V4:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                        this.frameBody = readBody(this.identifier, (AbstractID3v2FrameBody) abstractID3v2Frame.getBody());
                        this.frameBody.setHeader(this);
                        this.frameBody.setTextEncoding(ID3TextEncodingConversion.getTextEncoding(this, this.frameBody.getTextEncoding()));
                        return;
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ((AbstractID3v2FrameBody) abstractID3v2Frame.getBody()).write(byteArrayOutputStream);
                    this.identifier = abstractID3v2Frame.getIdentifier();
                    this.frameBody = new FrameBodyUnsupported(this.identifier, byteArrayOutputStream.toByteArray());
                    this.frameBody.setHeader(this);
                    logger.finer("V4:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New Id Unsupported is:" + this.identifier);
                    return;
                }
                logger.severe("Orig id is:" + abstractID3v2Frame.getIdentifier() + ":Unable to create Frame Body");
                throw new InvalidFrameException("Orig id is:" + abstractID3v2Frame.getIdentifier() + "Unable to create Frame Body");
            }
        } else if (z) {
            if (ID3Tags.isID3v22FrameIdentifier(abstractID3v2Frame.getIdentifier())) {
                this.identifier = ID3Tags.convertFrameID22To23(abstractID3v2Frame.getIdentifier());
                if (this.identifier != null) {
                    logger.config("V3:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                    this.frameBody = (AbstractTagFrameBody) ID3Tags.copyObject(abstractID3v2Frame.getBody());
                    this.frameBody.setHeader(this);
                    return;
                } else if (ID3Tags.isID3v22FrameIdentifier(abstractID3v2Frame.getIdentifier())) {
                    this.identifier = ID3Tags.forceFrameID22To23(abstractID3v2Frame.getIdentifier());
                    if (this.identifier != null) {
                        logger.config("V22Orig id is:" + abstractID3v2Frame.getIdentifier() + "New id is:" + this.identifier);
                        this.frameBody = readBody(this.identifier, (AbstractID3v2FrameBody) abstractID3v2Frame.getBody());
                        this.frameBody.setHeader(this);
                        return;
                    } else {
                        this.frameBody = new FrameBodyDeprecated((AbstractID3v2FrameBody) abstractID3v2Frame.getBody());
                        this.frameBody.setHeader(this);
                        this.identifier = abstractID3v2Frame.getIdentifier();
                        logger.config("Deprecated:V22:orig id id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                        return;
                    }
                }
            } else {
                this.frameBody = new FrameBodyUnsupported((FrameBodyUnsupported) abstractID3v2Frame.getBody());
                this.frameBody.setHeader(this);
                this.identifier = abstractID3v2Frame.getIdentifier();
                logger.config("UNKNOWN:Orig id is:" + abstractID3v2Frame.getIdentifier() + ":New id is:" + this.identifier);
                return;
            }
        }
        logger.warning("Frame is unknown version:" + abstractID3v2Frame.getClass());
    }

    public ID3v23Frame(ByteBuffer byteBuffer, String str) throws InvalidFrameException, InvalidDataTypeException {
        setLoggingFilename(str);
        read(byteBuffer);
    }

    public ID3v23Frame(ByteBuffer byteBuffer) throws InvalidFrameException, InvalidDataTypeException {
        this(byteBuffer, "");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return this.frameBody.getSize() + 10;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame, org.jaudiotagger.tag.id3.AbstractTagFrame, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ID3v23Frame)) {
            return false;
        }
        ID3v23Frame iD3v23Frame = (ID3v23Frame) obj;
        return EqualsUtil.areEqual(this.statusFlags, iD3v23Frame.statusFlags) && EqualsUtil.areEqual(this.encodingFlags, iD3v23Frame.encodingFlags) && super.equals(iD3v23Frame);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidFrameException, InvalidDataTypeException {
        int i;
        int i2;
        String identifier = readIdentifier(byteBuffer);
        if (!isValidID3v2FrameIdentifier(identifier)) {
            logger.config(getLoggingFilename() + ":Invalid identifier:" + identifier);
            byteBuffer.position(byteBuffer.position() - (getFrameIdSize() - 1));
            throw new InvalidFrameIdentifierException(getLoggingFilename() + ":" + identifier + ":is not a valid ID3v2.30 frame");
        }
        this.frameSize = byteBuffer.getInt();
        if (this.frameSize < 0) {
            logger.warning(getLoggingFilename() + ":Invalid Frame Size:" + this.frameSize + ":" + identifier);
            throw new InvalidFrameException(identifier + " is invalid frame:" + this.frameSize);
        }
        if (this.frameSize == 0) {
            logger.warning(getLoggingFilename() + ":Empty Frame Size:" + identifier);
            byteBuffer.get();
            byteBuffer.get();
            throw new EmptyFrameException(identifier + " is empty frame");
        }
        if (this.frameSize + 2 > byteBuffer.remaining()) {
            logger.warning(getLoggingFilename() + ":Invalid Frame size of " + this.frameSize + " larger than size of" + byteBuffer.remaining() + " before mp3 audio:" + identifier);
            throw new InvalidFrameException(identifier + " is invalid frame:" + this.frameSize + " larger than size of" + byteBuffer.remaining() + " before mp3 audio:" + identifier);
        }
        this.statusFlags = new StatusFlags(byteBuffer.get());
        this.encodingFlags = new EncodingFlags(byteBuffer.get());
        String strConvertFrameID23To24 = ID3Tags.convertFrameID23To24(identifier);
        if (strConvertFrameID23To24 == null) {
            strConvertFrameID23To24 = ID3Tags.isID3v23FrameIdentifier(identifier) ? identifier : "Unsupported";
        }
        if (((EncodingFlags) this.encodingFlags).isCompression()) {
            i2 = byteBuffer.getInt();
            logger.fine(getLoggingFilename() + ":Decompressed frame size is:" + i2);
            i = 4;
        } else {
            i = 0;
            i2 = -1;
        }
        if (((EncodingFlags) this.encodingFlags).isEncryption()) {
            i++;
            this.encryptionMethod = byteBuffer.get();
        }
        if (((EncodingFlags) this.encodingFlags).isGrouping()) {
            i++;
            this.groupIdentifier = byteBuffer.get();
        }
        if (((EncodingFlags) this.encodingFlags).isNonStandardFlags()) {
            logger.severe(getLoggingFilename() + ":InvalidEncodingFlags:" + Hex.asHex(((EncodingFlags) this.encodingFlags).getFlags()));
        }
        if (((EncodingFlags) this.encodingFlags).isCompression() && i2 > this.frameSize * 100) {
            throw new InvalidFrameException(identifier + " is invalid frame, frame size " + this.frameSize + " cannot be:" + i2 + " when uncompressed");
        }
        int i3 = this.frameSize - i;
        if (i3 <= 0) {
            throw new InvalidFrameException(identifier + " is invalid frame, realframeSize is:" + i3);
        }
        try {
            if (((EncodingFlags) this.encodingFlags).isCompression()) {
                ByteBuffer byteBufferUncompress = ID3Compression.uncompress(identifier, getLoggingFilename(), byteBuffer, i2, i3);
                if (((EncodingFlags) this.encodingFlags).isEncryption()) {
                    this.frameBody = readEncryptedBody(strConvertFrameID23To24, byteBufferUncompress, i2);
                } else {
                    this.frameBody = readBody(strConvertFrameID23To24, byteBufferUncompress, i2);
                }
            } else if (((EncodingFlags) this.encodingFlags).isEncryption()) {
                if (byteBuffer.remaining() >= this.frameSize) {
                    ByteBuffer byteBufferSlice = byteBuffer.slice();
                    byteBufferSlice.limit(this.frameSize);
                    this.frameBody = readEncryptedBody(identifier, byteBufferSlice, this.frameSize);
                } else {
                    logger.warning(getLoggingFilename() + ":Invalid Frame " + this.frameSize + " encodingFlagSetButNotEnoughBytes:" + byteBuffer.remaining() + " before mp3 audio:" + identifier);
                    throw new InvalidFrameException(identifier + " invalid frame:" + this.frameSize + "  encodingFlagSetButNotEnoughBytes:" + byteBuffer.remaining() + " before mp3 audio:" + identifier);
                }
            } else {
                ByteBuffer byteBufferSlice2 = byteBuffer.slice();
                byteBufferSlice2.limit(i3);
                this.frameBody = readBody(strConvertFrameID23To24, byteBufferSlice2, i3);
            }
            if (!(this.frameBody instanceof ID3v23FrameBody)) {
                logger.config(getLoggingFilename() + ":Converted frameBody with:" + identifier + " to deprecated frameBody");
                this.frameBody = new FrameBodyDeprecated((AbstractID3v2FrameBody) this.frameBody);
            }
        } finally {
            byteBuffer.position(byteBuffer.position() + i3);
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        logger.config("Writing frame to buffer:" + getIdentifier());
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(10);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ((AbstractID3v2FrameBody) this.frameBody).write(byteArrayOutputStream2);
        if (getIdentifier().length() == 3) {
            this.identifier += ' ';
        }
        byteBufferAllocate.put(getIdentifier().getBytes(StandardCharsets.ISO_8859_1), 0, 4);
        logger.fine("Frame Size Is:" + this.frameBody.getSize());
        byteBufferAllocate.putInt(this.frameBody.getSize());
        byteBufferAllocate.put(this.statusFlags.getWriteFlags());
        ((EncodingFlags) this.encodingFlags).unsetNonStandardFlags();
        ((EncodingFlags) this.encodingFlags).unsetCompression();
        byteBufferAllocate.put(this.encodingFlags.getFlags());
        try {
            byteArrayOutputStream.write(byteBufferAllocate.array());
            if (((EncodingFlags) this.encodingFlags).isEncryption()) {
                byteArrayOutputStream.write(this.encryptionMethod);
            }
            if (((EncodingFlags) this.encodingFlags).isGrouping()) {
                byteArrayOutputStream.write(this.groupIdentifier);
            }
            byteArrayOutputStream.write(byteArrayOutputStream2.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public AbstractID3v2Frame.StatusFlags getStatusFlags() {
        return this.statusFlags;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public AbstractID3v2Frame.EncodingFlags getEncodingFlags() {
        return this.encodingFlags;
    }

    public int getEncryptionMethod() {
        return this.encryptionMethod;
    }

    public int getGroupIdentifier() {
        return this.groupIdentifier;
    }

    class StatusFlags extends AbstractID3v2Frame.StatusFlags {
        public static final int MASK_FILE_ALTER_PRESERVATION = 64;
        public static final int MASK_READ_ONLY = 32;
        public static final int MASK_TAG_ALTER_PRESERVATION = 128;
        public static final String TYPE_FILEALTERPRESERVATION = "typeFileAlterPreservation";
        public static final String TYPE_READONLY = "typeReadOnly";
        public static final String TYPE_TAGALTERPRESERVATION = "typeTagAlterPreservation";

        private byte convertV4ToV3Flags(byte b) {
            byte b2 = (b & 32) != 0 ? (byte) 64 : (byte) 0;
            return (b & SignedBytes.MAX_POWER_OF_TWO) != 0 ? (byte) (b2 | (-128)) : b2;
        }

        public StatusFlags() {
            super();
            this.originalFlags = (byte) 0;
            this.writeFlags = (byte) 0;
        }

        StatusFlags(byte b) {
            super();
            this.originalFlags = b;
            this.writeFlags = b;
            modifyFlags();
        }

        StatusFlags(ID3v24Frame.StatusFlags statusFlags) {
            super();
            this.originalFlags = convertV4ToV3Flags(statusFlags.getOriginalFlags());
            this.writeFlags = this.originalFlags;
            modifyFlags();
        }

        protected void modifyFlags() {
            if (ID3v23Frames.getInstanceOf().isDiscardIfFileAltered(ID3v23Frame.this.getIdentifier())) {
                this.writeFlags = (byte) (this.writeFlags | SignedBytes.MAX_POWER_OF_TWO);
                this.writeFlags = (byte) (this.writeFlags & 127);
            } else {
                this.writeFlags = (byte) (this.writeFlags & (-65));
                this.writeFlags = (byte) (this.writeFlags & 127);
            }
        }

        @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame.StatusFlags
        public void createStructure() {
            MP3File.getStructureFormatter().openHeadingElement("statusFlags", "");
            MP3File.getStructureFormatter().addElement("typeTagAlterPreservation", this.originalFlags & 128);
            MP3File.getStructureFormatter().addElement("typeFileAlterPreservation", this.originalFlags & SignedBytes.MAX_POWER_OF_TWO);
            MP3File.getStructureFormatter().addElement("typeReadOnly", this.originalFlags & 32);
            MP3File.getStructureFormatter().closeHeadingElement("statusFlags");
        }
    }

    class EncodingFlags extends AbstractID3v2Frame.EncodingFlags {
        public static final int MASK_COMPRESSION = 128;
        public static final int MASK_ENCRYPTION = 64;
        public static final int MASK_GROUPING_IDENTITY = 32;
        public static final String TYPE_COMPRESSION = "compression";
        public static final String TYPE_ENCRYPTION = "encryption";
        public static final String TYPE_GROUPIDENTITY = "groupidentity";

        public EncodingFlags() {
            super();
        }

        public EncodingFlags(byte b) {
            super(b);
            logEnabledFlags();
        }

        public void setCompression() {
            this.flags = (byte) (this.flags | 128);
        }

        public void setEncryption() {
            this.flags = (byte) (this.flags | SignedBytes.MAX_POWER_OF_TWO);
        }

        public void setGrouping() {
            this.flags = (byte) (this.flags | 32);
        }

        public void unsetCompression() {
            this.flags = (byte) (this.flags & 127);
        }

        public void unsetEncryption() {
            this.flags = (byte) (this.flags & (-65));
        }

        public void unsetGrouping() {
            this.flags = (byte) (this.flags & (-33));
        }

        public boolean isNonStandardFlags() {
            return (this.flags & 16) > 0 || (this.flags & 8) > 0 || (this.flags & 4) > 0 || (this.flags & 2) > 0 || (this.flags & 1) > 0;
        }

        public void unsetNonStandardFlags() {
            if (isNonStandardFlags()) {
                AbstractTagItem.logger.warning(ID3v23Frame.this.getLoggingFilename() + ":" + ID3v23Frame.this.getIdentifier() + ":Unsetting Unknown Encoding Flags:" + Hex.asHex(this.flags));
                this.flags = (byte) (this.flags & (-17));
                this.flags = (byte) (this.flags & (-9));
                this.flags = (byte) (this.flags & (-5));
                this.flags = (byte) (this.flags & (-3));
                this.flags = (byte) (this.flags & (-2));
            }
        }

        public void logEnabledFlags() {
            if (isNonStandardFlags()) {
                AbstractTagItem.logger.warning(ID3v23Frame.this.getLoggingFilename() + ":" + ID3v23Frame.this.identifier + ":Unknown Encoding Flags:" + Hex.asHex(this.flags));
            }
            if (isCompression()) {
                AbstractTagItem.logger.warning(ID3v23Frame.this.getLoggingFilename() + ":" + ID3v23Frame.this.identifier + " is compressed");
            }
            if (isEncryption()) {
                AbstractTagItem.logger.warning(ID3v23Frame.this.getLoggingFilename() + ":" + ID3v23Frame.this.identifier + " is encrypted");
            }
            if (isGrouping()) {
                AbstractTagItem.logger.warning(ID3v23Frame.this.getLoggingFilename() + ":" + ID3v23Frame.this.identifier + " is grouped");
            }
        }

        public boolean isCompression() {
            return (this.flags & 128) > 0;
        }

        public boolean isEncryption() {
            return (this.flags & SignedBytes.MAX_POWER_OF_TWO) > 0;
        }

        public boolean isGrouping() {
            return (this.flags & 32) > 0;
        }

        @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame.EncodingFlags
        public void createStructure() {
            MP3File.getStructureFormatter().openHeadingElement("encodingFlags", "");
            MP3File.getStructureFormatter().addElement("compression", this.flags & 128);
            MP3File.getStructureFormatter().addElement("encryption", this.flags & SignedBytes.MAX_POWER_OF_TWO);
            MP3File.getStructureFormatter().addElement("groupidentity", this.flags & 32);
            MP3File.getStructureFormatter().closeHeadingElement("encodingFlags");
        }
    }

    public boolean isValidID3v2FrameIdentifier(String str) {
        return validFrameIdentifier.matcher(str).matches();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement("frame", getIdentifier());
        MP3File.getStructureFormatter().addElement("frameSize", this.frameSize);
        this.statusFlags.createStructure();
        this.encodingFlags.createStructure();
        this.frameBody.createStructure();
        MP3File.getStructureFormatter().closeHeadingElement("frame");
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isCommon() {
        return ID3v23Frames.getInstanceOf().isCommon(getId());
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return ID3v23Frames.getInstanceOf().isBinary(getId());
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setEncoding(Charset charset) {
        Integer idForCharset = TextEncoding.getInstanceOf().getIdForCharset(charset);
        if (idForCharset == null || idForCharset.intValue() >= 2) {
            return;
        }
        getBody().setTextEncoding(idForCharset.byteValue());
    }
}
