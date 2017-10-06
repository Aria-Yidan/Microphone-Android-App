package com.lcl.example.testmyserversocket.main;

import java.util.Vector;

public class ChatManager {
	//ตฅภýปฏ
	private ChatManager(){}
	private static final ChatManager cm = new ChatManager();
	public static ChatManager getChatManager(){
		return cm;
	}

	Vector<ChatSocket> vector = new Vector<ChatSocket>(); 
	
	public void add(ChatSocket cs){
		vector.add(cs);
	}
	
	public void publish(ChatSocket cs, String out){
		System.out.println(out);
		for(int i=0; i<vector.size();i++){
			ChatSocket csChatSocket = vector.get(i);
			if (!cs.equals(csChatSocket)){
				
				csChatSocket.out(out);
			}
		}
			
	}
}
