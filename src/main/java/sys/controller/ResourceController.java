package sys.controller;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sys.model.Resources;
import sys.service.ResourceService;

import java.util.List;

/**
 * author: zf
 * Date: 2016/10/17  15:38
 * Description:
 */
@Controller
@RequestMapping("/resManage")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;


    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
/**
 * @Description: 查询系统资源列表
 * @author: zf
 * @Date:   2016/10/17
 */
    @RequestMapping(value = "/resQuery",method = RequestMethod.GET)
    @ResponseBody
    public Object queryResList(){
        String simpleName = this.getClass().getSimpleName();
        int hashCode = this.getClass().hashCode();
        Thread thread = Thread.currentThread();
        logger.info("thread"+thread+"--访问资源列表--当前类"+simpleName+hashCode);
        JSONObject result = new JSONObject();
        try {
            List<Resources> list = resourceService.findList();
            if(null!=list && list.size()>0){
                result.put("code",200);
                result.put("resList",list);
            }else {
                result.put("code",404);
            }
        }catch (Exception e){
            result.put("code",500);
        }
        return result;
    }
    @RequestMapping(value = "/resFind",method = RequestMethod.GET)
    @ResponseBody
    public Object FindOne(Resources resources){
        JSONObject result = new JSONObject();
        try {
            Resources res= resourceService.findOne(resources);
            if(null!=resources ){
                result.put("code",200);
                result.put("res",res);
            }else {
                result.put("code",404);
            }
        }catch (Exception e){
            result.put("code",500);
        }
        return result;
    }
    /**
     * @Description: 资源新增
     * @author: zf
     * @Date:   2016/10/18
     */
    @RequestMapping(value = "/resAdd",method = RequestMethod.POST)
    @ResponseBody
    public Object add(@RequestBody Resources resources){
        JSONObject result = new JSONObject();
        try {
            int i = resourceService.addRes(resources);
            if(i ==1 ){
                result.put("code",200);
                result.put("msg","添加成功");
            }else {
                result.put("code",500);
                result.put("msg","添加失败");
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","添加失败");
        }
        return result;
    }

    /**
     * @Description: 资源下拉获取: parentId
     * @author: zf
     * @Date:   2016/10/19
     */
    @RequestMapping(value = "/letGo/resSelect",method = RequestMethod.GET)
    @ResponseBody
    public Object resSelect(){
        JSONObject result = new JSONObject();
        try {
            List<Resources> list = resourceService.getSelect();
            if(null!=list && list.size()>0){
                result.put("code",200);
                result.put("resList",list);
            }else {
                result.put("code",404);
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","获取失败");
        }
        return result;
    }

    @RequestMapping(value = "/resUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Object resUpdate(@RequestBody Resources resources){
        JSONObject result = new JSONObject();
        try {
           int i  = resourceService.updateRes(resources);
            if(i==1){
                result.put("code",200);
                result.put("msg","修改成功！");
            }else {
                result.put("code",500);
                result.put("msg","修改失败");
            }
        }catch (Exception e){
            result.put("code",500);
            result.put("msg","修改失败");
        }
        return result;
    }

}
