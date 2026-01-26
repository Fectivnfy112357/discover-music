package coil3.decode;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: DataSource.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcoil3/decode/DataSource;", "", "<init>", "(Ljava/lang/String;I)V", "MEMORY_CACHE", "MEMORY", "DISK", "NETWORK", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class DataSource {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ DataSource[] $VALUES;
    public static final DataSource MEMORY_CACHE = new DataSource("MEMORY_CACHE", 0);
    public static final DataSource MEMORY = new DataSource("MEMORY", 1);
    public static final DataSource DISK = new DataSource("DISK", 2);
    public static final DataSource NETWORK = new DataSource("NETWORK", 3);

    private static final /* synthetic */ DataSource[] $values() {
        return new DataSource[]{MEMORY_CACHE, MEMORY, DISK, NETWORK};
    }

    public static EnumEntries<DataSource> getEntries() {
        return $ENTRIES;
    }

    private DataSource(String str, int i) {
    }

    static {
        DataSource[] dataSourceArr$values = $values();
        $VALUES = dataSourceArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(dataSourceArr$values);
    }

    public static DataSource valueOf(String str) {
        return (DataSource) Enum.valueOf(DataSource.class, str);
    }

    public static DataSource[] values() {
        return (DataSource[]) $VALUES.clone();
    }
}
