package sys.dao;

import com.github.abel533.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import sys.model.User;
import sys.model.UserRoleKey;
@MapperScan
public interface UserRoleMapper extends Mapper<UserRoleKey> {

    int insertBatch(User user);
}