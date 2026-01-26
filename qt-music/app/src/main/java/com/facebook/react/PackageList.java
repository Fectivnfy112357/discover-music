package com.facebook.react;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.BV.LinearGradient.LinearGradientPackage;
import com.azendoo.reactnativesnackbar.SnackbarPackage;
import com.doublesymmetry.trackplayer.TrackPlayer;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.localmediametadata.LocalMediaMetadataPackage;
import com.oblador.vectoricons.VectorIconsPackage;
import com.ocetnik.timer.BackgroundTimerPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.clipboard.ClipboardPackage;
import com.reactnativedocumentpicker.RNDocumentPickerPackage;
import com.rnfs.RNFSPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import com.zmxv.RNSound.RNSoundPackage;
import com.zoontek.rnpermissions.RNPermissionsPackage;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost) {
        this(reactNativeHost, (MainPackageConfig) null);
    }

    public PackageList(Application application) {
        this(application, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application;
        this.mConfig = mainPackageConfig;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost = this.reactNativeHost;
        return reactNativeHost == null ? this.application : reactNativeHost.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new MainReactPackage(this.mConfig), new AsyncStoragePackage(), new ClipboardPackage(), new BackgroundTimerPackage(), new RNDocumentPickerPackage(), new RNFSPackage(), new LinearGradientPackage(), new LocalMediaMetadataPackage(), new RNPermissionsPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new SnackbarPackage(), new RNSoundPackage(), new TrackPlayer(), new VectorIconsPackage()));
    }
}
