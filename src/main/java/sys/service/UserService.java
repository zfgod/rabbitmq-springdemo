package sys.service;

import com.github.abel533.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.dao.UserMapper;
import sys.dao.UserRoleMapper;
import sys.model.User;
import sys.model.UserRoleKey;

import java.util.List;

/**
 * author: zf
 * Date: 2016/10/13  17:17
 * Description:
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public User commitLogin(User user) {
        User singleUser = userMapper.querySingleUser(user.getUserName());//登录时获取用户
        if(singleUser!=null && user.getUserPassword().equals(singleUser.getUserPassword())){
            return singleUser;
        }
        return null;
    }

    public List<User> queryUsers() {
        return userMapper.queryUserList();
    }

    /**
     * 用户当前绑定的角色id
     * @param user
     * @return
     */
    public List<Integer> getCurrentRoles(User user) {
        return userMapper.getCurrentRoles(user);
    }

    public boolean bindRoles(User user) {
        Example example =new Example(UserRoleKey.class);
        example.createCriteria().andEqualTo("user_id",user.getUserId());
        userRoleMapper.deleteByExample(example);
        List<Integer> roleList = user.getRoleList();
        if(roleList!=null && roleList.size()>0){
           return roleList.size() == userRoleMapper.insertBatch(user);
        }
        return false;
    }
}
