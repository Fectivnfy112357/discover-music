package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public final class HevcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int stereoMode;
    public final NalUnitUtil.H265VpsData vpsData;
    public final int width;

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        return parseImpl(parsableByteArray, false, null);
    }

    public static HevcConfig parseLayered(ParsableByteArray parsableByteArray, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        return parseImpl(parsableByteArray, true, h265VpsData);
    }

    private static HevcConfig parseImpl(ParsableByteArray parsableByteArray, boolean z, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        int i;
        NalUnitUtil.H265Sei3dRefDisplayInfoData h265Sei3dRefDisplayInfo;
        int i2;
        int i3;
        int i4;
        try {
            if (z) {
                parsableByteArray.skipBytes(4);
            } else {
                parsableByteArray.skipBytes(21);
            }
            int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i5 = 0;
            for (int i6 = 0; i6 < unsignedByte2; i6++) {
                parsableByteArray.skipBytes(1);
                int unsignedShort = parsableByteArray.readUnsignedShort();
                for (int i7 = 0; i7 < unsignedShort; i7++) {
                    int unsignedShort2 = parsableByteArray.readUnsignedShort();
                    i5 += unsignedShort2 + 4;
                    parsableByteArray.skipBytes(unsignedShort2);
                }
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i5];
            NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
            int i8 = -1;
            int i9 = -1;
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int i14 = -1;
            int i15 = -1;
            int i16 = -1;
            float f = 1.0f;
            String strBuildHevcCodecString = null;
            int i17 = 0;
            int i18 = 0;
            while (i17 < unsignedByte2) {
                int unsignedByte3 = parsableByteArray.readUnsignedByte() & 63;
                int unsignedShort3 = parsableByteArray.readUnsignedShort();
                NalUnitUtil.H265VpsData h265VpsNalUnit = h265VpsData2;
                int i19 = 0;
                while (i19 < unsignedShort3) {
                    int unsignedShort4 = parsableByteArray.readUnsignedShort();
                    int i20 = unsignedByte2;
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i18, NalUnitUtil.NAL_START_CODE.length);
                    int length = i18 + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                    if (unsignedByte3 == 32 && i19 == 0) {
                        h265VpsNalUnit = NalUnitUtil.parseH265VpsNalUnit(bArr, length, length + unsignedShort4);
                        i = unsignedShort3;
                    } else {
                        if (unsignedByte3 == 33 && i19 == 0) {
                            NalUnitUtil.H265SpsData h265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + unsignedShort4, h265VpsNalUnit);
                            int i21 = h265SpsNalUnit.width;
                            int i22 = h265SpsNalUnit.height;
                            i10 = h265SpsNalUnit.bitDepthLumaMinus8 + 8;
                            i11 = h265SpsNalUnit.bitDepthChromaMinus8 + 8;
                            int i23 = h265SpsNalUnit.colorSpace;
                            int i24 = h265SpsNalUnit.colorRange;
                            i12 = i23;
                            int i25 = h265SpsNalUnit.colorTransfer;
                            float f2 = h265SpsNalUnit.pixelWidthHeightRatio;
                            int i26 = h265SpsNalUnit.maxNumReorderPics;
                            if (h265SpsNalUnit.profileTierLevel != null) {
                                i2 = i24;
                                i = unsignedShort3;
                                i3 = i21;
                                i4 = i22;
                                strBuildHevcCodecString = CodecSpecificDataUtil.buildHevcCodecString(h265SpsNalUnit.profileTierLevel.generalProfileSpace, h265SpsNalUnit.profileTierLevel.generalTierFlag, h265SpsNalUnit.profileTierLevel.generalProfileIdc, h265SpsNalUnit.profileTierLevel.generalProfileCompatibilityFlags, h265SpsNalUnit.profileTierLevel.constraintBytes, h265SpsNalUnit.profileTierLevel.generalLevelIdc);
                            } else {
                                i2 = i24;
                                i = unsignedShort3;
                                i3 = i21;
                                i4 = i22;
                            }
                            i8 = i3;
                            i9 = i4;
                            i14 = i25;
                            i13 = i2;
                            i16 = i26;
                            f = f2;
                        } else {
                            i = unsignedShort3;
                            if (unsignedByte3 == 39 && i19 == 0 && (h265Sei3dRefDisplayInfo = NalUnitUtil.parseH265Sei3dRefDisplayInfo(bArr, length, length + unsignedShort4)) != null && h265VpsNalUnit != null) {
                                i15 = h265Sei3dRefDisplayInfo.leftViewId == h265VpsNalUnit.layerInfos.get(0).viewId ? 4 : 5;
                            }
                        }
                        i18 = length + unsignedShort4;
                        parsableByteArray.skipBytes(unsignedShort4);
                        i19++;
                        unsignedShort3 = i;
                        unsignedByte2 = i20;
                    }
                    i18 = length + unsignedShort4;
                    parsableByteArray.skipBytes(unsignedShort4);
                    i19++;
                    unsignedShort3 = i;
                    unsignedByte2 = i20;
                }
                i17++;
                h265VpsData2 = h265VpsNalUnit;
            }
            return new HevcConfig(i5 == 0 ? Collections.emptyList() : Collections.singletonList(bArr), unsignedByte + 1, i8, i9, i10, i11, i12, i13, i14, i15, f, i16, strBuildHevcCodecString, h265VpsData2);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing".concat(z ? "L-HEVC config" : "HEVC config"), e);
        }
    }

    private HevcConfig(List<byte[]> list, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float f, int i10, String str, NalUnitUtil.H265VpsData h265VpsData) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i;
        this.width = i2;
        this.height = i3;
        this.bitdepthLuma = i4;
        this.bitdepthChroma = i5;
        this.colorSpace = i6;
        this.colorRange = i7;
        this.colorTransfer = i8;
        this.stereoMode = i9;
        this.pixelWidthHeightRatio = f;
        this.maxNumReorderPics = i10;
        this.codecs = str;
        this.vpsData = h265VpsData;
    }
}
