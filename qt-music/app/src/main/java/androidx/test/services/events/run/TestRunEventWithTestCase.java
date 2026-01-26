package androidx.test.services.events.run;

import android.os.Parcel;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.TestCaseInfo;

/* loaded from: classes.dex */
public abstract class TestRunEventWithTestCase extends TestRunEvent {
    public final TestCaseInfo testCase;

    TestRunEventWithTestCase(Parcel source) {
        this.testCase = new TestCaseInfo(source);
    }

    TestRunEventWithTestCase(TestCaseInfo testCase) {
        Checks.checkNotNull(testCase, "testCase cannot be null");
        this.testCase = testCase;
    }

    @Override // androidx.test.services.events.run.TestRunEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        this.testCase.writeToParcel(parcel, i);
    }
}
