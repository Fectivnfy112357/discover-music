package org.mozilla.universalchardet.prober;

import org.mozilla.universalchardet.prober.CharsetProber;

/* loaded from: classes3.dex */
public class MBCSGroupProber extends CharsetProber {
    private int activeNum;
    private int bestGuess;
    private boolean[] isActive = new boolean[7];
    private CharsetProber[] probers;
    private CharsetProber.ProbingState state;

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void setOption() {
    }

    public MBCSGroupProber() {
        CharsetProber[] charsetProberArr = new CharsetProber[7];
        this.probers = charsetProberArr;
        charsetProberArr[0] = new UTF8Prober();
        this.probers[1] = new SJISProber();
        this.probers[2] = new EUCJPProber();
        this.probers[3] = new GB18030Prober();
        this.probers[4] = new EUCKRProber();
        this.probers[5] = new Big5Prober();
        this.probers[6] = new EUCTWProber();
        reset();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public String getCharSetName() {
        if (this.bestGuess == -1) {
            getConfidence();
            if (this.bestGuess == -1) {
                this.bestGuess = 0;
            }
        }
        return this.probers[this.bestGuess].getCharSetName();
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public float getConfidence() {
        if (this.state == CharsetProber.ProbingState.FOUND_IT) {
            return 0.99f;
        }
        if (this.state == CharsetProber.ProbingState.NOT_ME) {
            return 0.01f;
        }
        float f = 0.0f;
        int i = 0;
        while (true) {
            CharsetProber[] charsetProberArr = this.probers;
            if (i >= charsetProberArr.length) {
                return f;
            }
            if (this.isActive[i]) {
                float confidence = charsetProberArr[i].getConfidence();
                if (f < confidence) {
                    this.bestGuess = i;
                    f = confidence;
                }
            }
            i++;
        }
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState getState() {
        return this.state;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public CharsetProber.ProbingState handleData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        int i3 = i2 + i;
        boolean z = true;
        int i4 = 0;
        while (i < i3) {
            byte b = bArr[i];
            if ((b & 128) != 0) {
                bArr2[i4] = b;
                i4++;
                z = true;
            } else if (z) {
                bArr2[i4] = b;
                i4++;
                z = false;
            }
            i++;
        }
        int i5 = 0;
        while (true) {
            CharsetProber[] charsetProberArr = this.probers;
            if (i5 >= charsetProberArr.length) {
                break;
            }
            if (this.isActive[i5]) {
                CharsetProber.ProbingState probingStateHandleData = charsetProberArr[i5].handleData(bArr2, 0, i4);
                if (probingStateHandleData == CharsetProber.ProbingState.FOUND_IT) {
                    this.bestGuess = i5;
                    this.state = CharsetProber.ProbingState.FOUND_IT;
                    break;
                }
                if (probingStateHandleData == CharsetProber.ProbingState.NOT_ME) {
                    this.isActive[i5] = false;
                    int i6 = this.activeNum - 1;
                    this.activeNum = i6;
                    if (i6 <= 0) {
                        this.state = CharsetProber.ProbingState.NOT_ME;
                        break;
                    }
                } else {
                    continue;
                }
            }
            i5++;
        }
        return this.state;
    }

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void reset() {
        int i = 0;
        this.activeNum = 0;
        while (true) {
            CharsetProber[] charsetProberArr = this.probers;
            if (i < charsetProberArr.length) {
                charsetProberArr[i].reset();
                this.isActive[i] = true;
                this.activeNum++;
                i++;
            } else {
                this.bestGuess = -1;
                this.state = CharsetProber.ProbingState.DETECTING;
                return;
            }
        }
    }
}
