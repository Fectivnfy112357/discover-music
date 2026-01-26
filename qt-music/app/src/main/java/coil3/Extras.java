package coil3;

import androidx.exifinterface.media.ExifInterface;
import coil3.util.Collections_jvmCommonKt;
import com.facebook.hermes.intl.Constants;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Extras.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0003\u000e\u000f\u0010B!\b\u0002\u0012\u0016\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J$\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\u0004H\u0086\u0002¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00010\u0003J\u0006\u0010\f\u001a\u00020\rR\u001e\u0010\u0002\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0004\u0012\u0004\u0012\u00020\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcoil3/Extras;", "", "data", "", "Lcoil3/Extras$Key;", "<init>", "(Ljava/util/Map;)V", "get", ExifInterface.GPS_DIRECTION_TRUE, "key", "(Lcoil3/Extras$Key;)Ljava/lang/Object;", "asMap", "newBuilder", "Lcoil3/Extras$Builder;", "Key", "Builder", "Companion", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Extras {
    public static final Extras EMPTY = new Builder().build();
    private final Map<Key<?>, Object> data;

    public /* synthetic */ Extras(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Extras) && Intrinsics.areEqual(this.data, ((Extras) obj).data);
    }

    public int hashCode() {
        return this.data.hashCode();
    }

    public String toString() {
        return "Extras(data=" + this.data + ')';
    }

    private Extras(Map<Key<?>, ? extends Object> map) {
        this.data = map;
    }

    public final <T> T get(Key<T> key) {
        return (T) this.data.get(key);
    }

    public final Map<Key<?>, Object> asMap() {
        return this.data;
    }

    public final Builder newBuilder() {
        return new Builder(this);
    }

    /* compiled from: Extras.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u0000 \t*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\tB\u000f\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0004\b\u0004\u0010\u0005R\u0013\u0010\u0003\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcoil3/Extras$Key;", ExifInterface.GPS_DIRECTION_TRUE, "", Constants.COLLATION_DEFAULT, "<init>", "(Ljava/lang/Object;)V", "getDefault", "()Ljava/lang/Object;", "Ljava/lang/Object;", "Companion", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Key<T> {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private final T default;

        public Key(T t) {
            this.default = t;
        }

        public final T getDefault() {
            return this.default;
        }

        /* compiled from: Extras.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcoil3/Extras$Key$Companion;", "", "<init>", "()V", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }
        }
    }

    /* compiled from: Extras.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\b\u0018\u00002\u00020\u0001B\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B!\b\u0016\u0012\u0016\u0010\u0004\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0004\b\u0002\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0002\u0010\nJ,\u0010\r\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00062\b\u0010\u0010\u001a\u0004\u0018\u0001H\u000eH\u0086\u0002¢\u0006\u0002\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\u0013\u001a\u00020\tR\u001e\u0010\u000b\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcoil3/Extras$Builder;", "", "<init>", "()V", "map", "", "Lcoil3/Extras$Key;", "(Ljava/util/Map;)V", "extras", "Lcoil3/Extras;", "(Lcoil3/Extras;)V", "data", "", "set", ExifInterface.GPS_DIRECTION_TRUE, "key", "value", "(Lcoil3/Extras$Key;Ljava/lang/Object;)Lcoil3/Extras$Builder;", "setAll", "build", "coil-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Builder {
        private final Map<Key<?>, Object> data;

        public Builder() {
            this.data = new LinkedHashMap();
        }

        public Builder(Map<Key<?>, ? extends Object> map) {
            this.data = MapsKt.toMutableMap(map);
        }

        public Builder(Extras extras) {
            this.data = MapsKt.toMutableMap(extras.data);
        }

        public final <T> Builder set(Key<T> key, T value) {
            if (value != null) {
                this.data.put(key, value);
            } else {
                this.data.remove(key);
            }
            return this;
        }

        public final Builder setAll(Extras extras) {
            for (Map.Entry entry : extras.data.entrySet()) {
                Key key = (Key) entry.getKey();
                Object value = entry.getValue();
                Intrinsics.checkNotNull(key, "null cannot be cast to non-null type coil3.Extras.Key<kotlin.Any>");
                set(key, value);
            }
            return this;
        }

        public final Extras build() {
            return new Extras(Collections_jvmCommonKt.toImmutableMap(this.data), null);
        }
    }
}
