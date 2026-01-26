package androidx.test.services.events.discovery;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.test.runner.internal.deps.aidl.BaseProxy;
import androidx.test.runner.internal.deps.aidl.BaseStub;
import androidx.test.runner.internal.deps.aidl.Codecs;

/* loaded from: classes.dex */
public interface ITestDiscoveryEvent extends IInterface {
    void send(TestDiscoveryEvent testDiscoveryEvent) throws RemoteException;

    public static abstract class Stub extends BaseStub implements ITestDiscoveryEvent {
        private static final String DESCRIPTOR = "androidx.test.services.events.discovery.ITestDiscoveryEvent";
        static final int TRANSACTION_send = 1;

        public Stub() {
            super(DESCRIPTOR);
        }

        public static ITestDiscoveryEvent asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface instanceof ITestDiscoveryEvent) {
                return (ITestDiscoveryEvent) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // androidx.test.runner.internal.deps.aidl.BaseStub
        protected boolean dispatchTransaction(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                return false;
            }
            send((TestDiscoveryEvent) Codecs.createParcelable(data, TestDiscoveryEvent.CREATOR));
            reply.writeNoException();
            return true;
        }

        public static class Proxy extends BaseProxy implements ITestDiscoveryEvent {
            Proxy(IBinder remote) {
                super(remote, Stub.DESCRIPTOR);
            }

            @Override // androidx.test.services.events.discovery.ITestDiscoveryEvent
            public void send(TestDiscoveryEvent testDiscoveryEvent) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(parcelObtainAndWriteInterfaceToken, testDiscoveryEvent);
                transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
            }
        }
    }
}
