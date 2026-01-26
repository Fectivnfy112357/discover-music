package org.jaudiotagger.audio.ogg;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.logging.Logger;
import org.jaudiotagger.audio.ogg.util.VorbisHeader;
import org.jaudiotagger.audio.ogg.util.VorbisPacketType;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentCreator;

/* loaded from: classes3.dex */
public class OggVorbisCommentTagCreator {
    public static final int FIELD_FRAMING_BIT_LENGTH = 1;
    public static final byte FRAMING_BIT_VALID_VALUE = 1;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg");
    private VorbisCommentCreator creator = new VorbisCommentCreator();

    public ByteBuffer convert(Tag tag) throws UnsupportedEncodingException {
        ByteBuffer byteBufferConvertMetadata = this.creator.convertMetadata(tag);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBufferConvertMetadata.capacity() + 8);
        byteBufferAllocate.put((byte) VorbisPacketType.COMMENT_HEADER.getType());
        byteBufferAllocate.put(VorbisHeader.CAPTURE_PATTERN_AS_BYTES);
        byteBufferAllocate.put(byteBufferConvertMetadata);
        byteBufferAllocate.put((byte) 1);
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }
}
