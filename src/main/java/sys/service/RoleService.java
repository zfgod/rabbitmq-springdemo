package sys.service;

import com.alibaba.fastjson.JSONObject;
import com.github.abel533.entity.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.dao.ResourcesMapper;
import sys.dao.ResourcesRoleMapper;
import sys.dao.RoleMapper;
import sys.model.Resources;
import sys.model.ResourcesRoleKey;
import sys.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * author: zf
 * Date: 2016/10/19  10:26
 * Description:
 */
@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private ResourcesMapper resourcesMapper;

    @Autowired
    private ResourcesRoleMapper rrMapper;
    /**
     * 重新授权角色时，获取所有资源权限和已经绑定的资源权限
     * @param result
     * @param role
     */
    public void findRoleResList(JSONObject result,Role role) {
        List<Resources> select = resourcesMapper.getSelect();//角色重新授权获取
        List<ResourcesRoleKey> roleResList =roleMapper.findRoleResList(role);
        //便于js勾选用户已绑定权限,只传递一个权限id的数组
        List<Integer> roleRes = new ArrayList<Integer>();
        for (ResourcesRoleKey roleRe : roleResList) {
            roleRes.add(roleRe.getResc_id());
        }
        result.put("roleRes",roleRes);
        result.put("resList",select);
    }

    /**
     * 角色列表数据获取
     * @return
     */
    public List<Role> queryList() {
        return roleMapper.queryList();
    }

    /**
     * 更新角色授权信息
     * @param role
     */
    public boolean updateBindRoleRes(Role role) {
        Example example = new Example(ResourcesRoleKey.class);
        example.createCriteria().andEqualTo("role_id",role.getId());
        rrMapper.deleteByExample(example);
        List<Integer> resList = role.getResList();
        if(resList!=null && resList.size()>0){
            return rrMapper.insertBindRoleRes(role) == resList.size();
        }else if(resList!=null && resList.size()==0){
            return true;
        }
        return false;
    }

    /**
     * 获取指定角色信息
     * @param role
     * @return
     */
    public Role getRole(Role role) {
        if(role.getId()!=null){
           return roleMapper.selectByPrimaryKey(role);
        }
        return null;
    }

    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKeySelective(role);
    }

    public int addRole(Role role) {
        return roleMapper.insertSelective(role);
    }

    public List<Role> findAllRoles() {
        Role r = new Role();
        r.setEnable(1);
        return roleMapper.select(r);
    }

    public List<Integer> getUsersUseRole(Set<Integer> updateRoles) {
        List<Integer> roles = new ArrayList<Integer>(updateRoles);
        return roleMapper.getUsersUseRole(roles);
    }
}
