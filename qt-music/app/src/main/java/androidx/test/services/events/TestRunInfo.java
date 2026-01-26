package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class TestRunInfo implements Parcelable {
    public static final Parcelable.Creator<TestRunInfo> CREATOR = new Parcelable.Creator<TestRunInfo>() { // from class: androidx.test.services.events.TestRunInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestRunInfo createFromParcel(Parcel source) {
            return new TestRunInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestRunInfo[] newArray(int size) {
            return new TestRunInfo[size];
        }
    };
    public final List<TestCaseInfo> testCases;
    public final String testRunName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TestRunInfo(String testRunName, List<TestCaseInfo> testCases) {
        this.testRunName = (String) Checks.checkNotNull(testRunName, "testRunName cannot be null");
        this.testCases = (List) Checks.checkNotNull(testCases, "testCases cannot be null");
    }

    public TestRunInfo(Parcel source) {
        Checks.checkNotNull(source, "source cannot be null");
        this.testRunName = (String) Checks.checkNotNull(source.readString(), "className cannot be null");
        ArrayList arrayList = new ArrayList();
        this.testCases = arrayList;
        source.readTypedList(arrayList, TestCaseInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.testRunName);
        parcel.writeTypedList(this.testCases);
    }
}
