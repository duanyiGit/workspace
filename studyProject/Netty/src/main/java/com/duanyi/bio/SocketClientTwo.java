package com.duanyi.bio;

import java.io.IOException;
import java.net.Socket;

public class SocketClientTwo {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",9000);
            System.out.println("建立连接");
            socket.getOutputStream().write("hellow server,i am two.....".getBytes());
            socket.getOutputStream().flush();
            System.out.println("向服务器发送消息结束");
            byte[] bytes = new byte[1024];
            socket.getInputStream().read(bytes);
            System.out.println("接收到服务器消息:"+ new String(bytes));
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
