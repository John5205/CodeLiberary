package com.yd.service.impl;

import java.util.List;

import com.yd.dao.IStudentDao;
import com.yd.dao.impl.StudentDaoImpl;
import com.yd.entity.Student;
import com.yd.service.IStudentService;

public class StudentServiceeImpl implements IStudentService,IStudentDao {
		
	IStudentDao dao = new StudentDaoImpl();
	
	@Override
	public int add(Student stu) {
		// TODO Auto-generated method stub
		return dao.add(stu);
	}

	@Override
	public int update(Student stu) {
		// TODO Auto-generated method stub
		return dao.update(stu);
	}

	@Override
	public int deleteById(int sid) {
		// TODO Auto-generated method stub
		return dao.deleteById(sid);
	}

	@Override
	public Student queryById(int sid) {
		// TODO Auto-generated method stub
		return dao.queryById(sid);
	}

	@Override
	public List<Student> queryAll() {
		// TODO Auto-generated method stub
		return dao.queryAll();
	}

	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return dao.queryCount();
	}

}
