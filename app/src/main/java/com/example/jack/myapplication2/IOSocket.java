package com.example.jack.myapplication2;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by jack on 2017/4/13.
 */

public class IOSocket implements Runnable {

    private Socket client;

    public IOSocket(Socket client) {
        this.client = client;
    }

    /* 读取命令 */
    public static String readCMDFromSocket(InputStream in) {
        int MAX_BUFFER_BYTES = 2048;
        String msg = "";
        byte[] tempbuffer = new byte[MAX_BUFFER_BYTES];
        try {
            int numReadedBytes = in.read(tempbuffer, 0, tempbuffer.length);
            msg = new String(tempbuffer, 0, numReadedBytes, "utf-8");
            tempbuffer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void run() {
        //BufferedOutputStream out;
        BufferedInputStream in;
        try {

            //out = new BufferedOutputStream(client.getOutputStream());
            in = new BufferedInputStream(client.getInputStream());
            //PC端发来的命令
            String order = "";
            //测试socket
            boolean ioThreadFlag = true;
            while (ioThreadFlag) {
                try {
                    if (!client.isConnected()) {
                        break;
                    }


                    //读取PC发送过来的命令
                    order = readCMDFromSocket(in);

                    /* 根据命令分别处理数据 */
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null) {
                    client.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
