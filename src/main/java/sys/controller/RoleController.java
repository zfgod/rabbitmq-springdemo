package sys.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.model.Role;
import sys.service.RoleService;

import java.util.List;

/**
 * author: zf
 * Date: 2016/10/19  10:25
 * Description:
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/query.do")
    @ResponseBody
    private Object queryRoles(){
        JSONObject result = new JSONObject();
        try{
            List<Role> roleList = roleService.queryList();
            result.put("code",200);
            result.put("roleList",roleList);
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","获取失败！");
        }
        return result;
    }

    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    private Object addRoles(@RequestBody Role role){
        JSONObject result = new JSONObject();
        try{
            int i  = roleService.addRole(role);
            if(i == 1){
                result.put("code",200);
                result.put("msg","添加角色成功！");
            }else {
                result.put("code",500);
                result.put("msg","添加角色失败！");
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","添加角色失败！");
        }
        return result;
    }


    /**
     * @Description: 查看角色-权限绑定信息
     * @author: zf
     * @Date:   2016/10/19
//     */
    @RequestMapping(value = "/letGo/bindRoleRes.do",method = RequestMethod.POST)
    @ResponseBody
    private Object getBindRoleRes(@RequestBody Role role){
        JSONObject result = new JSONObject();
        try{
            roleService.findRoleResList(result,role);
            result.put("code",200);
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","获取失败！");
        }
        return result;
    }

    /**
     * 更新角色绑定的权限
     * @param role
     * @return
     */
    @RequestMapping(value = "/bindRoleRes.do",method = RequestMethod.POST)
    @ResponseBody
    private Object updateBindRoleRes(@RequestBody Role role){
        JSONObject result = new JSONObject();
        try{
            boolean b = roleService.updateBindRoleRes(role);
            if(b){
                addRemindInServlet(role);
                result.put("code",200);
                result.put("msg","授权成功！");
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","授权失败！");
        }
        return result;
    }

    /**
     * 获取指定角色信息
     * @param role  id必须有
     * @return
     */
    @RequestMapping(value = "/find.do",method = RequestMethod.GET)
    @ResponseBody
    private Object getRole(Role role){
        JSONObject result = new JSONObject();
        try{
             Role r = roleService.getRole(role);
            if(r!=null){
                result.put("code",200);
                result.put("role",r);
            }else {
                result.put("code",404);
                result.put("msg","没有找到");
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","获取失败！");
        }
        return result;
    }

    /***
     * 更新角色信息
     * @param role
     * @return
     */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    private Object editRole(@RequestBody Role role){
        JSONObject result = new JSONObject();
        try{
            int i  = roleService.updateRole(role);
            if(i==1){
                result.put("code",200);
                result.put("msg","更新成功");
            }else {
                result.put("code",500);
                result.put("msg","更新失败");
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","获取失败！");
        }
        return result;
    }

}
