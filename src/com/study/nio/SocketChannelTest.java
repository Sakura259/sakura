package com.study.nio;

import com.sun.corba.se.spi.presentation.rmi.IDLNameTranslator;

import javax.xml.validation.ValidatorHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by Sakura on 2019/4/21.
 */
public class SocketChannelTest {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",7000));
        //发送请求
        ByteBuffer byteBuffer = ByteBuffer.wrap("123".getBytes());
        socketChannel.write(byteBuffer);
        //读取响应
        ByteBuffer readBuffer =ByteBuffer.allocate(1024);

        int num;
        while (true){
            if((num=socketChannel.read(readBuffer))>0){
                readBuffer.flip();
                byte[] bytes = new byte[num];
                readBuffer.get(bytes);
                System.out.println(new String(bytes,"UTF-8"));
            }
        }
    }
}
