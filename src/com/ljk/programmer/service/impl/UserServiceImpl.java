package com.ljk.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljk.programmer.dao.UserDao;
import com.ljk.programmer.entity.User;
import com.ljk.programmer.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao ;
	
	@Override
	public User findByUserName(String username) {
		
		return userDao.findByUserName(username);
	}

	@Override
	public int add(User user) {
	
		return userDao.add(user);
	}

	@Override
	public List<User> findList(Map<String,Object> queryMap) {
		// TODO Auto-generated method stub
		return userDao.findList(queryMap);
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		
		return userDao.getTotal(queryMap);
	}

	@Override
	public int eidt(User user) {
		
		return userDao.eidt(user);
	}

	@Override
	public int delete(String ids) {
		
		return userDao.delete(ids);
	}

}
