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
			JOptionPane.showMessageDialog(null, "��ȡIPʧ�ܣ����鱾�ؾ�������");
		}
		
		
		
	}

}
