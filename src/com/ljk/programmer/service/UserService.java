package com.ljk.programmer.service;



import java.util.List;
import java.util.Map;

import com.ljk.programmer.entity.User;


public interface UserService {

	public User findByUserName(String username);
	
	//添加
	public int add(User user);
	
	
	public List<User> findList(Map<String,Object> queryMap);
	
	public int  getTotal(Map<String,Object> queryMap);
	
	
	public int eidt(User user);
	
	
	public int delete(String ids);
}
