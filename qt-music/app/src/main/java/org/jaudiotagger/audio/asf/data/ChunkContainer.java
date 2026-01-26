package org.jaudiotagger.audio.asf.data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jaudiotagger.audio.asf.util.ChunkPositionComparator;
import org.jaudiotagger.audio.asf.util.Utils;

/* loaded from: classes3.dex */
public class ChunkContainer extends Chunk {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Set<GUID> MULTI_CHUNKS;
    private final Map<GUID, List<Chunk>> chunkTable;

    static {
        HashSet hashSet = new HashSet();
        MULTI_CHUNKS = hashSet;
        hashSet.add(GUID.GUID_STREAM);
    }

    protected static boolean chunkstartsUnique(ChunkContainer chunkContainer) {
        HashSet hashSet = new HashSet();
        Iterator<Chunk> it = chunkContainer.getChunks().iterator();
        boolean zAdd = true;
        while (it.hasNext()) {
            zAdd &= hashSet.add(Long.valueOf(it.next().getPosition()));
        }
        return zAdd;
    }

    public ChunkContainer(GUID guid, long j, BigInteger bigInteger) {
        super(guid, j, bigInteger);
        this.chunkTable = new Hashtable();
    }

    public void addChunk(Chunk chunk) {
        List<Chunk> listAssertChunkList = assertChunkList(chunk.getGuid());
        if (!listAssertChunkList.isEmpty() && !MULTI_CHUNKS.contains(chunk.getGuid())) {
            throw new IllegalArgumentException("The GUID of the given chunk indicates, that there is no more instance allowed.");
        }
        listAssertChunkList.add(chunk);
    }

    protected List<Chunk> assertChunkList(GUID guid) {
        List<Chunk> list = this.chunkTable.get(guid);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        this.chunkTable.put(guid, arrayList);
        return arrayList;
    }

    public Collection<Chunk> getChunks() {
        ArrayList arrayList = new ArrayList();
        Iterator<List<Chunk>> it = this.chunkTable.values().iterator();
        while (it.hasNext()) {
            arrayList.addAll(it.next());
        }
        return arrayList;
    }

    protected Chunk getFirst(GUID guid, Class<? extends Chunk> cls) {
        List<Chunk> list = this.chunkTable.get(guid);
        if (list != null && !list.isEmpty()) {
            Chunk chunk = list.get(0);
            if (cls.isAssignableFrom(chunk.getClass())) {
                return chunk;
            }
        }
        return null;
    }

    public boolean hasChunkByGUID(GUID guid) {
        return this.chunkTable.containsKey(guid);
    }

    @Override // org.jaudiotagger.audio.asf.data.Chunk
    public String prettyPrint(String str) {
        return prettyPrint(str, "");
    }

    public String prettyPrint(String str, String str2) {
        StringBuilder sb = new StringBuilder(super.prettyPrint(str));
        sb.append(str2);
        sb.append(str).append("  |").append(Utils.LINE_SEPARATOR);
        ArrayList arrayList = new ArrayList(getChunks());
        Collections.sort(arrayList, new ChunkPositionComparator());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(((Chunk) it.next()).prettyPrint(str + "  |"));
            sb.append(str).append("  |").append(Utils.LINE_SEPARATOR);
        }
        return sb.toString();
    }
}
