package androidx.test.runner.internal.deps.desugar;

import java.io.Closeable;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class ThrowableExtension {
    private static final String ANDROID_OS_BUILD_VERSION = "android.os.Build$VERSION";
    static final int API_LEVEL;
    static final AbstractDesugaringStrategy STRATEGY;
    public static final String SYSTEM_PROPERTY_TWR_DISABLE_MIMIC = "androidx.test.runner.internal.deps.desugar.twr_disable_mimic";

    /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014 A[Catch: all -> 0x0026, TryCatch #0 {all -> 0x0026, blocks: (B:4:0x0006, B:6:0x000e, B:7:0x0014, B:9:0x001a, B:10:0x0020), top: B:24:0x0006 }] */
    static {
        /*
            java.lang.Integer r0 = readApiLevelFromBuildVersion()     // Catch: java.lang.Throwable -> L28
            if (r0 == 0) goto L14
            int r1 = r0.intValue()     // Catch: java.lang.Throwable -> L26
            r2 = 19
            if (r1 < r2) goto L14
            androidx.test.runner.internal.deps.desugar.ThrowableExtension$ReuseDesugaringStrategy r1 = new androidx.test.runner.internal.deps.desugar.ThrowableExtension$ReuseDesugaringStrategy     // Catch: java.lang.Throwable -> L26
            r1.<init>()     // Catch: java.lang.Throwable -> L26
            goto L63
        L14:
            boolean r1 = useMimicStrategy()     // Catch: java.lang.Throwable -> L26
            if (r1 == 0) goto L20
            androidx.test.runner.internal.deps.desugar.ThrowableExtension$MimicDesugaringStrategy r1 = new androidx.test.runner.internal.deps.desugar.ThrowableExtension$MimicDesugaringStrategy     // Catch: java.lang.Throwable -> L26
            r1.<init>()     // Catch: java.lang.Throwable -> L26
            goto L63
        L20:
            androidx.test.runner.internal.deps.desugar.ThrowableExtension$NullDesugaringStrategy r1 = new androidx.test.runner.internal.deps.desugar.ThrowableExtension$NullDesugaringStrategy     // Catch: java.lang.Throwable -> L26
            r1.<init>()     // Catch: java.lang.Throwable -> L26
            goto L63
        L26:
            r1 = move-exception
            goto L2a
        L28:
            r1 = move-exception
            r0 = 0
        L2a:
            java.io.PrintStream r2 = java.lang.System.err
            java.lang.Class<androidx.test.runner.internal.deps.desugar.ThrowableExtension$NullDesugaringStrategy> r3 = androidx.test.runner.internal.deps.desugar.ThrowableExtension.NullDesugaringStrategy.class
            java.lang.String r3 = r3.getName()
            java.lang.String r4 = java.lang.String.valueOf(r3)
            int r4 = r4.length()
            int r4 = r4 + 133
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r4)
            java.lang.String r4 = "An error has occurred when initializing the try-with-resources desuguring strategy. The default strategy "
            java.lang.StringBuilder r4 = r5.append(r4)
            java.lang.StringBuilder r3 = r4.append(r3)
            java.lang.String r4 = "will be used. The error is: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            r2.println(r3)
            java.io.PrintStream r2 = java.lang.System.err
            r1.printStackTrace(r2)
            androidx.test.runner.internal.deps.desugar.ThrowableExtension$NullDesugaringStrategy r1 = new androidx.test.runner.internal.deps.desugar.ThrowableExtension$NullDesugaringStrategy
            r1.<init>()
        L63:
            androidx.test.runner.internal.deps.desugar.ThrowableExtension.STRATEGY = r1
            if (r0 != 0) goto L69
            r0 = 1
            goto L6d
        L69:
            int r0 = r0.intValue()
        L6d:
            androidx.test.runner.internal.deps.desugar.ThrowableExtension.API_LEVEL = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.test.runner.internal.deps.desugar.ThrowableExtension.<clinit>():void");
    }

    public static AbstractDesugaringStrategy getStrategy() {
        return STRATEGY;
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        STRATEGY.addSuppressed(th, th2);
    }

    public static Throwable[] getSuppressed(Throwable th) {
        return STRATEGY.getSuppressed(th);
    }

    public static void printStackTrace(Throwable th) {
        STRATEGY.printStackTrace(th);
    }

    public static void printStackTrace(Throwable th, PrintWriter printWriter) {
        STRATEGY.printStackTrace(th, printWriter);
    }

    public static void printStackTrace(Throwable th, PrintStream printStream) {
        STRATEGY.printStackTrace(th, printStream);
    }

    public static void closeResource(Throwable th, Object obj) throws Throwable {
        if (obj == null) {
            return;
        }
        try {
            if (API_LEVEL >= 19) {
                ((AutoCloseable) obj).close();
                return;
            }
            if (obj instanceof Closeable) {
                ((Closeable) obj).close();
                return;
            }
            try {
                obj.getClass().getMethod("close", new Class[0]).invoke(obj, new Object[0]);
            } catch (ExceptionInInitializerError e) {
                e = e;
                String strValueOf = String.valueOf(obj.getClass());
                throw new AssertionError(new StringBuilder(String.valueOf(strValueOf).length() + 24).append("Fail to call close() on ").append(strValueOf).toString(), e);
            } catch (IllegalAccessException e2) {
                e = e2;
                String strValueOf2 = String.valueOf(obj.getClass());
                throw new AssertionError(new StringBuilder(String.valueOf(strValueOf2).length() + 24).append("Fail to call close() on ").append(strValueOf2).toString(), e);
            } catch (IllegalArgumentException e3) {
                e = e3;
                String strValueOf22 = String.valueOf(obj.getClass());
                throw new AssertionError(new StringBuilder(String.valueOf(strValueOf22).length() + 24).append("Fail to call close() on ").append(strValueOf22).toString(), e);
            } catch (NoSuchMethodException e4) {
                e = e4;
                String strValueOf3 = String.valueOf(obj.getClass());
                throw new AssertionError(new StringBuilder(String.valueOf(strValueOf3).length() + 32).append(strValueOf3).append(" does not have a close() method.").toString(), e);
            } catch (SecurityException e5) {
                e = e5;
                String strValueOf32 = String.valueOf(obj.getClass());
                throw new AssertionError(new StringBuilder(String.valueOf(strValueOf32).length() + 32).append(strValueOf32).append(" does not have a close() method.").toString(), e);
            } catch (InvocationTargetException e6) {
                throw e6.getCause();
            }
        } catch (Throwable th2) {
            if (th != null) {
                addSuppressed(th, th2);
                throw th;
            }
            throw th2;
        }
    }

    private static boolean useMimicStrategy() {
        return !Boolean.getBoolean(SYSTEM_PROPERTY_TWR_DISABLE_MIMIC);
    }

    private static Integer readApiLevelFromBuildVersion() {
        try {
            return (Integer) Class.forName(ANDROID_OS_BUILD_VERSION).getField("SDK_INT").get(null);
        } catch (Exception e) {
            System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
            e.printStackTrace(System.err);
            return null;
        }
    }

    static abstract class AbstractDesugaringStrategy {
        protected static final Throwable[] EMPTY_THROWABLE_ARRAY = new Throwable[0];

        public abstract void addSuppressed(Throwable th, Throwable th2);

        public abstract Throwable[] getSuppressed(Throwable th);

        public abstract void printStackTrace(Throwable th);

        public abstract void printStackTrace(Throwable th, PrintStream printStream);

        public abstract void printStackTrace(Throwable th, PrintWriter printWriter);

        AbstractDesugaringStrategy() {
        }
    }

    static final class ReuseDesugaringStrategy extends AbstractDesugaringStrategy {
        ReuseDesugaringStrategy() {
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void addSuppressed(Throwable th, Throwable th2) {
            th.addSuppressed(th2);
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public Throwable[] getSuppressed(Throwable th) {
            return th.getSuppressed();
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th) {
            th.printStackTrace();
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }

    static final class MimicDesugaringStrategy extends AbstractDesugaringStrategy {
        static final String SUPPRESSED_PREFIX = "Suppressed: ";
        private final ConcurrentWeakIdentityHashMap map = new ConcurrentWeakIdentityHashMap();

        MimicDesugaringStrategy() {
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void addSuppressed(Throwable th, Throwable th2) {
            if (th2 == th) {
                throw new IllegalArgumentException("Self suppression is not allowed.", th2);
            }
            if (th2 == null) {
                throw new NullPointerException("The suppressed exception cannot be null.");
            }
            this.map.get(th, true).add(th2);
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public Throwable[] getSuppressed(Throwable th) {
            List<Throwable> list = this.map.get(th, false);
            if (list == null || list.isEmpty()) {
                return EMPTY_THROWABLE_ARRAY;
            }
            return (Throwable[]) list.toArray(EMPTY_THROWABLE_ARRAY);
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th) {
            th.printStackTrace();
            List<Throwable> list = this.map.get(th, false);
            if (list == null) {
                return;
            }
            synchronized (list) {
                for (Throwable th2 : list) {
                    System.err.print(SUPPRESSED_PREFIX);
                    th2.printStackTrace();
                }
            }
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
            List<Throwable> list = this.map.get(th, false);
            if (list == null) {
                return;
            }
            synchronized (list) {
                for (Throwable th2 : list) {
                    printStream.print(SUPPRESSED_PREFIX);
                    th2.printStackTrace(printStream);
                }
            }
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
            List<Throwable> list = this.map.get(th, false);
            if (list == null) {
                return;
            }
            synchronized (list) {
                for (Throwable th2 : list) {
                    printWriter.print(SUPPRESSED_PREFIX);
                    th2.printStackTrace(printWriter);
                }
            }
        }
    }

    static final class ConcurrentWeakIdentityHashMap {
        private final ConcurrentHashMap<WeakKey, List<Throwable>> map = new ConcurrentHashMap<>(16, 0.75f, 10);
        private final ReferenceQueue<Throwable> referenceQueue = new ReferenceQueue<>();

        ConcurrentWeakIdentityHashMap() {
        }

        public List<Throwable> get(Throwable th, boolean z) {
            deleteEmptyKeys();
            List<Throwable> list = this.map.get(new WeakKey(th, null));
            if (!z || list != null) {
                return list;
            }
            Vector vector = new Vector(2);
            List<Throwable> listPutIfAbsent = this.map.putIfAbsent(new WeakKey(th, this.referenceQueue), vector);
            return listPutIfAbsent == null ? vector : listPutIfAbsent;
        }

        int size() {
            return this.map.size();
        }

        void deleteEmptyKeys() {
            while (true) {
                Reference<? extends Throwable> referencePoll = this.referenceQueue.poll();
                if (referencePoll == null) {
                    return;
                } else {
                    this.map.remove(referencePoll);
                }
            }
        }

        private static final class WeakKey extends WeakReference<Throwable> {
            private final int hash;

            public WeakKey(Throwable th, ReferenceQueue<Throwable> referenceQueue) {
                super(th, referenceQueue);
                if (th == null) {
                    throw new NullPointerException("The referent cannot be null");
                }
                this.hash = System.identityHashCode(th);
            }

            public int hashCode() {
                return this.hash;
            }

            public boolean equals(Object obj) {
                if (obj == null || obj.getClass() != getClass()) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                WeakKey weakKey = (WeakKey) obj;
                return this.hash == weakKey.hash && get() == weakKey.get();
            }
        }
    }

    static final class NullDesugaringStrategy extends AbstractDesugaringStrategy {
        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void addSuppressed(Throwable th, Throwable th2) {
        }

        NullDesugaringStrategy() {
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public Throwable[] getSuppressed(Throwable th) {
            return EMPTY_THROWABLE_ARRAY;
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th) {
            th.printStackTrace();
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th, PrintStream printStream) {
            th.printStackTrace(printStream);
        }

        @Override // androidx.test.runner.internal.deps.desugar.ThrowableExtension.AbstractDesugaringStrategy
        public void printStackTrace(Throwable th, PrintWriter printWriter) {
            th.printStackTrace(printWriter);
        }
    }
}
