package com.music.controller;

import com.music.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/music")
public class MusicController {

    @Autowired
    private MusicService musicService;

    // 硬编码的服务器存储路径
    private static final String SERVER_ROOT_PATH = "/www/dk_project/dk_app/alist/data/data/我的音乐";
    //private static final String SERVER_ROOT_PATH = "C:\\Users\\86191\\Downloads\\test";

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build();

    @GetMapping("/search/{source}")
    public String search(@PathVariable String source,
                         @RequestParam String keyword,
                         @RequestParam(defaultValue = "1") int page) {
        try {
            switch (source.toLowerCase()) {
                case "kw": return musicService.searchKw(keyword, page);
                case "wy": return musicService.searchWy(keyword, page);
                case "kg": return musicService.searchKg(keyword, page);
                case "tx": return musicService.searchTx(keyword, page);
                case "mg": return musicService.searchMg(keyword, page);
                default: return "{\"error\": \"Unsupported source\"}";
            }
        } catch (Exception e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    @GetMapping("/detail/{source}")
    public String detail(@PathVariable String source,
                         @RequestParam String id,
                         @RequestParam(defaultValue = "standard") String level) {
        try {
            switch (source.toLowerCase()) {
                case "kw": return musicService.detailKw(id, level);
                case "wy": return musicService.detailWy(id, level);
                case "kg": return musicService.detailKg(id, level);
                case "tx": return musicService.detailTx(id, level);
                case "mg": return musicService.detailMg(id, level);
                default: return "{\"error\": \"Unsupported source\"}";
            }
        } catch (Exception e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }

    /**
     * 单文件下载代理
     */
    @GetMapping("/download")
    public void download(@RequestParam String url, @RequestParam String name, HttpServletResponse response) {
        try {
            Request request = new Request.Builder().url(url).addHeader("User-Agent", "Mozilla/5.0").build();
            try (Response okResponse = client.newCall(request).execute()) {
                if (!okResponse.isSuccessful()) {
                    response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Remote file download failed");
                    return;
                }
                String encodedFilename = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFilename);
                
                try (InputStream is = okResponse.body().byteStream();
                     OutputStream os = response.getOutputStream()) {
                    byte[] buffer = new byte[8192];
                    int len;
                    while ((len = is.read(buffer)) != -1) {
                        os.write(buffer, 0, len);
                    }
                    os.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量打包下载接口
     */
    @PostMapping("/download/batch")
    public void downloadBatch(@RequestBody Map<String, String> data, HttpServletResponse response) {
        String fileName = data.getOrDefault("fileName", "music.zip");
        
        try {
            String encodedFilename = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFilename);

            try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
                if (data.containsKey("audioUrl") && data.containsKey("audioName")) {
                    downloadUrlToZip(zos, data.get("audioUrl"), data.get("audioName"));
                }
                if (data.containsKey("lyricText") && data.containsKey("lyricName")) {
                    ZipEntry entry = new ZipEntry(data.get("lyricName"));
                    zos.putNextEntry(entry);
                    zos.write(data.get("lyricText").getBytes(StandardCharsets.UTF_8));
                    zos.closeEntry();
                }
                if (data.containsKey("coverUrl") && data.containsKey("coverName")) {
                    downloadUrlToZip(zos, data.get("coverUrl"), data.get("coverName"));
                }
                zos.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存到服务器接口
     */
    @PostMapping("/save-to-server")
    public Map<String, Object> saveToServer(@RequestBody Map<String, String> data) {
        Map<String, Object> result = new java.util.HashMap<>();
        
        String artist = data.getOrDefault("artist", "Unknown Artist").replaceAll("[/\\\\:*?\"<>|]", "_");
        String album = data.getOrDefault("album", "Unknown Album").replaceAll("[/\\\\:*?\"<>|]", "_");
        String songName = data.getOrDefault("songName", "Unknown Song").replaceAll("[/\\\\:*?\"<>|]", "_");
        
        String audioUrl = data.get("audioUrl");
        String lyricText = data.get("lyricText");
        String coverUrl = data.get("coverUrl");

        if (audioUrl == null) {
            result.put("code", 400);
            result.put("msg", "No audio URL provided");
            return result;
        }

        try {
            // 1. 构建目录: ROOT/歌手/专辑
            File artistDir = new File(SERVER_ROOT_PATH, artist);
            File albumDir = new File(artistDir, album);
            if (!albumDir.exists()) {
                if (!albumDir.mkdirs()) {
                    result.put("code", 500);
                    result.put("msg", "Failed to create directory");
                    return result;
                }
            }

            // 2. 计算序号
            int maxIndex = 0;
            File[] files = albumDir.listFiles();
            if (files != null) {
                Pattern pattern = Pattern.compile("^(\\d{2})-.*");
                for (File f : files) {
                    Matcher matcher = pattern.matcher(f.getName());
                    if (matcher.find()) {
                        try {
                            int index = Integer.parseInt(matcher.group(1));
                            if (index > maxIndex) maxIndex = index;
                        } catch (NumberFormatException ignored) {}
                    }
                }
            }
            int nextIndex = maxIndex + 1;
            String prefix = String.format("%02d-%s-%s", nextIndex, artist, songName);

            // 3. 保存文件
            // A. 音频
            String audioExt = audioUrl.contains(".flac") ? ".flac" : ".mp3";
            File audioFile = new File(albumDir, prefix + audioExt);
            downloadUrlToFile(audioUrl, audioFile);

            // B. 歌词
            if (lyricText != null && !lyricText.isEmpty()) {
                File lrcFile = new File(albumDir, prefix + ".lrc");
                try (FileOutputStream fos = new FileOutputStream(lrcFile)) {
                    fos.write(lyricText.getBytes(StandardCharsets.UTF_8));
                }
            }

            // C. 封面
            if (coverUrl != null && !coverUrl.isEmpty()) {
                File coverFile = new File(albumDir, prefix + "-cover.jpg");
                downloadUrlToFile(coverUrl, coverFile);
            }

            result.put("code", 200);
            result.put("msg", "Saved successfully");
            result.put("path", audioFile.getAbsolutePath());
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 500);
            result.put("msg", "Error: " + e.getMessage());
            return result;
        }
    }

    private void downloadUrlToZip(ZipOutputStream zos, String url, String entryName) {
        try {
            Request request = new Request.Builder().url(url).addHeader("User-Agent", "Mozilla/5.0").build();
            try (Response okResponse = client.newCall(request).execute()) {
                if (okResponse.isSuccessful() && okResponse.body() != null) {
                    ZipEntry entry = new ZipEntry(entryName);
                    zos.putNextEntry(entry);
                    try (InputStream is = okResponse.body().byteStream()) {
                        byte[] buffer = new byte[8192];
                        int len;
                        while ((len = is.read(buffer)) != -1) {
                            zos.write(buffer, 0, len);
                        }
                    }
                    zos.closeEntry();
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to download file to zip: " + entryName + " - " + e.getMessage());
        }
    }

    private void downloadUrlToFile(String url, File targetFile) throws Exception {
        Request request = new Request.Builder().url(url).addHeader("User-Agent", "Mozilla/5.0").build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new Exception("Download failed: " + response.code());
            }
            try (InputStream is = response.body().byteStream();
                 FileOutputStream fos = new FileOutputStream(targetFile)) {
                byte[] buffer = new byte[8192];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
        }
    }
}
