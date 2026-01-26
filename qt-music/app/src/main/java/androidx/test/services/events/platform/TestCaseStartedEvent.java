package androidx.test.services.events.platform;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.TimeStamp;
import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes.dex */
public final class TestCaseStartedEvent extends TestPlatformEvent {
    public final TestCaseInfo testCase;
    public final TimeStamp timeStamp;

    public TestCaseStartedEvent(TestCaseInfo testCase, TimeStamp timeStamp) {
        this.testCase = (TestCaseInfo) Checks.checkNotNull(testCase, "testCase cannot be null");
        this.timeStamp = (TimeStamp) Checks.checkNotNull(timeStamp, "timeStamp cannot be null");
    }

    public TestCaseStartedEvent(Parcel source) {
        this.testCase = new TestCaseInfo(source);
        this.timeStamp = new TimeStamp(source);
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.testCase.writeToParcel(parcel, i);
        this.timeStamp.writeToParcel(parcel, i);
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent
    public TestPlatformEvent.EventType instanceType() {
        return TestPlatformEvent.EventType.TEST_CASE_STARTED;
    }
}
