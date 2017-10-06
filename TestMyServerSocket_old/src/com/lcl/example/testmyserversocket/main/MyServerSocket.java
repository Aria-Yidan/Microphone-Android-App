package com.lcl.example.testmyserversocket.main;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class MyServerSocket {

	public static void main(String[] args) {
		String IP = null;
		try {
			IP = InetAddress.getLocalHost().getHostAddress();
			new Window(IP);
			new ServerListener().start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "获取IP失败！请检查本地局域网！");
		}
		
		
		
	}

}
