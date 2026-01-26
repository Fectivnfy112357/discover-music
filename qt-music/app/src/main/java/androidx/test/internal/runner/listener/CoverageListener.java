package androidx.test.internal.runner.listener;

import android.app.Instrumentation;
import android.os.Bundle;
import androidx.test.internal.runner.coverage.InstrumentationCoverageReporter;
import androidx.test.internal.runner.storage.RunnerFileIO;
import androidx.test.internal.runner.storage.RunnerIO;
import java.io.PrintStream;
import org.junit.runner.Result;

/* loaded from: classes.dex */
public class CoverageListener extends InstrumentationRunListener {
    private static final String REPORT_KEY_COVERAGE_PATH = "coverageFilePath";
    private final String coverageFilePath;
    private InstrumentationCoverageReporter coverageReporter;
    private RunnerIO runnerIO;

    public CoverageListener(String customCoverageFilePath) {
        this(customCoverageFilePath, new RunnerFileIO());
    }

    public CoverageListener(String customCoverageFilePath, RunnerIO runnerIO) {
        this.coverageFilePath = customCoverageFilePath;
        this.runnerIO = runnerIO;
    }

    CoverageListener(String customCoverageFilePath, InstrumentationCoverageReporter coverageReporter) {
        this.coverageFilePath = customCoverageFilePath;
        this.coverageReporter = coverageReporter;
    }

    @Override // androidx.test.internal.runner.listener.InstrumentationRunListener
    public void setInstrumentation(Instrumentation instr) {
        super.setInstrumentation(instr);
        this.coverageReporter = new InstrumentationCoverageReporter(getInstrumentation(), this.runnerIO);
    }

    @Override // androidx.test.internal.runner.listener.InstrumentationRunListener
    public void instrumentationRunFinished(PrintStream writer, Bundle results, Result junitResults) {
        results.putString(REPORT_KEY_COVERAGE_PATH, this.coverageReporter.generateCoverageReport(this.coverageFilePath, writer));
    }
}
