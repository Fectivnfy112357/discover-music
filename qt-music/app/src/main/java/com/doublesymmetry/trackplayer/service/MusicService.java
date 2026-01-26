package com.doublesymmetry.trackplayer.service;

import android.app.ActivityManager;
import android.app.ForegroundServiceStartNotAllowedException;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.doublesymmetry.kotlinaudio.event.EventHolder;
import com.doublesymmetry.kotlinaudio.models.AudioItem;
import com.doublesymmetry.kotlinaudio.models.AudioItemTransitionReason;
import com.doublesymmetry.kotlinaudio.models.AudioPlayerState;
import com.doublesymmetry.kotlinaudio.models.Capability;
import com.doublesymmetry.kotlinaudio.models.FocusChangeData;
import com.doublesymmetry.kotlinaudio.models.MediaSessionCallback;
import com.doublesymmetry.kotlinaudio.models.NotificationButton;
import com.doublesymmetry.kotlinaudio.models.NotificationConfig;
import com.doublesymmetry.kotlinaudio.models.NotificationState;
import com.doublesymmetry.kotlinaudio.models.PlayWhenReadyChangeData;
import com.doublesymmetry.kotlinaudio.models.PlaybackError;
import com.doublesymmetry.kotlinaudio.models.RepeatMode;
import com.doublesymmetry.kotlinaudio.players.QueuedAudioPlayer;
import com.doublesymmetry.trackplayer.R;
import com.doublesymmetry.trackplayer.extensions.AudioPlayerStateExtKt;
import com.doublesymmetry.trackplayer.extensions.NumberExt;
import com.doublesymmetry.trackplayer.model.MetadataAdapter;
import com.doublesymmetry.trackplayer.model.PlaybackMetadata;
import com.doublesymmetry.trackplayer.model.Track;
import com.doublesymmetry.trackplayer.model.TrackAudioItem;
import com.doublesymmetry.trackplayer.module.MusicEvents;
import com.doublesymmetry.trackplayer.utils.BundleUtils;
import com.facebook.react.HeadlessJsTaskService;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.android.exoplayer2.MediaMetadata;
import com.umeng.analytics.pro.bm;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharedFlow;
import org.mozilla.universalchardet.prober.HebrewProber;
import timber.log.Timber;

/* compiled from: MusicService.kt */
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u001d\b\u0007\u0018\u0000 \u008c\u00012\u00020\u0001:\u0006\u008b\u0001\u008c\u0001\u008d\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020\fH\u0007J\u0016\u00109\u001a\u00020:2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0007J\u001e\u00109\u001a\u00020:2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u0006\u0010<\u001a\u00020%H\u0007J\b\u0010=\u001a\u00020:H\u0007J\b\u0010>\u001a\u00020:H\u0007J\u001c\u0010?\u001a\u00020:2\u0006\u0010\u000f\u001a\u00020@2\n\b\u0002\u0010A\u001a\u0004\u0018\u00010\u0014H\u0003J \u0010B\u001a\u00020:2\u0006\u0010\u000f\u001a\u00020@2\u000e\b\u0002\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00140\bH\u0003J)\u0010C\u001a\u00020:2\b\u0010D\u001a\u0004\u0018\u00010%2\b\u0010E\u001a\u0004\u0018\u00010%2\u0006\u0010F\u001a\u00020GH\u0002¢\u0006\u0002\u0010HJ\b\u0010I\u001a\u00020:H\u0002J\b\u0010J\u001a\u00020GH\u0007J\b\u0010K\u001a\u00020%H\u0007J\b\u0010L\u001a\u00020GH\u0007J\b\u0010M\u001a\u00020%H\u0002J\b\u0010N\u001a\u00020\u0014H\u0002J\u0010\u0010O\u001a\u00020\u00142\u0006\u0010-\u001a\u00020.H\u0007J\b\u0010P\u001a\u00020GH\u0007J\b\u0010Q\u001a\u00020RH\u0007J\b\u0010S\u001a\u00020TH\u0007J\u0012\u0010U\u001a\u00020V2\b\u0010W\u001a\u0004\u0018\u00010XH\u0014J\b\u0010Y\u001a\u00020RH\u0007J\u0010\u0010Z\u001a\u00020\u00172\u0006\u0010[\u001a\u00020\tH\u0002J\u0006\u0010\\\u001a\u00020\u0017J\u0010\u0010]\u001a\u00020:2\u0006\u0010;\u001a\u00020\fH\u0007J\u0018\u0010^\u001a\u00020:2\u0006\u0010_\u001a\u00020%2\u0006\u0010`\u001a\u00020%H\u0007J\b\u0010a\u001a\u00020:H\u0003J\u0012\u0010b\u001a\u00020c2\b\u0010W\u001a\u0004\u0018\u00010XH\u0017J\b\u0010d\u001a\u00020:H\u0017J\u0010\u0010e\u001a\u00020:2\u0006\u0010f\u001a\u00020%H\u0017J\"\u0010g\u001a\u00020%2\b\u0010W\u001a\u0004\u0018\u00010X2\u0006\u0010h\u001a\u00020%2\u0006\u0010i\u001a\u00020%H\u0016J\u0012\u0010j\u001a\u00020:2\b\u0010k\u001a\u0004\u0018\u00010XH\u0017J\b\u0010l\u001a\u00020:H\u0007J\b\u0010m\u001a\u00020:H\u0007J\u000e\u0010n\u001a\u00020\u0014H\u0083@¢\u0006\u0002\u0010oJ\u0016\u0010p\u001a\b\u0012\u0004\u0012\u00020\u00140q2\u0006\u0010r\u001a\u00020GH\u0003J\u0010\u0010s\u001a\u00020:2\u0006\u0010D\u001a\u00020%H\u0007J\u0016\u0010s\u001a\u00020:2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020%0\bH\u0007J\b\u0010u\u001a\u00020:H\u0007J\b\u0010v\u001a\u00020:H\u0007J\b\u0010w\u001a\u00020:H\u0007J\u0010\u0010x\u001a\u00020:2\u0006\u0010y\u001a\u00020RH\u0007J\u0010\u0010z\u001a\u00020:2\u0006\u0010{\u001a\u00020RH\u0007J\u0010\u0010|\u001a\u00020:2\u0006\u0010\u0016\u001a\u00020RH\u0007J\u0010\u0010}\u001a\u00020:2\u0006\u0010\u0016\u001a\u00020TH\u0007J\u0010\u0010~\u001a\u00020:2\u0006\u0010\u0016\u001a\u00020RH\u0007J\b\u0010\u007f\u001a\u00020:H\u0003J\u0014\u0010\u0080\u0001\u001a\u00020:2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u0014H\u0007J\u0011\u0010\u0082\u0001\u001a\u00020:2\u0006\u0010D\u001a\u00020%H\u0007J\t\u0010\u0083\u0001\u001a\u00020:H\u0007J\t\u0010\u0084\u0001\u001a\u00020:H\u0007J\t\u0010\u0085\u0001\u001a\u00020:H\u0002J\t\u0010\u0086\u0001\u001a\u00020:H\u0007J\u0019\u0010\u0087\u0001\u001a\u00020:2\u0006\u0010D\u001a\u00020%2\u0006\u0010;\u001a\u00020\fH\u0007J\u0011\u0010\u0088\u0001\u001a\u00020:2\u0006\u0010;\u001a\u00020\fH\u0007J\u0012\u0010\u0089\u0001\u001a\u00020:2\u0007\u0010\u008a\u0001\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010&\u001a\u00020%2\u0006\u0010\u0016\u001a\u00020%8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u000e\u0010+\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010-\u001a\u00020.8F¢\u0006\u0006\u001a\u0004\b/\u00100R\u000e\u00101\u001a\u00020%X\u0082\u000e¢\u0006\u0002\n\u0000R&\u00103\u001a\u00020\u00172\u0006\u00102\u001a\u00020\u00178\u0006@BX\u0087\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b4\u0010\u0002\u001a\u0004\b5\u0010\u001aR\u0017\u00106\u001a\b\u0012\u0004\u0012\u00020\f0\b8F¢\u0006\u0006\u001a\u0004\b7\u00108¨\u0006\u008e\u0001"}, d2 = {"Lcom/doublesymmetry/trackplayer/service/MusicService;", "Lcom/facebook/react/HeadlessJsTaskService;", "()V", MusicService.APP_KILLED_PLAYBACK_BEHAVIOR_KEY, "Lcom/doublesymmetry/trackplayer/service/MusicService$AppKilledPlaybackBehavior;", "binder", "Lcom/doublesymmetry/trackplayer/service/MusicService$MusicBinder;", "capabilities", "", "Lcom/doublesymmetry/kotlinaudio/models/Capability;", "compactCapabilities", "currentTrack", "Lcom/doublesymmetry/trackplayer/model/Track;", "getCurrentTrack", "()Lcom/doublesymmetry/trackplayer/model/Track;", "event", "Lcom/doublesymmetry/kotlinaudio/event/EventHolder;", "getEvent", "()Lcom/doublesymmetry/kotlinaudio/event/EventHolder;", "latestOptions", "Landroid/os/Bundle;", "notificationCapabilities", "value", "", "playWhenReady", "getPlayWhenReady", "()Z", "setPlayWhenReady", "(Z)V", "playbackError", "Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;", "getPlaybackError", "()Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;", "player", "Lcom/doublesymmetry/kotlinaudio/players/QueuedAudioPlayer;", "progressUpdateJob", "Lkotlinx/coroutines/Job;", "", "ratingType", "getRatingType", "()I", "setRatingType", "(I)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", MusicService.STATE_KEY, "Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", "getState", "()Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", MusicService.STOP_FOREGROUND_GRACE_PERIOD_KEY, "<set-?>", MusicService.STOPPING_APP_PAUSES_PLAYBACK_KEY, "getStoppingAppPausesPlayback$annotations", "getStoppingAppPausesPlayback", "tracks", "getTracks", "()Ljava/util/List;", "add", "", MusicService.TRACK_KEY, "atIndex", "clear", "clearNotificationMetadata", "emit", "", "data", "emitList", "emitPlaybackTrackChangedEvents", "index", "previousIndex", "oldPosition", "", "(Ljava/lang/Integer;Ljava/lang/Integer;D)V", "emitQueueEndedEvent", "getBufferedPositionInSeconds", "getCurrentTrackIndex", "getDurationInSeconds", "getPendingIntentFlags", "getPlaybackErrorBundle", "getPlayerStateBundle", "getPositionInSeconds", "getRate", "", "getRepeatMode", "Lcom/doublesymmetry/kotlinaudio/models/RepeatMode;", "getTaskConfig", "Lcom/facebook/react/jstasks/HeadlessJsTaskConfig;", "intent", "Landroid/content/Intent;", "getVolume", "isCompact", "capability", "isForegroundService", "load", "move", "fromIndex", "toIndex", "observeEvents", "onBind", "Landroid/os/IBinder;", "onDestroy", "onHeadlessJsTaskFinish", "taskId", "onStartCommand", "flags", "startId", "onTaskRemoved", "rootIntent", "pause", "play", "progressUpdateEvent", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "progressUpdateEventFlow", "Lkotlinx/coroutines/flow/Flow;", bm.aY, "remove", "indexes", "removePreviousTracks", "removeUpcomingTracks", "retry", "seekBy", "offset", "seekTo", "seconds", "setRate", "setRepeatMode", "setVolume", "setupForegrounding", "setupPlayer", "playerOptions", "skip", "skipToNext", "skipToPrevious", "startAndStopEmptyNotificationToAvoidANR", "stop", "updateMetadataForTrack", "updateNowPlayingMetadata", "updateOptions", "options", "AppKilledPlaybackBehavior", "Companion", "MusicBinder", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MusicService extends HeadlessJsTaskService {
    public static final String ANDROID_AUDIO_CONTENT_TYPE = "androidAudioContentType";
    public static final String ANDROID_OPTIONS_KEY = "android";
    public static final String APP_KILLED_PLAYBACK_BEHAVIOR_KEY = "appKilledPlaybackBehavior";
    public static final String AUTO_HANDLE_INTERRUPTIONS = "autoHandleInterruptions";
    public static final String AUTO_UPDATE_METADATA = "autoUpdateMetadata";
    public static final String BACKWARD_JUMP_INTERVAL_KEY = "backwardJumpInterval";
    public static final String BACK_BUFFER_KEY = "backBuffer";
    public static final String BUFFERED_POSITION_KEY = "buffered";
    public static final String DATA_KEY = "data";
    public static final double DEFAULT_JUMP_INTERVAL = 15.0d;
    public static final int DEFAULT_STOP_FOREGROUND_GRACE_PERIOD = 5;
    public static final String DURATION_KEY = "duration";
    public static final int EMPTY_NOTIFICATION_ID = 1;
    public static final String ERROR_KEY = "error";
    public static final String EVENT_KEY = "event";
    public static final String FORWARD_JUMP_INTERVAL_KEY = "forwardJumpInterval";
    public static final String IS_FOCUS_LOSS_PERMANENT_KEY = "permanent";
    public static final String IS_PAUSED_KEY = "paused";
    public static final String MAX_BUFFER_KEY = "maxBuffer";
    public static final String MAX_CACHE_SIZE_KEY = "maxCacheSize";
    public static final String MIN_BUFFER_KEY = "minBuffer";
    public static final String NEXT_TRACK_KEY = "nextTrack";
    public static final String PAUSE_ON_INTERRUPTION_KEY = "alwaysPauseOnInterruption";
    public static final String PLAY_BUFFER_KEY = "playBuffer";
    public static final String POSITION_KEY = "position";
    public static final String PROGRESS_UPDATE_EVENT_INTERVAL_KEY = "progressUpdateEventInterval";
    public static final String STATE_KEY = "state";
    public static final String STOPPING_APP_PAUSES_PLAYBACK_KEY = "stoppingAppPausesPlayback";
    public static final String STOP_FOREGROUND_GRACE_PERIOD_KEY = "stopForegroundGracePeriod";
    public static final String TASK_KEY = "TrackPlayer";
    public static final String TRACK_KEY = "track";
    private Bundle latestOptions;
    private QueuedAudioPlayer player;
    private Job progressUpdateJob;
    private final MusicBinder binder = new MusicBinder();
    private final CoroutineScope scope = CoroutineScopeKt.MainScope();
    private boolean stoppingAppPausesPlayback = true;
    private AppKilledPlaybackBehavior appKilledPlaybackBehavior = AppKilledPlaybackBehavior.CONTINUE_PLAYBACK;
    private int stopForegroundGracePeriod = 5;
    private List<? extends Capability> capabilities = CollectionsKt.emptyList();
    private List<? extends Capability> notificationCapabilities = CollectionsKt.emptyList();
    private List<? extends Capability> compactCapabilities = CollectionsKt.emptyList();

    /* compiled from: MusicService.kt */
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Capability.values().length];
            try {
                iArr[Capability.PLAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Capability.PAUSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Capability.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Capability.SKIP_TO_NEXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Capability.SKIP_TO_PREVIOUS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Capability.JUMP_FORWARD.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[Capability.JUMP_BACKWARD.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[Capability.SEEK_TO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[AppKilledPlaybackBehavior.values().length];
            try {
                iArr2[AppKilledPlaybackBehavior.PAUSE_PLAYBACK.ordinal()] = 1;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[AppKilledPlaybackBehavior.STOP_PLAYBACK_AND_REMOVE_NOTIFICATION.ordinal()] = 2;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private final int getPendingIntentFlags() {
        return 335544320;
    }

    @Deprecated(message = "This will be removed soon")
    public static /* synthetic */ void getStoppingAppPausesPlayback$annotations() {
    }

    @Override // com.facebook.react.HeadlessJsTaskService, com.facebook.react.jstasks.HeadlessJsTaskEventListener
    public void onHeadlessJsTaskFinish(int taskId) {
    }

    public final boolean getStoppingAppPausesPlayback() {
        return this.stoppingAppPausesPlayback;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/doublesymmetry/trackplayer/service/MusicService$AppKilledPlaybackBehavior;", "", "string", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getString", "()Ljava/lang/String;", "CONTINUE_PLAYBACK", "PAUSE_PLAYBACK", "STOP_PLAYBACK_AND_REMOVE_NOTIFICATION", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class AppKilledPlaybackBehavior {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ AppKilledPlaybackBehavior[] $VALUES;
        public static final AppKilledPlaybackBehavior CONTINUE_PLAYBACK = new AppKilledPlaybackBehavior("CONTINUE_PLAYBACK", 0, "continue-playback");
        public static final AppKilledPlaybackBehavior PAUSE_PLAYBACK = new AppKilledPlaybackBehavior("PAUSE_PLAYBACK", 1, "pause-playback");
        public static final AppKilledPlaybackBehavior STOP_PLAYBACK_AND_REMOVE_NOTIFICATION = new AppKilledPlaybackBehavior("STOP_PLAYBACK_AND_REMOVE_NOTIFICATION", 2, "stop-playback-and-remove-notification");
        private final String string;

        private static final /* synthetic */ AppKilledPlaybackBehavior[] $values() {
            return new AppKilledPlaybackBehavior[]{CONTINUE_PLAYBACK, PAUSE_PLAYBACK, STOP_PLAYBACK_AND_REMOVE_NOTIFICATION};
        }

        public static EnumEntries<AppKilledPlaybackBehavior> getEntries() {
            return $ENTRIES;
        }

        public static AppKilledPlaybackBehavior valueOf(String str) {
            return (AppKilledPlaybackBehavior) Enum.valueOf(AppKilledPlaybackBehavior.class, str);
        }

        public static AppKilledPlaybackBehavior[] values() {
            return (AppKilledPlaybackBehavior[]) $VALUES.clone();
        }

        private AppKilledPlaybackBehavior(String str, int i, String str2) {
            this.string = str2;
        }

        public final String getString() {
            return this.string;
        }

        static {
            AppKilledPlaybackBehavior[] appKilledPlaybackBehaviorArr$values = $values();
            $VALUES = appKilledPlaybackBehaviorArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(appKilledPlaybackBehaviorArr$values);
        }
    }

    public final List<Track> getTracks() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        List<AudioItem> items = queuedAudioPlayer.getItems();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(items, 10));
        for (AudioItem audioItem : items) {
            Intrinsics.checkNotNull(audioItem, "null cannot be cast to non-null type com.doublesymmetry.trackplayer.model.TrackAudioItem");
            arrayList.add(((TrackAudioItem) audioItem).getTrack());
        }
        return arrayList;
    }

    public final Track getCurrentTrack() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        AudioItem currentItem = queuedAudioPlayer.getCurrentItem();
        Intrinsics.checkNotNull(currentItem, "null cannot be cast to non-null type com.doublesymmetry.trackplayer.model.TrackAudioItem");
        return ((TrackAudioItem) currentItem).getTrack();
    }

    public final AudioPlayerState getState() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getPlayerState();
    }

    public final int getRatingType() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getRatingType();
    }

    public final void setRatingType(int i) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.setRatingType(i);
    }

    public final PlaybackError getPlaybackError() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getPlaybackError();
    }

    public final EventHolder getEvent() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getEvent();
    }

    public final boolean getPlayWhenReady() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getPlayWhenReady();
    }

    public final void setPlayWhenReady(boolean z) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.setPlayWhenReady(z);
    }

    @Override // com.facebook.react.HeadlessJsTaskService, android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTask(getTaskConfig(intent));
        startAndStopEmptyNotificationToAvoidANR();
        return 1;
    }

    private final void startAndStopEmptyNotificationToAvoidANR() {
        Object systemService = getSystemService("notification");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
        NotificationManager notificationManager = (NotificationManager) systemService;
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel(getString(R.string.rntp_temporary_channel_id), getString(R.string.rntp_temporary_channel_name), 2));
        }
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this, getString(R.string.rntp_temporary_channel_id)).setPriority(-1).setCategory(NotificationCompat.CATEGORY_SERVICE).setSmallIcon(com.google.android.exoplayer2.ui.R.drawable.exo_notification_small_icon);
        Intrinsics.checkNotNullExpressionValue(smallIcon, "setSmallIcon(...)");
        if (Build.VERSION.SDK_INT >= 31) {
            smallIcon.setForegroundServiceBehavior(1);
        }
        Notification notificationBuild = smallIcon.build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        startForeground(1, notificationBuild);
        stopForeground(true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void setupPlayer(android.os.Bundle r14) {
        /*
            Method dump skipped, instructions count: 306
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.trackplayer.service.MusicService.setupPlayer(android.os.Bundle):void");
    }

    public final void updateOptions(Bundle options) {
        AppKilledPlaybackBehavior appKilledPlaybackBehavior;
        ArrayList arrayListEmptyList;
        ArrayList arrayListEmptyList2;
        ArrayList arrayListEmptyList3;
        NotificationButton play_pause;
        Intrinsics.checkNotNullParameter(options, "options");
        this.latestOptions = options;
        Bundle bundle = options.getBundle(ANDROID_OPTIONS_KEY);
        C01501 c01501 = new PropertyReference1Impl() { // from class: com.doublesymmetry.trackplayer.service.MusicService.updateOptions.1
            @Override // kotlin.jvm.internal.PropertyReference1Impl, kotlin.reflect.KProperty1
            public Object get(Object obj) {
                return ((AppKilledPlaybackBehavior) obj).getString();
            }
        };
        String string = bundle != null ? bundle.getString(APP_KILLED_PLAYBACK_BEHAVIOR_KEY) : null;
        AppKilledPlaybackBehavior[] appKilledPlaybackBehaviorArrValues = AppKilledPlaybackBehavior.values();
        int length = appKilledPlaybackBehaviorArrValues.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                appKilledPlaybackBehavior = null;
                break;
            }
            appKilledPlaybackBehavior = appKilledPlaybackBehaviorArrValues[i];
            if (Intrinsics.areEqual(c01501.invoke(appKilledPlaybackBehavior), string)) {
                break;
            } else {
                i++;
            }
        }
        AppKilledPlaybackBehavior appKilledPlaybackBehavior2 = appKilledPlaybackBehavior;
        if (appKilledPlaybackBehavior2 == null) {
            appKilledPlaybackBehavior2 = AppKilledPlaybackBehavior.CONTINUE_PLAYBACK;
        }
        this.appKilledPlaybackBehavior = appKilledPlaybackBehavior2;
        Integer intOrNull = BundleUtils.INSTANCE.getIntOrNull(bundle, STOP_FOREGROUND_GRACE_PERIOD_KEY);
        if (intOrNull != null) {
            this.stopForegroundGracePeriod = intOrNull.intValue();
        }
        options.getBoolean(STOPPING_APP_PAUSES_PLAYBACK_KEY);
        boolean z = options.getBoolean(STOPPING_APP_PAUSES_PLAYBACK_KEY);
        this.stoppingAppPausesPlayback = z;
        if (z) {
            this.appKilledPlaybackBehavior = AppKilledPlaybackBehavior.PAUSE_PLAYBACK;
        }
        setRatingType(BundleUtils.INSTANCE.getInt(options, "ratingType", 0));
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.getPlayerOptions().setAlwaysPauseOnInterruption(bundle != null ? bundle.getBoolean(PAUSE_ON_INTERRUPTION_KEY) : false);
        ArrayList<Integer> integerArrayList = options.getIntegerArrayList("capabilities");
        if (integerArrayList == null) {
            arrayListEmptyList = CollectionsKt.emptyList();
        } else {
            ArrayList<Integer> arrayList = integerArrayList;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (Integer num : arrayList) {
                Capability[] capabilityArrValues = Capability.values();
                Intrinsics.checkNotNull(num);
                arrayList2.add(capabilityArrValues[num.intValue()]);
            }
            arrayListEmptyList = arrayList2;
        }
        this.capabilities = arrayListEmptyList;
        ArrayList<Integer> integerArrayList2 = options.getIntegerArrayList("notificationCapabilities");
        if (integerArrayList2 == null) {
            arrayListEmptyList2 = CollectionsKt.emptyList();
        } else {
            ArrayList<Integer> arrayList3 = integerArrayList2;
            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList3, 10));
            for (Integer num2 : arrayList3) {
                Capability[] capabilityArrValues2 = Capability.values();
                Intrinsics.checkNotNull(num2);
                arrayList4.add(capabilityArrValues2[num2.intValue()]);
            }
            arrayListEmptyList2 = arrayList4;
        }
        this.notificationCapabilities = arrayListEmptyList2;
        ArrayList<Integer> integerArrayList3 = options.getIntegerArrayList("compactCapabilities");
        if (integerArrayList3 == null) {
            arrayListEmptyList3 = CollectionsKt.emptyList();
        } else {
            ArrayList<Integer> arrayList5 = integerArrayList3;
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
            for (Integer num3 : arrayList5) {
                Capability[] capabilityArrValues3 = Capability.values();
                Intrinsics.checkNotNull(num3);
                arrayList6.add(capabilityArrValues3[num3.intValue()]);
            }
            arrayListEmptyList3 = arrayList6;
        }
        this.compactCapabilities = arrayListEmptyList3;
        if (this.notificationCapabilities.isEmpty()) {
            this.notificationCapabilities = this.capabilities;
        }
        List<? extends Capability> list = this.notificationCapabilities;
        ArrayList arrayList7 = new ArrayList();
        for (Capability capability : list) {
            switch (WhenMappings.$EnumSwitchMapping$0[capability.ordinal()]) {
                case 1:
                case 2:
                    MusicService musicService = this;
                    play_pause = new NotificationButton.PLAY_PAUSE(BundleUtils.INSTANCE.getIconOrNull(musicService, options, "playIcon"), BundleUtils.INSTANCE.getIconOrNull(musicService, options, "pauseIcon"));
                    break;
                case 3:
                    play_pause = new NotificationButton.STOP(BundleUtils.INSTANCE.getIconOrNull(this, options, "stopIcon"));
                    break;
                case 4:
                    play_pause = new NotificationButton.NEXT(BundleUtils.INSTANCE.getIconOrNull(this, options, "nextIcon"), isCompact(capability));
                    break;
                case 5:
                    play_pause = new NotificationButton.PREVIOUS(BundleUtils.INSTANCE.getIconOrNull(this, options, "previousIcon"), isCompact(capability));
                    break;
                case 6:
                    play_pause = new NotificationButton.FORWARD(Integer.valueOf(BundleUtils.INSTANCE.getIcon(this, options, "forwardIcon", R.drawable.forward)), isCompact(capability));
                    break;
                case 7:
                    play_pause = new NotificationButton.BACKWARD(Integer.valueOf(BundleUtils.INSTANCE.getIcon(this, options, "rewindIcon", R.drawable.rewind)), isCompact(capability));
                    break;
                case 8:
                    play_pause = NotificationButton.SEEK_TO.INSTANCE;
                    break;
                default:
                    play_pause = null;
                    break;
            }
            if (play_pause != null) {
                arrayList7.add(play_pause);
            }
        }
        ArrayList arrayList8 = arrayList7;
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage != null) {
            launchIntentForPackage.setFlags(603979776);
            launchIntentForPackage.setData(Uri.parse("trackplayer://notification.click"));
            launchIntentForPackage.setAction("android.intent.action.VIEW");
        } else {
            launchIntentForPackage = null;
        }
        MusicService musicService2 = this;
        NotificationConfig notificationConfig = new NotificationConfig(arrayList8, BundleUtils.INSTANCE.getIntOrNull(options, "color"), BundleUtils.INSTANCE.getIconOrNull(musicService2, options, "icon"), PendingIntent.getActivity(musicService2, 0, launchIntentForPackage, getPendingIntentFlags()));
        QueuedAudioPlayer queuedAudioPlayer2 = this.player;
        if (queuedAudioPlayer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer2 = null;
        }
        queuedAudioPlayer2.getNotificationManager().createNotification(notificationConfig);
        Job job = this.progressUpdateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Double doubleOrNull = BundleUtils.INSTANCE.getDoubleOrNull(options, PROGRESS_UPDATE_EVENT_INTERVAL_KEY);
        if (doubleOrNull == null || doubleOrNull.doubleValue() <= 0.0d) {
            return;
        }
        this.progressUpdateJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01517(doubleOrNull, null), 3, null);
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$updateOptions$7", f = "MusicService.kt", i = {}, l = {HebrewProber.FINAL_TSADI}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$updateOptions$7, reason: invalid class name and case insensitive filesystem */
    static final class C01517 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Double $updateInterval;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01517(Double d, Continuation<? super C01517> continuation) {
            super(2, continuation);
            this.$updateInterval = d;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new C01517(this.$updateInterval, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01517) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow flowProgressUpdateEventFlow = MusicService.this.progressUpdateEventFlow(this.$updateInterval.doubleValue());
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (flowProgressUpdateEventFlow.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.updateOptions.7.1
                    public final Object emit(Bundle bundle, Continuation<? super Unit> continuation) {
                        musicService.emit(MusicEvents.PLAYBACK_PROGRESS_UPDATED, bundle);
                        return Unit.INSTANCE;
                    }

                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((Bundle) obj2, (Continuation<? super Unit>) continuation);
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroid/os/Bundle;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$progressUpdateEventFlow$1", f = "MusicService.kt", i = {0, 1, 2}, l = {254, 255, 258}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$progressUpdateEventFlow$1, reason: invalid class name and case insensitive filesystem */
    static final class C01471 extends SuspendLambda implements Function2<FlowCollector<? super Bundle>, Continuation<? super Unit>, Object> {
        final /* synthetic */ double $interval;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01471(double d, Continuation<? super C01471> continuation) {
            super(2, continuation);
            this.$interval = d;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01471 c01471 = MusicService.this.new C01471(this.$interval, continuation);
            c01471.L$0 = obj;
            return c01471;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Bundle> flowCollector, Continuation<? super Unit> continuation) {
            return ((C01471) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x004d  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x006f A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0085 A[RETURN] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x0083 -> B:8:0x0018). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 3
                r3 = 2
                r4 = 1
                if (r1 == 0) goto L32
                if (r1 == r4) goto L2a
                if (r1 == r3) goto L22
                if (r1 != r2) goto L1a
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
            L18:
                r11 = r1
                goto L39
            L1a:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L22:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L71
            L2a:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L60
            L32:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
            L39:
                com.doublesymmetry.trackplayer.service.MusicService r1 = com.doublesymmetry.trackplayer.service.MusicService.this
                com.doublesymmetry.kotlinaudio.players.QueuedAudioPlayer r1 = com.doublesymmetry.trackplayer.service.MusicService.access$getPlayer$p(r1)
                if (r1 != 0) goto L47
                java.lang.String r1 = "player"
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r1 = 0
            L47:
                boolean r1 = r1.isPlaying()
                if (r1 == 0) goto L70
                com.doublesymmetry.trackplayer.service.MusicService r1 = com.doublesymmetry.trackplayer.service.MusicService.this
                r5 = r10
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r10.L$0 = r11
                r10.label = r4
                java.lang.Object r1 = com.doublesymmetry.trackplayer.service.MusicService.access$progressUpdateEvent(r1, r5)
                if (r1 != r0) goto L5d
                return r0
            L5d:
                r9 = r1
                r1 = r11
                r11 = r9
            L60:
                android.os.Bundle r11 = (android.os.Bundle) r11
                r5 = r10
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r10.L$0 = r1
                r10.label = r3
                java.lang.Object r11 = r1.emit(r11, r5)
                if (r11 != r0) goto L71
                return r0
            L70:
                r1 = r11
            L71:
                double r5 = r10.$interval
                r11 = 1000(0x3e8, float:1.401E-42)
                double r7 = (double) r11
                double r5 = r5 * r7
                long r5 = (long) r5
                r11 = r10
                kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
                r10.L$0 = r1
                r10.label = r2
                java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r5, r11)
                if (r11 != r0) goto L18
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.trackplayer.service.MusicService.C01471.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Flow<Bundle> progressUpdateEventFlow(double interval) {
        return FlowKt.flow(new C01471(interval, null));
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$progressUpdateEvent$2", f = "MusicService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$progressUpdateEvent$2, reason: invalid class name and case insensitive filesystem */
    static final class C01462 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bundle>, Object> {
        int label;

        C01462(Continuation<? super C01462> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new C01462(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bundle> continuation) {
            return ((C01462) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Bundle bundle = new Bundle();
            MusicService musicService = MusicService.this;
            NumberExt.Companion companion = NumberExt.INSTANCE;
            QueuedAudioPlayer queuedAudioPlayer = musicService.player;
            QueuedAudioPlayer queuedAudioPlayer2 = null;
            if (queuedAudioPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                queuedAudioPlayer = null;
            }
            bundle.putDouble("position", companion.toSeconds(Boxing.boxLong(queuedAudioPlayer.getPosition())));
            NumberExt.Companion companion2 = NumberExt.INSTANCE;
            QueuedAudioPlayer queuedAudioPlayer3 = musicService.player;
            if (queuedAudioPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                queuedAudioPlayer3 = null;
            }
            bundle.putDouble("duration", companion2.toSeconds(Boxing.boxLong(queuedAudioPlayer3.getDuration())));
            NumberExt.Companion companion3 = NumberExt.INSTANCE;
            QueuedAudioPlayer queuedAudioPlayer4 = musicService.player;
            if (queuedAudioPlayer4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                queuedAudioPlayer4 = null;
            }
            bundle.putDouble(MusicService.BUFFERED_POSITION_KEY, companion3.toSeconds(Boxing.boxLong(queuedAudioPlayer4.getBufferedPosition())));
            QueuedAudioPlayer queuedAudioPlayer5 = musicService.player;
            if (queuedAudioPlayer5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
            } else {
                queuedAudioPlayer2 = queuedAudioPlayer5;
            }
            bundle.putInt(MusicService.TRACK_KEY, queuedAudioPlayer2.getCurrentIndex());
            return bundle;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object progressUpdateEvent(Continuation<? super Bundle> continuation) {
        return BuildersKt.withContext(Dispatchers.getMain(), new C01462(null), continuation);
    }

    private final boolean isCompact(Capability capability) {
        return this.compactCapabilities.contains(capability);
    }

    public final void add(Track track) {
        Intrinsics.checkNotNullParameter(track, "track");
        add(CollectionsKt.listOf(track));
    }

    public final void add(List<Track> tracks) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        List<Track> list = tracks;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Track) it.next()).toAudioItem());
        }
        ArrayList arrayList2 = arrayList;
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.add(arrayList2);
    }

    public final void add(List<Track> tracks, int atIndex) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        List<Track> list = tracks;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((Track) it.next()).toAudioItem());
        }
        ArrayList arrayList2 = arrayList;
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.add(arrayList2, atIndex);
    }

    public final void load(Track track) {
        Intrinsics.checkNotNullParameter(track, "track");
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.load(track.toAudioItem());
    }

    public final void move(int fromIndex, int toIndex) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.move(fromIndex, toIndex);
    }

    public final void remove(int index) {
        remove(CollectionsKt.listOf(Integer.valueOf(index)));
    }

    public final void remove(List<Integer> indexes) {
        Intrinsics.checkNotNullParameter(indexes, "indexes");
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.remove(indexes);
    }

    public final void clear() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.clear();
    }

    public final void play() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.play();
    }

    public final void pause() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.pause();
    }

    public final void stop() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.stop();
    }

    public final void removeUpcomingTracks() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.removeUpcomingItems();
    }

    public final void removePreviousTracks() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.removePreviousItems();
    }

    public final void skip(int index) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.jumpToItem(index);
    }

    public final void skipToNext() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.next();
    }

    public final void skipToPrevious() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.previous();
    }

    public final void seekTo(float seconds) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.seek((long) (seconds * 1000), TimeUnit.MILLISECONDS);
    }

    public final void seekBy(float offset) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.seekBy((long) offset, TimeUnit.SECONDS);
    }

    public final void retry() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.prepare();
    }

    public final int getCurrentTrackIndex() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getCurrentIndex();
    }

    public final float getRate() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getPlaybackSpeed();
    }

    public final void setRate(float value) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.setPlaybackSpeed(value);
    }

    public final RepeatMode getRepeatMode() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getPlayerOptions().getRepeatMode();
    }

    public final void setRepeatMode(RepeatMode value) {
        Intrinsics.checkNotNullParameter(value, "value");
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.getPlayerOptions().setRepeatMode(value);
    }

    public final float getVolume() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return queuedAudioPlayer.getVolume();
    }

    public final void setVolume(float value) {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.setVolume(value);
    }

    public final double getDurationInSeconds() {
        NumberExt.Companion companion = NumberExt.INSTANCE;
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return companion.toSeconds(Long.valueOf(queuedAudioPlayer.getDuration()));
    }

    public final double getPositionInSeconds() {
        NumberExt.Companion companion = NumberExt.INSTANCE;
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return companion.toSeconds(Long.valueOf(queuedAudioPlayer.getPosition()));
    }

    public final double getBufferedPositionInSeconds() {
        NumberExt.Companion companion = NumberExt.INSTANCE;
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        return companion.toSeconds(Long.valueOf(queuedAudioPlayer.getBufferedPosition()));
    }

    public final Bundle getPlayerStateBundle(AudioPlayerState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        Bundle bundle = new Bundle();
        bundle.putString(STATE_KEY, AudioPlayerStateExtKt.getAsLibState(state).getState());
        if (state == AudioPlayerState.ERROR) {
            bundle.putBundle("error", getPlaybackErrorBundle());
        }
        return bundle;
    }

    public final void updateMetadataForTrack(int index, Track track) {
        Intrinsics.checkNotNullParameter(track, "track");
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.replaceItem(index, track.toAudioItem());
    }

    public final void updateNowPlayingMetadata(Track track) {
        Intrinsics.checkNotNullParameter(track, "track");
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.getNotificationManager().overrideMetadata(track.toAudioItem());
    }

    public final void clearNotificationMetadata() {
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        queuedAudioPlayer.getNotificationManager().hideNotification();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitPlaybackTrackChangedEvents(Integer index, Integer previousIndex, double oldPosition) {
        Bundle bundle = new Bundle();
        bundle.putDouble("position", oldPosition);
        if (index != null) {
            bundle.putInt(NEXT_TRACK_KEY, index.intValue());
        }
        if (previousIndex != null) {
            bundle.putInt(TRACK_KEY, previousIndex.intValue());
        }
        emit(MusicEvents.PLAYBACK_TRACK_CHANGED, bundle);
        Bundle bundle2 = new Bundle();
        bundle2.putDouble("lastPosition", oldPosition);
        if (!getTracks().isEmpty()) {
            QueuedAudioPlayer queuedAudioPlayer = this.player;
            QueuedAudioPlayer queuedAudioPlayer2 = null;
            if (queuedAudioPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                queuedAudioPlayer = null;
            }
            bundle2.putInt("index", queuedAudioPlayer.getCurrentIndex());
            List<Track> tracks = getTracks();
            QueuedAudioPlayer queuedAudioPlayer3 = this.player;
            if (queuedAudioPlayer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
            } else {
                queuedAudioPlayer2 = queuedAudioPlayer3;
            }
            bundle2.putBundle(TRACK_KEY, tracks.get(queuedAudioPlayer2.getCurrentIndex()).getOriginalItem());
            if (previousIndex != null) {
                bundle2.putInt("lastIndex", previousIndex.intValue());
                bundle2.putBundle("lastTrack", getTracks().get(previousIndex.intValue()).getOriginalItem());
            }
        }
        emit(MusicEvents.PLAYBACK_ACTIVE_TRACK_CHANGED, bundle2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emitQueueEndedEvent() {
        Bundle bundle = new Bundle();
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        QueuedAudioPlayer queuedAudioPlayer2 = null;
        if (queuedAudioPlayer == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer = null;
        }
        bundle.putInt(TRACK_KEY, queuedAudioPlayer.getCurrentIndex());
        NumberExt.Companion companion = NumberExt.INSTANCE;
        QueuedAudioPlayer queuedAudioPlayer3 = this.player;
        if (queuedAudioPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
        } else {
            queuedAudioPlayer2 = queuedAudioPlayer3;
        }
        bundle.putDouble("position", companion.toSeconds(Long.valueOf(queuedAudioPlayer2.getPosition())));
        emit(MusicEvents.PLAYBACK_QUEUE_ENDED, bundle);
    }

    public final boolean isForegroundService() {
        Object systemService = getBaseContext().getSystemService("activity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) systemService).getRunningServices(Integer.MAX_VALUE)) {
            if (Intrinsics.areEqual(MusicService.class.getName(), runningServiceInfo.service.getClassName())) {
                return runningServiceInfo.foreground;
            }
        }
        Timber.INSTANCE.e("isForegroundService found no matching service", new Object[0]);
        return false;
    }

    private final void setupForegrounding() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01481(booleanRef, booleanRef2, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01492(objectRef, objectRef2, booleanRef, booleanRef2, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setupForegrounding$startForegroundIfNecessary(MusicService musicService, Ref.ObjectRef<Notification> objectRef, Ref.ObjectRef<Integer> objectRef2) {
        if (musicService.isForegroundService()) {
            Timber.INSTANCE.d("skipping foregrounding as the service is already foregrounded", new Object[0]);
            return;
        }
        if (objectRef.element == null) {
            Timber.INSTANCE.d("can't startForeground as the notification is null", new Object[0]);
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                Integer num = objectRef2.element;
                Intrinsics.checkNotNull(num);
                int iIntValue = num.intValue();
                Notification notification = objectRef.element;
                Intrinsics.checkNotNull(notification);
                musicService.startForeground(iIntValue, notification, 2);
            } else {
                Integer num2 = objectRef2.element;
                Intrinsics.checkNotNull(num2);
                musicService.startForeground(num2.intValue(), objectRef.element);
            }
            Timber.INSTANCE.d("notification has been foregrounded", new Object[0]);
        } catch (Exception e) {
            if (Build.VERSION.SDK_INT < 31 || !(e instanceof ForegroundServiceStartNotAllowedException)) {
                return;
            }
            Timber.INSTANCE.e("ForegroundServiceStartNotAllowedException: App tried to start a foreground Service when it was not allowed to do so.", e);
            Bundle bundle = new Bundle();
            bundle.putString(StackTraceHelper.MESSAGE_KEY, e.getMessage());
            bundle.putString("code", "android-foreground-service-start-not-allowed");
            Unit unit = Unit.INSTANCE;
            musicService.emit(MusicEvents.PLAYER_ERROR, bundle);
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$setupForegrounding$1", f = "MusicService.kt", i = {}, l = {556}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$setupForegrounding$1, reason: invalid class name and case insensitive filesystem */
    static final class C01481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.BooleanRef $removeNotificationWhenNotOngoing;
        final /* synthetic */ Ref.BooleanRef $stopForegroundWhenNotOngoing;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01481(Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, Continuation<? super C01481> continuation) {
            super(2, continuation);
            this.$stopForegroundWhenNotOngoing = booleanRef;
            this.$removeNotificationWhenNotOngoing = booleanRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new C01481(this.$stopForegroundWhenNotOngoing, this.$removeNotificationWhenNotOngoing, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final List listListOf = CollectionsKt.listOf((Object[]) new AudioPlayerState[]{AudioPlayerState.IDLE, AudioPlayerState.ENDED, AudioPlayerState.STOPPED, AudioPlayerState.ERROR, AudioPlayerState.PAUSED});
                final List listListOf2 = CollectionsKt.listOf((Object[]) new AudioPlayerState[]{AudioPlayerState.IDLE, AudioPlayerState.STOPPED, AudioPlayerState.ERROR});
                final List listListOf3 = CollectionsKt.listOf((Object[]) new AudioPlayerState[]{AudioPlayerState.LOADING, AudioPlayerState.READY, AudioPlayerState.BUFFERING});
                final Ref.IntRef intRef = new Ref.IntRef();
                SharedFlow<AudioPlayerState> stateChange = MusicService.this.getEvent().getStateChange();
                final Ref.BooleanRef booleanRef = this.$stopForegroundWhenNotOngoing;
                final Ref.BooleanRef booleanRef2 = this.$removeNotificationWhenNotOngoing;
                this.label = 1;
                if (stateChange.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.setupForegrounding.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((AudioPlayerState) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(AudioPlayerState audioPlayerState, Continuation<? super Unit> continuation) {
                        intRef.element++;
                        if (listListOf3.contains(audioPlayerState)) {
                            return Unit.INSTANCE;
                        }
                        booleanRef.element = intRef.element > 1 && listListOf.contains(audioPlayerState);
                        booleanRef2.element = booleanRef.element && listListOf2.contains(audioPlayerState);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setupForegrounding$shouldStopForeground(Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, MusicService musicService) {
        return booleanRef.element && (booleanRef2.element || musicService.isForegroundService());
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$setupForegrounding$2", f = "MusicService.kt", i = {}, l = {571}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$setupForegrounding$2, reason: invalid class name and case insensitive filesystem */
    static final class C01492 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<Notification> $notification;
        final /* synthetic */ Ref.ObjectRef<Integer> $notificationId;
        final /* synthetic */ Ref.BooleanRef $removeNotificationWhenNotOngoing;
        final /* synthetic */ Ref.BooleanRef $stopForegroundWhenNotOngoing;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01492(Ref.ObjectRef<Integer> objectRef, Ref.ObjectRef<Notification> objectRef2, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, Continuation<? super C01492> continuation) {
            super(2, continuation);
            this.$notificationId = objectRef;
            this.$notification = objectRef2;
            this.$stopForegroundWhenNotOngoing = booleanRef;
            this.$removeNotificationWhenNotOngoing = booleanRef2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new C01492(this.$notificationId, this.$notification, this.$stopForegroundWhenNotOngoing, this.$removeNotificationWhenNotOngoing, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01492) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<NotificationState> notificationStateChange = MusicService.this.getEvent().getNotificationStateChange();
                final Ref.ObjectRef<Integer> objectRef = this.$notificationId;
                final Ref.ObjectRef<Notification> objectRef2 = this.$notification;
                final MusicService musicService = MusicService.this;
                final Ref.BooleanRef booleanRef = this.$stopForegroundWhenNotOngoing;
                final Ref.BooleanRef booleanRef2 = this.$removeNotificationWhenNotOngoing;
                this.label = 1;
                if (notificationStateChange.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.setupForegrounding.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((NotificationState) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(NotificationState notificationState, Continuation<? super Unit> continuation) {
                        if (notificationState instanceof NotificationState.POSTED) {
                            NotificationState.POSTED posted = (NotificationState.POSTED) notificationState;
                            Timber.INSTANCE.d("notification posted with id=%s, ongoing=%s", Boxing.boxInt(posted.getNotificationId()), Boxing.boxBoolean(posted.getOngoing()));
                            objectRef.element = (T) Boxing.boxInt(posted.getNotificationId());
                            objectRef2.element = (T) posted.getNotification();
                            QueuedAudioPlayer queuedAudioPlayer = null;
                            if (posted.getOngoing()) {
                                QueuedAudioPlayer queuedAudioPlayer2 = musicService.player;
                                if (queuedAudioPlayer2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("player");
                                } else {
                                    queuedAudioPlayer = queuedAudioPlayer2;
                                }
                                if (queuedAudioPlayer.getPlayWhenReady()) {
                                    MusicService.setupForegrounding$startForegroundIfNecessary(musicService, objectRef2, objectRef);
                                }
                            } else if (MusicService.setupForegrounding$shouldStopForeground(booleanRef, booleanRef2, musicService)) {
                                BuildersKt__Builders_commonKt.launch$default(musicService.scope, null, null, new C00111(musicService, booleanRef2, booleanRef, null), 3, null);
                            }
                        }
                        return Unit.INSTANCE;
                    }

                    /* compiled from: MusicService.kt */
                    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
                    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$setupForegrounding$2$1$1", f = "MusicService.kt", i = {}, l = {588}, m = "invokeSuspend", n = {}, s = {})
                    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$setupForegrounding$2$1$1, reason: invalid class name and collision with other inner class name */
                    static final class C00111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                        final /* synthetic */ Ref.BooleanRef $removeNotificationWhenNotOngoing;
                        final /* synthetic */ Ref.BooleanRef $stopForegroundWhenNotOngoing;
                        int label;
                        final /* synthetic */ MusicService this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        C00111(MusicService musicService, Ref.BooleanRef booleanRef, Ref.BooleanRef booleanRef2, Continuation<? super C00111> continuation) {
                            super(2, continuation);
                            this.this$0 = musicService;
                            this.$removeNotificationWhenNotOngoing = booleanRef;
                            this.$stopForegroundWhenNotOngoing = booleanRef2;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                            return new C00111(this.this$0, this.$removeNotificationWhenNotOngoing, this.$stopForegroundWhenNotOngoing, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                            return ((C00111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            int i = this.label;
                            if (i == 0) {
                                ResultKt.throwOnFailure(obj);
                                this.label = 1;
                                if (DelayKt.delay(this.this$0.stopForegroundGracePeriod * 1000, this) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                            } else {
                                if (i != 1) {
                                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                                }
                                ResultKt.throwOnFailure(obj);
                            }
                            if (MusicService.setupForegrounding$shouldStopForeground(this.$stopForegroundWhenNotOngoing, this.$removeNotificationWhenNotOngoing, this.this$0)) {
                                this.this$0.stopForeground(this.$removeNotificationWhenNotOngoing.element);
                                Timber.INSTANCE.d("Notification has been stopped", new Object[0]);
                            }
                            return Unit.INSTANCE;
                        }
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$1", f = "MusicService.kt", i = {}, l = {606}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<AudioPlayerState> stateChange = MusicService.this.getEvent().getStateChange();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (stateChange.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((AudioPlayerState) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(AudioPlayerState audioPlayerState, Continuation<? super Unit> continuation) {
                        MusicService musicService2 = musicService;
                        musicService2.emit(MusicEvents.PLAYBACK_STATE, musicService2.getPlayerStateBundle(audioPlayerState));
                        if (audioPlayerState == AudioPlayerState.ENDED) {
                            QueuedAudioPlayer queuedAudioPlayer = musicService.player;
                            if (queuedAudioPlayer == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("player");
                                queuedAudioPlayer = null;
                            }
                            if (queuedAudioPlayer.getNextItem() == null) {
                                musicService.emitQueueEndedEvent();
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    private final void observeEvents() {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass2(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass3(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass4(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass5(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass6(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass7(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass8(null), 3, null);
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$2", f = "MusicService.kt", i = {}, l = {616}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<AudioItemTransitionReason> audioItemTransition = MusicService.this.getEvent().getAudioItemTransition();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (audioItemTransition.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((AudioItemTransitionReason) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(AudioItemTransitionReason audioItemTransitionReason, Continuation<? super Unit> continuation) {
                        if (!(audioItemTransitionReason instanceof AudioItemTransitionReason.REPEAT)) {
                            MusicService musicService2 = musicService;
                            QueuedAudioPlayer queuedAudioPlayer = musicService2.player;
                            QueuedAudioPlayer queuedAudioPlayer2 = null;
                            if (queuedAudioPlayer == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("player");
                                queuedAudioPlayer = null;
                            }
                            Integer numBoxInt = Boxing.boxInt(queuedAudioPlayer.getCurrentIndex());
                            QueuedAudioPlayer queuedAudioPlayer3 = musicService.player;
                            if (queuedAudioPlayer3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("player");
                            } else {
                                queuedAudioPlayer2 = queuedAudioPlayer3;
                            }
                            musicService2.emitPlaybackTrackChangedEvents(numBoxInt, queuedAudioPlayer2.getPreviousIndex(), NumberExt.INSTANCE.toSeconds(Boxing.boxLong(audioItemTransitionReason != null ? audioItemTransitionReason.getOldPosition() : 0L)));
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$3", f = "MusicService.kt", i = {}, l = {628}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<FocusChangeData> onAudioFocusChanged = MusicService.this.getEvent().getOnAudioFocusChanged();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (onAudioFocusChanged.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((FocusChangeData) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(FocusChangeData focusChangeData, Continuation<? super Unit> continuation) {
                        Bundle bundle = new Bundle();
                        MusicService musicService2 = musicService;
                        bundle.putBoolean(MusicService.IS_FOCUS_LOSS_PERMANENT_KEY, focusChangeData.isFocusLostPermanently());
                        bundle.putBoolean(MusicService.IS_PAUSED_KEY, focusChangeData.isPaused());
                        musicService2.emit(MusicEvents.BUTTON_DUCK, bundle);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$4", f = "MusicService.kt", i = {}, l = {638}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<MediaSessionCallback> onPlayerActionTriggeredExternally = MusicService.this.getEvent().getOnPlayerActionTriggeredExternally();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (onPlayerActionTriggeredExternally.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.4.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((MediaSessionCallback) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(MediaSessionCallback mediaSessionCallback, Continuation<? super Unit> continuation) {
                        if (mediaSessionCallback instanceof MediaSessionCallback.RATING) {
                            Bundle bundle = new Bundle();
                            MusicService musicService2 = musicService;
                            BundleUtils.INSTANCE.setRating(bundle, "rating", ((MediaSessionCallback.RATING) mediaSessionCallback).getRating());
                            musicService2.emit(MusicEvents.BUTTON_SET_RATING, bundle);
                        } else if (mediaSessionCallback instanceof MediaSessionCallback.SEEK) {
                            Bundle bundle2 = new Bundle();
                            MusicService musicService3 = musicService;
                            bundle2.putDouble("position", NumberExt.INSTANCE.toSeconds(Boxing.boxLong(((MediaSessionCallback.SEEK) mediaSessionCallback).getPositionMs())));
                            musicService3.emit(MusicEvents.BUTTON_SEEK_TO, bundle2);
                        } else if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.PLAY.INSTANCE)) {
                            MusicService.emit$default(musicService, MusicEvents.BUTTON_PLAY, null, 2, null);
                        } else if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.PAUSE.INSTANCE)) {
                            MusicService.emit$default(musicService, MusicEvents.BUTTON_PAUSE, null, 2, null);
                        } else if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.NEXT.INSTANCE)) {
                            MusicService.emit$default(musicService, MusicEvents.BUTTON_SKIP_NEXT, null, 2, null);
                        } else if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.PREVIOUS.INSTANCE)) {
                            MusicService.emit$default(musicService, MusicEvents.BUTTON_SKIP_PREVIOUS, null, 2, null);
                        } else if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.STOP.INSTANCE)) {
                            MusicService.emit$default(musicService, MusicEvents.BUTTON_STOP, null, 2, null);
                        } else {
                            if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.FORWARD.INSTANCE)) {
                                Bundle bundle3 = new Bundle();
                                MusicService musicService4 = musicService;
                                Bundle bundle4 = musicService4.latestOptions;
                                bundle3.putInt(bm.aY, (int) (bundle4 != null ? bundle4.getDouble(MusicService.FORWARD_JUMP_INTERVAL_KEY, 15.0d) : 15.0d));
                                musicService4.emit(MusicEvents.BUTTON_JUMP_FORWARD, bundle3);
                            } else if (Intrinsics.areEqual(mediaSessionCallback, MediaSessionCallback.REWIND.INSTANCE)) {
                                Bundle bundle5 = new Bundle();
                                MusicService musicService5 = musicService;
                                Bundle bundle6 = musicService5.latestOptions;
                                bundle5.putInt(bm.aY, (int) (bundle6 != null ? bundle6.getDouble(MusicService.BACKWARD_JUMP_INTERVAL_KEY, 15.0d) : 15.0d));
                                musicService5.emit(MusicEvents.BUTTON_JUMP_BACKWARD, bundle5);
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$5", f = "MusicService.kt", i = {}, l = {676}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$5, reason: invalid class name */
    static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass5(Continuation<? super AnonymousClass5> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass5(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<com.google.android.exoplayer2.metadata.Metadata> onTimedMetadata = MusicService.this.getEvent().getOnTimedMetadata();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (onTimedMetadata.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.5.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((com.google.android.exoplayer2.metadata.Metadata) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(com.google.android.exoplayer2.metadata.Metadata metadata, Continuation<? super Unit> continuation) {
                        List<Bundle> listFromMetadata = MetadataAdapter.INSTANCE.fromMetadata(metadata);
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("metadata", new ArrayList<>(listFromMetadata));
                        musicService.emit(MusicEvents.METADATA_TIMED_RECEIVED, bundle);
                        PlaybackMetadata playbackMetadataFromId3Metadata = PlaybackMetadata.INSTANCE.fromId3Metadata(metadata);
                        if (playbackMetadataFromId3Metadata == null && (playbackMetadataFromId3Metadata = PlaybackMetadata.INSTANCE.fromIcy(metadata)) == null && (playbackMetadataFromId3Metadata = PlaybackMetadata.INSTANCE.fromVorbisComment(metadata)) == null) {
                            playbackMetadataFromId3Metadata = PlaybackMetadata.INSTANCE.fromQuickTime(metadata);
                        }
                        if (playbackMetadataFromId3Metadata != null) {
                            Bundle bundle2 = new Bundle();
                            MusicService musicService2 = musicService;
                            bundle2.putString("source", playbackMetadataFromId3Metadata.getSource());
                            bundle2.putString("title", playbackMetadataFromId3Metadata.getTitle());
                            bundle2.putString("url", playbackMetadataFromId3Metadata.getUrl());
                            bundle2.putString("artist", playbackMetadataFromId3Metadata.getArtist());
                            bundle2.putString("album", playbackMetadataFromId3Metadata.getAlbum());
                            bundle2.putString("date", playbackMetadataFromId3Metadata.getDate());
                            bundle2.putString("genre", playbackMetadataFromId3Metadata.getGenre());
                            musicService2.emit(MusicEvents.PLAYBACK_METADATA, bundle2);
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$6", f = "MusicService.kt", i = {}, l = {705}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$6, reason: invalid class name */
    static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass6(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<MediaMetadata> onCommonMetadata = MusicService.this.getEvent().getOnCommonMetadata();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (onCommonMetadata.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.6.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((MediaMetadata) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(MediaMetadata mediaMetadata, Continuation<? super Unit> continuation) {
                        Bundle bundleFromMediaMetadata = MetadataAdapter.INSTANCE.fromMediaMetadata(mediaMetadata);
                        Bundle bundle = new Bundle();
                        bundle.putBundle("metadata", bundleFromMediaMetadata);
                        musicService.emit(MusicEvents.METADATA_COMMON_RECEIVED, bundle);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$7", f = "MusicService.kt", i = {}, l = {715}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$7, reason: invalid class name */
    static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass7(Continuation<? super AnonymousClass7> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass7(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass7) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<PlayWhenReadyChangeData> playWhenReadyChange = MusicService.this.getEvent().getPlayWhenReadyChange();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (playWhenReadyChange.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.7.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((PlayWhenReadyChangeData) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(PlayWhenReadyChangeData playWhenReadyChangeData, Continuation<? super Unit> continuation) {
                        Bundle bundle = new Bundle();
                        MusicService musicService2 = musicService;
                        bundle.putBoolean("playWhenReady", playWhenReadyChangeData.getPlayWhenReady());
                        musicService2.emit(MusicEvents.PLAYBACK_PLAY_WHEN_READY_CHANGED, bundle);
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.service.MusicService$observeEvents$8", f = "MusicService.kt", i = {}, l = {724}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.service.MusicService$observeEvents$8, reason: invalid class name */
    static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass8(Continuation<? super AnonymousClass8> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicService.this.new AnonymousClass8(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass8) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                SharedFlow<PlaybackError> playbackError = MusicService.this.getEvent().getPlaybackError();
                final MusicService musicService = MusicService.this;
                this.label = 1;
                if (playbackError.collect(new FlowCollector() { // from class: com.doublesymmetry.trackplayer.service.MusicService.observeEvents.8.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((PlaybackError) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(PlaybackError playbackError2, Continuation<? super Unit> continuation) {
                        MusicService musicService2 = musicService;
                        musicService2.emit(MusicEvents.PLAYBACK_ERROR, musicService2.getPlaybackErrorBundle());
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bundle getPlaybackErrorBundle() {
        Bundle bundle = new Bundle();
        PlaybackError playbackError = getPlaybackError();
        if ((playbackError != null ? playbackError.getMessage() : null) != null) {
            bundle.putString(StackTraceHelper.MESSAGE_KEY, playbackError.getMessage());
        }
        if ((playbackError != null ? playbackError.getCode() : null) != null) {
            bundle.putString("code", "android-" + playbackError.getCode());
        }
        return bundle;
    }

    static /* synthetic */ void emit$default(MusicService musicService, String str, Bundle bundle, int i, Object obj) {
        if ((i & 2) != 0) {
            bundle = null;
        }
        musicService.emit(str, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void emit(String event, Bundle data) {
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        ReactContext currentReactContext = getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        if (currentReactContext == null || (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) == null) {
            return;
        }
        rCTDeviceEventEmitter.emit(event, data != null ? Arguments.fromBundle(data) : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void emitList$default(MusicService musicService, String str, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = CollectionsKt.emptyList();
        }
        musicService.emitList(str, list);
    }

    private final void emitList(String event, List<Bundle> data) {
        DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter;
        WritableArray writableArrayCreateArray = Arguments.createArray();
        Iterator<T> it = data.iterator();
        while (it.hasNext()) {
            writableArrayCreateArray.pushMap(Arguments.fromBundle((Bundle) it.next()));
        }
        ReactContext currentReactContext = getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
        if (currentReactContext == null || (rCTDeviceEventEmitter = (DeviceEventManagerModule.RCTDeviceEventEmitter) currentReactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)) == null) {
            return;
        }
        rCTDeviceEventEmitter.emit(event, writableArrayCreateArray);
    }

    @Override // com.facebook.react.HeadlessJsTaskService
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        return new HeadlessJsTaskConfig(TASK_KEY, Arguments.createMap(), 0L, true);
    }

    @Override // com.facebook.react.HeadlessJsTaskService, android.app.Service
    public IBinder onBind(Intent intent) {
        return this.binder;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (this.player == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[this.appKilledPlaybackBehavior.ordinal()];
        QueuedAudioPlayer queuedAudioPlayer = null;
        if (i == 1) {
            QueuedAudioPlayer queuedAudioPlayer2 = this.player;
            if (queuedAudioPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
            } else {
                queuedAudioPlayer = queuedAudioPlayer2;
            }
            queuedAudioPlayer.pause();
            return;
        }
        if (i != 2) {
            return;
        }
        QueuedAudioPlayer queuedAudioPlayer3 = this.player;
        if (queuedAudioPlayer3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
            queuedAudioPlayer3 = null;
        }
        queuedAudioPlayer3.clear();
        QueuedAudioPlayer queuedAudioPlayer4 = this.player;
        if (queuedAudioPlayer4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("player");
        } else {
            queuedAudioPlayer = queuedAudioPlayer4;
        }
        queuedAudioPlayer.stop();
        stopForeground(1);
        stopSelf();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    @Override // com.facebook.react.HeadlessJsTaskService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        QueuedAudioPlayer queuedAudioPlayer = this.player;
        if (queuedAudioPlayer != null) {
            if (queuedAudioPlayer == null) {
                Intrinsics.throwUninitializedPropertyAccessException("player");
                queuedAudioPlayer = null;
            }
            queuedAudioPlayer.destroy();
        }
        Job job = this.progressUpdateJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    /* compiled from: MusicService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0087\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/doublesymmetry/trackplayer/service/MusicService$MusicBinder;", "Landroid/os/Binder;", "(Lcom/doublesymmetry/trackplayer/service/MusicService;)V", NotificationCompat.CATEGORY_SERVICE, "Lcom/doublesymmetry/trackplayer/service/MusicService;", "getService", "()Lcom/doublesymmetry/trackplayer/service/MusicService;", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public final class MusicBinder extends Binder {
        private final MusicService service;

        public MusicBinder() {
            this.service = MusicService.this;
        }

        public final MusicService getService() {
            return this.service;
        }
    }
}
