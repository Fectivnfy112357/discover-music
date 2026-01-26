package androidx.test.internal.runner;

import android.app.Instrumentation;
import dalvik.system.DexFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public class ClassPathScanner {
    private static final String TAG = "ClassPathScanner";
    private final Set<String> classPath;

    public static class AcceptAllFilter implements ClassNameFilter {
        @Override // androidx.test.internal.runner.ClassPathScanner.ClassNameFilter
        public boolean accept(String className) {
            return true;
        }
    }

    public interface ClassNameFilter {
        boolean accept(String className);
    }

    public static class ChainedClassNameFilter implements ClassNameFilter {
        private final List<ClassNameFilter> filters = new ArrayList();

        public void add(ClassNameFilter filter) {
            this.filters.add(filter);
        }

        public void addAll(ClassNameFilter... filters) {
            this.filters.addAll(Arrays.asList(filters));
        }

        @Override // androidx.test.internal.runner.ClassPathScanner.ClassNameFilter
        public boolean accept(String className) {
            Iterator<ClassNameFilter> it = this.filters.iterator();
            while (it.hasNext()) {
                if (!it.next().accept(className)) {
                    return false;
                }
            }
            return true;
        }
    }

    public static class ExternalClassNameFilter implements ClassNameFilter {
        @Override // androidx.test.internal.runner.ClassPathScanner.ClassNameFilter
        public boolean accept(String pathName) {
            return !pathName.contains("$");
        }
    }

    public static class InclusivePackageNamesFilter implements ClassNameFilter {
        private final Collection<String> pkgNames;

        InclusivePackageNamesFilter(Collection<String> pkgNames) {
            this.pkgNames = new ArrayList(pkgNames.size());
            for (String str : pkgNames) {
                if (!str.endsWith(".")) {
                    this.pkgNames.add(String.format("%s.", str));
                } else {
                    this.pkgNames.add(str);
                }
            }
        }

        @Override // androidx.test.internal.runner.ClassPathScanner.ClassNameFilter
        public boolean accept(String pathName) {
            Iterator<String> it = this.pkgNames.iterator();
            while (it.hasNext()) {
                if (pathName.startsWith(it.next())) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class ExcludePackageNameFilter implements ClassNameFilter {
        private final String pkgName;

        ExcludePackageNameFilter(String pkgName) {
            if (!pkgName.endsWith(".")) {
                this.pkgName = String.format("%s.", pkgName);
            } else {
                this.pkgName = pkgName;
            }
        }

        @Override // androidx.test.internal.runner.ClassPathScanner.ClassNameFilter
        public boolean accept(String pathName) {
            return !pathName.startsWith(this.pkgName);
        }
    }

    static class ExcludeClassNamesFilter implements ClassNameFilter {
        private final Set<String> excludedClassNames;

        public ExcludeClassNamesFilter(Set<String> excludedClassNames) {
            this.excludedClassNames = excludedClassNames;
        }

        @Override // androidx.test.internal.runner.ClassPathScanner.ClassNameFilter
        public boolean accept(String className) {
            return !this.excludedClassNames.contains(className);
        }
    }

    public ClassPathScanner(String... paths) {
        this(Arrays.asList(paths));
    }

    public ClassPathScanner(Collection<String> paths) {
        HashSet hashSet = new HashSet();
        this.classPath = hashSet;
        hashSet.addAll(paths);
    }

    public static Collection<String> getDefaultClasspaths(Instrumentation instrumentation) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(instrumentation.getContext().getPackageCodePath());
        return arrayList;
    }

    private void addEntriesFromPath(Set<String> entryNames, String path, ClassNameFilter filter) throws IOException {
        DexFile dexFileLoadDex;
        DexFile dexFile = null;
        try {
            try {
                dexFileLoadDex = new DexFile(path);
            } catch (IOException e) {
                if (path.endsWith(".zip")) {
                    dexFileLoadDex = DexFile.loadDex(path, String.valueOf(path.substring(0, path.length() - 3)).concat("dex"), 0);
                } else {
                    throw e;
                }
            }
            Enumeration<String> enumerationEntries = dexFileLoadDex.entries();
            while (enumerationEntries.hasMoreElements()) {
                String strNextElement = enumerationEntries.nextElement();
                if (filter.accept(strNextElement)) {
                    entryNames.add(strNextElement);
                }
            }
            if (dexFileLoadDex != null) {
                dexFileLoadDex.close();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                dexFile.close();
            }
            throw th;
        }
    }

    public Set<String> getClassPathEntries(ClassNameFilter filter) throws IOException {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<String> it = this.classPath.iterator();
        while (it.hasNext()) {
            addEntriesFromPath(linkedHashSet, it.next(), filter);
        }
        return linkedHashSet;
    }
}
