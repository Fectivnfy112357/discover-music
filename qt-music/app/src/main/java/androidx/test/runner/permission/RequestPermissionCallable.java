package androidx.test.runner.permission;

import android.content.Context;
import android.text.TextUtils;
import androidx.test.internal.util.Checks;
import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public abstract class RequestPermissionCallable implements Callable<Result> {
    private final String permission;
    private final ShellCommand shellCommand;
    private final Context targetContext;
    private final String targetPackage;

    public enum Result {
        SUCCESS,
        FAILURE
    }

    public RequestPermissionCallable(ShellCommand shellCommand, Context targetContext, String permission) {
        this.shellCommand = (ShellCommand) Checks.checkNotNull(shellCommand, "shellCommand cannot be null!");
        Context context = (Context) Checks.checkNotNull(targetContext, "targetContext cannot be null!");
        this.targetContext = context;
        String packageName = context.getPackageName();
        Checks.checkState(!TextUtils.isEmpty(packageName), "targetPackage cannot be empty or null!");
        this.targetPackage = packageName;
        this.permission = permission;
    }

    protected String getPermission() {
        return this.permission;
    }

    protected boolean isPermissionGranted() {
        return this.targetContext.checkCallingOrSelfPermission(this.permission) == 0;
    }

    protected ShellCommand getShellCommand() {
        return this.shellCommand;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestPermissionCallable requestPermissionCallable = (RequestPermissionCallable) o;
        return Objects.equals(this.targetPackage, requestPermissionCallable.targetPackage) && Objects.equals(this.permission, requestPermissionCallable.permission);
    }

    public int hashCode() {
        return Objects.hash(this.targetPackage, this.permission);
    }
}
