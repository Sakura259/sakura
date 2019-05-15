package com.study.nio;

import sun.misc.IOUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Sakura on 2019/4/21.
 */
public class SocketHandler implements Runnable {
    private SocketChannel socketChannel;

    public SocketHandler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int num;
            while (true) {
                if (((num = socketChannel.read(byteBuffer)) > 0)) {
                    byteBuffer.flip();
                    byte[] bytes = new byte[num];
                    byteBuffer.get(bytes);
                    String string = new String(bytes, "utf-8");
                    System.out.println("服务端收到的数据："+string);

                    ByteBuffer writeBuffer = ByteBuffer.wrap(("收到数据 :"+string).getBytes());
                    socketChannel.write(writeBuffer);
                    byteBuffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
