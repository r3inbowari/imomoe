import common.Imomoe;
import common.ImomoeBangumiSource;

import java.util.ArrayList;

public class cmd {
    public static void main(String[] args) throws Exception {
        System.out.println("你");
        Imomoe a = new Imomoe();
        a.searchVideo("你", 2);
        ArrayList<ImomoeBangumiSource> b = a.getBangumiDetails().get(0).getVideoList();
        System.out.println(a.getBangumiDetails().get(0).getTitle());

    }
}
