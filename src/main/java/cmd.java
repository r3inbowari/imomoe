import api.Sakura;
import api.SakuraBangumi;
import common.Imomoe;
import common.ImomoeBangumiSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class cmd {
    public static void main(String[] args) throws Exception {
//        System.out.println("你");
//        Imomoe a = new Imomoe("http://www.yhdm.tv/");
//        a.searchVideo("你", 2);
//        ArrayList<ImomoeBangumiSource> b = a.getBangumiDetails().get(0).getVideoList();
//        System.out.println(a.getBangumiDetails().get(0).getTitle());
//
        Sakura a = new Sakura("http://www.yhdm.tv");
        int k = a.search("柯南", 1);
        SakuraBangumi b = a.searchResult().get(0).loadDetail();
        System.out.println(b.getAlias());
        String playSrc = b.getPlaySource(1027);
        System.out.println(playSrc);

    }
}
