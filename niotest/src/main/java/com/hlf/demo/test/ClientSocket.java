package com.hlf.demo.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * nio client socket
 * @author hlf
 * @since 2019/07/15 09:51
 */
public class ClientSocket {
    private static ClientSocket clientSocket;
    private static Selector selector;

    public static void main( String[] args ) throws Exception {
        ClientSocket.getInstance().init( "localhost", 8001 ).listen();
    }

    private static ClientSocket getInstance() {
        if ( clientSocket == null ) {
            synchronized ( ClientSocket.class ) {
                if ( clientSocket == null ) {
                    clientSocket = new ClientSocket();
                }
            }
        }
        return clientSocket;
    }

    private ClientSocket init(String ip, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect( new InetSocketAddress( ip, port ) );
        socketChannel.configureBlocking( false );

        selector = Selector.open();
        socketChannel.register( selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ );

        return this;
    }

    private void listen() throws Exception {
        System.out.println( "client start" );
        while( true ) {
            selector.select();

            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
            while( selectionKeys.hasNext() ) {
                SelectionKey selectionKey = selectionKeys.next();
                selectionKeys.remove();

                if ( selectionKey.isConnectable() ) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.configureBlocking( false );
                    ByteBuffer buffer = ByteBuffer.wrap( "hello, this is client".getBytes() );
                    socketChannel.write( buffer );

                    socketChannel.register( selector, SelectionKey.OP_READ );
                    System.out.println( "client write" );
                } else if ( selectionKey.isReadable() ) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    socketChannel.configureBlocking( false );

                    ByteBuffer buffer = ByteBuffer.allocate( 1024 );

                    int length = socketChannel.read( buffer );

                    System.out.println( "client read:" + socketChannel + new String( buffer.array(), 0, length ) );

                    socketChannel.write( ByteBuffer.wrap( ( "client " + System.currentTimeMillis() ).getBytes() ) );

                    Thread.sleep( 10000 );
                }
            }
        }
    }
}
