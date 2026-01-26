package org.jaudiotagger.tag.mp4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import org.jaudiotagger.audio.generic.AbstractTagCreator;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.audio.mp4.Mp4AtomIdentifier;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.field.Mp4TagCoverField;

/* loaded from: classes3.dex */
public class Mp4TagCreator extends AbstractTagCreator {
    @Override // org.jaudiotagger.audio.generic.AbstractTagCreator
    public ByteBuffer convertMetadata(Tag tag, boolean z) throws UnsupportedEncodingException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Iterator<TagField> fields = tag.getFields();
            boolean z2 = false;
            while (fields.hasNext()) {
                TagField next = fields.next();
                if (!(next instanceof Mp4TagCoverField)) {
                    byteArrayOutputStream.write(next.getRawContent());
                } else if (!z2) {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        Iterator<TagField> it = tag.getFields(FieldKey.COVER_ART).iterator();
                        while (it.hasNext()) {
                            byteArrayOutputStream2.write(((Mp4TagField) it.next()).getRawContentDataOnly());
                        }
                        byte[] byteArray = byteArrayOutputStream2.toByteArray();
                        byteArrayOutputStream.write(Utils.getSizeBEInt32(byteArray.length + 8));
                        byteArrayOutputStream.write(Mp4FieldKey.ARTWORK.getFieldName().getBytes(StandardCharsets.ISO_8859_1));
                        byteArrayOutputStream.write(byteArray);
                        z2 = true;
                    } catch (KeyNotFoundException unused) {
                        throw new RuntimeException("Unable to find COVERART Key");
                    }
                }
            }
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            byteArrayOutputStream3.write(Utils.getSizeBEInt32(byteArrayOutputStream.size() + 8));
            byteArrayOutputStream3.write(Mp4AtomIdentifier.ILST.getFieldName().getBytes(StandardCharsets.ISO_8859_1));
            byteArrayOutputStream3.write(byteArrayOutputStream.toByteArray());
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream3.toByteArray());
            byteBufferWrap.rewind();
            return byteBufferWrap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
