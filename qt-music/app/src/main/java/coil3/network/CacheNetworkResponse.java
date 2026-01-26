package coil3.network;

import coil3.network.NetworkHeaders;
import coil3.network.internal.UtilsKt;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import okio.BufferedSink;
import okio.BufferedSource;

/* compiled from: CacheNetworkResponse.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, d2 = {"Lcoil3/network/CacheNetworkResponse;", "", "<init>", "()V", "readFrom", "Lcoil3/network/NetworkResponse;", "source", "Lokio/BufferedSource;", "writeTo", "", "response", "sink", "Lokio/BufferedSink;", "coil-network-core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes.dex */
public final class CacheNetworkResponse {
    public static final CacheNetworkResponse INSTANCE = new CacheNetworkResponse();

    private CacheNetworkResponse() {
    }

    public final NetworkResponse readFrom(BufferedSource source) throws NumberFormatException {
        int i = Integer.parseInt(source.readUtf8LineStrict());
        long j = Long.parseLong(source.readUtf8LineStrict());
        long j2 = Long.parseLong(source.readUtf8LineStrict());
        NetworkHeaders.Builder builder = new NetworkHeaders.Builder();
        int i2 = Integer.parseInt(source.readUtf8LineStrict());
        for (int i3 = 0; i3 < i2; i3++) {
            UtilsKt.append(builder, source.readUtf8LineStrict());
        }
        return new NetworkResponse(i, j, j2, builder.build(), null, null, 48, null);
    }

    public final void writeTo(NetworkResponse response, BufferedSink sink) throws IOException {
        sink.writeDecimalLong(response.getCode()).writeByte(10);
        sink.writeDecimalLong(response.getRequestMillis()).writeByte(10);
        sink.writeDecimalLong(response.getResponseMillis()).writeByte(10);
        Set<Map.Entry<String, List<String>>> setEntrySet = response.getHeaders().asMap().entrySet();
        Iterator<T> it = setEntrySet.iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((List) ((Map.Entry) it.next()).getValue()).size();
        }
        sink.writeDecimalLong(size).writeByte(10);
        for (Map.Entry<String, List<String>> entry : setEntrySet) {
            Iterator<String> it2 = entry.getValue().iterator();
            while (it2.hasNext()) {
                sink.writeUtf8(entry.getKey()).writeUtf8(":").writeUtf8(it2.next()).writeByte(10);
            }
        }
    }
}
