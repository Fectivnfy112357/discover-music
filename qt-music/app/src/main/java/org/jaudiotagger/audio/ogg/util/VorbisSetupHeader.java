package org.jaudiotagger.audio.ogg.util;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/* loaded from: classes3.dex */
public class VorbisSetupHeader implements VorbisHeader {
    public static Logger logger = Logger.getLogger("org.jaudiotagger.audio.ogg.atom");
    private boolean isValid = false;

    public VorbisSetupHeader(byte[] bArr) {
        decodeHeader(bArr);
    }

    public boolean isValid() {
        return this.isValid;
    }

    public void decodeHeader(byte[] bArr) {
        byte b = bArr[0];
        logger.fine("packetType" + ((int) b));
        String str = new String(bArr, 1, 6, StandardCharsets.ISO_8859_1);
        if (b == VorbisPacketType.SETUP_HEADER.getType() && str.equals(VorbisHeader.CAPTURE_PATTERN)) {
            this.isValid = true;
        }
    }
}
