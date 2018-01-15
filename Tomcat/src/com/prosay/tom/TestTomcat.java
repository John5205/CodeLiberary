package com.prosay.tom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 模拟一个tomcat
 * @author 2017
 * @Date 2018年1月1日
 * @company 亚帝科技有限公司
 */
public class TestTomcat {
	public static void main(String[] args) throws IOException {
		// 浏览器相当于一个客户端，这里的9090是我设置的tomcat端口号，一般默认是8080
		ServerSocket server = new ServerSocket(9090);
		System.out.println("tomcat服务器启动了。。。");
		// 等待浏览器访问【浏览器实质相当于一个客户端】
		Socket socket = server.accept();
		System.out.println("浏览器请求成功！");
		// 解析请求 获取 http request 请求头中的内容
		InputStream in = socket.getInputStream();
		// 将字节流转换为字节流再包装字符缓冲流
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String tmp = "";
		while ((tmp=reader.readLine()) != null && tmp.length()>0) {
			str.append(tmp);
			str.append("\r\n");
		}
		System.out.println(str.toString());
		// 做出响应
		// tomcat容器将根目录下的文件解析 响应给浏览器
		String webRoot = "D:/WEB";
		String[] msgs = str.toString().split(" ");
		OutputStream out = socket.getOutputStream();
		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
		writer.println("HTTP/1.1 200 OK");
		writer.println("Content-Type: text/html;charset=utf-8");
		writer.println();		// 请求头写完后需要结束标记
		//writer.println("哈哈，今晚吃鸡去");
		writer.flush();
		
		if (msgs.length>2) {
			FileInputStream fileIn = new FileInputStream(new File(webRoot + msgs[1]));
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = fileIn.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
			fileIn.close();
			out.close();
		}
		writer.close();
		System.out.println("响应完成。");
	}
}
