package com.xks.parkingserver.client;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

/**
 * Created by ALKL on 2016/9/3.
 */

public class ClientHandler extends IoHandlerAdapter {
    private Logger logger = Logger.getLogger(OtherServer.class);

    private final String values;
    public ClientHandler(String values) {
        this.values = values;
    }
    @Override
    public void sessionOpened(IoSession session) {
        session.write(values);
    }
}
