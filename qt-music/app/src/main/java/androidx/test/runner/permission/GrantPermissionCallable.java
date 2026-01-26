package androidx.test.runner.permission;

import android.content.Context;
import android.util.Log;
import androidx.test.runner.permission.RequestPermissionCallable;

/* loaded from: classes.dex */
class GrantPermissionCallable extends RequestPermissionCallable {
    private static final String TAG = "GrantPermissionCallable";

    GrantPermissionCallable(ShellCommand shellCommand, Context context, String permission) {
        super(shellCommand, context, permission);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    public RequestPermissionCallable.Result call() throws Exception {
        if (isPermissionGranted()) {
            String permission = getPermission();
            Log.i(TAG, new StringBuilder(String.valueOf(permission).length() + 32).append("Permission: ").append(permission).append(" is already granted!").toString());
            return RequestPermissionCallable.Result.SUCCESS;
        }
        try {
            getShellCommand().execute();
            if (!isPermissionGranted()) {
                Thread.sleep(1000L);
                if (!isPermissionGranted()) {
                    String permission2 = getPermission();
                    Log.e(TAG, new StringBuilder(String.valueOf(permission2).length() + 31).append("Permission: ").append(permission2).append(" cannot be granted!").toString());
                    return RequestPermissionCallable.Result.FAILURE;
                }
            }
            return RequestPermissionCallable.Result.SUCCESS;
        } catch (Throwable th) {
            if (!isPermissionGranted()) {
                Thread.sleep(1000L);
                if (!isPermissionGranted()) {
                    String permission3 = getPermission();
                    Log.e(TAG, new StringBuilder(String.valueOf(permission3).length() + 31).append("Permission: ").append(permission3).append(" cannot be granted!").toString());
                    return RequestPermissionCallable.Result.FAILURE;
                }
            }
            throw th;
        }
    }
}
