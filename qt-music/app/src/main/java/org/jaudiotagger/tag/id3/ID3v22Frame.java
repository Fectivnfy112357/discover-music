package org.jaudiotagger.tag.id3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.EmptyFrameException;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.InvalidFrameIdentifierException;
import org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody;
import org.jaudiotagger.tag.id3.framebody.FrameBodyDeprecated;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.utils.EqualsUtil;

/* loaded from: classes3.dex */
public class ID3v22Frame extends AbstractID3v2Frame {
    protected static final int FRAME_HEADER_SIZE = 6;
    protected static final int FRAME_ID_SIZE = 3;
    protected static final int FRAME_SIZE_SIZE = 3;
    private static Pattern validFrameIdentifier = Pattern.compile("[A-Z][0-9A-Z]{2}");

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected int getFrameHeaderSize() {
        return 6;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected int getFrameIdSize() {
        return 3;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected int getFrameSizeSize() {
        return 3;
    }

    public ID3v22Frame() {
    }

    public ID3v22Frame(AbstractID3v2FrameBody abstractID3v2FrameBody) {
        super(abstractID3v2FrameBody);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame, org.jaudiotagger.tag.id3.AbstractTagFrame, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ID3v22Frame)) {
            return false;
        }
        ID3v22Frame iD3v22Frame = (ID3v22Frame) obj;
        return EqualsUtil.areEqual(this.statusFlags, iD3v22Frame.statusFlags) && EqualsUtil.areEqual(this.encodingFlags, iD3v22Frame.encodingFlags) && super.equals(iD3v22Frame);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ID3v22Frame(java.lang.String r6) {
        /*
            r5 = this;
            java.lang.String r0 = "org.jaudiotagger.tag.id3.framebody.FrameBody"
            r5.<init>()
            java.util.logging.Logger r1 = org.jaudiotagger.tag.id3.ID3v22Frame.logger
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Creating empty frame of type"
            r2.<init>(r3)
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r2 = r2.toString()
            r1.config(r2)
            r5.identifier = r6
            boolean r1 = org.jaudiotagger.tag.id3.ID3Tags.isID3v22FrameIdentifier(r6)
            if (r1 == 0) goto L50
            java.lang.String r1 = org.jaudiotagger.tag.id3.ID3Tags.forceFrameID22To23(r6)
            if (r1 == 0) goto L28
            goto L50
        L28:
            java.lang.String r1 = "CRM"
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L31
            goto L50
        L31:
            java.lang.String r1 = "TYE"
            boolean r1 = r6.equals(r1)
            if (r1 != 0) goto L4d
            java.lang.String r1 = "TIM"
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L42
            goto L4d
        L42:
            boolean r1 = org.jaudiotagger.tag.id3.ID3Tags.isID3v22FrameIdentifier(r6)
            if (r1 == 0) goto L50
            java.lang.String r1 = org.jaudiotagger.tag.id3.ID3Tags.convertFrameID22To23(r6)
            goto L51
        L4d:
            java.lang.String r1 = "TDRC"
            goto L51
        L50:
            r1 = r6
        L51:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            r2.<init>(r0)     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            java.lang.StringBuilder r0 = r2.append(r1)     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            java.lang.String r0 = r0.toString()     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            java.lang.Object r0 = r0.newInstance()     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            org.jaudiotagger.tag.id3.AbstractTagFrameBody r0 = (org.jaudiotagger.tag.id3.AbstractTagFrameBody) r0     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            r5.frameBody = r0     // Catch: java.lang.IllegalAccessException -> L6b java.lang.InstantiationException -> L7d java.lang.ClassNotFoundException -> L8f
            goto La2
        L6b:
            r6 = move-exception
            java.util.logging.Logger r0 = org.jaudiotagger.tag.id3.ID3v22Frame.logger
            java.util.logging.Level r1 = java.util.logging.Level.SEVERE
            java.lang.String r2 = r6.getMessage()
            r0.log(r1, r2, r6)
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r6)
            throw r0
        L7d:
            r6 = move-exception
            java.util.logging.Logger r0 = org.jaudiotagger.tag.id3.ID3v22Frame.logger
            java.util.logging.Level r1 = java.util.logging.Level.SEVERE
            java.lang.String r2 = r6.getMessage()
            r0.log(r1, r2, r6)
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r6)
            throw r0
        L8f:
            r0 = move-exception
            java.util.logging.Logger r2 = org.jaudiotagger.tag.id3.ID3v22Frame.logger
            java.util.logging.Level r3 = java.util.logging.Level.SEVERE
            java.lang.String r4 = r0.getMessage()
            r2.log(r3, r4, r0)
            org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported r0 = new org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported
            r0.<init>(r6)
            r5.frameBody = r0
        La2:
            org.jaudiotagger.tag.id3.AbstractTagFrameBody r6 = r5.frameBody
            r6.setHeader(r5)
            java.util.logging.Logger r6 = org.jaudiotagger.tag.id3.ID3v22Frame.logger
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Created empty frame of type"
            r0.<init>(r2)
            java.lang.String r2 = r5.identifier
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.String r2 = "with frame body of"
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r6.config(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.tag.id3.ID3v22Frame.<init>(java.lang.String):void");
    }

    public ID3v22Frame(ID3v22Frame iD3v22Frame) {
        super(iD3v22Frame);
        logger.config("Creating frame from a frame of same version");
    }

    private void createV22FrameFromV23Frame(ID3v23Frame iD3v23Frame) throws InvalidFrameException {
        this.identifier = ID3Tags.convertFrameID23To22(iD3v23Frame.getIdentifier());
        if (this.identifier != null) {
            logger.config("V2:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
            this.frameBody = (AbstractID3v2FrameBody) ID3Tags.copyObject(iD3v23Frame.getBody());
            return;
        }
        if (ID3Tags.isID3v23FrameIdentifier(iD3v23Frame.getIdentifier())) {
            this.identifier = ID3Tags.forceFrameID23To22(iD3v23Frame.getIdentifier());
            if (this.identifier != null) {
                logger.config("V2:Force:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
                this.frameBody = readBody(this.identifier, (AbstractID3v2FrameBody) iD3v23Frame.getBody());
                return;
            }
            throw new InvalidFrameException("Unable to convert v23 frame:" + iD3v23Frame.getIdentifier() + " to a v22 frame");
        }
        if (iD3v23Frame.getBody() instanceof FrameBodyDeprecated) {
            if (ID3Tags.isID3v22FrameIdentifier(iD3v23Frame.getIdentifier())) {
                this.frameBody = iD3v23Frame.getBody();
                this.identifier = iD3v23Frame.getIdentifier();
                logger.config("DEPRECATED:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
                return;
            } else {
                this.frameBody = new FrameBodyDeprecated((FrameBodyDeprecated) iD3v23Frame.getBody());
                this.identifier = iD3v23Frame.getIdentifier();
                logger.config("DEPRECATED:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
                return;
            }
        }
        this.frameBody = new FrameBodyUnsupported((FrameBodyUnsupported) iD3v23Frame.getBody());
        this.identifier = iD3v23Frame.getIdentifier();
        logger.config("v2:UNKNOWN:Orig id is:" + iD3v23Frame.getIdentifier() + ":New id is:" + this.identifier);
    }

    public ID3v22Frame(AbstractID3v2Frame abstractID3v2Frame) throws InvalidFrameException {
        logger.config("Creating frame from a frame of a different version");
        if (abstractID3v2Frame instanceof ID3v22Frame) {
            throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
        }
        if (abstractID3v2Frame instanceof ID3v24Frame) {
            createV22FrameFromV23Frame(new ID3v23Frame(abstractID3v2Frame));
        } else if (abstractID3v2Frame instanceof ID3v23Frame) {
            createV22FrameFromV23Frame((ID3v23Frame) abstractID3v2Frame);
        }
        this.frameBody.setHeader(this);
        logger.config("Created frame from a frame of a different version");
    }

    public ID3v22Frame(ByteBuffer byteBuffer, String str) throws InvalidFrameException, InvalidDataTypeException {
        setLoggingFilename(str);
        read(byteBuffer);
    }

    public ID3v22Frame(ByteBuffer byteBuffer) throws InvalidFrameException, InvalidDataTypeException {
        this(byteBuffer, "");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return this.frameBody.getSize() + getFrameHeaderSize();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    protected boolean isPadding(byte[] bArr) {
        return bArr[0] == 0 && bArr[1] == 0 && bArr[2] == 0;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws InvalidFrameException, InvalidDataTypeException {
        String identifier = readIdentifier(byteBuffer);
        byte[] bArr = new byte[getFrameSizeSize()];
        if (!isValidID3v2FrameIdentifier(identifier)) {
            logger.config("Invalid identifier:" + identifier);
            byteBuffer.position(byteBuffer.position() - (getFrameIdSize() - 1));
            throw new InvalidFrameIdentifierException(getLoggingFilename() + ":" + identifier + ":is not a valid ID3v2.20 frame");
        }
        byteBuffer.get(bArr, 0, getFrameSizeSize());
        this.frameSize = decodeSize(bArr);
        if (this.frameSize < 0) {
            throw new InvalidFrameException(identifier + " has invalid size of:" + this.frameSize);
        }
        if (this.frameSize == 0) {
            logger.warning("Empty Frame:" + identifier);
            throw new EmptyFrameException(identifier + " is empty frame");
        }
        if (this.frameSize > byteBuffer.remaining()) {
            logger.warning("Invalid Frame size larger than size before mp3 audio:" + identifier);
            throw new InvalidFrameException(identifier + " is invalid frame");
        }
        logger.fine("Frame Size Is:" + this.frameSize);
        String strConvertFrameID22To24 = ID3Tags.convertFrameID22To24(identifier);
        if (strConvertFrameID22To24 == null && (strConvertFrameID22To24 = ID3Tags.convertFrameID22To23(identifier)) == null) {
            strConvertFrameID22To24 = ID3Tags.isID3v22FrameIdentifier(identifier) ? identifier : "Unsupported";
        }
        logger.fine("Identifier was:" + identifier + " reading using:" + strConvertFrameID22To24);
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBufferSlice.limit(this.frameSize);
        try {
            this.frameBody = readBody(strConvertFrameID22To24, byteBufferSlice, this.frameSize);
        } finally {
            byteBuffer.position(byteBuffer.position() + this.frameSize);
        }
    }

    private int decodeSize(byte[] bArr) {
        int iIntValue = new BigInteger(bArr).intValue();
        if (iIntValue < 0) {
            logger.warning("Invalid Frame Size of:" + iIntValue + "Decoded from bin:" + Integer.toBinaryString(iIntValue) + "Decoded from hex:" + Integer.toHexString(iIntValue));
        }
        return iIntValue;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public void write(ByteArrayOutputStream byteArrayOutputStream) {
        logger.config("Write Frame to Buffer" + getIdentifier());
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(getFrameHeaderSize());
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        ((AbstractID3v2FrameBody) this.frameBody).write(byteArrayOutputStream2);
        byteBufferAllocate.put(getIdentifier().getBytes(StandardCharsets.ISO_8859_1), 0, getFrameIdSize());
        encodeSize(byteBufferAllocate, this.frameBody.getSize());
        try {
            byteArrayOutputStream.write(byteBufferAllocate.array());
            byteArrayOutputStream.write(byteArrayOutputStream2.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void encodeSize(ByteBuffer byteBuffer, int i) {
        byteBuffer.put((byte) ((16711680 & i) >> 16));
        byteBuffer.put((byte) ((65280 & i) >> 8));
        byteBuffer.put((byte) (i & 255));
        logger.fine("Frame Size Is Actual:" + i + ":Encoded bin:" + Integer.toBinaryString(i) + ":Encoded Hex" + Integer.toHexString(i));
    }

    public boolean isValidID3v2FrameIdentifier(String str) {
        return validFrameIdentifier.matcher(str).matches();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Frame
    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement("frame", getIdentifier());
        MP3File.getStructureFormatter().addElement("frameSize", this.frameSize);
        this.frameBody.createStructure();
        MP3File.getStructureFormatter().closeHeadingElement("frame");
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isCommon() {
        return ID3v22Frames.getInstanceOf().isCommon(getId());
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isBinary() {
        return ID3v22Frames.getInstanceOf().isBinary(getId());
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
