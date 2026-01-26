package androidx.test.services.events.run;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.FailureInfo;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes.dex */
public class TestFailureEvent extends TestRunEventWithTestCase {
    public final FailureInfo failure;

    public TestFailureEvent(TestCaseInfo testCase, FailureInfo failure) {
        super(testCase);
        Checks.checkNotNull(failure, "failure cannot be null");
        this.failure = failure;
    }

    TestFailureEvent(Parcel source) {
        super(source);
        this.failure = new FailureInfo(source);
    }

    @Override // androidx.test.services.events.run.TestRunEvent
    TestRunEvent.EventType instanceType() {
        return TestRunEvent.EventType.TEST_FAILURE;
    }

    @Override // androidx.test.services.events.run.TestRunEventWithTestCase, androidx.test.services.events.run.TestRunEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, 0);
        this.failure.writeToParcel(parcel, 0);
    }
}
