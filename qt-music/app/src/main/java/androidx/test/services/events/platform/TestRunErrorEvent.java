package androidx.test.services.events.platform;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.ErrorInfo;
import androidx.test.services.events.TestRunInfo;
import androidx.test.services.events.TimeStamp;
import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes.dex */
public class TestRunErrorEvent extends TestPlatformEvent {
    public final ErrorInfo error;
    public final TestRunInfo testRun;
    public final TimeStamp timeStamp;

    public TestRunErrorEvent(TestRunInfo testRun, ErrorInfo error, TimeStamp timeStamp) {
        this.testRun = (TestRunInfo) Checks.checkNotNull(testRun, "testRun cannot be null");
        this.error = (ErrorInfo) Checks.checkNotNull(error, "error cannot be null");
        this.timeStamp = (TimeStamp) Checks.checkNotNull(timeStamp, "timeStamp cannot be null");
    }

    TestRunErrorEvent(Parcel source) {
        this.testRun = new TestRunInfo(source);
        this.error = new ErrorInfo(source);
        this.timeStamp = new TimeStamp(source);
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent
    TestPlatformEvent.EventType instanceType() {
        return TestPlatformEvent.EventType.TEST_RUN_ERROR;
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.testRun.writeToParcel(parcel, i);
        this.error.writeToParcel(parcel, i);
        this.timeStamp.writeToParcel(parcel, i);
    }
}
