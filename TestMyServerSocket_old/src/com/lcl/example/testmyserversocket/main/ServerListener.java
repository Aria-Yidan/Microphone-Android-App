package com.lcl.example.testmyserversocket.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ServerListener extends Thread {

	public void run()
	{
		//port from 1-65535
		try {
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(12388);
			while(true)
			{
				//block
				Socket  socket = serverSocket.accept();
				//建立连接
				JOptionPane.showMessageDialog(null, "有客户机连接到本机的12388端口");
				//将Socket传递给新的线程
				ChatSocket cs = new ChatSocket(socket);
				cs.start();
				ChatManager.getChatManager().add(cs);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
