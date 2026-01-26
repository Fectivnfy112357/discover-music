package org.mozilla.universalchardet;

import java.io.FileInputStream;
import org.mozilla.universalchardet.prober.CharsetProber;
import org.mozilla.universalchardet.prober.EscCharsetProber;
import org.mozilla.universalchardet.prober.Latin1Prober;
import org.mozilla.universalchardet.prober.MBCSGroupProber;
import org.mozilla.universalchardet.prober.SBCSGroupProber;

/* loaded from: classes3.dex */
public class UniversalDetector {
    public static final float MINIMUM_THRESHOLD = 0.2f;
    public static final float SHORTCUT_THRESHOLD = 0.95f;
    private String detectedCharset;
    private boolean done;
    private boolean gotData;
    private InputState inputState;
    private byte lastChar;
    private CharsetListener listener;
    private boolean start;
    private CharsetProber escCharsetProber = null;
    private CharsetProber[] probers = new CharsetProber[3];

    public enum InputState {
        PURE_ASCII,
        ESC_ASCII,
        HIGHBYTE
    }

    public UniversalDetector(CharsetListener charsetListener) {
        this.listener = charsetListener;
        int i = 0;
        while (true) {
            CharsetProber[] charsetProberArr = this.probers;
            if (i < charsetProberArr.length) {
                charsetProberArr[i] = null;
                i++;
            } else {
                reset();
                return;
            }
        }
    }

    public boolean isDone() {
        return this.done;
    }

    public String getDetectedCharset() {
        return this.detectedCharset;
    }

    public void setListener(CharsetListener charsetListener) {
        this.listener = charsetListener;
    }

    public CharsetListener getListener() {
        return this.listener;
    }

    public void handleData(byte[] bArr, int i, int i2) {
        if (this.done) {
            return;
        }
        if (i2 > 0) {
            this.gotData = true;
        }
        int i3 = 0;
        if (this.start) {
            this.start = false;
            if (i2 > 3) {
                int i4 = bArr[i] & 255;
                int i5 = bArr[i + 1] & 255;
                int i6 = bArr[i + 2] & 255;
                int i7 = bArr[i + 3] & 255;
                if (i4 != 0) {
                    if (i4 != 239) {
                        if (i4 != 254) {
                            if (i4 == 255) {
                                if (i5 == 254 && i6 == 0 && i7 == 0) {
                                    this.detectedCharset = Constants.CHARSET_UTF_32LE;
                                } else if (i5 == 254) {
                                    this.detectedCharset = Constants.CHARSET_UTF_16LE;
                                }
                            }
                        } else if (i5 == 255 && i6 == 0 && i7 == 0) {
                            this.detectedCharset = Constants.CHARSET_X_ISO_10646_UCS_4_3412;
                        } else if (i5 == 255) {
                            this.detectedCharset = Constants.CHARSET_UTF_16BE;
                        }
                    } else if (i5 == 187 && i6 == 191) {
                        this.detectedCharset = Constants.CHARSET_UTF_8;
                    }
                } else if (i5 == 0 && i6 == 254 && i7 == 255) {
                    this.detectedCharset = Constants.CHARSET_UTF_32BE;
                } else if (i5 == 0 && i6 == 255 && i7 == 254) {
                    this.detectedCharset = Constants.CHARSET_X_ISO_10646_UCS_4_2143;
                }
                if (this.detectedCharset != null) {
                    this.done = true;
                    return;
                }
            }
        }
        int i8 = i + i2;
        for (int i9 = i; i9 < i8; i9++) {
            byte b = bArr[i9];
            int i10 = b & 255;
            if ((b & 128) != 0 && i10 != 160) {
                if (this.inputState != InputState.HIGHBYTE) {
                    this.inputState = InputState.HIGHBYTE;
                    if (this.escCharsetProber != null) {
                        this.escCharsetProber = null;
                    }
                    CharsetProber[] charsetProberArr = this.probers;
                    if (charsetProberArr[0] == null) {
                        charsetProberArr[0] = new MBCSGroupProber();
                    }
                    CharsetProber[] charsetProberArr2 = this.probers;
                    if (charsetProberArr2[1] == null) {
                        charsetProberArr2[1] = new SBCSGroupProber();
                    }
                    CharsetProber[] charsetProberArr3 = this.probers;
                    if (charsetProberArr3[2] == null) {
                        charsetProberArr3[2] = new Latin1Prober();
                    }
                }
            } else {
                if (this.inputState == InputState.PURE_ASCII && (i10 == 27 || (i10 == 123 && this.lastChar == 126))) {
                    this.inputState = InputState.ESC_ASCII;
                }
                this.lastChar = bArr[i9];
            }
        }
        if (this.inputState == InputState.ESC_ASCII) {
            if (this.escCharsetProber == null) {
                this.escCharsetProber = new EscCharsetProber();
            }
            if (this.escCharsetProber.handleData(bArr, i, i2) == CharsetProber.ProbingState.FOUND_IT) {
                this.done = true;
                this.detectedCharset = this.escCharsetProber.getCharSetName();
                return;
            }
            return;
        }
        if (this.inputState != InputState.HIGHBYTE) {
            return;
        }
        while (true) {
            CharsetProber[] charsetProberArr4 = this.probers;
            if (i3 >= charsetProberArr4.length) {
                return;
            }
            if (charsetProberArr4[i3].handleData(bArr, i, i2) == CharsetProber.ProbingState.FOUND_IT) {
                this.done = true;
                this.detectedCharset = this.probers[i3].getCharSetName();
                return;
            }
            i3++;
        }
    }

    public void dataEnd() {
        CharsetProber[] charsetProberArr;
        if (this.gotData) {
            String str = this.detectedCharset;
            if (str != null) {
                this.done = true;
                CharsetListener charsetListener = this.listener;
                if (charsetListener != null) {
                    charsetListener.report(str);
                    return;
                }
                return;
            }
            if (this.inputState != InputState.HIGHBYTE) {
                InputState inputState = InputState.ESC_ASCII;
                return;
            }
            int i = 0;
            float f = 0.0f;
            int i2 = 0;
            while (true) {
                charsetProberArr = this.probers;
                if (i >= charsetProberArr.length) {
                    break;
                }
                float confidence = charsetProberArr[i].getConfidence();
                if (confidence > f) {
                    i2 = i;
                    f = confidence;
                }
                i++;
            }
            if (f > 0.2f) {
                String charSetName = charsetProberArr[i2].getCharSetName();
                this.detectedCharset = charSetName;
                CharsetListener charsetListener2 = this.listener;
                if (charsetListener2 != null) {
                    charsetListener2.report(charSetName);
                }
            }
        }
    }

    public void reset() {
        int i = 0;
        this.done = false;
        this.start = true;
        this.detectedCharset = null;
        this.gotData = false;
        this.inputState = InputState.PURE_ASCII;
        this.lastChar = (byte) 0;
        CharsetProber charsetProber = this.escCharsetProber;
        if (charsetProber != null) {
            charsetProber.reset();
        }
        while (true) {
            CharsetProber[] charsetProberArr = this.probers;
            if (i >= charsetProberArr.length) {
                return;
            }
            CharsetProber charsetProber2 = charsetProberArr[i];
            if (charsetProber2 != null) {
                charsetProber2.reset();
            }
            i++;
        }
    }

    public static void main(String[] strArr) throws Exception {
        if (strArr.length != 1) {
            System.out.println("USAGE: java UniversalDetector filename");
            return;
        }
        UniversalDetector universalDetector = new UniversalDetector(new CharsetListener() { // from class: org.mozilla.universalchardet.UniversalDetector.1
            @Override // org.mozilla.universalchardet.CharsetListener
            public void report(String str) {
                System.out.println("charset = " + str);
            }
        });
        byte[] bArr = new byte[4096];
        FileInputStream fileInputStream = new FileInputStream(strArr[0]);
        while (true) {
            int i = fileInputStream.read(bArr);
            if (i <= 0 || universalDetector.isDone()) {
                break;
            } else {
                universalDetector.handleData(bArr, 0, i);
            }
        }
        universalDetector.dataEnd();
    }
}
