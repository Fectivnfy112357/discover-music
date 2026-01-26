package org.jaudiotagger.tag.id3.reference;

import org.jaudiotagger.tag.reference.Tagger;

/* loaded from: classes3.dex */
public abstract class ID3Rating {
    public abstract int convertRatingFromFiveStarScale(int i);

    public abstract int convertRatingToFiveStarScale(int i);

    /* renamed from: org.jaudiotagger.tag.id3.reference.ID3Rating$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$jaudiotagger$tag$reference$Tagger;

        static {
            int[] iArr = new int[Tagger.values().length];
            $SwitchMap$org$jaudiotagger$tag$reference$Tagger = iArr;
            try {
                iArr[Tagger.ITUNES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$reference$Tagger[Tagger.MEDIA_MONKEY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$jaudiotagger$tag$reference$Tagger[Tagger.MEDIAPLAYER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static ID3Rating getInstance(Tagger tagger) {
        int i = AnonymousClass1.$SwitchMap$org$jaudiotagger$tag$reference$Tagger[tagger.ordinal()];
        if (i == 1) {
            return ITunesRating.getInstance();
        }
        if (i == 2) {
            return MediaMonkeyPlayerRating.getInstance();
        }
        if (i == 3) {
            return MediaPlayerRating.getInstance();
        }
        return MediaPlayerRating.getInstance();
    }
}
