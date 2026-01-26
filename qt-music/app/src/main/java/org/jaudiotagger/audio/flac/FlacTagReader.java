package org.jaudiotagger.audio.flac;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.flac.metadatablock.BlockType;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockDataPicture;
import org.jaudiotagger.audio.flac.metadatablock.MetadataBlockHeader;
import org.jaudiotagger.logging.Hex;
import org.jaudiotagger.tag.InvalidFrameException;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentReader;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;

/* loaded from: classes3.dex */
public class FlacTagReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac");
    private VorbisCommentReader vorbisCommentReader = new VorbisCommentReader();

    public FlacTag read(Path path) throws CannotReadException, IOException {
        FileChannel fileChannelOpen = FileChannel.open(path, new OpenOption[0]);
        try {
            new FlacStreamReader(fileChannelOpen, path.toString() + " ").findStream();
            ArrayList arrayList = new ArrayList();
            VorbisCommentTag vorbisCommentTagCreateNewTag = null;
            boolean zIsLastBlock = false;
            while (!zIsLastBlock) {
                if (logger.isLoggable(Level.CONFIG)) {
                    logger.config(path + " Looking for MetaBlockHeader at:" + fileChannelOpen.position());
                }
                MetadataBlockHeader header = MetadataBlockHeader.readHeader(fileChannelOpen);
                if (header == null) {
                    break;
                }
                if (logger.isLoggable(Level.CONFIG)) {
                    logger.config(path + " Reading MetadataBlockHeader:" + header.toString() + " ending at " + fileChannelOpen.position());
                }
                if (header.getBlockType() != null) {
                    int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[header.getBlockType().ordinal()];
                    if (i == 1) {
                        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(header.getDataLength());
                        fileChannelOpen.read(byteBufferAllocate);
                        vorbisCommentTagCreateNewTag = this.vorbisCommentReader.read(byteBufferAllocate.array(), false, path);
                    } else if (i == 2) {
                        try {
                            arrayList.add(new MetadataBlockDataPicture(header, fileChannelOpen));
                        } catch (IOException e) {
                            logger.warning(path + "Unable to read picture metablock, ignoring:" + e.getMessage());
                        } catch (InvalidFrameException e2) {
                            logger.warning(path + "Unable to read picture metablock, ignoring" + e2.getMessage());
                        }
                    } else if (i == 3) {
                        try {
                            fileChannelOpen.position(fileChannelOpen.position() + header.getDataLength());
                        } catch (IOException e3) {
                            logger.warning(path + "Unable to readseek metablock, ignoring:" + e3.getMessage());
                        }
                    } else {
                        if (logger.isLoggable(Level.CONFIG)) {
                            logger.config(path + "Ignoring MetadataBlock:" + header.getBlockType());
                        }
                        fileChannelOpen.position(fileChannelOpen.position() + header.getDataLength());
                    }
                }
                zIsLastBlock = header.isLastBlock();
            }
            logger.config("Audio should start at:" + Hex.asHex(fileChannelOpen.position()));
            if (vorbisCommentTagCreateNewTag == null) {
                vorbisCommentTagCreateNewTag = VorbisCommentTag.createNewTag();
            }
            FlacTag flacTag = new FlacTag(vorbisCommentTagCreateNewTag, arrayList);
            if (fileChannelOpen != null) {
                fileChannelOpen.close();
            }
            return flacTag;
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
    }

    /* renamed from: org.jaudiotagger.audio.flac.FlacTagReader$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType;

        static {
            int[] iArr = new int[BlockType.values().length];
            $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType = iArr;
            try {
                iArr[BlockType.VORBIS_COMMENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.PICTURE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$audio$flac$metadatablock$BlockType[BlockType.SEEKTABLE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }
}
