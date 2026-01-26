package org.jaudiotagger.audio;

/* loaded from: classes3.dex */
public enum SupportedFileFormat {
    OGG("ogg", "Ogg"),
    OGA("oga", "Oga"),
    MP3("mp3", "Mp3"),
    FLAC("flac", "Flac"),
    MP4("mp4", "Mp4"),
    M4A("m4a", "Mp4"),
    M4P("m4p", "M4p"),
    WMA("wma", "Wma"),
    WAV("wav", "Wav"),
    RA("ra", "Ra"),
    RM("rm", "Rm"),
    M4B("m4b", "Mp4"),
    AIF("aif", "Aif"),
    AIFF("aiff", "Aif"),
    AIFC("aifc", "Aif Compressed"),
    DSF("dsf", "Dsf"),
    DFF("dff", "Dff");

    private String displayName;
    private String filesuffix;

    SupportedFileFormat(String str, String str2) {
        this.filesuffix = str;
        this.displayName = str2;
    }

    public String getFilesuffix() {
        return this.filesuffix;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
