package androidx.test.rule;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import androidx.test.internal.util.Checks;
import androidx.test.platform.app.InstrumentationRegistry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes.dex */
public class ServiceTestRule implements TestRule {
    private static final long DEFAULT_TIMEOUT = 5;
    private static final String TAG = "ServiceTestRule";
    private IBinder binder;
    boolean serviceBound;
    private ServiceConnection serviceConn;
    private Intent serviceIntent;
    boolean serviceStarted;
    private final TimeUnit timeUnit;
    private final long timeout;

    protected void afterService() {
    }

    protected void beforeService() {
    }

    public ServiceTestRule() {
        this(5L, TimeUnit.SECONDS);
    }

    protected ServiceTestRule(long timeout, TimeUnit timeUnit) {
        this.serviceStarted = false;
        this.serviceBound = false;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public static ServiceTestRule withTimeout(long timeout, TimeUnit timeUnit) {
        return new ServiceTestRule(timeout, timeUnit);
    }

    public void startService(Intent intent) throws TimeoutException {
        this.serviceIntent = (Intent) Checks.checkNotNull(intent, "intent can't be null");
        InstrumentationRegistry.getInstrumentation().getTargetContext().startService(this.serviceIntent);
        this.serviceStarted = true;
        this.serviceBound = bindServiceAndWait(this.serviceIntent, null, 1);
    }

    public IBinder bindService(Intent intent) throws TimeoutException {
        this.serviceIntent = ((Intent) Checks.checkNotNull(intent, "intent can't be null")).cloneFilter();
        this.serviceBound = bindServiceAndWait(intent, null, 1);
        return this.binder;
    }

    public IBinder bindService(Intent intent, ServiceConnection connection, int flags) throws TimeoutException {
        this.serviceIntent = ((Intent) Checks.checkNotNull(intent, "intent can't be null")).cloneFilter();
        this.serviceBound = bindServiceAndWait(this.serviceIntent, (ServiceConnection) Checks.checkNotNull(connection, "connection can't be null"), flags);
        return this.binder;
    }

    boolean bindServiceAndWait(Intent intent, final ServiceConnection conn, int flags) throws TimeoutException {
        ProxyServiceConnection proxyServiceConnection = new ProxyServiceConnection(conn);
        boolean zBindService = InstrumentationRegistry.getInstrumentation().getTargetContext().bindService(intent, proxyServiceConnection, flags);
        if (zBindService) {
            waitOnLatch(proxyServiceConnection.connectedLatch, "connected");
            this.serviceConn = proxyServiceConnection;
        } else {
            Log.e(TAG, "Failed to bind to service! Is your service declared in the manifest?");
        }
        return zBindService;
    }

    public void unbindService() {
        if (this.serviceBound) {
            InstrumentationRegistry.getInstrumentation().getTargetContext().unbindService(this.serviceConn);
            this.binder = null;
            this.serviceBound = false;
        }
    }

    class ProxyServiceConnection implements ServiceConnection {
        private ServiceConnection callerConnection;
        public CountDownLatch connectedLatch;

        private ProxyServiceConnection(ServiceConnection connection) {
            this.connectedLatch = new CountDownLatch(1);
            this.callerConnection = connection;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName name, IBinder service) {
            ServiceTestRule.this.binder = service;
            ServiceConnection serviceConnection = this.callerConnection;
            if (serviceConnection != null) {
                serviceConnection.onServiceConnected(name, service);
            }
            this.connectedLatch.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName name) {
            Log.e(ServiceTestRule.TAG, "Connection to the Service has been lost!");
            ServiceTestRule.this.binder = null;
            ServiceConnection serviceConnection = this.callerConnection;
            if (serviceConnection != null) {
                serviceConnection.onServiceDisconnected(name);
            }
        }
    }

    private void waitOnLatch(CountDownLatch latch, String actionName) throws TimeoutException {
        try {
            if (latch.await(this.timeout, this.timeUnit)) {
                return;
            }
            long j = this.timeout;
            String strName = this.timeUnit.name();
            throw new TimeoutException(new StringBuilder(String.valueOf(strName).length() + 56 + String.valueOf(actionName).length()).append("Waited for ").append(j).append(" ").append(strName).append(", but service was never ").append(actionName).toString());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            String strValueOf = String.valueOf(actionName);
            throw new RuntimeException(strValueOf.length() != 0 ? "Interrupted while waiting for service to be ".concat(strValueOf) : new String("Interrupted while waiting for service to be "), e);
        }
    }

    void shutdownService() throws TimeoutException {
        if (this.serviceStarted) {
            InstrumentationRegistry.getInstrumentation().getTargetContext().stopService(this.serviceIntent);
            this.serviceStarted = false;
        }
        unbindService();
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(final Statement base, Description description) {
        return new ServiceStatement(base);
    }

    private class ServiceStatement extends Statement {
        private final Statement base;

        public ServiceStatement(Statement base) {
            this.base = base;
        }

        @Override // org.junit.runners.model.Statement
        public void evaluate() throws Throwable {
            try {
                ServiceTestRule.this.beforeService();
                this.base.evaluate();
            } finally {
                ServiceTestRule.this.shutdownService();
                ServiceTestRule.this.afterService();
            }
        }
    }
}
