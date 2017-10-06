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
				//��������
				JOptionPane.showMessageDialog(null, "�пͻ������ӵ�������12388�˿�");
				//��Socket���ݸ��µ��߳�
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
