package com.swmansion.rnscreens.bottomsheet;

import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;

/* compiled from: SheetUtils.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004J\u0016\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004¨\u0006\r"}, d2 = {"Lcom/swmansion/rnscreens/bottomsheet/SheetUtils;", "", "()V", "detentIndexFromSheetState", "", MusicService.STATE_KEY, "detentCount", "isStateLessEqualThan", "", "otherState", "isStateStable", "sheetStateFromDetentIndex", "index", "react-native-screens_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SheetUtils {
    public static final SheetUtils INSTANCE = new SheetUtils();

    public final boolean isStateLessEqualThan(int state, int otherState) {
        if (state == otherState) {
            return true;
        }
        return (state == 6 || otherState == 6) ? state == 6 ? otherState == 3 : state == 4 && otherState != 5 : state > otherState;
    }

    public final boolean isStateStable(int state) {
        return state == 3 || state == 4 || state == 5 || state == 6;
    }

    private SheetUtils() {
    }

    public final int sheetStateFromDetentIndex(int index, int detentCount) {
        if (detentCount != 1) {
            if (detentCount == 2) {
                if (index == -1) {
                    return 5;
                }
                if (index != 0) {
                    if (index != 1) {
                        throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + detentCount + " / " + index);
                    }
                }
                return 4;
            }
            if (detentCount != 3) {
                throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + detentCount + " / " + index);
            }
            if (index == -1) {
                return 5;
            }
            if (index != 0) {
                if (index == 1) {
                    return 6;
                }
                if (index != 2) {
                    throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + detentCount + " / " + index);
                }
            }
            return 4;
        }
        if (index == -1) {
            return 5;
        }
        if (index != 0) {
            throw new IllegalArgumentException("[RNScreens] Invalid detentCount/index combination " + detentCount + " / " + index);
        }
        return 3;
    }

    public final int detentIndexFromSheetState(int state, int detentCount) {
        if (detentCount != 1) {
            if (detentCount == 2) {
                if (state != 3) {
                    if (state != 4) {
                        if (state == 5) {
                            return -1;
                        }
                        throw new IllegalArgumentException("[RNScreens] Invalid state " + state + " for detentCount " + detentCount);
                    }
                }
                return 1;
            }
            if (detentCount != 3) {
                throw new IllegalArgumentException("[RNScreens] Invalid state " + state + " for detentCount " + detentCount);
            }
            if (state == 3) {
                return 2;
            }
            if (state != 4) {
                if (state == 5) {
                    return -1;
                }
                if (state != 6) {
                    throw new IllegalArgumentException("[RNScreens] Invalid state " + state + " for detentCount " + detentCount);
                }
                return 1;
            }
        } else if (state != 3) {
            if (state == 5) {
                return -1;
            }
            throw new IllegalArgumentException("[RNScreens] Invalid state " + state + " for detentCount " + detentCount);
        }
        return 0;
    }
}
