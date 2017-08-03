package cn.tedu.note.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {
	@RequestMapping("/hello")
	@ResponseBody
	public Object hello(HttpServletResponse res) {
		Cookie demo = new Cookie("name","Tom");
//		demo.setMaxAge(60*60*24*30);
		demo.setPath("/");
		res.addCookie(demo);
		return new String[]{"Hello","World!"};
	}
	
	@RequestMapping("/hi.do")
	@ResponseBody
	public Object hi(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies) {
			System.out.println(cookie);
		}
		return new String[]{"Hi"};
	}
}


















