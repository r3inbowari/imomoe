package api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class SakuraBangumi {
    // SakuraBangumi <--- search page
    private String title; // 标题
    private String update; // 更新时间
    private String detail; // 介绍 <--- update detail pull
    private String cover; // 封面地址
    private String detailURL; // 详情地址
    private String bangumiID; // 索引

    SakuraBangumi(String title, String cover, String detail, String detailURL, String update) {
        this.title = title;
        this.update = update;
        this.detail = detail;
        this.cover = cover;
        this.detailURL = detailURL;

        this.bangumiID = detailURL.substring(detailURL.lastIndexOf("/") + 1, detailURL.lastIndexOf("."));
    }

    SakuraBangumi(String title, String detailURL) {
        this.title = title;
        this.detailURL = detailURL;

        this.bangumiID = detailURL.substring(detailURL.lastIndexOf("/") + 1, detailURL.lastIndexOf("."));
    }

    // SakuraBangumi <--- detail page
    private String rating; // 评分
    private String pullTime; // 上映
    private String tag; // 标签
    private String area; // 区域
    private String alias; // 别名
    private int count; // 集数总和

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDetailURL() {
        return detailURL;
    }

    public void setDetailURL(String detailURL) {
        this.detailURL = detailURL;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPullTime() {
        return pullTime;
    }

    public void setPullTime(String pullTime) {
        this.pullTime = pullTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public SakuraBangumi loadMoreDetail() throws Exception {
        Document doc = null;
        try {
            doc = Jsoup.connect(Sakura.BaseURL + detailURL).get();
        } catch (IOException e) {
            throw new Exception("error loadDetail");
        }
        Elements el = doc.getElementsByClass("movurl").first().getElementsByTag("a");
        this.count = el.size();
        Element rater = doc.getElementsByClass("rate r").first();
        this.rating = rater.getElementsByTag("em").first().text();
        this.update = rater.getElementsByClass("sinfo").first().getElementsByTag("p").last().text();
        if (rater.getElementsByClass("sinfo").first().getElementsByTag("p").size() > 1) {
            this.alias = rater.getElementsByClass("sinfo").first().getElementsByTag("p").first().text();
            this.alias = alias.substring(3, alias.length());
        } else {
            this.alias = "无";
        }

        if (cover == null) {
            cover = doc.getElementsByClass("thumb l").first().getElementsByTag("img").attr("src");
        }

        if (detail == null) {
            detail = doc.getElementsByClass("info").text();
        }
        return this;
    }

    public String getPlaySource(int index) throws Exception {
        if (index > count || count == 0) {
            throw new Exception("error playIndex or not loadDetail");
        }
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.yhdm.tv" + "/v/" + bangumiID + "-" + String.valueOf(index + 1) + ".html").get();
        } catch (IOException e) {
            throw new Exception("error playIndex connect");
        }
        String onclick = doc.getElementById("play_1").attr("onclick");
        return onclick.substring(onclick.indexOf("'") + 1, onclick.lastIndexOf("$"));
    }
}
