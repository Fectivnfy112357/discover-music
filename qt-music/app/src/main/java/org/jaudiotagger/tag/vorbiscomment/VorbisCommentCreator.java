package org.jaudiotagger.tag.vorbiscomment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.jaudiotagger.audio.generic.AbstractTagCreator;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;

/* loaded from: classes3.dex */
public class VorbisCommentCreator extends AbstractTagCreator {
    @Override // org.jaudiotagger.audio.generic.AbstractTagCreator
    public ByteBuffer convertMetadata(Tag tag, boolean z) throws UnsupportedEncodingException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            String vendor = ((VorbisCommentTag) tag).getVendor();
            byteArrayOutputStream.write(Utils.getSizeLEInt32(vendor.getBytes(StandardCharsets.UTF_8).length));
            byteArrayOutputStream.write(vendor.getBytes(StandardCharsets.UTF_8));
            int fieldCount = tag.getFieldCount();
            if (((VorbisCommentTag) tag).hasField(VorbisCommentFieldKey.VENDOR)) {
                fieldCount--;
            }
            byteArrayOutputStream.write(Utils.getSizeLEInt32(fieldCount));
            Iterator<TagField> fields = tag.getFields();
            while (fields.hasNext()) {
                TagField next = fields.next();
                if (!next.getId().equals(VorbisCommentFieldKey.VENDOR.getFieldName())) {
                    byteArrayOutputStream.write(next.getRawContent());
                }
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            byteBufferWrap.rewind();
            return byteBufferWrap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
