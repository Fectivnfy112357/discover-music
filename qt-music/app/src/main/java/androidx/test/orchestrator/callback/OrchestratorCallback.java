package androidx.test.orchestrator.callback;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import androidx.test.runner.internal.deps.aidl.BaseProxy;
import androidx.test.runner.internal.deps.aidl.BaseStub;
import androidx.test.runner.internal.deps.aidl.Codecs;

/* loaded from: classes.dex */
public interface OrchestratorCallback extends IInterface {
    void addTest(String test) throws RemoteException;

    void sendTestNotification(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends BaseStub implements OrchestratorCallback {
        private static final String DESCRIPTOR = "androidx.test.orchestrator.callback.OrchestratorCallback";
        static final int TRANSACTION_addTest = 1;
        static final int TRANSACTION_sendTestNotification = 2;

        public Stub() {
            super(DESCRIPTOR);
        }

        public static OrchestratorCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = obj.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface instanceof OrchestratorCallback) {
                return (OrchestratorCallback) iInterfaceQueryLocalInterface;
            }
            return new Proxy(obj);
        }

        @Override // androidx.test.runner.internal.deps.aidl.BaseStub
        protected boolean dispatchTransaction(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                addTest(data.readString());
            } else {
                if (code != 2) {
                    return false;
                }
                sendTestNotification((Bundle) Codecs.createParcelable(data, Bundle.CREATOR));
            }
            reply.writeNoException();
            return true;
        }

        public static class Proxy extends BaseProxy implements OrchestratorCallback {
            Proxy(IBinder remote) {
                super(remote, Stub.DESCRIPTOR);
            }

            @Override // androidx.test.orchestrator.callback.OrchestratorCallback
            public void addTest(String test) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                parcelObtainAndWriteInterfaceToken.writeString(test);
                transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
            }

            @Override // androidx.test.orchestrator.callback.OrchestratorCallback
            public void sendTestNotification(Bundle bundle) throws RemoteException {
                Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
                Codecs.writeParcelable(parcelObtainAndWriteInterfaceToken, bundle);
                transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
            }
        }
    }
}
