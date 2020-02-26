package common;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImomoeBangumi extends Imomoe {
    private String title;
    private String image;
    private String detail;
    private String bangumiID;

    public static final String P_ID = "/view/(?<bid>.*?)\\.";

    ImomoeBangumi(String title, String image, String detail) {
        this.title = title;
        this.image = image;
        this.detail = detail;

        Pattern pattern = Pattern.compile(P_ID);
        Matcher matcher = pattern.matcher(detail);
        matcher.find();
        this.bangumiID = matcher.group(1);
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getBangumiID() {
        return this.bangumiID;
    }


    public ArrayList<ImomoeBangumiSource> getVideoList() throws Exception {
        return super.getVideoList(this.bangumiID);
    }

}
