package org.jaudiotagger.audio.asf;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.jaudiotagger.audio.SupportedFileFormat;
import org.jaudiotagger.audio.asf.data.AsfHeader;
import org.jaudiotagger.audio.asf.data.MetadataContainer;
import org.jaudiotagger.audio.asf.data.MetadataDescriptor;
import org.jaudiotagger.audio.asf.io.AsfExtHeaderReader;
import org.jaudiotagger.audio.asf.io.AsfHeaderReader;
import org.jaudiotagger.audio.asf.io.ContentBrandingReader;
import org.jaudiotagger.audio.asf.io.ContentDescriptionReader;
import org.jaudiotagger.audio.asf.io.FileHeaderReader;
import org.jaudiotagger.audio.asf.io.LanguageListReader;
import org.jaudiotagger.audio.asf.io.MetadataReader;
import org.jaudiotagger.audio.asf.io.StreamChunkReader;
import org.jaudiotagger.audio.asf.util.TagConverter;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.tag.asf.AsfTag;

/* loaded from: classes3.dex */
public class AsfFileReader extends AudioFileReader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final AsfHeaderReader HEADER_READER;
    private static final Logger LOGGER = Logger.getLogger("org.jaudiotagger.audio.asf");

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentDescriptionReader.class);
        arrayList.add(ContentBrandingReader.class);
        arrayList.add(MetadataReader.class);
        arrayList.add(LanguageListReader.class);
        AsfExtHeaderReader asfExtHeaderReader = new AsfExtHeaderReader(arrayList, true);
        arrayList.add(FileHeaderReader.class);
        arrayList.add(StreamChunkReader.class);
        AsfHeaderReader asfHeaderReader = new AsfHeaderReader(arrayList, true);
        HEADER_READER = asfHeaderReader;
        asfHeaderReader.setExtendedHeaderReader(asfExtHeaderReader);
    }

    private boolean determineVariableBitrate(AsfHeader asfHeader) {
        List<MetadataDescriptor> descriptorsByName;
        MetadataContainer metadataContainerFindExtendedContentDescription = asfHeader.findExtendedContentDescription();
        if (metadataContainerFindExtendedContentDescription == null || (descriptorsByName = metadataContainerFindExtendedContentDescription.getDescriptorsByName("IsVBR")) == null || descriptorsByName.isEmpty()) {
            return false;
        }
        return Boolean.TRUE.toString().equals(descriptorsByName.get(0).getString());
    }

    private GenericAudioHeader getAudioHeader(AsfHeader asfHeader) throws CannotReadException {
        GenericAudioHeader genericAudioHeader = new GenericAudioHeader();
        if (asfHeader.getFileHeader() == null) {
            throw new CannotReadException("Invalid ASF/WMA file. File header object not available.");
        }
        if (asfHeader.getAudioStreamChunk() == null) {
            throw new CannotReadException("Invalid ASF/WMA file. No audio stream contained.");
        }
        genericAudioHeader.setBitRate(asfHeader.getAudioStreamChunk().getKbps());
        genericAudioHeader.setChannelNumber((int) asfHeader.getAudioStreamChunk().getChannelCount());
        genericAudioHeader.setFormat(SupportedFileFormat.WMA.getDisplayName());
        genericAudioHeader.setEncodingType("ASF (audio): " + asfHeader.getAudioStreamChunk().getCodecDescription());
        genericAudioHeader.setLossless(asfHeader.getAudioStreamChunk().getCompressionFormat() == 355);
        genericAudioHeader.setPreciseLength(asfHeader.getFileHeader().getPreciseDuration());
        genericAudioHeader.setSamplingRate((int) asfHeader.getAudioStreamChunk().getSamplingRate());
        genericAudioHeader.setVariableBitRate(determineVariableBitrate(asfHeader));
        genericAudioHeader.setBitsPerSample(asfHeader.getAudioStreamChunk().getBitsPerSample());
        return genericAudioHeader;
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected GenericAudioHeader getEncodingInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        randomAccessFile.seek(0L);
        try {
            AsfHeader infoHeader = AsfHeaderReader.readInfoHeader(randomAccessFile);
            if (infoHeader == null) {
                throw new CannotReadException("Some values must have been incorrect for interpretation as asf with wma content.");
            }
            return getAudioHeader(infoHeader);
        } catch (Exception e) {
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            if (e instanceof CannotReadException) {
                throw ((CannotReadException) e);
            }
            throw new CannotReadException("Failed to read. Cause: " + e.getMessage(), e);
        }
    }

    private AsfTag getTag(AsfHeader asfHeader) {
        return TagConverter.createTagOf(asfHeader);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    public AsfTag getTag(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        randomAccessFile.seek(0L);
        try {
            AsfHeader tagHeader = AsfHeaderReader.readTagHeader(randomAccessFile);
            if (tagHeader == null) {
                throw new CannotReadException("Some values must have been incorrect for interpretation as asf with wma content.");
            }
            return TagConverter.createTagOf(tagHeader);
        } catch (Exception e) {
            logger.severe(e.getMessage());
            if (e instanceof IOException) {
                throw ((IOException) e);
            }
            if (e instanceof CannotReadException) {
                throw ((CannotReadException) e);
            }
            throw new CannotReadException("Failed to read. Cause: " + e.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x013a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.jaudiotagger.audio.AudioFile read(java.io.File r12) throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 346
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.asf.AsfFileReader.read(java.io.File):org.jaudiotagger.audio.AudioFile");
    }
}
