package com.doublesymmetry.kotlinaudio.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import androidx.core.app.NotificationCompat;
import coil.Coil;
import coil.ImageLoader;
import coil.request.Disposable;
import coil.request.ImageRequest;
import coil.target.Target;
import com.doublesymmetry.kotlinaudio.R;
import com.doublesymmetry.kotlinaudio.event.NotificationEventHolder;
import com.doublesymmetry.kotlinaudio.event.PlayerEventHolder;
import com.doublesymmetry.kotlinaudio.models.AudioItem;
import com.doublesymmetry.kotlinaudio.models.AudioItemHolder;
import com.doublesymmetry.kotlinaudio.models.AudioItemOptions;
import com.doublesymmetry.kotlinaudio.models.MediaSessionCallback;
import com.doublesymmetry.kotlinaudio.models.NotificationButton;
import com.doublesymmetry.kotlinaudio.models.NotificationConfig;
import com.doublesymmetry.kotlinaudio.models.NotificationState;
import com.doublesymmetry.kotlinaudio.players.components.MediaItemExtKt;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector;
import com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.umeng.analytics.pro.f;
import com.umeng.ccg.a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import okhttp3.Headers;

/* compiled from: NotificationManager.kt */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0002\u0013\u0016\u0018\u0000 \u0093\u00012\u00020\u0001:\u0002\u0093\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010]\u001a\u00020^2\b\b\u0001\u0010_\u001a\u00020\u001b2\u0006\u0010`\u001a\u00020aH\u0002J\u000e\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020eJ \u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020\u001b2\u0006\u0010i\u001a\u00020a2\u0006\u0010j\u001a\u00020\u001bH\u0002J\r\u0010k\u001a\u00020cH\u0000¢\u0006\u0002\blJ\u001b\u0010m\u001a\u0004\u0018\u00010a2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010oJ\u001b\u0010p\u001a\u0004\u0018\u00010a2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010oJ\u001b\u0010q\u001a\u0004\u0018\u00010a2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010oJ\u001b\u0010r\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010sJ\u001b\u0010t\u001a\u0004\u0018\u00010u2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010vJ\u001b\u0010w\u001a\u0004\u0018\u00010a2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010oJ\u001b\u0010x\u001a\u0004\u0018\u00010a2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010oJ\u0006\u0010y\u001a\u00020zJ\b\u0010{\u001a\u00020|H\u0002J\u001b\u0010}\u001a\u0004\u0018\u00010a2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0002\u0010oJ\u001c\u0010~\u001a\u0004\u0018\u00010\u007f2\n\b\u0002\u0010n\u001a\u0004\u0018\u00010\u001bH\u0002¢\u0006\u0003\u0010\u0080\u0001J\u0012\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0006\u0010i\u001a\u00020aH\u0002J\b\u0010\u0083\u0001\u001a\u00030\u0082\u0001J\b\u0010\u0084\u0001\u001a\u00030\u0082\u0001J\u0019\u0010\u0085\u0001\u001a\u0002092\u000e\u0010\u0086\u0001\u001a\t\u0012\u0004\u0012\u00020\u00110\u0087\u0001H\u0002J\u001c\u0010\u0088\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u001b2\u0007\u0010\u008a\u0001\u001a\u000209H\u0016J&\u0010\u008b\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0089\u0001\u001a\u00020\u001b2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0007\u0010\u008e\u0001\u001a\u000209H\u0016J\u0011\u0010\u008f\u0001\u001a\u00030\u0082\u00012\u0007\u0010\u0090\u0001\u001a\u00020*J\u0012\u0010\u0091\u0001\u001a\u00030\u0082\u00012\u0006\u0010d\u001a\u00020eH\u0002J\n\u0010\u0092\u0001\u001a\u00030\u0082\u0001H\u0002R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010'X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010+\u001a\u0004\u0018\u00010*2\b\u0010)\u001a\u0004\u0018\u00010*@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001e\u00104\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b5\u0010\u001d\"\u0004\b6\u0010\u001fR\u000e\u00107\u001a\u000208X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010:\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R$\u0010?\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010<\"\u0004\bA\u0010>R$\u0010B\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010<\"\u0004\bD\u0010>R$\u0010E\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010<\"\u0004\bG\u0010>R$\u0010H\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010<\"\u0004\bJ\u0010>R$\u0010K\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010<\"\u0004\bM\u0010>R$\u0010N\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010<\"\u0004\bP\u0010>R$\u0010Q\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010<\"\u0004\bS\u0010>R$\u0010T\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bU\u0010<\"\u0004\bV\u0010>R$\u0010W\u001a\u0002092\u0006\u0010)\u001a\u000209@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010<\"\u0004\bY\u0010>R\u001e\u0010Z\u001a\u0004\u0018\u00010\u001bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b[\u0010\u001d\"\u0004\b\\\u0010\u001f¨\u0006\u0094\u0001"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/notification/NotificationManager;", "Lcom/google/android/exoplayer2/ui/PlayerNotificationManager$NotificationListener;", f.X, "Landroid/content/Context;", "player", "Lcom/google/android/exoplayer2/Player;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSessionConnector", "Lcom/google/android/exoplayer2/ext/mediasession/MediaSessionConnector;", "event", "Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;", "playerEventHolder", "Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;", "(Landroid/content/Context;Lcom/google/android/exoplayer2/Player;Landroid/support/v4/media/session/MediaSessionCompat;Lcom/google/android/exoplayer2/ext/mediasession/MediaSessionConnector;Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;)V", "buttons", "", "Lcom/doublesymmetry/kotlinaudio/models/NotificationButton;", "customActionReceiver", "com/doublesymmetry/kotlinaudio/notification/NotificationManager$customActionReceiver$1", "Lcom/doublesymmetry/kotlinaudio/notification/NotificationManager$customActionReceiver$1;", "descriptionAdapter", "com/doublesymmetry/kotlinaudio/notification/NotificationManager$descriptionAdapter$1", "Lcom/doublesymmetry/kotlinaudio/notification/NotificationManager$descriptionAdapter$1;", "getEvent", "()Lcom/doublesymmetry/kotlinaudio/event/NotificationEventHolder;", "forwardIcon", "", "getForwardIcon", "()Ljava/lang/Integer;", "setForwardIcon", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "iconPlaceholder", "Landroid/graphics/Bitmap;", "internalNotificationManager", "Lcom/google/android/exoplayer2/ui/PlayerNotificationManager;", "invalidateThrottleCount", "notificationMetadataArtworkDisposable", "Lcoil/request/Disposable;", "notificationMetadataBitmap", "value", "Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "overrideAudioItem", "getOverrideAudioItem$kotlin_audio_release", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "setOverrideAudioItem$kotlin_audio_release", "(Lcom/doublesymmetry/kotlinaudio/models/AudioItem;)V", "pendingIntent", "Landroid/app/PendingIntent;", "getPlayerEventHolder", "()Lcom/doublesymmetry/kotlinaudio/event/PlayerEventHolder;", "rewindIcon", "getRewindIcon", "setRewindIcon", "scope", "Lkotlinx/coroutines/CoroutineScope;", "", "showForwardButton", "getShowForwardButton", "()Z", "setShowForwardButton", "(Z)V", "showForwardButtonCompact", "getShowForwardButtonCompact", "setShowForwardButtonCompact", "showNextButton", "getShowNextButton", "setShowNextButton", "showNextButtonCompact", "getShowNextButtonCompact", "setShowNextButtonCompact", "showPlayPauseButton", "getShowPlayPauseButton", "setShowPlayPauseButton", "showPreviousButton", "getShowPreviousButton", "setShowPreviousButton", "showPreviousButtonCompact", "getShowPreviousButtonCompact", "setShowPreviousButtonCompact", "showRewindButton", "getShowRewindButton", "setShowRewindButton", "showRewindButtonCompact", "getShowRewindButtonCompact", "setShowRewindButtonCompact", "showStopButton", "getShowStopButton", "setShowStopButton", "stopIcon", "getStopIcon", "setStopIcon", "createMediaSessionAction", "Lcom/google/android/exoplayer2/ext/mediasession/MediaSessionConnector$CustomActionProvider;", "drawableRes", "actionName", "", "createNotification", "Lkotlinx/coroutines/Job;", "config", "Lcom/doublesymmetry/kotlinaudio/models/NotificationConfig;", "createNotificationAction", "Landroidx/core/app/NotificationCompat$Action;", "drawable", a.t, "instanceId", "destroy", "destroy$kotlin_audio_release", "getAlbumTitle", "index", "(Ljava/lang/Integer;)Ljava/lang/String;", "getArtist", "getArtworkUrl", "getCachedArtworkBitmap", "(Ljava/lang/Integer;)Landroid/graphics/Bitmap;", "getDuration", "", "(Ljava/lang/Integer;)Ljava/lang/Long;", "getGenre", "getMediaItemArtworkUrl", "getMediaMetadataCompat", "Landroid/support/v4/media/MediaMetadataCompat;", "getNetworkHeaders", "Lokhttp3/Headers;", "getTitle", "getUserRating", "Landroid/support/v4/media/RatingCompat;", "(Ljava/lang/Integer;)Landroid/support/v4/media/RatingCompat;", "handlePlayerAction", "", "hideNotification", "invalidate", "isNotificationButtonsChanged", "newButtons", "", "onNotificationCancelled", "notificationId", "dismissedByUser", "onNotificationPosted", "notification", "Landroid/app/Notification;", "ongoing", "overrideMetadata", "item", "setupInternalNotificationManager", "updateMediaSessionPlaybackActions", "Companion", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class NotificationManager implements PlayerNotificationManager.NotificationListener {
    private static final String CHANNEL_ID = "kotlin_audio_player";
    private static final int DEFAULT_FORWARD_ICON;
    private static final int DEFAULT_REWIND_ICON;
    private static final int DEFAULT_STOP_ICON;
    private static final String FORWARD = "forward";
    private static final int NOTIFICATION_ID = 1;
    private static final String REWIND = "rewind";
    private static final String STOP = "stop";
    private static final boolean needsCustomActionsToAddMissingButtons;
    private final Set<NotificationButton> buttons;
    private final Context context;
    private final NotificationManager$customActionReceiver$1 customActionReceiver;
    private final NotificationManager$descriptionAdapter$1 descriptionAdapter;
    private final NotificationEventHolder event;
    private Integer forwardIcon;
    private Bitmap iconPlaceholder;
    private PlayerNotificationManager internalNotificationManager;
    private int invalidateThrottleCount;
    private final MediaSessionCompat mediaSession;
    private final MediaSessionConnector mediaSessionConnector;
    private Disposable notificationMetadataArtworkDisposable;
    private Bitmap notificationMetadataBitmap;
    private AudioItem overrideAudioItem;
    private PendingIntent pendingIntent;
    private final Player player;
    private final PlayerEventHolder playerEventHolder;
    private Integer rewindIcon;
    private final CoroutineScope scope;
    private boolean showForwardButton;
    private boolean showForwardButtonCompact;
    private boolean showNextButton;
    private boolean showNextButtonCompact;
    private boolean showPlayPauseButton;
    private boolean showPreviousButton;
    private boolean showPreviousButtonCompact;
    private boolean showRewindButton;
    private boolean showRewindButtonCompact;
    private boolean showStopButton;
    private Integer stopIcon;

    /* JADX WARN: Type inference failed for: r2v1, types: [com.doublesymmetry.kotlinaudio.notification.NotificationManager$descriptionAdapter$1] */
    /* JADX WARN: Type inference failed for: r2v10, types: [com.doublesymmetry.kotlinaudio.notification.NotificationManager$customActionReceiver$1] */
    public NotificationManager(Context context, Player player, MediaSessionCompat mediaSession, MediaSessionConnector mediaSessionConnector, NotificationEventHolder event, PlayerEventHolder playerEventHolder) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(player, "player");
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        Intrinsics.checkNotNullParameter(mediaSessionConnector, "mediaSessionConnector");
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(playerEventHolder, "playerEventHolder");
        this.context = context;
        this.player = player;
        this.mediaSession = mediaSession;
        this.mediaSessionConnector = mediaSessionConnector;
        this.event = event;
        this.playerEventHolder = playerEventHolder;
        this.descriptionAdapter = new PlayerNotificationManager.MediaDescriptionAdapter() { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager$descriptionAdapter$1
            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public CharSequence getCurrentContentTitle(Player player2) {
                Intrinsics.checkNotNullParameter(player2, "player");
                String title$default = NotificationManager.getTitle$default(this.this$0, null, 1, null);
                if (title$default == null) {
                    title$default = "";
                }
                return title$default;
            }

            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public PendingIntent createCurrentContentIntent(Player player2) {
                Intrinsics.checkNotNullParameter(player2, "player");
                return this.this$0.pendingIntent;
            }

            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public CharSequence getCurrentContentText(Player player2) {
                Intrinsics.checkNotNullParameter(player2, "player");
                String artist$default = NotificationManager.getArtist$default(this.this$0, null, 1, null);
                if (artist$default == null) {
                    artist$default = "";
                }
                return artist$default;
            }

            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public CharSequence getCurrentSubText(Player player2) {
                Intrinsics.checkNotNullParameter(player2, "player");
                return player2.getMediaMetadata().displayTitle;
            }

            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.MediaDescriptionAdapter
            public Bitmap getCurrentLargeIcon(Player player2, PlayerNotificationManager.BitmapCallback callback) {
                Intrinsics.checkNotNullParameter(player2, "player");
                Intrinsics.checkNotNullParameter(callback, "callback");
                Bitmap cachedArtworkBitmap$default = NotificationManager.getCachedArtworkBitmap$default(this.this$0, null, 1, null);
                if (cachedArtworkBitmap$default != null) {
                    return cachedArtworkBitmap$default;
                }
                String mediaItemArtworkUrl$default = NotificationManager.getMediaItemArtworkUrl$default(this.this$0, null, 1, null);
                Headers networkHeaders = this.this$0.getNetworkHeaders();
                MediaItem currentMediaItem = player2.getCurrentMediaItem();
                final AudioItemHolder audioItemHolder = currentMediaItem != null ? MediaItemExtKt.getAudioItemHolder(currentMediaItem) : null;
                if (mediaItemArtworkUrl$default != null) {
                    if ((audioItemHolder != null ? audioItemHolder.getArtworkBitmap() : null) == null) {
                        ImageLoader imageLoader = Coil.imageLoader(this.this$0.context);
                        ImageRequest.Builder builderHeaders = new ImageRequest.Builder(this.this$0.context).data(mediaItemArtworkUrl$default).headers(networkHeaders);
                        final NotificationManager notificationManager = this.this$0;
                        imageLoader.enqueue(builderHeaders.target(new Target() { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager$descriptionAdapter$1$getCurrentLargeIcon$$inlined$target$default$1
                            @Override // coil.target.Target
                            public void onError(Drawable error) {
                            }

                            @Override // coil.target.Target
                            public void onStart(Drawable placeholder) {
                            }

                            @Override // coil.target.Target
                            public void onSuccess(Drawable result) {
                                Intrinsics.checkNotNull(result, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
                                Bitmap bitmap = ((BitmapDrawable) result).getBitmap();
                                AudioItemHolder audioItemHolder2 = audioItemHolder;
                                if (audioItemHolder2 != null) {
                                    audioItemHolder2.setArtworkBitmap(bitmap);
                                }
                                notificationManager.invalidate();
                            }
                        }).build());
                    }
                }
                return this.this$0.iconPlaceholder;
            }
        };
        this.scope = CoroutineScopeKt.MainScope();
        this.buttons = new LinkedHashSet();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(64, 64, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(64, 64, Bitmap.Config.ARGB_8888)");
        this.iconPlaceholder = bitmapCreateBitmap;
        mediaSessionConnector.setQueueNavigator(new TimelineQueueNavigator(mediaSession) { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager.1
            @Override // com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator, com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.QueueNavigator
            public long getSupportedQueueNavigatorActions(Player player2) {
                long j;
                Intrinsics.checkNotNullParameter(player2, "player");
                long j2 = 0;
                for (NotificationButton notificationButton : NotificationManager.this.buttons) {
                    if (notificationButton instanceof NotificationButton.NEXT) {
                        j = 32;
                    } else {
                        j = notificationButton instanceof NotificationButton.PREVIOUS ? 16L : 0L;
                    }
                    j2 |= j;
                }
                return j2;
            }

            @Override // com.google.android.exoplayer2.ext.mediasession.TimelineQueueNavigator
            public MediaDescriptionCompat getMediaDescription(Player player2, int windowIndex) {
                Intrinsics.checkNotNullParameter(player2, "player");
                String title = NotificationManager.this.getTitle(Integer.valueOf(windowIndex));
                String artist = NotificationManager.this.getArtist(Integer.valueOf(windowIndex));
                MediaDescriptionCompat.Builder builder = new MediaDescriptionCompat.Builder();
                builder.setTitle(title);
                builder.setSubtitle(artist);
                Bundle bundle = new Bundle();
                if (title != null) {
                    bundle.putString(MediaMetadataCompat.METADATA_KEY_TITLE, title);
                }
                if (artist != null) {
                    bundle.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist);
                }
                builder.setExtras(bundle);
                MediaDescriptionCompat mediaDescriptionCompatBuild = builder.build();
                Intrinsics.checkNotNullExpressionValue(mediaDescriptionCompatBuild, "Builder().apply {\n      …                }.build()");
                return mediaDescriptionCompatBuild;
            }
        });
        mediaSessionConnector.setMetadataDeduplicationEnabled(true);
        this.customActionReceiver = new PlayerNotificationManager.CustomActionReceiver() { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager$customActionReceiver$1
            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.CustomActionReceiver
            public Map<String, NotificationCompat.Action> createCustomActions(Context context2, int instanceId) {
                Intrinsics.checkNotNullParameter(context2, "context");
                if (!NotificationManager.needsCustomActionsToAddMissingButtons) {
                    return new LinkedHashMap();
                }
                Pair[] pairArr = new Pair[3];
                NotificationManager notificationManager = this.this$0;
                Integer rewindIcon = notificationManager.getRewindIcon();
                pairArr[0] = TuplesKt.to("rewind", notificationManager.createNotificationAction(rewindIcon != null ? rewindIcon.intValue() : NotificationManager.DEFAULT_REWIND_ICON, "rewind", instanceId));
                NotificationManager notificationManager2 = this.this$0;
                Integer forwardIcon = notificationManager2.getForwardIcon();
                pairArr[1] = TuplesKt.to("forward", notificationManager2.createNotificationAction(forwardIcon != null ? forwardIcon.intValue() : NotificationManager.DEFAULT_FORWARD_ICON, "forward", instanceId));
                NotificationManager notificationManager3 = this.this$0;
                Integer stopIcon = notificationManager3.getStopIcon();
                pairArr[2] = TuplesKt.to("stop", notificationManager3.createNotificationAction(stopIcon != null ? stopIcon.intValue() : NotificationManager.DEFAULT_STOP_ICON, "stop", instanceId));
                return MapsKt.mutableMapOf(pairArr);
            }

            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.CustomActionReceiver
            public List<String> getCustomActions(Player player2) {
                String str;
                Intrinsics.checkNotNullParameter(player2, "player");
                if (!NotificationManager.needsCustomActionsToAddMissingButtons) {
                    return CollectionsKt.emptyList();
                }
                Set<NotificationButton> set = this.this$0.buttons;
                ArrayList arrayList = new ArrayList();
                for (NotificationButton notificationButton : set) {
                    if (notificationButton instanceof NotificationButton.BACKWARD) {
                        str = "rewind";
                    } else if (notificationButton instanceof NotificationButton.FORWARD) {
                        str = "forward";
                    } else if (notificationButton instanceof NotificationButton.STOP) {
                        str = "stop";
                    } else {
                        str = null;
                    }
                    if (str != null) {
                        arrayList.add(str);
                    }
                }
                return arrayList;
            }

            @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.CustomActionReceiver
            public void onCustomAction(Player player2, String action, Intent intent) {
                Intrinsics.checkNotNullParameter(player2, "player");
                Intrinsics.checkNotNullParameter(action, "action");
                Intrinsics.checkNotNullParameter(intent, "intent");
                this.this$0.handlePlayerAction(action);
            }
        };
    }

    public static final /* synthetic */ PlayerNotificationManager access$getInternalNotificationManager$p(NotificationManager notificationManager) {
        return notificationManager.internalNotificationManager;
    }

    public final NotificationEventHolder getEvent() {
        return this.event;
    }

    public final PlayerEventHolder getPlayerEventHolder() {
        return this.playerEventHolder;
    }

    /* renamed from: getOverrideAudioItem$kotlin_audio_release, reason: from getter */
    public final AudioItem getOverrideAudioItem() {
        return this.overrideAudioItem;
    }

    public final void setOverrideAudioItem$kotlin_audio_release(AudioItem audioItem) {
        this.notificationMetadataBitmap = null;
        Headers networkHeaders = getNetworkHeaders();
        if (!Intrinsics.areEqual(this.overrideAudioItem, audioItem)) {
            if ((audioItem != null ? audioItem.getArtwork() : null) != null) {
                Disposable disposable = this.notificationMetadataArtworkDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                this.notificationMetadataArtworkDisposable = Coil.imageLoader(this.context).enqueue(new ImageRequest.Builder(this.context).data(audioItem.getArtwork()).headers(networkHeaders).target(new Target() { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager$special$$inlined$target$default$1
                    @Override // coil.target.Target
                    public void onError(Drawable error) {
                    }

                    @Override // coil.target.Target
                    public void onStart(Drawable placeholder) {
                    }

                    @Override // coil.target.Target
                    public void onSuccess(Drawable result) {
                        NotificationManager notificationManager = this.this$0;
                        Intrinsics.checkNotNull(result, "null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
                        notificationManager.notificationMetadataBitmap = ((BitmapDrawable) result).getBitmap();
                        this.this$0.invalidate();
                    }
                }).build());
            }
        }
        this.overrideAudioItem = audioItem;
        invalidate();
    }

    static /* synthetic */ String getTitle$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getTitle(num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getTitle(Integer index) {
        MediaMetadata mediaMetadata;
        CharSequence charSequence;
        String title;
        AudioItemHolder audioItemHolder;
        MediaItem currentMediaItem = index == null ? this.player.getCurrentMediaItem() : this.player.getMediaItemAt(index.intValue());
        AudioItem audioItem = (currentMediaItem == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(currentMediaItem)) == null) ? null : audioItemHolder.getAudioItem();
        AudioItem audioItem2 = this.overrideAudioItem;
        if (audioItem2 != null && (title = audioItem2.getTitle()) != null) {
            return title;
        }
        if (currentMediaItem != null && (mediaMetadata = currentMediaItem.mediaMetadata) != null && (charSequence = mediaMetadata.title) != null) {
            return charSequence.toString();
        }
        if (audioItem != null) {
            return audioItem.getTitle();
        }
        return null;
    }

    static /* synthetic */ String getArtist$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getArtist(num);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getArtist(Integer index) {
        MediaMetadata mediaMetadata;
        CharSequence charSequence;
        MediaMetadata mediaMetadata2;
        CharSequence charSequence2;
        String artist;
        AudioItemHolder audioItemHolder;
        MediaItem currentMediaItem = index == null ? this.player.getCurrentMediaItem() : this.player.getMediaItemAt(index.intValue());
        AudioItem audioItem = (currentMediaItem == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(currentMediaItem)) == null) ? null : audioItemHolder.getAudioItem();
        AudioItem audioItem2 = this.overrideAudioItem;
        if (audioItem2 != null && (artist = audioItem2.getArtist()) != null) {
            return artist;
        }
        if (currentMediaItem != null && (mediaMetadata2 = currentMediaItem.mediaMetadata) != null && (charSequence2 = mediaMetadata2.artist) != null) {
            return charSequence2.toString();
        }
        String string = (currentMediaItem == null || (mediaMetadata = currentMediaItem.mediaMetadata) == null || (charSequence = mediaMetadata.albumArtist) == null) ? null : charSequence.toString();
        if (string != null) {
            return string;
        }
        if (audioItem != null) {
            return audioItem.getArtist();
        }
        return null;
    }

    static /* synthetic */ String getGenre$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getGenre(num);
    }

    private final String getGenre(Integer index) {
        MediaMetadata mediaMetadata;
        CharSequence charSequence;
        MediaItem currentMediaItem = index == null ? this.player.getCurrentMediaItem() : this.player.getMediaItemAt(index.intValue());
        if (currentMediaItem == null || (mediaMetadata = currentMediaItem.mediaMetadata) == null || (charSequence = mediaMetadata.genre) == null) {
            return null;
        }
        return charSequence.toString();
    }

    static /* synthetic */ String getAlbumTitle$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getAlbumTitle(num);
    }

    private final String getAlbumTitle(Integer index) {
        AudioItemHolder audioItemHolder;
        AudioItem audioItem;
        MediaMetadata mediaMetadata;
        CharSequence charSequence;
        String string;
        MediaItem currentMediaItem = index == null ? this.player.getCurrentMediaItem() : this.player.getMediaItemAt(index.intValue());
        if (currentMediaItem != null && (mediaMetadata = currentMediaItem.mediaMetadata) != null && (charSequence = mediaMetadata.albumTitle) != null && (string = charSequence.toString()) != null) {
            return string;
        }
        if (currentMediaItem == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(currentMediaItem)) == null || (audioItem = audioItemHolder.getAudioItem()) == null) {
            return null;
        }
        return audioItem.getAlbumTitle();
    }

    static /* synthetic */ String getArtworkUrl$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getArtworkUrl(num);
    }

    private final String getArtworkUrl(Integer index) {
        return getMediaItemArtworkUrl(index);
    }

    static /* synthetic */ String getMediaItemArtworkUrl$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getMediaItemArtworkUrl(num);
    }

    private final String getMediaItemArtworkUrl(Integer index) {
        AudioItemHolder audioItemHolder;
        AudioItem audioItem;
        MediaMetadata mediaMetadata;
        Uri uri;
        String artwork;
        MediaItem currentMediaItem = index == null ? this.player.getCurrentMediaItem() : this.player.getMediaItemAt(index.intValue());
        AudioItem audioItem2 = this.overrideAudioItem;
        if (audioItem2 != null && (artwork = audioItem2.getArtwork()) != null) {
            return artwork;
        }
        String string = (currentMediaItem == null || (mediaMetadata = currentMediaItem.mediaMetadata) == null || (uri = mediaMetadata.artworkUri) == null) ? null : uri.toString();
        if (string != null) {
            return string;
        }
        if (currentMediaItem == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(currentMediaItem)) == null || (audioItem = audioItemHolder.getAudioItem()) == null) {
            return null;
        }
        return audioItem.getArtwork();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Headers getNetworkHeaders() {
        AudioItemHolder audioItemHolder;
        AudioItem audioItem;
        AudioItemOptions options;
        Map<String, String> headers;
        Headers headersOf;
        MediaItem currentMediaItem = this.player.getCurrentMediaItem();
        return (currentMediaItem == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(currentMediaItem)) == null || (audioItem = audioItemHolder.getAudioItem()) == null || (options = audioItem.getOptions()) == null || (headers = options.getHeaders()) == null || (headersOf = Headers.INSTANCE.of(headers)) == null) ? new Headers.Builder().build() : headersOf;
    }

    static /* synthetic */ Bitmap getCachedArtworkBitmap$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getCachedArtworkBitmap(num);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final android.graphics.Bitmap getCachedArtworkBitmap(java.lang.Integer r5) {
        /*
            r4 = this;
            com.google.android.exoplayer2.Player r0 = r4.player
            if (r5 != 0) goto L9
            com.google.android.exoplayer2.MediaItem r0 = r0.getCurrentMediaItem()
            goto L11
        L9:
            int r1 = r5.intValue()
            com.google.android.exoplayer2.MediaItem r0 = r0.getMediaItemAt(r1)
        L11:
            r1 = 0
            if (r5 == 0) goto L23
            com.google.android.exoplayer2.Player r2 = r4.player
            int r2 = r2.getCurrentMediaItemIndex()
            int r5 = r5.intValue()
            if (r5 != r2) goto L21
            goto L23
        L21:
            r5 = r1
            goto L24
        L23:
            r5 = 1
        L24:
            com.google.android.exoplayer2.Player r2 = r4.player
            com.google.android.exoplayer2.MediaMetadata r2 = r2.getMediaMetadata()
            byte[] r2 = r2.artworkData
            if (r5 == 0) goto L35
            com.doublesymmetry.kotlinaudio.models.AudioItem r3 = r4.overrideAudioItem
            if (r3 == 0) goto L35
            android.graphics.Bitmap r5 = r4.notificationMetadataBitmap
            goto L4d
        L35:
            if (r5 == 0) goto L3f
            if (r2 == 0) goto L3f
            int r5 = r2.length
            android.graphics.Bitmap r5 = android.graphics.BitmapFactory.decodeByteArray(r2, r1, r5)
            goto L4d
        L3f:
            if (r0 == 0) goto L4c
            com.doublesymmetry.kotlinaudio.models.AudioItemHolder r5 = com.doublesymmetry.kotlinaudio.players.components.MediaItemExtKt.getAudioItemHolder(r0)
            if (r5 == 0) goto L4c
            android.graphics.Bitmap r5 = r5.getArtworkBitmap()
            goto L4d
        L4c:
            r5 = 0
        L4d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.kotlinaudio.notification.NotificationManager.getCachedArtworkBitmap(java.lang.Integer):android.graphics.Bitmap");
    }

    static /* synthetic */ Long getDuration$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getDuration(num);
    }

    private final Long getDuration(Integer index) {
        MediaItem mediaItemAt;
        long jLongValue;
        AudioItemHolder audioItemHolder;
        AudioItem audioItem;
        Long duration;
        Long duration2;
        if (index == null) {
            mediaItemAt = this.player.getCurrentMediaItem();
        } else {
            mediaItemAt = this.player.getMediaItemAt(index.intValue());
        }
        if (this.player.isCurrentMediaItemDynamic() || this.player.getDuration() == -9223372036854775807L) {
            AudioItem audioItem2 = this.overrideAudioItem;
            if (audioItem2 == null || (duration = audioItem2.getDuration()) == null) {
                Long duration3 = (mediaItemAt == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(mediaItemAt)) == null || (audioItem = audioItemHolder.getAudioItem()) == null) ? null : audioItem.getDuration();
                jLongValue = duration3 != null ? duration3.longValue() : -1L;
            } else {
                jLongValue = duration.longValue();
            }
            return Long.valueOf(jLongValue);
        }
        AudioItem audioItem3 = this.overrideAudioItem;
        return Long.valueOf((audioItem3 == null || (duration2 = audioItem3.getDuration()) == null) ? this.player.getDuration() : duration2.longValue());
    }

    static /* synthetic */ RatingCompat getUserRating$default(NotificationManager notificationManager, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        return notificationManager.getUserRating(num);
    }

    private final RatingCompat getUserRating(Integer index) {
        MediaItem mediaItemAt;
        MediaMetadata mediaMetadata;
        if (index == null) {
            mediaItemAt = this.player.getCurrentMediaItem();
        } else {
            mediaItemAt = this.player.getMediaItemAt(index.intValue());
        }
        return RatingCompat.fromRating((mediaItemAt == null || (mediaMetadata = mediaItemAt.mediaMetadata) == null) ? null : mediaMetadata.userRating);
    }

    public final boolean getShowPlayPauseButton() {
        return this.showPlayPauseButton;
    }

    public final void setShowPlayPauseButton(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showPlayPauseButton$1(this, z, null), 3, null);
    }

    public final boolean getShowStopButton() {
        return this.showStopButton;
    }

    public final void setShowStopButton(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showStopButton$1(this, z, null), 3, null);
    }

    public final boolean getShowForwardButton() {
        return this.showForwardButton;
    }

    public final void setShowForwardButton(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showForwardButton$1(this, z, null), 3, null);
    }

    public final boolean getShowForwardButtonCompact() {
        return this.showForwardButtonCompact;
    }

    public final void setShowForwardButtonCompact(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showForwardButtonCompact$1(this, z, null), 3, null);
    }

    public final boolean getShowRewindButton() {
        return this.showRewindButton;
    }

    public final void setShowRewindButton(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showRewindButton$1(this, z, null), 3, null);
    }

    public final boolean getShowRewindButtonCompact() {
        return this.showRewindButtonCompact;
    }

    public final void setShowRewindButtonCompact(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showRewindButtonCompact$1(this, z, null), 3, null);
    }

    public final boolean getShowNextButton() {
        return this.showNextButton;
    }

    public final void setShowNextButton(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showNextButton$1(this, z, null), 3, null);
    }

    public final boolean getShowNextButtonCompact() {
        return this.showNextButtonCompact;
    }

    public final void setShowNextButtonCompact(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showNextButtonCompact$1(this, z, null), 3, null);
    }

    public final boolean getShowPreviousButton() {
        return this.showPreviousButton;
    }

    public final void setShowPreviousButton(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showPreviousButton$1(this, z, null), 3, null);
    }

    public final boolean getShowPreviousButtonCompact() {
        return this.showPreviousButtonCompact;
    }

    public final void setShowPreviousButtonCompact(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$showPreviousButtonCompact$1(this, z, null), 3, null);
    }

    public final Integer getStopIcon() {
        return this.stopIcon;
    }

    public final void setStopIcon(Integer num) {
        this.stopIcon = num;
    }

    public final Integer getForwardIcon() {
        return this.forwardIcon;
    }

    public final void setForwardIcon(Integer num) {
        this.forwardIcon = num;
    }

    public final Integer getRewindIcon() {
        return this.rewindIcon;
    }

    public final void setRewindIcon(Integer num) {
        this.rewindIcon = num;
    }

    public final void overrideMetadata(AudioItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        setOverrideAudioItem$kotlin_audio_release(item);
    }

    public final MediaMetadataCompat getMediaMetadataCompat() {
        CharSequence charSequence;
        CharSequence charSequence2;
        MediaItem currentMediaItem = this.player.getCurrentMediaItem();
        MediaMetadata mediaMetadata = currentMediaItem != null ? currentMediaItem.mediaMetadata : null;
        MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
        String artist$default = getArtist$default(this, null, 1, null);
        if (artist$default != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, artist$default);
        }
        String title$default = getTitle$default(this, null, 1, null);
        if (title$default != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_TITLE, title$default);
            builder.putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, title$default);
        }
        if (mediaMetadata != null && (charSequence2 = mediaMetadata.subtitle) != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, charSequence2.toString());
        }
        if (mediaMetadata != null && (charSequence = mediaMetadata.description) != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_DESCRIPTION, charSequence.toString());
        }
        String albumTitle$default = getAlbumTitle$default(this, null, 1, null);
        if (albumTitle$default != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM, albumTitle$default);
        }
        String genre$default = getGenre$default(this, null, 1, null);
        if (genre$default != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_GENRE, genre$default);
        }
        Long duration$default = getDuration$default(this, null, 1, null);
        if (duration$default != null) {
            builder.putLong(MediaMetadataCompat.METADATA_KEY_DURATION, duration$default.longValue());
        }
        String artworkUrl$default = getArtworkUrl$default(this, null, 1, null);
        if (artworkUrl$default != null) {
            builder.putString(MediaMetadataCompat.METADATA_KEY_ART_URI, artworkUrl$default);
        }
        Bitmap cachedArtworkBitmap$default = getCachedArtworkBitmap$default(this, null, 1, null);
        if (cachedArtworkBitmap$default != null) {
            builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, cachedArtworkBitmap$default);
            builder.putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, cachedArtworkBitmap$default);
        }
        RatingCompat userRating$default = getUserRating$default(this, null, 1, null);
        if (userRating$default != null) {
            builder.putRating(MediaMetadataCompat.METADATA_KEY_RATING, userRating$default);
        }
        MediaMetadataCompat mediaMetadataCompatBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(mediaMetadataCompatBuild, "Builder().apply {\n      …      }\n        }.build()");
        return mediaMetadataCompatBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationCompat.Action createNotificationAction(int drawable, String action, int instanceId) {
        Intent intent = new Intent(action).setPackage(this.context.getPackageName());
        Intrinsics.checkNotNullExpressionValue(intent, "Intent(action).setPackage(context.packageName)");
        NotificationCompat.Action actionBuild = new NotificationCompat.Action.Builder(drawable, action, PendingIntent.getBroadcast(this.context, instanceId, intent, 335544320)).build();
        Intrinsics.checkNotNullExpressionValue(actionBuild, "Builder(drawable, action, pendingIntent).build()");
        return actionBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handlePlayerAction(String action) {
        int iHashCode = action.hashCode();
        if (iHashCode == -934318917) {
            if (action.equals(REWIND)) {
                this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.REWIND.INSTANCE);
            }
        } else if (iHashCode == -677145915) {
            if (action.equals(FORWARD)) {
                this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.FORWARD.INSTANCE);
            }
        } else if (iHashCode == 3540994 && action.equals(STOP)) {
            this.playerEventHolder.updateOnPlayerActionTriggeredExternally$kotlin_audio_release(MediaSessionCallback.STOP.INSTANCE);
        }
    }

    /* compiled from: NotificationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.kotlinaudio.notification.NotificationManager$invalidate$1", f = "NotificationManager.kt", i = {}, l = {503}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.kotlinaudio.notification.NotificationManager$invalidate$1, reason: invalid class name and case insensitive filesystem */
    static final class C01051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01051(Continuation<? super C01051> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationManager.this.new C01051(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01051) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PlayerNotificationManager playerNotificationManager = NotificationManager.this.internalNotificationManager;
                if (playerNotificationManager != null) {
                    playerNotificationManager.invalidate();
                }
                NotificationManager.this.mediaSessionConnector.invalidateMediaSessionQueue();
                NotificationManager.this.mediaSessionConnector.invalidateMediaSessionMetadata();
                this.label = 1;
                if (DelayKt.delay(300L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            boolean z = NotificationManager.this.invalidateThrottleCount > 1;
            NotificationManager.this.invalidateThrottleCount = 0;
            if (z) {
                NotificationManager.this.invalidate();
            }
            return Unit.INSTANCE;
        }
    }

    public final void invalidate() {
        int i = this.invalidateThrottleCount;
        this.invalidateThrottleCount = i + 1;
        if (i == 0) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01051(null), 3, null);
        }
    }

    /* compiled from: NotificationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.kotlinaudio.notification.NotificationManager$createNotification$1", f = "NotificationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.kotlinaudio.notification.NotificationManager$createNotification$1, reason: invalid class name and case insensitive filesystem */
    static final class C01041 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ NotificationConfig $config;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01041(NotificationConfig notificationConfig, Continuation<? super C01041> continuation) {
            super(2, continuation);
            this.$config = notificationConfig;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationManager.this.new C01041(this.$config, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01041) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Integer icon;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            if (NotificationManager.this.isNotificationButtonsChanged(this.$config.getButtons())) {
                NotificationManager.this.hideNotification();
            }
            Set set = NotificationManager.this.buttons;
            NotificationConfig notificationConfig = this.$config;
            set.clear();
            set.addAll(notificationConfig.getButtons());
            NotificationManager.this.setStopIcon(null);
            NotificationManager.this.setForwardIcon(null);
            NotificationManager.this.setRewindIcon(null);
            NotificationManager.this.updateMediaSessionPlaybackActions();
            NotificationManager.this.pendingIntent = this.$config.getPendingIntent();
            NotificationManager.this.setShowPlayPauseButton(false);
            NotificationManager.this.setShowForwardButton(false);
            NotificationManager.this.setShowRewindButton(false);
            NotificationManager.this.setShowNextButton(false);
            NotificationManager.this.setShowPreviousButton(false);
            NotificationManager.this.setShowStopButton(false);
            if (NotificationManager.this.internalNotificationManager == null) {
                NotificationManager notificationManager = NotificationManager.this;
                PlayerNotificationManager.Builder builder = new PlayerNotificationManager.Builder(NotificationManager.this.context, 1, NotificationManager.CHANNEL_ID);
                NotificationManager notificationManager2 = NotificationManager.this;
                builder.setChannelNameResourceId(R.string.playback_channel_name);
                builder.setMediaDescriptionAdapter(notificationManager2.descriptionAdapter);
                builder.setCustomActionReceiver(notificationManager2.customActionReceiver);
                builder.setNotificationListener(notificationManager2);
                for (NotificationButton notificationButton : notificationManager2.buttons) {
                    if (notificationButton != null) {
                        if (notificationButton instanceof NotificationButton.PLAY_PAUSE) {
                            NotificationButton.PLAY_PAUSE play_pause = (NotificationButton.PLAY_PAUSE) notificationButton;
                            Integer playIcon = play_pause.getPlayIcon();
                            if (playIcon != null) {
                                builder.setPlayActionIconResourceId(playIcon.intValue());
                            }
                            Integer pauseIcon = play_pause.getPauseIcon();
                            if (pauseIcon != null) {
                                builder.setPauseActionIconResourceId(pauseIcon.intValue());
                            }
                        } else if (notificationButton instanceof NotificationButton.STOP) {
                            Integer icon2 = ((NotificationButton.STOP) notificationButton).getIcon();
                            if (icon2 != null) {
                                builder.setStopActionIconResourceId(icon2.intValue());
                            }
                        } else if (notificationButton instanceof NotificationButton.FORWARD) {
                            Integer icon3 = ((NotificationButton.FORWARD) notificationButton).getIcon();
                            if (icon3 != null) {
                                builder.setFastForwardActionIconResourceId(icon3.intValue());
                            }
                        } else if (notificationButton instanceof NotificationButton.BACKWARD) {
                            Integer icon4 = ((NotificationButton.BACKWARD) notificationButton).getIcon();
                            if (icon4 != null) {
                                builder.setRewindActionIconResourceId(icon4.intValue());
                            }
                        } else if (notificationButton instanceof NotificationButton.NEXT) {
                            Integer icon5 = ((NotificationButton.NEXT) notificationButton).getIcon();
                            if (icon5 != null) {
                                builder.setNextActionIconResourceId(icon5.intValue());
                            }
                        } else if ((notificationButton instanceof NotificationButton.PREVIOUS) && (icon = ((NotificationButton.PREVIOUS) notificationButton).getIcon()) != null) {
                            builder.setPreviousActionIconResourceId(icon.intValue());
                        }
                    }
                }
                PlayerNotificationManager playerNotificationManagerBuild = builder.build();
                NotificationManager notificationManager3 = NotificationManager.this;
                playerNotificationManagerBuild.setMediaSessionToken(notificationManager3.mediaSession.getSessionToken());
                playerNotificationManagerBuild.setPlayer(notificationManager3.player);
                notificationManager.internalNotificationManager = playerNotificationManagerBuild;
            }
            NotificationManager.this.setupInternalNotificationManager(this.$config);
            return Unit.INSTANCE;
        }
    }

    public final Job createNotification(NotificationConfig config) {
        Intrinsics.checkNotNullParameter(config, "config");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01041(config, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isNotificationButtonsChanged(List<? extends NotificationButton> newButtons) {
        boolean zAreEqual;
        boolean z;
        List listFilterNotNull = CollectionsKt.filterNotNull(this.buttons);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(listFilterNotNull, 10)), 16));
        for (Object obj : listFilterNotNull) {
            linkedHashMap.put(Reflection.getOrCreateKotlinClass(((NotificationButton) obj).getClass()), obj);
        }
        List<? extends NotificationButton> list = newButtons;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (NotificationButton notificationButton : list) {
            if (notificationButton instanceof NotificationButton.PLAY_PAUSE) {
                Object obj2 = linkedHashMap.get(Reflection.getOrCreateKotlinClass(NotificationButton.PLAY_PAUSE.class));
                NotificationButton.PLAY_PAUSE play_pause = obj2 instanceof NotificationButton.PLAY_PAUSE ? (NotificationButton.PLAY_PAUSE) obj2 : null;
                NotificationButton.PLAY_PAUSE play_pause2 = (NotificationButton.PLAY_PAUSE) notificationButton;
                if (Intrinsics.areEqual(play_pause2.getPauseIcon(), play_pause != null ? play_pause.getPauseIcon() : null)) {
                    if (Intrinsics.areEqual(play_pause2.getPlayIcon(), play_pause != null ? play_pause.getPlayIcon() : null)) {
                        z = false;
                    }
                }
                z = true;
            } else {
                if (notificationButton instanceof NotificationButton.STOP) {
                    Object obj3 = linkedHashMap.get(Reflection.getOrCreateKotlinClass(NotificationButton.STOP.class));
                    NotificationButton.STOP stop = obj3 instanceof NotificationButton.STOP ? (NotificationButton.STOP) obj3 : null;
                    zAreEqual = Intrinsics.areEqual(((NotificationButton.STOP) notificationButton).getIcon(), stop != null ? stop.getIcon() : null);
                } else if (notificationButton instanceof NotificationButton.FORWARD) {
                    Object obj4 = linkedHashMap.get(Reflection.getOrCreateKotlinClass(NotificationButton.FORWARD.class));
                    NotificationButton.FORWARD forward = obj4 instanceof NotificationButton.FORWARD ? (NotificationButton.FORWARD) obj4 : null;
                    zAreEqual = Intrinsics.areEqual(((NotificationButton.FORWARD) notificationButton).getIcon(), forward != null ? forward.getIcon() : null);
                } else if (notificationButton instanceof NotificationButton.BACKWARD) {
                    Object obj5 = linkedHashMap.get(Reflection.getOrCreateKotlinClass(NotificationButton.BACKWARD.class));
                    NotificationButton.BACKWARD backward = obj5 instanceof NotificationButton.BACKWARD ? (NotificationButton.BACKWARD) obj5 : null;
                    zAreEqual = Intrinsics.areEqual(((NotificationButton.BACKWARD) notificationButton).getIcon(), backward != null ? backward.getIcon() : null);
                } else if (notificationButton instanceof NotificationButton.NEXT) {
                    Object obj6 = linkedHashMap.get(Reflection.getOrCreateKotlinClass(NotificationButton.NEXT.class));
                    NotificationButton.NEXT next = obj6 instanceof NotificationButton.NEXT ? (NotificationButton.NEXT) obj6 : null;
                    zAreEqual = Intrinsics.areEqual(((NotificationButton.NEXT) notificationButton).getIcon(), next != null ? next.getIcon() : null);
                } else {
                    if (notificationButton instanceof NotificationButton.PREVIOUS) {
                        Object obj7 = linkedHashMap.get(Reflection.getOrCreateKotlinClass(NotificationButton.PREVIOUS.class));
                        NotificationButton.PREVIOUS previous = obj7 instanceof NotificationButton.PREVIOUS ? (NotificationButton.PREVIOUS) obj7 : null;
                        zAreEqual = Intrinsics.areEqual(((NotificationButton.PREVIOUS) notificationButton).getIcon(), previous != null ? previous.getIcon() : null);
                    }
                    z = false;
                }
                z = !zAreEqual;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateMediaSessionPlaybackActions() {
        MediaSessionConnector.CustomActionProvider customActionProviderCreateMediaSessionAction;
        long j;
        MediaSessionConnector mediaSessionConnector = this.mediaSessionConnector;
        long j2 = 6553600;
        for (NotificationButton notificationButton : this.buttons) {
            if (notificationButton instanceof NotificationButton.PLAY_PAUSE) {
                j = 6;
            } else if (notificationButton instanceof NotificationButton.BACKWARD) {
                Integer icon = ((NotificationButton.BACKWARD) notificationButton).getIcon();
                if (icon == null) {
                    icon = this.rewindIcon;
                }
                this.rewindIcon = icon;
                j = 8;
            } else if (notificationButton instanceof NotificationButton.FORWARD) {
                Integer icon2 = ((NotificationButton.FORWARD) notificationButton).getIcon();
                if (icon2 == null) {
                    icon2 = this.forwardIcon;
                }
                this.forwardIcon = icon2;
                j = 64;
            } else if (notificationButton instanceof NotificationButton.SEEK_TO) {
                j = 256;
            } else if (notificationButton instanceof NotificationButton.STOP) {
                Integer icon3 = ((NotificationButton.STOP) notificationButton).getIcon();
                if (icon3 == null) {
                    icon3 = this.stopIcon;
                }
                this.stopIcon = icon3;
                j = 1;
            } else {
                j = 0;
            }
            j2 |= j;
        }
        mediaSessionConnector.setEnabledPlaybackActions(j2);
        if (needsCustomActionsToAddMissingButtons) {
            List<NotificationButton> listSortedWith = CollectionsKt.sortedWith(this.buttons, new Comparator() { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager$updateMediaSessionPlaybackActions$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    int i;
                    NotificationButton notificationButton2 = (NotificationButton) t;
                    int i2 = 3;
                    if (notificationButton2 instanceof NotificationButton.BACKWARD) {
                        i = 1;
                    } else if (notificationButton2 instanceof NotificationButton.FORWARD) {
                        i = 2;
                    } else {
                        i = notificationButton2 instanceof NotificationButton.STOP ? 3 : 4;
                    }
                    Integer numValueOf = Integer.valueOf(i);
                    NotificationButton notificationButton3 = (NotificationButton) t2;
                    if (notificationButton3 instanceof NotificationButton.BACKWARD) {
                        i2 = 1;
                    } else if (notificationButton3 instanceof NotificationButton.FORWARD) {
                        i2 = 2;
                    } else if (!(notificationButton3 instanceof NotificationButton.STOP)) {
                        i2 = 4;
                    }
                    return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(i2));
                }
            });
            ArrayList arrayList = new ArrayList();
            for (NotificationButton notificationButton2 : listSortedWith) {
                if (notificationButton2 instanceof NotificationButton.BACKWARD) {
                    Integer num = this.rewindIcon;
                    customActionProviderCreateMediaSessionAction = createMediaSessionAction(num != null ? num.intValue() : DEFAULT_REWIND_ICON, REWIND);
                } else if (notificationButton2 instanceof NotificationButton.FORWARD) {
                    Integer num2 = this.forwardIcon;
                    customActionProviderCreateMediaSessionAction = createMediaSessionAction(num2 != null ? num2.intValue() : DEFAULT_FORWARD_ICON, FORWARD);
                } else if (notificationButton2 instanceof NotificationButton.STOP) {
                    Integer num3 = this.stopIcon;
                    customActionProviderCreateMediaSessionAction = createMediaSessionAction(num3 != null ? num3.intValue() : DEFAULT_STOP_ICON, STOP);
                } else {
                    customActionProviderCreateMediaSessionAction = null;
                }
                if (customActionProviderCreateMediaSessionAction != null) {
                    arrayList.add(customActionProviderCreateMediaSessionAction);
                }
            }
            MediaSessionConnector mediaSessionConnector2 = this.mediaSessionConnector;
            Object[] array = arrayList.toArray(new MediaSessionConnector.CustomActionProvider[0]);
            Intrinsics.checkNotNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            MediaSessionConnector.CustomActionProvider[] customActionProviderArr = (MediaSessionConnector.CustomActionProvider[]) array;
            mediaSessionConnector2.setCustomActionProviders((MediaSessionConnector.CustomActionProvider[]) Arrays.copyOf(customActionProviderArr, customActionProviderArr.length));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupInternalNotificationManager(NotificationConfig config) {
        PlayerNotificationManager playerNotificationManager = this.internalNotificationManager;
        if (playerNotificationManager != null) {
            Integer accentColor = config.getAccentColor();
            playerNotificationManager.setColor(accentColor != null ? accentColor.intValue() : 0);
            Integer smallIcon = config.getSmallIcon();
            if (smallIcon != null) {
                playerNotificationManager.setSmallIcon(smallIcon.intValue());
            }
            for (NotificationButton notificationButton : this.buttons) {
                if (notificationButton != null) {
                    if (notificationButton instanceof NotificationButton.PLAY_PAUSE) {
                        setShowPlayPauseButton(true);
                    } else if (notificationButton instanceof NotificationButton.STOP) {
                        setShowStopButton(true);
                    } else if (notificationButton instanceof NotificationButton.FORWARD) {
                        setShowForwardButton(true);
                        setShowForwardButtonCompact(((NotificationButton.FORWARD) notificationButton).getIsCompact());
                    } else if (notificationButton instanceof NotificationButton.BACKWARD) {
                        setShowRewindButton(true);
                        setShowRewindButtonCompact(((NotificationButton.BACKWARD) notificationButton).getIsCompact());
                    } else if (notificationButton instanceof NotificationButton.NEXT) {
                        setShowNextButton(true);
                        setShowNextButtonCompact(((NotificationButton.NEXT) notificationButton).getIsCompact());
                    } else if (notificationButton instanceof NotificationButton.PREVIOUS) {
                        setShowPreviousButton(true);
                        setShowPreviousButtonCompact(((NotificationButton.PREVIOUS) notificationButton).getIsCompact());
                    }
                }
            }
        }
    }

    public final void hideNotification() {
        PlayerNotificationManager playerNotificationManager = this.internalNotificationManager;
        if (playerNotificationManager != null) {
            playerNotificationManager.setPlayer(null);
        }
        this.internalNotificationManager = null;
        invalidate();
    }

    /* compiled from: NotificationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.kotlinaudio.notification.NotificationManager$onNotificationPosted$1", f = "NotificationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.kotlinaudio.notification.NotificationManager$onNotificationPosted$1, reason: invalid class name and case insensitive filesystem */
    static final class C01071 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Notification $notification;
        final /* synthetic */ int $notificationId;
        final /* synthetic */ boolean $ongoing;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01071(int i, Notification notification, boolean z, Continuation<? super C01071> continuation) {
            super(2, continuation);
            this.$notificationId = i;
            this.$notification = notification;
            this.$ongoing = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationManager.this.new C01071(this.$notificationId, this.$notification, this.$ongoing, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01071) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            NotificationManager.this.getEvent().updateNotificationState$kotlin_audio_release(new NotificationState.POSTED(this.$notificationId, this.$notification, this.$ongoing));
            return Unit.INSTANCE;
        }
    }

    @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.NotificationListener
    public void onNotificationPosted(int notificationId, Notification notification, boolean ongoing) {
        Intrinsics.checkNotNullParameter(notification, "notification");
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01071(notificationId, notification, ongoing, null), 3, null);
    }

    /* compiled from: NotificationManager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 7, 1}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.kotlinaudio.notification.NotificationManager$onNotificationCancelled$1", f = "NotificationManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.kotlinaudio.notification.NotificationManager$onNotificationCancelled$1, reason: invalid class name and case insensitive filesystem */
    static final class C01061 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $notificationId;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01061(int i, Continuation<? super C01061> continuation) {
            super(2, continuation);
            this.$notificationId = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return NotificationManager.this.new C01061(this.$notificationId, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01061) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            NotificationManager.this.getEvent().updateNotificationState$kotlin_audio_release(new NotificationState.CANCELLED(this.$notificationId));
            return Unit.INSTANCE;
        }
    }

    @Override // com.google.android.exoplayer2.ui.PlayerNotificationManager.NotificationListener
    public void onNotificationCancelled(int notificationId, boolean dismissedByUser) {
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01061(notificationId, null), 3, null);
    }

    public final Job destroy$kotlin_audio_release() {
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new NotificationManager$destroy$1(this, null), 3, null);
    }

    private final MediaSessionConnector.CustomActionProvider createMediaSessionAction(final int drawableRes, final String actionName) {
        return new MediaSessionConnector.CustomActionProvider() { // from class: com.doublesymmetry.kotlinaudio.notification.NotificationManager.createMediaSessionAction.1
            @Override // com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.CustomActionProvider
            public PlaybackStateCompat.CustomAction getCustomAction(Player player) {
                Intrinsics.checkNotNullParameter(player, "player");
                String str = actionName;
                return new PlaybackStateCompat.CustomAction.Builder(str, str, drawableRes).build();
            }

            @Override // com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector.CustomActionProvider
            public void onCustomAction(Player player, String action, Bundle extras) {
                Intrinsics.checkNotNullParameter(player, "player");
                Intrinsics.checkNotNullParameter(action, "action");
                this.handlePlayerAction(action);
            }
        };
    }

    static {
        needsCustomActionsToAddMissingButtons = Build.VERSION.SDK_INT >= 33;
        DEFAULT_STOP_ICON = com.google.android.exoplayer2.ui.R.drawable.exo_notification_stop;
        DEFAULT_REWIND_ICON = com.google.android.exoplayer2.ui.R.drawable.exo_notification_rewind;
        DEFAULT_FORWARD_ICON = com.google.android.exoplayer2.ui.R.drawable.exo_notification_fastforward;
    }
}
