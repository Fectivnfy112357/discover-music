package androidx.test.services.events.run;

import android.os.Parcel;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.run.TestRunEvent;

/* loaded from: classes.dex */
public class TestIgnoredEvent extends TestRunEventWithTestCase {
    public TestIgnoredEvent(TestCaseInfo testCase) {
        super(testCase);
    }

    TestIgnoredEvent(Parcel source) {
        super(source);
    }

    @Override // androidx.test.services.events.run.TestRunEvent
    TestRunEvent.EventType instanceType() {
        return TestRunEvent.EventType.TEST_IGNORED;
    }
}
