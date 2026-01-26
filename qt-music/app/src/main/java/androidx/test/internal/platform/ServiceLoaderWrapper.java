package androidx.test.internal.platform;

import android.os.StrictMode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/* loaded from: classes.dex */
public final class ServiceLoaderWrapper {

    public interface Factory<T> {
        T create();
    }

    private ServiceLoaderWrapper() {
    }

    public static <T> List<T> loadService(Class<T> serviceClass) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        ArrayList arrayList = new ArrayList();
        Iterator it = ServiceLoader.load(serviceClass).iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        return arrayList;
    }

    public static <T> T loadSingleService(Class<T> cls, Factory<T> factory) {
        T t = (T) loadSingleServiceOrNull(cls);
        return t == null ? factory.create() : t;
    }

    public static <T> T loadSingleServiceOrNull(Class<T> cls) {
        List listLoadService = loadService(cls);
        if (listLoadService.isEmpty()) {
            return null;
        }
        if (listLoadService.size() == 1) {
            return (T) listLoadService.get(0);
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(strValueOf.length() != 0 ? "Found more than one implementation for ".concat(strValueOf) : new String("Found more than one implementation for "));
    }
}
