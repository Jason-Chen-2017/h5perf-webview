package com.example.jack.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    Button testButton;
    String testUrl = "https://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        testButton = (Button) findViewById(R.id.test_button);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runTest(testUrl);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //创建服务端Socket
                    ServerSocket serverSocket = new ServerSocket(8888);
                    Socket socket;
                    int read;
                    String content;

                    while ((socket = serverSocket.accept()) != null) {//当有客户端连接时进入
                        InputStream is = socket.getInputStream();
                        OutputStream os = socket.getOutputStream();
                        content = "";
                        while ((read = is.read()) != -1) {
                            System.out.print((char) read);
                            content += (char) read;
                        }
                        os.write("ok".getBytes());
                        os.close();
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 在当前机器的apk上执行H5测试
     *
     * @param testUrl
     */
    private void runTest(String testUrl) {
        Intent intent = new Intent(MainActivity.this, H5TestActivity.class);
        intent.putExtra("testUrl", testUrl);
        startActivity(intent);
    }


}
