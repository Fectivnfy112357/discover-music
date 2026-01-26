package com.doublesymmetry.trackplayer.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NumberExt.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/doublesymmetry/trackplayer/extensions/NumberExt;", "", "()V", "Companion", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NumberExt {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: NumberExt.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0005J\n\u0010\u0006\u001a\u00020\u0007*\u00020\u0005¨\u0006\b"}, d2 = {"Lcom/doublesymmetry/trackplayer/extensions/NumberExt$Companion;", "", "()V", "toMilliseconds", "", "", "toSeconds", "", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final double toSeconds(Number number) {
            Intrinsics.checkNotNullParameter(number, "<this>");
            return number.doubleValue() / 1000;
        }

        public final long toMilliseconds(Number number) {
            Intrinsics.checkNotNullParameter(number, "<this>");
            return (long) (number.doubleValue() * 1000);
        }
    }
}
