package coil3;

import coil3.ComponentRegistry;
import coil3.RealImageLoader;
import coil3.fetch.ByteBufferFetcher;
import coil3.fetch.JarFileFetcher;
import coil3.map.FileMapper;
import java.io.File;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;

/* compiled from: RealImageLoader.jvmCommon.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004"}, d2 = {"addJvmComponents", "Lcoil3/ComponentRegistry$Builder;", "options", "Lcoil3/RealImageLoader$Options;", "coil-core_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RealImageLoader_jvmCommonKt {
    public static final ComponentRegistry.Builder addJvmComponents(ComponentRegistry.Builder builder, RealImageLoader.Options options) {
        return builder.add(new FileMapper(), Reflection.getOrCreateKotlinClass(File.class)).add(new JarFileFetcher.Factory(), Reflection.getOrCreateKotlinClass(Uri.class)).add(new ByteBufferFetcher.Factory(), Reflection.getOrCreateKotlinClass(ByteBuffer.class));
    }
}
