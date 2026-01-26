package org.jaudiotagger.tag.mp4.atom;

import com.facebook.react.views.progressbar.ReactProgressBarViewManager;

/* loaded from: classes3.dex */
public enum Mp4ContentTypeValue {
    MOVIE("Movie", 0),
    NORMAL(ReactProgressBarViewManager.DEFAULT_STYLE, 1),
    AUDIO_BOOK("AudioBook", 2),
    BOOKMARK("Whacked Bookmark", 5),
    MUSIC_VIDEO("Music Video", 6),
    SHORT_FILM("Short Film", 9),
    TV_SHOW("TV Show", 10),
    BOOKLET("Booklet", 11);

    private String description;
    private int id;

    Mp4ContentTypeValue(String str, int i) {
        this.description = str;
        this.id = i;
    }

    public int getId() {
        return this.id;
    }

    public String getIdAsString() {
        return String.valueOf(this.id);
    }

    public String getDescription() {
        return this.description;
    }
}
