package com.facebook.react.shell;

import com.facebook.fbreact.specs.NativeAccessibilityInfoSpec;
import com.facebook.fbreact.specs.NativeAnimatedModuleSpec;
import com.facebook.fbreact.specs.NativeBlobModuleSpec;
import com.facebook.fbreact.specs.NativeDevToolsSettingsManagerSpec;
import com.facebook.fbreact.specs.NativeDialogManagerAndroidSpec;
import com.facebook.fbreact.specs.NativeFileReaderModuleSpec;
import com.facebook.fbreact.specs.NativeI18nManagerSpec;
import com.facebook.fbreact.specs.NativeIntentAndroidSpec;
import com.facebook.fbreact.specs.NativeNetworkingAndroidSpec;
import com.facebook.fbreact.specs.NativePermissionsAndroidSpec;
import com.facebook.fbreact.specs.NativeShareModuleSpec;
import com.facebook.fbreact.specs.NativeSoundManagerSpec;
import com.facebook.fbreact.specs.NativeVibrationSpec;
import com.facebook.fbreact.specs.NativeWebSocketModuleSpec;
import com.facebook.react.BaseReactPackage;
import com.facebook.react.ViewManagerOnDemandReactPackage;
import com.facebook.react.animated.NativeAnimatedModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ClassFinder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.accessibilityinfo.AccessibilityInfoModule;
import com.facebook.react.modules.appearance.AppearanceModule;
import com.facebook.react.modules.appstate.AppStateModule;
import com.facebook.react.modules.blob.BlobModule;
import com.facebook.react.modules.blob.FileReaderModule;
import com.facebook.react.modules.camera.ImageStoreManager;
import com.facebook.react.modules.clipboard.ClipboardModule;
import com.facebook.react.modules.devloading.DevLoadingModule;
import com.facebook.react.modules.devtoolssettings.DevToolsSettingsManagerModule;
import com.facebook.react.modules.dialog.DialogModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.i18nmanager.I18nManagerModule;
import com.facebook.react.modules.image.ImageLoaderModule;
import com.facebook.react.modules.intent.IntentModule;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.permissions.PermissionsModule;
import com.facebook.react.modules.share.ShareModule;
import com.facebook.react.modules.sound.SoundManagerModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.modules.toast.ToastModule;
import com.facebook.react.modules.vibration.VibrationModule;
import com.facebook.react.modules.websocket.WebSocketModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.safeareaview.ReactSafeAreaViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.unimplementedview.ReactUnimplementedViewManager;
import com.facebook.react.views.view.ReactViewManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public class MainReactPackage extends BaseReactPackage implements ViewManagerOnDemandReactPackage {
    private MainPackageConfig mConfig;
    private Map<String, ModuleSpec> mViewManagers;

    static /* synthetic */ Map lambda$fallbackForMissingClass$0(Map map) {
        return map;
    }

    public MainReactPackage() {
    }

    public MainReactPackage(MainPackageConfig mainPackageConfig) {
        this.mConfig = mainPackageConfig;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2115067288:
                if (str.equals("ToastAndroid")) {
                    c = 0;
                    break;
                }
                break;
            case -1962922905:
                if (str.equals("ImageStoreManager")) {
                    c = 1;
                    break;
                }
                break;
            case -1850625090:
                if (str.equals(NativeSoundManagerSpec.NAME)) {
                    c = 2;
                    break;
                }
                break;
            case -1654566518:
                if (str.equals(NativeDialogManagerAndroidSpec.NAME)) {
                    c = 3;
                    break;
                }
                break;
            case -1344126773:
                if (str.equals(NativeFileReaderModuleSpec.NAME)) {
                    c = 4;
                    break;
                }
                break;
            case -1062061717:
                if (str.equals(NativePermissionsAndroidSpec.NAME)) {
                    c = 5;
                    break;
                }
                break;
            case -657277650:
                if (str.equals("ImageLoader")) {
                    c = 6;
                    break;
                }
                break;
            case -570370161:
                if (str.equals(NativeI18nManagerSpec.NAME)) {
                    c = 7;
                    break;
                }
                break;
            case -504784764:
                if (str.equals("Appearance")) {
                    c = '\b';
                    break;
                }
                break;
            case -457866500:
                if (str.equals(NativeAccessibilityInfoSpec.NAME)) {
                    c = '\t';
                    break;
                }
                break;
            case -382654004:
                if (str.equals("StatusBarManager")) {
                    c = '\n';
                    break;
                }
                break;
            case -254310125:
                if (str.equals(NativeWebSocketModuleSpec.NAME)) {
                    c = 11;
                    break;
                }
                break;
            case -99249460:
                if (str.equals("DevLoadingView")) {
                    c = '\f';
                    break;
                }
                break;
            case 163245714:
                if (str.equals(FrescoModule.NAME)) {
                    c = '\r';
                    break;
                }
                break;
            case 403570038:
                if (str.equals("Clipboard")) {
                    c = 14;
                    break;
                }
                break;
            case 443276868:
                if (str.equals(NativeDevToolsSettingsManagerSpec.NAME)) {
                    c = 15;
                    break;
                }
                break;
            case 563961875:
                if (str.equals(NativeIntentAndroidSpec.NAME)) {
                    c = 16;
                    break;
                }
                break;
            case 1221389072:
                if (str.equals("AppState")) {
                    c = 17;
                    break;
                }
                break;
            case 1515242260:
                if (str.equals(NativeNetworkingAndroidSpec.NAME)) {
                    c = 18;
                    break;
                }
                break;
            case 1547941001:
                if (str.equals(NativeBlobModuleSpec.NAME)) {
                    c = 19;
                    break;
                }
                break;
            case 1555425035:
                if (str.equals(NativeShareModuleSpec.NAME)) {
                    c = 20;
                    break;
                }
                break;
            case 1721274886:
                if (str.equals(NativeAnimatedModuleSpec.NAME)) {
                    c = 21;
                    break;
                }
                break;
            case 1922110066:
                if (str.equals(NativeVibrationSpec.NAME)) {
                    c = 22;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return new ToastModule(reactApplicationContext);
            case 1:
                return new ImageStoreManager(reactApplicationContext);
            case 2:
                return new SoundManagerModule(reactApplicationContext);
            case 3:
                return new DialogModule(reactApplicationContext);
            case 4:
                return new FileReaderModule(reactApplicationContext);
            case 5:
                return new PermissionsModule(reactApplicationContext);
            case 6:
                return new ImageLoaderModule(reactApplicationContext);
            case 7:
                return new I18nManagerModule(reactApplicationContext);
            case '\b':
                return new AppearanceModule(reactApplicationContext);
            case '\t':
                return new AccessibilityInfoModule(reactApplicationContext);
            case '\n':
                return new StatusBarModule(reactApplicationContext);
            case 11:
                return new WebSocketModule(reactApplicationContext);
            case '\f':
                return new DevLoadingModule(reactApplicationContext);
            case '\r':
                MainPackageConfig mainPackageConfig = this.mConfig;
                return new FrescoModule(reactApplicationContext, true, mainPackageConfig != null ? mainPackageConfig.getFrescoConfig() : null);
            case 14:
                return new ClipboardModule(reactApplicationContext);
            case 15:
                return new DevToolsSettingsManagerModule(reactApplicationContext);
            case 16:
                return new IntentModule(reactApplicationContext);
            case 17:
                return new AppStateModule(reactApplicationContext);
            case 18:
                return new NetworkingModule(reactApplicationContext);
            case 19:
                return new BlobModule(reactApplicationContext);
            case 20:
                return new ShareModule(reactApplicationContext);
            case 21:
                return new NativeAnimatedModule(reactApplicationContext);
            case 22:
                return new VibrationModule(reactApplicationContext);
            default:
                return null;
        }
    }

    @Override // com.facebook.react.BaseReactPackage, com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ReactDrawerLayoutManager());
        arrayList.add(new ReactHorizontalScrollViewManager());
        arrayList.add(new ReactHorizontalScrollContainerViewManager());
        arrayList.add(new ReactProgressBarViewManager());
        arrayList.add(new ReactScrollViewManager());
        arrayList.add(new ReactSwitchManager());
        arrayList.add(new ReactSafeAreaViewManager());
        arrayList.add(new SwipeRefreshLayoutManager());
        arrayList.add(new FrescoBasedReactTextInlineImageViewManager());
        arrayList.add(new ReactImageManager());
        arrayList.add(new ReactModalHostManager());
        arrayList.add(new ReactRawTextManager());
        arrayList.add(new ReactTextInputManager());
        arrayList.add(new ReactTextViewManager());
        arrayList.add(new ReactViewManager());
        arrayList.add(new ReactVirtualTextViewManager());
        arrayList.add(new ReactUnimplementedViewManager());
        return arrayList;
    }

    private static void appendMap(Map<String, ModuleSpec> map, String str, Provider<? extends NativeModule> provider) {
        map.put(str, ModuleSpec.viewManagerSpec(provider));
    }

    public Map<String, ModuleSpec> getViewManagersMap() {
        if (this.mViewManagers == null) {
            HashMap map = new HashMap();
            appendMap(map, ReactDrawerLayoutManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda9
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactDrawerLayoutManager();
                }
            });
            appendMap(map, ReactHorizontalScrollViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda17
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactHorizontalScrollViewManager();
                }
            });
            appendMap(map, ReactHorizontalScrollContainerViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda1
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactHorizontalScrollContainerViewManager();
                }
            });
            appendMap(map, ReactProgressBarViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda2
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactProgressBarViewManager();
                }
            });
            appendMap(map, ReactSafeAreaViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda3
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactSafeAreaViewManager();
                }
            });
            appendMap(map, ReactScrollViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda4
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactScrollViewManager();
                }
            });
            appendMap(map, ReactSwitchManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda5
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactSwitchManager();
                }
            });
            appendMap(map, SwipeRefreshLayoutManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda6
                @Override // javax.inject.Provider
                public final Object get() {
                    return new SwipeRefreshLayoutManager();
                }
            });
            appendMap(map, FrescoBasedReactTextInlineImageViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda7
                @Override // javax.inject.Provider
                public final Object get() {
                    return new FrescoBasedReactTextInlineImageViewManager();
                }
            });
            appendMap(map, ReactImageManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda8
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactImageManager();
                }
            });
            appendMap(map, ReactModalHostManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda10
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactModalHostManager();
                }
            });
            appendMap(map, ReactRawTextManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda11
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactRawTextManager();
                }
            });
            appendMap(map, ReactTextInputManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda12
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactTextInputManager();
                }
            });
            appendMap(map, ReactTextViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda13
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactTextViewManager();
                }
            });
            appendMap(map, "RCTView", new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda14
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactViewManager();
                }
            });
            appendMap(map, ReactVirtualTextViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda15
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactVirtualTextViewManager();
                }
            });
            appendMap(map, ReactUnimplementedViewManager.REACT_CLASS, new Provider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda16
                @Override // javax.inject.Provider
                public final Object get() {
                    return new ReactUnimplementedViewManager();
                }
            });
            this.mViewManagers = map;
        }
        return this.mViewManagers;
    }

    @Override // com.facebook.react.BaseReactPackage
    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return new ArrayList(getViewManagersMap().values());
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public Collection<String> getViewManagerNames(ReactApplicationContext reactApplicationContext) {
        return getViewManagersMap().keySet();
    }

    @Override // com.facebook.react.ViewManagerOnDemandReactPackage
    public ViewManager createViewManager(ReactApplicationContext reactApplicationContext, String str) {
        ModuleSpec moduleSpec = getViewManagersMap().get(str);
        if (moduleSpec != null) {
            return (ViewManager) moduleSpec.getProvider().get();
        }
        return null;
    }

    @Override // com.facebook.react.BaseReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        if (!ClassFinder.canLoadClassesFromAnnotationProcessors()) {
            return fallbackForMissingClass();
        }
        try {
            return (ReactModuleInfoProvider) ClassFinder.findClass("com.facebook.react.shell.MainReactPackage$$ReactModuleInfoProvider").newInstance();
        } catch (ClassNotFoundException unused) {
            return fallbackForMissingClass();
        } catch (IllegalAccessException e) {
            throw new RuntimeException("No ReactModuleInfoProvider for com.facebook.react.shell.MainReactPackage$$ReactModuleInfoProvider", e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("No ReactModuleInfoProvider for com.facebook.react.shell.MainReactPackage$$ReactModuleInfoProvider", e2);
        }
    }

    private ReactModuleInfoProvider fallbackForMissingClass() {
        Class[] clsArr = {AccessibilityInfoModule.class, AppearanceModule.class, AppStateModule.class, BlobModule.class, DevLoadingModule.class, FileReaderModule.class, ClipboardModule.class, DialogModule.class, FrescoModule.class, I18nManagerModule.class, ImageLoaderModule.class, ImageStoreManager.class, IntentModule.class, NativeAnimatedModule.class, NetworkingModule.class, PermissionsModule.class, DevToolsSettingsManagerModule.class, ShareModule.class, StatusBarModule.class, SoundManagerModule.class, ToastModule.class, VibrationModule.class, WebSocketModule.class};
        final HashMap map = new HashMap();
        for (int i = 0; i < 23; i++) {
            Class cls = clsArr[i];
            ReactModule reactModule = (ReactModule) cls.getAnnotation(ReactModule.class);
            if (reactModule != null) {
                map.put(reactModule.name(), new ReactModuleInfo(reactModule.name(), cls.getName(), reactModule.canOverrideExistingModule(), reactModule.needsEagerInit(), reactModule.isCxxModule(), ReactModuleInfo.classIsTurboModule(cls)));
            }
        }
        return new ReactModuleInfoProvider() { // from class: com.facebook.react.shell.MainReactPackage$$ExternalSyntheticLambda0
            @Override // com.facebook.react.module.model.ReactModuleInfoProvider
            public final Map getReactModuleInfos() {
                return MainReactPackage.lambda$fallbackForMissingClass$0(map);
            }
        };
    }
}
