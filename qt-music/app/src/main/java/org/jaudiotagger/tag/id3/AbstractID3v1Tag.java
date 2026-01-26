package org.jaudiotagger.tag.id3;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public abstract class AbstractID3v1Tag extends AbstractID3Tag {
    protected static final byte END_OF_FIELD = 0;
    protected static final int FIELD_ALBUM_LENGTH = 30;
    protected static final int FIELD_ALBUM_POS = 63;
    protected static final int FIELD_ARTIST_LENGTH = 30;
    protected static final int FIELD_ARTIST_POS = 33;
    protected static final int FIELD_GENRE_LENGTH = 1;
    protected static final int FIELD_GENRE_POS = 127;
    protected static final int FIELD_TAGID_LENGTH = 3;
    protected static final int FIELD_TAGID_POS = 0;
    protected static final int FIELD_TITLE_LENGTH = 30;
    protected static final int FIELD_TITLE_POS = 3;
    protected static final int FIELD_YEAR_LENGTH = 4;
    protected static final int FIELD_YEAR_POS = 93;
    public static final String TAG = "TAG";
    protected static final int TAG_DATA_LENGTH = 125;
    protected static final int TAG_LENGTH = 128;
    protected static final String TYPE_ALBUM = "album";
    protected static final String TYPE_ARTIST = "artist";
    protected static final String TYPE_GENRE = "genre";
    protected static final String TYPE_TITLE = "title";
    protected static final String TYPE_YEAR = "year";
    public static Logger logger = Logger.getLogger("org.jaudiotagger.tag.id3");
    protected static Pattern endofStringPattern = Pattern.compile("\\x00");
    protected static final byte[] TAG_ID = {84, 65, 71};

    @Override // org.jaudiotagger.tag.id3.AbstractTagItem
    public int getSize() {
        return 128;
    }

    public AbstractID3v1Tag() {
    }

    public AbstractID3v1Tag(AbstractID3v1Tag abstractID3v1Tag) {
        super(abstractID3v1Tag);
    }

    public static boolean seekForV1OrV11Tag(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[3];
        byteBuffer.get(bArr, 0, 3);
        return Arrays.equals(bArr, TAG_ID);
    }

    @Override // org.jaudiotagger.tag.id3.AbstractTag
    public void delete(RandomAccessFile randomAccessFile) throws IOException {
        logger.config("Deleting ID3v1 from file if exists");
        FileChannel channel = randomAccessFile.getChannel();
        if (randomAccessFile.length() < 128) {
            throw new IOException("File not large enough to contain a tag");
        }
        channel.position(randomAccessFile.length() - 128);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
        channel.read(byteBufferAllocate);
        byteBufferAllocate.rewind();
        if (seekForV1OrV11Tag(byteBufferAllocate)) {
            try {
                logger.config("Deleted ID3v1 tag");
                randomAccessFile.setLength(randomAccessFile.length() - 128);
                return;
            } catch (IOException e) {
                logger.severe("Unable to delete existing ID3v1 Tag:" + e.getMessage());
                return;
            }
        }
        logger.config("Unable to find ID3v1 tag to deleteField");
    }
}
