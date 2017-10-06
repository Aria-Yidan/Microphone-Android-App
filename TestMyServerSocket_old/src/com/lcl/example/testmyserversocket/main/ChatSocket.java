package com.lcl.example.testmyserversocket.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class ChatSocket extends Thread {

	public Socket socket;

	public ChatSocket(Socket s){
		this.socket = s;
	}

	public void out(String out){
		try {
			socket.getOutputStream().write(out.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(){
		byte[] data = new byte[1];
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					socket.getInputStream());
			File music = new File("C:/Users/Public/Music/Microphone.mp4");
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(music));
			while (bufferedInputStream.read(data) != -1){
                bufferedOutputStream.write(data);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
//            JOptionPane.showMessageDialog(null, "文件传输成功！");
            
            try{
            	Runtime.getRuntime().exec("C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe "+"C:\\Users\\Public\\Music\\Microphone.mp4");
            }catch (IOException e){
                e.printStackTrace();
            }
            
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
