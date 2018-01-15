package com.yd.dao;

import java.util.List;
import com.yd.entity.Student;

/**
 * Dao:学生dao 定义该dao完成数据库操作
 * 
 * @author john
 * @Date 2018年1月11日
 * @company 亚帝科技有限公司
 */
public interface IStudentDao {
	/**
	 * 添加学生信息到学生表中
	 * 
	 * @param s  需要添加的学生信息
	 *             -1表示添加信息失败
	 */
	public int add(Student s);

	/**
	 * 更新学生表的信息
	 * 
	 * @param s
	 *            -1表示添加信息失败
	 */
	public int update(Student s);

	/**
	 * 根据sid删除学生
	 * 
	 * @param sid
	 *            -1表示添加信息失败
	 */
	public int deleteById(int sid);

	/**
	 * 根据sid查询学生信息
	 * 
	 * @param sid
	 */

	public Student queryById(int sid);

	/**
	 * 查询所有学生信息
	 */
	public List<Student> queryAll();

	/**
	 * 查询学生信息条记录
	 */
	public int queryCount();
}
