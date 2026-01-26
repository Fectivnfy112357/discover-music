package androidx.test.services.events.platform;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.TestRunInfo;
import androidx.test.services.events.TimeStamp;
import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes.dex */
public class TestRunStartedEvent extends TestPlatformEvent {
    public final TestRunInfo testRun;
    public final TimeStamp timeStamp;

    public TestRunStartedEvent(TestRunInfo testRun, TimeStamp timeStamp) {
        this.testRun = (TestRunInfo) Checks.checkNotNull(testRun, "testRun cannot be null");
        this.timeStamp = (TimeStamp) Checks.checkNotNull(timeStamp, "timeStamp cannot be null");
    }

    public TestRunStartedEvent(Parcel source) {
        this.testRun = new TestRunInfo(source);
        this.timeStamp = new TimeStamp(source);
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.testRun.writeToParcel(parcel, i);
        this.timeStamp.writeToParcel(parcel, i);
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent
    TestPlatformEvent.EventType instanceType() {
        return TestPlatformEvent.EventType.TEST_RUN_STARTED;
    }
}
