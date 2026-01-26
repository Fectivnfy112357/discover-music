package org.jaudiotagger.audio.flac;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.logging.Logger;
import org.jaudiotagger.audio.flac.metadatablock.BlockType;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockHeader;
import org.jaudiotagger.audio.generic.AbstractTagCreator;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentCreator;

/* loaded from: classes3.dex */
public class FlacTagCreator extends AbstractTagCreator {
    public static final int DEFAULT_PADDING = 4000;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac");
    private static final VorbisCommentCreator creator = new VorbisCommentCreator();

    @Override // org.jaudiotagger.audio.generic.AbstractTagCreator
    public ByteBuffer convertMetadata(Tag tag, boolean z) throws UnsupportedEncodingException {
        ByteBuffer byteBufferConvertMetadata;
        int iLimit;
        MetadataBlockHeader metadataBlockHeader;
        MetadataBlockHeader metadataBlockHeader2;
        FlacTag flacTag = (FlacTag) tag;
        if (flacTag.getVorbisCommentTag() != null) {
            byteBufferConvertMetadata = creator.convertMetadata(flacTag.getVorbisCommentTag());
            iLimit = byteBufferConvertMetadata.capacity() + 4;
        } else {
            byteBufferConvertMetadata = null;
            iLimit = 0;
        }
        Iterator<MetadataBlockDataPicture> it = flacTag.getImages().iterator();
        while (it.hasNext()) {
            iLimit += it.next().getBytes().limit() + 4;
        }
        logger.config("Convert flac tag:taglength:" + iLimit);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(iLimit);
        if (flacTag.getVorbisCommentTag() != null) {
            if (z || flacTag.getImages().size() > 0) {
                metadataBlockHeader2 = new MetadataBlockHeader(false, BlockType.VORBIS_COMMENT, byteBufferConvertMetadata.capacity());
            } else {
                metadataBlockHeader2 = new MetadataBlockHeader(true, BlockType.VORBIS_COMMENT, byteBufferConvertMetadata.capacity());
            }
            byteBufferAllocate.put(metadataBlockHeader2.getBytes());
            byteBufferAllocate.put(byteBufferConvertMetadata);
        }
        ListIterator<MetadataBlockDataPicture> listIterator = flacTag.getImages().listIterator();
        while (listIterator.hasNext()) {
            MetadataBlockDataPicture next = listIterator.next();
            if (z || listIterator.hasNext()) {
                metadataBlockHeader = new MetadataBlockHeader(false, BlockType.PICTURE, next.getLength());
            } else {
                metadataBlockHeader = new MetadataBlockHeader(true, BlockType.PICTURE, next.getLength());
            }
            byteBufferAllocate.put(metadataBlockHeader.getBytes());
            byteBufferAllocate.put(next.getBytes());
        }
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }
}
