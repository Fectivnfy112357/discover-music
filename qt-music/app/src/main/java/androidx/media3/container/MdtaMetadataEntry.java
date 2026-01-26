package androidx.media3.container;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media3.common.Metadata;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class MdtaMetadataEntry implements Metadata.Entry {
    public static final Parcelable.Creator<MdtaMetadataEntry> CREATOR = new Parcelable.Creator<MdtaMetadataEntry>() { // from class: androidx.media3.container.MdtaMetadataEntry.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MdtaMetadataEntry createFromParcel(Parcel parcel) {
            return new MdtaMetadataEntry(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MdtaMetadataEntry[] newArray(int i) {
            return new MdtaMetadataEntry[i];
        }
    };
    public static final int DEFAULT_LOCALE_INDICATOR = 0;
    public static final byte EDITABLE_TRACKS_SAMPLES_LOCATION_INTERLEAVED = 1;
    public static final byte EDITABLE_TRACKS_SAMPLES_LOCATION_IN_EDIT_DATA_MP4 = 0;
    public static final String KEY_ANDROID_CAPTURE_FPS = "com.android.capture.fps";
    public static final String KEY_EDITABLE_TRACKS_LENGTH = "editable.tracks.length";
    public static final String KEY_EDITABLE_TRACKS_MAP = "editable.tracks.map";
    public static final String KEY_EDITABLE_TRACKS_OFFSET = "editable.tracks.offset";
    public static final String KEY_EDITABLE_TRACKS_SAMPLES_LOCATION = "editable.tracks.samples.location";
    public static final int TYPE_INDICATOR_8_BIT_UNSIGNED_INT = 75;
    public static final int TYPE_INDICATOR_FLOAT32 = 23;
    public static final int TYPE_INDICATOR_INT32 = 67;
    public static final int TYPE_INDICATOR_RESERVED = 0;
    public static final int TYPE_INDICATOR_STRING = 1;
    public static final int TYPE_INDICATOR_UNSIGNED_INT64 = 78;
    public final String key;
    public final int localeIndicator;
    public final int typeIndicator;
    public final byte[] value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MdtaMetadataEntry(String str, byte[] bArr, int i) {
        this(str, bArr, 0, i);
    }

    public MdtaMetadataEntry(String str, byte[] bArr, int i, int i2) {
        validateData(str, bArr, i2);
        this.key = str;
        this.value = bArr;
        this.localeIndicator = i;
        this.typeIndicator = i2;
    }

    private MdtaMetadataEntry(Parcel parcel) {
        String str = (String) Util.castNonNull(parcel.readString());
        this.key = str;
        byte[] bArr = (byte[]) Util.castNonNull(parcel.createByteArray());
        this.value = bArr;
        this.localeIndicator = parcel.readInt();
        int i = parcel.readInt();
        this.typeIndicator = i;
        validateData(str, bArr, i);
    }

    public List<Integer> getEditableTrackTypesFromMap() {
        Assertions.checkState(this.key.equals(KEY_EDITABLE_TRACKS_MAP), "Metadata is not an editable tracks map");
        byte b = this.value[1];
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < b; i++) {
            arrayList.add(Integer.valueOf(this.value[i + 2]));
        }
        return arrayList;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) obj;
        return this.key.equals(mdtaMetadataEntry.key) && Arrays.equals(this.value, mdtaMetadataEntry.value) && this.localeIndicator == mdtaMetadataEntry.localeIndicator && this.typeIndicator == mdtaMetadataEntry.typeIndicator;
    }

    public int hashCode() {
        return ((((((527 + this.key.hashCode()) * 31) + Arrays.hashCode(this.value)) * 31) + this.localeIndicator) * 31) + this.typeIndicator;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String toString() {
        /*
            r3 = this;
            int r0 = r3.typeIndicator
            if (r0 == 0) goto L57
            r1 = 1
            if (r0 == r1) goto L50
            r1 = 23
            if (r0 == r1) goto L41
            r1 = 67
            if (r0 == r1) goto L36
            r1 = 75
            if (r0 == r1) goto L28
            r1 = 78
            if (r0 == r1) goto L18
            goto L6a
        L18:
            androidx.media3.common.util.ParsableByteArray r0 = new androidx.media3.common.util.ParsableByteArray
            byte[] r1 = r3.value
            r0.<init>(r1)
            long r0 = r0.readUnsignedLongToLong()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L70
        L28:
            byte[] r0 = r3.value
            r1 = 0
            r0 = r0[r1]
            int r0 = kotlin.UByte$$ExternalSyntheticBackport0.m(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L70
        L36:
            byte[] r0 = r3.value
            int r0 = com.google.common.primitives.Ints.fromByteArray(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L70
        L41:
            byte[] r0 = r3.value
            int r0 = com.google.common.primitives.Ints.fromByteArray(r0)
            float r0 = java.lang.Float.intBitsToFloat(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L70
        L50:
            byte[] r0 = r3.value
            java.lang.String r0 = androidx.media3.common.util.Util.fromUtf8Bytes(r0)
            goto L70
        L57:
            java.lang.String r0 = r3.key
            java.lang.String r1 = "editable.tracks.map"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L6a
            java.util.List r0 = r3.getEditableTrackTypesFromMap()
            java.lang.String r0 = getFormattedValueForEditableTracksMap(r0)
            goto L70
        L6a:
            byte[] r0 = r3.value
            java.lang.String r0 = androidx.media3.common.util.Util.toHexString(r0)
        L70:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "mdta: key="
            r1.<init>(r2)
            java.lang.String r2 = r3.key
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r2 = ", value="
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r0 = r1.append(r0)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.container.MdtaMetadataEntry.toString():java.lang.String");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.key);
        parcel.writeByteArray(this.value);
        parcel.writeInt(this.localeIndicator);
        parcel.writeInt(this.typeIndicator);
    }

    private static void validateData(String str, byte[] bArr, int i) {
        boolean z;
        byte b;
        str.hashCode();
        switch (str) {
            case "com.android.capture.fps":
                if (i == 23 && bArr.length == 4) {
                    z = true;
                }
                Assertions.checkArgument(z);
                break;
            case "editable.tracks.samples.location":
                if (i == 75 && bArr.length == 1 && ((b = bArr[0]) == 0 || b == 1)) {
                    z = true;
                }
                Assertions.checkArgument(z);
                break;
            case "editable.tracks.length":
            case "editable.tracks.offset":
                if (i == 78 && bArr.length == 8) {
                    z = true;
                }
                Assertions.checkArgument(z);
                break;
            case "editable.tracks.map":
                Assertions.checkArgument(i == 0);
                break;
        }
    }

    private static String getFormattedValueForEditableTracksMap(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("track types = ");
        Joiner.on(',').appendTo(sb, (Iterable<? extends Object>) list);
        return sb.toString();
    }
}
