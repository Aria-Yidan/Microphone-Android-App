package com.lcl.example.testmyserversocket.main;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Window {
	private JFrame f = new JFrame("��˷�-PC�����");
	private JLabel hellolabel;
	
	public Window(String IP){
		f.setVisible(true);
		
		f.setLocation(500, 200);
		
		f.setSize(400, 250);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setResizable(false);
//-------------------------------------------------------------------
		hellolabel = new JLabel("��ӭʹ����˷磡��������ʼ���ɹ���IP�� "+IP,JLabel.CENTER);
		
		f.add(hellolabel);
		
		
	}

}
