package androidx.test.services.events.run;

import android.os.Parcel;
import androidx.test.services.events.FailureInfo;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes.dex */
public class TestAssumptionFailureEvent extends TestFailureEvent {
    public TestAssumptionFailureEvent(TestCaseInfo testCase, FailureInfo failure) {
        super(testCase, failure);
    }

    TestAssumptionFailureEvent(Parcel source) {
        super(source);
    }

    @Override // androidx.test.services.events.run.TestFailureEvent, androidx.test.services.events.run.TestRunEvent
    TestRunEvent.EventType instanceType() {
        return TestRunEvent.EventType.TEST_ASSUMPTION_FAILURE;
    }
}
