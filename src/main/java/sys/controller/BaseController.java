package sys.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import sys.model.Role;
import sys.model.User;
import sys.utils.ParamsUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

/**
 * author: zf
 * Date: 2016/10/13  16:05
 * Description:
 */
public class BaseController {
    protected HttpSession session;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ServletContext servletContext;

    @ModelAttribute()
    public  void setHttpObjects(HttpSession session,
                                HttpServletRequest request,
                                HttpServletResponse response)
    {
        this.request = request;
        this.response = response;
        this.session = session;
        this.servletContext =  session.getServletContext();
    }



    public void addUserInServer(User hasUser) {
        if(servletContext.getAttribute("loginUsers")==null){
            servletContext.setAttribute("loginUsers",new HashSet<Integer>());
        }
        Set<Integer> loginUsers = (Set<Integer>) servletContext.getAttribute("loginUsers");
        loginUsers.add(hasUser.getUserId());
    }

    public void removeUserInServer(User hasUser) {
        if(servletContext.getAttribute("loginUsers")!=null){
            Set<Integer> loginUsers = (Set<Integer>) servletContext.getAttribute("loginUsers");
            loginUsers.remove(hasUser.getUserId());
        }
    }

    public Boolean hasUserInServer(User hasUser) {
        if(servletContext.getAttribute("loginUsers")!=null){
            Set<Integer> loginUsers = (Set<Integer>) servletContext.getAttribute("loginUsers");
            return loginUsers.contains(hasUser.getUserId());
        }
        return false;
    }
    public Set<Integer> getLoginUsers(){
        if(servletContext.getAttribute("loginUsers")!=null){
            return (Set<Integer>) servletContext.getAttribute("loginUsers");
        }
        return null;
    }

    public void addRoleInServlet(Role role){
        if(servletContext.getAttribute("updateRoles")==null){
            servletContext.setAttribute("updateRoles",new HashSet<Integer>());
        }
        Set<Integer> updateRoles = (Set<Integer>) servletContext.getAttribute("updateRoles");
        updateRoles.add(role.getId());
    }


    public void addRemindInServlet(Role role){
        addRoleInServlet(role);
    }
    public void removeRoleInServlet(Role role){
        if(servletContext.getAttribute("updateRoles")!=null){
            Set<Integer> updateRoles = (Set<Integer>) servletContext.getAttribute("updateRoles");
            updateRoles.remove(role.getId());
        }
    }

    public void removeRolesInServlet(Set roles){
        if(servletContext.getAttribute("updateRoles")!=null){
            Set<Integer> updateRoles = (Set<Integer>) servletContext.getAttribute("updateRoles");
            updateRoles.removeAll(roles);
        }
    }
    public Set<Integer> getUpdateRoles(){
        if(servletContext.getAttribute("updateRoles")!=null){
            return (Set<Integer>) servletContext.getAttribute("updateRoles");
        }
        return null;
    }

    public Integer getLoginUserId(){
        User loginUser = (User) session.getAttribute(ParamsUtils.user_sessin);
        return  loginUser.getUserId();
    }
}
