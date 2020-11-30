package com.codeworld.fc.monitor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName ActiveUserListener
 * Description TODO
 * Author Lenovo
 * Date 2020/10/16
 * Version 1.0
**/
public class ActiveUserListener implements HttpSessionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActiveUserListener.class);

    public static AtomicInteger userCount = new AtomicInteger(0);



    @Override
    public void sessionCreated(HttpSessionEvent se) {

        userCount.getAndIncrement();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        userCount.getAndDecrement();
    }
}

