package com.vtube.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.vtube.dto.ProductV1Detail;
import com.vtube.dto.SearchV1Result;
import com.vtube.dto.VideoV1Play;
import com.vtube.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RestController
public class SearchController {

    @GetMapping("/search")
    ApiResult search(@RequestParam String q) {
        List<SearchV1Result> results = new ArrayList<>();
        String url = "https://nnyy.in/so?q=" + q;

        try {
            Document doc = Jsoup.connect(url).get();
            Elements items = doc.select("div.lists-content ul li");

            for (Element item : items) {
                SearchV1Result searchResult = new SearchV1Result();

                // 获取标题和链接
                Element titleElement = item.select("h2 a").first();
                if (titleElement != null) {
                    searchResult.setTitle(titleElement.text());
                    // 使用正则表达式提取 ID
                    String fullLink = "https://nnyy.in" + titleElement.attr("href");

                    String id = extractIdFromLink(fullLink);

                    searchResult.setId(id);
                }

                // 获取图片URL
                Element imgElement = item.select("a.thumbnail img").first();
                if (imgElement != null) {
                    searchResult.setImageUrl("https://nnyy.in" + imgElement.attr("src"));
                }

                // 获取note和国家年份信息
                Element noteElement = item.select("div.note span").first();
                if (noteElement != null) {
                    searchResult.setNote(noteElement.text());
                }

                Elements countryYearElements = item.select("div.countrie span.orange");
                if (countryYearElements.size() >= 2) {
                    searchResult.setYear(countryYearElements.get(0).text());
                    searchResult.setCountry(countryYearElements.get(1).text());
                }

                // 获取评分
                Element ratingElement = item.select("footer .rate").first();
                if (ratingElement != null) {
                    searchResult.setRating(ratingElement.text());
                }

                results.add(searchResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ApiResult.ok(results);
    }

    @GetMapping("/detail")
    ApiResult getDetail(@RequestParam String id) throws IOException {
        ProductV1Detail productDetail = new ProductV1Detail();
        String url = "https://nnyy.in/dongman/" + id + ".html";

        try {
            Document doc = Jsoup.connect(url).get();
            Element header = doc.select("header.product-header").first();

            if (header != null) {
                // 获取标题和年份
                Element titleElement = header.select("h1.product-title").first();
                if (titleElement != null) {
                    String fullTitle = titleElement.html();
                    // 使用正则表达式提取年份
                    String title = fullTitle.replaceAll("<span.*?</span>", "").trim();
                    String year = extractYear(fullTitle);

                    productDetail.setTitle(title);
                    productDetail.setYear(year);
                }

                // 获取评分
                Element ratingElement = header.select("span.rate").first();
                if (ratingElement != null) {
                    productDetail.setRating(ratingElement.text());
                }

                // 获取图片URL
                Element imgElement = header.select("img.thumb.detail-img").first();
                if (imgElement != null) {
                    productDetail.setImageUrl("https://nnyy.in" + imgElement.attr("src"));
                }

                // 获取导演列表
                productDetail.setDirectors(getListFromElements(header, "div.product-excerpt", 0));

                // 获取主演列表
                productDetail.setStars(getListFromElements(header, "div.product-excerpt", 1));

                // 获取类型列表
                productDetail.setGenres(getListFromElements(header, "div.product-excerpt", 2));

                // 获取制片国家/地区
                Element countryElement = header.select("div.product-excerpt").get(3).select("span a").first();
                if (countryElement != null) {
                    productDetail.setCountry(countryElement.text());
                }

                // 获取别名
                Element aliasElement = header.select("div.product-excerpt").get(4).select("span").first();
                if (aliasElement != null) {
                    productDetail.setAlias(aliasElement.text());
                }

                // 获取剧情简介
                Element synopsisElement = header.select("div.product-excerpt").get(5).select("span").first();
                if (synopsisElement != null) {
                    productDetail.setSynopsis(synopsisElement.text());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ApiResult.ok(productDetail);
    }

    @GetMapping("/getVideoPlays")
    ApiResult getVideoPlays(@RequestParam String id, @RequestParam(defaultValue = "1") Integer ep) {
        try {
            String apiUrl = "https://nnyy.in/_gp/" + id + "/ep" + ep;
            String jsonResponse = fetchJsonData(apiUrl);

            // 解析JSON并提取video_plays信息
            List<VideoV1Play.VideoPlay> videoPlays = extractVideoPlays(jsonResponse);

            // 获取剧集总数并构建VideoV1Play对象
            int episodesCount = fetchEpisodesCount(id);

            VideoV1Play videoV1Play = new VideoV1Play();
            videoV1Play.setVideoPlays(videoPlays);
            videoV1Play.setEpisodesCount(episodesCount);

            return ApiResult.ok(videoV1Play);

        } catch (Exception e) {
            e.printStackTrace();
            // 返回一个空的 videoPlays 列表和0的episodesCount
            VideoV1Play videoV1Play = new VideoV1Play();
            videoV1Play.setVideoPlays(Collections.emptyList());
            videoV1Play.setEpisodesCount(0);
            return ApiResult.ok(videoV1Play);
        }
    }

    private String fetchJsonData(String apiUrl) {
        // 使用Hutool的HttpUtil进行GET请求
        return HttpUtil.get(apiUrl);
    }

    private List<VideoV1Play.VideoPlay> extractVideoPlays(String jsonResponse) {
        return JSONUtil.parseObj(jsonResponse).getBeanList("video_plays", VideoV1Play.VideoPlay.class);
    }

    private int fetchEpisodesCount(String id) {
        String url = "https://nnyy.in/dongman/" + id + ".html";
        String html = HttpUtil.get(url);

        // 使用正则表达式匹配li元素
        Pattern pattern = Pattern.compile("<li class=\"play-btn.*?ep_slug=\"ep\\d+\".*?</li>");
        Matcher matcher = pattern.matcher(html);

        int episodesCount = 0;
        while (matcher.find()) {
            episodesCount++;
        }

        return episodesCount;
    }


    private List<String> getListFromElements(Element header, String selector, int index) {
        List<String> list = new ArrayList<>();
        Elements elements = header.select(selector).get(index).select("span a");
        for (Element element : elements) {
            list.add(element.text());
        }
        return list;
    }

    private String extractYear(String fullTitle) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\((\\d{4})\\)");
        java.util.regex.Matcher matcher = pattern.matcher(fullTitle);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "未知";
    }

    private String extractIdFromLink(String url) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("/dongman/(\\d+)\\.html");
        java.util.regex.Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}