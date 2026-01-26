package com.music.service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.music.util.CryptoUtil;
import com.music.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class MusicService {

    private static final String DEFAULT_CONFIG = "{\n" +
            "  \"endpoints\": {\n" +
            "    \"kw\": {\"protocol\": \"https\", \"domain\": \"music.haitangw.cc\", \"path\": \"/music/kw.php\", \"params\": {\"id\": \"{rid}\", \"level\": \"{level}\", \"type\": \"json\"}},\n" +
            "    \"wy\": {\"protocol\": \"https\", \"domain\": \"music.haitangw.cc\", \"path\": \"/wy/wy.php\", \"params\": {\"id\": \"{rid}\", \"level\": \"{level}\", \"type\": \"json\"}},\n" +
            "    \"kg\": {\"protocol\": \"https\", \"domain\": \"music.haitangw.cc\", \"path\": \"/kgqq/kg.php\", \"params\": {\"id\": \"{rid}\", \"level\": \"{level}\", \"type\": \"json\"}},\n" +
            "    \"qq\": {\"protocol\": \"https\", \"domain\": \"music.haitangw.cc\", \"path\": \"/kgqq/qq.php\", \"params\": {\"id\": \"{rid}\", \"level\": \"{level}\", \"type\": \"json\"}},\n" +
            "    \"tx\": {\"protocol\": \"https\", \"domain\": \"music.haitangw.cc\", \"path\": \"/kgqq/qq.php\", \"params\": {\"id\": \"{rid}\", \"level\": \"{level}\", \"type\": \"json\"}},\n" +
            "    \"mg\": {\"protocol\": \"https\", \"domain\": \"music.haitangw.cc\", \"path\": \"/musicapi/mg.php\", \"params\": {\"id\": \"{rid}\", \"level\": \"{level}\", \"type\": \"json\"}}\n" +
            "  }\n" +
            "}";

    private JSONObject detailConfig = JSON.parseObject(DEFAULT_CONFIG);

    /**
     * 小黄源 (kw) 搜索 - 酷我官方 API
     */
    public String searchKw(String keyword, int page) throws IOException {
        String url = "http://search.kuwo.cn/r.s?client=kt&all=" + URLEncoder.encode(keyword, "UTF-8") +
                "&pn=" + (page - 1) + "&rn=30&uid=794762570&ver=kwplayer_ar_9.2.2.1&vipver=1&show_copyright_off=1&newver=1&ft=music&cluster=0&strategy=2012&encoding=utf8&rformat=json&vermerge=1&mobi=1&issubtitle=1";

        String response = HttpUtil.get(url);
        // 酷我返回的 JSON 比较特殊，可能包含非标准字符
        JSONObject json = JSON.parseObject(response);
        JSONArray abslist = json.getJSONArray("abslist");
        JSONArray resultList = new JSONArray();

        if (abslist != null) {
            for (int i = 0; i < abslist.size(); i++) {
                JSONObject song = abslist.getJSONObject(i);
                JSONObject processed = processKwSongData(song);
                if (processed != null) resultList.add(processed);
            }
        }

        return buildSuccessResponse(resultList, "酷我音乐:本地官方API");
    }

    private JSONObject processKwSongData(JSONObject song) {
        try {
            String ridStr = song.getString("MUSICRID");
            if (ridStr == null || ridStr.isEmpty()) return null;
            String rid = ridStr.replace("MUSIC_", "");

            JSONObject res = new JSONObject();
            res.put("rid", rid);
            res.put("name", song.getString("SONGNAME"));
            res.put("artist", song.getString("ARTIST").replace("&", "、"));
            res.put("album", song.getString("ALBUM"));
            res.put("pic", getKwCover(song));
            res.put("duration", formatDuration(song.getIntValue("DURATION")));
            res.put("quality", parseKwQualityInfo(song.getString("N_MINFO")));
            return res;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 小红源 (wy) 搜索 - 网易云 EAPI
     */
    public String searchWy(String keyword, int page) throws IOException {
        JSONObject params = new JSONObject();
        params.put("s", keyword);
        params.put("type", 1);
        params.put("limit", 30);
        params.put("offset", (page - 1) * 30);
        params.put("total", page == 1);

        String encryptedParams = CryptoUtil.wyEapiEncrypt("/api/cloudsearch/pc", params.toJSONString());
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        headers.put("Origin", "https://music.163.com");

        String body = "params=" + encryptedParams;
        String response = HttpUtil.postForm("http://interface.music.163.com/eapi/batch", 
            new HashMap<String, String>() {{ put("params", encryptedParams); }}, headers);

        JSONObject json = JSON.parseObject(response);
        if (json.containsKey("result")) json = json.getJSONObject("result");
        
        JSONArray songs = json.getJSONArray("songs");
        JSONArray resultList = new JSONArray();
        if (songs != null) {
            for (int i = 0; i < songs.size(); i++) {
                JSONObject processed = processWySongData(songs.getJSONObject(i));
                if (processed != null) resultList.add(processed);
            }
        }
        return buildSuccessResponse(resultList, "网易云音乐:本地官方API");
    }

    private JSONObject processWySongData(JSONObject song) {
        try {
            JSONObject res = new JSONObject();
            String id = song.getString("id");
            if (id == null) return null;
            
            res.put("rid", id);
            res.put("name", song.getString("name"));
            res.put("artist", getWyArtistName(song.getJSONArray("ar")));
            
            JSONObject album = song.getJSONObject("al");
            if (album != null) {
                res.put("album", album.getString("name"));
                res.put("pic", album.getString("picUrl"));
            } else {
                res.put("album", "");
                res.put("pic", "");
            }
            
            res.put("duration", formatDuration(song.getIntValue("dt") / 1000));
            
            JSONArray quality = new JSONArray();
            
            // 获取最大音质级别
            String maxBrLevel = "";
            JSONObject privilege = song.getJSONObject("privilege");
            if (privilege != null) {
                maxBrLevel = privilege.getString("maxBrLevel");
            }
            
            // 普通音质 (l)
            JSONObject l = song.getJSONObject("l");
            if (l != null && l.getLongValue("size") > 0) {
                addQuality(quality, formatFileSize(l.getLongValue("size")), "普通音质MP3", "standard");
            }
            
            // 高音质 (h)
            JSONObject h = song.getJSONObject("h");
            if (h != null && h.getLongValue("size") > 0) {
                addQuality(quality, formatFileSize(h.getLongValue("size")), "高音质MP3", "exhigh");
            }
            
            // 无损音质 (sq)
            JSONObject sq = song.getJSONObject("sq");
            if (sq != null && sq.getLongValue("size") > 0) {
                addQuality(quality, formatFileSize(sq.getLongValue("size")), "无损音质FLAC", "lossless");
            }
            
            // 超清母带 (hr / hires)
            if ("hires".equals(maxBrLevel)) {
                JSONObject hr = song.getJSONObject("hr");
                String size = "未知大小";
                if (hr != null && hr.getLongValue("size") > 0) {
                    size = formatFileSize(hr.getLongValue("size"));
                }
                addQuality(quality, size, "超清母带FLAC", "jymaster");
            }
            
            if (quality.isEmpty()) {
                addQuality(quality, "未知大小", "普通音质MP3", "standard");
            }
            
            res.put("quality", quality);
            return res;
        } catch (Exception e) {
            log.error("处理网易云歌曲数据失败", e);
            return null;
        }
    }

    /**
     * 小狗源 (kg) 搜索 - 酷狗
     */
    public String searchKg(String keyword, int page) throws IOException {
        String url = "https://songsearch.kugou.com/song_search_v2?keyword=" + URLEncoder.encode(keyword, "UTF-8") +
                "&page=" + page + "&pagesize=30&platform=WebFilter";
        
        String response = HttpUtil.get(url);
        JSONObject json = JSON.parseObject(response);
        JSONArray lists = json.getJSONObject("data").getJSONArray("lists");
        JSONArray resultList = new JSONArray();

        if (lists != null) {
            for (int i = 0; i < lists.size(); i++) {
                JSONObject song = lists.getJSONObject(i);
                JSONObject processed = new JSONObject();
                processed.put("rid", song.getString("FileHash"));
                processed.put("name", song.getString("SongName"));
                processed.put("artist", song.getString("SingerName"));
                processed.put("album", song.getString("AlbumName"));
                processed.put("pic", ""); // 酷狗搜索结果通常不带图，详情才有
                processed.put("duration", formatDuration(song.getIntValue("Duration")));
                
                JSONArray quality = new JSONArray();
                if (song.getLongValue("FileSize") > 0) {
                    addQuality(quality, formatFileSize(song.getLongValue("FileSize")), "普通音质MP3", "standard");
                }
                if (song.getLongValue("HQFileSize") > 0) {
                    addQuality(quality, formatFileSize(song.getLongValue("HQFileSize")), "高音质MP3", "exhigh");
                }
                if (song.getLongValue("SQFileSize") > 0) {
                    addQuality(quality, formatFileSize(song.getLongValue("SQFileSize")), "无损音质FLAC", "lossless");
                }
                if (song.getLongValue("ResFileSize") > 0) {
                    addQuality(quality, formatFileSize(song.getLongValue("ResFileSize")), "超清母带FLAC", "jymaster");
                }
                if (quality.isEmpty()) {
                    addQuality(quality, "未知大小", "普通音质MP3", "standard");
                }
                
                processed.put("quality", quality);
                resultList.add(processed);
            }
        }
        return buildSuccessResponse(resultList, "酷狗音乐:本地官方API");
    }

    /**
     * 小绿源 (tx) 搜索 - QQ音乐
     */
    public String searchTx(String keyword, int page) throws IOException {
        JSONObject reqData = new JSONObject();
        JSONObject comm = new JSONObject();
        comm.put("ct", "11");
        comm.put("cv", "14090508");
        comm.put("v", "14090508");
        comm.put("tmeAppID", "qqmusic");
        comm.put("phonetype", "EBG-AN10");
        comm.put("deviceScore", "553.47");
        comm.put("devicelevel", "50");
        comm.put("newdevicelevel", "20");
        comm.put("rom", "HuaWei/EMOTION/EmotionUI_14.2.0");
        comm.put("os_ver", "12");
        comm.put("OpenUDID", "0");
        comm.put("OpenUDID2", "0");
        comm.put("QIMEI36", "0");
        comm.put("udid", "0");
        comm.put("chid", "0");
        comm.put("aid", "0");
        comm.put("oaid", "0");
        comm.put("taid", "0");
        comm.put("tid", "0");
        comm.put("wid", "0");
        comm.put("uid", "0");
        comm.put("sid", "0");
        comm.put("modeSwitch", "6");
        comm.put("teenMode", "0");
        comm.put("ui_mode", "2D");
        comm.put("nettype", "1020");
        comm.put("v4ip", "");
        reqData.put("comm", comm);

        JSONObject req = new JSONObject();
        req.put("method", "DoSearchForQQMusicMobile");
        req.put("module", "music.search.SearchCgiService");
        JSONObject param = new JSONObject();
        param.put("query", keyword);
        param.put("page_num", page);
        param.put("num_per_page", 30);
        param.put("search_type", 0);
        param.put("highlight", 0);
        param.put("nqc_flag", 0);
        param.put("multi_zhida", 0);
        param.put("cat", 2);
        param.put("grp", 1);
        param.put("sin", 0);
        param.put("sem", 0);
        req.put("param", param);
        reqData.put("req", req);

        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "QQMusic 14090508(android 12)");
        headers.put("Content-Type", "application/json");

        String response = HttpUtil.post("https://u.y.qq.com/cgi-bin/musicu.fcg", reqData.toJSONString(), headers);
        JSONObject json = JSON.parseObject(response);
        
        JSONArray list = null;
        // 增强的解析逻辑，与 Kotlin extractQqSongs 保持一致
        if (json.containsKey("req") && json.getJSONObject("req").containsKey("data")) {
             JSONObject data = json.getJSONObject("req").getJSONObject("data");
             if (data.containsKey("item_song")) {
                 list = data.getJSONArray("item_song");
             } else if (data.containsKey("body") && data.getJSONObject("body").containsKey("item_song")) {
                 list = data.getJSONObject("body").getJSONArray("item_song");
             } else if (data.containsKey("song")) {
                 list = data.getJSONArray("song");
             } else if (data.containsKey("list")) {
                 list = data.getJSONArray("list");
             }
        }
        
        // 最后的兜底：如果还找不到，尝试遍历 data 的所有 key (Kotlin 逻辑)
        if (list == null && json.containsKey("req") && json.getJSONObject("req").containsKey("data")) {
            JSONObject data = json.getJSONObject("req").getJSONObject("data");
             for (String key : data.keySet()) {
                 Object val = data.get(key);
                 if (val instanceof JSONObject && ((JSONObject) val).containsKey("file")) {
                     if (list == null) list = new JSONArray();
                     list.add(val);
                 }
             }
        }
        
        JSONArray resultList = new JSONArray();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                JSONObject song = list.getJSONObject(i);
                JSONObject processed = new JSONObject();
                
                // 确保有 mid 且 file 对象存在 (Kotlin 逻辑: song.has("file") && song.has("mid"))
                if (!song.containsKey("mid") || !song.containsKey("file")) continue;

                processed.put("rid", song.getString("mid"));
                String name = song.getString("name");
                String titleExtra = song.getString("title_extra");
                if (titleExtra != null && !titleExtra.isEmpty()) {
                    name = name + titleExtra;
                }
                processed.put("name", name);
                processed.put("artist", getQqArtistName(song.getJSONArray("singer")));
                
                String albumName = "";
                String albumMid = "";
                if (song.containsKey("album")) {
                    JSONObject album = song.getJSONObject("album");
                    albumName = album.getString("name");
                    albumMid = album.getString("mid");
                }
                processed.put("album", albumName);
                
                // 封面逻辑优化
                String pic = "";
                if (albumMid != null && !albumMid.isEmpty() && !"空".equals(albumMid)) {
                    pic = "https://y.gtimg.cn/music/photo_new/T002R500x500M000" + albumMid + ".jpg";
                } else {
                    JSONArray singers = song.getJSONArray("singer");
                    if (singers != null && !singers.isEmpty()) {
                        String singerMid = singers.getJSONObject(0).getString("mid");
                        if (singerMid != null && !singerMid.isEmpty()) {
                            pic = "https://y.gtimg.cn/music/photo_new/T001R500x500M000" + singerMid + ".jpg";
                        }
                    }
                }
                processed.put("pic", pic);
                processed.put("duration", formatDuration(song.getIntValue("interval")));
                
                // 音质解析
                JSONArray quality = new JSONArray();
                JSONObject file = song.getJSONObject("file");
                if (file.getLongValue("size_128mp3") > 0) addQuality(quality, formatFileSize(file.getLongValue("size_128mp3")), "普通音质MP3", "standard");
                if (file.getLongValue("size_320mp3") > 0) addQuality(quality, formatFileSize(file.getLongValue("size_320mp3")), "高音质MP3", "exhigh");
                if (file.getLongValue("size_flac") > 0) addQuality(quality, formatFileSize(file.getLongValue("size_flac")), "无损音质FLAC", "lossless");
                if (file.getLongValue("size_hires") > 0) addQuality(quality, formatFileSize(file.getLongValue("size_hires")), "超清母带FLAC", "jymaster");
                if (quality.isEmpty()) addQuality(quality, "未知大小", "普通音质MP3", "standard");
                
                processed.put("quality", quality);
                resultList.add(processed);
            }
        }
        return buildSuccessResponse(resultList, "QQ音乐:本地官方API");
    }

    /**
     * 小粉源 (mg) 搜索 - 咪咕
     */
    public String searchMg(String keyword, int page) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        Map<String, String> sig = CryptoUtil.createMgSignature(timestamp, keyword);
        
        String searchSwitch = "{\"song\":1,\"album\":0,\"singer\":0,\"tagSong\":1,\"mvSong\":0,\"bestShow\":1,\"songlist\":0,\"lyricSong\":0}";
        String url = "https://jadeite.migu.cn/music_search/v3/search/searchAll?isCorrect=0&isCopyright=1&searchSwitch=" + 
                URLEncoder.encode(searchSwitch, "UTF-8") + "&pageSize=30&text=" + 
                URLEncoder.encode(keyword, "UTF-8") + "&pageNo=" + page + "&sort=0&sid=USS";
        
        Map<String, String> headers = new HashMap<>();
        headers.put("uiVersion", "A_music_3.6.1");
        headers.put("deviceId", sig.get("deviceId"));
        headers.put("timestamp", timestamp);
        headers.put("sign", sig.get("sign"));
        headers.put("channel", "0146921");
        headers.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 11.0.0; zh-cn; MI 11 Build/OPR1.170623.032) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30");

        String response = HttpUtil.get(url, headers);
        JSONObject json = JSON.parseObject(response);
        JSONArray resultList = new JSONArray();
        
        JSONObject songResultData = json.getJSONObject("songResultData");
        if (songResultData != null) {
            JSONArray lists = songResultData.getJSONArray("resultList");
            if (lists != null) {
                for (int i = 0; i < lists.size(); i++) {
                    JSONArray innerList = lists.getJSONArray(i);
                    for (int j = 0; j < innerList.size(); j++) {
                        JSONObject song = innerList.getJSONObject(j);
                        if (!song.containsKey("copyrightId")) continue; // 过滤非歌曲

                        JSONObject processed = new JSONObject();
                        processed.put("rid", song.getString("copyrightId"));
                        processed.put("name", song.getString("name"));
                        processed.put("artist", getMgArtistName(song.getJSONArray("singerList")));
                        processed.put("album", song.getString("album"));
                        processed.put("pic", song.getString("img1"));
                        processed.put("duration", "未知");
                        
                        JSONArray quality = new JSONArray();
                        JSONArray audioFormats = song.getJSONArray("audioFormats");
                        if (audioFormats != null) {
                             for(int k=0; k<audioFormats.size(); k++) {
                                 JSONObject fmt = audioFormats.getJSONObject(k);
                                 String formatType = fmt.getString("formatType");
                                 long size = fmt.containsKey("asize") ? fmt.getLongValue("asize") : fmt.getLongValue("isize");
                                 
                                 if ("PQ".equals(formatType)) addQuality(quality, formatFileSize(size), "普通音质MP3", "standard");
                                 else if ("HQ".equals(formatType)) addQuality(quality, formatFileSize(size), "高音质MP3", "exhigh");
                                 else if ("SQ".equals(formatType)) addQuality(quality, formatFileSize(size), "无损音质FLAC", "lossless");
                                 else if ("ZQ24".equals(formatType)) addQuality(quality, formatFileSize(size), "超清母带FLAC", "jymaster");
                             }
                        }
                        
                        if (quality.isEmpty()) {
                            addQuality(quality, "未知", "普通音质MP3", "standard");
                        }
                        
                        processed.put("quality", quality);
                        resultList.add(processed);
                    }
                }
            }
        }
        return buildSuccessResponse(resultList, "咪咕音乐:本地官方API");
    }

    // --- 详情接口逻辑 ---

    public String detailKw(String rid, String level) throws IOException { return getDetail("kw", rid, level); }
    public String detailWy(String rid, String level) throws IOException {
        JSONObject resultData = new JSONObject();
        
        // 1. 获取基础详情 (Metadata)
        try {
            JSONObject params = new JSONObject();
            params.put("c", "[{\"id\":" + rid + "}]");
            params.put("ids", "[" + rid + "]");
            String enc = CryptoUtil.wyEapiEncrypt("/api/v3/song/detail", params.toJSONString());
            String resp = HttpUtil.postForm("http://interface.music.163.com/eapi/v3/song/detail", 
                new HashMap<String, String>() {{ put("params", enc); }}, 
                new HashMap<String, String>() {{ put("Origin", "https://music.163.com"); }});
            
            JSONObject json = JSON.parseObject(resp);
            if (json.containsKey("songs")) {
                JSONArray songs = json.getJSONArray("songs");
                if (!songs.isEmpty()) {
                    JSONObject s = songs.getJSONObject(0);
                    resultData.put("name", s.getString("name"));
                    resultData.put("artist", getWyArtistName(s.getJSONArray("ar")));
                    JSONObject al = s.getJSONObject("al");
                    resultData.put("album", al != null ? al.getString("name") : "");
                    resultData.put("pic", al != null ? al.getString("picUrl") : "");
                }
            }
        } catch (Exception e) {
            log.error("Wy detail error", e);
        }

        // 2. 获取播放链接 (URL)
        try {
            JSONObject params = new JSONObject();
            params.put("ids", "[\"" + rid + "\"]");
            params.put("level", level);
            params.put("encodeType", "flac");
            String enc = CryptoUtil.wyEapiEncrypt("/api/song/enhance/player/url/v1", params.toJSONString());
            String resp = HttpUtil.postForm("http://interface.music.163.com/eapi/song/enhance/player/url/v1", 
                 new HashMap<String, String>() {{ put("params", enc); }}, 
                 new HashMap<String, String>() {{ put("Origin", "https://music.163.com"); }});
            
            JSONObject json = JSON.parseObject(resp);
            if (json.containsKey("data")) {
                JSONArray data = json.getJSONArray("data");
                if (data != null && !data.isEmpty()) {
                    JSONObject song = data.getJSONObject(0);
                    resultData.put("url", song.getString("url"));
                    resultData.put("br", song.getLongValue("br"));
                    resultData.put("size", song.getLongValue("size"));
                    resultData.put("type", song.getString("type"));
                }
            }
        } catch (Exception e) {
             log.error("Wy url error", e);
        }

        // 3. 获取歌词 (Lyric)
        try {
            JSONObject params = new JSONObject();
            params.put("id", rid);
            params.put("lv", "-1");
            params.put("kv", "-1");
            params.put("tv", "-1");
            String enc = CryptoUtil.wyEapiEncrypt("/api/song/lyric", params.toJSONString());
            String resp = HttpUtil.postForm("http://interface.music.163.com/eapi/song/lyric", 
                 new HashMap<String, String>() {{ put("params", enc); }}, 
                 new HashMap<String, String>() {{ put("Origin", "https://music.163.com"); }});
            
            JSONObject json = JSON.parseObject(resp);
            if (json.containsKey("lrc")) {
                resultData.put("lrc", json.getJSONObject("lrc").getString("lyric"));
            }
        } catch (Exception e) {
             log.error("Wy lrc error", e);
        }
        
        // 构造最终返回
        JSONObject finalRes = new JSONObject();
        finalRes.put("code", 200);
        finalRes.put("data", resultData);
        finalRes.put("_method", "网易云音乐:本地官方API聚合");
        
        return finalRes.toJSONString();
    }
    public String detailKg(String rid, String level) throws IOException { return getDetail("kg", rid, level); }
    public String detailTx(String rid, String level) throws IOException { return getDetail("tx", rid, level); }
    public String detailMg(String rid, String level) throws IOException { return getDetail("mg", rid, level); }

    private String getDetail(String platform, String rid, String level) throws IOException { 
        String url = buildDetailUrl(platform, rid, level);
        log.info("Requesting detail: platform={}, url={}", platform, url);
        return HttpUtil.get(url);
    }

    private String buildDetailUrl(String platform, String rid, String level) throws IOException {
        JSONObject endpoints = detailConfig.getJSONObject("endpoints");
        JSONObject config = endpoints.getJSONObject(platform);
        if (config == null) throw new IllegalArgumentException("Unsupported platform: " + platform);

        StringBuilder sb = new StringBuilder();
        sb.append(config.getString("protocol")).append("://")
          .append(config.getString("domain"))
          .append(config.getString("path")).append("?");

        JSONObject params = config.getJSONObject("params");
        for (String key : params.keySet()) {
            String val = params.getString(key)
                    .replace("{rid}", rid)
                    .replace("{level}", level)
                    .replace("{timestamp}", String.valueOf(System.currentTimeMillis()))
                    .replace("{nonce}", HttpUtil.generateRandomString(8));
            sb.append(key).append("=").append(URLEncoder.encode(val, "UTF-8")).append("&");
        }
        return sb.toString();
    }

    // --- 辅助工具方法 ---

    private String buildSuccessResponse(JSONArray data, String method) {
        JSONObject res = new JSONObject();
        res.put("code", 200);
        res.put("message", "搜索成功");
        res.put("data", data);
        res.put("_method", method);
        return res.toJSONString();
    }

    private void addQuality(JSONArray array, String size, String qualityName, String level) {
        JSONObject q = new JSONObject();
        q.put("size", size);
        q.put("quality", qualityName);
        q.put("level", level);
        array.add(q);
    }

    private String formatDuration(int seconds) {
        if (seconds <= 0) return "00:00";
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    private String formatFileSize(long bytes) {
        if (bytes <= 0) return "未知";
        if (bytes < 1024) return bytes + "B";
        if (bytes < 1048576) return String.format("%.2fKB", bytes / 1024.0);
        return String.format("%.2fMB", bytes / 1048576.0);
    }

    private String getKwCover(JSONObject song) {
        String shortPic = song.getString("web_albumpic_short");
        if (shortPic == null || shortPic.isEmpty()) return "";
        return "https://img4.kuwo.cn/star/albumcover/" + shortPic;
    }

    private JSONArray parseKwQualityInfo(String nMinfo) {
        JSONArray res = new JSONArray();
        if (nMinfo == null || nMinfo.isEmpty()) {
            addQuality(res, "未知大小", "普通音质MP3", "standard");
            return res;
        }

        List<JSONObject> qualities = new ArrayList<>();
        String[] parts = nMinfo.split(";");
        Pattern pattern = Pattern.compile("level:(\\w+),bitrate:(\\d+),format:(\\w+),size:([\\w.]+)");

        for (String part : parts) {
            Matcher matcher = pattern.matcher(part);
            if (matcher.find()) {
                String bitrate = matcher.group(2);
                String sizeStr = matcher.group(4);
                
                JSONObject q = new JSONObject();
                boolean matched = false;

                if ("128".equals(bitrate)) {
                    q.put("size", formatKwFileSize(sizeStr));
                    q.put("quality", "普通音质MP3");
                    q.put("level", "standard");
                    matched = true;
                } else if ("320".equals(bitrate)) {
                    q.put("size", formatKwFileSize(sizeStr));
                    q.put("quality", "高音质MP3");
                    q.put("level", "exhigh");
                    matched = true;
                } else if ("2000".equals(bitrate)) {
                    q.put("size", formatKwFileSize(sizeStr));
                    q.put("quality", "无损音质FLAC");
                    q.put("level", "lossless");
                    matched = true;
                } else if ("4000".equals(bitrate)) {
                    q.put("size", formatKwFileSize(sizeStr));
                    q.put("quality", "超清母带FLAC");
                    q.put("level", "jymaster");
                    matched = true;
                }

                if (matched) {
                    qualities.add(q);
                }
            }
        }

        // 反转列表，高质量在前？或者根据安卓逻辑 CollectionsKt.reverse(arrayList)
        // 安卓代码中进行了 reverse，我们也要做
        for (int i = qualities.size() - 1; i >= 0; i--) {
            res.add(qualities.get(i));
        }

        if (res.isEmpty()) {
            addQuality(res, "未知大小", "普通音质MP3", "standard");
        }
        return res;
    }

    private String formatKwFileSize(String sizeStr) {
        if (sizeStr == null || sizeStr.isEmpty() || "0".equals(sizeStr)) {
            return "未知大小";
        }
        // 如果包含字母，直接转大写返回
        if (Pattern.compile("[A-Za-z]").matcher(sizeStr).find()) {
            return sizeStr.toUpperCase();
        }
        try {
            double d = Double.parseDouble(sizeStr);
            if (d > 0) {
                String[] units = {"B", "KB", "MB", "GB"};
                int i = 0;
                while (d >= 1024 && i < 3) {
                    d /= 1024;
                    i++;
                }
                return String.format("%.2f %s", d, units[i]);
            }
        } catch (NumberFormatException e) {
            // ignore
        }
        return "未知大小";
    }

    private String getWyArtistName(JSONArray ar) {
        if (ar == null) return "未知";
        List<String> names = new ArrayList<>();
        for (int i = 0; i < ar.size(); i++) names.add(ar.getJSONObject(i).getString("name"));
        return String.join("、", names);
    }

    private String getQqArtistName(JSONArray singers) {
        if (singers == null) return "未知";
        List<String> names = new ArrayList<>();
        for (int i = 0; i < singers.size(); i++) names.add(singers.getJSONObject(i).getString("name"));
        return String.join("、", names);
    }

    private String getMgArtistName(JSONArray singers) {
        if (singers == null) return "未知";
        List<String> names = new ArrayList<>();
        for (int i = 0; i < singers.size(); i++) {
            JSONObject s = singers.getJSONObject(i);
            names.add(s.getString("singerName") != null ? s.getString("singerName") : s.getString("name"));
        }
        return String.join("、", names);
    }
}