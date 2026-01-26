package com.facebook.react;

import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.inject.Provider;

/* loaded from: classes2.dex */
public abstract class BaseReactPackage implements ReactPackage {
    @Override // com.facebook.react.ReactPackage
    public abstract NativeModule getModule(String str, ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    @Override // com.facebook.react.ReactPackage
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        throw new UnsupportedOperationException("createNativeModules method is not supported. Use getModule() method instead.");
    }

    Iterable<ModuleHolder> getNativeModuleIterator(final ReactApplicationContext reactApplicationContext) {
        final Iterator<Map.Entry<String, ReactModuleInfo>> it = getReactModuleInfoProvider().getReactModuleInfos().entrySet().iterator();
        return new Iterable() { // from class: com.facebook.react.BaseReactPackage$$ExternalSyntheticLambda0
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return this.f$0.lambda$getNativeModuleIterator$0(it, reactApplicationContext);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Iterator lambda$getNativeModuleIterator$0(final Iterator it, final ReactApplicationContext reactApplicationContext) {
        return new Iterator<ModuleHolder>() { // from class: com.facebook.react.BaseReactPackage.1
            Map.Entry<String, ReactModuleInfo> nextEntry = null;

            private void findNext() {
                while (it.hasNext()) {
                    Map.Entry<String, ReactModuleInfo> entry = (Map.Entry) it.next();
                    ReactModuleInfo value = entry.getValue();
                    if (!ReactFeatureFlags.useTurboModules || !value.getIsTurboModule()) {
                        this.nextEntry = entry;
                        return;
                    }
                }
                this.nextEntry = null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.nextEntry == null) {
                    findNext();
                }
                return this.nextEntry != null;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public ModuleHolder next() {
                if (this.nextEntry == null) {
                    findNext();
                }
                Map.Entry<String, ReactModuleInfo> entry = this.nextEntry;
                if (entry == null) {
                    throw new NoSuchElementException("ModuleHolder not found");
                }
                findNext();
                return new ModuleHolder(entry.getValue(), BaseReactPackage.this.new ModuleHolderProvider(entry.getKey(), reactApplicationContext));
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove native modules from the list");
            }
        };
    }

    protected List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> viewManagers = getViewManagers(reactApplicationContext);
        if (viewManagers == null || viewManagers.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<ModuleSpec> it = viewManagers.iterator();
        while (it.hasNext()) {
            arrayList.add((ViewManager) it.next().getProvider().get());
        }
        return arrayList;
    }

    private class ModuleHolderProvider implements Provider<NativeModule> {
        private final String mName;
        private final ReactApplicationContext mReactContext;

        public ModuleHolderProvider(String str, ReactApplicationContext reactApplicationContext) {
            this.mName = str;
            this.mReactContext = reactApplicationContext;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // javax.inject.Provider
        public NativeModule get() {
            return BaseReactPackage.this.getModule(this.mName, this.mReactContext);
        }
    }
}
