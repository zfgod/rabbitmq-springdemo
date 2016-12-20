package sys.dao;

import com.github.abel533.mapper.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import sys.model.ResourcesRoleKey;
import sys.model.Role;

import java.util.List;
@MapperScan
public interface RoleMapper extends Mapper<Role> {

    List<ResourcesRoleKey> findRoleResList(Role role);

    List<Role> queryList();

    List<Integer> getUsersUseRole(List<Integer> roles);
}