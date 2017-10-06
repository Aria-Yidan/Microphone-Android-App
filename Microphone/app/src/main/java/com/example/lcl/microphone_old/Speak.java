package com.example.lcl.microphone_old;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class Speak extends AppCompatActivity implements View.OnClickListener {
    private String ip = null;

    private File recordAudioFile;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speak);

        Intent i = getIntent();
        ip = i.getStringExtra("IP");

        connect();

        TextView tv1;
        tv1 = (TextView) findViewById(R.id.tv_connected_IP);
        tv1.setText(ip);

        Button btnRecord = (Button) findViewById(R.id.btnRecord);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        Button btnPlay = (Button) findViewById(R.id.btnPlay);
        btnRecord.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPlay.setOnClickListener(this);


    }
    //----------------------------------------------------------------------------------------------
    public Socket socket = null;

    public void connect(){
        System.out.println("IP是：" + ip);
        AsyncTask<Void,String,Void> read = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    socket = new Socket(ip,12388);
                    publishProgress("@success");
                } catch (IOException e) {
                    Toast.makeText(Speak.this, "无法建立连接！", Toast.LENGTH_SHORT).show();
                }

                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {

                if (values[0].equals("@success")){
                    Toast.makeText(Speak.this,"连接成功！",Toast.LENGTH_SHORT).show();
                }

                super.onProgressUpdate(values);
            }
        };
        read.execute();

    }

    public void send(){

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRecord:
                try {
                    recordAudioFile = File.createTempFile("record",".mp4");
                    mediaRecorder = new MediaRecorder();
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

                    mediaRecorder.setOutputFile(recordAudioFile.getAbsolutePath());
                    mediaRecorder.prepare();
                    mediaRecorder.start();
                    Toast.makeText(this,"开始录音",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.btnStop:
                if (mediaRecorder != null){
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    Toast.makeText(this,"停止录制",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnPlay:

                byte[] data = new byte[1];
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(recordAudioFile));
                    while (bufferedInputStream.read(data) != -1){
                        bufferedOutputStream.write(data);
                    }
                    bufferedOutputStream.flush();
                    bufferedInputStream.close();
                    bufferedOutputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                recordAudioFile.delete();

                break;

            default:
                break;
        }
    }
}
