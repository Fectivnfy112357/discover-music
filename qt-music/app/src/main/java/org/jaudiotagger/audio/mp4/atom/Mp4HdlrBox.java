package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.mp4.Mp4AtomIdentifier;

/* loaded from: classes3.dex */
public class Mp4HdlrBox extends AbstractMp4Box {
    public static final int HANDLER_LENGTH = 4;
    public static final int HANDLER_POS = 8;
    public static final int ITUNES_META_HDLR_DAT_LENGTH = 26;
    public static final int NAME_LENGTH = 2;
    public static final int OTHER_FLAG_LENGTH = 3;
    public static final int RESERVED1_LENGTH = 4;
    public static final int RESERVED1_POS = 12;
    public static final int RESERVED2_LENGTH = 4;
    public static final int RESERVED3_LENGTH = 4;
    public static final int RESERVED_FLAG_LENGTH = 4;
    public static final int VERSION_FLAG_LENGTH = 1;
    private static Map<String, MediaDataType> mediaDataTypeMap = new HashMap();
    private String handlerType;
    private MediaDataType mediaDataType;

    static {
        for (MediaDataType mediaDataType : MediaDataType.values()) {
            mediaDataTypeMap.put(mediaDataType.getId(), mediaDataType);
        }
    }

    public Mp4HdlrBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer;
    }

    public void processData() throws CannotReadException {
        this.dataBuffer.position(this.dataBuffer.position() + 8);
        try {
            this.handlerType = Charset.forName("ISO-8859-1").newDecoder().decode((ByteBuffer) this.dataBuffer.slice().limit(4)).toString();
        } catch (CharacterCodingException unused) {
        }
        this.mediaDataType = mediaDataTypeMap.get(this.handlerType);
    }

    public String getHandlerType() {
        return this.handlerType;
    }

    public MediaDataType getMediaDataType() {
        return this.mediaDataType;
    }

    public String toString() {
        return "handlerType:" + this.handlerType + ":human readable:" + this.mediaDataType.getDescription();
    }

    public enum MediaDataType {
        ODSM("odsm", "ObjectDescriptorStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        CRSM("crsm", "ClockReferenceStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        SDSM("sdsm", "SceneDescriptionStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        M7SM("m7sm", "MPEG7Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        OCSM("ocsm", "ObjectContentInfoStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        IPSM("ipsm", "IPMP Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        MJSM("mjsm", "MPEG-J Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO"),
        MDIR("mdir", "Apple Meta Data iTunes Reader"),
        MP7B("mp7b", "MPEG-7 binary XML"),
        MP7T("mp7t", "MPEG-7 XML"),
        VIDE("vide", "Video Track"),
        SOUN("soun", "Sound Track"),
        HINT("hint", "Hint Track"),
        APPL("appl", "Apple specific"),
        META("meta", "Timed Metadata track - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");

        private String description;
        private String id;

        MediaDataType(String str, String str2) {
            this.id = str;
            this.description = str2;
        }

        public String getId() {
            return this.id;
        }

        public String getDescription() {
            return this.description;
        }
    }

    public static Mp4HdlrBox createiTunesStyleHdlrBox() {
        Mp4BoxHeader mp4BoxHeader = new Mp4BoxHeader(Mp4AtomIdentifier.HDLR.getFieldName());
        mp4BoxHeader.setLength(34);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(26);
        byteBufferAllocate.put(8, (byte) 109);
        byteBufferAllocate.put(9, (byte) 100);
        byteBufferAllocate.put(10, (byte) 105);
        byteBufferAllocate.put(11, (byte) 114);
        byteBufferAllocate.put(12, (byte) 97);
        byteBufferAllocate.put(13, (byte) 112);
        byteBufferAllocate.put(14, (byte) 112);
        byteBufferAllocate.put(15, (byte) 108);
        byteBufferAllocate.rewind();
        return new Mp4HdlrBox(mp4BoxHeader, byteBufferAllocate);
    }
}
