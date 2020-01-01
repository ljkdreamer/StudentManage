package com.ljk.programmer.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ljk.programmer.entity.User;

import net.sf.json.JSONObject;

/**
 * 登录过滤拦截器
 * @author Administrator
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	//在进入之后拦截
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	//提前拦截
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {
		String uri = req.getRequestURI();
		System.out.println("进入登录拦截器,url:"+uri);
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			//ajax请求
			if("XMLHttpRequest".equals("X-Requested-With")){
				Map<String,String> map=new HashMap<String, String>();
				map.put("type", "error");
				map.put("msg", "登录状态已经失效,请重新登录");
				resp.getWriter().write(JSONObject.fromObject(map).toString());
				return false;
			}
			//表示登录失败
			System.out.println("未登录,或者登录失效,url:"+uri);
			//拿到根路径,在加上url地址
			resp.sendRedirect(req.getContextPath()+"/system/login");
			return false;
		}
		return true;
	}

}
