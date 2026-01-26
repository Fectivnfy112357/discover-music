package androidx.test.rule.provider;

import android.util.Log;
import java.io.File;

/* loaded from: classes.dex */
final class DatabaseArgs {
    private static final String TAG = "DatabaseArgs";
    private File dBCmdFile;
    private String[] dBCmds;
    private File dBDataFile;
    private String dBName;

    public DatabaseArgs(String dbName) {
        this.dBName = dbName;
    }

    public void setDBCmds(String... dbCmds) {
        if (this.dBCmds != null) {
            Log.w(TAG, String.format("Commands for database %s already set", this.dBName));
        }
        this.dBCmds = dbCmds;
    }

    public void setDBCmdFile(File dbCmdFile) {
        if (this.dBCmdFile != null) {
            Log.w(TAG, String.format("Command file for database %s already set", this.dBName));
        }
        this.dBCmdFile = dbCmdFile;
    }

    public void setDBDataFile(File dbDataFile) {
        if (this.dBDataFile != null) {
            Log.w(TAG, String.format("Data file to restore for database %s already set", this.dBName));
        }
        this.dBDataFile = dbDataFile;
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

    public boolean hasDBCmds() {
        return this.dBCmds != null;
    }

    public boolean hasDBCmdFile() {
        return this.dBCmdFile != null;
    }

    public boolean hasDBDataFile() {
        return this.dBDataFile != null;
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
}
