package androidx.test.rule.provider;

import android.content.ContentProvider;
import android.util.Log;
import java.io.File;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
final class ProviderArgs {
    private static final String TAG = "ProviderArgs";
    private final String authority;
    private File dBCmdFile;
    private String[] dBCmds;
    private File dBDataFile;
    private String dBName;
    private final Class<? extends ContentProvider> providerClass;
    private WeakReference<ContentProvider> providerRef;

    public ProviderArgs(String authority, Class<? extends ContentProvider> providerClass) {
        this.authority = authority;
        this.providerClass = providerClass;
    }

    public void setDBName(String dbName) {
        if (this.dBName != null) {
            Log.w(TAG, String.format("Database name for ContentProvider with authority %s already exists", this.authority));
        }
        this.dBName = dbName;
    }

    public void setDBCmds(String... dbCmds) {
        if (this.dBCmds != null) {
            Log.w(TAG, String.format("Database commands for ContentProvider with authority %s already set", this.authority));
        }
        this.dBCmds = dbCmds;
    }

    public void setDBCmdFile(File dbCmdFile) {
        if (this.dBCmdFile != null) {
            Log.w(TAG, String.format("Database command file for ContentProvider with authority %s already set", this.authority));
        }
        this.dBCmdFile = dbCmdFile;
    }

    public void setDBDataFile(File dbDataFile) {
        if (this.dBDataFile != null) {
            Log.w(TAG, String.format("Database file to restore for ContentProvider with authority %s already set", this.authority));
        }
        this.dBDataFile = dbDataFile;
    }

    public void setProviderRef(ContentProvider provider) {
        if (this.providerRef != null) {
            Log.w(TAG, String.format("Reference to Provider instance with authority %s already set", this.authority));
        }
        this.providerRef = new WeakReference<>(provider);
    }

    public void addDBCmds(String... dbCmds) {
        String[] strArr = this.dBCmds;
        if (strArr == null) {
            this.dBCmds = dbCmds;
            return;
        }
        String[] strArr2 = new String[strArr.length + dbCmds.length];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        System.arraycopy(dbCmds, 0, strArr2, this.dBCmds.length, dbCmds.length);
        this.dBCmds = strArr2;
    }

    public boolean hasDBName() {
        return this.dBName != null;
    }

    public boolean hasDBCmds() {
        return this.dBCmds != null;
    }

    public boolean hasDBCmdFile() {
        return this.dBCmdFile != null;
    }

    public boolean hasDBDataFile() {
        return this.dBDataFile != null;
    }

    public Class<? extends ContentProvider> getProviderClass() {
        return this.providerClass;
    }

    public String getAuthority() {
        return this.authority;
    }

    public String getDBName() {
        return this.dBName;
    }

    public String[] getDBCmds() {
        return this.dBCmds;
    }

    public File getDBCmdFile() {
        return this.dBCmdFile;
    }

    public File getDBDataFile() {
        return this.dBDataFile;
    }

    public ContentProvider getProvider() {
        WeakReference<ContentProvider> weakReference = this.providerRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }
}
