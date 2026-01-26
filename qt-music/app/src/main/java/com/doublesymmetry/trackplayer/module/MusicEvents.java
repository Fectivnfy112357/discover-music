package com.doublesymmetry.trackplayer.module;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.umeng.analytics.pro.f;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MusicEvents.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/doublesymmetry/trackplayer/module/MusicEvents;", "Landroid/content/BroadcastReceiver;", "reactContext", "Lcom/facebook/react/bridge/ReactContext;", "(Lcom/facebook/react/bridge/ReactContext;)V", "onReceive", "", f.X, "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MusicEvents extends BroadcastReceiver {
    public static final String BUTTON_DUCK = "remote-duck";
    public static final String BUTTON_JUMP_BACKWARD = "remote-jump-backward";
    public static final String BUTTON_JUMP_FORWARD = "remote-jump-forward";
    public static final String BUTTON_PAUSE = "remote-pause";
    public static final String BUTTON_PLAY = "remote-play";
    public static final String BUTTON_PLAY_FROM_ID = "remote-play-id";
    public static final String BUTTON_PLAY_FROM_SEARCH = "remote-play-search";
    public static final String BUTTON_SEEK_TO = "remote-seek";
    public static final String BUTTON_SET_RATING = "remote-set-rating";
    public static final String BUTTON_SKIP = "remote-skip";
    public static final String BUTTON_SKIP_NEXT = "remote-next";
    public static final String BUTTON_SKIP_PREVIOUS = "remote-previous";
    public static final String BUTTON_STOP = "remote-stop";
    public static final String EVENT_INTENT = "com.doublesymmetry.trackplayer.event";
    public static final String METADATA_CHAPTER_RECEIVED = "metadata-chapter-received";
    public static final String METADATA_COMMON_RECEIVED = "metadata-common-received";
    public static final String METADATA_PAYLOAD_KEY = "metadata";
    public static final String METADATA_TIMED_RECEIVED = "metadata-timed-received";
    public static final String PLAYBACK_ACTIVE_TRACK_CHANGED = "playback-active-track-changed";
    public static final String PLAYBACK_ERROR = "playback-error";
    public static final String PLAYBACK_METADATA = "playback-metadata-received";
    public static final String PLAYBACK_PLAY_WHEN_READY_CHANGED = "playback-play-when-ready-changed";
    public static final String PLAYBACK_PROGRESS_UPDATED = "playback-progress-updated";
    public static final String PLAYBACK_QUEUE_ENDED = "playback-queue-ended";
    public static final String PLAYBACK_STATE = "playback-state";
    public static final String PLAYBACK_TRACK_CHANGED = "playback-track-changed";
    public static final String PLAYER_ERROR = "player-error";
    private final ReactContext reactContext;

    public MusicEvents(ReactContext reactContext) {
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.reactContext = reactContext;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        String stringExtra = intent.getStringExtra("event");
        Bundle bundleExtra = intent.getBundleExtra("data");
        WritableMap writableMapFromBundle = bundleExtra != null ? Arguments.fromBundle(bundleExtra) : null;
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) this.reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class);
        Intrinsics.checkNotNull(stringExtra);
        rCTDeviceEventEmitter.emit(stringExtra, writableMapFromBundle);
    }
}
