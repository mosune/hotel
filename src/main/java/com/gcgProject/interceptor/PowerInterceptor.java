package com.gcgProject.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gcgProject.entity.Menu;
import com.gcgProject.entity.User;

/**
 * 权限拦截
 * @author Administrator
 *
 */
public class PowerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getRequestURI();
		User user = (User) request.getSession().getAttribute("user");
		List<Menu> menus = user.getMainMenus();
		for (Menu menu : menus) {
			if (checkUrl(path, menu.getPath())) {
				return true;
			}
		}
		request.getRequestDispatcher("/404.jsp").forward(request, response);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
	
	private boolean checkUrl(String url, String menu) {
		String b = url.substring(0, url.lastIndexOf("/"));
		String c = b.substring(0, b.lastIndexOf("/"));
		return c.substring(c.indexOf("//") + 2).equals(menu);
	}

}
