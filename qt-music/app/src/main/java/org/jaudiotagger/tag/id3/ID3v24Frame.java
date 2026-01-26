package org.jaudiotagger.tag.id3;

import com.google.common.primitives.SignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.EmptyFrameException;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.InvalidFrameIdentifierException;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.Lyrics3Line;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.ID3v23Frame;
import org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyDeprecated;
import org.jaudiotagger.tag.id3.framebody.FrameBodySYLT;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTALB;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTCOM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTIT2;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTMOO;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTPE1;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUSLT;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported;
import org.jaudiotagger.tag.id3.framebody.ID3v24FrameBody;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.tag.lyrics3.FieldFrameBodyAUT;
import org.jaudiotagger.tag.lyrics3.FieldFrameBodyEAL;
import org.jaudiotagger.tag.lyrics3.FieldFrameBodyEAR;
import org.jaudiotagger.tag.lyrics3.FieldFrameBodyETT;
import org.jaudiotagger.tag.lyrics3.FieldFrameBodyINF;
import org.jaudiotagger.tag.lyrics3.FieldFrameBodyLYR;
import org.jaudiotagger.tag.lyrics3.Lyrics3v2Field;
import org.jaudiotagger.tag.lyrics3.Lyrics3v2Fields;
import org.jaudiotagger.utils.EqualsUtil;

/* loaded from: classes3.dex */
public class ID3v24Frame extends AbstractID3v2Frame {
    protected static final int FRAME_DATA_LENGTH_SIZE = 4;
    protected static final int FRAME_ENCRYPTION_INDICATOR_SIZE = 1;
    protected static final int FRAME_FLAGS_SIZE = 2;
    protected static final int FRAME_GROUPING_INDICATOR_SIZE = 1;
    protected static final int FRAME_HEADER_SIZE = 10;
    protected static final int FRAME_ID_SIZE = 4;
    protected static final int FRAME_SIZE_SIZE = 4;
    private static Pattern validFrameIdentifier = Pattern.compile("[A-Z][0-9A-Z]{3}");
    private int encryptionMethod;
    private int groupIdentifier;

    protected int getFrameFlagsSize() {
        return 2;
    }

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

    public ID3v24Frame() {
    }

    public ID3v24Frame(String str) {
        super(str);
        this.statusFlags = new StatusFlags();
        this.encodingFlags = new EncodingFlags();
    }

    public ID3v24Frame(ID3v24Frame iD3v24Frame) {
        super(iD3v24Frame);
        this.statusFlags = new StatusFlags(iD3v24Frame.getStatusFlags().getOriginalFlags());
        this.encodingFlags = new EncodingFlags(iD3v24Frame.getEncodingFlags().getFlags());
    }

    private void createV24FrameFromV23Frame(ID3v23Frame iD3v23Frame) throws InvalidFrameException {
        this.identifier = ID3Tags.convertFrameID23To24(iD3v23Frame.getIdentifier());
        logger.finer("Creating V24frame from v23:" + iD3v23Frame.getIdentifier() + ":" + this.identifier);
        if (iD3v23Frame.getBody() instanceof FrameBodyUnsupported) {
            this.frameBody = new FrameBodyUnsupported((FrameBodyUnsupported) iD3v23Frame.getBody());
            this.frameBody.setHeader(this);
            this.identifier = iD3v23Frame.getIdentifier();
            logger.finer("V3:UnsupportedBody:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
            return;
        }
        if (this.identifier != null) {
            if (iD3v23Frame.getIdentifier().equals("TXXX") && ((FrameBodyTXXX) iD3v23Frame.getBody()).getDescription().equals(FrameBodyTXXX.MOOD)) {
                this.frameBody = new FrameBodyTMOO((FrameBodyTXXX) iD3v23Frame.getBody());
                this.frameBody.setHeader(this);
                this.identifier = this.frameBody.getIdentifier();
                return;
            } else {
                logger.finer("V3:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
                this.frameBody = (AbstractTagFrameBody) ID3Tags.copyObject(iD3v23Frame.getBody());
                this.frameBody.setHeader(this);
                return;
            }
        }
        if (ID3Tags.isID3v23FrameIdentifier(iD3v23Frame.getIdentifier())) {
            this.identifier = ID3Tags.forceFrameID23To24(iD3v23Frame.getIdentifier());
            if (this.identifier != null) {
                logger.config("V3:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
                this.frameBody = readBody(this.identifier, (AbstractID3v2FrameBody) iD3v23Frame.getBody());
                this.frameBody.setHeader(this);
                return;
            } else {
                this.frameBody = new FrameBodyDeprecated((AbstractID3v2FrameBody) iD3v23Frame.getBody());
                this.frameBody.setHeader(this);
                this.identifier = iD3v23Frame.getIdentifier();
                logger.finer("V3:Deprecated:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
                return;
            }
        }
        if (iD3v23Frame.getBody() instanceof FrameBodyUnsupported) {
            this.frameBody = new FrameBodyUnsupported((FrameBodyUnsupported) iD3v23Frame.getBody());
            this.frameBody.setHeader(this);
            this.identifier = iD3v23Frame.getIdentifier();
            logger.finer("V3:Unknown:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
            return;
        }
        if (iD3v23Frame.getBody() instanceof FrameBodyDeprecated) {
            this.frameBody = new FrameBodyDeprecated((FrameBodyDeprecated) iD3v23Frame.getBody());
            this.frameBody.setHeader(this);
            this.identifier = iD3v23Frame.getIdentifier();
            logger.finer("V3:Deprecated:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
        }
    }

    protected ID3v24Frame(ID3v23Frame iD3v23Frame, String str) throws InvalidFrameException {
        this.identifier = str;
        this.statusFlags = new StatusFlags((ID3v23Frame.StatusFlags) iD3v23Frame.getStatusFlags());
        this.encodingFlags = new EncodingFlags(iD3v23Frame.getEncodingFlags().getFlags());
    }

    public ID3v24Frame(AbstractID3v2Frame abstractID3v2Frame) throws InvalidFrameException {
        if (abstractID3v2Frame instanceof ID3v24Frame) {
            throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
        }
        boolean z = abstractID3v2Frame instanceof ID3v23Frame;
        if (z) {
            this.statusFlags = new StatusFlags((ID3v23Frame.StatusFlags) abstractID3v2Frame.getStatusFlags());
            this.encodingFlags = new EncodingFlags(abstractID3v2Frame.getEncodingFlags().getFlags());
        } else if (abstractID3v2Frame instanceof ID3v22Frame) {
            this.statusFlags = new StatusFlags();
            this.encodingFlags = new EncodingFlags();
        }
        if (z) {
            createV24FrameFromV23Frame((ID3v23Frame) abstractID3v2Frame);
        } else if (abstractID3v2Frame instanceof ID3v22Frame) {
            createV24FrameFromV23Frame(new ID3v23Frame(abstractID3v2Frame));
        }
        this.frameBody.setHeader(this);
    }

    public ID3v24Frame(Lyrics3v2Field lyrics3v2Field) throws InvalidTagException {
        String identifier = lyrics3v2Field.getIdentifier();
        if (identifier.equals(Lyrics3v2Fields.FIELD_V2_INDICATIONS)) {
            throw new InvalidTagException("Cannot create ID3v2.40 frame from Lyrics3 indications field.");
        }
        if (identifier.equals(Lyrics3v2Fields.FIELD_V2_LYRICS_MULTI_LINE_TEXT)) {
            FieldFrameBodyLYR fieldFrameBodyLYR = (FieldFrameBodyLYR) lyrics3v2Field.getBody();
            Iterator<Lyrics3Line> it = fieldFrameBodyLYR.iterator();
            boolean zHasTimeStamp = fieldFrameBodyLYR.hasTimeStamp();
            FrameBodySYLT frameBodySYLT = new FrameBodySYLT(0, "ENG", 2, 1, "", new byte[0]);
            FrameBodyUSLT frameBodyUSLT = new FrameBodyUSLT((byte) 0, "ENG", "", "");
            while (it.hasNext()) {
                Lyrics3Line next = it.next();
                if (!zHasTimeStamp) {
                    frameBodyUSLT.addLyric(next);
                }
            }
            if (zHasTimeStamp) {
                this.frameBody = frameBodySYLT;
                this.frameBody.setHeader(this);
                return;
            } else {
                this.frameBody = frameBodyUSLT;
                this.frameBody.setHeader(this);
                return;
            }
        }
        if (identifier.equals(Lyrics3v2Fields.FIELD_V2_ADDITIONAL_MULTI_LINE_TEXT)) {
            this.frameBody = new FrameBodyCOMM((byte) 0, "ENG", "", ((FieldFrameBodyINF) lyrics3v2Field.getBody()).getAdditionalInformation());
            this.frameBody.setHeader(this);
            return;
        }
        if (identifier.equals(Lyrics3v2Fields.FIELD_V2_AUTHOR)) {
            this.frameBody = new FrameBodyTCOM((byte) 0, ((FieldFrameBodyAUT) lyrics3v2Field.getBody()).getAuthor());
            this.frameBody.setHeader(this);
            return;
        }
        if (identifier.equals(Lyrics3v2Fields.FIELD_V2_ALBUM)) {
            this.frameBody = new FrameBodyTALB((byte) 0, ((FieldFrameBodyEAL) lyrics3v2Field.getBody()).getAlbum());
            this.frameBody.setHeader(this);
        } else if (identifier.equals(Lyrics3v2Fields.FIELD_V2_ARTIST)) {
            this.frameBody = new FrameBodyTPE1((byte) 0, ((FieldFrameBodyEAR) lyrics3v2Field.getBody()).getArtist());
            this.frameBody.setHeader(this);
        } else if (identifier.equals(Lyrics3v2Fields.FIELD_V2_TRACK)) {
            this.frameBody = new FrameBodyTIT2((byte) 0, ((FieldFrameBodyETT) lyrics3v2Field.getBody()).getTitle());
            this.frameBody.setHeader(this);
        } else {
            if (identifier.equals(Lyrics3v2Fields.FIELD_V2_IMAGE)) {
                throw new InvalidTagException("Cannot create ID3v2.40 frame from Lyrics3 image field.");
            }
            throw new InvalidTagException("Cannot caret ID3v2.40 frame from " + identifier + " Lyrics3 field");
        }
    }

    public ID3v24Frame(ByteBuffer byteBuffer, String str) throws InvalidFrameException, InvalidDataTypeException {
        setLoggingFilename(str);
        read(byteBuffer);
    }

    public ID3v24Frame(ByteBuffer byteBuffer) throws InvalidFrameException, InvalidDataTypeException {
        this(byteBuffer, "");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame, org.jaudiotagger.tag.id3.AbstractTagFrame, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ID3v24Frame)) {
            return false;
        }
        ID3v24Frame iD3v24Frame = (ID3v24Frame) obj;
        return EqualsUtil.areEqual(this.statusFlags, iD3v24Frame.statusFlags) && EqualsUtil.areEqual(this.encodingFlags, iD3v24Frame.encodingFlags) && super.equals(iD3v24Frame);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return this.frameBody.getSize() + 10;
    }

    private void checkIfFrameSizeThatIsNotSyncSafe(ByteBuffer byteBuffer) throws InvalidFrameException {
        if (this.frameSize > 127) {
            int iPosition = byteBuffer.position();
            byteBuffer.position(iPosition - getFrameIdSize());
            int i = byteBuffer.getInt();
            byteBuffer.position(iPosition - getFrameIdSize());
            boolean zIsBufferNotSyncSafe = ID3SyncSafeInteger.isBufferNotSyncSafe(byteBuffer);
            byteBuffer.position(iPosition);
            if (zIsBufferNotSyncSafe) {
                logger.warning(getLoggingFilename() + ":Frame size is NOT stored as a sync safe integer:" + this.identifier);
                if (i > byteBuffer.remaining() - (-getFrameFlagsSize())) {
                    logger.warning(getLoggingFilename() + ":Invalid Frame size larger than size before mp3 audio:" + this.identifier);
                    throw new InvalidFrameException(this.identifier + " is invalid frame");
                }
                this.frameSize = i;
                return;
            }
            byte[] bArr = new byte[getFrameIdSize()];
            byteBuffer.position(this.frameSize + iPosition + getFrameFlagsSize());
            if (byteBuffer.remaining() < getFrameIdSize()) {
                byteBuffer.position(iPosition);
                return;
            }
            byteBuffer.get(bArr, 0, getFrameIdSize());
            byteBuffer.position(iPosition);
            if (isValidID3v2FrameIdentifier(new String(bArr)) || ID3SyncSafeInteger.isBufferEmpty(bArr)) {
                return;
            }
            if (i > byteBuffer.remaining() - getFrameFlagsSize()) {
                byteBuffer.position(iPosition);
                return;
            }
            byte[] bArr2 = new byte[getFrameIdSize()];
            byteBuffer.position(iPosition + i + getFrameFlagsSize());
            if (byteBuffer.remaining() >= getFrameIdSize()) {
                byteBuffer.get(bArr2, 0, getFrameIdSize());
                String str = new String(bArr2);
                byteBuffer.position(iPosition);
                if (isValidID3v2FrameIdentifier(str)) {
                    this.frameSize = i;
                    logger.warning(getLoggingFilename() + ":Assuming frame size is NOT stored as a sync safe integer:" + this.identifier);
                    return;
                } else {
                    if (ID3SyncSafeInteger.isBufferEmpty(bArr2)) {
                        this.frameSize = i;
                        logger.warning(getLoggingFilename() + ":Assuming frame size is NOT stored as a sync safe integer:" + this.identifier);
                        return;
                    }
                    return;
                }
            }
            byteBuffer.position(iPosition);
            if (byteBuffer.remaining() == 0) {
                this.frameSize = i;
            }
        }
    }

    private void getFrameSize(ByteBuffer byteBuffer) throws InvalidFrameException {
        this.frameSize = ID3SyncSafeInteger.bufferToValue(byteBuffer);
        if (this.frameSize < 0) {
            logger.warning(getLoggingFilename() + ":Invalid Frame size:" + this.identifier);
            throw new InvalidFrameException(this.identifier + " is invalid frame");
        }
        if (this.frameSize == 0) {
            logger.warning(getLoggingFilename() + ":Empty Frame:" + this.identifier);
            byteBuffer.get();
            byteBuffer.get();
            throw new EmptyFrameException(this.identifier + " is empty frame");
        }
        if (this.frameSize > byteBuffer.remaining() - 2) {
            logger.warning(getLoggingFilename() + ":Invalid Frame size larger than size before mp3 audio:" + this.identifier);
            throw new InvalidFrameException(this.identifier + " is invalid frame");
        }
        checkIfFrameSizeThatIsNotSyncSafe(byteBuffer);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidFrameException, InvalidDataTypeException {
        int iBufferToValue;
        int iLimit;
        String identifier = readIdentifier(byteBuffer);
        int i = 1;
        if (!isValidID3v2FrameIdentifier(identifier)) {
            logger.config(getLoggingFilename() + ":Invalid identifier:" + identifier);
            byteBuffer.position(byteBuffer.position() - (getFrameIdSize() - 1));
            throw new InvalidFrameIdentifierException(getLoggingFilename() + ":" + identifier + ":is not a valid ID3v2.30 frame");
        }
        getFrameSize(byteBuffer);
        this.statusFlags = new StatusFlags(byteBuffer.get());
        this.encodingFlags = new EncodingFlags(byteBuffer.get());
        if (((EncodingFlags) this.encodingFlags).isGrouping()) {
            this.groupIdentifier = byteBuffer.get();
        } else {
            i = 0;
        }
        if (((EncodingFlags) this.encodingFlags).isEncryption()) {
            i++;
            this.encryptionMethod = byteBuffer.get();
        }
        if (((EncodingFlags) this.encodingFlags).isDataLengthIndicator()) {
            iBufferToValue = ID3SyncSafeInteger.bufferToValue(byteBuffer);
            i += 4;
            logger.config(getLoggingFilename() + ":Frame Size Is:" + this.frameSize + " Data Length Size:" + iBufferToValue);
        } else {
            iBufferToValue = -1;
        }
        int i2 = this.frameSize - i;
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBufferSlice.limit(i2);
        if (((EncodingFlags) this.encodingFlags).isUnsynchronised()) {
            byteBufferSlice = ID3Unsynchronization.synchronize(byteBufferSlice);
            iLimit = byteBufferSlice.limit();
            logger.config(getLoggingFilename() + ":Frame Size After Syncing is:" + iLimit);
        } else {
            iLimit = i2;
        }
        try {
            if (((EncodingFlags) this.encodingFlags).isCompression()) {
                ByteBuffer byteBufferUncompress = ID3Compression.uncompress(identifier, getLoggingFilename(), byteBuffer, iBufferToValue, i2);
                if (((EncodingFlags) this.encodingFlags).isEncryption()) {
                    this.frameBody = readEncryptedBody(identifier, byteBufferUncompress, iBufferToValue);
                } else {
                    this.frameBody = readBody(identifier, byteBufferUncompress, iBufferToValue);
                }
            } else if (((EncodingFlags) this.encodingFlags).isEncryption()) {
                byteBuffer.slice().limit(i2);
                this.frameBody = readEncryptedBody(identifier, byteBuffer, this.frameSize);
            } else {
                this.frameBody = readBody(identifier, byteBufferSlice, iLimit);
            }
            if (!(this.frameBody instanceof ID3v24FrameBody)) {
                logger.config(getLoggingFilename() + ":Converted frame body with:" + identifier + " to deprecated framebody");
                this.frameBody = new FrameBodyDeprecated((AbstractID3v2FrameBody) this.frameBody);
            }
        } finally {
            byteBuffer.position(byteBuffer.position() + i2);
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        logger.config("Writing frame to file:" + getIdentifier());
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(10);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ((AbstractID3v2FrameBody) this.frameBody).write(byteArrayOutputStream2);
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        boolean z = TagOptionSingleton.getInstance().isUnsyncTags() && ID3Unsynchronization.requiresUnsynchronization(byteArray);
        if (z) {
            byteArray = ID3Unsynchronization.unsynchronize(byteArray);
            logger.config("bodybytebuffer:sizeafterunsynchronisation:" + byteArray.length);
        }
        if (getIdentifier().length() == 3) {
            this.identifier += ' ';
        }
        byteBufferAllocate.put(getIdentifier().getBytes(StandardCharsets.ISO_8859_1), 0, 4);
        int length = byteArray.length;
        logger.fine("Frame Size Is:" + length);
        byteBufferAllocate.put(ID3SyncSafeInteger.valueToBuffer(length));
        byteBufferAllocate.put(this.statusFlags.getWriteFlags());
        ((EncodingFlags) this.encodingFlags).unsetNonStandardFlags();
        if (z) {
            ((EncodingFlags) this.encodingFlags).setUnsynchronised();
        } else {
            ((EncodingFlags) this.encodingFlags).unsetUnsynchronised();
        }
        ((EncodingFlags) this.encodingFlags).unsetCompression();
        ((EncodingFlags) this.encodingFlags).unsetDataLengthIndicator();
        byteBufferAllocate.put(this.encodingFlags.getFlags());
        try {
            byteArrayOutputStream.write(byteBufferAllocate.array());
            if (((EncodingFlags) this.encodingFlags).isEncryption()) {
                byteArrayOutputStream.write(this.encryptionMethod);
            }
            if (((EncodingFlags) this.encodingFlags).isGrouping()) {
                byteArrayOutputStream.write(this.groupIdentifier);
            }
            byteArrayOutputStream.write(byteArray);
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

    public class StatusFlags extends AbstractID3v2Frame.StatusFlags {
        public static final int MASK_FILE_ALTER_PRESERVATION = 32;
        public static final int MASK_READ_ONLY = 16;
        public static final int MASK_TAG_ALTER_PRESERVATION = 64;
        public static final String TYPE_FILEALTERPRESERVATION = "typeFileAlterPreservation";
        public static final String TYPE_READONLY = "typeReadOnly";
        public static final String TYPE_TAGALTERPRESERVATION = "typeTagAlterPreservation";

        private byte convertV3ToV4Flags(byte b) {
            byte b2 = (b & SignedBytes.MAX_POWER_OF_TWO) != 0 ? (byte) 32 : (byte) 0;
            return (b & 128) != 0 ? (byte) (b2 | SignedBytes.MAX_POWER_OF_TWO) : b2;
        }

        StatusFlags() {
            super();
        }

        StatusFlags(byte b) {
            super();
            this.originalFlags = b;
            this.writeFlags = b;
            modifyFlags();
        }

        StatusFlags(ID3v23Frame.StatusFlags statusFlags) {
            super();
            this.originalFlags = convertV3ToV4Flags(statusFlags.getOriginalFlags());
            this.writeFlags = this.originalFlags;
            modifyFlags();
        }

        protected void modifyFlags() {
            if (ID3v24Frames.getInstanceOf().isDiscardIfFileAltered(ID3v24Frame.this.getIdentifier())) {
                this.writeFlags = (byte) (this.writeFlags | 32);
                this.writeFlags = (byte) (this.writeFlags & (-65));
            } else {
                this.writeFlags = (byte) (this.writeFlags & (-33));
                this.writeFlags = (byte) (this.writeFlags & (-65));
            }
        }

        @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame.StatusFlags
        public void createStructure() {
            MP3File.getStructureFormatter().openHeadingElement("statusFlags", "");
            MP3File.getStructureFormatter().addElement("typeTagAlterPreservation", this.originalFlags & SignedBytes.MAX_POWER_OF_TWO);
            MP3File.getStructureFormatter().addElement("typeFileAlterPreservation", this.originalFlags & 32);
            MP3File.getStructureFormatter().addElement("typeReadOnly", this.originalFlags & 16);
            MP3File.getStructureFormatter().closeHeadingElement("statusFlags");
        }
    }

    class EncodingFlags extends AbstractID3v2Frame.EncodingFlags {
        public static final int MASK_COMPRESSION = 8;
        public static final int MASK_DATA_LENGTH_INDICATOR = 1;
        public static final int MASK_ENCRYPTION = 4;
        public static final int MASK_FRAME_UNSYNCHRONIZATION = 2;
        public static final int MASK_GROUPING_IDENTITY = 64;
        public static final String TYPE_COMPRESSION = "compression";
        public static final String TYPE_DATALENGTHINDICATOR = "dataLengthIndicator";
        public static final String TYPE_ENCRYPTION = "encryption";
        public static final String TYPE_FRAMEUNSYNCHRONIZATION = "frameUnsynchronisation";
        public static final String TYPE_GROUPIDENTITY = "groupidentity";

        EncodingFlags() {
            super();
        }

        EncodingFlags(byte b) {
            super(b);
            logEnabledFlags();
        }

        public void logEnabledFlags() {
            if (isNonStandardFlags()) {
                AbstractTagItem.logger.warning(ID3v24Frame.this.getLoggingFilename() + ":" + ID3v24Frame.this.identifier + ":Unknown Encoding Flags:" + Hex.asHex(this.flags));
            }
            if (isCompression()) {
                AbstractTagItem.logger.warning(ErrorMessage.MP3_FRAME_IS_COMPRESSED.getMsg(ID3v24Frame.this.getLoggingFilename(), ID3v24Frame.this.identifier));
            }
            if (isEncryption()) {
                AbstractTagItem.logger.warning(ErrorMessage.MP3_FRAME_IS_ENCRYPTED.getMsg(ID3v24Frame.this.getLoggingFilename(), ID3v24Frame.this.identifier));
            }
            if (isGrouping()) {
                AbstractTagItem.logger.config(ErrorMessage.MP3_FRAME_IS_GROUPED.getMsg(ID3v24Frame.this.getLoggingFilename(), ID3v24Frame.this.identifier));
            }
            if (isUnsynchronised()) {
                AbstractTagItem.logger.config(ErrorMessage.MP3_FRAME_IS_UNSYNCHRONISED.getMsg(ID3v24Frame.this.getLoggingFilename(), ID3v24Frame.this.identifier));
            }
            if (isDataLengthIndicator()) {
                AbstractTagItem.logger.config(ErrorMessage.MP3_FRAME_IS_DATA_LENGTH_INDICATOR.getMsg(ID3v24Frame.this.getLoggingFilename(), ID3v24Frame.this.identifier));
            }
        }

        @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame.EncodingFlags
        public byte getFlags() {
            return this.flags;
        }

        public boolean isCompression() {
            return (this.flags & 8) > 0;
        }

        public boolean isEncryption() {
            return (this.flags & 4) > 0;
        }

        public boolean isGrouping() {
            return (this.flags & SignedBytes.MAX_POWER_OF_TWO) > 0;
        }

        public boolean isUnsynchronised() {
            return (this.flags & 2) > 0;
        }

        public boolean isDataLengthIndicator() {
            return (this.flags & 1) > 0;
        }

        public void setCompression() {
            this.flags = (byte) (this.flags | 8);
        }

        public void setEncryption() {
            this.flags = (byte) (this.flags | 4);
        }

        public void setGrouping() {
            this.flags = (byte) (this.flags | SignedBytes.MAX_POWER_OF_TWO);
        }

        public void setUnsynchronised() {
            this.flags = (byte) (this.flags | 2);
        }

        public void setDataLengthIndicator() {
            this.flags = (byte) (this.flags | 1);
        }

        public void unsetCompression() {
            this.flags = (byte) (this.flags & (-9));
        }

        public void unsetEncryption() {
            this.flags = (byte) (this.flags & (-5));
        }

        public void unsetGrouping() {
            this.flags = (byte) (this.flags & (-65));
        }

        public void unsetUnsynchronised() {
            this.flags = (byte) (this.flags & (-3));
        }

        public void unsetDataLengthIndicator() {
            this.flags = (byte) (this.flags & (-2));
        }

        public boolean isNonStandardFlags() {
            return (this.flags & 128) > 0 || (this.flags & 32) > 0 || (this.flags & 16) > 0;
        }

        public void unsetNonStandardFlags() {
            if (isNonStandardFlags()) {
                AbstractTagItem.logger.warning(ID3v24Frame.this.getLoggingFilename() + ":" + ID3v24Frame.this.getIdentifier() + ":Unsetting Unknown Encoding Flags:" + Hex.asHex(this.flags));
                this.flags = (byte) (this.flags & 127);
                this.flags = (byte) (this.flags & (-33));
                this.flags = (byte) (this.flags & (-17));
            }
        }

        @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame.EncodingFlags
        public void createStructure() {
            MP3File.getStructureFormatter().openHeadingElement("encodingFlags", "");
            MP3File.getStructureFormatter().addElement("compression", this.flags & 8);
            MP3File.getStructureFormatter().addElement("encryption", this.flags & 4);
            MP3File.getStructureFormatter().addElement("groupidentity", this.flags & SignedBytes.MAX_POWER_OF_TWO);
            MP3File.getStructureFormatter().addElement(TYPE_FRAMEUNSYNCHRONIZATION, this.flags & 2);
            MP3File.getStructureFormatter().addElement(TYPE_DATALENGTHINDICATOR, this.flags & 1);
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
        return ID3v24Frames.getInstanceOf().isCommon(getId());
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return ID3v24Frames.getInstanceOf().isBinary(getId());
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setEncoding(Charset charset) {
        Integer idForCharset = TextEncoding.getInstanceOf().getIdForCharset(charset);
        if (idForCharset == null || idForCharset.intValue() >= 4) {
            return;
        }
        getBody().setTextEncoding(idForCharset.byteValue());
    }
}
