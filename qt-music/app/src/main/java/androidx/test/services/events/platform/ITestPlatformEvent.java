package androidx.test.services.events.platform;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.test.runner.internal.deps.aidl.BaseProxy;
import androidx.test.runner.internal.deps.aidl.BaseStub;
import androidx.test.runner.internal.deps.aidl.Codecs;

/* loaded from: classes.dex */
public interface ITestPlatformEvent extends IInterface {
    void send(TestPlatformEvent testPlatformEvent) throws RemoteException;

    public static abstract class Stub extends BaseStub implements ITestPlatformEvent {
        private static final String DESCRIPTOR = "androidx.test.services.events.platform.ITestPlatformEvent";
        static final int TRANSACTION_send = 1;

        public Stub() {
            super(DESCRIPTOR);
        }

        public static ITestPlatformEvent asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface instanceof ITestPlatformEvent) {
                return (ITestPlatformEvent) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // androidx.test.runner.internal.deps.aidl.BaseStub
        protected boolean dispatchTransaction(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                return false;
            }
            send((TestPlatformEvent) Codecs.createParcelable(data, TestPlatformEvent.CREATOR));
            reply.writeNoException();
            return true;
        }

        public static class Proxy extends BaseProxy implements ITestPlatformEvent {
            Proxy(IBinder remote) {
                super(remote, Stub.DESCRIPTOR);
            }

            @Override // androidx.test.services.events.platform.ITestPlatformEvent
            public void send(TestPlatformEvent testPlatformEvent) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(parcelObtainAndWriteInterfaceToken, testPlatformEvent);
                transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
            }
        }
    }
}
