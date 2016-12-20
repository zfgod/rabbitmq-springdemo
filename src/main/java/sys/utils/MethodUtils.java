package sys.utils;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * author: zf
 * Date: 2016/9/1  14:33
 * Description:
 */
public class MethodUtils {
//  处理分页数据返回
    public JSONObject returnPageResult(JSONObject result,int pageSize,int pageIndex,
                                       long total,List items){
        result.put("code",200);
        result.put("total_count",total);
        result.put("total_page",total%pageSize==0?total/pageSize:total/pageSize+1);
        result.put("page_size",pageSize);
        result.put("page",pageIndex);
        result.put("prev_page",pageIndex>=2?pageIndex-1:1);
        result.put("items",items);
        return result;
    }
}
