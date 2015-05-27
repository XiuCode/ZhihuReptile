import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // 定义即将访问的链接
        //http://www.zhihu.com/people/zhang-jia-wei/answers?page=115
        for (int i = 1; i <= 115; i++) {
            String url = "http://www.zhihu.com/people/zhang-jia-wei/answers?page="+i;
            // 访问链接并获取页面内容
            String content = Spider.SendGet(url);
            // 获取张佳玮的问题
            ArrayList<Zhihu> myZhihu = Spider.GetContent(content);
            // 打印结果
            // System.out.println(myZhihu);

            //输出到文件
            for (Zhihu zhihu : myZhihu) {
                FileReaderWriter.writeIntoFile(zhihu.writeString(),
                        "OutputFile/张佳玮.txt", true);
            }
        }
    }
}
