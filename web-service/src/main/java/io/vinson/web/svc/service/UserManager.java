package io.vinson.web.svc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class UserManager {

    private static final Logger logger = LoggerFactory.getLogger(UserManager.class);

    public static final String USER_ID = "$userId";

    @Autowired
    private HttpServletRequest request;

    public void login(Integer userId) {
        HttpSession session = getSession();
        session.setAttribute(USER_ID, userId);
        logger.info("userId {} login", userId);
    }

    public void logout() {
        HttpSession session = getSession();
        Integer userId = (Integer) session.getAttribute(USER_ID);
        session.invalidate();
        logger.info("userId {} logout", userId);
    }

    public Integer getUserId() {
        HttpSession session = getSession();
        Integer userId = (Integer) session.getAttribute(USER_ID);
        if(userId == null) {
            return 1;
            //throw new RequestValidateException("没有登录");
        }
        return userId;//userId;
    }

    private HttpSession getSession() {
        return request.getSession();
    }
}

