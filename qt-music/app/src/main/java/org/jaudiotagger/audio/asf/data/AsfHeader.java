package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public final class AsfHeader extends ChunkContainer {
    public static final Charset ASF_CHARSET = Charset.forName("UTF-16LE");
    public static final byte[] ZERO_TERM = {0, 0};
    private final long chunkCount;

    static {
        new HashSet().add(GUID.GUID_STREAM);
    }

    public AsfHeader(long j, BigInteger bigInteger, long j2) {
        super(GUID.GUID_HEADER, j, bigInteger);
        this.chunkCount = j2;
    }

    public ContentDescription findContentDescription() {
        ContentDescription contentDescription = getContentDescription();
        return (contentDescription != null || getExtendedHeader() == null) ? contentDescription : getExtendedHeader().getContentDescription();
    }

    public MetadataContainer findExtendedContentDescription() {
        MetadataContainer extendedContentDescription = getExtendedContentDescription();
        return (extendedContentDescription != null || getExtendedHeader() == null) ? extendedContentDescription : getExtendedHeader().getExtendedContentDescription();
    }

    public MetadataContainer findMetadataContainer(ContainerType containerType) {
        MetadataContainer metadataContainer = (MetadataContainer) getFirst(containerType.getContainerGUID(), MetadataContainer.class);
        return metadataContainer == null ? (MetadataContainer) getExtendedHeader().getFirst(containerType.getContainerGUID(), MetadataContainer.class) : metadataContainer;
    }

    public AudioStreamChunk getAudioStreamChunk() {
        List<Chunk> listAssertChunkList = assertChunkList(GUID.GUID_STREAM);
        AudioStreamChunk audioStreamChunk = null;
        for (int i = 0; i < listAssertChunkList.size() && audioStreamChunk == null; i++) {
            if (listAssertChunkList.get(i) instanceof AudioStreamChunk) {
                audioStreamChunk = (AudioStreamChunk) listAssertChunkList.get(i);
            }
        }
        return audioStreamChunk;
    }

    public long getChunkCount() {
        return this.chunkCount;
    }

    public ContentDescription getContentDescription() {
        return (ContentDescription) getFirst(GUID.GUID_CONTENTDESCRIPTION, ContentDescription.class);
    }

    public EncodingChunk getEncodingChunk() {
        return (EncodingChunk) getFirst(GUID.GUID_ENCODING, EncodingChunk.class);
    }

    public EncryptionChunk getEncryptionChunk() {
        return (EncryptionChunk) getFirst(GUID.GUID_CONTENT_ENCRYPTION, EncryptionChunk.class);
    }

    public MetadataContainer getExtendedContentDescription() {
        return (MetadataContainer) getFirst(GUID.GUID_EXTENDED_CONTENT_DESCRIPTION, MetadataContainer.class);
    }

    public AsfExtendedHeader getExtendedHeader() {
        return (AsfExtendedHeader) getFirst(GUID.GUID_HEADER_EXTENSION, AsfExtendedHeader.class);
    }

    public FileHeader getFileHeader() {
        return (FileHeader) getFirst(GUID.GUID_FILE, FileHeader.class);
    }

    public StreamBitratePropertiesChunk getStreamBitratePropertiesChunk() {
        return (StreamBitratePropertiesChunk) getFirst(GUID.GUID_STREAM_BITRATE_PROPERTIES, StreamBitratePropertiesChunk.class);
    }

    @Override // org.jaudiotagger.audio.asf.data.ChunkContainer, org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        return new StringBuilder(super.prettyPrint(str, str + "  | : Contains: \"" + getChunkCount() + "\" chunks" + Utils.LINE_SEPARATOR)).toString();
    }
}
