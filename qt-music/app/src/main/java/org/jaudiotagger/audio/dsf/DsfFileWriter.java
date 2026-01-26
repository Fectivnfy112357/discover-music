package org.jaudiotagger.audio.dsf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.NoWritePermissionsException;
import org.jaudiotagger.audio.generic.AudioFileWriter2;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

/* loaded from: classes3.dex */
public class DsfFileWriter extends AudioFileWriter2 {
    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void writeTag(Tag tag, Path path) throws IOException, CannotWriteException {
        try {
            FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
            try {
                DsdChunk chunk = DsdChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, DsdChunk.DSD_HEADER_LENGTH));
                if (chunk != null) {
                    if (chunk.getMetadataOffset() > 0) {
                        fileChannelOpen.position(chunk.getMetadataOffset());
                        if (fileChannelOpen.size() - fileChannelOpen.position() < DsfChunkType.ID3.getCode().length() || ID3Chunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, (int) (fileChannelOpen.size() - fileChannelOpen.position()))) != null) {
                            fileChannelOpen.position(chunk.getMetadataOffset());
                            fileChannelOpen.truncate(fileChannelOpen.position());
                            fileChannelOpen.write(convert((AbstractID3v2Tag) tag));
                            chunk.setFileLength(fileChannelOpen.size());
                            fileChannelOpen.position(0L);
                            fileChannelOpen.write(chunk.write());
                        } else {
                            throw new CannotWriteException(path + "Could not find existing ID3v2 Tag (1)");
                        }
                    } else {
                        fileChannelOpen.position(fileChannelOpen.size());
                        chunk.setMetadataOffset(fileChannelOpen.size());
                        fileChannelOpen.write(convert((AbstractID3v2Tag) tag));
                        chunk.setFileLength(fileChannelOpen.size());
                        fileChannelOpen.position(0L);
                        fileChannelOpen.write(chunk.write());
                    }
                }
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
            } catch (Throwable th) {
                if (fileChannelOpen != null) {
                    try {
                        fileChannelOpen.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (AccessDeniedException e) {
            throw new NoWritePermissionsException(path + ":" + e.getMessage());
        } catch (IOException e2) {
            throw new CannotWriteException(e2.getMessage());
        }
    }

    public ByteBuffer convert(AbstractID3v2Tag abstractID3v2Tag) throws UnsupportedEncodingException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            long size = abstractID3v2Tag.getSize();
            if (size > 0 && Utils.isOddLength(size)) {
                size++;
            }
            abstractID3v2Tag.write(byteArrayOutputStream, (int) size);
            if (Utils.isOddLength(byteArrayOutputStream.toByteArray().length)) {
                int length = byteArrayOutputStream.toByteArray().length + 1;
                ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                abstractID3v2Tag.write(byteArrayOutputStream2, length);
                byteArrayOutputStream = byteArrayOutputStream2;
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            byteBufferWrap.rewind();
            return byteBufferWrap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileWriter2
    protected void deleteTag(Tag tag, Path path) throws IOException, CannotWriteException {
        try {
            FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
            try {
                DsdChunk chunk = DsdChunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, DsdChunk.DSD_HEADER_LENGTH));
                if (chunk != null && chunk.getMetadataOffset() > 0) {
                    fileChannelOpen.position(chunk.getMetadataOffset());
                    if (ID3Chunk.readChunk(Utils.readFileDataIntoBufferLE(fileChannelOpen, (int) (fileChannelOpen.size() - fileChannelOpen.position()))) != null) {
                        fileChannelOpen.truncate(chunk.getMetadataOffset());
                        chunk.setMetadataOffset(0L);
                        chunk.setFileLength(fileChannelOpen.size());
                        fileChannelOpen.position(0L);
                        fileChannelOpen.write(chunk.write());
                    }
                }
                if (fileChannelOpen != null) {
                    fileChannelOpen.close();
                }
            } finally {
            }
        } catch (IOException e) {
            throw new CannotWriteException(path + ":" + e.getMessage());
        }
    }
}
