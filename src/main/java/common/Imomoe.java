package common;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Imomoe {
    protected String baseURL;

    private ArrayList<ImomoeBangumi> lastSearch;

    /**
     * 最后一次搜索结果
     * @return
     */
    public ArrayList<ImomoeBangumi> getBangumiDetails() {
        return this.lastSearch;
    }

    public Imomoe(String baseURL) {
        this.baseURL = baseURL;
    }

    public Imomoe() {
        this.baseURL = "http://www.imomoe.in/";
    }

    public String getBaseURL() {
        return this.baseURL;
    }

    /**
     * JS获取
     * @param videoID 剧集索引
     * @return
     * @throws Exception
     */
    private String getVideosJS(String videoID) throws Exception {
        return Jsoup.connect(baseURL + "player/" + videoID + "-0-0.html").get().getElementsByClass("player").first().getElementsByTag("script").first().attr("src");
    }

    /**
     * 解析
     * @param videoBangumiJS 得到的JS
     * @return
     * @throws Exception
     */
    private ArrayList<ImomoeBangumiSource> getVideoSet(String videoBangumiJS) throws Exception {
        Node jsNode = Jsoup.connect(baseURL + videoBangumiJS).ignoreContentType(true).get().body().childNode(0);
        String js = FontUtil.decodeUnicode(jsNode.toString());
        return ImomoeParser.parseVideosObject(js);
    }

    /**
     * 获取番剧剧集
     * @param videoID 剧集索引
     * @return
     * @throws Exception
     */
    public ArrayList<ImomoeBangumiSource> getVideoList(String videoID) throws Exception {
        return getVideoSet(getVideosJS(videoID));
    }

    /**
     * 搜索番剧
     * @param keyword 关键字
     * @return
     * @throws Exception
     */
    public ArrayList<ImomoeBangumi> searchVideo(String keyword) throws Exception {
        Elements a = Jsoup.connect(baseURL + "search.asp").data("searchword", keyword).data("page", "1").postDataCharset("gb2312").post().getElementsByClass("fire").first().getElementsByClass("pics").first().getElementsByTag("li");
        ArrayList<ImomoeBangumi> ret = new ArrayList<>();
        for (Element ele : a) {
            String title = ele.getElementsByTag("a").attr("title");
            String src = ele.getElementsByTag("img").attr("src");
            String href = ele.getElementsByTag("a").attr("href");
            ImomoeBangumi ib = new ImomoeBangumi(title, src, href);
            ret.add(ib);
        }
        this.lastSearch = ret;
        return ret;
    }

    /**
     * 搜索番剧
     * @param keyword 关键字
     * @return
     * @throws Exception
     */
    public ArrayList<ImomoeBangumi> searchVideo(String keyword, int page) throws Exception {
        Elements a = Jsoup.connect(baseURL + "search.asp").data("searchword", keyword).data("page", String.valueOf(page)).postDataCharset("gb2312").post().getElementsByClass("fire").first().getElementsByClass("pics").first().getElementsByTag("li");
        ArrayList<ImomoeBangumi> ret = new ArrayList<>();
        for (Element ele : a) {
            String title = ele.getElementsByTag("a").attr("title");
            String src = ele.getElementsByTag("img").attr("src");
            String href = ele.getElementsByTag("a").attr("href");
            ImomoeBangumi ib = new ImomoeBangumi(title, src, href);
            ret.add(ib);
        }
        this.lastSearch = ret;
        return ret;
    }
}
