package com.lcl.example.testmyserversocket.main;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Window {
	private JFrame f = new JFrame("麦克风-PC服务端");
	private JLabel hellolabel;
	
	public Window(String IP){
		f.setVisible(true);
		
		f.setLocation(500, 200);
		
		f.setSize(400, 250);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setResizable(false);
//-------------------------------------------------------------------
		hellolabel = new JLabel("欢迎使用麦克风！服务器初始化成功！IP： "+IP,JLabel.CENTER);
		
		f.add(hellolabel);
		
		
	}

}
