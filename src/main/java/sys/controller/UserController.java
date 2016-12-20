package sys.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.model.Role;
import sys.model.User;
import sys.service.RoleService;
import sys.service.UserService;

import java.util.List;

/**
 * author: zf
 * Date: 2016/10/24  16:36
 * Description:
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService ;

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public Object queryUsers(){
        JSONObject result = new JSONObject();
        List<User> userList = userService.queryUsers();
        if(userList!=null &&userList.size()>0){
            result.put("code",200);
            result.put("list",userList);
        }else {
            result.put("code",404);
        }
        return result;
    }
    @RequestMapping(value = "/letGo/rSelect",method = RequestMethod.POST)
    @ResponseBody
    public Object getRolesForBind(@RequestBody User user){
        JSONObject result = new JSONObject();
        List<Integer> cList = userService.getCurrentRoles(user);
        List<Role> rSelect = roleService.findAllRoles();
        result.put("code",200);
        result.put("currentRoles",cList);
        result.put("rSelect",rSelect);
        return result;
    }

    @RequestMapping(value = "/bindRole",method = RequestMethod.POST)
    @ResponseBody
    public Object bindRoles(@RequestBody User user){
        JSONObject result = new JSONObject();
        boolean flag = userService.bindRoles(user);
        if(flag){
            result.put("code",200);
            result.put("msg","绑定成功");
        }
        return result;
    }

}
