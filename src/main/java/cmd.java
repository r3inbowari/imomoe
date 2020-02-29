import api.Sakura;
import api.SakuraBangumi;
import api.SakuraType;

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

        // 关键字搜索
        int k = a.search("你的名字", 1);

        // 加载更多信息
        SakuraBangumi b = a.searchResult().get(0).loadMoreDetail();
        System.out.println(b.getAlias());

        // 加载播放地址
        String playSrc = b.getPlaySource(0);
        System.out.println(playSrc);

        // 加载每日番剧
        ArrayList<SakuraBangumi> da = a.getDailyUpdate(0); // 周一
        da.get(0).loadMoreDetail();
        System.out.println(da.get(0).getPlaySource(0));

        // 加载类型主题
        a.getByTheme(SakuraType.BANGUMI_TYPE_AREA_JAPAN, 1);
    }
}
