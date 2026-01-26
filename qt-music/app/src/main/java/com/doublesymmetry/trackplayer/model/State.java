package com.doublesymmetry.trackplayer.model;

import com.doublesymmetry.trackplayer.service.MusicService;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: State.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/State;", "", MusicService.STATE_KEY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getState", "()Ljava/lang/String;", "Buffering", "None", "Ready", "Paused", "Stopped", "Playing", "Loading", "Error", "Ended", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class State {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ State[] $VALUES;
    private final String state;
    public static final State Buffering = new State("Buffering", 0, "buffering");
    public static final State None = new State("None", 1, "none");
    public static final State Ready = new State("Ready", 2, "ready");
    public static final State Paused = new State("Paused", 3, MusicService.IS_PAUSED_KEY);
    public static final State Stopped = new State("Stopped", 4, "stopped");
    public static final State Playing = new State("Playing", 5, "playing");
    public static final State Loading = new State("Loading", 6, "loading");
    public static final State Error = new State("Error", 7, "error");
    public static final State Ended = new State("Ended", 8, "ended");

    private static final /* synthetic */ State[] $values() {
        return new State[]{Buffering, None, Ready, Paused, Stopped, Playing, Loading, Error, Ended};
    }

    public static EnumEntries<State> getEntries() {
        return $ENTRIES;
    }

    public static State valueOf(String str) {
        return (State) Enum.valueOf(State.class, str);
    }

    public static State[] values() {
        return (State[]) $VALUES.clone();
    }

    private State(String str, int i, String str2) {
        this.state = str2;
    }

    public final String getState() {
        return this.state;
    }

    static {
        State[] stateArr$values = $values();
        $VALUES = stateArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(stateArr$values);
    }
}
