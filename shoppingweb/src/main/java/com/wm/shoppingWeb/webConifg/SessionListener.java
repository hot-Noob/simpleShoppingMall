package com.wm.shoppingWeb.webConifg;

import com.wm.shoppingWeb.controller.SessionContext;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @ClassName SessionListener
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 21:57
 * @Version 1.0
 **/
public class SessionListener implements HttpSessionListener {
    private SessionContext myc = SessionContext.getInstance();
    private final static int SESSION_MAX_LIVE_TIME = 2 * 60 * 60;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        session.setMaxInactiveInterval(SESSION_MAX_LIVE_TIME);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        myc.delSession(session);
    }

}