package com.hlf.demo.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * nio test
 * @author hlf
 * @since 2019/07/15 09:06
 */
public class StandNioTest {

    /**
     * NIO读取文件流
     */
    private static void readNio() {
        try{
            //1、开启文件读取流
            FileInputStream  fileInputStream = new FileInputStream( "/Users/hulingfeng/Desktop/1.pmml" );
            //2、获取fileChannel
            FileChannel channel = fileInputStream.getChannel();
            //3、设置ByteBuffer大小，一次能容纳capacity字节
            int capacity = 16;
            ByteBuffer bf = ByteBuffer.allocate(capacity);
            //4、当read返回-1时，表示文件读取完毕
            int length = -1;
            while((length = channel.read(bf)) != -1) {
                byte[] bytes = bf.array();
                System.out.println(new String(bytes, 0, length));
                //4、将bf position设置为0，方便下次读取
                bf.clear();
            }
            channel.close();
            fileInputStream.close();
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    private static void writeNio() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/hulingfeng/Desktop/123.pmml");
            FileChannel channel = fileOutputStream.getChannel();
            String string= "1231242342144";
            ByteBuffer bf = ByteBuffer.allocate( 1024 );
            bf.clear();
            //从byteBuffer的position位置填充byte
            bf.put( string.getBytes() );
            bf.flip();
            int length = 0;
            while ( bf.hasRemaining() ) {
                length = channel.write( bf );
                System.out.println( bf );
            }
            channel.close();
            fileOutputStream.close();
        } catch ( IOException e ){
            e.printStackTrace();
        }
    }

    public static void main( String[] args ) {
        StandNioTest.readNio();
        StandNioTest.writeNio();
    }

}
