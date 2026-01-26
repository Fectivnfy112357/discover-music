package com.doublesymmetry.trackplayer.model;

import android.net.Uri;
import android.os.Bundle;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.material.timepicker.TimeModel;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: MetadataAdapter.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b6\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0004¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/MetadataAdapter;", "", "()V", "Companion", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class MetadataAdapter {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public /* synthetic */ MetadataAdapter(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: MetadataAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b2\u0006\u0010\u0005\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/doublesymmetry/trackplayer/model/MetadataAdapter$Companion;", "", "()V", "fromMediaMetadata", "Landroid/os/Bundle;", "metadata", "Lcom/google/android/exoplayer2/MediaMetadata;", "fromMetadata", "", "Lcom/google/android/exoplayer2/metadata/Metadata;", "react-native-track-player_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
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
        /* JADX WARN: Removed duplicated region for block: B:37:0x00ff  */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0111  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x0123  */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0135  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0147  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.util.List<android.os.Bundle> fromMetadata(com.google.android.exoplayer2.metadata.Metadata r23) {
            /*
                Method dump skipped, instructions count: 1044
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.doublesymmetry.trackplayer.model.MetadataAdapter.Companion.fromMetadata(com.google.android.exoplayer2.metadata.Metadata):java.util.List");
        }

        public final Bundle fromMediaMetadata(MediaMetadata metadata) {
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            Bundle bundle = new Bundle();
            CharSequence charSequence = metadata.title;
            if (charSequence != null) {
                bundle.putString("title", charSequence.toString());
            }
            CharSequence charSequence2 = metadata.artist;
            if (charSequence2 != null) {
                bundle.putString("artist", charSequence2.toString());
            }
            CharSequence charSequence3 = metadata.albumTitle;
            if (charSequence3 != null) {
                bundle.putString("albumName", charSequence3.toString());
            }
            CharSequence charSequence4 = metadata.subtitle;
            if (charSequence4 != null) {
                bundle.putString("subtitle", charSequence4.toString());
            }
            CharSequence charSequence5 = metadata.description;
            if (charSequence5 != null) {
                bundle.putString("description", charSequence5.toString());
            }
            Uri uri = metadata.artworkUri;
            if (uri != null) {
                bundle.putString("artworkUri", uri.toString());
            }
            Integer num = metadata.trackNumber;
            if (num != null) {
                Intrinsics.checkNotNull(num);
                bundle.putInt("trackNumber", num.intValue());
            }
            CharSequence charSequence6 = metadata.composer;
            if (charSequence6 != null) {
                bundle.putString("composer", charSequence6.toString());
            }
            CharSequence charSequence7 = metadata.conductor;
            if (charSequence7 != null) {
                bundle.putString("conductor", charSequence7.toString());
            }
            CharSequence charSequence8 = metadata.genre;
            if (charSequence8 != null) {
                bundle.putString("genre", charSequence8.toString());
            }
            CharSequence charSequence9 = metadata.compilation;
            if (charSequence9 != null) {
                bundle.putString("compilation", charSequence9.toString());
            }
            CharSequence charSequence10 = metadata.station;
            if (charSequence10 != null) {
                bundle.putString("station", charSequence10.toString());
            }
            Integer num2 = metadata.mediaType;
            if (num2 != null) {
                Intrinsics.checkNotNull(num2);
                bundle.putInt("mediaType", num2.intValue());
            }
            Pair pair = TuplesKt.to(metadata.recordingDay, metadata.recordingMonth);
            Integer num3 = (Integer) pair.component1();
            Integer num4 = (Integer) pair.component2();
            if (num3 != null && num4 != null) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{num3}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String str2 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{num4}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                bundle.putString("creationDate", str + str2);
            } else if (num3 != null) {
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String str3 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{num3}, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                bundle.putString("creationDate", str3);
            } else if (num4 != null) {
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String str4 = String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, Arrays.copyOf(new Object[]{num4}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
                bundle.putString("creationDate", str4);
            }
            Integer num5 = metadata.recordingYear;
            if (num5 != null) {
                bundle.putString("creationYear", String.valueOf(num5.intValue()));
            }
            return bundle;
        }
    }

    private MetadataAdapter() {
    }
}
