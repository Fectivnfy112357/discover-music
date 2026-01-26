package androidx.test.internal.runner.coverage;

import android.app.Instrumentation;
import android.util.Log;
import androidx.test.internal.runner.storage.RunnerIO;
import androidx.test.internal.runner.storage.RunnerTestStorageIO;
import androidx.test.runner.internal.deps.desugar.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/* loaded from: classes.dex */
public class InstrumentationCoverageReporter {
    private static final String DEFAULT_COVERAGE_FILE_NAME = "coverage.ec";
    private static final String EMMA_RUNTIME_CLASS = "com.vladium.emma.rt.RT";
    private static final String TAG = "InstrumentationCoverageReporter";
    private final Instrumentation instrumentation;
    private final RunnerIO runnerIO;

    public InstrumentationCoverageReporter(Instrumentation instrumentation, RunnerIO runnerIO) {
        this.instrumentation = instrumentation;
        this.runnerIO = runnerIO;
    }

    public String generateCoverageReport(String coverageFilePath, PrintStream instrumentationResultWriter) {
        String strDumpCoverageToFile;
        if (this.runnerIO instanceof RunnerTestStorageIO) {
            strDumpCoverageToFile = dumpCoverageToTestStorage(coverageFilePath, instrumentationResultWriter);
        } else {
            strDumpCoverageToFile = dumpCoverageToFile(coverageFilePath, instrumentationResultWriter);
        }
        String str = TAG;
        String strValueOf = String.valueOf(strDumpCoverageToFile);
        Log.d(str, strValueOf.length() != 0 ? "Coverage file was generated to ".concat(strValueOf) : new String("Coverage file was generated to "));
        instrumentationResultWriter.format("\nGenerated code coverage data to %s", strDumpCoverageToFile);
        return strDumpCoverageToFile;
    }

    private String dumpCoverageToFile(String coverageFilePath, PrintStream instrumentationResultWriter) {
        if (coverageFilePath == null) {
            Log.d(TAG, "No coverage file path was specified. Dumps to the default file path.");
            String absolutePath = this.instrumentation.getTargetContext().getFilesDir().getAbsolutePath();
            String str = File.separator;
            coverageFilePath = new StringBuilder(String.valueOf(absolutePath).length() + 11 + String.valueOf(str).length()).append(absolutePath).append(str).append(DEFAULT_COVERAGE_FILE_NAME).toString();
        }
        if (!generateCoverageInternal(coverageFilePath, instrumentationResultWriter)) {
            Log.w(TAG, "Failed to generate the coverage data file. Please refer to the instrumentation result for more info.");
        }
        return coverageFilePath;
    }

    private String dumpCoverageToTestStorage(String coverageFilePath, PrintStream instrumentationResultWriter) {
        if (coverageFilePath == null) {
            Log.d(TAG, "No coverage file path was specified. Dumps to the default coverage file using test storage.");
            coverageFilePath = DEFAULT_COVERAGE_FILE_NAME;
        }
        String absolutePath = this.instrumentation.getTargetContext().getFilesDir().getAbsolutePath();
        String str = File.separator;
        String string = new StringBuilder(String.valueOf(absolutePath).length() + 11 + String.valueOf(str).length()).append(absolutePath).append(str).append(DEFAULT_COVERAGE_FILE_NAME).toString();
        if (!generateCoverageInternal(string, instrumentationResultWriter)) {
            Log.w(TAG, "Failed to generate the coverage data file. Please refer to the instrumentation result for more info.");
        }
        try {
            Log.d(TAG, "Test service is available. Moving the coverage data file to be managed by the storage service.");
            moveFileToTestStorage(string, coverageFilePath);
            return coverageFilePath;
        } catch (IOException e) {
            reportEmmaError(instrumentationResultWriter, e);
            return null;
        }
    }

    private void moveFileToTestStorage(String srcFilePath, String destFilePath) throws IOException {
        File file = new File(srcFilePath);
        if (file.exists()) {
            String str = TAG;
            Log.d(str, String.format("Moving coverage file [%s] to the internal test storage [%s].", srcFilePath, destFilePath));
            OutputStream outputStreamOpenOutputStream = this.runnerIO.openOutputStream(destFilePath);
            try {
                FileChannel channel = new FileInputStream(srcFilePath).getChannel();
                try {
                    WritableByteChannel writableByteChannelNewChannel = Channels.newChannel(outputStreamOpenOutputStream);
                    try {
                        channel.transferTo(0L, channel.size(), writableByteChannelNewChannel);
                        if (writableByteChannelNewChannel != null) {
                            writableByteChannelNewChannel.close();
                        }
                        if (channel != null) {
                            channel.close();
                        }
                        if (outputStreamOpenOutputStream != null) {
                            outputStreamOpenOutputStream.close();
                        }
                        if (file.delete()) {
                            return;
                        }
                        Log.e(str, String.format("Failed to delete original coverage file [%s]", file.getAbsolutePath()));
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                if (outputStreamOpenOutputStream != null) {
                    try {
                        outputStreamOpenOutputStream.close();
                    } catch (Throwable th2) {
                        ThrowableExtension.addSuppressed(th, th2);
                    }
                }
                throw th;
            }
        }
    }

    public boolean generateCoverageInternal(String coverageFilePath, PrintStream instrumentationResultWriter) throws IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls;
        File file = new File(coverageFilePath);
        try {
            try {
                try {
                    cls = Class.forName(EMMA_RUNTIME_CLASS, true, this.instrumentation.getTargetContext().getClassLoader());
                } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException e) {
                    reportEmmaError(instrumentationResultWriter, e);
                    return false;
                }
            } catch (ClassNotFoundException e2) {
                reportEmmaError(instrumentationResultWriter, "Is Emma/JaCoCo jar on classpath?", e2);
                return false;
            }
        } catch (ClassNotFoundException unused) {
            cls = Class.forName(EMMA_RUNTIME_CLASS, true, this.instrumentation.getContext().getClassLoader());
            Log.w(TAG, "Generating coverage for alternate test context.");
            instrumentationResultWriter.format("\nWarning: %s", "Generating coverage for alternate test context.");
        }
        cls.getMethod("dumpCoverageData", file.getClass(), Boolean.TYPE, Boolean.TYPE).invoke(null, file, false, false);
        return true;
    }

    private void reportEmmaError(PrintStream writer, Exception e) {
        reportEmmaError(writer, "", e);
    }

    private void reportEmmaError(PrintStream writer, String hint, Exception e) {
        String strValueOf = String.valueOf(hint);
        String strConcat = strValueOf.length() != 0 ? "Failed to generate Emma/JaCoCo coverage. ".concat(strValueOf) : new String("Failed to generate Emma/JaCoCo coverage. ");
        Log.e(TAG, strConcat, e);
        writer.format("\nError: %s", strConcat);
    }
}
