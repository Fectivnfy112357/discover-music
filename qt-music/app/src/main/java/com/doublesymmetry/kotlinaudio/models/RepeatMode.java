package com.doublesymmetry.kotlinaudio.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: QueuedPlayerOptions.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u0000 \u00062\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0007"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "", "(Ljava/lang/String;I)V", "OFF", "ONE", "ALL", "Companion", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public enum RepeatMode {
    OFF,
    ONE,
    ALL;


    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* compiled from: QueuedPlayerOptions.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/models/RepeatMode$Companion;", "", "()V", "fromOrdinal", "Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "ordinal", "", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final RepeatMode fromOrdinal(int ordinal) {
            if (ordinal == 0) {
                return RepeatMode.OFF;
            }
            if (ordinal == 1) {
                return RepeatMode.ONE;
            }
            if (ordinal == 2) {
                return RepeatMode.ALL;
            }
            throw new IllegalStateException("Wrong ordinal".toString());
        }
    }
}
