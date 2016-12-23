package resource.secImpl;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;

/**
 * 自己实现的过滤用户请求类，也可以直接使用 FilterSecurityInterceptor
 *
 * AbstractSecurityInterceptor有三个派生类：
 * FilterSecurityInterceptor，负责处理FilterInvocation，实现对URL资源的拦截。
 * MethodSecurityInterceptor，负责处理MethodInvocation，实现对方法调用的拦截。
 * AspectJSecurityInterceptor，负责处理JoinPoint，主要是用于对切面方法(AOP)调用的拦截。
 *
 * 还可以直接使用注解对Action方法进行拦截，例如在方法上加：
 * @PreAuthorize("hasRole('ROLE_SUPER')")
 * <!-- 用户是否拥有所请求资源的权限 -->
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if(configAttributes == null) {
			return;
		}
		/*所请求的资源拥有的权限(一个资源对多个权限)*/
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		/*当前用户的资源权限*/
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		/*多个权限，这里采用只要用户具备其中一个就表示可以通过*/
		ConfigAttribute configAttribute;
		String needPermission;
		while(iterator.hasNext()) {
			configAttribute = iterator.next();
			//访问请求资源所需要的权限
			needPermission = configAttribute.getAttribute();
			//用户所拥有的权限authentication
			for(GrantedAuthority ga : authorities) {
				if(needPermission.equals(ga.getAuthority())) {
					return;
				}
			}
		}
		throw new AccessDeniedException(" 没有权限访问！ ");
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
}