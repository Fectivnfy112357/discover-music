package com.localmediametadata;

import android.os.Bundle;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableMap;
import com.umeng.analytics.pro.bm;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.KeyNotFoundException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.ArtworkFactory;

/* loaded from: classes3.dex */
public class Metadata {
    private static WritableMap buildMetadata(MediaFile mediaFile, AudioHeader audioHeader, Tag tag) throws KeyNotFoundException {
        WritableMap writableMapCreateMap = Arguments.createMap();
        String first = tag.getFirst(FieldKey.TITLE);
        if ("".equals(first)) {
            first = Utils.getName(mediaFile.getName());
        }
        writableMapCreateMap.putString("name", first);
        writableMapCreateMap.putString("singer", tag.getFirst(FieldKey.ARTIST).replaceAll("\\u0000", "、"));
        writableMapCreateMap.putString("albumName", tag.getFirst(FieldKey.ALBUM));
        writableMapCreateMap.putDouble(bm.aY, audioHeader.getTrackLength());
        writableMapCreateMap.putString("bitrate", audioHeader.getBitRate());
        writableMapCreateMap.putString("type", audioHeader.getEncodingType());
        writableMapCreateMap.putString("ext", Utils.getFileExtension(mediaFile.getName()));
        writableMapCreateMap.putDouble("size", mediaFile.size());
        return writableMapCreateMap;
    }

    public static WritableMap readMetadata(ReactApplicationContext reactApplicationContext, String str) throws Exception {
        MediaFile mediaFile = new MediaFile(reactApplicationContext, str);
        try {
            AudioFile audioFile = AudioFileIO.read(mediaFile.getFile(false));
            return buildMetadata(mediaFile, audioFile.getAudioHeader(), audioFile.getTagOrCreateDefault());
        } finally {
            mediaFile.closeFile();
        }
    }

    public static void writeMetadata(File file, Bundle bundle, boolean z) throws Exception {
        Tag tagOrCreateAndSetDefault;
        AudioFile audioFile = AudioFileIO.read(file);
        if (z) {
            tagOrCreateAndSetDefault = audioFile.createDefaultTag();
            audioFile.setTag(tagOrCreateAndSetDefault);
        } else {
            tagOrCreateAndSetDefault = audioFile.getTagOrCreateAndSetDefault();
        }
        tagOrCreateAndSetDefault.setField(FieldKey.TITLE, bundle.getString("name", ""));
        tagOrCreateAndSetDefault.setField(FieldKey.ARTIST, bundle.getString("singer", ""));
        tagOrCreateAndSetDefault.setField(FieldKey.ALBUM, bundle.getString("albumName", ""));
        audioFile.commit();
    }

    public static void writeMetadata(ReactApplicationContext reactApplicationContext, String str, Bundle bundle, boolean z) throws Exception {
        MediaFile mediaFile = new MediaFile(reactApplicationContext, str);
        try {
            try {
                writeMetadata(mediaFile.getFile(true), bundle, z);
            } catch (Exception unused) {
                mediaFile.closeFile();
                writeMetadata(mediaFile.getTempFile(), bundle, z);
            }
        } finally {
            mediaFile.closeFile();
        }
    }

    public static String readPic(ReactApplicationContext reactApplicationContext, String str, String str2) throws Exception {
        MediaFile mediaFile = new MediaFile(reactApplicationContext, str);
        try {
            File file = mediaFile.getFile(false);
            Artwork firstArtwork = AudioFileIO.read(file).getTagOrCreateDefault().getFirstArtwork();
            if (firstArtwork == null) {
                return "";
            }
            if (firstArtwork.isLinked()) {
                return firstArtwork.getImageUrl();
            }
            File file2 = new File(str2);
            if (!file2.exists() && !file2.mkdirs()) {
                throw new Exception("Directory does not exist");
            }
            File file3 = new File(str2, Utils.getName(file.getName()) + "." + ImageFormats.getFormatForMimeType(firstArtwork.getMimeType()).toLowerCase());
            FileOutputStream fileOutputStream = new FileOutputStream(file3);
            try {
                fileOutputStream.write(firstArtwork.getBinaryData());
                fileOutputStream.close();
                return file3.getPath();
            } finally {
            }
        } finally {
            mediaFile.closeFile();
        }
    }

    public static void writeFlacPic(AudioFile audioFile, Artwork artwork) throws Exception {
        FlacTag flacTag = (FlacTag) audioFile.getTagOrCreateAndSetDefault();
        TagField tagFieldCreateArtworkField = flacTag.createArtworkField(artwork.getBinaryData(), artwork.getPictureType(), artwork.getMimeType(), artwork.getDescription(), artwork.getWidth(), artwork.getHeight(), 0, "image/jpeg".equals(artwork.getMimeType()) ? 24 : 32);
        flacTag.setField(tagFieldCreateArtworkField);
        try {
            audioFile.commit();
        } catch (Exception e) {
            if (e.getMessage().contains("permissions")) {
                flacTag.deleteArtworkField();
                audioFile.commit();
                flacTag.setField(tagFieldCreateArtworkField);
                audioFile.commit();
                return;
            }
            throw e;
        }
    }

    private static void writePic(File file, String str) throws Exception {
        AudioFile audioFile = AudioFileIO.read(file);
        if ("".equals(str)) {
            audioFile.getTagOrCreateAndSetDefault().deleteArtworkField();
            audioFile.commit();
            return;
        }
        Artwork artworkCreateArtworkFromFile = ArtworkFactory.createArtworkFromFile(new File(str));
        if ("flac".equalsIgnoreCase(Utils.getFileExtension(file.getName()))) {
            writeFlacPic(audioFile, artworkCreateArtworkFromFile);
            return;
        }
        Tag tagOrCreateAndSetDefault = audioFile.getTagOrCreateAndSetDefault();
        tagOrCreateAndSetDefault.setField(artworkCreateArtworkFromFile);
        try {
            audioFile.commit();
        } catch (Exception e) {
            if (e.getMessage().contains("permissions")) {
                tagOrCreateAndSetDefault.deleteArtworkField();
                audioFile.commit();
                tagOrCreateAndSetDefault.setField(artworkCreateArtworkFromFile);
                audioFile.commit();
                return;
            }
            throw e;
        }
    }

    public static void writePic(ReactApplicationContext reactApplicationContext, String str, String str2) throws Exception {
        MediaFile mediaFile = new MediaFile(reactApplicationContext, str);
        try {
            try {
                writePic(mediaFile.getFile(true), str2);
            } catch (Exception unused) {
                mediaFile.closeFile();
                writePic(mediaFile.getTempFile(), str2);
            }
        } finally {
            mediaFile.closeFile();
        }
    }

    public static String readLyricFile(File file) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    byteArrayOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    return Utils.decodeString(byteArrayOutputStream.toByteArray());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String readLyric(ReactApplicationContext reactApplicationContext, String str, boolean z) throws Exception {
        MediaFile mediaFile = new MediaFile(reactApplicationContext, str);
        MediaFile mediaFile2 = z ? new MediaFile(reactApplicationContext, str.substring(0, str.lastIndexOf(".")) + ".lrc") : null;
        try {
            File file = mediaFile.getFile(false);
            if (z && mediaFile2.exists()) {
                String lyricFile = readLyricFile(mediaFile2.getFile(false));
                if (!"".equals(lyricFile)) {
                    return lyricFile;
                }
            }
            return AudioFileIO.read(file).getTagOrCreateDefault().getFirst(FieldKey.LYRICS);
        } finally {
            mediaFile.closeFile();
        }
    }

    public static void writeLyric(File file, String str) throws Exception {
        AudioFile audioFile = AudioFileIO.read(file);
        Tag tagOrCreateAndSetDefault = audioFile.getTagOrCreateAndSetDefault();
        if ("".equals(str)) {
            tagOrCreateAndSetDefault.deleteField(FieldKey.LYRICS);
            audioFile.commit();
            return;
        }
        tagOrCreateAndSetDefault.setField(FieldKey.LYRICS, str);
        try {
            audioFile.commit();
        } catch (Exception e) {
            if (e.getMessage().contains("permissions")) {
                tagOrCreateAndSetDefault.deleteField(FieldKey.LYRICS);
                audioFile.commit();
                tagOrCreateAndSetDefault.setField(FieldKey.LYRICS, str);
                audioFile.commit();
                return;
            }
            throw e;
        }
    }

    public static void writeLyric(ReactApplicationContext reactApplicationContext, String str, String str2) throws Exception {
        MediaFile mediaFile = new MediaFile(reactApplicationContext, str);
        try {
            try {
                writeLyric(mediaFile.getFile(true), str2);
            } catch (Exception unused) {
                mediaFile.closeFile();
                writeLyric(mediaFile.getTempFile(), str2);
            }
        } finally {
            mediaFile.closeFile();
        }
    }
}
