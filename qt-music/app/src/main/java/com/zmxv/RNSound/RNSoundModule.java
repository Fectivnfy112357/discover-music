package com.zmxv.RNSound;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public class RNSoundModule extends ReactContextBaseJavaModule implements AudioManager.OnAudioFocusChangeListener {
    static final Object NULL = null;
    String category;
    ReactApplicationContext context;
    Double focusedPlayerKey;
    Boolean mixWithOthers;
    Map<Double, MediaPlayer> playerPool;
    Boolean wasPlayingBeforeFocusChange;

    @ReactMethod
    public void addListener(String str) {
    }

    @ReactMethod
    public void enable(Boolean bool) {
    }

    @ReactMethod
    public void removeListeners(Integer num) {
    }

    public RNSoundModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.playerPool = new HashMap();
        this.mixWithOthers = true;
        this.wasPlayingBeforeFocusChange = false;
        this.context = reactApplicationContext;
        this.category = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOnPlay(boolean z, Double d) {
        ReactApplicationContext reactApplicationContext = this.context;
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putBoolean("isPlaying", z);
        writableMapCreateMap.putDouble("playerKey", d.doubleValue());
        ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("onPlayChange", writableMapCreateMap);
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNSound";
    }

    @ReactMethod
    public void prepare(String str, Double d, ReadableMap readableMap, final Callback callback) throws IllegalStateException, Resources.NotFoundException, IOException, SecurityException, IllegalArgumentException {
        Integer num;
        MediaPlayer mediaPlayerCreateMediaPlayer = createMediaPlayer(str);
        if (readableMap.hasKey("speed")) {
            mediaPlayerCreateMediaPlayer.setPlaybackParams(mediaPlayerCreateMediaPlayer.getPlaybackParams().setSpeed((float) readableMap.getDouble("speed")));
        }
        if (mediaPlayerCreateMediaPlayer == null) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("code", -1);
            writableMapCreateMap.putString(StackTraceHelper.MESSAGE_KEY, "resource not found");
            callback.invoke(writableMapCreateMap, NULL);
            return;
        }
        this.playerPool.put(d, mediaPlayerCreateMediaPlayer);
        String str2 = this.category;
        if (str2 != null) {
            str2.hashCode();
            switch (str2) {
                case "System":
                    num = 1;
                    break;
                case "Ring":
                    num = 2;
                    break;
                case "Alarm":
                    num = 4;
                    break;
                case "Voice":
                    num = 0;
                    break;
                case "Ambient":
                    num = 5;
                    break;
                case "Playback":
                    num = 3;
                    break;
                default:
                    Log.e("RNSoundModule", String.format("Unrecognised category %s", this.category));
                    num = null;
                    break;
            }
            if (num != null) {
                mediaPlayerCreateMediaPlayer.setAudioStreamType(num.intValue());
            }
        }
        mediaPlayerCreateMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.zmxv.RNSound.RNSoundModule.1
            boolean callbackWasCalled = false;

            @Override // android.media.MediaPlayer.OnPreparedListener
            public synchronized void onPrepared(MediaPlayer mediaPlayer) {
                if (this.callbackWasCalled) {
                    return;
                }
                this.callbackWasCalled = true;
                WritableMap writableMapCreateMap2 = Arguments.createMap();
                writableMapCreateMap2.putDouble("duration", mediaPlayer.getDuration() * 0.001d);
                try {
                    callback.invoke(RNSoundModule.NULL, writableMapCreateMap2);
                } catch (RuntimeException e) {
                    Log.e("RNSoundModule", "Exception", e);
                }
            }
        });
        mediaPlayerCreateMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.zmxv.RNSound.RNSoundModule.2
            boolean callbackWasCalled = false;

            @Override // android.media.MediaPlayer.OnErrorListener
            public synchronized boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                if (this.callbackWasCalled) {
                    return true;
                }
                this.callbackWasCalled = true;
                try {
                    WritableMap writableMapCreateMap2 = Arguments.createMap();
                    writableMapCreateMap2.putInt("what", i);
                    writableMapCreateMap2.putInt("extra", i2);
                    callback.invoke(writableMapCreateMap2, RNSoundModule.NULL);
                } catch (RuntimeException e) {
                    Log.e("RNSoundModule", "Exception", e);
                }
                return true;
            }
        });
        try {
            if (readableMap.hasKey("loadSync") && readableMap.getBoolean("loadSync")) {
                mediaPlayerCreateMediaPlayer.prepare();
            } else {
                mediaPlayerCreateMediaPlayer.prepareAsync();
            }
        } catch (Exception e) {
            Log.e("RNSoundModule", "Exception", e);
        }
    }

    protected MediaPlayer createMediaPlayer(String str) throws IllegalStateException, Resources.NotFoundException, IOException, SecurityException, IllegalArgumentException {
        int identifier = this.context.getResources().getIdentifier(str, "raw", this.context.getPackageName());
        MediaPlayer mediaPlayer = new MediaPlayer();
        if (identifier != 0) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenRawResourceFd = this.context.getResources().openRawResourceFd(identifier);
                mediaPlayer.setDataSource(assetFileDescriptorOpenRawResourceFd.getFileDescriptor(), assetFileDescriptorOpenRawResourceFd.getStartOffset(), assetFileDescriptorOpenRawResourceFd.getLength());
                assetFileDescriptorOpenRawResourceFd.close();
                return mediaPlayer;
            } catch (IOException e) {
                Log.e("RNSoundModule", "Exception", e);
                return null;
            }
        }
        if (str.startsWith("http://") || str.startsWith("https://")) {
            mediaPlayer.setAudioStreamType(3);
            Log.i("RNSoundModule", str);
            try {
                mediaPlayer.setDataSource(str);
                return mediaPlayer;
            } catch (IOException e2) {
                Log.e("RNSoundModule", "Exception", e2);
                return null;
            }
        }
        if (str.startsWith("asset:/")) {
            try {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.context.getAssets().openFd(str.replace("asset:/", ""));
                mediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
                assetFileDescriptorOpenFd.close();
                return mediaPlayer;
            } catch (IOException e3) {
                Log.e("RNSoundModule", "Exception", e3);
                return null;
            }
        }
        if (str.startsWith("file:/")) {
            try {
                mediaPlayer.setDataSource(str);
                return mediaPlayer;
            } catch (IOException e4) {
                Log.e("RNSoundModule", "Exception", e4);
                return null;
            }
        }
        if (new File(str).exists()) {
            mediaPlayer.setAudioStreamType(3);
            Log.i("RNSoundModule", str);
            try {
                mediaPlayer.setDataSource(str);
                return mediaPlayer;
            } catch (IOException e5) {
                Log.e("RNSoundModule", "Exception", e5);
            }
        }
        return null;
    }

    @ReactMethod
    public void play(final Double d, final Callback callback) throws IllegalStateException {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer == null) {
            setOnPlay(false, d);
            if (callback != null) {
                callback.invoke(false);
                return;
            }
            return;
        }
        if (mediaPlayer.isPlaying()) {
            return;
        }
        if (!this.mixWithOthers.booleanValue()) {
            ((AudioManager) this.context.getSystemService("audio")).requestAudioFocus(this, 3, 1);
            this.focusedPlayerKey = d;
        }
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.zmxv.RNSound.RNSoundModule.3
            boolean callbackWasCalled = false;

            @Override // android.media.MediaPlayer.OnCompletionListener
            public synchronized void onCompletion(MediaPlayer mediaPlayer2) {
                if (!mediaPlayer2.isLooping()) {
                    RNSoundModule.this.setOnPlay(false, d);
                    if (this.callbackWasCalled) {
                        return;
                    }
                    this.callbackWasCalled = true;
                    try {
                        callback.invoke(true);
                    } catch (Exception unused) {
                    }
                }
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.zmxv.RNSound.RNSoundModule.4
            boolean callbackWasCalled = false;

            @Override // android.media.MediaPlayer.OnErrorListener
            public synchronized boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                RNSoundModule.this.setOnPlay(false, d);
                if (this.callbackWasCalled) {
                    return true;
                }
                this.callbackWasCalled = true;
                try {
                    callback.invoke(true);
                } catch (Exception unused) {
                }
                return true;
            }
        });
        mediaPlayer.start();
        setOnPlay(true, d);
    }

    @ReactMethod
    public void pause(Double d, Callback callback) throws IllegalStateException {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        if (callback != null) {
            callback.invoke(new Object[0]);
        }
    }

    @ReactMethod
    public void stop(Double d, Callback callback) throws IllegalStateException {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0);
        }
        if (!this.mixWithOthers.booleanValue() && d == this.focusedPlayerKey) {
            ((AudioManager) this.context.getSystemService("audio")).abandonAudioFocus(this);
        }
        callback.invoke(new Object[0]);
    }

    @ReactMethod
    public void reset(Double d) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
    }

    @ReactMethod
    public void release(Double d) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            mediaPlayer.release();
            this.playerPool.remove(d);
            if (this.mixWithOthers.booleanValue() || d != this.focusedPlayerKey) {
                return;
            }
            ((AudioManager) this.context.getSystemService("audio")).abandonAudioFocus(this);
        }
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
        Iterator<Map.Entry<Double, MediaPlayer>> it = this.playerPool.entrySet().iterator();
        while (it.hasNext()) {
            MediaPlayer value = it.next().getValue();
            if (value != null) {
                value.reset();
                value.release();
            }
            it.remove();
        }
    }

    @ReactMethod
    public void setVolume(Double d, Float f, Float f2) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f.floatValue(), f2.floatValue());
        }
    }

    @ReactMethod
    public void getSystemVolume(Callback callback) {
        try {
            AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
            callback.invoke(Float.valueOf(audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3)));
        } catch (Exception e) {
            WritableMap writableMapCreateMap = Arguments.createMap();
            writableMapCreateMap.putInt("code", -1);
            writableMapCreateMap.putString(StackTraceHelper.MESSAGE_KEY, e.getMessage());
            callback.invoke(writableMapCreateMap);
        }
    }

    @ReactMethod
    public void setSystemVolume(Float f) {
        ((AudioManager) this.context.getSystemService("audio")).setStreamVolume(3, Math.round(r0.getStreamMaxVolume(3) * f.floatValue()), 0);
    }

    @ReactMethod
    public void setLooping(Double d, Boolean bool) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setSpeed(Double d, Float f) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(f.floatValue()));
        }
    }

    @ReactMethod
    public void setPitch(Double d, Float f) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setPitch(f.floatValue()));
        }
    }

    @ReactMethod
    public void setCurrentTime(Double d, Float f) throws IllegalStateException {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(Math.round(f.floatValue() * 1000.0f));
        }
    }

    @ReactMethod
    public void getCurrentTime(Double d, Callback callback) {
        MediaPlayer mediaPlayer = this.playerPool.get(d);
        if (mediaPlayer == null) {
            callback.invoke(-1, false);
        } else {
            callback.invoke(Double.valueOf(mediaPlayer.getCurrentPosition() * 0.001d), Boolean.valueOf(mediaPlayer.isPlaying()));
        }
    }

    @ReactMethod
    public void setSpeakerphoneOn(Double d, Boolean bool) {
        if (this.playerPool.get(d) != null) {
            AudioManager audioManager = (AudioManager) this.context.getSystemService("audio");
            if (bool.booleanValue()) {
                audioManager.setMode(3);
            } else {
                audioManager.setMode(0);
            }
            audioManager.setSpeakerphoneOn(bool.booleanValue());
        }
    }

    @ReactMethod
    public void setCategory(String str, Boolean bool) {
        this.category = str;
        this.mixWithOthers = bool;
    }

    @Override // android.media.AudioManager.OnAudioFocusChangeListener
    public void onAudioFocusChange(int i) throws IllegalStateException {
        MediaPlayer mediaPlayer;
        if (this.mixWithOthers.booleanValue() || (mediaPlayer = this.playerPool.get(this.focusedPlayerKey)) == null) {
            return;
        }
        if (i <= 0) {
            Boolean boolValueOf = Boolean.valueOf(mediaPlayer.isPlaying());
            this.wasPlayingBeforeFocusChange = boolValueOf;
            if (boolValueOf.booleanValue()) {
                pause(this.focusedPlayerKey, null);
                return;
            }
            return;
        }
        if (this.wasPlayingBeforeFocusChange.booleanValue()) {
            play(this.focusedPlayerKey, null);
            this.wasPlayingBeforeFocusChange = false;
        }
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    public Map<String, Object> getConstants() {
        HashMap map = new HashMap();
        map.put("IsAndroid", true);
        return map;
    }
}
