package com.ljk.programmer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ljk.programmer.entity.User;
import com.ljk.programmer.page.Page;
import com.ljk.programmer.service.UserService;
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private  UserService userService;
	
	/***
	 * 用户管理列表页
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
    public ModelAndView list(ModelAndView model)
    {
		model.setViewName("user/user-list");
		return model;
    }
	
	//获取用户列表
	@RequestMapping(value="/get_list",method=RequestMethod.POST)
	@ResponseBody
	//返回json字符串
	public Map<String,Object> getList(
			@RequestParam(value="username",defaultValue="",required=false)String username,
			Page page)
	{
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> querymap=new HashMap<String,Object>();
		querymap.put("username", "%"+username+"%");
		querymap.put("offset", page.getOffset());
		querymap.put("pageSize",page.getRows());
		map.put("rows",userService.findList(querymap));
		map.put("total",userService.getTotal(querymap));
		return map;
	}
	
	//返回json数据
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,String> add(User user)
    {
		Map<String,String> map=new HashMap<String, String>();
		if(user==null){
			map.put("type", "error");
			map.put("msg", "数据绑定出错,请绑定管理员");
			return map;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			map.put("type", "error");
			map.put("msg", "用户名不能为空");
			return map;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			map.put("type", "error");
			map.put("msg", "密码不能为空");
			return map;
		}
		User findByUserName = userService.findByUserName(user.getUsername());
		if(findByUserName!=null){
			map.put("type", "error");
			map.put("msg", "该用户已经存在");
			return map;
		}
		if(userService.add(user)<=0){
			map.put("type", "error");
			map.put("msg", "添加失败");
			return map;
		}
		map.put("type", "success");
		map.put("msg", "添加成功");
		return map;
    }
	
	
	
	//修改
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
    public Map<String,String> eidt(User user)
    {
		System.out.println("我来了");
		Map<String,String> map=new HashMap<String, String>();
		if(user==null){
			map.put("type", "error");
			map.put("msg", "数据绑定出错,请绑定管理员");
			return map;
		}
		if(StringUtils.isEmpty(user.getUsername())){
			map.put("type", "error");
			map.put("msg", "用户名不能为空");
			return map;
		}
		if(StringUtils.isEmpty(user.getPassword())){
			map.put("type", "error");
			map.put("msg", "密码不能为空");
			return map;
		}
		User findByUserName = userService.findByUserName(user.getUsername());
		if(findByUserName!=null){
			if(user.getId()!=findByUserName.getId()){
				map.put("type", "error");
				map.put("msg", "该用户已经存在");
				return map;
			}
		}
		if(userService.eidt(user)<=0){
			map.put("type", "error");
			map.put("msg", "修改失败");
			return map;
		}
		map.put("type", "success");
		map.put("msg", "添加成功");
		return map;
    }
	
	
	
	//删除
		@RequestMapping(value="/delete",method=RequestMethod.POST)
		@ResponseBody
	    public Map<String,String> delete(
	    		@RequestParam(value="ids[]")Long[] ids)
	    {
			Map<String,String> map=new HashMap<String, String>();
			if(map==null){
				map.put("type", "error");
				map.put("msg", "删除失败");
				return map;
			}
			
			String idString="(";
			for(Long id:ids){
				idString+=id+",";
			}
			String substring = idString.substring(0, idString.length()-1);
			substring+=")";
			if(userService.delete(substring)<=0){
				map.put("type", "error");
				map.put("msg", "删除失败");
				return map;
			}
			System.out.println(substring);
			
			map.put("type", "success");
			map.put("msg", "删除成功");
			return map;
	    }
	
	
	
	
}
