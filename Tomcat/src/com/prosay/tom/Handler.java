package com.prosay.tom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 
 * 用来处理用户的单个请求的子线程处理类
 * 
 * @author john
 * @Date 2018年1月1日
 * @company 亚帝科技有限公司
 */
public class Handler implements Runnable {
	private Socket socket;

	public Handler(Socket socket) {
		this.socket = socket;
	}

	/**
	 * 执行子线程响应处理
	 */
	@Override
	public void run() {
		try {
			// 解析请求 获取 http request 请求头中的内容
			InputStream in = socket.getInputStream();
			// 将字节流转换为字节流再包装字符缓冲流
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String tmp = "";
			while ((tmp = reader.readLine()) != null && tmp.length() > 0) {
				str.append(tmp);
				str.append("\r\n");
			}
			System.out.println(str.toString());
			// 做出响应
			// tomcat容器将根目录下的文件解析 响应给浏览器
			String webRoot = "D:/web";
			String[] msgs = str.toString().split(" ");
			OutputStream out = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
			writer.println("HTTP/1.1 200 OK");
			writer.println("Content-Type: text/html;charset=utf-8");
			writer.println(); // 请求头写完后需要结束标记
			// writer.println("今晚咋们吃鸡去咋样");
			writer.flush();

			if (msgs.length > 2) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
