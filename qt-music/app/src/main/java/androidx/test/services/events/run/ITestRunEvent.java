package androidx.test.services.events.run;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.test.runner.internal.deps.aidl.BaseProxy;
import androidx.test.runner.internal.deps.aidl.BaseStub;
import androidx.test.runner.internal.deps.aidl.Codecs;

/* loaded from: classes.dex */
public interface ITestRunEvent extends IInterface {
    void send(TestRunEvent testRunEvent) throws RemoteException;

    public static abstract class Stub extends BaseStub implements ITestRunEvent {
        private static final String DESCRIPTOR = "androidx.test.services.events.run.ITestRunEvent";
        static final int TRANSACTION_send = 1;

        public Stub() {
            super(DESCRIPTOR);
        }

        public static ITestRunEvent asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface instanceof ITestRunEvent) {
                return (ITestRunEvent) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // androidx.test.runner.internal.deps.aidl.BaseStub
        protected boolean dispatchTransaction(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1) {
                return false;
            }
            send((TestRunEvent) Codecs.createParcelable(data, TestRunEvent.CREATOR));
            reply.writeNoException();
            return true;
        }

        public static class Proxy extends BaseProxy implements ITestRunEvent {
            Proxy(IBinder remote) {
                super(remote, Stub.DESCRIPTOR);
            }

            @Override // androidx.test.services.events.run.ITestRunEvent
            public void send(TestRunEvent testRunEvent) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(parcelObtainAndWriteInterfaceToken, testRunEvent);
                transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
            }
        }
    }
}
