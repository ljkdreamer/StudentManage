package com.ljk.programmer.service;

import java.util.List;
import java.util.Map;

import com.ljk.programmer.entity.Grade;




public interface GradeService {
	
	

	
	//添加
	public int add(Grade grade);
	
	
	public List<Grade> findList(Map<String,Object> queryMap);
	
	public int  getTotal(Map<String,Object> queryMap);
	
	public List<Grade> findAll();
	
	public int eidt(Grade grade);
	
	
	public int delete(String ids);
	
	

}
