package coil3.util;

import coil3.util.Logger;
import com.facebook.react.devsupport.StackTraceHelper;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: logging.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\u0018\u00002\u00020\u0001B\u0013\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0011"}, d2 = {"Lcoil3/util/DebugLogger;", "Lcoil3/util/Logger;", "minLevel", "Lcoil3/util/Logger$Level;", "<init>", "(Lcoil3/util/Logger$Level;)V", "getMinLevel", "()Lcoil3/util/Logger$Level;", "setMinLevel", "log", "", "tag", "", "level", StackTraceHelper.MESSAGE_KEY, "throwable", "", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DebugLogger implements Logger {
    private Logger.Level minLevel;

    public DebugLogger() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DebugLogger(Logger.Level level) {
        this.minLevel = level;
    }

    public /* synthetic */ DebugLogger(Logger.Level level, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? Logger.Level.Debug : level);
    }

    @Override // coil3.util.Logger
    public Logger.Level getMinLevel() {
        return this.minLevel;
    }

    @Override // coil3.util.Logger
    public void setMinLevel(Logger.Level level) {
        this.minLevel = level;
    }

    @Override // coil3.util.Logger
    public void log(String tag, Logger.Level level, String message, Throwable throwable) {
        if (message != null) {
            Utils_androidKt.println(level, tag, message);
        }
        if (throwable != null) {
            Utils_androidKt.println(level, tag, ExceptionsKt.stackTraceToString(throwable));
        }
    }
}
