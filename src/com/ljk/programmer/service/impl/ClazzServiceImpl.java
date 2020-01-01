package com.ljk.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljk.programmer.dao.ClazzDao;
import com.ljk.programmer.entity.Clazz;
import com.ljk.programmer.service.ClazzService;
@Service
public class ClazzServiceImpl implements ClazzService {

	@Autowired
	private ClazzDao ClazzDao ;
	
	

	@Override
	public int add(Clazz Clazz) {
	
		return ClazzDao.add(Clazz);
	}

	@Override
	public List<Clazz> findList(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return ClazzDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		
		return ClazzDao.getTotal(queryMap);
	}

	@Override
	public int eidt(Clazz Clazz) {
		
		return ClazzDao.eidt(Clazz);
	}

	@Override
	public int delete(String ids) {
		
		return ClazzDao.delete(ids);
	}

	@Override
	public List<Clazz> findAll() {
		// TODO Auto-generated method stub
		return ClazzDao.findAll();
	}

}
