package org.jaudiotagger.tag.id3.reference;

/* loaded from: classes3.dex */
public class ITunesRating extends ID3Rating {
    private static ID3Rating rating;

    @Override // org.jaudiotagger.tag.id3.reference.ID3Rating
    public int convertRatingToFiveStarScale(int i) {
        if (i <= 0) {
            return 0;
        }
        if (i <= 20) {
            return 1;
        }
        if (i <= 40) {
            return 2;
        }
        if (i <= 60) {
            return 3;
        }
        return i <= 80 ? 4 : 5;
    }

    private ITunesRating() {
    }

    @Override // org.jaudiotagger.tag.id3.reference.ID3Rating
    public int convertRatingFromFiveStarScale(int i) {
        if (i < 0 || i > 5) {
            throw new IllegalArgumentException("convert Ratings from Five Star Scale accepts values from 0 to 5 not:" + i);
        }
        if (i == 1) {
            return 20;
        }
        if (i == 2) {
            return 40;
        }
        if (i == 3) {
            return 60;
        }
        if (i != 4) {
            return i != 5 ? 0 : 100;
        }
        return 80;
    }

    public static ID3Rating getInstance() {
        if (rating == null) {
            rating = new ITunesRating();
        }
        return rating;
    }
}
