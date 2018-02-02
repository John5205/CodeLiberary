<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录成功页面</title>
</head>
<body>
	<%
 	String username =(String)session.getAttribute("username");
	if(username == null){
		request.setAttribute("error", "您还未登录，登录后再来吧!");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return;
	}
	%>
	欢迎用户<%=username %>到来
	
	<form action="/MyWeb_04/CalcServlet" method="post">
	请输入数值:<input type="text" name="num1" /><br/>
	请输入数值:<input type="text" name="num2" /><br/>
			<input type="submit" value="计算" />
	</form>		
</body>
</html>