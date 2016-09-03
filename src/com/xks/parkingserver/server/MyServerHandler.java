package com.xks.parkingserver.server;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;


/**
 * Created by ALKL on 2016/9/3.
 */
public class MyServerHandler extends IoHandlerAdapter {
    private static Logger logger = Logger.getLogger(MinaServer.class);
    private static MyServerHandler INSTANCE = new MyServerHandler();

    public static MyServerHandler getInstance() {
        return INSTANCE;
    }

    //用于发送消息
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    //用于接收消息
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        super.messageReceived(session, message);
        String str = message.toString();    //message为收到的消息
        logger.info("The message received is [" + str + "]");
        if (str.endsWith("quit")) {
            session.close(true);
            return;
         }
     }

}
