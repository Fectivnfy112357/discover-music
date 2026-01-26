package org.jaudiotagger.tag.id3;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.logging.Level;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.InvalidTagException;
import org.jaudiotagger.tag.PaddingException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.TagTextField;
import org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody;
import org.jaudiotagger.tag.id3.framebody.FrameBodyEncrypted;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.utils.EqualsUtil;

/* loaded from: classes3.dex */
public abstract class AbstractID3v2Frame extends AbstractTagFrame implements TagTextField {
    protected static final String TYPE_FRAME = "frame";
    protected static final String TYPE_FRAME_SIZE = "frameSize";
    protected static final String UNSUPPORTED_ID = "Unsupported";
    EncodingFlags encodingFlags;
    protected int frameSize;
    protected String identifier;
    private String loggingFilename;
    StatusFlags statusFlags;

    @Override // org.jaudiotagger.tag.TagField
    public void copyContent(TagField tagField) {
    }

    protected abstract int getFrameHeaderSize();

    protected abstract int getFrameIdSize();

    protected abstract int getFrameSizeSize();

    @Override // org.jaudiotagger.tag.TagField
    public void isBinary(boolean z) {
    }

    public abstract void write(ByteArrayOutputStream byteArrayOutputStream);

    protected AbstractID3v2Frame() {
        this.identifier = "";
        this.loggingFilename = "";
        this.statusFlags = null;
        this.encodingFlags = null;
    }

    public AbstractID3v2Frame(AbstractID3v2Frame abstractID3v2Frame) {
        super(abstractID3v2Frame);
        this.identifier = "";
        this.loggingFilename = "";
        this.statusFlags = null;
        this.encodingFlags = null;
    }

    public AbstractID3v2Frame(AbstractID3v2FrameBody abstractID3v2FrameBody) {
        this.identifier = "";
        this.loggingFilename = "";
        this.statusFlags = null;
        this.encodingFlags = null;
        this.frameBody = abstractID3v2FrameBody;
        this.frameBody.setHeader(this);
    }

    public AbstractID3v2Frame(String str) {
        this.identifier = "";
        this.loggingFilename = "";
        this.statusFlags = null;
        this.encodingFlags = null;
        logger.config("Creating empty frame of type" + str);
        this.identifier = str;
        try {
            this.frameBody = (AbstractTagFrameBody) Class.forName("org.jaudiotagger.tag.id3.framebody.FrameBody" + str).newInstance();
        } catch (ClassNotFoundException e) {
            logger.severe(e.getMessage());
            this.frameBody = new FrameBodyUnsupported(str);
        } catch (IllegalAccessException e2) {
            logger.log(Level.SEVERE, "IllegalAccessException:" + str, (Throwable) e2);
            throw new RuntimeException(e2);
        } catch (InstantiationException e3) {
            logger.log(Level.SEVERE, "InstantiationException:" + str, (Throwable) e3);
            throw new RuntimeException(e3);
        }
        this.frameBody.setHeader(this);
        if (this instanceof ID3v24Frame) {
            this.frameBody.setTextEncoding(TagOptionSingleton.getInstance().getId3v24DefaultTextEncoding());
        } else if (this instanceof ID3v23Frame) {
            this.frameBody.setTextEncoding(TagOptionSingleton.getInstance().getId3v23DefaultTextEncoding());
        }
        logger.config("Created empty frame of type" + str);
    }

    protected String getLoggingFilename() {
        return this.loggingFilename;
    }

    protected void setLoggingFilename(String str) {
        this.loggingFilename = str;
    }

    @Override // org.jaudiotagger.tag.TagField
    public String getId() {
        return getIdentifier();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return this.identifier;
    }

    protected AbstractID3v2FrameBody readEncryptedBody(String str, ByteBuffer byteBuffer, int i) throws InvalidFrameException, InvalidDataTypeException {
        try {
            FrameBodyEncrypted frameBodyEncrypted = new FrameBodyEncrypted(str, byteBuffer, i);
            frameBodyEncrypted.setHeader(this);
            return frameBodyEncrypted;
        } catch (InvalidTagException e) {
            throw new InvalidDataTypeException(e);
        }
    }

    protected boolean isPadding(byte[] bArr) {
        return bArr[0] == 0 && bArr[1] == 0 && bArr[2] == 0 && bArr[3] == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:312:0x04cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody readBody(java.lang.String r6, java.nio.ByteBuffer r7, int r8) throws org.jaudiotagger.tag.InvalidFrameException, org.jaudiotagger.tag.InvalidDataTypeException, java.lang.ClassNotFoundException {
        /*
            Method dump skipped, instructions count: 2996
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.tag.id3.AbstractID3v2Frame.readBody(java.lang.String, java.nio.ByteBuffer, int):org.jaudiotagger.tag.id3.framebody.AbstractID3v2FrameBody");
    }

    protected String readIdentifier(ByteBuffer byteBuffer) throws InvalidFrameException {
        byte[] bArr = new byte[getFrameIdSize()];
        if (getFrameIdSize() <= byteBuffer.remaining()) {
            byteBuffer.get(bArr, 0, getFrameIdSize());
        }
        if (isPadding(bArr)) {
            throw new PaddingException(getLoggingFilename() + ":only padding found");
        }
        if (getFrameHeaderSize() - getFrameIdSize() > byteBuffer.remaining()) {
            logger.warning(getLoggingFilename() + ":No space to find another frame:");
            throw new InvalidFrameException(getLoggingFilename() + ":No space to find another frame");
        }
        this.identifier = new String(bArr);
        logger.fine(getLoggingFilename() + ":Identifier is" + this.identifier);
        return this.identifier;
    }

    protected AbstractID3v2FrameBody readBody(String str, AbstractID3v2FrameBody abstractID3v2FrameBody) throws InvalidFrameException {
        try {
            AbstractID3v2FrameBody abstractID3v2FrameBody2 = (AbstractID3v2FrameBody) Class.forName("org.jaudiotagger.tag.id3.framebody.FrameBody" + str).getConstructor(abstractID3v2FrameBody.getClass()).newInstance(abstractID3v2FrameBody);
            logger.finer("frame Body created" + abstractID3v2FrameBody2.getIdentifier());
            abstractID3v2FrameBody2.setHeader(this);
            return abstractID3v2FrameBody2;
        } catch (ClassNotFoundException unused) {
            logger.config("Identifier not recognised:" + str + " unable to create framebody");
            throw new InvalidFrameException("FrameBody" + str + " does not exist");
        } catch (IllegalAccessException e) {
            logger.log(Level.SEVERE, "Illegal access exception :" + e.getMessage(), (Throwable) e);
            throw new RuntimeException(e.getMessage());
        } catch (InstantiationException e2) {
            logger.log(Level.SEVERE, "Instantiation exception:" + e2.getMessage(), (Throwable) e2);
            throw new RuntimeException(e2.getMessage());
        } catch (NoSuchMethodException e3) {
            logger.log(Level.SEVERE, "No such method:" + e3.getMessage(), (Throwable) e3);
            throw new InvalidFrameException("FrameBody" + str + " does not have a constructor that takes:" + abstractID3v2FrameBody.getClass().getName());
        } catch (InvocationTargetException e4) {
            logger.severe("An error occurred within abstractID3v2FrameBody");
            logger.log(Level.SEVERE, "Invocation target exception:" + e4.getCause().getMessage(), e4.getCause());
            if (e4.getCause() instanceof Error) {
                throw ((Error) e4.getCause());
            }
            if (e4.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) e4.getCause());
            }
            throw new InvalidFrameException(e4.getCause().getMessage());
        }
    }

    @Override // org.jaudiotagger.tag.TagField
    public byte[] getRawContent() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        write(byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.jaudiotagger.tag.TagField
    public boolean isEmpty() {
        return getBody() == null;
    }

    public StatusFlags getStatusFlags() {
        return this.statusFlags;
    }

    public EncodingFlags getEncodingFlags() {
        return this.encodingFlags;
    }

    public class StatusFlags {
        protected static final String TYPE_FLAGS = "statusFlags";
        protected byte originalFlags;
        protected byte writeFlags;

        public void createStructure() {
        }

        protected StatusFlags() {
        }

        public byte getOriginalFlags() {
            return this.originalFlags;
        }

        public byte getWriteFlags() {
            return this.writeFlags;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof StatusFlags)) {
                return false;
            }
            StatusFlags statusFlags = (StatusFlags) obj;
            return EqualsUtil.areEqual((long) getOriginalFlags(), (long) statusFlags.getOriginalFlags()) && EqualsUtil.areEqual((long) getWriteFlags(), (long) statusFlags.getWriteFlags());
        }
    }

    class EncodingFlags {
        protected static final String TYPE_FLAGS = "encodingFlags";
        protected byte flags;

        public void createStructure() {
        }

        protected EncodingFlags() {
            resetFlags();
        }

        protected EncodingFlags(byte b) {
            setFlags(b);
        }

        public byte getFlags() {
            return this.flags;
        }

        public void setFlags(byte b) {
            this.flags = b;
        }

        public void resetFlags() {
            setFlags((byte) 0);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof EncodingFlags) {
                return EqualsUtil.areEqual(getFlags(), ((EncodingFlags) obj).getFlags());
            }
            return false;
        }
    }

    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement(TYPE_FRAME, getIdentifier());
        MP3File.getStructureFormatter().closeHeadingElement(TYPE_FRAME);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagFrame, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AbstractID3v2Frame) {
            return super.equals((AbstractID3v2Frame) obj);
        }
        return false;
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public String getContent() {
        return getBody().getUserFriendlyValue();
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public Charset getEncoding() {
        return TextEncoding.getInstanceOf().getCharsetForId(getBody().getTextEncoding());
    }

    @Override // org.jaudiotagger.tag.TagTextField
    public void setContent(String str) {
        throw new UnsupportedOperationException("Not implemented please use the generic tag methods for setting content");
    }
}
