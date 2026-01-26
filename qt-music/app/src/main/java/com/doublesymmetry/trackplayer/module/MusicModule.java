package com.doublesymmetry.trackplayer.module;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.doublesymmetry.kotlinaudio.models.Capability;
import com.doublesymmetry.kotlinaudio.models.RepeatMode;
import com.doublesymmetry.trackplayer.extensions.NumberExt;
import com.doublesymmetry.trackplayer.model.State;
import com.doublesymmetry.trackplayer.model.Track;
import com.doublesymmetry.trackplayer.service.MusicService;
import com.doublesymmetry.trackplayer.utils.AppForegroundTracker;
import com.doublesymmetry.trackplayer.utils.RejectionException;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.umeng.analytics.pro.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.OnErrorAction;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import timber.log.Timber;

/* compiled from: MusicModule.kt */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0018\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\"\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\fH\u0002J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0014\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 H\u0016J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\b\u0010$\u001a\u00020!H\u0017J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010'\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010(\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010)\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010+\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010.\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\b\u0010/\u001a\u000200H\u0016J\u0010\u00101\u001a\u0002002\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u001a\u00102\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u0001032\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J \u00104\u001a\u00020\u00122\u0006\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u00107\u001a\u0002002\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010<\u001a\u0002002\u0006\u00108\u001a\u000209H\u0016J\u0010\u0010=\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010>\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00190@2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0002J\u001c\u0010A\u001a\u0002002\u0006\u0010\u0017\u001a\u00020\u000e2\n\u0010B\u001a\u00060Cj\u0002`DH\u0002J\u001a\u0010E\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010F\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010G\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010H\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010I\u001a\u00020\u00122\u0006\u0010J\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010L\u001a\u00020\u00122\u0006\u0010M\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010N\u001a\u00020\u00122\u0006\u0010O\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u001a\u0010P\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010Q\u001a\u00020\u00122\u0006\u0010R\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010S\u001a\u00020\u00122\u0006\u0010T\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010U\u001a\u00020\u00122\u0006\u0010V\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u001a\u0010W\u001a\u0002002\b\u0010\u0013\u001a\u0004\u0018\u0001032\u0006\u0010X\u001a\u00020\u000eH\u0007J \u0010Y\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u00162\u0006\u0010Z\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010[\u001a\u00020\u00122\u0006\u0010Z\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0018\u0010\\\u001a\u00020\u00122\u0006\u0010Z\u001a\u00020K2\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010]\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\"\u0010^\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u00162\b\u0010_\u001a\u0004\u0018\u0001032\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u001a\u0010`\u001a\u00020\u00122\b\u0010_\u001a\u0004\u0018\u0001032\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u001a\u0010a\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u0001032\u0006\u0010\u0017\u001a\u00020\u000eH\u0007J\u0010\u0010b\u001a\u00020\b2\u0006\u0010X\u001a\u00020\u000eH\u0002R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006c"}, d2 = {"Lcom/doublesymmetry/trackplayer/module/MusicModule;", "Lcom/facebook/react/bridge/ReactContextBaseJavaModule;", "Landroid/content/ServiceConnection;", "reactContext", "Lcom/facebook/react/bridge/ReactApplicationContext;", "(Lcom/facebook/react/bridge/ReactApplicationContext;)V", f.X, "isServiceBound", "", "musicService", "Lcom/doublesymmetry/trackplayer/service/MusicService;", "playerOptions", "Landroid/os/Bundle;", "playerSetUpPromise", "Lcom/facebook/react/bridge/Promise;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "add", "Lkotlinx/coroutines/Job;", "data", "Lcom/facebook/react/bridge/ReadableArray;", "insertBeforeIndex", "", "callback", "bundleToTrack", "Lcom/doublesymmetry/trackplayer/model/Track;", "bundle", "clearNowPlayingMetadata", "getActiveTrack", "getActiveTrackIndex", "getBufferedPosition", "getConstants", "", "", "", "getDuration", "getName", "getPlayWhenReady", "getPlaybackState", "getPosition", "getProgress", "getQueue", "getRate", "getRepeatMode", "getTrack", "index", "getVolume", "initialize", "", "isServiceRunning", "load", "Lcom/facebook/react/bridge/ReadableMap;", "move", "fromIndex", "toIndex", "onServiceConnected", "name", "Landroid/content/ComponentName;", NotificationCompat.CATEGORY_SERVICE, "Landroid/os/IBinder;", "onServiceDisconnected", "pause", "play", "readableArrayToTrackList", "", "rejectWithException", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "remove", "removeUpcomingTracks", "reset", "retry", "seekBy", "offset", "", "seekTo", "seconds", "setPlayWhenReady", "playWhenReady", "setQueue", "setRate", "rate", "setRepeatMode", "mode", "setVolume", "volume", "setupPlayer", BaseJavaModule.METHOD_TYPE_PROMISE, "skip", "initialTime", "skipToNext", "skipToPrevious", "stop", "updateMetadataForTrack", "map", "updateNowPlayingMetadata", "updateOptions", "verifyServiceBoundOrReject", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final class MusicModule extends ReactContextBaseJavaModule implements ServiceConnection {
    private final ReactApplicationContext context;
    private boolean isServiceBound;
    private MusicService musicService;
    private Bundle playerOptions;
    private Promise playerSetUpPromise;
    private final CoroutineScope scope;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MusicModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Intrinsics.checkNotNullParameter(reactContext, "reactContext");
        this.scope = CoroutineScopeKt.MainScope();
        this.context = reactContext;
    }

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return "TrackPlayerModule";
    }

    @Override // com.facebook.react.bridge.BaseJavaModule, com.facebook.react.bridge.NativeModule
    public void initialize() {
        Timber.INSTANCE.plant(new Timber.DebugTree());
        AppForegroundTracker.INSTANCE.start();
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$onServiceConnected$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$onServiceConnected$1, reason: invalid class name and case insensitive filesystem */
    static final class C01241 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ IBinder $service;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01241(IBinder iBinder, Continuation<? super C01241> continuation) {
            super(2, continuation);
            this.$service = iBinder;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01241(this.$service, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01241) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.musicService == null) {
                    IBinder iBinder = this.$service;
                    Intrinsics.checkNotNull(iBinder, "null cannot be cast to non-null type com.doublesymmetry.trackplayer.service.MusicService.MusicBinder");
                    MusicModule.this.musicService = ((MusicService.MusicBinder) iBinder).getService();
                    MusicService musicService = MusicModule.this.musicService;
                    if (musicService == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService = null;
                    }
                    musicService.setupPlayer(MusicModule.this.playerOptions);
                    Promise promise = MusicModule.this.playerSetUpPromise;
                    if (promise != null) {
                        promise.resolve(null);
                    }
                }
                MusicModule.this.isServiceBound = true;
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(service, "service");
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01241(service, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$onServiceDisconnected$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$onServiceDisconnected$1, reason: invalid class name and case insensitive filesystem */
    static final class C01251 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C01251(Continuation<? super C01251> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01251(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01251) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                MusicModule.this.isServiceBound = false;
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        Intrinsics.checkNotNullParameter(name, "name");
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01251(null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean verifyServiceBoundOrReject(Promise promise) {
        if (this.isServiceBound) {
            return false;
        }
        promise.reject("player_not_initialized", "The player is not initialized. Call setupPlayer first.");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Track bundleToTrack(Bundle bundle) {
        ReactApplicationContext reactApplicationContext = this.context;
        MusicService musicService = this.musicService;
        if (musicService == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicService");
            musicService = null;
        }
        return new Track(reactApplicationContext, bundle, musicService.getRatingType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rejectWithException(Promise callback, Exception exception) {
        if (exception instanceof RejectionException) {
            callback.reject(((RejectionException) exception).getCode(), exception);
        } else {
            callback.reject("runtime_exception", exception);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Track> readableArrayToTrackList(ReadableArray data) throws RejectionException {
        ArrayList list = Arguments.toList(data);
        if (list == null) {
            throw new RejectionException("invalid_parameter", "Was not given an array of tracks");
        }
        ArrayList arrayList = list;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (Object obj : arrayList) {
            if (!(obj instanceof Bundle)) {
                throw new RejectionException("invalid_track_object", "Track was not a dictionary type");
            }
            arrayList2.add(bundleToTrack((Bundle) obj));
        }
        return CollectionsKt.toMutableList((Collection) arrayList2);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("CAPABILITY_PLAY", Integer.valueOf(Capability.PLAY.ordinal()));
        map.put("CAPABILITY_PLAY_FROM_ID", Integer.valueOf(Capability.PLAY_FROM_ID.ordinal()));
        map.put("CAPABILITY_PLAY_FROM_SEARCH", Integer.valueOf(Capability.PLAY_FROM_SEARCH.ordinal()));
        map.put("CAPABILITY_PAUSE", Integer.valueOf(Capability.PAUSE.ordinal()));
        map.put("CAPABILITY_STOP", Integer.valueOf(Capability.STOP.ordinal()));
        map.put("CAPABILITY_SEEK_TO", Integer.valueOf(Capability.SEEK_TO.ordinal()));
        map.put("CAPABILITY_SKIP", Integer.valueOf(OnErrorAction.SKIP.ordinal()));
        map.put("CAPABILITY_SKIP_TO_NEXT", Integer.valueOf(Capability.SKIP_TO_NEXT.ordinal()));
        map.put("CAPABILITY_SKIP_TO_PREVIOUS", Integer.valueOf(Capability.SKIP_TO_PREVIOUS.ordinal()));
        map.put("CAPABILITY_SET_RATING", Integer.valueOf(Capability.SET_RATING.ordinal()));
        map.put("CAPABILITY_JUMP_FORWARD", Integer.valueOf(Capability.JUMP_FORWARD.ordinal()));
        map.put("CAPABILITY_JUMP_BACKWARD", Integer.valueOf(Capability.JUMP_BACKWARD.ordinal()));
        map.put("STATE_NONE", State.None.getState());
        map.put("STATE_READY", State.Ready.getState());
        map.put("STATE_PLAYING", State.Playing.getState());
        map.put("STATE_PAUSED", State.Paused.getState());
        map.put("STATE_STOPPED", State.Stopped.getState());
        map.put("STATE_BUFFERING", State.Buffering.getState());
        map.put("STATE_LOADING", State.Loading.getState());
        map.put("RATING_HEART", 1);
        map.put("RATING_THUMBS_UP_DOWN", 2);
        map.put("RATING_3_STARS", 3);
        map.put("RATING_4_STARS", 4);
        map.put("RATING_5_STARS", 5);
        map.put("RATING_PERCENTAGE", 6);
        map.put("REPEAT_OFF", 0);
        map.put("REPEAT_TRACK", 1);
        map.put("REPEAT_QUEUE", 2);
        return map;
    }

    @ReactMethod
    public final void setupPlayer(ReadableMap data, Promise promise) {
        int milliseconds;
        int milliseconds2;
        int milliseconds3;
        Intrinsics.checkNotNullParameter(promise, "promise");
        if (this.isServiceBound) {
            promise.reject("player_already_initialized", "The player has already been initialized via setupPlayer.");
            return;
        }
        if (Build.VERSION.SDK_INT >= 26 && AppForegroundTracker.INSTANCE.getBackgrounded()) {
            promise.reject("android_cannot_setup_player_in_background", "On Android the app must be in the foreground when setting up the player.");
            return;
        }
        Bundle bundle = Arguments.toBundle(data);
        int milliseconds4 = 50000;
        if (bundle != null) {
            milliseconds = (int) NumberExt.INSTANCE.toMilliseconds(Double.valueOf(bundle.getDouble(MusicService.MIN_BUFFER_KEY)));
        } else {
            milliseconds = 50000;
        }
        if (bundle != null) {
            milliseconds4 = (int) NumberExt.INSTANCE.toMilliseconds(Double.valueOf(bundle.getDouble(MusicService.MAX_BUFFER_KEY)));
        }
        if (bundle != null) {
            milliseconds2 = (int) NumberExt.INSTANCE.toMilliseconds(Double.valueOf(bundle.getDouble(MusicService.PLAY_BUFFER_KEY)));
        } else {
            milliseconds2 = 2500;
        }
        if (bundle != null) {
            milliseconds3 = (int) NumberExt.INSTANCE.toMilliseconds(Double.valueOf(bundle.getDouble(MusicService.BACK_BUFFER_KEY)));
        } else {
            milliseconds3 = 0;
        }
        if (milliseconds2 < 0) {
            promise.reject("play_buffer_error", "The value for playBuffer should be greater than or equal to zero.");
            return;
        }
        if (milliseconds3 < 0) {
            promise.reject("back_buffer_error", "The value for backBuffer should be greater than or equal to zero.");
            return;
        }
        if (milliseconds < milliseconds2) {
            promise.reject("min_buffer_error", "The value for minBuffer should be greater than or equal to playBuffer.");
            return;
        }
        if (milliseconds4 < milliseconds) {
            promise.reject("min_buffer_error", "The value for maxBuffer should be greater than or equal to minBuffer.");
            return;
        }
        this.playerSetUpPromise = promise;
        this.playerOptions = bundle;
        LocalBroadcastManager.getInstance(this.context).registerReceiver(new MusicEvents(this.context), new IntentFilter(MusicEvents.EVENT_INTENT));
        Intent intent = new Intent(this.context, (Class<?>) MusicService.class);
        if (Build.VERSION.SDK_INT >= 26) {
            this.context.startForegroundService(intent);
        } else {
            this.context.startService(intent);
        }
        this.context.bindService(intent, this, 1);
    }

    @Deprecated(message = "Backwards compatible function from the old android implementation. Should be removed in the next major release.")
    @ReactMethod
    public final void isServiceRunning(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        callback.resolve(Boolean.valueOf(this.isServiceBound));
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$updateOptions$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$updateOptions$1, reason: invalid class name and case insensitive filesystem */
    static final class C01451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ ReadableMap $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01451(Promise promise, ReadableMap readableMap, Continuation<? super C01451> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$data = readableMap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01451(this.$callback, this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Bundle bundle = Arguments.toBundle(this.$data);
                if (bundle != null) {
                    MusicService musicService = MusicModule.this.musicService;
                    if (musicService == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService = null;
                    }
                    musicService.updateOptions(bundle);
                }
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job updateOptions(ReadableMap data, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01451(callback, data, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$add$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$add$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ ReadableArray $data;
        final /* synthetic */ int $insertBeforeIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Promise promise, ReadableArray readableArray, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$data = readableArray;
            this.$insertBeforeIndex = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new AnonymousClass1(this.$callback, this.$data, this.$insertBeforeIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            List<Track> list;
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                try {
                    list = MusicModule.this.readableArrayToTrackList(this.$data);
                    i = this.$insertBeforeIndex;
                } catch (Exception e) {
                    MusicModule.this.rejectWithException(this.$callback, e);
                }
                if (i >= -1) {
                    MusicService musicService = MusicModule.this.musicService;
                    MusicService musicService2 = null;
                    if (musicService == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService = null;
                    }
                    if (i <= musicService.getTracks().size()) {
                        int size = this.$insertBeforeIndex;
                        if (size == -1) {
                            MusicService musicService3 = MusicModule.this.musicService;
                            if (musicService3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("musicService");
                                musicService3 = null;
                            }
                            size = musicService3.getTracks().size();
                        }
                        MusicService musicService4 = MusicModule.this.musicService;
                        if (musicService4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        } else {
                            musicService2 = musicService4;
                        }
                        musicService2.add(list, size);
                        this.$callback.resolve(Boxing.boxInt(size));
                        return Unit.INSTANCE;
                    }
                }
                this.$callback.reject("index_out_of_bounds", "The track index is out of bounds");
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job add(ReadableArray data, int insertBeforeIndex, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new AnonymousClass1(callback, data, insertBeforeIndex, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$load$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$load$1, reason: invalid class name and case insensitive filesystem */
    static final class C01221 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ ReadableMap $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01221(Promise promise, ReadableMap readableMap, Continuation<? super C01221> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$data = readableMap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01221(this.$callback, this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01221) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                ReadableMap readableMap = this.$data;
                if (readableMap == null) {
                    this.$callback.resolve(null);
                    return Unit.INSTANCE;
                }
                Bundle bundle = Arguments.toBundle(readableMap);
                if (bundle != null) {
                    MusicService musicService = MusicModule.this.musicService;
                    if (musicService == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService = null;
                    }
                    musicService.load(MusicModule.this.bundleToTrack(bundle));
                    this.$callback.resolve(null);
                } else {
                    this.$callback.reject("invalid_track_object", "Track was not a dictionary type");
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job load(ReadableMap data, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01221(callback, data, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$move$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$move$1, reason: invalid class name and case insensitive filesystem */
    static final class C01231 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ int $fromIndex;
        final /* synthetic */ int $toIndex;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01231(Promise promise, int i, int i2, Continuation<? super C01231> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$fromIndex = i;
            this.$toIndex = i2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01231(this.$callback, this.$fromIndex, this.$toIndex, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01231) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.move(this.$fromIndex, this.$toIndex);
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job move(int fromIndex, int toIndex, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01231(callback, fromIndex, toIndex, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$remove$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$remove$1, reason: invalid class name and case insensitive filesystem */
    static final class C01281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ ReadableArray $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01281(Promise promise, ReadableArray readableArray, Continuation<? super C01281> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$data = readableArray;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01281(this.$callback, this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01281) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                ArrayList list = Arguments.toList(this.$data);
                if (list != null) {
                    MusicService musicService = MusicModule.this.musicService;
                    if (musicService == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService = null;
                    }
                    int size = musicService.getTracks().size();
                    ArrayList arrayList = new ArrayList();
                    Iterator it = list.iterator();
                    while (it.hasNext()) {
                        Object next = it.next();
                        int iIntValue = next instanceof Integer ? ((Number) next).intValue() : Integer.parseInt(String.valueOf(next));
                        if (iIntValue < 0 || iIntValue >= size) {
                            this.$callback.reject("index_out_of_bounds", "One or more indexes was out of bounds");
                            return Unit.INSTANCE;
                        }
                        arrayList.add(Boxing.boxInt(iIntValue));
                    }
                    MusicService musicService2 = MusicModule.this.musicService;
                    if (musicService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService2 = null;
                    }
                    musicService2.remove(arrayList);
                }
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job remove(ReadableArray data, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01281(callback, data, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$updateMetadataForTrack$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$updateMetadataForTrack$1, reason: invalid class name and case insensitive filesystem */
    static final class C01431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ int $index;
        final /* synthetic */ ReadableMap $map;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01431(Promise promise, int i, ReadableMap readableMap, Continuation<? super C01431> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$index = i;
            this.$map = readableMap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01431(this.$callback, this.$index, this.$map, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r5.label
                if (r0 != 0) goto L93
                kotlin.ResultKt.throwOnFailure(r6)
                com.doublesymmetry.trackplayer.module.MusicModule r6 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.facebook.react.bridge.Promise r0 = r5.$callback
                boolean r6 = com.doublesymmetry.trackplayer.module.MusicModule.access$verifyServiceBoundOrReject(r6, r0)
                if (r6 == 0) goto L17
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L17:
                int r6 = r5.$index
                if (r6 < 0) goto L87
                com.doublesymmetry.trackplayer.module.MusicModule r0 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.doublesymmetry.trackplayer.service.MusicService r0 = com.doublesymmetry.trackplayer.module.MusicModule.access$getMusicService$p(r0)
                java.lang.String r1 = "musicService"
                r2 = 0
                if (r0 != 0) goto L2a
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r0 = r2
            L2a:
                java.util.List r0 = r0.getTracks()
                int r0 = r0.size()
                if (r6 < r0) goto L35
                goto L87
            L35:
                com.doublesymmetry.trackplayer.module.MusicModule r6 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.facebook.react.bridge.ReactApplicationContext r6 = com.doublesymmetry.trackplayer.module.MusicModule.access$getContext$p(r6)
                com.facebook.react.bridge.ReactContext r6 = (com.facebook.react.bridge.ReactContext) r6
                com.doublesymmetry.trackplayer.module.MusicModule r0 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.doublesymmetry.trackplayer.service.MusicService r0 = com.doublesymmetry.trackplayer.module.MusicModule.access$getMusicService$p(r0)
                if (r0 != 0) goto L49
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r0 = r2
            L49:
                java.util.List r0 = r0.getTracks()
                int r3 = r5.$index
                java.lang.Object r0 = r0.get(r3)
                com.doublesymmetry.trackplayer.model.Track r0 = (com.doublesymmetry.trackplayer.model.Track) r0
                android.content.Context r6 = (android.content.Context) r6
                com.facebook.react.bridge.ReadableMap r3 = r5.$map
                android.os.Bundle r3 = com.facebook.react.bridge.Arguments.toBundle(r3)
                com.doublesymmetry.trackplayer.module.MusicModule r4 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.doublesymmetry.trackplayer.service.MusicService r4 = com.doublesymmetry.trackplayer.module.MusicModule.access$getMusicService$p(r4)
                if (r4 != 0) goto L69
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r4 = r2
            L69:
                int r4 = r4.getRatingType()
                r0.setMetadata(r6, r3, r4)
                com.doublesymmetry.trackplayer.module.MusicModule r6 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.doublesymmetry.trackplayer.service.MusicService r6 = com.doublesymmetry.trackplayer.module.MusicModule.access$getMusicService$p(r6)
                if (r6 != 0) goto L7c
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r6 = r2
            L7c:
                int r1 = r5.$index
                r6.updateMetadataForTrack(r1, r0)
                com.facebook.react.bridge.Promise r6 = r5.$callback
                r6.resolve(r2)
                goto L90
            L87:
                com.facebook.react.bridge.Promise r6 = r5.$callback
                java.lang.String r0 = "index_out_of_bounds"
                java.lang.String r1 = "The index is out of bounds"
                r6.reject(r0, r1)
            L90:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L93:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.trackplayer.module.MusicModule.C01431.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @ReactMethod
    public final Job updateMetadataForTrack(int index, ReadableMap map, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01431(callback, index, map, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$updateNowPlayingMetadata$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$updateNowPlayingMetadata$1, reason: invalid class name and case insensitive filesystem */
    static final class C01441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ ReadableMap $map;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01441(Promise promise, ReadableMap readableMap, Continuation<? super C01441> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$map = readableMap;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01441(this.$callback, this.$map, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                if (musicService.getTracks().isEmpty()) {
                    this.$callback.reject("no_current_item", "There is no current item in the player");
                }
                ReactApplicationContext reactApplicationContext = MusicModule.this.context;
                Bundle bundle = Arguments.toBundle(this.$map);
                if (bundle != null) {
                    MusicModule musicModule = MusicModule.this;
                    Track trackBundleToTrack = musicModule.bundleToTrack(bundle);
                    MusicService musicService2 = musicModule.musicService;
                    if (musicService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService2 = null;
                    }
                    musicService2.updateNowPlayingMetadata(trackBundleToTrack);
                }
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job updateNowPlayingMetadata(ReadableMap map, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01441(callback, map, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$clearNowPlayingMetadata$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$clearNowPlayingMetadata$1, reason: invalid class name and case insensitive filesystem */
    static final class C01081 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01081(Promise promise, Continuation<? super C01081> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01081(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01081) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                if (musicService.getTracks().isEmpty()) {
                    this.$callback.reject("no_current_item", "There is no current item in the player");
                }
                MusicService musicService2 = MusicModule.this.musicService;
                if (musicService2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService2 = null;
                }
                musicService2.clearNotificationMetadata();
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job clearNowPlayingMetadata(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01081(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$removeUpcomingTracks$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$removeUpcomingTracks$1, reason: invalid class name and case insensitive filesystem */
    static final class C01291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01291(Promise promise, Continuation<? super C01291> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01291(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01291) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.removeUpcomingTracks();
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job removeUpcomingTracks(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01291(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$skip$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$skip$1, reason: invalid class name and case insensitive filesystem */
    static final class C01391 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ int $index;
        final /* synthetic */ float $initialTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01391(Promise promise, int i, float f, Continuation<? super C01391> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$index = i;
            this.$initialTime = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01391(this.$callback, this.$index, this.$initialTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01391) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.skip(this.$index);
                if (this.$initialTime >= 0.0f) {
                    MusicService musicService2 = MusicModule.this.musicService;
                    if (musicService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService2 = null;
                    }
                    musicService2.seekTo(this.$initialTime);
                }
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job skip(int index, float initialTime, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01391(callback, index, initialTime, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$skipToNext$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$skipToNext$1, reason: invalid class name and case insensitive filesystem */
    static final class C01401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ float $initialTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01401(Promise promise, float f, Continuation<? super C01401> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$initialTime = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01401(this.$callback, this.$initialTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.skipToNext();
                if (this.$initialTime >= 0.0f) {
                    MusicService musicService2 = MusicModule.this.musicService;
                    if (musicService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService2 = null;
                    }
                    musicService2.seekTo(this.$initialTime);
                }
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job skipToNext(float initialTime, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01401(callback, initialTime, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$skipToPrevious$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$skipToPrevious$1, reason: invalid class name and case insensitive filesystem */
    static final class C01411 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ float $initialTime;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01411(Promise promise, float f, Continuation<? super C01411> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$initialTime = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01411(this.$callback, this.$initialTime, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01411) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.skipToPrevious();
                if (this.$initialTime >= 0.0f) {
                    MusicService musicService2 = MusicModule.this.musicService;
                    if (musicService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService2 = null;
                    }
                    musicService2.seekTo(this.$initialTime);
                }
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job skipToPrevious(float initialTime, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01411(callback, initialTime, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$reset$1", f = "MusicModule.kt", i = {}, l = {429}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$reset$1, reason: invalid class name and case insensitive filesystem */
    static final class C01301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01301(Promise promise, Continuation<? super C01301> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01301(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.stop();
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
            MusicService musicService2 = MusicModule.this.musicService;
            if (musicService2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("musicService");
                musicService2 = null;
            }
            musicService2.clear();
            this.$callback.resolve(null);
            return Unit.INSTANCE;
        }
    }

    @ReactMethod
    public final Job reset(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01301(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$play$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$play$1, reason: invalid class name and case insensitive filesystem */
    static final class C01271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01271(Promise promise, Continuation<? super C01271> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01271(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.play();
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job play(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01271(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$pause$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$pause$1, reason: invalid class name and case insensitive filesystem */
    static final class C01261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01261(Promise promise, Continuation<? super C01261> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01261(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.pause();
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job pause(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01261(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$stop$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$stop$1, reason: invalid class name and case insensitive filesystem */
    static final class C01421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01421(Promise promise, Continuation<? super C01421> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01421(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.stop();
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job stop(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01421(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$seekTo$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$seekTo$1, reason: invalid class name and case insensitive filesystem */
    static final class C01331 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ float $seconds;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01331(Promise promise, float f, Continuation<? super C01331> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$seconds = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01331(this.$callback, this.$seconds, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01331) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.seekTo(this.$seconds);
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job seekTo(float seconds, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01331(callback, seconds, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$seekBy$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$seekBy$1, reason: invalid class name and case insensitive filesystem */
    static final class C01321 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ float $offset;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01321(Promise promise, float f, Continuation<? super C01321> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$offset = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01321(this.$callback, this.$offset, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01321) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.seekBy(this.$offset);
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job seekBy(float offset, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01321(callback, offset, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$retry$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$retry$1, reason: invalid class name and case insensitive filesystem */
    static final class C01311 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01311(Promise promise, Continuation<? super C01311> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01311(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01311) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.retry();
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job retry(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01311(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$setVolume$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$setVolume$1, reason: invalid class name and case insensitive filesystem */
    static final class C01381 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ float $volume;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01381(Promise promise, float f, Continuation<? super C01381> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$volume = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01381(this.$callback, this.$volume, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01381) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.setVolume(this.$volume);
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job setVolume(float volume, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01381(callback, volume, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getVolume$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getVolume$1, reason: invalid class name and case insensitive filesystem */
    static final class C01211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01211(Promise promise, Continuation<? super C01211> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01211(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxFloat(musicService.getVolume()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getVolume(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01211(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$setRate$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$setRate$1, reason: invalid class name and case insensitive filesystem */
    static final class C01361 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ float $rate;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01361(Promise promise, float f, Continuation<? super C01361> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$rate = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01361(this.$callback, this.$rate, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01361) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.setRate(this.$rate);
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job setRate(float rate, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01361(callback, rate, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getRate$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getRate$1, reason: invalid class name and case insensitive filesystem */
    static final class C01181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01181(Promise promise, Continuation<? super C01181> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01181(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxFloat(musicService.getRate()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getRate(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01181(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$setRepeatMode$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$setRepeatMode$1, reason: invalid class name and case insensitive filesystem */
    static final class C01371 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ int $mode;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01371(Promise promise, int i, Continuation<? super C01371> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$mode = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01371(this.$callback, this.$mode, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01371) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.setRepeatMode(RepeatMode.INSTANCE.fromOrdinal(this.$mode));
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job setRepeatMode(int mode, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01371(callback, mode, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getRepeatMode$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getRepeatMode$1, reason: invalid class name and case insensitive filesystem */
    static final class C01191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01191(Promise promise, Continuation<? super C01191> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01191(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01191) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxInt(musicService.getRepeatMode().ordinal()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getRepeatMode(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01191(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$setPlayWhenReady$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$setPlayWhenReady$1, reason: invalid class name and case insensitive filesystem */
    static final class C01341 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ boolean $playWhenReady;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01341(Promise promise, boolean z, Continuation<? super C01341> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$playWhenReady = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01341(this.$callback, this.$playWhenReady, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01341) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                musicService.setPlayWhenReady(this.$playWhenReady);
                this.$callback.resolve(null);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job setPlayWhenReady(boolean playWhenReady, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01341(callback, playWhenReady, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getPlayWhenReady$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getPlayWhenReady$1, reason: invalid class name and case insensitive filesystem */
    static final class C01131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01131(Promise promise, Continuation<? super C01131> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01131(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxBoolean(musicService.getPlayWhenReady()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getPlayWhenReady(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01131(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getTrack$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getTrack$1, reason: invalid class name and case insensitive filesystem */
    static final class C01201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ int $index;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01201(Promise promise, int i, Continuation<? super C01201> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$index = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01201(this.$callback, this.$index, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x005b  */
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
                if (r0 != 0) goto L63
                kotlin.ResultKt.throwOnFailure(r4)
                com.doublesymmetry.trackplayer.module.MusicModule r4 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.facebook.react.bridge.Promise r0 = r3.$callback
                boolean r4 = com.doublesymmetry.trackplayer.module.MusicModule.access$verifyServiceBoundOrReject(r4, r0)
                if (r4 == 0) goto L17
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            L17:
                int r4 = r3.$index
                r0 = 0
                if (r4 < 0) goto L5b
                com.doublesymmetry.trackplayer.module.MusicModule r1 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.doublesymmetry.trackplayer.service.MusicService r1 = com.doublesymmetry.trackplayer.module.MusicModule.access$getMusicService$p(r1)
                java.lang.String r2 = "musicService"
                if (r1 != 0) goto L2a
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                r1 = r0
            L2a:
                java.util.List r1 = r1.getTracks()
                int r1 = r1.size()
                if (r4 >= r1) goto L5b
                com.facebook.react.bridge.Promise r4 = r3.$callback
                com.doublesymmetry.trackplayer.module.MusicModule r1 = com.doublesymmetry.trackplayer.module.MusicModule.this
                com.doublesymmetry.trackplayer.service.MusicService r1 = com.doublesymmetry.trackplayer.module.MusicModule.access$getMusicService$p(r1)
                if (r1 != 0) goto L42
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
                goto L43
            L42:
                r0 = r1
            L43:
                java.util.List r0 = r0.getTracks()
                int r1 = r3.$index
                java.lang.Object r0 = r0.get(r1)
                com.doublesymmetry.trackplayer.model.Track r0 = (com.doublesymmetry.trackplayer.model.Track) r0
                android.os.Bundle r0 = r0.getOriginalItem()
                com.facebook.react.bridge.WritableMap r0 = com.facebook.react.bridge.Arguments.fromBundle(r0)
                r4.resolve(r0)
                goto L60
            L5b:
                com.facebook.react.bridge.Promise r4 = r3.$callback
                r4.resolve(r0)
            L60:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                return r4
            L63:
                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r4.<init>(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.trackplayer.module.MusicModule.C01201.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @ReactMethod
    public final Job getTrack(int index, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01201(callback, index, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getQueue$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getQueue$1, reason: invalid class name and case insensitive filesystem */
    static final class C01171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01171(Promise promise, Continuation<? super C01171> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01171(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                List<Track> tracks = musicService.getTracks();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(tracks, 10));
                Iterator<T> it = tracks.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Track) it.next()).getOriginalItem());
                }
                promise.resolve(Arguments.fromList(arrayList));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getQueue(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01171(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$setQueue$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$setQueue$1, reason: invalid class name and case insensitive filesystem */
    static final class C01351 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        final /* synthetic */ ReadableArray $data;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01351(Promise promise, ReadableArray readableArray, Continuation<? super C01351> continuation) {
            super(2, continuation);
            this.$callback = promise;
            this.$data = readableArray;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01351(this.$callback, this.$data, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01351) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                try {
                    MusicService musicService = MusicModule.this.musicService;
                    if (musicService == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService = null;
                    }
                    musicService.clear();
                    MusicService musicService2 = MusicModule.this.musicService;
                    if (musicService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService2 = null;
                    }
                    musicService2.add(MusicModule.this.readableArrayToTrackList(this.$data));
                    this.$callback.resolve(null);
                } catch (Exception e) {
                    MusicModule.this.rejectWithException(this.$callback, e);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job setQueue(ReadableArray data, Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01351(callback, data, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getActiveTrackIndex$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getActiveTrackIndex$1, reason: invalid class name and case insensitive filesystem */
    static final class C01101 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01101(Promise promise, Continuation<? super C01101> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01101(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01101) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                Integer numBoxInt = null;
                MusicService musicService2 = null;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                if (!musicService.getTracks().isEmpty()) {
                    MusicService musicService3 = MusicModule.this.musicService;
                    if (musicService3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    } else {
                        musicService2 = musicService3;
                    }
                    numBoxInt = Boxing.boxInt(musicService2.getCurrentTrackIndex());
                }
                promise.resolve(numBoxInt);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getActiveTrackIndex(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01101(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getActiveTrack$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getActiveTrack$1, reason: invalid class name and case insensitive filesystem */
    static final class C01091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01091(Promise promise, Continuation<? super C01091> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01091(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01091) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                WritableMap writableMapFromBundle = null;
                MusicService musicService2 = null;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                if (!musicService.getTracks().isEmpty()) {
                    MusicService musicService3 = MusicModule.this.musicService;
                    if (musicService3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                        musicService3 = null;
                    }
                    List<Track> tracks = musicService3.getTracks();
                    MusicService musicService4 = MusicModule.this.musicService;
                    if (musicService4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    } else {
                        musicService2 = musicService4;
                    }
                    writableMapFromBundle = Arguments.fromBundle(tracks.get(musicService2.getCurrentTrackIndex()).getOriginalItem());
                }
                promise.resolve(writableMapFromBundle);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getActiveTrack(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01091(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getDuration$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getDuration$1, reason: invalid class name and case insensitive filesystem */
    static final class C01121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01121(Promise promise, Continuation<? super C01121> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01121(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxDouble(musicService.getDurationInSeconds()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getDuration(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01121(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getBufferedPosition$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getBufferedPosition$1, reason: invalid class name and case insensitive filesystem */
    static final class C01111 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01111(Promise promise, Continuation<? super C01111> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01111(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01111) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxDouble(musicService.getBufferedPositionInSeconds()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getBufferedPosition(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01111(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getPosition$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getPosition$1, reason: invalid class name and case insensitive filesystem */
    static final class C01151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01151(Promise promise, Continuation<? super C01151> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01151(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                promise.resolve(Boxing.boxDouble(musicService.getPositionInSeconds()));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getPosition(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01151(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getProgress$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getProgress$1, reason: invalid class name and case insensitive filesystem */
    static final class C01161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01161(Promise promise, Continuation<? super C01161> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01161(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Bundle bundle = new Bundle();
                MusicService musicService = MusicModule.this.musicService;
                MusicService musicService2 = null;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                bundle.putDouble("duration", musicService.getDurationInSeconds());
                MusicService musicService3 = MusicModule.this.musicService;
                if (musicService3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService3 = null;
                }
                bundle.putDouble("position", musicService3.getPositionInSeconds());
                MusicService musicService4 = MusicModule.this.musicService;
                if (musicService4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                } else {
                    musicService2 = musicService4;
                }
                bundle.putDouble(MusicService.BUFFERED_POSITION_KEY, musicService2.getBufferedPositionInSeconds());
                this.$callback.resolve(Arguments.fromBundle(bundle));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getProgress(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01161(callback, null), 3, null);
    }

    /* compiled from: MusicModule.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "com.doublesymmetry.trackplayer.module.MusicModule$getPlaybackState$1", f = "MusicModule.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.doublesymmetry.trackplayer.module.MusicModule$getPlaybackState$1, reason: invalid class name and case insensitive filesystem */
    static final class C01141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Promise $callback;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C01141(Promise promise, Continuation<? super C01141> continuation) {
            super(2, continuation);
            this.$callback = promise;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MusicModule.this.new C01141(this.$callback, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (MusicModule.this.verifyServiceBoundOrReject(this.$callback)) {
                    return Unit.INSTANCE;
                }
                Promise promise = this.$callback;
                MusicService musicService = MusicModule.this.musicService;
                MusicService musicService2 = null;
                if (musicService == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                    musicService = null;
                }
                MusicService musicService3 = MusicModule.this.musicService;
                if (musicService3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("musicService");
                } else {
                    musicService2 = musicService3;
                }
                promise.resolve(Arguments.fromBundle(musicService.getPlayerStateBundle(musicService2.getState())));
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @ReactMethod
    public final Job getPlaybackState(Promise callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        return BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C01141(callback, null), 3, null);
    }
}
