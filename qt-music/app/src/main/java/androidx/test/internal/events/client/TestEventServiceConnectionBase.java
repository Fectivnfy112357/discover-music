package androidx.test.internal.events.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.util.Log;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public class TestEventServiceConnectionBase<T extends IInterface> implements TestEventServiceConnection {
    private static final String TAG = "ConnectionBase";
    private final TestEventClientConnectListener listener;
    private final ServiceFromBinder<T> serviceFromBinder;
    private final String serviceName;
    private final String servicePackageName;
    public T service = null;
    private final ServiceConnection connection = new ServiceConnection() { // from class: androidx.test.internal.events.client.TestEventServiceConnectionBase.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            TestEventServiceConnectionBase testEventServiceConnectionBase = TestEventServiceConnectionBase.this;
            testEventServiceConnectionBase.service = (T) testEventServiceConnectionBase.serviceFromBinder.asInterface(iBinder);
            String strValueOf = String.valueOf(TestEventServiceConnectionBase.this.serviceName);
            Log.d(TestEventServiceConnectionBase.TAG, strValueOf.length() != 0 ? "Connected to ".concat(strValueOf) : new String("Connected to "));
            TestEventServiceConnectionBase.this.listener.onTestEventClientConnect();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName className) {
            TestEventServiceConnectionBase.this.service = null;
            String strValueOf = String.valueOf(TestEventServiceConnectionBase.this.serviceName);
            Log.d(TestEventServiceConnectionBase.TAG, strValueOf.length() != 0 ? "Disconnected from ".concat(strValueOf) : new String("Disconnected from "));
        }
    };

    public interface ServiceFromBinder<T extends IInterface> {
        T asInterface(IBinder binder);
    }

    public TestEventServiceConnectionBase(String serviceName, ServiceFromBinder<T> serviceFromBinder, TestEventClientConnectListener listener) {
        this.serviceName = (String) Checks.checkNotNull(getServiceNameOnly(serviceName), "serviceName cannot be null");
        this.servicePackageName = (String) Checks.checkNotNull(getServicePackage(serviceName), "servicePackageName cannot be null");
        this.listener = (TestEventClientConnectListener) Checks.checkNotNull(listener, "listener cannot be null");
        this.serviceFromBinder = (ServiceFromBinder) Checks.checkNotNull(serviceFromBinder, "serviceFromBinder cannot be null");
    }

    @Override // androidx.test.internal.events.client.TestEventServiceConnection
    public void connect(Context context) {
        Intent intent = new Intent(this.serviceName);
        intent.setPackage(this.servicePackageName);
        if (context.bindService(intent, this.connection, 1)) {
            return;
        }
        String strValueOf = String.valueOf(this.serviceName);
        throw new IllegalStateException(strValueOf.length() != 0 ? "Cannot connect to ".concat(strValueOf) : new String("Cannot connect to "));
    }

    static String getServiceNameOnly(String serviceName) {
        String[] strArrSplit = serviceName.split("/");
        if (strArrSplit.length == 2) {
            if (!strArrSplit[1].startsWith(".")) {
                return strArrSplit[1];
            }
            String strValueOf = String.valueOf(strArrSplit[0]);
            String strValueOf2 = String.valueOf(strArrSplit[1]);
            return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
        }
        if (strArrSplit.length == 1) {
            return strArrSplit[0];
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(serviceName).length() + 22).append("Invalid serviceName [").append(serviceName).append("]").toString());
    }

    static String getServicePackage(String serviceName) {
        String[] strArrSplit = serviceName.split("/");
        if (strArrSplit.length >= 2) {
            return strArrSplit[0];
        }
        return null;
    }
}
