package api;

import common.Imomoe;
import common.ImomoeBangumi;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Sakura {

    static public String BaseURL;
    private ArrayList<SakuraBangumi> lastSearch;

    public Sakura() {
        BaseURL = "http://www.yhdm.tv";
    }

    public Sakura(String baseURL) {
        BaseURL = baseURL;
    }

    public ArrayList<SakuraBangumi> searchResult() {
        return this.lastSearch;
    }

    public int search(String keyword, int page) throws Exception {
        Document doc = null;
        try {
            doc = Jsoup.connect(BaseURL + "/search/" + keyword).data("page", String.valueOf(page)).get();
        } catch (IOException e) {
            throw new Exception("error search");
        }
        Elements el = doc.getElementsByClass("lpic").first().getElementsByTag("li");
        String str = doc.getElementById("totalnum").text();
        int it = Integer.parseInt(str.substring(0, str.length() - 1));
        ArrayList<SakuraBangumi> ret = new ArrayList<>();
        for (Element ele : el) {
            String detailURL = ele.getElementsByTag("a").attr("href");
            String cover = ele.getElementsByTag("img").attr("src");
            String title = ele.getElementsByTag("img").attr("alt");
            String update = ele.getElementsByTag("span").first().text();
            String detail = ele.getElementsByTag("p").last().text();
            ret.add(new SakuraBangumi(title, cover, detail, detailURL, update));
        }
        this.lastSearch = ret;
        return it;
    }
}
