package resource.secImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sys.dao.ResourcesMapper;
import sys.dao.UserMapper;
import sys.model.Resources;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * org.springframework.security.core.userdetails.User
 * 			该类实现 UserDetails 接口，该类在验证成功后会被保存在当前回话的principal对象中
 * 获得对象的方式：
 * WebUserDetails webUserDetails = (WebUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 * 或在JSP中：
 * <sec:authentication property="principal.username"/>
 *
 * 如果需要包括用户的其他属性，可以实现 UserDetails 接口中增加相应属性即可
 * 权限验证类
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private ResourcesMapper resourcesDao ;
	// 登录成功 加载用户的资源权限
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 取得用户的权限
		sys.model.User users = userDao.querySingleUser(username);
		if  (users == null)
            throw new UsernameNotFoundException(username+" not exist!");
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(users);
		// 封装成spring security的user
		User userDetail = new User(
				users.getUserName(),
				users.getUserPassword(),
				true, true, true, true,
				grantedAuths	//用户的权限
			);
		return userDetail;
	}

	// 取得用户的权限
	private Set<GrantedAuthority> obtionGrantedAuthorities(sys.model.User user) {
		List<Resources> resources = resourcesDao.getUserResources(String.valueOf(user.getUserName()));
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		for (Resources res : resources) {
			// （或者说用户所拥有的权限） 注意：必须"ROLE_"开头
			authSet.add(new SimpleGrantedAuthority("ROLE_" + res.getResKey()));
		}
		return authSet;
	}
}