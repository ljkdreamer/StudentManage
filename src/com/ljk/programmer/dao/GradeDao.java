package com.ljk.programmer.dao;

import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.ljk.programmer.entity.Grade;

@Repository
public interface GradeDao {
	
	
	//添加
		public int add(Grade grade);
		
		//查询全部,加分页查询
		public List<Grade> findList(Map<String,Object> queryMap);
		
		public int  getTotal(Map<String,Object> queryMap);
		
		public int eidt(Grade grade);
		
		public int delete(String ids);
		
		public List<Grade> findAll();
}
