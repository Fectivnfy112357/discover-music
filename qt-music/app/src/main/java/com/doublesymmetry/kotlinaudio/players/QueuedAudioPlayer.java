package com.doublesymmetry.kotlinaudio.players;

import android.content.Context;
import com.doublesymmetry.kotlinaudio.models.AudioItem;
import com.doublesymmetry.kotlinaudio.models.AudioItemHolder;
import com.doublesymmetry.kotlinaudio.models.BufferConfig;
import com.doublesymmetry.kotlinaudio.models.CacheConfig;
import com.doublesymmetry.kotlinaudio.models.DefaultQueuedPlayerOptions;
import com.doublesymmetry.kotlinaudio.models.PlayerConfig;
import com.doublesymmetry.kotlinaudio.players.components.MediaItemExtKt;
import com.google.android.exoplayer2.IllegalSeekPositionException;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.source.MediaSource;
import com.umeng.analytics.pro.f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: QueuedAudioPlayer.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0011\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u000e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0010J\u0016\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020/J\u0014\u0010+\u001a\u00020,2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014J\u001c\u0010+\u001a\u00020,2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010.\u001a\u00020/J\u001c\u0010+\u001a\u00020,2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u00100\u001a\u00020\fJ\b\u00101\u001a\u00020,H\u0016J\b\u00102\u001a\u00020,H\u0016J\u000e\u00103\u001a\u00020,2\u0006\u00104\u001a\u00020\fJ\u0016\u00103\u001a\u00020,2\u0006\u00104\u001a\u00020\f2\u0006\u0010.\u001a\u00020/J\u0010\u00105\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0010H\u0016J\u0018\u00105\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020/H\u0016J\u0016\u00106\u001a\u00020,2\u0006\u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\fJ\u0006\u00109\u001a\u00020,J\u0006\u0010:\u001a\u00020,J\u000e\u0010;\u001a\u00020,2\u0006\u00104\u001a\u00020\fJ\u0014\u0010;\u001a\u00020,2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\f0\u0014J\u0006\u0010=\u001a\u00020,J\u0006\u0010>\u001a\u00020,J\u0016\u0010?\u001a\u00020,2\u0006\u00104\u001a\u00020\f2\u0006\u0010-\u001a\u00020\u0010R\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0012R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00100\u00148F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0016R\u0014\u0010\u001e\u001a\u00020\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0013\u0010\"\u001a\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b#\u0010\u0019R\u0013\u0010$\u001a\u0004\u0018\u00010\u00108F¢\u0006\u0006\u001a\u0004\b%\u0010\u0012R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100\u00148F¢\u0006\u0006\u001a\u0004\b'\u0010\u0016R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/doublesymmetry/kotlinaudio/players/QueuedAudioPlayer;", "Lcom/doublesymmetry/kotlinaudio/players/BaseAudioPlayer;", f.X, "Landroid/content/Context;", "playerConfig", "Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;", "bufferConfig", "Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;", "cacheConfig", "Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;", "(Landroid/content/Context;Lcom/doublesymmetry/kotlinaudio/models/PlayerConfig;Lcom/doublesymmetry/kotlinaudio/models/BufferConfig;Lcom/doublesymmetry/kotlinaudio/models/CacheConfig;)V", "currentIndex", "", "getCurrentIndex", "()I", "currentItem", "Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "getCurrentItem", "()Lcom/doublesymmetry/kotlinaudio/models/AudioItem;", "items", "", "getItems", "()Ljava/util/List;", "nextIndex", "getNextIndex", "()Ljava/lang/Integer;", "nextItem", "getNextItem", "nextItems", "getNextItems", "playerOptions", "Lcom/doublesymmetry/kotlinaudio/models/DefaultQueuedPlayerOptions;", "getPlayerOptions", "()Lcom/doublesymmetry/kotlinaudio/models/DefaultQueuedPlayerOptions;", "previousIndex", "getPreviousIndex", "previousItem", "getPreviousItem", "previousItems", "getPreviousItems", "queue", "Ljava/util/LinkedList;", "Lcom/google/android/exoplayer2/source/MediaSource;", "add", "", "item", "playWhenReady", "", "atIndex", "clear", "destroy", "jumpToItem", "index", "load", "move", "fromIndex", "toIndex", "next", "previous", "remove", "indexes", "removePreviousItems", "removeUpcomingItems", "replaceItem", "kotlin-audio_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class QueuedAudioPlayer extends BaseAudioPlayer {
    private final DefaultQueuedPlayerOptions playerOptions;
    private final LinkedList<MediaSource> queue;

    public /* synthetic */ QueuedAudioPlayer(Context context, PlayerConfig playerConfig, BufferConfig bufferConfig, CacheConfig cacheConfig, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new PlayerConfig(false, false, false, null, null, 31, null) : playerConfig, (i & 4) != 0 ? null : bufferConfig, (i & 8) != 0 ? null : cacheConfig);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QueuedAudioPlayer(Context context, PlayerConfig playerConfig, BufferConfig bufferConfig, CacheConfig cacheConfig) {
        super(context, playerConfig, bufferConfig, cacheConfig);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playerConfig, "playerConfig");
        this.queue = new LinkedList<>();
        this.playerOptions = new DefaultQueuedPlayerOptions(getExoPlayer(), false, 2, null);
    }

    @Override // com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer
    public DefaultQueuedPlayerOptions getPlayerOptions() {
        return this.playerOptions;
    }

    public final int getCurrentIndex() {
        return getExoPlayer().getCurrentMediaItemIndex();
    }

    @Override // com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer
    public AudioItem getCurrentItem() {
        MediaItem mediaItem;
        AudioItemHolder audioItemHolder;
        MediaSource mediaSource = (MediaSource) CollectionsKt.getOrNull(this.queue, getCurrentIndex());
        if (mediaSource == null || (mediaItem = mediaSource.getMediaItem()) == null || (audioItemHolder = MediaItemExtKt.getAudioItemHolder(mediaItem)) == null) {
            return null;
        }
        return audioItemHolder.getAudioItem();
    }

    public final Integer getNextIndex() {
        if (getExoPlayer().getNextMediaItemIndex() == -1) {
            return null;
        }
        return Integer.valueOf(getExoPlayer().getNextMediaItemIndex());
    }

    public final Integer getPreviousIndex() {
        if (getExoPlayer().getPreviousMediaItemIndex() == -1) {
            return null;
        }
        return Integer.valueOf(getExoPlayer().getPreviousMediaItemIndex());
    }

    public final List<AudioItem> getItems() {
        LinkedList<MediaSource> linkedList = this.queue;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(linkedList, 10));
        Iterator<T> it = linkedList.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = ((MediaSource) it.next()).getMediaItem();
            Intrinsics.checkNotNullExpressionValue(mediaItem, "it.mediaItem");
            arrayList.add(MediaItemExtKt.getAudioItemHolder(mediaItem).getAudioItem());
        }
        return arrayList;
    }

    public final List<AudioItem> getPreviousItems() {
        if (this.queue.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<MediaSource> listSubList = this.queue.subList(0, getExoPlayer().getCurrentMediaItemIndex());
        Intrinsics.checkNotNullExpressionValue(listSubList, "queue\n                .s…er.currentMediaItemIndex)");
        List<MediaSource> list = listSubList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = ((MediaSource) it.next()).getMediaItem();
            Intrinsics.checkNotNullExpressionValue(mediaItem, "it.mediaItem");
            arrayList.add(MediaItemExtKt.getAudioItemHolder(mediaItem).getAudioItem());
        }
        return arrayList;
    }

    public final List<AudioItem> getNextItems() {
        if (this.queue.isEmpty()) {
            return CollectionsKt.emptyList();
        }
        List<MediaSource> listSubList = this.queue.subList(getExoPlayer().getCurrentMediaItemIndex(), CollectionsKt.getLastIndex(this.queue));
        Intrinsics.checkNotNullExpressionValue(listSubList, "queue\n                .s…emIndex, queue.lastIndex)");
        List<MediaSource> list = listSubList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            MediaItem mediaItem = ((MediaSource) it.next()).getMediaItem();
            Intrinsics.checkNotNullExpressionValue(mediaItem, "it.mediaItem");
            arrayList.add(MediaItemExtKt.getAudioItemHolder(mediaItem).getAudioItem());
        }
        return arrayList;
    }

    public final AudioItem getNextItem() {
        return (AudioItem) CollectionsKt.getOrNull(getItems(), getCurrentIndex() + 1);
    }

    public final AudioItem getPreviousItem() {
        return (AudioItem) CollectionsKt.getOrNull(getItems(), getCurrentIndex() - 1);
    }

    @Override // com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer
    public void load(AudioItem item, boolean playWhenReady) {
        Intrinsics.checkNotNullParameter(item, "item");
        load(item);
        getExoPlayer().setPlayWhenReady(playWhenReady);
    }

    @Override // com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer
    public void load(AudioItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (this.queue.isEmpty()) {
            add(item);
            return;
        }
        MediaSource mediaSourceFromAudioItem = getMediaSourceFromAudioItem(item);
        this.queue.set(getCurrentIndex(), mediaSourceFromAudioItem);
        getExoPlayer().addMediaSource(getCurrentIndex() + 1, mediaSourceFromAudioItem);
        getExoPlayer().removeMediaItem(getCurrentIndex());
        getExoPlayer().seekTo(getCurrentIndex(), -9223372036854775807L);
        getExoPlayer().prepare();
    }

    public final void add(AudioItem item, boolean playWhenReady) {
        Intrinsics.checkNotNullParameter(item, "item");
        getExoPlayer().setPlayWhenReady(playWhenReady);
        add(item);
    }

    public final void add(AudioItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        MediaSource mediaSourceFromAudioItem = getMediaSourceFromAudioItem(item);
        this.queue.add(mediaSourceFromAudioItem);
        getExoPlayer().addMediaSource(mediaSourceFromAudioItem);
        getExoPlayer().prepare();
    }

    public final void add(List<? extends AudioItem> items, boolean playWhenReady) {
        Intrinsics.checkNotNullParameter(items, "items");
        getExoPlayer().setPlayWhenReady(playWhenReady);
        add(items);
    }

    public final void add(List<? extends AudioItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        List<? extends AudioItem> list = items;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(getMediaSourceFromAudioItem((AudioItem) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        this.queue.addAll(arrayList2);
        getExoPlayer().addMediaSources(arrayList2);
        getExoPlayer().prepare();
    }

    public final void add(List<? extends AudioItem> items, int atIndex) {
        Intrinsics.checkNotNullParameter(items, "items");
        List<? extends AudioItem> list = items;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(getMediaSourceFromAudioItem((AudioItem) it.next()));
        }
        ArrayList arrayList2 = arrayList;
        this.queue.addAll(atIndex, arrayList2);
        getExoPlayer().addMediaSources(atIndex, arrayList2);
        getExoPlayer().prepare();
    }

    public final void remove(int index) {
        this.queue.remove(index);
        getExoPlayer().removeMediaItem(index);
    }

    public final void remove(List<Integer> indexes) {
        Intrinsics.checkNotNullParameter(indexes, "indexes");
        List mutableList = CollectionsKt.toMutableList((Collection) indexes);
        CollectionsKt.sortDescending(mutableList);
        Iterator it = mutableList.iterator();
        while (it.hasNext()) {
            remove(((Number) it.next()).intValue());
        }
    }

    public final void next() {
        getExoPlayer().seekToNextMediaItem();
        getExoPlayer().prepare();
    }

    public final void previous() {
        getExoPlayer().seekToPreviousMediaItem();
        getExoPlayer().prepare();
    }

    public final void move(int fromIndex, int toIndex) {
        getExoPlayer().moveMediaItem(fromIndex, toIndex);
        MediaSource mediaSource = this.queue.get(fromIndex);
        Intrinsics.checkNotNullExpressionValue(mediaSource, "queue[fromIndex]");
        MediaSource mediaSource2 = mediaSource;
        this.queue.remove(fromIndex);
        LinkedList<MediaSource> linkedList = this.queue;
        int size = getItems().size();
        if (toIndex <= fromIndex) {
            toIndex--;
        }
        linkedList.add(Math.max(0, Math.min(size, toIndex)), mediaSource2);
    }

    public final void jumpToItem(int index, boolean playWhenReady) {
        getExoPlayer().setPlayWhenReady(playWhenReady);
        jumpToItem(index);
    }

    public final void jumpToItem(int index) {
        try {
            getExoPlayer().seekTo(index, -9223372036854775807L);
            getExoPlayer().prepare();
        } catch (IllegalSeekPositionException unused) {
            throw new Error("This item index " + index + " does not exist. The size of the queue is " + this.queue.size() + " items.");
        }
    }

    public final void replaceItem(int index, AudioItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        this.queue.set(index, getMediaSourceFromAudioItem(item));
        if (index == getCurrentIndex()) {
            updateNotificationIfNecessary$kotlin_audio_release(item);
        }
    }

    public final void removeUpcomingItems() {
        if (CollectionsKt.getLastIndex(this.queue) == -1 || getCurrentIndex() == -1) {
            return;
        }
        int lastIndex = CollectionsKt.getLastIndex(this.queue) + 1;
        int currentIndex = getCurrentIndex() + 1;
        getExoPlayer().removeMediaItems(currentIndex, lastIndex);
        this.queue.subList(currentIndex, lastIndex).clear();
    }

    public final void removePreviousItems() {
        getExoPlayer().removeMediaItems(0, getCurrentIndex());
        this.queue.subList(0, getCurrentIndex()).clear();
    }

    @Override // com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer
    public void destroy() {
        this.queue.clear();
        super.destroy();
    }

    @Override // com.doublesymmetry.kotlinaudio.players.BaseAudioPlayer
    public void clear() {
        this.queue.clear();
        super.clear();
    }
}
