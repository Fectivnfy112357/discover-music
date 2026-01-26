package androidx.test.orchestrator.junit;

import android.os.Parcel;
import android.os.Parcelable;
import org.junit.runner.Description;

/* loaded from: classes.dex */
public final class ParcelableDescription implements Parcelable {
    public static final Parcelable.Creator<ParcelableDescription> CREATOR = new Parcelable.Creator<ParcelableDescription>() { // from class: androidx.test.orchestrator.junit.ParcelableDescription.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableDescription createFromParcel(Parcel in) {
            return new ParcelableDescription(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableDescription[] newArray(int size) {
            return new ParcelableDescription[size];
        }
    };
    private final String className;
    private final String displayName;
    private final String methodName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableDescription(Description description) {
        this.className = description.getClassName();
        this.methodName = description.getMethodName();
        this.displayName = description.getDisplayName();
    }

    public ParcelableDescription(String classAndMethodName) {
        String[] strArrSplit = classAndMethodName.split("#");
        if (strArrSplit.length > 0) {
            this.className = strArrSplit[0];
            this.methodName = strArrSplit.length > 1 ? strArrSplit[1] : "";
        } else {
            this.className = "";
            this.methodName = "";
        }
        this.displayName = String.format("%s(%s)", this.methodName, this.className);
    }

    private ParcelableDescription(Parcel in) {
        this.className = getNonNullString(in);
        this.methodName = getNonNullString(in);
        this.displayName = getNonNullString(in);
    }

    private String getNonNullString(Parcel in) {
        String string = in.readString();
        return string == null ? "" : string;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.className);
        out.writeString(this.methodName);
        out.writeString(this.displayName);
    }

    public String getClassName() {
        return this.className;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
