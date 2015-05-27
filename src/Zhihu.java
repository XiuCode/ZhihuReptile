import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Zhihu {
    public String question;// 问题
    public String questionDescription;// 问题描述
    public String zhihuUrl;// 网页链接
    public String answers;//回答
    public static int number;


    // 构造方法初始化数据
    public Zhihu(String url) {
        // 初始化属性
        question = "";
        questionDescription = "";
        zhihuUrl = "http://www.zhihu.com" + url;
        //answers = new ArrayList<String>();
        answers = "";
        number = 1;

        System.out.println("正在抓取" + zhihuUrl);
        // 根据url获取该问答的细节
        String content = Spider.SendGet(zhihuUrl);
        Pattern pattern;
        Matcher matcher;
        // 匹配标题
        pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            question = matcher.group(1);
        }
        // 匹配描述
        pattern = Pattern
                .compile("zh-question-detail.+?<div.+?>(.*?)</div>");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            questionDescription = matcher.group(1);
        }
        // 匹配答案
        pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
        matcher = pattern.matcher(content);
        if (matcher.find()) {
            answers = matcher.group(1);
        }
    }

    @Override
    //结果直接打印
    public String toString() {
        return "问题：" + question + "\n" + "描述：" + questionDescription + "\n"
                + "链接：" + zhihuUrl + "\n公子说：" + answers + "\n";
    }

    //结果输出到文件
    public String writeString() {
        String result = "";
        result += "--------------------------------------------------" + "\r\n";
        result += "问题" + number+ ": " + question + "\r\n";
        result += "描述：" + questionDescription + "\r\n";
        result += "链接：" + zhihuUrl + "\r\n";
        result += "公子说：" + answers + "\r\n";
        result += "\r\n\r\n";

        result = result.replaceAll("<br>", "\r\n");
        result = result.replaceAll("<.*?>", "");
        number++;
        return result;
    }
}
