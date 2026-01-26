package androidx.test.services.events.platform;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.ErrorInfo;
import androidx.test.services.events.TestCaseInfo;
import androidx.test.services.events.TimeStamp;
import androidx.test.services.events.platform.TestPlatformEvent;

/* loaded from: classes.dex */
public class TestCaseErrorEvent extends TestPlatformEvent {
    public final ErrorInfo error;
    public final TestCaseInfo testCase;
    public final TimeStamp timeStamp;

    public TestCaseErrorEvent(TestCaseInfo testCase, ErrorInfo error, TimeStamp timeStamp) {
        this.testCase = (TestCaseInfo) Checks.checkNotNull(testCase, "testCase cannot be null");
        this.error = (ErrorInfo) Checks.checkNotNull(error, "error cannot be null");
        this.timeStamp = (TimeStamp) Checks.checkNotNull(timeStamp, "timeStamp cannot be null");
    }

    TestCaseErrorEvent(Parcel source) {
        this.testCase = new TestCaseInfo(source);
        this.error = new ErrorInfo(source);
        this.timeStamp = new TimeStamp(source);
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent
    TestPlatformEvent.EventType instanceType() {
        return TestPlatformEvent.EventType.TEST_CASE_ERROR;
    }

    @Override // androidx.test.services.events.platform.TestPlatformEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.testCase.writeToParcel(parcel, i);
        this.error.writeToParcel(parcel, i);
        this.timeStamp.writeToParcel(parcel, i);
    }
}
