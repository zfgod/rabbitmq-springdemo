package common.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * author: zf
 * Date: 2016/10/12  15:02
 * Description: Spring 提供的 PropertiesLoaderUtils
 */
public class ParamLoaderUtils {
    /**
     * 允许您直接通过基于类路径的文件地址加载属性资源
     * 备注: 基于类路径，classpath下面（classes目录下）,其他目录下面的读取不到
     * 最大的好处就是：实时加载配置文件，修改后立即生效，不必重启
     */
    public static void main(String[] args) {
        String fileName = "log4j.properties";
/*        Properties properties = parseFileParams(fileName);
        if(properties!=null){
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                Object o = keys.nextElement();
                String property = properties.getProperty((String) o);
                System.out.println(o+"="+property);
            }
        }*/

        String param = getParam(fileName, "log4j.rootLogger");
        System.out.println(param);
        String[] arrayParams = getArrayParams(fileName, "log4j.rootLogger", "\\,");
        if(arrayParams!=null){
            for (String arrayParam : arrayParams) {
                System.out.println(arrayParam);
            }
        }else {
            System.out.println("null");
        }
    }
    private static Properties parseFileParams(String fileName ){
        Properties props = new Properties();
        while(true){
            try {
//              文件名称,自动查找,不需要指明路径
                props = PropertiesLoaderUtils.loadAllProperties(fileName);
                if(!props.keySet().isEmpty()){
                    return props;
                }else {
                    return null;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getParam(String target,String key){
        Properties properties = parseFileParams(target);
        if(properties!=null){
            return properties.getProperty(key);
        }
        return null;
    }

    public static String[] getArrayParams(String target,String key,String regex){
        Properties properties = parseFileParams(target);
        if(properties!=null && properties.containsKey(key)){
             return properties.getProperty(key).split(regex);
        }
        return null;
    }
}
