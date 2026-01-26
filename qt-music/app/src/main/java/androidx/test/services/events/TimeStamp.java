package androidx.test.services.events;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.test.internal.util.Checks;

/* loaded from: classes.dex */
public final class TimeStamp implements Parcelable {
    public static final Parcelable.Creator<TimeStamp> CREATOR = new Parcelable.Creator<TimeStamp>() { // from class: androidx.test.services.events.TimeStamp.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeStamp createFromParcel(Parcel source) {
            return new TimeStamp(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TimeStamp[] newArray(int size) {
            return new TimeStamp[size];
        }
    };
    public final Integer nanos;
    public final Long seconds;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public TimeStamp(Long seconds, Integer nanos) {
        this.seconds = (Long) Checks.checkNotNull(seconds, "seconds cannot be null");
        this.nanos = (Integer) Checks.checkNotNull(nanos, "nanos cannot be null");
    }

    public TimeStamp(Parcel source) {
        Checks.checkNotNull(source, "source cannot be null");
        this.seconds = (Long) Checks.checkNotNull(Long.valueOf(source.readLong()), "seconds cannot be null");
        this.nanos = (Integer) Checks.checkNotNull(Integer.valueOf(source.readInt()), "nanos cannot be null");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.seconds.longValue());
        parcel.writeInt(this.nanos.intValue());
    }
}
