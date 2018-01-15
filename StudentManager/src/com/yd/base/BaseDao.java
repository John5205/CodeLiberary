package com.yd.base;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yd.utities.DBUtities;

/**
 * 
 * @author john
 * @Date 2018年1月13日
 * @company 亚帝科技有限公司
 */
public class BaseDao {
	/**
	 * 这是对连接数据库、创建sql管道设置的参数
	 */
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;

	/**
	 * 根据是sid进行删除学生信息
	 * 
	 * @param sql
	 *            sql中的删除执行语句参数
	 * @param objs
	 *            sql中占位符参数
	 * @return -1是返回一个添加失败的信息
	 */
	public static int deleteById(String sql, Object... objs) {
		// 获取连接通道
		conn = DBUtities.getconnection();

		try {
			// 建立通道
			ps = conn.prepareStatement(sql);
			// 设置占位符
			setPlaceHoder(objs);
			// 执行sql语句
			int update = ps.executeUpdate();
			return update;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtities.close(ps, conn);
		}
		return -1;
	}

	/**
	 * 进行学生表中添加学生信息
	 * 
	 * @param sql
	 *            sql中的添加执行语句参数
	 * @param objs
	 *            sql中占位符参数
	 * @return -1是返回一个添加失败的信息
	 */
	public int baseAddInfo(String sql, Object... objs) {
		// 1.加载驱动
		conn = DBUtities.getconnection();
		try {
			// 2.建立SQL语句
			ps = conn.prepareStatement(sql);
			// 3.执行sql语句
			setPlaceHoder(objs);
			int update = ps.executeUpdate();
			return update;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtities.close(ps, conn);
		}
		return -1;
	}

	/**
	 * 根据sid进行对学生信息修改
	 * 
	 * @param sql
	 *            sql中的修改执行语句参数
	 * @param objs
	 *            sql中占位符参数
	 * @return -1是返回一个修改失败的信息
	 */
	public static int baseUpdate(String sql, Object... objs) {
		// 1.加载驱动
		conn = DBUtities.getconnection();
		try {
			// 2.建立SQL语句
			ps = conn.prepareStatement(sql);
			// 3.执行sql语句
			setPlaceHoder(objs);
			int update = ps.executeUpdate();

			return update;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtities.close(ps, conn);
		}
		return -1;
	}

	/**
	 * 根据学生的sid进查询学生信息
	 * 
	 * @param sql
	 *            sql中的查询执行语句参数
	 * @param C
	 *            entity中的实体类的参数
	 * @param objs
	 *            sql中占位符参数
	 * @return
	 */
	public <T> T queryById(String sql, Class c, Object... objs) {
		// 获取连接通道
		conn = DBUtities.getconnection();
		try {
			// 建立sql通道
			ps = conn.prepareStatement(sql);
			// 遍历占位进行赋值
			setPlaceHoder(objs);
			// 执行sql语句
			rs = ps.executeQuery();
			// 获取元数据
			ResultSetMetaData md = rs.getMetaData();
			// 获取查询表的列数
			int columnCount = md.getColumnCount();
			System.out.println("总共有" + columnCount + "列");

			while (rs.next()) {
				// 创建字节码对象
				Object obj = c.newInstance();
				for (int i = 0; i < columnCount; i++) {
					// 获取动态的对应的列
					String columnLabel = md.getColumnLabel(i + 1).toLowerCase();
					// 获取动态的对应的列值
					Object value = rs.getObject(columnLabel);
					if (value == null) {
						continue;
					}
					// 判断数据库中的字段在javabean是否存在
					if (hasField(c, columnLabel)) {
						Field field = c.getDeclaredField(columnLabel);
						// 进行暴力访问
						field.setAccessible(true);

						if (value instanceof BigDecimal) {
							// 数据库中对应的Number类型在Java里面是BigDecimal
							BigDecimal bd = (BigDecimal) value;
							// 判断字段是否是该类型,如果是int类型否则就是double类型
							if (field.getType().getName().equals("int")) {
								field.set(obj, bd.intValue());
							} else {
								field.set(obj, bd.doubleValue());
							}
						} else {
							field.set(obj, value);
						}
					}
				}
				return (T) obj;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 查询学生所有信息
	 * 
	 * @param sql
	 *            sql中的修改执行语句参数
	 * @param c
	 *            entity中的实体类的参数
	 * @param objs
	 *            sql中占位符参数
	 * @return list返回一个list集合
	 */
	public <T> List<T> queryAll(String sql, Class c, Object... objs) {
		// 创建集合把信息放存入集合
		List<T> list = new ArrayList<T>();
		// 获取连接通道
		conn = DBUtities.getconnection();
		try {
			// 建立sql通道
			ps = conn.prepareStatement(sql);
			// 遍历占位进行赋值
			setPlaceHoder(objs);
			// 执行sql语句
			rs = ps.executeQuery();
			// 获取元数据
			ResultSetMetaData md = rs.getMetaData();
			// 获取查询表的列数
			int columnCount = md.getColumnCount();
			System.out.println("总共有" + columnCount + "列");

			while (rs.next()) {
				// 创建字节码对象
				Object obj = c.newInstance();
				for (int i = 0; i < columnCount; i++) {
					// 获取动态的对应的列
					String columnLabel = md.getColumnLabel(i + 1).toLowerCase();
					// 获取动态的对应的列值
					Object value = rs.getObject(columnLabel);
					if (value == null) {
						continue;
					}
					// 判断数据库中的字段在javabean是否存在
					if (hasField(c, columnLabel)) {
						Field field = c.getDeclaredField(columnLabel);
						// 进行暴力访问
						field.setAccessible(true);

						if (value instanceof BigDecimal) {
							// 数据库中对应的Number类型在Java里面是BigDecimal
							BigDecimal bd = (BigDecimal) value;
							// 判断字段是否是该类型,如果是int类型否则就是double类型
							if (field.getType().getName().equals("int")) {
								field.set(obj, bd.intValue());
							} else {
								field.set(obj, bd.doubleValue());
							}
						} else {
							field.set(obj, value);
						}
					}
				}
				list.add((T) obj);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 判断数据库中查询出来的列名是否在javabean中存在
	 * 
	 * @param c
	 *            类对象
	 * @param columnLabel字段名称
	 * @return true 存在 false 不存在
	 * 
	 */
	public boolean hasField(Class c, String columnLabel) {
		Field[] fields = c.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().equals(columnLabel)) {

				return true;
			}
		}
		return false;
	}

	/**
	 * 设置占位符赋值方法
	 * 
	 * @param objs
	 *            sql中占位符参数
	 * @throws SQLException
	 *             sql语句错误异常
	 */
	public static void setPlaceHoder(Object... objs) throws SQLException {
		if (objs != null) {
			// 遍历占位进行赋值
			for (int i = 0; i < objs.length; i++) {
				ps.setObject(i + 1, objs[i]);
			}
		}
	}
}