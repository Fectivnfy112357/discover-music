package androidx.test.rule.provider;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.test.internal.util.Checks;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
class DelegatingContext extends ContextWrapper {
    private static final int NO_OP_PID = -1;
    private static final int NO_OP_UID = -1;
    private static final String TAG = "DelegatingContext";
    private final ContentResolver contentResolver;
    private final Context context;
    private Set<String> databases;
    private Set<String> files;
    private final String prefix;
    private Set<String> revokedPermissions;

    @Override // android.content.ContextWrapper, android.content.Context
    public Context getApplicationContext() {
        return this;
    }

    public DelegatingContext(Context context, String prefix, ContentResolver contentResolver) {
        super((Context) Checks.checkNotNull(context));
        this.databases = new HashSet();
        this.files = new HashSet();
        this.revokedPermissions = new HashSet();
        this.context = context;
        this.prefix = (String) Checks.checkNotNull(prefix);
        this.contentResolver = (ContentResolver) Checks.checkNotNull(contentResolver);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ContentResolver getContentResolver() {
        return this.contentResolver;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDir(String name, int mode) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Directory name cannot be empty or null");
        return this.context.getDir(getPrefixName(name), mode);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Database name cannot be empty or null");
        if (!this.databases.contains(name)) {
            addDatabase(name);
            String prefixName = getPrefixName(name);
            if (this.context.getDatabasePath(prefixName).exists() && !this.context.deleteDatabase(prefixName)) {
                Log.w(TAG, new StringBuilder(String.valueOf(prefixName).length() + 65).append("Database with prefixed name ").append(prefixName).append(" already exists but failed to delete.").toString());
            }
        }
        return this.context.openOrCreateDatabase(getPrefixName(name), mode, factory);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Database name cannot be empty or null");
        String prefixName = getPrefixName(name);
        if (!this.databases.contains(name)) {
            addDatabase(name);
            if (this.context.getDatabasePath(prefixName).exists() && !this.context.deleteDatabase(prefixName)) {
                Log.w(TAG, new StringBuilder(String.valueOf(prefixName).length() + 66).append("Database with prefixed name ").append(prefixName).append(" already exists and cannot be deleted.").toString());
            }
        }
        return this.context.openOrCreateDatabase(prefixName, mode, factory, errorHandler);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String[] databaseList() {
        Set<String> set = this.databases;
        return (String[]) set.toArray(new String[set.size()]);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean deleteDatabase(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Database name cannot be empty or null");
        if (!this.databases.contains(name) || !this.context.deleteDatabase(getPrefixName(name))) {
            return false;
        }
        this.databases.remove(name);
        return true;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getDatabasePath(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Database name cannot be empty or null");
        return this.context.getDatabasePath(getPrefixName(name));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public FileInputStream openFileInput(String name) throws FileNotFoundException {
        Checks.checkArgument(!TextUtils.isEmpty(name), "File name cannot be empty or null");
        if (!this.files.contains(name)) {
            throw new FileNotFoundException(String.format("File %s is not found in current context", name));
        }
        return this.context.openFileInput(getPrefixName(name));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public FileOutputStream openFileOutput(String name, int mode) throws FileNotFoundException {
        Checks.checkArgument(!TextUtils.isEmpty(name), "File name cannot be empty or null");
        FileOutputStream fileOutputStreamOpenFileOutput = this.context.openFileOutput(getPrefixName(name), mode);
        if (fileOutputStreamOpenFileOutput != null) {
            this.files.add(name);
        }
        return fileOutputStreamOpenFileOutput;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String[] fileList() {
        Set<String> set = this.files;
        return (String[]) set.toArray(new String[set.size()]);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getFileStreamPath(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "File name cannot be empty or null");
        return this.context.getFileStreamPath(getPrefixName(name));
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean deleteFile(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "File name cannot be empty or null");
        if (!this.files.contains(name) || !this.context.deleteFile(getPrefixName(name))) {
            return false;
        }
        this.files.remove(name);
        return true;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "name cannot be empty or null");
        if ("appops".equals(name)) {
            return this.context.getSystemService("appops");
        }
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkPermission(String permission, int pid, int uid) {
        Checks.checkArgument(!TextUtils.isEmpty(permission), "permission cannot be null or empty");
        return this.revokedPermissions.contains(permission) ? -1 : 0;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkCallingPermission(String permission) {
        return checkPermission(permission, -1, -1);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkCallingOrSelfPermission(String permission) {
        return checkPermission(permission, -1, -1);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkSelfPermission(String permission) {
        return checkPermission(permission, -1, -1);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforcePermission(String permission, int pid, int uid, String message) {
        if (checkPermission(permission, pid, uid) != 0) {
            String strConcat = message != null ? String.valueOf(message).concat(": ") : "";
            throw new SecurityException(new StringBuilder(String.valueOf(strConcat).length() + 14 + String.valueOf(permission).length()).append(strConcat).append("No permission ").append(permission).toString());
        }
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforceCallingPermission(String permission, String message) {
        enforcePermission(permission, -1, -1, message);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforceCallingOrSelfPermission(String permission, String message) {
        enforcePermission(permission, -1, -1, message);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkUriPermission(Uri uri, int pid, int uid, int modeFlags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkCallingUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkCallingOrSelfUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int checkUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforceUriPermission(Uri uri, int pid, int uid, int modeFlags, String message) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforceCallingUriPermission(Uri uri, int modeFlags, String message) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforceCallingOrSelfUriPermission(Uri uri, int modeFlags, String message) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void enforceUriPermission(Uri uri, String readPermission, String writePermission, int pid, int uid, int modeFlags, String message) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getFilesDir() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getNoBackupFilesDir() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getExternalFilesDir(String s) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getObbDir() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getObbDirs() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getCacheDir() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getCodeCacheDir() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File getExternalCacheDir() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getExternalCacheDirs() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getExternalMediaDirs() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public File[] getExternalFilesDirs(String s) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public PackageManager getPackageManager() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Looper getMainLooper() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int resID) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ClassLoader getClassLoader() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageName() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ApplicationInfo getApplicationInfo() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageResourcePath() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getPackageCodePath() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendBroadcast(Intent intent, String s) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendBroadcast(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendOrderedBroadcast(Intent intent, String s, BroadcastReceiver broadcastReceiver, Handler handler, int i, String s1, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendOrderedBroadcast(Intent intent, String s) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, String s, BroadcastReceiver broadcastReceiver, Handler handler, int i, String s1, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendBroadcastAsUser(Intent intent, UserHandle userHandle, String s) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendStickyBroadcast(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastReceiver, Handler handler, int i, String s, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, BroadcastReceiver broadcastReceiver, Handler handler, int i, String s, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void removeStickyBroadcast(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void removeStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void grantUriPermission(String toPackage, Uri uri, int modeFlags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void revokeUriPermission(Uri uri, int modeFlags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public SharedPreferences getSharedPreferences(String name, int mode) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Drawable getWallpaper() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Drawable peekWallpaper() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int getWallpaperDesiredMinimumHeight() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public int getWallpaperDesiredMinimumWidth() {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setWallpaper(Bitmap bitmap) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setWallpaper(InputStream inputStream) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void clearWallpaper() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intents) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intents, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startIntentSender(IntentSender intentSender, Intent intent, int i, int i1, int i2) throws IntentSender.SendIntentException {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void startIntentSender(IntentSender intentSender, Intent intent, int i, int i1, int i2, Bundle bundle) throws IntentSender.SendIntentException {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Intent registerReceiver(BroadcastReceiver receiver, IntentFilter filter) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String s, Handler handler) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unregisterReceiver(BroadcastReceiver receiver) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public ComponentName startService(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean stopService(Intent intent) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void unbindService(ServiceConnection serviceConnection) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean startInstrumentation(ComponentName componentName, String s, Bundle bundle) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public String getSystemServiceName(Class<?> aClass) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createPackageContext(String packageName, int flags) throws PackageManager.NameNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createConfigurationContext(Configuration overrideConfiguration) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Context createDisplayContext(Display display) {
        throw new UnsupportedOperationException();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public boolean isRestricted() {
        throw new UnsupportedOperationException();
    }

    boolean addDatabase(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Database name cannot be empty or null");
        return this.databases.add(name);
    }

    void addRevokedPermission(String permission) {
        Checks.checkArgument(!TextUtils.isEmpty(permission), "permission cannot be null or empty");
        this.revokedPermissions.add(permission);
    }

    private String getPrefixName(String name) {
        Checks.checkArgument(!TextUtils.isEmpty(name), "Name cannot be empty or null");
        String strValueOf = String.valueOf(this.prefix);
        String strValueOf2 = String.valueOf(name);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }
}
