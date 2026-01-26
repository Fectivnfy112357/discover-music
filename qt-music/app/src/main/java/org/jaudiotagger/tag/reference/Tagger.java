package org.jaudiotagger.tag.reference;

/* loaded from: classes3.dex */
public enum Tagger {
    ITUNES(0, "iTunes"),
    MEDIAPLAYER(1, "Windows Media Player"),
    WINAMP(2, "Winamp"),
    MP3TAG(3, "Mp3 Tag"),
    MEDIA_MONKEY(4, "Media Monkey"),
    TAG_AND_RENAME(5, "Tag and Rename"),
    PICARD(6, "Picard"),
    JAIKOZ(7, "Jaikoz"),
    TAGSCANNER(8, "Tagscanner"),
    XIPH(9, "Xiph"),
    FOOBAR2000(10, "Foobar2000"),
    BEATUNES(11, "Beatunes"),
    SONGBIRD(12, "Songbird"),
    JRIVER(13, "JRiver"),
    GODFATHER(14, "The Godfather"),
    MUSICHI(15, "Musichi"),
    ROON(16, "Roon"),
    SONGKONG(17, "SongKong"),
    MINIMSERVER(18, "MinimServer");

    private String desc;

    Tagger(int i, String str) {
        this.desc = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.desc;
    }
}
