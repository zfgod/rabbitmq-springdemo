package common.utils;

import java.util.ResourceBundle;
import java.util.Set;

/**
 * author: zf
 * Date: 2016/10/12  14:09
 * Description: java代码读取配置文件的值
 *              只能读取.properties文件
 */
public class ResourceBundleUtils {

    public static void main(String[] args) {
//      resource目录下： config/config.properties
//      target: config/config.properties
     /*   ResourceBundle allMessage = getAllMessage("config.config");
        if(allMessage!=null){
            Enumeration<String> keys = allMessage.getKeys();
            while (keys.hasMoreElements()){
                String s = keys.nextElement();
                System.out.println(s+"="+allMessage.getString(s));
            }
        }else {
            System.out.println("null");
        }*/
//      target: config/database.properties
       /* String driverClassName = getValue("config.database", "driverClassName");
        System.out.println(driverClassName);*/

        String[] support_types = getArrayVaules("config.fileupload", "support_types", "\\|\\|");
        for (String support_type : support_types) {
            System.out.println(support_type);
        }

    }
    /**
     * 获取指定配置文件中所以的数据
     * @param propertyName
     *调用方式：
     * 1.配置文件放在resource源包下 不用加后缀
     *              PropertiesUtil.getAllMessage("message");
     * 2.放在包里面的
     *              PropertiesUtil.getAllMessage("com.test.message");
     * @return key - value 键值对
     *
     *
     */
    public static ResourceBundle getAllMessage(String propertyName) {
        // 获得资源包
        ResourceBundle rb = ResourceBundle.getBundle(propertyName.trim());
        Set<String> strings = rb.keySet();
        while (strings.size()>0){
            return rb ;
        }
        return null;
    }

    public static String getValue(String target,String key){
        ResourceBundle rb = ResourceBundle.getBundle(target.trim());
        return rb.getString(key);
    }
/**
 * regex 分隔符，"|",或者其他一些特殊分隔 需要加"\\";  "||" 不要用,如果是，分隔符为 "\\|\\|"
 * 最好是用  逗号、分号或者下划线  来分隔，注意中英文
 * */
    public static String[] getArrayVaules(String target,String key,String regex){
        ResourceBundle rb = ResourceBundle.getBundle(target.trim());
        String string = rb.getString(key);
        return string.split(regex);
    }
}
