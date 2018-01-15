package com.yd.service;

import java.util.List;

import com.yd.entity.Student;
/**
 * 针对学生提供的服务
 * service:学生
 * @author john
 * @Date 2018年1月11日
 * @company 亚帝科技有限公司
 */
public interface IStudentService {
	/**
	 * 添加学生信息到表
	 * @param s 需要添加的学生信息
	 * @return 
	 */     
	public int add(Student s);
	
	/**
	 * 更新学生表的信息
	 * @param s 
	 * 		-1表示添加信息失败
	 */	
	public int update(Student s);
	/**
	 * 根据sid删除学生
	 * @param sid
	 * 			-1表示添加信息失败
	 */
	public int deleteById(int sid);
	/**
	 * 根据sid查询学生信息
	 * @param id
	 */
	 
	public Student queryById(int sid);
	/**
	 * 查询所有学生信息
	 */
	public List<Student> queryAll();
	/**
	 * 查询学生信息条记录
	 */
	public int  queryCount();
}
