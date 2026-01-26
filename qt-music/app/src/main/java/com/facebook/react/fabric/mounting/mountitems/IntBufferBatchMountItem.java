package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.internal.featureflags.ReactNativeFeatureFlags;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.systrace.Systrace;

/* loaded from: classes2.dex */
final class IntBufferBatchMountItem implements BatchMountItem {
    static final int INSTRUCTION_CREATE = 2;
    static final int INSTRUCTION_DELETE = 4;
    static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    static final int INSTRUCTION_INSERT = 8;
    static final int INSTRUCTION_REMOVE = 16;
    static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    static final int INSTRUCTION_UPDATE_OVERFLOW_INSET = 1024;
    static final int INSTRUCTION_UPDATE_PADDING = 512;
    static final int INSTRUCTION_UPDATE_PROPS = 32;
    static final int INSTRUCTION_UPDATE_STATE = 64;
    static final String TAG = "IntBufferBatchMountItem";
    private final int mCommitNumber;
    private final int[] mIntBuffer;
    private final int mIntBufferLen;
    private final Object[] mObjBuffer;
    private final int mObjBufferLen;
    private final int mSurfaceId;

    IntBufferBatchMountItem(int i, int[] iArr, Object[] objArr, int i2) {
        this.mSurfaceId = i;
        this.mCommitNumber = i2;
        this.mIntBuffer = iArr;
        this.mObjBuffer = objArr;
        this.mIntBufferLen = iArr.length;
        this.mObjBufferLen = objArr.length;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        int i;
        int i2;
        long j;
        int i3;
        int i4;
        int i5;
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(TAG, "Skipping batch of MountItems; no SurfaceMountingManager found for [%d].", Integer.valueOf(this.mSurfaceId));
            return;
        }
        if (surfaceManager.isStopped()) {
            FLog.e(TAG, "Skipping batch of MountItems; was stopped [%d].", Integer.valueOf(this.mSurfaceId));
            return;
        }
        if (ReactNativeFeatureFlags.enableFabricLogs()) {
            FLog.d(TAG, "Executing IntBufferBatchMountItem on surface [%d]", Integer.valueOf(this.mSurfaceId));
        }
        int i6 = 0;
        int i7 = 0;
        while (i6 < this.mIntBufferLen) {
            int[] iArr = this.mIntBuffer;
            int i8 = i6 + 1;
            int i9 = iArr[i6];
            int i10 = i9 & (-2);
            if ((i9 & 1) != 0) {
                int i11 = iArr[i8];
                i8 = i6 + 2;
                i = i11;
            } else {
                i = 1;
            }
            long j2 = 0;
            Systrace.beginSection(0L, "IntBufferBatchMountItem::mountInstructions::" + nameForInstructionString(i10), new String[]{"numInstructions", String.valueOf(i)}, 2);
            int i12 = i7;
            i6 = i8;
            int i13 = 0;
            while (i13 < i) {
                if (i10 == 2) {
                    String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i12]);
                    int[] iArr2 = this.mIntBuffer;
                    int i14 = iArr2[i6];
                    Object[] objArr = this.mObjBuffer;
                    ReadableMap readableMap = (ReadableMap) objArr[i12 + 1];
                    int i15 = i12 + 3;
                    StateWrapper stateWrapper = (StateWrapper) objArr[i12 + 2];
                    i12 += 4;
                    int i16 = i6 + 2;
                    i2 = i13;
                    surfaceManager.createView(fabricComponentName, i14, readableMap, stateWrapper, (EventEmitterWrapper) objArr[i15], iArr2[i6 + 1] == 1);
                    i6 = i16;
                } else {
                    i2 = i13;
                    if (i10 == 4) {
                        surfaceManager.deleteView(this.mIntBuffer[i6]);
                        i6++;
                    } else if (i10 == 8) {
                        int[] iArr3 = this.mIntBuffer;
                        int i17 = iArr3[i6];
                        int i18 = i6 + 2;
                        int i19 = iArr3[i6 + 1];
                        i6 += 3;
                        surfaceManager.addViewAt(i19, i17, iArr3[i18]);
                    } else if (i10 == 16) {
                        int[] iArr4 = this.mIntBuffer;
                        int i20 = iArr4[i6];
                        int i21 = i6 + 2;
                        int i22 = iArr4[i6 + 1];
                        i6 += 3;
                        surfaceManager.removeViewAt(i20, i22, iArr4[i21]);
                    } else {
                        if (i10 == 32) {
                            i4 = i6 + 1;
                            i5 = i12 + 1;
                            surfaceManager.updateProps(this.mIntBuffer[i6], (ReadableMap) this.mObjBuffer[i12]);
                        } else if (i10 == 64) {
                            i4 = i6 + 1;
                            i5 = i12 + 1;
                            surfaceManager.updateState(this.mIntBuffer[i6], (StateWrapper) this.mObjBuffer[i12]);
                        } else {
                            if (i10 == 128) {
                                int[] iArr5 = this.mIntBuffer;
                                int i23 = iArr5[i6];
                                int i24 = iArr5[i6 + 1];
                                int i25 = iArr5[i6 + 2];
                                int i26 = iArr5[i6 + 3];
                                int i27 = iArr5[i6 + 4];
                                int i28 = iArr5[i6 + 5];
                                int i29 = i6 + 7;
                                int i30 = iArr5[i6 + 6];
                                if (ReactNativeFeatureFlags.setAndroidLayoutDirection()) {
                                    j = 0;
                                    surfaceManager.updateLayout(i23, i24, i25, i26, i27, i28, i30, this.mIntBuffer[i29]);
                                    i29 = i6 + 8;
                                } else {
                                    j = 0;
                                    surfaceManager.updateLayout(i23, i24, i25, i26, i27, i28, i30, 0);
                                }
                                i6 = i29;
                            } else {
                                j = 0;
                                if (i10 == 512) {
                                    int[] iArr6 = this.mIntBuffer;
                                    i3 = i6 + 5;
                                    surfaceManager.updatePadding(iArr6[i6], iArr6[i6 + 1], iArr6[i6 + 2], iArr6[i6 + 3], iArr6[i6 + 4]);
                                } else if (i10 == 1024) {
                                    int[] iArr7 = this.mIntBuffer;
                                    i3 = i6 + 5;
                                    surfaceManager.updateOverflowInset(iArr7[i6], iArr7[i6 + 1], iArr7[i6 + 2], iArr7[i6 + 3], iArr7[i6 + 4]);
                                } else if (i10 == 256) {
                                    surfaceManager.updateEventEmitter(this.mIntBuffer[i6], (EventEmitterWrapper) this.mObjBuffer[i12]);
                                    i6++;
                                    i12++;
                                } else {
                                    throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i10 + " at index: " + i6);
                                }
                                i6 = i3;
                            }
                            i13 = i2 + 1;
                            j2 = j;
                        }
                        i6 = i4;
                        i12 = i5;
                    }
                }
                j = 0;
                i13 = i2 + 1;
                j2 = j;
            }
            Systrace.endSection(j2);
            i7 = i12;
        }
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.BatchMountItem
    public boolean isBatchEmpty() {
        return this.mIntBufferLen == 0;
    }

    public String toString() {
        int i;
        int i2;
        int i3;
        int i4;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("IntBufferBatchMountItem [surface:%d]:\n", Integer.valueOf(this.mSurfaceId)));
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.mIntBufferLen) {
                int[] iArr = this.mIntBuffer;
                int i7 = i5 + 1;
                int i8 = iArr[i5];
                int i9 = i8 & (-2);
                int i10 = 1;
                if ((i8 & 1) != 0) {
                    i10 = iArr[i7];
                    i7 = i5 + 2;
                }
                i5 = i7;
                for (int i11 = 0; i11 < i10; i11++) {
                    if (i9 == 2) {
                        String fabricComponentName = FabricNameComponentMapping.getFabricComponentName((String) this.mObjBuffer[i6]);
                        i6 += 4;
                        int i12 = i5 + 1;
                        Integer numValueOf = Integer.valueOf(this.mIntBuffer[i5]);
                        i5 += 2;
                        sb.append(String.format("CREATE [%d] - layoutable:%d - %s\n", numValueOf, Integer.valueOf(this.mIntBuffer[i12]), fabricComponentName));
                    } else {
                        if (i9 == 4) {
                            i = i5 + 1;
                            sb.append(String.format("DELETE [%d]\n", Integer.valueOf(this.mIntBuffer[i5])));
                        } else if (i9 == 8) {
                            Integer numValueOf2 = Integer.valueOf(this.mIntBuffer[i5]);
                            int i13 = i5 + 2;
                            Integer numValueOf3 = Integer.valueOf(this.mIntBuffer[i5 + 1]);
                            i5 += 3;
                            sb.append(String.format("INSERT [%d]->[%d] @%d\n", numValueOf2, numValueOf3, Integer.valueOf(this.mIntBuffer[i13])));
                        } else if (i9 == 16) {
                            Integer numValueOf4 = Integer.valueOf(this.mIntBuffer[i5]);
                            int i14 = i5 + 2;
                            Integer numValueOf5 = Integer.valueOf(this.mIntBuffer[i5 + 1]);
                            i5 += 3;
                            sb.append(String.format("REMOVE [%d]->[%d] @%d\n", numValueOf4, numValueOf5, Integer.valueOf(this.mIntBuffer[i14])));
                        } else {
                            String string = "<null>";
                            if (i9 == 32) {
                                i3 = i6 + 1;
                                Object obj = this.mObjBuffer[i6];
                                if (!FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
                                    string = "<hidden>";
                                } else if (obj != null) {
                                    string = obj.toString();
                                }
                                i4 = i5 + 1;
                                sb.append(String.format("UPDATE PROPS [%d]: %s\n", Integer.valueOf(this.mIntBuffer[i5]), string));
                            } else if (i9 == 64) {
                                i3 = i6 + 1;
                                StateWrapper stateWrapper = (StateWrapper) this.mObjBuffer[i6];
                                if (!FabricUIManager.IS_DEVELOPMENT_ENVIRONMENT) {
                                    string = "<hidden>";
                                } else if (stateWrapper != null) {
                                    string = stateWrapper.toString();
                                }
                                i4 = i5 + 1;
                                sb.append(String.format("UPDATE STATE [%d]: %s\n", Integer.valueOf(this.mIntBuffer[i5]), string));
                            } else if (i9 == 128) {
                                int[] iArr2 = this.mIntBuffer;
                                int i15 = iArr2[i5];
                                int i16 = iArr2[i5 + 1];
                                int i17 = iArr2[i5 + 2];
                                int i18 = iArr2[i5 + 3];
                                int i19 = iArr2[i5 + 4];
                                int i20 = iArr2[i5 + 5];
                                int i21 = i5 + 7;
                                int i22 = iArr2[i5 + 6];
                                if (ReactNativeFeatureFlags.setAndroidLayoutDirection()) {
                                    i2 = this.mIntBuffer[i21];
                                    i21 = i5 + 8;
                                } else {
                                    i2 = 0;
                                }
                                sb.append(String.format("UPDATE LAYOUT [%d]->[%d]: x:%d y:%d w:%d h:%d displayType:%d layoutDirection: %d\n", Integer.valueOf(i16), Integer.valueOf(i15), Integer.valueOf(i17), Integer.valueOf(i18), Integer.valueOf(i19), Integer.valueOf(i20), Integer.valueOf(i22), Integer.valueOf(i2)));
                                i5 = i21;
                            } else if (i9 == 512) {
                                Integer numValueOf6 = Integer.valueOf(this.mIntBuffer[i5]);
                                Integer numValueOf7 = Integer.valueOf(this.mIntBuffer[i5 + 1]);
                                Integer numValueOf8 = Integer.valueOf(this.mIntBuffer[i5 + 2]);
                                int i23 = i5 + 4;
                                Integer numValueOf9 = Integer.valueOf(this.mIntBuffer[i5 + 3]);
                                i5 += 5;
                                sb.append(String.format("UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", numValueOf6, numValueOf7, numValueOf8, numValueOf9, Integer.valueOf(this.mIntBuffer[i23])));
                            } else if (i9 == 1024) {
                                Integer numValueOf10 = Integer.valueOf(this.mIntBuffer[i5]);
                                Integer numValueOf11 = Integer.valueOf(this.mIntBuffer[i5 + 1]);
                                Integer numValueOf12 = Integer.valueOf(this.mIntBuffer[i5 + 2]);
                                int i24 = i5 + 4;
                                Integer numValueOf13 = Integer.valueOf(this.mIntBuffer[i5 + 3]);
                                i5 += 5;
                                sb.append(String.format("UPDATE OVERFLOWINSET [%d]: left:%d top:%d right:%d bottom:%d\n", numValueOf10, numValueOf11, numValueOf12, numValueOf13, Integer.valueOf(this.mIntBuffer[i24])));
                            } else if (i9 == 256) {
                                i6++;
                                i = i5 + 1;
                                sb.append(String.format("UPDATE EVENTEMITTER [%d]\n", Integer.valueOf(this.mIntBuffer[i5])));
                            } else {
                                FLog.e(TAG, "String so far: " + sb.toString());
                                throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + i9 + " at index: " + i5);
                            }
                            i5 = i4;
                            i6 = i3;
                        }
                        i5 = i;
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            FLog.e(TAG, "Caught exception trying to print", e);
            StringBuilder sb2 = new StringBuilder();
            for (int i25 = 0; i25 < this.mIntBufferLen; i25++) {
                sb2.append(this.mIntBuffer[i25]);
                sb2.append(", ");
            }
            FLog.e(TAG, sb2.toString());
            for (int i26 = 0; i26 < this.mObjBufferLen; i26++) {
                String str = TAG;
                Object obj2 = this.mObjBuffer[i26];
                FLog.e(str, obj2 != null ? obj2.toString() : "null");
            }
            return "";
        }
    }

    private static String nameForInstructionString(int i) {
        if (i == 2) {
            return "CREATE";
        }
        if (i == 4) {
            return "DELETE";
        }
        if (i == 8) {
            return "INSERT";
        }
        if (i == 16) {
            return "REMOVE";
        }
        if (i == 32) {
            return "UPDATE_PROPS";
        }
        if (i == 64) {
            return "UPDATE_STATE";
        }
        if (i == 128) {
            return "UPDATE_LAYOUT";
        }
        if (i == 512) {
            return "UPDATE_PADDING";
        }
        if (i == 1024) {
            return "UPDATE_OVERFLOW_INSET";
        }
        if (i == 256) {
            return "UPDATE_EVENT_EMITTER";
        }
        return "UNKNOWN";
    }
}
