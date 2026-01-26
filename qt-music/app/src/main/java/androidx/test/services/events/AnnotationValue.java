package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class AnnotationValue implements Parcelable {
    public static final Parcelable.Creator<AnnotationValue> CREATOR = new Parcelable.Creator<AnnotationValue>() { // from class: androidx.test.services.events.AnnotationValue.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AnnotationValue createFromParcel(Parcel source) {
            return new AnnotationValue(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AnnotationValue[] newArray(int size) {
            return new AnnotationValue[size];
        }
    };
    public final String fieldName;
    public final List<String> fieldValues;
    public final String valueType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AnnotationValue(String fieldName, List<String> fieldValues, String valueType) {
        Checks.checkNotNull(fieldName, "fieldName cannot be null");
        Checks.checkNotNull(fieldValues, "fieldValues cannot be null");
        Checks.checkNotNull(valueType, "valueType cannot be null");
        this.fieldName = fieldName;
        this.fieldValues = fieldValues;
        this.valueType = valueType;
    }

    private AnnotationValue(Parcel source) {
        this.fieldName = source.readString();
        ArrayList arrayList = new ArrayList();
        this.fieldValues = arrayList;
        source.readStringList(arrayList);
        this.valueType = source.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.fieldName);
        parcel.writeStringList(this.fieldValues);
        parcel.writeString(this.valueType);
    }
}
