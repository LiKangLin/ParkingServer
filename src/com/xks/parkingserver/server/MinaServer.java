package com.xks.parkingserver.server;


import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;



/**
 * Created by ALKL on 2016/9/3.
 */
public class MinaServer {

    private static Logger logger = Logger.getLogger(MinaServer.class);   //获取日志信息

    public static void main(String [] args) throws IOException {
        //编写TCPServer,使用IoAcceptor的实现NioSocketAcceptor
        NioSocketAcceptor acceptor = new NioSocketAcceptor();
        //获得一个实例对象
        MyServerHandler myServerHandler = MyServerHandler.getInstance();
        //向IoService 注册IoHandler
        acceptor.setHandler(myServerHandler);

        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter. WINDOWS.getValue())));
        //设置读取缓冲的字节数，但一般不需要调用这个方法，IoProcessor 会自动调整缓冲的大小
     //   acceptor.getSessionConfig().setReadBufferSize(2048);
        //读写操作在10秒内无任何操作则进入空闲状态
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE,10);
        //绑定的套接字端口号
        acceptor.bind(new InetSocketAddress(9123));
        logger.info("------服务启动-------");

    }
}
