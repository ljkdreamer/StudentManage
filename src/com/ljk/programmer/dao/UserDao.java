package com.ljk.programmer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ljk.programmer.entity.User;

@Repository
public interface UserDao {
	public User findByUserName(String username);
	
	//添加
		public int add(User user);
		
		//查询全部,加分页查询
		public List<User> findList(Map<String,Object> queryMap);
		
		public int  getTotal(Map<String,Object> queryMap);
		
		public int eidt(User user);
		
		public int delete(@Param("ids")String ids);
}
