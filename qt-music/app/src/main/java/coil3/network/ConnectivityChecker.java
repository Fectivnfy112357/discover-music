package coil3.network;

import kotlin.Metadata;

/* compiled from: ConnectivityChecker.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bç\u0080\u0001\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Lcoil3/network/ConnectivityChecker;", "", "isOnline", "", "Companion", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public interface ConnectivityChecker {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final ConnectivityChecker ONLINE = new ConnectivityChecker() { // from class: coil3.network.ConnectivityChecker$$ExternalSyntheticLambda0
        @Override // coil3.network.ConnectivityChecker
        public final boolean isOnline() {
            return ConnectivityChecker.ONLINE$lambda$0();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    static boolean ONLINE$lambda$0() {
        return true;
    }

    boolean isOnline();

    /* compiled from: ConnectivityChecker.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u0006"}, d2 = {"Lcoil3/network/ConnectivityChecker$Companion;", "", "<init>", "()V", "ONLINE", "Lcoil3/network/ConnectivityChecker;", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }
    }
}
