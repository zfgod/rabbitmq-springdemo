package sys.dao;

import com.github.abel533.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import sys.model.Resources;

import java.util.List;

/**
 * 这里程序启动时，读取spring-security.xml时，就会加载系统权限资源，调用mapper接口
 * 此时若并没有读取到spring配置文件对应mapper接口的读取，将会无法注入
 * 所以注解提前注入
 * */
@MapperScan
public interface ResourcesMapper extends Mapper<Resources> {
     List<Resources> findAll();

     List<Resources> getUserResources(String s);

     List<Resources> getSelect();
}