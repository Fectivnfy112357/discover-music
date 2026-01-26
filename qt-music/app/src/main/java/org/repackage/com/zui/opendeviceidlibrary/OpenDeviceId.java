package org.repackage.com.zui.opendeviceidlibrary;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import org.repackage.com.zui.deviceidservice.IDeviceidInterface;

/* loaded from: classes3.dex */
public class OpenDeviceId {
    private static String c = "OpenDeviceId library";
    private static boolean d = false;
    private IDeviceidInterface b;
    private ServiceConnection e;
    private Context a = null;
    private CallBack f = null;

    public interface CallBack<T> {
        void a(T t, OpenDeviceId openDeviceId);
    }

    public int a(Context context, CallBack<String> callBack) {
        if (context == null) {
            throw new NullPointerException("Context can not be null.");
        }
        this.a = context;
        this.f = callBack;
        this.e = new ServiceConnection() { // from class: org.repackage.com.zui.opendeviceidlibrary.OpenDeviceId.1
            @Override // android.content.ServiceConnection
            public synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                OpenDeviceId.this.b = IDeviceidInterface.Stub.a(iBinder);
                if (OpenDeviceId.this.f != null) {
                    OpenDeviceId.this.f.a("Deviceid Service Connected", OpenDeviceId.this);
                }
                OpenDeviceId.this.a("Service onServiceConnected");
            }

            @Override // android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName componentName) {
                OpenDeviceId.this.b = null;
                OpenDeviceId.this.a("Service onServiceDisconnected");
            }
        };
        Intent intent = new Intent();
        intent.setClassName("org.repackage.com.zui.deviceidservice", "org.repackage.com.zui.deviceidservice.DeviceidService");
        if (this.a.bindService(intent, this.e, 1)) {
            a("bindService Successful!");
            return 1;
        }
        a("bindService Failed!");
        return -1;
    }

    public String a() {
        if (this.a == null) {
            b("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        try {
            IDeviceidInterface iDeviceidInterface = this.b;
            if (iDeviceidInterface != null) {
                return iDeviceidInterface.a();
            }
            return null;
        } catch (RemoteException e) {
            b("getOAID error, RemoteException!");
            e.printStackTrace();
            return null;
        }
    }

    public String b() {
        if (this.a == null) {
            b("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        try {
            IDeviceidInterface iDeviceidInterface = this.b;
            if (iDeviceidInterface != null) {
                return iDeviceidInterface.b();
            }
            return null;
        } catch (RemoteException e) {
            b("getUDID error, RemoteException!");
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            b("getUDID error, Exception!");
            e2.printStackTrace();
            return null;
        }
    }

    public boolean c() {
        try {
            if (this.b == null) {
                return false;
            }
            a("Device support opendeviceid");
            return this.b.c();
        } catch (RemoteException unused) {
            b("isSupport error, RemoteException!");
            return false;
        }
    }

    public String d() {
        Context context = this.a;
        if (context == null) {
            a("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        String packageName = context.getPackageName();
        a("liufeng, getVAID package：" + packageName);
        if (packageName != null && !packageName.equals("")) {
            try {
                IDeviceidInterface iDeviceidInterface = this.b;
                if (iDeviceidInterface != null) {
                    return iDeviceidInterface.a(packageName);
                }
                return null;
            } catch (RemoteException e) {
                b("getVAID error, RemoteException!");
                e.printStackTrace();
                return null;
            }
        }
        a("input package is null!");
        return null;
    }

    public String e() {
        Context context = this.a;
        if (context == null) {
            a("Context is null.");
            throw new IllegalArgumentException("Context is null, must be new OpenDeviceId first");
        }
        String packageName = context.getPackageName();
        a("liufeng, getAAID package：" + packageName);
        String strB = null;
        if (packageName != null && !packageName.equals("")) {
            try {
                IDeviceidInterface iDeviceidInterface = this.b;
                if (iDeviceidInterface == null) {
                    return null;
                }
                strB = iDeviceidInterface.b(packageName);
                return ((strB == null || "".equals(strB)) && this.b.c(packageName)) ? this.b.b(packageName) : strB;
            } catch (RemoteException unused) {
                b("getAAID error, RemoteException!");
                return strB;
            }
        }
        a("input package is null!");
        return null;
    }

    public void f() {
        try {
            this.a.unbindService(this.e);
            a("unBind Service successful");
        } catch (IllegalArgumentException unused) {
            b("unBind Service exception");
        }
        this.b = null;
    }

    public void a(boolean z) {
        d = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (d) {
            Log.i(c, str);
        }
    }

    private void b(String str) {
        if (d) {
            Log.e(c, str);
        }
    }
}
