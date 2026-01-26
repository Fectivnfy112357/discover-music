package org.jaudiotagger.audio.asf.data;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public final class ContentDescription extends MetadataContainer {
    public static final String KEY_AUTHOR = "AUTHOR";
    public static final String KEY_COPYRIGHT = "COPYRIGHT";
    public static final String KEY_DESCRIPTION = "DESCRIPTION";
    public static final String KEY_RATING = "RATING";
    public static final String KEY_TITLE = "TITLE";
    public static final Set<String> ALLOWED = new HashSet(Arrays.asList(KEY_AUTHOR, KEY_COPYRIGHT, KEY_DESCRIPTION, KEY_RATING, KEY_TITLE));

    public ContentDescription() {
        this(0L, BigInteger.ZERO);
    }

    public ContentDescription(long j, BigInteger bigInteger) {
        super(ContainerType.CONTENT_DESCRIPTION, j, bigInteger);
    }

    public String getAuthor() {
        return getValueFor(KEY_AUTHOR);
    }

    public String getComment() {
        return getValueFor(KEY_DESCRIPTION);
    }

    public String getCopyRight() {
        return getValueFor(KEY_COPYRIGHT);
    }

    @Override // org.jaudiotagger.audio.asf.data.MetadataContainer, org.jaudiotagger.audio.asf.io.WriteableChunk
    public long getCurrentAsfChunkSize() {
        return (getAuthor().length() * 2) + 44 + (getComment().length() * 2) + (getRating().length() * 2) + (getTitle().length() * 2) + (getCopyRight().length() * 2);
    }

    public String getRating() {
        return getValueFor(KEY_RATING);
    }

    public String getTitle() {
        return getValueFor(KEY_TITLE);
    }

    @Override // org.jaudiotagger.audio.asf.data.MetadataContainer
    public boolean isAddSupported(MetadataDescriptor metadataDescriptor) {
        return ALLOWED.contains(metadataDescriptor.getName()) && super.isAddSupported(metadataDescriptor);
    }

    @Override // org.jaudiotagger.audio.asf.data.MetadataContainer, org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        sb.append(str).append("  |->Title      : ").append(getTitle()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |->Author     : ").append(getAuthor()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |->Copyright  : ").append(getCopyRight()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |->Description: ").append(getComment()).append(Utils.LINE_SEPARATOR);
        sb.append(str).append("  |->Rating     :").append(getRating()).append(Utils.LINE_SEPARATOR);
        return sb.toString();
    }

    public void setAuthor(String str) throws IllegalArgumentException {
        setStringValue(KEY_AUTHOR, str);
    }

    public void setComment(String str) throws IllegalArgumentException {
        setStringValue(KEY_DESCRIPTION, str);
    }

    public void setCopyright(String str) throws IllegalArgumentException {
        setStringValue(KEY_COPYRIGHT, str);
    }

    public void setRating(String str) throws IllegalArgumentException {
        setStringValue(KEY_RATING, str);
    }

    public void setTitle(String str) throws IllegalArgumentException {
        setStringValue(KEY_TITLE, str);
    }

    @Override // org.jaudiotagger.audio.asf.data.MetadataContainer, org.jaudiotagger.audio.asf.io.WriteableChunk
    public long writeInto(OutputStream outputStream) throws IOException {
        long currentAsfChunkSize = getCurrentAsfChunkSize();
        outputStream.write(getGuid().getBytes());
        Utils.writeUINT64(getCurrentAsfChunkSize(), outputStream);
        Utils.writeUINT16((getTitle().length() * 2) + 2, outputStream);
        Utils.writeUINT16((getAuthor().length() * 2) + 2, outputStream);
        Utils.writeUINT16((getCopyRight().length() * 2) + 2, outputStream);
        Utils.writeUINT16((getComment().length() * 2) + 2, outputStream);
        Utils.writeUINT16((getRating().length() * 2) + 2, outputStream);
        outputStream.write(Utils.getBytes(getTitle(), AsfHeader.ASF_CHARSET));
        outputStream.write(AsfHeader.ZERO_TERM);
        outputStream.write(Utils.getBytes(getAuthor(), AsfHeader.ASF_CHARSET));
        outputStream.write(AsfHeader.ZERO_TERM);
        outputStream.write(Utils.getBytes(getCopyRight(), AsfHeader.ASF_CHARSET));
        outputStream.write(AsfHeader.ZERO_TERM);
        outputStream.write(Utils.getBytes(getComment(), AsfHeader.ASF_CHARSET));
        outputStream.write(AsfHeader.ZERO_TERM);
        outputStream.write(Utils.getBytes(getRating(), AsfHeader.ASF_CHARSET));
        outputStream.write(AsfHeader.ZERO_TERM);
        return currentAsfChunkSize;
    }
}
