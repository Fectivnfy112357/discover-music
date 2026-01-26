package androidx.test.rule.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.mock.MockContentResolver;
import android.text.TextUtils;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class ProviderTestRule implements TestRule {
    private static final String TAG = "ProviderTestRule";
    private final DelegatingContext context;
    private final Set<DatabaseArgs> databaseArgsSet;
    private final Set<WeakReference<ContentProvider>> providersRef;
    private final ContentResolver resolver;

    protected void afterProviderCleanedUp() {
    }

    protected void beforeProviderSetup() {
    }

    ProviderTestRule(Set<WeakReference<ContentProvider>> providersRef, Set<DatabaseArgs> databaseArgsSet, ContentResolver resolver, DelegatingContext context) {
        this.providersRef = providersRef;
        this.databaseArgsSet = databaseArgsSet;
        this.resolver = resolver;
        this.context = context;
    }

    public ContentResolver getResolver() {
        return this.resolver;
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(Statement base, Description description) {
        return new ProviderStatement(base);
    }

    public void runDatabaseCommands(String dbName, String... dbCmds) throws SQLException {
        Checks.checkNotNull(dbName);
        Checks.checkNotNull(dbCmds);
        if (dbCmds.length > 0) {
            SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = this.context.openOrCreateDatabase(dbName, 0, null);
            for (String str : dbCmds) {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.execSQL(str);
                    } catch (SQLiteException e) {
                        Log.e(TAG, String.format("Error executing sql command %s, possibly wrong or duplicated commands (e.g. same table insertion command without checking current table existence).", str));
                        throw e;
                    }
                }
            }
        }
    }

    public void revokePermission(String permission) {
        Checks.checkArgument(!TextUtils.isEmpty(permission), "permission cannot be null or empty");
        this.context.addRevokedPermission(permission);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpProviders() throws Throwable {
        beforeProviderSetup();
        Iterator<DatabaseArgs> it = this.databaseArgsSet.iterator();
        while (it.hasNext()) {
            setUpProvider(it.next());
        }
    }

    private void setUpProvider(DatabaseArgs databaseArgs) throws Throwable {
        if (databaseArgs.hasDBDataFile()) {
            restoreDBDataFromFile(databaseArgs);
        }
        if (databaseArgs.hasDBCmdFile()) {
            collectDBCmdsFromFile(databaseArgs);
        }
        if (databaseArgs.hasDBCmds()) {
            runDatabaseCommands(databaseArgs.getDBName(), databaseArgs.getDBCmds());
        }
    }

    private void restoreDBDataFromFile(DatabaseArgs databaseArgs) throws IOException {
        File dBDataFile = databaseArgs.getDBDataFile();
        Checks.checkState(dBDataFile.exists(), String.format("The database file %s doesn't exist!", dBDataFile));
        String dBName = databaseArgs.getDBName();
        copyFile(dBDataFile, this.context.getDatabasePath(dBName));
        this.context.addDatabase(dBName);
    }

    private void collectDBCmdsFromFile(DatabaseArgs databaseArgs) throws Throwable {
        File dBCmdFile = databaseArgs.getDBCmdFile();
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = null;
        try {
            try {
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(dBCmdFile), Charset.forName("UTF-8")));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line != null) {
                            if (!TextUtils.isEmpty(line)) {
                                arrayList.add(line);
                            }
                        } else {
                            bufferedReader2.close();
                            databaseArgs.addDBCmds((String[]) arrayList.toArray(new String[arrayList.size()]));
                            return;
                        }
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        Log.e(TAG, String.format("Cannot open command file %s to read", dBCmdFile));
                        throw e;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    private void copyFile(File src, File dest) throws IOException {
        File parentFile = dest.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            String str = String.format("error happened creating parent dir for file %s", dest);
            Log.e(TAG, str);
            throw new IOException(str);
        }
        FileChannel channel = new FileInputStream(src).getChannel();
        FileChannel channel2 = new FileOutputStream(dest).getChannel();
        try {
            try {
                channel.transferTo(0L, channel.size(), channel2);
            } catch (IOException e) {
                Log.e(TAG, String.format("error happened copying file from %s to %s", src, dest));
                throw e;
            }
        } finally {
            channel.close();
            channel2.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanUpProviders() {
        Iterator<WeakReference<ContentProvider>> it = this.providersRef.iterator();
        while (it.hasNext()) {
            ContentProvider contentProvider = it.next().get();
            if (contentProvider != null) {
                contentProvider.shutdown();
            }
        }
        Iterator<DatabaseArgs> it2 = this.databaseArgsSet.iterator();
        while (it2.hasNext()) {
            String dBName = it2.next().getDBName();
            if (dBName != null) {
                this.context.deleteDatabase(dBName);
            }
        }
        afterProviderCleanedUp();
    }

    public static class Builder {
        private static final String DEFAULT_PREFIX = "test.";
        private final Map<String, DatabaseArgs> databaseArgsMap;
        private String prefix;
        private final Map<String, Class<? extends ContentProvider>> providerClasses;

        public <T extends ContentProvider> Builder(Class<T> providerClass, String providerAuth) {
            HashMap map = new HashMap();
            this.providerClasses = map;
            this.databaseArgsMap = new HashMap();
            this.prefix = DEFAULT_PREFIX;
            Checks.checkNotNull(providerClass);
            Checks.checkNotNull(providerAuth);
            map.put(providerAuth, providerClass);
        }

        public Builder setPrefix(String prefix) {
            Checks.checkArgument(!TextUtils.isEmpty(prefix), "The prefix cannot be null or empty");
            this.prefix = prefix;
            return this;
        }

        public Builder setDatabaseFile(String dbName, File dbDataFile) {
            Checks.checkNotNull(dbName);
            Checks.checkNotNull(dbDataFile);
            getDatabaseArgs(dbName).setDBDataFile(dbDataFile);
            return this;
        }

        public Builder setDatabaseCommands(String dbName, String... dbCmds) {
            Checks.checkNotNull(dbName);
            Checks.checkNotNull(dbCmds);
            getDatabaseArgs(dbName).setDBCmds(dbCmds);
            return this;
        }

        public Builder setDatabaseCommandsFile(String dbName, File dbCmdFile) {
            Checks.checkNotNull(dbName);
            Checks.checkNotNull(dbCmdFile);
            getDatabaseArgs(dbName).setDBCmdFile(dbCmdFile);
            return this;
        }

        public <T extends ContentProvider> Builder addProvider(Class<T> providerClass, String providerAuth) {
            Checks.checkNotNull(providerClass);
            Checks.checkNotNull(providerAuth);
            Checks.checkState(this.providerClasses.size() > 0, "No existing provider yet while trying to add more");
            Checks.checkState(!this.providerClasses.containsKey(providerAuth), String.format("ContentProvider with authority %s already exists.", providerAuth));
            this.providerClasses.put(providerAuth, providerClass);
            return this;
        }

        public ProviderTestRule build() {
            HashSet hashSet = new HashSet();
            MockContentResolver mockContentResolver = new MockContentResolver();
            DelegatingContext delegatingContext = new DelegatingContext(InstrumentationRegistry.getInstrumentation().getTargetContext(), this.prefix, mockContentResolver);
            for (Map.Entry<String, Class<? extends ContentProvider>> entry : this.providerClasses.entrySet()) {
                hashSet.add(new WeakReference(createProvider(entry.getKey(), entry.getValue(), mockContentResolver, delegatingContext)));
            }
            return new ProviderTestRule(hashSet, new HashSet(this.databaseArgsMap.values()), mockContentResolver, delegatingContext);
        }

        private ContentProvider createProvider(String auth, Class<? extends ContentProvider> clazz, MockContentResolver resolver, Context context) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
            try {
                ContentProvider contentProviderNewInstance = clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
                ProviderInfo providerInfo = new ProviderInfo();
                providerInfo.authority = auth;
                contentProviderNewInstance.attachInfo(context, providerInfo);
                resolver.addProvider(providerInfo.authority, contentProviderNewInstance);
                return contentProviderNewInstance;
            } catch (IllegalAccessException e) {
                String strValueOf = String.valueOf(clazz.toString());
                Log.e(ProviderTestRule.TAG, strValueOf.length() != 0 ? "IllegalAccessException occurred when trying create new Instance for ".concat(strValueOf) : new String("IllegalAccessException occurred when trying create new Instance for "));
                throw new RuntimeException(e);
            } catch (InstantiationException e2) {
                String strValueOf2 = String.valueOf(clazz.toString());
                Log.e(ProviderTestRule.TAG, strValueOf2.length() != 0 ? "InstantiationException occurred when trying create new Instance for ".concat(strValueOf2) : new String("InstantiationException occurred when trying create new Instance for "));
                throw new RuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                String strValueOf3 = String.valueOf(clazz.toString());
                Log.e(ProviderTestRule.TAG, strValueOf3.length() != 0 ? "NoSuchMethodException occurred when trying create new Instance for ".concat(strValueOf3) : new String("NoSuchMethodException occurred when trying create new Instance for "));
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                String strValueOf4 = String.valueOf(clazz.toString());
                Log.e(ProviderTestRule.TAG, strValueOf4.length() != 0 ? "InvocationTargetException occurred when trying create new Instance for ".concat(strValueOf4) : new String("InvocationTargetException occurred when trying create new Instance for "));
                throw new RuntimeException(e4);
            }
        }

        private DatabaseArgs getDatabaseArgs(String dbName) {
            if (this.databaseArgsMap.containsKey(dbName)) {
                return this.databaseArgsMap.get(dbName);
            }
            DatabaseArgs databaseArgs = new DatabaseArgs(dbName);
            this.databaseArgsMap.put(dbName, databaseArgs);
            return databaseArgs;
        }
    }

    private class ProviderStatement extends Statement {
        private final Statement base;

        public ProviderStatement(Statement base) {
            this.base = base;
        }

        @Override // org.junit.runners.model.Statement
        public void evaluate() throws Throwable {
            try {
                ProviderTestRule.this.setUpProviders();
                this.base.evaluate();
            } finally {
                ProviderTestRule.this.cleanUpProviders();
            }
        }
    }
}
