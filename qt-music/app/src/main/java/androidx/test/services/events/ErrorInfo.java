package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public final class ErrorInfo implements Parcelable {
    public static final Parcelable.Creator<ErrorInfo> CREATOR = new Parcelable.Creator<ErrorInfo>() { // from class: androidx.test.services.events.ErrorInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorInfo createFromParcel(Parcel source) {
            return new ErrorInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ErrorInfo[] newArray(int size) {
            return new ErrorInfo[size];
        }
    };
    public final String errorMessage;
    public final String errorType;
    public final String stackTrace;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ErrorInfo(String errorMessage, String errorType, String stackTrace) {
        this.errorMessage = errorMessage;
        this.errorType = errorType;
        this.stackTrace = (String) Checks.checkNotNull(stackTrace, "stackTrace cannot be null");
    }

    public ErrorInfo(Parcel source) {
        Checks.checkNotNull(source, "source cannot be null");
        this.errorMessage = source.readString();
        this.errorType = source.readString();
        this.stackTrace = (String) Checks.checkNotNull(source.readString(), "stackTrace cannot be null");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorType);
        parcel.writeString(this.stackTrace);
    }
}
