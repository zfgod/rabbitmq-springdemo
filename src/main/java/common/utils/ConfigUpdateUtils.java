package common.utils;


import java.io.*;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Properties;

/**
 * author: zf
 * Date: 2016/10/12  16:02
 * Description:更新配置文件参数
 */
public class ConfigUpdateUtils {
    public static void main(String[] args) {
//      String fileName = "log4j.properties";
        String fileName = "config/config.properties";
        updateOrAddProperties(fileName,"aa2","mm2");
    }
    /**
     * 传递键值对的Map，更新properties文件
     * @param fileName
     * 文件名(放在resource源包目录下)，需要后缀
     */
    public static void updateOrAddProperties(String fileName,String key,String value) {
        //getResource方法使用了utf-8对路径信息进行了编码，当路径中存在中文和空格时，他会对这些字符进行转换，这样，
        //得到的往往不是我们想要的真实路径，在此，调用了URLDecoder的decode方法进行解码，以便得到原始的中文及空格路径。
        String filePath = ConfigUpdateUtils.class.getClassLoader().getResource(fileName).getPath();
        Properties props = new Properties();
        BufferedWriter bw = null;
        try {
            filePath = URLDecoder.decode(filePath, "utf-8");
            InputStream in =new BufferedInputStream(new FileInputStream(filePath));
            props.load(in);
            System.out.println(props);
            props.setProperty(key,value);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            props.store(bw, "updateOrAdd"+new Date());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
