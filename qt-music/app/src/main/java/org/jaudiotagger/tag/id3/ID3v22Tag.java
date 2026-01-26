package org.jaudiotagger.tag.id3;

import com.google.common.primitives.SignedBytes;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.EmptyFrameException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.InvalidDataTypeException;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.InvalidFrameIdentifierException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.PaddingException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagNotFoundException;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyTextInfo;
import org.jaudiotagger.tag.id3.framebody.FrameBodyPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTCON;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTDRC;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.tag.reference.PictureTypes;

/* loaded from: classes3.dex */
public class ID3v22Tag extends AbstractID3v2Tag {
    public static final byte MAJOR_VERSION = 2;
    public static final int MASK_V22_COMPRESSION = 64;
    public static final int MASK_V22_UNSYNCHRONIZATION = 128;
    public static final byte RELEASE = 2;
    public static final byte REVISION = 0;
    protected static final String TYPE_COMPRESSION = "compression";
    protected static final String TYPE_UNSYNCHRONISATION = "unsyncronisation";
    protected boolean compression;
    protected boolean unsynchronization;

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getMajorVersion() {
        return (byte) 2;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getRelease() {
        return (byte) 2;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag
    public byte getRevision() {
        return (byte) 0;
    }

    public ID3v22Tag() {
        this.compression = false;
        this.unsynchronization = false;
        this.frameMap = new LinkedHashMap();
        this.encryptedFrameMap = new LinkedHashMap();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    protected void copyPrimitives(AbstractID3v2Tag abstractID3v2Tag) {
        logger.config("Copying primitives");
        super.copyPrimitives(abstractID3v2Tag);
        if (abstractID3v2Tag instanceof ID3v22Tag) {
            ID3v22Tag iD3v22Tag = (ID3v22Tag) abstractID3v2Tag;
            this.compression = iD3v22Tag.compression;
            this.unsynchronization = iD3v22Tag.unsynchronization;
        } else if (abstractID3v2Tag instanceof ID3v23Tag) {
            ID3v23Tag iD3v23Tag = (ID3v23Tag) abstractID3v2Tag;
            this.compression = iD3v23Tag.compression;
            this.unsynchronization = iD3v23Tag.unsynchronization;
        } else if (abstractID3v2Tag instanceof ID3v24Tag) {
            this.compression = false;
            this.unsynchronization = ((ID3v24Tag) abstractID3v2Tag).unsynchronization;
        }
    }

    public ID3v22Tag(ID3v22Tag iD3v22Tag) {
        super(iD3v22Tag);
        this.compression = false;
        this.unsynchronization = false;
        logger.config("Creating tag from another tag of same type");
        copyPrimitives(iD3v22Tag);
        copyFrames(iD3v22Tag);
    }

    public ID3v22Tag(AbstractTag abstractTag) {
        ID3v24Tag iD3v24Tag;
        this.compression = false;
        this.unsynchronization = false;
        this.frameMap = new LinkedHashMap();
        this.encryptedFrameMap = new LinkedHashMap();
        logger.config("Creating tag from a tag of a different version");
        if (abstractTag != null) {
            if (!(abstractTag instanceof ID3v23Tag) && (abstractTag instanceof ID3v22Tag)) {
                throw new UnsupportedOperationException("Copy Constructor not called. Please type cast the argument");
            }
            if (abstractTag instanceof ID3v24Tag) {
                iD3v24Tag = (ID3v24Tag) abstractTag;
            } else {
                iD3v24Tag = new ID3v24Tag(abstractTag);
            }
            setLoggingFilename(iD3v24Tag.getLoggingFilename());
            copyPrimitives(iD3v24Tag);
            copyFrames(iD3v24Tag);
            logger.config("Created tag from a tag of a different version");
        }
    }

    public ID3v22Tag(ByteBuffer byteBuffer, String str) throws TagException {
        this.compression = false;
        this.unsynchronization = false;
        setLoggingFilename(str);
        read(byteBuffer);
    }

    public ID3v22Tag(ByteBuffer byteBuffer) throws TagException {
        this(byteBuffer, "");
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3Tag, org.jaudiotagger.tag.id3.AbstractTagItem
    public String getIdentifier() {
        return "ID3v2_2.20";
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag, org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return super.getSize() + 10;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag, org.jaudiotagger.tag.id3.AbstractTag, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        if (!(obj instanceof ID3v22Tag)) {
            return false;
        }
        ID3v22Tag iD3v22Tag = (ID3v22Tag) obj;
        return this.compression == iD3v22Tag.compression && this.unsynchronization == iD3v22Tag.unsynchronization && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    protected List<AbstractID3v2Frame> convertFrame(AbstractID3v2Frame abstractID3v2Frame) throws InvalidFrameException {
        ArrayList arrayList = new ArrayList();
        if (abstractID3v2Frame.getIdentifier().equals(ID3v24Frames.FRAME_ID_YEAR) && (abstractID3v2Frame.getBody() instanceof FrameBodyTDRC)) {
            FrameBodyTDRC frameBodyTDRC = (FrameBodyTDRC) abstractID3v2Frame.getBody();
            if (frameBodyTDRC.getYear().length() != 0) {
                ID3v22Frame iD3v22Frame = new ID3v22Frame(ID3v22Frames.FRAME_ID_V2_TYER);
                ((AbstractFrameBodyTextInfo) iD3v22Frame.getBody()).setText(frameBodyTDRC.getYear());
                arrayList.add(iD3v22Frame);
            }
            if (frameBodyTDRC.getTime().length() != 0) {
                ID3v22Frame iD3v22Frame2 = new ID3v22Frame(ID3v22Frames.FRAME_ID_V2_TIME);
                ((AbstractFrameBodyTextInfo) iD3v22Frame2.getBody()).setText(frameBodyTDRC.getTime());
                arrayList.add(iD3v22Frame2);
            }
        } else {
            arrayList.add(new ID3v22Frame(abstractID3v2Frame));
        }
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    public void addFrame(AbstractID3v2Frame abstractID3v2Frame) {
        try {
            if (abstractID3v2Frame instanceof ID3v22Frame) {
                copyFrameIntoMap(abstractID3v2Frame.getIdentifier(), abstractID3v2Frame);
                return;
            }
            for (AbstractID3v2Frame abstractID3v2Frame2 : convertFrame(abstractID3v2Frame)) {
                copyFrameIntoMap(abstractID3v2Frame2.getIdentifier(), abstractID3v2Frame2);
            }
        } catch (InvalidFrameException unused) {
            logger.log(Level.SEVERE, "Unable to convert frame:" + abstractID3v2Frame.getIdentifier());
        }
    }

    private void readHeaderFlags(ByteBuffer byteBuffer) throws TagException {
        byte b = byteBuffer.get();
        boolean z = (b & 128) != 0;
        this.unsynchronization = z;
        this.compression = (b & SignedBytes.MAX_POWER_OF_TWO) != 0;
        if (z) {
            logger.config(ErrorMessage.ID3_TAG_UNSYNCHRONIZED.getMsg(getLoggingFilename()));
        }
        if (this.compression) {
            logger.config(ErrorMessage.ID3_TAG_COMPRESSED.getMsg(getLoggingFilename()));
        }
        if ((b & 32) != 0) {
            logger.warning(ErrorMessage.ID3_INVALID_OR_UNKNOWN_FLAG_SET.getMsg(getLoggingFilename(), 32));
        }
        if ((b & 16) != 0) {
            logger.warning(ErrorMessage.ID3_INVALID_OR_UNKNOWN_FLAG_SET.getMsg(getLoggingFilename(), 16));
        }
        if ((b & 8) != 0) {
            logger.warning(ErrorMessage.ID3_INVALID_OR_UNKNOWN_FLAG_SET.getMsg(getLoggingFilename(), 8));
        }
        if ((b & 4) != 0) {
            logger.warning(ErrorMessage.ID3_INVALID_OR_UNKNOWN_FLAG_SET.getMsg(getLoggingFilename(), 4));
        }
        if ((b & 2) != 0) {
            logger.warning(ErrorMessage.ID3_INVALID_OR_UNKNOWN_FLAG_SET.getMsg(getLoggingFilename(), 2));
        }
        if ((b & 1) != 0) {
            logger.warning(ErrorMessage.ID3_INVALID_OR_UNKNOWN_FLAG_SET.getMsg(getLoggingFilename(), 8));
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public void read(ByteBuffer byteBuffer) throws TagException {
        if (!seek(byteBuffer)) {
            throw new TagNotFoundException("ID3v2.20 tag not found");
        }
        logger.config(getLoggingFilename() + ":Reading tag from file");
        readHeaderFlags(byteBuffer);
        int iBufferToValue = ID3SyncSafeInteger.bufferToValue(byteBuffer);
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        if (this.unsynchronization) {
            byteBufferSlice = ID3Unsynchronization.synchronize(byteBufferSlice);
        }
        readFrames(byteBufferSlice, iBufferToValue);
        logger.config(getLoggingFilename() + ":Loaded Frames,there are:" + this.frameMap.keySet().size());
    }

    protected void readFrames(ByteBuffer byteBuffer, int i) {
        this.frameMap = new LinkedHashMap();
        this.encryptedFrameMap = new LinkedHashMap();
        this.fileReadSize = i;
        logger.finest(getLoggingFilename() + ":Start of frame body at:" + byteBuffer.position() + ",frames sizes and padding is:" + i);
        while (byteBuffer.position() < i) {
            try {
                logger.config(getLoggingFilename() + ":looking for next frame at:" + byteBuffer.position());
                ID3v22Frame iD3v22Frame = new ID3v22Frame(byteBuffer, getLoggingFilename());
                loadFrameIntoMap(iD3v22Frame.getIdentifier(), iD3v22Frame);
            } catch (EmptyFrameException e) {
                logger.warning(getLoggingFilename() + ":Empty Frame:" + e.getMessage());
                this.emptyFrameBytes += 6;
            } catch (InvalidDataTypeException e2) {
                logger.warning(getLoggingFilename() + ":Corrupt Frame:" + e2.getMessage());
                this.invalidFrames++;
            } catch (PaddingException unused) {
                logger.config(getLoggingFilename() + ":Found padding starting at:" + byteBuffer.position());
                return;
            } catch (InvalidFrameIdentifierException e3) {
                logger.config(getLoggingFilename() + ":Invalid Frame Identifier:" + e3.getMessage());
                this.invalidFrames++;
                return;
            } catch (InvalidFrameException e4) {
                logger.warning(getLoggingFilename() + ":Invalid Frame:" + e4.getMessage());
                this.invalidFrames++;
                return;
            }
        }
    }

    protected void translateFrame(AbstractID3v2Frame abstractID3v2Frame) {
        FrameBodyTDRC frameBodyTDRC = (FrameBodyTDRC) abstractID3v2Frame.getBody();
        if (frameBodyTDRC.getYear().length() != 0) {
            ID3v22Frame iD3v22Frame = new ID3v22Frame(ID3v22Frames.FRAME_ID_V2_TYER);
            ((AbstractFrameBodyTextInfo) iD3v22Frame.getBody()).setText(frameBodyTDRC.getYear());
            setFrame(iD3v22Frame);
        }
        if (frameBodyTDRC.getTime().length() != 0) {
            ID3v22Frame iD3v22Frame2 = new ID3v22Frame(ID3v22Frames.FRAME_ID_V2_TIME);
            ((AbstractFrameBodyTextInfo) iD3v22Frame2.getBody()).setText(frameBodyTDRC.getTime());
            setFrame(iD3v22Frame2);
        }
    }

    private ByteBuffer writeHeaderToBuffer(int i, int i2) throws IOException {
        this.compression = false;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(10);
        byteBufferAllocate.put(TAG_ID);
        byteBufferAllocate.put(getMajorVersion());
        byteBufferAllocate.put(getRevision());
        byte b = this.unsynchronization ? (byte) (-128) : (byte) 0;
        if (this.compression) {
            b = (byte) (b | SignedBytes.MAX_POWER_OF_TWO);
        }
        byteBufferAllocate.put(b);
        byteBufferAllocate.put(ID3SyncSafeInteger.valueToBuffer(i + i2));
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    public long write(File file, long j) throws IOException {
        setLoggingFilename(file.getName());
        logger.config("Writing tag to file:" + getLoggingFilename());
        byte[] byteArray = writeFramesToBuffer().toByteArray();
        this.unsynchronization = TagOptionSingleton.getInstance().isUnsyncTags() && ID3Unsynchronization.requiresUnsynchronization(byteArray);
        if (isUnsynchronization()) {
            byteArray = ID3Unsynchronization.unsynchronize(byteArray);
            logger.config(getLoggingFilename() + ":bodybytebuffer:sizeafterunsynchronisation:" + byteArray.length);
        }
        byte[] bArr = byteArray;
        int iCalculateTagSize = calculateTagSize(bArr.length + 10, (int) j);
        int length = iCalculateTagSize - (bArr.length + 10);
        logger.config(getLoggingFilename() + ":Current audiostart:" + j);
        logger.config(getLoggingFilename() + ":Size including padding:" + iCalculateTagSize);
        logger.config(getLoggingFilename() + ":Padding:" + length);
        writeBufferToFile(file, writeHeaderToBuffer(length, bArr.length), bArr, length, iCalculateTagSize, j);
        return iCalculateTagSize;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    public void write(WritableByteChannel writableByteChannel, int i) throws IOException {
        logger.config(getLoggingFilename() + ":Writing tag to channel");
        byte[] byteArray = writeFramesToBuffer().toByteArray();
        logger.config(getLoggingFilename() + ":bodybytebuffer:sizebeforeunsynchronisation:" + byteArray.length);
        this.unsynchronization = TagOptionSingleton.getInstance().isUnsyncTags() && ID3Unsynchronization.requiresUnsynchronization(byteArray);
        if (isUnsynchronization()) {
            byteArray = ID3Unsynchronization.unsynchronize(byteArray);
            logger.config(getLoggingFilename() + ":bodybytebuffer:sizeafterunsynchronisation:" + byteArray.length);
        }
        int iCalculateTagSize = i > 0 ? calculateTagSize(byteArray.length + 10, i) - (byteArray.length + 10) : 0;
        writableByteChannel.write(writeHeaderToBuffer(iCalculateTagSize, byteArray.length));
        writableByteChannel.write(ByteBuffer.wrap(byteArray));
        writePadding(writableByteChannel, iCalculateTagSize);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    public void createStructure() {
        MP3File.getStructureFormatter().openHeadingElement("tag", getIdentifier());
        super.createStructureHeader();
        MP3File.getStructureFormatter().openHeadingElement("header", "");
        MP3File.getStructureFormatter().addElement("compression", this.compression);
        MP3File.getStructureFormatter().addElement(TYPE_UNSYNCHRONISATION, this.unsynchronization);
        MP3File.getStructureFormatter().closeHeadingElement("header");
        super.createStructureBody();
        MP3File.getStructureFormatter().closeHeadingElement("tag");
    }

    public boolean isUnsynchronization() {
        return this.unsynchronization;
    }

    public boolean isCompression() {
        return this.compression;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    public ID3v22Frame createFrame(String str) {
        return new ID3v22Frame(str);
    }

    public TagField createField(ID3v22FieldKey iD3v22FieldKey, String str) throws KeyNotFoundException, FieldDataInvalidException {
        if (iD3v22FieldKey != null) {
            return doCreateTagField(new AbstractID3v2Tag.FrameAndSubId(null, iD3v22FieldKey.getFrameId(), iD3v22FieldKey.getSubId()), str);
        }
        throw new KeyNotFoundException();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag, org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (strArr == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        String str = strArr[0];
        if (fieldKey != FieldKey.GENRE) {
            return super.createField(fieldKey, strArr);
        }
        if (str == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        ID3v22Frame iD3v22FrameCreateFrame = createFrame(getFrameAndSubIdFromGenericKey(fieldKey).getFrameId());
        FrameBodyTCON frameBodyTCON = (FrameBodyTCON) iD3v22FrameCreateFrame.getBody();
        frameBodyTCON.setV23Format();
        frameBodyTCON.setText(FrameBodyTCON.convertGenericToID3v22Genre(str));
        return iD3v22FrameCreateFrame;
    }

    public String getFirst(ID3v22FieldKey iD3v22FieldKey) throws KeyNotFoundException {
        if (iD3v22FieldKey == null) {
            throw new KeyNotFoundException();
        }
        FieldKey genericKeyFromId3 = ID3v22Frames.getInstanceOf().getGenericKeyFromId3(iD3v22FieldKey);
        if (genericKeyFromId3 != null) {
            return super.getFirst(genericKeyFromId3);
        }
        return super.doGetValueAtIndex(new AbstractID3v2Tag.FrameAndSubId(null, iD3v22FieldKey.getFrameId(), iD3v22FieldKey.getSubId()), 0);
    }

    public void deleteField(ID3v22FieldKey iD3v22FieldKey) throws KeyNotFoundException {
        if (iD3v22FieldKey == null) {
            throw new KeyNotFoundException();
        }
        super.doDeleteTagField(new AbstractID3v2Tag.FrameAndSubId(null, iD3v22FieldKey.getFrameId(), iD3v22FieldKey.getSubId()));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(String str) throws KeyNotFoundException {
        super.doDeleteTagField(new AbstractID3v2Tag.FrameAndSubId(null, str, null));
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    protected AbstractID3v2Tag.FrameAndSubId getFrameAndSubIdFromGenericKey(FieldKey fieldKey) {
        if (fieldKey == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        ID3v22FieldKey id3KeyFromGenericKey = ID3v22Frames.getInstanceOf().getId3KeyFromGenericKey(fieldKey);
        if (id3KeyFromGenericKey == null) {
            throw new KeyNotFoundException(fieldKey.name());
        }
        return new AbstractID3v2Tag.FrameAndSubId(fieldKey, id3KeyFromGenericKey.getFrameId(), id3KeyFromGenericKey.getSubId());
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    protected ID3Frames getID3Frames() {
        return ID3v22Frames.getInstanceOf();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    public Comparator<String> getPreferredFrameOrderComparator() {
        return ID3v22PreferredFrameOrderComparator.getInstanceof();
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<Artwork> getArtworkList() {
        List<TagField> fields = getFields(FieldKey.COVER_ART);
        ArrayList arrayList = new ArrayList(fields.size());
        Iterator<TagField> it = fields.iterator();
        while (it.hasNext()) {
            FrameBodyPIC frameBodyPIC = (FrameBodyPIC) ((AbstractID3v2Frame) it.next()).getBody();
            Artwork artwork = ArtworkFactory.getNew();
            artwork.setMimeType(ImageFormats.getMimeTypeForFormat(frameBodyPIC.getFormatType()));
            artwork.setPictureType(frameBodyPIC.getPictureType());
            artwork.setDescription(frameBodyPIC.getDescription());
            if (frameBodyPIC.isImageUrl()) {
                artwork.setLinked(true);
                artwork.setImageUrl(frameBodyPIC.getImageUrl());
            } else {
                artwork.setBinaryData(frameBodyPIC.getImageData());
            }
            arrayList.add(artwork);
        }
        return arrayList;
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(Artwork artwork) throws FieldDataInvalidException {
        ID3v22Frame iD3v22FrameCreateFrame = createFrame(getFrameAndSubIdFromGenericKey(FieldKey.COVER_ART).getFrameId());
        FrameBodyPIC frameBodyPIC = (FrameBodyPIC) iD3v22FrameCreateFrame.getBody();
        if (!artwork.isLinked()) {
            frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_DATA, artwork.getBinaryData());
            frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_TYPE, Integer.valueOf(artwork.getPictureType()));
            frameBodyPIC.setObjectValue(DataTypes.OBJ_IMAGE_FORMAT, ImageFormats.getFormatForMimeType(artwork.getMimeType()));
            frameBodyPIC.setObjectValue(DataTypes.OBJ_DESCRIPTION, artwork.getDescription());
            return iD3v22FrameCreateFrame;
        }
        try {
            frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_DATA, artwork.getImageUrl().getBytes("ISO-8859-1"));
            frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_TYPE, Integer.valueOf(artwork.getPictureType()));
            frameBodyPIC.setObjectValue(DataTypes.OBJ_IMAGE_FORMAT, "-->");
            frameBodyPIC.setObjectValue(DataTypes.OBJ_DESCRIPTION, artwork.getDescription());
            return iD3v22FrameCreateFrame;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public TagField createArtworkField(byte[] bArr, String str) {
        ID3v22Frame iD3v22FrameCreateFrame = createFrame(getFrameAndSubIdFromGenericKey(FieldKey.COVER_ART).getFrameId());
        FrameBodyPIC frameBodyPIC = (FrameBodyPIC) iD3v22FrameCreateFrame.getBody();
        frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_DATA, bArr);
        frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_TYPE, PictureTypes.DEFAULT_ID);
        frameBodyPIC.setObjectValue(DataTypes.OBJ_IMAGE_FORMAT, ImageFormats.getFormatForMimeType(str));
        frameBodyPIC.setObjectValue(DataTypes.OBJ_DESCRIPTION, "");
        return iD3v22FrameCreateFrame;
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag, org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == FieldKey.GENRE) {
            List<TagField> fields = getFields(fieldKey);
            ArrayList arrayList = new ArrayList();
            if (fields != null && fields.size() > 0) {
                Iterator<String> it = ((FrameBodyTCON) ((AbstractID3v2Frame) fields.get(0)).getBody()).getValues().iterator();
                while (it.hasNext()) {
                    arrayList.add(FrameBodyTCON.convertID3v22GenreToGeneric(it.next()));
                }
            }
            return arrayList;
        }
        return super.getAll(fieldKey);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag, org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (fieldKey == FieldKey.GENRE) {
            List<TagField> fields = getFields(fieldKey);
            if (fields != null && fields.size() > 0) {
                return FrameBodyTCON.convertID3v22GenreToGeneric(((FrameBodyTCON) ((AbstractID3v2Frame) fields.get(0)).getBody()).getValues().get(i));
            }
            return "";
        }
        return super.getValue(fieldKey, i);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractID3v2Tag
    protected void loadFrameIntoMap(String str, AbstractID3v2Frame abstractID3v2Frame) {
        if (abstractID3v2Frame.getBody() instanceof FrameBodyTCON) {
            ((FrameBodyTCON) abstractID3v2Frame.getBody()).setV23Format();
        }
        super.loadFrameIntoMap(str, abstractID3v2Frame);
    }
}
