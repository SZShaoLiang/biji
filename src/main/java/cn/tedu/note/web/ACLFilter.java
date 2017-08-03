package cn.tedu.note.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.tedu.note.service.UserService;

public class ACLFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		String path = request.getRequestURI();		
		path = path.substring(path.indexOf("/",1));
//		System.out.println("trim path:" +path);
		if(path.matches(".*/edit\\.html$")) {
			checkLogin(request,response,chain);
			return;
		}
		if(path.matches(".(note).*\\.do$")) {
			checkDotDo(request,response,chain);
			return;
		}
		chain.doFilter(request, response);
	}

	private void checkDotDo(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		System.out.println("checkDotDo");
		String token = null;
		token = getCookie(request, "token");	
		if(token == null) {
			responseJSON(response);
			return;
		}
		UserService service = getUserService(request);
		String userId = getCookie(request,"userId");
		if(service.checkToken(userId,token)) {
			chain.doFilter(request, response);
			return;
		}
		responseJSON(response);
	}

	private void responseJSON(HttpServletResponse response) throws IOException {
		String json = "{\"state\":1,\"message\":\"必须登录！\"}";
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
	}

	private UserService getUserService(HttpServletRequest request) {
		ServletContext sc = request.getServletContext();
		//获取spring容器
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(sc);
		//从容器中获取UserService 对象
		UserService service = ctx.getBean("userService",UserService.class);
		return service;
	}

	private String getCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		//如果客户端没有cookie，就会返回null
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				if(cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	private void checkLogin(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//检查是否有 token cookie
		//如果没有，重定向到log_in.html
		String token = getCookie(request,"token");
		if(token == null) {
			responseLogin(request, response);
			return;
		}
		UserService service = getUserService(request);
		String userId = getCookie(request,"userId");
		//检查token的有效性
		if(service.checkToken(userId,token)) {
			chain.doFilter(request, response);
			return;
		}
		//重定向到登录页 log_in.html
		responseLogin(request, response);
	}

	private void responseLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String path = request.getContextPath() + "/log_in.html";
		response.sendRedirect(path);
	}

	public void init(FilterConfig cfg) throws ServletException {
		
	}

}




































