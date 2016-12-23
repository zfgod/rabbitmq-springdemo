package resource.secImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import sys.model.Resources;
import sys.dao.ResourcesMapper;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * 加载资源与权限的对应关系
 * */
@Service
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private ResourcesMapper resourcesMapper;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public Collection<ConfigAttribute> getAllConfigAttributes() {

		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	/**
	 * 加载所有资源与权限的关系
	 */
	@PostConstruct
	private void loadResourceDefine() {
//		System.err.println("-----------MySecurityMetadataSource loadResourceDefine ----------- ");
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<Resources> resources = this.resourcesMapper.findAll();
			ConfigAttribute configAttribute;
			String resUrl;
			for (Resources resource : resources) {
				//通过资源名称来表示具体的权限 注意：必须"ROLE_"开头
				configAttribute = new SecurityConfig("ROLE_" + resource.getResKey());
				resUrl = resource.getResUrl();
				if(resourceMap.containsKey(resUrl)){
					resourceMap.get(resUrl).add(configAttribute);
				}else {
                    //新put key-value,value list必须新建
					Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
					configAttributes.add(configAttribute);
					resourceMap.put(resource.getResUrl(), configAttributes);
				}
			}
		}
	}
	/**
	 * 	返回所请求资源所需要的权限
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		if(resourceMap == null) {
			loadResourceDefine();
		}
		if(requestUrl.indexOf("?")> -1){//处理请求地址后面带参数
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		Collection<ConfigAttribute> configAttributes = resourceMap.get(requestUrl);
            /*如果为null,视为系统未定义的资源路径*/
		if(configAttributes == null){
			configAttributes = resourceMap.get("undefine");//此权限每个用户都不具有,则未加入的url不会通过
		}
		return configAttributes;
	}
}