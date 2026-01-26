package org.mozilla.universalchardet.prober;

import java.nio.ByteBuffer;
import org.mozilla.universalchardet.prober.CharsetProber;
import org.mozilla.universalchardet.prober.sequence.HebrewModel;
import org.mozilla.universalchardet.prober.sequence.Ibm855Model;
import org.mozilla.universalchardet.prober.sequence.Ibm866Model;
import org.mozilla.universalchardet.prober.sequence.Koi8rModel;
import org.mozilla.universalchardet.prober.sequence.Latin5BulgarianModel;
import org.mozilla.universalchardet.prober.sequence.Latin5Model;
import org.mozilla.universalchardet.prober.sequence.Latin7Model;
import org.mozilla.universalchardet.prober.sequence.MacCyrillicModel;
import org.mozilla.universalchardet.prober.sequence.SequenceModel;
import org.mozilla.universalchardet.prober.sequence.Win1251BulgarianModel;
import org.mozilla.universalchardet.prober.sequence.Win1251Model;
import org.mozilla.universalchardet.prober.sequence.Win1253Model;

/* loaded from: classes3.dex */
public class SBCSGroupProber extends CharsetProber {
    private int activeNum;
    private int bestGuess;
    private boolean[] isActive = new boolean[13];
    private CharsetProber[] probers;
    private CharsetProber.ProbingState state;
    private static final SequenceModel win1251Model = new Win1251Model();
    private static final SequenceModel koi8rModel = new Koi8rModel();
    private static final SequenceModel latin5Model = new Latin5Model();
    private static final SequenceModel macCyrillicModel = new MacCyrillicModel();
    private static final SequenceModel ibm866Model = new Ibm866Model();
    private static final SequenceModel ibm855Model = new Ibm855Model();
    private static final SequenceModel latin7Model = new Latin7Model();
    private static final SequenceModel win1253Model = new Win1253Model();
    private static final SequenceModel latin5BulgarianModel = new Latin5BulgarianModel();
    private static final SequenceModel win1251BulgarianModel = new Win1251BulgarianModel();
    private static final SequenceModel hebrewModel = new HebrewModel();

    @Override // org.mozilla.universalchardet.prober.CharsetProber
    public void setOption() {
    }

    public SBCSGroupProber() {
        CharsetProber[] charsetProberArr = new CharsetProber[13];
        this.probers = charsetProberArr;
        charsetProberArr[0] = new SingleByteCharsetProber(win1251Model);
        this.probers[1] = new SingleByteCharsetProber(koi8rModel);
        this.probers[2] = new SingleByteCharsetProber(latin5Model);
        this.probers[3] = new SingleByteCharsetProber(macCyrillicModel);
        this.probers[4] = new SingleByteCharsetProber(ibm866Model);
        this.probers[5] = new SingleByteCharsetProber(ibm855Model);
        this.probers[6] = new SingleByteCharsetProber(latin7Model);
        this.probers[7] = new SingleByteCharsetProber(win1253Model);
        this.probers[8] = new SingleByteCharsetProber(latin5BulgarianModel);
        this.probers[9] = new SingleByteCharsetProber(win1251BulgarianModel);
        HebrewProber hebrewProber = new HebrewProber();
        CharsetProber[] charsetProberArr2 = this.probers;
        charsetProberArr2[10] = hebrewProber;
        SequenceModel sequenceModel = hebrewModel;
        charsetProberArr2[11] = new SingleByteCharsetProber(sequenceModel, false, hebrewProber);
        this.probers[12] = new SingleByteCharsetProber(sequenceModel, true, hebrewProber);
        CharsetProber[] charsetProberArr3 = this.probers;
        hebrewProber.setModalProbers(charsetProberArr3[11], charsetProberArr3[12]);
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
        ByteBuffer byteBufferFilterWithoutEnglishLetters = filterWithoutEnglishLetters(bArr, i, i2);
        if (byteBufferFilterWithoutEnglishLetters.position() != 0) {
            int i3 = 0;
            while (true) {
                CharsetProber[] charsetProberArr = this.probers;
                if (i3 >= charsetProberArr.length) {
                    break;
                }
                if (this.isActive[i3]) {
                    CharsetProber.ProbingState probingStateHandleData = charsetProberArr[i3].handleData(byteBufferFilterWithoutEnglishLetters.array(), 0, byteBufferFilterWithoutEnglishLetters.position());
                    if (probingStateHandleData == CharsetProber.ProbingState.FOUND_IT) {
                        this.bestGuess = i3;
                        this.state = CharsetProber.ProbingState.FOUND_IT;
                        break;
                    }
                    if (probingStateHandleData == CharsetProber.ProbingState.NOT_ME) {
                        this.isActive[i3] = false;
                        int i4 = this.activeNum - 1;
                        this.activeNum = i4;
                        if (i4 <= 0) {
                            this.state = CharsetProber.ProbingState.NOT_ME;
                            break;
                        }
                    } else {
                        continue;
                    }
                }
                i3++;
            }
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
