package com.yd.dao.impl;

import java.util.List;

import com.yd.base.BaseDao;
import com.yd.dao.IStudentDao;
import com.yd.entity.Student;

/**
 * 
 * @author john
 * @Date 2018年1月11日
 * @company 亚帝科技有限公司
 */
public class StudentDaoImpl extends BaseDao implements IStudentDao {
	private String sql;

	@Override
	public int add(Student stu) {
		sql = " insert into t_student(sid,sname,ssex,sage,sbirthday,saddress,cid) values(seq_t_student.nextval,?,?,?,?,?,?) ";
		return super.baseAddInfo(sql, stu.getSname(), stu.getSsex(), stu.getSage(), stu.getSbirthday(),
				stu.getSaddress(), stu.getCid());
	}

	@Override
	public int update(Student stu) {
		sql = " update t_student set sname =?,ssex = ?,sage = ?,sbirthday = ?,saddress = ?,cid = ? where sid = ? ";
		return super.baseUpdate(sql, stu.getSid(), stu.getSname(), stu.getSsex(), stu.getSage(), stu.getSbirthday(),
				stu.getSaddress(), stu.getCid());
	}

	@Override
	public int deleteById(int sid) {
		sql = " delete from t_student  where sid = ?  ";
		return super.deleteById(sql, sid);
	}

	@Override
	public Student queryById(int sid) {
		sql = " select * from t_student where sid = ? ";
		return super.queryById(sql, Student.class, sid);
	}

	@Override
	public List<Student> queryAll() {
		sql = " select * from t_student ";
		return super.queryAll(sql, Student.class);
	}

	@Override
	public int queryCount() {
		return 0;
	}

}