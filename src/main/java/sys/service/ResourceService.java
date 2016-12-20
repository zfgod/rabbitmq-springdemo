package sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.dao.ResourcesMapper;
import sys.model.Resources;

import java.util.List;

/**
 * author: zf
 * Date: 2016/10/17  15:39
 * Description:
 */
@Service
public class ResourceService {
    @Autowired
    private ResourcesMapper resourcesMapper;
    private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);
    /**
     * 资源列表
     * @return
     */
    public List<Resources> findList() {
        String simpleName = this.getClass().getSimpleName();
        int hashCode = this.getClass().hashCode();
        Thread thread = Thread.currentThread();
        logger.info("thread"+thread+"--查询资源列表--当前类"+simpleName+hashCode);
        List<Resources> all = resourcesMapper.findAll();//资源列表数据
//处理未定义资源标记不在页面显示
        for (Resources resources : all) {
            if(resources.getId()==0){
                all.remove(resources);
                break;
            }
        }
        return all;
    }

    /**
     * 获取指定资源信息
     * @param resources
     * @return
     */
    public Resources findOne(Resources resources) {
        return resourcesMapper.selectByPrimaryKey(resources);
    }

    /**
     * 添加资源
     * @param resources
     * @return
     */
    public int addRes(Resources resources) {
        return resourcesMapper.insertSelective(resources);
    }

    /**
     * 获取资源下拉
     * @return
     */
    public List<Resources> getSelect() {
        return resourcesMapper.getSelect();
    }

    /**
     * 修改资源信息
     * @param resources
     * @return
     */
    public int updateRes(Resources resources){
        return resourcesMapper.updateByPrimaryKeySelective(resources);
    }
}
