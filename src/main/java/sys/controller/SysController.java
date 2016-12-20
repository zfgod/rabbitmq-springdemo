package sys.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.model.User;
import sys.service.UserService;
import sys.utils.ParamsUtils;


/**
 * author: zf
 * Date: 2016/10/13  16:04
 * Description:
 */
@Controller
@RequestMapping("/sys")
public class SysController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager myAuthenticationManager;


                /**
     * @Description: 访问登录页面
     * @author: zf
     * @Date:   2016/10/13
     */
    @RequestMapping(value = "/toLogin")
    public String toLogin(Model model){
        model.addAttribute("testKey","testValue");
        return "/sys/login";
    }

    /**
     * @Description: 用户登录
     * @author: zf
     * @Date:   2016/10/13
     */
    @RequestMapping(value = "/commitLogin",method = RequestMethod.POST)
    @ResponseBody
    public Object commitLogin(@RequestBody User user){
       JSONObject result = new JSONObject();
       User hasUser =  userService.commitLogin(user);
       if(hasUser!=null){
           //进入用户认证
           //session存储
           //进入主页
           Authentication authentication = myAuthenticationManager
                   .authenticate(
                           new UsernamePasswordAuthenticationToken(hasUser.getUserName(),hasUser.getUserPassword()));
           SecurityContext securityContext = SecurityContextHolder.getContext();
           securityContext.setAuthentication(authentication);
           session.setAttribute(ParamsUtils.user_security_sedssin, securityContext);
                   // 当验证都通过后，把用户信息放在session里
           request.getSession().setAttribute(ParamsUtils.user_sessin, hasUser);
           addUserInServer(hasUser);
           result.put("code", 200);
           result.put("msg","登录成功！");
           return  result;
       }else {
           result.put("loginError", "用户名或密码输入错误！");
           result.put("code", 500);
       }
       return  result;
    }



    /**
     * @Description: 访问主页
     * @author: zf
     * @Date:   2016/10/14
     */
    @RequestMapping(value = "/main")
    public String goMain(Model model){
       model.addAttribute("loginUser",session.getAttribute(ParamsUtils.user_sessin));
       return "sys/main";
    }


}
