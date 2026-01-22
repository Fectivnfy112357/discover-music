package com.music.service;

import lombok.extern.slf4j.Slf4j;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.images.StandardArtwork;
import org.springframework.stereotype.Service;

import java.io.File;

@Slf4j
@Service
public class AudioTagService {

    public void setAudioTags(File audioFile, String title, String artist, String album, String track, File coverFile) {
        if (audioFile == null || !audioFile.exists()) {
            log.warn("Audio file does not exist: {}", audioFile);
            return;
        }

        try {
            AudioFile f = AudioFileIO.read(audioFile);
            Tag tag = f.getTag();
            // 如果没有标签，创建一个默认的
            if (tag == null) {
                tag = f.createDefaultTag();
                f.setTag(tag);
            }

            // 设置基本信息
            if (title != null) tag.setField(FieldKey.TITLE, title);
            if (artist != null) tag.setField(FieldKey.ARTIST, artist);
            if (album != null) tag.setField(FieldKey.ALBUM, album);
            if (track != null) tag.setField(FieldKey.TRACK, track);

            // 设置封面
            if (coverFile != null && coverFile.exists()) {
                try {
                    // 先删除已有的封面信息
                    tag.deleteArtworkField();
                    
                    Artwork artwork = StandardArtwork.createArtworkFromFile(coverFile);
                    tag.setField(artwork);
                } catch (Exception e) {
                    log.error("Error setting cover art: {}", e.getMessage());
                }
            }

            f.commit();
            log.info("Successfully updated tags for: {}", audioFile.getName());

        } catch (Exception e) {
            log.error("Failed to set audio tags for file: {}", audioFile.getName(), e);
        }
    }
}
