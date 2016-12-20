package sys.dao;

import com.github.abel533.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import sys.model.User;

import java.util.List;
@MapperScan
public interface UserMapper extends Mapper<User> {

    User querySingleUser(String username);

    List<User> queryUserList();

    List<Integer> getCurrentRoles(User user);
}