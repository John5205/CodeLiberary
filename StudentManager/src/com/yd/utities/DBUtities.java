package com.yd.utities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBUtities工具类
 * 
 * @author john
 * @Date 2018年1月11日
 * @company 亚帝科技有限公司
 */
public class DBUtities {
	/**
	 * 定义连接参数
	 */
	private static String url;
	private static String user;
	private static String password;
	private static String OracleDriver;
	private static String profile = "profile.txt";

	/**
	 * 静态初始化
	 */
	static {
		init();
	}

	/**
	 * 获取连接数据库的配置文件
	 * 
	 * @return Properties
	 */
	public static Properties readprofile() {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileReader(profile));
			return prop;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化数据
	 */
	public static void init() {
		Properties prop = readprofile();
		OracleDriver = prop.getProperty("OracleDriver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");

	}

	/**
	 * 返回连接数据库通道
	 * 
	 * @return Connection
	 * 
	 */
	public static Connection getconnection() {
		try {
			Class.forName(OracleDriver);
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 释放ps/conn资源
	 * 
	 * @param ps
	 *            执行参数
	 * @param conn
	 *            连接参数
	 */
	public static void close(PreparedStatement ps, Connection conn) {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 释放ps/conn/rs资源
	 * 
	 * @param ps
	 *            执行参数
	 * @param conn
	 *            连接参数
	 * @param rs
	 */
	public static void close(PreparedStatement ps, Connection conn, ResultSet rs) {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
