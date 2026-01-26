package com.doublesymmetry.kotlinaudio.players;

import android.content.Context;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.content.ContextCompat;
import androidx.media.AudioAttributesCompat;
import androidx.media.AudioFocusRequestCompat;
import androidx.media.AudioManagerCompat;
import com.doublesymmetry.kotlinaudio.event.EventHolder;
import com.doublesymmetry.kotlinaudio.event.NotificationEventHolder;
import com.doublesymmetry.kotlinaudio.event.PlayerEventHolder;
import com.doublesymmetry.kotlinaudio.models.AudioContentType;
import com.doublesymmetry.kotlinaudio.models.AudioItem;
import com.doublesymmetry.kotlinaudio.models.AudioItemHolder;
import com.doublesymmetry.kotlinaudio.models.AudioItemTransitionReason;
import com.doublesymmetry.kotlinaudio.models.AudioPlayerState;
import com.doublesymmetry.kotlinaudio.models.BufferConfig;
import com.doublesymmetry.kotlinaudio.models.CacheConfig;
import com.doublesymmetry.kotlinaudio.models.DefaultPlayerOptions;
import com.doublesymmetry.kotlinaudio.models.MediaSessionCallback;
import com.doublesymmetry.kotlinaudio.models.MediaType;
import com.doublesymmetry.kotlinaudio.models.PlayWhenReadyChangeData;
import com.doublesymmetry.kotlinaudio.models.PlaybackError;
import com.doublesymmetry.kotlinaudio.models.PlayerConfig;
import com.doublesymmetry.kotlinaudio.models.PlayerOptions;
import com.doublesymmetry.kotlinaudio.models.PositionChangedReason;
import com.doublesymmetry.kotlinaudio.models.WakeMode;
import com.doublesymmetry.kotlinaudio.notification.NotificationManager;
import com.doublesymmetry.kotlinaudio.players.components.MediaItemExtKt;
import com.doublesymmetry.kotlinaudio.players.components.PlayerCache;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ForwardingPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSourceException;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.upstream.cache.CacheDataSource;
import com.google.android.exoplayer2.upstream.cache.SimpleCache;
import com.umeng.analytics.pro.f;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import timber.log.Timber;

/* compiled from: BaseAudioPlayer.kt */
@Metadata(d1 = {"\u0000æ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 \u008e\u00012\u00020\u0001:\u0004\u008e\u0001\u008f\u0001B+\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\b\u0010f\u001a\u00020gH\u0002J\b\u0010h\u001a\u00020gH\u0017J\u001a\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\b\u0010m\u001a\u0004\u0018\u00010nH\u0002J\b\u0010o\u001a\u00020pH\u0002J\u001a\u0010q\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\b\u0010m\u001a\u0004\u0018\u00010nH\u0002J\u0018\u0010r\u001a\u00020s2\u0006\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020nH\u0002J\u001a\u0010t\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\b\u0010m\u001a\u0004\u0018\u00010nH\u0002J\b\u0010u\u001a\u00020gH\u0017J\u0010\u0010v\u001a\u00020n2\u0006\u0010m\u001a\u00020nH\u0002J\u0010\u0010w\u001a\u00020j2\u0006\u0010x\u001a\u00020\u001aH\u0004J\u0010\u0010y\u001a\u00020g2\u0006\u0010z\u001a\u00020\u001aH\u0016J\u001a\u0010y\u001a\u00020g2\u0006\u0010z\u001a\u00020\u001a2\b\b\u0002\u00108\u001a\u00020\fH\u0016J\u0010\u0010{\u001a\u00020g2\u0006\u0010|\u001a\u00020UH\u0016J\u0006\u0010}\u001a\u00020gJ\u0006\u0010~\u001a\u00020gJ\u0006\u0010\u007f\u001a\u00020gJ\t\u0010\u0080\u0001\u001a\u00020gH\u0002J\u001b\u0010\u0081\u0001\u001a\u00020g2\u0006\u0010\u001d\u001a\u00020\u00122\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0016J\u001c\u0010\u0084\u0001\u001a\u00020g2\u0007\u0010\u0085\u0001\u001a\u00020\u00122\b\u0010\u0082\u0001\u001a\u00030\u0083\u0001H\u0016J\u000f\u0010\u0086\u0001\u001a\u00020g2\u0006\u0010}\u001a\u00020\fJ\u0012\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\t\u0010\u0089\u0001\u001a\u00020gH\u0017J\u0007\u0010\u008a\u0001\u001a\u00020gJ\u001c\u0010\u008b\u0001\u001a\u00020g2\u000b\b\u0002\u0010\u008c\u0001\u001a\u0004\u0018\u00010\u001aH\u0000¢\u0006\u0003\b\u008d\u0001R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u001f\u001a\u00020 ¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b*\u0010\u000eR\u0011\u0010+\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b+\u0010\u000eR\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u00102\u001a\u000203¢\u0006\b\n\u0000\u001a\u0004\b4\u00105R\u000e\u00106\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00108\u001a\u00020\f2\u0006\u00107\u001a\u00020\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b9\u0010\u000e\"\u0004\b:\u0010\u0010R\u001c\u0010;\u001a\u0004\u0018\u00010<X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R$\u0010B\u001a\u00020A2\u0006\u00107\u001a\u00020A8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010I\u001a\u00020JX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR$\u0010N\u001a\u00020M2\u0006\u00107\u001a\u00020M@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0011\u0010S\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\bT\u0010\u0014R$\u0010V\u001a\u00020U2\u0006\u00107\u001a\u00020U@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u000e\u0010[\u001a\u00020\\X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010]\u001a\u00020\f2\u0006\u00107\u001a\u00020\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b^\u0010\u000e\"\u0004\b_\u0010\u0010R$\u0010`\u001a\u00020A2\u0006\u00107\u001a\u00020A8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\ba\u0010D\"\u0004\bb\u0010FR\u001e\u0010c\u001a\u00020A2\u0006\u00107\u001a\u00020A@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\bd\u0010FR\u000e\u0010e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0090\u0001"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/players/BaseAudioPlayer;", "Landroid/media/AudioManager$OnAudioFocusChangeListener;", f.X, "Landroid/content/Context;", "playerConfig", "Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;", "bufferConfig", "Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;", "cacheConfig", "Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;", "(Landroid/content/Context;Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;)V", "automaticallyUpdateNotificationMetadata", "", "getAutomaticallyUpdateNotificationMetadata", "()Z", "setAutomaticallyUpdateNotificationMetadata", "(Z)V", "bufferedPosition", "", "getBufferedPosition", "()J", "cache", "Lcom/google/android/exoplayer2/upstream/cache/SimpleCache;", "getContext$kotlin_audio_release", "()Landroid/content/Context;", "currentItem", "Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "getCurrentItem", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "duration", "getDuration", "event", "Lcom/doublesymmetry/kotlinaudio/event/EventHolder;", "getEvent", "()Lcom/doublesymmetry/kotlinaudio/event/EventHolder;", "exoPlayer", "Lcom/google/android/exoplayer2/ExoPlayer;", "getExoPlayer", "()Lcom/google/android/exoplayer2/ExoPlayer;", "focus", "Landroidx/media/AudioFocusRequestCompat;", "hasAudioFocus", "isCurrentMediaItemLive", "isPlaying", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSessionConnector", "Lcom/google/android/exoplayer2/ext/mediasession/MediaSessionConnector;", "notificationEventHolder", "Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;", "notificationManager", "Lcom/doublesymmetry/kotlinaudio/notification/NotificationManager;", "getNotificationManager", "()Lcom/doublesymmetry/kotlinaudio/notification/NotificationManager;", "oldPosition", "value", "playWhenReady", "getPlayWhenReady", "setPlayWhenReady", "playbackError", "Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;", "getPlaybackError", "()Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;", "setPlaybackError", "(Lcom/doublesymmetry/kotlinaudio/models/PlaybackError;)V", "", "playbackSpeed", "getPlaybackSpeed", "()F", "setPlaybackSpeed", "(F)V", "playerEventHolder", "Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;", "playerOptions", "Lcom/doublesymmetry/kotlinaudio/models/PlayerOptions;", "getPlayerOptions", "()Lcom/doublesymmetry/kotlinaudio/models/PlayerOptions;", "Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", "playerState", "getPlayerState", "()Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;", "setPlayerState", "(Lcom/doublesymmetry/kotlinaudio/models/AudioPlayerState;)V", "position", "getPosition", "", "ratingType", "getRatingType", "()I", "setRatingType", "(I)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "skipSilence", "getSkipSilence", "setSkipSilence", "volume", "getVolume", "setVolume", "volumeMultiplier", "setVolumeMultiplier", "wasDucking", "abandonAudioFocusIfHeld", "", "clear", "createDashSource", "Lcom/google/android/exoplayer2/source/MediaSource;", "mediaItem", "Lcom/google/android/exoplayer2/MediaItem;", "factory", "Lcom/google/android/exoplayer2/upstream/DataSource$Factory;", "createForwardingPlayer", "Lcom/google/android/exoplayer2/ForwardingPlayer;", "createHlsSource", "createProgressiveSource", "Lcom/google/android/exoplayer2/source/ProgressiveMediaSource;", "createSsSource", "destroy", "enableCaching", "getMediaSourceFromAudioItem", "audioItem", "load", "item", "onAudioFocusChange", "focusChange", "pause", "play", "prepare", "requestAudioFocus", "seek", "unit", "Ljava/util/concurrent/TimeUnit;", "seekBy", "offset", "setPauseAtEndOfItem", "setupBuffer", "Lcom/google/android/exoplayer2/DefaultLoadControl;", "stop", "togglePlaying", "updateNotificationIfNecessary", "overrideAudioItem", "updateNotificationIfNecessary$kotlin_audio_release", "Companion", "PlayerListener", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public abstract class BaseAudioPlayer implements AudioManager.OnAudioFocusChangeListener {
    public static final String APPLICATION_NAME = "react-native-track-player";
    private boolean automaticallyUpdateNotificationMetadata;
    private final BufferConfig bufferConfig;
    private SimpleCache cache;
    private final CacheConfig cacheConfig;
    private final Context context;
    private final EventHolder event;
    private final ExoPlayer exoPlayer;
    private AudioFocusRequestCompat focus;
    private boolean hasAudioFocus;
    private final MediaSessionCompat mediaSession;
    private final MediaSessionConnector mediaSessionConnector;
    private final NotificationEventHolder notificationEventHolder;
    private final NotificationManager notificationManager;
    private long oldPosition;
    private PlaybackError playbackError;
    private PlayerConfig playerConfig;
    private final PlayerEventHolder playerEventHolder;
    private final PlayerOptions playerOptions;
    private AudioPlayerState playerState;
    private int ratingType;
    private final CoroutineScope scope;
    private float volumeMultiplier;
    private boolean wasDucking;

    /* compiled from: BaseAudioPlayer.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;

        static {
            int[] iArr = new int[AudioPlayerState.values().length];
            iArr[AudioPlayerState.IDLE.ordinal()] = 1;
            iArr[AudioPlayerState.ERROR.ordinal()] = 2;
            iArr[AudioPlayerState.READY.ordinal()] = 3;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[WakeMode.values().length];
            iArr2[WakeMode.NONE.ordinal()] = 1;
            iArr2[WakeMode.LOCAL.ordinal()] = 2;
            iArr2[WakeMode.NETWORK.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[MediaType.values().length];
            iArr3[MediaType.DASH.ordinal()] = 1;
            iArr3[MediaType.HLS.ordinal()] = 2;
            iArr3[MediaType.SMOOTH_STREAMING.ordinal()] = 3;
            $EnumSwitchMapping$2 = iArr3;
        }
    }

    public BaseAudioPlayer(Context context, PlayerConfig playerConfig, BufferConfig bufferConfig, CacheConfig cacheConfig) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playerConfig, "playerConfig");
        this.context = context;
        this.bufferConfig = bufferConfig;
        this.cacheConfig = cacheConfig;
        CoroutineScope coroutineScopeMainScope = CoroutineScopeKt.MainScope();
        this.scope = coroutineScopeMainScope;
        this.playerConfig = playerConfig;
        int i = 0;
        this.playerOptions = new DefaultPlayerOptions(false, 1, null);
        this.playerState = AudioPlayerState.IDLE;
        this.automaticallyUpdateNotificationMetadata = true;
        this.volumeMultiplier = 1.0f;
        NotificationEventHolder notificationEventHolder = new NotificationEventHolder();
        this.notificationEventHolder = notificationEventHolder;
        PlayerEventHolder playerEventHolder = new PlayerEventHolder();
        this.playerEventHolder = playerEventHolder;
        this.event = new EventHolder(notificationEventHolder, playerEventHolder);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "KotlinAudioPlayer");
        this.mediaSession = mediaSessionCompat;
        MediaSessionConnector mediaSessionConnector = new MediaSessionConnector(mediaSessionCompat);
        this.mediaSessionConnector = mediaSessionConnector;
        if (cacheConfig != null) {
            this.cache = PlayerCache.INSTANCE.getInstance(context, cacheConfig);
        }
        ExoPlayer.Builder handleAudioBecomingNoisy = new ExoPlayer.Builder(context).setHandleAudioBecomingNoisy(playerConfig.getHandleAudioBecomingNoisy());
        int i2 = WhenMappings.$EnumSwitchMapping$1[playerConfig.getWakeMode().ordinal()];
        if (i2 != 1) {
            i = 2;
            if (i2 == 2) {
                i = 1;
            } else if (i2 != 3) {
                throw new NoWhenBranchMatchedException();
            }
        }
        ExoPlayer.Builder wakeMode = handleAudioBecomingNoisy.setWakeMode(i);
        if (bufferConfig != null) {
            wakeMode.setLoadControl(setupBuffer(bufferConfig));
        }
        ExoPlayer exoPlayerBuild = wakeMode.build();
        Intrinsics.checkNotNullExpressionValue(exoPlayerBuild, "Builder(context)\n       …   }\n            .build()");
        this.exoPlayer = exoPlayerBuild;
        mediaSessionCompat.setActive(true);
        Player playerCreateForwardingPlayer = playerConfig.getInterceptPlayerActionsTriggeredExternally() ? createForwardingPlayer() : exoPlayerBuild;
        this.notificationManager = new NotificationManager(context, playerCreateForwardingPlayer, mediaSessionCompat, mediaSessionConnector, notificationEventHolder, playerEventHolder);
        exoPlayerBuild.addListener(new PlayerListener());
        BuildersKt__Builders_commonKt.launch$default(coroutineScopeMainScope, null, null, new AnonymousClass2(playerConfig, this, playerCreateForwardingPlayer, null), 3, null);
        playerEventHolder.updateAudioPlayerState$kotlin_audio_release(AudioPlayerState.IDLE);
    }

    /* renamed from: getContext$kotlin_audio_release, reason: from getter */
    public final Context getContext() {
        return this.context;
    }

    protected final ExoPlayer getExoPlayer() {
        return this.exoPlayer;
    }

    public final NotificationManager getNotificationManager() {
        return this.notificationManager;
    }

    public PlayerOptions getPlayerOptions() {
        return this.playerOptions;
    }

    public AudioItem getCurrentItem() {
        AudioItemHolder audioItemHolder;
        MediaItem currentMediaItem = this.exoPlayer.getCurrentMediaItem();
        if (currentMediaItem == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(currentMediaItem)) == null) {
            return null;
        }
        return audioItemHolder.getAudioItem();
    }

    public final PlaybackError getPlaybackError() {
        return this.playbackError;
    }

    public final void setPlaybackError(PlaybackError playbackError) {
        this.playbackError = playbackError;
    }

    public final AudioPlayerState getPlayerState() {
        return this.playerState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPlayerState(AudioPlayerState audioPlayerState) {
        if (audioPlayerState != this.playerState) {
            this.playerState = audioPlayerState;
            this.playerEventHolder.updateAudioPlayerState$kotlin_audio_release(audioPlayerState);
            if (this.playerConfig.getHandleAudioFocus()) {
                return;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[audioPlayerState.ordinal()];
            if (i == 1 || i == 2) {
                abandonAudioFocusIfHeld();
            } else {
                if (i != 3) {
                    return;
                }
                requestAudioFocus();
            }
        }
    }

    public final boolean getPlayWhenReady() {
        return this.exoPlayer.getPlayWhenReady();
    }

    public final void setPlayWhenReady(boolean z) {
        this.exoPlayer.setPlayWhenReady(z);
    }

    public final long getDuration() {
        if (this.exoPlayer.getDuration() == -9223372036854775807L) {
            return 0L;
        }
        return this.exoPlayer.getDuration();
    }

    public final boolean isCurrentMediaItemLive() {
        return this.exoPlayer.isCurrentMediaItemLive();
    }

    public final long getPosition() {
        if (this.exoPlayer.getCurrentPosition() == -1) {
            return 0L;
        }
        return this.exoPlayer.getCurrentPosition();
    }

    public final long getBufferedPosition() {
        if (this.exoPlayer.getBufferedPosition() == -1) {
            return 0L;
        }
        return this.exoPlayer.getBufferedPosition();
    }

    public final float getVolume() {
        return this.exoPlayer.getVolume();
    }

    public final void setVolume(float f) {
        this.exoPlayer.setVolume(f * this.volumeMultiplier);
    }

    public final float getPlaybackSpeed() {
        return this.exoPlayer.getPlaybackParameters().speed;
    }

    public final void setPlaybackSpeed(float f) {
        this.exoPlayer.setPlaybackSpeed(f);
    }

    public final boolean getAutomaticallyUpdateNotificationMetadata() {
        return this.automaticallyUpdateNotificationMetadata;
    }

    public final void setAutomaticallyUpdateNotificationMetadata(boolean z) {
        this.automaticallyUpdateNotificationMetadata = z;
    }

    private final void setVolumeMultiplier(float f) {
        this.volumeMultiplier = f;
        setVolume(getVolume());
    }

    public final boolean isPlaying() {
        return this.exoPlayer.isPlaying();
    }

    public final int getRatingType() {
        return this.ratingType;
    }

    public final void setRatingType(int i) {
        this.ratingType = i;
        this.mediaSession.setRatingType(i);
        this.mediaSessionConnector.setRatingCallback(new MediaSessionConnector.RatingCallback() { // from class: com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$ratingType$1
            @Override // com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.CommandReceiver
            public boolean onCommand(Player player, String command, Bundle extras, ResultReceiver cb) {
                Intrinsics.checkNotNullParameter(player, "player");
                Intrinsics.checkNotNullParameter(command, "command");
                return true;
            }

            @Override // com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.RatingCallback
            public void onSetRating(Player player, RatingCompat rating) {
                Intrinsics.checkNotNullParameter(player, "player");
                Intrinsics.checkNotNullParameter(rating, "rating");
                this.this$0.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(new MediaSessionCallback.RATING(rating, null));
            }

            @Override // com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.RatingCallback
            public void onSetRating(Player player, RatingCompat rating, Bundle extras) {
                Intrinsics.checkNotNullParameter(player, "player");
                Intrinsics.checkNotNullParameter(rating, "rating");
                this.this$0.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(new MediaSessionCallback.RATING(rating, extras));
            }
        });
    }

    public final EventHolder getEvent() {
        return this.event;
    }

    /* compiled from: BaseAudioPlayer.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$2", f = "BaseAudioPlayer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PlayerConfig $playerConfig;
        final /* synthetic */ Player $playerToUse;
        int label;
        final /* synthetic */ BaseAudioPlayer this$0;

        /* compiled from: BaseAudioPlayer.kt */
        @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
        /* renamed from: com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$2$WhenMappings */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[AudioContentType.values().length];
                iArr[AudioContentType.MUSIC.ordinal()] = 1;
                iArr[AudioContentType.SPEECH.ordinal()] = 2;
                iArr[AudioContentType.SONIFICATION.ordinal()] = 3;
                iArr[AudioContentType.MOVIE.ordinal()] = 4;
                iArr[AudioContentType.UNKNOWN.ordinal()] = 5;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PlayerConfig playerConfig, BaseAudioPlayer baseAudioPlayer, Player player, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$playerConfig = playerConfig;
            this.this$0 = baseAudioPlayer;
            this.$playerToUse = player;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$playerConfig, this.this$0, this.$playerToUse, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0038 A[PHI: r2
  0x0038: PHI (r2v2 int) = (r2v1 int), (r2v3 int) binds: [B:5:0x0023, B:9:0x002b] A[DONT_GENERATE, DONT_INLINE]] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r4) {
            /*
                r3 = this;
                kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r3.label
                if (r0 != 0) goto L73
                kotlin.ResultKt.throwOnFailure(r4)
                com.google.android.exoplayer2.audio.AudioAttributes$Builder r4 = new com.google.android.exoplayer2.audio.AudioAttributes$Builder
                r4.<init>()
                r0 = 1
                com.google.android.exoplayer2.audio.AudioAttributes$Builder r4 = r4.setUsage(r0)
                com.doublesymmetry.kotlinaudio.models.PlayerConfig r1 = r3.$playerConfig
                com.doublesymmetry.kotlinaudio.models.AudioContentType r1 = r1.getAudioContentType()
                int[] r2 = com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.AnonymousClass2.WhenMappings.$EnumSwitchMapping$0
                int r1 = r1.ordinal()
                r1 = r2[r1]
                r2 = 2
                if (r1 == r0) goto L38
                if (r1 == r2) goto L39
                r0 = 4
                r2 = 3
                if (r1 == r2) goto L39
                if (r1 == r0) goto L38
                r0 = 5
                if (r1 != r0) goto L32
                r0 = 0
                goto L39
            L32:
                kotlin.NoWhenBranchMatchedException r4 = new kotlin.NoWhenBranchMatchedException
                r4.<init>()
                throw r4
            L38:
                r0 = r2
            L39:
                com.google.android.exoplayer2.audio.AudioAttributes$Builder r4 = r4.setContentType(r0)
                com.google.android.exoplayer2.audio.AudioAttributes r4 = r4.build()
                java.lang.String r0 = "Builder()\n              …\n                .build()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
                com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer r0 = r3.this$0
                com.google.android.exoplayer2.ExoPlayer r0 = r0.getExoPlayer()
                com.doublesymmetry.kotlinaudio.models.PlayerConfig r1 = r3.$playerConfig
                boolean r1 = r1.getHandleAudioFocus()
                r0.setAudioAttributes(r4, r1)
                com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer r4 = r3.this$0
                com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector r4 = com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.access$getMediaSessionConnector$p(r4)
                com.google.android.exoplayer2.Player r0 = r3.$playerToUse
                r4.setPlayer(r0)
                com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer r4 = r3.this$0
                com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector r4 = com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.access$getMediaSessionConnector$p(r4)
                com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer r0 = r3.this$0
                com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$2$$ExternalSyntheticLambda0 r1 = new com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$2$$ExternalSyntheticLambda0
                r1.<init>()
                r4.setMediaMetadataProvider(r1)
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            L73:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invokeSuspend$lambda-0, reason: not valid java name */
        public static final MediaMetadataCompat m286invokeSuspend$lambda0(BaseAudioPlayer baseAudioPlayer, Player player) {
            return baseAudioPlayer.getNotificationManager().getMediaMetadataCompat();
        }
    }

    private final ForwardingPlayer createForwardingPlayer() {
        return new ForwardingPlayer(this.exoPlayer) { // from class: com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.createForwardingPlayer.1
            {
                super(exoPlayer);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void play() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.PLAY.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void pause() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.PAUSE.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void seekToNext() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.NEXT.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void seekToPrevious() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.PREVIOUS.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void seekForward() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.FORWARD.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void seekBack() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.REWIND.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void stop() {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.STOP.INSTANCE);
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void seekTo(int mediaItemIndex, long positionMs) {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(new MediaSessionCallback.SEEK(positionMs));
            }

            @Override // com.google.android.exoplayer2.ForwardingPlayer, com.google.android.exoplayer2.Player
            public void seekTo(long positionMs) {
                BaseAudioPlayer.this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(new MediaSessionCallback.SEEK(positionMs));
            }
        };
    }

    public static /* synthetic */ void updateNotificationIfNecessary$kotlin_audio_release$default(BaseAudioPlayer baseAudioPlayer, AudioItem audioItem, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: updateNotificationIfNecessary");
        }
        if ((i & 1) != 0) {
            audioItem = null;
        }
        baseAudioPlayer.updateNotificationIfNecessary$kotlin_audio_release(audioItem);
    }

    public final void updateNotificationIfNecessary$kotlin_audio_release(AudioItem overrideAudioItem) {
        if (this.automaticallyUpdateNotificationMetadata) {
            this.notificationManager.setOverrideAudioItem$kotlin_audio_release(overrideAudioItem);
        }
    }

    private final DefaultLoadControl setupBuffer(BufferConfig bufferConfig) {
        Integer backBuffer;
        Integer playBuffer;
        Integer maxBuffer;
        Integer minBuffer;
        int iIntValue = 50000;
        int iIntValue2 = (bufferConfig.getMinBuffer() == null || ((minBuffer = bufferConfig.getMinBuffer()) != null && minBuffer.intValue() == 0)) ? 50000 : bufferConfig.getMinBuffer().intValue();
        if (bufferConfig.getMaxBuffer() != null && ((maxBuffer = bufferConfig.getMaxBuffer()) == null || maxBuffer.intValue() != 0)) {
            iIntValue = bufferConfig.getMaxBuffer().intValue();
        }
        int iIntValue3 = (bufferConfig.getPlayBuffer() == null || ((playBuffer = bufferConfig.getPlayBuffer()) != null && playBuffer.intValue() == 0)) ? 2500 : bufferConfig.getPlayBuffer().intValue();
        DefaultLoadControl defaultLoadControlBuild = new DefaultLoadControl.Builder().setBufferDurationsMs(iIntValue2, iIntValue, iIntValue3, iIntValue3 * 2).setBackBuffer((bufferConfig.getBackBuffer() == null || ((backBuffer = bufferConfig.getBackBuffer()) != null && backBuffer.intValue() == 0)) ? 0 : bufferConfig.getBackBuffer().intValue(), false).build();
        Intrinsics.checkNotNullExpressionValue(defaultLoadControlBuild, "Builder()\n              …\n                .build()");
        return defaultLoadControlBuild;
    }

    public static /* synthetic */ void load$default(BaseAudioPlayer baseAudioPlayer, AudioItem audioItem, boolean z, int i, Object obj) throws DataSourceException, Resources.NotFoundException, NumberFormatException {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: load");
        }
        if ((i & 2) != 0) {
            z = true;
        }
        baseAudioPlayer.load(audioItem, z);
    }

    public void load(AudioItem item, boolean playWhenReady) throws DataSourceException, Resources.NotFoundException, NumberFormatException {
        Intrinsics.checkNotNullParameter(item, "item");
        this.exoPlayer.setPlayWhenReady(playWhenReady);
        load(item);
    }

    public void load(AudioItem item) throws DataSourceException, Resources.NotFoundException, NumberFormatException {
        Intrinsics.checkNotNullParameter(item, "item");
        this.exoPlayer.addMediaSource(getMediaSourceFromAudioItem(item));
        this.exoPlayer.prepare();
    }

    public final void togglePlaying() {
        if (this.exoPlayer.isPlaying()) {
            pause();
        } else {
            play();
        }
    }

    public final boolean getSkipSilence() {
        return this.exoPlayer.getSkipSilenceEnabled();
    }

    public final void setSkipSilence(boolean z) {
        this.exoPlayer.setSkipSilenceEnabled(z);
    }

    public final void play() {
        this.exoPlayer.play();
        if (getCurrentItem() != null) {
            this.exoPlayer.prepare();
        }
    }

    public final void prepare() {
        if (getCurrentItem() != null) {
            this.exoPlayer.prepare();
        }
    }

    public final void pause() {
        this.exoPlayer.pause();
    }

    public void stop() {
        setPlayerState(AudioPlayerState.STOPPED);
        this.exoPlayer.setPlayWhenReady(false);
        this.exoPlayer.stop();
    }

    public void clear() {
        this.exoPlayer.clearMediaItems();
    }

    public final void setPauseAtEndOfItem(boolean pause) {
        this.exoPlayer.setPauseAtEndOfMediaItems(pause);
    }

    public void destroy() {
        abandonAudioFocusIfHeld();
        stop();
        this.notificationManager.destroy$kotlin_audio_release();
        this.exoPlayer.release();
        SimpleCache simpleCache = this.cache;
        if (simpleCache != null) {
            simpleCache.release();
        }
        this.cache = null;
        this.mediaSession.setActive(false);
    }

    public void seek(long duration, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.exoPlayer.seekTo(TimeUnit.MILLISECONDS.convert(duration, unit));
    }

    public void seekBy(long offset, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        this.exoPlayer.seekTo(this.exoPlayer.getCurrentPosition() + TimeUnit.MILLISECONDS.convert(offset, unit));
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final com.google.android.exoplayer2.source.MediaSource getMediaSourceFromAudioItem(com.doublesymmetry.kotlinaudio.models.AudioItem r6) throws com.google.android.exoplayer2.upstream.DataSourceException, android.content.res.Resources.NotFoundException, java.lang.NumberFormatException {
        /*
            r5 = this;
            java.lang.String r0 = "audioItem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = r6.getAudioUrl()
            android.net.Uri r0 = android.net.Uri.parse(r0)
            com.google.android.exoplayer2.MediaItem$Builder r1 = new com.google.android.exoplayer2.MediaItem$Builder
            r1.<init>()
            java.lang.String r2 = r6.getAudioUrl()
            com.google.android.exoplayer2.MediaItem$Builder r1 = r1.setUri(r2)
            com.doublesymmetry.kotlinaudio.models.AudioItemHolder r2 = new com.doublesymmetry.kotlinaudio.models.AudioItemHolder
            r2.<init>(r6)
            com.google.android.exoplayer2.MediaItem$Builder r1 = r1.setTag(r2)
            com.google.android.exoplayer2.MediaItem r1 = r1.build()
            java.lang.String r2 = "Builder()\n            .s…em))\n            .build()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.doublesymmetry.kotlinaudio.models.AudioItemOptions r2 = r6.getOptions()
            if (r2 == 0) goto L54
            com.doublesymmetry.kotlinaudio.models.AudioItemOptions r2 = r6.getOptions()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r2 = r2.getUserAgent()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            if (r2 == 0) goto L54
            boolean r2 = kotlin.text.StringsKt.isBlank(r2)
            if (r2 == 0) goto L48
            goto L54
        L48:
            com.doublesymmetry.kotlinaudio.models.AudioItemOptions r2 = r6.getOptions()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r2 = r2.getUserAgent()
            goto L5c
        L54:
            android.content.Context r2 = r5.context
            java.lang.String r3 = "react-native-track-player"
            java.lang.String r2 = com.google.android.exoplayer2.util.Util.getUserAgent(r2, r3)
        L5c:
            com.doublesymmetry.kotlinaudio.models.AudioItemOptions r3 = r6.getOptions()
            if (r3 == 0) goto L67
            java.lang.Integer r3 = r3.getResourceId()
            goto L68
        L67:
            r3 = 0
        L68:
            r4 = 1
            if (r3 == 0) goto L80
            com.google.android.exoplayer2.upstream.RawResourceDataSource r2 = new com.google.android.exoplayer2.upstream.RawResourceDataSource
            android.content.Context r3 = r5.context
            r2.<init>(r3)
            com.google.android.exoplayer2.upstream.DataSpec r3 = new com.google.android.exoplayer2.upstream.DataSpec
            r3.<init>(r0)
            r2.open(r3)
            com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$$ExternalSyntheticLambda0 r0 = new com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer$$ExternalSyntheticLambda0
            r0.<init>()
            goto Lb4
        L80:
            boolean r0 = com.doublesymmetry.kotlinaudio.utils.UtilsKt.isUriLocalFile(r0)
            if (r0 == 0) goto L90
            com.google.android.exoplayer2.upstream.DefaultDataSourceFactory r0 = new com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
            android.content.Context r3 = r5.context
            r0.<init>(r3, r2)
            com.google.android.exoplayer2.upstream.DataSource$Factory r0 = (com.google.android.exoplayer2.upstream.DataSource.Factory) r0
            goto Lb4
        L90:
            com.google.android.exoplayer2.upstream.DefaultHttpDataSource$Factory r0 = new com.google.android.exoplayer2.upstream.DefaultHttpDataSource$Factory
            r0.<init>()
            r0.setUserAgent(r2)
            r0.setAllowCrossProtocolRedirects(r4)
            com.doublesymmetry.kotlinaudio.models.AudioItemOptions r2 = r6.getOptions()
            if (r2 == 0) goto Lae
            java.util.Map r2 = r2.getHeaders()
            if (r2 == 0) goto Lae
            java.util.Map r2 = kotlin.collections.MapsKt.toMap(r2)
            r0.setDefaultRequestProperties(r2)
        Lae:
            com.google.android.exoplayer2.upstream.DataSource$Factory r0 = (com.google.android.exoplayer2.upstream.DataSource.Factory) r0
            com.google.android.exoplayer2.upstream.DataSource$Factory r0 = r5.enableCaching(r0)
        Lb4:
            com.doublesymmetry.kotlinaudio.models.MediaType r6 = r6.getType()
            int[] r2 = com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.WhenMappings.$EnumSwitchMapping$2
            int r6 = r6.ordinal()
            r6 = r2[r6]
            if (r6 == r4) goto Ld9
            r2 = 2
            if (r6 == r2) goto Ld4
            r2 = 3
            if (r6 == r2) goto Lcf
            com.google.android.exoplayer2.source.ProgressiveMediaSource r6 = r5.createProgressiveSource(r1, r0)
            com.google.android.exoplayer2.source.MediaSource r6 = (com.google.android.exoplayer2.source.MediaSource) r6
            goto Ldd
        Lcf:
            com.google.android.exoplayer2.source.MediaSource r6 = r5.createSsSource(r1, r0)
            goto Ldd
        Ld4:
            com.google.android.exoplayer2.source.MediaSource r6 = r5.createHlsSource(r1, r0)
            goto Ldd
        Ld9:
            com.google.android.exoplayer2.source.MediaSource r6 = r5.createDashSource(r1, r0)
        Ldd:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer.getMediaSourceFromAudioItem(com.doublesymmetry.kotlinaudio.models.AudioItem):com.google.android.exoplayer2.source.MediaSource");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: getMediaSourceFromAudioItem$lambda-2, reason: not valid java name */
    public static final DataSource m285getMediaSourceFromAudioItem$lambda2(RawResourceDataSource raw) {
        Intrinsics.checkNotNullParameter(raw, "$raw");
        return raw;
    }

    private final MediaSource createDashSource(MediaItem mediaItem, DataSource.Factory factory) {
        Intrinsics.checkNotNull(factory);
        DashMediaSource dashMediaSourceCreateMediaSource = new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(factory), factory).createMediaSource(mediaItem);
        Intrinsics.checkNotNullExpressionValue(dashMediaSourceCreateMediaSource, "Factory(DefaultDashChunk…ateMediaSource(mediaItem)");
        return dashMediaSourceCreateMediaSource;
    }

    private final MediaSource createHlsSource(MediaItem mediaItem, DataSource.Factory factory) {
        Intrinsics.checkNotNull(factory);
        HlsMediaSource hlsMediaSourceCreateMediaSource = new HlsMediaSource.Factory(factory).createMediaSource(mediaItem);
        Intrinsics.checkNotNullExpressionValue(hlsMediaSourceCreateMediaSource, "Factory(factory!!)\n     …ateMediaSource(mediaItem)");
        return hlsMediaSourceCreateMediaSource;
    }

    private final MediaSource createSsSource(MediaItem mediaItem, DataSource.Factory factory) {
        Intrinsics.checkNotNull(factory);
        SsMediaSource ssMediaSourceCreateMediaSource = new SsMediaSource.Factory(new DefaultSsChunkSource.Factory(factory), factory).createMediaSource(mediaItem);
        Intrinsics.checkNotNullExpressionValue(ssMediaSourceCreateMediaSource, "Factory(DefaultSsChunkSo…ateMediaSource(mediaItem)");
        return ssMediaSourceCreateMediaSource;
    }

    private final ProgressiveMediaSource createProgressiveSource(MediaItem mediaItem, DataSource.Factory factory) {
        ProgressiveMediaSource progressiveMediaSourceCreateMediaSource = new ProgressiveMediaSource.Factory(factory, new DefaultExtractorsFactory().setConstantBitrateSeekingEnabled(true)).createMediaSource(mediaItem);
        Intrinsics.checkNotNullExpressionValue(progressiveMediaSourceCreateMediaSource, "Factory(\n            fac…ateMediaSource(mediaItem)");
        return progressiveMediaSourceCreateMediaSource;
    }

    private final DataSource.Factory enableCaching(DataSource.Factory factory) {
        CacheConfig cacheConfig;
        if (this.cache == null || (cacheConfig = this.cacheConfig) == null) {
            return factory;
        }
        Long maxCacheSize = cacheConfig.getMaxCacheSize();
        if ((maxCacheSize != null ? maxCacheSize.longValue() : 0L) <= 0) {
            return factory;
        }
        CacheDataSource.Factory factory2 = new CacheDataSource.Factory();
        SimpleCache simpleCache = this.cache;
        Intrinsics.checkNotNull(simpleCache);
        factory2.setCache(simpleCache);
        factory2.setUpstreamDataSourceFactory(factory);
        factory2.setFlags(2);
        return factory2;
    }

    private final void requestAudioFocus() {
        int iRequestAudioFocus;
        if (this.hasAudioFocus) {
            return;
        }
        Timber.INSTANCE.d("Requesting audio focus...", new Object[0]);
        AudioManager audioManager = (AudioManager) ContextCompat.getSystemService(this.context, AudioManager.class);
        AudioFocusRequestCompat audioFocusRequestCompatBuild = new AudioFocusRequestCompat.Builder(1).setOnAudioFocusChangeListener(this).setAudioAttributes(new AudioAttributesCompat.Builder().setUsage(1).setContentType(2).build()).setWillPauseWhenDucked(getPlayerOptions().getAlwaysPauseOnInterruption()).build();
        this.focus = audioFocusRequestCompatBuild;
        if (audioManager == null || audioFocusRequestCompatBuild == null) {
            iRequestAudioFocus = 0;
        } else {
            Intrinsics.checkNotNull(audioFocusRequestCompatBuild);
            iRequestAudioFocus = AudioManagerCompat.requestAudioFocus(audioManager, audioFocusRequestCompatBuild);
        }
        this.hasAudioFocus = iRequestAudioFocus == 1;
    }

    private final void abandonAudioFocusIfHeld() {
        int iAbandonAudioFocusRequest;
        AudioFocusRequestCompat audioFocusRequestCompat;
        if (this.hasAudioFocus) {
            Timber.INSTANCE.d("Abandoning audio focus...", new Object[0]);
            AudioManager audioManager = (AudioManager) ContextCompat.getSystemService(this.context, AudioManager.class);
            if (audioManager == null || (audioFocusRequestCompat = this.focus) == null) {
                iAbandonAudioFocusRequest = 0;
            } else {
                Intrinsics.checkNotNull(audioFocusRequestCompat);
                iAbandonAudioFocusRequest = AudioManagerCompat.abandonAudioFocusRequest(audioManager, audioFocusRequestCompat);
            }
            this.hasAudioFocus = iAbandonAudioFocusRequest != 1;
        }
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int focusChange) {
        boolean alwaysPauseOnInterruption;
        Timber.INSTANCE.d("Audio focus changed", new Object[0]);
        boolean z = focusChange == -1;
        if (focusChange != -3) {
            alwaysPauseOnInterruption = focusChange == -2 || focusChange == -1;
        } else {
            alwaysPauseOnInterruption = getPlayerOptions().getAlwaysPauseOnInterruption();
        }
        if (!this.playerConfig.getHandleAudioFocus()) {
            if (z) {
                abandonAudioFocusIfHeld();
            }
            if (focusChange == -3 && !getPlayerOptions().getAlwaysPauseOnInterruption()) {
                setVolumeMultiplier(0.5f);
                this.wasDucking = true;
            } else if (this.wasDucking) {
                setVolumeMultiplier(1.0f);
                this.wasDucking = false;
            }
        }
        this.playerEventHolder.updateOnAudioFocusChanged$kotlin_audio_release(alwaysPauseOnInterruption, z);
    }

    /* compiled from: BaseAudioPlayer.kt */
    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u001a\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J \u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u001e"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/players/BaseAudioPlayer$PlayerListener;", "Lcom/google/android/exoplayer2/Player$Listener;", "(Lcom/doublesymmetry/kotlinaudio/players/BaseAudioPlayer;)V", "onEvents", "", "player", "Lcom/google/android/exoplayer2/Player;", f.ax, "Lcom/google/android/exoplayer2/Player$Events;", "onMediaItemTransition", "mediaItem", "Lcom/google/android/exoplayer2/MediaItem;", "reason", "", "onMediaMetadataChanged", "mediaMetadata", "Lcom/google/android/exoplayer2/MediaMetadata;", "onMetadata", "metadata", "Lcom/google/android/exoplayer2/metadata/Metadata;", "onPlayWhenReadyChanged", "playWhenReady", "", "onPlayerError", "error", "Lcom/google/android/exoplayer2/PlaybackException;", "onPositionDiscontinuity", "oldPosition", "Lcom/google/android/exoplayer2/Player$PositionInfo;", "newPosition", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class PlayerListener implements Player.Listener {
        public PlayerListener() {
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onMetadata(com.google.android.exoplayer2.metadata.Metadata metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            BaseAudioPlayer.this.playerEventHolder.updateOnTimedMetadata$kotlin_audio_release(metadata);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onMediaMetadataChanged(MediaMetadata mediaMetadata) {
            Intrinsics.checkNotNullParameter(mediaMetadata, "mediaMetadata");
            BaseAudioPlayer.this.playerEventHolder.updateOnCommonMetadata$kotlin_audio_release(mediaMetadata);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onPositionDiscontinuity(Player.PositionInfo oldPosition, Player.PositionInfo newPosition, int reason) {
            Intrinsics.checkNotNullParameter(oldPosition, "oldPosition");
            Intrinsics.checkNotNullParameter(newPosition, "newPosition");
            BaseAudioPlayer.this.oldPosition = oldPosition.positionMs;
            if (reason == 0) {
                BaseAudioPlayer.this.playerEventHolder.updatePositionChangedReason$kotlin_audio_release(new PositionChangedReason.AUTO(oldPosition.positionMs, newPosition.positionMs));
                return;
            }
            if (reason == 1) {
                BaseAudioPlayer.this.playerEventHolder.updatePositionChangedReason$kotlin_audio_release(new PositionChangedReason.SEEK(oldPosition.positionMs, newPosition.positionMs));
                return;
            }
            if (reason == 2) {
                BaseAudioPlayer.this.playerEventHolder.updatePositionChangedReason$kotlin_audio_release(new PositionChangedReason.SEEK_FAILED(oldPosition.positionMs, newPosition.positionMs));
                return;
            }
            if (reason == 3) {
                BaseAudioPlayer.this.playerEventHolder.updatePositionChangedReason$kotlin_audio_release(new PositionChangedReason.SKIPPED_PERIOD(oldPosition.positionMs, newPosition.positionMs));
            } else if (reason == 4) {
                BaseAudioPlayer.this.playerEventHolder.updatePositionChangedReason$kotlin_audio_release(new PositionChangedReason.QUEUE_CHANGED(oldPosition.positionMs, newPosition.positionMs));
            } else {
                if (reason != 5) {
                    return;
                }
                BaseAudioPlayer.this.playerEventHolder.updatePositionChangedReason$kotlin_audio_release(new PositionChangedReason.UNKNOWN(oldPosition.positionMs, newPosition.positionMs));
            }
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onMediaItemTransition(MediaItem mediaItem, int reason) {
            if (reason == 0) {
                BaseAudioPlayer.this.playerEventHolder.updateAudioItemTransition$kotlin_audio_release(new AudioItemTransitionReason.REPEAT(BaseAudioPlayer.this.oldPosition));
            } else if (reason == 1) {
                BaseAudioPlayer.this.playerEventHolder.updateAudioItemTransition$kotlin_audio_release(new AudioItemTransitionReason.AUTO(BaseAudioPlayer.this.oldPosition));
            } else if (reason == 2) {
                BaseAudioPlayer.this.playerEventHolder.updateAudioItemTransition$kotlin_audio_release(new AudioItemTransitionReason.SEEK_TO_ANOTHER_AUDIO_ITEM(BaseAudioPlayer.this.oldPosition));
            } else if (reason == 3) {
                BaseAudioPlayer.this.playerEventHolder.updateAudioItemTransition$kotlin_audio_release(new AudioItemTransitionReason.QUEUE_CHANGED(BaseAudioPlayer.this.oldPosition));
            }
            BaseAudioPlayer.updateNotificationIfNecessary$kotlin_audio_release$default(BaseAudioPlayer.this, null, 1, null);
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
            BaseAudioPlayer.this.playerEventHolder.updatePlayWhenReadyChange$kotlin_audio_release(new PlayWhenReadyChangeData(playWhenReady, reason == 5));
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onEvents(Player player, Player.Events events) {
            Intrinsics.checkNotNullParameter(player, "player");
            Intrinsics.checkNotNullParameter(events, "events");
            int size = events.size();
            for (int i = 0; i < size; i++) {
                int i2 = events.get(i);
                AudioPlayerState audioPlayerState = null;
                if (i2 == 1) {
                    BaseAudioPlayer.this.setPlaybackError(null);
                    if (BaseAudioPlayer.this.getCurrentItem() != null) {
                        BaseAudioPlayer.this.setPlayerState(AudioPlayerState.LOADING);
                        if (BaseAudioPlayer.this.isPlaying()) {
                            BaseAudioPlayer.this.setPlayerState(AudioPlayerState.READY);
                            BaseAudioPlayer.this.setPlayerState(AudioPlayerState.PLAYING);
                        }
                    }
                } else if (i2 != 7) {
                    if (i2 == 4) {
                        int playbackState = player.getPlaybackState();
                        if (playbackState != 1) {
                            if (playbackState == 2) {
                                audioPlayerState = AudioPlayerState.BUFFERING;
                            } else if (playbackState == 3) {
                                audioPlayerState = AudioPlayerState.READY;
                            } else if (playbackState == 4) {
                                audioPlayerState = player.getMediaItemCount() > 0 ? AudioPlayerState.ENDED : AudioPlayerState.IDLE;
                            }
                        } else if (BaseAudioPlayer.this.getPlayerState() != AudioPlayerState.ERROR && BaseAudioPlayer.this.getPlayerState() != AudioPlayerState.STOPPED) {
                            audioPlayerState = AudioPlayerState.IDLE;
                        }
                        if (audioPlayerState != null && audioPlayerState != BaseAudioPlayer.this.getPlayerState()) {
                            BaseAudioPlayer.this.setPlayerState(audioPlayerState);
                        }
                    } else if (i2 == 5 && !player.getPlayWhenReady() && BaseAudioPlayer.this.getPlayerState() != AudioPlayerState.STOPPED) {
                        BaseAudioPlayer.this.setPlayerState(AudioPlayerState.PAUSED);
                    }
                } else if (player.isPlaying()) {
                    BaseAudioPlayer.this.setPlayerState(AudioPlayerState.PLAYING);
                }
            }
        }

        @Override // com.google.android.exoplayer2.Player.Listener
        public void onPlayerError(PlaybackException error) {
            Intrinsics.checkNotNullParameter(error, "error");
            String errorCodeName = error.getErrorCodeName();
            Intrinsics.checkNotNullExpressionValue(errorCodeName, "error.errorCodeName");
            String strReplace$default = StringsKt.replace$default(errorCodeName, "ERROR_CODE_", "", false, 4, (Object) null);
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            String lowerCase = strReplace$default.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
            PlaybackError playbackError = new PlaybackError(StringsKt.replace$default(lowerCase, "_", "-", false, 4, (Object) null), error.getMessage());
            BaseAudioPlayer.this.playerEventHolder.updatePlaybackError$kotlin_audio_release(playbackError);
            BaseAudioPlayer.this.setPlaybackError(playbackError);
            BaseAudioPlayer.this.setPlayerState(AudioPlayerState.ERROR);
        }
    }
}
