package resource.secImpl;/*
package resource.secImpl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sys.dao.UserMapper;
import sys.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * 这个类主要是用户登录验证
 * 如果是自定义的登录，将不会用此登�?
 *//*

public class MyAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	*/
/**
	 * 登录成功后跳转的地址
	 *//*

	private String successUrl = "/sys/main.html";
	*/
/**
	 * 登录失败后跳转的地址
	 *//*

	private String errorUrl = "/sys/login.html";
	@Autowired
	private UserMapper userDao;

	*/
/**
	 * 自定义表单参数的name属�?�，默认�? j_username �? j_password
	 * 定义登录成功和失败的跳转地址
	 *//*

	public void init() {
//		System.err.println(" ---------------  MyAuthenticationFilter init--------------- ");
		this.setUsernameParameter(USERNAME);
		this.setPasswordParameter(PASSWORD);
		// 验证成功，跳转的页面
		SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
		successHandler.setDefaultTargetUrl(successUrl);
		this.setAuthenticationSuccessHandler(successHandler);

		// 验证失败，跳转的页面
		SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl(errorUrl);
		this.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
//		System.err.println(" ---------------  MyAuthenticationFilter attemptAuthentication--------------- ");
		
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: "
							+ request.getMethod());
		}

		String username = obtainUsername(request).trim();
		String password = obtainPassword(request).trim();
		// System.out.println(">>>>>>>>>>000<<<<<<<<<< username is " +
		// username);
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不能为空�?");// 在界面输出自定义的信息！�?
			throw exception;
		}

		// 验证用户账号与密码是否正�?
		User users = this.userDao.querySingleUser(username);
		if (users == null || !users.getUserPassword().equals(password)) {
			BadCredentialsException exception = new BadCredentialsException(
					"用户名或密码不匹配！");// 在界面输出自定义的信息！�?
			// request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
			// exception);
			throw exception;
		}
		// 当验证都通过后，把用户信息放在session�?
		request.getSession().setAttribute("userSession", users);

		// 实现 Authentication
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		// 允许子类设置详细属�??
		setDetails(request, authRequest);

		// 运行UserDetailsService的loadUserByUsername 再次封装Authentication
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getErrorUrl() {
		return errorUrl;
	}

	public void setErrorUrl(String errorUrl) {
		this.errorUrl = errorUrl;
	}
}
*/
