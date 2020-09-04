package com.duanyi.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while (true){
                System.out.println("等待连接。。。");
                //阻塞方法，等待连接
                final Socket socket = serverSocket.accept();
                System.out.println("有客户端连接");
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           handle(socket);
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handle(Socket socket) throws IOException {
        System.out.println("thread id="+ Thread.currentThread().getId());
        byte[] bytes = new byte[1024];
        System.out.println("等待read");
        //阻塞方法，等待客户端写数据，读取到客户端发送的数据后才会处理新的客户端数据
        int read = socket.getInputStream().read(bytes);
        System.out.println("read 完毕");
        if(read != -1){
            System.out.println("接收到客户端的消息"+new String(bytes,0,read));
            System.out.println("thread id = " + Thread.currentThread().getId());
        }
        socket.getOutputStream().write("hellow client".getBytes());
        socket.getOutputStream().flush();
    }
}
