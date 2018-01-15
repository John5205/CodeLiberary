package com.prosay.tom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Web应用程序的主入口
 * @author john
 * @Date 2018年1月1日
 * @company 亚帝科技有限公司
 */
public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(80);	//  浏览器默认访问端口
			while(true){
				// 阻塞方法，没有请求连接的时候，程序会在此行等待
				Socket socket = server.accept();
				Thread tr = new Thread(new Handler(socket));
				tr.start();
			}
		} catch (IOException e) {
		}	
	}
}
