package common;

public class ImomoeBangumiSource {
    private String bangumi_url;
    private String bangumi_num;

    public ImomoeBangumiSource(String bangumi_num, String bangumi_url) {
        this.bangumi_num = bangumi_num;
        this.bangumi_url = bangumi_url;
    }

    public String getBangumiSource() {
        return bangumi_url;
    }

    public String getBangumiNum () {
        return bangumi_num;
    }

    public void setBangumiURL(String bangumi_url) {
        this.bangumi_url = bangumi_url;
    }

    public void setBangumiNum(String bangumi_num) {
        this.bangumi_num = bangumi_num;
    }
}
