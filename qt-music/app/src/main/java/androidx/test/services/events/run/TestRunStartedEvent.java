package androidx.test.services.events.run;

import android.os.Parcel;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes.dex */
public class TestRunStartedEvent extends TestRunEventWithTestCase {
    public TestRunStartedEvent(TestCaseInfo testCase) {
        super(testCase);
    }

    TestRunStartedEvent(Parcel source) {
        super(source);
    }

    @Override // androidx.test.services.events.run.TestRunEvent
    TestRunEvent.EventType instanceType() {
        return TestRunEvent.EventType.STARTED;
    }
}
