package org.jaudiotagger.audio.mp3;

import java.nio.ByteBuffer;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;

/* loaded from: classes3.dex */
public class ByteArrayMP3AudioHeader extends MP3AudioHeader {
    /* JADX WARN: Removed duplicated region for block: B:14:0x002e A[PHI: r3
  0x002e: PHI (r3v7 boolean) = (r3v1 boolean), (r3v1 boolean), (r3v2 boolean), (r3v5 boolean) binds: [B:4:0x000f, B:19:0x002e, B:13:0x002d, B:11:0x002a] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public ByteArrayMP3AudioHeader(byte[] r7) {
        /*
            r6 = this;
            r6.<init>()
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r7)
            r1 = 0
            r3 = 0
        La:
            boolean r4 = org.jaudiotagger.audio.mp3.MPEGFrameHeader.isMPEGFrame(r0)
            r5 = 1
            if (r4 == 0) goto L2e
            org.jaudiotagger.audio.mp3.MPEGFrameHeader r4 = org.jaudiotagger.audio.mp3.MPEGFrameHeader.parseMPEGHeader(r0)     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L2e
            r6.mp3FrameHeader = r4     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L2e
            org.jaudiotagger.audio.mp3.MPEGFrameHeader r3 = r6.mp3FrameHeader     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L2d
            java.nio.ByteBuffer r3 = org.jaudiotagger.audio.mp3.XingFrame.isXingFrame(r0, r3)     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L2d
            if (r3 == 0) goto L26
            org.jaudiotagger.audio.mp3.XingFrame r0 = org.jaudiotagger.audio.mp3.XingFrame.parseXingFrame(r3)     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L3b
            r6.mp3XingFrame = r0     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L3b
            goto L3b
        L26:
            boolean r3 = r6.isNextFrameValid(r0)     // Catch: org.jaudiotagger.audio.exceptions.InvalidAudioFrameException -> L2d
            if (r3 == 0) goto L2e
            goto L3b
        L2d:
            r3 = r5
        L2e:
            int r4 = r0.position()
            int r4 = r4 + r5
            r0.position(r4)
            r4 = 1
            long r1 = r1 + r4
            if (r3 == 0) goto La
        L3b:
            int r7 = r7.length
            long r3 = (long) r7
            r6.setFileSize(r3)
            r6.setMp3StartByte(r1)
            r6.setTimePerFrame()
            r6.setNumberOfFrames()
            r6.setTrackLength()
            r6.setBitRate()
            r6.setEncoder()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jaudiotagger.audio.mp3.ByteArrayMP3AudioHeader.<init>(byte[]):void");
    }

    private boolean isNextFrameValid(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        byteBuffer.position(byteBuffer.position() + this.mp3FrameHeader.getFrameLength());
        boolean z = false;
        if (MPEGFrameHeader.isMPEGFrame(byteBuffer)) {
            try {
                MPEGFrameHeader.parseMPEGHeader(byteBuffer);
                MP3AudioHeader.logger.finer("Check next frame confirms is an audio header ");
                z = true;
            } catch (InvalidAudioFrameException unused) {
                MP3AudioHeader.logger.finer("Check next frame has identified this is not an audio header");
            }
        }
        byteBuffer.position(iPosition);
        return z;
    }
}
