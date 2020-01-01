package com.ljk.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljk.programmer.dao.GradeDao;
import com.ljk.programmer.entity.Grade;
import com.ljk.programmer.service.GradeService;
@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao ;
	
	

	@Override
	public int add(Grade Grade) {
	
		return gradeDao.add(Grade);
	}

	@Override
	public List<Grade> findList(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return gradeDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		
		return gradeDao.getTotal(queryMap);
	}

	@Override
	public int eidt(Grade Grade) {
		
		return gradeDao.eidt(Grade);
	}

	@Override
	public int delete(String ids) {
		
		return gradeDao.delete(ids);
	}

	@Override
	public List<Grade> findAll() {
		// TODO Auto-generated method stub
		return gradeDao.findAll();
	}

}
