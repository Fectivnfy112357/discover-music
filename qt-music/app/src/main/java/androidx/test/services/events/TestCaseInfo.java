package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class TestCaseInfo implements Parcelable {
    public static final Parcelable.Creator<TestCaseInfo> CREATOR = new Parcelable.Creator<TestCaseInfo>() { // from class: androidx.test.services.events.TestCaseInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestCaseInfo createFromParcel(Parcel source) {
            return new TestCaseInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TestCaseInfo[] newArray(int size) {
            return new TestCaseInfo[size];
        }
    };
    public final List<AnnotationInfo> classAnnotations;
    public final String className;
    public final List<AnnotationInfo> methodAnnotations;
    public final String methodName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TestCaseInfo(Parcel source) {
        Checks.checkNotNull(source, "source cannot be null");
        this.className = (String) Checks.checkNotNull(source.readString(), "className cannot be null");
        this.methodName = (String) Checks.checkNotNull(source.readString(), "methodName cannot be null");
        ArrayList arrayList = new ArrayList();
        this.methodAnnotations = arrayList;
        source.readTypedList(arrayList, AnnotationInfo.CREATOR);
        ArrayList arrayList2 = new ArrayList();
        this.classAnnotations = arrayList2;
        source.readTypedList(arrayList2, AnnotationInfo.CREATOR);
    }

    public TestCaseInfo(String className, String methodName, List<AnnotationInfo> methodAnnotations, List<AnnotationInfo> classAnnotations) {
        this.className = (String) Checks.checkNotNull(className, "className cannot be null");
        this.methodName = (String) Checks.checkNotNull(methodName, "methodName cannot be null");
        this.classAnnotations = (List) Checks.checkNotNull(classAnnotations, "classAnnotations cannot be null");
        this.methodAnnotations = (List) Checks.checkNotNull(methodAnnotations, "methodAnnotations cannot be null");
    }

    public String getClassAndMethodName() {
        String str = this.className;
        String str2 = this.methodName;
        return new StringBuilder(String.valueOf(str).length() + 1 + String.valueOf(str2).length()).append(str).append("#").append(str2).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.className);
        parcel.writeString(this.methodName);
        parcel.writeTypedList(this.methodAnnotations);
        parcel.writeTypedList(this.classAnnotations);
    }
}
