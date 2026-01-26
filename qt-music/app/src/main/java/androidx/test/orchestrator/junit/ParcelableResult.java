package androidx.test.orchestrator.junit;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/* loaded from: classes.dex */
public final class ParcelableResult implements Parcelable {
    public static final Parcelable.Creator<ParcelableResult> CREATOR = new Parcelable.Creator<ParcelableResult>() { // from class: androidx.test.orchestrator.junit.ParcelableResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableResult createFromParcel(Parcel in) {
            return new ParcelableResult(in);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableResult[] newArray(int size) {
            return new ParcelableResult[size];
        }
    };
    private final List<ParcelableFailure> failures;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableResult(List<ParcelableFailure> failures) {
        this.failures = failures;
    }

    public ParcelableResult(Result result) {
        this.failures = new ArrayList();
        Iterator<Failure> it = result.getFailures().iterator();
        while (it.hasNext()) {
            this.failures.add(new ParcelableFailure(it.next()));
        }
    }

    private ParcelableResult(Parcel in) {
        this.failures = new ArrayList();
        for (Object obj : in.readArray(ParcelableFailure[].class.getClassLoader())) {
            this.failures.add((ParcelableFailure) obj);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel out, int flags) {
        out.writeArray(this.failures.toArray());
    }

    public boolean wasSuccessful() {
        return this.failures.isEmpty();
    }

    public List<ParcelableFailure> getFailures() {
        return this.failures;
    }

    public int getFailureCount() {
        return this.failures.size();
    }
}
