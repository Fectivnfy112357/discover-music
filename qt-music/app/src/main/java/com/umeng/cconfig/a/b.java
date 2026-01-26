package com.umeng.cconfig.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import com.umeng.analytics.pro.g;
import com.umeng.cconfig.b.d;
import com.umeng.commonsdk.statistics.common.ULog;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public final class b {
    private static SQLiteOpenHelper c;
    private static Context d;
    public SQLiteDatabase a;
    private AtomicInteger b;

    static class a {
        private static final b a = new b(0);
    }

    private b() {
        this.b = new AtomicInteger();
    }

    /* synthetic */ b(byte b) {
        this();
    }

    public static b a(Context context) {
        if (d == null && context != null) {
            Context applicationContext = context.getApplicationContext();
            d = applicationContext;
            c = com.umeng.cconfig.a.a.a(applicationContext);
        }
        return a.a;
    }

    public final synchronized SQLiteDatabase a() {
        if (this.b.incrementAndGet() == 1) {
            this.a = c.getWritableDatabase();
        }
        return this.a;
    }

    public final boolean a(String str) {
        SQLiteDatabase sQLiteDatabase;
        try {
            try {
                a();
                this.a.beginTransaction();
                String str2 = "update  __cc set __a=\"1\" where __ts=\"" + str + "\"";
                ULog.i("jessie", "[DbManager] updateCloudConfigByTimestamp：".concat(String.valueOf(str2)));
                this.a.execSQL(str2);
                this.a.setTransactionSuccessful();
                sQLiteDatabase = this.a;
            } catch (SQLiteDatabaseCorruptException e) {
                try {
                    e.printStackTrace();
                    try {
                        SQLiteDatabase sQLiteDatabase2 = this.a;
                        if (sQLiteDatabase2 != null) {
                            sQLiteDatabase2.endTransaction();
                        }
                    } catch (Throwable unused) {
                    }
                    b();
                    return false;
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.endTransaction();
                        }
                    } catch (Throwable unused2) {
                    }
                    b();
                    throw th;
                }
            } catch (Throwable unused3) {
                sQLiteDatabase = this.a;
                if (sQLiteDatabase != null) {
                }
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable unused4) {
        }
        b();
        return true;
    }

    public final synchronized void b() {
        try {
            if (this.b.decrementAndGet() == 0) {
                this.a.close();
            }
        } catch (Throwable unused) {
        }
    }

    public final boolean b(String str) {
        SQLiteDatabase sQLiteDatabase;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                a();
                this.a.beginTransaction();
                Cursor cursorRawQuery = this.a.rawQuery("select *  from __cc where __a=\"1\" group by __ts", null);
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ts"));
                        String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(g.d.a.d));
                        String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
                        new StringBuilder().append(String.format("timeStamp = %s, content = %s, active = %s, id = %s", string, string2, string3, cursorRawQuery.getString(cursorRawQuery.getColumnIndex("id"))));
                        d dVar = new d();
                        dVar.a = string;
                        dVar.b = string2;
                        dVar.c = string3;
                        if (!TextUtils.isEmpty(string) && !str.equals(string)) {
                            arrayList.add(dVar);
                        }
                    }
                }
                int size = arrayList.size();
                String string4 = "";
                int i = 0;
                while (i < size) {
                    d dVar2 = (d) arrayList.get(i);
                    string4 = (i != size + (-1) ? new StringBuilder().append(string4).append("__ts=\"").append(dVar2.a).append("\" or ") : new StringBuilder().append(string4).append("__ts=\"").append(dVar2.a).append("\"")).toString();
                    String str2 = "update  __cc set __a=\"0\" where " + string4;
                    ULog.i("jessie", "[DbManager] updateOtherCloudConfigInfo : ".concat(String.valueOf(str2)));
                    this.a.execSQL(str2);
                    this.a.setTransactionSuccessful();
                    i++;
                }
                sQLiteDatabase = this.a;
            } catch (SQLiteDatabaseCorruptException e) {
                try {
                    e.printStackTrace();
                    try {
                        SQLiteDatabase sQLiteDatabase2 = this.a;
                        if (sQLiteDatabase2 != null) {
                            sQLiteDatabase2.endTransaction();
                        }
                    } catch (Throwable unused) {
                    }
                    b();
                    return false;
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.endTransaction();
                        }
                    } catch (Throwable unused2) {
                    }
                    b();
                    throw th;
                }
            } catch (Throwable unused3) {
                sQLiteDatabase = this.a;
                if (sQLiteDatabase != null) {
                }
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable unused4) {
        }
        b();
        return true;
    }

    public final d c() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursorRawQuery;
        d dVar = new d();
        try {
            try {
                a();
                this.a.beginTransaction();
                cursorRawQuery = this.a.rawQuery("select *  from __cc where __a=\"1\" group by __ts", null);
            } catch (SQLiteDatabaseCorruptException e) {
                try {
                    e.printStackTrace();
                    sQLiteDatabase = this.a;
                    if (sQLiteDatabase != null) {
                    }
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.endTransaction();
                        }
                    } catch (Throwable unused) {
                    }
                    b();
                    throw th;
                }
            } catch (Throwable unused2) {
                sQLiteDatabase = this.a;
                if (sQLiteDatabase != null) {
                }
            }
        } catch (Throwable unused3) {
        }
        if (cursorRawQuery == null || !cursorRawQuery.moveToNext()) {
            this.a.setTransactionSuccessful();
            sQLiteDatabase = this.a;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
            b();
            return dVar;
        }
        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ts"));
        String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(g.d.a.d));
        String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
        ULog.i("jessie", "[DbManager] selectRecentActiveOne".concat(String.valueOf(String.format("timeStamp = %s, content = %s, active = %s, id = %s", string, string2, string3, cursorRawQuery.getString(cursorRawQuery.getColumnIndex("id"))) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)));
        d dVar2 = new d();
        dVar2.a = string;
        dVar2.b = string2;
        dVar2.c = string3;
        try {
            SQLiteDatabase sQLiteDatabase2 = this.a;
            if (sQLiteDatabase2 != null) {
                sQLiteDatabase2.endTransaction();
            }
        } catch (Throwable unused4) {
        }
        b();
        return dVar2;
    }

    public final boolean d() {
        SQLiteDatabase sQLiteDatabase;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                try {
                    a();
                    this.a.beginTransaction();
                    Cursor cursorRawQuery = this.a.rawQuery("select *  from __cc where __a=\"0\" order by __ts asc", null);
                    if (cursorRawQuery != null) {
                        while (cursorRawQuery.moveToNext()) {
                            arrayList.add(cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ts")));
                        }
                    }
                    int size = arrayList.size();
                    int i = size - 5;
                    String string = "";
                    if (i > 0) {
                        int i2 = 0;
                        while (i2 < i) {
                            string = (i2 != size + (-6) ? new StringBuilder().append(string).append("__ts=\"").append((String) arrayList.get(i2)).append("\" or ") : new StringBuilder().append(string).append("__ts=\"").append((String) arrayList.get(i2)).append("\"")).toString();
                            i2++;
                        }
                        String str = "delete from __cc where " + string;
                        ULog.i("jessie", "[DbManager] deleteExtraCloudConfigInfo: ".concat(String.valueOf(str)));
                        this.a.execSQL(str);
                        this.a.setTransactionSuccessful();
                    }
                    sQLiteDatabase = this.a;
                } catch (SQLiteDatabaseCorruptException e) {
                    try {
                        e.printStackTrace();
                        sQLiteDatabase = this.a;
                        if (sQLiteDatabase != null) {
                        }
                    } catch (Throwable th) {
                        try {
                            if (this.a != null) {
                                this.a.endTransaction();
                            }
                        } catch (Throwable unused) {
                        }
                        b();
                        throw th;
                    }
                }
            } catch (Throwable unused2) {
                sQLiteDatabase = this.a;
                if (sQLiteDatabase != null) {
                }
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable unused3) {
        }
        b();
        return false;
    }

    public final d e() {
        SQLiteDatabase sQLiteDatabase;
        d dVar = new d();
        try {
            try {
                a();
                this.a.beginTransaction();
                Cursor cursorRawQuery = this.a.rawQuery("select *  from __cc order by __ts desc", null);
                if (cursorRawQuery != null) {
                    while (cursorRawQuery.moveToNext()) {
                        String string = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__ts"));
                        String string2 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex(g.d.a.d));
                        String string3 = cursorRawQuery.getString(cursorRawQuery.getColumnIndex("__a"));
                        ULog.i("jessie", "[DbManager] getLastestConfigInfo".concat(String.valueOf(String.format("timeStamp = %s, content = %s, active = %s, id = %s", string, string2, string3, cursorRawQuery.getString(cursorRawQuery.getColumnIndex("id"))) + ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE)));
                        dVar.a = string;
                        dVar.b = string2;
                        dVar.c = string3;
                        if (string != null) {
                            try {
                                SQLiteDatabase sQLiteDatabase2 = this.a;
                                if (sQLiteDatabase2 != null) {
                                    sQLiteDatabase2.endTransaction();
                                }
                            } catch (Throwable unused) {
                            }
                            b();
                            return dVar;
                        }
                    }
                }
                this.a.setTransactionSuccessful();
                sQLiteDatabase = this.a;
            } catch (SQLiteDatabaseCorruptException e) {
                try {
                    e.printStackTrace();
                    sQLiteDatabase = this.a;
                    if (sQLiteDatabase != null) {
                    }
                } catch (Throwable th) {
                    try {
                        if (this.a != null) {
                            this.a.endTransaction();
                        }
                    } catch (Throwable unused2) {
                    }
                    b();
                    throw th;
                }
            } catch (Throwable unused3) {
                sQLiteDatabase = this.a;
                if (sQLiteDatabase != null) {
                }
            }
            if (sQLiteDatabase != null) {
                sQLiteDatabase.endTransaction();
            }
        } catch (Throwable unused4) {
        }
        b();
        return dVar;
    }
}
