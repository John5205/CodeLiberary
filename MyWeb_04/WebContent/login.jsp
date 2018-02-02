<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
		<style type="text/css">
			#span{
				color:red;
				font-wight:bold;
			}	
		</style>
</head>
<body>
	<%
		String message = "";
		String error = (String) request.getAttribute("error");
		if(message != null){
			message = error;
		}
	%>
	<span id="msg"><%=message %></span>
<%-- 	<%
		/*
			客户端下一次再访问login.jsp的时候就会带着这个Cookie，我们可以在login.jsp中获取这个Cookie
			 * 如果这个Cookie中存在我们防止的Cookie，那么就设置到用户名的输入框
		*/
		String rememberName = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c : cookies){
				if("remeberName".equals(c.getName())){
					rememberName = c.getValue();
				}
			}
		}
	%> --%>
	<form action="/MyWeb_04/LoginServlet" method="post">
		用 户:<input type="text" name="username" /><br/>
		密 码:<input type="password" name="password" /><br/>
		    <input type="submit" value="登录" />
	</form>
	
</body>
</html>