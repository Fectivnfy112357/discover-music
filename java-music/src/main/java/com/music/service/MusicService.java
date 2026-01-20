package com.music.service;

import com.music.util.HttpUtil;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class MusicService {

    private static final String BASE_URL = "https://music.haitangw.cc";

    /**
     * 小黄源 (kw) 搜索
     */
    public String searchKw(String keyword, int page) throws IOException {
        String url = BASE_URL + "/music/?kw&name=" + keyword + "&page=" + page;
        return HttpUtil.get(url);
    }

    /**
     * 小黄源 (kw) 详情
     */
    public String detailKw(String rid, String level) throws IOException {
        String url = BASE_URL + "/music/kw.php?id=" + rid + "&level=" + level;
        return HttpUtil.get(url);
    }

    /**
     * 小红源 (wy) 搜索
     */
    public String searchWy(String keyword, int page) throws IOException {
        String url = BASE_URL + "/music/?wy&name=" + keyword + "&page=" + page + "&limit=30";
        return HttpUtil.get(url);
    }

    /**
     * 小红源 (wy) 详情
     */
    public String detailWy(String rid, String level) throws IOException {
        String url = BASE_URL + "/wy/wy.php?id=" + rid + "&type=json&level=" + level;
        return HttpUtil.get(url);
    }

    /**
     * 小狗源 (kg) 搜索
     */
    public String searchKg(String keyword, int page) throws IOException {
        String url = BASE_URL + "/search/kgsearch.php/?kg&name=" + keyword + "&page=" + page + "&limit=30";
        return HttpUtil.get(url);
    }

    /**
     * 小狗源 (kg) 详情
     */
    public String detailKg(String rid, String level) throws IOException {
        String url = BASE_URL + "/kgqq/kg.php?id=" + rid + "&level=" + level;
        return HttpUtil.get(url);
    }

    /**
     * 小绿源 (tx) 搜索
     */
    public String searchTx(String keyword, int page) throws IOException {
        String url = BASE_URL + "/music/?qq&name=" + keyword + "&page=" + page + "&limit=20";
        return HttpUtil.get(url);
    }

    /**
     * 小绿源 (tx) 详情
     */
    public String detailTx(String rid, String level) throws IOException {
        String url = BASE_URL + "/kgqq/qq.php?id=" + rid + "&level=" + level;
        return HttpUtil.get(url);
    }

    /**
     * 小粉源 (mg) 搜索
     */
    public String searchMg(String keyword, int page) throws IOException {
        String url = BASE_URL + "/musicapi/?mg&name=" + keyword + "&page=" + page + "&limit=30";
        return HttpUtil.get(url);
    }

    /**
     * 小粉源 (mg) 详情
     */
    public String detailMg(String rid, String level) throws IOException {
        String url = BASE_URL + "/musicapi/mg.php?id=" + rid + "&type=json&level=" + level;
        return HttpUtil.get(url);
    }
}
