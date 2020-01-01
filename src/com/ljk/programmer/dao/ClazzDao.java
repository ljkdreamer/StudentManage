package com.ljk.programmer.dao;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.ljk.programmer.entity.Clazz;

@Repository
public interface ClazzDao {
	
	
	//添加
		public int add(Clazz Clazz);
		
		//查询全部,加分页查询
		public List<Clazz> findList(Map<String,Object> queryMap);
		
		public int  getTotal(Map<String,Object> queryMap);
		
		public int eidt(Clazz Clazz);
		
		public int delete(String ids);
		
		public List<Clazz> findAll();
}
