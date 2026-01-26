package org.jaudiotagger.tag.datatype;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes3.dex */
public interface HashMapInterface<K, V> {
    Map<K, V> getKeyToValue();

    Map<V, K> getValueToKey();

    Iterator<V> iterator();
}
