package common;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImomoeParser {
    public static final String P_VIDEO = "(',\\[)?'(?<num>.{0,8}?)\\$(?<url>.*?)\\$(?<type>.*?)'";

    public static ArrayList<ImomoeBangumiSource> parseVideosObject(final String jsCode) {
        // 去除源2
        int lastIndex = jsCode.lastIndexOf("],['");
        String js = "";
        if (lastIndex > 1) {
            js = jsCode.substring(0, lastIndex + 1);
        } else {
            js = jsCode;
        }
        // 匹配开始
        Pattern pattern = Pattern.compile(P_VIDEO);
        Matcher matcher = pattern.matcher(js);
        // Map<String, String> videoMap = new HashMap<>();
        // 改用array
        ArrayList<ImomoeBangumiSource> imomoeBangumiSources = new ArrayList<>();
//        while (matcher.find()) {
//            if (!videoMap.containsKey(matcher.group("num"))) {
//                videoMap.put(matcher.group("num"), matcher.group("url"));
//            }
//        }
        while (matcher.find()) {
            imomoeBangumiSources.add(new ImomoeBangumiSource(matcher.group("num"), matcher.group("url")));
        }
        return imomoeBangumiSources;
    }
}
