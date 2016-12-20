package sys.dao;

import com.github.abel533.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import sys.model.ResourcesRoleKey;
import sys.model.Role;
@MapperScan
public interface ResourcesRoleMapper extends Mapper<ResourcesRoleKey> {

    int insertBindRoleRes(Role role);
}