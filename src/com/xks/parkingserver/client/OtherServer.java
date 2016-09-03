package com.xks.parkingserver.client;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by ALKL on 2016/9/3.
 */
public class OtherServer {
    public static void main(String [] args){
        IoConnector connector=new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(
                        new TextLineCodecFactory(
                                Charset.forName("UTF-8"),
                                LineDelimiter.WINDOWS.getValue(),
                                LineDelimiter.WINDOWS.getValue()
                        )
                )
        );
        connector.setHandler(new ClientHandler("你好！/r/n 大家好！"));

        connector.connect(new InetSocketAddress("10.13.65.78", 9123));
    }
}
