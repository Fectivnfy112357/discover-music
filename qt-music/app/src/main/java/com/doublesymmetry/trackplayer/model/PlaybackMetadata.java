package com.doublesymmetry.trackplayer.model;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.flac.VorbisComment;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.metadata.icy.IcyInfo;
import com.google.android.exoplayer2.metadata.id3.TextInformationFrame;
import com.google.android.exoplayer2.metadata.id3.UrlLinkFrame;
import com.google.android.exoplayer2.metadata.mp4.MdtaMetadataEntry;
import com.umeng.commonsdk.statistics.UMErrorCode;
import java.util.Iterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.jaudiotagger.audio.asf.data.ContentDescription;
import org.jaudiotagger.tag.datatype.DataTypes;
import org.jaudiotagger.tag.id3.ID3v22Frames;
import org.jaudiotagger.tag.id3.ID3v24Frames;

/* compiled from: PlaybackMetadata.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 (2\u00020\u0001:\u0001(BU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J[\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020&HÖ\u0001J\t\u0010'\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\f\"\u0004\b\u0012\u0010\u000eR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\fR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\f\"\u0004\b\u0019\u0010\u000e¨\u0006)"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/PlaybackMetadata;", "", "source", "", "title", "url", "artist", "album", "date", "genre", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAlbum", "()Ljava/lang/String;", "setAlbum", "(Ljava/lang/String;)V", "getArtist", "setArtist", "getDate", "setDate", "getGenre", "setGenre", "getSource", "getTitle", "setTitle", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public final /* data */ class PlaybackMetadata {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String album;
    private String artist;
    private String date;
    private String genre;
    private final String source;
    private String title;
    private String url;

    public static /* synthetic */ PlaybackMetadata copy$default(PlaybackMetadata playbackMetadata, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = playbackMetadata.source;
        }
        if ((i & 2) != 0) {
            str2 = playbackMetadata.title;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = playbackMetadata.url;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = playbackMetadata.artist;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = playbackMetadata.album;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = playbackMetadata.date;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = playbackMetadata.genre;
        }
        return playbackMetadata.copy(str, str8, str9, str10, str11, str12, str7);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    /* renamed from: component2, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component3, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component4, reason: from getter */
    public final String getArtist() {
        return this.artist;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAlbum() {
        return this.album;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDate() {
        return this.date;
    }

    /* renamed from: component7, reason: from getter */
    public final String getGenre() {
        return this.genre;
    }

    public final PlaybackMetadata copy(String source, String title, String url, String artist, String album, String date, String genre) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new PlaybackMetadata(source, title, url, artist, album, date, genre);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PlaybackMetadata)) {
            return false;
        }
        PlaybackMetadata playbackMetadata = (PlaybackMetadata) other;
        return Intrinsics.areEqual(this.source, playbackMetadata.source) && Intrinsics.areEqual(this.title, playbackMetadata.title) && Intrinsics.areEqual(this.url, playbackMetadata.url) && Intrinsics.areEqual(this.artist, playbackMetadata.artist) && Intrinsics.areEqual(this.album, playbackMetadata.album) && Intrinsics.areEqual(this.date, playbackMetadata.date) && Intrinsics.areEqual(this.genre, playbackMetadata.genre);
    }

    public int hashCode() {
        int iHashCode = this.source.hashCode() * 31;
        String str = this.title;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.url;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.artist;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.album;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.date;
        int iHashCode6 = (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.genre;
        return iHashCode6 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "PlaybackMetadata(source=" + this.source + ", title=" + this.title + ", url=" + this.url + ", artist=" + this.artist + ", album=" + this.album + ", date=" + this.date + ", genre=" + this.genre + ")";
    }

    public PlaybackMetadata(String source, String str, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.title = str;
        this.url = str2;
        this.artist = str3;
        this.album = str4;
        this.date = str5;
        this.genre = str6;
    }

    public /* synthetic */ PlaybackMetadata(String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2, (i & 4) != 0 ? null : str3, (i & 8) != 0 ? null : str4, (i & 16) != 0 ? null : str5, (i & 32) != 0 ? null : str6, (i & 64) == 0 ? str7 : null);
    }

    public final String getSource() {
        return this.source;
    }

    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(String str) {
        this.title = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getArtist() {
        return this.artist;
    }

    public final void setArtist(String str) {
        this.artist = str;
    }

    public final String getAlbum() {
        return this.album;
    }

    public final void setAlbum(String str) {
        this.album = str;
    }

    public final String getDate() {
        return this.date;
    }

    public final void setDate(String str) {
        this.date = str;
    }

    public final String getGenre() {
        return this.genre;
    }

    public final void setGenre(String str) {
        this.genre = str;
    }

    /* compiled from: PlaybackMetadata.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\n"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/PlaybackMetadata$Companion;", "", "()V", "fromIcy", "Lcom/doublesymmetry/trackplayer/model/PlaybackMetadata;", "metadata", "Lcom/google/android/exoplayer2/metadata/Metadata;", "fromId3Metadata", "fromQuickTime", "fromVorbisComment", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        public final PlaybackMetadata fromId3Metadata(com.google.android.exoplayer2.metadata.Metadata metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            boolean z = false;
            Iterator<Integer> it = RangesKt.until(0, metadata.length()).iterator();
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            while (it.hasNext()) {
                Metadata.Entry entry = metadata.get(((IntIterator) it).nextInt());
                Intrinsics.checkNotNullExpressionValue(entry, "get(...)");
                if (entry instanceof TextInformationFrame) {
                    TextInformationFrame textInformationFrame = (TextInformationFrame) entry;
                    String id = textInformationFrame.id;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    String upperCase = id.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                    switch (upperCase.hashCode()) {
                        case 82815:
                            if (!upperCase.equals(ID3v22Frames.FRAME_ID_V2_ALBUM)) {
                                break;
                            } else {
                                str4 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 82880:
                            if (!upperCase.equals(ID3v22Frames.FRAME_ID_V2_GENRE)) {
                                break;
                            } else {
                                str6 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 83253:
                            if (!upperCase.equals(ID3v22Frames.FRAME_ID_V2_ARTIST)) {
                                break;
                            } else {
                                str3 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 83255:
                            if (!upperCase.equals(ID3v22Frames.FRAME_ID_V2_TORY)) {
                                break;
                            } else {
                                str5 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 83378:
                            if (!upperCase.equals(ID3v22Frames.FRAME_ID_V2_TITLE)) {
                                break;
                            } else {
                                str = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2567331:
                            if (!upperCase.equals("TALB")) {
                                break;
                            } else {
                                str4 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2569358:
                            if (!upperCase.equals("TCON")) {
                                break;
                            } else {
                                str6 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2570401:
                            if (!upperCase.equals(ID3v24Frames.FRAME_ID_YEAR)) {
                                break;
                            } else {
                                str5 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2575251:
                            if (!upperCase.equals("TIT2")) {
                                break;
                            } else {
                                str = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2580454:
                            if (!upperCase.equals("TOAL")) {
                                break;
                            } else {
                                str4 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2580912:
                            if (!upperCase.equals("TOPE")) {
                                break;
                            } else {
                                str3 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                        case 2581512:
                            if (!upperCase.equals("TPE1")) {
                                break;
                            } else {
                                str3 = textInformationFrame.value;
                                z = true;
                                break;
                            }
                    }
                } else if (entry instanceof UrlLinkFrame) {
                    UrlLinkFrame urlLinkFrame = (UrlLinkFrame) entry;
                    String id2 = urlLinkFrame.id;
                    Intrinsics.checkNotNullExpressionValue(id2, "id");
                    String upperCase2 = id2.toUpperCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                    switch (upperCase2.hashCode()) {
                        case 85704:
                            if (!upperCase2.equals(ID3v22Frames.FRAME_ID_V2_URL_ARTIST_WEB)) {
                                break;
                            } else {
                                str2 = urlLinkFrame.url;
                                z = true;
                                break;
                            }
                        case 2669821:
                            if (!upperCase2.equals("WOAF")) {
                                break;
                            } else {
                                str2 = urlLinkFrame.url;
                                z = true;
                                break;
                            }
                        case 2669833:
                            if (!upperCase2.equals("WOAR")) {
                                break;
                            } else {
                                str2 = urlLinkFrame.url;
                                z = true;
                                break;
                            }
                        case 2669834:
                            if (!upperCase2.equals("WOAS")) {
                                break;
                            } else {
                                str2 = urlLinkFrame.url;
                                z = true;
                                break;
                            }
                    }
                }
            }
            if (z) {
                return new PlaybackMetadata("id3", str, str2, str3, str4, str5, str6);
            }
            return null;
        }

        public final PlaybackMetadata fromIcy(com.google.android.exoplayer2.metadata.Metadata metadata) {
            int iIndexOf$default;
            String strSubstring;
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            int length = metadata.length();
            int i = 0;
            while (true) {
                String strSubstring2 = null;
                if (i >= length) {
                    return null;
                }
                Metadata.Entry entry = metadata.get(i);
                Intrinsics.checkNotNullExpressionValue(entry, "get(...)");
                if (entry instanceof IcyHeaders) {
                    IcyHeaders icyHeaders = (IcyHeaders) entry;
                    return new PlaybackMetadata("icy-headers", icyHeaders.name, icyHeaders.url, null, null, null, icyHeaders.genre, 56, null);
                }
                if (entry instanceof IcyInfo) {
                    IcyInfo icyInfo = (IcyInfo) entry;
                    if (icyInfo.title == null) {
                        iIndexOf$default = -1;
                    } else {
                        String str = icyInfo.title;
                        Intrinsics.checkNotNull(str);
                        iIndexOf$default = StringsKt.indexOf$default((CharSequence) str, " - ", 0, false, 6, (Object) null);
                    }
                    if (iIndexOf$default != -1) {
                        String str2 = icyInfo.title;
                        Intrinsics.checkNotNull(str2);
                        strSubstring2 = str2.substring(0, iIndexOf$default);
                        Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                        String str3 = icyInfo.title;
                        Intrinsics.checkNotNull(str3);
                        strSubstring = str3.substring(iIndexOf$default + 3);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    } else {
                        strSubstring = icyInfo.title;
                    }
                    return new PlaybackMetadata("icy", strSubstring, icyInfo.url, strSubstring2, null, null, null, UMErrorCode.E_UM_BE_DEFLATE_FAILED, null);
                }
                i++;
            }
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        public final PlaybackMetadata fromVorbisComment(com.google.android.exoplayer2.metadata.Metadata metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            int length = metadata.length();
            boolean z = false;
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            String str6 = null;
            for (int i = 0; i < length; i++) {
                Metadata.Entry entry = metadata.get(i);
                Intrinsics.checkNotNullExpressionValue(entry, "get(...)");
                if (entry instanceof VorbisComment) {
                    VorbisComment vorbisComment = (VorbisComment) entry;
                    String str7 = vorbisComment.key;
                    switch (str7.hashCode()) {
                        case 84303:
                            if (str7.equals(DataTypes.OBJ_URL)) {
                                str2 = vorbisComment.value;
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 2090926:
                            if (str7.equals("DATE")) {
                                str5 = vorbisComment.value;
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 62359119:
                            if (str7.equals("ALBUM")) {
                                str4 = vorbisComment.value;
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 67703139:
                            if (str7.equals("GENRE")) {
                                str6 = vorbisComment.value;
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 79833656:
                            if (str7.equals(ContentDescription.KEY_TITLE)) {
                                str = vorbisComment.value;
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 1939198791:
                            if (str7.equals("ARTIST")) {
                                str3 = vorbisComment.value;
                                z = true;
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
            if (z) {
                return new PlaybackMetadata("vorbis-comment", str, str2, str3, str4, str5, str6);
            }
            return null;
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
        java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
        	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
         */
        public final PlaybackMetadata fromQuickTime(com.google.android.exoplayer2.metadata.Metadata metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            int length = metadata.length();
            boolean z = false;
            String string = null;
            String string2 = null;
            String string3 = null;
            String string4 = null;
            String string5 = null;
            for (int i = 0; i < length; i++) {
                Metadata.Entry entry = metadata.get(i);
                Intrinsics.checkNotNullExpressionValue(entry, "get(...)");
                if (entry instanceof MdtaMetadataEntry) {
                    MdtaMetadataEntry mdtaMetadataEntry = (MdtaMetadataEntry) entry;
                    String str = mdtaMetadataEntry.key;
                    switch (str.hashCode()) {
                        case -1709129566:
                            if (str.equals("com.apple.quicktime.creationdate")) {
                                string4 = mdtaMetadataEntry.value.toString();
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case -90973028:
                            if (str.equals("com.apple.quicktime.artist")) {
                                string2 = mdtaMetadataEntry.value.toString();
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 1659437690:
                            if (str.equals("com.apple.quicktime.album")) {
                                string3 = mdtaMetadataEntry.value.toString();
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 1664781710:
                            if (str.equals("com.apple.quicktime.genre")) {
                                string5 = mdtaMetadataEntry.value.toString();
                                z = true;
                                break;
                            } else {
                                break;
                            }
                        case 1676912227:
                            if (str.equals("com.apple.quicktime.title")) {
                                string = mdtaMetadataEntry.value.toString();
                                z = true;
                                break;
                            } else {
                                break;
                            }
                    }
                }
            }
            if (!z) {
                return null;
            }
            return new PlaybackMetadata("quicktime", string, null, string2, string3, string4, string5, 4, null);
        }
    }
}
