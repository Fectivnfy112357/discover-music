package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public final class TestStatus implements Parcelable {
    public static final Parcelable.Creator<TestStatus> CREATOR = new Parcelable.Creator<TestStatus>() { // from class: androidx.test.services.events.TestStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestStatus createFromParcel(Parcel source) {
            return new TestStatus(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestStatus[] newArray(int size) {
            return new TestStatus[size];
        }
    };
    public Status status;

    public enum Status {
        CANCELLED,
        IGNORED,
        SKIPPED,
        ABORTED,
        PASSED,
        FAILED
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TestStatus(Status status) {
        this.status = (Status) Checks.checkNotNull(status, "status cannot be null");
    }

    public TestStatus(Parcel source) {
        Checks.checkNotNull(source, "source cannot be null");
        this.status = Status.valueOf((String) Checks.checkNotNull(source.readString(), "status cannot be null"));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.status.name());
    }
}
