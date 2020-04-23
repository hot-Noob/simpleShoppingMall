package com.wm.shoppingWeb.controller;

import javax.servlet.http.HttpSession;
import java.util.concurrent.ConcurrentHashMap;

import static com.wm.shoppingWeb.controller.UserConstant.USER_ID;

/**
 * @ClassName SessionContext
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/7 21:28
 * @Version 1.0
 **/
public class SessionContext {
    private static SessionContext instance;
    private ConcurrentHashMap<Integer,HttpSession> sessionMap;
    private int CAPACITY = 16;

    private SessionContext() {
        sessionMap = new ConcurrentHashMap<>(CAPACITY);
    }

    public static SessionContext getInstance() {
        if (instance == null) {
            instance = SessionContextFactory.getSessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null || session.getAttribute(USER_ID) != null) {
            sessionMap.put((Integer) session.getAttribute(USER_ID), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getAttribute(USER_ID));
        }
    }

    public synchronized HttpSession getSession(int userId) {
        if (userId <= 0) {
            return null;
        }
        return sessionMap.get(userId);
    }


    private static class SessionContextFactory {
        public static SessionContext getSessionContext() {
            return new SessionContext();
        }
    }
} 
