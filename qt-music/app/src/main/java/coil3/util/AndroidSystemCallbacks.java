package coil3.util;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import coil3.RealImageLoader;
import coil3.memory.MemoryCache;
import coil3.util.Logger;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;

/* compiled from: SystemCallbacks.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 #2\u00020\u00012\u00020\u0002:\u0001#B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0010\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0018H\u0016J\u001d\u0010 \u001a\u00020\u00182\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00180\"H\u0082\bR)\u0010\u0003\u001a\u0014\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00040\u00040\u0007j\u0002`\t¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lcoil3/util/AndroidSystemCallbacks;", "Lcoil3/util/SystemCallbacks;", "Landroid/content/ComponentCallbacks2;", "imageLoader", "Lcoil3/RealImageLoader;", "<init>", "(Lcoil3/RealImageLoader;)V", "Ljava/lang/ref/WeakReference;", "kotlin.jvm.PlatformType", "Lcoil3/util/WeakReference;", "getImageLoader$annotations", "()V", "getImageLoader", "()Ljava/lang/ref/WeakReference;", "application", "Landroid/content/Context;", "shutdown", "", "getShutdown$annotations", "getShutdown", "()Z", "setShutdown", "(Z)V", "registerMemoryPressureCallbacks", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onTrimMemory", "level", "", "onLowMemory", "withImageLoader", "block", "Lkotlin/Function1;", "Companion", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AndroidSystemCallbacks implements SystemCallbacks, ComponentCallbacks2 {
    private static final Companion Companion = new Companion(null);
    private static final String TAG = "AndroidSystemCallbacks";
    private Context application;
    private final WeakReference<RealImageLoader> imageLoader;
    private boolean shutdown;

    public static /* synthetic */ void getImageLoader$annotations() {
    }

    public static /* synthetic */ void getShutdown$annotations() {
    }

    public AndroidSystemCallbacks(RealImageLoader realImageLoader) {
        this.imageLoader = new WeakReference<>(realImageLoader);
    }

    public final WeakReference<RealImageLoader> getImageLoader() {
        return this.imageLoader;
    }

    public final boolean getShutdown() {
        return this.shutdown;
    }

    public final void setShutdown(boolean z) {
        this.shutdown = z;
    }

    @Override // coil3.util.SystemCallbacks
    public synchronized void shutdown() {
        if (this.shutdown) {
            return;
        }
        this.shutdown = true;
        Context context = this.application;
        if (context != null) {
            context.unregisterComponentCallbacks(this);
        }
        this.imageLoader.clear();
    }

    @Override // android.content.ComponentCallbacks
    public synchronized void onLowMemory() {
        onTrimMemory(80);
    }

    private final void withImageLoader(Function1<? super RealImageLoader, Unit> block) {
        RealImageLoader realImageLoader = this.imageLoader.get();
        if (realImageLoader != null) {
            block.invoke(realImageLoader);
        } else {
            shutdown();
        }
    }

    /* compiled from: SystemCallbacks.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcoil3/util/AndroidSystemCallbacks$Companion;", "", "<init>", "()V", AbstractID3v1Tag.TAG, "", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // coil3.util.SystemCallbacks
    public synchronized void registerMemoryPressureCallbacks() {
        RealImageLoader realImageLoader = this.imageLoader.get();
        if (realImageLoader != null) {
            if (this.application == null) {
                Context application = realImageLoader.getOptions().getApplication();
                this.application = application;
                application.registerComponentCallbacks(this);
            }
        } else {
            shutdown();
        }
    }

    @Override // android.content.ComponentCallbacks
    public synchronized void onConfigurationChanged(Configuration newConfig) {
        if (this.imageLoader.get() == null) {
            shutdown();
        }
    }

    @Override // android.content.ComponentCallbacks2
    public synchronized void onTrimMemory(int level) {
        MemoryCache memoryCache;
        RealImageLoader realImageLoader = this.imageLoader.get();
        if (realImageLoader != null) {
            Logger logger = realImageLoader.getOptions().getLogger();
            if (logger != null) {
                Logger.Level level2 = Logger.Level.Verbose;
                if (logger.getMinLevel().compareTo(level2) <= 0) {
                    logger.log(TAG, level2, "trimMemory, level=" + level, null);
                }
            }
            if (level >= 40) {
                MemoryCache memoryCache2 = realImageLoader.getMemoryCache();
                if (memoryCache2 != null) {
                    memoryCache2.clear();
                }
            } else if (level >= 10 && (memoryCache = realImageLoader.getMemoryCache()) != null) {
                memoryCache.trimToSize(memoryCache.getSize() / 2);
            }
        } else {
            shutdown();
        }
    }
}
