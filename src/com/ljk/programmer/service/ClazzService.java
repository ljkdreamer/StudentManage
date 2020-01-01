package com.ljk.programmer.service;

import java.util.List;
import java.util.Map;

import com.ljk.programmer.entity.Clazz;




public interface ClazzService {
	
	

	
	//添加
	public int add(Clazz clazz);
	
	
	public List<Clazz> findList(Map<String,Object> queryMap);
	
	public int  getTotal(Map<String,Object> queryMap);
	
	public List<Clazz> findAll();
	
	public int eidt(Clazz clazz);
	
	
	public int delete(String ids);
	
	

}
