package org.jaudiotagger.tag.id3;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.google.android.exoplayer2.source.rtsp.SessionDescription;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeSet;
import java.util.logging.Level;
import org.jaudiotagger.audio.exceptions.UnableToCreateFileException;
import org.jaudiotagger.audio.exceptions.UnableToModifyFileException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.logging.FileSystemMessage;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.TagOptionSingleton;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.datatype.Pair;
import org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyNumberTotal;
import org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyPairs;
import org.jaudiotagger.tag.id3.framebody.AbstractFrameBodyTextInfo;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyCOMM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyEncrypted;
import org.jaudiotagger.tag.id3.framebody.FrameBodyIPLS;
import org.jaudiotagger.tag.id3.framebody.FrameBodyPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyPOPM;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTIPL;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTMCL;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTXXX;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUFID;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUSLT;
import org.jaudiotagger.tag.id3.framebody.FrameBodyUnsupported;
import org.jaudiotagger.tag.id3.framebody.FrameBodyWOAR;
import org.jaudiotagger.tag.id3.framebody.FrameBodyWXXX;
import org.jaudiotagger.tag.id3.valuepair.ID3NumberTotalFields;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.reference.Languages;
import org.jaudiotagger.tag.reference.PictureTypes;
import org.jaudiotagger.utils.ShiftData;

/* loaded from: classes3.dex */
public abstract class AbstractID3v2Tag extends AbstractID3Tag implements Tag {
    public static final int FIELD_TAGID_LENGTH = 3;
    public static final int FIELD_TAGID_POS = 0;
    public static final int FIELD_TAG_FLAG_LENGTH = 1;
    public static final int FIELD_TAG_FLAG_POS = 5;
    public static final int FIELD_TAG_MAJOR_VERSION_LENGTH = 1;
    public static final int FIELD_TAG_MAJOR_VERSION_POS = 3;
    public static final int FIELD_TAG_MINOR_VERSION_LENGTH = 1;
    public static final int FIELD_TAG_MINOR_VERSION_POS = 4;
    public static final int FIELD_TAG_SIZE_LENGTH = 4;
    public static final int FIELD_TAG_SIZE_POS = 6;
    public static final String TAGID = "ID3";
    public static final int TAG_HEADER_LENGTH = 10;
    public static final byte[] TAG_ID = {73, 68, 51};
    protected static final int TAG_SIZE_INCREMENT = 100;
    protected static final String TYPE_BODY = "body";
    protected static final String TYPE_DUPLICATEBYTES = "duplicateBytes";
    protected static final String TYPE_DUPLICATEFRAMEID = "duplicateFrameId";
    protected static final String TYPE_EMPTYFRAMEBYTES = "emptyFrameBytes";
    protected static final String TYPE_FILEREADSIZE = "fileReadSize";
    protected static final String TYPE_HEADER = "header";
    protected static final String TYPE_INVALIDFRAMES = "invalidFrames";
    private Long startLocationInFile = null;
    private Long endLocationInFile = null;
    protected Map<String, List<TagField>> frameMap = null;
    protected Map<String, List<TagField>> encryptedFrameMap = null;
    protected String duplicateFrameId = "";
    protected int duplicateBytes = 0;
    protected int emptyFrameBytes = 0;
    protected int fileReadSize = 0;
    protected int invalidFrames = 0;

    protected abstract void addFrame(AbstractID3v2Frame abstractID3v2Frame);

    protected abstract List<AbstractID3v2Frame> convertFrame(AbstractID3v2Frame abstractID3v2Frame) throws InvalidFrameException;

    public abstract AbstractID3v2Frame createFrame(String str);

    protected abstract FrameAndSubId getFrameAndSubIdFromGenericKey(FieldKey fieldKey);

    protected abstract ID3Frames getID3Frames();

    public abstract Comparator<String> getPreferredFrameOrderComparator();

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasCommonFields() {
        return true;
    }

    public abstract long write(File file, long j) throws IOException;

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void write(RandomAccessFile randomAccessFile) throws IOException {
    }

    public void write(WritableByteChannel writableByteChannel, int i) throws IOException {
    }

    private static boolean isID3V2Header(RandomAccessFile randomAccessFile) throws IOException {
        long filePointer = randomAccessFile.getFilePointer();
        byte[] bArr = new byte[3];
        randomAccessFile.read(bArr);
        randomAccessFile.seek(filePointer);
        return Arrays.equals(bArr, TAG_ID);
    }

    private static boolean isID3V2Header(FileChannel fileChannel) throws IOException {
        long jPosition = fileChannel.position();
        ByteBuffer fileDataIntoBufferBE = Utils.readFileDataIntoBufferBE(fileChannel, 3);
        fileChannel.position(jPosition);
        return Utils.readThreeBytesAsChars(fileDataIntoBufferBE).equals(TAGID);
    }

    public static boolean isId3Tag(RandomAccessFile randomAccessFile) throws IOException {
        if (!isID3V2Header(randomAccessFile)) {
            return false;
        }
        randomAccessFile.seek(randomAccessFile.getFilePointer() + 6);
        randomAccessFile.read(new byte[4]);
        randomAccessFile.seek(ID3SyncSafeInteger.bufferToValue(ByteBuffer.wrap(r0)) + 10);
        return true;
    }

    public static boolean isId3Tag(FileChannel fileChannel) throws IOException {
        if (!isID3V2Header(fileChannel)) {
            return false;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(4);
        fileChannel.position(fileChannel.position() + 6);
        fileChannel.read(byteBufferAllocateDirect);
        byteBufferAllocateDirect.flip();
        fileChannel.position(ID3SyncSafeInteger.bufferToValue(byteBufferAllocateDirect) + 10);
        return true;
    }

    public AbstractID3v2Tag() {
    }

    protected AbstractID3v2Tag(AbstractID3v2Tag abstractID3v2Tag) {
    }

    protected void copyPrimitives(AbstractID3v2Tag abstractID3v2Tag) {
        logger.config("Copying Primitives");
        this.duplicateFrameId = abstractID3v2Tag.duplicateFrameId;
        this.duplicateBytes = abstractID3v2Tag.duplicateBytes;
        this.emptyFrameBytes = abstractID3v2Tag.emptyFrameBytes;
        this.fileReadSize = abstractID3v2Tag.fileReadSize;
        this.invalidFrames = abstractID3v2Tag.invalidFrames;
    }

    protected void copyFrames(AbstractID3v2Tag abstractID3v2Tag) {
        this.frameMap = new LinkedHashMap();
        this.encryptedFrameMap = new LinkedHashMap();
        Iterator<String> it = abstractID3v2Tag.frameMap.keySet().iterator();
        while (it.hasNext()) {
            for (TagField tagField : abstractID3v2Tag.frameMap.get(it.next())) {
                if (tagField instanceof AbstractID3v2Frame) {
                    addFrame((AbstractID3v2Frame) tagField);
                } else if (tagField instanceof TyerTdatAggregatedFrame) {
                    Iterator<AbstractID3v2Frame> it2 = ((TyerTdatAggregatedFrame) tagField).getFrames().iterator();
                    while (it2.hasNext()) {
                        addFrame(it2.next());
                    }
                }
            }
        }
    }

    public int getDuplicateBytes() {
        return this.duplicateBytes;
    }

    public String getDuplicateFrameId() {
        return this.duplicateFrameId;
    }

    public int getEmptyFrameBytes() {
        return this.emptyFrameBytes;
    }

    public int getInvalidFrames() {
        return this.invalidFrames;
    }

    public int getFileReadBytes() {
        return this.fileReadSize;
    }

    public boolean hasFrame(String str) {
        return this.frameMap.containsKey(str);
    }

    public boolean hasFrameAndBody(String str) {
        boolean z = false;
        if (hasFrame(str)) {
            List<TagField> frame = getFrame(str);
            if (frame.size() > 0) {
                z = true;
                if (frame.get(0) instanceof AbstractID3v2Frame) {
                    return !(((AbstractID3v2Frame) r3).getBody() instanceof FrameBodyUnsupported);
                }
            }
        }
        return z;
    }

    public boolean hasFrameOfType(String str) {
        Iterator<String> it = this.frameMap.keySet().iterator();
        boolean z = false;
        while (it.hasNext() && !z) {
            if (it.next().startsWith(str)) {
                z = true;
            }
        }
        return z;
    }

    public List<TagField> getFrame(String str) {
        return this.frameMap.get(str);
    }

    public List<TagField> getEncryptedFrame(String str) {
        return this.encryptedFrameMap.get(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(String str) {
        AbstractID3v2Frame firstField = getFirstField(str);
        if (firstField == null) {
            return "";
        }
        return getTextValueForFrame(firstField);
    }

    private String getTextValueForFrame(AbstractID3v2Frame abstractID3v2Frame) {
        return abstractID3v2Frame.getBody().getUserFriendlyValue();
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField getFirstField(FieldKey fieldKey) throws KeyNotFoundException {
        List<TagField> fields = getFields(fieldKey);
        if (fields.size() > 0) {
            return fields.get(0);
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.Tag
    public AbstractID3v2Frame getFirstField(String str) {
        List<TagField> frame = getFrame(str);
        if (frame == null || frame.isEmpty() || containsAggregatedFrame(frame)) {
            return null;
        }
        return (AbstractID3v2Frame) frame.get(0);
    }

    public void setFrame(AbstractID3v2Frame abstractID3v2Frame) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(abstractID3v2Frame);
        this.frameMap.put(abstractID3v2Frame.getIdentifier(), arrayList);
    }

    protected void setTagField(String str, TagField tagField) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(tagField);
        this.frameMap.put(str, arrayList);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        setField(createField(fieldKey, strArr));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        addField(createField(fieldKey, strArr));
    }

    public void mergeNumberTotalFrames(AbstractID3v2Frame abstractID3v2Frame, AbstractID3v2Frame abstractID3v2Frame2) {
        AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal = (AbstractFrameBodyNumberTotal) abstractID3v2Frame.getBody();
        AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal2 = (AbstractFrameBodyNumberTotal) abstractID3v2Frame2.getBody();
        if (abstractFrameBodyNumberTotal.getNumber() != null && abstractFrameBodyNumberTotal.getNumber().intValue() > 0) {
            abstractFrameBodyNumberTotal2.setNumber(abstractFrameBodyNumberTotal.getNumberAsText());
        }
        if (abstractFrameBodyNumberTotal.getTotal() == null || abstractFrameBodyNumberTotal.getTotal().intValue() <= 0) {
            return;
        }
        abstractFrameBodyNumberTotal2.setTotal(abstractFrameBodyNumberTotal.getTotalAsText());
    }

    public void mergeDuplicateFrames(AbstractID3v2Frame abstractID3v2Frame) {
        List<TagField> arrayList = this.frameMap.get(abstractID3v2Frame.getId());
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
        ListIterator<TagField> listIterator = arrayList.listIterator();
        while (listIterator.hasNext()) {
            TagField next = listIterator.next();
            if (next instanceof AbstractID3v2Frame) {
                AbstractID3v2Frame abstractID3v2Frame2 = (AbstractID3v2Frame) next;
                if (abstractID3v2Frame.getBody() instanceof FrameBodyTXXX) {
                    if (((FrameBodyTXXX) abstractID3v2Frame.getBody()).getDescription().equals(((FrameBodyTXXX) abstractID3v2Frame2.getBody()).getDescription())) {
                        listIterator.set(abstractID3v2Frame);
                        this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
                        return;
                    }
                } else if (abstractID3v2Frame.getBody() instanceof FrameBodyWXXX) {
                    if (((FrameBodyWXXX) abstractID3v2Frame.getBody()).getDescription().equals(((FrameBodyWXXX) abstractID3v2Frame2.getBody()).getDescription())) {
                        listIterator.set(abstractID3v2Frame);
                        this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
                        return;
                    }
                } else if (abstractID3v2Frame.getBody() instanceof FrameBodyCOMM) {
                    if (((FrameBodyCOMM) abstractID3v2Frame.getBody()).getDescription().equals(((FrameBodyCOMM) abstractID3v2Frame2.getBody()).getDescription())) {
                        listIterator.set(abstractID3v2Frame);
                        this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
                        return;
                    }
                } else if (abstractID3v2Frame.getBody() instanceof FrameBodyUFID) {
                    if (((FrameBodyUFID) abstractID3v2Frame.getBody()).getOwner().equals(((FrameBodyUFID) abstractID3v2Frame2.getBody()).getOwner())) {
                        listIterator.set(abstractID3v2Frame);
                        this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
                        return;
                    }
                } else if (abstractID3v2Frame.getBody() instanceof FrameBodyUSLT) {
                    if (((FrameBodyUSLT) abstractID3v2Frame.getBody()).getDescription().equals(((FrameBodyUSLT) abstractID3v2Frame2.getBody()).getDescription())) {
                        listIterator.set(abstractID3v2Frame);
                        this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
                        return;
                    }
                } else if (abstractID3v2Frame.getBody() instanceof FrameBodyPOPM) {
                    if (((FrameBodyPOPM) abstractID3v2Frame.getBody()).getEmailToUser().equals(((FrameBodyPOPM) abstractID3v2Frame2.getBody()).getEmailToUser())) {
                        listIterator.set(abstractID3v2Frame);
                        this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
                        return;
                    }
                } else if (abstractID3v2Frame.getBody() instanceof AbstractFrameBodyNumberTotal) {
                    mergeNumberTotalFrames(abstractID3v2Frame, abstractID3v2Frame2);
                    return;
                } else if (abstractID3v2Frame.getBody() instanceof AbstractFrameBodyPairs) {
                    ((AbstractFrameBodyPairs) abstractID3v2Frame2.getBody()).addPair(((AbstractFrameBodyPairs) abstractID3v2Frame.getBody()).getText());
                    return;
                }
            }
        }
        if (!getID3Frames().isMultipleAllowed(abstractID3v2Frame.getId())) {
            setFrame(abstractID3v2Frame);
        } else {
            arrayList.add(abstractID3v2Frame);
            this.frameMap.put(abstractID3v2Frame.getId(), arrayList);
        }
    }

    private void addNewFrameToMap(List<TagField> list, Map<String, List<TagField>> map, AbstractID3v2Frame abstractID3v2Frame, AbstractID3v2Frame abstractID3v2Frame2) {
        if (list.size() == 0) {
            list.add(abstractID3v2Frame);
            list.add(abstractID3v2Frame2);
            map.put(abstractID3v2Frame2.getId(), list);
            return;
        }
        list.add(abstractID3v2Frame2);
    }

    private void addNewFrameOrAddField(List<TagField> list, Map<String, List<TagField>> map, AbstractID3v2Frame abstractID3v2Frame, AbstractID3v2Frame abstractID3v2Frame2) {
        ArrayList arrayList = new ArrayList();
        if (abstractID3v2Frame != null) {
            arrayList.add(abstractID3v2Frame);
        } else {
            arrayList.addAll(list);
        }
        if (abstractID3v2Frame2.getBody() instanceof FrameBodyTXXX) {
            FrameBodyTXXX frameBodyTXXX = (FrameBodyTXXX) abstractID3v2Frame2.getBody();
            ListIterator listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                FrameBodyTXXX frameBodyTXXX2 = (FrameBodyTXXX) ((AbstractID3v2Frame) listIterator.next()).getBody();
                if (frameBodyTXXX.getDescription().equals(frameBodyTXXX2.getDescription())) {
                    frameBodyTXXX2.addTextValue(frameBodyTXXX.getText());
                    return;
                }
            }
            addNewFrameToMap(list, map, abstractID3v2Frame, abstractID3v2Frame2);
            return;
        }
        if (abstractID3v2Frame2.getBody() instanceof FrameBodyWXXX) {
            FrameBodyWXXX frameBodyWXXX = (FrameBodyWXXX) abstractID3v2Frame2.getBody();
            ListIterator listIterator2 = arrayList.listIterator();
            while (listIterator2.hasNext()) {
                FrameBodyWXXX frameBodyWXXX2 = (FrameBodyWXXX) ((AbstractID3v2Frame) listIterator2.next()).getBody();
                if (frameBodyWXXX.getDescription().equals(frameBodyWXXX2.getDescription())) {
                    frameBodyWXXX2.addUrlLink(frameBodyWXXX.getUrlLink());
                    return;
                }
            }
            addNewFrameToMap(list, map, abstractID3v2Frame, abstractID3v2Frame2);
            return;
        }
        if (abstractID3v2Frame2.getBody() instanceof AbstractFrameBodyTextInfo) {
            ((AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody()).addTextValue(((AbstractFrameBodyTextInfo) abstractID3v2Frame2.getBody()).getText());
            return;
        }
        if (abstractID3v2Frame2.getBody() instanceof AbstractFrameBodyPairs) {
            ((AbstractFrameBodyPairs) abstractID3v2Frame.getBody()).addPair(((AbstractFrameBodyPairs) abstractID3v2Frame2.getBody()).getText());
            return;
        }
        if (abstractID3v2Frame2.getBody() instanceof AbstractFrameBodyNumberTotal) {
            AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal = (AbstractFrameBodyNumberTotal) abstractID3v2Frame2.getBody();
            AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal2 = (AbstractFrameBodyNumberTotal) abstractID3v2Frame.getBody();
            if (abstractFrameBodyNumberTotal.getNumber() != null && abstractFrameBodyNumberTotal.getNumber().intValue() > 0) {
                abstractFrameBodyNumberTotal2.setNumber(abstractFrameBodyNumberTotal.getNumberAsText());
            }
            if (abstractFrameBodyNumberTotal.getTotal() == null || abstractFrameBodyNumberTotal.getTotal().intValue() <= 0) {
                return;
            }
            abstractFrameBodyNumberTotal2.setTotal(abstractFrameBodyNumberTotal.getTotalAsText());
            return;
        }
        addNewFrameToMap(list, map, abstractID3v2Frame, abstractID3v2Frame2);
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(TagField tagField) throws FieldDataInvalidException {
        boolean z = tagField instanceof AbstractID3v2Frame;
        if (!z && !(tagField instanceof AggregatedFrame)) {
            throw new FieldDataInvalidException("Field " + tagField + " is not of type AbstractID3v2Frame nor AggregatedFrame");
        }
        if (z) {
            mergeDuplicateFrames((AbstractID3v2Frame) tagField);
        } else {
            setTagField(tagField.getId(), tagField);
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(TagField tagField) throws FieldDataInvalidException {
        if (tagField == null) {
            return;
        }
        boolean z = tagField instanceof AbstractID3v2Frame;
        if (!z && !(tagField instanceof AggregatedFrame)) {
            throw new FieldDataInvalidException("Field " + tagField + " is not of type AbstractID3v2Frame or AggregatedFrame");
        }
        if (z) {
            AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) tagField;
            List<TagField> list = this.frameMap.get(tagField.getId());
            if (list == null) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(tagField);
                this.frameMap.put(tagField.getId(), arrayList);
                return;
            } else if (list.size() == 1 && (list.get(0) instanceof AbstractID3v2Frame)) {
                addNewFrameOrAddField(list, this.frameMap, (AbstractID3v2Frame) list.get(0), abstractID3v2Frame);
                return;
            } else {
                addNewFrameOrAddField(list, this.frameMap, null, abstractID3v2Frame);
                return;
            }
        }
        setTagField(tagField.getId(), tagField);
    }

    public void setFrame(String str, List<TagField> list) {
        logger.finest("Adding " + list.size() + " frames for " + str);
        this.frameMap.put(str, list);
    }

    public Iterator<Object> getFrameOfType(String str) {
        HashSet hashSet = new HashSet();
        for (String str2 : this.frameMap.keySet()) {
            if (str2.startsWith(str)) {
                List<TagField> list = this.frameMap.get(str2);
                if (list instanceof List) {
                    Iterator<TagField> it = list.iterator();
                    while (it.hasNext()) {
                        hashSet.add(it.next());
                    }
                } else {
                    hashSet.add(list);
                }
            }
        }
        return hashSet.iterator();
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void delete(RandomAccessFile randomAccessFile) throws IOException {
        byte[] bArr = new byte[3];
        FileChannel channel = randomAccessFile.getChannel();
        channel.position();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(10);
        channel.read(byteBufferAllocate, 0L);
        byteBufferAllocate.flip();
        if (seek(byteBufferAllocate)) {
            randomAccessFile.seek(0L);
            randomAccessFile.write(bArr);
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag, org.jaudiotagger.tag.id3.AbstractTagItem
    public boolean equals(Object obj) {
        return (obj instanceof AbstractID3v2Tag) && this.frameMap.equals(((AbstractID3v2Tag) obj).frameMap) && super.equals(obj);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public Iterator<List<TagField>> iterator() {
        return this.frameMap.values().iterator();
    }

    public void removeFrame(String str) {
        logger.config("Removing frame with identifier:" + str);
        this.frameMap.remove(str);
    }

    public void removeUnsupportedFrames() {
        Iterator<List<TagField>> it = iterator();
        while (it.hasNext()) {
            List<TagField> next = it.next();
            Iterator<TagField> it2 = next.iterator();
            while (it2.hasNext()) {
                TagField next2 = it2.next();
                if (next2 instanceof AbstractID3v2Frame) {
                    AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) next2;
                    if (abstractID3v2Frame.getBody() instanceof FrameBodyUnsupported) {
                        logger.finest("Removing frame" + abstractID3v2Frame.getIdentifier());
                        it2.remove();
                    }
                }
            }
            if (next.isEmpty()) {
                it.remove();
            }
        }
    }

    public void removeFrameOfType(String str) {
        HashSet<String> hashSet = new HashSet();
        for (String str2 : this.frameMap.keySet()) {
            if (str2.startsWith(str)) {
                hashSet.add(str2);
            }
        }
        for (String str3 : hashSet) {
            logger.finest("Removing frame with identifier:" + str3 + "because starts with:" + str);
            this.frameMap.remove(str3);
        }
    }

    protected FileLock getFileLockForWriting(FileChannel fileChannel, String str) throws IOException {
        logger.finest("locking fileChannel for " + str);
        try {
            FileLock fileLockTryLock = fileChannel.tryLock();
            if (fileLockTryLock != null) {
                return fileLockTryLock;
            }
            throw new IOException(ErrorMessage.GENERAL_WRITE_FAILED_FILE_LOCKED.getMsg(str));
        } catch (IOException | Error unused) {
            return null;
        }
    }

    public void write(OutputStream outputStream) throws IOException {
        write(Channels.newChannel(outputStream), 0);
    }

    public void write(OutputStream outputStream, int i) throws IOException {
        write(Channels.newChannel(outputStream), i);
    }

    protected void writePadding(WritableByteChannel writableByteChannel, int i) throws IOException {
        if (i > 0) {
            writableByteChannel.write(ByteBuffer.wrap(new byte[i]));
        }
    }

    public static long getV2TagSizeIfExists(File file) throws IOException {
        FileInputStream fileInputStream;
        FileChannel channel = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Throwable th) {
            th = th;
            fileInputStream = null;
        }
        try {
            channel = fileInputStream.getChannel();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(10);
            channel.read(byteBufferAllocate);
            byteBufferAllocate.flip();
            if (byteBufferAllocate.limit() < 10) {
                if (channel != null) {
                    channel.close();
                }
                fileInputStream.close();
                return 0L;
            }
            if (channel != null) {
                channel.close();
            }
            fileInputStream.close();
            byte[] bArr = new byte[3];
            byteBufferAllocate.get(bArr, 0, 3);
            if (!Arrays.equals(bArr, TAG_ID)) {
                return 0L;
            }
            byte b = byteBufferAllocate.get();
            if (b != 2 && b != 3 && b != 4) {
                return 0L;
            }
            byteBufferAllocate.get();
            byteBufferAllocate.get();
            return ID3SyncSafeInteger.bufferToValue(byteBufferAllocate) + 10;
        } catch (Throwable th2) {
            th = th2;
            if (channel != null) {
                channel.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public boolean seek(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        logger.config("ByteBuffer pos:" + byteBuffer.position() + ":limit" + byteBuffer.limit() + ":cap" + byteBuffer.capacity());
        byte[] bArr = new byte[3];
        byteBuffer.get(bArr, 0, 3);
        return Arrays.equals(bArr, TAG_ID) && byteBuffer.get() == getMajorVersion() && byteBuffer.get() == getRevision();
    }

    protected int calculateTagSize(int i, int i2) {
        return TagOptionSingleton.getInstance().isId3v2PaddingWillShorten() ? i : i <= i2 ? i2 : i + 100;
    }

    protected void writeBufferToFile(File file, ByteBuffer byteBuffer, byte[] bArr, int i, int i2, long j) throws IOException {
        try {
            SeekableByteChannel seekableByteChannelNewByteChannel = Files.newByteChannel(file.toPath(), StandardOpenOption.READ, StandardOpenOption.WRITE);
            long j2 = i2;
            try {
                if (j2 > j) {
                    seekableByteChannelNewByteChannel.position(j);
                    ShiftData.shiftDataByOffsetToMakeSpace(seekableByteChannelNewByteChannel, (int) (j2 - j));
                } else if (TagOptionSingleton.getInstance().isId3v2PaddingWillShorten() && j2 < j) {
                    seekableByteChannelNewByteChannel.position(j);
                    ShiftData.shiftDataByOffsetToShrinkSpace(seekableByteChannelNewByteChannel, (int) (j - j2));
                }
                seekableByteChannelNewByteChannel.position(0L);
                seekableByteChannelNewByteChannel.write(byteBuffer);
                seekableByteChannelNewByteChannel.write(ByteBuffer.wrap(bArr));
                seekableByteChannelNewByteChannel.write(ByteBuffer.wrap(new byte[i]));
                if (seekableByteChannelNewByteChannel != null) {
                    seekableByteChannelNewByteChannel.close();
                }
            } finally {
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, getLoggingFilename() + e.getMessage(), (Throwable) e);
            if (e.getMessage().equals(FileSystemMessage.ACCESS_IS_DENIED.getMsg())) {
                logger.severe(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(file.getParentFile().getPath()));
                throw new UnableToModifyFileException(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(file.getParentFile().getPath()));
            }
            logger.severe(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(file.getParentFile().getPath()));
            throw new UnableToCreateFileException(ErrorMessage.GENERAL_WRITE_FAILED_TO_OPEN_FILE_FOR_EDITING.getMsg(file.getParentFile().getPath()));
        }
    }

    private boolean containsAggregatedFrame(Collection<TagField> collection) {
        if (collection != null) {
            Iterator<TagField> it = collection.iterator();
            while (it.hasNext()) {
                if (it.next() instanceof AggregatedFrame) {
                    return true;
                }
            }
        }
        return false;
    }

    protected final void copyFrameIntoMap(String str, AbstractID3v2Frame abstractID3v2Frame) {
        List<TagField> list = this.frameMap.get(abstractID3v2Frame.getIdentifier());
        if (list == null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(abstractID3v2Frame);
            this.frameMap.put(abstractID3v2Frame.getIdentifier(), arrayList);
        } else if (containsAggregatedFrame(list)) {
            logger.severe("Duplicated Aggregate Frame, ignoring:" + str);
        } else {
            combineFrames(abstractID3v2Frame, list);
        }
    }

    protected void combineFrames(AbstractID3v2Frame abstractID3v2Frame, List<TagField> list) {
        list.add(abstractID3v2Frame);
    }

    protected void loadFrameIntoMap(String str, AbstractID3v2Frame abstractID3v2Frame) {
        if (abstractID3v2Frame.getBody() instanceof FrameBodyEncrypted) {
            loadFrameIntoSpecifiedMap(this.encryptedFrameMap, str, abstractID3v2Frame);
        } else {
            loadFrameIntoSpecifiedMap(this.frameMap, str, abstractID3v2Frame);
        }
    }

    protected void loadFrameIntoSpecifiedMap(Map<String, List<TagField>> map, String str, AbstractID3v2Frame abstractID3v2Frame) {
        if (ID3v24Frames.getInstanceOf().isMultipleAllowed(str) || ID3v23Frames.getInstanceOf().isMultipleAllowed(str) || ID3v22Frames.getInstanceOf().isMultipleAllowed(str)) {
            if (map.containsKey(str)) {
                map.get(str).add(abstractID3v2Frame);
                return;
            }
            logger.finer("Adding Multi FrameList(3)" + str);
            ArrayList arrayList = new ArrayList();
            arrayList.add(abstractID3v2Frame);
            map.put(str, arrayList);
            return;
        }
        if (map.containsKey(str) && !map.get(str).isEmpty()) {
            logger.warning("Ignoring Duplicate Frame:" + str);
            if (this.duplicateFrameId.length() > 0) {
                this.duplicateFrameId += ";";
            }
            this.duplicateFrameId += str;
            for (TagField tagField : this.frameMap.get(str)) {
                if (tagField instanceof AbstractID3v2Frame) {
                    this.duplicateBytes += ((AbstractID3v2Frame) tagField).getSize();
                }
            }
            return;
        }
        logger.finer("Adding Frame" + str);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(abstractID3v2Frame);
        map.put(str, arrayList2);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        int size = 0;
        for (List<TagField> list : this.frameMap.values()) {
            if (list != null) {
                for (TagField tagField : list) {
                    if (tagField instanceof AggregatedFrame) {
                        Iterator<AbstractID3v2Frame> it = ((AggregatedFrame) tagField).frames.iterator();
                        while (it.hasNext()) {
                            size += it.next().getSize();
                        }
                    } else if (tagField instanceof AbstractID3v2Frame) {
                        size += ((AbstractID3v2Frame) tagField).getSize();
                    }
                }
            }
        }
        return size;
    }

    protected ByteArrayOutputStream writeFramesToBuffer() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeFramesToBufferStream(this.frameMap, byteArrayOutputStream);
        writeFramesToBufferStream(this.encryptedFrameMap, byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    private void writeFramesToBufferStream(Map<String, List<TagField>> map, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        TreeSet treeSet = new TreeSet(getPreferredFrameOrderComparator());
        treeSet.addAll(map.keySet());
        Iterator it = treeSet.iterator();
        while (it.hasNext()) {
            for (TagField tagField : map.get((String) it.next())) {
                if (tagField instanceof AbstractID3v2Frame) {
                    AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) tagField;
                    abstractID3v2Frame.setLoggingFilename(getLoggingFilename());
                    abstractID3v2Frame.write(byteArrayOutputStream);
                } else if (tagField instanceof AggregatedFrame) {
                    for (AbstractID3v2Frame abstractID3v2Frame2 : ((AggregatedFrame) tagField).getFrames()) {
                        abstractID3v2Frame2.setLoggingFilename(getLoggingFilename());
                        abstractID3v2Frame2.write(byteArrayOutputStream);
                    }
                }
            }
        }
    }

    public void createStructure() {
        createStructureHeader();
        createStructureBody();
    }

    public void createStructureHeader() {
        MP3File.getStructureFormatter().addElement(TYPE_DUPLICATEBYTES, this.duplicateBytes);
        MP3File.getStructureFormatter().addElement(TYPE_DUPLICATEFRAMEID, this.duplicateFrameId);
        MP3File.getStructureFormatter().addElement(TYPE_EMPTYFRAMEBYTES, this.emptyFrameBytes);
        MP3File.getStructureFormatter().addElement(TYPE_FILEREADSIZE, this.fileReadSize);
        MP3File.getStructureFormatter().addElement(TYPE_INVALIDFRAMES, this.invalidFrames);
    }

    public void createStructureBody() {
        MP3File.getStructureFormatter().openHeadingElement("body", "");
        Iterator<List<TagField>> it = this.frameMap.values().iterator();
        while (it.hasNext()) {
            for (TagField tagField : it.next()) {
                if (tagField instanceof AbstractID3v2Frame) {
                    ((AbstractID3v2Frame) tagField).createStructure();
                }
            }
        }
        MP3File.getStructureFormatter().closeHeadingElement("body");
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<String> getAll(FieldKey fieldKey) throws KeyNotFoundException {
        ArrayList arrayList = new ArrayList();
        List<TagField> fields = getFields(fieldKey);
        if (ID3NumberTotalFields.isNumber(fieldKey)) {
            if (fields != null && fields.size() > 0) {
                arrayList.add(((AbstractFrameBodyNumberTotal) ((AbstractID3v2Frame) fields.get(0)).getBody()).getNumberAsText());
            }
            return arrayList;
        }
        if (ID3NumberTotalFields.isTotal(fieldKey)) {
            if (fields != null && fields.size() > 0) {
                arrayList.add(((AbstractFrameBodyNumberTotal) ((AbstractID3v2Frame) fields.get(0)).getBody()).getTotalAsText());
            }
            return arrayList;
        }
        if (fieldKey == FieldKey.RATING) {
            if (fields != null && fields.size() > 0) {
                arrayList.add(String.valueOf(((FrameBodyPOPM) ((AbstractID3v2Frame) fields.get(0)).getBody()).getRating()));
            }
            return arrayList;
        }
        return doGetValues(getFrameAndSubIdFromGenericKey(fieldKey));
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(String str) throws KeyNotFoundException {
        List<TagField> frame = getFrame(str);
        if (frame == null) {
            return new ArrayList();
        }
        if (frame instanceof List) {
            return frame;
        }
        throw new RuntimeException("Found entry in frameMap that was not a frame or a list:" + frame);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(FieldKey fieldKey) {
        if (fieldKey == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        try {
            return getFirstField(fieldKey) != null;
        } catch (KeyNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage(), (Throwable) e);
            return false;
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean hasField(String str) {
        return hasFrame(str);
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean isEmpty() {
        return this.frameMap.size() == 0;
    }

    @Override // org.jaudiotagger.tag.Tag
    public Iterator<TagField> getFields() {
        ArrayList arrayList = new ArrayList();
        Iterator<List<TagField>> it = this.frameMap.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next());
        }
        return arrayList.iterator();
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCount() {
        int i = 0;
        while (true) {
            try {
                getFields().next();
                i++;
            } catch (NoSuchElementException unused) {
                return i;
            }
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public int getFieldCountIncludingSubValues() {
        Iterator<TagField> fields = getFields();
        int numberOfValues = 0;
        while (true) {
            try {
                TagField next = fields.next();
                if (next instanceof AbstractID3v2Frame) {
                    AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) next;
                    if ((abstractID3v2Frame.getBody() instanceof AbstractFrameBodyTextInfo) && !(abstractID3v2Frame.getBody() instanceof FrameBodyTXXX)) {
                        numberOfValues += ((AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody()).getNumberOfValues();
                    }
                }
                numberOfValues++;
            } catch (NoSuchElementException unused) {
                return numberOfValues;
            }
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public boolean setEncoding(Charset charset) throws FieldDataInvalidException {
        throw new UnsupportedOperationException("Not Implemented Yet");
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getFirst(FieldKey fieldKey) throws KeyNotFoundException {
        return getValue(fieldKey, 0);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String getValue(FieldKey fieldKey, int i) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (ID3NumberTotalFields.isNumber(fieldKey) || ID3NumberTotalFields.isTotal(fieldKey)) {
            List<TagField> fields = getFields(fieldKey);
            if (fields == null || fields.size() <= 0) {
                return "";
            }
            AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) fields.get(0);
            if (ID3NumberTotalFields.isNumber(fieldKey)) {
                return ((AbstractFrameBodyNumberTotal) abstractID3v2Frame.getBody()).getNumberAsText();
            }
            if (ID3NumberTotalFields.isTotal(fieldKey)) {
                return ((AbstractFrameBodyNumberTotal) abstractID3v2Frame.getBody()).getTotalAsText();
            }
        } else if (fieldKey == FieldKey.RATING) {
            List<TagField> fields2 = getFields(fieldKey);
            return (fields2 == null || fields2.size() <= i) ? "" : String.valueOf(((FrameBodyPOPM) ((AbstractID3v2Frame) fields2.get(i)).getBody()).getRating());
        }
        return doGetValueAtIndex(getFrameAndSubIdFromGenericKey(fieldKey), i);
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createField(FieldKey fieldKey, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        String str;
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        if (strArr == null || (str = strArr[0]) == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        FrameAndSubId frameAndSubIdFromGenericKey = getFrameAndSubIdFromGenericKey(fieldKey);
        if (ID3NumberTotalFields.isNumber(fieldKey)) {
            AbstractID3v2Frame abstractID3v2FrameCreateFrame = createFrame(frameAndSubIdFromGenericKey.getFrameId());
            ((AbstractFrameBodyNumberTotal) abstractID3v2FrameCreateFrame.getBody()).setNumber(str);
            return abstractID3v2FrameCreateFrame;
        }
        if (ID3NumberTotalFields.isTotal(fieldKey)) {
            AbstractID3v2Frame abstractID3v2FrameCreateFrame2 = createFrame(frameAndSubIdFromGenericKey.getFrameId());
            ((AbstractFrameBodyNumberTotal) abstractID3v2FrameCreateFrame2.getBody()).setTotal(str);
            return abstractID3v2FrameCreateFrame2;
        }
        return doCreateTagField(frameAndSubIdFromGenericKey, strArr);
    }

    protected TagField doCreateTagField(FrameAndSubId frameAndSubId, String... strArr) throws KeyNotFoundException, FieldDataInvalidException {
        String str = strArr[0];
        AbstractID3v2Frame abstractID3v2FrameCreateFrame = createFrame(frameAndSubId.getFrameId());
        if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyUFID) {
            ((FrameBodyUFID) abstractID3v2FrameCreateFrame.getBody()).setOwner(frameAndSubId.getSubId());
            try {
                ((FrameBodyUFID) abstractID3v2FrameCreateFrame.getBody()).setUniqueIdentifier(str.getBytes("ISO-8859-1"));
            } catch (UnsupportedEncodingException unused) {
                throw new RuntimeException("When encoding UFID charset ISO-8859-1 was deemed unsupported");
            }
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyTXXX) {
            ((FrameBodyTXXX) abstractID3v2FrameCreateFrame.getBody()).setDescription(frameAndSubId.getSubId());
            ((FrameBodyTXXX) abstractID3v2FrameCreateFrame.getBody()).setText(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyWXXX) {
            ((FrameBodyWXXX) abstractID3v2FrameCreateFrame.getBody()).setDescription(frameAndSubId.getSubId());
            ((FrameBodyWXXX) abstractID3v2FrameCreateFrame.getBody()).setUrlLink(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyCOMM) {
            if (frameAndSubId.getSubId() != null) {
                ((FrameBodyCOMM) abstractID3v2FrameCreateFrame.getBody()).setDescription(frameAndSubId.getSubId());
                if (((FrameBodyCOMM) abstractID3v2FrameCreateFrame.getBody()).isMediaMonkeyFrame()) {
                    ((FrameBodyCOMM) abstractID3v2FrameCreateFrame.getBody()).setLanguage(Languages.MEDIA_MONKEY_ID);
                }
            }
            ((FrameBodyCOMM) abstractID3v2FrameCreateFrame.getBody()).setText(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyUSLT) {
            ((FrameBodyUSLT) abstractID3v2FrameCreateFrame.getBody()).setDescription("");
            ((FrameBodyUSLT) abstractID3v2FrameCreateFrame.getBody()).setLyric(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyWOAR) {
            ((FrameBodyWOAR) abstractID3v2FrameCreateFrame.getBody()).setUrlLink(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof AbstractFrameBodyTextInfo) {
            ((AbstractFrameBodyTextInfo) abstractID3v2FrameCreateFrame.getBody()).setText(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyPOPM) {
            ((FrameBodyPOPM) abstractID3v2FrameCreateFrame.getBody()).parseString(str);
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyIPLS) {
            if (frameAndSubId.getSubId() != null) {
                ((FrameBodyIPLS) abstractID3v2FrameCreateFrame.getBody()).addPair(frameAndSubId.getSubId(), str);
            } else if (strArr.length >= 2) {
                ((FrameBodyIPLS) abstractID3v2FrameCreateFrame.getBody()).addPair(strArr[0], strArr[1]);
            } else {
                ((FrameBodyIPLS) abstractID3v2FrameCreateFrame.getBody()).addPair(strArr[0]);
            }
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyTIPL) {
            if (frameAndSubId.getSubId() != null) {
                ((FrameBodyTIPL) abstractID3v2FrameCreateFrame.getBody()).addPair(frameAndSubId.getSubId(), str);
            } else if (strArr.length >= 2) {
                ((FrameBodyTIPL) abstractID3v2FrameCreateFrame.getBody()).addPair(strArr[0], strArr[1]);
            } else {
                ((FrameBodyTIPL) abstractID3v2FrameCreateFrame.getBody()).addPair(strArr[0]);
            }
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyTMCL) {
            if (strArr.length >= 2) {
                ((FrameBodyTMCL) abstractID3v2FrameCreateFrame.getBody()).addPair(strArr[0], strArr[1]);
            } else {
                ((FrameBodyTMCL) abstractID3v2FrameCreateFrame.getBody()).addPair(strArr[0]);
            }
        } else {
            if ((abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyAPIC) || (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyPIC)) {
                throw new UnsupportedOperationException(ErrorMessage.ARTWORK_CANNOT_BE_CREATED_WITH_THIS_METHOD.getMsg());
            }
            throw new FieldDataInvalidException("Field with key of:" + frameAndSubId.getFrameId() + ":does not accept cannot parse data:" + str);
        }
        return abstractID3v2FrameCreateFrame;
    }

    protected List<String> doGetValues(FrameAndSubId frameAndSubId) throws KeyNotFoundException {
        ArrayList arrayList = new ArrayList();
        if (frameAndSubId.getSubId() != null) {
            ListIterator<TagField> listIterator = getFields(frameAndSubId.getFrameId()).listIterator();
            while (listIterator.hasNext()) {
                AbstractTagFrameBody body = ((AbstractID3v2Frame) listIterator.next()).getBody();
                if (body instanceof FrameBodyTXXX) {
                    FrameBodyTXXX frameBodyTXXX = (FrameBodyTXXX) body;
                    if (frameBodyTXXX.getDescription().equals(frameAndSubId.getSubId())) {
                        arrayList.addAll(frameBodyTXXX.getValues());
                    }
                } else if (body instanceof FrameBodyWXXX) {
                    FrameBodyWXXX frameBodyWXXX = (FrameBodyWXXX) body;
                    if (frameBodyWXXX.getDescription().equals(frameAndSubId.getSubId())) {
                        arrayList.addAll(frameBodyWXXX.getUrlLinks());
                    }
                } else if (body instanceof FrameBodyCOMM) {
                    FrameBodyCOMM frameBodyCOMM = (FrameBodyCOMM) body;
                    if (frameBodyCOMM.getDescription().equals(frameAndSubId.getSubId())) {
                        arrayList.addAll(frameBodyCOMM.getValues());
                    }
                } else if (body instanceof FrameBodyUFID) {
                    FrameBodyUFID frameBodyUFID = (FrameBodyUFID) body;
                    if (frameBodyUFID.getOwner().equals(frameAndSubId.getSubId()) && frameBodyUFID.getUniqueIdentifier() != null) {
                        arrayList.add(new String(frameBodyUFID.getUniqueIdentifier()));
                    }
                } else if (body instanceof AbstractFrameBodyPairs) {
                    for (Pair pair : ((AbstractFrameBodyPairs) body).getPairing().getMapping()) {
                        if (pair.getKey().equals(frameAndSubId.getSubId()) && pair.getValue() != null) {
                            arrayList.add(pair.getValue());
                        }
                    }
                } else {
                    logger.severe(getLoggingFilename() + ":Need to implement getFields(FieldKey genericKey) for:" + frameAndSubId + body.getClass());
                }
            }
        } else if (frameAndSubId.getGenericKey() != null && frameAndSubId.getGenericKey() == FieldKey.INVOLVEDPEOPLE) {
            ListIterator<TagField> listIterator2 = getFields(frameAndSubId.getFrameId()).listIterator();
            while (listIterator2.hasNext()) {
                AbstractTagFrameBody body2 = ((AbstractID3v2Frame) listIterator2.next()).getBody();
                if (body2 instanceof AbstractFrameBodyPairs) {
                    for (Pair pair2 : ((AbstractFrameBodyPairs) body2).getPairing().getMapping()) {
                        if (!pair2.getValue().isEmpty()) {
                            if (!pair2.getKey().isEmpty()) {
                                arrayList.add(pair2.getPairValue());
                            } else {
                                arrayList.add(pair2.getValue());
                            }
                        }
                    }
                }
            }
        } else {
            Iterator<TagField> it = getFields(frameAndSubId.getFrameId()).iterator();
            while (it.hasNext()) {
                AbstractID3v2Frame abstractID3v2Frame = (AbstractID3v2Frame) it.next();
                if (abstractID3v2Frame != null) {
                    if (abstractID3v2Frame.getBody() instanceof AbstractFrameBodyTextInfo) {
                        arrayList.addAll(((AbstractFrameBodyTextInfo) abstractID3v2Frame.getBody()).getValues());
                    } else {
                        arrayList.add(getTextValueForFrame(abstractID3v2Frame));
                    }
                }
            }
        }
        return arrayList;
    }

    protected String doGetValueAtIndex(FrameAndSubId frameAndSubId, int i) throws KeyNotFoundException {
        List<String> listDoGetValues = doGetValues(frameAndSubId);
        if (listDoGetValues.size() > i) {
            return listDoGetValues.get(i);
        }
        return "";
    }

    public TagField createLinkedArtworkField(String str) {
        AbstractID3v2Frame abstractID3v2FrameCreateFrame = createFrame(getFrameAndSubIdFromGenericKey(FieldKey.COVER_ART).getFrameId());
        if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyAPIC) {
            FrameBodyAPIC frameBodyAPIC = (FrameBodyAPIC) abstractID3v2FrameCreateFrame.getBody();
            frameBodyAPIC.setObjectValue(DataTypes.OBJ_PICTURE_DATA, str.getBytes(StandardCharsets.ISO_8859_1));
            frameBodyAPIC.setObjectValue(DataTypes.OBJ_PICTURE_TYPE, PictureTypes.DEFAULT_ID);
            frameBodyAPIC.setObjectValue(DataTypes.OBJ_MIME_TYPE, "-->");
            frameBodyAPIC.setObjectValue(DataTypes.OBJ_DESCRIPTION, "");
        } else if (abstractID3v2FrameCreateFrame.getBody() instanceof FrameBodyPIC) {
            FrameBodyPIC frameBodyPIC = (FrameBodyPIC) abstractID3v2FrameCreateFrame.getBody();
            frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_DATA, str.getBytes(StandardCharsets.ISO_8859_1));
            frameBodyPIC.setObjectValue(DataTypes.OBJ_PICTURE_TYPE, PictureTypes.DEFAULT_ID);
            frameBodyPIC.setObjectValue(DataTypes.OBJ_IMAGE_FORMAT, "-->");
            frameBodyPIC.setObjectValue(DataTypes.OBJ_DESCRIPTION, "");
        }
        return abstractID3v2FrameCreateFrame;
    }

    private void deleteNumberTotalFrame(FrameAndSubId frameAndSubId, FieldKey fieldKey, FieldKey fieldKey2, boolean z) throws KeyNotFoundException {
        if (z) {
            if (getFirst(fieldKey2).length() == 0) {
                doDeleteTagField(frameAndSubId);
                return;
            }
            List<TagField> frame = getFrame(frameAndSubId.getFrameId());
            if (frame.size() > 0) {
                AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal = (AbstractFrameBodyNumberTotal) ((AbstractID3v2Frame) frame.get(0)).getBody();
                if (abstractFrameBodyNumberTotal.getTotal().intValue() == 0) {
                    doDeleteTagField(frameAndSubId);
                    return;
                } else {
                    abstractFrameBodyNumberTotal.setNumber((Integer) 0);
                    return;
                }
            }
            return;
        }
        if (getFirst(fieldKey).length() == 0) {
            doDeleteTagField(frameAndSubId);
            return;
        }
        for (TagField tagField : getFrame(frameAndSubId.getFrameId())) {
            if (tagField instanceof AbstractID3v2Frame) {
                AbstractFrameBodyNumberTotal abstractFrameBodyNumberTotal2 = (AbstractFrameBodyNumberTotal) ((AbstractID3v2Frame) tagField).getBody();
                if (abstractFrameBodyNumberTotal2.getNumber().intValue() == 0) {
                    doDeleteTagField(frameAndSubId);
                }
                abstractFrameBodyNumberTotal2.setTotal((Integer) 0);
            }
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteField(FieldKey fieldKey) throws KeyNotFoundException {
        FrameAndSubId frameAndSubIdFromGenericKey = getFrameAndSubIdFromGenericKey(fieldKey);
        if (fieldKey == null) {
            throw new KeyNotFoundException();
        }
        switch (AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$FieldKey[fieldKey.ordinal()]) {
            case 1:
                deleteNumberTotalFrame(frameAndSubIdFromGenericKey, FieldKey.TRACK, FieldKey.TRACK_TOTAL, true);
                return;
            case 2:
                deleteNumberTotalFrame(frameAndSubIdFromGenericKey, FieldKey.TRACK, FieldKey.TRACK_TOTAL, false);
                return;
            case 3:
                deleteNumberTotalFrame(frameAndSubIdFromGenericKey, FieldKey.DISC_NO, FieldKey.DISC_TOTAL, true);
                return;
            case 4:
                deleteNumberTotalFrame(frameAndSubIdFromGenericKey, FieldKey.DISC_NO, FieldKey.DISC_TOTAL, false);
                return;
            case 5:
                deleteNumberTotalFrame(frameAndSubIdFromGenericKey, FieldKey.MOVEMENT_NO, FieldKey.MOVEMENT_TOTAL, true);
                return;
            case 6:
                deleteNumberTotalFrame(frameAndSubIdFromGenericKey, FieldKey.MOVEMENT_NO, FieldKey.MOVEMENT_TOTAL, false);
                return;
            default:
                doDeleteTagField(frameAndSubIdFromGenericKey);
                return;
        }
    }

    /* renamed from: org.jaudiotagger.tag.id3.AbstractID3v2Tag$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$FieldKey;

        static {
            int[] iArr = new int[FieldKey.values().length];
            $SwitchMap$org$jaudiotagger$tag$FieldKey = iArr;
            try {
                iArr[FieldKey.TRACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.TRACK_TOTAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.DISC_NO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.DISC_TOTAL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.MOVEMENT_NO.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$FieldKey[FieldKey.MOVEMENT_TOTAL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    protected void doDeleteTagField(FrameAndSubId frameAndSubId) throws KeyNotFoundException {
        if (frameAndSubId.getSubId() != null) {
            List<TagField> fields = getFields(frameAndSubId.getFrameId());
            ListIterator<TagField> listIterator = fields.listIterator();
            while (listIterator.hasNext()) {
                AbstractTagFrameBody body = ((AbstractID3v2Frame) listIterator.next()).getBody();
                if (body instanceof FrameBodyTXXX) {
                    if (((FrameBodyTXXX) body).getDescription().equals(frameAndSubId.getSubId())) {
                        if (fields.size() == 1) {
                            removeFrame(frameAndSubId.getFrameId());
                        } else {
                            listIterator.remove();
                        }
                    }
                } else if (body instanceof FrameBodyCOMM) {
                    if (((FrameBodyCOMM) body).getDescription().equals(frameAndSubId.getSubId())) {
                        if (fields.size() == 1) {
                            removeFrame(frameAndSubId.getFrameId());
                        } else {
                            listIterator.remove();
                        }
                    }
                } else if (body instanceof FrameBodyWXXX) {
                    if (((FrameBodyWXXX) body).getDescription().equals(frameAndSubId.getSubId())) {
                        if (fields.size() == 1) {
                            removeFrame(frameAndSubId.getFrameId());
                        } else {
                            listIterator.remove();
                        }
                    }
                } else if (body instanceof FrameBodyUFID) {
                    if (((FrameBodyUFID) body).getOwner().equals(frameAndSubId.getSubId())) {
                        if (fields.size() == 1) {
                            removeFrame(frameAndSubId.getFrameId());
                        } else {
                            listIterator.remove();
                        }
                    }
                } else {
                    throw new RuntimeException("Need to implement getFields(FieldKey genericKey) for:" + body.getClass());
                }
            }
            return;
        }
        if (frameAndSubId.getSubId() == null) {
            removeFrame(frameAndSubId.getFrameId());
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public List<TagField> getFields(FieldKey fieldKey) throws KeyNotFoundException {
        if (fieldKey == null) {
            throw new IllegalArgumentException(ErrorMessage.GENERAL_INVALID_NULL_ARGUMENT.getMsg());
        }
        FrameAndSubId frameAndSubIdFromGenericKey = getFrameAndSubIdFromGenericKey(fieldKey);
        if (frameAndSubIdFromGenericKey == null) {
            throw new KeyNotFoundException();
        }
        List<TagField> fields = getFields(frameAndSubIdFromGenericKey.getFrameId());
        ArrayList arrayList = new ArrayList();
        if (frameAndSubIdFromGenericKey.getSubId() != null) {
            for (TagField tagField : fields) {
                AbstractTagFrameBody body = ((AbstractID3v2Frame) tagField).getBody();
                if (body instanceof FrameBodyTXXX) {
                    if (((FrameBodyTXXX) body).getDescription().equals(frameAndSubIdFromGenericKey.getSubId())) {
                        arrayList.add(tagField);
                    }
                } else if (body instanceof FrameBodyWXXX) {
                    if (((FrameBodyWXXX) body).getDescription().equals(frameAndSubIdFromGenericKey.getSubId())) {
                        arrayList.add(tagField);
                    }
                } else if (body instanceof FrameBodyCOMM) {
                    if (((FrameBodyCOMM) body).getDescription().equals(frameAndSubIdFromGenericKey.getSubId())) {
                        arrayList.add(tagField);
                    }
                } else if (body instanceof FrameBodyUFID) {
                    if (((FrameBodyUFID) body).getOwner().equals(frameAndSubIdFromGenericKey.getSubId())) {
                        arrayList.add(tagField);
                    }
                } else if (body instanceof FrameBodyIPLS) {
                    Iterator<Pair> it = ((FrameBodyIPLS) body).getPairing().getMapping().iterator();
                    while (it.hasNext()) {
                        if (it.next().getKey().equals(frameAndSubIdFromGenericKey.getSubId())) {
                            arrayList.add(tagField);
                        }
                    }
                } else if (body instanceof FrameBodyTIPL) {
                    Iterator<Pair> it2 = ((FrameBodyTIPL) body).getPairing().getMapping().iterator();
                    while (it2.hasNext()) {
                        if (it2.next().getKey().equals(frameAndSubIdFromGenericKey.getSubId())) {
                            arrayList.add(tagField);
                        }
                    }
                } else {
                    logger.severe("Need to implement getFields(FieldKey genericKey) for:" + frameAndSubIdFromGenericKey + body.getClass());
                }
            }
            return arrayList;
        }
        if (ID3NumberTotalFields.isNumber(fieldKey)) {
            for (TagField tagField2 : fields) {
                AbstractTagFrameBody body2 = ((AbstractID3v2Frame) tagField2).getBody();
                if ((body2 instanceof AbstractFrameBodyNumberTotal) && ((AbstractFrameBodyNumberTotal) body2).getNumber() != null) {
                    arrayList.add(tagField2);
                }
            }
            return arrayList;
        }
        if (!ID3NumberTotalFields.isTotal(fieldKey)) {
            return fields;
        }
        for (TagField tagField3 : fields) {
            AbstractTagFrameBody body3 = ((AbstractID3v2Frame) tagField3).getBody();
            if ((body3 instanceof AbstractFrameBodyNumberTotal) && ((AbstractFrameBodyNumberTotal) body3).getTotal() != null) {
                arrayList.add(tagField3);
            }
        }
        return arrayList;
    }

    class FrameAndSubId {
        private String frameId;
        private FieldKey genericKey;
        private String subId;

        public FrameAndSubId(FieldKey fieldKey, String str, String str2) {
            this.genericKey = fieldKey;
            this.frameId = str;
            this.subId = str2;
        }

        public FieldKey getGenericKey() {
            return this.genericKey;
        }

        public String getFrameId() {
            return this.frameId;
        }

        public String getSubId() {
            return this.subId;
        }

        public String toString() {
            return String.format("%s:%s:%s", this.genericKey.name(), this.frameId, this.subId);
        }
    }

    @Override // org.jaudiotagger.tag.Tag
    public Artwork getFirstArtwork() {
        List<Artwork> artworkList = getArtworkList();
        if (artworkList.size() > 0) {
            return artworkList.get(0);
        }
        return null;
    }

    @Override // org.jaudiotagger.tag.Tag
    public void setField(Artwork artwork) throws FieldDataInvalidException {
        setField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void addField(Artwork artwork) throws FieldDataInvalidException {
        addField(createField(artwork));
    }

    @Override // org.jaudiotagger.tag.Tag
    public void deleteArtworkField() throws KeyNotFoundException {
        deleteField(FieldKey.COVER_ART);
    }

    @Override // org.jaudiotagger.tag.Tag
    public String toString() {
        StringBuilder sb = new StringBuilder("Tag content:\n");
        Iterator<TagField> fields = getFields();
        while (fields.hasNext()) {
            TagField next = fields.next();
            sb.append("\t");
            sb.append(next.getId());
            sb.append(":");
            sb.append(next.toString());
            sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
        }
        return sb.toString();
    }

    @Override // org.jaudiotagger.tag.Tag
    public TagField createCompilationField(boolean z) throws KeyNotFoundException, FieldDataInvalidException {
        return z ? createField(FieldKey.IS_COMPILATION, "1") : createField(FieldKey.IS_COMPILATION, SessionDescription.SUPPORTED_SDP_VERSION);
    }

    public Long getStartLocationInFile() {
        return this.startLocationInFile;
    }

    public void setStartLocationInFile(long j) {
        this.startLocationInFile = Long.valueOf(j);
    }

    public Long getEndLocationInFile() {
        return this.endLocationInFile;
    }

    public void setEndLocationInFile(long j) {
        this.endLocationInFile = Long.valueOf(j);
    }
}
