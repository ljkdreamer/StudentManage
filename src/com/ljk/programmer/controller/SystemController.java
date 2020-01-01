package com.ljk.programmer.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljk.programmer.entity.User;
import com.ljk.programmer.service.UserService;
import com.ljk.programmer.util.CpachaUtil;

/**
 * 系统主页控制器
 * @author Administrator
 *
 */

@RequestMapping("/system")
@Controller
public class SystemController {
	@Autowired
	private UserService userService;
	
	//method:后边跟一个枚举类型,表示请求方式
	//value:代表映射值
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		  model.setViewName("system/index");
		 
		return model;
	}
	
	/***
	 * 转跳登录页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		  model.setViewName("system/login");
		return model;
	}
	
	
	/***
	 * 表单提交
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	//返回json字符串
	public Map<String,String> login(
			@RequestParam(value="username",required=true)String username,
			@RequestParam(value="password",required=true)String password,
			@RequestParam(value="vcode",required=true)String vcode,
			@RequestParam(value="type",required=true)int type,
			HttpServletRequest request){
		 Map<String,String> map=new HashMap<String,String>();
		 if(StringUtils.isEmpty(username)){
			 map.put("type", "error");
			 map.put("msg", "用户名不能为空");
			 return map;
		 }
		 if(StringUtils.isEmpty(password)){
			 map.put("type", "error");
			 map.put("msg", "密码不能为空");
			 return map;
		 }if(StringUtils.isEmpty(vcode)){
			 map.put("type", "error");
			 map.put("msg", "验证码不能为空");
			 return map;
		 }
		 String loginCpacha = request.getSession().getAttribute("loginCpacha").toString();
		 if(StringUtils.isEmpty(loginCpacha)){
			 map.put("type", "error");
			 map.put("msg", "长时间未操作,会话已经失效,请刷新后重试");
			 return map;
		 }if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
			 map.put("type", "error");
			 map.put("msg", "验证码错误");
			 return map; 
		 }
		 //清空session里边的验证码,避免内存浪费
		 request.setAttribute("loginCpacha", null);	
		 if(type==1){//管理员
			 //从数据库查找账户密码
			 User findByUserName = userService.findByUserName(username);
			 if(findByUserName==null){
				 map.put("type", "error");
				 map.put("msg", "该用户不存在");
				 return map;
			 }
			 if(!password.equals(findByUserName.getPassword())){
				 map.put("type", "error");
				 map.put("msg", "密码错误");
				 return map;
			 }
			 request.getSession().setAttribute("user", findByUserName);
		 } if(type==2){
			 //从数据库查找账户密码
			
		 }
		
		 map.put("type", "success");
		 map.put("msg", "登陆成功");
		 return map;
	}
	
	
	/***
	 * 生成验证码
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/get_capacha",method=RequestMethod.GET)
	public void getCapacha(HttpServletRequest request,HttpServletResponse response){
		 // System.out.println("哈哈哈哈");
		  CpachaUtil cpachaUtil = new CpachaUtil(4,98,33);
		  String code = cpachaUtil.generatorVCode();
		  request.getSession().setAttribute("loginCpacha",code);
		  BufferedImage image = cpachaUtil.generatorRotateVCodeImage(code,true);
		  try {
			ImageIO.write(image,"gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
