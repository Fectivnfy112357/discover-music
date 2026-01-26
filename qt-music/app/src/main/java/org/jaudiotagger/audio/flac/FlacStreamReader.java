package org.jaudiotagger.audio.flac;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.Utils;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;

/* loaded from: classes3.dex */
public class FlacStreamReader {
    public static final String FLAC_STREAM_IDENTIFIER = "fLaC";
    public static final int FLAC_STREAM_IDENTIFIER_LENGTH = 4;
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.flac");
    private FileChannel fc;
    private String loggingName;
    private int startOfFlacInFile;

    public FlacStreamReader(FileChannel fileChannel, String str) {
        this.fc = fileChannel;
        this.loggingName = str;
    }

    public void findStream() throws CannotReadException, IOException {
        if (this.fc.size() == 0) {
            throw new CannotReadException("Error: File empty " + this.loggingName);
        }
        this.fc.position(0L);
        if (isFlacHeader()) {
            this.startOfFlacInFile = 0;
        } else {
            if (isId3v2Tag()) {
                this.startOfFlacInFile = (int) (this.fc.position() - 4);
                return;
            }
            throw new CannotReadException(this.loggingName + ErrorMessage.FLAC_NO_FLAC_HEADER_FOUND.getMsg());
        }
    }

    private boolean isId3v2Tag() throws IOException {
        this.fc.position(0L);
        if (!AbstractID3v2Tag.isId3Tag(this.fc)) {
            return false;
        }
        logger.warning(this.loggingName + ErrorMessage.FLAC_CONTAINS_ID3TAG.getMsg(Long.valueOf(this.fc.position())));
        return isFlacHeader();
    }

    private boolean isFlacHeader() throws IOException {
        return Utils.readFourBytesAsChars(Utils.readFileDataIntoBufferBE(this.fc, 4)).equals(FLAC_STREAM_IDENTIFIER);
    }

    public int getStartOfFlacInFile() {
        return this.startOfFlacInFile;
    }
}
