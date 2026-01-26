package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class AnnotationInfo implements Parcelable {
    public static final Parcelable.Creator<AnnotationInfo> CREATOR = new Parcelable.Creator<AnnotationInfo>() { // from class: androidx.test.services.events.AnnotationInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AnnotationInfo createFromParcel(Parcel source) {
            return new AnnotationInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AnnotationInfo[] newArray(int size) {
            return new AnnotationInfo[size];
        }
    };
    public final String name;
    public final List<AnnotationValue> values;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AnnotationInfo(String annotationName, List<AnnotationValue> annotationValues) {
        Checks.checkNotNull(annotationName, "annotationName cannot be null");
        Checks.checkNotNull(annotationName, "annotationValues cannot be null");
        this.name = annotationName;
        this.values = annotationValues;
    }

    private AnnotationInfo(Parcel source) {
        this.name = source.readString();
        ArrayList arrayList = new ArrayList();
        this.values = arrayList;
        source.readTypedList(arrayList, AnnotationValue.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeTypedList(this.values);
    }
}
