package androidx.test.runner.permission;

import android.app.UiAutomation;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.test.InstrumentationRegistry;
import androidx.test.internal.util.Checks;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
class UiAutomationShellCommand extends ShellCommand {
    private static final String TAG = "UiAutomationShellCmd";
    private final PmCommand command;
    private final String permission;
    private final String targetPackage;
    private final UiAutomation uiAutomation = (UiAutomation) Checks.checkNotNull(InstrumentationRegistry.getInstrumentation().getUiAutomation());

    enum PmCommand {
        GRANT_PERMISSION("grant");

        private final String pmCommand;

        PmCommand(String command) {
            String strValueOf = String.valueOf(command);
            this.pmCommand = strValueOf.length() != 0 ? "pm ".concat(strValueOf) : new String("pm ");
        }

        public String get() {
            return this.pmCommand;
        }
    }

    UiAutomationShellCommand(String targetPackage, String permission, PmCommand pmCommand) {
        this.targetPackage = shellEscape(targetPackage);
        this.permission = shellEscape(permission);
        this.command = pmCommand;
    }

    @Override // androidx.test.runner.permission.ShellCommand
    public void execute() throws Exception {
        executePermissionCommand(commandForPermission());
    }

    protected String commandForPermission() {
        return this.command.get() + " " + this.targetPackage + " " + this.permission;
    }

    private void executePermissionCommand(String cmd) throws Throwable {
        String strValueOf = String.valueOf(cmd);
        Log.i(TAG, strValueOf.length() != 0 ? "Requesting permission: ".concat(strValueOf) : new String("Requesting permission: "));
        try {
            awaitTermination(this.uiAutomation.executeShellCommand(cmd), 2L, TimeUnit.SECONDS);
        } catch (TimeoutException unused) {
            String strValueOf2 = String.valueOf(cmd);
            Log.e(TAG, strValueOf2.length() != 0 ? "Timeout while executing cmd: ".concat(strValueOf2) : new String("Timeout while executing cmd: "));
        }
    }

    private static void awaitTermination(ParcelFileDescriptor pfDescriptor, long timeout, TimeUnit unit) throws Throwable {
        long millis = unit.toMillis(timeout);
        long jCurrentTimeMillis = millis > 0 ? System.currentTimeMillis() + millis : 0L;
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new ParcelFileDescriptor.AutoCloseInputStream(pfDescriptor)));
            do {
                try {
                    String line = bufferedReader2.readLine();
                    if (line != null) {
                        Log.i(TAG, line);
                    } else {
                        bufferedReader2.close();
                        return;
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } while (jCurrentTimeMillis <= System.currentTimeMillis());
            throw new TimeoutException();
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
