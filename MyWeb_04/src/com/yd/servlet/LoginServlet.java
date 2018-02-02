package com.yd.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yd.entity.User;
import com.yd.utils.UserDao;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * 这是不连数据库,判断账号的存在登录进行简单的登陆/计算
		 * String username = request.getParameter("username"); String password =
		 * request.getParameter("password"); String s1 = "admin"; String s2 =
		 * "123";
		 * 
		 * if(username.equals(s1) && password.equals(s2)){ Cookie cookie = new
		 * Cookie("remeberName", username); cookie.setMaxAge(60*60*24);
		 * response.addCookie(cookie);
		 * 
		 * HttpSession session = request.getSession();
		 * session.setAttribute("username",username);
		 * //response.sendRedirect("/MyWeb_04/successful.jsp");
		 * request.getRequestDispatcher("/successful.jsp").forward(request,
		 * response);
		 * 
		 * }else{ request.setAttribute("error", "您的账号和密码不匹配，请重新输入");
		 * request.getRequestDispatcher("/login.jsp").forward(request,
		 * response); }
		 */
		//通过连接数据库的账户，判断账号的存在登录进行简单的登陆/计算
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//System.out.println(username + password);
		// 创建对象
		UserDao userdao = new UserDao();
		User user = UserDao.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			// response.sendRedirect("/MyWeb_04/successful.jsp");
			request.getRequestDispatcher("/successful.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "您的账号和密码不匹配，请重新输入");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}
}
