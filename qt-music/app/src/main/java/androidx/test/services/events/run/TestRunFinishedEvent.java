package androidx.test.services.events.run;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;
import androidx.test.services.events.FailureInfo;
import androidx.test.services.events.run.TestRunEvent;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class TestRunFinishedEvent extends TestRunEvent {
    public final int count;
    public final List<FailureInfo> failures;
    public final int ignoreCount;
    public final long runTime;

    public TestRunFinishedEvent(int count, int ignoreCount, long runTime, List<FailureInfo> failures) {
        Checks.checkNotNull(failures, "failures cannot be null");
        this.count = count;
        this.ignoreCount = ignoreCount;
        this.runTime = runTime;
        this.failures = failures;
    }

    TestRunFinishedEvent(Parcel source) {
        this.count = source.readInt();
        this.ignoreCount = source.readInt();
        this.runTime = source.readLong();
        this.failures = new ArrayList();
        for (Parcelable parcelable : source.readParcelableArray(FailureInfo[].class.getClassLoader())) {
            this.failures.add((FailureInfo) parcelable);
        }
    }

    @Override // androidx.test.services.events.run.TestRunEvent, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.count);
        parcel.writeInt(this.ignoreCount);
        parcel.writeLong(this.runTime);
        parcel.writeParcelableArray((FailureInfo[]) this.failures.toArray(new FailureInfo[0]), i);
    }

    @Override // androidx.test.services.events.run.TestRunEvent
    TestRunEvent.EventType instanceType() {
        return TestRunEvent.EventType.FINISHED;
    }
}
