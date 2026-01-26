package org.jaudiotagger.audio.mp4.atom;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jaudiotagger.audio.exceptions.CannotReadException;

/* loaded from: classes3.dex */
public class Mp4FtypBox extends AbstractMp4Box {
    private static final int COMPATIBLE_BRAND_LENGTH = 4;
    private static final int MAJOR_BRAND_LENGTH = 4;
    private List<String> compatibleBrands = new ArrayList();
    private String majorBrand;
    private int majorBrandVersion;

    public Mp4FtypBox(Mp4BoxHeader mp4BoxHeader, ByteBuffer byteBuffer) {
        this.header = mp4BoxHeader;
        this.dataBuffer = byteBuffer;
        this.dataBuffer.order(ByteOrder.BIG_ENDIAN);
    }

    public void processData() throws CannotReadException {
        CharsetDecoder charsetDecoderNewDecoder = Charset.forName("ISO-8859-1").newDecoder();
        try {
            this.majorBrand = charsetDecoderNewDecoder.decode((ByteBuffer) this.dataBuffer.slice().limit(4)).toString();
        } catch (CharacterCodingException unused) {
        }
        this.dataBuffer.position(this.dataBuffer.position() + 4);
        this.majorBrandVersion = this.dataBuffer.getInt();
        while (this.dataBuffer.position() < this.dataBuffer.limit() && this.dataBuffer.limit() - this.dataBuffer.position() >= 4) {
            charsetDecoderNewDecoder.onMalformedInput(CodingErrorAction.REPORT);
            charsetDecoderNewDecoder.onMalformedInput(CodingErrorAction.REPORT);
            try {
                String string = charsetDecoderNewDecoder.decode((ByteBuffer) this.dataBuffer.slice().limit(4)).toString();
                if (!string.equals("\u0000\u0000\u0000\u0000")) {
                    this.compatibleBrands.add(string);
                }
            } catch (CharacterCodingException unused2) {
            }
            this.dataBuffer.position(this.dataBuffer.position() + 4);
        }
    }

    public String toString() {
        String str = "Major Brand:" + this.majorBrand + "Version:" + this.majorBrandVersion;
        if (this.compatibleBrands.size() <= 0) {
            return str;
        }
        String str2 = str + "Compatible:";
        Iterator<String> it = this.compatibleBrands.iterator();
        while (it.hasNext()) {
            str2 = (str2 + it.next()) + ",";
        }
        return str2.substring(0, str2.length() - 1);
    }

    public String getMajorBrand() {
        return this.majorBrand;
    }

    public int getMajorBrandVersion() {
        return this.majorBrandVersion;
    }

    public List<String> getCompatibleBrands() {
        return this.compatibleBrands;
    }

    public enum Brand {
        ISO14496_1_BASE_MEDIA("isom", "ISO 14496-1"),
        ISO14496_12_BASE_MEDIA("iso2", "ISO 14496-12"),
        ISO14496_1_VERSION_1("mp41", "ISO 14496-1"),
        ISO14496_1_VERSION_2("mp42", "ISO 14496-2:Multi track with BIFS scenes"),
        QUICKTIME_MOVIE("qt  ", "Original Quicktime"),
        JVT_AVC("avc1", "JVT"),
        THREEG_MOBILE_MP4("MPA ", "3G Mobile"),
        APPLE_AAC_AUDIO("M4P ", "Apple Audio"),
        AES_ENCRYPTED_AUDIO("M4B ", "Apple encrypted Audio"),
        APPLE_AUDIO("mp71", "Apple Audio"),
        ISO14496_12_MPEG7_METADATA("mp71", "MAIN_SYNTHESIS"),
        APPLE_AUDIO_ONLY("M4A ", "M4A Audio");

        private String description;
        private String id;

        Brand(String str, String str2) {
            this.id = str;
            this.description = str2;
        }

        public String getId() {
            return this.id;
        }

        public String getDescription() {
            return this.description;
        }
    }
}
