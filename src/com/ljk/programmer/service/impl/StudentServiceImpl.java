package com.ljk.programmer.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljk.programmer.dao.StudentDao;
import com.ljk.programmer.entity.Student;
import com.ljk.programmer.service.StudentService;
import com.ljk.programmer.util.BCrypt;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	@Override
	public int add(Student student) {
		// TODO Auto-generated method stub
		String gensalt = BCrypt.gensalt();
		String hashpw = BCrypt.hashpw(student.getPassword(), gensalt);
		student.setPassword(hashpw);
		return studentDao.add(student);
	}

	@Override
	public int edit(Student student) {
		// TODO Auto-generated method stub
		String gensalt = BCrypt.gensalt();
		String hashpw = BCrypt.hashpw(student.getPassword(), gensalt);
		student.setPassword(hashpw);
		return studentDao.edit(student);
	}

	@Override
	public int delete(String ids) {
		// TODO Auto-generated method stub
		return studentDao.delete(ids);
	}

	@Override
	public List<Student> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentDao.findList(queryMap);
	}

	@Override
	public List<Student> findAll() {
		// TODO Auto-generated method stub
		return studentDao.findAll();
	}

	@Override
	public int getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return studentDao.getTotal(queryMap);
	}

	@Override
	public Student findByUserName(String username) {
		// TODO Auto-generated method stub
		return studentDao.findByUserName(username);
	}

}
