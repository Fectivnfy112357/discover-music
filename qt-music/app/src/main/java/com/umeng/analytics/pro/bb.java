package com.umeng.analytics.pro;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.umeng.analytics.pro.b;
import com.umeng.analytics.pro.c;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: HonorDeviceIdSupplier.java */
/* loaded from: classes3.dex */
class bb implements ax {
    private static String a = "";

    /* compiled from: HonorDeviceIdSupplier.java */
    interface c {
        public static final int a = 0;
        public static final String b = "oa_id_flag";
    }

    bb() {
    }

    @Override // com.umeng.analytics.pro.ax
    public String a(Context context) {
        if (context == null) {
            return null;
        }
        a aVar = new a();
        Intent intent = new Intent();
        intent.setAction("com.hihonor.id.HnOaIdService");
        intent.setPackage("com.hihonor.id");
        if (context.bindService(intent, aVar, 1)) {
            try {
                c.b.a(aVar.a()).a(new b());
                return a;
            } catch (Exception unused) {
            } finally {
                context.unbindService(aVar);
            }
        }
        return null;
    }

    /* compiled from: HonorDeviceIdSupplier.java */
    private static final class b extends b.AbstractBinderC0034b {
        @Override // com.umeng.analytics.pro.b
        public void a(int i, long j, boolean z, float f, double d, String str) throws RemoteException {
        }

        private b() {
        }

        @Override // com.umeng.analytics.pro.b
        public void a(int i, Bundle bundle) throws RemoteException {
            if (i != 0 || bundle == null) {
                return;
            }
            String unused = bb.a = bundle.getString(c.b);
        }
    }

    /* compiled from: HonorDeviceIdSupplier.java */
    private static final class a implements ServiceConnection {
        boolean a;
        private final LinkedBlockingQueue<IBinder> b;

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                this.b.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        public IBinder a() throws InterruptedException {
            if (this.a) {
                throw new IllegalStateException();
            }
            this.a = true;
            return this.b.poll(5L, TimeUnit.SECONDS);
        }

        private a() {
            this.a = false;
            this.b = new LinkedBlockingQueue<>();
        }
    }
}
