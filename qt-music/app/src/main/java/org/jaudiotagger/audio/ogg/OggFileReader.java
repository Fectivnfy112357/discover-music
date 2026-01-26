package org.jaudiotagger.audio.ogg;

import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.generic.AudioFileReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.ogg.util.OggInfoReader;
import org.jaudiotagger.audio.ogg.util.OggPageHeader;
import org.jaudiotagger.tag.Tag;

/* loaded from: classes3.dex */
public class OggFileReader extends AudioFileReader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg");
    private OggInfoReader ir = new OggInfoReader();
    private OggVorbisTagReader vtr = new OggVorbisTagReader();

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected GenericAudioHeader getEncodingInfo(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        return this.ir.read(randomAccessFile);
    }

    @Override // org.jaudiotagger.audio.generic.AudioFileReader
    protected Tag getTag(RandomAccessFile randomAccessFile) throws CannotReadException, IOException {
        return this.vtr.read(randomAccessFile);
    }

    public OggPageHeader readOggPageHeader(RandomAccessFile randomAccessFile, int i) throws CannotReadException, IOException {
        OggPageHeader oggPageHeader = OggPageHeader.read(randomAccessFile);
        while (i > 0) {
            randomAccessFile.seek(randomAccessFile.getFilePointer() + oggPageHeader.getPageLength());
            oggPageHeader = OggPageHeader.read(randomAccessFile);
            i--;
        }
        return oggPageHeader;
    }

    public void summarizeOggPageHeaders(File file) throws CannotReadException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            System.out.println("pageHeader starts at absolute file position:" + randomAccessFile.getFilePointer());
            OggPageHeader oggPageHeader = OggPageHeader.read(randomAccessFile);
            System.out.println("pageHeader finishes at absolute file position:" + randomAccessFile.getFilePointer());
            System.out.println(oggPageHeader + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            randomAccessFile.seek(randomAccessFile.getFilePointer() + oggPageHeader.getPageLength());
        }
        System.out.println("Raf File Pointer at:" + randomAccessFile.getFilePointer() + "File Size is:" + randomAccessFile.length());
        randomAccessFile.close();
    }

    public void shortSummarizeOggPageHeaders(File file) throws CannotReadException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        int i = 0;
        while (randomAccessFile.getFilePointer() < randomAccessFile.length()) {
            System.out.println("pageHeader starts at absolute file position:" + randomAccessFile.getFilePointer());
            OggPageHeader oggPageHeader = OggPageHeader.read(randomAccessFile);
            System.out.println("pageHeader finishes at absolute file position:" + randomAccessFile.getFilePointer());
            System.out.println(oggPageHeader + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
            randomAccessFile.seek(randomAccessFile.getFilePointer() + oggPageHeader.getPageLength());
            i++;
            if (i >= 5) {
                break;
            }
        }
        System.out.println("Raf File Pointer at:" + randomAccessFile.getFilePointer() + "File Size is:" + randomAccessFile.length());
        randomAccessFile.close();
    }
}
