package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public final class FailureInfo implements Parcelable {
    public static final Parcelable.Creator<FailureInfo> CREATOR = new Parcelable.Creator<FailureInfo>() { // from class: androidx.test.services.events.FailureInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FailureInfo createFromParcel(Parcel source) {
            return new FailureInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FailureInfo[] newArray(int size) {
            return new FailureInfo[size];
        }
    };
    public final String failureMessage;
    public final String failureType;
    public final String stackTrace;
    public final TestCaseInfo testCase;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public FailureInfo(String failureMessage, String failureType, String stackTrace, TestCaseInfo testCase) {
        Checks.checkNotNull(stackTrace, "stackTrace cannot be null");
        Checks.checkNotNull(testCase, "testCase cannot be null");
        this.failureMessage = failureMessage;
        this.failureType = failureType;
        this.stackTrace = stackTrace;
        this.testCase = testCase;
    }

    public FailureInfo(Parcel source) {
        Checks.checkNotNull(source, "source cannot be null");
        this.failureMessage = source.readString();
        this.failureType = source.readString();
        this.stackTrace = source.readString();
        this.testCase = (TestCaseInfo) source.readParcelable(TestCaseInfo.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.failureMessage);
        parcel.writeString(this.failureType);
        parcel.writeString(this.stackTrace);
        parcel.writeParcelable(this.testCase, i);
    }
}
