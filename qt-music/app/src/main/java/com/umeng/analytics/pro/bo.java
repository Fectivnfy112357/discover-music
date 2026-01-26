package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.debug.UMRTLog;

/* compiled from: CacheDBHelper.java */
/* loaded from: classes3.dex */
public class bo extends SQLiteOpenHelper {
    private static final Object b = new Object();
    private static bo c = null;
    private static final String d = "CREATE TABLE IF NOT EXISTS stf(_id INTEGER PRIMARY KEY AUTOINCREMENT, _tp TEXT, _hd TEXT, _bd TEXT, _ts TEXT, _uuid TEXT, _re1 TEXT, _re2 TEXT)";
    private static final String e = "DROP TABLE IF EXISTS stf";
    private static final String f = "DELETE FROM stf WHERE _id IN( SELECT _id FROM stf ORDER BY _id LIMIT 1)";
    private final Context a;

    public static final int a() {
        return 1;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static bo a(Context context) {
        bo boVar;
        synchronized (b) {
            if (c == null) {
                c = new bo(context, bq.b, null, 1);
            }
            boVar = c;
        }
        return boVar;
    }

    private bo(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.a = context;
    }

    private void a(SQLiteDatabase sQLiteDatabase) throws SQLException {
        try {
            sQLiteDatabase.execSQL(e);
            sQLiteDatabase.execSQL(d);
        } catch (SQLException unused) {
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) throws SQLException {
        try {
            sQLiteDatabase.execSQL(d);
        } catch (SQLiteDatabaseCorruptException unused) {
            a(sQLiteDatabase);
        } catch (Throwable th) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [有状态]创建二级缓存数据库失败: " + th.getMessage());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) throws SQLException {
        b(sQLiteDatabase);
    }

    public void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return;
            }
            writableDatabase.close();
        } catch (Throwable unused) {
        }
    }

    public void a(String str, ContentValues contentValues) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return;
            }
            try {
                writableDatabase.beginTransaction();
                writableDatabase.insert(str, null, contentValues);
                writableDatabase.setTransactionSuccessful();
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [有状态]插入二级缓存数据记录 成功。");
                if (writableDatabase == null) {
                    return;
                }
            } catch (Throwable unused) {
                if (writableDatabase == null) {
                    return;
                }
            }
            writableDatabase.endTransaction();
            writableDatabase.close();
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, String str2, String[] strArr) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return;
            }
            try {
                writableDatabase.beginTransaction();
                writableDatabase.delete(str, str2, strArr);
                writableDatabase.setTransactionSuccessful();
                if (writableDatabase == null) {
                    return;
                }
            } catch (Throwable unused) {
                if (writableDatabase == null) {
                    return;
                }
            }
            writableDatabase.endTransaction();
            writableDatabase.close();
        } catch (Throwable unused2) {
        }
    }

    private void d() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return;
            }
            try {
                writableDatabase.execSQL(f);
                if (writableDatabase == null) {
                    return;
                }
            } catch (Throwable unused) {
                if (writableDatabase == null) {
                    return;
                }
            }
            writableDatabase.close();
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.umeng.analytics.pro.bp a(java.lang.String r19) {
        /*
            r18 = this;
            r10 = r18
            r0 = 6
            java.lang.String[] r3 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "_uuid"
            r12 = 0
            r3[r12] = r0     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "_tp"
            r13 = 1
            r3[r13] = r0     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "_hd"
            r14 = 2
            r3[r14] = r0     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "_bd"
            r15 = 3
            r3[r15] = r0     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "_re1"
            r9 = 4
            r3[r9] = r0     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "_re2"
            r8 = 5
            r3[r8] = r0     // Catch: java.lang.Throwable -> L8d
            java.lang.String r0 = "1"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r16 = 0
            r1 = r18
            r2 = r19
            r11 = r8
            r8 = r16
            r11 = r9
            r9 = r0
            android.database.Cursor r0 = r1.a(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L8d
            if (r0 == 0) goto L86
            boolean r1 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L84
            if (r1 == 0) goto L86
            com.umeng.analytics.pro.bp r1 = new com.umeng.analytics.pro.bp     // Catch: java.lang.Throwable -> L84
            r1.<init>()     // Catch: java.lang.Throwable -> L84
            java.lang.String r2 = r0.getString(r12)     // Catch: java.lang.Throwable -> L80
            r1.a = r2     // Catch: java.lang.Throwable -> L80
            java.lang.String r2 = r0.getString(r13)     // Catch: java.lang.Throwable -> L80
            r1.b = r2     // Catch: java.lang.Throwable -> L80
            java.lang.String r2 = r0.getString(r14)     // Catch: java.lang.Throwable -> L80
            java.lang.String r3 = r0.getString(r15)     // Catch: java.lang.Throwable -> L80
            android.content.Context r4 = r10.a     // Catch: java.lang.Throwable -> L80
            com.umeng.analytics.pro.k r4 = com.umeng.analytics.pro.k.a(r4)     // Catch: java.lang.Throwable -> L80
            java.lang.String r2 = r4.d(r2)     // Catch: java.lang.Throwable -> L80
            r1.c = r2     // Catch: java.lang.Throwable -> L80
            android.content.Context r2 = r10.a     // Catch: java.lang.Throwable -> L80
            com.umeng.analytics.pro.k r2 = com.umeng.analytics.pro.k.a(r2)     // Catch: java.lang.Throwable -> L80
            java.lang.String r2 = r2.d(r3)     // Catch: java.lang.Throwable -> L80
            r1.d = r2     // Catch: java.lang.Throwable -> L80
            java.lang.String r2 = r0.getString(r11)     // Catch: java.lang.Throwable -> L80
            r1.e = r2     // Catch: java.lang.Throwable -> L80
            r2 = 5
            java.lang.String r2 = r0.getString(r2)     // Catch: java.lang.Throwable -> L80
            r1.f = r2     // Catch: java.lang.Throwable -> L80
            r11 = r1
            goto L87
        L80:
            r11 = r0
            r17 = r1
            goto L90
        L84:
            r11 = r0
            goto L8e
        L86:
            r11 = 0
        L87:
            if (r0 == 0) goto L9a
            r0.close()
            goto L9a
        L8d:
            r11 = 0
        L8e:
            r17 = 0
        L90:
            r18.d()     // Catch: java.lang.Throwable -> L9b
            if (r11 == 0) goto L98
            r11.close()
        L98:
            r11 = r17
        L9a:
            return r11
        L9b:
            r0 = move-exception
            r1 = r0
            if (r11 == 0) goto La2
            r11.close()
        La2:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.bo.a(java.lang.String):com.umeng.analytics.pro.bp");
    }

    public void a(String str, String str2) {
        a(str, "_uuid=?", new String[]{str2});
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return null;
            }
            return writableDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        } catch (Throwable unused) {
            return null;
        }
    }

    public boolean b(String str) {
        SQLiteDatabase writableDatabase;
        Cursor cursorQuery = null;
        try {
            writableDatabase = getWritableDatabase();
            if (writableDatabase != null) {
                try {
                    if (writableDatabase.isOpen()) {
                        cursorQuery = writableDatabase.query(str, null, null, null, null, null, null, null);
                    }
                } catch (Throwable unused) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    if (writableDatabase == null) {
                        return false;
                    }
                    writableDatabase.close();
                    return false;
                }
            }
            if (cursorQuery != null) {
                if (cursorQuery.getCount() > 0) {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    if (writableDatabase == null) {
                        return true;
                    }
                    writableDatabase.close();
                    return true;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            if (writableDatabase == null) {
                return false;
            }
        } catch (Throwable unused2) {
            writableDatabase = null;
        }
        writableDatabase.close();
        return false;
    }

    public boolean c() {
        return !b(bq.c);
    }
}
