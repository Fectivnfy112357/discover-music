package org.jaudiotagger.audio.asf.io;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.jaudiotagger.audio.asf.data.GUID;

/* loaded from: classes3.dex */
final class ModificationResult {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final long byteDifference;
    private final int chunkDifference;
    private final Set<GUID> occuredGUIDs;

    public ModificationResult(int i, long j, GUID... guidArr) {
        HashSet hashSet = new HashSet();
        this.occuredGUIDs = hashSet;
        this.chunkDifference = i;
        this.byteDifference = j;
        hashSet.addAll(Arrays.asList(guidArr));
    }

    public ModificationResult(int i, long j, Set<GUID> set) {
        HashSet hashSet = new HashSet();
        this.occuredGUIDs = hashSet;
        this.chunkDifference = i;
        this.byteDifference = j;
        hashSet.addAll(set);
    }

    public long getByteDifference() {
        return this.byteDifference;
    }

    public int getChunkCountDifference() {
        return this.chunkDifference;
    }

    public Set<GUID> getOccuredGUIDs() {
        return new HashSet(this.occuredGUIDs);
    }
}
